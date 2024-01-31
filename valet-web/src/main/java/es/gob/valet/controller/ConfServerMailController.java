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
 * <b>File:</b><p>es.gob.valet.controller.ConfServerMailController.java.</p>
 * <b>Description:</b><p>Class that manages the requests related to the ConfServerMail administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.7, 30/01/2024.
 */
package es.gob.valet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.form.ConfServerMailForm;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.model.entity.ConfServerMail;
import es.gob.valet.persistence.configuration.services.ifaces.IConfServerMailService;

/**
 * <p>Class that manages the requests related to the ConfServerMail administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.7, 30/01/2024.
 */
@Controller
public class ConfServerMailController {

	/**
	 * Constant that represents the default text string of the password displayed in edit mode.
	 */
	private static final String PASSWORD_EDIT = "********";

	/**
	 * Method that maps the add ConfServerMail web request to the controller and sets the
	 * backing form.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "confmailadmin")
	public String confServerMailAdmin(Model model) {

		ConfServerMailForm confServerMailForm = new ConfServerMailForm();
		IConfServerMailService confServerMailService = ManagerPersistenceConfigurationServices.getInstance().getConfServerMailService();
		// TODO Al obtener el listado de TODOS los servidores de correo se debe
		// obtener una lista,
		// no una única instancia, y en consecuencia, mostrar ese listado en la
		// administración.
		ConfServerMail confServerMail = confServerMailService.getAllConfServerMail();
		if (confServerMail == null) {
			confServerMail = new ConfServerMail();
			confServerMail.setUseAuthenticationMail(Boolean.FALSE);
		}

		confServerMailForm.setIdConfServerMail(confServerMail.getIdConfServerMail());
		confServerMailForm.setIssuerMail(confServerMail.getIssuerMail());
		confServerMailForm.setHostMail(confServerMail.getHostMail());
		confServerMailForm.setPortMail(confServerMail.getPortMail());
		confServerMailForm.setConnectionTimeout(confServerMail.getConnectionTimeout());
		confServerMailForm.setReadingTimeout(confServerMail.getReadingTimeout());
		confServerMailForm.setTlsEnabled(confServerMail.getTlsEnabled());
		if(null != confServerMail.getCertificateFile()) {
			confServerMailForm.setCertificateFile(confServerMail.getCertificateFile());
			confServerMailForm.setOriginalNameFile(confServerMail.getOriginalNameFile());
		}
		
		boolean authentication = Boolean.FALSE;
		if (confServerMail.getUseAuthenticationMail() != null) {
			authentication = confServerMail.getUseAuthenticationMail();

		}

		confServerMailForm.setUseAuthenticationMail(authentication);
		if (authentication) {
			confServerMailForm.setUserMail(confServerMail.getUserMail());
			if (!UtilsStringChar.isNullOrEmpty(confServerMail.getPasswordMail())) {
				confServerMailForm.setNewPassword(Boolean.TRUE);
				confServerMailForm.setPasswordMail(PASSWORD_EDIT);
			}
		} else {
			confServerMailForm.setUserMail(null);
			confServerMailForm.setPasswordMail(null);
		}

		model.addAttribute("noAuthentication", !authentication);
		model.addAttribute("confServerMailForm", confServerMailForm);

		return "fragments/confmailadmin.html";
	}

}
