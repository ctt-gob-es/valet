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
 * <b>File:</b><p>es.gob.valet.crypto.keystore.StandardKeystoreFacade.java.</p>
 * <b>Description:</b><p>Class that manages all the operations related with JCE, JCEKS and PKCS#12 keystores.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 27/04/2022.
 */
package es.gob.valet.crypto.keystore;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Logger;

import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.crypto.exception.CryptographyException;
import es.gob.valet.crypto.utils.CryptographyValidationUtils;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.keystore.elements.KeystoreCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException;
import es.gob.valet.persistence.configuration.model.entity.CStatusCertificate;
import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;
import es.gob.valet.persistence.configuration.model.utils.IStatusCertificateIdConstants;
import es.gob.valet.persistence.exceptions.CipherException;
import es.gob.valet.persistence.utils.UtilsAESCipher;

/**
 * <p>Class that manages all the operations related with JCE, JCEKS and PKCS#12 keystores.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 27/04/2022.
 */
public class StandardKeystoreFacade implements IKeystoreFacade {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(StandardKeystoreFacade.class);

	/**
	 * Attribute that represents the information about the keystore from the cache system.
	 */
	private KeystoreCacheObject keystoreCacheObject = null;

	/**
	 * Constructor method for the class StandardKeystoreFacade.java.
	 */
	private StandardKeystoreFacade() {
		super();
	}

	/**
	 * Constructor method for the class StandardKeystoreFacade.java.
	 * @param kco Keystore Cache Object representation for the keystore.
	 */
	public StandardKeystoreFacade(KeystoreCacheObject kco) {
		this();
		keystoreCacheObject = kco;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getAllCertificateAlias()
	 */
	@Override
	public List<String> getAllCertificateAlias() throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_031, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		try {
			// Devolvemos la lista de alias de certificados.
			return getAliases(true);
		} catch (KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_034, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_032, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		}

	}

