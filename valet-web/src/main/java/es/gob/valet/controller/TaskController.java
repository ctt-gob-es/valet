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
 * <b>File:</b><p>es.gob.valet.controller.TaskController.java.</p>
 * <b>Description:</b><p>Class that manages the requests related to the Task's administration.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>2 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 2 oct. 2018.
 */
package es.gob.valet.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.gob.valet.commons.utils.GeneralConstants;
import es.gob.valet.form.ConstantsForm;
import es.gob.valet.form.TaskForm;
import es.gob.valet.i18n.Language;
import es.gob.valet.persistence.configuration.model.entity.CPlannerType;
import es.gob.valet.persistence.configuration.model.entity.Planner;
import es.gob.valet.persistence.configuration.model.entity.Task;
import es.gob.valet.persistence.configuration.services.ifaces.ICPlannerTypeService;
import es.gob.valet.persistence.configuration.services.ifaces.IPlannerService;
import es.gob.valet.persistence.configuration.services.ifaces.ITaskService;

/** 
 * <p>Class that manages the requests related to the Task's administration.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 2 oct. 2018.
 */
@Controller
public class TaskController {

	/**
	 * Constant that represents the parameter 'idTask'.
	 */
	private static final String FIELD_ID_TASK = "idTask";

	/**
	 * Constant that represents the format date.
	 */
	private static final String FORMAT_DATE = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Attribute that represents the service object for acceding to TaskRespository.
	 */
	@Autowired
	private ITaskService taskService;

	/**
	 * Attribute that represents the service object for acceding to PlannerRespository.
	 */
	@Autowired
	private IPlannerService plannerService;
	/**
	 * Attribute that represents the service object for acceding to CPlannerTypeRespository.
	 */
	@Autowired
	private ICPlannerTypeService cplannerTypeService;


	/**
	 * Method that loads the task.
	 * 
	 * @param idTask Parameter that represents ID of task.
	 * @param model Holder object form model attributes.
	 * @return String that represents the name of the view to forward.
	 */
	@RequestMapping(value = "/loadtask", method = RequestMethod.GET)
	public String loadTask(@RequestParam(FIELD_ID_TASK) Long idTask, Model model) {

		TaskForm taskForm = new TaskForm();

		Planner planner = new Planner();

		// se obtiene de persistencia la tarea seleccionada, para obtener el
		// nombre
		Task taskSelected = taskService.getTaskById(idTask);
		taskForm.setIdTask(idTask);
		taskForm.setName(taskSelected.getName());

		// se cargan los tipos de planificadores
		List<ConstantsForm> typePlanners = loadTypePlanner();
		taskForm.setListPlannerType(typePlanners);
	
		
		
		// se obtiene el planificador asociado a la tarea.
		planner = plannerService.getFirstListPlannersByTask(idTask);
		taskForm.setIdPlanner(planner.getIdPlanner());
		//obtenemos el tipo de planificador asociado a la tarea.
		Long idCPlannerType = planner.getPlannerType().getIdPlannerType();
		taskForm.setIdPlannerType(idCPlannerType);
	
		//ConstantsForm typePlannerSelected = mapConstants.get(idCPlannerType);
		Boolean isDisabledPeriod = false;
		if(idCPlannerType.equals(GeneralConstants.PLANNING_TYPE_DAYLY) || idCPlannerType.equals(GeneralConstants.PLANNING_TYPE_DATE)){
			isDisabledPeriod =true;
		}
		
		//se indica si la tarea está habilitada o no.
		taskForm.setIsEnabled(taskSelected.getIsEnabled());
	
		//se obtiene la hora, minutos, segundos asociados al planificador
		taskForm.setHourPeriod(planner.getHourPeriod());
		taskForm.setMinutePeriod(planner.getMinutePeriod());
		taskForm.setSecondPeriod(planner.getSecondPeriod());
		
		//se obtiene fecha de inicio del planificador		
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
		taskForm.setInitDayString(sdf.format(planner.getInitDay()));
		model.addAttribute("typePlanners", typePlanners);
		model.addAttribute("isDisabledPeriod",isDisabledPeriod );
		model.addAttribute("taskform", taskForm);
		
		
		return "fragments/taskadmin.html";

	}

	/**
	 * Method that loads types planners.
	 * @return List of constants that represents the different types of planners.
	 */
	private List<ConstantsForm> loadTypePlanner() {
		List<ConstantsForm> listPlannerType = new ArrayList<ConstantsForm>();
		// obtenemos los tipos de planificadores.
		List<CPlannerType> listCPlannerType = cplannerTypeService.getAllPlannerType();
		for (CPlannerType typePlanner: listCPlannerType) {
			ConstantsForm item = new ConstantsForm(typePlanner.getIdPlannerType(), getConstantsValue(typePlanner.getTokenName()));
			listPlannerType.add(item);
		}
		
		return listPlannerType;
	}

	/**
	 * Method that gets string constant from multilanguage file.
	 * 
	 * @param key Key for getting constant string from multilanguage file.
	 * @return Constants string.
	 */
	private String getConstantsValue(String key) {
		return Language.getResPersistenceConstants(key);
	}
}
