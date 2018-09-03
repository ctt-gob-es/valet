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
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17 jul. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 17 jul. 2018.
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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import es.gob.valet.commons.utilidades.GeneralConstants;
import es.gob.valet.commons.utilidades.UtilsResources;
import es.gob.valet.form.MappingTslForm;
import es.gob.valet.form.TslForm;
import es.gob.valet.i18n.LanguageWeb;
import es.gob.valet.i18n.LogMessages;
import es.gob.valet.persistence.configuration.model.entity.CTslImpl;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslValet;
import es.gob.valet.service.ICTslImplService;
import es.gob.valet.service.ITslCountryRegionMappingService;
import es.gob.valet.service.ITslCountryRegionService;
import es.gob.valet.service.ITslValetService;

/** 
 * <p>Class that manages the REST request related to the TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 17 jul. 2018.
 */
@RestController
public class TslRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(GeneralConstants.LOGGER_NAME_VALET_LOG);

	/**
	 * Attribute that represents the service object for accessing the
	 * repository.
	 */
	@Autowired
	private ITslValetService tslValetService;

	@Autowired
	private ICTslImplService cTSLImplService;

	@Autowired
	private ITslCountryRegionService tslCountryRegionService;

	@Autowired
	private ITslCountryRegionMappingService tslCountryRegionMappingService;

	private static final String FIELD_IMPL_TSL_FILE = "implTslFile";
	private static final String FIELD_ID_TSL = "idTslValet";
	private static final String FIELD_COUNTRY = "country";
	private static final String FIELD_URL = "urlTsl";
	private static final String FIELD_FILE_DOC = "fileDocument";
	private static final String FIELD_SPECIFICATION = "specification";
	private static final String FIELD_VERSION = "version";
	private static final String EXTENSION_PDF = ".pdf";
	private static final String EXTENSION_XML = ".xml";
	private static final String INFO_EXIST_TSL = "existTsl";
	private static final String FIELD_MAPPING_ID = "mappingIdentificator";
	private static final String FIELD_MAPPING_VALUE = "mappingValue";
	private static final String KEY_JS_INFO_EXIST_IDENTIFICATOR = "existIdentificator";
	private static final String KEY_JS_ERROR_UPDATE_TSL = "errorUpdateTsl";
	private static final String KEY_JS_ERROR_SAVE_TSL = "errorSaveTsl";

	/**
	 * Method that maps the list users web requests to the controller and
	 * forwards the list of users to the view.
	 * 
	 * @param input
	 *            Holder object for datatable attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/tsldatatable", method = RequestMethod.GET)
	public DataTablesOutput<TslValet> loadTslDataTable(@Valid DataTablesInput input) {
		return (DataTablesOutput<TslValet>) tslValetService.findAllTsl(input);

	}

	/**
	 * Method that obtains the list of available versions for the indicated specification.
	 * 
	 * @param specification Specification selected in the form.
	 * @return List of versions.
	 */
	@RequestMapping(path = "/loadversions", method = RequestMethod.GET)
	public List<String> loadVersions(@RequestParam("specification") String specification) {
		List<String> versions = new ArrayList<String>();

		if (!specification.equals(String.valueOf(-1))) {
			Map<String, Set<String>> res = cTSLImplService.getsTSLRelationSpecificatioAndVersion();
			versions.addAll(res.get(specification));
		}
		return versions;

	}

	/**
	 * Method that add a new TSL.
	 * 
	 * @param idTSL Identifier TSL
	 * @param file 
	 * @param specification
	 * @param url
	 * @param version
	 * @return
	 * @throws IOException
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/savetsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslValet> saveTsl(@RequestParam(FIELD_IMPL_TSL_FILE) MultipartFile implTslFile, @RequestParam(FIELD_SPECIFICATION) String specification, @RequestParam(FIELD_URL) String url, @RequestParam(FIELD_VERSION) String version) throws IOException {

		DataTablesOutput<TslValet> dtOutput = new DataTablesOutput<>();

		boolean error = false;
		byte[ ] fileBytes = null;
		JSONObject json = new JSONObject();
		List<TslValet> listTSL = new ArrayList<TslValet>();
		TslValet tslValet = null;

		try {
			// comprobamos que se han indicado todos los campos obligatorios
			if (implTslFile == null || implTslFile.getSize() == 0 || implTslFile.getBytes() == null || implTslFile.getBytes().length == 0) {
				LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
				json.put(FIELD_IMPL_TSL_FILE + "_span", LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
				error = true;

			} else {
				fileBytes = implTslFile.getBytes();

			}

			if (specification == null || specification.equals(String.valueOf(-1))) {
				LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_SPECIFICATION));
				json.put(FIELD_SPECIFICATION + "_span", LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_SPECIFICATION));
				error = true;
			}

			if (version == null || version.equals("") || version.equals(String.valueOf(-1))) {
				LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_VERSION));
				json.put(FIELD_VERSION + "_span", LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_VERSION));
				error = true;
			}

			if (!error) {
				// creamos una nueva instancia de TslValet

				tslValet = new TslValet();

				// Construimos el InputStream asociado al array y lo parseamos
				ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes);
				// TODO hay que crear la clase para obtener información del XML.

				// TODO Para las pruebas. Borrar y asignar los valores obtenidos
				// en
				// el
				// mapeo.

				/** DATOSSS PARA CREAR UNA TSL DE ESPAÑA************************************************************/
				/*LocalDateTime expDate = LocalDateTime.of(2019, 8, 10, 0, 0, 0);
				LocalDateTime issueDate = LocalDateTime.now();
				Instant instantExp = expDate.atZone(ZoneId.systemDefault()).toInstant();
				tslValet.setExpirationDate(Date.from(instantExp));
				Instant instantIssue = issueDate.atZone(ZoneId.systemDefault()).toInstant();
				tslValet.setIssueDate(Date.from(instantIssue));
				tslValet.setUriTslLocation(url);
				tslValet.setResponsible("responsable TSL Spain");
				
				// Obtenemos el pais de base de datos, sino se inserta el
				// obtenido en el mapeo.
				TslCountryRegion country = tslCountryRegionService.getTslCountryRegionById(Long.valueOf(1));*/

				/*	DATOS PARA CREAR UNA TSL DE FRANCIA************************************************************/
				LocalDateTime expDate = LocalDateTime.of(2020, 2, 10, 0, 0, 0);
				LocalDateTime issueDate = LocalDateTime.now();
				Instant instantExp = expDate.atZone(ZoneId.systemDefault()).toInstant();
				Instant instantIssue = issueDate.atZone(ZoneId.systemDefault()).toInstant();

				// el país lo obtenemos del XML, si no existiera se insertaría
				// el
				// nuevo pais en bd? //TODO preguntar a JAGG
				TslCountryRegion country = tslCountryRegionService.getTslCountryRegionById(Long.valueOf(2));
				tslValet.setResponsible("responsable TSL France");

				// se comprueba si ya existe una TSL del mismo país
				if (tslValetService.findByCountry(country) != null) {
					LOGGER.error(LanguageWeb.getFormatResWebValet(LogMessages.ERROR_EXISTS_TSL_COUNTRY, new Object[ ] { country.getCountryRegionName() }));
					json.put(INFO_EXIST_TSL, LanguageWeb.getFormatResWebValet(LogMessages.ERROR_EXISTS_TSL_COUNTRY, new Object[ ] { country.getCountryRegionName() }));

					listTSL = StreamSupport.stream(tslValetService.getAllTSL().spliterator(), false).collect(Collectors.toList());
					dtOutput.setError(json.toString());
				} else {

					tslValet.setCountry(country);
					// construimos el alias de la TSL, nos servirá como nombre
					// para
					// las
					// descargas de implementación y de documento legible.
					// Formato
					// (CodigoPais-Secuencia-VVersion)
					Integer seqNum = 1;

					String alias = country.getCountryRegionCode() + "-" + String.valueOf(seqNum) + "-V" + version;
					tslValet.setAlias(alias);
					// fecha de caducidad
					tslValet.setExpirationDate(Date.from(instantExp));
					// fecha de emisión
					tslValet.setIssueDate(Date.from(instantIssue));
					// url, si no se ha incluido en el formulario se obtiene del
					// XML
					tslValet.setUriTslLocation(url);
					// implementación TSL
					tslValet.setXmlDocument(fileBytes);
					// número de secuencia
					tslValet.setSequenceNumber(seqNum);
					// si existe nueva TSL disponible
					tslValet.setNewTSLAvaliable("N");

					// se realiza comprobación de la especificación y la versión
					// seleccionada en el formulario, con lo obtenido del XML
					CTslImpl tslImplForm = cTSLImplService.getCTSLImplBySpecificationVersion(specification, version);
					if (tslImplForm == null) {
						tslImplForm = new CTslImpl(); // TODO preguntar JAGG
					}
					tslValet.setTslImpl(tslImplForm);

					// añade la TSL a la base de datos.
					TslValet tslNew = tslValetService.saveTSL(tslValet);

					// lo añade a una lista de TSLs
					listTSL.add(tslNew);
					dtOutput.setData(listTSL);
				}

			} else {
				listTSL = StreamSupport.stream(tslValetService.getAllTSL().spliterator(), false).collect(Collectors.toList());
				dtOutput.setError(json.toString());
			}

		} catch (Exception e) {
			LOGGER.error(LanguageWeb.getFormatResWebValet(LogMessages.ERROR_SAVE_TSL, new Object[ ] { e.getMessage() }));
			json.put(KEY_JS_ERROR_SAVE_TSL, LanguageWeb.getResWebValet(LogMessages.ERROR_SAVE_TSL_WEB));
			listTSL = StreamSupport.stream(tslValetService.getAllTSL().spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());
		}
		dtOutput.setData(listTSL);
		return dtOutput;
	}

	/**
	 * 
	 * 
	 * @param idTSL
	 * @param file
	 * @param specification
	 * @param url
	 * @param version
	 * @return
	 * @throws IOException
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/updatetsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslValet> updateTsl(@RequestParam(FIELD_ID_TSL) Long idTSL, @RequestParam(FIELD_URL) String url, @RequestParam(FIELD_IMPL_TSL_FILE) MultipartFile implTslFile, @RequestParam(FIELD_FILE_DOC) MultipartFile fileDocument) throws IOException {

		DataTablesOutput<TslValet> dtOutput = new DataTablesOutput<>();

		boolean error = false;
		byte[ ] fileImplementationTsl = null;
		byte[ ] fileLegibleDocument = null;
		JSONObject json = new JSONObject();
		List<TslValet> listTSL = new ArrayList<TslValet>();
		TslValet tsl = null;
		try {

			// se comprueba que existe la tsl que se quiere editar.
			if (idTSL != null) {
				tsl = tslValetService.getTslValetById(idTSL);
			} else {
				LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_UPDATE_TSL));
				error = true;
				json.put(KEY_JS_ERROR_UPDATE_TSL, LanguageWeb.getResWebValet(LogMessages.ERROR_UPDATE_TSL));

			}

			if (!error) {
				// comprobamos si se ha añadido una nueva implementación
		
					if (implTslFile != null && implTslFile.getSize() > 0 && implTslFile.getBytes()!= null && implTslFile.getBytes().length > 0) {
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

					// (CodigoPais-Secuencia-VVersion)
					String alias = tsl.getCountry().getCountryRegionCode() + "-" + String.valueOf(seqNumber) + "-V" + tsl.getTslImpl().getVersion();
					tsl.setAlias(alias);

					CTslImpl ctslUpdate = cTSLImplService.getCTSLImpById(Long.valueOf(2));
					tsl.setTslImpl(ctslUpdate);

				} else {
					// incluimos la que ya existía
					tsl.setXmlDocument(tsl.getXmlDocument());
				}

				// comprobamos si se ha subido documento legible de la tsl.
				if (fileDocument != null) {
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
				TslValet tslNew = tslValetService.saveTSL(tsl);

				// lo añade a una lista de TSLs, para que salga en la datatable
				// actualizada.
				listTSL.add(tslNew);
				dtOutput.setData(listTSL);
			} else {
				// si ha ocurrido un error, se deja la lista TSL, tal como
				// estaba.
				listTSL = StreamSupport.stream(tslValetService.getAllTSL().spliterator(), false).collect(Collectors.toList());
				dtOutput.setError(json.toString());
			}

		} catch (Exception e) {
			LOGGER.error(LanguageWeb.getFormatResWebValet(LogMessages.ERROR_SAVE_TSL, new Object[ ] { e.getMessage() }));
			json.put(KEY_JS_ERROR_SAVE_TSL, LanguageWeb.getResWebValet(LogMessages.ERROR_EDIT_TSL_WEB));
			listTSL = StreamSupport.stream(tslValetService.getAllTSL().spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());

		}
		dtOutput.setData(listTSL);
		return dtOutput;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public @ResponseBody void downloadTsl(HttpServletResponse response, @RequestParam("id") Long idTsl) throws IOException {
		TslValet tsl = tslValetService.getTslValetById(idTsl);
		byte[ ] implTsl;
		if (tsl != null) {
			implTsl = tsl.getXmlDocument();
			String mimeType = UtilsResources.getMimeType(implTsl);
			String extension = UtilsResources.getExtension(mimeType);
			if (extension == null) {
				extension = "";
			}

			InputStream in = new ByteArrayInputStream(implTsl);
			response.setContentType(mimeType);
			response.setContentLength(implTsl.length);
			response.setHeader("Content-Disposition", "attachment; filename=" + tsl.getAlias() + EXTENSION_XML);
			FileCopyUtils.copy(in, response.getOutputStream());
		}
	}

	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	public @ResponseBody void downloadDocument(HttpServletResponse response, @RequestParam("id") Long idTsl) throws IOException {

		TslValet tsl = tslValetService.getTslValetById(idTsl);
		byte[ ] legibleDoc;
		if (tsl != null) {
			legibleDoc = tsl.getLegibleDocument();
			String mimeType = UtilsResources.getMimeType(legibleDoc);
			String extension = UtilsResources.getExtension(mimeType);
			if (extension == null) {
				extension = "";
			}
			InputStream in = new ByteArrayInputStream(legibleDoc);
			response.setContentType(mimeType);
			response.setContentLength(legibleDoc.length);
			response.setHeader("Content-Disposition", "attachment; filename=" + tsl.getAlias() + EXTENSION_PDF);
			FileCopyUtils.copy(in, response.getOutputStream());
		}
	}

	/**
	 * 
	 */
	@JsonView(MappingTslForm.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/loadmappingbyid", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MappingTslForm loadMappingById(@RequestParam("idTslCountryRegionMapping") Long idTslCountryRegionMapping) {
		TslCountryRegionMapping tslCRM = tslCountryRegionMappingService.getTslCountryRegionMappingById(idTslCountryRegionMapping);
		MappingTslForm mappingTslForm = new MappingTslForm();
		mappingTslForm.setIdTslCountryRegionMapping(idTslCountryRegionMapping);
		mappingTslForm.setIdTslCountryRegion(tslCRM.getTslCountryRegion().getIdTslCountryRegion());
		mappingTslForm.setMappingIdentificator(tslCRM.getMappingIdentificator());
		mappingTslForm.setMappingValue(tslCRM.getMappingValue());
		return mappingTslForm;
	}

	/**
	 * 
	 */
	@JsonView(MappingTslForm.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/loadconfirmdelete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public MappingTslForm loadConfirmDeleteMapping(@RequestParam("idTslCountryRegionMapping") Long idTslCountryRegionMapping, @RequestParam("rowindex") String rowIndexMapping) {
		MappingTslForm mappingTslForm = new MappingTslForm();
		mappingTslForm.setIdTslCountryRegionMapping(idTslCountryRegionMapping);
		mappingTslForm.setRowIndexMapping(rowIndexMapping);
		return mappingTslForm;
	}

	/**
	 * Method that refreshes the screen of editing TSL without getting to persist.
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
			LOGGER.info(LanguageWeb.getResWebValet(LogMessages.INFO_NOT_UPDATE_FILE_IMPL_TSL));
			//json.put(FIELD_IMPL_TSL_FILE + "_span", LanguageWeb.getResWebValet(LogMessages.INFO_NOT_UPDATE_FILE_IMPL_TSL));
			//se mantiene el que tenía la tsl
		
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
				LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_COUNTRY_INVALID));
				json.put(FIELD_IMPL_TSL_FILE + "_span", LanguageWeb.getResWebValet(LogMessages.ERROR_COUNTRY_INVALID));
			}
			if (error) {
				tslForm.setError(json.toString());
			}
		}

		return tslForm;
	}

	@RequestMapping(path = "/loadmapping", method = RequestMethod.GET)
	@JsonView(DataTablesOutput.View.class)
	public @ResponseBody DataTablesOutput<TslCountryRegionMapping> loadMapping(@RequestParam("id") Long idCountry) {
		DataTablesOutput<TslCountryRegionMapping> dtOutput = new DataTablesOutput<TslCountryRegionMapping>();
		List<TslCountryRegionMapping> listMapping = new ArrayList<TslCountryRegionMapping>();

		if (idCountry != null) {
			// obtenemos todos los mapeos de ese pais
			listMapping = tslCountryRegionMappingService.getAllMappingByIdCountry(idCountry);
			dtOutput.setData(listMapping);
		}

		dtOutput.setData(listMapping);
		return dtOutput;
	}

	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/savemappingtsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslCountryRegionMapping> saveMappingTsl(@RequestParam("idTslCountryRegion") Long idTslCountryRegion, @RequestParam("mappingIdentificator") String mappingIdentificator, @RequestParam("mappingValue") String mappingValue) throws IOException {

		DataTablesOutput<TslCountryRegionMapping> dtOutput = new DataTablesOutput<>();

		boolean error = false;

		JSONObject json = new JSONObject();
		List<TslCountryRegionMapping> listTslCountryRegionMapping = new ArrayList<TslCountryRegionMapping>();
		TslCountryRegionMapping tslCountryRegionMapping = null;

		// String mappingIdentificator =
		// mappingTslForm.getMappingIdentificator();
		// String mappingValue = mappingTslForm.getMappingValue();
		// Long idCountryRegionMapping =
		// mappingTslForm.getIdTslCountryRegionMapping();
		if (mappingIdentificator == null || mappingIdentificator.isEmpty() || mappingIdentificator.length() != mappingIdentificator.trim().length()) {
			LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_IDENTIFICATOR));
			json.put(FIELD_MAPPING_ID + "_span", LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_IDENTIFICATOR));
			error = true;
		}
		if (mappingValue == null || mappingValue.isEmpty() || mappingValue.length() != mappingValue.trim().length()) {
			LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_VALUE));
			json.put(FIELD_MAPPING_VALUE + "_span", LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_VALUE));
			error = true;
		}

		// se comprueba si existe un identificador igual
		if (tslCountryRegionMappingService.existIdentificator(mappingIdentificator, idTslCountryRegion)) {
			LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_VALUE));
			json.put(KEY_JS_INFO_EXIST_IDENTIFICATOR, LanguageWeb.getResWebValet(LogMessages.ERROR_IDENTIFICATOR_DUPLICATE));
			error = true;
		}

		if (!error) {
			// se trata de un nuevo valor
			tslCountryRegionMapping = new TslCountryRegionMapping();
			TslCountryRegion tslCountryRegion = tslCountryRegionService.getTslCountryRegionById(idTslCountryRegion);

			tslCountryRegionMapping.setTslCountryRegion(tslCountryRegion);
			tslCountryRegionMapping.setMappingIdentificator(mappingIdentificator);
			tslCountryRegionMapping.setMappingValue(mappingValue);

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

	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/modifymappingtsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslCountryRegionMapping> modifyMappingTsl(@RequestParam("idTslCountryRegionMapping") Long idTslCountryRegionMapping, @RequestParam("idTslCountryRegion") Long idTslCountryRegion, @RequestParam("mappingValue") String mappingValue) throws IOException {

		DataTablesOutput<TslCountryRegionMapping> dtOutput = new DataTablesOutput<>();

		boolean error = false;

		JSONObject json = new JSONObject();
		List<TslCountryRegionMapping> listTslCountryRegionMapping = new ArrayList<TslCountryRegionMapping>();
		TslCountryRegionMapping tslCountryRegionMapping = null;

		if (mappingValue == null || mappingValue.isEmpty() || mappingValue.length() != mappingValue.trim().length()) {
			LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_VALUE));
			json.put(FIELD_MAPPING_VALUE + "_spanEdit", LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_VALUE));
			error = true;
		}

		if (idTslCountryRegionMapping == null) {
			error = true;
			LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_EDIT_MAPPING));
		}
		// se obtiene el campo
		tslCountryRegionMapping = tslCountryRegionMappingService.getTslCountryRegionMappingById(idTslCountryRegionMapping);
		if (tslCountryRegionMapping == null) {
			error = true;
			LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_EDIT_MAPPING));
		}

		if (!error) {

			tslCountryRegionMapping.setMappingValue(mappingValue);

			TslCountryRegionMapping tslCRMNew = tslCountryRegionMappingService.save(tslCountryRegionMapping);

			// lo añade a una lista de Mapeos
			listTslCountryRegionMapping.add(tslCRMNew);

			// dtOutput.setData(listTslCountryRegionMapping);
		} else {
			listTslCountryRegionMapping = StreamSupport.stream(tslCountryRegionMappingService.getAllMappingByIdCountry(idTslCountryRegion).spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());
		}

		dtOutput.setData(listTslCountryRegionMapping);
		return dtOutput;
	}

	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(path = "/deletemappingbyid", method = RequestMethod.POST)
	public String deleteMappingById(@RequestParam("idTslCountryRegionMapping") Long idTslCountryRegionMapping, @RequestParam("rowIndexMapping") String index) {

		try {
			tslCountryRegionMappingService.deleteTslCountryRegionMapping(idTslCountryRegionMapping);
		} catch (Exception e) {
			index = "-1";
		}
		return index;
	}

	/**
	 * Method that maps the delete user request from datatable to the controller
	 * and performs the delete of the user identified by its id.
	 * 
	 * @param userId
	 *            Identifier of the user to be deleted.
	 * @param index
	 *            Row index of the datatable.
	 * @return String that represents the name of the view to redirect.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/deletetsl", method = RequestMethod.POST)
	public String deleteTsl(@RequestParam("id") Long idTslValet, @RequestParam("index") String index) {

		TslValet tsl = tslValetService.getTslValetById(idTslValet);
		Long idCountryRegion = tsl.getCountry().getIdTslCountryRegion();
		tslValetService.deleteTslValet(idTslValet);
		// se elimina también los mapeos del pais de la TSL eliminada.
		List<TslCountryRegionMapping> listMapping = new ArrayList<TslCountryRegionMapping>();
		listMapping = tslCountryRegionMappingService.getAllMappingByIdCountry(idCountryRegion);
		tslCountryRegionMappingService.deleteTslCountryRegionMappingByInBatch(listMapping);

		return index;
	}

}