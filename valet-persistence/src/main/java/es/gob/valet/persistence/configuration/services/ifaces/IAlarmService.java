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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.IAlarmService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer
 * in relation of the Alarm entity.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>2 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 2 oct. 2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.entity.Alarm;

/** 
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the Alarm entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 2 oct. 2018.
 */
public interface IAlarmService {

	/**
	 *  Gets the list of alarms.
	 * 
	 * @return List of alarms.
	 */
	List<Alarm> getAllAlarm();

	/**
	 * Method that gets alarm by ID of Alarm.
	 * @param idAlarm Id of Alarm
	 * @return {@link Alarm} an object that represents the Alarm.
	 */
	Alarm getAlarmById(String idAlarm);

	/**
	* Method that saves Alarm.
	* @param Alarm to update.
	* @return {@link Alarm} an object that represents the Alarm.
	*/
	Alarm saveAlarm(Alarm alarm);

	/**
	 * Method that delete a Alarm.
	 * @param idAlarm Id of Alarm
	 */
	void deleteAlarm(Long idAlarm);

	/**
	 * TODO
	 * 
	 * @param input
	 * @return
	 */
	DataTablesOutput<Alarm> getAllAlarm(DataTablesInput input);

}
