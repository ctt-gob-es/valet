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
 * <b>File:</b><p>es.gob.valet.tasks.HiddenTasksManager.java.</p>
 * <b>Description:</b><p>Class that manages the named 'Hidden Tasks'. This tasks are from/for
 * the system, and the final user (administrator) don't manage these.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/01/2019.
 */
package es.gob.valet.tasks;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.StaticValetConfig;
import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.commons.utils.UtilsDeploymentType;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.ICoreGeneralMessages;
import es.gob.valet.quartz.planner.IPlanner;
import es.gob.valet.quartz.planner.PlannerDate;
import es.gob.valet.quartz.planner.PlannerPeriod;
import es.gob.valet.quartz.scheduler.HiddenTasksScheduler;
import es.gob.valet.quartz.scheduler.ValetSchedulerException;
import es.gob.valet.quartz.task.Task;

/**
 * <p>Class that manages the named 'Hidden Tasks'. This tasks are from/for
 * the system, and the final user (administrator) don't manage these.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/01/2019.
 */
public final class HiddenTasksManager {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(HiddenTasksManager.class);

	/**
	 * Constant attribute that represents the key for the task property.
	 */
	private static final String PROPERTY_TASK = "task";

	/**
	 * Constant attribute that represents the key for the target task property.
	 */
	private static final String PROPERTY_TASK_TARGET = "target";

	/**
	 * Constant attribute that represents the value for the target property
	 * to indicate that the task is for an administration instance.
	 */
	private static final String TASK_TARGET_ADMINISTRATION = "administration";

	/**
	 * Constant attribute that represents the value for the target property
	 * to indicate that the task is for a service instance.
	 */
	private static final String TASK_TARGET_SERVICE = "service";

	/**
	 * Constant attribute that represents the value for the target property
	 * to indicate that the task is for all the instances.
	 */
	private static final String TASK_TARGET_BOTH = "both";

	/**
	 * Constant attribute that represents the key for the class task property.
	 */
	private static final String PROPERTY_TASK_CLASS = "class";

	/**
	 * Constant attribute that represents the key for the start date task property.
	 */
	private static final String PROPERTY_TASK_STARTDATE = "startDate";

	/**
	 * Constant attribute that represents the value for the startDate property
	 * to indicate that the task must start now.
	 */
	private static final String TASK_STARTDATE_NOW = "NOW";

	/**
	 * Constant attribute that represents the value for the startDate property
	 * to indicate that the task must start after wait the defined period.
	 */
	private static final String TASK_STARTDATE_WAIT_PERIOD = "WAIT_PERIOD";

	/**
	 * Constant attribute that represents the key for the period task property.
	 */
	private static final String PROPERTY_TASK_PERIOD = "period";

	/**
	 * Constant attribute that represents a regex expresion for the dot character.
	 */
	private static final String REGEX_DOT = "\\.";

	/**
	 * Attribute that represents a map with the configuration of the differents
	 * hidden tasks defined in the properties file.
	 */
	private static Map<String, Map<String, Object>> actualHiddenTasks = null;

	/**
	 * Constructor method for the class HiddenTasksManager.java.
	 */
	private HiddenTasksManager() {
		super();
		reloadHiddenTasks();
	}

	/**
	 * Cleans the information about the actual hidden tasks.
	 */
	public static void cleanActualHiddenTasks() {
		actualHiddenTasks = null;
	}

	/**
	 * Method that load/reload the hidden tasks
	 * from the properties file, and run these.
	 */
	public static void reloadHiddenTasks() {

		Properties hiddenTasksProperties = StaticValetConfig.getProperties(PROPERTY_TASK);
		Map<String, Map<String, Object>> tempHiddenTasks = loadTempHiddenTasks(hiddenTasksProperties);
		compareAndUpdateWithActualTasks(tempHiddenTasks);

	}

