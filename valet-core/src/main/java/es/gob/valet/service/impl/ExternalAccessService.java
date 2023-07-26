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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.impl.SystemCertificateService.java.</p>
 * <b>Description:</b><p> Class that implements the communication with the operations of the persistence layer for SystemCertificate.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 26/07/2023.
 */
package es.gob.valet.service.impl;


import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509ExtendedTrustManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICommonsUtilGeneralMessages;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.i18n.messages.ICoreTslMessages;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.repository.ExternalAccessRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.ExternalAccessTablesRepository;
import es.gob.valet.service.ifaces.IExternalAccessService;
import es.gob.valet.tsl.access.TSLManager;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for ExternalAccess.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 26/07/2023.
 */
@Service("ExternalAccessService")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ExternalAccessService implements IExternalAccessService {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ExternalAccessService.class);
	
	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private ExternalAccessRepository repository;

	/**
	 * Attribute that represents the injected interface that provides CRUD operations for the persistence.
	 */
	@Autowired
	private ExternalAccessTablesRepository dtRepository;

	/**
	 * Attribute that represent protocol http.
	 */
	public static final String HTTP = "http";
	
	/**
	 * Attribute that represent protocol https.
	 */
	public static final String HTTPS = "https";
	
	/**
	 * Attribute that represent protocol ldap.
	 */
	public static final String LDAP = "ldap:";

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	@Override
	public DataTablesOutput<ExternalAccess> getAll(DataTablesInput input) {
		return dtRepository.findAll(input);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getUrlDataById(java.lang.Long)
	 */
	@Override
	public ExternalAccess getUrlDataById(Long idUrlData) {
		return repository.findByIdUrl(idUrlData);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getDataUrlByUrl(java.lang.String)
	 */
	@Override
	public ExternalAccess getDataUrlByUrl(String url) {
		return repository.findByUrl(url);

	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#saveTryConnInExternalAccess(java.lang.String, java.lang.String)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveTryConnInExternalAccess(String uriTslLocation, String originUrl) {
		ExternalAccess externalAccess = repository.findByUrl(uriTslLocation);
		// Solo insertaremos aquellas url que no estén en la BD. Este valor es único.
		if(null == externalAccess) {
			ExternalAccess externalAccesSave = new ExternalAccess();
			externalAccesSave.setUrl(uriTslLocation);
			externalAccesSave.setOriginUrl(originUrl);
			// Realizamos el test de conexión con la url
			boolean stateConn = this.testConnUrl(uriTslLocation);
			externalAccesSave.setStateConn(stateConn);
			externalAccesSave.setLastConn(new Date());
			repository.save(externalAccesSave);
		}
	}

	/**
	 * Method that realize test connection about url.
	 * 
	 * @param uriTslLocation parameter that contain url.
	 * @return result of test.
	 */
	private boolean testConnUrl(String uriTslLocation) {
		boolean urlConnected = false;
		
		try {
			if(uriTslLocation.indexOf(LDAP) != -1) {
				Hashtable<String, String> environment = new Hashtable<String, String>();

		        environment.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		        environment.put(Context.PROVIDER_URL, uriTslLocation);
		        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
		        environment.put("com.sun.jndi.ldap.read.timeout", String.valueOf(NumberConstants.NUM3000));
		        
		        DirContext context = new InitialDirContext(environment);
		        context.close();
			} else {
				URL url = new URL(uriTslLocation);
				
				if(url.getProtocol().equals(HTTP)) {
					URLConnection connection = url.openConnection();
					connection.setConnectTimeout(NumberConstants.NUM3000);
			        connection.connect();
				} else if(url.getProtocol().equals(HTTPS)) {
					/*
			         *  fix for
			         *    Exception in thread "main" javax.net.ssl.SSLHandshakeException:
			         *       sun.security.validator.ValidatorException:
			         *           PKIX path building failed: sun.security.provider.certpath.SunCertPathBuilderException:
			         *               unable to find valid certification path to requested target
			         * Dado que en java no podemos descargarnos certificados de intermediarios para conectarnos a una url como sucede 
			         * en un navegador convencional, emularemos este comportamiento para conectarnos sin problemas.               
			         */
					TrustManager[ ] trustAllCerts = new TrustManager[ ] { new X509ExtendedTrustManager() {

						@Override
						public java.security.cert.X509Certificate[ ] getAcceptedIssuers() {
							return null;
						}

						@Override
						public void checkClientTrusted(X509Certificate[ ] certs, String authType) {
						}

						@Override
						public void checkServerTrusted(X509Certificate[ ] certs, String authType) {
						}

						@Override
						public void checkClientTrusted(X509Certificate[ ] xcs, String string, Socket socket) throws CertificateException {

						}

						@Override
						public void checkServerTrusted(X509Certificate[ ] xcs, String string, Socket socket) throws CertificateException {

						}

						@Override
						public void checkClientTrusted(X509Certificate[ ] xcs, String string, SSLEngine ssle) throws CertificateException {

						}

						@Override
						public void checkServerTrusted(X509Certificate[ ] xcs, String string, SSLEngine ssle) throws CertificateException {

						}

					} };
					
					SSLContext sc = SSLContext.getInstance("SSL");
			        sc.init(null, trustAllCerts, new java.security.SecureRandom());
			        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			
			        // Create all-trusting host name verifier
			        HostnameVerifier allHostsValid = new HostnameVerifier() {
			            @Override
			            public boolean verify(String hostname, SSLSession session) {
			                return true;
			            }
			        };
			        // Install the all-trusting host verifier
			        HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
			        /*
			         * end of the fix
			         */
			        
			        URLConnection connection = url.openConnection();
					connection.setConnectTimeout(NumberConstants.NUM3000);
			        connection.connect();
				}
			}
			
			urlConnected = true;
		} catch (SocketException e) {
			// Se considera que el socket se cerró cuando se estaba escribiendo datos en el flujo de salida y el servidor nos está avisando con RST.
			urlConnected = true;
			LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL401, new Object[ ] { uriTslLocation }));
		} catch (IOException e) {
			urlConnected = false;
			LOGGER.error(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL402, new Object[ ] { uriTslLocation }));
		} catch (NoSuchAlgorithmException e) {
			urlConnected = false;
			LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.ERROR_SERVICE_01));
		} catch (KeyManagementException e) {
			urlConnected = false;
			LOGGER.error(Language.getResCoreGeneral(ICoreGeneralMessages.ERROR_SERVICE_02));
		} catch (NamingException e) {
			urlConnected = false;
			LOGGER.error(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL402, new Object[ ] { uriTslLocation }));
		}
		
		return urlConnected;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#secureUrlAccess()
	 */
	public void secureUrlAccess() throws Exception {
		TSLManager.getInstance().prepareUrlAccess();
	}
}
