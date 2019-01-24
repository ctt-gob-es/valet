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
 * <b>Description:</b><p>Class that manages the requests related to the Alarm administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 24/01/2019.
 */
package es.gob.valet.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import es.gob.valet.form.AlarmForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.model.entity.Alarm;
import es.gob.valet.persistence.configuration.model.entity.Mail;
import es.gob.valet.persistence.configuration.services.ifaces.IAlarmService;
import es.gob.valet.persistence.configuration.services.ifaces.IMailService;

/**
 * <p>Class that manages the requests related to the Alarm administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 24/01/2019.
 */
@Controller
public class AlarmController {

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
		IAlarmService alarmService = ManagerPersistenceConfigurationServices.getInstance().getAlarmService();
		IMailService mailService = ManagerPersistenceConfigurationServices.getInstance().getMailService();
		alarms = StreamSupport.stream(alarmService.getAllAlarm().spliterator(), false).collect(Collectors.toList());

		// Cambiamos los valores de los tokens de la descripción.
		if (alarms != null) {
			for (Alarm alarm: alarms) {
				alarm.setDescription(Language.getResPersistenceConstants(alarm.getDescription()));
			}
		}

		mails = StreamSupport.stream(mailService.getAllMail().spliterator(), false).collect(Collectors.toList());

		model.addAttribute("alarms", alarms);
		model.addAttribute("mailsAlarm", mails);
		model.addAttribute("alarmForm", alarmForm);

		return "fragments/alarmadmin.html";
	}

}