	/**
	 * Method that order the properties and create a temporally structure
	 * for the tasks to load.
	 * @param hiddenTasksProperties Properties of the hidden tasks.
	 * @return Structure with the differents hidden tasks loaded.
	 */
	private static Map<String, Map<String, Object>> loadTempHiddenTasks(Properties hiddenTasksProperties) {

		// Iniciamos el hastable temporal.
		Map<String, Map<String, Object>> tempHiddenTasks = new HashMap<String, Map<String, Object>>();

		// Si existen propiedades definidas en el fichero...
		if (!hiddenTasksProperties.isEmpty()) {

			// Obtenemos la lista de keys ordenadas alfabéticamente.
			Set<Object> keysTreeSet = new TreeSet<Object>(hiddenTasksProperties.keySet());
			String[ ] keysArray = keysTreeSet.toArray(new String[0]);

			Set<String> tasksOmittedSet = new TreeSet<String>();

			// Las recorremos.
			for (String key: keysArray) {

				// Descomponemos la clave.
				String[ ] keySplitted = key.split(REGEX_DOT);

				boolean evaluateProperty = keySplitted != null && keySplitted.length == NumberConstants.NUM3 && !UtilsStringChar.isNullOrEmptyTrim(keySplitted[1]);
				evaluateProperty = evaluateProperty && !UtilsStringChar.isNullOrEmptyTrim(keySplitted[2]);
				if (evaluateProperty) {
					evaluateProperty = keySplitted[2].equals(PROPERTY_TASK_TARGET) || keySplitted[2].equals(PROPERTY_TASK_CLASS) || keySplitted[2].equals(PROPERTY_TASK_STARTDATE);
					evaluateProperty = evaluateProperty || keySplitted[2].equals(PROPERTY_TASK_PERIOD);
				}

				// Para evitar complejidad ciclomática se continúa en un método
				// auxiliar.
				loadTempHiddenTasksAux(evaluateProperty, tasksOmittedSet, tempHiddenTasks, keySplitted[1], hiddenTasksProperties);

			}

		}

		return tempHiddenTasks;

	}

	/**
	 * Auxiliar method to avoid cyclomatic complexity.
	 * @param evaluateProperty Flag that indicates if it is necessary to evaluate this property.
	 * @param tasksOmittedSet Set of task that already could be avoided.
	 * @param tempHiddenTasks Map where to set the different properties of the task.
	 * @param taskName Name of the task property to obtain.
	 * @param hiddenTasksProperties Hidden tasks properties.
	 */
	private static void loadTempHiddenTasksAux(boolean evaluateProperty, Set<String> tasksOmittedSet, Map<String, Map<String, Object>> tempHiddenTasks, String taskName, Properties hiddenTasksProperties) {

		// Si la forma de la clave no es correcta la ignoramos.
		if (evaluateProperty) {

			// Si la tarea ya fue omitida o ya está añadida en el
			// map temporal no hacemos nada.
			if (!tasksOmittedSet.contains(taskName) && !tempHiddenTasks.containsKey(taskName)) {
				// Añadimos la clave y su valor al map temporal.
				addPropertiesTaskToTempTasksMap(hiddenTasksProperties, tempHiddenTasks, tasksOmittedSet, taskName);
			}

		}

	}

