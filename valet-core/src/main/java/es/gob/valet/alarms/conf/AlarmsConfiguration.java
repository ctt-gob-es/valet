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
 * <b>File:</b><p>es.gob.valet.alarms.conf.AlarmsConfiguration.java.</p>
 * <b>Description:</b><p>Class that manages the information relating to the alarms that are stored in the configuration.
 * It provides the whole necessary functionality to consult and set this information.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/01/2019.
 */
package es.gob.valet.alarms.conf;

import java.util.ArrayList;
import java.util.List;

import es.gob.valet.i18n.Language;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.Mail;

/**
 * <p>Class that manages the information relating to the alarms that are stored in the configuration.
 * It provides the whole necessary functionality to consult and set this information.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/01/2019.
 */
public final class AlarmsConfiguration {

	/**
	 * Attribute that represents the unique instance of this class.
	 */
	private static AlarmsConfiguration instance = null;

	/**
	 * Constructor method for the class AlarmsConfiguration.java.
	 */
	public AlarmsConfiguration() {
		super();
	}

	/**
	 * Returns the unique instance of {@link AlarmsConfiguration}.
	 * @return The unique instance of {@link AlarmsConfiguration}.
	 */
	public static AlarmsConfiguration getInstance() {

		// Inicializamos el objeto a devolver si fuera necesario.
		if (instance == null) {
			instance = new AlarmsConfiguration();
		}
		return instance;

	}

	/**
	 * Checks if at the moment, the input alarm is enabled/active.
	 * @param alarmId Alarm identificator to check.
	 * @return <code>true</code> if at the moment, the alarm is considered enabled/active.
	 */
	public boolean isAlarmEnabled(String alarmId) {
		return ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getAlarmService().getAlarmById(alarmId).getActive().booleanValue();
	}

	/**
	 * Gets the time (in milliseconds) that must be blocked the alarm.
	 * @param alarmId Alarm identificator from which consult the information.
	 * @return the time (in milliseconds) that must be blocked the alarm or zero it must not be blocked.
	 */
	public long getBlockTimeInMilliseconds(String alarmId) {
		Long time = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getAlarmService().getAlarmById(alarmId).getTimeBlock();
		long result = time == null ? 0l : time.longValue() < 0l ? 0 : time.longValue();
		return result;
	}

	/**
	 * Gets the list of destination addresses associated to the input alarm.
	 * @param alarmId Alarm identificator.
	 * @return the list of destination addresses associated to the input alarm. <code>null</code> if
	 * there is no addresses asssigned to this alarm.
	 */
	public List<String> getListDestinationAddresses(String alarmId) {

		List<String> result = null;

		List<Mail> mailList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getAlarmService().getAlarmById(alarmId).getMails();
		if (mailList != null && !mailList.isEmpty()) {

			result = new ArrayList<String>(mailList.size());
			for (Mail mail: mailList) {
				result.add(mail.getEmailAddress());
			}

		}

		return result;

	}

	/**
	 * Gets the description associated to the input alarm.
	 * @param alarmId Alarm identificator.
	 * @return the description associated to the input alarm.
	 */
	public String getDescriptionAlarm(String alarmId) {

		return Language.getResPersistenceConstants(ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getAlarmService().getAlarmById(alarmId).getDescription());

	}

}
