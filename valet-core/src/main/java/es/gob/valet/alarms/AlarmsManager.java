/*
/*******************************************************************************
 * Copyright (C) 2018 MINHAFP, Gobierno de España
 * This program is licensed and may be used, modified and redistributed under the  terms
 * of the European Public License (EUPL), either version 1.1 or (at your option)
 * any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and
 * more details.
 * You should have received a copy of the EUPL1.1 license
 * along with this program; if not, you may find it at
 * http:joinup.ec.europa.eu/software/page/eupl/licence-eupl
 ******************************************************************************/

/**
 * <b>File:</b><p>es.gob.valet.alarms.AlarmsManager.java.</p>
 * <b>Description:</b><p>Class that manages the events of all the alarms. This class represents
 * the general manager of alarms module and provides the necessary logic interface
 * for the other modules can interact with this one.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.4, 19/09/2023.
 */
package es.gob.valet.alarms;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.alarms.conf.AlarmsConfiguration;
import es.gob.valet.alarms.exception.AlarmException;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsGrayLog;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreGeneralMessages;
import es.gob.valet.quartz.task.Task;
import es.gob.valet.tasks.HiddenTasksManager;
import es.gob.valet.tasks.TempBlockedAlarmTask;
import es.gob.valet.utils.threads.EMailException;
import es.gob.valet.utils.threads.EMailTimeLimitedOperation;

/**
 * <p>Class that manages the events of all the alarms. This class represents
 * the general manager of alarms module and provides the necessary logic interface
 * for the other modules can interact with this one.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4, 19/09/2023.
 */
public final class AlarmsManager {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(AlarmsManager.class);

	/**
	 * Constant attribute that represents the unique instance of this class.
	 */
	private static AlarmsManager instance = null;

	/**
	 * Constructor method for the class AlarmsManager.java.
	 */
	private AlarmsManager() {
		super();
	}

	/**
	 * Gets the unique instance of this class.
	 * @return the unique instance of this class.
	 */
	public static AlarmsManager getInstance() {
		if (instance == null) {
			instance = new AlarmsManager();
		}
		return instance;
	}

	/**
	 * Method that register an alarm event and send the email notification or save it for later.
	 * @param alarmId Alarm identificator.
	 * @param alarmMsg Alarm Message.
	 */
	public synchronized void registerAlarmEvent(String alarmId, String alarmMsg) {
		LOGGER.info(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_018, new Object[]{alarmId, alarmMsg}));

