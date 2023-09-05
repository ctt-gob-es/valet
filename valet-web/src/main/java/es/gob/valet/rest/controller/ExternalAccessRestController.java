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
 * <b>File:</b><p>es.gob.valet.rest.controller.ApplicationRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST request related to the Applications administration and JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>11/12/2018.</p>
 * @author Gobierno de España.
 * @version 2.0, 26/07/2023.
 */
package es.gob.valet.rest.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.form.ExternalAccessForm;
import es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO;
import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.service.ifaces.IExternalAccessService;
import es.gob.valet.service.impl.ExternalAccessService;

/**
 * <p>
 * Class that manages the REST request related to the Applications
 * administration and JSON communication.
 * </p>
 * <b>Project:</b>
 * <p>
 * Platform for detection and validation of certificates recognized in European
 * TSL.
 * </p>
 * 
 * @version 1.1, 08/08/2023.
 */
@RestController
public class ExternalAccessRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ExternalAccessRestController.class);

	/**
	 * Attribute that represents the value search entered for the user in the input
	 * search.
	 */
	private static final String REQ_PARAM_VALUE_SEARCH = "valueSearch";

	/**
	 * Attribute that represents the injected interface that provides CRUD
	 * operations for the persistence.
	 */
	@Autowired
	private IExternalAccessService iExternalAccessService;

	/**
	 * Method that search in tree value enter for user in searching.
	 * 
	 * @param valueSearch parameter that contain value enter for user in searching.
	 * @param response    parameter that represents posibility errors in process.
	 * @return
	 * @return tree with nodes found.
	 */

	@RequestMapping(value = "/searchExternalAccess", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public DataTablesOutput<ExternalAccess> searchExternalAccess(@RequestBody ExternalAccessForm externalAccessForm,
			HttpServletResponse response) {
		// IExternalAccessService appService =
		// ManagerPersistenceConfigurationServices.getInstance().getExternalAccessValetService();
		return null;
	}

	/**
	 * Method to load the datatable with all the mappings corresponding to the selected TSL .
	 * @param idCountryRegion Parameter that represents a country/region identifier.
	 * @return {@link DataTablesOutput<TslCountryRegionMapping>}
	 */
	@RequestMapping(value = "/externalAccessDatatable", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<ExternalAccessDTO> loadExternalAccessBySearch(@RequestBody ExternalAccessForm externalAccessForm) {
		DataTablesOutput<ExternalAccessDTO> dtOutput = new DataTablesOutput<ExternalAccessDTO>();
		
		List<ExternalAccessDTO> listExternalAccess = new ArrayList<ExternalAccessDTO>();
		Date toDate =null;
		Date fromDate=null;
		ExternalAccess externalAccess = new ExternalAccess();

		try {
			
			if((externalAccessForm.getDateFrom()!=null && externalAccessForm.getDateFrom()!="")) {
				fromDate = UtilsDate.transformDate(externalAccessForm.getDateFrom(), UtilsDate.FORMAT_DATE_TIME_STANDARD);
			}
			if((externalAccessForm.getDateTo()!=null && externalAccessForm.getDateTo()!="")) {
				 toDate = UtilsDate.transformDate(externalAccessForm.getDateTo(), UtilsDate.FORMAT_DATE_TIME_STANDARD);
			}
		
			if(toDate!=null && fromDate!=null && !fromDate.before(toDate)) {
				throw new Exception("La fecha 'desde' debe ser anterior a la fecha 'hasta'.");
			}
			if(externalAccessForm.getStateConn() !=null) {
				externalAccess.setStateConn(externalAccessForm.getStateConn());
			}
			if(externalAccessForm.getUrl() !=null || !externalAccessForm.getUrl().isEmpty()) {
				externalAccess.setUrl(externalAccessForm.getUrl());
			}
			
			listExternalAccess = iExternalAccessService.getAllListDTOByFilter(externalAccess,fromDate,toDate);
			
			dtOutput.setData(listExternalAccess);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			dtOutput.setError(e.getMessage());
		}finally {
			return dtOutput;

		}
	
	}

	/**
	 * Method that maps the list applications to the controller and forwards the
	 * list of applications to the view.
	 * 
	 * @param input Holder object for datatable attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/externalAccessDatatableInicial", method = RequestMethod.GET)
	public DataTablesOutput<ExternalAccess> loadExternalAccessInicial(@NotEmpty DataTablesInput input) {
		DataTablesOutput<ExternalAccess> tablaVacia = new DataTablesOutput<ExternalAccess>();
		return tablaVacia;
	}

	
	@RequestMapping(value = "/tryConn", method = RequestMethod.POST)
	public ExternalAccessDTO tryConn(@RequestParam(value = "id") Long id, Model model) {
		ExternalAccess externalAccess = iExternalAccessService.getUrlDataById(id);
		ExternalAccessDTO externalDTO =  new ExternalAccessDTO();
		try {
			externalAccess = iExternalAccessService.getExternalAccessAndTestConn(externalAccess.getUrl(), externalAccess.getOriginUrl(), null);
			
			externalDTO.setIdUrl(externalAccess.getIdUrl());
			externalDTO.setUrl(externalAccess.getUrl());
			externalDTO.setStateConn(externalAccess.getStateConn());
			externalDTO.setMessageError(iExternalAccessService.getMessageErrorValue());
		}catch (Exception e) {
			//TODO: rellenar con error en caso de que algo vaya mal
			LOGGER.error(e.getCause());
		}
		return externalDTO;

	}

	
}
