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
 * <b>File:</b><p>es.gob.valet.service.impl.KeystoreServiceImpl.java.</p>
 * <b>Description:</b><p> .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/12/2022.</p>
 * @author Gobierno de España.
 * @version 1.2, 19/09/2023.
 */
package es.gob.valet.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.gob.valet.certificates.CertificateCacheManager;
import es.gob.valet.commons.utils.AESCipher;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsKeystore;
import es.gob.valet.constant.KeystoreConstants;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.CoreLogMessages;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreGeneralMessages;
import es.gob.valet.persistence.configuration.model.entity.CStatusCertificate;
import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;
import es.gob.valet.persistence.configuration.model.repository.KeystoreRepository;
import es.gob.valet.persistence.configuration.model.repository.SystemCertificateRepository;
import es.gob.valet.persistence.configuration.model.utils.KeystoreIdConstants;
import es.gob.valet.persistence.exceptions.CipherException;
import es.gob.valet.persistence.exceptions.CryptographyException;
import es.gob.valet.persistence.utils.UtilsAESCipher;
import es.gob.valet.service.ifaces.IKeystoreService;
import es.gob.valet.tsl.certValidation.impl.common.WrapperX509Cert;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;

/** 
 * <p>Class .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 19/09/2023.
 */
@Service("keystoreServiceImpl")
public class KeystoreServiceImpl implements IKeystoreService{
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(KeystoreServiceImpl.class);
	
	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence. 
	 */
	@Autowired
	KeystoreRepository keystoreRepository;
	
	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence. 
	 */
	@Autowired
	SystemCertificateRepository systemCertificateRepository;
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IKeystoreService#saveCertificateKeystoreCA(byte[], java.lang.String)
	 */
	public void saveCertificateKeystoreCA(byte[] certificateInBytes, String alias) throws KeyStoreException {
		try {
    		// Obtenemos el almacen de Entidad perteneciente a CA
    		Keystore keystoreEntity = keystoreRepository.findByIdKeystore(KeystoreConstants.ID_CA_TRUSTED_KEYSTORE);
    		// Obtenemos la password encriptada del blob, la cual está almacenada en BD
    		String passwordKeystoreBlob = this.getKeystoreDecodedPassword(keystoreEntity);
    		// Obtenemos el keystore java de CA
    		InputStream stream = new ByteArrayInputStream(keystoreEntity.getKeystore());
    		KeyStore keystoreJava;
    		keystoreJava = KeyStore.getInstance(UtilsKeystore.JCEKS);
    		keystoreJava.load(stream, passwordKeystoreBlob.toCharArray());
    		    		
    		// Comprobamos que no exista el certificado dentro del keystore.
			Certificate certificate = keystoreJava.getCertificate(alias);
			if(null != certificate) {
				throw new KeyStoreException(Language.getFormatResCoreGeneral(CoreGeneralMessages.STANDARD_KEYSTORE_074, new Object[ ] { alias }));
			} else {
				// Se obtiene X509Certificate y el WrapperX509Cert
				X509Certificate certToAdd = UtilsCertificate.getX509Certificate(certificateInBytes);
				WrapperX509Cert wrapperX509CertAdd = new WrapperX509Cert(certToAdd);
				// Registramos el certificado en el keystore java de CA y actualizamos el keystore del entity
				keystoreJava.setCertificateEntry(alias, certToAdd);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				keystoreJava.store(baos, new String(UtilsAESCipher.getInstance().decryptMessage(keystoreEntity.getPassword())).toCharArray());
				keystoreEntity.setKeystore(baos.toByteArray());
				keystoreRepository.save(keystoreEntity);
				// Registramos el certificado en la tabla SYSTEM_CERTIFICATE.
				SystemCertificate systemCertificate = new SystemCertificate();
				systemCertificate.setAlias(alias);
				Keystore ks = new Keystore();
				ks.setIdKeystore(KeystoreConstants.ID_CA_TRUSTED_KEYSTORE);
				systemCertificate.setKeystore(ks);
				systemCertificate.setIssuer(wrapperX509CertAdd.getIssuer());
				systemCertificate.setSubject(wrapperX509CertAdd.getSubject());
				systemCertificate.setCountry(wrapperX509CertAdd.getCountry());
				systemCertificate.setIsKey(false);
				CStatusCertificate cStatusCertificate = new CStatusCertificate();
				cStatusCertificate.setIdStatusCertificate(0L);
				systemCertificate.setStatusCert(cStatusCertificate);
				systemCertificate.setValidationCert(Boolean.FALSE);
				systemCertificateRepository.save(systemCertificate);
				// Actualizamos la lista de certificados CA que existen en memoria.
				CertificateCacheManager.getInstance().loadListCertificateCA();
			}
    	} catch (CryptographyException | CommonUtilsException | TSLCertificateValidationException | NoSuchAlgorithmException | CertificateException | CipherException | IOException e) {
			String errorMsg = Language.getFormatResCoreGeneral(CoreGeneralMessages.STANDARD_KEYSTORE_008, new Object[]{e.getMessage()});
			LOGGER.error(errorMsg);
		} 
	}
	
