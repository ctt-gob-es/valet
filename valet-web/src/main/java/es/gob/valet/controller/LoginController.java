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
 * <b>File:</b><p>es.gob.valet.controller.LoginController.java.</p>
 * <b>Description:</b><p>Class that maps the request for the login form to the controller.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 15 jun. 2018.
 */
package es.gob.valet.controller;

import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.StaticValetConfig;

/** 
 * <p>Class that maps the request for the login form to the controller.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 15 jun. 2018.
 */
@Controller
public class LoginController {

	/**
	 * Method that map the root request for the application to the controller to the login view.
	 * 
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Model model) {
		WebAdminController.setLastAccessMessageShowed(false);
		String accessOptions = StaticValetConfig.getProperty(StaticValetConfig.ACCESS_OPTIONS);
		model.addAttribute("accessOptions", accessOptions);
		model.addAttribute("randomString", getRandomStringToLogin());
		return "login.html";
	}
	
	/**
	 * Method returning a random string
	 * @return string
	 */
	private String getRandomStringToLogin(){
		String banco = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		
		String cadena = "";
	    for (int x = 0; x < NumberConstants.NUM12; x++) {
	        int indiceAleatorio = numeroAleatorioEnRango(0, banco.length() - 1);
	        char caracterAleatorio = banco.charAt(indiceAleatorio);
	        cadena += caracterAleatorio;
	    }
	    cadena += "-" + getTimeToMillis();

	    return cadena;
		
	}
	
	/**
	 * Method that a random range
	 * @param minimo
	 * @param maximo
	 * @return number
	 */
	public static int numeroAleatorioEnRango(int minimo, int maximo) {
	    return ThreadLocalRandom.current().nextInt(minimo, maximo + 1);
	}
	
	/**
	 * Method returning the current date in milliseconds
	 * @return date
	 */
	private Long getTimeToMillis(){
	    Calendar calendar = Calendar.getInstance();
	    return calendar.getTimeInMillis();
	}
}
