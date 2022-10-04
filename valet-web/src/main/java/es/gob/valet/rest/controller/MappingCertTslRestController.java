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
 * @version 1.5, 04/10/2022.
 */
package es.gob.valet.rest.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.configuration.model.dto.MappingCertTslsDTO;
import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;
import es.gob.valet.persistence.configuration.model.entity.TSLService;
import es.gob.valet.persistence.configuration.services.ifaces.IMappingCertTslService;
import es.gob.valet.persistence.utils.BootstrapTreeNode;
import es.gob.valet.tsl.access.TslInformationTree;

/**
 * <p>Class that manages the REST request related to the Mapping Certificate TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.5, 04/10/2022.
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
	 * Attribute that represents the tsp service name select for the user in the tree.
	 */
	public static final String TSPSERVICENAMESELECTTREE = "tspServiceNameSelectTree";

	/**
	 * Attribute that represents the tsp name select for the user in the tree.
	 */
	private static final String TSPNAMESELECTTREE = "tspNameSelectTree";
	
	/**
	 * Attribute that represents the country select for the user in the tree.
	 */
	private static final String COUNTRYSELECTTREE = "countrySelectTree";
	
	/**
	 * Attribute that represents the certificate attached for the user in the input.
	 */
	private static final String INPUT_FILECERTIFICATETSL = "fileCertificateTsl";
	
	/**
	 * Attribute that represents the value search entered for the user in the input search.
	 */
	private static final String INPUT_VALUESEARCH = "valueSearch";
	
	/**
	 * Attribute that represents the value id mapping certificate tsl click for the user in the datatable.
	 */
	private static final String DATATABLE_IDMAPPINGCERTTSL = "idMappingCertTsl";
	
	/**
	 * Attribute that represents the value Tsl Service DTO who model attribute of the interface.
	 */
	public static final String TSLSERVICEDTO = "tslServiceDTO";
	
	/**
	 * Attribute that represents the status 506 for valet validation exception in call ajax.
	 */
	private static final int VALIDATIONSMAPPINGCERTTSL = 506;
	
	
	/**
	 * Method that obtain the list of data to datatable in part front. This information be represent in object Datatable.
	 * 
	 * @param idMappingCertTsl parameter that contain id of mapping certificate tsls.
	 * @return the list of data to datatable in part front. This information be represent in object Datatable.
	 */
	@PostMapping(value = "/loadingDatatableMappingCertTsls")
	public DataTablesOutput<MappingCertTslsDTO> loadingDatatableMappingCertTsls(@RequestParam(DATATABLE_IDMAPPINGCERTTSL) Long idMappingCertTsl) {
		
		DataTablesOutput<MappingCertTslsDTO> dataTablesOutput =  iMappingCertTslService.createDatatableMappingCertTsls(idMappingCertTsl);
		
		return dataTablesOutput;
	}
	
	/**
	 * Method that search in tree value enter for user in searching. 
	 * 
	 * @param valueSearch parameter that contain value enter for user in searching.
	 * @param response parameter that represents posibility errors in process.
	 * @return tree with nodes found.
	 */
	@SuppressWarnings("static-access")
	@PostMapping(value = "/searchInTree")
	public String searchInTree(@RequestParam(INPUT_VALUESEARCH) String valueSearch, HttpServletResponse response) {
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
			@RequestPart(TSPSERVICENAMESELECTTREE) String tspServiceNameSelectTree,
			@RequestPart(TSPNAMESELECTTREE) String tspNameSelectTree,
			@RequestPart(COUNTRYSELECTTREE) String countrySelectTree,
			@RequestPart(INPUT_FILECERTIFICATETSL) MultipartFile fileCertificateTsl, 
			HttpServletResponse response) {
		ObjectMapper objectMapper = new ObjectMapper();
		String res = null;
		try {
			Map<String, String> errors = this.validateInputs(fileCertificateTsl);
			if(!errors.isEmpty()) {
				response.setStatus(VALIDATIONSMAPPINGCERTTSL);
				res = objectMapper.writeValueAsString(errors);
			} else {
				Map<String, List<TslMappingDTO>> mapTslMappingDTO = tslInformationTree.getMapTslMappingTree();
				TSLService tslService = iMappingCertTslService.saveOrUpdateTslService(mapTslMappingDTO, tspServiceNameSelectTree, tspNameSelectTree, countrySelectTree, fileCertificateTsl.getBytes());
				res = objectMapper.writeValueAsString(tslService);
			}
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			LOGGER.error(e.getMessage(), e);
			res = e.getMessage();
		} catch (ParseException e) {
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
	private Map<String, String> validateInputs(MultipartFile fileCertificateTsl) throws IOException {
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
}
