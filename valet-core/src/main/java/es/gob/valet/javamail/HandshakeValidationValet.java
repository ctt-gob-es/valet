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
 * <b>File:</b><p> es.gob.valet.javamail.HandshakeValidationValet.java.</p>
 * <b>Description:</b><p> Implementation of X509TrustManager used for SSL/TLS handshake validation. This implementation fully trusts certificates presented by the client and performs specific verification in the case of certificates presented by the server.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>30/01/2024.</p>
 * @author Gobierno de España.
 * @version 1.0, 30/01/2024.
 */
package es.gob.valet.javamail;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.model.entity.ConfServerMail;

/**
 * <p> Implementation of X509TrustManager used for SSL/TLS handshake validation. This implementation fully trusts certificates presented by the client and performs specific verification in the case of certificates presented by the server.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 30/01/2024.
 */
public class HandshakeValidationValet implements X509TrustManager {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(HandshakeValidationValet.class);
	
	/**
     * {@inheritDoc}
     * <p>
     * No action is needed for client certificate validation. This implementation fully trusts
     * certificates presented by the client, and no specific verification is required.
     */
	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		// No es necesario hacer nada
		// Se confía completamente en los certificados presentados por el cliente y no necesitamos realizar ninguna verificación específica.
	}

	/**
     * {@inheritDoc}
     * <p>
     * Validates the certificates presented by the server, using the certificates provided by the client
     * as a reference.
     *
     * @param chain    Array of certificates presented by the server.
     * @param authType The authentication type.
     * @throws CertificateException If the presented certificates are not supported or fail verification.
     */
	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		LOGGER.debug("Comenzamos a validar los certificados proporcionados por el servidor, con los que nos proporciona el cliente");
		boolean accept = false;
		X509Certificate x509Certificate;
		ConfServerMail confServerMail = ManagerPersistenceConfigurationServices.getInstance().getConfServerMailService().getConfServerMailById(NumberConstants.NUM1_LONG);
		try {
			x509Certificate = UtilsCertificate.getX509Certificate(confServerMail.getCertificateFile());
			for(int i=0;i<chain.length;i++) {
    			LOGGER.debug(chain[i].getSubjectDN().getName());
    			// Validamos que el certificado del cliente ha sido emtido por el mismo emisor que nuestro certificado de servidor
    			if(chain[i].getIssuerX500Principal().equals(x509Certificate.getSubjectX500Principal())) {
    				accept = true;
        	        break; // Rompemos el bucle más cercano ya que hemos encontrado un certificado que lo contiene un emisor 
    			}
    		}
    		if(!accept) {
    			throw new CertificateException("Certificado no soportado");
    		}
		} catch (CommonUtilsException e) {
			throw new CertificateException("Se ha producido un fallo al verificar la clave pública del certificado");
		}
		LOGGER.debug("checkServerTrusted");
	}

	/**
     * {@inheritDoc}
     * <p>
     * Returns null, indicating that no specific certificates are accepted.
     *
     * @return Null.
     */
	@Override
	public X509Certificate[] getAcceptedIssuers() {
		//No es necesario hacer nada
		return null;
	}
		
}
