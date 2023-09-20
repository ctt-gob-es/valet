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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.keystore.elements.KeystoreCacheObject.java.</p>
 * <b>Description:</b><p>Class that represents a keystore in the clustered cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.3, 19/09/2023.
 */
package es.gob.valet.persistence.configuration.cache.modules.keystore.elements;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.PersistenceCacheMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException;
import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;
import es.gob.valet.persistence.utils.UtilsAESCipher;

/**
 * <p>Class that represents a keystore in the clustered cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 19/09/2023.
 */
public class KeystoreCacheObject extends ConfigurationCacheObject {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -6067492406425335493L;

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(KeystoreCacheObject.class);

	/**
	 * Keystore identifier.
	 */
	private long idKeystore = -1;

	/**
	 * Keystore name.
	 */
	private String name = null;

	/**
	 * Token for the keystore description.
	 */
	private String tokenName = null;

	/**
	 * KeyStore object representation.
	 */
	private transient KeyStore keystore = null;

	/**
	 * Byte array that represents the keystore.
	 */
	private byte[ ] keystoreBytes = null;

	/**
	 * Flag that represents if the keystore is hardware (HSM).
	 */
	private boolean isHardware = false;

	/**
	 * Encrypted password.
	 */
	private String password = null;

	/**
	 * Keystore Type.
	 */
	private String keystoreType = null;

	/**
	 * Keystore version. This number must be equal to the same keystore in the data base.
	 */
	private long version = -1;

	/**
	 * Attribute that indicates if the entries of the hardware keystore must be stored into the database and the HSM (true) or only into the HSM (false).
	 */
	private boolean hasBackup = false;

	/**
	 * Attribute that represents a map with the alias of the entries stored inside of the keystore. The key is the alias of the entry on the database, and
	 * the value is the SHA-1 hash of the certificate encoded on Base 64 concatenates with the SHA-1 hash of the private key for the certificate
	 * encoded on Base 64. This attribute is used only when the keystore is hardware.
	 */
	private Map<String, String> aliasMap = new HashMap<String, String>();

	/**
	 * Constructor method for the class KeystoreCacheObject.java.
	 */
	public KeystoreCacheObject() {
		super();
	}

	/**
	 * Constructor method for the class KeystoreCacheObject.java.
	 * @param kp Keystore POJO from which build the Keystore Cache Object
	 * @throws KeystoreCacheException In case of some error building the keystore.
	 */
	public KeystoreCacheObject(Keystore kp) throws KeystoreCacheException {
		this();
		try {
			if (kp != null) {
				setIdKeystore(kp.getIdKeystore().longValue());
				setName(kp.getName());
				setTokenName(kp.getTokenName());
				setHardware(kp.getIsHardware().booleanValue());
				setPassword(kp.getPassword());
				setKeystoreType(kp.getKeystoreType());
				setVersion(kp.getVersion().longValue());
				setHasBackup(kp.getHasBackup());
				if (isHardware) {
					// Vaciamos el mapa de alias
					aliasMap.clear();

					// Obtenemos de la BBDD la lista de certificados de sistema
					// asociados al almacén de claves
					List<SystemCertificate> listSystemCertificates = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getKeystoreService().getKeystoreById(kp.getIdKeystore(), true).getListSystemCertificates();
					// Recorremos la lista de certificados de sistema
					for (SystemCertificate systemCertificate: listSystemCertificates) {
						// Añadimos al mapa de alias la entrada asociada
						aliasMap.put(systemCertificate.getAlias(), systemCertificate.getHash());
					}
					// Si no tiene respaldo
					if (!hasBackup) {
						// Establecemos el almacén de claves como objeto java
						// nulo.
						// Así forzamos que todas las operaciones con dicho
						// objeto
						// se hagan a través
						// de la fachada de keystores
						setKeystore(null);
					}
					// Si tiene respaldo
					else {
						// Obtenemos el almacén de claves, definido como copia
						// de seguridad, como objeto java
						KeyStore ks = KeyStore.getInstance(keystoreType);
						ByteArrayInputStream bais = new ByteArrayInputStream(kp.getKeystore());
						ks.load(bais, new String(UtilsAESCipher.getInstance().decryptMessage(password)).toCharArray());
						setKeystore(ks);
					}
				} else {
					// Vaciamos el mapa de alias
					aliasMap.clear();
					// Obtenemos el almacén de claves como objeto java
					KeyStore ks = KeyStore.getInstance(keystoreType);
					ByteArrayInputStream bais = new ByteArrayInputStream(kp.getKeystore());
					ks.load(bais, new String(UtilsAESCipher.getInstance().decryptMessage(password)).toCharArray());
					setKeystore(ks);
				}
			}
		} catch (Exception e) {
			throw new KeystoreCacheException(ValetExceptionConstants.COD_190, Language.getResPersistenceCache(PersistenceCacheMessages.CONFIG_KEYSTORE_CACHE_LOG002), e);
		}
	}

	/**
	 * Gets the value of the attribute {@link #idKeystore}.
	 * @return the value of the attribute {@link #idKeystore}.
	 */
	public final long getIdKeystore() {
		return idKeystore;
	}

