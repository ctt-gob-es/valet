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
 * <b>File:</b><p>es.gob.valet.service.impl.ExportService.java.</p>
 * <b>Description:</b><p>Class that implements the communication with the operations of the persistence layer for ExportTsls.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 19/03/2025.
 */
package es.gob.valet.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Base64;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.gob.valet.commons.utils.AESCipher;
import es.gob.valet.commons.utils.CryptographicConstants;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCrypto;
import es.gob.valet.commons.utils.UtilsKeystore;
import es.gob.valet.commons.utils.UtilsResources;
import es.gob.valet.exceptions.CipherException;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.ExportException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.i18n.utils.UtilsServer;
import es.gob.valet.persistence.configuration.model.dto.TslCountryRegionDTO;
import es.gob.valet.persistence.configuration.model.dto.TslDataDTO;
import es.gob.valet.persistence.configuration.model.dto.TslServiceDTO;
import es.gob.valet.persistence.configuration.model.entity.SigningCertificate;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.entity.TslService;
import es.gob.valet.persistence.configuration.model.repository.SigningCertificateRepository;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository;
import es.gob.valet.persistence.configuration.model.repository.TslDataRepository;
import es.gob.valet.persistence.configuration.model.repository.TslServiceRepository;
import es.gob.valet.service.ifaces.IExportService;
import es.gob.valet.sign.ExportFileSigner;
import es.gob.valet.sign.cades.SignatureException;

