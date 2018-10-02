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
 * <b>File:</b><p>es.gob.valet.crypto.keystore.KeystoreFacade.java.</p>
 * <b>Description:</b><p>Class that manages all the operations related with JCE, JCEKS and PKCS#12 keystores.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>26 sept. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 26 sept. 2018.
 */
package es.gob.valet.crypto.keystore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;

import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.crypto.exception.CryptographyException;
import es.gob.valet.crypto.utils.CryptographyValidationUtils;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreMessages;
import es.gob.valet.persistence.configuration.model.entity.Keystore;

/** 
 * <p>Class that manages all the operations related with JCE, JCEKS and PKCS#12 keystores.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 26 sept. 2018.
 */
public class KeystoreFacade implements IKeystoreFacade {

	/**
	 * Attribute that represents a p12 key store file extension.
	 */
	private static final String P12_KEYSTORE_EXTENSION = "p12";

	/**
	 * Attribute that represents a pfx key store file extension.
	 */
	private static final String PFX_KEYSTORE_EXTENSION = "pfx";

	/**
	 * Attribute that represents the PKCS#12 keystore type.
	 */
	private static final String PKCS12 = "PKCS12";


	/**
	 * Attribute that represents the information about the keystore from the cache system.
	 */
	private Keystore keystoreValet = null;

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(KeystoreFacade.class);

