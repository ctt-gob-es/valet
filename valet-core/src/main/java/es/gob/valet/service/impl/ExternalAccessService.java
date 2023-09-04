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
 * @version 1.9, 10/08/2023.
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

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import es.gob.valet.alarms.AlarmsManager;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.i18n.messages.ICoreTslMessages;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.repository.ExternalAccessRepository;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.ExternalAccessTablesRepository;
import es.gob.valet.persistence.configuration.model.specification.ExternalAccessSpecification;
import es.gob.valet.persistence.configuration.model.utils.IAlarmIdConstants;
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
import es.gob.valet.tsl.parsing.impl.common.TSLObject;

/**
 * <p>Class that implements the communication with the operations of the persistence layer for ExternalAccess.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 2.0, 04/09/2023.
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
	private ExternalAccessRepository externalAccessRepository;

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
	 * Attribute that represents the service object for accessing the repository of country region.
	 */
	@Autowired
	private TslCountryRegionRepository tslCountryRegionRepository;
	
	private ExternalAccessSpecification externalAccessSpecification;
	
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
	 * Constant attribute that represents the token 'iterateAndSaveUrl'.
	 */
	private static final String ITERATEANDSAVEURL = "iterateAndSaveUrl";
	
	/**
	 * Constant attribute that represents the token 'iterateAndDeleteUrl'.
	 */
	private static final String ITERATEANDDELETEURL = "iterateAndDeleteUrl";
	
	/**
	 * Constant attribute that represents the token '1'.
	 */
	public static final int OPERATION1 = 1;
	
	/**
	 * Constant attribute that represents the token '2'.
	 */
	public static final int OPERATION2 = 2;
	
	/**
	 * Constant attribute that represents the token '3'.
	 */
	public static final int OPERATION3 = 3;

	/**
	 * Constant attribute that represents the token '4'.
	 */
	public static final int OPERATION4 = 4;
	
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
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	@Override
	public List<ExternalAccess>  getAllList(ExternalAccess request, Date fromDate,Date toDate) {
		externalAccessSpecification = new ExternalAccessSpecification(request);
		Specification<ExternalAccess> specificationExternalAccess = externalAccessSpecification.getExternalAccess(request, fromDate, toDate);
		return externalAccessRepository.findAll(specificationExternalAccess);
	}
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	@Override
	public List<ExternalAccessDTO>  getAllListDTO(ExternalAccess request, Date fromDate,Date toDate) {
		externalAccessSpecification = new ExternalAccessSpecification(request);
		Specification<ExternalAccess> specificationExternalAccess = externalAccessSpecification.getExternalAccess(request, fromDate, toDate);
		List<ExternalAccess>  listExternalAccess = externalAccessRepository.findAll(specificationExternalAccess);
		return createListExternalAccessDTO(listExternalAccess);
		
	}
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getAll(java.lang.Long)
	 */
	@Override
	public List<ExternalAccessDTO>  getListDTObyId(List<Long> ids) {
		List<ExternalAccess>  listExternalAccess  = new ArrayList<>();
		if(ids.size()>1000) {
			for (int i = 0; i < ids.size();i=i+1000) {
				List<Long> registros = new ArrayList<>();
				if((i+1000)<ids.size()) {
					registros = ids.subList(i, i+1000);
				}else {
					registros = ids.subList(i, ids.size());
				}
				listExternalAccess.addAll(externalAccessRepository.findByIdUrlInQuery(registros));
		    } 
		}else {
			listExternalAccess = externalAccessRepository.findByIdUrlInQuery(ids);
		}
		return createListExternalAccessDTO(listExternalAccess);
		
	}
	
	/**
	 * 
	 * @param listExternalAccess
	 * @return
	 */
	public List<ExternalAccessDTO> createListExternalAccessDTO(List<ExternalAccess> listExternalAccess){
		 List<ExternalAccessDTO> listExternalAccessDTO = new ArrayList<ExternalAccessDTO>();
		 for(ExternalAccess externalAccess: listExternalAccess) {
			 ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
			 externalAccessDTO.setIdUrl(externalAccess.getIdUrl());
			 externalAccessDTO.setLastConn(externalAccess.getLastConn());
			 externalAccessDTO.setUrl(externalAccess.getUrl());
			 externalAccessDTO.setStateConn(externalAccess.getStateConn());
			 externalAccessDTO.setOriginUrl(externalAccess.getOriginUrl());
			 listExternalAccessDTO.add(externalAccessDTO);
		 }
		return listExternalAccessDTO;
		
	}


	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getUrlDataById(java.lang.Long)
	 */
	@Override
	public ExternalAccess getUrlDataById(Long idUrlData) {
		return externalAccessRepository.findByIdUrl(idUrlData);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#getDataUrlByUrl(java.lang.String)
	 */
	@Override
	public ExternalAccess getDataUrlByUrl(String url) {
		return externalAccessRepository.findByUrl(url);

	}
	
	/**
	 * Method that realize test of connection url and save result operation.
	 * 
	 * @param uriTslLocation parameter that contain url.
	 * @param originUrl the originUrl to set
	 * @param externalAccessDTO parameter that contain all information about operation.
	 * @return object persist in BD.
	 */
	@Override
	public ExternalAccess getExternalAccessAndTestConn(String uriTslLocation, String originUrl, ExternalAccessDTO externalAccessDTO) {
		ExternalAccess externalAccess = externalAccessRepository.findByUrl(uriTslLocation);
		// Realizamos el test de conexión con la url
		boolean stateConn = this.testConnUrl(uriTslLocation);
		
		// Si no existe creamos el registro con el test de conexión realizado.
		if (null == externalAccess) {
			externalAccess = new ExternalAccess();
			externalAccess.setUrl(uriTslLocation);
			externalAccess.setOriginUrl(originUrl);
			externalAccess.setStateConn(stateConn);
			externalAccess.setLastConn(new Date());
			TslCountryRegion tslCountryRegion = new TslCountryRegion();
			tslCountryRegion.setIdTslCountryRegion(externalAccessDTO.getIdCountryRegion());
			externalAccess.setTslCountryRegion(tslCountryRegion);
		} else {
			externalAccess.setStateConn(stateConn);
			externalAccess.setLastConn(new Date());
		}
		
		return externalAccess;
	}

	/**
	 * Method that get external access found.
	 * 
	 * @param uriTslLocation parameter that contain url.
	 * @return object found in BD.
	 */
	public ExternalAccess getExternalAccess(String uriTslLocation) {
		ExternalAccess externalAccess = externalAccessRepository.findByUrl(uriTslLocation);
		return externalAccess;
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
		        environment.put("com.sun.jndi.ldap.read.timeout", String.valueOf(NumberConstants.NUM1000));
		        environment.put("com.sun.jndi.ldap.connect.timeout", String.valueOf(NumberConstants.NUM1000));
		        
		        LdapContext context = new InitialLdapContext(environment, null);
		        context.close();
			} else {
				URL url = new URL(uriTslLocation);
				
				if(url.getProtocol().equals(HTTP)) {
					URLConnection connection = url.openConnection();
					connection.setConnectTimeout(NumberConstants.NUM1000);
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
					connection.setConnectTimeout(NumberConstants.NUM1000);
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
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#prepareUrlExternalAccessForTask()
	 */
	public void prepareUrlExternalAccessForTask() {
		long timeProcess = System.currentTimeMillis();
		LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL406));
		// Obtenemos todas las url de la BD.
		List<ExternalAccess> listExternalAccess = externalAccessRepository.findAll();
		
		// Cargamos un nuevo DTO con toda la información encapsulada.
		ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
		externalAccessDTO.setListUrlDistributionPointCRLResult(listExternalAccess.stream().filter(p -> p.getOriginUrl().equals(DISTRIBUTIONPOINTCRL)).map(ExternalAccess::getUrl).collect(Collectors.toList()));
		externalAccessDTO.setListUrlDistributionPointDPResult(listExternalAccess.stream().filter(p -> p.getOriginUrl().equals(DISTRIBUTIONPOINT)).map(ExternalAccess::getUrl).collect(Collectors.toList()));
		externalAccessDTO.setListUrlDistributionPointOCSPResult(listExternalAccess.stream().filter(p -> p.getOriginUrl().equals(DISTRIBUTIONPOINTOCSP)).map(ExternalAccess::getUrl).collect(Collectors.toList()));
		externalAccessDTO.setListUrlIssuerResult(listExternalAccess.stream().filter(p -> p.getOriginUrl().equals(ISSUERALTERNATIVENAME)).map(ExternalAccess::getUrl).collect(Collectors.toList()));
		
		// Realizamos los cambios en la bd, en base al resultado del test de conexión.
		this.makeChangesToExternalAccess(externalAccessDTO, ITERATEANDSAVEURL);
		
		// Enviamos la alarmma en caso de que en el resultado de los cambios realizados haya algun test que haya fallado.
		String messageHead = Language.getResCoreGeneral(ICoreGeneralMessages.ALM009_EVENT_001);
		this.launchAlarmIfTestConnFail(externalAccessDTO.getListExternalAccessResult(), messageHead);
		
		LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL407, new Object[ ] { String.valueOf((System.currentTimeMillis() - timeProcess)) }));
	}
	
	/**
	 * Method that realize test connection to external access and update result in BD to list id url. 
	 * 
	 * @param listIdUrl parameter that contain id for realize test connection.
	 */
	public void prepareUrlExternalAccessForTestConn(List<Long> listIdUrl) {
		long timeProcess = System.currentTimeMillis();
		LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL414));
		if(null != listIdUrl && !listIdUrl.isEmpty()) {
			// Obtenemos todas las url de la BD.
			List<ExternalAccess> listExternalAccess = externalAccessRepository.findByIdUrlInQuery(listIdUrl);
			
			ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
			externalAccessDTO.setListUrlDistributionPointCRLResult(listExternalAccess.stream().filter(p -> p.getOriginUrl().equals(DISTRIBUTIONPOINTCRL)).map(ExternalAccess::getUrl).collect(Collectors.toList()));
			externalAccessDTO.setListUrlDistributionPointDPResult(listExternalAccess.stream().filter(p -> p.getOriginUrl().equals(DISTRIBUTIONPOINT)).map(ExternalAccess::getUrl).collect(Collectors.toList()));
			externalAccessDTO.setListUrlDistributionPointOCSPResult(listExternalAccess.stream().filter(p -> p.getOriginUrl().equals(DISTRIBUTIONPOINTOCSP)).map(ExternalAccess::getUrl).collect(Collectors.toList()));
			externalAccessDTO.setListUrlIssuerResult(listExternalAccess.stream().filter(p -> p.getOriginUrl().equals(ISSUERALTERNATIVENAME)).map(ExternalAccess::getUrl).collect(Collectors.toList()));
			
			this.makeChangesToExternalAccess(externalAccessDTO, ITERATEANDSAVEURL);
			String messageHead = Language.getResCoreGeneral(ICoreGeneralMessages.ALM009_EVENT_002);
			this.launchAlarmIfTestConnFail(externalAccessDTO.getListExternalAccessResult(), messageHead);
		}
		LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL415, new Object[ ] { String.valueOf((System.currentTimeMillis() - timeProcess)) }));
	}
	
	/**
	 * Method that obtain url with distribution point TSL and realize test about this url.
	 * 
	 * @throws Exception Any exception that occurs during the execution.
	 */
	public void prepareUrlExternalAccessInitPlatform() {
		long timeProcess = System.currentTimeMillis();
		LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL408));
		try {
			// Obtenemos todas las regiones dadas de alta en base de datos.
    		List<TslCountryRegion> tcrList = iTslCountryRegionService.getAllTslCountryRegion(false);
    		// Si la lista obtenida no es nula ni vacía, contianuamos.
    		if (tcrList != null && !tcrList.isEmpty()) {
    			
    			// Obtenemos todas las url de todas las TSL.
    			List<ExternalAccessDTO> listExternalAccessDTO = this.obtainAllUrlToRegionTSL(tcrList);
    			
    			// Recorremos todas las urls obtenidas de las TSL y almacenamos el resultado de haber realizado cambios en accesos externos.
    			List<ExternalAccess> listExternalAccessResult = new ArrayList<>();
    			for (ExternalAccessDTO externalAccessDTO: listExternalAccessDTO) {
    				this.makeChangesToExternalAccess(externalAccessDTO, ITERATEANDSAVEURL);
    				listExternalAccessResult.addAll(externalAccessDTO.getListExternalAccessResult());
    			}
    			
    			// Enviamos la alarmma en caso de que en el resultado de los cambios realizados haya algun test que haya fallado.
    			String messageHead = Language.getResCoreGeneral(ICoreGeneralMessages.ALM009_EVENT_003);
    			this.launchAlarmIfTestConnFail(listExternalAccessResult, messageHead);
    		}
		} catch (Exception e) {
			String msg = Language.getResCoreTsl(ICoreTslMessages.LOGMTSL400);
			LOGGER.error(msg, e);
		}
		LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL409, new Object[ ] { String.valueOf((System.currentTimeMillis() - timeProcess)) }));
	}

	/**
	 * Method that launch alarm if found test connection failed.
	 * 
	 * @param listExternalAccessResult parameter that contain list with object of result operation.
	 * @param messageHead parameter that contain head message for mail.
	 */
	private void launchAlarmIfTestConnFail(List<ExternalAccess> listExternalAccessResult, String messageHead) {
		// Filtramos aquellos accesos externos resultantes que hayan dado error en el test de conexión. Si es así enviaremos la alarma.
		StringBuffer messageMail = new StringBuffer();
		if(null != listExternalAccessResult.stream().filter(p -> p.getStateConn() == false).findAny()) {
			messageMail.append(messageHead);
			List<TslCountryRegion> listTslCountryRegion = tslCountryRegionRepository.findAll();
			for (TslCountryRegion tslCountryRegion: listTslCountryRegion) {
				List<ExternalAccess> listExternalAccessTestConnKo =  listExternalAccessResult.stream().filter(p -> p.getStateConn() == false && p.getTslCountryRegion().getIdTslCountryRegion().equals(tslCountryRegion.getIdTslCountryRegion())).collect(Collectors.toList());
				if(null != listExternalAccessTestConnKo && !listExternalAccessTestConnKo.isEmpty()) {
					this.createMessageMail(tslCountryRegion, listExternalAccessTestConnKo, messageMail);
				}
			}
			// Lanzamos la alarma correspondiente...
			AlarmsManager.getInstance().registerAlarmEvent(IAlarmIdConstants.ALM009_CONNECTION_FAIL, messageMail.toString());
		}
	}

	/**
	 * Method that create message for send email.
	 * 
	 * @param tslCountryRegion TSL Country/Region to use.
	 * @param listExternalAccessTestConnKo parameter that contain list with object of fail test connection.
	 * @param messageMail parameter that contain body of message.
	 */
	private void createMessageMail(TslCountryRegion tslCountryRegion, List<ExternalAccess> listExternalAccessTestConnKo, StringBuffer messageMail) {
		messageMail.append(System.lineSeparator()).append(System.lineSeparator());
		messageMail.append(Language.getFormatResCoreGeneral(ICoreGeneralMessages.ALM009_EVENT_004, new Object[ ] { tslCountryRegion.getCountryRegionName(), tslCountryRegion.getTslData().getTslImpl().getVersion() }));
		for (ExternalAccess externalAccess: listExternalAccessTestConnKo) {
			messageMail.append(System.lineSeparator());
			messageMail.append(externalAccess.getUrl());
			messageMail.append(";");
			messageMail.append(new UtilsDate(externalAccess.getLastConn()));
		}
	}

	/**
	 * Method that update external access from TSL.
	 * 
	 * @param tslObject TSL object representation to use.
	 * @throws TSLCertificateValidationException if occurs any error.
	 */
	public void prepareUrlExternalAccessToTSL(ITSLObject tslObject) throws TSLCertificateValidationException {
		long timeProcess = System.currentTimeMillis();
		LOGGER.info(Language.getResCoreTsl(ICoreTslMessages.LOGMTSL410));
		// Generamos un nuevo DTO con la información de las URL del país a insertar/editar.
		ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
		externalAccessDTO.setIdCountryRegion(tslCountryRegionRepository.findByCountryRegionCode(tslObject.getSchemeInformation().getSchemeTerritory()).getIdTslCountryRegion());
		// Extraemos las url de la TSL cargada.
		this.extractUrlToDistributionPoints(externalAccessDTO, tslObject);
		// Realizamos los cambios en la bd, en base al resultado del test de conexión.
		this.makeChangesToExternalAccess(externalAccessDTO, ITERATEANDSAVEURL);
		LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL411, new Object[ ] { String.valueOf((System.currentTimeMillis() - timeProcess)) }));
	}

	/**
	 * Method that delete external access from TSL.
	 * 
	 * @param tslObject TSL object representation to use.
	 * @throws TSLCertificateValidationException if occurs any error.
	 */
	public void prepareUrlExternalAccessToDelete(ITSLObject tslObject) throws TSLCertificateValidationException {
		LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL412, new Object[ ] { tslObject.getSchemeInformation().getSchemeTerritory() }));
		// Generamos un nuevo DTO con la información de las URL del país a eliminar.
		ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
		List<ExternalAccess> listExternalAccess = externalAccessRepository.findAll().stream().filter(p -> p.getTslCountryRegion().getCountryRegionCode().equals(tslObject.getSchemeInformation().getSchemeTerritory())).collect(Collectors.toList());
		externalAccessDTO.setListExternalAccessResult(listExternalAccess);
		// Realizamos los cambios en la bd, en base al resultado del test de conexión.
		this.makeChangesToExternalAccess(externalAccessDTO, ITERATEANDDELETEURL);
		LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL413, new Object[ ] { tslObject.getSchemeInformation().getSchemeTerritory() }));
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#makeChangesToExternalAccess(es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO, java.lang.String)
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public synchronized void makeChangesToExternalAccess(ExternalAccessDTO externalAccessDTO, String action) {
		try {
			if(action.equals(ITERATEANDSAVEURL)) {
				// Recorreremos todas las urls obtenidas y realizamos test de conexión.
				this.iterateAllUrlAndTestConn(externalAccessDTO);
				
				// Almacenamos los resultados de los test
				externalAccessRepository.saveAll(externalAccessDTO.getListExternalAccessResult());
			} else if(action.equals(ITERATEANDDELETEURL)) {
				// Recorreremos todas las urls obtenidas.
				this.iterateAllUrl(externalAccessDTO);
				
				// Eliminamos los puntos de distribución de la TSL.
				externalAccessRepository.deleteAll(externalAccessDTO.getListExternalAccessResult());
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	/**
	 * Method that iterate all url and obtain external access.
	 * 
	 * @param externalAccessDTO parameter that contain all information about operation.
	 */
	private void iterateAllUrl(ExternalAccessDTO externalAccessDTO) {
		// Eliminamos los duplicados en puntos de distribución
		List<String> listUrlDistributionPointDPWithoutDuplicate = externalAccessDTO.getListUrlDistributionPointDPResult().stream().distinct().collect(Collectors.toList());
		for (String urlDistributionPoint: listUrlDistributionPointDPWithoutDuplicate) {
			externalAccessDTO.getListExternalAccessResult().add(this.getExternalAccess(urlDistributionPoint));
		}
		
		// Eliminamos los duplicados en puntos de distribución de issuers alternative name
		List<String> listUrlIssuerWithoutDuplicate = externalAccessDTO.getListUrlIssuerResult().stream().distinct().collect(Collectors.toList());
		for (String urlIssuerAlternativeName: listUrlIssuerWithoutDuplicate) {
			externalAccessDTO.getListExternalAccessResult().add(this.getExternalAccess(urlIssuerAlternativeName));
		}
		
		// Eliminamos los duplicados en puntos de distribución de crl
		List<String> listUrlDistributionPointCRLWithoutDuplicate = externalAccessDTO.getListUrlDistributionPointCRLResult().stream().distinct().collect(Collectors.toList());
		for (String urlDistributionPointCRL: listUrlDistributionPointCRLWithoutDuplicate) {
			externalAccessDTO.getListExternalAccessResult().add(this.getExternalAccess(urlDistributionPointCRL));
		}
		
		// Eliminamos los duplicados en puntos de distribución de ocsp
		List<String> listUrlDistributionPointOCSPWithoutDuplicate = externalAccessDTO.getListUrlDistributionPointOCSPResult().stream().distinct().collect(Collectors.toList());
		for (String urlDistributionPointOCSP: listUrlDistributionPointOCSPWithoutDuplicate) {
			externalAccessDTO.getListExternalAccessResult().add(this.getExternalAccess(urlDistributionPointOCSP));
		}
	}

	/**
	 * Method that iterate all url, obtain external access and realize test conection.
	 * 
	 * @param externalAccessDTO parameter that contain all information about operation.
	 */
	private void iterateAllUrlAndTestConn(ExternalAccessDTO externalAccessDTO) {
		// Eliminamos los duplicados en puntos de distribución
		List<String> listUrlDistributionPointDPWithoutDuplicate = externalAccessDTO.getListUrlDistributionPointDPResult().stream().distinct().collect(Collectors.toList());
		for (String urlDistributionPoint: listUrlDistributionPointDPWithoutDuplicate) {
			externalAccessDTO.getListExternalAccessResult().add(this.getExternalAccessAndTestConn(urlDistributionPoint, DISTRIBUTIONPOINT, externalAccessDTO));
		}
		
		// Eliminamos los duplicados en puntos de distribución de issuers alternative name
		List<String> listUrlIssuerWithoutDuplicate = externalAccessDTO.getListUrlIssuerResult().stream().distinct().collect(Collectors.toList());
		for (String urlIssuerAlternativeName: listUrlIssuerWithoutDuplicate) {
			externalAccessDTO.getListExternalAccessResult().add(this.getExternalAccessAndTestConn(urlIssuerAlternativeName, ISSUERALTERNATIVENAME, externalAccessDTO));
		}
		
		// Eliminamos los duplicados en puntos de distribución de crl
		List<String> listUrlDistributionPointCRLWithoutDuplicate = externalAccessDTO.getListUrlDistributionPointCRLResult().stream().distinct().collect(Collectors.toList());
		for (String urlDistributionPointCRL: listUrlDistributionPointCRLWithoutDuplicate) {
			externalAccessDTO.getListExternalAccessResult().add(this.getExternalAccessAndTestConn(urlDistributionPointCRL, DISTRIBUTIONPOINTCRL, externalAccessDTO));
		}
		
		// Eliminamos los duplicados en puntos de distribución de ocsp
		List<String> listUrlDistributionPointOCSPWithoutDuplicate = externalAccessDTO.getListUrlDistributionPointOCSPResult().stream().distinct().collect(Collectors.toList());
		for (String urlDistributionPointOCSP: listUrlDistributionPointOCSPWithoutDuplicate) {
			externalAccessDTO.getListExternalAccessResult().add(this.getExternalAccessAndTestConn(urlDistributionPointOCSP, DISTRIBUTIONPOINTOCSP, externalAccessDTO));
		}
	}

	/**
	 * Method that obtain all url for distinct distribution point.
	 * 
	 * @param tcrList parameter that contain list with country region.
	 * @throws TSLCacheException if occurs any error.
	 * @throws TSLArgumentException if occurs any error.
	 * @throws TSLParsingException if occurs any error.
	 * @throws TSLMalformedException if occurs any error.
	 * @throws TSLCertificateValidationException if occurs any error.
	 */
	private List<ExternalAccessDTO> obtainAllUrlToRegionTSL(List<TslCountryRegion> tcrList) throws TSLCacheException, TSLArgumentException, TSLParsingException, TSLMalformedException, TSLCertificateValidationException {
		List<ExternalAccessDTO> listExternalAccessDTO = new ArrayList<>();
		// Por cada una de las regiones almacenaremos las urls de acceso.
		for (TslCountryRegion tcr: tcrList) {
			if(null != tcr.getTslData().getIdTslData()) {
				// Obtenemos el TSL Data asociado.
				TslData td = iTslDataService.getTslDataById(tcr.getTslData().getIdTslData(), true, false);
				long initProcess = System.currentTimeMillis();
				// Si no es nulo, continuamos.
				LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL398, new Object[] { tcr.getCountryRegionCode() }));
				if (td != null) {
					// Tratamos de parsearlo.
					ITSLObject tslObject =  TSLManager.getInstance().buildAndCheckTSL(td);
					ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
					externalAccessDTO.setIdCountryRegion(tcr.getIdTslCountryRegion());
					this.extractUrlToDistributionPoints(externalAccessDTO, tslObject);
					listExternalAccessDTO.add(externalAccessDTO);
				}
				LOGGER.info(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL399, new Object[] { tcr.getCountryRegionCode(), (System.currentTimeMillis() - initProcess) }));
			}
		}
		return listExternalAccessDTO;
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#extractUrlToDistributionPoints(es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO, es.gob.valet.tsl.parsing.ifaces.ITSLObject)
	 */
	public void extractUrlToDistributionPoints(ExternalAccessDTO externalAccessDTO, ITSLObject tslObject) throws TSLCertificateValidationException {
		// Si lo hemos conseguido parsear...
		if (tslObject != null) {
			// Accedemos a la url del punto de distribución en caso de que exista. Solo existe una por TSL.
			if (tslObject.getSchemeInformation().isThereSomeDistributionPoint()) {
				for (int i = 0; i < tslObject.getSchemeInformation().getDistributionPoints().size(); i++) {
					if (!tslObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".pdf") && !tslObject.getSchemeInformation().getDistributionPoints().get(i).toString().endsWith(".PDF")) {
						String uriTslLocation =  tslObject.getSchemeInformation().getDistributionPoints().get(i).toString();
						// filtraremos las url validas. Solo aquellas que contengan http, https o ldap.
						if(uriTslLocation.indexOf(HTTP) != -1 || uriTslLocation.indexOf(HTTPS) != -1 || uriTslLocation.indexOf(LDAP) != -1) {
							externalAccessDTO.getListUrlDistributionPointDPResult().add(uriTslLocation);
						} else {
							LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL416, new Object[ ] { uriTslLocation }));
						}
						break;
					}
				}
			}
			
			// Obtenemos los certificados de la TSL
			List<X509Certificate> listX509Certificate = TSLManager.getInstance().getListCertificatesTSL(tslObject);
			for (X509Certificate x509Certificate: listX509Certificate) {
				// Controlamos las posibles excepciones que se puedan producir al analizar el certificado. Si falla continuamos con el proceso.
				try {
					WrapperX509Cert wrapperX509Cert = new WrapperX509Cert(x509Certificate);
					
					// Buscamos la url del IssuerAlternativeName
					String urlIssuerAlternativeName = wrapperX509Cert.getIssuerAlternativeName();
					if(null != urlIssuerAlternativeName && !UtilsStringChar.isNullOrEmpty(urlIssuerAlternativeName)) {
						// filtraremos las url validas. Solo aquellas que contengan http, https o ldap.
						if(urlIssuerAlternativeName.indexOf(HTTP) != -1|| urlIssuerAlternativeName.indexOf(LDAP) != -1) {
							externalAccessDTO.getListUrlIssuerResult().add(urlIssuerAlternativeName);
						} else {
							LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL416, new Object[ ] { urlIssuerAlternativeName }));
						}
					}
					
					// Buscamos la url del DistributionPointCRL
					List<String> listUrlDistributionPointCRL = this.searchUrlDistributionPointCrl(x509Certificate);
					if(!listUrlDistributionPointCRL.isEmpty()) {
						externalAccessDTO.getListUrlDistributionPointCRLResult().addAll(listUrlDistributionPointCRL);
					}
					
					// Buscamos la url del DistributionPointOCSP
					List<String> listUrlDistributionPointOCSP = this.searchUrlDistributionPointOcsp(x509Certificate);
					if(!listUrlDistributionPointOCSP.isEmpty()) {
						externalAccessDTO.getListUrlDistributionPointOCSPResult().addAll(listUrlDistributionPointOCSP);
					}
				} catch (Exception e) {
					LOGGER.error(e);
				}
			}
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
						// filtraremos las url validas. Solo aquellas que contengan http, https o ldap.
						if(ocspUriString.indexOf(HTTP) != -1|| ocspUriString.indexOf(LDAP) != -1) {
							listUrlDistributionPointOCSP.add(ocspUriString);
						} else {
							LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL416, new Object[ ] { ocspUriString }));
						}
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
								// filtraremos las url validas. Solo aquellas que contengan http, https o ldap.
								if(uriSelected.indexOf(HTTP) != -1|| uriSelected.indexOf(LDAP) != -1) {
									listUrlDistributionPointCRL.add(uriSelected);
								} else {
									LOGGER.warn(Language.getFormatResCoreTsl(ICoreTslMessages.LOGMTSL416, new Object[ ] { uriSelected }));
								}
							}
						}
					}
				}
			}
		}
		return listUrlDistributionPointCRL;
	}
	
	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.service.ifaces.IExternalAccessService#operationsOnExternalAccess(int, es.gob.valet.tsl.parsing.impl.common.TSLObject, java.util.List)
	 */
	public synchronized void operationsOnExternalAccess(int operation, TSLObject tslObject, List<Long> listIdUrl) {
		switch (operation) {
			case OPERATION1:
				prepareUrlExternalAccessInitPlatform();
				break;
			case OPERATION2:
				try {
					prepareUrlExternalAccessToTSL(tslObject);
				} catch (TSLCertificateValidationException e) {
					LOGGER.error(e);
				}
				break;
			case OPERATION3:
				try {
					prepareUrlExternalAccessToDelete(tslObject);
				} catch (TSLCertificateValidationException e) {
					LOGGER.error(e);
				}
				break;
			case OPERATION4:
				prepareUrlExternalAccessForTask();
				break;
			default:
				break;
		}
	}
	
	/**
	 * 
	 * <p>Class to generate threads in distribution point extraction operations for TSL.</p>
	 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
	 * @version 1.0, 03/08/2023.
	 */
	public class ExternalAccessServiceThread extends Thread {
		
		private int operation;
		private TSLObject tslObject;
		
		/**
		 * 
		 * Constructor method for the class ExternalAccessService.java.
		 * 
		 * @param operation parameter that contain number operation to a switch.
		 * @param tslObject parameter that contain posible tsl extract.
		 */
		public ExternalAccessServiceThread(int operation, ITSLObject tslObject) {
			this.operation = operation;
			this.tslObject = (TSLObject) tslObject;
		}
		
		/**
		 * 
		 * {@inheritDoc}
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			operationsOnExternalAccess(this.operation, this.tslObject, null);
		}
	}


}