	/**
	 * Sets the value of the attribute {@link #idKeystore}.
	 * @param idKeystoreParam The value for the attribute {@link #idKeystore}.
	 */
	public final void setIdKeystore(long idKeystoreParam) {
		this.idKeystore = idKeystoreParam;
	}

	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Sets the value of the attribute {@link #name}.
	 * @param nameParam The value for the attribute {@link #name}.
	 */
	public final void setName(String nameParam) {
		this.name = nameParam;
	}

	/**
	 * Gets the value of the attribute {@link #tokenName}.
	 * @return the value of the attribute {@link #tokenName}.
	 */
	public final String getTokenName() {
		return tokenName;
	}

	/**
	 * Sets the value of the attribute {@link #tokenName}.
	 * @param tokenNameParam The value for the attribute {@link #tokenName}.
	 */
	public final void setTokenName(String tokenNameParam) {
		this.tokenName = tokenNameParam;
	}

	/**
	 * Gets the value of the attribute {@link #keystore}.
	 * @return the value of the attribute {@link #keystore}.
	 */
	public final KeyStore getKeystore() {
		return keystore;
	}

	/**
	 * Sets the value of the attribute {@link #keystore}.
	 * @param keystoreParam The value for the attribute {@link #keystore}.
	 */
	public final void setKeystore(KeyStore keystoreParam) {
		this.keystore = keystoreParam;
	}

	/**
	 * Gets the value of the attribute {@link #keystoreBytes}.
	 * @return the value of the attribute {@link #keystoreBytes}.
	 */
	public final byte[ ] getKeystoreBytes() {

		if (keystoreBytes == null && keystore != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try {
				keystore.store(baos, new String(UtilsAESCipher.getInstance().decryptMessage(password)).toCharArray());
			} catch (Exception e) {
				return null;
			}
			return baos.toByteArray();
		}

		return keystoreBytes;
	}

	/**
	 * Sets the value of the attribute {@link #keystoreBytes}.
	 * @param keystoreBytesParam The value for the attribute {@link #keystoreBytes}.
	 */
	public final void setKeystoreBytes(byte[ ] keystoreBytesParam) {
		if (keystoreBytesParam != null) {
			this.keystoreBytes = keystoreBytesParam.clone();
		} else {
			this.keystoreBytes = null;
		}
	}

	/**
	 * Gets the value of the attribute {@link #isHardware}.
	 * @return the value of the attribute {@link #isHardware}.
	 */
	public final boolean isHardware() {
		return isHardware;
	}

	/**
	 * Sets the value of the attribute {@link #isHardware}.
	 * @param isHardwareParam The value for the attribute {@link #isHardware}.
	 */
	public final void setHardware(boolean isHardwareParam) {
		this.isHardware = isHardwareParam;
	}

	/**
	 * Gets the value of the attribute {@link #password}.
	 * @return the value of the attribute {@link #password}.
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * Sets the value of the attribute {@link #password}.
	 * @param passwordParam The value for the attribute {@link #password}.
	 */
	public final void setPassword(String passwordParam) {
		this.password = passwordParam;
	}

	/**
	 * Gets the value of the attribute {@link #keystoreType}.
	 * @return the value of the attribute {@link #keystoreType}.
	 */
	public final String getKeystoreType() {
		return keystoreType;
	}

	/**
	 * Sets the value of the attribute {@link #keystoreType}.
	 * @param keystoreTypeParam The value for the attribute {@link #keystoreType}.
	 */
	public final void setKeystoreType(String keystoreTypeParam) {
		this.keystoreType = keystoreTypeParam;
	}

	/**
	 * Gets the value of the attribute {@link #version}.
	 * @return the value of the attribute {@link #version}.
	 */
	public final long getVersion() {
		return version;
	}

	/**
	 * Sets the value of the attribute {@link #version}.
	 * @param versionParam The value for the attribute {@link #version}.
	 */
	public final void setVersion(long versionParam) {
		this.version = versionParam;
	}

	/**
	 * Gets the value of the attribute {@link #aliasMap}.
	 * @return the value of the attribute {@link #aliasMap}.
	 */
	public final Map<String, String> getAliasMap() {
		return aliasMap;
	}

	/**
	 * Sets the value of the attribute {@link #aliasMap}.
	 * @param aliasMapParam The value for the attribute {@link #aliasMap}.
	 */
	public final void setAliasMap(Map<String, String> aliasMapParam) {
		this.aliasMap = aliasMapParam;
	}

	/**
	 * Gets the value of the attribute {@link #hasBackup}.
	 * @return the value of the attribute {@link #hasBackup}.
	 */
	public final boolean isHasBackup() {
		return hasBackup;
	}

	/**
	 * Sets the value of the attribute {@link #hasBackup}.
	 * @param hasBackupParam The value for the attribute {@link #hasBackup}.
	 */
	public final void setHasBackup(boolean hasBackupParam) {
		this.hasBackup = hasBackupParam;
	}

}
