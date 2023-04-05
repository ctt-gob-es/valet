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
 * <b>File:</b><p>es.gob.valet.rest.controller.TaskRestController.java.</p>
 * <b>Description:</b><p>Class that manages the REST request related to the Task's administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.8, 03/04/2023.
 */
package es.gob.valet.rest.controller;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.form.TaskForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.model.entity.Planner;
import es.gob.valet.persistence.configuration.model.entity.Task;
import es.gob.valet.persistence.configuration.services.ifaces.IPlannerService;
import es.gob.valet.persistence.configuration.services.ifaces.ITaskService;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.tasks.TasksManager;

/**
 * <p>Class that manages the REST request related to the Task's administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.8, 03/04/2023.
 */
@RestController
public class TaskRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(TaskRestController.class);

	/**
	 * Constant that represents the format date.
	 */
	private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Method to update the task.
	 * @param taskForm Parameter that represents the backing form for editing a Task
	 * @return Modified task.
	 */
	@RequestMapping(value = "/updatetask", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TaskForm updateTask(@RequestBody TaskForm taskForm) {

	

		try {

			// validamos las fechas
			if (!validateInitDate(taskForm)) {
				taskForm.setError(Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATE_DATE));
			} else {
				// se obtiene el planificador
				IPlannerService plannerService = ManagerPersistenceConfigurationServices.getInstance().getPlannerService();
				Planner planner = plannerService.getPlannerById(taskForm.getIdPlanner());

				// se obtiene el tipo de planificador seleccionado
				Long idCPlannerType = taskForm.getIdPlannerType();

				// se actualizan los campos horas, minutos y segundos si el
				// planificador es tipo periodico.
				if (idCPlannerType.equals(GeneralConstants.PLANNING_TYPE_PERIODIC)) {
					// se actualizan los campos horas, minutos y segundos
					planner.setHourPeriod(taskForm.getHourPeriod());
					planner.setMinutePeriod(taskForm.getMinutePeriod());
					planner.setSecondPeriod(taskForm.getSecondPeriod());
				}
				// se actualiza la fecha inicial por si se ha modificado.
				Date initDay = UtilsDate.transformDate(taskForm.getInitDayString(), UtilsDate.FORMAT_DATE_TIME_STANDARD);

				planner.setInitDay(initDay);

				// se obtiene la clase que implementa la tarea
				ITaskService taskService = ManagerPersistenceConfigurationServices.getInstance().getTaskService();
				Task task = taskService.getTaskById(taskForm.getIdTask(), false);

				// se actualiza la tarea indicando si está habilitada o no.
				task.setIsEnabled(taskForm.getIsEnabled());

				// persistimos los cambios del planificador y d la tarea.
				Task updatedTask = taskService.saveTask(task);
				plannerService.savePlanner(planner);

				TasksManager.addOrUpdateTask(updatedTask);
				String taskName = Language.getResPersistenceConstants(task.getTokenName());
				String infoMsg = Language.getFormatResWebGeneral(IWebGeneralMessages.INFO_UPDATE_TASK_OK, new Object[ ] { taskName });
				LOGGER.info(infoMsg);
				taskForm.setMsgOk(infoMsg);

			}
		} catch (ParseException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_PARSE_DATE, new Object[ ] { e.getMessage() }));
			taskForm.setError(Language.getResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));
		} catch (TaskValetException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK, new Object[ ] { e.getMessage() }));
			taskForm.setError(Language.getResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB, new Object[ ] { e.getMessage() }));
			taskForm.setError(Language.getResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));
		}

		return taskForm;
	}

	/**
	 * Method to validate the date indicated for the planning of the task.
	 * @param taskForm Parameter that represents the backing form for editing a Task
	 * @return true, if the date is correct.
	 */
	private Boolean validateInitDate(TaskForm taskForm) {
		Boolean result = true;
		if (taskForm.getIsEnabled()) {
			// se comprueba que la fecha indicada no sea anterior a la actual
			LocalDate now = LocalDate.now();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
			LocalDate initDay = LocalDate.parse(taskForm.getInitDayString(), formatter);
			if (initDay == null || initDay.isBefore(now)) {
				result = false;
			}
		}

		return result;
	}

}
