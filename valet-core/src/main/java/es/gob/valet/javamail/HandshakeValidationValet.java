package es.gob.valet.javamail;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

import org.apache.log4j.Logger;

public class HandshakeValidationValet implements X509TrustManager {

	private static final Logger LOGGER = Logger.getLogger(HandshakeValidationValet.class);

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		// No es necesario hacer nada
		// Se confía completamente en los certificados presentados por el cliente y no necesitamos realizar ninguna verificación específica.
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		LOGGER.debug("Comenzamos a validar los certificados proporcionados por el servidor");
		boolean accept = false;
		for(int i=0;i<chain.length;i++) {
			System.out.println(chain[i].getSubjectDN().getName());
			//TODO - Se verifica que alguno de los certificados de la cadena corresponde con el registrado en la configuraci�n del correo, si encunetra alguno accept=true
		
		}
		if(!accept) {
			throw new CertificateException("Certificado no soportado");
		}
		LOGGER.debug("checkServerTrusted");
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		//No es necesario hacer nada
		return null;
	}
		
}
