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
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.6, 19/09/2023.
 */
package es.gob.valet.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.exceptions.ValetRestException;
import es.gob.valet.rest.elements.DetectCertInTslInfoAndValidationResponse;
import es.gob.valet.rest.elements.TslInformationResponse;
import es.gob.valet.rest.elements.TslInformationVersionsResponse;
import es.gob.valet.rest.elements.json.ByteArrayB64;
import es.gob.valet.rest.elements.json.DateString;

/**
 * <p>Interface that represents the TSL restful service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.6, 19/09/2023.
 */
public interface ITslRestService {
	
	/**
	 * Method that returns the information and revocation status of the input certificate extracted from a TSL.
	 * @param application Application identifier.
	 * @param delegatedApp Delegated application identifier.
	 * @param tslLocation TSL location to use. It could be <code>null</code>.
	 * @param certByteArrayB64 Certificate to detect (byte[] in Base64 encoded).
	 * @param detectionDate Date to use to detect and validate the input certificate. Format: {@value UtilsDate#FORMAT_DATE_TIME_JSON}.
	 * @param getInfo Flag that indicates if it is necessary to get the certificate information in response.
	 * @param checkRevStatus Flag that indicates if it is necessary to check the revocation status of the input certificate.
	 * @param returnRevocationEvidence Flag that indicates if it is necessary to return the revocation evidence (only if {@code checkRevStatus} is <code>true</code>).
	 * @param crlsByteArrayB64List List of byte arrays (in base 64) that represents the CRL to use to validate the certificate. <code>null</code> if there is not.
	 * If this is defined, then {@code checkRevStatus} is considered <code>true</code>.
	 * @param basicOcspResponsesByteArrayB64List List of byte arrays (in base 64) that represents the Basic OCSP responses to use to validate the certificate. <code>null</code> if there is not.
	 * @param returnCertificateChain Flag that indicates if it is necessary to return chain certificate.
	 * If this is defined, then {@code checkRevStatus} is considered <code>true</code>.
	 * @return Structure with detected certificate in TSL and revocation status.
	 * @throws ValetRestException If some error is produced in the execution of the service.
	 */
	@POST
	@Path("/detectCertInTslInfoAndValidation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	DetectCertInTslInfoAndValidationResponse detectCertInTslInfoAndValidation(@FormParam(TslMappingConstants.PARAM_APPLICATION) String application, @FormParam(TslMappingConstants.PARAM_DELEGATED_APP) String delegatedApp, @FormParam(TslMappingConstants.PARAM_TSL_LOCATION) String tslLocation, @FormParam(TslMappingConstants.PARAM_CERTIFICATE) ByteArrayB64 certByteArrayB64, @FormParam(TslMappingConstants.PARAM_DETECTION_DATE) DateString detectionDate, @FormParam(TslMappingConstants.PARAM_GET_INFO) Boolean getInfo, @FormParam(TslMappingConstants.PARAM_CHECK_REV_STATUS) Boolean checkRevStatus, @FormParam(TslMappingConstants.PARAM_RETURN_REV_EVID) Boolean returnRevocationEvidence, @FormParam(TslMappingConstants.PARAM_CRLS_BYTE_ARRAY) List<ByteArrayB64> crlsByteArrayB64List, @FormParam(TslMappingConstants.PARAM_BASIC_OCSP_RESPONSES_BYTE_ARRAY) List<ByteArrayB64> basicOcspResponsesByteArrayB64List, @FormParam(TslMappingConstants.PARAM_RETURN_CERT_CHAIN) Boolean returnCertificateChain) throws ValetRestException;

	/**
	 * Method that returns the TSL information.
	 * @param application Application identifier.
	 * @param delegatedApp Delegated application identifier.
	 * @param countryRegionCode Country/Region code that represents the TSL. It could be <code>null</code>.
	 * @param tslLocation TSL location to use. It could be <code>null</code>.
	 * @param getTslXmlData Flag that indicates if it is necessary to return the XML data that represents the TSL.
	 * @return Structure of TSL information.
	 * @throws ValetRestException If some error is produced in the execution of the service.
	 */
	@POST
	@Path("/getTslInformation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	TslInformationResponse getTslInformation(@FormParam(TslMappingConstants.PARAM_APPLICATION) String application, @FormParam(TslMappingConstants.PARAM_DELEGATED_APP) String delegatedApp, @FormParam(TslMappingConstants.PARAM_COUNTRY_REGION_CODE) String countryRegionCode, @FormParam(TslMappingConstants.PARAM_TSL_LOCATION) String tslLocation, @FormParam(TslMappingConstants.PARAM_GET_TSL_XML_DATA) Boolean getTslXmlData) throws ValetRestException;

	/**
	 * Method that returns the versions of the TSLs registered in valET.
	 * @return Structure with a map that relates the TSL with the registered version.
	 * @throws throws  ValetRestException If some error is produced in the execution of the service.
	 */
	@POST
	@Path("/getTslInfoVersions")
	@Produces(MediaType.APPLICATION_JSON)
	TslInformationVersionsResponse getTslInfoVersions() throws ValetRestException;

}
