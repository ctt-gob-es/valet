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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.TSLValidatorMappingCalculator.java.</p>
 * <b>Description:</b><p>This class offers static methods to extract mappings of a certificate
 * validated through a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.3, 27/09/2021.
 */
package es.gob.valet.tsl.certValidation.impl;

import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.bouncycastle.asn1.x509.qualified.ETSIQCObjectIdentifiers;

import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreTslMessages;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionMappingCacheObject;
import es.gob.valet.persistence.configuration.model.utils.IAssociationTypeIdConstants;
import es.gob.valet.rest.services.ITslMappingConstants;
import es.gob.valet.tsl.access.TSLProperties;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorOtherConstants;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.certValidation.impl.common.TSLCertificateExtensionAnalyzer;
import es.gob.valet.tsl.certValidation.impl.common.WrapperX509Cert;
import es.gob.valet.tsl.exceptions.TSLValidationException;
import es.gob.valet.tsl.parsing.ifaces.ITSLOIDs;

/**
 * <p>This class offers static methods to extract mappings of a certificate
 * validated through a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 27/09/2021.
 */
public final class TSLValidatorMappingCalculator {

    /**
     * Attribute that represents the object that manages the log of the class.
     */
    private static final Logger LOGGER = Logger.getLogger(TSLValidatorMappingCalculator.class);
	/**
	 * Constant attribute that represents the set of mapping names that are static.
	 */
	public static final Set<String> STATIC_MAPPING_NAMES_SET = new HashSet<String>(Arrays.asList(ITslMappingConstants.MAPPING_KEY_CERT_QUALIFIED, ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, ITslMappingConstants.MAPPING_KEY_QSCD));

	/**
	 * Constructor method for the class TSLValidatorMappingCalculator.java.
	 */
	private TSLValidatorMappingCalculator() {
		super();
	}

	/**
	 * Checks if the input mapping name matches with some of the static mapping names.
	 * @param mappingName Mapping name to check.
	 * @return <code>true</code> if the input mapping name matches with some of the static mapping names,
	 * otherwise <code>false</code>.
	 */
	public static boolean checksIfMappingNameMatchesWithSomeStaticMappingName(String mappingName) {
		return STATIC_MAPPING_NAMES_SET.contains(mappingName);
	}

