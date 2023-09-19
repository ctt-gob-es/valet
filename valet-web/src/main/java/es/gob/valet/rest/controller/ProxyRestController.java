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
 * <b>File:</b><p>es.gob.valet.rest.controller.ProxyRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST request related to the proxy configuration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>16/08/2018.</p>
 * @author Gobierno de España.
 * @version 1.6, 19/09/2023.
 */
package es.gob.valet.rest.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.form.ProxyForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.WebGeneralMessages;
import es.gob.valet.persistence.configuration.model.entity.COperationMode;
import es.gob.valet.persistence.configuration.model.entity.Proxy;
import es.gob.valet.persistence.configuration.services.ifaces.ICOperationModeService;
import es.gob.valet.persistence.configuration.services.ifaces.IProxyService;
import es.gob.valet.persistence.utils.UtilsAESCipher;
import es.gob.valet.utils.UtilsProxy;

/**
 * <p>Class that manages the REST request related to the proxy configuration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.6, 19/09/2023.
 */
@RestController
public class ProxyRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ProxyRestController.class);

	/**
	 * Attribute that represents the service object for acceding to ProxyRespository.
	 */
	@Autowired
	private IProxyService proxyService;
	/**
	 * Attribute that represents the service object for acceding to COperationModeRespository.
	 */
	@Autowired
	private ICOperationModeService operationModeService;

	/**
	 * Method to update the task.
	 * @param proxyForm Parameter that represents the backing form for editing the proxy.
	 * @return Modified task.
	 */
	@RequestMapping(value = "/saveproxy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ProxyForm saveProxy(@RequestBody ProxyForm proxyForm) {

		ProxyForm proxyFormUpdated = proxyForm;
		LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.INFO_PROXY_INIT));

		try {
			Proxy proxy = proxyService.getProxyById(proxyForm.getIdProxy());
			COperationMode operationMode = operationModeService.getOperationModeById(proxyForm.getIdOperationMode());
			proxy.setOperationMode(operationMode);

			if (!UtilsStringChar.isNullOrEmpty(proxyForm.getHost())) {
				proxy.setHostProxy(proxyForm.getHost());
			}

			proxy.setPortProxy(proxyForm.getPort());

			if (!UtilsStringChar.isNullOrEmpty(proxyForm.getUser())) {
				proxy.setUserProxy(proxyForm.getUser());
			}

			if (!UtilsStringChar.isNullOrEmpty(proxyForm.getPassword())) {
				String pwd = proxyForm.getPassword();
				if (pwd == null) {
					proxy.setPasswordProxy(null);
				} else {
					proxy.setPasswordProxy(new String(UtilsAESCipher.getInstance().encryptMessage(pwd)));
				}
			}
			if (!UtilsStringChar.isNullOrEmpty(proxyForm.getUserDomain())) {
				proxy.setUserDomain(proxyForm.getUserDomain());
			}
			if (!UtilsStringChar.isNullOrEmpty(proxyForm.getAddressList())) {
				proxy.setAddressList(proxyForm.getAddressList());
			}

			proxy.setIsLocalAddress(proxyForm.getIsLocalAddress());

			// Se guarda en la base de datos.
			proxyService.saveProxy(proxy);
			// Se actualiza la configuración de las utilidades del proxy.
			UtilsProxy.loadProxyConfiguration();
			LOGGER.info(Language.getResWebGeneral(WebGeneralMessages.INFO_MODIFY_PROXY_OK));
			proxyFormUpdated.setMsgOk(Language.getResWebGeneral(WebGeneralMessages.INFO_MODIFY_PROXY_OK));
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(WebGeneralMessages.ERROR_MODIFY_PROXY, new Object[ ] { e.getMessage() }));
			if (proxyForm == null) {
				proxyFormUpdated = new ProxyForm();
			}
			proxyFormUpdated.setError(Language.getResWebGeneral(WebGeneralMessages.ERROR_MODIFY_PROXY_WEB));
		}

		return proxyFormUpdated;
	}
}
