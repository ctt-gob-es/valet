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
 * @version 1.5, 01/08/2023.
 */
package es.gob.valet.service.ifaces;

import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;

/**
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the ExternalAccess entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.5, 01/08/2023.
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
	 * Method that test a connection with url and save this test. 
	 * 
	 * @param uriTslLocation parameter that contain url.
	 * @param originUrl paramenter that contain origin url.
	 */
	void testConnExternalAccessAndSaveResult(String uriTslLocation, String originUrl);

	/**
	 * Method that realize test connection to external access and update result in BD. 
	 */
	void realizeTestAndUpdateResult();
	
	/**
	 * Method that extract all distribution point from TSL.
	 * 
	 * @param listUrlDistributionPointDPResult parameter that store all url valid who distribution point.
	 * @param listUrlIssuerResult parameter that store all url valid who issuer.
	 * @param listUrlDistributionPointCRLResult parameter that store all url valid who CRL.
	 * @param listUrlDistributionPointOCSPResult parameter that store all url valid who OCSP.
	 * @param tslObject TSL object representation to use.
	 * @throws TSLCertificateValidationException if occurs any error.
	 */
	void extractUrlToDistributionPoints(List<String> listUrlDistributionPointDPResult, List<String> listUrlIssuerResult, List<String> listUrlDistributionPointCRLResult, List<String> listUrlDistributionPointOCSPResult, ITSLObject tslObject) throws TSLCertificateValidationException;
	
	/**
	 * Method that iterate all url obtains from distribution points.
	 * 
	 * @param listUrlDistributionPointDPResult parameter that store all url valid who distribution point.
	 * @param listUrlIssuerResult parameter that store all url valid who issuer.
	 * @param listUrlDistributionPointCRLResult parameter that store all url valid who CRL.
	 * @param listUrlDistributionPointOCSPResult parameter that store all url valid who OCSP.
	 */
	void iterateAllUrl(List<String> listUrlDistributionPointDPResult, List<String> listUrlIssuerResult, List<String> listUrlDistributionPointCRLResult, List<String> listUrlDistributionPointOCSPResult);
	
}
