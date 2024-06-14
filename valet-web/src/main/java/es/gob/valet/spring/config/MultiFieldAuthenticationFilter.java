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
 * <b>File:</b><p>es.gob.afirma.spring.config.MultiFieldAuthenticationFilter.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * <b>Date:</b><p>03/04/2020.</p>
 * @author Gobierno de España.
 * @version 1.2, 29/06/2023.
 */
package es.gob.valet.spring.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.gob.valet.commons.utils.UtilsStringChar;

/** 
 * <p>Class .</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * @version 1.2, 29/06/2023.
 */
public class MultiFieldAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	/**
	 * Attribute that represents the constant param of signature in base 64. 
	 */
	public static final String SPRING_SIGBASE64 = "signatureBase64";
	/**
	 * Attribute that represents the constant param of signature in base 64. 
	 */
	public static final String RAMDON_STRING = "randomString";
	
	/**
	 * Attribute that represents the param of signature in base 64. . 
	 */
	private String sigBase64Param = SPRING_SIGBASE64;
	
    /**
     * {@inheritDoc}
     * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
			final HttpServletResponse response) throws AuthenticationException {

    	HttpSession session = request.getSession();
    	
    	Authentication auth = null;
    	
		String username = obtainUsername(request);
		
		if (username == null) {
			username = UtilsStringChar.EMPTY_STRING;
		}

		username = username.trim();
		session.setAttribute("username", username);	
		
		String signatureBase64 = obtainSignatureBase64(request);
		
		if (signatureBase64 == null) {
			signatureBase64 = "";
		}

		if(signatureBase64!=null && !signatureBase64.isEmpty()){
			//tipo CERTIFICATE
			session.setAttribute("typeLogin", "C");	
		}
		else{
			//tipo PASSWORD
			session.setAttribute("typeLogin", "P");	
		}
		
		
		UsernamePasswordAuthenticationToken authRequest = null;
		try {
			MultiFieldLoginUserDetails customUser = new MultiFieldLoginUserDetails(username, signatureBase64);
			authRequest = new UsernamePasswordAuthenticationToken(customUser, request.getParameter(RAMDON_STRING), AuthorityUtils.createAuthorityList("USER"));
			// Allow subclasses to set the "details" property
			setDetails(request, authRequest);
			auth = this.getAuthenticationManager().authenticate(authRequest);
		} catch (IllegalArgumentException iae) {
			auth = null;
		}
		return auth;
	}
    
    /**
     * Get sigBase64Param.
     * @return sigBase64Param
     */
    public String getSigBase64Param() {
    	return sigBase64Param;
    }
    
    /**
     * Set sigBase64Param.
     * @param sigBase64ParamP set sigBase64Param
     */
    public void setSigBase64Param(final String sigBase64ParamP) {
		this.sigBase64Param = sigBase64ParamP;
	}
    
    /**
     * Method that obtains de signature in base 64.
     * @param request HttpServletRequest
     * @return signature
     */
    private String obtainSignatureBase64(final HttpServletRequest request) {
		return request.getParameter(SPRING_SIGBASE64);
    }
}