	public void saveCertificateKeystoreOCSP(byte[] certificateInBytes, String alias) {
		try {
			// Obtenemos el almacen de Entidad perteneciente a OCSP
			Keystore keystoreEntity = keystoreRepository.findByIdKeystore(KeystoreIdConstants.ID_OCSP_TRUSTSTORE);
			// Obtenemos la password encriptada del blob, la cual está almacenada en BD
			String passwordKeystoreBlob = this.getKeystoreDecodedPassword(keystoreEntity);
			// Obtenemos el keystore java de CA
			InputStream stream = new ByteArrayInputStream(keystoreEntity.getKeystore());
			KeyStore keystoreJava;
			keystoreJava = KeyStore.getInstance(UtilsKeystore.JCEKS);
			keystoreJava.load(stream, passwordKeystoreBlob.toCharArray());
			
			// Comprobamos que no exista el certificado dentro del keystore.
			Certificate certificate = keystoreJava.getCertificate(alias);
			if(null != certificate) {
				throw new KeyStoreException(Language.getFormatResCoreGeneral(CoreGeneralMessages.STANDARD_KEYSTORE_074, new Object[ ] { alias }));
			} else {
				// Se obtiene X509Certificate y el WrapperX509Cert
				X509Certificate certToAdd = UtilsCertificate.getX509Certificate(certificateInBytes);
				WrapperX509Cert wrapperX509CertAdd = new WrapperX509Cert(certToAdd);
				// Registramos el certificado en el keystore java de CA y actualizamos el keystore del entity
				keystoreJava.setCertificateEntry(alias, certToAdd);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				keystoreJava.store(baos, new String(UtilsAESCipher.getInstance().decryptMessage(keystoreEntity.getPassword())).toCharArray());
				keystoreEntity.setKeystore(baos.toByteArray());
				keystoreRepository.save(keystoreEntity);
				// Registramos el certificado en la tabla SYSTEM_CERTIFICATE.
				SystemCertificate systemCertificate = new SystemCertificate();
				systemCertificate.setAlias(alias);
				Keystore ks = new Keystore();
				ks.setIdKeystore(KeystoreIdConstants.ID_OCSP_TRUSTSTORE);
				systemCertificate.setKeystore(ks);
				systemCertificate.setIssuer(wrapperX509CertAdd.getIssuer());
				systemCertificate.setSubject(wrapperX509CertAdd.getSubject());
				systemCertificate.setCountry(wrapperX509CertAdd.getCountry());
				systemCertificate.setIsKey(false);
				CStatusCertificate cStatusCertificate = new CStatusCertificate();
				cStatusCertificate.setIdStatusCertificate(0L);
				systemCertificate.setStatusCert(cStatusCertificate);
				systemCertificate.setValidationCert(Boolean.FALSE);
				systemCertificateRepository.save(systemCertificate);
				// Actualizamos la lista de certificados que existen en memoria.
				CertificateCacheManager.getInstance().loadListCertificateOCSP();
			}
		} catch (CryptographyException | CommonUtilsException | TSLCertificateValidationException | NoSuchAlgorithmException | CertificateException | CipherException | IOException | KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreGeneral(CoreGeneralMessages.STANDARD_KEYSTORE_008, new Object[]{e.getMessage()});
			LOGGER.error(errorMsg);
		} 
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IKeystoreService#getKeystoreDecodedPassword(es.gob.valet.persistence.configuration.model.entity.Keystore)
	 */
	public String getKeystoreDecodedPassword(Keystore keystore) throws CryptographyException {
		try {
			return new String(AESCipher.getInstance().decryptMessage(keystore.getPassword()));
		} catch (Exception e) {
			String errorMsg = Language.getFormatResCoreValet(CoreLogMessages.ERRORCORE013, new Object[ ] { Language.getResPersistenceConstants(keystore.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(errorMsg, e);
		}
	}
	
}
