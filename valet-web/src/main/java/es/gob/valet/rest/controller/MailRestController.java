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
 * <b>File:</b><p>es.gob.valet.rest.controller.MailRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST requests related to the Mails administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 25/10/2018.
 */
package es.gob.valet.rest.controller;

import java.util.ArrayList;
import java.util.List;
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

import es.gob.valet.form.MailForm;
import es.gob.valet.persistence.configuration.model.entity.Mail;
import es.gob.valet.persistence.configuration.services.ifaces.IMailService;
import es.gob.valet.rest.exception.OrderedValidation;

/**
 * <p>Class that manages the REST requests related to the Mails administration and
 * JSON communication.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 25/10/2018.
 */
@RestController
public class MailRestController {

	/**
	 * Attribute that represents the service object for accessing the
	 * MailRespository.
	 */
	@Autowired
	private IMailService mailService;

	/**
	 * Method that maps the list mails web requests to the controller and
	 * forwards the list of mails to the view.
	 *
	 * @param input
	 *            Holder object for datatable attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/mailsdatatable", method = RequestMethod.GET)
	public DataTablesOutput<Mail> mails(@Valid DataTablesInput input) {
		return (DataTablesOutput<Mail>) mailService.getAllMail(input);
	}

	/**
	 * Method that maps the delete mail request from datatable to the controller
	 * and performs the delete of the mail identified by its id.
	 *
	 * @param mailId
	 *            Identifier of the mail to be deleted.
	 * @param index
	 *            Row index of the datatable.
	 * @return String that represents the name of the view to redirect.
	 */
	@JsonView(DataTablesOutput.View.class)
	@RequestMapping(path = "/deletemail", method = RequestMethod.POST)
	public String deleteMail(@RequestParam("id") Long mailId, @RequestParam("index") String index) {
		mailService.deleteMail(mailId);

		return index;
	}

	/**
	 * Method that maps the save user web request to the controller and saves it
	 * in the persistence.
	 * @param mailForm Object that represents the backing user form.
	 * @param bindingResult Object that represents the form validation result.
	 * @return {@link DataTablesOutput<UserValet>}
	 */
	@RequestMapping(value = "/savemail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(DataTablesOutput.View.class)
	public @ResponseBody DataTablesOutput<Mail> save(@Validated(OrderedValidation.class) @RequestBody MailForm mailForm, BindingResult bindingResult) {
		DataTablesOutput<Mail> dtOutput = new DataTablesOutput<>();
		Mail mail = null;
		List<Mail> listNewMail = new ArrayList<Mail>();

		if (bindingResult.hasErrors()) {
			listNewMail = StreamSupport.stream(mailService.getAllMail().spliterator(), false).collect(Collectors.toList());
			JSONObject json = new JSONObject();
			for (FieldError o: bindingResult.getFieldErrors()) {
				json.put(o.getField() + "_span", o.getDefaultMessage());
			}
			dtOutput.setError(json.toString());
		} else {
			try {
				if (mailForm.getIdMail() != null) {
					mail = mailService.getMailById(mailForm.getIdMail(), false);
				} else {
					mail = new Mail();
				}

				mail.setEmailAddress(mailForm.getEmailAddress());
				Mail mailNew = mailService.saveMail(mail);

				listNewMail.add(mailNew);
			} catch (Exception e) {
				listNewMail = StreamSupport.stream(mailService.getAllMail().spliterator(), false).collect(Collectors.toList());
				throw e;
			}
		}

		dtOutput.setData(listNewMail);

		return dtOutput;

	}

	/**
	 * Method that maps the save user web request to the controller and saves it
	 * in the persistence.
	 * @param mailForm Object that represents the backing user form.
	 * @param bindingResult Object that represents the form validation result.
	 * @return {@link DataTablesOutput<UserValet>}
	 */
	@RequestMapping(value = "/savemailedit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@JsonView(DataTablesOutput.View.class)
	public @ResponseBody DataTablesOutput<Mail> saveEdit(@Validated(OrderedValidation.class) @RequestBody MailForm mailForm, BindingResult bindingResult) {
		DataTablesOutput<Mail> dtOutput = new DataTablesOutput<>();
		Mail mail = null;
		List<Mail> listNewMail = new ArrayList<Mail>();

		if (bindingResult.hasErrors()) {
			listNewMail = StreamSupport.stream(mailService.getAllMail().spliterator(), false).collect(Collectors.toList());
			JSONObject json = new JSONObject();
			for (FieldError o: bindingResult.getFieldErrors()) {
				json.put(o.getField() + "_span", o.getDefaultMessage());
			}
			dtOutput.setError(json.toString());
		} else {
			try {
				if (mailForm.getIdMail() != null) {
					mail = mailService.getMailById(mailForm.getIdMail(), false);
				} else {
					mail = new Mail();
				}

				mail.setEmailAddress(mailForm.getEmailAddress());

				Mail mailNew = mailService.saveMail(mail);

				listNewMail.add(mailNew);
			} catch (Exception e) {
				listNewMail = StreamSupport.stream(mailService.getAllMail().spliterator(), false).collect(Collectors.toList());
				throw e;
			}
		}

		dtOutput.setData(listNewMail);

		return dtOutput;

	}

}
