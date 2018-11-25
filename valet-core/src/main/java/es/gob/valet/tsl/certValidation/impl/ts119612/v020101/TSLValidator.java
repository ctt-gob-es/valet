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
 * <b>File:</b><p>es.gob.valet.tsl.certValidation.impl.ts119612.v020101.TSLValidator.java.</p>
 * <b>Description:</b><p>Class that represents a TSL Validator implementation for the
 * ETSI TS 119612 2.1.1 specification.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25/11/2018.
 */
package es.gob.valet.tsl.certValidation.impl.ts119612.v020101;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreTslMessages;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorOtherConstants;
import es.gob.valet.tsl.certValidation.ifaces.ITSLValidatorResult;
import es.gob.valet.tsl.certValidation.impl.TSLValidatorMappingCalculator;
import es.gob.valet.tsl.certValidation.impl.common.ATSLValidator;
import es.gob.valet.tsl.certValidation.impl.common.TSLCertificateExtensionAnalyzer;
import es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult;
import es.gob.valet.tsl.exceptions.TSLValidationException;
import es.gob.valet.tsl.parsing.ifaces.IAnyTypeExtension;
import es.gob.valet.tsl.parsing.ifaces.ITSLCommonURIs;
import es.gob.valet.tsl.parsing.ifaces.ITSLOIDs;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSPService;
import es.gob.valet.tsl.parsing.impl.common.extensions.AdditionalServiceInformation;

/**
 * <p>Class that represents a TSL Validator implementation for the
 * ETSI TS 119612 2.1.1 specification.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25/11/2018.
 */
public class TSLValidator extends ATSLValidator {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(TSLValidator.class);

