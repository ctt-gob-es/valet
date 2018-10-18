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
 * @version 1.1, 18/10/2018.
 */
package es.gob.valet.rest.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.commons.utils.UtilsFecha;
import es.gob.valet.form.TaskForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.persistence.configuration.model.entity.Planner;
import es.gob.valet.persistence.configuration.model.entity.Task;
import es.gob.valet.persistence.configuration.services.ifaces.IPlannerService;
import es.gob.valet.persistence.configuration.services.ifaces.ITaskService;
import es.gob.valet.quartz.planner.IPlanificador;
import es.gob.valet.quartz.planner.PlanificadorPeriodico;
import es.gob.valet.quartz.planner.PlanificadorPorFecha;
import es.gob.valet.quartz.scheduler.ProcessTasksScheduler;
import es.gob.valet.quartz.scheduler.ValetSchedulerException;

/**
 * <p>Class that manages the REST request related to the Task's administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 18/10/2018.
 */
@RestController
public class TaskRestController {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(GeneralConstants.LOGGER_NAME_VALET_LOG);

	/**
	 * Constant that represents the format date.
	 */
	private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Attribute that represents the service object for acceding to PlannerRespository.
	 */
	@Autowired
	private IPlannerService plannerService;

	/**
	 * Attribute that represents the service object for acceding to TaskRespository.
	 */
	@Autowired
	private ITaskService taskService;

