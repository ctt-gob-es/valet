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
 * <b>File:</b><p>es.gob.valet.rest.controller.TslRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST request related to the TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/07/2018.</p>
 * @author Gobierno de España.
 * @version 1.7, 08/11/2018.
 */
package es.gob.valet.rest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.form.TslForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.CAssociationType;
import es.gob.valet.persistence.configuration.model.entity.CTslImpl;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.utils.ITslImplIdConstants;
import es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService;
import es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;

/**
 * <p>Class that manages the REST request related to the TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.7, 08/11/2018.
 */
@RestController
public class TslRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(GeneralConstants.LOGGER_NAME_VALET_LOG);

	/**
	 * Constant attribute that represents the token 'text/xml'.
	 */
	private static final String TOKEN_TEXT_XML = "text/xml";

	/**
	 * Constant attribute that represents the token 'application/pdf'.
	 */
	private static final String TOKEN_APPLICATION_PDF = "application/pdf";

	/**
	 * Constant that represents the parameter 'implTslFile'.
	 */
	private static final String FIELD_IMPL_TSL_FILE = "implTslFile";

	/**
	 * Constant that represents the parameter 'idTslData'.
	 */
	private static final String FIELD_ID_TSL = "idTslData";

	/**
	 * Constant that represents the parameter 'idTslCountryRegionMapping'.
	 */
	private static final String FIELD_ID_COUNTRY_REGION_MAPPING = "idTslCountryRegionMapping";

	/**
	 * Constant that represents the parameter 'country'.
	 */
	private static final String FIELD_COUNTRY = "country";

	/**
	 * Constant that represents the parameter 'urlTsl'.
	 */
	private static final String FIELD_URL = "urlTsl";

	/**
	 * Constant that represents the parameter 'fileDocument'.
	 */
	private static final String FIELD_FILE_DOC = "fileDocument";

	/**
	 * Constant that represents the parameter 'specification'.
	 */
	private static final String FIELD_SPECIFICATION = "specification";

	/**
	 * Constant that represents the parameter 'version'.
	 */
	private static final String FIELD_VERSION = "version";

	/**
	 * Constant that represents the extension PDF.
	 */
	private static final String EXTENSION_PDF = ".pdf";

	/**
	 * Constant that represents the extension XML.
	 */
	private static final String EXTENSION_XML = ".xml";

	/**
	 * Constant that represents the parameter 'existTsl'.
	 */
	private static final String INFO_EXIST_TSL = "existTsl";

	/**
	 * Constant that represents the parameter 'mappingIdentificator'.
	 */
	private static final String FIELD_MAPPING_ID = "mappingIdentificator";

	/**
	 * Constant that represents the parameter 'mappingValue'.
	 */
	private static final String FIELD_MAPPING_VALUE = "mappingValue";

	/**
	 * Constant that represents the key Json 'existIdentificator'.
	 */
	private static final String KEY_JS_INFO_EXIST_IDENTIFICATOR = "existIdentificator";

	/**
	 * Constant that represents the key Json 'errorUpdateTsl'.
	 */
	private static final String KEY_JS_ERROR_UPDATE_TSL = "errorUpdateTsl";

	/**
	 * Constant that represents the key Json 'errorSaveTsl'.
	 */
	private static final String KEY_JS_ERROR_SAVE_TSL = "errorSaveTsl";

	/**
	 * Method that maps the list users web requests to the controller and
	 * forwards the list of users to the view.
	 * @param input Holder object for datatable attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/tsldatatable", method = RequestMethod.GET)
	public DataTablesOutput<TslData> loadTslDataTable(@Valid DataTablesInput input) {
		ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();
		return (DataTablesOutput<TslData>) tslDataService.getAllTsl(input);

	}

	/**
	 * Method that obtains the list of available versions for the indicated specification.
	 * @param specification Specification selected in the form.
	 * @return List of versions.
	 */
	@RequestMapping(path = "/loadversions", method = RequestMethod.GET)
	public List<String> loadVersions(@RequestParam("specification") String specification) {
		List<String> versions = new ArrayList<String>();

		if (!specification.equals(String.valueOf(-1))) {
			ICTslImplService cTSLImplService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService();
			Map<String, Set<String>> res = cTSLImplService.getsTSLRelationSpecificatioAndVersion();
			versions.addAll(res.get(specification));
		}
		return versions;

	}

	/**
	 * Method that adds a new TSL.
	 * @param implTslFile Parameter that represents the file with the implementation of the TSL.
	 * @param specification  Parameter that represents the ETSI TS number specification for TSL.
	 * @param url Parameter that represents the URI where this TSL is officially located.
	 * @param version Parameter that represents the ETSI TS specification version.
	 * @return {@link DataTablesOutput<TslData>}
	 * @throws IOException If the method fails.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/savetsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslData> saveTsl(@RequestParam(FIELD_IMPL_TSL_FILE) MultipartFile implTslFile, @RequestParam(FIELD_SPECIFICATION) String specification, @RequestParam(FIELD_URL) String url, @RequestParam(FIELD_VERSION) String version) throws IOException {

		DataTablesOutput<TslData> dtOutput = new DataTablesOutput<>();

		boolean error = false;
		byte[ ] fileBytes = null;
		JSONObject json = new JSONObject();
		List<TslData> listTSL = new ArrayList<TslData>();
		TslData tslData = null;

		ITslCountryRegionService tslCountryRegionService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService();
		ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();
		ICTslImplService cTSLImplService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService();
		try {
			// comprobamos que se han indicado todos los campos obligatorios
			if (implTslFile == null || implTslFile.getSize() == 0 || implTslFile.getBytes() == null || implTslFile.getBytes().length == 0) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
				json.put(FIELD_IMPL_TSL_FILE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
				error = true;

			} else {
				fileBytes = implTslFile.getBytes();
			}

			if (specification == null || specification.equals(String.valueOf(-1))) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_SPECIFICATION));
				json.put(FIELD_SPECIFICATION + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_SPECIFICATION));
				error = true;
			}

			if (UtilsStringChar.isNullOrEmpty(version) || version.equals(String.valueOf(-1))) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VERSION));
				json.put(FIELD_VERSION + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VERSION));
				error = true;
			}

			if (!error) {
				// creamos una nueva instancia de TslData

				tslData = new TslData();

				// Construimos el InputStream asociado al array y lo parseamos
				ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes);
				// TODO hay que crear la clase para obtener información del XML.

				// TODO Para las pruebas. Borrar y asignar los valores obtenidos
				// en
				// el
				// mapeo.

				/*DATOSSS PARA CREAR UNA TSL DE ESPAÑA************************************************************/
				// LocalDateTime expDate = LocalDateTime.of(2019, 8, 10, 0, 0,
				// 0);
				// LocalDateTime issueDate = LocalDateTime.now();
				// Instant instantExp =
				// expDate.atZone(ZoneId.systemDefault()).toInstant();
				// tslData.setExpirationDate(Date.from(instantExp));
				// Instant instantIssue =
				// issueDate.atZone(ZoneId.systemDefault()).toInstant();
				// tslData.setIssueDate(Date.from(instantIssue));
				// tslData.setUriTslLocation(url);
				// tslData.setResponsible("responsable TSL Spain");
				//
				// // Obtenemos el pais de base de datos, sino se inserta el
				// // obtenido en el mapeo.
				// String codeCountry = "ES";

				/**	DATOS PARA CREAR UNA TSL DE FRANCIA***********************************************************/
				LocalDateTime expDate = LocalDateTime.of(2020, 2, 2, 0, 0, 0);
				LocalDateTime issueDate = LocalDateTime.now();
				Instant instantExp = expDate.atZone(ZoneId.systemDefault()).toInstant();
				tslData.setExpirationDate(Date.from(instantExp));
				Instant instantIssue = issueDate.atZone(ZoneId.systemDefault()).toInstant();
				tslData.setIssueDate(Date.from(instantIssue));
				tslData.setUriTslLocation(url);
				tslData.setResponsible("responsable TSL Francia");

				// Obtenemos el pais de base de datos, sino se inserta el
				// obtenido en el mapeo.
				String codeCountry = "FR";

				TslCountryRegion country = tslCountryRegionService.getTslCountryRegionByCode(codeCountry, false);
				// si country no es nulo, es que existe una TSL asociada a ese
				// pais.
				if (country != null) {
					LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_EXISTS_TSL_COUNTRY, new Object[ ] { country.getCountryRegionName() }));
					json.put(INFO_EXIST_TSL, Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_EXISTS_TSL_COUNTRY, new Object[ ] { country.getCountryRegionName() }));

					listTSL = StreamSupport.stream(tslDataService.getAllTSL().spliterator(), false).collect(Collectors.toList());
					dtOutput.setError(json.toString());

				} else {

					// se crea una nueva instancia de TslCountryRegion,
					country = new TslCountryRegion();
					// el codigo se obtiene del XML
					country.setCountryRegionCode(codeCountry);
					// el nombre de una clase d utilidades
					country.setCountryRegionName("France");
					country.setTslData(tslData);

					// se obtienen los datos del XML

					// fecha de caducidad
					tslData.setExpirationDate(Date.from(instantExp));
					// fecha de emisión
					tslData.setIssueDate(Date.from(instantIssue));
					// url, si no se ha incluido en el formulario se obtiene del
					// XML
					tslData.setUriTslLocation(url);
					// implementación TSL
					tslData.setXmlDocument(fileBytes);
					// número de secuencia
					tslData.setSequenceNumber(1);
					// si existe nueva TSL disponible
					tslData.setNewTSLAvailable("N");
					// se realiza comprobación de la especificación y la versión
					// seleccionada en el formulario, con lo obtenido del XML
					CTslImpl tslImplForm = cTSLImplService.getCTSLImplBySpecificationVersion(specification, version);
					if (tslImplForm == null) {
						tslImplForm = new CTslImpl(); // TODO preguntar JAGG
					}
					tslData.setTslImpl(tslImplForm);
					tslData.setTslCountryRegion(country);
					TslData tslNew = tslDataService.saveTSL(tslData);
					// lo añade a una lista de TSLs
					listTSL.add(tslNew);
					dtOutput.setData(listTSL);

				}

			} else {
				listTSL = StreamSupport.stream(tslDataService.getAllTSL().spliterator(), false).collect(Collectors.toList());
				dtOutput.setError(json.toString());
			}

		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_SAVE_TSL, new Object[ ] { e.getMessage() }));
			json.put(KEY_JS_ERROR_SAVE_TSL, Language.getResWebGeneral(IWebGeneralMessages.ERROR_SAVE_TSL_WEB));
			listTSL = StreamSupport.stream(tslDataService.getAllTSL().spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());
		}
		dtOutput.setData(listTSL);
		return dtOutput;
	}

	/**
	 * Method that updates a TSL.
	 * @param idTSL Parameter that represents the identifier TSL.
	 * @param url Parameter that represents the URI where this TSL is officially located.
	 * @param implTslFile  Parameter that represents the file with the implementation of the TSL.
	 * @param fileDocument Parameter that represents the file with the legible document associated to TSL.
	 * @return {@link DataTablesOutput<TslData>}
	 * @throws IOException If the method fails.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/updatetsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslData> updateTsl(@RequestParam(FIELD_ID_TSL) Long idTSL, @RequestParam(FIELD_URL) String url, @RequestParam(FIELD_IMPL_TSL_FILE) MultipartFile implTslFile, @RequestParam(FIELD_FILE_DOC) MultipartFile fileDocument) throws IOException {

		DataTablesOutput<TslData> dtOutput = new DataTablesOutput<>();

		boolean error = false;
		byte[ ] fileImplementationTsl = null;
		byte[ ] fileLegibleDocument = null;
		JSONObject json = new JSONObject();
		List<TslData> listTSL = new ArrayList<TslData>();
		TslData tsl = null;
		ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();
		ICTslImplService cTSLImplService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCTslImplService();
		try {

			// se comprueba que existe la tsl que se quiere editar.
			if (idTSL != null) {
				tsl = tslDataService.getTslDataById(idTSL, true, true);
			} else {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TSL));
				error = true;
				json.put(KEY_JS_ERROR_UPDATE_TSL, Language.getResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TSL));

			}

			if (!error) {
				// comprobamos si se ha añadido una nueva implementación

				if (implTslFile != null && implTslFile.getSize() > 0 && implTslFile.getBytes() != null && implTslFile.getBytes().length > 0) {
					// se ha añadido nueva implementación, se actualiza.
					fileImplementationTsl = implTslFile.getBytes();
					tsl.setXmlDocument(fileImplementationTsl);
					// se obtiene del XML los nuevos valores y se actualiza la
					// tsl
					tsl.setExpirationDate(new Date());
					tsl.setIssueDate(new Date());
					tsl.setResponsible("responsable actualizado");
					int seqNumber = 3;
					tsl.setSequenceNumber(seqNumber);

					CTslImpl ctslUpdate = cTSLImplService.getCTSLImpById(ITslImplIdConstants.ID_TSLIMPL_119162_020101);
					tsl.setTslImpl(ctslUpdate);

				} else {
					// incluimos la que ya existía
					tsl.setXmlDocument(tsl.getXmlDocument());
				}

				// comprobamos si se ha subido documento legible de la tsl.
				if (fileDocument != null && fileDocument.getSize() > 0) {
					fileLegibleDocument = fileDocument.getBytes();
					tsl.setLegibleDocument(fileLegibleDocument);
				} else {
					// si es nulo, comprobamos si existía ya un documento
					// legible,
					// en tal caso, se incluye en los atributos del formulario
					if (tsl.getLegibleDocument() != null) {
						tsl.setLegibleDocument(tsl.getLegibleDocument());
					}
				}

				// se actualiza la url
				tsl.setUriTslLocation(url);

				// añade la TSL a la base de datos.
				TslData tslNew = tslDataService.saveTSL(tsl);

				// lo añade a una lista de TSLs, para que salga en la datatable
				// actualizada.
				listTSL.add(tslNew);
				dtOutput.setData(listTSL);
			} else {
				// si ha ocurrido un error, se deja la lista TSL, tal como
				// estaba.
				listTSL = StreamSupport.stream(tslDataService.getAllTSL().spliterator(), false).collect(Collectors.toList());
				dtOutput.setError(json.toString());
			}

		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_SAVE_TSL, new Object[ ] { e.getMessage() }));
			json.put(KEY_JS_ERROR_SAVE_TSL, Language.getResWebGeneral(IWebGeneralMessages.ERROR_EDIT_TSL_WEB));
			listTSL = StreamSupport.stream(tslDataService.getAllTSL().spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());

		}
		dtOutput.setData(listTSL);
		return dtOutput;
	}

	/**
	 * Method that download the XML document with the implementation of the TSL.
	 * @param response Parameter that represents the response with information about file to download.
	 * @param idTsl Parameter that represents the identifier TSL.
	 * @throws IOException If the method fails.
	 */
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	@ResponseBody
	public void downloadTsl(HttpServletResponse response, @RequestParam("id") Long idTsl) throws IOException {
		ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();
		TslData tsl = tslDataService.getTslDataById(idTsl, true, false);
		byte[ ] implTsl;
		if (tsl != null) {
			implTsl = tsl.getXmlDocument();
			InputStream in = new ByteArrayInputStream(implTsl);
			response.setContentType(TOKEN_TEXT_XML);
			response.setContentLength(implTsl.length);
			response.setHeader("Content-Disposition", "attachment; filename=" + tsl.getTslCountryRegion().getCountryRegionCode() + UtilsStringChar.SYMBOL_HYPHEN_STRING + tsl.getSequenceNumber() + EXTENSION_XML);
			FileCopyUtils.copy(in, response.getOutputStream());
		}
	}

	/**
	 * Method that downloads the legible document that describes the TSL.
	 * @param response  Parameter that represents the response with information about file to download.
	 * @param idTsl Parameter that represents the identifier TSL.
	 * @throws IOException If the method fails.
	 */
	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	@ResponseBody
	public void downloadDocument(HttpServletResponse response, @RequestParam("id") Long idTsl) throws IOException {
		ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();
		TslData tsl = tslDataService.getTslDataById(idTsl, false, true);
		byte[ ] legibleDoc;
		if (tsl != null) {
			legibleDoc = tsl.getLegibleDocument();
			InputStream in = new ByteArrayInputStream(legibleDoc);
			response.setContentType(TOKEN_APPLICATION_PDF);
			response.setContentLength(legibleDoc.length);
			response.setHeader("Content-Disposition", "attachment; filename=" + tsl.getTslCountryRegion().getCountryRegionCode() + UtilsStringChar.SYMBOL_HYPHEN_STRING + tsl.getSequenceNumber() + EXTENSION_PDF);
			FileCopyUtils.copy(in, response.getOutputStream());
		}
	}

	/**
	 * Method that refreshes the screen of editing TSL without getting to persist.
	 * @param idTSL Parameter that represents the identifier TSL.
	 * @param idCountryRegion Parameter that represents a country identifier.
	 * @param implTslFile Parameter that represents the file with the implementation of the TSL.
	 * @return TslForm object with the updated data of the form.
	 * @throws IOException If the method fails.
	 */
	@JsonView(TslForm.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/updateimplfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public TslForm updateImplFile(@RequestParam(FIELD_ID_TSL) Long idTSL, @RequestParam(FIELD_COUNTRY) Long idCountryRegion, @RequestParam(FIELD_IMPL_TSL_FILE) MultipartFile implTslFile) throws IOException {
		TslForm tslForm = new TslForm();
		byte[ ] fileBytes = null;
		JSONObject json = new JSONObject();
		boolean error = false;
		// se comprueba si se ha actualizado la implementación de TSL, si es así
		// se obtiene los nuevos datos
		if (implTslFile == null || implTslFile.getSize() == 0 || implTslFile.getBytes() == null || implTslFile.getBytes().length == 0) {

			// se muestra mensaje indicando que no se ha actualizado
			LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.INFO_NOT_UPDATE_FILE_IMPL_TSL));
			// json.put(FIELD_IMPL_TSL_FILE + "_span",
			// LanguageWeb.getResWebValet(LogMessages.INFO_NOT_UPDATE_FILE_IMPL_TSL));
			// se mantiene el que tenía la tsl
		} else {

			fileBytes = implTslFile.getBytes();

			// Construimos el InputStream asociado al array y lo parseamos
			ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes);

			// primero se comprueba el país de la TSL
			// País. Se comprueba, que sea el mismo que el de la TSL que se está
			// actualizando, sino error y mensaje

			// se obtiene el id del pais, desde el mapeo... hacemos la prueba
			// con España, id=1
			Long idCountryXML = Long.valueOf(1);

			if (idCountryXML.equals(idCountryRegion)) {
				// seguimos obteniendo la información desde el fichero.

				// TODO Para las pruebas. Borrar y asignar los valores obtenidos
				// en
				// el
				// mapeo. Solo se obtendrán aquellos datos que se van a
				// visualizar
				// en la pantalla de editar.

				// Fechas de emisión y caducidad
				LocalDateTime expDate = LocalDateTime.of(2020, 10, 10, 0, 0, 0);
				LocalDateTime issueDate = LocalDateTime.now();
				Instant instantExp = expDate.atZone(ZoneId.systemDefault()).toInstant();
				Instant instantIssue = issueDate.atZone(ZoneId.systemDefault()).toInstant();

				tslForm.setExpirationDate(Date.from(instantExp).toString());
				tslForm.setIssueDate(Date.from(instantIssue).toString());

				tslForm.setImplTslFile(implTslFile);
				// número de secuencia
				tslForm.setSequenceNumber(2);

				// responsable
				tslForm.setTslResponsible("nombre responsable");

				// nombre tsl
				tslForm.setTslName("nuevo nombre");
			} else {
				// se indica que se está intentando actualizar con una TSL de
				// otro país.
				// se muestra mensaje indicando que no se ha actualizado
				error = true;
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_COUNTRY_INVALID));
				json.put(FIELD_IMPL_TSL_FILE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_COUNTRY_INVALID));
			}
			if (error) {
				tslForm.setError(json.toString());
			}
		}

		return tslForm;
	}

	/**
	 * Method to load the datatable with all the mappings corresponding to the selected TSL .
	 * @param idCountryRegion Parameter that represents a country/region identifier.
	 * @return {@link DataTablesOutput<TslCountryRegionMapping>}
	 */
	@RequestMapping(path = "/loadmapping", method = RequestMethod.GET)
	@JsonView(DataTablesOutput.View.class)
	public @ResponseBody DataTablesOutput<TslCountryRegionMapping> loadMapping(@RequestParam("id") Long idCountryRegion) {
		DataTablesOutput<TslCountryRegionMapping> dtOutput = new DataTablesOutput<TslCountryRegionMapping>();
		List<TslCountryRegionMapping> listMapping = new ArrayList<TslCountryRegionMapping>();
		ITslCountryRegionMappingService tslCountryRegionMappingService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService();
		Map<Long, String> mapAssociationType = loadAssociationType();
		if (idCountryRegion != null) {
			// obtenemos todos los mapeos de ese pais
			listMapping = tslCountryRegionMappingService.getAllMappingByIdCountry(idCountryRegion);
			List<TslCountryRegionMapping> listMappingLanguage = new ArrayList<TslCountryRegionMapping>();
			for (TslCountryRegionMapping tcrm: listMapping) {
				CAssociationType cat = tcrm.getAssociationType();
				cat.setTokenName(mapAssociationType.get(tcrm.getAssociationType().getIdAssociationType()));
				tcrm.setAssociationType(cat);
				listMappingLanguage.add(tcrm);
			}
			dtOutput.setData(listMappingLanguage);
		}

		dtOutput.setData(listMapping);
		return dtOutput;
	}

	/**
	 * Method that creates a new mapping for the indicated TSL.
	 * @param idTslCountryRegion Parameter that represents a country/region identifier.
	 * @param mappingIdentificator Parameter that represents the identificator for the logical mapping.
	 * @param mappingValue Parameter that represents the value for the mapping.
	 * @param idMappingType Parameter that represents the association type for the mapping.
	 * @return {@link DataTablesOutput<TslCountryRegionMapping>}
	 * @throws IOException If the method fails.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/savemappingtsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslCountryRegionMapping> saveMappingTsl(@RequestParam("idTslCountryRegion") Long idTslCountryRegion, @RequestParam("mappingIdentificator") String mappingIdentificator, @RequestParam("mappingValue") String mappingValue, @RequestParam("idMappingType") Long idMappingType) throws IOException {

		DataTablesOutput<TslCountryRegionMapping> dtOutput = new DataTablesOutput<>();

		boolean error = false;

		JSONObject json = new JSONObject();
		List<TslCountryRegionMapping> listTslCountryRegionMapping = new ArrayList<TslCountryRegionMapping>();
		TslCountryRegionMapping tslCountryRegionMapping = null;

		ITslCountryRegionService tslCountryRegionService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionService();
		ITslCountryRegionMappingService tslCountryRegionMappingService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService();
		ICAssociationTypeService cAssociationTypeService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCAssociationTypeService();

		if (mappingIdentificator == null || mappingIdentificator.isEmpty() || mappingIdentificator.length() != mappingIdentificator.trim().length()) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_IDENTIFICATOR));
			json.put(FIELD_MAPPING_ID + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_IDENTIFICATOR));
			error = true;
		}
		if (mappingValue == null || mappingValue.isEmpty() || mappingValue.length() != mappingValue.trim().length()) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VALUE));
			json.put(FIELD_MAPPING_VALUE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VALUE));
			error = true;
		}

		// se comprueba si existe un identificador igual
		if (tslCountryRegionMappingService.existIdentificator(mappingIdentificator, idTslCountryRegion)) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_IDENTIFICATOR_DUPLICATE));
			json.put(KEY_JS_INFO_EXIST_IDENTIFICATOR, Language.getResWebGeneral(IWebGeneralMessages.ERROR_IDENTIFICATOR_DUPLICATE));
			error = true;
		}

		if (!error) {
			// se trata de un nuevo valor
			tslCountryRegionMapping = new TslCountryRegionMapping();
			TslCountryRegion tslCountryRegion = tslCountryRegionService.getTslCountryRegionById(idTslCountryRegion, false);

			tslCountryRegionMapping.setTslCountryRegion(tslCountryRegion);
			tslCountryRegionMapping.setMappingIdentificator(mappingIdentificator);
			tslCountryRegionMapping.setMappingValue(mappingValue);

			// se obtiene el tipo de asociación
			CAssociationType associationType = cAssociationTypeService.getAssociationTypeById(idMappingType);
			tslCountryRegionMapping.setAssociationType(associationType);

			TslCountryRegionMapping tslCRMNew = tslCountryRegionMappingService.save(tslCountryRegionMapping);
			// lo añade a una lista de Mapeos
			listTslCountryRegionMapping.add(tslCRMNew);
			dtOutput.setData(listTslCountryRegionMapping);

		} else {
			listTslCountryRegionMapping = StreamSupport.stream(tslCountryRegionMappingService.getAllMappingByIdCountry(idTslCountryRegion).spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());
		}
		dtOutput.setData(listTslCountryRegionMapping);
		return dtOutput;
	}

	/**
	 * Method to modify the value of an identifier.
	 * @param idTslCountryRegionMapping  Parameter that represents the ID of the mapping.
	 * @param idTslCountryRegion  Parameter that represents the ID of the country/region.
	 * @param mappingValue Parameter that represents the new value for the mapping.
	 * @param idMappingType Parameter that represents the type of association.
	 * @return {@link DataTablesOutput<TslCountryRegionMapping>}
	 * @throws IOException If the method fails.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/modifymappingtsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslCountryRegionMapping> modifyMappingTsl(@RequestParam(FIELD_ID_COUNTRY_REGION_MAPPING) Long idTslCountryRegionMapping, @RequestParam("idTslCountryRegion") Long idTslCountryRegion, @RequestParam("mappingValue") String mappingValue, @RequestParam("idMappingType") Long idMappingType) throws IOException {

		DataTablesOutput<TslCountryRegionMapping> dtOutput = new DataTablesOutput<>();
		boolean error = false;
		JSONObject json = new JSONObject();
		List<TslCountryRegionMapping> listTslCountryRegionMapping = new ArrayList<TslCountryRegionMapping>();
		TslCountryRegionMapping tslCountryRegionMapping = null;

		ICAssociationTypeService associationTypeService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCAssociationTypeService();
		ITslCountryRegionMappingService tslCountryRegionMappingService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService();
		if (mappingValue == null || mappingValue.isEmpty() || mappingValue.length() != mappingValue.trim().length()) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VALUE));
			json.put(FIELD_MAPPING_VALUE + "_spanEdit", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VALUE));
			error = true;
		}

		if (idTslCountryRegionMapping == null) {
			error = true;
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_EDIT_MAPPING));
		}
		// se obtiene el campo
		tslCountryRegionMapping = tslCountryRegionMappingService.getTslCountryRegionMappingById(idTslCountryRegionMapping);
		if (tslCountryRegionMapping == null) {
			error = true;
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_EDIT_MAPPING));
		}

		if (!error) {
			tslCountryRegionMapping.setMappingValue(mappingValue);
			// se obtiene el tipo de asociación
			CAssociationType associationType = associationTypeService.getAssociationTypeById(idMappingType);
			tslCountryRegionMapping.setAssociationType(associationType);

			TslCountryRegionMapping tslCRMNew = tslCountryRegionMappingService.save(tslCountryRegionMapping);

			// lo añade a una lista de Mapeos
			listTslCountryRegionMapping.add(tslCRMNew);

		} else {
			listTslCountryRegionMapping = StreamSupport.stream(tslCountryRegionMappingService.getAllMappingByIdCountry(idTslCountryRegion).spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());
		}

		dtOutput.setData(listTslCountryRegionMapping);
		return dtOutput;
	}

	/**
	 * Method to remove a mappings.
	 * @param idTslCountryRegionMapping Parameter that represents ID of mapping to delete.
	 * @param indexParam Parameter that represents the index of the row of the selected mapping.
	 * @return String that represents the index of the deleted row.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "/deletemappingbyid", method = RequestMethod.POST)
	public String deleteMappingById(@RequestParam(FIELD_ID_COUNTRY_REGION_MAPPING) Long idTslCountryRegionMapping, @RequestParam("rowIndexMapping") String indexParam) {
		String index = indexParam;
		ITslCountryRegionMappingService tslCountryRegionMappingService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService();

		try {
			tslCountryRegionMappingService.deleteTslCountryRegionMapping(idTslCountryRegionMapping);
		} catch (Exception e) {
			index = "-1";
		}
		return index;
	}

	/**
	 * Method that assigns the removal request of the datatable TSL to the controller and performs the elimination of the TSL identified by its id.
	 * @param idTslData Identifier of the TSL to be deleted.
	 * @param indexParam Row index of the datatable.
	 * @return String that represents the index of the deleted row.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/deletetsl", method = RequestMethod.POST)
	public String deleteTsl(@RequestParam("id") Long idTslData, @RequestParam("index") String indexParam) {
		String index = indexParam;
		ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();
		try {
			tslDataService.deleteTslData(idTslData);
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_SAVE_TSL, new Object[ ] { e.getMessage() }));
			index = "-1";
		}

		return index;
	}

	/**
	 * Method to load the types of associations in the table by applying multi-language.
	 * @return Map that contains the types of associations with multi-language.
	 */
	private Map<Long, String> loadAssociationType() {
		Map<Long, String> mapAssociationType  = new HashMap<Long, String>();
		// obtenemos los tipos de planificadores.
		ICAssociationTypeService cAssociationTypeService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCAssociationTypeService();
		List<CAssociationType> listCAssociationType = cAssociationTypeService.getAllAssociationType();
		for (CAssociationType associationType: listCAssociationType) {
			mapAssociationType.put(associationType.getIdAssociationType(), Language.getResPersistenceConstants(associationType.getTokenName()));
		}
		return mapAssociationType;

	}


}