	/**
	 * Method that obtains a list with the alias of the entries stored inside of a keystore.
	 * @param forCertificates Parameter that indicates whether the entries will be certificates (true) or keys (false).
	 * @return a list with the found alias.
	 * @throws KeyStoreException If the keystore has not been initialized (loaded).
	 */
	private List<String> getAliases(boolean forCertificates) throws KeyStoreException {

		List<String> result = new ArrayList<String>();
		KeyStore ks = keystoreCacheObject.getKeystore();
		Enumeration<String> listAlias = ks.aliases();
		while (listAlias.hasMoreElements()) {
			String alias = (String) listAlias.nextElement();
			if (forCertificates && ks.isCertificateEntry(alias) || !forCertificates && ks.isKeyEntry(alias)) {
				result.add(alias);
			}
		}
		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getCertificate(java.lang.String)
	 */
	@Override
	public Certificate getCertificate(String alias) throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_017, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		try {
			// Comprobamos que el alias no sea nulo.
			CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_004));
			// Devolvemos el certificado del almacén de claves.
			return getCertificateFromKeystore(alias);
		} catch (KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_020, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_018, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		}

	}

	/**
	 * Method that obtains a certificate from the keystore.
	 * @param alias Parameter that represents the alias of the associated entry.
	 * @return an object that represents the certificate.
	 * @throws KeyStoreException If the keystore has not been initialized (loaded).
	 */
	private Certificate getCertificateFromKeystore(String alias) throws KeyStoreException {
		Certificate cert = null;
		KeyStore ks = keystoreCacheObject.getKeystore();
		if (ks.isCertificateEntry(alias) || ks.isKeyEntry(alias)) {
			cert = ks.getCertificate(alias);
		}
		return cert;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getAllCertificates()
	 */
	@Override
	public List<Certificate> getAllCertificates() throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_060, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		try {
			// Calculamos y devolvemos la lista de certificados...
			List<Certificate> result = new ArrayList<Certificate>();
			KeyStore ks = keystoreCacheObject.getKeystore();
			Enumeration<String> listAlias = ks.aliases();
			while (listAlias.hasMoreElements()) {
				String alias = (String) listAlias.nextElement();
				if (ks.isCertificateEntry(alias)) {
					result.add(ks.getCertificate(alias));
				}
			}
			return result;
		} catch (KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_063, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_061, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getPrivateKey(java.lang.String)
	 */
	@Override
	public PrivateKey getPrivateKey(String alias) throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_021, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		try {
			// Comprobamos que el alias no sea nulo...
			CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_004));
			// Devolvemos la clave privada del almacén de claves.
			return getPrivateKeyFromKeystore(alias);
		} catch (UnrecoverableKeyException | KeyStoreException
				| NoSuchAlgorithmException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_024, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_022, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		}

	}

	/**
	 * Method that obtains a private key from the keystore.
	 * @param alias Parameter that represents the alias of the associated entry.
	 * @return an object that represents the private key.
	 * @throws UnrecoverableKeyException If the key cannot be recovered (e.g., the given password is wrong).
	 * @throws KeyStoreException If the keystore has not been initialized (loaded).
	 * @throws NoSuchAlgorithmException If the algorithm for recovering the key cannot be found.
	 * @throws CryptographyException If there is some error decrypting the password of the keystore.
	 */
	private PrivateKey getPrivateKeyFromKeystore(String alias) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CryptographyException {

		PrivateKey pk = null;
		KeyStore ks = keystoreCacheObject.getKeystore();
		if (ks.isKeyEntry(alias)) {
			pk = (PrivateKey) ks.getKey(alias, getKeystoreDecodedPassword().toCharArray());
		}
		return pk;

	}

	/**
	 * Method that obtains the decoded password of the keystore represented by {@link #keystoreCacheObject}.
	 * @return the decoded password of the keystore represented by {@link #keystoreCacheObject}.
	 * @throws CryptographyException If the method fails.
	 */
	private String getKeystoreDecodedPassword() throws CryptographyException {

		try {
			return new String(UtilsAESCipher.getInstance().decryptMessage(keystoreCacheObject.getPassword()));
		} catch (CipherException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_014, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#storeCertificate(java.lang.String, java.security.cert.Certificate, java.security.Key, java.lang.Long)
	 */
	@Override
	public KeystoreCacheObject storeCertificate(String alias, Certificate certificate, Key key, Long statusCert) throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_001, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		try {
			// Comprobamos que el certificado no sea nulo...
			CryptographyValidationUtils.checkIsNotNull(certificate, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_003));
			// Comprobamos que el alias no sea nulo...
			CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_004));
			// Actualizamos el almacén de claves físicamente. Si la clave es
			// nula, sólo se insertará el certificado.
			addEntryToKeystore(alias, certificate, key);
			// Guardamos los datos en base de datos y caché.
			Long status = statusCert == null ? IStatusCertificateIdConstants.ID_SC_CORRECT : statusCert;
			saveSystemCertificateAndUpdateKeystore(alias, certificate, key, status);
		} catch (KeyStoreException | CertificateEncodingException
				| CommonUtilsException | KeystoreCacheException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_009, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_002, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		}
		// Devolvemos los datos en caché actualizados del almacén de claves
		return keystoreCacheObject;

	}

	/**
	 * Method that inserts an entry inside of a keystore.
	 * @param alias Parameter that represents the alias of the entry to store.
	 * @param cert Parameter that represents the certificate associated to the new entry.
	 * @param key Parameter that represents the private key associated to the new entry.
	 * @throws KeyStoreException If there is some error inserting the entry into the keystore.
	 * @throws CryptographyException If there is some error decrypting the password of the keystore.
	 */
	private void addEntryToKeystore(String alias, Certificate cert, Key key) throws KeyStoreException, CryptographyException {
		char[ ] keystorePass = getKeystoreDecodedPassword().toCharArray();
		KeyStore ks = keystoreCacheObject.getKeystore();
		if (key == null) {
			ks.setCertificateEntry(alias, cert);
		} else {
			ks.setKeyEntry(alias, key, keystorePass, new Certificate[ ] { cert });
		}
		keystoreCacheObject.setKeystore(ks);
		keystoreCacheObject.setKeystoreBytes(null);
		keystoreCacheObject.setVersion(keystoreCacheObject.getVersion() + 1);
	}

	/**
	 * Saves the system certificate and the keystore in data base and cache.
	 * @param alias Alias for the certificate in the keystore.
	 * @param cert Certificate added in the keystore.
	 * @param key Private key added in the keystore (it could be <code>null</code>).
	 * @param statusCert Status of the certificate added.
	 * @throws CommonUtilsException In case of some error extracting the issuer and subject from the input certificate.
	 * @throws CertificateEncodingException In case of some error building the X509 Certificate.
	 * @throws KeystoreCacheException In case of some error adding the keystore in the cache.
	 */
	private void saveSystemCertificateAndUpdateKeystore(String alias, Certificate cert, Key key, Long statusCert) throws CertificateEncodingException, CommonUtilsException, KeystoreCacheException {

		// Recuperamos el keystore a actualizar de base de datos.
		Keystore ks = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(keystoreCacheObject.getIdKeystore(), false);
		// Le actualizamos su contenido y versionado.
		ks.setKeystore(keystoreCacheObject.getKeystoreBytes());
		ks.setVersion(keystoreCacheObject.getVersion());

		// Se crea una nueva instancia de SystemCertificate.
		SystemCertificate sc = new SystemCertificate();
		// Se le asignan los valores...
		sc.setAlias(alias);
		sc.setKeystore(ks);
		sc.setIsKey(key != null);
		X509Certificate x509cert = UtilsCertificate.getX509Certificate(cert.getEncoded());
		sc.setIssuer(UtilsCertificate.getCertificateIssuerId(x509cert));
		sc.setSubject(UtilsCertificate.getCertificateId(x509cert));
		CStatusCertificate cStatusCert = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCStatusCertificateService().getCStatusCertificateById(statusCert);
		sc.setStatusCert(cStatusCert);
		// Se obtiene el país del almacén de los certificados
		String countryOfCertificate = UtilsCertificate.getCountryOfTheCertificateString(x509cert);
		sc.setCountry(countryOfCertificate);

		// Guardamos el keystore.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().saveKeystore(ks);
		// Guardamos el system certificate.
		sc.setKeystore(ks);
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().saveSystemCertificate(sc);

		// Actualizamos el keystore en la caché.
		ConfigurationCacheFacade.keystoreAddUpdateKeystore(keystoreCacheObject);

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#updateCertificateAlias(java.lang.String, java.lang.String)
	 */
	@Override
	public KeystoreCacheObject updateCertificateAlias(String oldEntryAlias, String newEntryAlias) throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_010, new Object[ ] { oldEntryAlias, newEntryAlias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		try {
			// Comprobamos que el alias original no sea nulo
			CryptographyValidationUtils.checkIsNotNull(oldEntryAlias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_012));

			// Comprobamos que el nuevo alias no sea nulo
			CryptographyValidationUtils.checkIsNotNull(newEntryAlias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_013));

			// Comprobamos que el nuevo alias no exista ya en el keystore.
			if (getCertificate(newEntryAlias) != null || getPrivateKey(newEntryAlias) != null) {
				throw new CryptographyException(IValetException.COD_190, Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_073, new Object[ ] { oldEntryAlias, newEntryAlias }));
			}

			// Obtenemos la clave decodificada del almacén de claves
			String keystoreDecodedPass = getKeystoreDecodedPassword();

			// Actualizamos el almacén de claves físicamente.
			updateEntryAlias(oldEntryAlias, newEntryAlias, keystoreDecodedPass);

			// Guardamos los datos en base de datos y caché.
			saveSystemCertificateAndUpdateKeystore(oldEntryAlias, newEntryAlias);

		} catch (UnrecoverableKeyException | KeyStoreException
				| NoSuchAlgorithmException | KeystoreCacheException
				| CommonUtilsException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_016, new Object[ ] { oldEntryAlias, newEntryAlias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_011, new Object[ ] { oldEntryAlias, newEntryAlias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		}
		// Devolvemos los datos en caché actualizados del almacén de claves
		return keystoreCacheObject;

	}

	/**
	 * Method that changes the alias of an entry stored inside of a keystore.
	 * @param oldEntryAlias Parameter that represents the alias to change.
	 * @param newEntryAlias Parameter that represents the new alias.
	 * @param entryDecodedPass Parameter that represents the decoded password of the entry.
	 * @throws NoSuchAlgorithmException If the algorithm for recovering the key cannot be found.
	 * @throws KeyStoreException If the keystore has not been initialized (loaded).
	 * @throws UnrecoverableKeyException If the key cannot be recovered (e.g., the given password is wrong).
	 */
	private void updateEntryAlias(String oldEntryAlias, String newEntryAlias, String entryDecodedPass) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException {
		char[ ] entryPass = null;
		KeyStore ks = keystoreCacheObject.getKeystore();

		if (ks.containsAlias(oldEntryAlias)) {
			if (ks.isCertificateEntry(oldEntryAlias)) {
				Certificate cert = ks.getCertificate(oldEntryAlias);
				ks.deleteEntry(oldEntryAlias);
				ks.setCertificateEntry(newEntryAlias, cert);
			} else if (ks.isKeyEntry(oldEntryAlias)) {
				entryPass = entryDecodedPass.toCharArray();
				Key key = ks.getKey(oldEntryAlias, entryPass);
				Certificate[ ] certChain = ks.getCertificateChain(oldEntryAlias);
				ks.deleteEntry(oldEntryAlias);
				ks.setKeyEntry(newEntryAlias, key, entryPass, certChain);
			}
		}
		keystoreCacheObject.setKeystore(ks);
		keystoreCacheObject.setKeystoreBytes(null);
		keystoreCacheObject.setVersion(keystoreCacheObject.getVersion() + 1);
	}

	/**
	 * Updates the system certificate and the save the keystore in data base and cache.
	 * @param oldEntryAlias Parameter that represents the old alias of the entry.
	 * @param newEntryAlias Parameter that represents the new alias of the entry.
	 * @throws KeystoreCacheException In case of some error adding the keystore in the cache.
	 * @throws CommonUtilsException 
	 */
	private void saveSystemCertificateAndUpdateKeystore(String oldEntryAlias, String newEntryAlias) throws KeystoreCacheException, CommonUtilsException {

		// Recuperamos el keystore a actualizar de base de datos.
		Keystore ks = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(keystoreCacheObject.getIdKeystore(), false);
		// Le actualizamos su contenido y versionado.
		ks.setKeystore(keystoreCacheObject.getKeystoreBytes());
		ks.setVersion(keystoreCacheObject.getVersion());

		// Obtenemos de base de datos el SystemCertificate asociado a ese alias
		// y keystore.
		SystemCertificate sc = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().getSystemCertificateByAliasAndKeystoreId(oldEntryAlias, ks.getIdKeystore());
		// Le modificamos el alias.
		sc.setAlias(newEntryAlias);

		// Guardamos el keystore.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().saveKeystore(ks);
		// Guardamos el system certificate.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().saveSystemCertificate(sc);

		// Actualizamos el keystore en la caché.
		ConfigurationCacheFacade.keystoreAddUpdateKeystore(keystoreCacheObject);

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#removeEntry(java.lang.String)
	 */
	@Override
	public KeystoreCacheObject removeEntry(String alias) throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_070, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));

		// Comprobamos que el alias no sea nulo.
		CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_004));

		// Obtenemos el almacén de claves.
		KeyStore ks = keystoreCacheObject.getKeystore();
		try {
			if (ks.containsAlias(alias)) {
				ks.deleteEntry(alias);
				keystoreCacheObject.setKeystore(ks);
				keystoreCacheObject.setKeystoreBytes(null);
				keystoreCacheObject.setVersion(keystoreCacheObject.getVersion() + 1);
				removeSystemCertificateAndSaveKeystore(alias);
			}
			// Devolvemos la representación en caché del keystore modificada.
			return keystoreCacheObject;
		} catch (KeyStoreException | KeystoreCacheException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_072, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_071, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		}

	}

	/**
	 * Removes the system certificate with the input alias and update the keystore in DDBB and cache.
	 * @param alias Alias of the system certificate.
	 * @throws KeystoreCacheException In case of some error updating the keystore in the cache.
	 */
	private void removeSystemCertificateAndSaveKeystore(String alias) throws KeystoreCacheException {

		// Recuperamos el keystore a actualizar de base de datos.
		Keystore ks = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(keystoreCacheObject.getIdKeystore(), false);
		// Le actualizamos su contenido y versionado.
		ks.setKeystore(keystoreCacheObject.getKeystoreBytes());
		ks.setVersion(keystoreCacheObject.getVersion());

		// Eliminamos de base de datos el system certificate con ese alias y de
		// este keystore.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().deleteSystemCertificate(alias, ks.getIdKeystore());
		// Guardamos el keystore.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().saveKeystore(ks);

		// Actualizamos el keystore en la caché.
		ConfigurationCacheFacade.keystoreAddUpdateKeystore(keystoreCacheObject);

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#removeEntriesList(java.util.List)
	 */
	@Override
	public KeystoreCacheObject removeEntriesList(List<String> aliasList) throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_055, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		try {

			// Comprobamos que la lista de alias no es nula ni vacía.
			CryptographyValidationUtils.checkIsNotNullAndNotEmpty(aliasList, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_057));

			// Obtenemos el almacén de claves.
			KeyStore ks = keystoreCacheObject.getKeystore();

			// Recorremos la lista de alias...
			for (String alias: aliasList) {
				// Eliminamos cada alias del almacén si es que existe en este.
				LOGGER.debug(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_058, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
				if (ks.containsAlias(alias)) {
					ks.deleteEntry(alias);
				}
			}

			keystoreCacheObject.setKeystore(ks);
			keystoreCacheObject.setKeystoreBytes(null);
			keystoreCacheObject.setVersion(keystoreCacheObject.getVersion() + 1);

			// Eliminamos de base de datos los system certificates y
			// actualizamos en caché
			// y base de datos el keystore.
			removeSystemCertificateListAndSaveKeystore(aliasList);

			// Devolvemos la representación en caché del keystore modificada.
			return keystoreCacheObject;
		} catch (KeyStoreException | KeystoreCacheException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_059, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_056, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		}

	}

	/**
	 * Removes the system certificate with the input alias and update the keystore in DDBB and cache.
	 * @param aliasList Parameter that represents the list of aliases to delete.
	 * @throws KeystoreCacheException In case of some error updating the keystore in the cache.
	 */
	private void removeSystemCertificateListAndSaveKeystore(List<String> aliasList) throws KeystoreCacheException {

		// Recuperamos el keystore a actualizar de base de datos.
		Keystore ks = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(keystoreCacheObject.getIdKeystore(), false);
		// Le actualizamos su contenido y versionado.
		ks.setKeystore(keystoreCacheObject.getKeystoreBytes());
		ks.setVersion(keystoreCacheObject.getVersion());

		// Eliminamos de base de datos los system certificate con esos alias y
		// de este keystore.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getSystemCertificateService().deleteSystemCertificateList(aliasList, ks.getIdKeystore());
		// Guardamos el keystore.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().saveKeystore(ks);

		// Actualizamos el keystore en la caché.
		ConfigurationCacheFacade.keystoreAddUpdateKeystore(keystoreCacheObject);

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getKeystore()
	 */
	@Override
	public KeyStore getKeystore() throws CryptographyException {
		return keystoreCacheObject.getKeystore();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getKeystoreName()
	 */
	@Override
	public String getKeystoreName() throws KeystoreCacheException, CryptographyException {
		return keystoreCacheObject.getName();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getKeystoreBytes()
	 */
	@Override
	public byte[ ] getKeystoreBytes() throws CryptographyException {
		return keystoreCacheObject.getKeystoreBytes();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getKeystorePassword()
	 */
	@Override
	public String getKeystorePassword() throws CryptographyException {
		return getKeystoreDecodedPassword();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#updateKeystorePassword(java.lang.String)
	 */
	@Override
	public KeystoreCacheObject updateKeystorePassword(String newPassword) throws CryptographyException {

		LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_025, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		try {
			// Comprobamos que la contraseña no es nula
			CryptographyValidationUtils.checkIsNotNull(newPassword, Language.getResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_027));

			// Actualizamos el almacén de claves físicamente.
			KeyStore ksAux = null;
			ksAux = changeKeystorePassword(getKeystoreDecodedPassword(), newPassword);
			// Actualizamos el objeto que representa al keystore en la caché.
			String passwordEncripted = new String(UtilsAESCipher.getInstance().encryptMessage(newPassword));
			keystoreCacheObject.setPassword(passwordEncripted);
			keystoreCacheObject.setKeystore(ksAux);
			keystoreCacheObject.setKeystoreBytes(null);
			keystoreCacheObject.setVersion(keystoreCacheObject.getVersion() + 1);

			updateKeystorePassword();

			// Devolvemos la representación del keystore en caché.
			return keystoreCacheObject;
		} catch (UnrecoverableKeyException | KeyStoreException
				| NoSuchAlgorithmException | CertificateException | IOException
				| CipherException | KeystoreCacheException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_029, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		} finally {
			LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_026, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) }));
		}

	}

	/**
	 * Method that changes the password of a keystore.
	 * @param oldPassword Parameter that represents the decoded old password of the keystore.
	 * @param newPassword Parameter that represents the decoded new password of the keystore.
	 * @return the updated keystore.
	 * @throws KeyStoreException If no Provider supports a KeyStoreSpi implementation for the specified type, or if the keystore has not been
	 * initialized (loaded).
	 * @throws NoSuchAlgorithmException If the algorithm used to check the integrity of the keystore cannot be found.
	 * @throws CertificateException If any of the certificates in the keystore could not be loaded.
	 * @throws IOException If there is an I/O or format problem with the keystore data, if a password is required but not given, or if the given password
	 * was incorrect. If the error is due to a wrong password, the cause of the IOException should be an UnrecoverableKeyException.
	 * @throws UnrecoverableKeyException If the key cannot be recovered (e.g., the given password is wrong).
	 */
	private KeyStore changeKeystorePassword(String oldPassword, String newPassword) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException {
		KeyStore ks = keystoreCacheObject.getKeystore();
		KeyStore ksAux = KeyStore.getInstance(keystoreCacheObject.getKeystoreType());
		char[ ] oldPass = oldPassword.toCharArray();
		char[ ] newPass = newPassword.toCharArray();
		ksAux.load(null, newPass);
		Enumeration<String> listAlias = ks.aliases();
		while (listAlias.hasMoreElements()) {
			String alias = (String) listAlias.nextElement();
			if (ks.isKeyEntry(alias)) {
				ksAux.setKeyEntry(alias, ks.getKey(alias, oldPass), newPass, ks.getCertificateChain(alias));
			} else if (ks.isCertificateEntry(alias) && ks.getCertificate(alias) != null) {
				ksAux.setCertificateEntry(alias, ks.getCertificate(alias));
			}
		}
		return ksAux;
	}

	/**
	 * Updates the keystore password in the DDBB and the cache.
	 * @throws KeystoreCacheException In case of some error updating the keystore in the cache.
	 */
	private void updateKeystorePassword() throws KeystoreCacheException {

		// Recuperamos el keystore a actualizar de base de datos.
		Keystore ks = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(keystoreCacheObject.getIdKeystore(), false);
		// Le actualizamos su contenido, versionado y password.
		ks.setKeystore(keystoreCacheObject.getKeystoreBytes());
		ks.setVersion(keystoreCacheObject.getVersion());
		ks.setPassword(keystoreCacheObject.getPassword());

		// Guardamos el keystore.
		ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().saveKeystore(ks);

		// Actualizamos el keystore en la caché.
		ConfigurationCacheFacade.keystoreAddUpdateKeystore(keystoreCacheObject);

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getProvider()
	 */
	@Override
	public Provider getProvider() throws CryptographyException {
		return keystoreCacheObject.getKeystore().getProvider();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getKeystoreCacheObject()
	 */
	@Override
	public KeystoreCacheObject getKeystoreCacheObject() {
		return keystoreCacheObject;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getAllX509Certificates()
	 */
	@Override
	public List<X509Certificate> getAllX509Certificates() throws CryptographyException {
		List<X509Certificate> result = new ArrayList<X509Certificate>();
		try {
			for (Certificate cer: getAllCertificates()) {

				result.add(UtilsCertificate.getX509Certificate(cer.getEncoded()));

			}
		} catch (CertificateEncodingException | CommonUtilsException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.STANDARD_KEYSTORE_063, new Object[ ] { Language.getResPersistenceConstants(keystoreCacheObject.getTokenName()) });
			throw new CryptographyException(IValetException.COD_190, errorMsg, e);
		}
		return result;
	}

}
