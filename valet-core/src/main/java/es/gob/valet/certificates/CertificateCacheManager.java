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
 * @version 1.0, 11/04/2022.
 */
package es.gob.valet.certificates;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.gob.valet.crypto.exception.CryptographyException;
import es.gob.valet.crypto.keystore.IKeystoreFacade;
import es.gob.valet.crypto.keystore.StandardKeystoreFacade;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.persistence.configuration.cache.engine.ConfigurationCacheFacade;
import es.gob.valet.persistence.configuration.cache.modules.keystore.elements.KeystoreCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.keystore.exceptions.KeystoreCacheException;
import es.gob.valet.persistence.configuration.model.utils.IKeystoreIdConstants;

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
 * @version 1.1, 22/02/2023.
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
			instance.loadListCertificateCA();

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

		} catch (CryptographyException e) {
			LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.CC_000, new Object[] {e.getCause()}));	
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
}
