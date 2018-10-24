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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.KeystoreService.java.</p>
 * <b>Description:</b><p>Class that implements the communication with the operations of the persistence layer for Keystore.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.repository.KeystoreRepository;
import es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for Keystore.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/09/2018.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KeystoreService implements IKeystoreService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private KeystoreRepository repository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getAllKeystore()
	 */
	@Override
	public List<Keystore> getAllKeystore() {
		List<Keystore> listKeystore = new ArrayList<Keystore>();
		listKeystore = repository.findAll();
		return listKeystore;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getNameKeystoreById(java.lang.Long)
	 */
	public String getNameKeystoreById(Long idKeystore) {
		String nameKeystore = null;
		Keystore keystore = repository.findByIdKeystore(idKeystore);
		if (keystore != null) {
			nameKeystore = keystore.getName();
		}
		return nameKeystore;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getKeystoreById(java.lang.Long)
	 */
	@Override
	public Keystore getKeystoreById(Long idKeystore) {
		return repository.findByIdKeystore(idKeystore);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#saveKeystore(es.gob.valet.persistence.configuration.model.entity.Keystore)
	 */
	@Override
	public Keystore saveKeystore(Keystore keystore) {
		return repository.save(keystore);
	}

}
