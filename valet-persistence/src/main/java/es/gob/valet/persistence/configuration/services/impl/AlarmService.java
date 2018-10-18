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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.AlarmService.java.</p>
 * <b>Description:</b><p>Class that implements the communication with the operations of the persistence layer for Alarm.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>2 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 2 oct. 2018.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.entity.Alarm;
import es.gob.valet.persistence.configuration.model.repository.AlarmRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.AlarmDataTablesRepository;
import es.gob.valet.persistence.configuration.services.ifaces.IAlarmService;

/** 
 * <p>Class that implements the communication with the operations of the persistence layer for Alarm.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 2 oct. 2018.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AlarmService implements IAlarmService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private AlarmRepository repository;

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private AlarmDataTablesRepository dtRepository;

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IAlarmService#getAllAlarm()
	 */
	@Override
	public List<Alarm> getAllAlarm() {
		List<Alarm> result = new ArrayList<Alarm>();
		result = repository.findAll();
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IAlarmService#getAlarmById(java.lang.Long)
	 */
	@Override
	public Alarm getAlarmById(String idAlarm) {
		return repository.findByIdAlarm(idAlarm);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IAlarmService#saveAlarm(es.gob.valet.persistence.configuration.model.entity.Alarm)
	 */
	@Override
	public Alarm saveAlarm(Alarm Alarm) {
		return repository.save(Alarm);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IAlarmService#deleteAlarm(java.lang.Long)
	 */
	@Override
	public void deleteAlarm(Long idAlarm) {
		repository.deleteById(idAlarm);
	}

	@Override
	public DataTablesOutput<Alarm> getAllAlarm(DataTablesInput input) {
		return dtRepository.findAll(input);

	}

}