/** 
 * <p>Class that implements the communication with the operations of the persistence layer for ExportTsls.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19/03/2025.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ExportService implements IExportService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ExportService.class);
	
	/**
	 * Constant attribute that represents the extension used to define JSON files.
	 */
	private static final String JSON_EXTENSION = ".json";
	
	/**
	 * Repository for TSL data operations.
	 */
	@Autowired
	private TslDataRepository tslDataRepository;

	/**
	 * Repository for TSL country and region mapping operations.
	 */
	@Autowired
	private TslCountryRegionRepository tslCountryRegionRepository;

	/**
	 * Repository for TSL service operations.
	 */
	@Autowired
	private TslServiceRepository tslServiceRepository;

	/**
	 * Repository for signing certificate operations.
	 */
	@Autowired
	private SigningCertificateRepository signingCertificateRepository;

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExportService#exportTslData(java.util.Properties, java.io.File)
	 */
	@Override
	public void exportTslData(Properties filesHashProperties, File tslDataFolder) throws IOException, NoSuchAlgorithmException {
		LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.LOG_EXP013));
		
		List<TslData> lisTslData = tslDataRepository.findAll();
		
		String tempFolderPath = UtilsServer.getWeblogicServerTempDir().replace("\\", "/");
		
		for (TslData tslData: lisTslData) {
			TslDataDTO tslDataDTO = new TslDataDTO(tslData);
			
			String jsonFileName = tslDataDTO.getTslCountryRegionDTO().getCountryRegionCode() + "-" + tslDataDTO.getSequenceNumber() + JSON_EXTENSION;
			
			File tslDataJSONFile = new File(tslDataFolder, jsonFileName);
			
			ObjectMapper mapper = new ObjectMapper();
			
			byte[ ] byteTslData = mapper.writeValueAsBytes(tslDataDTO);
			
			FileUtils.writeByteArrayToFile(tslDataJSONFile, byteTslData);
			
			// Calculamos el resumen en SHA-256 del fichero y lo
			// añadimos codificado en Base64 al archivo de
			// propiedades
			// con los hashes la
			// entrada correspondiente
			byte[ ] hash = UtilsCrypto.calculateHash(byteTslData);
			filesHashProperties.setProperty(tslDataJSONFile.getPath().replace("\\", "/").split(tempFolderPath)[1], Base64.getEncoder().encodeToString(hash));
		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExportService#exportMappingToTsls(java.util.Properties, java.io.File)
	 */
	@Override
	public void exportMappingToTsls(Properties filesHashProperties, File tslCountryRegionMappingFolder) throws IOException, NoSuchAlgorithmException {
		LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.LOG_EXP014));
		
		String tempFolderPath = UtilsServer.getWeblogicServerTempDir().replace("\\", "/");
		
		List<TslCountryRegion> listTslCountryRegion = tslCountryRegionRepository.findAllWithMappings();
		
		for (TslCountryRegion tslCountryRegion: listTslCountryRegion) {
			
			TslCountryRegionDTO tslCountryRegionDTO = new TslCountryRegionDTO(tslCountryRegion, true);
			
			String jsonFileName = "MAPEO" + "-" + tslCountryRegionDTO.getCountryRegionCode() + JSON_EXTENSION;
			
			File tslDataJSONFile = new File(tslCountryRegionMappingFolder, jsonFileName);
			
			ObjectMapper mapper = new ObjectMapper();
			
			byte[ ] byteTslData = mapper.writeValueAsBytes(tslCountryRegionDTO);
			
			FileUtils.writeByteArrayToFile(tslDataJSONFile, byteTslData);
			
			// Calculamos el resumen en SHA-256 del fichero y lo
			// añadimos codificado en Base64 al archivo de
			// propiedades
			// con los hashes la
			// entrada correspondiente
			byte[ ] hash = UtilsCrypto.calculateHash(byteTslData);
			filesHashProperties.setProperty(tslDataJSONFile.getPath().replace("\\", "/").split(tempFolderPath)[1], Base64.getEncoder().encodeToString(hash));
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExportService#exportMappingToService(java.util.Properties, java.io.File)
	 */
	@Override
	public void exportMappingToService(Properties filesHashProperties, File tslMappingFolder) throws IOException, NoSuchAlgorithmException, CommonUtilsException {
		LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.LOG_EXP015));
		
		String tempFolderPath = UtilsServer.getWeblogicServerTempDir().replace("\\", "/");
		
		List<TslService> listTslService = tslServiceRepository.findAllWithMappingsNotNull();
		
		for (TslService tslService: listTslService) {
			TslServiceDTO tslServiceDTO = new TslServiceDTO(tslService);
			
			String jsonFileName = tslServiceDTO.getTspServiceName().replaceAll("[:\\s]+", "_") + JSON_EXTENSION;
			
			File tslServiceJSONFile = new File(tslMappingFolder, jsonFileName);
			
			ObjectMapper mapper = new ObjectMapper();
			
			byte[ ] byteTslData = mapper.writeValueAsBytes(tslServiceDTO);
			
			FileUtils.writeByteArrayToFile(tslServiceJSONFile, byteTslData);
			
			// Calculamos el resumen en SHA-256 del fichero y lo
			// añadimos codificado en Base64 al archivo de
			// propiedades
			// con los hashes la
			// entrada correspondiente
			byte[ ] hash = UtilsCrypto.calculateHash(byteTslData);
			filesHashProperties.setProperty(tslServiceJSONFile.getPath().replace("\\", "/").split(tempFolderPath)[1], Base64.getEncoder().encodeToString(hash));
		}
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExportService#addMetaInf(java.util.Properties, java.io.File, java.lang.String, java.io.File)
	 */
	@Override
	public byte[] addMetaInf(Properties filesHashProperties, File metaInfFolder, String version, File tslsValetFolder) throws ExportException {
	    LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.LOG_EXP016));

	    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
	    	filesHashProperties.store(out, Language.getFormatResWebGeneral(WebGeneralMessages.LOG_EXP018, version));
	        
	        byte[] hashData = out.toByteArray();
	        String nameFile = "files_hash_v_"+version+".properties";
	        FileUtils.writeByteArrayToFile(new File(metaInfFolder, nameFile), hashData);
	        
	        return hashData; // Devuelve el array generado
	    } catch (Exception e) {
	        deleteTemporalFiles(tslsValetFolder);
	        throw new ExportException(Language.getResWebGeneral(WebGeneralMessages.LOG_EXP017), e);
	    }
	}
	
	/**
	 * Deletes the temporary directory created for the export process.
	 *
	 * @param tslsValetFolder the temporary folder to be deleted
	 * @throws ExportException if an error occurs while deleting the temporary directory
	 */
	private void deleteTemporalFiles(File tslsValetFolder) throws ExportException {
		if (tslsValetFolder != null && tslsValetFolder.exists()) {
			try {
				// Borramos la estructura temporal de ficheros.
				FileUtils.deleteDirectory(tslsValetFolder);
			} catch (IOException e) {
				throw new ExportException(Language.getResWebGeneral(WebGeneralMessages.LOG_EXP019), e);
			}
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExportService#signHash(byte[])
	 */
	@Override
	public byte[] signHash(byte[ ] hashByteArray) throws ExportException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, SignatureException, CipherException {
		SigningCertificate signingCertificate = signingCertificateRepository.findByIdSigningCertificate(NumberConstants.NUM1_LONG);
		
		if(null == signingCertificate) {
			throw new ExportException(Language.getResWebGeneral(WebGeneralMessages.LOG_EXP020));
		}
		
		byte[] byteKeystoreP12 = Base64.getDecoder().decode(signingCertificate.getKeystore());
		String password = AESCipher.getInstance().decryptMessageBC(signingCertificate.getKeystorePassword());
		
		KeyStore keyStoreP12 = UtilsKeystore.loadKsPKCS12(byteKeystoreP12, password);
		
		String alias = UtilsKeystore.getFirstAlias(keyStoreP12);
		PrivateKey pk = (PrivateKey) keyStoreP12.getKey(alias, password.toCharArray());
		PrivateKeyEntry pke = new PrivateKeyEntry(pk, keyStoreP12.getCertificateChain(alias));
		
		// Firmamos el fichero en formato CAdES-BES
		return ExportFileSigner.sign(hashByteArray, CryptographicConstants.HASH_ALGORITHM_SHA256withRSA, pke);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExportService#getZIPFileWithTsls(java.io.File, byte[], java.io.File)
	 */
	@Override
	public String getZIPFileWithTsls(File metaInfFolder, byte[ ] signedHashFile, File tslsValetFolder) throws ExportException {
		String zipValet = null;
		// Accedemos a la carpeta META-INF y creamos dentro el fichero signature.p7s que se corresponde con la firma
		File signatureFile = new File(metaInfFolder, "signature.p7s");
		try {
			FileUtils.writeByteArrayToFile(signatureFile, signedHashFile);
		} catch (IOException e) {
			deleteTemporalFiles(tslsValetFolder);
			throw new ExportException(Language.getResWebGeneral(WebGeneralMessages.LOG_EXP021), e);
		}
		
		// Metemos la estructura de carpetas y ficheros generados en un fichero
		// ZIP
		OutputStream fos = null;
		OutputStream zos = null;
		try {
			fos = new ByteArrayOutputStream();
			zos = new ZipOutputStream(fos);
			addDirToZIPArchive(zos, tslsValetFolder);
		} finally {
			// Cerramos recursos
			UtilsResources.safeCloseOutputStream(zos);
			UtilsResources.safeCloseOutputStream(fos);
		}
		
		// Borramos la estructura temporal de ficheros
		deleteTemporalFiles(tslsValetFolder);
		
		if (fos != null) {
			zipValet = Base64.getEncoder().encodeToString(((ByteArrayOutputStream) fos).toByteArray());
		}
	
		return zipValet;
	}

	/**
	 * Recursively adds the files from the specified directory to a ZIP archive.
	 * 
	 * @param zos the output stream representing the ZIP archive
	 * @param tslsValetFolder the folder whose files and subdirectories will be added to the archive
	 * @throws ExportException if an error occurs during the process of adding files to the ZIP archive
	 */
	private void addDirToZIPArchive(OutputStream zos, File tslsValetFolder) throws ExportException {
		File[ ] files = tslsValetFolder.listFiles();
		for (int i = 0; i < files.length; i++) {
			// if the file is directory, use recursion
			if (files[i].isDirectory()) {
				addDirToZIPArchive(zos, files[i]);
				continue;
			}
			InputStream fis = null;
			try {
				byte[ ] buffer = new byte[NumberConstants.NUM1024];
				fis = new FileInputStream(files[i]);
				((ZipOutputStream) zos).putNextEntry(new ZipEntry(files[i].getParentFile().getName() + "/" + files[i].getName()));
				int length;
				while ((length = fis.read(buffer)) > 0) {
					zos.write(buffer, 0, length);
				}
				((ZipOutputStream) zos).closeEntry();
			} catch (IOException e) {
				// Borramos la estructura temporal de ficheros
				deleteTemporalFiles(tslsValetFolder);
				throw new ExportException(Language.getResWebGeneral(WebGeneralMessages.LOG_EXP022), e);
			} finally {
				// Cerramos recursos
				UtilsResources.safeCloseInputStream(fis);
			}
		}
	}
}
