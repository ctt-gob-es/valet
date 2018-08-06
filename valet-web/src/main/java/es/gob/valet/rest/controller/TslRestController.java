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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utilidades.GeneralConstants;
import es.gob.valet.commons.utilidades.UtilsResources;
import es.gob.valet.i18n.LanguageWeb;
import es.gob.valet.i18n.LogMessages;
import es.gob.valet.persistence.configuration.model.entity.CTSLImpl;
import es.gob.valet.persistence.configuration.model.entity.TSLCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TSLValet;
import es.gob.valet.service.ICTSLImplService;
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
	private ICTSLImplService cTSLImplService;

	@Autowired
	private ITslCountryRegionService tslCountryRegionService;

	private static final String FIELD_FILE = "file";
	private static final String FIELD_ID_TSL = "idTslValet";
	private static final String FIELD_URL = "urlTsl";
	private static final String FIELD_FILE_DOC = "fileDocument";
	private static final String FIELD_SPECIFICATION = "specification";
	private static final String FIELD_VERSION = "version";
	private static final String CONTENT_TYPE_XML = "application/xml";
	private static final String CONTENT_TYPE_PDF = "application/pdf";

	private static final String DEFAULT_TSL_XML = "downloads/implementationTSL.xml";
	private static final String DEFAULT_LEGIBLE_DOC ="downloads/legibleDocument.pdf";
	
	@Autowired
    private ServletContext servletContext;
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
	public DataTablesOutput<TSLValet> loadTslDataTable(@Valid DataTablesInput input) {

		return (DataTablesOutput<TSLValet>) tslValetService.findAllTsl(input);

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
		Map<String, Set<String>> res = cTSLImplService.getsTSLRelationSpecificatioAndVersion();

		versions.addAll(res.get(specification));

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
	public @ResponseBody DataTablesOutput<TSLValet> saveTsl(@RequestParam(FIELD_ID_TSL) Long idTSL, @RequestParam(FIELD_FILE) MultipartFile file, @RequestParam(FIELD_SPECIFICATION) String specification, @RequestParam(FIELD_URL) String url, @RequestParam(FIELD_VERSION) String version) throws IOException {

		DataTablesOutput<TSLValet> dtOutput = new DataTablesOutput<>();

		boolean error = false;

		byte[ ] fileBytes = null;
		JSONObject json = new JSONObject();
		List<TSLValet> listTSL = new ArrayList<TSLValet>();
		TSLValet tsl = null;

		// comprobamos que se han indicado todos los campos obligatorios
		if (file == null || file.getSize() == 0) {
			LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
			json.put(FIELD_FILE + "_span", "Es obligatorio seleccionar un archivo");
			error = true;
		} else {
			fileBytes = file.getBytes();

		}

		if (specification == null || specification.length() != specification.trim().length()) {
			LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_SPECIFICATION));
			json.put(FIELD_SPECIFICATION + "_span", "Es obligatorio indicar la especificación de la TSL");
			error = true;
		}

		if (version == null || version.length() != version.trim().length()) {
			LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_VERSION));
			json.put(FIELD_VERSION + "_span", LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_BLANK_VERSION));
			error = true;
		}

		if (idTSL != null) {
			tsl = tslValetService.getTslValetById(idTSL);
		} else {
			tsl = new TSLValet();
		}
		if (!error) {
			// Construimos el InputStream asociado al array y lo parseamos

			ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes);
			// TODO hay que crear la clase para obtener información del XML.

			// TODO Para las pruebas. Borrar y asignar los valores obtenidos en el
			// mapeo.
			LocalDateTime expDate = LocalDateTime.of(2019, 8, 10, 0, 0, 0);
			LocalDateTime issueDate = LocalDateTime.now();
			Instant instantExp = expDate.atZone(ZoneId.systemDefault()).toInstant();
			tsl.setExpirationDate(Date.from(instantExp));
			Instant instantIssue = issueDate.atZone(ZoneId.systemDefault()).toInstant();
			tsl.setIssueDate(Date.from(instantIssue));
			tsl.setUriTslLocation(url);

			// Obtenemos el pais de base de datos, sino se inserta el
			// obtenido en el mapeo.
			TSLCountryRegion country = tslCountryRegionService.getTSLCountryRegionById(Long.valueOf(1));
			if (country == null) {
				country = new TSLCountryRegion();
				country.setCountryRegionCode("ES");
				country.setCountryRegionName("Spain");
			}

			tsl.setCountry(country);

			// implementación
			tsl.setXmlDocument(fileBytes);

			tsl.setSequenceNumber(1);
			tsl.setNewTSLAvaliable("N");

			CTSLImpl tslImplForm = cTSLImplService.getCTSLImplBySpecificationVersion(specification, version);
			if (tslImplForm == null) {
				tslImplForm = new CTSLImpl(); // TODO preguntar JA

			}
			tsl.setTslImpl(tslImplForm);

			// añade la TSL a la base de datos.
			TSLValet tslNew = tslValetService.saveTSL(tsl);

			// lo añade a una lista de TSLs
			listTSL.add(tslNew);

			dtOutput.setData(listTSL);
		} else {
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
	public @ResponseBody DataTablesOutput<TSLValet> updateTsl(@RequestParam(FIELD_ID_TSL) Long idTSL, @RequestParam(FIELD_URL) String url, @RequestParam(FIELD_FILE) MultipartFile file, @RequestParam(FIELD_FILE_DOC) MultipartFile fileDocument) throws IOException {

		DataTablesOutput<TSLValet> dtOutput = new DataTablesOutput<>();

		boolean error = false;

		byte[ ] fileBytes = null;
		JSONObject json = new JSONObject();
		List<TSLValet> listTSL = new ArrayList<TSLValet>();
		TSLValet tsl = null;

		if (idTSL != null) {
			tsl = tslValetService.getTslValetById(idTSL);
		} else {
			error = true;

		}
		if (!error) {

			// comprobamos si se ha subido documento legible de la tsl.
			if (fileDocument == null || fileDocument.getSize() == 0) {
				LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
				json.put(FIELD_FILE + "_span", "Es obligatorio seleccionar un archivo");
				error = true;
			} else {
				fileBytes = fileDocument.getBytes();
				tsl.setLegibleDocument(fileBytes);

			}

			if (file == null || file.getSize() == 0) {
				LOGGER.error(LanguageWeb.getResWebValet(LogMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
				json.put(FIELD_FILE + "_span", "Es obligatorio seleccionar un archivo");
				error = true;
			} else {
				fileBytes = file.getBytes();
				tsl.setXmlDocument(fileBytes);

			}
			
			
			tsl.setUriTslLocation(url);

			// añade la TSL a la base de datos.
			TSLValet tslNew = tslValetService.saveTSL(tsl);

			// lo añade a una lista de TSLs
			listTSL.add(tslNew);

			dtOutput.setData(listTSL);
		} else {
			listTSL = StreamSupport.stream(tslValetService.getAllTSL().spliterator(), false).collect(Collectors.toList());
			dtOutput.setError(json.toString());
		}

		dtOutput.setData(listTSL);
		return dtOutput;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public @ResponseBody void downloadTsl(HttpServletResponse response, @RequestParam("id") Long idTsl) throws IOException {
			TSLValet tsl = tslValetService.getTslValetById(idTsl);
			ClassPathResource newFile = new ClassPathResource(DEFAULT_TSL_XML);
			byte[] documentXml = tsl.getXmlDocument();
			UtilsResources.writeBytesToFile(newFile.getFile(), documentXml);
			InputStream in = new FileInputStream(newFile.getFile());
			response.setContentType(UtilsResources.getMimeType(documentXml));
			response.setContentLength(documentXml.length);
			response.setHeader("Content-Disposition", "attachment; filename=\"ImplementacionTsl.xml\"");
			FileCopyUtils.copy(in, response.getOutputStream());
		
}
	
	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	public @ResponseBody void downloadDocument(HttpServletResponse response, @RequestParam("id") Long idTsl) throws IOException {
	
		TSLValet tsl = tslValetService.getTslValetById(idTsl);
		byte[] legibleDoc;
		if(tsl != null){
			legibleDoc = tsl.getLegibleDocument();
			String mimeType = UtilsResources.getMimeType(legibleDoc);
			String extension = UtilsResources.getExtension(mimeType);
			if(extension == null){
				extension = "";
			}
			InputStream in = new ByteArrayInputStream(legibleDoc);
				response.setContentType(mimeType);
				response.setContentLength(legibleDoc.length);
    			response.setHeader("Content-Disposition", "attachment; filename=\"DocLegibleTsl.pdf\"");
    			FileCopyUtils.copy(in, response.getOutputStream());
		}
//			TSLValet tsl = tslValetService.getTslValetById(idTsl);
//			ClassPathResource newFile = new ClassPathResource(DEFAULT_LEGIBLE_DOC);
//			UtilsResources.writeBytesToFile(newFile.getFile(), tsl.getLegibleDocument());
//			InputStream in = new FileInputStream(newFile.getFile());
//			response.setContentType(CONTENT_TYPE_PDF);
//			response.setContentLength(tsl.getLegibleDocument().length);
//			response.setHeader("Content-Disposition", "attachment; filename=\"DocumentoLegible.pdf\"");
//			FileCopyUtils.copy(in, response.getOutputStream());
		
}
	
	
	

}