	/**
	 * Method that adds the properties of the hidden task obtained from the file properties in
	 * a temporally map.
	 * @param hiddenTasksProperties Hidden tasks properties.
	 * @param tempHiddenTasks Map where to set the different properties of the task.
	 * @param tasksOmmitedSet Set of task that already could be avoided.
	 * @param taskName Name of the property to obtain.
	 */
	private static void addPropertiesTaskToTempTasksMap(Properties hiddenTasksProperties, Map<String, Map<String, Object>> tempHiddenTasks, Set<String> tasksOmmitedSet, String taskName) {

		// Inicializamos las variables de las propiedades de la tarea.
		String target = null;
		boolean validTarget = false;
		Class<Task> taskClass = null;
		Date startDate = null;
		Long periodMilliseconds = null;

		// Bandera que nos indica si hemos recuperado correctamente
		// todas las propiedades necesarias para formar la tarea.
		boolean allPropertiesCollected = false;

		// Creamos la variable-map que contendrá las propiedades de la tarea.
		Map<String, Object> tempTaskProperties = null;

		// Recuperamos el target de la tarea.
		target = getTargetTaskFromProperties(hiddenTasksProperties, taskName);

		// Si lo hemos recuperado correctamente continuamos...
		if (target != null) {

			// Comprobamos que el target corresponde con este despliegue.
			validTarget = target.equals(TASK_TARGET_BOTH) || target.equals(TASK_TARGET_SERVICE) && UtilsDeploymentType.isDeployedServices();
			validTarget = validTarget || target.equals(TASK_TARGET_ADMINISTRATION) && UtilsDeploymentType.isDeployedWebAdmin();

			// Si el target es correcto continuamos...
			if (validTarget) {

				// Recuperamos la clase de la tarea.
				taskClass = getClassTaskFromProperties(hiddenTasksProperties, taskName, target);

				// Si la hemos recuperado correctamente continuamos...
				if (taskClass != null) {

					// Recuperamos el periodo de ejecución...
					periodMilliseconds = getPeriodTaskFromProperties(hiddenTasksProperties, taskName);

					// Si lo hemos recuperado correctamente continuamos...
					if (periodMilliseconds != null) {

						// Inicializamos el map donde se almacenarán las
						// propiedades de la tarea.
						tempTaskProperties = new HashMap<String, Object>();

						// Recuperamos la fecha de inicio de la tarea.
						startDate = getInitialDateTaskFromProperties(hiddenTasksProperties, taskName, periodMilliseconds, tempTaskProperties);

						// Si la hemos recuperado correctamente continuamos...
						if (startDate != null) {

							allPropertiesCollected = true;

						}

					}

				}

			}

		}

		// Continuamos en otro método auxiliar para evitar la complejidad
		// ciclomática.
		addPropertiesTaskToTempTasksMapAux(allPropertiesCollected, tempTaskProperties, target, taskClass, periodMilliseconds, startDate, tempHiddenTasks, tasksOmmitedSet, taskName, validTarget);

	}

