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

import es.gob.valet.commons.utils.UtilsCountryLanguage;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICommonsUtilGeneralMessages;
import es.gob.valet.persistence.configuration.model.dto.SigningCertificateDTO;
import es.gob.valet.persistence.configuration.model.dto.TslPendValDTO;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.entity.TslPendVal;
import es.gob.valet.persistence.configuration.model.repository.TslDataRepository;
import es.gob.valet.persistence.configuration.model.repository.TslPendValRepository;
import es.gob.valet.service.ifaces.ISigningCertService;
import es.gob.valet.service.ifaces.ITslPendValService;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.exceptions.TSLManagingException;
import es.gob.valet.tsl.exceptions.TSLParsingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;
import es.gob.valet.utils.TSLSpecificationsVersions;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class TslPendValService implements ITslPendValService {

	private static final Logger LOGGER = LogManager.getLogger(TslPendValService.class);
	
	@Autowired
	private TslPendValRepository tslPendValRepository;
	
	@Autowired
	private ISigningCertService iSigningCertService;
	
	@Autowired
	private TslDataRepository tslDataRepository;
	
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
				LOGGER.error(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_RESOURCES_CODE_000, new Object[ ] { "ByteArrayInputStream" }), e);
			} catch (TSLArgumentException | TSLParsingException | TSLMalformedException | CertificateEncodingException | CommonUtilsException e) {
				LOGGER.error("Se ha producido un fallo al construir la tsl a partir del xml", e);
			} 
		
		}
		
		return lisTslPendValDTO;
	}
	
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
			LOGGER.error(Language.getFormatResCommonsUtilGeneral(ICommonsUtilGeneralMessages.UTILS_RESOURCES_CODE_000, new Object[ ] { "ByteArrayInputStream" }), e);
		} catch (TSLArgumentException | TSLParsingException | TSLMalformedException | CertificateEncodingException | CommonUtilsException e) {
			LOGGER.error("Se ha producido un fallo al construir la tsl a partir del xml", e);
		} 
		
		return hashMap;
	}
	
	public void confirmTslPendVal(Long idTslPendVal) {
		try {
			Map<String, Object> hashMapTslPendVal = this.obtainTslPendVal(idTslPendVal);
			// Actualizamos o insertamos una nueva TSL la cual estaba pendiente de validar
			TslData tslData = tslDataRepository.findByUriTslLocation(hashMapTslPendVal.get("uriTslLocation").toString());
			if(null != tslData) {
				TSLManager.getInstance().updateTSLData(tslData.getIdTslData(), (byte[ ]) hashMapTslPendVal.get("xmlDocument") , hashMapTslPendVal.get("uriTslLocation").toString(), tslData.getLegibleDocument());
			} else {
				TSLManager.getInstance().addNewTSLData((ITSLObject) hashMapTslPendVal.get("iTSLObject"), hashMapTslPendVal.get("uriTslLocation").toString(), (byte[ ]) hashMapTslPendVal.get("xmlDocument"));
			}
			// Eliminamos la TSL que estaba pendiente de validar
			tslPendValRepository.delete((TslPendVal) hashMapTslPendVal.get("tslPendVal"));
		} catch (TSLManagingException e) {
			LOGGER.error("Se ha producido un fallo al insertar o actualizar la TSL", e);
		}
	}
	
	public void declineTslPendVal(Long idTslPendVal) {
		TslPendVal tslPendVal = tslPendValRepository.findById(idTslPendVal).get();
		tslPendValRepository.delete(tslPendVal);
	}
}
