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
 * <b>File:</b><p>es.gob.valet.rest.services.ITslRestService.java.</p>
 * <b>Description:</b><p>Interface that represents the TSL restful service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>07/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 07/09/2018.
 */
package es.gob.valet.rest.services;

import java.util.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.gob.valet.rest.elements.DetectCertInTslInfoAndValidationResponse;
import es.gob.valet.rest.elements.GetTslInformationResponse;

/**
 * <p>Interface that represents the TSL restful service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 07/09/2018.
 */
public interface ITslRestService {

	/**
	 * Constant attribute that represents the token parameter 'application'.
	 */
	String APPLICATION = "application";

	/**
	 * Constant attribute that represents the token parameter 'delegatedApplication'.
	 */
	String DELEGATED_APP = "delegatedApplication";

	/**
	 * Constant attribute that represents the token parameter 'tslLocation'.
	 */
	String TSL_LOCATION = "tslLocation";

	/**
	 * Constant attribute that represents the token parameter 'certificate'.
	 */
	String CERTIFICATE = "certificate";

	/**
	 * Constant attribute that represents the token parameter 'detectionDate'.
	 */
	String DETECTION_DATE = "detectionDate";

	/**
	 * Constant attribute that represents the token parameter 'getInfo'.
	 */
	String GET_INFO = "getInfo";

	/**
	 * Constant attribute that represents the token parameter 'checkRevocationStatus'.
	 */
	String CHECK_REV_STATUS = "checkRevocationStatus";

	/**
	 * Constant attribute that represents the token parameter 'returnRevocationEvidence'.
	 */
	String RETURN_REV_EVID = "returnRevocationEvidence";

	/**
	 * Constant attribute that represents the token parameter 'countryRegion'.
	 */
	String COUNTRY_REGION = "countryRegion";

	/**
	 * Constant attribute that represents the token parameter 'getTslXmlData'.
	 */
	String GET_TSL_XML_DATA = "getTslXmlData";

	/**
	 * Method that returns the information and validation of detected certificates in TSL.
	 * @param application Application
	 * @param delegatedApp Delegated application
	 * @param tslLocation TSL location
	 * @param certificate Certificate to detect (byte[] in Base64 encoded)
	 * @param detectionDate Detection date
	 * @param getInfo Get certificate info in response
	 * @param checkRevStatus Check revocation status
	 * @param returnRevoEvid Get revocation evidence (only if {@code checkRevocationStatus} is true)
	 * @return detectCertInTslInfoAndValidation structure with detected certificate in TSL and validation
	 */
	@POST
	@Path("/detectCertInTslInfoAndValidation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	DetectCertInTslInfoAndValidationResponse detectCertInTslInfoAndValidation(@FormParam(APPLICATION) final String application, @FormParam(DELEGATED_APP) final String delegatedApp, @FormParam(TSL_LOCATION) final String tslLocation, @FormParam(CERTIFICATE) final String certificate, @FormParam(DETECTION_DATE) final Date detectionDate, @FormParam(GET_INFO) final Boolean getInfo, @FormParam(CHECK_REV_STATUS) final Boolean checkRevStatus, @FormParam(RETURN_REV_EVID) final Boolean returnRevoEvid);

	/**
	 * Method that returns the TSL information.
	 * @param application Application
	 * @param delegatedApp Delegated application
	 * @param countryRegion Country region in TSL
	 * @param tslLocation TSL Location
	 * @param getTslXmlData Get TSL xml data in response
	 * @return getTslInformation structure of TSL information
	 */
	@POST
	@Path("/getTslInformation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	GetTslInformationResponse getTslInformation(@FormParam(APPLICATION) final String application, @FormParam(DELEGATED_APP) final String delegatedApp, @FormParam(COUNTRY_REGION) final String countryRegion, @FormParam(TSL_LOCATION) final String tslLocation, @FormParam(GET_TSL_XML_DATA) final Boolean getTslXmlData);

}
