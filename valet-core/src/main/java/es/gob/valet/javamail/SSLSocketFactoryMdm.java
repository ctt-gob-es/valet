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
 * <b>Description:</b><p>Custom implementation of SSLSocketFactory that enhances SSL/TLS socket creation by providing a specific TrustManager, {@link HandshakeValidationMdm}, for handshake validation. This implementation aims to improve security by carefully validating certificates presented by the server.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>30/01/2024.</p>
 * @author Gobierno de España.
 * @version 1.0, 30/01/2024.
 */
package es.gob.valet.javamail;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>Custom implementation of SSLSocketFactory that enhances SSL/TLS socket creation by providing a specific TrustManager, {@link HandshakeValidationMdm}, for handshake validation. This implementation aims to improve security by carefully validating certificates presented by the server.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 30/01/2024.
 */
public class SSLSocketFactoryMdm extends SSLSocketFactory {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(SSLSocketFactoryMdm.class);
	
	 /**
     * The underlying SSLSocketFactory used for creating SSL sockets.
     */
	private SSLSocketFactory factory;

	 /**
     * Constructs a new instance of SSLSocketFactoryValet.
     * Initializes the underlying SSLSocketFactory with a custom TrustManager, {@link HandshakeValidationMdm},
     * to perform enhanced SSL/TLS handshake validation.
     */
	public SSLSocketFactoryMdm() {
		try {
			LOGGER.debug("Se instancia factory");
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[ ] { new HandshakeValidationMdm() }, null);
			factory = (SSLSocketFactory) sslcontext.getSocketFactory();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	/**
     * Gets the default SocketFactory instance.
     *
     * @return A new instance of SSLSocketFactoryValet.
     */
	public static SocketFactory getDefault() {
		return new SSLSocketFactoryMdm();
	}

	/**
     * {@inheritDoc}
     */
	public Socket createSocket() throws IOException {
		return factory.createSocket();
	}

	/**
     * {@inheritDoc}
     */
	public Socket createSocket(Socket socket, String s, int i, boolean flag) throws IOException {
		return factory.createSocket(socket, s, i, flag);
	}

	/**
     * {@inheritDoc}
     */
	public Socket createSocket(InetAddress inaddr, int i, InetAddress inaddr1, int j) throws IOException {
		return factory.createSocket(inaddr, i, inaddr1, j);
	}

	/**
     * {@inheritDoc}
     */
	public Socket createSocket(InetAddress inaddr, int i) throws IOException {
		return factory.createSocket(inaddr, i);
	}

	/**
     * {@inheritDoc}
     */
	public Socket createSocket(String s, int i, InetAddress inaddr, int j) throws IOException {
		return factory.createSocket(s, i, inaddr, j);
	}

	/**
     * {@inheritDoc}
     */
	public Socket createSocket(String s, int i) throws IOException {
		return factory.createSocket(s, i);
	}

	/**
     * {@inheritDoc}
     */
	public String[ ] getDefaultCipherSuites() {
		return factory.getDefaultCipherSuites();
	}

	/**
     * {@inheritDoc}
     */
	public String[ ] getSupportedCipherSuites() {
		return factory.getSupportedCipherSuites();
	}
}
