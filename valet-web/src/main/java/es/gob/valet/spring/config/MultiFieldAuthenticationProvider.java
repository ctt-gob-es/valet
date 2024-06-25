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
 * <b>File:</b><p>es.gob.afirma.spring.config.MultiFieldAuthenticationProvider.java.</p>
 * <b>Description:</b><p> .</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * <b>Date:</b><p>03/4/2020.</p>
 * @author Gobierno de España.
 * @version 1.7, 17/06/2024.
 */
package es.gob.valet.spring.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import es.gob.afirma.transformers.TransformersConstants;
import es.gob.afirma.transformers.TransformersFacade;
import es.gob.afirma.utils.DSSConstants.DSSTagsRequest;
import es.gob.afirma.utils.DSSConstants.ReportDetailLevel;
import es.gob.afirma.utils.GeneralConstants;
import es.gob.afirma.utils.UtilsFileSystemCommons;
import es.gob.afirma.wsServiceInvoker.Afirma5ServiceInvokerFacade;
import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsCertificate;
import es.gob.valet.crypto.cades.verifier.CAdESAnalyzer;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.model.repository.UserValetRepository;

/**
 * <p>
 * Class .
 * </p>
 * <b>Project:</b>
 * <p>
 * Horizontal platform of validation services of multiPKI certificates and
 * electronic signature.
 * </p>
 * 
 * @version 1.7, 13/05/2023.
 */
@Component
public class MultiFieldAuthenticationProvider implements AuthenticationProvider {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(MultiFieldAuthenticationProvider.class);

	/**
	 * Attribute that represents the incorrect user.
	 */
	private static final String USER_NOT_AUTHORIZED = "Usuario no autorizado";

	/**
	 * Attribute that represents the incorrect user.
	 */
	private static final String USER_INCORRECT = "Usuario incorrecto";

	/**
	 * Attribute that represents the user will be blocked.
	 */
	private static final String USER_WILL_BE_BLOCKED = "Intentos superado";

	/**
	 * Attribute that represents the blocked user.
	 */
	private static final String USER_BLOCKED = "Usuario bloqueado";

	/**
	 * Attribute that represents the expired user.
	 */
	private static final String USER_EXPIRED = "Usuario caducado";

	/**
	 * Attribute that represents the success value of the major result
	 */
	private static final String MAJOR_SEUCCESS_RESULT = "urn:oasis:names:tc:dss:1.0:resultmajor:Success";

	/**
	 * Attribute that represents the success value of the minor result
	 */
	private static final String MINOR_SEUCCESS_RESULT = "urn:oasis:names:tc:dss:1.0:profiles:XSS:resultminor:valid:certificate:Definitive";

	/**
	 * Attribute that represents the key of the nif
	 */
	private static final String NIF_RESPONSABLE = "NIFResponsable";

	/**
	 * Attribute that represents the interface that provides access to the CRUD
	 * operations for the UserAfirma entity.
	 */
	@Autowired
	private final UserValetRepository userRepository;

