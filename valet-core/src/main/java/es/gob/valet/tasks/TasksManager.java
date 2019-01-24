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
 * <b>File:</b><p>es.gob.valet.tasks.TasksManager.java.</p>
 * <b>Description:</b><p>Class that manages the named 'Tasks'. This tasks are only managed
 * by administrators of the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/01/2019.
 */
package es.gob.valet.tasks;

import java.util.List;

import org.apache.log4j.Logger;

import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.Planner;
import es.gob.valet.persistence.configuration.model.entity.Task;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.quartz.planner.IPlanner;
import es.gob.valet.quartz.planner.PlannerDate;
import es.gob.valet.quartz.planner.PlannerPeriod;
import es.gob.valet.quartz.scheduler.TasksScheduler;
import es.gob.valet.quartz.scheduler.ValetSchedulerException;

/**
 * <p>Class that manages the named 'Tasks'. This tasks are only managed
 * by administrators of the platform.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/01/2019.
 */
public final class TasksManager {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(TasksManager.class);

	/**
	 * Constructor method for the class TasksManager.java.
	 */
	private TasksManager() {
		super();
	}

	/**
	 * Method that loads all the tasks of the system and launches them for their execution.
	 */
	public static void loadTasks() {

		LOGGER.info(Language.getResCoreGeneral(ICoreGeneralMessages.TASK_MNG_000));

		// Obtenemos la lista de tareas.
		List<Task> tasksList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getTaskService().getAllTask();

		// Si la lista de tareas no es nula ni vacía...
		if (tasksList != null && !tasksList.isEmpty()) {

			LOGGER.debug(Language.getFormatResCoreGeneral(ICoreGeneralMessages.TASK_MNG_001, new Object[ ] { tasksList.size() }));

			// Se recorren todas las tareas...
			for (Task task: tasksList) {

				// Si la tarea está activa...
				if (task.getIsEnabled()) {

					String taskName = null;
					String implementationClassName = null;

					try {

						// Se extraen sus datos.
						taskName = Language.getResPersistenceConstants(task.getTokenName());
						implementationClassName = task.getImplementationClass();

						// Llamamos al método auxiliar (para reducir complejidad
						// ciclomática).
						loadTasksAux(task, taskName, implementationClassName);

					} catch (ClassNotFoundException e) {
						LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.TASK_MNG_004, new Object[ ] { implementationClassName, taskName }), e);
					}

				}

			}

		}

	}

	/**
	 * Private auxiliar method to load the tasks and reduce the cyclomatic complexity.
	 * @param task POJO object with the data of the task.
	 * @param taskName Name of the task.
	 * @param implementationClassName Implementation class name of the task.
	 * @throws ClassNotFoundException In case of some error with the class that implements the task.
	 */
	@SuppressWarnings("unchecked")
	private static void loadTasksAux(Task task, String taskName, String implementationClassName) throws ClassNotFoundException {

		// Cargamos y obtenemos la lista de planificadores
		// asociados.
		List<Planner> plannersList = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getPlannerService().getListPlannersByTask(task.getIdTask());

		// Si al menos hay un planificador...
		if (plannersList != null && !plannersList.isEmpty()) {

			LOGGER.debug(Language.getFormatResCoreGeneral(ICoreGeneralMessages.TASK_MNG_002, new Object[ ] { taskName, implementationClassName }));

			TasksScheduler tasksScheduler = TasksScheduler.getInstance();
			for (Planner planner: plannersList) {
				// Construimos el planificador asociado.
				IPlanner calculatedPlanner = getCalculatedPlannerFromDataBase(planner);
				try {
					tasksScheduler.addOrReplacePlannerInTask(taskName, calculatedPlanner, (Class<es.gob.valet.quartz.task.Task>) Class.forName(implementationClassName), null);
				} catch (ValetSchedulerException e) {
					LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.TASK_MNG_003, new Object[ ] { taskName }), e);
				}
			}

		}

	}

	/**
	 * Method that obtains a planner through the data base configuration of it.
	 * @param planner Parameter that represents the information of the planner in the database.
	 * @return an object that represents the calculated planner.
	 */
	public static IPlanner getCalculatedPlannerFromDataBase(Planner planner) {
		IPlanner result = null;
		switch (planner.getPlannerType().getIdPlannerType().intValue()) {
			case IPlanner.PLANNER_TYPE_DIARY:
			case IPlanner.PLANNER_TYPE_PERIOD:
				result = new PlannerPeriod(planner);
				break;
			case IPlanner.PLANNER_TYPE_DATE:
				result = new PlannerDate(planner);
				break;
			default:
				break;
		}
		return result;
	}

	/**
	 * Adds or update a task in the scheduler.
	 * @param task Task pojo from the data base to add/update in the scheduler.
	 * @throws TaskValetException In case of some error working with the task.
	 */
	public static void addOrUpdateTask(Task task) throws TaskValetException {

		// Obtenemos el nombre de la tarea.
		String taskName = Language.getResPersistenceConstants(task.getTokenName());
		// Obtenemos su implementación.
		String implementationClassName = task.getImplementationClass();

		try {

			// Si la tarea existe, la eliminamos del scheduler.
			TasksScheduler tasksScheduler = TasksScheduler.getInstance();

			if (tasksScheduler.checkIfExistsTask(taskName)) {
				tasksScheduler.stopTask(taskName);
			}

			// Si la tarea está habilitada, la generamos de nuevo.
			if (task.getIsEnabled()) {

				// Llamamos al método auxiliar (para reducir complejidad
				// ciclomática).
				loadTasksAux(task, taskName, implementationClassName);

			}
		} catch (ValetSchedulerException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.TASK_MNG_003, new Object[ ] { taskName });
			LOGGER.error(errorMsg, e);
			throw new TaskValetException(IValetException.COD_185, errorMsg, e);
		} catch (ClassNotFoundException e) {
			String errorMsg = Language.getFormatResCoreGeneral(ICoreGeneralMessages.TASK_MNG_004, new Object[ ] { implementationClassName, taskName });
			LOGGER.error(errorMsg, e);
			throw new TaskValetException(IValetException.COD_185, errorMsg, e);
		}

	}

}
