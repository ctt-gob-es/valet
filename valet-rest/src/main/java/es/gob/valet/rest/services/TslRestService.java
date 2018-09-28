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
 * <b>Description:</b><p>Class that represents the TSL restful service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/08/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 07/09/2018.
 */
package es.gob.valet.rest.services;

import java.text.ParseException;
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

import es.gob.valet.commons.utils.UtilsFecha;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.ValetRestException;
import es.gob.valet.i18n.ILogMessages;
import es.gob.valet.i18n.Language;
import es.gob.valet.rest.elements.CertDetectedInTSL;
import es.gob.valet.rest.elements.DetectCertInTslInfoAndValidationResponse;
import es.gob.valet.rest.elements.GetTslInformationResponse;
import es.gob.valet.rest.elements.ResultTslInfVal;
import es.gob.valet.rest.elements.TslInformation;
import es.gob.valet.rest.elements.TslRevocationStatus;

/**
 * <p>Class that represents the statistics restful service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 07/09/2018.
 */
@Path("/tsl")
public class TslRestService implements ITslRestService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(TslRestService.class);

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.rest.services.ITslRestService#detectCertInTslInfoAndValidation(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean)
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@Override
	@POST
	@Path("/detectCertInTslInfoAndValidation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public DetectCertInTslInfoAndValidationResponse detectCertInTslInfoAndValidation(@FormParam(APPLICATION) final String application, @FormParam(DELEGATED_APP) final String delegatedApp, @FormParam(TSL_LOCATION) final String tslLocation, @FormParam(CERTIFICATE) final String certificate, @FormParam(DETECTION_DATE) final String detectionDate, @FormParam(GET_INFO) final Boolean getInfo, @FormParam(CHECK_REV_STATUS) final Boolean checkRevStatus, @FormParam(RETURN_REV_EVID) final Boolean returnRevoEvid) throws ValetRestException {
		// CHECKSTYLE:ON
		LOGGER.info(Language.getFormatResRestValet(ILogMessages.REST_LOG001, new Object[ ] { application, delegatedApp, tslLocation, certificate, detectionDate, getInfo, checkRevStatus, returnRevoEvid }));
		Boolean allIsOk = Boolean.TRUE;

		DetectCertInTslInfoAndValidationResponse result = new DetectCertInTslInfoAndValidationResponse();

		// Check required parameters
		String resultCheckParams = checkParamsDetectCertInTslInfoAndValidationResponse(application, certificate, getInfo, checkRevStatus, returnRevoEvid);
		if (!UtilsStringChar.isNullOrEmpty(resultCheckParams)) {
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
			LOGGER.error(Language.getFormatResRestValet(ILogMessages.REST_LOG004));
			result.setStatus(0);
			result.setDescription(Language.getFormatResRestValet(ILogMessages.REST_LOG004));
		}

		// Check if date format received is valid.
		Date detectionDateAux = null;
		if (!UtilsStringChar.isNullOrEmpty(detectionDate)) {
			try {
				detectionDateAux = UtilsFecha.convierteFecha(detectionDate, UtilsFecha.FORMATO_FECHA_ESTANDAR);
			} catch (ParseException e) {
				allIsOk = Boolean.FALSE;
				LOGGER.error(Language.getFormatResRestValet(ILogMessages.REST_LOG006, new Object[ ] { "detectionDate" }));
				result.setStatus(0);
				result.setDescription(Language.getFormatResRestValet(ILogMessages.REST_LOG006, new Object[ ] { "detectionDate" }));
			}
		}

		// If all is OK, we can continue
		if (allIsOk) {

			if (detectionDateAux == null) {
				detectionDateAux = new Date();
			}

			// Fill the object to return

			///////////////////////////////////////////////////////////////////////
			//////////// TODO: RELLENAR OBJETO DE RESPUESTA
			///////////////////////////////////////////////////////////////////////
			result.setStatus(1);
			result.setDescription(UtilsStringChar.EMPTY_STRING);
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
	 * Method that checks required parameters for {@link es.gob.valet.rest.services.TslRestService#detectCertInTslInfoAndValidation} method.
	 * @param application Application
	 * @param certificate Certificate to detect
	 * @param getInfo Get certificate info in response
	 * @param checkRevStatus Check revocation status
	 * @param returnRevoEvid Get revocation evidence
	 * @return boolean False if any parameter is null or empty
	 */
	private String checkParamsDetectCertInTslInfoAndValidationResponse(final String application, final String certificate, final Boolean getInfo, final Boolean checkRevStatus, final Boolean returnRevoEvid) {

		StringBuffer result = new StringBuffer();
		result.append(Language.getFormatResRestValet(ILogMessages.REST_LOG003, new Object[ ] { "detectCertInTslInfoAndValidation" }));
		Boolean checkError = Boolean.FALSE;

		// Check received parameters
		if (UtilsStringChar.isNullOrEmpty(application)) {
			checkError = Boolean.TRUE;
			result.append(" 'application'");
		}

		if (UtilsStringChar.isNullOrEmpty(certificate)) {
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
	 * {@inheritDoc}
	 * @see es.gob.valet.rest.services.ITslRestService#getTslInformation(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@Override
	@POST
	@Path("/getTslInformation")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public GetTslInformationResponse getTslInformation(@FormParam(APPLICATION) final String application, @FormParam(DELEGATED_APP) final String delegatedApp, @FormParam(COUNTRY_REGION) final String countryRegion, @FormParam(TSL_LOCATION) final String tslLocation, @FormParam(GET_TSL_XML_DATA) final Boolean getTslXmlData) throws ValetRestException {
		// CHECKSTYLE:ON
		LOGGER.info(Language.getFormatResRestValet(ILogMessages.REST_LOG002, new Object[ ] { application, delegatedApp, countryRegion, tslLocation, getTslXmlData }));
		Boolean allIsOk = Boolean.TRUE;

		GetTslInformationResponse result = new GetTslInformationResponse();

		// Check required parameters
		String resultCheckParams = checkParamsGetTslInformation(application, getTslXmlData);
		if (!UtilsStringChar.isNullOrEmpty(resultCheckParams)) {
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
			LOGGER.error(Language.getFormatResRestValet(ILogMessages.REST_LOG005));
			result.setStatus(0);
			result.setDescription(Language.getFormatResRestValet(ILogMessages.REST_LOG005));
		}

		// If all is OK, we can continue
		if (allIsOk) {
			// Fill the object to return

			///////////////////////////////////////////////////////////////////////
			//////////// TODO: RELLENAR OBJETO DE RESPUESTA
			///////////////////////////////////////////////////////////////////////
			result.setStatus(1);
			result.setDescription(UtilsStringChar.EMPTY_STRING);
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
	 * Method that checks required parameters for {@link es.gob.valet.rest.services.TslRestService#getTslInformation} method.
	 * @param application Application
	 * @param getTslXmlData Get TSL xml data
	 * @return boolean False if any parameter is null or empty
	 */
	private String checkParamsGetTslInformation(final String application, final Boolean getTslXmlData) {

		StringBuffer result = new StringBuffer();
		result.append(Language.getFormatResRestValet(ILogMessages.REST_LOG003, new Object[ ] { "getTslInformation" }));
		Boolean checkError = Boolean.FALSE;

		// Check received parameters
		if (UtilsStringChar.isNullOrEmpty(application)) {
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