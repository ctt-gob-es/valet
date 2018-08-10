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
 * <b>File:</b><p>es.gob.valet.rest.TslRestService.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>7 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 7 ago. 2018.
 */
package es.gob.valet.rest;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import entity.CertDetectedInTSL;
import entity.DetectCertInTslInfoAndValidationResponse;
import entity.GetTslInformationResponse;
import entity.ResultTslInfVal;
import entity.TslInformation;
import entity.TslRevocationStatus;
import es.gob.valet.i18n.LanguageRest;
import es.gob.valet.rest.util.IRestLogConstants;

/** 
 * <p>Class that represents the statistics restful service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 7/8/2018.
 */
@Path("/tsl")
public class TslRestService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(TslRestService.class);

	/**
	 * Attribute that represents application Constant. 
	 */
	private static final String APPLICATION = "application";

	/**
	 * Attribute that represents delegatedApplication Constant. 
	 */
	private static final String DELEGATED_APP = "delegatedApplication";

	/**
	 * Attribute that represents tslLocation Constant. 
	 */
	private static final String TSL_LOCATION = "tslLocation";

	/**
	 * Attribute that represents certificate Constant. 
	 */
	private static final String CERTIFICATE = "certificate";

	/**
	 * Attribute that represents detectionDate Constant. 
	 */
	private static final String DETECTION_DATE = "detectionDate";

	/**
	 * Attribute that represents getInfo Constant. 
	 */
	private static final String GET_INFO = "getInfo";

	/**
	 * Attribute that represents checkRevocationStatus Constant. 
	 */
	private static final String CHECK_REV_STATUS = "checkRevocationStatus";

	/**
	 * Attribute that represents returnRevocationEvidence Constant. 
	 */
	private static final String RETURN_REV_EVID = "returnRevocationEvidence";

	/**
	 * Attribute that represents countryRegion Constant. 
	 */
	private static final String COUNTRY_REGION = "countryRegion";

	/**
	 * Attribute that represents getTslXmlData Constant. 
	 */
	private static final String GET_TSL_XML_DATA = "getTslXmlData";

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
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@POST
	@Path("/detectCertInTslInfoAndValidation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public DetectCertInTslInfoAndValidationResponse detectCertInTslInfoAndValidation(@FormParam(APPLICATION) final String application, @FormParam(DELEGATED_APP) final String delegatedApp, @FormParam(TSL_LOCATION) final String tslLocation, @FormParam(CERTIFICATE) final String certificate, @FormParam(DETECTION_DATE) final Date detectionDate, @FormParam(GET_INFO) final Boolean getInfo, @FormParam(CHECK_REV_STATUS) final Boolean checkRevStatus, @FormParam(RETURN_REV_EVID) final Boolean returnRevoEvid) {
		// CHECKSTYLE:ON
		LOGGER.info(LanguageRest.getFormatResRestValet(IRestLogConstants.REST_LOG001, new Object[ ] { application, delegatedApp, tslLocation, certificate, detectionDate, getInfo, checkRevStatus, returnRevoEvid }));
		Boolean allIsOk = Boolean.TRUE;

		DetectCertInTslInfoAndValidationResponse result = new DetectCertInTslInfoAndValidationResponse();

		// Check required parameters
		String resultCheckParams = checkParamsDetectCertInTslInfoAndValidationResponse(application, certificate, getInfo, checkRevStatus, returnRevoEvid);
		if (resultCheckParams != null && !resultCheckParams.equals("")) {
			allIsOk = Boolean.FALSE;
			LOGGER.error(resultCheckParams);
			result.setStatus(0);
			result.setDescription(resultCheckParams);
		}

		// Check specific features
		// Parameter 'returnRevocationEvidence' only can be true if parameter
		// 'checkRevocationStatus' is true
		if (allIsOk && returnRevoEvid && !checkRevStatus) {
			allIsOk = Boolean.FALSE;
			LOGGER.error(LanguageRest.getFormatResRestValet(IRestLogConstants.REST_LOG004));
			result.setStatus(0);
			result.setDescription(LanguageRest.getFormatResRestValet(IRestLogConstants.REST_LOG004));
		}

		// If all is OK, we can continue
		if (allIsOk) {
			Date detectionDateAux = detectionDate;
			if (detectionDateAux == null) {
				detectionDateAux = new Date();
			}

			// Fill the object to return

			///////////////////////////////////////////////////////////////////////
			//////////// TODO: RELLENAR OBJETO DE RESPUESTA
			///////////////////////////////////////////////////////////////////////
			result.setStatus(1);
			result.setDescription("");
			ResultTslInfVal resultTslInfValP = new ResultTslInfVal();
			TslInformation tslInformation = new TslInformation();
			CertDetectedInTSL certDetectInTSL = new CertDetectedInTSL();

			tslInformation.setCountryRegion("Sevilla");
			tslInformation.setSequenceNumber(1);
			tslInformation.setTslLocation("Location");
			tslInformation.setIssued(new Date());
			tslInformation.setNextUpdate(new Date());
			// TslXmlData always null on this service
			tslInformation.setTslXmlData(null);
			resultTslInfValP.setTslInformation(tslInformation);

			certDetectInTSL.setTspName("TSP");
			certDetectInTSL.setTspServiceName("TSP SERVICE NAME");
			certDetectInTSL.setTspServiceType("TSP SERVICE TYPE");
			certDetectInTSL.setTspServiceStatus("TSP SERVICE STATUS");

			// Fill certInfo only when 'getInfo' parameter is true
			if (getInfo) {
				Map<String, String> mapCertInfo = new ConcurrentHashMap<String, String>();
				mapCertInfo.put("name", "Usuario");
				mapCertInfo.put("surname", "apellido1 apellido2");
				mapCertInfo.put("id", "11111111H");

				certDetectInTSL.setCertInfo(mapCertInfo);
			}

			// Fill TslRevocationStatus only when 'checkRevocationStatus'
			// parameter is true
			if (checkRevStatus) {
				TslRevocationStatus tslRevocStatus = new TslRevocationStatus();
				tslRevocStatus.setRevocationStatus(1);
				tslRevocStatus.setRevocationDesc("Descripcion de la revocacion");
				Boolean isFromServStatus = Boolean.TRUE;
				tslRevocStatus.setIsFromServStat(isFromServStatus);
				if (!isFromServStatus) {
					tslRevocStatus.setUrl("http://www.example.com");
					tslRevocStatus.setDpAia(false);
					tslRevocStatus.setTspServiceName("TSP SERVICE NAME REVOCATION STATUS");
					tslRevocStatus.setTspServiceType("TSP SERVICE TYPE REVOCATION STATUS");
					tslRevocStatus.setTspServiceStatus("TSP SERVICE STATUS REVOCATION STATUS");
					tslRevocStatus.setEvidenceType(1);
					byte[ ] evidencia = "Evidencia".getBytes();
					tslRevocStatus.setEvidence(evidencia);
				}

				certDetectInTSL.setTslRevocStatus(tslRevocStatus);
			}

			resultTslInfValP.setCertDetectedInTSL(certDetectInTSL);
			result.setResultTslInfVal(resultTslInfValP);

			///////////////////////////////////////////////////////////////////////
			//////////// FIN RELLENAR OBJETO DE RESPUESTA DE EJEMPLO
			///////////////////////////////////////////////////////////////////////
		}

		return result;
	}

	/**
	 * Method that checks required parameters for {@link es.gob.valet.rest.TslRestService#detectCertInTslInfoAndValidation} method.
	 * 
	 * @param application Application
	 * @param certificate Certificate to detect
	 * @param getInfo Get certificate info in response
	 * @param checkRevStatus Check revocation status
	 * @param returnRevoEvid Get revocation evidence
	 * @return boolean False if any parameter is null or empty
	 */
	private String checkParamsDetectCertInTslInfoAndValidationResponse(final String application, final String certificate, final Boolean getInfo, final Boolean checkRevStatus, final Boolean returnRevoEvid) {

		StringBuffer result = new StringBuffer();
		result.append(LanguageRest.getFormatResRestValet(IRestLogConstants.REST_LOG003, new Object[ ] { "detectCertInTslInfoAndValidation" }));
		Boolean checkError = Boolean.FALSE;

		// Check received parameters
		if (application == null || application.equals("")) {
			checkError = Boolean.TRUE;
			result.append(" 'application'");
		}

		if (certificate == null || certificate.equals("")) {
			checkError = Boolean.TRUE;
			result.append(" 'certificate'");
		}

		if (getInfo == null) {
			checkError = Boolean.TRUE;
			result.append(" 'getInfo'");
		}

		if (checkRevStatus == null) {
			checkError = Boolean.TRUE;
			result.append(" 'checkRevocationStatus'");
		}

		if (returnRevoEvid == null) {
			checkError = Boolean.TRUE;
			result.append(" 'returnRevocationEvidence'");
		}

		if (!checkError) {
			result = new StringBuffer();
		}

		return result.toString();
	}

	/**
	 * Method that returns the TSL information.
	 * @param application Application
	 * @param delegatedApp Delegated application
	 * @param countryRegion Country region in TSL
	 * @param tslLocation TSL Location
	 * @param getTslXmlData Get TSL xml data in response
	 * @return getTslInformation structure of TSL information
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@POST
	@Path("/getTslInformation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public GetTslInformationResponse getTslInformation(@FormParam(APPLICATION) final String application, @FormParam(DELEGATED_APP) final String delegatedApp, @FormParam(COUNTRY_REGION) final String countryRegion, @FormParam(TSL_LOCATION) final String tslLocation, @FormParam(GET_TSL_XML_DATA) final Boolean getTslXmlData) {
		// CHECKSTYLE:ON
		LOGGER.info(LanguageRest.getFormatResRestValet(IRestLogConstants.REST_LOG002, new Object[ ] { application, delegatedApp, countryRegion, tslLocation, getTslXmlData }));
		Boolean allIsOk = Boolean.TRUE;

		GetTslInformationResponse result = new GetTslInformationResponse();

		// Check required parameters
		String resultCheckParams = checkParamsGetTslInformation(application, getTslXmlData);
		if (resultCheckParams != null && !resultCheckParams.equals("")) {
			allIsOk = Boolean.FALSE;
			LOGGER.error(resultCheckParams);
			result.setStatus(0);
			result.setDescription(resultCheckParams);
		}

		// Check specific features
		// Only is possible specify parameter 'countryRegion' or 'tslLocation'
		// but not both
		if (allIsOk && countryRegion != null && tslLocation != null) {
			allIsOk = Boolean.FALSE;
			LOGGER.error(LanguageRest.getFormatResRestValet(IRestLogConstants.REST_LOG004));
			result.setStatus(0);
			result.setDescription(LanguageRest.getFormatResRestValet(IRestLogConstants.REST_LOG004));
		}

		// If all is OK, we can continue
		if (allIsOk) {
			// Fill the object to return

			///////////////////////////////////////////////////////////////////////
			//////////// TODO: RELLENAR OBJETO DE RESPUESTA
			///////////////////////////////////////////////////////////////////////
			result.setStatus(1);
			result.setDescription("");
			TslInformation tslInformation = new TslInformation();

			tslInformation.setCountryRegion("Sevilla");
			tslInformation.setSequenceNumber(1);
			tslInformation.setTslLocation("Location");
			tslInformation.setIssued(new Date());
			tslInformation.setNextUpdate(new Date());
			// Fill certInfo only when 'getTslXmlData' parameter is true
			if (getTslXmlData) {
				byte[ ] xmlData = "TSL_XML_DATA".getBytes();
				tslInformation.setTslXmlData(xmlData);
			}

			result.setTslInformation(tslInformation);

			///////////////////////////////////////////////////////////////////////
			//////////// FIN RELLENAR OBJETO DE RESPUESTA DE EJEMPLO
			///////////////////////////////////////////////////////////////////////

		}

		return result;
	}

	/**
	 * Method that checks required parameters for {@link es.gob.valet.rest.TslRestService#getTslInformation} method.
	 * 
	 * @param application Application
	 * @param getTslXmlData Get TSL xml data
	 * @return boolean False if any parameter is null or empty
	 */
	private String checkParamsGetTslInformation(final String application, final Boolean getTslXmlData) {

		StringBuffer result = new StringBuffer();
		result.append(LanguageRest.getFormatResRestValet(IRestLogConstants.REST_LOG003, new Object[ ] { "getTslInformation" }));
		Boolean checkError = Boolean.FALSE;

		// Check received parameters
		if (application == null || application.equals("")) {
			checkError = Boolean.TRUE;
			result.append(" 'application'");
		}

		if (getTslXmlData == null) {
			checkError = Boolean.TRUE;
			result.append(" 'getTslXmlData'");
		}

		if (!checkError) {
			result = new StringBuffer();
		}

		return result.toString();
	}

}