	/**
	 * Constructor method for the class UserDetailServiceImpl.java.
	 *
	 * @param repository
	 */
	@Autowired
	public MultiFieldAuthenticationProvider(final UserValetRepository userRepository) {
		this.userRepository = userRepository;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#authenticate(org.springframework.security.core.Authentication)
	 */
	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

		Authentication auth = null;
		MultiFieldLoginUserDetails customUser = (MultiFieldLoginUserDetails) authentication.getPrincipal();
		String name = customUser.getUsername();
		String password = "";
		String signatureBase64 = customUser.getSignatureBase64();

		byte[ ] signBase64Bytes = null;
		UserValet curruser = null;
		boolean userExpired = false;

		if (!StringUtils.isEmpty(signatureBase64)) {
			signBase64Bytes = Base64.getDecoder().decode(signatureBase64.getBytes());
			CAdESAnalyzer analyzer = new CAdESAnalyzer();
			try {
				analyzer.init(signBase64Bytes);

				// Obtenemos los certificados de la firma con los que buscaremos
				// un usuario en base de datos. En el listado sólo debería haber
				// uno para la firma del login.
				List<X509Certificate> certs = analyzer.getSigningCertificates();

				if (CollectionUtils.isNotEmpty(certs)) {
					// Obtenemos el certificado
					X509Certificate certificate = certs.get(0);

					// Obtenemos los parámetros para validar un certificado en
					// un acceso de contingencia.
					String certPath = StaticValetConfig.getProperty(StaticValetConfig.LOGIN_ACCESS_CERTIFICATE);
					String certIssuerPath = StaticValetConfig.getProperty(StaticValetConfig.LOGIN_ACCESS_CERTIFICATE_ISSUER);
					String nifProperty = StaticValetConfig.getProperty(StaticValetConfig.LOGIN_ACCESS_CERTIFICATE_DNI);

					// Si queremos acceder con certificado de contigencia
					// realizaremos las distintaas validaciones
					if (validateCertificateUser(certificate, certPath, certIssuerPath, nifProperty)) {
						curruser = userRepository.findByNif(nifProperty);
					} else {
						// Si no queremos la validación con el certificado de
						// contigencia realizaremos la validación por @firma a
						// través de los campos lógicos
						// establecidos para éste certificado.
						String nif = validateCertFromAfirma(certificate);
						curruser = userRepository.findByNif(nif);
					}
					}else {
						auth = null;
					}

			} catch (CertificateException | IOException e) {
				auth = null;
			} catch (CommonUtilsException e) {
				auth = null;
			}

		}
		
		if (curruser != null) {
			if (curruser.getIsBlocked()) {
				throw new BadCredentialsException(USER_BLOCKED);
			} else {
				
				if (!StringUtils.isEmpty(signatureBase64)) {

					if (userExpired) {
						Integer intentosMaximos = 5;
						curruser.setAttemptsNumber(curruser.getAttemptsNumber() + 1);
						if (curruser.getAttemptsNumber() > intentosMaximos) {
							curruser.setIsBlocked(true);
							userRepository.save(curruser);
							throw new BadCredentialsException(USER_WILL_BE_BLOCKED);
						}

					} else {
						curruser.setAttemptsNumber(NumberConstants.NUM0);
					}

					List<GrantedAuthority> grantedAuths = new ArrayList<>();
					grantedAuths.add(new SimpleGrantedAuthority("USER"));
					
					if (name.isEmpty()) {
						name = curruser.getNif();
					}
					
					String hashedPassword = null;
					try {
						hashedPassword = getPasswordHashed(password);
					} catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					auth = new UsernamePasswordAuthenticationToken(name, hashedPassword, grantedAuths);

					InetAddress address = null;

					try {
						address = InetAddress.getLocalHost();
					} catch (UnknownHostException e) {
						e.printStackTrace();
					}

					String lastAccessIp = address.getHostName();
					curruser.setLastIpAccess(lastAccessIp);

					userRepository.save(curruser);
				} else {
					throw new BadCredentialsException(USER_NOT_AUTHORIZED);
				}
			}
		} else {
			throw new UsernameNotFoundException(USER_INCORRECT);
		}
		return auth;
	}
	

	/**
	 * Validates the provided X.509 certificate using the Afirma platform for certificate validation.
	 * Retrieves and returns the DNI associated with the certificate if validation is successful.
	 *
	 * @param certificate the X.509 certificate to validate.
	 * @return the NIF associated with the certificate if validation succeeds.
	 */
	private String validateCertFromAfirma(X509Certificate certificate) {
		String nif = null;
		try {
			byte[ ] encodedCert = certificate.getEncoded();
			String APPLICATION_NAME = StaticValetConfig.getProperty(StaticValetConfig.APP_NAME);

			Map<String, Object> inParams = new HashMap<String, Object>();
			inParams.put(DSSTagsRequest.CLAIMED_IDENTITY, APPLICATION_NAME);
			inParams.put(DSSTagsRequest.REPORT_DETAIL_LEVEL, ReportDetailLevel.ALL_DETAILS);
			inParams.put(DSSTagsRequest.CHECK_CERTIFICATE_STATUS, "true");
			inParams.put(DSSTagsRequest.RETURN_READABLE_CERT_INFO, "");
			inParams.put(DSSTagsRequest.X509_CERTIFICATE, UtilsFileSystemCommons.getFileBase64Encoded(encodedCert));

			String xmlInput = TransformersFacade.getInstance().generateXml(inParams, GeneralConstants.DSS_AFIRMA_VERIFY_CERTIFICATE_REQUEST, GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, TransformersConstants.VERSION_10);
			String xmlOutput = Afirma5ServiceInvokerFacade.getInstance().invokeService(xmlInput, GeneralConstants.DSS_AFIRMA_VERIFY_CERTIFICATE_REQUEST, GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, APPLICATION_NAME);
			LOGGER.info("Output: " + xmlOutput);

			Map<String, Object> propertiesResult = TransformersFacade.getInstance().parseResponse(xmlOutput, GeneralConstants.DSS_AFIRMA_VERIFY_CERTIFICATE_REQUEST, GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, TransformersConstants.VERSION_10);
			String ResultMajor = (String) propertiesResult.get(StaticValetConfig.getProperty(StaticValetConfig.MAJOR_RESULT));
			String ResultMinor = (String) propertiesResult.get(StaticValetConfig.getProperty(StaticValetConfig.MINOR_RESULT));
			LOGGER.info("Major result: " + ResultMajor + " - Minor result: " + ResultMinor);

			if (MAJOR_SEUCCESS_RESULT.equalsIgnoreCase(ResultMajor) && MINOR_SEUCCESS_RESULT.equalsIgnoreCase(ResultMinor)) {
				HashMap<String, Object> optionalOutputs = (HashMap<String, Object>) propertiesResult.get(StaticValetConfig.getProperty(StaticValetConfig.NIF_RESPONSABLE));
				nif = (String) optionalOutputs.get(NIF_RESPONSABLE);
				LOGGER.info("Nif: " + nif);
			} else {
				throw new BadCredentialsException(USER_NOT_AUTHORIZED);
			}
		} catch (Exception e) {
			throw new BadCredentialsException(USER_NOT_AUTHORIZED);
		}

		return nif;

	}

