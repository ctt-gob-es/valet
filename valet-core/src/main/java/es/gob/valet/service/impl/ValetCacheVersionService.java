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
 * <b>File:</b><p>es.gob.valet.service.impl.ValetCacheVersionService.java.</p>
 * <b>Description:</b><p>Service implementation for managing valet cache versions. Provides operations to update the cache version in the persistent storage.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/11/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 07/11/2025.
 */
package es.gob.valet.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.persistence.configuration.model.entity.ValetCacheVersion;
import es.gob.valet.persistence.configuration.model.repository.ValetCacheVersionRepository;
import es.gob.valet.service.ifaces.IValetCacheVersionService;

/** 
 * <p>Service implementation for managing valet cache versions. Provides operations to update the cache version in the persistent storage.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 07/11/2025.
 */
@Service
public class ValetCacheVersionService implements IValetCacheVersionService {

	/**
	 * Injects the ValetCacheVersionRepository dependency.
	 * <p>
	 * Used to access and modify valet cache version data in the database.
	 */
	@Autowired
    private ValetCacheVersionRepository valetCacheVersionRepository;
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IValetCacheVersionService#updateCacheVersion()
	 */
	@Override
	public void updateCacheVersion() {
		ValetCacheVersion valetCacheVersion = valetCacheVersionRepository.findByIdValetCacheVersion(NumberConstants.NUM1_LONG);
    	valetCacheVersion.setVersionNumber(valetCacheVersion.getVersionNumber() + NumberConstants.NUM1);
    	valetCacheVersion.setLastUpdate(new Date());
    	valetCacheVersionRepository.save(valetCacheVersion);
	}

}
