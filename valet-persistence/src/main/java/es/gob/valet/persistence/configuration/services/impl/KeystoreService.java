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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.KeystoreService.java.</p>
 * <b>Description:</b><p>Class that implements the communication with the operations of the persistence layer for Keystore.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.4, 18/01/2024.
 */
package es.gob.valet.persistence.configuration.services.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.commons.utils.AESCipher;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.ICoreLogMessages;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.CStatusCertificate;
import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;
import es.gob.valet.persistence.configuration.model.repository.KeystoreRepository;
import es.gob.valet.persistence.configuration.model.utils.IKeystoreIdConstants;
import es.gob.valet.persistence.configuration.model.utils.IStatusCertificateIdConstants;
import es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService;
import es.gob.valet.persistence.exceptions.CryptographyException;
import es.gob.valet.persistence.utils.CryptographyValidationUtils;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for Keystore.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.4, 18/01/2024.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class KeystoreService implements IKeystoreService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(KeystoreService.class);
	
	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private KeystoreRepository keystoreRepository;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getAllKeystore()
	 */
	@Override
	public List<Keystore> getAllKeystore() {
		List<Keystore> listKeystore = new ArrayList<Keystore>();
		listKeystore = keystoreRepository.findAll();
		return listKeystore;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getNameKeystoreById(java.lang.Long)
	 */
	public String getNameKeystoreById(Long idKeystore) {
		String nameKeystore = null;
		Keystore keystore = keystoreRepository.findByIdKeystore(idKeystore);
		if (keystore != null) {
			nameKeystore = keystore.getName();
		}
		return nameKeystore;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getKeystoreById(java.lang.Long, boolean)
	 */
	@Override
	@Transactional // TODO ¿Es necesario al haberlo puesto ya en la interfaz?
	public Keystore getKeystoreById(Long idKeystore, boolean loadSystemCertificates) {
		Keystore result = keystoreRepository.findByIdKeystore(idKeystore);
		if (result != null && loadSystemCertificates && result.getListSystemCertificates() != null) {
			result.getListSystemCertificates().size();
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#saveKeystore(es.gob.valet.persistence.configuration.model.entity.Keystore)
	 */
	@Override
	public Keystore saveKeystore(Keystore keystore) {
		return keystoreRepository.save(keystore);
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getKeystoreById(java.lang.String)
	 */
	public Keystore getKeystoreById(String idKeystore) {
		Keystore result = keystoreRepository.findByIdKeystore(Long.valueOf(idKeystore));
		return result;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#storeCertificate(java.lang.String, java.security.cert.Certificate, java.security.Key, java.lang.Long, boolean, es.gob.valet.persistence.configuration.model.entity.Keystore)
	 */
	public void storeCertificate(String alias, Certificate certificate, Key key, Long statusCert, boolean validationCert, Keystore ksEntity) throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_001, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		try {
			// Comprobamos que el certificado no sea nulo...
			CryptographyValidationUtils.checkIsNotNull(certificate, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_003));
			// Comprobamos que el alias no sea nulo...
			CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_004));
			// Comprobamos que el alias no esté en el almacén
			java.security.KeyStore ksJava = this.getKeystore(ksEntity);
			if (null == ksJava.getCertificate(alias)) {
				// Actualizamos el almacÃ©n de claves fÃ­sicamente. Si la clave es
    			// nula, sÃ³lo se insertarÃ¡ el certificado.
    			addEntryToKsJavaAndUpdateKsEntity(alias, certificate, key, ksEntity);
    			// Guardamos los datos en base de datos y cachÃ©.
    			Long status = statusCert == null ? IStatusCertificateIdConstants.ID_SC_CORRECT : statusCert;
    			saveSystemCertificate(alias, certificate, key, status, validationCert, ksEntity);
			} else {
				LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_074, new Object[ ] { alias }));
			}
		} catch (KeyStoreException | CommonUtilsException | NoSuchAlgorithmException | CertificateException | IOException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_009, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(ValetExceptionConstants.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_002, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		}
	
	}
	
	/**
	 * Saves the system certificate and the keystore in data base.
	 * @param alias Alias for the certificate in the keystore.
	 * @param cert Certificate added in the keystore.
	 * @param key Private key added in the keystore (it could be <code>null</code>).
	 * @param statusCert Status of the certificate added.
	 * @param validationCert parameter that contain if certificate is valid.
	 * @param ksEntity Parameter that represents entity to keystore obtain. 
	 * @throws CommonUtilsException In case of some error extracting the issuer and subject from the input certificate.
	 * @throws CertificateEncodingException In case of some error building the X509 Certificate.
	 */
	private void saveSystemCertificate(String alias, Certificate cert, Key key, Long statusCert, boolean validationCert, Keystore ksEntity) throws CertificateEncodingException, CommonUtilsException {
		
		// Se crea una nueva instancia de SystemCertificate.
		SystemCertificate sc = new SystemCertificate();
		// Se le asignan los valores...
		sc.setAlias(alias);
		sc.setIsKey(key != null);
		X509Certificate x509cert = UtilsCertificate.getX509Certificate(cert.getEncoded());
		sc.setIssuer(UtilsCertificate.getCertificateIssuerId(x509cert));
		sc.setSubject(UtilsCertificate.getCertificateId(x509cert));
		CStatusCertificate cStatusCert = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCStatusCertificateService().getCStatusCertificateById(statusCert);
		sc.setStatusCert(cStatusCert);
		sc.setValidationCert(validationCert);
		// Se obtiene el paÃ­s del almacÃ©n de los certificados
		String countryOfCertificate = UtilsCertificate.getCountryOfTheCertificateString(x509cert);
		sc.setCountry(countryOfCertificate);
		
		// Guardamos el system certificate.
		sc.setKeystore(ksEntity);
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().saveSystemCertificate(sc);
		
	}
	
	/**
	 * Method that inserts an entry inside of a keystore.
	 * @param alias Parameter that represents the alias of the entry to store.
	 * @param cert Parameter that represents the certificate associated to the new entry.
	 * @param key Parameter that represents the private key associated to the new entry.
	 * @param ksEntity Parameter that represents entity to keystore obtain.
	 * @throws CommonUtilsException In case of some error extracting the issuer and subject from the input certificate.
	 * @throws KeyStoreException If there is some error inserting the entry into the keystore.
	 * @throws CryptographyException If there is some error decrypting the password of the keystore.
	 */
	private void addEntryToKsJavaAndUpdateKsEntity(String alias, Certificate cert, Key key, Keystore ksEntity) throws KeyStoreException, CryptographyException, CommonUtilsException {
		try {
			// Obtenemos la password
			String passKs = this.getKeystoreDecodedPassword(ksEntity);

			// Crea un objeto KeyStore Java
			KeyStore ksJava = KeyStore.getInstance(ksEntity.getKeystoreType());

			// Carga el keystore de entrada desde el array de bytes
			ByteArrayInputStream bais = new ByteArrayInputStream(ksEntity.getKeystore());
			ksJava.load(bais, passKs.toCharArray());

			// AÃ±ado la nueva entrada al keystore de Java
			if (key == null) {
				ksJava.setCertificateEntry(alias, cert);
			} else {
				ksJava.setKeyEntry(alias, key, passKs.toCharArray(), new Certificate[ ] { cert });
			}

			// Convierto nuevamente el keystore de Java en un array de bytes de
			// salida
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ksJava.store(baos, passKs.toCharArray());

			// Actualizo el keystore de Java en la entidad e incremento su
			// versiÃ³n puesto que lo hemos actualizado
			ksEntity.setKeystore(baos.toByteArray());
			ksEntity.setVersion(ksEntity.getVersion() + 1);

			keystoreRepository.save(ksEntity);
		} catch (Exception e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_009, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName())});
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(ValetExceptionConstants.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_002, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		}
	}
	
	/**
	 * Method that obtains the decoded password of the keystore represented by {@link #keystore}.
	 * @return the decoded password of the keystore represented by {@link #String}.
	 * @throws CryptographyException If the method fails.
	 */
	public String getKeystoreDecodedPassword(Keystore keystore) throws CryptographyException {
		try {
			return new String(AESCipher.getInstance().decryptMessage(keystore.getPassword()));
		} catch (Exception e) {
			String errorMsg = Language.getFormatResCoreValet(ICoreLogMessages.ERRORCORE013, new Object[ ] { Language.getResPersistenceConstants(keystore.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(errorMsg, e);
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#updateCertificateAlias(java.lang.String, java.lang.String, es.gob.valet.persistence.configuration.model.entity.Keystore)
	 */
	public void updateCertificateAlias(String oldEntryAlias, String newEntryAlias, Keystore ksEntity) throws CryptographyException {
		
		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_010, new Object[ ] { oldEntryAlias, newEntryAlias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		try {
			// Comprobamos que el alias original no sea nulo
			CryptographyValidationUtils.checkIsNotNull(oldEntryAlias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_012));

			// Comprobamos que el nuevo alias no sea nulo
			CryptographyValidationUtils.checkIsNotNull(newEntryAlias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_013));

			// Comprobamos que el nuevo alias no exista ya en el keystore.
			if (getCertificate(newEntryAlias, ksEntity) != null || getPrivateKey(newEntryAlias, ksEntity) != null) {
				throw new CryptographyException(ValetExceptionConstants.COD_190, Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_073, new Object[ ] { oldEntryAlias, newEntryAlias }));
			}

			// Obtenemos la clave decodificada del almacén de claves
			String keystoreDecodedPass = this.getKeystoreDecodedPassword(ksEntity);
			
			// Actualizamos el almacén de claves físicamente.
			updateEntryAliasKsJavaAndKsEntity(oldEntryAlias, newEntryAlias, keystoreDecodedPass, ksEntity);

			// Guardamos los datos en base de datos y caché.
			updateSystemCertificate(oldEntryAlias, newEntryAlias, ksEntity);
			
		} catch (KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_016, new Object[ ] { oldEntryAlias, newEntryAlias, Language.getResPersistenceConstants(ksEntity.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(ValetExceptionConstants.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_011, new Object[ ] { oldEntryAlias, newEntryAlias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		}
		
	}
	
	/**
	 * Updates the system certificate and the save the keystore in data base.
	 * @param oldEntryAlias Parameter that represents the old alias of the entry.
	 * @param newEntryAlias Parameter that represents the new alias of the entry.
	 * @param ksEntity Parameter that represents entity to keystore obtain. 
	 */
	private void updateSystemCertificate(String oldEntryAlias, String newEntryAlias, Keystore ksEntity) {
		// Obtenemos de base de datos el SystemCertificate asociado a ese alias
		// y keystore.
		SystemCertificate sc = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getSystemCertificateByAliasAndKeystoreId(oldEntryAlias, ksEntity.getIdKeystore());
		// Le modificamos el alias.
		sc.setAlias(newEntryAlias);
		
		// Guardamos el system certificate.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().saveSystemCertificate(sc);		
	}

	/**
	 * Method that changes the alias of an entry stored inside of a keystore.
	 * @param oldEntryAlias Parameter that represents the alias to change.
	 * @param newEntryAlias Parameter that represents the new alias.
	 * @param entryDecodedPass Parameter that represents the decoded password of the entry.
	 * @param ksEntity Parameter that represents entity to keystore obtain. 
	 * @throws KeyStoreException If the keystore has not been initialized (loaded).
	 */
	private void updateEntryAliasKsJavaAndKsEntity(String oldEntryAlias, String newEntryAlias, String entryDecodedPass, Keystore ksEntity) throws KeyStoreException {
		try {
			char[ ] entryPass = null;

			// Obtenemos la password
			String passKs = this.getKeystoreDecodedPassword(ksEntity);

			// Crea un objeto KeyStore Java
			KeyStore ksJava = KeyStore.getInstance(ksEntity.getKeystoreType());

			// Carga el keystore de entrada desde el array de bytes
			ByteArrayInputStream bais = new ByteArrayInputStream(ksEntity.getKeystore());
			ksJava.load(bais, passKs.toCharArray());

			if (ksJava.containsAlias(oldEntryAlias)) {
				if (ksJava.isCertificateEntry(oldEntryAlias)) {
					Certificate cert = ksJava.getCertificate(oldEntryAlias);
					ksJava.deleteEntry(oldEntryAlias);
					ksJava.setCertificateEntry(newEntryAlias, cert);
				} else if (ksJava.isKeyEntry(oldEntryAlias)) {
					entryPass = entryDecodedPass.toCharArray();
					Key key = ksJava.getKey(oldEntryAlias, entryPass);
					Certificate[ ] certChain = ksJava.getCertificateChain(oldEntryAlias);
					ksJava.deleteEntry(oldEntryAlias);
					ksJava.setKeyEntry(newEntryAlias, key, entryPass, certChain);
				}
			}

			// Convierto nuevamente el keystore de Java en un array de bytes de
			// salida
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ksJava.store(baos, passKs.toCharArray());

			// Actualizo el keystore de Java en la entidad e incremento su
			// versión puesto que lo hemos actualizado
			ksEntity.setKeystore(baos.toByteArray());
			ksEntity.setVersion(ksEntity.getVersion() + 1);

			keystoreRepository.save(ksEntity);
			
		} catch (CryptographyException | CertificateException
				| NoSuchAlgorithmException | IOException
				| UnrecoverableKeyException e) {
			throw new KeyStoreException(e);
		}
	}

	/**
	 * Method that obtains a private key from the alias.
	 * @param alias Parameter that represents the alias of the private key to obtain.
	 * @param ksEntity Parameter that represents entity to keystore obtain. 
	 * @return an object that represents the private.
	 * @throws CryptographyException If the method fails.
	 */
	private Object getPrivateKey(String alias, Keystore ksEntity) throws CryptographyException {
		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_021, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		try {
			// Comprobamos que el alias no sea nulo...
			CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_004));
			// Devolvemos la clave privada del almacén de claves.
			return getPrivateKeyFromKeystore(alias, ksEntity);
		} catch (KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_024, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(ValetExceptionConstants.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_022, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		}
	}

	/**
	 * Method that obtains a private key from the keystore.
	 * @param alias Parameter that represents the alias of the associated entry.
	 * @param ksEntity Parameter that represents entity to keystore obtain.
	 * @return an object that represents the private key.
	 * @throws KeyStoreException If the keystore has not been initialized (loaded).
	 */
	private PrivateKey getPrivateKeyFromKeystore(String alias, Keystore ksEntity) throws KeyStoreException {
		PrivateKey pk = null;
		try {
			// Obtenemos la password
			String passKs = this.getKeystoreDecodedPassword(ksEntity);

			// Crea un objeto KeyStore Java
			KeyStore ksJava = KeyStore.getInstance(ksEntity.getKeystoreType());

			// Carga el keystore de entrada desde el array de bytes
			ByteArrayInputStream bais = new ByteArrayInputStream(ksEntity.getKeystore());
			ksJava.load(bais, passKs.toCharArray());

			if (ksJava.isKeyEntry(alias)) {
				pk = (PrivateKey) ksJava.getKey(alias, passKs.toCharArray());
			}
		}catch (CryptographyException | CertificateException | NoSuchAlgorithmException | IOException | KeyStoreException | UnrecoverableKeyException  e) {
			throw new KeyStoreException(e);
		}
		return pk;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getCertificate(java.lang.String, es.gob.valet.persistence.configuration.model.entity.Keystore)
	 */
	public Certificate getCertificate(String alias, Keystore ksEntity) throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_017, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		try {
			// Comprobamos que el alias no sea nulo.
			CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_004));
			// Devolvemos el certificado del almacén de claves.
			return getCertificateFromKeystore(alias, ksEntity);
		} catch (KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_020, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(ValetExceptionConstants.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_018, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		}

	}
	
	/**
	 * Method that obtains a certificate from the keystore.
	 * @param alias Parameter that represents the alias of the associated entry.
	 * @param ksEntity Parameter that represents entity to keystore obtain.
	 * @return an object that represents the certificate.
	 * @throws KeyStoreException If the keystore has not been initialized (loaded).
	 */
	private Certificate getCertificateFromKeystore(String alias, Keystore ksEntity) throws KeyStoreException {
		Certificate cert = null;
		try {
			// Obtenemos la password
			String passKs = this.getKeystoreDecodedPassword(ksEntity);

			// Crea un objeto KeyStore Java
			KeyStore ksJava = KeyStore.getInstance(ksEntity.getKeystoreType());

			// Carga el keystore de entrada desde el array de bytes
			ByteArrayInputStream bais = new ByteArrayInputStream(ksEntity.getKeystore());
			ksJava.load(bais, passKs.toCharArray());

			if (ksJava.isCertificateEntry(alias) || ksJava.isKeyEntry(alias)) {
				cert = ksJava.getCertificate(alias);
			}
		}catch (CryptographyException | CertificateException | NoSuchAlgorithmException | IOException | KeyStoreException  e) {
			throw new KeyStoreException(e);
		}
		return cert;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#removeEntry(java.lang.String, es.gob.valet.persistence.configuration.model.entity.Keystore)
	 */
	public void removeEntry(String alias, Keystore ksEntity) throws CryptographyException {
		
		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_070, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));

		// Comprobamos que el alias no sea nulo.
		CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_004));

		try {
			// Obtenemos la password
			String passKs = this.getKeystoreDecodedPassword(ksEntity);

			// Crea un objeto KeyStore Java
			KeyStore ksJava = KeyStore.getInstance(ksEntity.getKeystoreType());

			// Carga el keystore de entrada desde el array de bytes
			ByteArrayInputStream bais = new ByteArrayInputStream(ksEntity.getKeystore());
			ksJava.load(bais, passKs.toCharArray());
			
			if (ksJava.containsAlias(alias)) {
				ksJava.deleteEntry(alias);
				
				// Convierto nuevamente el keystore de Java en un array de bytes de
				// salida
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ksJava.store(baos, passKs.toCharArray());

				// Actualizo el keystore de Java en la entidad e incremento su
				// versión puesto que lo hemos actualizado
				ksEntity.setKeystore(baos.toByteArray());
				ksEntity.setVersion(ksEntity.getVersion() + 1);

				keystoreRepository.save(ksEntity);
				
				removeSystemCertificate(alias, ksEntity);
			}
			
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_072, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(ValetExceptionConstants.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_071, new Object[ ] { alias, Language.getResPersistenceConstants(ksEntity.getTokenName()) }));
		}
		
	}

	/**
	 * Removes the system certificate with the input alias and update the keystore in DDBB.
	 * @param alias Alias of the system certificate.
	 * @param ksEntity Parameter that represents entity to keystore obtain.
	 */
	private void removeSystemCertificate(String alias, Keystore ksEntity) {
		// Eliminamos de base de datos el system certificate con ese alias y de
		// este keystore.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().deleteSystemCertificate(alias, ksEntity.getIdKeystore());
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getListCertificateCA()
	 */
	public List<X509Certificate> getListCertificateCA() throws CryptographyException {
		
		Keystore ksEntity = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(String.valueOf(IKeystoreIdConstants.ID_CA_TRUSTSTORE));
		
		return this.getListCertificateFromKs(ksEntity);
	}

	/**
	 * Retrieves a list of X.509 certificates from the provided Keystore entity.
	 *
	 * @param ksEntity The Keystore entity containing information such as type, keystore data, and password.
	 * @return A List of X.509 certificates obtained from the keystore.
	 * @throws CryptographyException If an error related to cryptography occurs during the certificate retrieval.
	 * @see Keystore
	 */
	public List<X509Certificate> getListCertificateFromKs(Keystore ksEntity) throws CryptographyException {
		 // Lista para almacenar los certificados
        List<X509Certificate> certList = new ArrayList<>();
        try {
            // Obtenemos la password
            String passKs = this.getKeystoreDecodedPassword(ksEntity);

            // Crea un objeto KeyStore Java
            KeyStore ksJava = KeyStore.getInstance(ksEntity.getKeystoreType());

            // Carga el keystore de entrada desde el array de bytes
            ByteArrayInputStream bais = new ByteArrayInputStream(ksEntity.getKeystore());
            ksJava.load(bais, passKs.toCharArray());

            // Obtiene la lista de alias en el keystore
            Enumeration<String> aliasEnum = ksJava.aliases();
            
            // Recorre los alias y obtiene los certificados asociados
            while (aliasEnum.hasMoreElements()) {
                String alias = aliasEnum.nextElement();
                Certificate cert = ksJava.getCertificate(alias);
                certList.add((X509Certificate) cert);
            }
        
        } catch (CryptographyException | KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
        	String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_063, new Object[ ] { Language.getResPersistenceConstants(ksEntity.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(ValetExceptionConstants.COD_190, errorMsg, e);
        }
        
        return certList;
    }
	
	/**
	 * Retrieves a mapping of alias names to X.509 certificates from the provided Keystore entity.
	 *
	 * @param ksEntity The Keystore entity containing information such as type, keystore data, and password.
	 * @return A Map where the keys are alias names and the values are corresponding X.509 certificates from the keystore.
	 * @throws CryptographyException If an error related to cryptography occurs during the certificate retrieval.
	 * @see Keystore
	 */
	public Map<String, X509Certificate> getMapCertificateFromKs(Keystore ksEntity) throws CryptographyException {
		// Lista para almacenar los certificados
		Map<String, X509Certificate> mapAliasCert = new HashMap<String, X509Certificate>();
		try {
			// Obtenemos la password
			String passKs = this.getKeystoreDecodedPassword(ksEntity);

			// Crea un objeto KeyStore Java
			KeyStore ksJava = KeyStore.getInstance(ksEntity.getKeystoreType());

			// Carga el keystore de entrada desde el array de bytes
			ByteArrayInputStream bais = new ByteArrayInputStream(ksEntity.getKeystore());
			ksJava.load(bais, passKs.toCharArray());

			// Obtiene la lista de alias en el keystore
			Enumeration<String> aliasEnum = ksJava.aliases();

			// Recorre los alias y obtiene los certificados asociados
			while (aliasEnum.hasMoreElements()) {
				String alias = aliasEnum.nextElement();
				Certificate cert = ksJava.getCertificate(alias);
				mapAliasCert.put(alias, (X509Certificate) cert);
			}

		} catch (CryptographyException | KeyStoreException
				| CertificateException | NoSuchAlgorithmException
				| IOException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_063, new Object[ ] { Language.getResPersistenceConstants(ksEntity.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(ValetExceptionConstants.COD_190, errorMsg, e);
		}

		return mapAliasCert;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getMapAliasX509CertCA()
	 */
	public Map<String, X509Certificate> getMapAliasX509CertCA() throws CryptographyException {

		Keystore ksEntity = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(String.valueOf(IKeystoreIdConstants.ID_CA_TRUSTSTORE));

		return this.getMapCertificateFromKs(ksEntity);
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getMapAliasX509CertOCSP()
	 */
	public Map<String, X509Certificate> getMapAliasX509CertOCSP() throws CryptographyException {

		Keystore ksEntity = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(String.valueOf(IKeystoreIdConstants.ID_OCSP_TRUSTSTORE));

		return this.getMapCertificateFromKs(ksEntity);
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService#getKeystore(es.gob.valet.persistence.configuration.model.entity.Keystore)
	 */
	public java.security.KeyStore getKeystore(Keystore ksEntity) throws CryptographyException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		
		// Obtenemos la password
        String passKs = this.getKeystoreDecodedPassword(ksEntity);

        // Crea un objeto KeyStore Java
        KeyStore ksJava = KeyStore.getInstance(ksEntity.getKeystoreType());

        // Carga el keystore de entrada desde el array de bytes
        ByteArrayInputStream bais = new ByteArrayInputStream(ksEntity.getKeystore());
        ksJava.load(bais, passKs.toCharArray());
		
		return ksJava;
	}
}