		// Comprobamos que los parámetros de entrada son correctos.
		if (checkParametersBeforeRegisterAlarm(alarmId, alarmMsg)) {

			// Registramos la alarma en GrayLog si así está configurado.
			UtilsGrayLog.writeMessageInGrayLog(UtilsGrayLog.LEVEL_ERROR, alarmId, null, alarmMsg);

			// Comprobamos si la alarma está habilitada...
			if (AlarmsConfiguration.getInstance().isAlarmEnabled(alarmId)) {

				// Comprobamos si esta alarma debe bloquearse (o podría
				// estarlo).
				if (AlarmsConfiguration.getInstance().getBlockTimeInMilliseconds(alarmId) > 0) {

					// Registramos la alarma bloqueándola o añadiendo un mensaje
					// nuevo
					// para el momento del desbloqueo.
					doAlarmOperation(ALARM_OP_ADD_ALARM_EVENT, alarmId, alarmMsg);

				} else {

					// Al no ser necesario gestionar bloqueos en esta alarma,
					// simplemente se manda el e-mail.
					try {
						sendEMailAlarm(alarmId, alarmMsg, false);
					} catch (AlarmException e) {
						LOGGER.error(Language.getResCoreGeneral(CoreGeneralMessages.ALARM_MNG_000), e);
					}

				}

			} else {

				LOGGER.warn(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_001, new Object[ ] { alarmId }));

			}

		}

	}

	/**
	 * Checks if the input parameters are valid for register an alarm.
	 * @param alarmId Alarm identificator.
	 * @param alarmMsg Alarm message.
	 * @return <code>true</code> if the parameters are valid, otherwise false.
	 */
	private boolean checkParametersBeforeRegisterAlarm(String alarmId, String alarmMsg) {

		if (UtilsStringChar.isNullOrEmptyTrim(alarmId)) {
			LOGGER.error(Language.getResCoreGeneral(CoreGeneralMessages.ALARM_MNG_002));
			return false;
		}

		if (UtilsStringChar.isNullOrEmptyTrim(alarmMsg)) {
			LOGGER.error(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_003, new Object[ ] { alarmId }));
			return false;
		}

		return true;

	}

	/**
	 * Send an e-mail for the input alarm and message only if the alarm has at least one
	 * destination address defined.
	 * @param alarmId Alarm identificator.
	 * @param alarmMsg Alarm message.
	 * @param addBlockedInformation Flag that indicates if it is necessary to add a reference in the
	 * body message showing that the alarm is going to be blocked for a time period.
	 * @throws AlarmException In case of some error sending the e-mail.
	 */
	private void sendEMailAlarm(String alarmId, String alarmMsg, boolean addBlockedInformation) throws AlarmException {

		// Obtenemos el listado de destinatarios.
		List<String> destinationAddressesList = AlarmsConfiguration.getInstance().getListDestinationAddresses(alarmId);

		// Si la lista es vacía o nula, no mandamos el mensaje.
		if (destinationAddressesList == null || destinationAddressesList.isEmpty()) {

			LOGGER.warn(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_004, new Object[ ] { alarmId }));

		} else {

			// Construimos el asunto del correo.
			String subject = Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_016, new Object[ ] { StaticValetConfig.getProperty(StaticValetConfig.INSTANCE_NAME_ID), alarmId, AlarmsConfiguration.getInstance().getDescriptionAlarm(alarmId) });

			// Construimos el cuerpo inicial del correo.
			String bodyInitialMessage = UtilsDate.getSystemDate(UtilsDate.FORMAT_DATE_TIME_STANDARD);

			try {
				// Creamos la instancia que gestiona el envío del mensaje como
				// hilo independiente (con tiempo de vida máximo).
				EMailTimeLimitedOperation etlo = new EMailTimeLimitedOperation(destinationAddressesList, subject, bodyInitialMessage);

				// Terminamos de construir el body.
				etlo.appendToBodyMessage(UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
				etlo.appendToBodyMessage(UtilsStringChar.SYMBOL_HYPHEN_STRING);
				etlo.appendToBodyMessage(UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
				etlo.appendToBodyMessageWithNewLine(alarmMsg);

				// Si hay que añadir la indicación de alarma bloqueada...
				if (addBlockedInformation) {
					etlo.appendToBodyMessageWithNewLine(UtilsStringChar.EMPTY_STRING);
					etlo.appendToBodyMessageWithNewLine(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_005, new Object[ ] { alarmId, StaticValetConfig.getProperty(StaticValetConfig.INSTANCE_NAME_ID), AlarmsConfiguration.getInstance().getBlockTimeInMilliseconds(alarmId) }));
				}

				// Se envía el mensaje.
				etlo.startOperation();
			} catch (EMailException e) {
				throw new AlarmException(ValetExceptionConstants.COD_201, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_006, new Object[ ] { alarmId }), e);
			}

		}

	}

	/**
	 * Constant attribute that represents the alarm operation: Add event of an alarm that could be blocked (if not already).
	 */
	private static final int ALARM_OP_ADD_ALARM_EVENT = 0;

	/**
	 * Constant Attribute that represents the alarm operation: Collect all the data, send the e-mail and remove from blocked alarm.
	 */
	private static final int ALARM_OP_REMOVE_ALARM_BLOCK_AND_SEND_MAIL = 1;

	/**
	 * Private method that synchronizes some operations related with alarms to avoid conflicts.
	 * @param operation The valid operations are:
	 * <ul>
	 * 	<li>{@link #ALARM_OP_ADD_ALARM_EVENT}: Add an event for the specified alarm and blocks it if not is blocked yet.</li>
	 *  <li>{@link #ALARM_OP_REMOVE_ALARM_BLOCK_AND_SEND_MAIL}: Remove the block for the specified alarm, and send the email with the events added.</li>
	 * </ul>
	 * @param alarmId Alarm identificator.
	 * @param alarmMsg Alarm message.
	 */
	private synchronized void doAlarmOperation(int operation, String alarmId, String alarmMsg) {

		switch (operation) {
			case ALARM_OP_ADD_ALARM_EVENT:
				try {
					addAlarmThatCouldBeBlockedEvent(alarmId, alarmMsg);
				} catch (AlarmException e) {
					LOGGER.error(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_007, new Object[ ] { alarmId }), e);
				}
				break;
			case ALARM_OP_REMOVE_ALARM_BLOCK_AND_SEND_MAIL:
				removeAlarmBlockAndSendMail(alarmId);
				break;
			default:
				LOGGER.error(Language.getResCoreGeneral(CoreGeneralMessages.ALARM_MNG_008));
				break;
		}

	}

	/**
	 * Attribute that represents a map with the relation between alarm identificator (of a blocked alarm)
	 * and the mail that is grouping all the message events while is blocked the alarm.
	 */
	private Map<String, EMailTimeLimitedOperation> alarmBlockedGroupEventMailMap = new HashMap<String, EMailTimeLimitedOperation>();

	/**
	 * Adds an event for a specified alarm. If this is not blocked, then send a e-mail notifying the event and after that blocks the alarm.
	 * If this is already blocked, then is addeda message to the mail that will be send when the block period expired.
	 * @param alarmId Alarm identificator.
	 * @param alarmMsg Alarm Message.
	 * @throws AlarmException In case of some error building the email for the alarm summary while it is blocked.
	 */
	private void addAlarmThatCouldBeBlockedEvent(String alarmId, String alarmMsg) throws AlarmException {

		// Comprobamos si actualmente la alarma ya está bloqueada.
		if (alarmBlockedGroupEventMailMap.containsKey(alarmId)) {

			// Obtenemos el correo que se está construyendo.
			EMailTimeLimitedOperation etlo = alarmBlockedGroupEventMailMap.get(alarmId);

			// Añadimos el mensaje del nuevo evento.
			etlo.appendToBodyMessage(UtilsDate.getSystemDate(UtilsDate.FORMAT_DATE_TIME_STANDARD));
			etlo.appendToBodyMessage(UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
			etlo.appendToBodyMessage(UtilsStringChar.SYMBOL_HYPHEN_STRING);
			etlo.appendToBodyMessage(UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
			etlo.appendToBodyMessageWithNewLine(alarmMsg);

		}
		// Si la alarma aún no está bloqueada...
		else {

			// Obtenemos el listado de destinatarios.
			List<String> destinationAddressesList = AlarmsConfiguration.getInstance().getListDestinationAddresses(alarmId);

			// Si la lista es vacía o nula, no mandamos el mensaje.
			if (destinationAddressesList == null || destinationAddressesList.isEmpty()) {

				LOGGER.warn(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_009, new Object[ ] { alarmId }));

			} else {

				// Enviamos la notificación de la alarma y el aviso de bloqueo.
				try {
					sendEMailAlarm(alarmId, alarmMsg, true);
				} catch (AlarmException e) {
					LOGGER.error(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_010, new Object[ ] { alarmId }), e);
				}

				// Construimos el asunto del correo.
				String subject = Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_011, new Object[ ] { StaticValetConfig.getProperty(StaticValetConfig.INSTANCE_NAME_ID), alarmId, AlarmsConfiguration.getInstance().getDescriptionAlarm(alarmId) });

				// Construimos el cuerpo inicial del correo.
				String bodyInitialMessage = UtilsDate.getSystemDate(UtilsDate.FORMAT_DATE_TIME_STANDARD);

				try {

					// Creamos el nuevo EMail en el que iremos acumulando los
					// eventos siguientes de esta
					// alarma hasta que se libere el bloqueo.
					EMailTimeLimitedOperation etlo = new EMailTimeLimitedOperation(destinationAddressesList, subject, bodyInitialMessage);

					// Terminamos de construir el body.
					etlo.appendToBodyMessage(UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
					etlo.appendToBodyMessage(UtilsStringChar.SYMBOL_HYPHEN_STRING);
					etlo.appendToBodyMessage(UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
					etlo.appendToBodyMessageWithNewLine(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_012, new Object[ ] { AlarmsConfiguration.getInstance().getBlockTimeInMilliseconds(alarmId) }));

					// Lo añadimos al map.
					alarmBlockedGroupEventMailMap.put(alarmId, etlo);

				} catch (EMailException e) {
					throw new AlarmException(ValetExceptionConstants.COD_201, Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_013, new Object[ ] { alarmId }), e);
				}

				// Activamos la tarea que desbloqueará la alarma.
				buildTaskToUnblockTheAlarm(alarmId);

			}

		}

	}

	/**
	 * Removes the blockade of an alarm (if this is blocked) and send the email with all the notifications.
	 * @param alarmId Alarm identificator.
	 */
	private void removeAlarmBlockAndSendMail(String alarmId) {

		// Recuperamos el correo que reune la información acumulada de eventos
		// de la alarma.
		EMailTimeLimitedOperation etlo = alarmBlockedGroupEventMailMap.remove(alarmId);

		// Si lo hemos recuperado...
		if (etlo != null) {

			// Le añadimos el mensaje de cierre al correo.
			etlo.appendToBodyMessage(UtilsDate.getSystemDate(UtilsDate.FORMAT_DATE_TIME_STANDARD));
			etlo.appendToBodyMessage(UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
			etlo.appendToBodyMessage(UtilsStringChar.SYMBOL_HYPHEN_STRING);
			etlo.appendToBodyMessage(UtilsStringChar.SPECIAL_BLANK_SPACE_STRING);
			etlo.appendToBodyMessageWithNewLine(Language.getResCoreGeneral(CoreGeneralMessages.ALARM_MNG_014));

			// Enviamos el correo.
			etlo.startOperation();

		} else {

			LOGGER.warn(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_015, new Object[ ] { alarmId }));

		}

	}

	/**
	 * This method finish the blockade of an alarm and send the email with all the related events.
	 * @param alarmId Alarm identificator.
	 */
	public void finishAlarmBlockade(String alarmId) {

		doAlarmOperation(ALARM_OP_REMOVE_ALARM_BLOCK_AND_SEND_MAIL, alarmId, null);

	}

	/**
	 * Builds a task to unblock an alarm.
	 * @param alarmId Alarm that must be unblocked in the task.
	 */
	private void buildTaskToUnblockTheAlarm(String alarmId) {

		long millisecondsToAdd = AlarmsConfiguration.getInstance().getBlockTimeInMilliseconds(alarmId);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MILLISECOND, Long.valueOf(millisecondsToAdd).intValue());
		Date dateToSendMailAndUnblockAlarm = cal.getTime();

		String taskName = TempBlockedAlarmTask.class.getSimpleName() + UtilsStringChar.SYMBOL_HYPHEN_STRING + alarmId;

		try {
			@SuppressWarnings("unchecked")
			Class<Task> taskClass = (Class<Task>) Class.forName(TempBlockedAlarmTask.class.getName());
			Map<String, Object> dataForTheTask = new HashMap<String, Object>();
			dataForTheTask.put(TempBlockedAlarmTask.KEY_ALARM_ID, alarmId);
			HiddenTasksManager.addOrUpdateHiddenTask(taskName, taskClass, dateToSendMailAndUnblockAlarm, 0l, dataForTheTask);
		} catch (ClassNotFoundException e) {
			LOGGER.error(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALARM_MNG_017, new Object[ ] { alarmId }), e);
		}

	}

}
