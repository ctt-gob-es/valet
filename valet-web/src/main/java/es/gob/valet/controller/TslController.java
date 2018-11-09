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
 * @version 1.7, 08/11/2018.
 */
package es.gob.valet.controller;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.form.ConstantsForm;
import es.gob.valet.form.MappingTslForm;
import es.gob.valet.form.TslForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.CAssociationType;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService;
import es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;

/**
 * <p>Class that manages the requests related to the TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 *  @version 1.7, 08/11/2018.
 */
@Controller
public class TslController {

	/**
	 * Constant that represents the parameter 'idTslCountryRegionMapping'.
	 */
	private static final String FIELD_ID_COUNTRY_REGION_MAPPING = "idTslCountryRegionMapping";
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
		List<String> listVersions = new ArrayList<String>();

		ICTslImplService cTSLImplService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService();
		List<String> listSpecifications = cTSLImplService.getAllSpecifications();

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
		ITslDataService tslService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();
		TslData tslData = tslService.getTslDataById(idTslData, false, false);
		TslForm tslForm = new TslForm();
		tslForm.setIdTslData(idTslData);
		tslForm.setCountryName(tslData.getTslCountryRegion().getCountryRegionName());
		tslForm.setCountry(tslData.getTslCountryRegion().getIdTslCountryRegion());
		tslForm.setTslName("prueba nombre tsl");
		tslForm.setTslResponsible("prueba nombre responsable");

		// Se comprueba si tiene documento legible
		if (tslData.getLegibleDocument() != null) {
			tslForm.setIsLegible(true);
		} else {
			tslForm.setIsLegible(false);
		}

		Date issueDate = tslData.getIssueDate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		tslForm.setIssueDate(sdf.format(issueDate));
		tslForm.setExpirationDate(sdf.format(tslData.getExpirationDate()));
		tslForm.setUrlTsl(tslData.getUriTslLocation());

		tslForm.setSequenceNumber(tslData.getSequenceNumber());
		model.addAttribute("isLegible", tslForm.getIsLegible());
		model.addAttribute("tslform", tslForm);
		return "modal/tsl/tslEditForm";
	}

	/**
	 * Method that loads a datatable with the mappings for the TSL of the indicated country.
	 * @param idCountryRegion Parameter that represents a country identifier.
	 * @param model Parameter that represents holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadmappingdatatable", method = RequestMethod.GET)
	public String loadMappingDataTable(@RequestParam("idTslCountryRegion") Long idCountryRegion, Model model) {
		MappingTslForm mappingTslForm = new MappingTslForm();
		MappingTslForm mappingTslEditForm = new MappingTslForm();
		mappingTslForm.setIdTslCountryRegion(idCountryRegion);

		ITslCountryRegionService tslCountryRegionService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService();
		mappingTslForm.setNameCountryRegion(tslCountryRegionService.getNameCountryRegionById(idCountryRegion));
		model.addAttribute("mappingtslform", mappingTslForm);
		model.addAttribute("mappingedittslform", mappingTslEditForm);

		//se cargan los tipos de asociaciones
		List<ConstantsForm> associationTypes = loadAssociationType();
		model.addAttribute("listTypes", associationTypes);
		return "fragments/tslmapping.html";
	}

	/**
	 * Method that loads the mapping by ID of TslCountryRegionMapping.
	 * @param idTslCountryRegionMapping Parameter that represents the ID of the mapping.
	 * @param model Parameter that represents holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadmappingbyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String loadMappingById(@RequestParam(FIELD_ID_COUNTRY_REGION_MAPPING) Long idTslCountryRegionMapping, Model model) {
		ITslCountryRegionMappingService tslCountryRegionMappingService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService();
		TslCountryRegionMapping tslCRM = tslCountryRegionMappingService.getTslCountryRegionMappingById(idTslCountryRegionMapping);
		MappingTslForm mappingTslForm = new MappingTslForm();
		mappingTslForm.setIdTslCountryRegionMapping(idTslCountryRegionMapping);
		mappingTslForm.setIdTslCountryRegion(tslCRM.getTslCountryRegion().getIdTslCountryRegion());
		mappingTslForm.setMappingIdentificator(tslCRM.getMappingIdentificator());
		mappingTslForm.setMappingValue(tslCRM.getMappingValue());
		mappingTslForm.setIdMappingType(tslCRM.getAssociationType().getIdAssociationType());

		//se cargan los tipos de asociaciones
		List<ConstantsForm> associationTypes = loadAssociationType();
		model.addAttribute("listTypes", associationTypes);
		model.addAttribute("mappingtslform", mappingTslForm);
		return "modal/tsl/mappingEditForm";
	}

	/**
	 * Method to load the datatable with all the mappings corresponding to the selected TSL .
	 * @param idCountryRegion Parameter that represents a country/region identifier.
	 * @param model Parameter that represents holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(path = "/loadaddmapping", method = RequestMethod.GET)
	public String loadAddMapping(@RequestParam("id") Long idCountryRegion, Model model) {

		//se cargan los tipos de asociaciones
		List<ConstantsForm> associationTypes = loadAssociationType();
		model.addAttribute("listTypes", associationTypes);
		MappingTslForm mappingTslForm = new MappingTslForm();
		mappingTslForm.setIdTslCountryRegion(idCountryRegion);
		model.addAttribute("mappingtslform", mappingTslForm);
		return "modal/tsl/mappingForm";

	}

	/**
	 * Method that loads the necessary information to show the confirmation modal to remove a selected mapping.
	 * @param idTslCountryRegionMapping Parameter that represents the ID of the mapping.
	 * @param rowIndexMapping Parameter that represents the index of the row of the selected mapping.
	 * @param model Parameter that represents holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadconfirmdelete", method = RequestMethod.GET)
	public String loadConfirmDeleteMapping(@RequestParam(FIELD_ID_COUNTRY_REGION_MAPPING) Long idTslCountryRegionMapping, @RequestParam("rowindex") String rowIndexMapping, Model model) {
		MappingTslForm mappingTslForm = new MappingTslForm();
		mappingTslForm.setIdTslCountryRegionMapping(idTslCountryRegionMapping);
		mappingTslForm.setRowIndexMapping(rowIndexMapping);
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
	 * Method that gets string constant from multilanguage file.
	 *
	 * @param key Key for getting constant string from multilanguage file.
	 * @return Constants string.
	 */
	private String getConstantsValue(String key) {
		return Language.getResPersistenceConstants(key);
	}

}
