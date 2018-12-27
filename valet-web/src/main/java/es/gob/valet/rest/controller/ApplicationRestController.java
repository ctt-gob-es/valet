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
 * @version 1.11, 27/12/2018.
 */
package es.gob.valet.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.form.ApplicationForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.application.exceptions.ApplicationCacheException;
import es.gob.valet.persistence.configuration.model.entity.ApplicationValet;
import es.gob.valet.persistence.configuration.services.ifaces.IApplicationValetService;

/**
 * <p>Class that manages the REST request related to the Applications administration and JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.11, 27/12/2018.
 */
@RestController
public class ApplicationRestController {

	/**
	 * Constant that represents the parameter 'idApplication'.
	 */
	private static final String FIELD_ID_APPLICATION = "idApplication";

	/**
	 * Constant that represents the parameter 'ind'.
	 */
	private static final String FIELD_ROW_INDEX_APPLICATION = "rowIndexApplication";

	/**
	 * Constant that represents the parameter 'identifier'.
	 */
	private static final String FIELD_IDENTIFIER = "identifier";

	/**
	 * Constant that represents the parameter 'name'.
	 */
	private static final String FIELD_NAME = "name";

	/**
	 * Constant that represents the parameter 'responsibleName'.
	 */
	private static final String FIELD_RESP_NAME = "responsibleName";

	/**
	 * Constant that represents the parameter 'responsibleSurnames'.
	 */
	private static final String FIELD_RESP_SURNAMES = "responsibleSurnames";

	/**
	 * Constant that represents the parameter 'responsiblePhone'.
	 */
	private static final String FIELD_RESP_PHONE = "responsiblePhone";

	/**
	 * Constant that represents the parameter 'responsibleMail'.
	 */
	private static final String FIELD_RESP_MAIL = "responsibleMail";
	/**
	 * Constant that represents the key Json 'errorSaveApp'.
	 */
	private static final String KEY_JS_ERROR_SAVE_APP = "errorSaveApp";

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(ApplicationRestController.class);

