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
 * <b>File:</b><p>es.gob.valet.spring.config.WebSecurityConfig.java.</p>
 * <b>Description:</b><p> Class that enables and configures the security of the Valet application.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>13 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.spring.config;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;

/** 
 * <p>Class that enables and configures the security of the Valet application. </p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(GeneralConstants.LOGGER_NAME_VALET_LOG);
	
	/**
	 * Attribute that represents the injected service for user authentication.
	 */
	@Autowired
	private MultiFieldAuthenticationProvider multiFieldAuthPr;
	
	/**
	 * Constant that represents the name of the cookie for session tracking. 
	 */
	public static final String SESSION_TRACKING_COOKIE_NAME = "JSESSIONID";
	
	/**
	 * Attribute that represents the injected service for success user authentication.
	 */
	@Autowired
	private SuccessHandler successHandler;

	/**
	 * Attribute that represents the injected service for failure user authentication.
	 */
	@Autowired
	private FailureHandler failureHandler;
	
	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.HttpSecurity)
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		try {
			http.authorizeRequests()
		    .antMatchers("/css/**", "/css/**", "/images/**", "/js/**", "/fonts/**", "/fonts/icons/themify/**", "/fonts/fontawesome/**", "/less/**", "/invalidSession")
		    .permitAll() // Enable css, images and js when logged out
			.and()
			.authorizeRequests()
			.antMatchers("/", "add", "delete/{id}", "edit/{id}", "save", "users")
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			.addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
			.logout().invalidateHttpSession(false).deleteCookies(SESSION_TRACKING_COOKIE_NAME).clearAuthentication(true).logoutSuccessUrl("/")
			.permitAll()
			.and()
			.httpBasic()
			.and()
			.csrf()
			.disable() // Disable CSRF
			.sessionManagement()
			.sessionFixation().migrateSession()
			.maximumSessions(1)
			.expiredUrl("/")
			.and()
			.invalidSessionUrl("/invalidSession");
			
		} catch (Exception e) {
			LOGGER.error(Language.getResWebGeneral(WebGeneralMessages.ERROR_WEB_SECURITY_001));
		}
    }
    
	/**
	 * Method that sets the authentication global configuration.
	 * @param auth Object that represents the Spring security builder.
	 * @throws Exception Object that represents the exception thrown in case of error.
	 */
	@Override
	public void configure(final AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(multiFieldAuthPr);
	}
    
	/**
	 * Filter authentication.
	 * @return MultiFieldAuthenticationFilter filter
	 * @throws Exception
	 */
	@Bean
	public MultiFieldAuthenticationFilter authenticationFilter() {
		MultiFieldAuthenticationFilter authFilter = new MultiFieldAuthenticationFilter();
		authFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/", "POST"));
		try {
			authFilter.setAuthenticationManager(authenticationManager());
		} catch (Exception e) {
			LOGGER.error("Error al autenticar usuario." + e);
		}
		authFilter.setAuthenticationSuccessHandler(successHandler);
		authFilter.setAuthenticationFailureHandler(failureHandler);
		authFilter.setUsernameParameter("username");
		authFilter.setPasswordParameter("password");
		authFilter.setSigBase64Param("signatureBase64");
		return authFilter;
	}
	
	/**
	 * Get multiFieldAuthPr.
	 * @return multiFieldAuthPr
	 */
	public MultiFieldAuthenticationProvider getMultiFieldAuthPr() {
		return multiFieldAuthPr;
	}

	/**
	 * Set multiFieldAuthPr.
	 * @param multiFieldAuthPrP set multiFieldAuthPr
	 */
	public void setMultiFieldAuthPr(final MultiFieldAuthenticationProvider multiFieldAuthPrP) {
		this.multiFieldAuthPr = multiFieldAuthPrP;
	}

	/**
	 * Get successHandler.
	 * @return successHandler
	 */
	public SuccessHandler getSuccessHandler() {
		return successHandler;
	}

	/**
	 * Set successHandler.
	 * @param successHandlerP set successHandler
	 */
	public void setSuccessHandler(final SuccessHandler successHandlerP) {
		this.successHandler = successHandlerP;
	}
}
