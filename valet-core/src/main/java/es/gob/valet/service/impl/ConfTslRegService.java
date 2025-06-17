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
 * <b>File:</b><p>es.gob.valet.service.impl.ConfTslRegService.java.</p>
 * <b>Description:</b><p>Class that implements the communication with the operations of the persistence layer for configured a tsl registration.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 17/06/2025.
 */
package es.gob.valet.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.persistence.configuration.model.dto.ConfTslRegDTO;
import es.gob.valet.persistence.configuration.model.entity.ConfTslReg;
import es.gob.valet.persistence.configuration.model.repository.ConfTslRegRepository;
import es.gob.valet.service.ifaces.IConfTslRegService;

/** 
 * <p>Class that implements the communication with the operations of the persistence layer for configured a tsl registration.</p>
 * <b>Project:</b><p>Class that implements the communication with the operations of the persistence layer for configured a tsl registration.</p>
 * @version 1.0, 17/06/2025.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ConfTslRegService implements IConfTslRegService {

	/**
	 * Repository for accessing the registered TSL configuration data.
	 *
	 * <p>Provides CRUD operations on the registered TSL configuration entity.</p>
	 */
	@Autowired
	private ConfTslRegRepository confTslRegRepository;
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IConfTslRegService#obtainConfTslReg()
	 */
	@Override
	public ConfTslRegDTO obtainConfTslReg() {
		ConfTslReg confTslReg = confTslRegRepository.findAll(PageRequest.of(0, 1)).stream().findFirst().orElse(null);
		
		ConfTslRegDTO confTslRegDTO = new ConfTslRegDTO(confTslReg); 
		
		return confTslRegDTO;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IConfTslRegService#saveConfTslReg(es.gob.valet.persistence.configuration.model.dto.ConfTslRegDTO)
	 */
	@Override
	public void saveConfTslReg(ConfTslRegDTO confTslRegDTO) {
		ConfTslReg confTslReg = confTslRegRepository.findAll(PageRequest.of(0, 1)).stream().findFirst().orElse(new ConfTslReg());
		
		confTslReg.setTslRegEnabled(confTslRegDTO.getTslRegEnabled());
		
		if(confTslRegDTO.getIdModeReg() != NumberConstants.NUM_NEG_1) {
			confTslReg.setModeRegTokenName("MODE_TSL_REG_" + confTslRegDTO.getIdModeReg());
		} else {
			confTslReg.setModeRegTokenName(null);
		}
		
		if(confTslRegDTO.getIdTypeFilterReg() != NumberConstants.NUM_NEG_1) {
			confTslReg.setTypeFilterRegTokenName("TYPE_FILTER_REG_" + confTslRegDTO.getIdTypeFilterReg());
		} else {
			confTslReg.setTypeFilterRegTokenName(null);
		}
		
		confTslRegRepository.save(confTslReg);
	}

	
}
