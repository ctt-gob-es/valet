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
 * <b>File:</b><p>es.gob.valet.tsl.access.TSLManager.java.</p>
 * <b>Description:</b><p>Class that reprensents the TSL Manager for all the differents operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 2.6, 19/09/2023.
 */
package es.gob.valet.tsl.access;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;

import es.gob.valet.audit.utils.CommonsCertificatesAuditTraces;
import es.gob.valet.audit.utils.CommonsTslAuditTraces;
import es.gob.valet.commons.utils.CryptographicConstants;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsCountryLanguage;
import es.gob.valet.commons.utils.UtilsCrypto;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsResources;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CommonsUtilGeneralMessages;
import es.gob.valet.i18n.messages.CoreTslMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionMappingCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.dto.TslCountryVersionDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;
import es.gob.valet.persistence.configuration.model.entity.CAssociationType;
import es.gob.valet.persistence.configuration.model.entity.CTslImpl;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.utils.AssociationTypeIdConstants;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;
import es.gob.valet.rest.elements.json.DateString;
import es.gob.valet.service.impl.ExternalAccessService;
import es.gob.valet.spring.config.ApplicationContextProvider;
import es.gob.valet.tasks.FindNewTslRevisionsTaskConstants;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidator;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.certValidation.impl.TSLValidatorFactory;
import es.gob.valet.tsl.certValidation.impl.TSLValidatorMappingCalculator;
import es.gob.valet.tsl.certValidation.impl.common.DigitalIdentitiesProcessor;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.exceptions.TSLParsingException;
import es.gob.valet.tsl.exceptions.TSLValidationException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSPService;
import es.gob.valet.tsl.parsing.impl.common.TrustServiceProvider;
import es.gob.valet.utils.TSLCommonURIs;

/**
 * <p>Class that reprensents the TSL Manager for all the differents operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 2.6, 19/09/2023.
 */
public final class TSLManager {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(TSLManager.class);

	/**
	 * Constant attribute that represents the token 'UNKNOWN'.
	 */
	private static final String TOKEN_UNKNOWN = "UNKNOWN";
	
	/**
	 * Constant attribute that represents the Sun property for the connection timeout.
	 */
	private static final String SUN_CONNECT_TIMEOUT_PROP = "sun.net.client.defaultConnectTimeout";
	
	/**
	 * Constant attribute that represents the Sun property for the connection timeout.
	 */
	private static final String SUN_READ_TIMEOUT_PROP = "sun.net.client.defaultReadTimeout";
	
	/**
	 * Attribute that represents the unique instance of this class (singleton).
	 */
	private static TSLManager instance = null;

	/**
	 * Attribute that represents a set of URL (String format) that represents the official
	 * european list of trusted lists. 
	 */
	private Set<String> setOfURLStringThatRepresentsEuLOTL = new TreeSet<String>();

	/**
	 * Attribute that represents the map of certificates that appear in the TSLs by Country.
	 */
	private Map<String, List<X509Certificate>> mapCertificateTSL = new HashMap<String, List<X509Certificate>>();

	/**
	 * Attribute that represents a set of URL (String format) that represents the official
	 * european list of trusted lists splitted by commas.
	 */
	private String setOfURLStringThatRepresentsEuLOTLinString = null;
	
	/**
	 * Attribute that represents a date not specified.
	 */
	private static final String DATE_NOT_SPECIFIED = "Not specified";
	
	/**
	 * Constructor method for the class TSLManager.java.
	 */
	private TSLManager() {
		super();
	}

	/**
	 * Gets the unique instance of this class.
	 * @return The unique instance of the {@link TSLManager} class.
	 */
	public static TSLManager getInstance() {

		if (instance == null) {
			instance = new TSLManager();
		}
		return instance;

	}