	/**
	 * Constructor method for the class KeystoreFacade.java.
	 * @param keystoreParam Parameter that represents the information about the keystore
	 */
	public KeystoreFacade(final Keystore keystoreParam) {
		keystoreValet = keystoreParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#storeCertificate(java.lang.String, java.security.cert.Certificate, java.security.Key)
	 */
	@Override
	public Keystore storeCertificate(final String alias, final Certificate certificate,final  Key key) throws CryptographyException {
		LOGGER.info(Language.getResCoreValet(ICoreMessages.CRYPTO_001));
		try {
			// Comprobamos que el certificado no sea nulo
			CryptographyValidationUtils.checkIsNotNull(certificate, Language.getResCoreValet(ICoreMessages.CRYPTO_003));

			// Comprobamos que el alias no sea nulo
			CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreValet(ICoreMessages.CRYPTO_004));

			// Tratamos de convertir el objeto Certificate a X509Certificate
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			X509Certificate cert = (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(certificate.getEncoded()));

			// Realizamos validación básica del certificado a añadir
			try {
				// Comprobamos el periodo de validez del certificado
				cert.checkValidity(Calendar.getInstance().getTime());

			} catch (CertificateExpiredException e) {
				// Certificado caducado
				LOGGER.warn(Language.getResCoreValet(ICoreMessages.CRYPTO_005));
			} catch (CertificateNotYetValidException e) {
				// Certificado no válido aún
				LOGGER.warn(Language.getResCoreValet(ICoreMessages.CRYPTO_006));
			}
			// Actualizamos el almacén de claves físicamente. Si la clave es
			// nula, sólo se insertará el certificado.
			LOGGER.debug(Language.getFormatResCoreValet(ICoreMessages.CRYPTO_007, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreValet.getTokenName()) }));
			addEntryToKeystore(alias, certificate, key);
		} catch (CertificateException e) {
			String errorMsg = Language.getResCoreValet(ICoreMessages.CRYPTO_008);
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(errorMsg, e);
		} catch (KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreValet(ICoreMessages.CRYPTO_009, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreValet.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(errorMsg, e);
		} finally {
			LOGGER.info(Language.getResCoreValet(ICoreMessages.CRYPTO_002));
		}
		// Devolvemos los datos actualizados del almacen de clave
		return keystoreValet;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#updateCertificate(java.lang.String, java.lang.String)
	 */
	@Override
	public Keystore updateCertificate(String oldEntryAlias, String newEntryAlias) throws CryptographyException {
		LOGGER.info(Language.getResCoreValet(ICoreMessages.CRYPTO_001));
		try {
			// Comprobamos que el alias no sea nulo
			CryptographyValidationUtils.checkIsNotNull(newEntryAlias, Language.getResCoreValet(ICoreMessages.CRYPTO_004));

			// Actualizamos el almacén de claves físicamente. Si la clave es
			// nula, sólo se insertará el certificado.
			LOGGER.debug(Language.getFormatResCoreValet(ICoreMessages.CRYPTO_007, new Object[ ] { newEntryAlias, Language.getResPersistenceConstants(keystoreValet.getTokenName()) }));
			updateEntryToKeystore(oldEntryAlias, newEntryAlias);
		} catch (KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreValet(ICoreMessages.CRYPTO_009, new Object[ ] { newEntryAlias, Language.getResPersistenceConstants(keystoreValet.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(errorMsg, e);
		} catch (UnrecoverableKeyException | NoSuchAlgorithmException e) {
			String errorMsg = Language.getFormatResCoreValet(ICoreMessages.CRYPTO_011, new Object[ ] { oldEntryAlias, newEntryAlias,Language.getResPersistenceConstants(keystoreValet.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(errorMsg, e);
		} finally {
			LOGGER.info(Language.getResCoreValet(ICoreMessages.CRYPTO_002));
		}
		// Devolvemos los datos en caché actualizados del almacén de claves
		return keystoreValet;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#deleteCertificate(java.lang.String)
	 */
	@Override
	public Keystore deleteCertificate(String alias) throws CryptographyException {

		char[ ] ksPass = new String(getKeystoreDecodedPassword(null)).toCharArray();

		// Cargamos el keystore desde la persistencia
		try (ByteArrayInputStream bais = new ByteArrayInputStream(keystoreValet.getKeystore());) {
			KeyStore keystore = KeyStore.getInstance(keystoreValet.getKeystoreType());
			keystore.load(bais, ksPass);

			if (keystore.containsAlias(alias)) {
				// Si existe la entrada, la elimino
				keystore.deleteEntry(alias);
			}

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			keystore.store(baos, ksPass);
			keystoreValet.setKeystore(baos.toByteArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException
				| KeyStoreException e) {
			String errorMsg = Language.getFormatResCoreValet(ICoreMessages.CRYPTO_009, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreValet.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(errorMsg, e);
		} finally {
			LOGGER.info(Language.getResCoreValet(ICoreMessages.CRYPTO_002));
		}

		return keystoreValet;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#getKeystoreType(java.lang.String)
	 */
	@Override
	public String getKeystoreType(String nameFile) {
		String keyStoreType = null;

		if (nameFile.endsWith(P12_KEYSTORE_EXTENSION) || nameFile.endsWith(PFX_KEYSTORE_EXTENSION)) {
			keyStoreType = PKCS12;
		}
		return keyStoreType;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.crypto.keystore.IKeystoreFacade#listAllAliases(java.security.KeyStore)
	 */
	@Override
	public List<String> listAllAliases(KeyStore ks) throws KeyStoreException {
		List<String> result = null;
		if (ks != null) {
			result = Collections.list(ks.aliases());
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.afirma.cryptography.keystore.IKeystoreFacade#getCertificate(java.lang.String)
	 */
	@Override
	public final X509Certificate getCertificate(String alias) {
		LOGGER.info(Language.getResCoreValet(ICoreMessages.CRYPTO_018));
		Certificate cert = null;
		X509Certificate x509Cert = null;
		try {
		
			// Comprobamos que el alias no sea nulo
			CryptographyValidationUtils.checkIsNotNull(alias, Language.getResCoreValet(ICoreMessages.CRYPTO_004));
			// Devolvemos el certificado del almacén de claves
			LOGGER.debug(Language.getFormatResCoreValet(ICoreMessages.CRYPTO_016, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreValet.getTokenName()) }));
			cert = getCertificateFromKeystore(alias);
			x509Cert = UtilsCertificate.getIaikCertificate(cert);
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | CommonUtilsException | CryptographyException e) {
			String errorMsg = Language.getFormatResCoreValet(ICoreMessages.CRYPTO_017, new Object[ ] { alias, Language.getResPersistenceConstants(keystoreValet.getTokenName()) });
			LOGGER.error(errorMsg, e);	
		
		} finally {
			LOGGER.info(Language.getResCoreValet(ICoreMessages.CRYPTO_019));
		}
		return x509Cert;
	}
	/**
	 * Method that obtains a certificate from the keystore.
	 * @param alias Parameter that represents the alias of the associated entry.
	 * @return an object that represents the certificate.
	 * @throws KeyStoreException If the keystore has not been initialized (loaded).
	 * @throws IOException If the method fails.
	 * @throws CertificateException 
	 * @throws NoSuchAlgorithmException 
	 */
	private Certificate getCertificateFromKeystore(String alias) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Certificate cert = null;
		KeyStore keystore = KeyStore.getInstance(keystoreValet.getKeystoreType());	
		
		//cargamos el keystore
		ByteArrayInputStream bais = new ByteArrayInputStream(keystoreValet.getKeystore());

		keystore.load(bais, null);
		if (keystore.isCertificateEntry(alias) || keystore.isKeyEntry(alias)) {
			cert = keystore.getCertificate(alias);
		}
		return cert;
	}
	
	/**
	 * Method that inserts an entry inside of a keystore.
	 * @param alias Parameter that represents the alias of the entry to store.
	 * @param cert Parameter that represents the certificate associated to the new entry.
	 * @param key Parameter that represents the private key associated to the new entry.
	 * @throws KeyStoreException If there is some error inserting the entry into the keystore.
	 * @throws CryptographyException If there is some error decrypting the password of the keystore.
	 */
	private void addEntryToKeystore(final String alias, final Certificate cert, final Key key) throws KeyStoreException, CryptographyException {

		// Cargamos el keystore desde la persistencia
		KeyStore keystore = KeyStore.getInstance(keystoreValet.getKeystoreType());
		//se obtiene char[] con la password decodificada, como son solos certificados, lo ponemos a null
		char[ ] ksPass = new String(getKeystoreDecodedPassword(null)).toCharArray();
		
		
		try (ByteArrayInputStream bais = new ByteArrayInputStream(keystoreValet.getKeystore());) {
			keystore.load(bais, new String(getKeystoreDecodedPassword(null)).toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException
				| IOException e) {
			LOGGER.error(Language.getResCoreValet(ICoreMessages.CRYPTO_014), e);
			
		}

		if (key == null) {
			keystore.setCertificateEntry(alias, cert);
		} else {
			keystore.setKeyEntry(alias, key, ksPass, new Certificate[ ] { cert });
		}

		// Establecemos el nuevo valor del almacén
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			keystore.store(baos, new String(ksPass).toCharArray());
			keystoreValet.setKeystore(baos.toByteArray());
		} catch (NoSuchAlgorithmException | CertificateException
				| IOException e) {
			LOGGER.error(Language.getResCoreValet(ICoreMessages.CRYPTO_015), e);
		}

	}



	/**
	 * Method that updates an alias entry inside of a keystore.
	 * @param oldEntryAlias Parameter that represents the alias to change.
	 * @param newEntryAlias Parameter that represents the new alias.
	 * @throws UnrecoverableKeyException If the key cannot be recovered (e.g., the given password is wrong).
	 * @throws KeyStoreException If there is some error inserting the entry into the keystore.
	 * @throws NoSuchAlgorithmException If the algorithm for recovering the key cannot be found.
	 * @throws CryptographyException If there is some error decrypting the password of the keystore.
	 * @return Object that represents the updated keystore.
	 */
	private Keystore updateEntryToKeystore(String oldEntryAlias, String newEntryAlias) throws UnrecoverableKeyException, KeyStoreException, NoSuchAlgorithmException, CryptographyException {

		char[ ] ksPass = new String(getKeystoreDecodedPassword(null)).toCharArray();
		// Cargamos el keystore  desde la persistencia
		KeyStore keystore = KeyStore.getInstance(keystoreValet.getKeystoreType());

		try (ByteArrayInputStream bais = new ByteArrayInputStream(keystoreValet.getKeystore());) {
			keystore.load(bais, ksPass);
		} catch (NoSuchAlgorithmException | CertificateException
				| IOException e) {
			LOGGER.error(Language.getResCoreValet(ICoreMessages.CRYPTO_014), e);
		}

		if (keystore.containsAlias(oldEntryAlias)) {
			if (keystore.isCertificateEntry(oldEntryAlias)) {
				Certificate cert = keystore.getCertificate(oldEntryAlias);
				keystore.deleteEntry(oldEntryAlias);
				keystore.setCertificateEntry(newEntryAlias, cert);
			} else if (keystore.isKeyEntry(oldEntryAlias)) {
				Key key = keystore.getKey(oldEntryAlias, ksPass);
				Certificate[ ] certChain = keystore.getCertificateChain(oldEntryAlias);
				keystore.deleteEntry(oldEntryAlias);
				keystore.setKeyEntry(newEntryAlias, key, ksPass, certChain);
			}
		}

		// Establecemos el nuevo valor del almacén SSL
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();) {
			keystore.store(baos, ksPass);
			keystoreValet.setKeystore(baos.toByteArray());
		} catch (NoSuchAlgorithmException | CertificateException
				| IOException e) {
			LOGGER.error(Language.getResCoreValet(ICoreMessages.CRYPTO_015), e);
		}

		return keystoreValet;
	}	
	
	/**
	 * Method that obtains the decoded password of the keystore represented by {@link #keystore}.
	 * @param password to decode.
	 * @return the decoded password of the keystore represented by {@link #keystore}.
	 * @throws CryptographyException If the method fails.
	 */
	private byte[ ] getKeystoreDecodedPassword(final String password) throws CryptographyException {
		try {		
			SecretKeySpec key = new SecretKeySpec(StaticValetConfig.getProperty(StaticValetConfig.AES_PASSWORD).getBytes(), StaticValetConfig.getProperty(StaticValetConfig.AES_ALGORITHM));
			Cipher cipher = Cipher.getInstance(StaticValetConfig.getProperty(StaticValetConfig.AES_PADDING_ALG));
			cipher.init(Cipher.DECRYPT_MODE, key);

			return cipher.doFinal(Base64.decodeBase64(password == null ? keystoreValet.getPassword() : password));
		} catch (Exception e) {
			String errorMsg = Language.getFormatResCoreValet(ICoreMessages.CRYPTO_010, new Object[ ] { Language.getResPersistenceConstants(keystoreValet.getTokenName()) });
			LOGGER.error(errorMsg, e);
			throw new CryptographyException(errorMsg, e);
		}
	}

}
