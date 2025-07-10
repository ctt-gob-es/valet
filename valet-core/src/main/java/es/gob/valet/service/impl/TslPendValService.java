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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.TslPendValService.java.</p>
 * <b>Description:</b><p>Class that implements the communication with the operations of the persistence layer for Tsl pending validation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/06/2025.</p>
 * @author Gobierno de España.
 * @version 1.4, 10/07/2025.
 */
package es.gob.valet.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCountryLanguage;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.ImporTslsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CommonsUtilGeneralMessages;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.configuration.model.dto.SigningCertificateDTO;
import es.gob.valet.persistence.configuration.model.dto.TslPendValDTO;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslLotlData;
import es.gob.valet.persistence.configuration.model.entity.TslPendVal;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository;
import es.gob.valet.persistence.configuration.model.repository.TslDataRepository;
import es.gob.valet.persistence.configuration.model.repository.TslPendValRepository;
import es.gob.valet.service.ifaces.ISigningCertService;
import es.gob.valet.service.ifaces.ITslPendValService;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.certValidation.impl.ts119612.v020101.TSLValidator;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.exceptions.TSLParsingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;
import es.gob.valet.utils.TSLSpecificationsVersions;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for Tsl pending validation.</p>
 * <b>Project:</b><p> Class that implements the communication with the operations of the persistence layer for Tsl pending validation.</p>
 * @version 1.4, 10/07/2025.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TslPendValService implements ITslPendValService {

	/** 
	 * Logger for the TslPendValService class, used to log application events and errors.
	 */
	private static final Logger LOGGER = LogManager.getLogger(TslPendValService.class);

	/**
	 * Repository for accessing and managing pending TSL validation entries.
	 */
	@Autowired
	private TslPendValRepository tslPendValRepository;

	/**
	 * Service for extracting and processing data from TSL signing certificates.
	 */
	@Autowired
	private ISigningCertService iSigningCertService;
	
	@Autowired
	private TslCountryRegionRepository tslCountryRegionRepository;
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.ITslPendValService#obtainAllTslPendVal()
	 */
	@Override
	public List<TslPendValDTO> obtainAllTslPendVal() {
		List<TslPendValDTO> lisTslPendValDTO = new ArrayList<TslPendValDTO>();
		List<TslPendVal> lisTslPendVal = tslPendValRepository.findAll();
		for (TslPendVal tslPendVal: lisTslPendVal) {
			try (ByteArrayInputStream bais = new ByteArrayInputStream(tslPendVal.getXmlDocument())){
				ITSLObject iTSLObject = new TSLObject(TSLSpecificationsVersions.SPECIFICATION_119612, TSLSpecificationsVersions.VERSION_020101);
				iTSLObject.buildTSLFromXMLcheckValues(bais);
				
				// Obtenemos los datos del datatable
				Long idTslPendVal = tslPendVal.getIdTslPendVal();
				String schemeTerritory = iTSLObject.getSchemeInformation().getSchemeTerritory();
				String countryRegionName = UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(schemeTerritory);
				int sequenceNumber = iTSLObject.getSchemeInformation().getTslSequenceNumber();
				String issueDate =  UtilsDate.toString(UtilsDate.FORMAT_DATE_INVERTED, iTSLObject.getSchemeInformation().getListIssueDateTime());
				String uriTslLocation = "";
				for (int i = 0; i < iTSLObject.getSchemeInformation().getDistributionPoints().size(); i++) {
					if (!iTSLObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".pdf") && !iTSLObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".PDF")) {
						uriTslLocation = iTSLObject.getSchemeInformation().getDistributionPoints().get(i).toString();
						break;
					}
				}
				
				// Obtenemos los datos de información de la TSL
				String tslName = iTSLObject.getSchemeInformation().getSchemeName(Locale.UK.getLanguage());
				String tslResponsible = null;
				// Recuperamos el nombre del operador del esquema en inglés.
				List<String> sonList = iTSLObject.getSchemeInformation().getSchemeOperatorNameInLanguage(Locale.UK.getLanguage());
				if (sonList != null && !sonList.isEmpty()) {
					tslResponsible = sonList.get(0);
				}
				// Si no lo hemos recuperado, tomamos el primero que haya.
				if (UtilsStringChar.isNullOrEmptyTrim(tslResponsible)) {
					tslResponsible = iTSLObject.getSchemeInformation().getSchemeOperatorNames().values().iterator().next().get(0);
				}
				// Recuperamos la fecha de caducidad
				String expirationDate="";
				Date dateExpirationDate = iTSLObject.getSchemeInformation().getNextUpdate();
				if(null != dateExpirationDate) {
					expirationDate =  UtilsDate.toString(UtilsDate.FORMAT_DATE_INVERTED, dateExpirationDate);
				}
				// Recuperamos el certificado que firma la TSL
				// Obtenemos los datos del certificado incluido en la firma
				Map<String, String> mapSigningCert = iSigningCertService.getCertificateDetailsMap(iTSLObject.getSignTsl().get());
				SigningCertificateDTO signingCertificateDTO = new SigningCertificateDTO((Long) null, mapSigningCert.get(SigningCertService.ISSUER), mapSigningCert.get(SigningCertService.SUBJECT), mapSigningCert.get(SigningCertService.SERIAL_NUMBER), mapSigningCert.get(SigningCertService.DATE_EXPIRED), mapSigningCert.get(SigningCertService.CERTIFICATE_B64));
				
				// preparamos la lista
				TslPendValDTO tslPendValDTO = new TslPendValDTO(idTslPendVal,countryRegionName,sequenceNumber,issueDate,uriTslLocation, tslName, tslResponsible, expirationDate, signingCertificateDTO);
				lisTslPendValDTO.add(tslPendValDTO);
				
			} catch (IOException e) {
				LOGGER.error(Language.getFormatResCommonsUtilGeneral(CommonsUtilGeneralMessages.UTILS_RESOURCES_CODE_000, new Object[ ] { "ByteArrayInputStream" }), e);
			} catch (TSLArgumentException | TSLParsingException | TSLMalformedException | CertificateEncodingException | CommonUtilsException e) {
				LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.LOG_TSLPENDVAL001), e);
			} 
		
		}
		
		return lisTslPendValDTO;
	}
	
	/**
	 * Retrieves and parses the TSL identified by the given ID, and constructs a map
	 * containing structured and raw data related to it.
	 *
	 * The returned map includes:
	 * <ul>
	 *   <li><b>tslPendValDTO</b> - A DTO with summarized TSL information for presentation.</li>
	 *   <li><b>uriTslLocation</b> - The URL of the main distribution point (non-PDF).</li>
	 *   <li><b>xmlDocument</b> - The raw XML content as a byte array.</li>
	 *   <li><b>iTSLObject</b> - The parsed TSL object model.</li>
	 *   <li><b>tslPendVal</b> - The original entity from the database.</li>
	 * </ul>
	 *
	 * @param idTslPendVal the identifier of the pending TSL
	 * @return a map containing parsed and raw TSL data elements
	 */
	public Map<String, Object> obtainTslPendVal(Long idTslPendVal) {
		TslPendVal tslPendVal = tslPendValRepository.findById(idTslPendVal).get();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		try (ByteArrayInputStream bais = new ByteArrayInputStream(tslPendVal.getXmlDocument())){
			ITSLObject iTSLObject = new TSLObject(TSLSpecificationsVersions.SPECIFICATION_119612, TSLSpecificationsVersions.VERSION_020101);
			iTSLObject.buildTSLFromXMLcheckValues(bais);
			
			// Obtenemos los datos del datatable
			String schemeTerritory = iTSLObject.getSchemeInformation().getSchemeTerritory();
			String countryRegionName = UtilsCountryLanguage.getFirstLocaleCountryNameOfCountryCode(schemeTerritory);
			int sequenceNumber = iTSLObject.getSchemeInformation().getTslSequenceNumber();
			String issueDate =  UtilsDate.toString(UtilsDate.FORMAT_DATE_INVERTED, iTSLObject.getSchemeInformation().getListIssueDateTime());
			String uriTslLocation = "";
			for (int i = 0; i < iTSLObject.getSchemeInformation().getDistributionPoints().size(); i++) {
				if (!iTSLObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".pdf") && !iTSLObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".PDF")) {
					uriTslLocation = iTSLObject.getSchemeInformation().getDistributionPoints().get(i).toString();
					break;
				}
			}
			
			// Obtenemos los datos de información de la TSL
			String tslName = iTSLObject.getSchemeInformation().getSchemeName(Locale.UK.getLanguage());
			String tslResponsible = null;
			// Recuperamos el nombre del operador del esquema en inglés.
			List<String> sonList = iTSLObject.getSchemeInformation().getSchemeOperatorNameInLanguage(Locale.UK.getLanguage());
			if (sonList != null && !sonList.isEmpty()) {
				tslResponsible = sonList.get(0);
			}
			// Si no lo hemos recuperado, tomamos el primero que haya.
			if (UtilsStringChar.isNullOrEmptyTrim(tslResponsible)) {
				tslResponsible = iTSLObject.getSchemeInformation().getSchemeOperatorNames().values().iterator().next().get(0);
			}
			// Recuperamos la fecha de caducidad
			String expirationDate="";
			Date dateExpirationDate = iTSLObject.getSchemeInformation().getNextUpdate();
			if(null != dateExpirationDate) {
				expirationDate =  UtilsDate.toString(UtilsDate.FORMAT_DATE_INVERTED, dateExpirationDate);
			}
			// Recuperamos el certificado que firma la TSL
			// Obtenemos los datos del certificado incluido en la firma
			Map<String, String> mapSigningCert = iSigningCertService.getCertificateDetailsMap(iTSLObject.getSignTsl().get());
			SigningCertificateDTO signingCertificateDTO = new SigningCertificateDTO((Long) null, mapSigningCert.get(SigningCertService.ISSUER), mapSigningCert.get(SigningCertService.SUBJECT), mapSigningCert.get(SigningCertService.SERIAL_NUMBER), mapSigningCert.get(SigningCertService.DATE_EXPIRED), mapSigningCert.get(SigningCertService.CERTIFICATE_B64));
			
			// preparamos la lista
			TslPendValDTO tslPendValDTO = new TslPendValDTO(idTslPendVal,countryRegionName,sequenceNumber,issueDate,uriTslLocation, tslName, tslResponsible, expirationDate, signingCertificateDTO);
			
			hashMap.put("tslPendValDTO", tslPendValDTO);
			hashMap.put("uriTslLocation", uriTslLocation);
			hashMap.put("xmlDocument", tslPendVal.getXmlDocument());
			hashMap.put("iTSLObject", iTSLObject);
			hashMap.put("tslPendVal", tslPendVal);
			
		} catch (IOException e) {
			LOGGER.error(Language.getFormatResCommonsUtilGeneral(CommonsUtilGeneralMessages.UTILS_RESOURCES_CODE_000, new Object[ ] { "ByteArrayInputStream" }), e);
		} catch (TSLArgumentException | TSLParsingException | TSLMalformedException | CertificateEncodingException | CommonUtilsException e) {
			LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.LOG_TSLPENDVAL001), e);
		} 
		
		return hashMap;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.ITslPendValService#confirmTslPendVal(java.lang.Long)
	 */
	public void confirmTslPendVal(Long idTslPendVal) {
		try {
			Map<String, Object> hashMapTslPendVal = this.obtainTslPendVal(idTslPendVal);
			
			// Obtenemos una variable para ver si el tipo de TSLs es una lista de listas
			ITSLObject iTSLObject = (ITSLObject) hashMapTslPendVal.get("iTSLObject");
			TSLValidator tSLValidator = new TSLValidator(iTSLObject);
			boolean lotl = tSLValidator.checkIfTSLisListOfLists(iTSLObject.getSchemeInformation().getTslType().toString());
			
			// Actualizamos o insertamos una nueva TSL la cual estaba pendiente de validar
			TslCountryRegion tslCountryRegion = tslCountryRegionRepository.findByCountryRegionWithTslData(iTSLObject.getSchemeInformation().getSchemeTerritory());
			if(null != tslCountryRegion && null != tslCountryRegion.getTslData()) {
				// Chequeamos si la TSL es una lista de listas
				if(lotl) {
					TslLotlData tslLotlData = tslCountryRegion.getTslData().getTslLotlData();
					tslLotlData.setSigningCertificate(iTSLObject.getSignTsl().get().getEncoded());
					// TODO: 921 - que campos actualizaremos y como los actualizaremos?¿?¿
				}
				TSLManager.getInstance().updateTSLData(tslCountryRegion.getTslData(), (byte[ ]) hashMapTslPendVal.get("xmlDocument") , hashMapTslPendVal.get("uriTslLocation").toString(), tslCountryRegion.getTslData().getLegibleDocument());
			} else {
				TSLManager.getInstance().addNewTSLData(iTSLObject, hashMapTslPendVal.get("uriTslLocation").toString(), (byte[ ]) hashMapTslPendVal.get("xmlDocument"), lotl);
			}
			// Eliminamos la TSL que estaba pendiente de validar
			tslPendValRepository.delete((TslPendVal) hashMapTslPendVal.get("tslPendVal"));
		} catch (TSLManagingException e) {
			LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.LOG_TSLPENDVAL002), e);
		} catch (CertificateEncodingException e) {
			LOGGER.error(Language.getResCommonsUtilGeneral(CommonsUtilGeneralMessages.UTILS_CERTIFICATE_002), e);
		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.ITslPendValService#declineTslPendVal(java.lang.Long)
	 */
	public void declineTslPendVal(Long idTslPendVal) {
		TslPendVal tslPendVal = tslPendValRepository.findById(idTslPendVal).get();
		tslPendValRepository.delete(tslPendVal);
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.ITslPendValService#addTslPendVal(es.gob.valet.persistence.configuration.model.entity.TslPendVal)
	 */
	public void addTslPendVal(TslPendVal tslPendVal) {
	    tslPendValRepository.save(tslPendVal);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.ITslPendValService#exitsTslPendVal()
	 */
	public boolean exitsTslPendVal() {
	    return tslPendValRepository.findAll().size() > NumberConstants.NUM0 ? true : false;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.ITslPendValService#exitsTslPendVal(java.lang.String)
	 */
	public boolean exitsTslPendVal(String country) {
	    return tslPendValRepository.findAll().stream().anyMatch(p -> p.getCountry().equals(country));
	}

}