	/**
	 * Method to update the task.
	 * @param taskForm Parameter that represents the backing form for editing a Task
	 * @return Modified task.
	 */
	@RequestMapping(value = "/updatetask", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody TaskForm updateTask(@RequestBody TaskForm taskForm) {

		// JSONObject json = new JSONObject();

		try {

			// validamos las fechas
			if (!validateInitDate(taskForm)) {
				taskForm.setError(Language.getResWebGeneral(IWebGeneralMessages.ERROR_VALIDATE_DATE));
			} else {
				// se obtiene el planificador
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
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Date initDay = sdf.parse(taskForm.getInitDayString());

				planner.setInitDay(initDay);

				// se obtiene la clase que implementa la tarea
				Task task = taskService.getTaskById(taskForm.getIdTask());

				// se actualiza la tarea indicando si está habilitada o no.
				task.setIsEnabled(taskForm.getIsEnabled());

				// persistimos los cambios del planificador y d la tarea.
				Task updatedTask = taskService.saveTask(task);
				Planner updatedPlanner = plannerService.savePlanner(planner);

				LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.INFO_UPDATE_TASK_OK));

				ProcessTasksScheduler process = ProcessTasksScheduler.getInstance();
				// se comprueba si la tarea está activa

				String taskName = task.getName();

				if (taskForm.getIsEnabled()) {
					IPlanificador planificador = getPlannerFromSchedulerConfiguration(updatedPlanner);
					// se obtiene la clase que implementa la tarea

					Class<es.gob.valet.quartz.task.Task> taskClass = getClassTask(task);

					process.addOrReplacePlannerInTask(taskName, planificador, taskClass, null);
					// json.put(KEY_JS_MESSAGE_UPDATE_OK,
					// Language.getFormatResWebValet(IWebGeneralMessages.INFO_TASK_ACTIVE_OK,
					// new Object[ ] {taskName }));
					taskForm.setMsgOk(Language.getFormatResWebGeneral(IWebGeneralMessages.INFO_TASK_ACTIVE_OK, new Object[ ] { taskName }));
				} else {
					// se comprueba que exista la tarea, si existe se para

					if (process.checkIfExistsTask(taskName)) {
						// se para
						process.stopTask(taskName);
						// json.put(KEY_JS_MESSAGE_UPDATE_OK,
						// Language.getFormatResWebValet(IWebGeneralMessages.INFO_TASK_STOP,
						// new Object[ ] {taskName }));
						taskForm.setMsgOk(Language.getFormatResWebGeneral(IWebGeneralMessages.INFO_TASK_STOP, new Object[ ] { taskName }));
					} else {
						// se indica que la tarea se ha modificado correctamente
						taskForm.setMsgOk(Language.getResWebGeneral(IWebGeneralMessages.INFO_UPDATE_TASK_OK));
					}

				}
			}
		} catch (ParseException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_PARSE_DATE, new Object[ ] { e.getMessage() }));
			// json.put(KEY_JS_MESSAGE_UPDATE_ERROR,
			// Language.getResWebValet(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));
			taskForm.setError(Language.getResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));

		} catch (ClassNotFoundException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_GET_CLASS_TASK, new Object[ ] { e.getMessage() }));
			// json.put(KEY_JS_MESSAGE_UPDATE_ERROR,
			// Language.getResWebValet(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));
			taskForm.setError(Language.getResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));
		} catch (ValetSchedulerException e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_ACTIVE_TASK, new Object[ ] { e.getMessage() }));
			// json.put(KEY_JS_MESSAGE_UPDATE_ERROR,
			// Language.getResWebValet(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));
			taskForm.setError(Language.getResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));
		} catch (Exception e) {
			LOGGER.error(Language.getFormatResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB, new Object[ ] { e.getMessage() }));
			taskForm.setError(Language.getResWebGeneral(IWebGeneralMessages.ERROR_UPDATE_TASK_WEB));
		}

		return taskForm;
	}

	/**
	 * Method that obtains a planner through the scheduler configuration.
	 * @param planner Parameter that represents the information of the planner in the database.
	 * @return an object that represents the planner.
	 */
	private IPlanificador getPlannerFromSchedulerConfiguration(Planner planner) {
		IPlanificador result = null;
		Long periodMs = null;
		switch (planner.getPlannerType().getIdPlannerType().intValue()) {
			case IPlanificador.TIPO_PLAN_DIARIO:
				periodMs = UtilsFecha.getPeriod(planner.getHourPeriod(), planner.getMinutePeriod(), planner.getSecondPeriod());
				PlanificadorPeriodico ppDaily = new PlanificadorPeriodico(periodMs, planner.getInitDay(), true);
				result = (IPlanificador) ppDaily;
				break;
			case IPlanificador.TIPO_PLAN_PERIOD:
				periodMs = UtilsFecha.getPeriod(planner.getHourPeriod(), planner.getMinutePeriod(), planner.getSecondPeriod());
				PlanificadorPeriodico ppPeriod = new PlanificadorPeriodico(periodMs, planner.getInitDay(), false);
				result = (IPlanificador) ppPeriod;
				break;
			case IPlanificador.TIPO_PLAN_PFECHA:
				PlanificadorPorFecha ppf = new PlanificadorPorFecha(planner.getInitDay());
				result = (IPlanificador) ppf;
				break;
			default:
				break;
		}
		return result;
	}

	/**
	 * Method to obtain the class that implements the task.
	 *
	 * @param task Parameter represents a task.
	 * @return Class that implements the task.
	 * @throws ClassNotFoundException
	 */
	private Class<es.gob.valet.quartz.task.Task> getClassTask(Task task) throws ClassNotFoundException {
		Class<es.gob.valet.quartz.task.Task> taskClass = null;
		String taskClassName = task.getImplementationClass();
		taskClass = (Class<es.gob.valet.quartz.task.Task>) Class.forName(taskClassName);
		return taskClass;
	}

	/**
	 * Method to validate the date indicated for the planning of the task.
	 *
	 * @param taskForm Parameter that represents the backing form for editing a Task
	 * @return true, if the date is correct.
	 */
	private Boolean validateInitDate(TaskForm taskForm) {
		Boolean result = true;

		// se comprueba que la fecha indicada no sea anterior a la actual
		LocalDate now = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
		LocalDate initDay = LocalDate.parse(taskForm.getInitDayString(), formatter);
		if (initDay == null || initDay.isBefore(now)) {
			result = false;
		}

		return result;
	}

}
