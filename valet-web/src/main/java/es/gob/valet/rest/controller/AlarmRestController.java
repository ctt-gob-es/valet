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
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>2 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 2 oct. 2018.
 */
package es.gob.valet.rest.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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

import es.gob.valet.form.AlarmForm;
import es.gob.valet.persistence.configuration.model.entity.Alarm;
import es.gob.valet.persistence.configuration.model.entity.Mail;
import es.gob.valet.persistence.configuration.services.ifaces.IAlarmService;
import es.gob.valet.persistence.configuration.services.ifaces.IMailService;
import es.gob.valet.rest.exception.OrderedValidation;

/** 
 * <p>Class that manages the REST requests related to the Alarms administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 2 oct. 2018.
 */
@RestController
public class AlarmRestController {

	/**
	 * Attribute that represents the service object for accessing the
	 * AlarmRespository.
	 */
	@Autowired
	private IAlarmService alarmService;

	@Autowired
	private IMailService mailService;

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
	public DataTablesOutput<Alarm> alarms(@Valid DataTablesInput input) {
		return (DataTablesOutput<Alarm>) alarmService.getAllAlarm(input);
	}

	/**
	* Method that maps the save user web request to the controller and saves
	it
	* in the persistence.
	*
	* @param alarmForm
	* Object that represents the backing alarm form.
	* @param bindingResult
	* Object that represents the form validation result.
	* @return {@link DataTablesOutput<Alarmt>}
	*/
	@RequestMapping(value = "/savealarm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(DataTablesOutput.View.class)
	public @ResponseBody DataTablesOutput<Alarm> save(@Validated(OrderedValidation.class) @RequestBody AlarmForm alarmForm, BindingResult bindingResult) {
		DataTablesOutput<Alarm> dtOutput = new DataTablesOutput<>();
		Alarm alarm = null;
		List<Alarm> listNewAlarm = new ArrayList<Alarm>();

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
				alarm.setActive(alarmForm.getActive());
				Set<Mail> mails = mailService.splitMails(alarmForm.getMailsConcat());
				alarm.setMails(mails);

				Alarm alarmNew = alarmService.saveAlarm(alarm);

				for (Mail m: mails) {
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
	 * TODO
	 * 
	 * @param idAlarm
	 * @return
	 */
	@RequestMapping(path = "/emails", method = RequestMethod.GET)
	public List<Long> emails(@RequestParam("id") String idAlarm) {
		List<Long> result = new ArrayList<Long>();
		Set<Mail> mails = new HashSet<Mail>();

		Alarm alarmMonitoriza = alarmService.getAlarmById(idAlarm);
		mails = alarmMonitoriza.getMails();

		for (Mail mm: mails) {
			result.add(mm.getIdMail());
		}

		return result;
	}

}
