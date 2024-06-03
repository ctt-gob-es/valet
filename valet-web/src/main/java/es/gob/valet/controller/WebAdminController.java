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
 * <b>File:</b><p>es.gob.valet.controller.WebAdminController.java.</p>
 * <b>Description:</b><p>Class that manages the requests related to the home page from vaLET.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>15/06/2018.</p>
 * @author Gobierno de España.
 * @version  @version 1.1, 06/11/2018.
 */
package es.gob.valet.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.Task;
import es.gob.valet.persistence.configuration.model.entity.UserValet;
import es.gob.valet.persistence.configuration.services.ifaces.IKeystoreService;
import es.gob.valet.persistence.configuration.services.ifaces.ITaskService;
import es.gob.valet.persistence.configuration.services.ifaces.IUserValetService;

/** 
* <p>Class that manages the requests related to the home page from vaLET.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 06/11/2018.
 */
@Controller
public class WebAdminController {
	/**
	 * Attribute that represents the service object for accessing the repository. 
	 */
	@Autowired
	private IKeystoreService keystoreService;
	
	/**
	 * Attribute that represents the service object for accessing the repository. 
	 */
	@Autowired
	private ITaskService taskService;
	
	/**
	 * Attribute that represents the service object for accessing the repository. 
	 */
	@Autowired
	private IUserValetService userValetService;
	
	private static boolean lastAccessMessageShowed = false;
	
	/**
	 *method that maps the list of keystores and sends it to the view "inicio.html".
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value="inicio", method = { RequestMethod.GET, RequestMethod.POST })
	public String index(Model model, final HttpServletRequest request,  @ModelAttribute("nifUser") String nifUser){
		String nif = "";
		if (nifUser != null && !nifUser.equals("")) {
			nif = nifUser;
	    } else {
	    	nif = request.getRemoteUser();
	    }
		UserValet userValet = userValetService.getUserValetByNif(nif);
		Date lastAccess = userValet.getLastAccess();
		String lastAccessFormated = null;
		String lastUserAccessMessage = null;
		if (lastAccess != null) {
			lastAccessFormated = UtilsDate.toString(UtilsDate.FORMAT_DATE_TIME_STANDARD, lastAccess);
			lastUserAccessMessage = Language.getFormatResWebGeneral(WebGeneralMessages.LAST_USER_ACCESS_MESSAGE, userValet.getNif(), lastAccessFormated.substring(11) , lastAccessFormated.substring(0, 10));
		}
		// Actualizamos la fecha de ultimo acceso
		userValet.setLastAccess(new Date());
		userValetService.saveUserValet(userValet);
		List<Keystore> listKeystores = keystoreService.getAllKeystore();
		List<Task> listTask = taskService.getAllTask();
		for(Task task: listTask){
			task.setTokenName(Language.getResPersistenceConstants(task.getTokenName()));
		}
		model.addAttribute("lastAccessMessageShowed", lastAccessMessageShowed);
		lastAccessMessageShowed = true;
		model.addAttribute("userLastAccess", lastUserAccessMessage);
		model.addAttribute("listtask", listTask);
		model.addAttribute("listkeystore", listKeystores);
		return "inicio.html";
	}

	/**
	 * Method that maps the invalid session request.
	* @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "invalidSession")
	public String invalid(Model model) {
		return "invalidSession.html";
	}
	
	public static void setLastAccessMessageShowed(boolean lastAccessMsgShowed) {
		lastAccessMessageShowed = lastAccessMsgShowed;
	}

}
