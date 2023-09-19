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
 * <b>File:</b><p>es.gob.valet.tasks.TempBlockedAlarmTask.java.</p>
 * <b>Description:</b><p>Class that represents a task to send an accumulative mail for a blocked alarm.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.2, 19/09/2023.
 */
package es.gob.valet.tasks;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.alarms.AlarmsManager;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreTasksMessages;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.quartz.task.Task;

/**
 * <p>Class that represents a task to send an accumulative mail for a blocked alarm.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 19/09/2023.
 */
public class TempBlockedAlarmTask extends Task {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(TempBlockedAlarmTask.class);

	/**
	 * Constant attribute that represents the key for the alarm identifier in the shared map.
	 */
	public static final String KEY_ALARM_ID = "KEY_ALARM_ID";

	/**
	 * Attribute that represents the target alarm identifier for this task.
	 */
	private transient String alarmId = null;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#initialMessage()
	 */
	@Override
	protected void initialMessage() {
		LOGGER.info(Language.getFormatResCoreTasks(CoreTasksMessages.TEMP_BLOCKED_ALARM_000, new Object[ ] { alarmId }));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#doActionOfTheTask()
	 */
	@Override
	protected void doActionOfTheTask() throws Exception {

		try {
			AlarmsManager.getInstance().finishAlarmBlockade(alarmId);
			LOGGER.info(Language.getFormatResCoreTasks(CoreTasksMessages.TEMP_BLOCKED_ALARM_003, new Object[ ] { alarmId }));
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResCoreTasks(CoreTasksMessages.TEMP_BLOCKED_ALARM_002, new Object[ ] { alarmId }));
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#endMessage()
	 */
	@Override
	protected void endMessage() {
		LOGGER.info(Language.getFormatResCoreTasks(CoreTasksMessages.TEMP_BLOCKED_ALARM_001, new Object[ ] { alarmId }));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.job.AbstractValetTaskQuartzJob#prepareParametersForTheTask(java.util.Map)
	 */
	@Override
	protected void prepareParametersForTheTask(Map<String, Object> dataMap) throws TaskValetException {

		// Recuperamos el identificador de la alarma.
		alarmId = (String) dataMap.get(KEY_ALARM_ID);

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.job.AbstractValetTaskQuartzJob#getDataResult()
	 */
	@Override
	protected Map<String, Object> getDataResult() throws TaskValetException {

		// Creamos el map.
		Map<String, Object> result = new HashMap<String, Object>();
		// Incluimos el identificador de la alarma.
		result.put(KEY_ALARM_ID, alarmId);
		// Devolvemos el map.
		return result;

	}

}
