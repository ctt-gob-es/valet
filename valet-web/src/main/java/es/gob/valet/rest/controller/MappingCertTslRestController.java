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
 * <b>File:</b><p>es.gob.valet.rest.controller.MappingCertTslRestController.java.</p>
 * <b>Description:</b><p> Class that manages the REST request related to the Mapping Certificate TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.8, 13/10/2022.
 */
package es.gob.valet.rest.controller;

import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_AUTHORITY_INFORMATION_ACCESS;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_CERTPOL_INFO_OIDS;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_CERT_VERSION;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_COMMON_NAME;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_COUNTRY;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_CRL_DISTRIBUTION_POINTS;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_GIVEN_NAME;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_ISSUER;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_IS_CA;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_KEY_USAGE;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_PSEUDONYM;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_QC_STATEMENTS_EXTEUTYPE_OIDS;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_QC_STATEMENTS_OIDS;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_SERIAL_NUMBER;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_SIGALG_NAME;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_SIGALG_OID;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_SUBJECT;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_SUBJECT_ALT_NAME;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_SUBJECT_SERIAL_NUMBER;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_SURNAME;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_VALID_FROM;
import static es.gob.valet.persistence.utils.ConstantsUtils.INFOCERT_VALID_TO;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.configuration.model.dto.MappingTslDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;
import es.gob.valet.persistence.configuration.model.dto.TslServiceDTO;
import es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService;
import es.gob.valet.persistence.utils.BootstrapTreeNode;
import es.gob.valet.tsl.access.TslInformationTree;
import es.gob.valet.tsl.certValidation.impl.common.WrapperX509Cert;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;

/**
 * <p>Class that manages the REST request related to the Mapping Certificate TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.8, 13/10/2022.
 */
@RestController
@RequestMapping(value = "/mappingCertTslRest")
public class MappingCertTslRestController {
	
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(GeneralConstants.LOGGER_NAME_VALET_LOG);

	/**
	 * Attribute that represents the service object for accessing the repository of mapping certificate tsls.
	 */
	@Autowired
	private IMappingCertTslService iMappingCertTslService;
	
	/**
	 * Attribute that represents bean spring for accessing the map of mapping certificate tsls.
	 */
	@Autowired
	private TslInformationTree tslInformationTree;
	
	/**
	 * Attribute that represents the identifier of the html input file certificate tsl id.
	 */
	private static final String FIELD_FILE_CERTIFICATE_TSL_ID = "fileCertificateTslId";
	
	/**
	 * Attribute that represents the identifier of the html input identificator logical field.
	 */
	private static final String FIELD_IDENTIFICATOR_LOGICAL_FIELD = "identificatorLogicalField";
	
	/**
	 * Attribute that represents the identifier of the html input value logical field free.
	 */
	private static final String FIELD_VALUE_LOGICAL_FIELD_FREE = "valueLogicalFieldFree";

	/**
	 * Attribute that represents the identifier of the html input value logical field simple.
	 */
	private static final String FIELD_VALUE_LOGICAL_FIELD_SIMPLE = "valueLogicalFieldSimple";
	
	/**
	 * Attribute that represents the tsp service name select for the user in the tree.
	 */
	public static final String REQ_PARAM_TSP_SERVICE_NAME_SELECT_TREE = "tspServiceNameSelectTree";
	
	/**
	 * Attribute that represents the id of tsp service name select for the user in the tree.
	 */
	public static final String REQ_PARAM_ID_TSL_MAPPING = "idTslMapping";
	
	/**
	 * Attribute that represents the identifier of the html input id siple value.
	 */
	public static final String REQ_PARAM_ID_SIMPLE_VALUE = "idSimpleValue";

	/**
	 * Attribute that represents the tsp name select for the user in the tree.
	 */
	private static final String REQ_PARAM_TSP_NAME_SELECT_TREE = "tspNameSelectTree";
	
