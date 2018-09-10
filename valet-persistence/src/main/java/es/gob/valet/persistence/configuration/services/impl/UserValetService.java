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
 * <b>File:</b><p>es.gob.valet.service.impl.UserValetService.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 15 jun. 2018.
 */
package es.gob.valet.persistence.configuration.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.model.repository.UserValetRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.UserValetDataTablesRepository;
import es.gob.valet.persistence.configuration.services.ifaces.IUserValetService;

/** 
 * <p>Class that implements the communication with the operations of the persistence layer.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 15 jun. 2018.
 */
@Service
public class UserValetService implements IUserValetService{
	
	/**
	 * Attribute that represents the injected interface that proves CRUD operations for the persistence.
	 */
@Autowired
private UserValetRepository repository;

/**
 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
 */
@Autowired
private UserValetDataTablesRepository dtRepository;


/**
 * {@inheritDoc}
 * @see es.gob.valet.persistence.configuration.services.ifaces.IUserValetService#getUserValetById(java.lang.Long)
 */
@Override
public UserValet getUserValetById(Long userId) {
	return repository.findByIdUserValet(userId);
}

/**
 * {@inheritDoc}
 * @see es.gob.valet.persistence.configuration.services.ifaces.IUserValetService#saveUserValet(es.gob.valet.persistence.configuration.model.entity.UserValet)
 */
@Override
public UserValet saveUserValet(UserValet user) {
	return repository.save(user);
	
}

/**
 * {@inheritDoc}
 * @see es.gob.valet.persistence.configuration.services.ifaces.IUserValetService#deleteUserValet(java.lang.Long)
 */
@Override
public void deleteUserValet(Long userId) {
	repository.deleteById(userId);
	
}

/**
 * {@inheritDoc}
 * @see es.gob.valet.persistence.configuration.services.ifaces.IUserValetService#getAllUserValet()
 */
@Override
public Iterable<UserValet> getAllUserValet() {
	return repository.findAll();
}

/**
 * {@inheritDoc}
 * @see es.gob.valet.persistence.configuration.services.ifaces.IUserValetService#getUserValetByLogin(java.lang.String)
 */
@Override
public UserValet getUserValetByLogin(String login) {
	return repository.findByLogin(login);
}

/**
 * {@inheritDoc}
 * @see es.gob.valet.persistence.configuration.services.ifaces.IUserValetService#findAll(org.springframework.data.jpa.datatables.mapping.DataTablesInput)
 */
@Override
public DataTablesOutput<UserValet> findAll(DataTablesInput input) {
	
	return dtRepository.findAll(input);
}

}
