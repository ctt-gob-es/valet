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
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 25 jun. 2018.
 */
package es.gob.valet.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import es.gob.valet.form.MappingTslForm;
import es.gob.valet.form.TslForm;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslValet;
import es.gob.valet.service.ICTslImplService;
import es.gob.valet.service.ITslCountryRegionMappingService;
import es.gob.valet.service.ITslCountryRegionService;
import es.gob.valet.service.ITslValetService;

/** 
 * <p>Class that manages the requests related to the TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 25 jun. 2018.
 */
@Controller
public class TslController {

	/**
	 * Attribute that represents the service object for acceding the repository.
	 */
	@Autowired
	private ITslValetService tslService;

	/**
	 * Attribute that represents the service object for acceding the repository. 
	 */
	@Autowired
	private ICTslImplService cTSLImplService;
	
	/**
	 * Attribute that represents the service object for acceding the repository. 
	 */
	@Autowired
	private ITslCountryRegionService tslCountryRegionService;
	
	/**
	 * Attribute that represents the service object for acceding the repository. 
	 */
	@Autowired
	private ITslCountryRegionMappingService tslCountryRegionMappingService;

	/**
	 * Attribute that represents the service object for acceding the repository
	 */

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
	 * @throws IOException 
	 */

	@RequestMapping(value = "addTsl")
	public String addTsl(Model model) throws IOException {
		List<String> listVersions = new ArrayList<String>();
		List<String> listSpecifications = cTSLImplService.getAllSpecifications();
	
		TslForm tslForm = new TslForm();
		model.addAttribute("tslform",tslForm);
		model.addAttribute("versions", listVersions);
		model.addAttribute("listSpecifications", listSpecifications);
		

		return "modal/tsl/tslForm.html";
	}

	/**
	 * Method that maps the editing of TSL data to the controller and sets the backing form.
	 * 
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "edittsl", method = RequestMethod.POST)
	public String editTsl(@RequestParam("id") Long idTslValet, Model model) {
		TslValet tslValet = tslService.getTslValetById(idTslValet);
		TslForm tslForm = new TslForm();
		tslForm.setIdTslValet(idTslValet);
		tslForm.setCountryName(tslValet.getCountry().getCountryRegionName());
		tslForm.setCountry(tslValet.getCountry().getIdTslCountryRegion());
		tslForm.setAlias(tslValet.getAlias());
		tslForm.setTslName("prueba nombre tsl");
		tslForm.setTslResponsible("prueba nombre responsable");
		
		//Se comprueba si tiene documento legible
		if(tslValet.getLegibleDocument()!=null){
			tslForm.setIsLegible(true);
		}else{
			tslForm.setIsLegible(false);
		}
		
		
		Date issueDate = tslValet.getIssueDate();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		tslForm.setIssueDate(sdf.format(issueDate));		
		tslForm.setExpirationDate(sdf.format(tslValet.getExpirationDate()));
		tslForm.setUrlTsl(tslValet.getUriTslLocation());
		
		
		tslForm.setSequenceNumber(tslValet.getSequenceNumber());
		model.addAttribute("isLegible", tslForm.getIsLegible());
		model.addAttribute("tslform", tslForm);
		return "modal/tsl/tslEditForm";
	}

	
	@RequestMapping(value = "/updateimpl", method = RequestMethod.POST)
	public String updateImplementationFile(@RequestParam("id") Long idTsl,  Model model){
			TslForm tslForm = new TslForm();
			tslForm.setIdTslValet(idTsl);
			model.addAttribute("tslform", tslForm);
			return "modal/tsl/tslUpdateImplForm";
	}
	
	
	/**
	 * Method that loads a datatable with the mappings for the TSL of the indicated country 
	 * 
	 * @param idCountryRegion Country idientifier.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadmappingdatatable", method = RequestMethod.GET)
	public String loadMappingDataTable(@RequestParam("idTslCountryRegion") Long idCountryRegion, Model model){
			MappingTslForm mappingTslForm = new MappingTslForm();
			MappingTslForm mappingTslEditForm = new MappingTslForm();
			mappingTslForm.setIdTslCountryRegion(idCountryRegion);
			mappingTslForm.setNameCountryRegion(tslCountryRegionService.getNameCountryRegionById(idCountryRegion));
			model.addAttribute("mappingtslform", mappingTslForm);
			model.addAttribute("mappingedittslform", mappingTslEditForm);
			return "fragments/tslmapping.html";
	}
	
	

//	@RequestMapping(value = "addmappingtsl")
//	public String addMappingTsl(Model model) throws IOException {
//	
//		MappingTslForm mappingTslForm = new MappingTslForm();
//	//	mappingTslForm.setIdTslValet(idTslValet);
//		//mappingTslForm.setIdTslCountryRegion(idTslCountryRegion);
//		model.addAttribute("mappingtslform",mappingTslForm);
//		return "modal/tsl/mappingTslForm";
//	}

}
