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
 * <b>File:</b><p>es.gob.valet.controller.ProxyController.java.</p>
 * <b>Description:</b><p>Class that manages the requests related to the configuration of the Proxy.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>16/08/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 25/10/2018.
 */
package es.gob.valet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.gob.valet.form.ProxyForm;
import es.gob.valet.persistence.configuration.model.entity.COperationMode;
import es.gob.valet.persistence.configuration.model.entity.Proxy;
import es.gob.valet.persistence.configuration.services.ifaces.ICOperationModeService;
import es.gob.valet.persistence.configuration.services.ifaces.IProxyService;

/**
 * <p>Class that manages the requests related to the configuration of the Proxy.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 25/10/2018.
 */
@Controller
public class ProxyController {

	/**
	 * Constant that represents the parameter 'idProxy'.
	 */
	private static final Long ID_PROXY = 1L;

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
	 * Method that loads the proxy configuration.
	 *
	 * @param model Holder object form model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadproxy", method = RequestMethod.GET)
	public String loadProxy(Model model) {

		ProxyForm proxyForm = new ProxyForm();
		Proxy proxy = proxyService.getProxyById(ID_PROXY);
		proxyForm.setIdProxy(ID_PROXY);
		proxyForm.setIdOperationMode(proxy.getOperationMode().getIdCOperationMode());
		proxyForm.setHost(proxy.getHostProxy());
		proxyForm.setPort(proxy.getPortProxy());
		proxyForm.setUser(proxy.getUserProxy());
		proxyForm.setPassword(proxy.getPasswordProxy());
		proxyForm.setUserDomain(proxy.getUserDomain());
		proxyForm.setAddressList(proxy.getAddressList());
		proxyForm.setIsLocalAddress(proxy.getIsLocalAddress());

		List<COperationMode> listOperationMode = operationModeService.getAllOperationMode();
		model.addAttribute("proxyForm", proxyForm);
		model.addAttribute("listOperationMode", listOperationMode);
		return "fragments/proxyadmin.html";
	}
}
