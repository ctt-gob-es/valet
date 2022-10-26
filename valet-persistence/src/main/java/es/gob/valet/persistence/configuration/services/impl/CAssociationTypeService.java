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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.CAssociationTypeService.java.</p>
 * <b>Description:</b><p> Class that implements the communication with the operations of the persistence layer for CAssociationType.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>22/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 07/10/2022.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.dto.CAssociationTypeDTO;
import es.gob.valet.persistence.configuration.model.entity.CAssociationType;
import es.gob.valet.persistence.configuration.model.repository.CAssociationTypeRepository;
import es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for CAssociationTypeService.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 07/10/2022.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CAssociationTypeService implements ICAssociationTypeService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private CAssociationTypeRepository repository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService#getAllAssociationType()
	 */
	@Override
	public List<CAssociationType> getAllAssociationType() {
	 return repository.findAll();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService#getAssociationType(java.lang.Long)
	 */
	@Override
	public CAssociationType getAssociationTypeById(Long idCAssociationType) {
		return repository.findByIdAssociationType(idCAssociationType);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService#getAllAssociationTypeDTO()
	 */
	public List<CAssociationTypeDTO> getAllAssociationTypeDTO() {
		List<CAssociationType> listCAssociationType = repository.findAll();
		List<CAssociationTypeDTO> listCAssociationTypeDTO = new ArrayList<>();
		for (CAssociationType cAssociationType : listCAssociationType) {
			CAssociationTypeDTO cAssociationTypeDTO = new CAssociationTypeDTO(cAssociationType);
			listCAssociationTypeDTO.add(cAssociationTypeDTO);
		}
		return listCAssociationTypeDTO;
	}
}