	/**
	 * Extracts the static mappings from the validation result, and add these on the input mapping set.
	 * @param tslCertExtAnalyzer TSL Certificate Extension Analyzer needed to resolve the mappings of the certificate.
	 * @param mappings Map in which adds the pairs <MappingName, MappingValue> calculated for the validated certificate.
	 * @param tslValidationResult TSL validation result from which get the static mapping information.
	 */
	public static void extractStaticMappingsFromResult(TSLCertificateExtensionAnalyzer tslCertExtAnalyzer, Map<String, String> mappings, ITSLValidatorResult tslValidationResult) {
		 LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL320));
		// Si ninguno de los parámetros de entrada es nulo...
		if (mappings != null && tslValidationResult != null) {

			// Establecemos primero si el certificado es qualified o no.
			switch (tslValidationResult.getMappingType()) {

				case ITSLValidatorResult.MAPPING_TYPE_UNKNOWN:
					try {
						 LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL329));
						mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_QUALIFIED, getMappingTypeQualifiedFromCertificate(tslCertExtAnalyzer));
					} catch (TSLValidationException e) {
						LOGGER.error(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL325));
						mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_QUALIFIED, ITslMappingConstants.MAPPING_VALUE_UNKNOWN);
					}
					break;

				case ITSLValidatorResult.MAPPING_TYPE_NONQUALIFIED:
					 LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL330));
					mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_QUALIFIED, ITslMappingConstants.MAPPING_VALUE_NO);
					break;

				case ITSLValidatorResult.MAPPING_TYPE_QUALIFIED:
					 LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL331));
					mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_QUALIFIED, ITslMappingConstants.MAPPING_VALUE_YES);
					break;

				default:
					break;
			}

			// Establecemos la clasificación del certificado.
			switch (tslValidationResult.getMappingClassification()) {
				case ITSLValidatorResult.MAPPING_CLASSIFICATION_OTHER_UNKNOWN:
					try {
						 LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL328));
						mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, getMappingClassificationFromCertificate(tslCertExtAnalyzer, true));
					} catch (TSLValidationException e) {
						mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, ITslMappingConstants.MAPPING_VALUE_UNKNOWN);
					}
					break;

				case ITSLValidatorResult.MAPPING_CLASSIFICATION_NATURAL_PERSON:
					 LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL332));
					mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_NATURAL_PERSON);
					break;

				case ITSLValidatorResult.MAPPING_CLASSIFICATION_LEGALPERSON:
					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL333));
					mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_LEGALPERSON);
					break;

				case ITSLValidatorResult.MAPPING_CLASSIFICATION_ESEAL:
					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL334));
					mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_ESEAL);
					break;

				case ITSLValidatorResult.MAPPING_CLASSIFICATION_ESIG:
					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL335));
					mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_ESIG);
					break;

				case ITSLValidatorResult.MAPPING_CLASSIFICATION_WSA:
					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL336));
					mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_WSA);
					break;

				case ITSLValidatorResult.MAPPING_CLASSIFICATION_TSA:
					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL337));
					mappings.put(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_TSA);
					break;

				default:
					break;
			}

			// Establecemos si el certificado es de un QSCD.
			switch (tslValidationResult.getMappingQSCD()) {

				case ITSLValidatorResult.MAPPING_QSCD_UNKNOWN:
					try {
						LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL326));
						mappings.put(ITslMappingConstants.MAPPING_KEY_QSCD, getMappingQSCDFromCertificate(tslCertExtAnalyzer));
					} catch (TSLValidationException e) {
						mappings.put(ITslMappingConstants.MAPPING_KEY_QSCD, ITslMappingConstants.MAPPING_VALUE_UNKNOWN);
					}
					break;

				case ITSLValidatorResult.MAPPING_QSCD_NO:
					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL338));
					mappings.put(ITslMappingConstants.MAPPING_KEY_QSCD, ITslMappingConstants.MAPPING_VALUE_NO);
					break;

				case ITSLValidatorResult.MAPPING_QSCD_YES:
					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL339));
					mappings.put(ITslMappingConstants.MAPPING_KEY_QSCD, ITslMappingConstants.MAPPING_VALUE_YES);
					break;

				case ITSLValidatorResult.MAPPING_QSCD_ASINCERT:
					try {
						LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL340));
						mappings.put(ITslMappingConstants.MAPPING_KEY_QSCD, getMappingQSCDFromCertificate(tslCertExtAnalyzer));
					} catch (TSLValidationException e) {
						LOGGER.error(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL341));
						mappings.put(ITslMappingConstants.MAPPING_KEY_QSCD, ITslMappingConstants.MAPPING_VALUE_UNKNOWN);
					}
					break;

				case ITSLValidatorResult.MAPPING_QSCD_YES_MANAGEDONBEHALF:
					LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL342));
					mappings.put(ITslMappingConstants.MAPPING_KEY_QSCD, ITslMappingConstants.MAPPING_VALUE_QSCD_YES_MANAGEDONBEHALF);
					break;

				default:
					break;
			}

		}

	}

	/**
	 * Tries to extract from the certificate if it is qualified or not.
	 * @param tslCertExtAnalyzer TSL Certificate Extension Analyzer needed to resolve the mappings of the certificate.
	 * @return String that represents the mapping type of the input certificate. It only can be one of the following:<br>
	 * <ul>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_YES}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_NO}</li>
	 * </ul>
	 * @throws TSLValidationException In case of some error parsing the input certificate with IAIK provider.
	 */
	public static String getMappingTypeQualifiedFromCertificate(TSLCertificateExtensionAnalyzer tslCertExtAnalyzer) throws TSLValidationException {

		// Por defecto el valor es desconocido.
		String result = ITslMappingConstants.MAPPING_VALUE_UNKNOWN;
		LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL322));
		// Si dispone de la extensión QcStatement y al menos uno es de los
		// cualificados, o en su defecto hay una extensión Certificate Policies
		// Policy Information que determina que es cualificado...
		if (tslCertExtAnalyzer.hasSomeQcStatementExtensionOid(ITSLValidatorOtherConstants.QCSTATEMENTS_OIDS_FOR_QUALIFIED_CERTS_LIST) || tslCertExtAnalyzer.hasSomeCertPolPolInfExtensionOid(ITSLValidatorOtherConstants.POLICYIDENTIFIERS_OIDS_FOR_QUALIFIED_CERTS_LIST)) {

			result = ITslMappingConstants.MAPPING_VALUE_YES;
			LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL323));

		} else {

			result = ITslMappingConstants.MAPPING_VALUE_NO;
			LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL324));

		}

		return result;

	}

	/**
	 * Tries to extract from the certificate its classification type.
	 * @param tslCertExtAnalyzer TSL Certificate Extension Analyzer needed to resolve the mappings of the certificate.
	 * @param translateMapping Flag that if it is <code>true</code>, then the returned mapping values
	 * {@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_NATURAL_PERSON} and {@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_LEGALPERSON}
	 * are translated to {@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_ESEAL} and
	 * {@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_ESIG} respectively.
	 * @return String that represents the mapping classification of the input certificate. It only can be one of the following:<br>
	 * <ul>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_UNKNOWN}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_NATURAL_PERSON}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_LEGALPERSON}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_ESEAL}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_ESIG}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_WSA}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_TSA}</li>
	 * </ul>
	 * @throws TSLValidationException In case of some error parsing the input certificate with IAIK provider.
	 */
	public static String getMappingClassificationFromCertificate(TSLCertificateExtensionAnalyzer tslCertExtAnalyzer, boolean translateMapping) throws TSLValidationException {

		// Por defecto el valor es desconocido.
		String result = ITslMappingConstants.MAPPING_VALUE_UNKNOWN;

		// Comprobamos si se dispone del QCStatements Extension - EuType
		// 1.3.6.1.5.5.7.1.3, la cual es opcional.
		if (tslCertExtAnalyzer.isThereSomeQcStatementEuTypeExtension()) {

			// Comprobamos los OID de ESIG, ESEAL y WSA.
			if (tslCertExtAnalyzer.hasQcStatementEuTypeExtensionOid(ITSLOIDs.OID_QCSTATEMENT_EXT_EUTYPE_ESIGN.getId())) {

				result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_ESIG;

			} else if (tslCertExtAnalyzer.hasQcStatementEuTypeExtensionOid(ITSLOIDs.OID_QCSTATEMENT_EXT_EUTYPE_ESEAL.getId())) {

				result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_ESEAL;

			} else if (tslCertExtAnalyzer.hasQcStatementEuTypeExtensionOid(ITSLOIDs.OID_QCSTATEMENT_EXT_EUTYPE_WEB.getId())) {

				result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_WSA;

			}

		}

		// IMPORTANTE, si al menos tiene el QcCompliance, ya se considera de
		// firma,
		// por lo que si aún no lo hemos determinado, lo comprobamos.
		if (result.equals(ITslMappingConstants.MAPPING_VALUE_UNKNOWN) && tslCertExtAnalyzer.isThereSomeQcStatementExtension()) {
			result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_ESIG;
		}

		// Si aún no hemos podido verificar que el certificado sea detectado,
		// comprobamos los CertificatePolicies.
		if (result.equals(ITslMappingConstants.MAPPING_VALUE_UNKNOWN) && tslCertExtAnalyzer.isThereSomeCertPolPolInfExtension()) {

			if (tslCertExtAnalyzer.hasSomeCertPolPolInfExtensionOid(ITSLValidatorOtherConstants.POLICYIDENTIFIERS_OIDS_FOR_ESIG_CERTS_LIST)) {
				result = translateMapping ? ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_ESIG : ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_NATURAL_PERSON;
			} else if (tslCertExtAnalyzer.hasSomeCertPolPolInfExtensionOid(ITSLValidatorOtherConstants.POLICYIDENTIFIERS_OIDS_FOR_ESEAL_CERTS_LIST)) {
				result = translateMapping ? ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_ESEAL : ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_LEGALPERSON;
			} else if (tslCertExtAnalyzer.hasSomeCertPolPolInfExtensionOid(ITSLValidatorOtherConstants.POLICYIDENTIFIERS_OIDS_FOR_WSA_CERTS_LIST)) {
				result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_WSA;
			}

		}

		return result;

	}

	/**
	 * Tries to extract from the certificate if it has its private key in a QSCD.
	 * @param tslCertExtAnalyzer TSL Certificate Extension Analyzer needed to resolve the mappings of the certificate.
	 * @return String that represents the mapping for the input certificate. It only can be one of the following:<br>
	 * <ul>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_UNKNOWN}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_YES}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_NO}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_ASINCERT}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_QSCD_YES_MANAGEDONBEHALF}</li>
	 * </ul>
	 * @throws TSLValidationException In case of some error parsing the input certificate with IAIK provider.
	 */
	public static String getMappingQSCDFromCertificate(TSLCertificateExtensionAnalyzer tslCertExtAnalyzer) throws TSLValidationException {

		// Inicializamos el resultado a desconocido.
		String result = ITslMappingConstants.MAPPING_VALUE_UNKNOWN;

		// Comprobamos si tiene la extensión QcStatement - QcEuSSCD
		if (tslCertExtAnalyzer.hasQcStatementExtensionOid(ETSIQCObjectIdentifiers.id_etsi_qcs_QcSSCD.getId())) {

			result = ITslMappingConstants.MAPPING_VALUE_YES;

		}

		// Comprobamos los CertificatePolicies - Policy Information.
		if (result.equals(ITslMappingConstants.MAPPING_VALUE_UNKNOWN) && tslCertExtAnalyzer.isThereSomeCertPolPolInfExtension()) {

			if (tslCertExtAnalyzer.hasSomeCertPolPolInfExtensionOid(ITSLValidatorOtherConstants.POLICYIDENTIFIERS_OIDS_FOR_CERTS_IN_QSCD_LIST)) {
				result = ITslMappingConstants.MAPPING_VALUE_YES;
			} else if (tslCertExtAnalyzer.hasSomeCertPolPolInfExtensionOid(ITSLValidatorOtherConstants.POLICYIDENTIFIERS_OIDS_FOR_CERTS_IN_NO_QSCD_LIST)) {
				result = ITslMappingConstants.MAPPING_VALUE_NO;
			}

		}

		// Se devuelve el resultado.
		return result;

	}

	/**
	 * Extracts the static mappings from the validation result, and add these on the input mapping set.
	 * If there is some error calculating any mapping, this mapping is not returned in the map.
	 * @param cert X509v3 certificate from which extract the mapping information.
	 * @param mappings Map in which adds the pairs <MappingName, MappingValue> calculated for the validated certificate.
	 * @param tslCrmcoSet Set of mappings to calculate with the input certificate.
	 * @throws TSLValidationException In case of some error generating the wrapper of the input certificate.
	 */
	public static void extractMappingsFromCertificate(X509Certificate cert, Map<String, String> mappings, Set<TSLCountryRegionMappingCacheObject> tslCrmcoSet) throws TSLValidationException {

		// Si el certificado no es nulo, y el conjunto de mapeos a extraer
		// no es nulo ni vacío, contianuamos.
		if (cert != null && mappings != null && tslCrmcoSet != null && !tslCrmcoSet.isEmpty()) {

			try {
				// Creamos un envoltorio para trabajar con el certificado.
				WrapperX509Cert wrappedCert = new WrapperX509Cert(cert);

				// Por cada uno de los mapeos a extraer...
				for (TSLCountryRegionMappingCacheObject tslCrmco: tslCrmcoSet) {

					// Calculamos el valor extraído del certificado.
					String mappingValue = calculateMapping(wrappedCert, tslCrmco);
					// Si el valor extraido no es nulo ni vacío...
					if (!UtilsStringChar.isNullOrEmptyTrim(mappingValue)) {
						// Lo añadimos a la lista de mapeos resultantes.
						mappings.put(tslCrmco.getIdentificator(), mappingValue);
					}

				}

			} catch (Exception e) {

				// Se produjo un error al tratar de crear el Wrapper del
				// certificado,
				// por lo que no se podrán extraer los mapeos.
				throw new TSLValidationException(IValetException.COD_187, Language.getResCoreTsl(ICoreTslMessages.LOGMTSL151), e);

			}

		}

	}

	/**
	 * Calculates the input mapping representation with the input certificate data information.
	 * If there is some error calculating the mapping, then returns <code>null</code>.
	 * @param wrappedCert X509v3 certificate from which extract the mapping information.
	 * @param tslCrmco mapping cache object with the information to extract from the certificate.
	 * @return String value with the mapping value calculated. <code>null</code> if there is some error
	 * or not was possible to extract the specified information.
	 */
	private static String calculateMapping(WrapperX509Cert wrappedCert, TSLCountryRegionMappingCacheObject tslCrmco) {

		String result = null;

		// Distinguimos según el tipo de asociación del mapeo.
		// Si se trata del tipo de asociación libre, establecemos el valor
		// almacenado.
		if (IAssociationTypeIdConstants.ID_FREE_ASSOCIATION.longValue() == tslCrmco.getAssociationType()) {

			result = tslCrmco.getValue();

		}
		// Si se trata del tipo de asociación simple, calculamos el valor.
		else if (IAssociationTypeIdConstants.ID_SIMPLE_ASSOCIATION.longValue() == tslCrmco.getAssociationType()) {

			result = calculateMappingAssociationSimple(wrappedCert, tslCrmco.getValue());

		}
		// Si es de cualquier otro tipo, lo ignoramos.

		return result;

	}

	/**
	 * Calculates the mapping with simple association of the input certificate for the input value.
	 * @param wrappedCert X509v3 certificate from which extract the mapping information.
	 * @param fieldToExtract String representation of the field to search in the certificate.
	 * @return String value with the mapping value calculated. <code>null</code> if there is some error
	 * or not was possible to extract the specified information.
	 */
	private static String calculateMappingAssociationSimple(WrapperX509Cert wrappedCert, String fieldToExtract) {

		String result = null;

		if (wrappedCert!=null && !UtilsStringChar.isNullOrEmpty(fieldToExtract)) {
			
			try {
				int certInfoToExtract = Integer.parseInt(fieldToExtract);
				result = wrappedCert.getCertificateInfo(certInfoToExtract);
			} catch (NumberFormatException e) {
				result = null;
			}
			
		}

		return result;

	}

	/**
	 * Calculates the mapping for the logic field 'certClassification' from the input mapping value
	 * of the logic field 'clasificacion'.
	 * @param classificationValue String representation of the mapping value of the logical field 'clasificacion'.
	 * @return String that represents the mapping classification for the input value. It only can be one of the following:<br>
	 * <ul>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_UNKNOWN}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_NATURAL_PERSON}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_LEGALPERSON}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_ESEAL}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_ESIG}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_WSA}</li>
	 *   <li>{@link ITslMappingConstants#MAPPING_VALUE_CLASSIFICATION_TSA}</li>
	 * </ul>
	 */
	public static String calculateMappingCertClassificationFromMappingClassification(String classificationValue) {

		String result = ITslMappingConstants.MAPPING_VALUE_UNKNOWN;

		// Si la entrada no es nula...
		if (!UtilsStringChar.isNullOrEmptyTrim(classificationValue)) {

			try {

				// Se intenta parsear.
				Integer classificationValueInteger = Integer.valueOf(classificationValue);

				// Miramos primero si es de tipo NATURAL_PERSON.
				Set<Integer> setOfValues = TSLProperties.getClassificationSetForCertClassificationNaturalPerson();
				if (setOfValues.contains(classificationValueInteger)) {

					result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_NATURAL_PERSON;

				} else {

					// Ahora si es de tipo LEGAL_PERSON.
					setOfValues = TSLProperties.getClassificationSetForCertClassificationLegalPerson();
					if (setOfValues.contains(classificationValueInteger)) {

						result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_LEGALPERSON;

					} else {

						// Ahora si es de tipo ESIG.
						setOfValues = TSLProperties.getClassificationSetForCertClassificationESIG();
						if (setOfValues.contains(classificationValueInteger)) {

							result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_ESIG;

						} else {

							// Ahora si es de tipo ESEAL.
							setOfValues = TSLProperties.getClassificationSetForCertClassificationESEAL();
							if (setOfValues.contains(classificationValueInteger)) {

								result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_ESEAL;

							} else {

								// Ahora si es de tipo WSA.
								setOfValues = TSLProperties.getClassificationSetForCertClassificationWSA();
								if (setOfValues.contains(classificationValueInteger)) {

									result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_WSA;

								} else {

									// Ahora si es de tipo TSA.
									setOfValues = TSLProperties.getClassificationSetForCertClassificationTSA();
									if (setOfValues.contains(classificationValueInteger)) {

										result = ITslMappingConstants.MAPPING_VALUE_CLASSIFICATION_TSA;

									}

								}

							}

						}

					}

				}

			} catch (NumberFormatException e) {
				result = ITslMappingConstants.MAPPING_VALUE_UNKNOWN;
			}

		}

		return result;

	}

	/**
	 * Adds the mappings extracted from the TSL information (and certificate) over the mappings configured in the certificate policies.
	 * If the {@link ITslMappingConstants#MAPPING_KEY_CERT_CLASSIFICATION} is with {@link ITslMappingConstants#MAPPING_VALUE_UNKNOWN} in TSL mappings, then is calculated
	 * from the {@link ITslMappingConstants#MAPPING_KEY_CERT_CLASIFICACION} field from the original mappings.
	 * @param certInfoMap Mappings calculated from the certificate policy configuration.
	 * @param certInfoMapFromTSL Mappings calculated from the information in the TSL and the certificate.
	 * @return Map with all the mixed mappings.
	 */
	public static Map<String, String> addMappingsAndCheckCertClassification(Map<String, String> certInfoMap, Map<String, String> certInfoMapFromTSL) {

		// Inicializamos el resultado.
		Map<String, String> result = certInfoMap;

		// Si los mapeos obtenidos de la TSL no son nulos...
		if (certInfoMapFromTSL != null) {

			// Si fuera nulo el resultado, creamos el map.
			if (result == null) {
				result = new HashMap<String, String>();
			}

			// Comprobamos el valor del campo 'certClassification' en los mapeos
			// de la TSL.
			String certClassificationValue = certInfoMapFromTSL.get(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION);

			// Si es nulo, vació o con valor desconocido, lo modificamos.
			if (UtilsStringChar.isNullOrEmptyTrim(certClassificationValue) || certClassificationValue.equals(ITslMappingConstants.MAPPING_VALUE_UNKNOWN)) {

				certInfoMapFromTSL.put(ITslMappingConstants.MAPPING_KEY_CERT_CLASSIFICATION, calculateMappingCertClassificationFromMappingClassification(result.get(ITslMappingConstants.MAPPING_KEY_CERT_CLASIFICACION)));

			}

			// Añadimos todos los mapeos obtenidos de la TSL, sobrescribiendo
			// los que hubiera en la política de certificación.
			result.putAll(certInfoMapFromTSL);

		}

		// Devolvemos el resultado.
		return result;

	}

}
