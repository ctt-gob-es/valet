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
 * @version 1.12, 22/05/2019.
 */
package es.gob.valet.rest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;

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

import es.gob.valet.commons.utils.UtilsResources;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.form.TslForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLCountryRegionCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject;
import es.gob.valet.persistence.configuration.model.entity.CAssociationType;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegionMapping;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.utils.IAssociationTypeIdConstants;
import es.gob.valet.persistence.configuration.services.ifaces.ICAssociationTypeService;
import es.gob.valet.persistence.configuration.services.ifaces.ICTslImplService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionMappingService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;

/**
 * <p>Class that manages the REST request related to the TSLs administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.12, 22/05/2019.
 */
@RestController
public class TslRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(TslRestController.class);

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
	 * Constant that represents the parameter 'idTslCountryRegion'.
	 */
	private static final String FIELD_ID_COUNTRY_REGION = "idTslCountryRegion";

	/**
	 * Constant that represents the parameter 'codeCountryRegion'.
	 */
	private static final String FIELD_CODE_COUNTRY_REGION = "codeCountryRegion";

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
	public DataTablesOutput<TslData> loadTslDataTable(@NotEmpty DataTablesInput input) {
		ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();
		return tslDataService.getAllTsl(input);

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
	 * @param specificationTsl  Parameter that represents the ETSI TS number specification for TSL.
	 * @param urlTsl Parameter that represents the URI where this TSL is officially located.
	 * @param versionTsl Parameter that represents the ETSI TS specification version.
	 * @return {@link DataTablesOutput<TslData>}
	 * @throws IOException If the method fails.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/savetsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslData> saveTsl(@RequestParam(FIELD_IMPL_TSL_FILE) MultipartFile implTslFile, @RequestParam(FIELD_SPECIFICATION) String specificationTsl, @RequestParam(FIELD_URL) String urlTsl, @RequestParam(FIELD_VERSION) String versionTsl) throws IOException {

		DataTablesOutput<TslData> dtOutput = new DataTablesOutput<>();

		boolean error = false;
		byte[ ] fileBytes = null;
		JSONObject json = new JSONObject();
		List<TslData> listTSL = new ArrayList<TslData>();

		ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();

		try {
			// comprobamos que se han indicado todos los campos obligatorios
			if (implTslFile == null || implTslFile.getSize() == 0 || implTslFile.getBytes() == null || implTslFile.getBytes().length == 0) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
				json.put(FIELD_IMPL_TSL_FILE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
				error = true;

			} else {
				fileBytes = implTslFile.getBytes();
			}

			if (specificationTsl == null || specificationTsl.equals(String.valueOf(-1))) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_SPECIFICATION));
				json.put(FIELD_SPECIFICATION + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_SPECIFICATION));
				error = true;
			}

			if (UtilsStringChar.isNullOrEmpty(versionTsl) || versionTsl.equals(String.valueOf(-1))) {
				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VERSION));
				json.put(FIELD_VERSION + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VERSION));
				error = true;
			}

			if (!error) {
				// se añade una nueva TSL en la base de datos y en la caché
				// compartida.
				TslData tslNew = TSLManager.getInstance().addNewTSLData(urlTsl, specificationTsl, versionTsl, fileBytes);

				// se actualiza la lsita de TSL para mostrar en la datatable.
				listTSL.add(tslNew);
				dtOutput.setData(listTSL);

			} else {
				listTSL = StreamSupport.stream(tslDataService.getAllTSL().spliterator(), false).collect(Collectors.toList());
				dtOutput.setError(json.toString());
			}

		} catch (TSLManagingException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_SAVE_TSL, new Object[ ] { e.getMessage() }), e);
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
	 * @param urlTsl Parameter that represents the URI where this TSL is officially located.
	 * @param implTslFile  Parameter that represents the file with the implementation of the TSL.
	 * @param fileDocument Parameter that represents the file with the legible document associated to TSL.
	 * @return {@link DataTablesOutput<TslData>}
	 * @throws IOException If the method fails.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/updatetsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslData> updateTsl(@RequestParam(FIELD_ID_TSL) Long idTSL, @RequestParam(FIELD_URL) String urlTsl, @RequestParam(FIELD_IMPL_TSL_FILE) MultipartFile implTslFile, @RequestParam(FIELD_FILE_DOC) MultipartFile fileDocument) throws IOException {

		DataTablesOutput<TslData> dtOutput = new DataTablesOutput<>();

		byte[ ] tslXMLbytes = null;
		byte[ ] legibleDocumentArrayByte = null;
		JSONObject json = new JSONObject();
		List<TslData> listTSL = new ArrayList<TslData>();

		ITslDataService tslDataService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslDataService();

		// comprobamos que no se haya dejado vacío el campo del fichero de TSL.
		if (implTslFile == null || implTslFile.getSize() == 0 || implTslFile.getBytes() == null || implTslFile.getBytes().length == 0) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));
			json.put(FIELD_IMPL_TSL_FILE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_NULL_FILE_IMPL_TSL));

		} else {
			tslXMLbytes = implTslFile.getBytes();
		}

		if (fileDocument != null) {
			legibleDocumentArrayByte = fileDocument.getBytes();
		}

		try {
			TslData tslDataUpdated = TSLManager.getInstance().updateTSLData(idTSL, tslXMLbytes, urlTsl, legibleDocumentArrayByte);
			listTSL.add(tslDataUpdated);
			dtOutput.setData(listTSL);

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

		try {
			TSLDataCacheObject tsldco = TSLManager.getInstance().getTSLDataCacheObject(idTsl);
			TSLCountryRegionCacheObject tslcrco = TSLManager.getInstance().getTSLCountryRegionByIdTslData(idTsl);
			String filenameTSL = tslcrco.getCode() + "-" + tsldco.getSequenceNumber() + EXTENSION_XML;
			byte[ ] implTsl = TSLManager.getInstance().getTSLDataXMLDocument(idTsl);
			InputStream in = new ByteArrayInputStream(implTsl);
			response.setContentType(TOKEN_TEXT_XML);
			response.setContentLength(implTsl.length);
			response.setHeader("Content-Disposition", "attachment; filename=" + filenameTSL);
			FileCopyUtils.copy(in, response.getOutputStream());

		} catch (TSLManagingException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_DOWNLOAD_TSL, new Object[ ] { e.getMessage() }));
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
		byte[ ] legibleDoc = null;
		try {
			TSLDataCacheObject tsldco = TSLManager.getInstance().getTSLDataCacheObject(idTsl);
			TSLCountryRegionCacheObject tslcrco = TSLManager.getInstance().getTSLCountryRegionByIdTslData(idTsl);
			String filenameTSL = tslcrco.getCode() + "-" + tsldco.getSequenceNumber() + EXTENSION_PDF;

			legibleDoc = TSLManager.getInstance().getTSLLegibleDocument(idTsl);

			InputStream in = new ByteArrayInputStream(legibleDoc);
			response.setContentType(TOKEN_APPLICATION_PDF);
			response.setContentLength(legibleDoc.length);
			response.setHeader("Content-Disposition", "attachment; filename=" + filenameTSL);
			FileCopyUtils.copy(in, response.getOutputStream());
		} catch (TSLManagingException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_DOWNLOAD_DOC_LEGIBLE, new Object[ ] { e.getMessage() }));
		}

	}

	/**
	 * Method that refreshes the screen of editing TSL without getting to persist.
	 * @param idTSL Parameter that represents the identifier TSL.
	 * @param implTslFile Parameter that represents the file with the implementation of the TSL.
	 * @return TslForm object with the updated data of the form.
	 * @throws IOException If the method fails.
	 * @throws TSLManagingException
	 */
	@JsonView(TslForm.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/updateimplfile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public TslForm updateImplFile(@RequestParam(FIELD_ID_TSL) Long idTSL, @RequestParam(FIELD_IMPL_TSL_FILE) MultipartFile implTslFile, @RequestParam(FIELD_SPECIFICATION) String specificationTsl, @RequestParam(FIELD_VERSION) String versionTsl) throws IOException {

		TslForm tslForm = new TslForm();
		byte[ ] tslXMLbytes = null;
		JSONObject json = new JSONObject();
		boolean error = false;
		// se comprueba si se ha actualizado la implementación de TSL, si es así
		// se obtiene los nuevos datos
		if (implTslFile == null || implTslFile.getSize() == 0 || implTslFile.getBytes() == null || implTslFile.getBytes().length == 0) {
			// se muestra mensaje indicando que no se ha actualizado
			LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.INFO_NOT_UPDATE_FILE_IMPL_TSL));
		} else {

			tslXMLbytes = implTslFile.getBytes();
			// Contruimos el InputStream asociado al array, y tratamos de
			// parsearlo y añadirlo.
			ByteArrayInputStream bais = new ByteArrayInputStream(tslXMLbytes);
			ITSLObject tslObject = null;
			try {
				tslObject = new TSLObject(specificationTsl, versionTsl);
				tslObject.buildTSLFromXMLcheckValues(bais);
				// se obtiene el código del país de la TSL que se está editando
				String ccr = TSLManager.getInstance().getTSLCountryRegionByIdTslData(idTSL).getCode();
				// se comprueba que sea del mismo país
				if (!tslObject.getSchemeInformation().getSchemeTerritory().equals(ccr)) {
					// se indica que se está intentando actualizar con una TSL
					// de
					// otro país.
					// se muestra mensaje indicando que no se ha actualizado
					error = true;
					LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_COUNTRY_INVALID));
					json.put(FIELD_IMPL_TSL_FILE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_COUNTRY_INVALID));
				} else {
					// actualizamos el formulario
					tslForm.setSpecification(tslObject.getSpecification());
					tslForm.setVersion(tslObject.getSpecificationVersion());
					tslForm.setSequenceNumber(tslObject.getSchemeInformation().getTslSequenceNumber());

					// Recuperamos el nombre del esquema en inglés.
					String tslNameNew = tslObject.getSchemeInformation().getSchemeName(Locale.UK.getLanguage());
					// Si no lo hemos recuperado, tomamos el primero que haya.
					if (UtilsStringChar.isNullOrEmptyTrim(tslNameNew)) {
						tslNameNew = tslObject.getSchemeInformation().getSchemeNames().values().iterator().next();
					}
					tslForm.setTslName(tslNameNew);

					// Recuperamos el nombre del responsable, el nombre del
					// operador del esquema en inglés.
					String responsible = null;
					List<String> sonList = tslObject.getSchemeInformation().getSchemeOperatorNameInLanguage(Locale.UK.getLanguage());
					if (sonList != null && !sonList.isEmpty()) {
						responsible = sonList.get(0);
					}
					// Si no lo hemos recuperado, tomamos el primero que haya.
					if (UtilsStringChar.isNullOrEmptyTrim(responsible)) {
						responsible = tslObject.getSchemeInformation().getSchemeOperatorNames().values().iterator().next().get(0);
					}
					tslForm.setTslResponsible(responsible);

					// componemos el nombre del fichero de la implementación XML
					// para
					// que se muestre en administración
					String filenameTSL = tslObject.getSchemeInformation().getSchemeTerritory() + "-" + tslObject.getSchemeInformation().getTslSequenceNumber() + EXTENSION_XML;
					tslForm.setAlias(filenameTSL);

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					tslForm.setIssueDate(sdf.format(tslObject.getSchemeInformation().getListIssueDateTime()));
					tslForm.setExpirationDate(sdf.format(tslObject.getSchemeInformation().getNextUpdate()));
					tslForm.setImplTslFile(implTslFile);
				}

			} catch (Exception e) {
				// throw new TSLManagingException(IValetException.COD_187,
				// Language.getResCoreTsl(ICoreTslMessages.LOGMTSL170), e);
			} finally {
				UtilsResources.safeCloseInputStream(bais);
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
	 * @param codeCountryRegion Parameter that represents a country/region identifier.
	 * @param mappingIdentificator Parameter that represents the identificator for the logical mapping.
	 * @param mappingFreeValue Parameter that represents the value for the mapping (free type).
	 * @param mappingSimpleValue Parameter that represents the value for the mapping (simple type).
	 * @param idMappingType Parameter that represents the association type for the mapping.
	 * @return {@link DataTablesOutput<TslCountryRegionMapping>}
	 * @throws IOException If the method fails.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/savemappingtsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslCountryRegionMapping> saveMappingTsl(@RequestParam(FIELD_CODE_COUNTRY_REGION) String codeCountryRegion, @RequestParam("mappingIdentificator") String mappingIdentificator, @RequestParam("mappingFreeValue") String mappingFreeValue, @RequestParam("mappingSimpleValue") String mappingSimpleValue, @RequestParam("idMappingType") Long idMappingType) throws IOException {

		DataTablesOutput<TslCountryRegionMapping> dtOutput = new DataTablesOutput<>();

		boolean error = false;

		JSONObject json = new JSONObject();
		List<TslCountryRegionMapping> listTslCountryRegionMapping = new ArrayList<TslCountryRegionMapping>();

		ITslCountryRegionMappingService tslCountryRegionMappingService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService();

		if (mappingIdentificator == null || mappingIdentificator.isEmpty() || mappingIdentificator.length() != mappingIdentificator.trim().length()) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_IDENTIFICATOR));
			json.put(FIELD_MAPPING_ID + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_IDENTIFICATOR));
			error = true;
		}
		// IAssociationTypeIdConstants
		String mappingValue = null;
		if (idMappingType != null) {

			if (idMappingType.equals(IAssociationTypeIdConstants.ID_SIMPLE_ASSOCIATION)) {
				mappingValue = mappingSimpleValue;
			} else if (idMappingType.equals(IAssociationTypeIdConstants.ID_FREE_ASSOCIATION)) {
				mappingValue = mappingFreeValue;
			}

		}

		if (mappingValue == null || mappingValue.isEmpty() || mappingValue.length() != mappingValue.trim().length()) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VALUE));
			json.put(FIELD_MAPPING_VALUE + "_span", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VALUE));
			error = true;
		}

		// se comprueba si existe un identificador igual
		try {
			if (TSLManager.getInstance().checkIfTSLCountryRegionMappingIdentificatorIsAlreadyDefined(codeCountryRegion, null, mappingIdentificator)) {

				LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_IDENTIFICATOR_DUPLICATE));
				json.put(KEY_JS_INFO_EXIST_IDENTIFICATOR, Language.getResWebGeneral(IWebGeneralMessages.ERROR_IDENTIFICATOR_DUPLICATE));
				error = true;
			}

			if (!error) {

				TslCountryRegionMapping tslCRMNew = TSLManager.getInstance().addNewMappingToCountryRegion(codeCountryRegion, mappingIdentificator, null, mappingValue, idMappingType);

				// lo añade a una lista de Mapeos
				listTslCountryRegionMapping.add(tslCRMNew);
				dtOutput.setData(listTslCountryRegionMapping);

			} else {
				TSLCountryRegionCacheObject tslcrco = TSLManager.getInstance().getTSLCountryRegionCacheObject(codeCountryRegion);
				listTslCountryRegionMapping = StreamSupport.stream(tslCountryRegionMappingService.getAllMappingByIdCountry(tslcrco.getCountryRegionId()).spliterator(), false).collect(Collectors.toList());
				dtOutput.setError(json.toString());
			}
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_SAVE_MAPPING, new Object[ ] { e.getMessage() }));
		}
		dtOutput.setData(listTslCountryRegionMapping);
		return dtOutput;
	}

	/**
	 * Method to modify the value of an identifier.
	 * @param idTslCountryRegionMapping  Parameter that represents the ID of the mapping.
	 * @param idTslCountryRegion  Parameter that represents the ID of the country/region.
	 * @param mappingIdentificator Parameter that represents the identificator for the logical mapping.
	 * @param mappingFreeValue Parameter that represents the value for the mapping (free type).
	 * @param mappingSimpleValue Parameter that represents the value for the mapping (simple type).
	 * @param idMappingType Parameter that represents the type of association.
	 * @return {@link DataTablesOutput<TslCountryRegionMapping>}
	 * @throws IOException If the method fails.
	 */
	@JsonView(DataTablesOutput.View.class)
	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/modifymappingtsl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DataTablesOutput<TslCountryRegionMapping> modifyMappingTsl(@RequestParam(FIELD_ID_COUNTRY_REGION_MAPPING) Long idTslCountryRegionMapping, @RequestParam(FIELD_ID_COUNTRY_REGION) Long idTslCountryRegion, @RequestParam("mappingIdentificator") String mappingIdentificator, @RequestParam("mappingFreeValue") String mappingFreeValue, @RequestParam("mappingSimpleValue") String mappingSimpleValue, @RequestParam("idMappingType") Long idMappingType) throws IOException {

		DataTablesOutput<TslCountryRegionMapping> dtOutput = new DataTablesOutput<>();
		boolean error = false;
		JSONObject json = new JSONObject();
		List<TslCountryRegionMapping> listTslCountryRegionMapping = new ArrayList<TslCountryRegionMapping>();

		String mappingValue = null;
		if (idMappingType != null) {
			if (idMappingType.equals(IAssociationTypeIdConstants.ID_SIMPLE_ASSOCIATION)) {
				mappingValue = mappingSimpleValue;
			} else if (idMappingType.equals(IAssociationTypeIdConstants.ID_FREE_ASSOCIATION)) {
				mappingValue = mappingFreeValue;
			}
		}

		if (mappingValue == null || mappingValue.isEmpty() || mappingValue.length() != mappingValue.trim().length()) {
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VALUE));
			json.put(FIELD_MAPPING_VALUE + "_spanEdit", Language.getResWebGeneral(IWebGeneralMessages.ERROR_NOT_BLANK_VALUE));
			error = true;
		}

		if (idTslCountryRegionMapping == null) {
			error = true;
			LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.ERROR_EDIT_MAPPING));
		}

		if (!error) {
			TslCountryRegionMapping tslCRMNew;
			try {
				tslCRMNew = TSLManager.getInstance().updateTSLCountryRegionMapping(idTslCountryRegionMapping, mappingIdentificator, null, mappingValue, idMappingType);
				// lo añade a una lista de Mapeos
				listTslCountryRegionMapping.add(tslCRMNew);
			} catch (TSLManagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			ITslCountryRegionMappingService tslCountryRegionMappingService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTslCountryRegionMappingService();
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
	public String deleteMappingById(@RequestParam(FIELD_ID_COUNTRY_REGION_MAPPING) Long idTslCountryRegionMapping, @RequestParam(FIELD_CODE_COUNTRY_REGION) String codeCountryRegion, @RequestParam("rowIndexMapping") String indexParam) {
		String index = indexParam;

		try {
			TSLManager.getInstance().removeTSLCountryRegionMapping(codeCountryRegion, idTslCountryRegionMapping);
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
		try {
			TSLManager.getInstance().removeTSLData(null, idTslData);
		} catch (TSLManagingException e) {
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
		Map<Long, String> mapAssociationType = new HashMap<Long, String>();
		// obtenemos los tipos de planificadores.
		ICAssociationTypeService cAssociationTypeService = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCAssociationTypeService();
		List<CAssociationType> listCAssociationType = cAssociationTypeService.getAllAssociationType();
		for (CAssociationType associationType: listCAssociationType) {
			mapAssociationType.put(associationType.getIdAssociationType(), Language.getResPersistenceConstants(associationType.getTokenName()));
		}
		return mapAssociationType;

	}

}