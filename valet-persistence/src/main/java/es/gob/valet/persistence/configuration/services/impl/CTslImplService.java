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
 * <b>File:</b><p>es.gob.valet.service.impl.CTSLImplServiceImpl.java.</p>
 * <b>Description:</b><p>Class that implements the communication with the operations of the persistence layer for CTslImpl.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17 jul. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 17 jul. 2018.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import es.gob.valet.persistence.configuration.model.entity.CTslImpl;
import es.gob.valet.persistence.configuration.model.repository.CTslImplRepository;
import es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService;

/** 
 * <p>Class that implements the communication with the operations of the persistence layer for CTslImpl.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 17 jul. 2018.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CTslImplService implements ICTslImplService {

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private CTslImplRepository repository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService#getAllCTSLImpl()
	 */
	public Iterable<CTslImpl> getAllCTSLImpl() {
		return repository.findAll();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService#getCTSLImpById(java.lang.Long)
	 */
	public CTslImpl getCTSLImpById(Long id) {
		return repository.findByIdCTSLImpl(id);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService#getAllSpecifications()
	 */
	public List<String> getAllSpecifications() {
		List<String> listSpecifications = new ArrayList<String>();
		Map<String, Set<String>> mapSpecVer = getsTSLRelationSpecificatioAndVersion();
		listSpecifications.addAll(mapSpecVer.keySet());
		return listSpecifications;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService#getsTSLRelationSpecificatioAndVersion()
	 */
	public Map<String, Set<String>> getsTSLRelationSpecificatioAndVersion() {
		Map<String, Set<String>> result = new HashMap<String, Set<String>>();
		List<CTslImpl> listCTSLImpl = repository.findAll();

		if (listCTSLImpl != null && !listCTSLImpl.isEmpty()) {
			for (CTslImpl cTSL: listCTSLImpl) {
				// se obtiene el listado de versiones almacenado para la
				// especificación actual
				Set<String> versions = result.get(cTSL.getSpecification());
				// si es nulo, es porque aún no se ha iniciado el listado
				if (versions == null) {
					versions = new TreeSet<String>();
				}
				// añadimos la versión
				versions.add(cTSL.getVersion());
				result.put(cTSL.getSpecification(), versions);
			}
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService#getCTSLImplBySpecificationVersion(java.lang.String, java.lang.String)
	 */
	public CTslImpl getCTSLImplBySpecificationVersion(String specification, String version) {
		CTslImpl result = null;
		List<CTslImpl> listCTSLImpl = repository.findAll();
		if (listCTSLImpl != null && !listCTSLImpl.isEmpty()) {
			for (CTslImpl cTSL: listCTSLImpl) {
				if(cTSL.getSpecification().equals(specification)){
					if(cTSL.getVersion().equals(version)){
						result = cTSL;
						break;
					}
				}
			}
		}

		return result;
	}

}
