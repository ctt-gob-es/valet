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
 * <b>File:</b><p>es.gob.valet.service.ifaces.IExternalAccessService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer
 * in relation of the ExternalAccess entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/07/2023.</p>
 * @author Gobierno de España.
 * @version 2.0, 19/12/2023.
 */
package es.gob.valet.service.ifaces;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;

/**
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the ExternalAccess entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 2.0, 19/12/2023.
 */
public interface IExternalAccessService {
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	DataTablesOutput<ExternalAccess> getAll(DataTablesInput input);

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getUrlDataById(java.lang.Long)
	 */
	ExternalAccess getUrlDataById(Long idUrlData);

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getDataUrlByUrl(java.lang.String)
	 */
	ExternalAccess getDataUrlByUrl(String url);

	/**
	 * Method that realize test connection to external access and update result in BD. 
	 */
	void prepareUrlExternalAccessForTask();
	
	/**
	 * Method that extract all distribution point from TSL.
	 * 
	 * @param externalAccessDTO parameter that contain all information about operation.
	 * @param tslObject TSL object representation to use.
	 * @throws TSLCertificateValidationException if occurs any error.
	 */
	void extractUrlToDistributionPoints(ExternalAccessDTO externalAccessDTO, ITSLObject tslObject) throws TSLCertificateValidationException;
	
	/**
	 * Method that make changes in external access. We can save/update externall access or delete. Here we will check for indeterminism over operations on the EXTERNAL_ACCCESS table.
	 * 
	 * @param externalAccessDTO parameter that contain all information about operation.
	 * @param action parameter that contain action to realize.
	 */
	void makeChangesToExternalAccess(ExternalAccessDTO externalAccessDTO, String action);

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	List<ExternalAccess> getAllList(ExternalAccess request, Date fromDate, Date toDate);
	
	/**
	 * Method that syncronize all operations about EXTERNAL_ACCESS table.
	 * 
	 * @param operation parameter that contain number operation.
	 * @param tslObject TSL object representation to use.
	 * @param listIdUrl parameter that contain id for realize test connection.
	 * @param externalAccess parameter that contain the object of ExternalAccessDTO
	 */
	void operationsOnExternalAccess(int operation, TSLObject tslObject, List<Long> listIdUrl);
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	List<ExternalAccessDTO> getAllListDTOByFilter(ExternalAccess request, Date fromDate, Date toDate);
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	List<ExternalAccessDTO> getListDTObyId(List<Long> ids);

	/**
	 * Method that realize test of connection url and save result operation.
	 * 
	 * @param uriTslLocation parameter that contain url.
	 * @param originUrl the originUrl to set
	 * @param externalAccessDTO parameter that contain all information about operation.
	 * @return object persist in BD.
	 */
	ExternalAccess getExternalAccessAndTestConn(String uriTslLocation, String originUrl,
			ExternalAccessDTO externalAccessDTO);

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	List<ExternalAccessDTO> getAllListDTO();

	/**
	 * Method that return the value of message error in connection url.
	 * @return the messageError
	 */
	String getMessageErrorValue();

	/**
	 * Method that realize test of connection url and save result operation.
	 * 
	 * @param uriTslLocation parameter that contain url.
	 * @param originUrl the originUrl to set
	 * @param externalAccessDTO parameter that contain all information about operation.
	 * @return object persist in BD.
	 */
	ExternalAccess getExternalAccessTestConnAndSave(String uriTslLocation, String originUrl,
			ExternalAccessDTO externalAccessDTO);
}