	/**
	 * Method that checks if exists some TSL from the indicated country of the issuer of the certificate
	 * in the TSLs with that identifiers, and that cover the validation date.
	 * @param x509Cert {@link X509Certificate} which obtains the country from its issuer.
	 * @param date Date that must cover the TSL to obtain.
	 * @return <code>true</code> if exists in the DDBB at least one TSL from the country of the certificate issuer.
	 * Otherwise <code>false</code>.
	 * @throws TSLManagingException In case of some error getting the information from the cache.
	 */
	public boolean existsAtLeastOneTslFromTheCountry(X509Certificate x509Cert, Date date) throws TSLManagingException {

		boolean result = false;

		TSLObject tslObject = getTSLFromTheCountry(x509Cert, date);
		if (tslObject == null) {
			LOGGER.debug(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL238, new Object[ ] { UtilsCertificate.getCountryOfTheCertificateString(x509Cert), date }));
		} else {
			LOGGER.debug(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL239, new Object[ ] { UtilsCertificate.getCountryOfTheCertificateString(x509Cert), date, tslObject.getSchemeInformation().getTslSequenceNumber() }));
			result = true;
		}

		return result;

	}

	/**
	 * Gets the TSL from the indicated certificate issuer country, that covers the specific date
	 * and it is in the allowed by the application.
	 * @param x509Cert {@link X509Certificate} which obtains the country from its issuer.
	 * @param date Date that must cover the TSL to obtain. If this parameter is <code>null</code>, then search
	 * the more updated TSL with that TSL location.
	 * @return a TSL Object representation of the finded TSL, or <code>null</code> if not is finded.
	 * @throws TSLManagingException In case of some error getting the information from the cache.
	 */
	public TSLObject getTSLFromTheCountry(X509Certificate x509Cert, Date date) throws TSLManagingException {

		TSLObject result = null;

		// Si el certificado no es nulo...
		if (x509Cert != null) {

			// Obtenemos el código del país/región asociada al
			// certificado en función de si es una CA (del subject)
			// o un certificado final (emisor).
			String countryCode = UtilsCertificate.getCountryOfTheCertificateString(x509Cert);
			// Si no lo hemos obtenido... mensaje.
			if (UtilsStringChar.isNullOrEmptyTrim(countryCode)) {
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL102));
			} else {
				result = getTSLFromTheCountry(countryCode, date);
			}

		}

		return result;

	}

	/**
	 * Gets the TSL from the indicated country, that covers the specific date
	 * and it is in the allowed by the application.
	 * @param countryCode String that specified the country/region (ISO 3166-1).
	 * @param date Date that must cover the TSL to obtain. If this parameter is <code>null</code>, then search
	 * the more updated TSL with that TSL location.
	 * @return a TSL Object representation of the finded TSL, or <code>null</code> if not is finded.
	 * @throws TSLManagingException In case of some error getting the information from the cache.
	 */
	public TSLObject getTSLFromTheCountry(String countryCode, Date date) throws TSLManagingException {

		TSLObject result = null;

		// Si el código de país no es nulo...
		if (!UtilsStringChar.isNullOrEmptyTrim(countryCode)) {

			// Obtenemos la TSL del país/región indicados.
			TSLDataCacheObject tdco = null;
			String dateString = date == null ? DATE_NOT_SPECIFIED : date.toString();
			try {
				tdco = getTSLDataFromCountryRegion(countryCode);
			} catch (TSLManagingException e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_191, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL103, new Object[ ] { countryCode, dateString }), e);
			}

			// Si lo hemos obtenido...
			if (tdco != null) {

				// Si hemos recibido una fecha, se debe cumplir que esta sea
				// anterior
				// a la fecha de caducidad de la TSL.
				if (date != null && date.before(tdco.getNextUpdateDate())) {
					// Establecemos como resultado esta TSL.
					result = (TSLObject) tdco.getTslObject();
				}else{
					LOGGER.warn(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL396, new Object[] {date.toString(), countryCode}));
				}

				
			}

		}

		return result;

	}

	/**
	 * Method that checks if exists some TSL with the indicated location
	 * in the TSLs with that identifiers, and that cover the validation date.
	 * @param tslLocation URI string representation of the TSL location where to obtain it.
	 * @param date Date that must cover the TSL to obtain. If this parameter is <code>null</code>, then search
	 * the more updated TSL with that TSL location.
	 * @return <code>true</code> if exists in the DDBB at least one TSL with the indicated location.
	 * Otherwise <code>false</code>.
	 * @throws TSLManagingException In case of some error getting the information from the cache.
	 */
	public boolean existsAtLeastOneTslFromLocation(String tslLocation, Date date) throws TSLManagingException {

		boolean result = false;

		TSLObject tslObject = getTSLfromTSLLocation(tslLocation, date);
		if (tslObject == null) {
			LOGGER.debug(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL240, new Object[ ] { tslLocation, date }));
		} else {
			LOGGER.debug(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL241, new Object[ ] { tslLocation, date, tslObject.getSchemeInformation().getTslSequenceNumber() }));
			result = true;
		}

		return result;

	}

	/**
	 * Gets the TSL Object associated to the TSL Location specified as input parameter, and that is valid
	 * for the validation date indicated.
	 * @param tslLocation URI string representation of the TSL location where to obtain it.
	 * @param date Date that must cover the TSL to obtain. If this parameter is <code>null</code>, then search
	 * the more updated TSL with that TSL location.
	 * @return TSL object representation with the location specified in the input parameter and that covers
	 * the input date. If the location is <code>null</code> or empty, then returns <code>null</code>.
	 * @throws TSLManagingException In case of some error getting the information from the cache.
	 */
	public TSLObject getTSLfromTSLLocation(String tslLocation, Date date) throws TSLManagingException {

		TSLObject result = null;

		// Si el parámetro de entrada que representa "TSLLocation" no es nulo ni
		// vacío, continuamos.
		if (!UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {

			// Obtenemos la TSL con dicha localización.
			TSLDataCacheObject tdco = null;
			String dateString = date == null ? DATE_NOT_SPECIFIED : date.toString();
			try {
				tdco = getTSLDataFromTSLLocation(tslLocation);
			} catch (TSLManagingException e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_191, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL103, new Object[ ] { tslLocation, dateString }), e);
			}
			// Si lo hemos recuperado...
			if (tdco != null) {
				// Si hemos recibido una fecha, se debe cumplir que esta sea
				// anterior
				// a la fecha de caducidad de la TSL.
				if (date != null && date.before(tdco.getNextUpdateDate())) {
					// Establecemos como resultado esta TSL.
					result = (TSLObject) tdco.getTslObject();
				}
			}

		}

		// Devolvemos lo recuperado.
		return result;

	}

	/**
	 * Tries to detect the input X509v3 certificate searching a valid TSL for it, and using this for the detection process.
	 * Then returns the validation/detection result. If no TSL is finded for the certificate, then returns <code>null</code>.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to detect.
	 * @param date Date that must cover the TSL to obtain. If this parameter is <code>null</code>, then search
	 * the more updated TSL with that TSL location.
	 * @return TSL validation result, with all the collected information. If no TSL is finded for the certificate, then returns
	 * <code>null</code>.
	 * @throws TSLManagingException If there is some error with the cache or validating the certificate with the TSL.
	 */
	public ITSLValidatorResult detectX509andGetMappingInfoWithTSL(String auditTransNumber, X509Certificate cert, Date date) throws TSLManagingException {

		ITSLValidatorResult result = null;

		// Primero buscamos una TSL para el certificado.
		TSLObject tslObject = getTSLFromTheCountry(cert, date);

		// Si hemos encontrado una TSL, continuamos.
		if (tslObject != null) {
			result = validateX509withTSL(auditTransNumber, cert, date, false, true, tslObject);
		}

		return result;

	}

	/**
	 * Tries to detect the input X509v3 certificate searching a valid TSL for it, and using this for the detection process.
	 * Then returns the validation/detection result. If no TSL is finded for the certificate, then returns <code>null</code>.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to detect.
	 * @param tslLocation URI string representation of the TSL location where to obtain it.
	 * @param date Date that must cover the TSL to obtain. If this parameter is <code>null</code>, then search
	 * the more updated TSL with that TSL location.
	 * @return TSL validation result, with all the collected information. If no TSL is finded for the certificate, then returns
	 * <code>null</code>.
	 * @throws TSLManagingException If there is some error with the cache or validating the certificate with the TSL.
	 */
	public ITSLValidatorResult detectX509andGetMappingInfoWithTSL(String auditTransNumber, X509Certificate cert, String tslLocation, Date date) throws TSLManagingException {

		ITSLValidatorResult result = null;

		// Primero buscamos una TSL para el certificado.
		TSLObject tslObject = getTSLfromTSLLocation(tslLocation, date);

		// Si hemos encontrado una TSL, continuamos.
		if (tslObject != null) {
			result = validateX509withTSL(auditTransNumber, cert, date, false, true, tslObject);
		}

		return result;

	}

	/**
	 * Tries to detect the input X509v3 certificate searching a valid TSL for it, and using this for the detection process.
	 * Then returns the mapping info associated to it. If no TSL is finded for the certificate, or the certificate is not detected,
	 * then returns <code>null</code>.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to detect.
	 * @param tslLocation URI string representation of the TSL location where to obtain it.
	 * @return Map with the mapping info obtained from the TSL and the input certificate if it has been detected.
	 * Otherwise <code>null</code>.
	 * @throws TSLManagingException If there is some error with the cache or detecting the certificate with the TSL.
	 */
	public Map<String, String> getInfoMappingCertFromTSL(String auditTransNumber, X509Certificate cert, String tslLocation) throws TSLManagingException {

		// Inicializamos el resultado.
		Map<String, String> result = null;

		Date date = cert.getNotBefore();

		// Inicializamos el resultado de detectar el certificado y obtener
		// mapeos.
		ITSLValidatorResult tslValResult = null;
		// En función de si disponemos del TSL Location...
		if (UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {
			tslValResult = detectX509andGetMappingInfoWithTSL(auditTransNumber, cert, date);
		} else {
			tslValResult = detectX509andGetMappingInfoWithTSL(auditTransNumber, cert, tslLocation, date);
		}

		// Si hemos obtenido resultado y el certificado ha sido detectado...
		if (tslValResult != null && tslValResult.hasBeenDetectedTheCertificate()) {

			// Obtenemos los mapeos.
			result = tslValResult.getMappings();

		}

		// Devolvemos el map con los mapeos obtenidos.
		return result;

	}

	/**
	 * Tries to detect the input X509v3 certificate in the last Spanish TSL, and using this for the detection process.
	 * Then returns the mapping info associated to it. If no TSL is finded for the certificate (and actual date),
	 * or the certificate is not detected, then returns <code>null</code>.
	 * If the mapping 'certClassification' is not obtained, then tries to get from the certificate attributes/extension.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to detect. If this is <code>null</code>, then this method returns <code>null</code>.
	 * @return Map with the mapping info obtained from the TSL and the input certificate if it has been detected.
	 * Otherwise <code>null</code>.
	 * @throws TSLManagingException If there is some error getting the mapping from the inout certificate.
	 */
	public Map<String, String> getFullInfoMappingCertFromSpainLastTSLAndFromCertItSelf(String auditTransNumber, X509Certificate cert) throws TSLManagingException {

		// Inicializamos el resultado.
		Map<String, String> result = null;

		if (cert != null) {

			try {

				// Buscamos la TSL de España.
				TSLDataCacheObject tdco = getTSLDataFromCountryRegion(UtilsCountryLanguage.ES_COUNTRY_CODE);

				// Si hemos encontrado la TSL y su fecha de caducidad es
				// posterior
				// a la fecha de emisión

				Date dateIssue = cert.getNotBefore();
				if (tdco != null && tdco.getNextUpdateDate().after(dateIssue)) {

					// Inicializamos el resultado de detectar el certificado y
					// obtener
					// mapeos.
					ITSLValidatorResult tslValResult = detectX509andGetMappingInfoWithTSL(auditTransNumber, cert, dateIssue);

					// Si hemos obtenido resultado y el certificado ha sido
					// detectado...
					if (tslValResult != null && tslValResult.hasBeenDetectedTheCertificate()) {

						// Obtenemos los mapeos.
						result = tslValResult.getMappings();

						// TODO: Esto no se puede hacer aquí porque es necesario
						// conocer el valor de 'clasificacion' que va en función
						// de la política.
						// // Tratamos de asignar el
						// // mapeo estático "certClassification" mediante el
						// campo "clasificacion"
						// // si no estuviera ya definido.
						// result =
						// TSLValidatorMappingCalculator.addMappingsAndCheckCertClassification(null,
						// result);

					}

				}

			} catch (TSLManagingException e) {

				try {
					throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL149, new Object[ ] { UtilsCertificate.getCertificateIssuerId(cert), cert.getSerialNumber().toString() }), e);
				} catch (CommonUtilsException cue) {
					throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL149, new Object[ ] { UtilsStringChar.EMPTY_STRING, cert.getSerialNumber().toString() }), e);
				}

			}

		}

		// Devolvemos el map con los mapeos obtenidos
		return result;

	}

	/**
	 * Tries to validate the input X509v3 certificate searching a valid TSL for it, and using this for the validation process.
	 * Then returns the validation result. If no TSL is finded for the certificate, then returns <code>null</code>.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to validate.
	 * @param date Date that must cover the TSL to obtain. If this parameter is <code>null</code>, then search
	 * the more updated TSL with that TSL location.
	 * @param checkStatusRevocation Flag that indicates if only try to detect the input certificate (<code>false</code>)
	 * or also checks the revocation status of this (<code>true</code>).
	 * @param calculateMappings Flag that indicates if it is necessary to calculate the mappings associated if the certificate
	 * has been detected (<code>true</code>) or not (<code>false</code>).
	 * @return TSL validation result, with all the collected information. If no TSL is finded for the certificate, then returns
	 * <code>null</code>.
	 * @throws TSLManagingException If there is some error with the cache or validating the certificate with the TSL.
	 */
	public ITSLValidatorResult validateX509withTSL(String auditTransNumber, X509Certificate cert, Date date, boolean checkStatusRevocation, boolean calculateMappings) throws TSLManagingException {

		ITSLValidatorResult result = null;

		// Primero buscamos una TSL para el certificado.
		TSLObject tslObject = getTSLFromTheCountry(cert, date);

		// Si no hemos encontrado una TSL, lo indicamos en auditoría...
		if (tslObject == null) {

			// Añadimos la traza de auditoría indicando que no se ha encontrado.
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, false, null, null, null, null);

		} else {

			// Añadimos la traza de auditoría indicando que ha encontrado
			// junto con la información asociada.
			DateString tslIssued = new DateString(tslObject.getSchemeInformation().getListIssueDateTime());
			DateString tslNextUpdate = new DateString(tslObject.getSchemeInformation().getNextUpdate());
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, true, tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber(), tslIssued, tslNextUpdate);

			// Continuamos el proceso...
			result = validateX509withTSL(auditTransNumber, cert, date, checkStatusRevocation, calculateMappings, tslObject);

		}

		return result;

	}

	/**
	 * Tries to validate the input X509v3 certificate searching a valid TSL for it, and using this for the validation process.
	 * Then returns the validation result. If no TSL is finded for the certificate, then returns <code>null</code>.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to validate.
	 * @param tslLocation URI string representation of the TSL location where to obtain it.
	 * @param date Date that must cover the TSL to obtain. If this parameter is <code>null</code>, then search
	 * the more updated TSL with that TSL location.
	 * @param checkStatusRevocation Flag that indicates if only try to detect the input certificate (<code>false</code>)
	 * or also checks the revocation status of this (<code>true</code>).
	 * @param calculateMappings Flag that indicates if it is necessary to calculate the mappings associated if the certificate
	 * has been detected (<code>true</code>) or not (<code>false</code>).
	 * @return TSL validation result, with all the collected information. If no TSL is finded for the certificate, then returns
	 * <code>null</code>.
	 * @throws TSLManagingException If there is some error with the cache or validating the certificate with the TSL.
	 */
	public ITSLValidatorResult validateX509withTSL(String auditTransNumber, X509Certificate cert, String tslLocation, Date date, boolean checkStatusRevocation, boolean calculateMappings) throws TSLManagingException {

		ITSLValidatorResult result = null;

		// Primero buscamos una TSL para el certificado.
		TSLObject tslObject = getTSLfromTSLLocation(tslLocation, date);

		// Si no hemos encontrado una TSL, lo indicamos en auditoría...
		if (tslObject == null) {

			// Añadimos la traza de auditoría indicando que no se ha encontrado.
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, false, null, null, null, null);

		} else {

			// Añadimos la traza de auditoría indicando que ha encontrado
			// junto con la información asociada.
			DateString tslIssued = new DateString(tslObject.getSchemeInformation().getListIssueDateTime());
			DateString tslNextUpdate = new DateString(tslObject.getSchemeInformation().getNextUpdate());
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, true, tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber(), tslIssued, tslNextUpdate);

			// Continuamos el proceso...
			result = validateX509withTSL(auditTransNumber, cert, date, checkStatusRevocation, calculateMappings, tslObject);

		}

		return result;

	}

	/**
	 * Tries to validate the input X509v3 certificate with.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to validate.
	 * @param validationDate Validation date to check.
	 * @param checkStatusRevocation Flag that indicates if only try to detect the input certificate (<code>false</code>)
	 * or also checks the revocation status of this (<code>true</code>).
	 * @param calculateMappings Flag that indicates if it is necessary to calculate the mappings associated if the certificate
	 * has been detected (<code>true</code>) or not (<code>false</code>).
	 * @param tslObject TSL object representation to use.
	 * @return TSL validation result, with all the collected information.
	 * @throws TSLManagingException If there is some error with the cache or validating the certificate with the TSL.
	 */
	private ITSLValidatorResult validateX509withTSL(String auditTransNumber, X509Certificate cert, Date validationDate, boolean checkStatusRevocation, boolean calculateMappings, TSLObject tslObject) throws TSLManagingException {

		LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL199, new Object[ ] { tslObject.getSchemeInformation().getTslVersionIdentifier(), tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber() }));

		ITSLValidatorResult result = null;

		// Tratamos de construir el validador de certificados mediante TSL.
		ITSLValidator tslValidator = TSLValidatorFactory.createTSLValidator(tslObject);

		// Si la fecha de validación es nula, utilizamos la fecha actual.
		Date validationDateToUse = validationDate;
		if (validationDateToUse == null) {
			validationDateToUse = Calendar.getInstance().getTime();
		}

		// Almacenamos en una variable si el certificado está orientado a
		// sellado de tiempo.
		boolean isTsaCertificate = checkIfCertificateIsForTSA(auditTransNumber, cert);

		// Guardamos en una variable si el certificado se corresponde
		// con el certificado de una CA.
		boolean isCACert = isTsaCertificate ? false : UtilsCertificate.isCA(cert);

		// Ejecutamos la validación del certificado con el validador construido
		// para la fecha indicada.
		try {
			result = tslValidator.validateCertificateWithTSL(auditTransNumber, cert, isCACert, isTsaCertificate, validationDateToUse, checkStatusRevocation);
		} catch (TSLArgumentException e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL149, new Object[ ] { tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber() }), e);
		} catch (TSLValidationException e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL149, new Object[ ] { tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber() }), e);
		}

		// Si no se ha producido excepción, el resultado no es nulo,
		// y el certificado ha sido detectado,
		// calculamos los mapeos asociados.
		if (calculateMappings) {
			calculateMappingsForCertificateAndSetInResult(auditTransNumber, cert, tslObject, result);
		}

		return result;

	}

	/**
	 * Checks if the input certificate has the key purpose for id-kp-timestamping.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 Certificate to check.
	 * @return <code>true</code> if the input certificate has the key purpose for id-kp-timestamping,
	 * otherwise <code>false</code>.
	 * @throws TSLManagingException In case of some error getting the keyPurpose list from the input certificate.
	 */
	private boolean checkIfCertificateIsForTSA(String auditTransNumber, X509Certificate cert) throws TSLManagingException {

		try {
			boolean result = UtilsCertificate.hasCertKeyPurposeTimeStamping(cert);
			if (result) {
				LOGGER.debug(Language.getResCoreTsl(CoreTslMessages.LOGMTSL200));
			}
			// Añadimos la traza de auditoría...
			CommonsCertificatesAuditTraces.addCertIsTsaCert(auditTransNumber, result);
			return result;
		} catch (CommonUtilsException e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL195), e);
		}

	}

	/**
	 * Method that uses the revocation values to validate the input certificate according the TSL associated to the certificate country.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to validate.
	 * @param validationDate Validation date to check.
	 * @param crls Array with the revocation values to analyze of type CRL.
	 * @param ocsps Array with the revocation values to analyze of type BasicOCSPResponse.
	 * @param calculateMappings Flag that indicates if it is necessary to calculate the mappings associated if the certificate
	 * has been detected (<code>true</code>) or not (<code>false</code>).
	 * @return TSL validation result, with all the collected information.
	 * @throws TSLManagingException In case of some error getting the TSL information from the cache, or using that to validate the certificate.
	 */
	public ITSLValidatorResult validateX509withTSLandRevocationValues(String auditTransNumber, X509Certificate cert, Date validationDate, X509CRL[ ] crls, BasicOCSPResp[ ] ocsps, boolean calculateMappings) throws TSLManagingException {

		ITSLValidatorResult result = null;

		// Si la fecha de validación es nula, utilizamos la fecha actual.
		Date validationDateToUse = validationDate;
		if (validationDateToUse == null) {
			validationDateToUse = Calendar.getInstance().getTime();
		}

		// Primero buscamos una TSL para el certificado.
		TSLObject tslObject = getTSLFromTheCountry(cert, validationDateToUse);

		// Si no hemos encontrado una TSL, lo indicamos en auditoría...
		if (tslObject == null) {

			// Añadimos la traza de auditoría indicando que no se ha encontrado.
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, false, null, null, null, null);
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL269));

		} else {

			// Añadimos la traza de auditoría indicando que ha encontrado
			// junto con la información asociada.
			DateString tslIssued = new DateString(tslObject.getSchemeInformation().getListIssueDateTime());
			DateString tslNextUpdate = new DateString(tslObject.getSchemeInformation().getNextUpdate());
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, true, tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber(), tslIssued, tslNextUpdate);
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL279));
			// Continuamos el proceso...
			result = validateX509withRevocationValues(auditTransNumber, cert, validationDateToUse, crls, ocsps, tslObject, calculateMappings);

		}

		return result;

	}

	/**
	 * Method that uses the revocation values to validate the input certificate according the TSL associated to the input URL location.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to validate.
	 * @param date Validation date to check.
	 * @param crls Array with the revocation values to analyze of type CRL.
	 * @param ocspResponses Array with the revocation values to analyze of type BasicOCSPResponse.
	 * @param tslLocation URI string representation of the TSL location where to obtain it.
	 * @param calculateMappings Flag that indicates if it is necessary to calculate the mappings associated if the certificate
	 * has been detected (<code>true</code>) or not (<code>false</code>).
	 * @return TSL validation result, with all the collected information.
	 * @throws TSLManagingException In case of some error getting the TSL information from the cache, or using that to validate the certificate.
	 */
	public ITSLValidatorResult validateX509withTSLLocationAndRevocationValues(String auditTransNumber, X509Certificate cert, Date date, X509CRL[ ] crls, BasicOCSPResp[ ] ocspResponses, String tslLocation, boolean calculateMappings) throws TSLManagingException {

		ITSLValidatorResult result = null;

		// Si la fecha de validación es nula, utilizamos la fecha actual.
		Date validationDateToUse = date;
		if (validationDateToUse == null) {
			validationDateToUse = Calendar.getInstance().getTime();
		}

		// Primero buscamos una TSL para el certificado.
		TSLObject tslObject = getTSLfromTSLLocation(tslLocation, validationDateToUse);

		// Si no hemos encontrado una TSL, lo indicamos en auditoría...
		if (tslObject == null) {

			// Añadimos la traza de auditoría indicando que no se ha encontrado.
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, false, null, null, null, null);

		} else {

			// Añadimos la traza de auditoría indicando que ha encontrado
			// junto con la información asociada.
			DateString tslIssued = new DateString(tslObject.getSchemeInformation().getListIssueDateTime());
			DateString tslNextUpdate = new DateString(tslObject.getSchemeInformation().getNextUpdate());
			CommonsTslAuditTraces.addTslFindedTrace(auditTransNumber, true, tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber(), tslIssued, tslNextUpdate);

			// Continuamos el proceso...
			result = validateX509withRevocationValues(auditTransNumber, cert, validationDateToUse, crls, ocspResponses, tslObject, calculateMappings);

		}

		return result;

	}

	/**
	 * Method that uses the revocation values to validate the input certificate according the input TSL.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate to validate.
	 * @param date Validation date to check.
	 * @param crls Array with the revocation values to analyze of type CRL.
	 * @param ocspResponses Array with the revocation values to analyze of type BasicOCSPResponse.
	 * @param tslObject TSL object representation to use.
	 * @param calculateMappings Flag that indicates if it is necessary to calculate the mappings associated if the certificate
	 * has been detected (<code>true</code>) or not (<code>false</code>).
	 * @return TSL validation result, with all the collected information.
	 * @throws TSLManagingException In case of some error verifying the revocation values.
	 */
	private ITSLValidatorResult validateX509withRevocationValues(String auditTransNumber, X509Certificate cert, Date date, X509CRL[ ] crls, BasicOCSPResp[ ] ocspResponses, TSLObject tslObject, boolean calculateMappings) throws TSLManagingException {

		// Obtenemos el resultado de comprobar si las evidencias de revocación
		// OCSP y/o CRL
		// están permitidas para la TSL indicada por su localizacion. Esto solo
		// ocurrirá si se encuentra
		// la TSL correspondiente y se detecta el tipo de certificado dentro de
		// esta.
		LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL199, new Object[ ] { tslObject.getSchemeInformation().getTslVersionIdentifier(), tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber() }));
		ITSLValidatorResult result = verifiesRevocationValuesForX509withTSL(auditTransNumber, cert, crls, ocspResponses, tslObject, date);

		// Calculamos los mapeos si procede.
		if (calculateMappings) {
			calculateMappingsForCertificateAndSetInResult(auditTransNumber, cert, tslObject, result);
		}

		return result;

	}

	/**
	 * Tries to verify that the input revocation values that are associated to the input X509v3 Certificate, are compatible
	 * with the TSL indentified by the input location.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate that must match with the revocation values.
	 * @param crls Array with the revocation values to analyze of type CRL.
	 * @param ocsps Array with the revocation values to analyze of type BasicOCSPResponse.
	 * @param tslObject TSL object representation to use.
	 * @param date Date that must cover the TSL to obtain. If this parameter is <code>null</code>, then search
	 * the more updated TSL with that TSL location.
	 * @return validation result object representation.
	 * @throws TSLManagingException If there is some error with the cache or verifying the revocation values.
	 */
	private ITSLValidatorResult verifiesRevocationValuesForX509withTSL(String auditTransNumber, X509Certificate cert, X509CRL[ ] crls, BasicOCSPResp[ ] ocsps, ITSLObject tslObject, Date date) throws TSLManagingException {

		ITSLValidatorResult result = null;

		// Comprobamos que han llegado evidencias de revocación.
		if (crls != null && crls.length > 0 || ocsps != null && ocsps.length > 0) {

			// Tratamos de construir el validador de certificados mediante TSL.
			ITSLValidator tslValidator = TSLValidatorFactory.createTSLValidator(tslObject);

			// Si la fecha de validación es nula, utilizamos la fecha actual.
			Date validationDateToUse = date;
			if (validationDateToUse == null) {
				validationDateToUse = Calendar.getInstance().getTime();
			}

			// Almacenamos en una variable si el certificado está orientado a
			// sellado de tiempo.
			boolean isTsaCertificate = checkIfCertificateIsForTSA(auditTransNumber, cert);

			// Guardamos en una variable si el certificado se corresponde
			// con el certificado de una CA.
			boolean isCACert = isTsaCertificate ? false : UtilsCertificate.isCA(cert);

			// Ejecutamos la validación del certificado con el validador
			// construido
			// para la fecha indicada.
			try {
				result = tslValidator.verifiesRevocationValuesForX509withTSL(auditTransNumber, cert, isCACert, isTsaCertificate, crls, ocsps, validationDateToUse);
			} catch (TSLArgumentException e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL149, new Object[ ] { tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber() }), e);
			} catch (TSLValidationException e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL149, new Object[ ] { tslObject.getSchemeInformation().getSchemeTerritory(), tslObject.getSchemeInformation().getTslSequenceNumber() }), e);
			}

		}

		return result;

	}

	/**
	 * Calculates the mapping for the input certificate and set these in the result. If there is some
	 * error parsing any mapping, then this is not returned.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate from which extracts the mapping information values.
	 * @param tslObject TSL object representation to use.
	 * @param tslValidationResult TSL validation result in which store the result mappings.
	 */
	private void calculateMappingsForCertificateAndSetInResult(String auditTransNumber, X509Certificate cert, TSLObject tslObject, ITSLValidatorResult tslValidationResult) {

		// Si el resultado no es nulo,
		// y el certificado ha sido detectado,
		// calculamos los mapeos asociados.
		if (tslValidationResult != null && tslValidationResult.hasBeenDetectedTheCertificate()) {

			// Obtenemos el código del país/región asociada al certificado.
			String countryCode = tslObject.getSchemeInformation().getSchemeTerritory();
			// Obtenemos de caché los mapeos asociados al país
			// del certificado.
			Set<TSLCountryRegionMappingCacheObject> tslCrmcoSet = null;
			try {
				tslCrmcoSet = ConfigurationCacheFacade.tslGetMappingFromCountryRegion(countryCode);
			} catch (Exception e) {
				LOGGER.error(Language.getResCoreTsl(CoreTslMessages.LOGMTSL150), e);
			}
			// Los calculamos y establecemos en el resultado.
			calculateMappingsForCertificateAndSetInResult(auditTransNumber, cert, tslCrmcoSet, tslValidationResult);

		}

	}

	/**
	 * Calculates the mapping for the input certificate and set these in the result. If there is some
	 * error parsing any mapping, then this is not returned.
	 * @param auditTransNumber Audit transaction number.
	 * @param cert X509v3 certificate from which extracts the mapping information values.
	 * @param tslCrmcoSet Set of mapping cached object representations to analyze.
	 * @param tslValidationResult TSL validation result where store the result mappings.
	 */
	private void calculateMappingsForCertificateAndSetInResult(String auditTransNumber, X509Certificate cert, Set<TSLCountryRegionMappingCacheObject> tslCrmcoSet, ITSLValidatorResult tslValidationResult) {

		// Iniciamos un map donde se almacenarán los pares <NombreMapeo,
		// ValorMapeo>.
		Map<String, String> mappings = new HashMap<String, String>();
		// Extraemos los valores de los mapeos fijos para todas las validaciones
		// mediante TSL.
		TSLValidatorMappingCalculator.extractStaticMappingsFromResult(mappings, tslValidationResult);

		// Extraemos los mapeos propios de la región.
		try {
			TSLValidatorMappingCalculator.extractMappingsFromCertificate(cert, mappings, tslCrmcoSet);
		} catch (TSLValidationException e) {
			LOGGER.error(Language.getResCoreTsl(CoreTslMessages.LOGMTSL152), e);
		}

		// Obtenemos los mapings de campos lógicos.
		
		String tspServiceName = tslValidationResult.getTSPServiceNameForDetect();
		try{
			TSLValidatorMappingCalculator.extractMappingsFromTSPService(cert, mappings, tspServiceName);
		}catch (TSLValidationException e) {
			LOGGER.error(Language.getResCoreTsl(CoreTslMessages.LOGMTSL355), e);
		}
		
		
		// Guardamos los mapeos calculados en el resultado de la validación.
		tslValidationResult.setMappings(mappings);
		// Lo indicamos en auditoría.
		CommonsCertificatesAuditTraces.addCertMappingFieldsTrace(auditTransNumber, mappings);

	}


	/**
	 * Checks if the input mapping name matches with some of the static mapping names.
	 * @param mappingName Mapping name to check.
	 * @return <code>true</code> if the input mapping name matches with some of the static mapping names,
	 * otherwise <code>false</code>.
	 */
	public boolean checksIfMappingNameMatchesWithSomeStaticMappingName(String mappingName) {
		return TSLValidatorMappingCalculator.checksIfMappingNameMatchesWithSomeStaticMappingName(mappingName);
	}

	/**
	 * Reloads the TSL cache. First clear all the data associated to the TSL in the cache, and then
	 * gets it from the data base and parses all the XML that reprensents its.
	 */
	public void reloadTSLCache() {

		LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL154));

		try {

			// Primero limpiamos el contenido actual de la caché (respecto a las
			// TSL).
			ConfigurationCacheFacade.tslClearTSLCache();

			mapCertificateTSL.clear();
			TslInformationTree.mapTslMappingTree.clear();

			// Obtenemos todas las regiones dadas de alta en base de datos.
			List<TslCountryRegion> tcrList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().getAllTslCountryRegion(false);

			// Si la lista obtenida no es nula ni vacía, contianuamos.
			if (tcrList != null && !tcrList.isEmpty()) {

				// Por cada una de las regiones...
				for (TslCountryRegion tcr: tcrList) {

					// Forzamos a que se cargue en caché.
					ConfigurationCacheFacade.tslGetTSLCountryRegionCacheObject(tcr.getCountryRegionCode());

					// Obtenemos el TSL Data asociado.
					TslData td = tcr.getTslData();

					// Si no es nulo, continuamos.
					if (td != null) {

						// Lo cargamos completamente.
						td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslDataById(td.getIdTslData(), true, false);

						// Tratamos de parsearlo.
						ITSLObject tslObject = buildAndCheckTSL(td);

						// Si lo hemos conseguido parsear...
						if (tslObject != null) {

							// Una vez parseado, lo damos de alta en la
							// caché compartida.
							ConfigurationCacheFacade.tslAddUpdateTSLData(td, tslObject);
							List<X509Certificate> listCertificates = getListCertificatesTSL(tslObject);
							mapCertificateTSL.put(tcr.getCountryRegionCode(), listCertificates);
							List<TslMappingDTO> listTslMappingTree = getListTslMappingTree(tcr.getCountryRegionCode(), td.getSequenceNumber().toString(), tslObject);
							TslInformationTree.mapTslMappingTree.put(tcr.getCountryRegionCode(), listTslMappingTree);
						}

					}

				}

			}

			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL155));

		} catch (Exception e) {
			LOGGER.error(Language.getResCoreTsl(CoreTslMessages.LOGMTSL153), e);
		}
	}

	/**
	 * Method to update the information of the TSL in mapTslMappingTree of the country passed as parameter.
	 * @param codeCountry Country/region code for the TSL.
	 */
	public void updateMapTslMappingTree(String codeCountry, String version, ITSLObject tslObject) {

		try {

			// Si lo hemos conseguido parsear...
			if (tslObject != null) {
				List<TslMappingDTO> listTslMappingTree = getListTslMappingTree(codeCountry, version, tslObject);
				TslInformationTree.mapTslMappingTree.put(codeCountry, listTslMappingTree);
			}

		} catch (Exception e) {
			LOGGER.error(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL354, new Object[ ] { e.getMessage() }), e);
		}
	}

	/**
	 * Method to delete the information of the TSL in mapTslMappingTree of the country passed as parameter.
	 * @param codeCountry Country/region code for the TSL
	 */
	public void deleteMapTslMappingTree(String codeCountry) {
		TslInformationTree.mapTslMappingTree.remove(codeCountry);
	}

	/**
	 * Method to extract the necessary information from each TSL to generate the tree of the TSL Mapping module.
	 * 
	 * @param codeCountry Country/region code for the TSL.
	 * @param version Version of TSL.
	 * @param tslObject TSL Object representation (already parsed)
	 * @return List of TslMappingDTO.
	 */
	private List<TslMappingDTO> getListTslMappingTree(String codeCountry, String version, ITSLObject tslObject) {
		LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL349));
		List<TslMappingDTO> result = new ArrayList<TslMappingDTO>();
		boolean error = Boolean.FALSE;
		// Recuperamos la lista de TSP y vamos analizando uno a uno.
		List<TrustServiceProvider> tspList = tslObject.getTrustServiceProviderList();
		// Si la lista no es nula ni vacía...
		if (tspList != null && !tspList.isEmpty()) {

			// La vamos recorriendo mientras no se termine y no se haya
			// modificado el resultado de la validación del certificado.
			for (int index = 0; index < tspList.size(); index++) {

				// Almacenamos en una variable el TSP a tratar.
				TrustServiceProvider tsp = tspList.get(index);
				List<String> tspNameList = tsp.getTspInformation().getTSPNamesForLanguage(Locale.UK.getLanguage());
				String tspName = tspNameList.get(0);

				List<TSPService> tspServiceList = tsp.getAllTSPServices();
				// Si la lista no es nula ni vacía...
				if (tspServiceList != null && !tspServiceList.isEmpty()) {
					for (int indexTspService = 0; indexTspService < tspServiceList.size(); indexTspService++) {

						TSPService tspService = tspServiceList.get(indexTspService);

						if (tspService != null) {
							ServiceHistoryInstance shi = tspService.getServiceInformation();
							// si el servicio es de tipo CA (certificados
							// cualificados o no).
							String tspServiceType = shi.getServiceTypeIdentifier().toString();
							if (!UtilsStringChar.isNullOrEmpty(tspServiceType)) {
								if (tspServiceType.equalsIgnoreCase(TSLCommonURIs.TSL_SERVICETYPE_CA_QC) || tspServiceType.equalsIgnoreCase(TSLCommonURIs.TSL_SERVICETYPE_CA_PKC) || tspServiceType.equalsIgnoreCase(TSLCommonURIs.TSL_SERVICETYPE_NATIONALROOTCA)) {
									TslMappingDTO tmDto = new TslMappingDTO(codeCountry, version, tspName);

									String tspServiceName = shi.getServiceNameInLanguage(Locale.UK.getLanguage());
									tmDto.setTspServiceName(tspServiceName);
									// se obtiene la identidad digital
									DigitalIdentitiesProcessor dipAux = new DigitalIdentitiesProcessor(shi.getAllDigitalIdentities());
									if (dipAux.getX509certList() != null && !dipAux.getX509certList().isEmpty()) {
										X509Certificate x509cert = dipAux.getX509certList().get(0);
										String digitalId = ""; // provisional
										try {
											digitalId = UtilsCrypto.calculateDigestReturnB64String(CryptographicConstants.HASH_ALGORITHM_SHA256, x509cert.getEncoded(), null);

										} catch (CommonUtilsException e) {
											error = Boolean.TRUE;
											LOGGER.error(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL351, new Object[ ] { codeCountry, tspName, tspServiceName }));

										} catch (CertificateEncodingException e) {
											error = Boolean.TRUE;
											LOGGER.error(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL352, new Object[ ] { codeCountry, tspName, tspServiceName }));
										}
										tmDto.setDigitalIdentity(digitalId);
										String expDate = UtilsCertificate.getValidTo(x509cert);
										tmDto.setExpirationDate(expDate);
									}
									if (!error)
										result.add(tmDto);
								}
							}

						}

					}

				}

			}
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL350));
		}
		return result;
	}

	/**
	 * Build and check a TSL Object from a TSL Data POJO.
	 * @param td Input POJO representation of a TSL Data in the data base.
	 * @return Generic TSL Object that represents, independently of its specification, the parsed
	 * and checked TSL. <code>null</code> if the input parameter is <code>null</code> or the POJO has
	 * not defined the XML of the TSL.
	 * @throws DatabaseConnectionException In case of some error of connection with the data base.
	 * @throws BusinessObjectException In case of some error managing objects with the data base.
	 * @throws TSLArgumentException In case of some arguments is wrong defined.
	 * @throws TSLMalformedException In case of the TSL is malformed (is not valid).
	 * @throws TSLParsingException In case of some error parsing the TSL.
	 */
	public ITSLObject buildAndCheckTSL(TslData td) throws TSLArgumentException, TSLParsingException, TSLMalformedException {

		ITSLObject result = null;

		// Si el pojo de entrada no es nulo...
		if (td != null) {

			// Si no es nulo...
			if (td.getXmlDocument() != null) {

				// Generamos el objeto resultante.
				result = new TSLObject(td.getTslImpl().getSpecification(), td.getTslImpl().getVersion());
				
				// Creamos un InputStream del array de bytes.
				try (ByteArrayInputStream bais = new ByteArrayInputStream(td.getXmlDocument())){

					// Finalmente contruimos la TSL y la chequeamos.
					result.buildTSLFromXMLcheckValuesCache(bais);

				} catch (IOException e) {
					LOGGER.error(Language.getFormatResCommonsUtilGeneral(CommonsUtilGeneralMessages.UTILS_RESOURCES_CODE_000, new Object[ ] { "ByteArrayInputStream" }), e);
				}

			}

		}

		return result;

	}

	// ////////////////////////////////////////////////////////////////////////////////////////////
	// MÉTODOS RELACIONADOS CON LA BASE DE DATOS Y LA CACHÉ.
	// ////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Reload the TSL Data in the cache.
	 * @param tslDataId TSL Data id to reload.
	 * @return An object representation of the TSL in the cache. <code>null</code> if it does not exist.
	 * @throws TSLManagingException In case of some error reloading the TSL.
	 */
	public TSLDataCacheObject reloadTSLDataInCache(long tslDataId) throws TSLManagingException {

		// Obtenemos la actual TSL de la caché.
		TSLDataCacheObject result = null;

		try {

			// Obtenemos el POJO de base de datos.
			TslData tslDataPojo = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslDataById(tslDataId, true, false);

			// Si no es nulo, tratamos de parsearlo.
			if (tslDataPojo != null) {

				// Tratamos de parsearlo.
				ITSLObject tslObject = buildAndCheckTSL(tslDataPojo);

				// Si lo hemos conseguido parsear...
				if (tslObject != null) {

					// Cargamos ahora el Country/Region correspondiente.
					TslCountryRegion tslCountryRegionPojo = tslDataPojo.getTslCountryRegion();

					// Obtenemos la actual TSL de la caché.
					result = getTSLDataCacheObject(tslDataId);

					// Si la hemos obtenido, hay que eliminarla.
					if (result != null) {
						// Eliminamos el que hay en la caché.
						ConfigurationCacheFacade.tslRemoveTSLDataFromCountryRegion(tslCountryRegionPojo.getCountryRegionCode());
					}

					// Una vez parseado, y eliminada de caché la existente,
					// lo damos de alta.
					result = ConfigurationCacheFacade.tslAddUpdateTSLData(tslDataPojo, tslObject);

				}

			}

		} catch (TSLCacheException | TSLArgumentException | TSLParsingException
				| TSLMalformedException e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL197), e);
		}

		return result;

	}

	/**
	 * Reload the selected TSL Data in the cache.
	 * @param tslDataIds Array of TSL Data id to reload.
	 * @return An array of object representation of the TSL in the cache. <code>null</code> if these do not exist
	 * or the input array is <code>null</code> or empty.
	 * @throws TSLManagingException In case of some error reloading the TSL.
	 */
	public TSLDataCacheObject[ ] reloadTSLDataInCache(long[ ] tslDataIds) throws TSLManagingException {

		TSLDataCacheObject[ ] result = null;

		if (tslDataIds != null && tslDataIds.length > 0) {

			result = new TSLDataCacheObject[tslDataIds.length];

			for (int index = 0; index < tslDataIds.length; index++) {

				result[index] = reloadTSLDataInCache(tslDataIds[index]);

			}

		}

		return result;

	}

	/**
	 * Gets a {@link Map} with the structure <Specification, Set<Version>> of the differents Specifications and
	 * versions for TSL that exists in the platform.
	 * @return {@link Map} with the structure <Specification, Set<Version>> of the differents Specifications and
	 * versions for TSL that exists in the platform.
	 * @throws TSLManagingException In case of some error with the data base connection.
	 */
	public Map<String, Set<String>> getsTSLRelationSpecificationAndVersion() throws TSLManagingException {

		Map<String, Set<String>> result = new HashMap<String, Set<String>>();

		// Obtenemos todas las constantes que definen algún tipo de
		// implementación de TSL.
		List<CTslImpl> ctiList;
		try {
			ctiList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService().getAllCTSLImpl();
		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL158), e);
		}

		// Los recorremos y vamos añadiendo en el map.
		for (CTslImpl cti: ctiList) {

			// Obtenemos el listado de versiones almacenado para la
			// especificación actual.
			Set<String> versions = result.get(cti.getSpecification());
			// Si es nulo, es porque aún no se a iniciado el listado.
			if (versions == null) {
				versions = new TreeSet<String>();
			}
			// Añadimos la versión.
			versions.add(cti.getVersion());
			result.put(cti.getSpecification(), versions);

		}

		return result;

	}

	/**
	 * Checks if the given country/region is already registered in the system.
	 * @param countryRegion Country/Region code ISO 3166.
	 * @return <code>true</code> if the input country/region is already registered, otherwise <code>false</code>.
	 * @throws TSLManagingException In case of some error getting the country/region configuration.
	 */
	public boolean isDefinedCountryRegion(String countryRegion) throws TSLManagingException {

		boolean result = false;

		if (!UtilsStringChar.isNullOrEmptyTrim(countryRegion)) {
			try {
				result = ConfigurationCacheFacade.tslGetTSLCountryRegionCacheObject(countryRegion) != null;
			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL159), e);
			}
		}

		return result;

	}

	/**
	 * Gets all the countries/regions codes that are defined in the system.
	 * @return {@link List} with all countries/regions codes that are defined in the system.
	 * <code>null</code> id there is not.
	 * @throws TSLManagingException In case of some error getting all the region codes.
	 */
	public List<String> getAllTSLCountriesRegionsCodes() throws TSLManagingException {

		List<String> result = null;

		// Obtenemos de base de datos el listado de países y regiones dados de
		// alta.
		try {
			result = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().getAllCodeTslCountryRegion();
		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL160), e);
		}

		return result;

	}

	/**
	 * Gets the country/region information from the cache.
	 * @param countryRegionCode Country/Region code to search in the cache.
	 * @return TSL country/region cache object representation. <code>null</code> if it does not exist.
	 * @throws TSLManagingException In case of some error getting the country/region information.
	 */
	public TSLCountryRegionCacheObject getTSLCountryRegionCacheObject(String countryRegionCode) throws TSLManagingException {

		TSLCountryRegionCacheObject result = null;

		if (!UtilsStringChar.isNullOrEmptyTrim(countryRegionCode)) {
			try {
				result = ConfigurationCacheFacade.tslGetTSLCountryRegionCacheObject(countryRegionCode.toUpperCase());
			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL161, new Object[ ] { countryRegionCode }), e);
			}
		}

		return result;

	}

	/**
	 * Updates the name of a specific country/region.
	 * @param tslcrId Data base identifier of the POJO that represents the country/region to update.
	 * @param newName New name to assign to the country/region. It can not be <code>null</code>.
	 * @throws TSLManagingException In case of some error updating the name of the country/region.
	 */
	public void updateTSLCountryRegionName(Long tslcrId, String newName) throws TSLManagingException {

		// Solo se actualizará si el nombre no es nulo ni vacío.
		if (!UtilsStringChar.isNullOrEmpty(newName)) {

			try {

				// Recuperamos de base de datos el POJO que representa al
				// país/región.
				TslCountryRegion tslcr = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().getTslCountryRegionById(tslcrId, false);

				// Si el POJO obtenido no es nulo, continuamos...
				if (tslcr != null) {

					// Recuperamos de caché el objeto que lo representa.
					TSLCountryRegionCacheObject tslcrco = ConfigurationCacheFacade.tslGetTSLCountryRegionCacheObject(tslcr.getCountryRegionCode());

					// Si el objeto recuperado de caché tampoco es nulo,
					// continuamos...
					if (tslcrco != null) {

						// Actualizamos el POJO en base de datos.
						tslcr.setCountryRegionName(newName);
						tslcr = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().updateSaveTslCountryRegion(tslcr);

						// Ahora lo actualizamos en la caché.
						tslcrco.setName(newName);
						ConfigurationCacheFacade.tslAddUpdateBasicTSLCountryRegion(tslcrco);

					}

				}

			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL162, new Object[ ] { tslcrId }), e);
			}

		}

	}

	/**
	 * Remove a TSL Country/Region from the data base and the cache.
	 * @param countryRegionCode Country/Region code that represents the country/region to remove.
	 * @throws TSLManagingException In case of some error while is removing the country/region from the data base and cache.
	 */
	public void removeTSLCountryRegion(String countryRegionCode) throws TSLManagingException {

		// Comprobamos que el código del país/región a eliminar no sea nulo.
		if (!UtilsStringChar.isNullOrEmptyTrim(countryRegionCode)) {

			try {

				// Recuperamos el objeto de la caché.
				TSLCountryRegionCacheObject tcrco = ConfigurationCacheFacade.tslGetTSLCountryRegionCacheObject(countryRegionCode);

				// Si lo hemos recuperado, continuamos...
				if (tcrco != null) {

					// Lo borramos de base de datos.
					ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().deleteTslCountryRegionById(tcrco.getCountryRegionId());

					// Lo borramos de la caché compartida.
					ConfigurationCacheFacade.tslRemoveTSLCountryRegion(countryRegionCode);

				}

			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL163, new Object[ ] { countryRegionCode }), e);
			}

		}

	}

	/**
	 * Gets all the mappings associated to the country/region represented by the input code.
	 * @param countryRegionCode Country/Region code representation.
	 * @return {@link Set} of TSL Country/Region Mapping Cache Object. <code>null</code> if there is not.
	 * @throws TSLManagingException In case of some error getting all the mapping information about the specified country/region.
	 */
	public Set<TSLCountryRegionMappingCacheObject> getAllMappingsFromCountryRegion(String countryRegionCode) throws TSLManagingException {

		Set<TSLCountryRegionMappingCacheObject> result = null;

		if (!UtilsStringChar.isNullOrEmptyTrim(countryRegionCode)) {
			try {
				result = ConfigurationCacheFacade.tslGetMappingFromCountryRegion(countryRegionCode);
			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL164, new Object[ ] { countryRegionCode }), e);
			}
		}

		return result;

	}

	/**
	 * Method that removes all the mappings associated with a country.
	 * @param countryRegionCode  Attribute that represents the country/region code for a TSL (ISO 3166).
	 * @throws TSLManagingException In case of some error remove all the mapping information about the specified country/region.
	 */
	public void deleteAllMappingFromCountryRegion(Long idCountryRegion, String countryRegionCode) throws TSLManagingException {

		try {
			// Lo borramos de base de datos.
			ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService().deleteTslCountryRegionMappingByCountry(idCountryRegion);
			// Lo borramos de la caché.

			ConfigurationCacheFacade.tslRemoveMappingFromCountryRegion(countryRegionCode, null);
		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL268, new Object[ ] { countryRegionCode }), e);
		}
	}

	/**
	 * Gets a country/region mapping information from the cache.
	 * @param countryRegionCode Country/Region code representation.
	 * @param mappingId Country/Region Mapping ID to get.
	 * @return TSL country/region mapping cache object representation. <code>null</code> if it does not exist.
	 * @throws TSLManagingException In case of some error getting the country/region mapping information.
	 */
	public TSLCountryRegionMappingCacheObject getTSLCountryRegionMappingCacheObject(String countryRegionCode, long mappingId) throws TSLManagingException {

		TSLCountryRegionMappingCacheObject result = null;

		// Recuperamos la lista de mapeos asociadas al país/región indicado.
		Set<TSLCountryRegionMappingCacheObject> mappingsSet = getAllMappingsFromCountryRegion(countryRegionCode);
		// Si el conjunto de mapeos asociados no es nulo ni vacío...
		if (mappingsSet != null && !mappingsSet.isEmpty()) {
			// Los recorremos hasta encontrar el que corresponde con el ID de
			// entrada...
			for (TSLCountryRegionMappingCacheObject tcrmco: mappingsSet) {
				if (tcrmco.getMappingId() == mappingId) {
					result = tcrmco;
					break;
				}
			}
		}

		return result;

	}

	/**
	 * Checks if the input identificator for a mapping in the specified country/region already is assigned to other mapping.
	 * @param countryRegionCode Country/Region code representation.
	 * @param mappingId ID of the mapping. If the mapping is new and does not exist in the data base, then this parameter value must
	 * be <code>null</code>.
	 * @param identificatorToCheck Mapping identificator to check if already exists in the data base for that country/region.
	 * @return <code>true</code> if the input mapping identificator is already defined for other mapping in the country/region specified.
	 * Otherwise <code>false</code>.
	 * @throws TSLManagingException In case of some error checking if the mapping identificator already is defined.
	 */
	public boolean checkIfTSLCountryRegionMappingIdentificatorIsAlreadyDefined(String countryRegionCode, Long mappingId, String identificatorToCheck) throws TSLManagingException {

		boolean result = false;

		// Recuperamos el conjunto de mapeos asociados al país/región.
		Set<TSLCountryRegionMappingCacheObject> mappingsSet = getAllMappingsFromCountryRegion(countryRegionCode);

		// Si no es nulo ni vacío...
		if (mappingsSet != null && !mappingsSet.isEmpty()) {

			// Lo recorremos y vamos comparando el identificador...
			for (TSLCountryRegionMappingCacheObject tcrmco: mappingsSet) {

				// Si los identificadores son iguales...
				if (tcrmco.getIdentificator().equals(identificatorToCheck)) {

					// Si el ID del mapeo de entrada es nulo, o distinto al
					// mapeo
					// encontrado, significa que el mapeo ya existe.
					if (mappingId == null || mappingId.longValue() != tcrmco.getMappingId()) {

						result = true;
						break;

					}

				}

			}

		}

		return result;

	}

	/**
	 * Adds a new mapping associated to the specified country/region.
	 * @param countryRegionCode Country/Region code that represnets the country/region in which add the mapping.
	 * @param mappingIdentificator Mapping identificator.
	 * @param mappingDescription Mapping Description. It is the only one that can be <code>null</code>.
	 * @param mappingValue Mapping value.
	 * @param associationType Association type for the mapping. It only must be {@link AssociationTypeIdConstants#ID_FREE_ASSOCIATION}
	 * or {@link AssociationTypeIdConstants#ID_SIMPLE_ASSOCIATION}.
	 * @return TSL Country/Region Mapping data base object representation that has been added. <code>null</code> if there is
	 * some problem with the input parameters, adding the mapping to the data base or if the TSL Country/Region is not defined.
	 * @throws TSLManagingException In case of some error adding the new mapping to the data base and cache.
	 */
	public TslCountryRegionMapping addNewMappingToCountryRegion(String countryRegionCode, String mappingIdentificator, String mappingDescription, String mappingValue, Long associationType) throws TSLManagingException {

		// Inicializamos el resultado...
		TslCountryRegionMapping result = null;

		// Comprobamos que los parámetros de entrada son válidos.
		if (!UtilsStringChar.isNullOrEmptyTrim(countryRegionCode) && !UtilsStringChar.isNullOrEmptyTrim(mappingIdentificator) && !UtilsStringChar.isNullOrEmptyTrim(mappingValue) && associationType != null) {

			try {

				// Recuperamos de la caché el país/región.
				TSLCountryRegionCacheObject tslcrco = ConfigurationCacheFacade.tslGetTSLCountryRegionCacheObject(countryRegionCode.toUpperCase());

				// Si lo hemos encontrado...
				if (tslcrco != null) {

					// Recuperamos de base de datos el POJO correspondiente.
					TslCountryRegion tslcr = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().getTslCountryRegionById(tslcrco.getCountryRegionId(), false);

					// Si lo hemos encontrado...
					if (tslcr != null) {

						// Creamos el nuevo POJO que representará al mapeo y lo
						// añadimos
						// en base de datos y caché.
						CAssociationType cat = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCAssociationTypeService().getAssociationTypeById(associationType);
						TslCountryRegionMapping tslcrm = new TslCountryRegionMapping();
						tslcrm.setTslCountryRegion(tslcr);
						tslcrm.setMappingIdentificator(mappingIdentificator);
						tslcrm.setMappingDescription(mappingDescription);
						tslcrm.setAssociationType(cat);
						tslcrm.setMappingValue(mappingValue);
						tslcrm = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService().save(tslcrm);

						ConfigurationCacheFacade.tslAddUpdateMappingToCountryRegion(countryRegionCode, tslcrm);

						// Asignamos el objeto añadido en base de datos como
						// resultado.
						result = tslcrm;

					}

				}

			} catch (Exception e) {

				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL165, new Object[ ] { countryRegionCode }), e);

			}

		}

		return result;

	}

	/**
	 * Updates a country/region mapping in the data base and cache.
	 * @param mappingId Country/Region Mapping ID in the data base.
	 * @param mappingIdentificator Mapping identificator to assign.
	 * @param mappingDescription Mapping description. It is the only one that can be <code>null</code>.
	 * @param mappingValue Mapping value to assign.
	 * @param associationType Association type for the mapping. It only must be {@link AssociationTypeIdConstants#ID_FREE_ASSOCIATION}
	 * or {@link AssociationTypeIdConstants#ID_SIMPLE_ASSOCIATION}.
	 * @return TSL Country/Region Mapping data base object representation that has been updated. <code>null</code> if there is
	 * some problem with the input parameters, adding the mapping to the data base or if the TSL Country/Region is not defined.
	 * @throws TSLManagingException In case of some error updating a TSL mapping.
	 */
	public TslCountryRegionMapping updateTSLCountryRegionMapping(Long mappingId, String mappingIdentificator, String mappingDescription, String mappingValue, Long associationType) throws TSLManagingException {
		// Inicializamos el resultado...
		TslCountryRegionMapping result = null;

		// Comprobamos que los parámetros de entrada son válidos.
		if (mappingId != null && !UtilsStringChar.isNullOrEmptyTrim(mappingIdentificator) && !UtilsStringChar.isNullOrEmptyTrim(mappingValue) && associationType != null) {

			try {

				// Recuperamos el POJO que representa al mapeo.
				TslCountryRegionMapping tslcrm = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService().getTslCountryRegionMappingById(mappingId);

				// Si lo hemos recuperado...
				if (tslcrm != null) {

					// Recuperamos el país/región asociado.
					TslCountryRegion tslcr = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().getTslCountryRegionById(tslcrm.getTslCountryRegion().getIdTslCountryRegion(), false);

					// Si lo hemos recuperado...
					if (tslcr != null) {

						// Lo actualizamos en base de datos.
						tslcrm.setMappingIdentificator(mappingIdentificator);
						tslcrm.setMappingDescription(mappingDescription);
						tslcrm.setMappingValue(mappingValue);
						CAssociationType cat = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCAssociationTypeService().getAssociationTypeById(associationType);
						tslcrm.setAssociationType(cat);
						ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService().save(tslcrm);

						// Lo actualizamos en la caché,
						ConfigurationCacheFacade.tslAddUpdateMappingToCountryRegion(tslcr.getCountryRegionCode(), tslcrm);

					}
					result = tslcrm;
				}

			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL166, new Object[ ] { mappingId }), e);
			}

		}
		return result;

	}

	/**
	 * Removes the specified country/region mapping from the data base and the cache.
	 * @param countryRegionCode Country/Region code that represnets the country/region from which removes the mapping.
	 * @param mappingId TSL country/region mapping ID to remove.
	 * @throws TSLManagingException In case of some error removing a specific TSL mapping from the data base or cache.
	 */
	public void removeTSLCountryRegionMapping(String countryRegionCode, long mappingId) throws TSLManagingException {

		// Comprobamos que la región/país no es nula.
		if (!UtilsStringChar.isNullOrEmptyTrim(countryRegionCode)) {

			try {

				// Lo borramos de base de datos.
				ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService().deleteTslCountryRegionMapping(mappingId);
				// Lo borramos de la caché.
				ConfigurationCacheFacade.tslRemoveMappingFromCountryRegion(countryRegionCode, mappingId);
				// se actualiza el mapa mapTslMappingTree
				deleteMapTslMappingTree(countryRegionCode);
			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL167, new Object[ ] { mappingId, countryRegionCode }), e);
			}

		}

	}

	/**
	 * Gets the TSL Data associated to a specific country/region from the cache.
	 * @param countryRegionCode Country/Region code to search in the cache.
	 * @return TSL Data Cache Object representation. <code>null</code> if there is not.
	 * @throws TSLManagingException In case of some error getting the TSL associated to a specific country/region.
	 */
	public TSLDataCacheObject getTSLDataFromCountryRegion(String countryRegionCode) throws TSLManagingException {

		TSLDataCacheObject result = null;

		if (!UtilsStringChar.isNullOrEmptyTrim(countryRegionCode)) {

			try {
				result = ConfigurationCacheFacade.tslGetTSLDataFromCountryRegion(countryRegionCode);

				// Si no lo hemos obtenido, puede ser porque no exista,
				// o porque no se haya cargado el TSL Data en caché.
				if (result == null) {

					// Intentamos recuperar el TSL Country/Region.
					TSLCountryRegionCacheObject tcrco = ConfigurationCacheFacade.tslGetTSLCountryRegionCacheObject(countryRegionCode);

					// Si no es nulo...
					if (tcrco != null) {

						// Buscamos en base de datos un TSL Data para este.
						TslCountryRegion tcr = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().getTslCountryRegionById(tcrco.getCountryRegionId(), false);
						TslData tslData = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslByCountryRegion(tcr, true, false);

						// Si lo hemos encontrado significa que hay que añadirlo
						// a la caché...
						if (tslData != null) {

							// Contruimos el InputStream asociado al array, y
							// tratamos de
							// parsearlo y añadirlo.
							ByteArrayInputStream bais = new ByteArrayInputStream(tslData.getXmlDocument());
							ITSLObject tslObject = null;
							try {
								tslObject = new TSLObject(tslData.getTslImpl().getSpecification(), tslData.getTslImpl().getVersion());
								tslObject.buildTSLFromXMLcheckValues(bais);
							} finally {
								UtilsResources.safeCloseInputStream(bais);
							}

							// Lo añadimos en la caché...
							result = ConfigurationCacheFacade.tslAddUpdateTSLData(tslData, tslObject);

						}

					}

				}

			} catch (TSLException | TSLCacheException e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL168, new Object[ ] { countryRegionCode }), e);
			}

		}

		return result;

	}

	/**
	 * Gets the TSL Data associated to a specific TSL location from the cache.
	 * @param tslLocation URI string representation of the TSL location where to obtain it.
	 * @return TSL Data Cache Object representation. <code>null</code> if there is not.
	 * @throws TSLManagingException In case of some error getting the TSL associated to a specific TSL location.
	 */
	public TSLDataCacheObject getTSLDataFromTSLLocation(String tslLocation) throws TSLManagingException {

		TSLDataCacheObject result = null;

		if (!UtilsStringChar.isNullOrEmptyTrim(tslLocation)) {

			try {
				result = ConfigurationCacheFacade.tslGetTSLDataFromLocation(tslLocation);

				// Si no lo hemos obtenido, puede ser porque no exista,
				// o porque no se haya cargado el TSL Data en caché.
				if (result == null) {

					// Lo buscamos en base de datos...
					TslData tslData = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslByTslLocation(tslLocation, true, false);

					// Si lo hemos encontrado significa que hay que añadirlo a
					// la caché...
					if (tslData != null) {

						// Contruimos el InputStream asociado al array, y
						// tratamos de
						// parsearlo y añadirlo.
						ByteArrayInputStream bais = new ByteArrayInputStream(tslData.getXmlDocument());
						ITSLObject tslObject = null;
						try {
							tslObject = new TSLObject(tslData.getTslImpl().getSpecification(), tslData.getTslImpl().getVersion());
							tslObject.buildTSLFromXMLcheckValues(bais);
						} finally {
							UtilsResources.safeCloseInputStream(bais);
						}

						// Lo añadimos en la caché...
						result = ConfigurationCacheFacade.tslAddUpdateTSLData(tslData, tslObject);

					}

				}

			} catch (TSLException | TSLCacheException e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_191, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL103, new Object[ ] { tslLocation, DATE_NOT_SPECIFIED }), e);
			}

		}

		return result;

	}

	/**
	 * Gets the TSL Data Cache Object representation with the input ID.
	 * @param tslDataId TSL Data ID to search.
	 * @return a TSL Data Cache Object representation with the input TSL Data ID.
	 * <code>null</code> if there is not exist.
	 * @throws TSLManagingException In case of some error getting the data from the cache.
	 */
	public TSLDataCacheObject getTSLDataCacheObject(long tslDataId) throws TSLManagingException {

		try {
			return ConfigurationCacheFacade.tslGetTSLDataCacheObject(tslDataId);
		} catch (TSLCacheException e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL174, new Object[ ] { tslDataId }), e);
		}

	}

	// /**
	// * Gets the TSL Data Cache Object representation with the input IDs.
	// * @param tslDataIds TSL Data ID to search.
	// * @return a TSL Data Cache Object array representation with the input TSL
	// Data IDs.
	// * <code>null</code> if these are not exist.
	// * @throws TSLManagingException In case of some error getting the data
	// from the cache.
	// */
	// public TSLDataCacheObject[ ] getTSLDataCacheObject(long[ ] tslDataIds)
	// throws TSLManagingException {
	//
	// TSLDataCacheObject[ ] result = null;
	// if (tslDataIds != null && tslDataIds.length > 0) {
	// result = new TSLDataCacheObject[tslDataIds.length];
	// for (int index = 0; index < tslDataIds.length; index++) {
	// result[index] = getTSLDataCacheObject(tslDataIds[index]);
	// }
	// }
	// return result;
	//
	// }

	/**
	 * Gets the Scheme Name associated to the TSL represented by the input ID.
	 * @param tslDataId TSL Data ID to search.
	 * @return Scheme Name associated to the TSL represented by the input ID. <code>null</code> if
	 * the TSL does not exist.
	 * @throws TSLManagingException In case of some error getting the data from the cache.
	 */
	public String getTSLSchemeName(long tslDataId) throws TSLManagingException {

		String result = null;

		// Recuperamos de caché los datos de la TSL.
		TSLDataCacheObject tsldco = getTSLDataCacheObject(tslDataId);

		// Si la hemos encontrado...
		if (tsldco != null) {

			// Recuperamos su objeto serializable.
			ITSLObject tslObject = (ITSLObject) tsldco.getTslObject();
			// Recuperamos el nombre del esquema en inglés.
			result = tslObject.getSchemeInformation().getSchemeName(Locale.UK.getLanguage());
			// Si no lo hemos recuperado, tomamos el primero que haya.
			if (UtilsStringChar.isNullOrEmptyTrim(result)) {
				result = tslObject.getSchemeInformation().getSchemeNames().values().iterator().next();
			}

		}

		return result;

	}

	/**
	 * Gets the Scheme Operator Name associated to the TSL represented by the input ID.
	 * @param tslDataId TSL Data ID to search.
	 * @return Scheme Operator Name associated to the TSL represented by the input ID. <code>null</code> if
	 * the TSL does not exist.
	 * @throws TSLManagingException In case of some error getting the data from the cache.
	 */
	public String getTSLSchemeOperatorName(long tslDataId) throws TSLManagingException {

		String result = null;

		// Recuperamos de caché los datos de la TSL.
		TSLDataCacheObject tsldco = getTSLDataCacheObject(tslDataId);

		// Si la hemos encontrado...
		if (tsldco != null) {

			// Recuperamos su objeto serializable.
			ITSLObject tslObject = (ITSLObject) tsldco.getTslObject();
			// Recuperamos el nombre del operador del esquema en inglés.
			List<String> sonList = tslObject.getSchemeInformation().getSchemeOperatorNameInLanguage(Locale.UK.getLanguage());
			if (sonList != null && !sonList.isEmpty()) {
				result = sonList.get(0);
			}
			// Si no lo hemos recuperado, tomamos el primero que haya.
			if (UtilsStringChar.isNullOrEmptyTrim(result)) {
				result = tslObject.getSchemeInformation().getSchemeOperatorNames().values().iterator().next().get(0);
			}

		}

		return result;

	}

	/**
	 * Gets the XML that defines the TSL with the input ID.
	 * @param tslDataId TSL data ID from which gets the XML.
	 * @return array of bytes that represents the XML of the TSL, or <code>null</code> if it does not exists.
	 * @throws TSLManagingException In case of some error getting the XML from the data base.
	 */
	public byte[ ] getTSLDataXMLDocument(long tslDataId) throws TSLManagingException {

		byte[ ] result = null;

		try {

			// Cargamos el pojo de base de datos.
			TslData td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslDataById(tslDataId, true, false);
			// Lo devolvemos si está definida.
			if (td != null) {
				result = td.getXmlDocument();
			}

		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL175, new Object[ ] { tslDataId }), e);
		}

		return result;

	}

	/**
	 * Gets the legible document that defines the TSL with the input ID.
	 * @param tslDataId TSL data ID from which gets the legible document.
	 * @return array of bytes that represents the legible document of the TSL.
	 * @throws TSLManagingException In case of some error getting the legible document from the data base.
	 */
	public byte[ ] getTSLLegibleDocument(long tslDataId) throws TSLManagingException {

		try {

			// Cargamos el pojo de base de datos.
			TslData td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslDataById(tslDataId, false, true);
			// Lo devolvemos.
			return td.getLegibleDocument();

		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL176, new Object[ ] { tslDataId }), e);
		}

	}

	/**
	 * Adds a new TSL Data in the data base and in the cache.
	 * @param urlTsl URL location for the TSL.
	 * @param tslSpecification TSL Specification that covers the input TSL.
	 * @param tslSpecificationVersion TSL Specification Version that covers the input TSL.
	 * @param tslXMLbytes Array of bytes that defines the TSL in a XML format.
	 * @return TSL Data data base object representation of the TSL data added. <code>null</code> if
	 * some input parameter is not correctly defined.
	 * @throws TSLManagingException In case of some error adding the TSL in the data base or the cache.
	 */
	public TslData addNewTSLData(String urlTsl, String tslSpecification, String tslSpecificationVersion, byte[ ] tslXMLbytes) throws TSLManagingException {

		TslData result = null;

		// Comprobamos que los parámetros de entrada sean válidos.
		if (!UtilsStringChar.isNullOrEmptyTrim(tslSpecification) && !UtilsStringChar.isNullOrEmptyTrim(tslSpecificationVersion) && tslXMLbytes != null) {

			// Contruimos el InputStream asociado al array, y tratamos de
			// parsearlo y añadirlo.
			ByteArrayInputStream bais = new ByteArrayInputStream(tslXMLbytes);
			ITSLObject tslObject = null;
			// try {
			try {
				tslObject = new TSLObject(tslSpecification, tslSpecificationVersion);

				tslObject.buildTSLFromXMLcheckValues(bais);
			} catch (TSLMalformedException e) {
				if (e.getErrorCode() != null && e.getErrorCode().equals(ValetExceptionConstants.COD_204)) {
					throw new TSLManagingException(ValetExceptionConstants.COD_204, e.getMessage(), e);
				} else {
					throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL170), e);
				}

			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL170), e);
			} finally {
				UtilsResources.safeCloseInputStream(bais);
			}

			// Una vez parseada la TSL, comprobamos a que país/region pertenece.
			String schemeTerritory = tslObject.getSchemeInformation().getSchemeTerritory();

			try {

				// Recuperamos de la caché el país/región.
				TSLCountryRegionCacheObject tcrco = ConfigurationCacheFacade.tslGetTSLCountryRegionCacheObject(schemeTerritory);

				// Si es nulo, lo añadimos en base de datos y lo volvemos a
				// recuperar.
				if (tcrco == null) {

					addNewTSLCountryRegionInDataBase(schemeTerritory);
					tcrco = ConfigurationCacheFacade.tslGetTSLCountryRegionCacheObject(schemeTerritory);

				}

				// Si el país/región ya tiene un TSL Data asociado, lo
				// eliminamos.
				if (tcrco.getTslDataId() != null) {

					removeTSLData(tcrco.getCode(), tcrco.getTslDataId());

				}

				// Añadimos un nuevo TSL Data asociado al país/región.
				TslData td = addNewTSLDataInDataBase(tcrco.getCountryRegionId(), urlTsl, tslXMLbytes, tslObject);

				// Y ahora lo añadimos en la caché compartida.
				ConfigurationCacheFacade.tslAddUpdateTSLData(td, tslObject);

				// Asignamos como resultado el objeto de base de datos.
				result = td;

				// se actualiza la información en los datos del arbol de mapeos
				// de
				// TSLs.
				updateMapTslMappingTree(td.getTslCountryRegion().getCountryRegionCode(), td.getSequenceNumber().toString(), tslObject);
				
				// Se actualizan los accessos externos con las url de los distintos distribution point que contenga la tsl.
				ApplicationContextProvider.getApplicationContext().getBean(ExternalAccessService.class).new ExternalAccessServiceThread(ExternalAccessService.OPERATION2, tslObject).start();
			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL171), e);
			}

		}

		return result;

	}

	/**
	 * Adds a new TSL Country Region in the data base.
	 * @param countryRegionCode Country/Region code representation.
	 * @throws DatabaseConnectionException In case of some error with the data base connection.
	 * @throws BusinessObjectException In case of some error managing POJOs.
	 */
	private void addNewTSLCountryRegionInDataBase(String countryRegionCode) {

		// Tratamos de obtener el nombre del país primero.
		String countryRegionName = UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(countryRegionCode);

		// Construimos el POJO y lo almacenamos en base de datos.
		TslCountryRegion tcr = new TslCountryRegion();
		tcr.setCountryRegionCode(countryRegionCode);
		tcr.setCountryRegionName(countryRegionName);

		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().updateSaveTslCountryRegion(tcr);

	}

	/**
	 * Add a new TSL Data in the data base.
	 * @param countryRegionId Country/Region ID to which add the new TSL Data.
	 * @param urlTsl URL location for the TSL.
	 * @param tslXMLbytes Array of bytes that represents the XML of the TSL.
	 * @param tslObject TSL Object representation (already parsed).
	 * @return the TSL Data POJO added.
	 */
	private TslData addNewTSLDataInDataBase(long countryRegionId, String urlTsl, byte[ ] tslXMLbytes, ITSLObject tslObject) {

		// Primero recuperamos el país/región.
		TslCountryRegion tcrp = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().getTslCountryRegionById(countryRegionId, false);

		// Recuperamos la constante que representa la especificación y versión
		// asociada a la TSL.
		CTslImpl ctip = null;
		List<CTslImpl> cTslImplList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService().getAllCTSLImpl();
		for (CTslImpl ctslImplPojo: cTslImplList) {

			if (tslObject.getSpecification().equals(ctslImplPojo.getSpecification()) && tslObject.getSpecificationVersion().equals(ctslImplPojo.getVersion())) {
				ctip = ctslImplPojo;
			}

		}

		// Counstruimos el TslDataPojo y vamos insertando los datos.
		TslData td = new TslData();
		td.setTslCountryRegion(tcrp);
		td.setTslImpl(ctip);
		String uriTslLocation = TOKEN_UNKNOWN;
		if (!UtilsStringChar.isNullOrEmptyTrim(urlTsl)) {
			uriTslLocation = urlTsl;
		} else if (tslObject.getSchemeInformation().isThereSomeDistributionPoint()) {
			for (int i = 0; i < tslObject.getSchemeInformation().getDistributionPoints().size(); i++) {
				if (!tslObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".pdf") && !tslObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".PDF")) {
					uriTslLocation = tslObject.getSchemeInformation().getDistributionPoints().get(i).toString();
					break;
				}
			}

		}
		td.setUriTslLocation(uriTslLocation);
		td.setXmlDocument(tslXMLbytes);
		td.setIssueDate(tslObject.getSchemeInformation().getListIssueDateTime());
		td.setExpirationDate(tslObject.getSchemeInformation().getNextUpdate());
		td.setSequenceNumber(tslObject.getSchemeInformation().getTslSequenceNumber());
		td.setNewTSLAvailable(FindNewTslRevisionsTaskConstants.NO_TSL_AVAILABLE);

		// Lo añadimos en base de datos.
		td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().saveTSL(td);

		return td;

	}

	/**
	 * Update the distribution point of a TSL Data in the data base an the cache.
	 * @param tslDataId TSL Data identifier to update.
	 * @param newDP New distribution point to assign to the TSL Data.
	 * @throws TSLManagingException In case of some error updating the distribution point of a TSL.
	 */
	public void updateDistributionPointTSLData(Long tslDataId, String newDP) throws TSLManagingException {

		try {

			// Obtenemos el TSLData de la BD.
			TslData td = null;
			try {
				td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslDataById(tslDataId, false, false);
			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL190, new Object[ ] { tslDataId, newDP }), e);
			}

			// Si se encuentra en la BD se actualiza...
			if (td != null) {
				// Recuperamos la TSL Data de la caché.
				TSLDataCacheObject tdco = ConfigurationCacheFacade.tslGetTSLDataCacheObject(tslDataId);

				// Lo actualizamos en base de datos.
				td.setUriTslLocation(newDP);
				td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().saveTSL(td);

				// Lo actualizamos en la caché comaprtida.
				ConfigurationCacheFacade.tslAddUpdateTSLData(td, tdco.getTslObject());
			}
		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL190, new Object[ ] { tslDataId, newDP }), e);
		}
	}

	/**
	 * Update the new TSL avaliable field of a TSL Data in the data base and the cache.
	 * @param tslDataId TSL Data identifier to update.
	 * @param newTSLAvailable New value for field new TSL avaliable to assign to the TSL Data.
	 * @throws TSLManagingException In case of some error updating the distribution point of a TSL.
	 */
	public void updateNewAvaliableTSLData(Long tslDataId, String newTSLAvailable) throws TSLManagingException {

		try {

			// Obtenemos el TSLData de la BD.
			TslData td = null;
			try {
				td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslDataById(tslDataId, false, false);
			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL191, new Object[ ] { tslDataId, newTSLAvailable }), e);
			}

			// Si se encuentra en la BD...
			if (td != null) {
				// Recuperamos la TSL Data de la caché.
				TSLDataCacheObject tdco = ConfigurationCacheFacade.tslGetTSLDataCacheObject(tslDataId);

				// Lo actualizamos en base de datos.
				td.setNewTSLAvailable(newTSLAvailable);
				td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().saveTSL(td);

				// Lo actualizamos en la caché comaprtida.
				ConfigurationCacheFacade.tslAddUpdateTSLData(td, tdco.getTslObject());
			}

		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL191, new Object[ ] { tslDataId, newTSLAvailable }), e);
		}
	}

	/**
	 * Update the last new TSL avaliable find field of a TSL Data in the data base an the cache.
	 * @param tslDataId TSL Data identifier to update.
	 * @param date New value for field last new TSL avaliable find to assign to the TSL Data.
	 * @throws TSLManagingException In case of some error updating the distribution point of a TSL.
	 */
	public void updateLastNewAvaliableTSLFindData(long tslDataId, Date date) throws TSLManagingException {

		try {

			// Obtenemos el TSLData de la BD.
			TslData td = null;
			try {
				td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslDataById(tslDataId, false, false);
			} catch (Exception e) {
				throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL192, new Object[ ] { tslDataId, UtilsDate.toString(UtilsDate.FORMAT_DATE, date) }), e);
			}

			// Si se encuentra en la BD se actualiza
			if (td != null) {
				// Recuperamos la TSL Data de la caché.
				TSLDataCacheObject tdco = ConfigurationCacheFacade.tslGetTSLDataCacheObject(tslDataId);

				// Lo actualizamos en base de datos.
				td.setLastNewTSLAvailableFind(date);
				td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().saveTSL(td);

				// Lo actualizamos en la caché comaprtida.
				ConfigurationCacheFacade.tslAddUpdateTSLData(td, tdco.getTslObject());
			}
		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL192, new Object[ ] { tslDataId, UtilsDate.toString(UtilsDate.FORMAT_DATE, date) }), e);
		}

	}

	/**
	 * Update the legible document of the input TSL.
	 * @param tslDataId TSL ID which identifies the TSL to update.
	 * @param legibleDocumentArrayBytes Array of bytes that represents the legible document. It can be <code>null</code>.
	 * @throws TSLManagingException In case of some error updating the TSL Legible Document in the data base and the cache.
	 */
	public void updateTSLDataLegibleDocument(long tslDataId, byte[ ] legibleDocumentArrayBytes) throws TSLManagingException {

		try {

			// Recuperamos de la caché compartida el TSLData a actualizar, y de
			// esta el objeto serializable que representa a la TSL.
			TSLDataCacheObject tdco = getTSLDataCacheObject(tslDataId);
			ITSLObject tslObject = (ITSLObject) tdco.getTslObject();

			// Cargamos el pojo de base de datos.
			TslData td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslDataById(tslDataId, false, false);
			// Asignamos y actualizamos el documento legible.
			td.setLegibleDocument(legibleDocumentArrayBytes);
			td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().saveTSL(td);

			// Lo actualizamos en la caché con el nuevo pojo.
			ConfigurationCacheFacade.tslAddUpdateTSLData(td, tslObject);

		} catch (Exception e) {

			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL177, new Object[ ] { tslDataId }), e);

		}

	}

	/**
	 * Removes the specified TSL Data from the data base and the cache.
	 * @param countryRegionCode Country/Region code representation. If this is <code>null</code>, tries to get
	 * from the TSLData.
	 * @param tslDataId TSL Data identifier to remove.
	 * @throws TSLManagingException In case of some error removing a TSL from the data base and cache.
	 */
	public void removeTSLData(String countryRegionCode, Long tslDataId) throws TSLManagingException {

		String crc = null;
		Long idCountryRegion = null;
		try {
			ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();
			// Calculamos el valor del Country/Region code.
			if (UtilsStringChar.isNullOrEmptyTrim(countryRegionCode)) {
				TSLDataCacheObject tsldco = getTSLDataCacheObject(tslDataId);
				if (tsldco != null) {
					ITSLObject tslObject = (ITSLObject) tsldco.getTslObject();
					crc = tslObject.getSchemeInformation().getSchemeTerritory();
				} else {
					// se obtiene el valor country/region code desde base de
					// datos
					TslData tslData = tslDataService.getTslDataById(tslDataId, false, false);
					crc = tslData.getTslCountryRegion().getCountryRegionCode();

				}
			} else {
				crc = countryRegionCode;

			}

			// Si hemos obtenido el Country/Region code, continuamos...
			if (crc != null) {
				ITslCountryRegionService countryRegionService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService();
				if (countryRegionService != null) {
					TslCountryRegion countryRegion = countryRegionService.getTslCountryRegionByCode(crc, false);
					if (countryRegion != null) {
						idCountryRegion = countryRegion.getIdTslCountryRegion();
					}
				}
				
				// Parseamos la TSL.
				ITSLObject tslObjectParser = buildAndCheckTSL(tslDataService.getTslDataById(tslDataId, false, false));
				
				// Lo eliminamos de base de datos.
				ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().deleteTslData(tslDataId);

				// Lo eliminamos de la caché compartida.
				ConfigurationCacheFacade.tslRemoveTSLDataFromCountryRegion(crc);

				// Lo eliminamos el mapeo de base de datos.
				ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService().deleteTslCountryRegionMappingByCountry(idCountryRegion);

				// Lo eliminamos el mapeo la caché compartida.
				ConfigurationCacheFacade.tslRemoveMappingFromCountryRegion(crc, null);
				
				
				// Se elimina el país de la caché compartida
				ConfigurationCacheFacade.tslRemoveTSLCountryRegion(crc);
				
				//se elimina de base de datos
				ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService().deleteTslCountryRegionById(idCountryRegion);
				
				// Eliminamos los acceso externos.
				ApplicationContextProvider.getApplicationContext().getBean(ExternalAccessService.class).new ExternalAccessServiceThread(ExternalAccessService.OPERATION3, tslObjectParser).start();
			}

		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL173, new Object[ ] { tslDataId, crc }), e);
		}

	}

	// /**
	// * Gets the CAs certificates that are with a good status from the input
	// TSL.
	// * @param tslObject Object from which extracts the certificates.
	// * @param caFromPolicy Map with the relation <CAIdentificator, X509v3
	// Certificate> from the certification
	// * policy to compare with the TSL.
	// * @return Map with the CAs certificates that are with a good status from
	// the
	// * input TSL and its names.
	// * @throws TSLManagingException In case of some error making the
	// comparation.
	// */
	// public TSLObjectX509DigIdentities
	// getTSLDigitalIdentitiesComparationWithCAs(ITSLObject tslObject,
	// Map<String, X509Certificate> caFromPolicy) throws TSLManagingException {
	//
	// return new TSLObjectX509DigIdentities(tslObject, caFromPolicy);
	//
	// }

	/**
	 * Checks if the input date is equal or is after the initial date from which can be
	 * used the TSL for detect and validate certificates.
	 * @param dateToCheck Date to check.
	 * @return <code>true</code> if the input date is equal or is after the initial date from which can be
	 * used the TSL for detect and validate certificates. Otherwise <code>false</code>.
	 */
	public boolean isEqualOrAfterInitialDate(Date dateToCheck) {

		// Inicialmente indicamos que no.
		boolean result = false;

		// Solo se compara si la fecha de entrada no es nula.
		if (dateToCheck != null) {
			Date initialDate = TSLProperties.getInitialDate();
			result = initialDate.equals(dateToCheck) || initialDate.before(dateToCheck);

			if (!result) {
				LOGGER.warn(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL225, new Object[ ] { dateToCheck, initialDate }));
			}

		}

		// Devolvemos el resultado.
		return result;

	}

	/**
	 * Method to gets Contry/Region  associated with the TSL.
	 * @param idTslData TSL data identifier to obtain the region / country to which it belongs.
	 * @return Contry/Region  obtained.
	 * @throws TSLManagingException In case of some error obtaining the name of the country/region.
	 * */
	public TSLCountryRegionCacheObject getTSLCountryRegionByIdTslData(Long idTslData) throws TSLManagingException {
		TSLCountryRegionCacheObject tslcrco = null;
		String crc = null;

		TSLDataCacheObject tsldco = getTSLDataCacheObject(idTslData);
		if (tsldco != null) {
			ITSLObject tslObject = (ITSLObject) tsldco.getTslObject();
			crc = tslObject.getSchemeInformation().getSchemeTerritory();
			tslcrco = getTSLCountryRegionCacheObject(crc);

		}
		return tslcrco;
	}

	/**
	 * Update the TSLData in the database and in the cache.
	 * @param tslDataId TSL ID which identifies the TSL to update.
	 * @param tslXMLbytes Array of bytes that defines the TSL in a XML.
	 * @param urlTsl URL location for the TSL.
	 * @param legibleDocumentArrayByte Array of bytes that represents the legible document. It can be <code>null</code>.
	 * @return Updated TslData.
	 * @throws TSLManagingException In case of some error updating the TSL Legible Document in the data base and the cache.
	 */
	public TslData updateTSLData(long tslDataId, byte[ ] tslXMLbytes, String urlTsl, byte[ ] legibleDocumentArrayByte) throws TSLManagingException {
		TslData result = null;
		try {

			// Recuperamos de la caché compartida el TSLData a actualizar, y de
			// esta el objeto serializable que representa a la TSL.
			TSLDataCacheObject tdco = getTSLDataCacheObject(tslDataId);

			ITSLObject tslObject = (ITSLObject) tdco.getTslObject();

			// Cargamos el pojo de base de datos.
			TslData td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslDataById(tslDataId, false, false);

			// se obtiene la información de la nueva TSL

			// parsearlo y añadirlo.
			if (tslXMLbytes != null) {
				ByteArrayInputStream bais = new ByteArrayInputStream(tslXMLbytes);

				try {

					tslObject.buildTSLFromXMLcheckValues(bais);

				} catch (TSLMalformedException e) {
					if (e.getErrorCode() != null && e.getErrorCode().equals(ValetExceptionConstants.COD_204)) {
						throw new TSLManagingException(ValetExceptionConstants.COD_204, e.getMessage(), e);
					} else {
						throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL170), e);
					}

				} catch (Exception e) {
					throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL170), e);
				} finally {
					UtilsResources.safeCloseInputStream(bais);
				}

				// se almacena el número de secuencia para comprobar si la nueva
				// es más actual.
				// se comprueba si hay que indicar que hay versión disponible o
				// no
				int sequenceOld = td.getSequenceNumber();
				int sequenceNew = tslObject.getSchemeInformation().getTslSequenceNumber();
				if (sequenceNew >= sequenceOld) {
					// No Existe una nueva versión de la TSL.
					td.setNewTSLAvailable(FindNewTslRevisionsTaskConstants.NO_TSL_AVAILABLE);
					td.setLastNewTSLAvailableFind(null);
				} else {
					// No Existe una nueva versión de la TSL.
					td.setNewTSLAvailable(FindNewTslRevisionsTaskConstants.NEW_TSL_AVAILABLE);
					td.setLastNewTSLAvailableFind(Calendar.getInstance().getTime());
				}

				// se actualiza el número de secuencia
				td.setSequenceNumber(sequenceNew);

				// se actualiza el responsable
				String responsible = TOKEN_UNKNOWN;
				// Recuperamos el nombre del operador del esquema en inglés.
				List<String> sonList = tslObject.getSchemeInformation().getSchemeOperatorNameInLanguage(Locale.UK.getLanguage());
				if (sonList != null && !sonList.isEmpty()) {
					responsible = sonList.get(0);
				}
				// Si no lo hemos recuperado, tomamos el primero que haya.
				if (UtilsStringChar.isNullOrEmptyTrim(responsible)) {
					responsible = tslObject.getSchemeInformation().getSchemeOperatorNames().values().iterator().next().get(0);
				}
				td.setResponsible(responsible);

				// fecha de emisión
				td.setIssueDate(tslObject.getSchemeInformation().getListIssueDateTime());

				// fecha de caducidad
				td.setExpirationDate(tslObject.getSchemeInformation().getNextUpdate());

				// Asignamos y actualizamos el fichero con la nueva TSL.
				td.setXmlDocument(tslXMLbytes);

				// Se actualiza la TSL en la base de datos.
				td = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().saveTSL(td);

				// Lo actualizamos en la caché con el nuevo pojo.
				ConfigurationCacheFacade.tslAddUpdateTSLData(td, tslObject);

			}
			// Punto de distribución
			if (!UtilsStringChar.isNullOrEmpty(urlTsl)) {
				updateDistributionPointTSLData(tslDataId, urlTsl);
			}

			// se actualiza documento legible
			if (legibleDocumentArrayByte != null) {
				updateTSLDataLegibleDocument(tslDataId, legibleDocumentArrayByte);
			}
			result = td;
			// se actualiza la información en los datos del arbol de mapeos de
			// TSLs.
			updateMapTslMappingTree(td.getTslCountryRegion().getCountryRegionCode(), td.getSequenceNumber().toString(), tslObject);
			
			// Se actualizan los accessos externos con las url de los distintos distribution point que contenga la tsl.
			ApplicationContextProvider.getApplicationContext().getBean(ExternalAccessService.class).new ExternalAccessServiceThread(ExternalAccessService.OPERATION2, tslObject).start();
		} catch (

		Exception e) {

			throw new TSLManagingException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL259, new Object[ ] { tslDataId }), e);

		}
		return result;

	}

	/**
	 * Gets the set of URL (String format) that represents the official
	 * european list of trusted lists.
	 * @return set of URL (String format) that represents the official
	 * european list of trusted lists.
	 */
	public Set<String> getSetOfURLStringThatRepresentsEuLOTL() {

		// Si aún no se ha inicializado...
		if (setOfURLStringThatRepresentsEuLOTL.isEmpty()) {

			// Como mínimo añadimos las dos URL conocidas a fecha de 20/08/2019:
			// -
			// https://ec.europa.eu/information_society/policy/esignature/trusted-list/tl-mp.xml
			setOfURLStringThatRepresentsEuLOTL.add(TSLCommonURIs.TSL_EU_LIST_OF_THE_LISTS_1);
			setOfURLStringThatRepresentsEuLOTLinString = TSLCommonURIs.TSL_EU_LIST_OF_THE_LISTS_1;
			// - https://ec.europa.eu/tools/lotl/eu-lotl.xml
			setOfURLStringThatRepresentsEuLOTL.add(TSLCommonURIs.TSL_EU_LIST_OF_THE_LISTS_2);
			setOfURLStringThatRepresentsEuLOTLinString += UtilsStringChar.SYMBOL_COMMA_STRING;
			setOfURLStringThatRepresentsEuLOTLinString += UtilsStringChar.SPECIAL_BLANK_SPACE_STRING;
			setOfURLStringThatRepresentsEuLOTLinString += TSLCommonURIs.TSL_EU_LIST_OF_THE_LISTS_2;

			// Ahora recolectamos las establecidas en la configuración estática,
			// y añadimos aquellas que
			// no estén ya.
			Properties props = StaticValetConfig.getProperties(StaticValetConfig.TSL_EU_LOTL_PREFIX);
			if (props != null && !props.isEmpty()) {
				Collection<Object> urlStringColl = props.values();
				for (Object urlStringObject: urlStringColl) {
					if (urlStringObject != null) {
						String urlString = ((String) urlStringObject).trim();
						if (!UtilsStringChar.isNullOrEmpty(urlString) && !setOfURLStringThatRepresentsEuLOTL.contains(urlString)) {
							setOfURLStringThatRepresentsEuLOTL.add(urlString);
							setOfURLStringThatRepresentsEuLOTLinString += UtilsStringChar.SYMBOL_COMMA_STRING;
							setOfURLStringThatRepresentsEuLOTLinString += UtilsStringChar.SPECIAL_BLANK_SPACE_STRING;
							setOfURLStringThatRepresentsEuLOTLinString += urlString;
						}
					}
				}
			}

		}

		// Devolvemos el conjunto de URL que reconocen la lista de las TSL
		// europeas...
		return setOfURLStringThatRepresentsEuLOTL;

	}

	/**
	 * Gets the set of URL (String format) that represents the official
	 * european list of trusted lists splitted by commas.
	 * @return set of URL (String format) that represents the official
	 * european list of trusted lists splitted by commas.
	 */
	public String getSetOfURLStringThatRepresentsEuLOTLinString() {

		return setOfURLStringThatRepresentsEuLOTLinString;

	}

	/**
	 * Method that obtains information about the version of each enabled TSL registered in valET.
	 * @return {@link Map} with all countries/regions codes and versions of eeach enabled TSL.
	 * <code>null</code> id there is not.
	 * @throws TSLManagingException In case of some error getting all information.
	 */
	public Map<String, Integer> getTslInfoVersions() throws TSLManagingException {

		Map<String, Integer> result = new HashMap<String, Integer>();

		// se obtiene de bbdd la relacion código pais/region - version de las
		// TSLs habilitadas.
		List<TslCountryVersionDTO> tslCountryVersionList = new ArrayList<TslCountryVersionDTO>();
		try {
			tslCountryVersionList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService().getTslCountryVersionAvailable();
		} catch (Exception e) {
			throw new TSLManagingException(ValetExceptionConstants.COD_203, Language.getResCoreTsl(CoreTslMessages.LOGMTSL267), e);
		}

		// Si el listado no es nulo ni vacío,
		// extraemos el código de cada país/region y la version
		if (tslCountryVersionList != null && !tslCountryVersionList.isEmpty()) {
			result = new HashMap<String, Integer>();
			for (TslCountryVersionDTO tslcv: tslCountryVersionList) {
				result.put(tslcv.getCountryRegionCode(), tslcv.getSequenceNumber());
			}

		}

		return result;

	}

	/**
	 * Method that obtains the list of certificates that appear in the TSL.
	 * @param tslObject TSL object representation to use.
	 * @return List of certificates.
	 */
	public List<X509Certificate> getListCertificatesTSL(ITSLObject tslObject) {

		List<X509Certificate> result = new ArrayList<X509Certificate>();
		// Recuperamos la lista de TSP y vamos analizando uno a uno.
		List<TrustServiceProvider> tspList = tslObject.getTrustServiceProviderList();
		// Si la lista no es nula ni vacía...
		if (tspList != null && !tspList.isEmpty()) {

			// La vamos recorriendo mientras no se termine y no se haya
			// modificado el resultado de la validación del certificado.
			for (int index = 0; index < tspList.size(); index++) {

				// Almacenamos en una variable el TSP a tratar.
				TrustServiceProvider tsp = tspList.get(index);

				List<TSPService> tspServiceList = tsp.getAllTSPServices();
				// Si la lista no es nula ni vacía...
				if (tspServiceList != null && !tspServiceList.isEmpty()) {
					for (int indexTspService = 0; indexTspService < tspServiceList.size(); indexTspService++) {
						TSPService tspService = tspServiceList.get(indexTspService);
						if (tspService != null) {
							ServiceHistoryInstance shi = tspService.getServiceInformation();

							DigitalIdentitiesProcessor dipAux = new DigitalIdentitiesProcessor(shi.getAllDigitalIdentities());
							if (dipAux.getX509certList() != null && !dipAux.getX509certList().isEmpty()) {
								result.addAll(dipAux.getX509certList());
							}
							if (tspService.isThereSomeServiceHistory()) {
								List<ServiceHistoryInstance> shiList = tspService.getAllServiceHistory();
								for (ServiceHistoryInstance shiTmp: shiList) {
									DigitalIdentitiesProcessor dipAuxHist = new DigitalIdentitiesProcessor(shiTmp.getAllDigitalIdentities());
									if (dipAuxHist.getX509certList() != null && !dipAuxHist.getX509certList().isEmpty()) {
										result.addAll(dipAuxHist.getX509certList());
									}
								}

							}

						}

					}

				}
			}

		}
		return result;

	}

	/**
	 * Gets the value of the attribute {@link #mapCertificateTSL}.
	 * @return the value of the attribute {@link #mapCertificateTSL}.
	 */
	public List<X509Certificate> getListCertificateTSL(String codeCountry) {
		List<X509Certificate> result = new ArrayList<X509Certificate>();
		if (codeCountry != null) {

			result = mapCertificateTSL.get(codeCountry);
		}
		return result;
	}
}
