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
 * <b>File:</b><p>es.gob.valet.controller.TslController.java.</p>
 * <b>Description:</b><p> Class that manages the requests related to the TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/06/2018.</p>
 * @author Gobierno de España.
 * @version 1.14, 03/04/2023.
 */
package es.gob.valet.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.commons.utils.CertificateConstants;
import es.gob.valet.form.ConstantsForm;
import es.gob.valet.form.MappingTslForm;
import es.gob.valet.form.TslForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.model.entity.CAssociationType;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.utils.IAssociationTypeIdConstants;
import es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;

/**
 * <p>Class that manages the requests related to the TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 *  @version 1.14, 03/04/2023.
 */
@Controller
public class TslController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(TslController.class);

	/**
	 * Constant that represents the parameter 'idTslCountryRegionMapping'.
	 */
	private static final String FIELD_ID_COUNTRY_REGION_MAPPING = "idTslCountryRegionMapping";

	/**
	 * Constant that represents the parameter 'rowIndexMapping'.
	 */
	private static final String FIELD_ROW_INDEX_MAPPING = "rowIndexMapping";
	/**
	 * Constant that represents the extension XML.
	 */
	private static final String EXTENSION_XML = ".xml";
	/**
	 * Constant that represents the parameter 'key'.
	 */
	private static final String FIELD_MAPPING_KEY = "key";
	/**
	 * Constant that represents the parameter 'value'.
	 */
	private static final String FIELD_MAPPING_VALUE = "value";

	/**
	 * Method that maps the list TSLs to the controller and forwards the list of TSLs to the view.
	 *
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "tsladmin", method = RequestMethod.GET)
	public String tslAdmin(Model model) {
		return "fragments/tsladmin.html";
	}

	/**
	 * Method that maps the add TSL web request to the controller and sets the
	 * backing form.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "addTsl")
	public String addTsl(Model model) {

		List<String> listSpecifications = new ArrayList<String>();
		List<String> listVersions = new ArrayList<String>();

		try {
			Map<String, Set<String>> mapTslSV = TSLManager.getInstance().getsTSLRelationSpecificationAndVersion();
			listSpecifications.addAll(mapTslSV.keySet());
		} catch (TSLManagingException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_LOAD_ADD_TSL, new Object[ ] { e.getMessage() }));
		}

		TslForm tslForm = new TslForm();
		model.addAttribute("tslform", tslForm);
		model.addAttribute("versions", listVersions);
		model.addAttribute("listSpecifications", listSpecifications);

		return "modal/tsl/tslForm.html";
	}

	/**
	 * Method that maps the editing of TSL data to the controller and sets the backing form.
	 * @param idTslData Identifier of the TSL to be edited.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "edittsl", method = RequestMethod.POST)
	public String editTsl(@RequestParam("id") Long idTslData, Model model) {

		TslForm tslForm = new TslForm();
		try {

			TSLDataCacheObject tsldco = TSLManager.getInstance().getTSLDataCacheObject(idTslData);
			TSLCountryRegionCacheObject tslcrco = TSLManager.getInstance().getTSLCountryRegionByIdTslData(idTslData);

			if (tsldco != null) {
				ITSLObject tslObject = (ITSLObject) tsldco.getTslObject();

				// se van obtiendo los datos a mostrar en el formulario
				tslForm.setIdTslData(idTslData);
				tslForm.setCountryName(tslcrco.getName());
				tslForm.setCountry(tslcrco.getCountryRegionId());
				tslForm.setSpecification(tslObject.getSpecification());
				tslForm.setVersion(tslObject.getSpecificationVersion());
				tslForm.setSequenceNumber(tslObject.getSchemeInformation().getTslSequenceNumber());
				tslForm.setUrlTsl(tsldco.getTslLocationUri());
				tslForm.setTslName(TSLManager.getInstance().getTSLSchemeName(idTslData));
				tslForm.setTslResponsible(TSLManager.getInstance().getTSLSchemeOperatorName(idTslData));
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				tslForm.setIssueDate(sdf.format(tsldco.getIssueDate()));
				if(tsldco.getNextUpdateDate() != null){
					tslForm.setExpirationDate(sdf.format(tsldco.getNextUpdateDate()));
				}
				

				if (TSLManager.getInstance().getTSLLegibleDocument(idTslData) != null) {
					tslForm.setIsLegible(true);
				} else {
					tslForm.setIsLegible(false);
				}
				// componemos el nombre del fichero de la implementación XML
				// para
				// que se muestre en administración
				String filenameTSL = tslcrco.getCode() + "-" + tsldco.getSequenceNumber() + EXTENSION_XML;
				tslForm.setAlias(filenameTSL);
			}

		} catch (TSLManagingException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_LOAD_EDIT_TSL, new Object[ ] { e.getMessage() }));
		}

		model.addAttribute("isLegible", tslForm.getIsLegible());
		model.addAttribute("tslform", tslForm);
		return "modal/tsl/tslEditForm";
	}

	/**
	 * Method that loads a datatable with the mappings for the TSL of the indicated country.
	 * @param countryRegionCode Parameter that represents a country/region code.
	 * @param model Parameter that represents holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadmappingdatatable", method = RequestMethod.GET)
	public String loadMappingDataTable(@RequestParam("countryRegionCode") String countryRegionCode, Model model) {
		MappingTslForm mappingTslForm = new MappingTslForm();
		MappingTslForm mappingTslEditForm = new MappingTslForm();
		mappingTslForm.setCodeCountryRegion(countryRegionCode);

		try {
			TSLCountryRegionCacheObject tslcrco = TSLManager.getInstance().getTSLCountryRegionCacheObject(countryRegionCode);
			mappingTslForm.setIdTslCountryRegion(tslcrco.getCountryRegionId());
			mappingTslForm.setNameCountryRegion(tslcrco.getName());

		} catch (TSLManagingException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_LOAD_TSL_MAPPING, new Object[ ] { e.getMessage() }));
		}
		// mappingTslForm.setNameCountryRegion(tslCountryRegionService.getNameCountryRegionById(idCountryRegion));
		model.addAttribute("mappingtslform", mappingTslForm);
		model.addAttribute("mappingedittslform", mappingTslEditForm);

		// se cargan los tipos de asociaciones
		List<ConstantsForm> associationTypes = loadAssociationType();
		model.addAttribute("listTypes", associationTypes);
		// Se cargan las opciones para la asociación simple.
		List<ConstantsForm> associationSimpleValues = loadSimpleAssociationValues();
		model.addAttribute("listAssocSimpleValues", associationSimpleValues);
		return "fragments/tslmapping.html";
	}

	/**
	 * Method that loads the mapping by ID of TslCountryRegionMapping.
	 * @param idTslCountryRegionMapping Parameter that represents the ID of the mapping.
	 * @param model Parameter that represents holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadmappingbyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String loadMappingById(@RequestParam(FIELD_ID_COUNTRY_REGION_MAPPING) Long idTslCountryRegionMapping, @RequestParam(FIELD_ROW_INDEX_MAPPING) String rowIndexMapping, Model model) {
		// TSLCountryRegionMappingCacheObject tslcrmco;
		MappingTslForm mappingTslForm = new MappingTslForm();
		TslCountryRegionMapping tslcrmco = ManagerPersistenceConfigurationServices.getInstance().getTslCountryRegionMappingService().getTslCountryRegionMappingById(idTslCountryRegionMapping);
		// TODO provisionalmente lo obtenemos directamente de BBDD, tendría que
		// ser desde la cache.

		// tslcrmco =
		// TSLManager.getInstance().getTSLCountryRegionMappingCacheObject(null,
		// idTslCountryRegionMapping);
		mappingTslForm.setIdTslCountryRegionMapping(idTslCountryRegionMapping);
		mappingTslForm.setMappingIdentificator(tslcrmco.getMappingIdentificator());
		mappingTslForm.setIdMappingType(tslcrmco.getAssociationType().getIdAssociationType());
		if (mappingTslForm.getIdMappingType().equals(IAssociationTypeIdConstants.ID_FREE_ASSOCIATION)) {
			mappingTslForm.setMappingFreeValue(tslcrmco.getMappingValue());
		} else if (mappingTslForm.getIdMappingType().equals(IAssociationTypeIdConstants.ID_SIMPLE_ASSOCIATION)) {
			mappingTslForm.setMappingSimpleValue(tslcrmco.getMappingValue());
		}
		mappingTslForm.setIdTslCountryRegion(tslcrmco.getTslCountryRegion().getIdTslCountryRegion());
		mappingTslForm.setCodeCountryRegion(tslcrmco.getTslCountryRegion().getCountryRegionCode());
		mappingTslForm.setRowIndexMapping(rowIndexMapping);
	
		// se cargan los tipos de asociaciones
		List<ConstantsForm> associationTypes = loadAssociationType();
		model.addAttribute("listTypes", associationTypes);
		// Se cargan las opciones para la asociación simple.
		List<ConstantsForm> associationSimpleValues = loadSimpleAssociationValues();
		model.addAttribute("listAssocSimpleValues", associationSimpleValues);
		model.addAttribute("mappingtslform", mappingTslForm);
		return "modal/tsl/mappingEditForm";
	}

	/**
	 * Method to load the datatable with all the mappings corresponding to the selected TSL .
	 * @param codeCountryRegion Parameter that represents a country/region identifier.
	 * @param model Parameter that represents holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(path = "/loadaddmapping", method = RequestMethod.GET)
	public String loadAddMapping(@RequestParam("id") String codeCountryRegion, Model model) {

		// Se cargan los tipos de asociaciones
		List<ConstantsForm> associationTypes = loadAssociationType();
		model.addAttribute("listTypes", associationTypes);
		// Se cargan las opciones para la asociación simple.
		List<ConstantsForm> associationSimpleValues = loadSimpleAssociationValues();
		model.addAttribute("listAssocSimpleValues", associationSimpleValues);
		MappingTslForm mappingTslForm = new MappingTslForm();
		mappingTslForm.setCodeCountryRegion(codeCountryRegion);
		model.addAttribute("mappingtslform", mappingTslForm);
		return "modal/tsl/mappingForm";

	}

	/**
	 * Method that loads the necessary information to show the confirmation modal to remove a selected mapping.
	 * @param idTslCountryRegionMapping Parameter that represents the ID of the mapping.
	 * @param codeRegionCountry Parameter that represents a country/region identifier.
	 * @param rowIndexMapping Parameter that represents the index of the row of the selected mapping.
	 * @param model Parameter that represents holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadconfirmdelete", method = RequestMethod.GET)
	public String loadConfirmDeleteMapping(@RequestParam(FIELD_ID_COUNTRY_REGION_MAPPING) Long idTslCountryRegionMapping, @RequestParam("codeRegionCountry") String codeRegionCountry, @RequestParam("rowindex") String rowIndexMapping, Model model) {
		MappingTslForm mappingTslForm = new MappingTslForm();
		mappingTslForm.setIdTslCountryRegionMapping(idTslCountryRegionMapping);
		mappingTslForm.setRowIndexMapping(rowIndexMapping);
		mappingTslForm.setCodeCountryRegion(codeRegionCountry);
		model.addAttribute("mappingtslform", mappingTslForm);
		return "modal/tsl/mappingDelete";
	}

	/**
	 * Method that loads association types.
	 * @return List of constants that represents the different association types.
	 */
	private List<ConstantsForm> loadAssociationType() {
		List<ConstantsForm> listAssociationTypes = new ArrayList<ConstantsForm>();
		// obtenemos los tipos de planificadores.
		ICAssociationTypeService cAssociationTypeService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCAssociationTypeService();
		List<CAssociationType> listCAssociationType = cAssociationTypeService.getAllAssociationType();
		for (CAssociationType associationType: listCAssociationType) {
			ConstantsForm item = new ConstantsForm(associationType.getIdAssociationType(), getConstantsValue(associationType.getTokenName()));
			listAssociationTypes.add(item);
		}

		return listAssociationTypes;
	}

	/**
	 * Method that loads the options for a simple association.
	 * @return List of constants that represents the options for a simple association.
	 */
	private List<ConstantsForm> loadSimpleAssociationValues() {

		List<ConstantsForm> result = new ArrayList<ConstantsForm>();

		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_CERT_VERSION).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_CERTVERSION)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_SUBJECT).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_SUBJECT)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_ISSUER).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_ISSUER)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_COMMON_NAME).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_COMMON_NAME)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_GIVEN_NAME).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_GIVEN_NAME)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_SURNAME).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_SURNAME)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_COUNTRY).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_COUNTRY)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_SUBJECT_SERIAL_NUMBER).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_SERIALNUMBER)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_PSEUDONYM).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_GENERAL_SUBJECT_PSEUDONYM)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_SERIAL_NUMBER).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_SERIALNUMBER)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_SIGALG_NAME).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_SIGALGNAME)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_SIGALG_OID).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_SIGALGOID)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_VALID_FROM).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_VALIDFROM)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_VALID_TO).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_VALIDTO)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_CERTPOL_INFO_OIDS).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_EXTENSION_CERTPOLINFOOIDS)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_QC_STATEMENTS_OIDS).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_EXTENSION_QCSTATOIDS)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_QC_STATEMENTS_EXTEUTYPE_OIDS).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_EXTENSION_QCSTATEUTYPEOIDS)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_SUBJECT_ALT_NAME).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_EXTENSION_SUBJECTALTNAME)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_IS_CA).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_EXTENSION_BASICCONSTRAINTISCA)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_KEY_USAGE).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_EXTENSION_KEYUSAGE)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_CRL_DISTRIBUTION_POINTS).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_EXTENSION_CRLDISTPOINT)));
		result.add(new ConstantsForm(Integer.valueOf(CertificateConstants.INFOCERT_AUTHORITY_INFORMATION_ACCESS).longValue(), Language.getResWebGeneral(IWebGeneralMessages.MAPPING_SIMPLE_EXTENSION_AIA)));
		

		return result;

	}

	/**
	 * Method that gets string constant from multilanguage file.
	 *
	 * @param key Key for getting constant string from multilanguage file.
	 * @return Constants string.
	 */
	private String getConstantsValue(String key) {
		return Language.getResPersistenceConstants(key);
	}

}