	/**
	 * Method to check if it is validated with the contingency certificate
	 * @param certificate the X.509 certificate to validate.
	 * @param certPath
	 * @param certIssuerPath
	 * @param nifProperty
	 * @return true, 
	 */
	private boolean validateCertificateUser(X509Certificate certificate, String certPath, String certIssuerPath, String nifProperty) throws CertificateException, IOException, CommonUtilsException, UsernameNotFoundException {
		Date currentDate = new Date();
		// Evaluaremos si el certificado ha expirado
		if (currentDate.after(certificate.getNotAfter()) || currentDate.before(certificate.getNotBefore())) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.PROPLOGIN001, new Object[ ] { certificate.getNotAfter().toString() }));
			throw new UsernameNotFoundException(USER_EXPIRED);
		}

		// Si las 3 propiedades existen y contienen valores continuamos con el
		// proceso
		if (certPath != null && !certPath.isEmpty() && certIssuerPath != null && !certIssuerPath.isEmpty() && nifProperty != null && !nifProperty.isEmpty()) {
			FileInputStream fis = new FileInputStream(certPath);
			CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
			X509Certificate propertyCertificate = (X509Certificate) certificateFactory.generateCertificate(fis);
			fis.close();

			// Evaluaremos que el certificado seleccionado en el miniapplet es
			// el mismo que esta alojado en el path de la propiedad
			if (checkIssuerAndSerialNumber(certificate, propertyCertificate)) {
				fis = new FileInputStream(certIssuerPath);
				X509Certificate propertyCertificateIssuer = (X509Certificate) certificateFactory.generateCertificate(fis);
				fis.close();

				// Evaluaremos que el certificado seleccionado en el miniapplet
				// forma parte del emisor seleccionado en la propiedad
				// establecida
				if (UtilsCertificate.isIssuer(propertyCertificateIssuer, propertyCertificate)) {
					return nifProperty.equals(getDniFromCertificate(certificate));
				} else {
					throw new UsernameNotFoundException(USER_INCORRECT);
				}
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param certificate
	 * @return
	 */
	private Object getDniFromCertificate(X509Certificate certificate) {
		String subjectCN = certificate.getSubjectX500Principal().getName();
		String[ ] parts = subjectCN.split(",");
		for (String part: parts) {
			if (part.trim().startsWith("CN=")) {
				int lastDashIndex = part.lastIndexOf("-");
				if (lastDashIndex != -1) {
					return part.substring(lastDashIndex + 1).trim();
				}
				break;
			}
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.springframework.security.authentication.AuthenticationProvider#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	/** The password encoder */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	private Boolean checkIssuerAndSerialNumber(X509Certificate firstCertificate, X509Certificate secondCertificate) {
		Boolean areEqual = false;

		if (firstCertificate.getIssuerX500Principal().equals(secondCertificate.getIssuerX500Principal()) && firstCertificate.getSerialNumber().equals(secondCertificate.getSerialNumber())) {
			areEqual = true;
		}

		return areEqual;
	}

	/**
	 * Method that encrypts the passwords
	 * @param password password
	 * @return encrypt the password
	 * @throws NoSuchAlgorithmException error
	 */
	private static String getPasswordHashed(String password) throws NoSuchAlgorithmException {
		byte[ ] passwordHash = MessageDigest.getInstance("SHA1").digest(password.getBytes());
		return new String(Base64.getEncoder().encode(passwordHash));
	}
}