	/**
	 * Constructor method for the class TSLValidator.java.
	 * @param tslObject
	 */
	public TSLValidator(ITSLObject tslObject) {
		super(tslObject);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSLisFromEuropeanMember()
	 */
	@Override
	protected boolean checkIfTSLisFromEuropeanMember() {
		String tslType = getTSLObject().getSchemeInformation().getTslType().toString();
		return tslType.equalsIgnoreCase(ITSLCommonURIs.TSL_TYPE_EUGENERIC) || tslType.equalsIgnoreCase(ITSLCommonURIs.TSL_TYPE_EULISTOFTHELIST);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSLisListOfLists(java.lang.String)
	 */
	@Override
	protected boolean checkIfTSLisListOfLists(String tslType) {
		return tslType.equalsIgnoreCase(ITSLCommonURIs.TSL_TYPE_EULISTOFTHELIST) || tslType.startsWith(ITSLCommonURIs.TSL_TYPE_NONEULISTOFTHELISTS_PREFFIX) && tslType.endsWith(ITSLCommonURIs.TSL_TYPE_NONEULISTOFTHELISTS_PREFFIX);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfStatusDeterminationApproachIsDelinquentOrEquivalent(java.lang.String)
	 */
	@Override
	protected boolean checkIfStatusDeterminationApproachIsDelinquentOrEquivalent(String statusDeterminationApproach) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSPServiceTypeIsCAPKC(java.lang.String)
	 */
	@Override
	public boolean checkIfTSPServiceTypeIsCAPKC(String tspServiceType) {
		return tspServiceType.equalsIgnoreCase(ITSLCommonURIs.TSL_SERVICETYPE_CA_PKC);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSPServiceTypeIsCAQC(java.lang.String)
	 */
	@Override
	public boolean checkIfTSPServiceTypeIsCAQC(String tspServiceType) {
		return tspServiceType.equalsIgnoreCase(ITSLCommonURIs.TSL_SERVICETYPE_CA_QC);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSPServiceTypeIsNationalRootCAQC(java.lang.String)
	 */
	@Override
	protected boolean checkIfTSPServiceTypeIsNationalRootCAQC(String tspServiceType) {
		return tspServiceType.equalsIgnoreCase(ITSLCommonURIs.TSL_SERVICETYPE_NATIONALROOTCA);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSPServiceTypeIsTSAQualified(java.lang.String)
	 */
	@Override
	protected boolean checkIfTSPServiceTypeIsTSAQualified(String tspServiceType) {
		return tspServiceType.equalsIgnoreCase(ITSLCommonURIs.TSL_SERVICETYPE_TSA_QTST);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSPServiceTypeIsTSANonQualified(java.lang.String)
	 */
	@Override
	protected boolean checkIfTSPServiceTypeIsTSANonQualified(String tspServiceType) {
		return tspServiceType.equalsIgnoreCase(ITSLCommonURIs.TSL_SERVICETYPE_TSA) || tspServiceType.equalsIgnoreCase(ITSLCommonURIs.TSL_SERVICETYPE_TSA_TSSQC) || tspServiceType.equalsIgnoreCase(ITSLCommonURIs.TSL_SERVICETYPE_TSA_TSS_ADESQC_AND_QES);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSPServiceAdditionalServiceInformationExtensionsDetectCert(es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult, es.gob.valet.tsl.parsing.impl.common.TSPService)
	 */
	@Override
	protected Boolean checkIfTSPServiceAdditionalServiceInformationExtensionsDetectCert(TSLValidatorResult validationResult, TSPService tspService) {

		// Inicialmente consideramos que no se han definido extensiones
		// AdditionalServiceInformation.
		Boolean result = null;

		// Primero recolectamos todas las extensiones del tipo
		// AdditionalServiceInformation.
		List<AdditionalServiceInformation> asiList = new ArrayList<AdditionalServiceInformation>();

		// Recuperamos la lista de extensiones del servicio, y si no es nula ni
		// vacía, continuamos.
		List<IAnyTypeExtension> extensionsList = tspService.getServiceInformation().getServiceInformationExtensions();
		if (extensionsList != null && !extensionsList.isEmpty()) {

			// Recorremos la lista buscando aquellas que sean de tipo
			// AdditionalServiceInformation.
			for (IAnyTypeExtension extension: extensionsList) {

				// Si es del tipo AdditionalServiceInformation...
				if (extension.getImplementationExtension() == IAnyTypeExtension.IMPL_ADDITIONAL_SERVICE_INFORMATION) {

					// La añadimos a la lista final.
					asiList.add((AdditionalServiceInformation) extension);

				}

			}

		}

		// Si la lista no es vacía...
		if (!asiList.isEmpty()) {

			// Ahora indicamos que el resultado es false.
			result = Boolean.FALSE;

			// Inicializamos las banderas que nos marcarán los valores
			// encontrados.
			boolean asiForESIG = false;
			boolean asiForESeal = false;
			boolean asiForWSA = false;

			// Recorremos la lista y vamos comprobando las URI.
			for (AdditionalServiceInformation asi: asiList) {

				// En función de la URI, vamos marcando las banderas.
				switch (asi.getUri().toString()) {
					case ITSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_ROOTCAQC:
						break;

					case ITSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_FORESIGNATURES:
						asiForESIG = true;
						break;

					case ITSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_FORESEALS:
						asiForESeal = true;
						break;

					case ITSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_FORWEBSITEAUTHENTICATION:
						asiForWSA = true;
						break;

					default:
						break;
				}

			}

			// Una vez tenemos los marcadores de los
			// AdditionalServiceInformation,
			// los vamos comprobando, y para ello recuperamos el analizador de
			// extensiones.
			TSLCertificateExtensionAnalyzer tslCertExtAnalyzer = validationResult.getTslCertificateExtensionAnalyzer();

			// Vamos comprobando de mayor requirimiento a menos...
			// Primero autenticación servidor...
			result = asiForWSA && (tslCertExtAnalyzer.hasQcStatementEuTypeExtensionOid(ITSLOIDs.OID_QCSTATEMENT_EXT_EUTYPE_WEB.getId()) || tslCertExtAnalyzer.hasSomeCertPolPolInfExtensionOid(ITSLValidatorOtherConstants.POLICYIDENTIFIERS_OIDS_FOR_WSA_CERTS_LIST));
			if (result) {

				validationResult.setMappingClassification(ITSLValidatorResult.MAPPING_CLASSIFICATION_WSA);

			} else {

				// Sello...
				result = asiForESeal && (tslCertExtAnalyzer.hasQcStatementEuTypeExtensionOid(ITSLOIDs.OID_QCSTATEMENT_EXT_EUTYPE_ESEAL.getId()) || tslCertExtAnalyzer.hasSomeCertPolPolInfExtensionOid(ITSLValidatorOtherConstants.POLICYIDENTIFIERS_OIDS_FOR_ESEAL_CERTS_LIST));
				if (result) {

					validationResult.setMappingClassification(ITSLValidatorResult.MAPPING_CLASSIFICATION_ESEAL);

				} else {

					result = asiForESIG && (tslCertExtAnalyzer.isThereSomeQcStatementExtension() || tslCertExtAnalyzer.hasSomeCertPolPolInfExtensionOid(ITSLValidatorOtherConstants.POLICYIDENTIFIERS_OIDS_FOR_ESIG_CERTS_LIST));
					if (result) {

						validationResult.setMappingClassification(ITSLValidatorResult.MAPPING_CLASSIFICATION_ESIG);

					}

				}

			}

		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#analyzeQualifierToSetMapping(es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult, java.lang.String)
	 */
	@Override
	protected void analyzeQualifierToSetMapping(TSLValidatorResult validationResult, String qualifierUriString) {

		switch (qualifierUriString) {

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCWITHSSCD:
				validationResult.setMappingQSCD(ITSLValidatorResult.MAPPING_QSCD_YES);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCNOSSCD:
				validationResult.setMappingQSCD(ITSLValidatorResult.MAPPING_QSCD_NO);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCSTATUSASINCERT:
				validationResult.setMappingQSCD(ITSLValidatorResult.MAPPING_QSCD_ASINCERT);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCWITHQSCD:
				validationResult.setMappingQSCD(ITSLValidatorResult.MAPPING_QSCD_YES);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCNOQSCD:
				validationResult.setMappingQSCD(ITSLValidatorResult.MAPPING_QSCD_NO);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCQSCDSTATUSASINCERT:
				validationResult.setMappingQSCD(ITSLValidatorResult.MAPPING_QSCD_ASINCERT);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCQSCDMANAGEDONBEHALF:
				validationResult.setMappingQSCD(ITSLValidatorResult.MAPPING_QSCD_YES_MANAGEDONBEHALF);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCFORLEGALPERSON:
				validationResult.setMappingClassification(ITSLValidatorResult.MAPPING_CLASSIFICATION_LEGALPERSON);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCFORESIG:
				validationResult.setMappingClassification(ITSLValidatorResult.MAPPING_CLASSIFICATION_ESIG);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCFORESEAL:
				validationResult.setMappingClassification(ITSLValidatorResult.MAPPING_CLASSIFICATION_ESEAL);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCFORWSA:
				validationResult.setMappingClassification(ITSLValidatorResult.MAPPING_CLASSIFICATION_WSA);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_NOTQUALIFIED:
				validationResult.setMappingType(ITSLValidatorResult.MAPPING_TYPE_NONQUALIFIED);
				break;

			case ITSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCSTATEMENT:
				validationResult.setMappingType(ITSLValidatorResult.MAPPING_TYPE_QUALIFIED);
				break;

			default:
				break;

		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfCertificateObeyWithConditionsToBeDetected(es.gob.valet.tsl.certValidation.impl.common.TSLCertificateExtensionAnalyzer)
	 */
	@Override
	protected boolean checkIfCertificateObeyWithConditionsToBeDetected(TSLCertificateExtensionAnalyzer tslCertExtAnalyzer) {

		// Inicializamos el resultado a que el certificado no es detectado.
		boolean result = false;

		try {

			// Obtenemos si es posible la información de si es Qualified.
			String mappingQualifiedCert = TSLValidatorMappingCalculator.getMappingTypeQualifiedFromCertificate(tslCertExtAnalyzer);

			// Obtenemos si es posible la información relativa a QSCD/SSCD.
			String mappingQscd = TSLValidatorMappingCalculator.getMappingQSCDFromCertificate(tslCertExtAnalyzer);

			// Obtenemos si es posible el tipo de certificado.
			String mappingClassification = TSLValidatorMappingCalculator.getMappingClassificationFromCertificate(tslCertExtAnalyzer, false);

			// Se debe cumplir:
			// - Que se haya podido determinar que el certificado es
			// cualificado.
			// - Si el certificado se encuentra (o no) en un SSCD/QSCD.
			// - Que el certificado haya sido emitido para una "legal person" o
			// para ESIG, ESEAL o WSA.
			result = TSLValidatorMappingCalculator.MAPPING_VALUE_YES.equals(mappingQualifiedCert) && !TSLValidatorMappingCalculator.MAPPING_VALUE_UNKNOWN.equals(mappingQscd);
			if (result) {
				result = TSLValidatorMappingCalculator.MAPPING_VALUE_CLASSIFICATION_LEGALPERSON.equals(mappingClassification) || TSLValidatorMappingCalculator.MAPPING_VALUE_CLASSIFICATION_ESIG.equals(mappingClassification);
				result = result || TSLValidatorMappingCalculator.MAPPING_VALUE_CLASSIFICATION_ESEAL.equals(mappingClassification) || TSLValidatorMappingCalculator.MAPPING_VALUE_CLASSIFICATION_WSA.equals(mappingClassification);
			}

		} catch (TSLValidationException e) {

			// En caso de no parsear el certificado, se muestra error, y se
			// considera
			// no detectado.
			LOGGER.error(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL197), e);

		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSPServiceStatusIsOK(java.lang.String)
	 */
	@Override
	public boolean checkIfTSPServiceStatusIsOK(String serviceStatus) {

		boolean result = serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_GRANTED) || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_RECOGNISEDATNATIONALLEVEL);
		result = result || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_UNDERSUPERVISION) || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_SUPERVISIONINCESSATION);
		result = result || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_ACCREDITED) || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_SETBYNATIONALLAW);
		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#setStatusResultInAccordanceWithTSPServiceCurrentStatus(java.lang.String, java.util.Date, java.util.Date, es.gob.valet.tsl.certValidation.impl.common.TSLValidatorResult)
	 */
	@Override
	protected void setStatusResultInAccordanceWithTSPServiceCurrentStatus(String serviceStatus, Date serviceStatusStartingTime, Date validationDate, TSLValidatorResult validationResult) {

		// Solo tenemos en cuenta el Servicio si su estado comenzó de forma
		// anterior
		// a la fecha de validación.
		if (serviceStatusStartingTime.before(validationDate)) {

			boolean statusOK = checkIfTSPServiceStatusIsOK(serviceStatus);

			boolean statusChainNotValid = serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_SUPERVISIONCEASED) || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_ACCREDITATIONCEASED);

			boolean statusRevoked = serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_SUPERVISIONREVOKED) || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_ACCREDITATIONREVOKED);
			statusRevoked = statusRevoked || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_WITHDRAWN) || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_DEPRECATEDATNATIONALLEVEL);
			statusRevoked = statusRevoked || serviceStatus.equals(ITSLCommonURIs.TSL_SERVICECURRENTSTATUS_DEPRECATEDBYNATIONALLAW);

			// Si el estado del servicio es OK, establecemos
			// que se detecta el certificado.
			if (statusOK) {

				// Se establece el estado a detectado (desconocido), ya que
				// ahora habría que
				// buscar la forma de comprobar el estado de revocación.
				validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_UNKNOWN);

			} else if (statusChainNotValid) {

				validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_CERTCHAIN_NOTVALID_SERVICESTATUS);

			} else if (statusRevoked) {

				validationResult.setResult(ITSLValidatorResult.RESULT_DETECTED_STATE_REVOKED_SERVICESTATUS);

			}

		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSPServiceTypeIsCRLCompatible(es.gob.valet.tsl.parsing.impl.common.TSPService, boolean)
	 */
	@Override
	public boolean checkIfTSPServiceTypeIsCRLCompatible(TSPService tspService, boolean isCertQualified) {

		boolean result = false;

		// Si el certificado es cualificado (qualified)...
		if (isCertQualified) {

			result = tspService.getServiceInformation().getServiceTypeIdentifier().toString().equals(ITSLCommonURIs.TSL_SERVICETYPE_CERTSTATUS_CRL_QC);

		}
		// Si no es cualificado...
		else {

			result = tspService.getServiceInformation().getServiceTypeIdentifier().toString().equals(ITSLCommonURIs.TSL_SERVICETYPE_CERTSTATUS_CRL);

		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.certValidation.impl.common.ATSLValidator#checkIfTSPServiceTypeIsOCSPCompatible(es.gob.valet.tsl.parsing.impl.common.TSPService, boolean)
	 */
	@Override
	public boolean checkIfTSPServiceTypeIsOCSPCompatible(TSPService tspService, boolean isCertQualified) {

		boolean result = false;

		// Si el certificado es cualificado (qualified)...
		if (isCertQualified) {

			result = tspService.getServiceInformation().getServiceTypeIdentifier().toString().equals(ITSLCommonURIs.TSL_SERVICETYPE_CERTSTATUS_OCSP_QC);

		}
		// Si no es cualificado...
		else {

			result = tspService.getServiceInformation().getServiceTypeIdentifier().toString().equals(ITSLCommonURIs.TSL_SERVICETYPE_CERTSTATUS_OCSP);

		}

		return result;

	}

}
