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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.SystemCertificateService.java.</p>
 * <b>Description:</b><p> Class that implements the communication with the operations of the persistence layer for SystemCertificate.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 06/11/2018.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.repository.SystemCertificateRepository;
import es.gob.valet.persistence.configuration.model.repository.ExternalAccessRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.SystemCertificateDataTablesRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.ExternalAccessTablesRepository;
import es.gob.valet.persistence.configuration.model.specification.KeystoreSpecification;
import es.gob.valet.persistence.configuration.services.ifaces.ISystemCertificateService;
import es.gob.valet.persistence.configuration.services.ifaces.IExternalAccessService;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for ExternalAccess.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 06/11/2018.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ExternalAccessService implements IExternalAccessService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private ExternalAccessRepository repository;

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private ExternalAccessTablesRepository dtRepository;


	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	@Override
	public Iterable<ExternalAccess> getAll(Keystore keystore) {
		return repository.findAll();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IExternalAccessService#getUrlDataById(java.lang.Long)
	 */
	@Override
	public ExternalAccess getUrlDataById(Long idUrlData) {
		return repository.findByIdUrl(idUrlData);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IExternalAccessService#getDataUrlByUrl(java.lang.String)
	 */
	@Override
	public ExternalAccess getDataUrlByUrl(String url) {
		return repository.findByUrl(url);

	}


}
