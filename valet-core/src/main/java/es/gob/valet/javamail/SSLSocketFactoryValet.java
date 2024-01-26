package es.gob.valet.javamail;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import javax.net.SocketFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.log4j.Logger;

public class SSLSocketFactoryValet extends SSLSocketFactory {
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
    private static final Logger LOGGER = Logger.getLogger(SSLSocketFactoryValet.class);
	
	private SSLSocketFactory factory;

	public SSLSocketFactoryValet() {
		try {
			LOGGER.info("Se instancia factory");
			SSLContext sslcontext = SSLContext.getInstance("TLS");
			sslcontext.init(null, new TrustManager[ ] { new HandshakeValidationValet() }, null);
			factory = (SSLSocketFactory) sslcontext.getSocketFactory();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}

	public static SocketFactory getDefault() {
		return new SSLSocketFactoryValet();
	}

	public Socket createSocket() throws IOException {
		return factory.createSocket();
	}

	public Socket createSocket(Socket socket, String s, int i, boolean flag) throws IOException {
		return factory.createSocket(socket, s, i, flag);
	}

	public Socket createSocket(InetAddress inaddr, int i, InetAddress inaddr1, int j) throws IOException {
		return factory.createSocket(inaddr, i, inaddr1, j);
	}

	public Socket createSocket(InetAddress inaddr, int i) throws IOException {
		return factory.createSocket(inaddr, i);
	}

	public Socket createSocket(String s, int i, InetAddress inaddr, int j) throws IOException {
		return factory.createSocket(s, i, inaddr, j);
	}

	public Socket createSocket(String s, int i) throws IOException {
		return factory.createSocket(s, i);
	}

	public String[ ] getDefaultCipherSuites() {
		return factory.getDefaultCipherSuites();
	}

	public String[ ] getSupportedCipherSuites() {
		return factory.getSupportedCipherSuites();
	}
}