	/**
	 * Attribute that represents the country select for the user in the tree.
	 */
	private static final String REQ_PARAM_COUNTRY_SELECT_TREE = "countrySelectTree";
	
	/**
	 * Attribute that represents the certificate attached for the user in the input.
	 */
	private static final String REQ_PARAM_FILE_CERTIFICATE_TSL = "fileCertificateTsl";
	
	/**
	 * Attribute that represents the value search entered for the user in the input search.
	 */
	private static final String REQ_PARAM_VALUE_SEARCH = "valueSearch";
	
	/**
	 * Attribute that represents the value Tsl Service DTO who model attribute of the interface.
	 */
	public static final String TSLSERVICEDTO = "tslServiceDTO";
	
	/**
	 * Attribute that represents the status 506 for valet validation exception in call ajax.
	 */
	private static final int VALIDATIONSMAPPINGCERTTSL = 506;
	
	/**
	 * Method that search in tree value enter for user in searching. 
	 * 
	 * @param valueSearch parameter that contain value enter for user in searching.
	 * @param response parameter that represents posibility errors in process.
	 * @return tree with nodes found.
	 */
	@SuppressWarnings("static-access")
	@PostMapping(value = "/searchInTree")
	public String searchInTree(@RequestParam(REQ_PARAM_VALUE_SEARCH) String valueSearch, HttpServletResponse response) {
		Map<String, List<TslMappingDTO>> mapTsl = tslInformationTree.getMapTslMappingTree();
		List<BootstrapTreeNode> listBootstrapTreeNode = iMappingCertTslService.createTreeMappingCertTsl(mapTsl, valueSearch);
		ObjectMapper mapper = new ObjectMapper();
		String res = null;
		try {
			res = mapper.writeValueAsString(listBootstrapTreeNode);
		} catch (JsonProcessingException e) {
			res = e.getMessage();
			LOGGER.error(res, e);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		return res;
		
	}
	
	/**
	 * Method that get the request to update certificate tsl
	 * 
	 * @param tspServiceNameSelectTree parameter that contain of tsp service name select for the user.
	 * @param tspNameSelectTree parameter that contain of tsp name select for the user.
	 * @param countrySelectTree parameter that contain of country select for the user.
	 * @param fileCertificateTsl parameter that contain certificate select for the user. 
	 * @param response parameter that contain response with status 200 or 500.
	 * @return object JSON with result.
	 */
	@SuppressWarnings("static-access")
	@PostMapping(value = "/updateCertTsl")
	public String updateCertTsl(
			@RequestPart(REQ_PARAM_TSP_SERVICE_NAME_SELECT_TREE) String tspServiceNameSelectTree,
			@RequestPart(REQ_PARAM_TSP_NAME_SELECT_TREE) String tspNameSelectTree,
			@RequestPart(REQ_PARAM_COUNTRY_SELECT_TREE) String countrySelectTree,
			@RequestPart(REQ_PARAM_FILE_CERTIFICATE_TSL) MultipartFile fileCertificateTsl, 
			HttpServletResponse response) {
		ObjectMapper objectMapper = new ObjectMapper();
		String res = null;
		try {
			Map<String, String> errors = this.validateInputsUpdate(fileCertificateTsl);
			if(!errors.isEmpty()) {
				response.setStatus(VALIDATIONSMAPPINGCERTTSL);
				res = objectMapper.writeValueAsString(errors);
			} else {
				Map<String, List<TslMappingDTO>> mapTslMappingDTO = tslInformationTree.getMapTslMappingTree();
				res = objectMapper.writeValueAsString(new TslServiceDTO(iMappingCertTslService.saveOrUpdateTslService(mapTslMappingDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree, fileCertificateTsl.getBytes()))); 
			}
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			res = e.getMessage();
		} catch (ParseException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			res = e.getMessage();
		} catch (CommonUtilsException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			res = e.getMessage();
		}
		return res;
	}

	/**
	 * Method that performs validations on the attached file.
	 * 
	 * @param fileCertificateTsl parameter that contain file with certificate or other file.
	 * @return map empty or with errors.
	 * @throws IOException If the method fails.
	 */
	private Map<String, String> validateInputsUpdate(MultipartFile fileCertificateTsl) throws IOException {
		Map<String, String> mErrors = new HashMap<String, String>();
		if (fileCertificateTsl == null || fileCertificateTsl.getSize() == 0 || fileCertificateTsl.getBytes() == null || fileCertificateTsl.getBytes().length == 0) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_CERTIFICATE_FILE));
			mErrors.put(FIELD_FILE_CERTIFICATE_TSL_ID + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_CERTIFICATE_FILE));
		} else {
			try {
				UtilsCertificate.getX509Certificate(fileCertificateTsl.getBytes());
			} catch (CommonUtilsException e) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_CERT_INCORRECT));
				mErrors.put(FIELD_FILE_CERTIFICATE_TSL_ID + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_CERT_INCORRECT));
			}
		}
		return mErrors;
	}
	
	/**
	 * Method that obtain tsp service name search for tsp service name select in the tree.
	 * 
	 * @param tspServiceNameSelectTree parameter that contain of tsp service name select for the user.
	 * @param response parameter that represents posibility errors in process.
	 * @return tsp service DTO in format Json.
	 */
	@PostMapping(value = "/obtainTspServiceNameSelectTree")
	private String obtainTspServiceNameSelectTree(@RequestParam(REQ_PARAM_TSP_SERVICE_NAME_SELECT_TREE) String tspServiceNameSelectTree, HttpServletResponse response) {
		TslServiceDTO tslServiceDTO = null;
		String res = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			tslServiceDTO = iMappingCertTslService.obtainTspServiceNameSelectTree(tspServiceNameSelectTree);
			res = objectMapper.writeValueAsString(tslServiceDTO);
		} catch (CommonUtilsException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res = e.getMessage();
		} catch (JsonProcessingException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res = e.getMessage();
		}
		return res;
	}
	
	/**
	 * Method that select value a watch when user press button watch value.
	 * 
	 * @param tspServiceNameSelectTree parameter that contain of tsp service name select for the user.
	 * @param idSimpleValue parameter that contain of id simple value.
	 * @param response parameter that represents posibility errors in process.
	 * @return value a watch when user press button watch value.
	 */
	@PostMapping(value = "/watchCertificate")
	private String watchCertificate(@RequestParam(REQ_PARAM_TSP_SERVICE_NAME_SELECT_TREE) String tspServiceNameSelectTree, @RequestParam(REQ_PARAM_ID_SIMPLE_VALUE) Integer idSimpleValue, HttpServletResponse response) {
		TslServiceDTO tslServiceDTO;
		String res = null;
		try {
			tslServiceDTO = iMappingCertTslService.obtainTspServiceNameSelectTree(tspServiceNameSelectTree);
			WrapperX509Cert wrapperX509Cert = new WrapperX509Cert(
					UtilsCertificate.getX509Certificate(tslServiceDTO.getCertificate()));

			switch (idSimpleValue) {
				case INFOCERT_CERT_VERSION:
					res = wrapperX509Cert.getX509CertVersion();
					break;
				case INFOCERT_SUBJECT:
					res = wrapperX509Cert.getSubject();
					break;
				case INFOCERT_ISSUER:
					res = wrapperX509Cert.getIssuer();
					break;
				case INFOCERT_SERIAL_NUMBER:
					res = wrapperX509Cert.getSerialNumber();
					break;
				case INFOCERT_SIGALG_NAME:
					res = wrapperX509Cert.getSignatureAlgorithmName();
					break;
				case INFOCERT_SIGALG_OID:
					res = wrapperX509Cert.getSignatureAlgorithmOID();
					break;
				case INFOCERT_VALID_FROM:
					res = wrapperX509Cert.getValidFrom();
					break;
				case INFOCERT_VALID_TO:
					res = wrapperX509Cert.getValidTo();
					break;
				case INFOCERT_CERTPOL_INFO_OIDS:
					res = wrapperX509Cert.getExtensionCertPoliciesInformationOIDs();
					break;
				case INFOCERT_QC_STATEMENTS_OIDS:
					res = wrapperX509Cert.getExtensionQcStatementsOIDs();
					break;
				case INFOCERT_QC_STATEMENTS_EXTEUTYPE_OIDS:
					res = wrapperX509Cert.getExtensionQcStatementExtEuTypeOids();
					break;
				case INFOCERT_SUBJECT_ALT_NAME:
					res = wrapperX509Cert.getExtensionSubjectAltName();
					break;
				case INFOCERT_IS_CA:
					res = wrapperX509Cert.getExtensionBasicConstrainstIsCA();
					break;
				case INFOCERT_KEY_USAGE:
					res = wrapperX509Cert.getExtensionKeyUsage();
					break;
				case INFOCERT_CRL_DISTRIBUTION_POINTS:
					res = wrapperX509Cert.getExtensionCRLDistributionPoints();
					break;
				case INFOCERT_AUTHORITY_INFORMATION_ACCESS:
					res = wrapperX509Cert.getExtensionAuthorityInformationAccess();
					break;
				case INFOCERT_SURNAME:
					res = wrapperX509Cert.getSurname();
					break;
				case INFOCERT_COMMON_NAME:
					res = wrapperX509Cert.getCommonName();
					break;
				case INFOCERT_GIVEN_NAME:
					res = wrapperX509Cert.getGivenName();
					break;
				case INFOCERT_COUNTRY:
					res = wrapperX509Cert.getCountry();
					break;
				case INFOCERT_PSEUDONYM:
					res = wrapperX509Cert.getPseudonym();
					break;
				case INFOCERT_SUBJECT_SERIAL_NUMBER:
					res = wrapperX509Cert.getSubjectSerieNumber();
					break;
				default:
					break;
			}
		} catch (CommonUtilsException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res = e.getMessage();
		} catch (TSLCertificateValidationException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res = e.getMessage();
		}
		return res;
	}
	
	/**
	 * Method that save new mapping with logical field and tsl service.
	 * 
	 * @param mappingTslDTO parameter that contain information from interface add logic field.
	 * @param tspServiceNameSelectTree parameter that contain of tsp service name select for the user.
	 * @param tspNameSelectTree parameter that contain of tsp name select for the user.
	 * @param countrySelectTree parameter that contain of country select for the user.
	 * @param response parameter that represents posibility errors in process.
	 * @return exit or error to process.
	 */
	@PostMapping(value = "/addMappingLogicField")
	@Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
	@SuppressWarnings("static-access")
	private String addMappingLogicField(@RequestBody MappingTslDTO mappingTslDTO,
			@RequestParam(REQ_PARAM_TSP_SERVICE_NAME_SELECT_TREE) String tspServiceNameSelectTree,
			@RequestParam(REQ_PARAM_TSP_NAME_SELECT_TREE) String tspNameSelectTree,
			@RequestParam(REQ_PARAM_COUNTRY_SELECT_TREE) String countrySelectTree, HttpServletResponse response) {
		String res = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map<String, String> mErrors = this.validateInputsSave(mappingTslDTO);
			if(!mErrors.isEmpty()) {
				response.setStatus(VALIDATIONSMAPPINGCERTTSL);
				res = objectMapper.writeValueAsString(mErrors);
			} else {
				Map<String, List<TslMappingDTO>> mapTslMappingDTO = tslInformationTree.getMapTslMappingTree();
				iMappingCertTslService.addMappingLogicField(mapTslMappingDTO, mappingTslDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree);
			}
		} catch (ParseException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			res = e.getMessage();
		} catch (JsonProcessingException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			res = e.getMessage();
		}
		return res;
	}

	/**
	 * Method that validate inputs to save new mapping. 
	 *  
	 * @param mappingTslDTO parameter that contain information from interface add logic field.
	 * @return map with errors if this exists.
	 */
	private Map<String, String> validateInputsSave(MappingTslDTO mappingTslDTO) {
		Map<String, String> mErrors = new HashMap<>();
		if(UtilsStringChar.isNullOrEmpty(mappingTslDTO.getLogicalFieldId())) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_IDENTIFICATOR_EMPTY));
			mErrors.put(FIELD_IDENTIFICATOR_LOGICAL_FIELD + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_IDENTIFICATOR_EMPTY));
		} else if (!mappingTslDTO.getLogicalFieldId().equals(mappingTslDTO.getLogicalFieldIdAux())
				&& iMappingCertTslService.existsTspServiceNameAndIdentificator(mappingTslDTO.getTslServiceDTO().getTspServiceName(), mappingTslDTO.getLogicalFieldId())) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_IDENTIFICATOR_DUPLICATE));
			mErrors.put(FIELD_IDENTIFICATOR_LOGICAL_FIELD + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_IDENTIFICATOR_DUPLICATE));
		}
		if (UtilsStringChar.isNullOrEmpty(mappingTslDTO.getLogicalFieldValue())) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_VALUE_EMPTY));
			mErrors.put(FIELD_VALUE_LOGICAL_FIELD_FREE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_VALUE_EMPTY));
		} else if(mappingTslDTO.getLogicalFieldValue().equals(String.valueOf(NumberConstants.NUM_NEG_1))) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_VALUE_EMPTY));
			mErrors.put(FIELD_VALUE_LOGICAL_FIELD_SIMPLE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATION_VALUE_EMPTY));
		}
 		return mErrors;
	}
	
	/**
	 * Method that realized calls for merge for mapping logic field entity.
	 * 
	 * @param mappingTslDTO parameter that contain mapping tsl DTO to transform a entity.
	 * @param response parameter that represents posibility errors in process.
	 * @return string with the result final.
	 */
	@PostMapping(value = "/mergeMappingLogicField")
	@Consumes(MediaType.APPLICATION_JSON_UTF8_VALUE)
	private String mergeMappingLogicField(@RequestBody MappingTslDTO mappingTslDTO, HttpServletResponse response) {
		String res = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			Map<String, String> mErrors = this.validateInputsSave(mappingTslDTO);
			if(!mErrors.isEmpty()) {
				response.setStatus(VALIDATIONSMAPPINGCERTTSL);
				res = objectMapper.writeValueAsString(mErrors);
			} else {
				iMappingCertTslService.mergeMappingLogicField(mappingTslDTO);
			}
		} catch (JsonProcessingException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			res = e.getMessage();
		}
		return res;
	}
	
	/**
	 * Method that realized calls merge to tsl mapping entity.
	 * 
	 * @param idTslMappingDelete parameter that represents the id of mapping tsl.
	 */
	@PostMapping(value = "/deleteMappingLogicalField")
	private void deleteMappingLogicalField(@RequestParam("idTslMappingDelete") Long idTslMappingDelete) {
		iMappingCertTslService.deleteMappingLogicalField(idTslMappingDelete);
	}
	
	/**
	 * Method that obtain a mapping of tsl service in format json.
	 * 
	 * @param tspServiceNameSelectTree parameter that contain of tsp service name select for the user.
	 * @param response parameter that represents posibility errors in process.
	 * @return mapping of tsl service in format json.
	 */
	@PostMapping(value = "/exportMappingToJson")
	private String exportMappingToJson(@RequestParam(REQ_PARAM_TSP_SERVICE_NAME_SELECT_TREE) String tspServiceNameSelectTree, HttpServletResponse response) {
		String res;
		try {
			 res = iMappingCertTslService.obtainJsonWithMappingsToTslService(tspServiceNameSelectTree);
		} catch (JsonProcessingException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			res = e.getMessage();
		}
		return res;
	}
}