	/**
	 * Method that maps the list applications to the controller and forwards the list of applications to the view.
	 * @param input Holder object for datatable attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/appdatatable", method = RequestMethod.GET)
	public DataTablesOutput<ApplicationValet> loadApplications(@Valid DataTablesInput input) {
		IApplicationValetService appService = ManagerPersistenceConfigurationServices.getInstance().getApplicationValetService();
		return (DataTablesOutput<ApplicationValet>) appService.getAllApplication(input);
	}

	/**
	 * Method that maps the save application to the controller and saves it
	 * in the persistence.
	 *
	 * @param appForm
	 *            Object that represents the backing application form.
	
	 * @return {@link DataTablesOutput<ApplicationValet>}
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/saveapp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody DataTablesOutput<ApplicationValet> saveApp(@RequestBody ApplicationForm appForm) {
		DataTablesOutput<ApplicationValet> dtOutput = new DataTablesOutput<>();
		IApplicationValetService appService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getApplicationValetService();
		ApplicationValet appValet = null;
		JSONObject json = new JSONObject();
	
		// se comprueba si se está editando o creando una nueva aplicación
		if (appForm.getIdApplication() != null) {
			// se está editando
			appValet = appService.getApplicationById(appForm.getIdApplication());
		} else {
			// se crea una nueva aplicación
			appValet = new ApplicationValet();
		}
		boolean error = false;
		List<ApplicationValet> listNewApp = new ArrayList<ApplicationValet>();
		// validaciones de parámetros de entrada
		if (UtilsStringChar.isNullOrEmpty(appForm.getIdentifier())) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_IDENTIFIER));
			json.put(FIELD_IDENTIFIER + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_IDENTIFIER));
			error = true;
		} else if(isDuplicateIdentifier(appForm.getIdentifier(), appValet.getIdentificator())){
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_DUPLICATE_IDENTIFIER));
			json.put(FIELD_IDENTIFIER + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_DUPLICATE_IDENTIFIER));
			error = true;
		}				
		if (UtilsStringChar.isNullOrEmpty(appForm.getName())) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_NAME_APP));
			json.put(FIELD_NAME + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_NAME_APP));
			error = true;
		}
		if (UtilsStringChar.isNullOrEmpty(appForm.getResponsibleName())) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_RESP_NAME));
			json.put(FIELD_RESP_NAME + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_RESP_NAME));
			error = true;
		}
		if (UtilsStringChar.isNullOrEmpty(appForm.getResponsibleSurnames())) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_RESP_SURNAMES));
			json.put(FIELD_RESP_SURNAMES + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_RESP_SURNAMES));
			error = true;
		}
		if (UtilsStringChar.isNullOrEmpty(appForm.getResponsibleMail())) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_RESP_MAIL));
			json.put(FIELD_RESP_MAIL + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_RESP_MAIL));
			error = true;
		}
		if (UtilsStringChar.isNullOrEmpty(appForm.getResponsibleMail())) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_RESP_PHONE));
			json.put(FIELD_RESP_PHONE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_RESP_PHONE));
			error = true;
		}

		if (!error) {
			appValet.setIdentificator(appForm.getIdentifier());
			appValet.setName(appForm.getName());
			appValet.setResponsibleName(appForm.getResponsibleName());
			appValet.setResponsibleSurnames(appForm.getResponsibleSurnames());
			appValet.setResponsibleMail(appForm.getResponsibleMail());
			appValet.setResponsiblePhone(appForm.getResponsiblePhone());

			ApplicationValet newAppValet = null;
			try {
				newAppValet = appService.saveApplicationValet(appValet);
				// se actualiza la caché
				ConfigurationCacheFacade.applicationAddUpdateApplication(appValet);
				listNewApp.add(newAppValet);
				dtOutput.setData(listNewApp);
			}

			catch (ApplicationCacheException e) {
				LOGGER.error(Language.getResWebGeneral(e.getErrorDescription()));
				json.put(KEY_JS_ERROR_SAVE_APP, e.getErrorDescription());
				listNewApp = StreamSupport.stream(appService.getAllApplication().spliterator(), false).collect(Collectors.toList());
				dtOutput.setError(json.toString());
			}
		} else {

			listNewApp = StreamSupport.stream(appService.getAllApplication().spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());
		}

		return dtOutput;

	}

	/**
	 * Method to remove an application.
	 * @param idApplication Parameter that represents ID of application.
	 * @param index Parameter that represents the index of the row of the selected application.
	 * @return String that represents the index of the deleted row.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "/deleteapp", method = RequestMethod.POST)
	public String deleteApplicationById(@RequestParam(FIELD_ID_APPLICATION) Long idApplication, @RequestParam(FIELD_ROW_INDEX_APPLICATION) String index) {

		String result = index;
		try {
			ManagerPersistenceConfigurationServices.getInstance().getApplicationValetService().deleteApplicationValet(idApplication);
			// se elimina también de la caché
			ConfigurationCacheFacade.applicationRemoveApplication(idApplication);
		} catch (Exception e) {
			result = "-1";
		}
		return result;
	}
	
	/**
	 * Method that validates if there is an application with the same identifier.
	 * 
	 * @param newIdentifier Parameter that represents the new identifier of the application.
	 * @param oldIdentifier Parameter that represents the identifier of the application that is being edited.
	 * @return true, if there is a registered application with the same identifier.
	 */
	private boolean isDuplicateIdentifier(String newIdentifier, String oldIdentifier){
		boolean result = false;
		IApplicationValetService appService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getApplicationValetService();
		//si se está editando la aplicación y se ha cambiado el identificador, o si se crea nueva aplicación, hay que comprobar que no exista una aplicación con el mismo identificador.
		if((oldIdentifier!=null && !newIdentifier.equals(oldIdentifier)) || oldIdentifier == null){
			if(appService.getApplicationByIdentificator(newIdentifier) != null){
			result = true;
			}
		}
		return result;
		
	}

}
