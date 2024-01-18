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
 * <b>File:</b><p>es.gob.valet.certificates.CertificateManager.java.</p>
 * <b>Description:</b><p>Class that manages the certificates stored in the cache, obtained from the TSLs and those registered in the CA trust store.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>11/04/2022.</p>
 * @author Gobierno de España.
 * @version 1.3, 11/01/2024.
 */
package es.gob.valet.certificates;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import es.gob.valet.crypto.keystore.IKeystoreFacade;
import es.gob.valet.crypto.keystore.StandardKeystoreFacade;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.keystore.elements.KeystoreCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException;
import es.gob.valet.persistence.configuration.model.utils.IKeystoreIdConstants;
import es.gob.valet.persistence.exceptions.CryptographyException;

/**
 * <p>
 * Class that manages the certificates stored in the cache, obtained from the
 * TSLs and those registered in the CA trust store.
 * </p>
 * <b>Project:</b>
 * <p>
 * Platform for detection and validation of certificates recognized in European
 * TSL.
 * </p>
 * 
 * @version 1.3, 11/01/2024.
 */
public final class CertificateCacheManager {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(CertificateCacheManager.class);

	/**
	 * Attribute that represents the unique instance of this class.
	 */
	private static CertificateCacheManager instance = null;

	/**
	 * Attribute that represents the list of certificates that have been
	 * registered in the CA trust store.
	 */
	private static List<X509Certificate> listCertificateCA;
	
	/**
	 * Attribute that represents the map of with alias/certificates that have been
	 * registered in the CA trust store.
	 */
	private static Map<String, X509Certificate> mapAliasX509CertCA;

	/**
	 * Attribute that represents the map of with alias/certificates that have been
	 * registered in the OCSP trust store.
	 */
	private static Map<String, X509Certificate> mapAliasX509CertOCSP;
	
	
	/**
	 * Constructor method for the class CertificateCacheManager.java.
	 */
	private CertificateCacheManager() {
		super();
	}

	/**
	 * Gets the unique instance of the manager.
	 * 
	 * @return Unique instance of the manager.
	 */

	public static synchronized CertificateCacheManager getInstance() {

		if (instance == null) {
			instance = new CertificateCacheManager();
			listCertificateCA = new ArrayList<X509Certificate>();
			mapAliasX509CertCA = new HashMap<String, X509Certificate>();
			mapAliasX509CertOCSP = new HashMap<String, X509Certificate>();
			instance.loadListCertificateCA();
			instance.loadListCertificateOCSP();
		}
		return instance;
	}

	/**
	 * Method that caches the different certificates stored in the CA trust
	 * store.
	 */
	public void loadListCertificateCA() {
		// obtenemos el almacén de claves de la caché
		KeystoreCacheObject kco = null;

		try {
			kco = ConfigurationCacheFacade.keystoreGetKeystoreCacheObject(IKeystoreIdConstants.ID_CA_TRUSTSTORE);

			IKeystoreFacade keystoreFacade = new StandardKeystoreFacade(kco);
			listCertificateCA.clear();
			List<X509Certificate> listX509CerCA;

			listX509CerCA = keystoreFacade.getAllX509Certificates();

			if (listX509CerCA != null && !listX509CerCA.isEmpty()) {
				listCertificateCA.addAll(listX509CerCA);
			}
			
			// creamos un mapa de alias con certificados X509
			mapAliasX509CertCA.clear();
			
			Map<String, X509Certificate> mapAliasX509CertificateCA = keystoreFacade.getAllAliasWithX509Certificates();
			
			if (null != mapAliasX509CertificateCA && !mapAliasX509CertificateCA.isEmpty()) {
				mapAliasX509CertCA.putAll(mapAliasX509CertificateCA);
			}
		} catch (CryptographyException e) {
			LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.CC_000, new Object[] {e.getCause()}));	
		} catch (KeystoreCacheException e) {
			LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.CC_000, new Object[] {e.getCause()}));	
		}
	}

	/**
	 * Method that caches the different alias/certificates stored in the OCSP trust
	 * store.
	 */
	public void loadListCertificateOCSP() {
		// obtenemos el almacén de claves de la caché
		KeystoreCacheObject kco = null;
		
		try {
			kco = ConfigurationCacheFacade.keystoreGetKeystoreCacheObject(IKeystoreIdConstants.ID_OCSP_TRUSTSTORE);
			
			IKeystoreFacade keystoreFacade = new StandardKeystoreFacade(kco);
			
			// creamos un mapa de alias con certificados X509
			mapAliasX509CertOCSP.clear();
			
			Map<String, X509Certificate> mapAliasX509CertificateOCSP = keystoreFacade.getAllAliasWithX509Certificates();
			
			if (null != mapAliasX509CertificateOCSP && !mapAliasX509CertificateOCSP.isEmpty()) {
				mapAliasX509CertOCSP.putAll(mapAliasX509CertificateOCSP);
			}
		} catch (KeystoreCacheException e) {
			LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.CC_000, new Object[] {e.getCause()}));	
		}
	}
	
	/**
	 * Gets the value of the attribute {@link #listCertificateCA}.
	 * 
	 * @return the value of the attribute {@link #listCertificateCA}.
	 */
	public static List<X509Certificate> getListCertificateCA() {
		return listCertificateCA;
	}

	/**
	 * Sets the value of the attribute {@link #listCertificateCA}.
	 * 
	 * @param listCertificateCA
	 *            The value for the attribute {@link #listCertificateCA}.
	 */
	public static void setListCertificateCA(List<X509Certificate> listCertificateCA) {
		CertificateCacheManager.listCertificateCA = listCertificateCA;
	}

	
	/**
	 * Gets the value of the attribute {@link #mapAliasX509CertCA}.
	 * @return the value of the attribute {@link #mapAliasX509CertCA}.
	 */
	public static Map<String, X509Certificate> getMapAliasX509CertCA() {
		return mapAliasX509CertCA;
	}

	
	/**
	 * Sets the value of the attribute {@link #mapAliasX509CertCA}.
	 * @param mapAliasX509CertCA The value for the attribute {@link #mapAliasX509CertCA}.
	 */
	public static void setMapAliasX509CertCA(Map<String, X509Certificate> mapAliasX509CertCA) {
		CertificateCacheManager.mapAliasX509CertCA = mapAliasX509CertCA;
	}

	
	/**
	 * Gets the value of the attribute {@link #mapAliasX509CertOCSP}.
	 * @return the value of the attribute {@link #mapAliasX509CertOCSP}.
	 */
	public static Map<String, X509Certificate> getMapAliasX509CertOCSP() {
		return mapAliasX509CertOCSP;
	}

	
	/**
	 * Sets the value of the attribute {@link #mapAliasX509CertOCSP}.
	 * @param mapAliasX509CertOCSP The value for the attribute {@link #mapAliasX509CertOCSP}.
	 */
	public static void setMapAliasX509CertOCSP(Map<String, X509Certificate> mapAliasX509CertOCSP) {
		CertificateCacheManager.mapAliasX509CertOCSP = mapAliasX509CertOCSP;
	}
	
	
}
