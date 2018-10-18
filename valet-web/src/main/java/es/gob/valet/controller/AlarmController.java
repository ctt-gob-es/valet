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
 * <b>File:</b><p>es.gob.valet.controller.AlarmController.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>2 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 2 oct. 2018.
 */
package es.gob.valet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.gob.valet.form.AlarmForm;
import es.gob.valet.persistence.configuration.model.entity.Alarm;
import es.gob.valet.persistence.configuration.model.entity.Mail;
import es.gob.valet.persistence.configuration.services.ifaces.IAlarmService;
import es.gob.valet.persistence.configuration.services.ifaces.IMailService;

/** 
 * <p>Class that manages the requests related to the Alarm administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 2 oct. 2018.
 */
@Controller
public class AlarmController {

	/**
	 * Attribute that represents the service object for acceding to AlarmRepository.
	 */
	@Autowired
	private IAlarmService alarmService;

	/**
	 * Attribute that represents the service object for acceding to MailRepository.
	 */
	@Autowired
	private IMailService mailService;

	/**
	 * Method that maps the add Alarm web request to the controller and sets the
	 * backing form.
	 * @param model Holder object for model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "alarmadmin")
	public String alarmAdmin(Model model) {
		List<Alarm> alarms = new ArrayList<Alarm>();
		List<Mail> mails = new ArrayList<Mail>();
		AlarmForm alarmForm = new AlarmForm();

		alarms = StreamSupport.stream(alarmService.getAllAlarm().spliterator(), false).collect(Collectors.toList());
		mails = StreamSupport.stream(mailService.getAllMail().spliterator(), false).collect(Collectors.toList());

		model.addAttribute("alarms", alarms);
		model.addAttribute("mailsAlarm", mails);
		model.addAttribute("alarmForm", alarmForm);

		return "fragments/alarmadmin.html";
	}

}
