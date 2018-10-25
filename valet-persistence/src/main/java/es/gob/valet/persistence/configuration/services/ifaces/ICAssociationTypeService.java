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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer related to CAssociationType.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>22/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 25/10/2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import java.util.List;

import es.gob.valet.persistence.configuration.model.entity.CAssociationType;

/**
 * <p>Interface that provides communication with the operations of the persistence layer related to CAssociationType.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 25/10/2018.
 */
public interface ICAssociationTypeService {

	/**
	 * Method that gets the list of all association type.
	 * @return List of type of mapping associations.
	 */
	List<CAssociationType> getAllAssociationType();
	
	/**
	 * Method that obtains a CAssociationType by its identifier. 
	 * 
	 * @param idCAssociationType The association type identifier.
	 * @return {@link CAssociationType} an object that represents the CAssociationType.
	 */
	CAssociationType getAssociationTypeById(Long idCAssociationType);

}
