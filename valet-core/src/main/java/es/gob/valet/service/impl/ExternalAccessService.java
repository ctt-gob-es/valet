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
 * @version 1.3, 31/07/2023.
 */
package es.gob.valet.service.impl;


import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
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
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.CRLDistPoint;
import org.bouncycastle.asn1.x509.DistributionPoint;
import org.bouncycastle.asn1.x509.DistributionPointName;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.i18n.messages.ICoreTslMessages;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.repository.ExternalAccessRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.ExternalAccessTablesRepository;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;
import es.gob.valet.service.ifaces.IExternalAccessService;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.certValidation.impl.common.WrapperX509Cert;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.exceptions.TSLParsingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for ExternalAccess.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.3, 31/07/2023.
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
	 * Attribute that represents the service object for accessing the repository of region service.
	 */
	@Autowired
	private ITslCountryRegionService iTslCountryRegionService;
	
	/**
	 * Attribute that represents the service object for accessing the repository of data service.
	 */
	@Autowired
	private ITslDataService iTslDataService;
	
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
	 * Constant attribute that represents the token 'DISTRIBUTIONPOINT'.
	 */
	private static final String DISTRIBUTIONPOINT = "DistributionPoint";
	
	/**
	 * Constant attribute that represents the token 'ISSUERALTERNATIVENAME'.
	 */
	private static final String ISSUERALTERNATIVENAME = "IssuerAlternativeName";

	/**
	 * Constant attribute that represents the token 'DISTRIBUTIONPOINTCRL'.
	 */
	private static final String DISTRIBUTIONPOINTCRL = "DistributionPointCRL";
	
	/**
	 * Constant attribute that represents the token 'DISTRIBUTIONPOINTOCSP'.
	 */
	private static final String DISTRIBUTIONPOINTOCSP = "DistributionPointOCSP";
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// Despues de poner la clase en servicio para la IOC al inicializar el servidor, preparamos las url de las TSL que ya existan en BD.
		this.prepareUrlExternalAccess();
	}
	
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
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#testConnExternalAccessAndSaveResult(java.lang.String, java.lang.String)
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void testConnExternalAccessAndSaveResult(String uriTslLocation, String originUrl) {
		ExternalAccess externalAccess = repository.findByUrl(uriTslLocation);
		// Si no existe creamos el registro con el test de conexión realizado.
		if (null == externalAccess) {
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
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#realizeTestAndUpdateResult()
	 */
	public void realizeTestAndUpdateResult() {
		// Obtenemos todas las url de la BD.
		List<ExternalAccess> listExternalAccess = repository.findAll();
		
		for (ExternalAccess externalAccess: listExternalAccess) {
			// Realizamos el test de conexión y almacenamos la información de estado y de última conexión actualizada.
			boolean stateConn = this.testConnUrl(externalAccess.getUrl());
			externalAccess.setStateConn(stateConn);
			externalAccess.setLastConn(new Date());
			repository.save(externalAccess);
		}
	}
	
	
	/**
	 * Method that obtain url with distribution point TSL and realize test about this url.
	 * 
	 * @throws Exception Any exception that occurs during the execution.
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void prepareUrlExternalAccess() {
		try {
			// Obtenemos todas las regiones dadas de alta en base de datos.
    		List<TslCountryRegion> tcrList = iTslCountryRegionService.getAllTslCountryRegion(false);
    		// Si la lista obtenida no es nula ni vacía, contianuamos.
    		if (tcrList != null && !tcrList.isEmpty()) {
    			
    			List<String> listUrlDistributionPointDPResult = new ArrayList<>();
    			List<String> listUrlIssuerResult = new ArrayList<>();
    			List<String> listUrlDistributionPointCRLResult = new ArrayList<>();
    			List<String> listUrlDistributionPointOCSPResult = new ArrayList<>();
    			
    			// Obtenemos todas las url de todas las TSL.
    			this.obtainAllUrl(tcrList, listUrlDistributionPointDPResult, listUrlIssuerResult, listUrlDistributionPointCRLResult, listUrlDistributionPointOCSPResult);
    			
    			// Recorreremos todas las urls obtenidas.
    			if(!listUrlDistributionPointDPResult.isEmpty()) {
    				// Eliminamos los duplicados
    				List<String> listUrlDistributionPointDPWithoutDuplicate = listUrlDistributionPointDPResult.stream().distinct().collect(Collectors.toList());
    				for (String urlDistributionPoint: listUrlDistributionPointDPWithoutDuplicate) {
    					this.testConnExternalAccessAndSaveResult(urlDistributionPoint, DISTRIBUTIONPOINT);
					}
    			}
    			if(!listUrlIssuerResult.isEmpty()) {
    				// Eliminamos los duplicados
    				List<String> listUrlIssuerWithoutDuplicate = listUrlIssuerResult.stream().distinct().collect(Collectors.toList());
    				for (String urlIssuerAlternativeName: listUrlIssuerWithoutDuplicate) {
    					this.testConnExternalAccessAndSaveResult(urlIssuerAlternativeName, ISSUERALTERNATIVENAME);
					}
    			}
    			if(!listUrlDistributionPointCRLResult.isEmpty()) {
    				// Eliminamos los duplicados
    				List<String> listUrlDistributionPointCRLWithoutDuplicate = listUrlDistributionPointCRLResult.stream().distinct().collect(Collectors.toList());
    				for (String urlDistributionPointCRL: listUrlDistributionPointCRLWithoutDuplicate) {
    					this.testConnExternalAccessAndSaveResult(urlDistributionPointCRL, DISTRIBUTIONPOINTCRL);
					}
    			}
    			if(!listUrlDistributionPointOCSPResult.isEmpty()) {
    				// Eliminamos los duplicados
    				List<String> listUrlDistributionPointOCSPWithoutDuplicate = listUrlDistributionPointOCSPResult.stream().distinct().collect(Collectors.toList());
    				for (String urlDistributionPointOCSP: listUrlDistributionPointOCSPWithoutDuplicate) {
    					this.testConnExternalAccessAndSaveResult(urlDistributionPointOCSP, DISTRIBUTIONPOINTOCSP);
					}
    			}
    		}
		} catch (Exception e) {
			String msg = Language.getResCoreTsl(ICoreTslMessages.LOGMTSL400);
			LOGGER.error(msg, e);
		}
	}

	/**
	 * Method that obtain all url for distinct distribution point.
	 * 
	 * @param tcrList parameter that contain list with country region.
	 * @param listUrlDistributionPointDPResult parameter that store all url valid who distribution point.
	 * @param listUrlIssuerResult parameter that store all url valid who issuer.
	 * @param listUrlDistributionPointCRLResult parameter that store all url valid who CRL.
	 * @param listUrlDistributionPointOCSPResult parameter that store all url valid who OCSP.
	 * @throws TSLCacheException if occurs any error.
	 * @throws TSLArgumentException if occurs any error.
	 * @throws TSLParsingException if occurs any error.
	 * @throws TSLMalformedException if occurs any error.
	 * @throws TSLCertificateValidationException if occurs any error.
	 */
	private void obtainAllUrl(List<TslCountryRegion> tcrList, List<String> listUrlDistributionPointDPResult, List<String> listUrlIssuerResult, List<String> listUrlDistributionPointCRLResult, List<String> listUrlDistributionPointOCSPResult) throws TSLCacheException, TSLArgumentException, TSLParsingException, TSLMalformedException, TSLCertificateValidationException {
		// Por cada una de las regiones almacenaremos las urls de acceso.
		for (TslCountryRegion tcr: tcrList) {
			// Obtenemos el TSL Data asociado.
			TslData td = iTslDataService.getTslDataById(tcr.getTslData().getIdTslData(), true, false);
			// Si no es nulo, continuamos.
			LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL398, new Object[] { tcr.getCountryRegionCode() }));
			if (td != null) {
				// Tratamos de parsearlo.
				ITSLObject tslObject =  TSLManager.getInstance().buildAndCheckTSL(td);
				// Si lo hemos conseguido parsear...
				if (tslObject != null) {
					// Accedemos a la url del punto de distribución en caso de que exista. Solo existe una por TSL.
					if (tslObject.getSchemeInformation().isThereSomeDistributionPoint()) {
						for (int i = 0; i < tslObject.getSchemeInformation().getDistributionPoints().size(); i++) {
							if (!tslObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".pdf") && !tslObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".PDF")) {
								String uriTslLocation =  tslObject.getSchemeInformation().getDistributionPoints().get(i).toString();
								listUrlDistributionPointDPResult.add(uriTslLocation);
								break;
							}
						}
					}
					
					// Obtenemos los certificados de la TSL
					List<X509Certificate> listX509Certificate = TSLManager.getInstance().getListCertificatesTSL(tslObject);
					for (X509Certificate x509Certificate: listX509Certificate) {
						WrapperX509Cert wrapperX509Cert = new WrapperX509Cert(x509Certificate);
						
						// Buscamos la url del IssuerAlternativeName
						String urlIssuerAlternativeName = wrapperX509Cert.getIssuerAlternativeName();
						if(null != urlIssuerAlternativeName && !UtilsStringChar.isNullOrEmpty(urlIssuerAlternativeName)) {
							listUrlIssuerResult.add(urlIssuerAlternativeName);
						}
						
						// Buscamos la url del DistributionPointCRL
						List<String> listUrlDistributionPointCRL = this.searchUrlDistributionPointCrl(x509Certificate);
						if(!listUrlDistributionPointCRL.isEmpty()) {
							listUrlDistributionPointCRLResult.addAll(listUrlDistributionPointCRL);
						}
						
						// Buscamos la url del DistributionPointOCSP
						List<String> listUrlDistributionPointOCSP = this.searchUrlDistributionPointOcsp(x509Certificate);
						if(!listUrlDistributionPointOCSP.isEmpty()) {
							listUrlDistributionPointOCSPResult.addAll(listUrlDistributionPointOCSP);
						}
					}
				}
			}
			LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL399, new Object[] { tcr.getCountryRegionCode() }));
		}
	}
	
	/**
	 * Method that validates the input x509 v3 certificate using the distribution point information on it.
	 * @param cert Certificate X509v3 to validate its revocation.
	 * @return list with url for distribution point OCSP.
	 */
	private List<String> searchUrlDistributionPointOcsp(X509Certificate x509Certificate) {
		List<String> listUrlDistributionPointOCSP = new ArrayList<>();
		
		// Recuperamos la información de acceso a los servicios disponibles en
		// la autoridad.
		AuthorityInformationAccess aia = null;
		try {
			aia = AuthorityInformationAccess.fromExtensions(UtilsCertificate.getBouncyCastleCertificate(x509Certificate).getTBSCertificate().getExtensions());
		} catch (Exception e) {
			LOGGER.error(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL186), e);
		}
		
		// Si la información recuperada no es nula, y al menos hay un
		// elemento...
		if (aia != null && aia.getAccessDescriptions() != null && aia.getAccessDescriptions().length > 0) {
			
			// Los vamos recorriendo uno a uno hasta que encontremos un
			// servicio OCSP que se pueda usar.
			AccessDescription[ ] accessDescArray = aia.getAccessDescriptions();
			
			for (AccessDescription accessDescription: accessDescArray) {
				if (OCSPObjectIdentifiers.id_pkix_ocsp.equals(accessDescription.getAccessMethod())) {
					// Obtenemos la URI de acceso al OCSP.
					GeneralName accessLocationGeneralName = accessDescription.getAccessLocation();
					if (accessLocationGeneralName.getTagNo() == GeneralName.uniformResourceIdentifier) {
						String ocspUriString = ((DERIA5String) accessLocationGeneralName.getName()).getString();
						listUrlDistributionPointOCSP.add(ocspUriString);
					}
				}
			}
		}
		
		return listUrlDistributionPointOCSP;
	}

	/**
	 * Search the URIs defined in the CRLDistributionPoint Extension.
	 * @return the URIs defined in the CRLDistributionPoint Extension, <code>null</code> if there is not.
	 */
	private List<String> searchUrlDistributionPointCrl(X509Certificate x509Certificate) {
		List<String> listUrlDistributionPointCRL = new ArrayList<>();
		
		// Recuperamos el listado de Distribution Points de tipo CRL.
		CRLDistPoint crlDps = null;
		ASN1InputStream dIn = null;
		try {
			Extensions extensions = UtilsCertificate.getBouncyCastleCertificate(x509Certificate).getTBSCertificate().getExtensions();
			Extension ext = extensions.getExtension(Extension.cRLDistributionPoints);
			byte[ ] octs = ext.getExtnValue().getOctets();
			dIn = new ASN1InputStream(octs);
			crlDps = CRLDistPoint.getInstance(dIn.readObject());
		} catch (Exception e1) {
			crlDps = null;
		} finally {
			if (dIn != null) {
				try {
					dIn.close();
				} catch (IOException e) {
					dIn = null;
				}
			}
		}
		// Si lo hemos obtenido...
		if (crlDps != null) {
			// Si la extensión no está vacía...
			DistributionPoint[ ] crlDpsArray = crlDps.getDistributionPoints();
			if (crlDpsArray != null && crlDpsArray.length > 0) {
				// Los vamos recorriendo uno a uno hasta encontrar una CRL que
				// se
				// pueda obtener...
				X509CRL crl = null;
				String uriSelected = null;
				for (int indexDp = 0; crl == null && indexDp < crlDpsArray.length; indexDp++) {
					// Obtenemos el name.
					DistributionPointName dpName = crlDpsArray[indexDp].getDistributionPoint();

					// Dentro del Distribution point el campo Name me
					// indica la CRL ---> Analizando
					if (dpName == null) {
						// Si no hay name en este punto de distribución
						// pruebo con otro.
						continue;
					}

					// Si se trata de un RelativeDistinguishedName,
					// entonces es un conjunto (SET)
					// de AttributeTypeAndValue, que a su vez es una
					// secuencia (SEQUENCE) de
					// pares (AttributeType, AttributeValue), que al
					// final son pares (OID, valor).
					if (dpName.getType() == DistributionPointName.NAME_RELATIVE_TO_CRL_ISSUER) {
						// Como no se conoce la especificación para
						// obtener los datos de la ruta CRL
						// a partir de los pares antes especificados, se
						// continúa con el siguiente DP.
						continue;
					} else {
						// La ruta CRL siempre vendrá en un
						// uniformResourceIdentifier (tipo 6 - IA5String)
						GeneralName[ ] generalNames = GeneralNames.getInstance(dpName.getName()).getNames();
						List<URI> uriDistPointsList = new ArrayList<URI>();
						for (GeneralName gn: generalNames) {
							if (gn.getTagNo() == GeneralName.uniformResourceIdentifier) {
								String uriString = ((DERIA5String) gn.getName()).getString();
								try {
									uriDistPointsList.add(new URI(uriString));
								} catch (URISyntaxException e) {
									continue;
								}
							}
						}
						
						// Si al menos se ha obtenido alguna ruta, se
						// continúa:
						if (!uriDistPointsList.isEmpty()) {
							// Recorremos las URI
							for (int index = 0; crl == null && index < uriDistPointsList.size(); index++) {
								// Obtenemos la uri a analizar.
								URI uri = uriDistPointsList.get(index);

								uriSelected = uri.toString();
								listUrlDistributionPointCRL.add(uriSelected);
							}
						}
					}
				}
			}
		}
		return listUrlDistributionPointCRL;
	}
}
