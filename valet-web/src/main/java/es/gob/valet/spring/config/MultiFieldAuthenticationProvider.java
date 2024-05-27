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
 * @version 1.6, 28/11/2023.
 */
package es.gob.valet.spring.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

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
		String password = customUser.getPassword();
		String signatureBase64 = customUser.getSignatureBase64();

		byte[] signBase64Bytes = null;
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
					
					// Importamos el certificado establecido en la ruta de la
					// propiedad "login.access.certificate"
					String certPath = StaticValetConfig.getProperty(StaticValetConfig.LOGIN_ACCESS_CERTIFICATE);

					// Comprobar que el emisor corresponde con el
					// establecido en la ruta de la propiedad
					// "login.access.certificate.issuer"
					String certIssuerPath = StaticValetConfig.getProperty(StaticValetConfig.LOGIN_ACCESS_CERTIFICATE_ISSUER);
					
					// Comprobar que el emisor corresponde con el
					// establecido en la ruta de la propiedad
					// "login.access.certificate.dni"
					String nifProperty = StaticValetConfig.getProperty(StaticValetConfig.LOGIN_ACCESS_CERTIFICATE_DNI);
					
					if (certPath != null && !certPath.isEmpty() && certIssuerPath != null && !certIssuerPath.isEmpty()&& nifProperty != null && !nifProperty.isEmpty()) {
						// Comprobamos su periodo de validez
						Date startDate = certificate.getNotBefore();
						Date endDate = certificate.getNotAfter();

						// Obtenemos la fecha actual
						Date currentDate = new Date();

						// Comprobamos que no esté caducado o que no sea válido aún
						if (currentDate.after(endDate)) {
							userExpired = true;
							// El certificado pasado por el usuario está caducado
							LOGGER.error(Language.getFormatResWebGeneral(
									WebGeneralMessages.PROPLOGIN001, new Object[] { endDate.toString() }));
							throw new CertificateExpiredException();
						} else if (currentDate.before(startDate)) {
							userExpired = true;
							// El certificado pasado por el usuario no es válido aún
							LOGGER.error(Language.getFormatResWebGeneral(
									WebGeneralMessages.PROPLOGIN002, new Object[] { startDate.toString() }));
							throw new CertificateNotYetValidException();
						}
						
						if (certPath.endsWith(".cer") || certPath.endsWith(".crt")) {
							FileInputStream fis = new FileInputStream(certPath);
							CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
							X509Certificate propertyCertificate = (X509Certificate) certificateFactory
									.generateCertificate(fis);
							fis.close();

							// Comprobar Issuer y Serial Number
							if (!checkIssuerAndSerialNumber(certificate, propertyCertificate)) {
								// TODO: Paso 4 -> Validación contra Servicios REST
							} else {
								if (certIssuerPath.endsWith(".cer") || certIssuerPath.endsWith(".crt")) {
									fis = new FileInputStream(certIssuerPath);
									X509Certificate propertyCertificateIssuer = (X509Certificate) certificateFactory
											.generateCertificate(fis);
									fis.close();

									// Comprobar que sea el emisor
									if (UtilsCertificate.isIssuer(propertyCertificateIssuer, propertyCertificate)) {
										String nif = null;
										
										if (certificate.getSubjectX500Principal().getName() != null ) {
											String subjectCN = certificate.getSubjectX500Principal().getName();
											String[] parts = subjectCN.split(",");
											
											for (String part : parts) {
										        if (part.trim().startsWith("CN=")) {
										        	int lastDashIndex = part.lastIndexOf("-");
										        	if (lastDashIndex != -1) {
										        		nif = part.substring(lastDashIndex + 1).trim();
										        	}
										            break;
										        }
										    }
										}
											
									    
									    if (nifProperty.equals(nif)) {
									    	LOGGER.info(Language.getFormatResWebGeneral(
									    			WebGeneralMessages.PROPLOGIN003));
									    	
									    	curruser = userRepository.findByNif(nif);
									    }
									}
								}
							}
						}
					}
					
				} else {
					auth = null;
				}

			} catch (CertificateException | IOException e) {
				auth = null;
			} catch (CommonUtilsException e) {
				auth = null;
			}

		} else {
			if (name != null && password != null) {
				curruser = userRepository.findByLogin(name);
			} else {
				auth = null;
			}
		}

		if (curruser != null) {
			if (curruser.getIsBlocked()) {
				throw new BadCredentialsException(USER_BLOCKED);
			} else {
				String hashedPassword = null;
				try {
					hashedPassword = getPasswordHashed(password);
				} catch (NoSuchAlgorithmException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				// If password is OK passwordEncoder().matches(password,
				// curruser.getPassword()
				if (!StringUtils.isEmpty(signatureBase64) || passwordEncoder().matches(password, curruser.getPassword())) {

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

		if (firstCertificate.getIssuerX500Principal().equals(secondCertificate.getIssuerX500Principal())
				&& firstCertificate.getSerialNumber().equals(secondCertificate.getSerialNumber())) {
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
        byte[] passwordHash = MessageDigest.getInstance("SHA1").digest(password.getBytes());
        return new String(Base64.getEncoder().encode(passwordHash));
    }
}