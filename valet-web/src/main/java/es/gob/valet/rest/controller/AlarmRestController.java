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
 * <b>File:</b><p>es.gob.valet.rest.controller.AlarmRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST requests related to the Alarms administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.5, 03/04/2023.
 */
package es.gob.valet.rest.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.constraints.NotEmpty;

import org.json.JSONObject;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.form.AlarmForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.model.entity.Alarm;
import es.gob.valet.persistence.configuration.model.entity.Mail;
import es.gob.valet.persistence.configuration.services.ifaces.IAlarmService;
import es.gob.valet.persistence.configuration.services.ifaces.IMailService;
import es.gob.valet.rest.exception.OrderedValidation;

/**
 * <p>Class that manages the REST requests related to the Alarms administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.5, 03/04/2023.
 */
@RestController
public class AlarmRestController {

	/**
	 * Method that maps the list alarms web requests to the controller and
	 * forwards the list of alarms to the view.
	 *
	 * @param input
	 *            Holder object for datatable attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/alarmsdatatable", method = RequestMethod.GET)
	public DataTablesOutput<Alarm> alarms(@NotEmpty DataTablesInput input) {

		DataTablesOutput<Alarm> result = new DataTablesOutput<Alarm>();

		IAlarmService alarmService = ManagerPersistenceConfigurationServices.getInstance().getAlarmService();
		result = (DataTablesOutput<Alarm>) alarmService.getAllAlarm(input);

		// Cambiamos los valores de los tokens de la descripción.
		List<Alarm> alarmListToShow = result.getData();
		if (alarmListToShow != null) {
			for (Alarm alarm: alarmListToShow) {
				alarm.setDescription(Language.getResPersistenceConstants(alarm.getDescription()));
			}
		}

		return result;

	}

	/**
	 * Method that maps the save user web request to the controller and saves it
	 * in the persistence.
	 * @param alarmForm Object that represents the backing alarm form.
	 * @param bindingResult Object that represents the form validation result.
	 * @return {@link DataTablesOutput<Alarmt>}
	 */
	@RequestMapping(value = "/savealarm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(DataTablesOutput.View.class)
	public @ResponseBody DataTablesOutput<Alarm> save(@Validated(OrderedValidation.class) @RequestBody AlarmForm alarmForm, BindingResult bindingResult) {
		DataTablesOutput<Alarm> dtOutput = new DataTablesOutput<>();
		Alarm alarm = null;
		List<Alarm> listNewAlarm = new ArrayList<Alarm>();
		IAlarmService alarmService = ManagerPersistenceConfigurationServices.getInstance().getAlarmService();
		if (bindingResult.hasErrors()) {
			listNewAlarm = StreamSupport.stream(alarmService.getAllAlarm().spliterator(), false).collect(Collectors.toList());
			JSONObject json = new JSONObject();
			for (FieldError o: bindingResult.getFieldErrors()) {
				json.put(o.getField() + "_span", o.getDefaultMessage());
			}
			dtOutput.setError(json.toString());
		} else {
			try {
				alarm = alarmService.getAlarmById(alarmForm.getIdAlarm());
				alarm.setIdAlarm(alarmForm.getIdAlarm());
				alarm.setTimeBlock(alarmForm.getTimeBlock());
				boolean active = alarmForm.getActive() == null? Boolean.FALSE : Boolean.TRUE;
				alarm.setActive(active);
				List<Mail> mails = splitMails(alarmForm.getMailsConcat());
				alarm.setMails(mails);

				Alarm alarmNew = alarmService.saveAlarm(alarm);

				for (Mail m: mails) {
					IMailService mailService = ManagerPersistenceConfigurationServices.getInstance().getMailService();
					mailService.saveMail(m);
				}

				listNewAlarm.add(alarmNew);
			} catch (Exception e) {
				listNewAlarm = StreamSupport.stream(alarmService.getAllAlarm().spliterator(), false).collect(Collectors.toList());
				throw e;
			}
		}

		dtOutput.setData(listNewAlarm);

		return dtOutput;
	}

	/**
	 * Create a Set of e-mails from a String.
	 * @param concatString String of e-mails.
	 * @return  {@link} Set<Mail>.
	 */
	private List<Mail> splitMails(String concatString) {

		List<Mail> result = new ArrayList<Mail>();
		String[ ] aux = concatString.split(UtilsStringChar.SYMBOL_AMPERSAND_STRING);
		IMailService mailService = ManagerPersistenceConfigurationServices.getInstance().getMailService();
		for (int i = 0; i < aux.length; i++) {
			result.add(mailService.getMailById(Long.parseLong(aux[i], NumberConstants.NUM10), false));
		}

		return result;

	}

	/**
	 * Method that get all emails of system.
	 * @param idAlarm Parameter that represents a Alarm identifier.
	 * @return {@link List<Long>}
	 */
	@RequestMapping(path = "/emails", method = RequestMethod.GET)
	public List<Long> emails(@RequestParam("id") String idAlarm) {
		List<Long> result = new ArrayList<Long>();
		IAlarmService alarmService = ManagerPersistenceConfigurationServices.getInstance().getAlarmService();
		Alarm alarmMonitoriza = alarmService.getAlarmById(idAlarm);
		List<Mail> mails = alarmMonitoriza.getMails();
		if (mails != null) {
			for (Mail mm: mails) {
				result.add(mm.getIdMail());
			}
		}

		return result;

	}

}