	/**
	 * Auxiliar method to avoid cyclomatic complexity.
	 * @param allPropertiesCollected Flag that indicates if all the properties has been collected.
	 * @param tempTaskProperties Temporal map where are stored the properties collected.
	 * @param target Target type for the task.
	 * @param taskClass Class that implements the task.
	 * @param periodMilliseconds Period to assign to the task.
	 * @param startDate Starting date for the task.
	 * @param tempHiddenTasks Temporal map where are stored all the hidden task with the associated properties.
	 * @param tasksOmmitedSet Temporal task where are stored the ids of the task that must be ommited.
	 * @param taskName Name of the task.
	 * @param validTarget Flag that indicates if the target is valid.
	 */
	private static void addPropertiesTaskToTempTasksMapAux(boolean allPropertiesCollected, Map<String, Object> tempTaskProperties, String target, Class<Task> taskClass, Long periodMilliseconds, Date startDate, Map<String, Map<String, Object>> tempHiddenTasks, Set<String> tasksOmmitedSet, String taskName, boolean validTarget) {

		// Si hemos recuperado todas las propiedades necesarias...
		if (allPropertiesCollected) {

			// Insertamos las nuevas propiedades.
			tempTaskProperties.put(PROPERTY_TASK_TARGET, target);
			tempTaskProperties.put(PROPERTY_TASK_CLASS, taskClass);
			tempTaskProperties.put(PROPERTY_TASK_PERIOD, periodMilliseconds);
			tempTaskProperties.put(PROPERTY_TASK_STARTDATE, startDate);
			tempHiddenTasks.put(taskName, tempTaskProperties);

		} else {

			tasksOmmitedSet.add(taskName);
			if (validTarget) {
				LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_000, new Object[ ] { taskName }));
			} else {
				LOGGER.info(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_006, new Object[ ] { taskName }));
			}

		}

	}

	/**
	 * Auxiliar method to get the target for a hidden task (service instance, administration instance or both).
	 * @param hiddenTasksProperties Hidden tasks properties.
	 * @param taskName Name of the property to obtain.
	 * @return The target for a hidden task (service instance, administration instance or both). <code>null</code>
	 * if the value property is not obtained.
	 */
	private static String getTargetTaskFromProperties(Properties hiddenTasksProperties, String taskName) {

		String result = hiddenTasksProperties.getProperty(PROPERTY_TASK + UtilsStringChar.SYMBOL_DOT_STRING + taskName + UtilsStringChar.SYMBOL_DOT_STRING + PROPERTY_TASK_TARGET);

		// Si no se ha recuperado correctamente, no se toman los datos de la
		// tarea.
		if (UtilsStringChar.isNullOrEmptyTrim(result)) {

			LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_001, new Object[ ] { taskName, PROPERTY_TASK_TARGET }));

		} else {

			switch (result) {
				case TASK_TARGET_ADMINISTRATION:
				case TASK_TARGET_SERVICE:
				case TASK_TARGET_BOTH:
					break;
				default:
					result = null;
					break;
			}

		}

		return result;

	}

	/**
	 * Auxiliar method to get the class that implements the hidden task.
	 * @param hiddenTasksProperties Hidden tasks properties.
	 * @param taskName Name of the property to obtain.
	 * @param target Target deployment for this task.
	 * @return The class that implements the hidden task. <code>null</code> if not is obtained.
	 */
	@SuppressWarnings("unchecked")
	private static Class<Task> getClassTaskFromProperties(Properties hiddenTasksProperties, String taskName, String target) {

		Class<Task> result = null;

		// Recuperamos la clase de la tarea.
		String classNameString = hiddenTasksProperties.getProperty(PROPERTY_TASK + UtilsStringChar.SYMBOL_DOT_STRING + taskName + UtilsStringChar.SYMBOL_DOT_STRING + PROPERTY_TASK_CLASS);
		// Si no se ha recuperado correctamente, no se toman los datos de la
		// tarea.
		if (UtilsStringChar.isNullOrEmptyTrim(classNameString)) {

			LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_001, new Object[ ] { taskName, PROPERTY_TASK_CLASS }));

		} else {

			classNameString = classNameString.trim();
			// Tratamos de generar el class.
			try {
				result = (Class<Task>) Class.forName(classNameString);
			} catch (ClassNotFoundException e) {
				LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_001, new Object[ ] { taskName, PROPERTY_TASK_CLASS }), e);
			} catch (ClassCastException e) {
				LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_001, new Object[ ] { taskName, PROPERTY_TASK_CLASS }), e);
			}

		}

		return result;

	}

	/**
	 * Auxiliar method to get the period to use with the hidden task.
	 * @param hiddenTasksProperties Hidden tasks properties.
	 * @param taskName Name of the property to obtain.
	 * @return The number of milliseconds that define the period for the task. <code>null</code> if it is not
	 * obtained.
	 */
	private static Long getPeriodTaskFromProperties(Properties hiddenTasksProperties, String taskName) {

		Long result = null;

		// Recuperamos el número de milisegundos del periodo.
		String periodString = hiddenTasksProperties.getProperty(PROPERTY_TASK + UtilsStringChar.SYMBOL_DOT_STRING + taskName + UtilsStringChar.SYMBOL_DOT_STRING + PROPERTY_TASK_PERIOD);
		// Si no se ha recuperado correctamente, no se toman los datos de la
		// tarea.
		if (UtilsStringChar.isNullOrEmptyTrim(periodString)) {

			LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_001, new Object[ ] { taskName, PROPERTY_TASK_PERIOD }));

		} else {

			periodString = periodString.trim();
			// Tratamos de parsear el número de milisegundos.
			try {
				result = Long.parseLong(periodString);
			} catch (NumberFormatException e) {
				LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_001, new Object[ ] { taskName, PROPERTY_TASK_PERIOD }), e);
			}

		}

		return result;

	}

	/**
	 * Auxiliar method to get the initial date to use with the hidden task.
	 * @param hiddenTasksProperties Hidden tasks properties.
	 * @param taskName Name of the property to obtain.
	 * @param periodMilliseconds number of milliseconds that define the period for the task.
	 * @param tempTaskProperties Map which stores the task properties.
	 * @return the initial date to use with the hidden task. <code>null</code> if it is not
	 * obtained.
	 */
	private static Date getInitialDateTaskFromProperties(Properties hiddenTasksProperties, String taskName, Long periodMilliseconds, Map<String, Object> tempTaskProperties) {

		Date result = null;

		// Recuperamos la fecha de comienzo.
		String startDateString = hiddenTasksProperties.getProperty(PROPERTY_TASK + UtilsStringChar.SYMBOL_DOT_STRING + taskName + UtilsStringChar.SYMBOL_DOT_STRING + PROPERTY_TASK_STARTDATE);
		// Si no se ha recuperado correctamente, no se toman los datos de la
		// tarea.
		if (UtilsStringChar.isNullOrEmptyTrim(startDateString)) {

			LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_001, new Object[ ] { taskName, PROPERTY_TASK_STARTDATE }));

		} else {

			startDateString = startDateString.trim();

			switch (startDateString) {
				// Si el valor asignado es 'NOW'...
				case TASK_STARTDATE_NOW:
					// Calculamos la fecha actual más 10 segundos, para dar
					// tiempo
					// al sistema para que inicie.
					Calendar calStartDate = Calendar.getInstance();
					calStartDate.add(Calendar.SECOND, NumberConstants.NUM10);
					result = calStartDate.getTime();
					// Indicamos en las propiedades de la tarea, la forma de
					// calcular la fecha.
					tempTaskProperties.put(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_NOW, Boolean.TRUE);
					tempTaskProperties.put(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_WAIT_PERIOD, Boolean.FALSE);
					break;

				// Si el valor asignado es 'WAIT_PERIOD'...
				case TASK_STARTDATE_WAIT_PERIOD:
					// Si es menor de 10 segundos, se aumenta hasta 10.
					int waitPeriod = periodMilliseconds.intValue();
					if (waitPeriod < NumberConstants.NUM10000) {
						waitPeriod = NumberConstants.NUM10000;
					}
					// Calculamos la fecha añadiéndole este tiempo a la actual.
					Calendar calWaitPeriod = Calendar.getInstance();
					calWaitPeriod.add(Calendar.MILLISECOND, waitPeriod);
					result = calWaitPeriod.getTime();
					// Indicamos en las propiedades de la tarea, la forma de
					// calcular la fecha.
					tempTaskProperties.put(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_NOW, Boolean.FALSE);
					tempTaskProperties.put(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_WAIT_PERIOD, Boolean.TRUE);
					break;

				default:
					// Tratamos de parsear la fecha.
					try {
						result = UtilsDate.transformDate(startDateString, UtilsDate.FORMAT_DATE_TIME_SECONDS);
						// Indicamos en las propiedades de la tarea, la forma de
						// calcular la fecha.
						tempTaskProperties.put(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_NOW, Boolean.FALSE);
						tempTaskProperties.put(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_WAIT_PERIOD, Boolean.FALSE);
					} catch (ParseException e) {
						LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_002, new Object[ ] { taskName, PROPERTY_TASK_STARTDATE, UtilsDate.FORMAT_DATE_TIME_SECONDS }));
					}
					break;

			}

		}

		return result;

	}

	/**
	 * Method that compares the actual defined tasks with the loaded,
	 * and removes, update or add the correspondient task.
	 * @param tempHiddenTasks Parameter that represents the actual tasks.
	 */
	private static void compareAndUpdateWithActualTasks(Map<String, Map<String, Object>> tempHiddenTasks) {

		// Iniciamos las listas que determinarán qué tareas deben actualizarse.
		List<String> toRemove = new ArrayList<String>();
		List<String> toUpdate = new ArrayList<String>();
		List<String> toAdd = new ArrayList<String>();

		// Comprobamos si aún tenemos que inicializar la lista actual de
		// tareas...
		if (actualHiddenTasks == null) {
			actualHiddenTasks = new HashMap<String, Map<String, Object>>();
		}

		// Recorremos las tareas actualmente definidas para ver si cambia alguna
		// propiedad o hay que eliminar alguna.
		Set<String> actualKeysTasksEnum = actualHiddenTasks.keySet();
		for (String actualTaskKey: actualKeysTasksEnum) {

			// Comprobamos si existe en el hashtable temporal.
			if (tempHiddenTasks.containsKey(actualTaskKey)) {

				// Recogemos las propiedades y las comparamos.
				Map<String, Object> actualTaskProperties = actualHiddenTasks.get(actualTaskKey);
				Map<String, Object> tempTaskProperties = tempHiddenTasks.get(actualTaskKey);

				String actualTarget = (String) actualTaskProperties.get(PROPERTY_TASK_TARGET);
				String tempTarget = (String) tempTaskProperties.get(PROPERTY_TASK_TARGET);
				@SuppressWarnings("unchecked")
				Class<Task> actualClass = (Class<Task>) actualTaskProperties.get(PROPERTY_TASK_CLASS);
				@SuppressWarnings("unchecked")
				Class<Task> tempClass = (Class<Task>) tempTaskProperties.get(PROPERTY_TASK_CLASS);
				Long actualPeriod = (Long) actualTaskProperties.get(PROPERTY_TASK_PERIOD);
				Long tempPeriod = (Long) tempTaskProperties.get(PROPERTY_TASK_PERIOD);
				Boolean actualStartNow = (Boolean) actualTaskProperties.get(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_NOW);
				Boolean tempStartNow = (Boolean) tempTaskProperties.get(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_NOW);
				Boolean actualWaitPeriod = (Boolean) actualTaskProperties.get(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_WAIT_PERIOD);
				Boolean tempWaitPeriod = (Boolean) tempTaskProperties.get(PROPERTY_TASK_STARTDATE + UtilsStringChar.SYMBOL_HYPHEN_STRING + TASK_STARTDATE_WAIT_PERIOD);
				Date actualStartDate = (Date) actualTaskProperties.get(PROPERTY_TASK_STARTDATE);
				Date tempStartDate = (Date) tempTaskProperties.get(PROPERTY_TASK_STARTDATE);

				// Para evitar complejidad ciclomática continuamos en otro
				// método.
				compareAndUpdateWithActualTasksAux(actualTarget, tempTarget, actualClass, tempClass, actualPeriod, tempPeriod, actualStartNow, tempStartNow, actualWaitPeriod, tempWaitPeriod, actualStartDate, tempStartDate, actualTaskKey, toAdd, toUpdate, tempHiddenTasks);

			} else {

				// Si no existe, se elimina la tarea.
				toRemove.add(actualTaskKey);

			}

		}

		// Recorremos las tareas que hayan quedado en el temporal para añadirlas
		// a las actuales.
		Set<String> tempKeysTasksEnum = tempHiddenTasks.keySet();
		for (String tempTaskKey: tempKeysTasksEnum) {

			// Añadimos la tarea a las actuales.
			actualHiddenTasks.put(tempTaskKey, tempHiddenTasks.get(tempTaskKey));
			toAdd.add(tempTaskKey);

		}

		// Se eliminan las tareas obtenidas.
		removeHiddenTasks(toRemove);
		// Se actualizan las ya existentes.
		updateHiddenTasks(toUpdate);
		// Se añaden las nuevas.
		addOrUpdateHiddenTasks(toAdd);

	}

	/**
	 * Auxiliar method to avoid cyclomatic complexity.
	 * @param actualTarget Target for the actual task.
	 * @param tempTarget Target for the task to add/update.
	 * @param actualClass Class that implements the actual task.
	 * @param tempClass Class that implements the task to add/update.
	 * @param actualPeriod Period for the actual task.
	 * @param tempPeriod Period for the task to add/update.
	 * @param actualStartNow Flag that indicates if the actual task initialize at the moment.
	 * @param tempStartNow Flag that indicates if the task to add/update initialize at the moment.
	 * @param actualWaitPeriod Flag that indicates if the actual task initialize waiting a period.
	 * @param tempWaitPeriod Flag that indicates if the task to add/update initialize waiting a period.
	 * @param actualStartDate Starting date for the actual task.
	 * @param tempStartDate Starting date for the task to add/update.
	 * @param actualTaskKey Key name for the actual task.
	 * @param toAdd List of task that must be added in the scheduler.
	 * @param toUpdate List of task that must be updated.
	 * @param tempHiddenTasks Map with all the properties of the task to work with.
	 */
	private static void compareAndUpdateWithActualTasksAux(String actualTarget, String tempTarget, Class<Task> actualClass, Class<Task> tempClass, Long actualPeriod, Long tempPeriod, Boolean actualStartNow, Boolean tempStartNow, Boolean actualWaitPeriod, Boolean tempWaitPeriod, Date actualStartDate, Date tempStartDate, String actualTaskKey, List<String> toAdd, List<String> toUpdate, Map<String, Map<String, Object>> tempHiddenTasks) {

		// Si hay alguna que no sea igual, indicamos que hay que
		// actualizar las propiedades de la tarea.
		boolean updateTask = !actualTarget.equals(tempTarget) || !actualClass.getName().equals(tempClass.getName()) || !actualPeriod.equals(tempPeriod);
		updateTask = updateTask || !actualStartNow.equals(tempStartNow) || !actualWaitPeriod.equals(tempWaitPeriod);
		if (!updateTask && !actualStartNow && !actualWaitPeriod) {
			updateTask = !actualStartDate.equals(tempStartDate);
		}
		// Si finalmente hay que actualizar la tarea...
		if (updateTask) {

			// Si el target es distinto, hay que eliminarla y volverla a
			// añadir...
			if (!actualTarget.equals(tempTarget)) {
				List<String> toRemoveTmp = new ArrayList<String>();
				toRemoveTmp.add(actualTaskKey);
				removeHiddenTasks(toRemoveTmp);
				// Es importante hacer la siguiente línea después de
				// eliminar la tarea
				// y no antes.
				actualHiddenTasks.put(actualTaskKey, tempHiddenTasks.get(actualTaskKey));
				toAdd.add(actualTaskKey);
			}
			// Si no, simplemente se actualiza.
			else {
				actualHiddenTasks.put(actualTaskKey, tempHiddenTasks.get(actualTaskKey));
				toUpdate.add(actualTaskKey);
			}

		}

		// Una vez tratada, se elimina del hashtable temporal.
		tempHiddenTasks.remove(actualTaskKey);

	}

	/**
	 * Method that removes from the execution and from the hastable, a list
	 * of hidden tasks.
	 * @param toRemove List of task names to remove.
	 */
	private static void removeHiddenTasks(List<String> toRemove) {

		// Recorremos la lista.
		for (String taskToRemove: toRemove) {

			try {
				// Detenemos la tarea en ejecución.
				HiddenTasksScheduler.getInstance().stopTask(taskToRemove);
				// Eliminamos la tarea del hashtable de actuales.
				actualHiddenTasks.remove(taskToRemove);
			} catch (ValetSchedulerException e) {
				LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_003, new Object[ ] { taskToRemove }), e);
			}

		}

	}

	/**
	 * Method that updates the configuration of the passed tasks list.
	 * @param toUpdate List of the task names that must be updated.
	 */
	private static void updateHiddenTasks(List<String> toUpdate) {

		// Recorremos la lista.
		for (String taskToUpdate: toUpdate) {

			// Recuperamos los datos de la tarea.
			@SuppressWarnings("unchecked")
			Class<Task> taskClass = (Class<Task>) actualHiddenTasks.get(taskToUpdate).get(PROPERTY_TASK_CLASS);
			Date taskStartDate = (Date) actualHiddenTasks.get(taskToUpdate).get(PROPERTY_TASK_STARTDATE);
			Long taskPeriod = (Long) actualHiddenTasks.get(taskToUpdate).get(PROPERTY_TASK_PERIOD);

			try {
				// Eliminamos la ya existente y añadimos una nueva.
				// Es necesario eliminarla porque puede haber cambiado la clase.
				// Detenemos la tarea en ejecución.
				HiddenTasksScheduler.getInstance().stopTask(taskToUpdate);
				// Añadimos la nueva.
				IPlanner planner = createPlanner(taskStartDate, taskPeriod.longValue());
				HiddenTasksScheduler.getInstance().addOrReplacePlannerInTask(taskToUpdate, planner, taskClass, null);
			} catch (ValetSchedulerException e) {
				LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_004, new Object[ ] { taskToUpdate }), e);
			}

		}

	}

	/**
	 * Method that add new hidden tasks.
	 * @param toAdd List of the new hidden tasks to create.
	 */
	private static void addOrUpdateHiddenTasks(List<String> toAdd) {

		// Recorremos la lista.
		for (String taskToAdd: toAdd) {

			// Recuperamos los datos de la tarea.
			String target = (String) actualHiddenTasks.get(taskToAdd).get(PROPERTY_TASK_TARGET);
			@SuppressWarnings("unchecked")
			Class<Task> taskClass = (Class<Task>) actualHiddenTasks.get(taskToAdd).get(PROPERTY_TASK_CLASS);
			Date taskStartDate = (Date) actualHiddenTasks.get(taskToAdd).get(PROPERTY_TASK_STARTDATE);
			Long taskPeriod = (Long) actualHiddenTasks.get(taskToAdd).get(PROPERTY_TASK_PERIOD);

			// En función del target, comprobamos si debemos inicializar la
			// tarea.
			boolean addTask = target.equals(TASK_TARGET_BOTH);
			addTask = addTask || target.equals(TASK_TARGET_SERVICE) && UtilsDeploymentType.isDeployedServices();
			addTask = addTask || target.equals(TASK_TARGET_ADMINISTRATION) && UtilsDeploymentType.isDeployedWebAdmin();

			// Añadimos la nueva tarea.
			if (addTask) {
				addOrUpdateHiddenTask(taskToAdd, taskClass, taskStartDate, taskPeriod.longValue(), null);
			}

		}

	}

	/**
	 * Add a hidden task in the scheduler.
	 * @param taskName Name of the task.
	 * @param taskClass Class that implements the task.
	 * @param taskStartDate Starting date for the execution of the task.
	 * @param period Period for the task.
	 * @param dataForTheTask Map with the parameters to pass to the task in even execution.
	 * @return <code>true</code> if the task has been added, otherwise <code>false</code>;
	 */
	public static boolean addOrUpdateHiddenTask(String taskName, Class<Task> taskClass, Date taskStartDate, long period, Map<String, Object> dataForTheTask) {

		boolean result = false;
		try {
			IPlanner planner = createPlanner(taskStartDate, period);
			HiddenTasksScheduler.getInstance().addOrReplacePlannerInTask(taskName, planner, taskClass, dataForTheTask);
			result = true;
		} catch (ValetSchedulerException e) {
			LOGGER.error(Language.getFormatResCoreGeneral(ICoreGeneralMessages.HIDDEN_TASK_MNG_005, new Object[ ] { taskName }), e);
		}
		return result;

	}

	/**
	 * Method that creates a planner from a start date and a period.
	 * @param startDate Start date.
	 * @param period Period in milliseconds. Zero if no period.
	 * @return Planner.
	 */
	private static IPlanner createPlanner(Date startDate, long period) {

		IPlanner result = null;

		// Si el periodo es menor o igual a 0, significa
		// que es un planificador por fecha.
		if (period <= 0) {

			PlannerDate planner = new PlannerDate(startDate);
			result = (IPlanner) planner;

		} else {

			PlannerPeriod planner = new PlannerPeriod(period, startDate, null);
			result = (IPlanner) planner;

		}

		return result;

	}

}
