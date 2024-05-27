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
 * <b>File:</b><p>es.gob.afirma.spring.config.FailureHandler.java.</p>
 * <b>Description:</b><p> .</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI
 * certificates and electronic signature.</p>
 * <b>Date:</b><p>03/04/2020.</p>
 * @author Gobierno de España.
 * @version 1.3, 29/06/2023.
 */
package es.gob.valet.spring.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * <p>Class .</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI
 * certificates and electronic signature</p>
 * @version 1.3, 29/06/2023.
 */
@Component
public class FailureHandler implements AuthenticationFailureHandler {

	/**
	 * Attribute that represents the incorrect password.
	 */
	private static final String USER_PASS_INCORRECT = "Password incorrecto";
	
	/**
	 * Attribute that represents the user will be blocked.
	 */
	private static final String USER_WILL_BE_BLOCKED = "Intentos superado";
	
	/**
	 * Attribute that represents the blocked user.
	 */
	private static final String USER_BLOCKED = "Usuario bloqueado";

	
	/**
	 * {@inheritDoc}
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response, final AuthenticationException exception) throws IOException, ServletException {
		if (exception != null) {
			String[] error = exception.getMessage().split(",");
			if(error[0].equals(USER_BLOCKED)){
				response.sendRedirect("?userIsBlocked");
			}else if(error[0].equals(USER_PASS_INCORRECT)){
				response.sendRedirect("?loginInvalidUserPassword&attemps="+error[1]);
			}else if(error[0].equals(USER_WILL_BE_BLOCKED)){
				response.sendRedirect("?userWillBeBlocked");
			} else {
				// Redirigimos para indicar usuario invalido.
				response.sendRedirect("?loginInvalidUser");
			}
		}
	}
}
