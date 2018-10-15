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
 * <b>File:</b><p>es.gob.valet.form.TaskForm.java.</p>
 * <b>Description:</b><p>Class that represents the backing form for editing a Task.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>2 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 2 oct. 2018.
 */
package es.gob.valet.form;

import java.util.List;

/** 
 * <p>Class that represents the backing form for editing a Task.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 2 oct. 2018.
 */

public class TaskForm {

	/**
	 * Constructor method for the class TaskForm.java. 
	 */
	public TaskForm() {
		
	}

	/**
	 * Attribute that represents the object ID of Task.
	 */
	private Long idTask;

	/**
	 * Attribute that represents the list of planner types.
	 */
	private List<ConstantsForm> listPlannerType;

	/**
	 * Attribute that represents type of planners.
	 */
	private String plannerType;
	

	/**
	 * Attribute that represents the ID of planner type.
	 */
	private Long idPlannerType;
	
	/**
	 * Attribute that represents the ID of planner.
	 */
	private Long idPlanner;
	
	/**
	 * Attribute that represents the name of the task.
	 */
	private String name;
	/**
	 * Attribute that represents the hours associated to a period.
	 */
	private Long hourPeriod;
	/**
	 * Attribute that represents the minutes associated to a period.
	 */
	private Long minutePeriod;
	/**
	 * Attribute that represents the seconds associated to a period.
	 */
	private Long secondPeriod;
	
	/**
	 * Attribute that indicates if the task is enabled or not.
	 */
	private  Boolean isEnabled = false;

	
	/**
	 * Attribute the init date of the planner String.
	 */
	private String initDayString;
	
/**
 * Attribute that represents the variable where the ok messages will be stored.
 */
	private String msgOk;
	
	/**
	 * Attribute that represents the variable where the error messages will be stored.
	 */
	private String error;

	/**
	 * Gets the value of the attribute {@link #idTask}.
	 * @return the value of the attribute {@link #idTask}.
	 */

	public Long getIdTask() {
		return idTask;
	}

	/**
	 * Sets the value of the attribute {@link #idTask}.
	 * @param idTaskParam The value for the attribute {@link #idTask}.
	 */
	public void setIdTask(Long idTaskParam) {
		this.idTask = idTaskParam;
	}

	/**
	 * Gets the value of the attribute {@link #listPlannerType}.
	 * @return the value of the attribute {@link #listPlannerType}.
	 */

	public List<ConstantsForm> getListPlannerType() {
		return listPlannerType;
	}

	/**
	 * Sets the value of the attribute {@link #listPlannerType}.
	 * @param listPlannerTypeParam The value for the attribute {@link #listPlannerType}.
	 */
	public void setListPlannerType(List<ConstantsForm> listPlannerTypeParam) {
		this.listPlannerType = listPlannerTypeParam;
	}

	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */

	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the attribute {@link #name}.
	 * @param nameParam The value for the attribute {@link #name}.
	 */
	public void setName(String nameParam) {
		this.name = nameParam;
	}



	/**
	 * Gets the value of the attribute {@link #hourPeriod}.
	 * @return the value of the attribute {@link #hourPeriod}.
	 */

	public Long getHourPeriod() {
		return hourPeriod;
	}

	/**
	 * Sets the value of the attribute {@link #hourPeriod}.
	 * @param hourPeriodParam The value for the attribute {@link #hourPeriod}.
	 */
	public void setHourPeriod(Long hourPeriodParam) {
		this.hourPeriod = hourPeriodParam;
	}

	/**
	 * Gets the value of the attribute {@link #minutePeriod}.
	 * @return the value of the attribute {@link #minutePeriod}.
	 */
	public Long getMinutePeriod() {
		return minutePeriod;
	}

	/**
	 * Sets the value of the attribute {@link #minutePeriod}.
	 * @param minutePeriodParam The value for the attribute {@link #minutePeriod}.
	 */
	public void setMinutePeriod(Long minutePeriodParam) {
		this.minutePeriod = minutePeriodParam;
	}

	/**
	 * Gets the value of the attribute {@link #secondPeriod}.
	 * @return the value of the attribute {@link #secondPeriod}.
	 */
	public Long getSecondPeriod() {
		return secondPeriod;
	}

	/**
	 * Sets the value of the attribute {@link #secondPeriod}.
	 * @param secondPeriodParam The value for the attribute {@link #secondPeriod}.
	 */
	public void setSecondPeriod(Long secondPeriodParam) {
		this.secondPeriod = secondPeriodParam;
	}

		
	
	/**
	 * Gets the value of the attribute {@link #isEnabled}.
	 * @return the value of the attribute {@link #isEnabled}.
	 */
	public Boolean getIsEnabled() {
		return isEnabled;
	}

	
	/**
	 * Sets the value of the attribute {@link #isEnabled}.
	 * @param isEnabledParam The value for the attribute {@link #isEnabled}.
	 */
	public void setIsEnabled(Boolean isEnabledParam) {
		this.isEnabled = isEnabledParam;
	}

	/**
	 * Gets the value of the attribute {@link #plannerType}.
	 * @return the value of the attribute {@link #plannerType}.
	 */
	
	public String getPlannerType() {
		return plannerType;
	}

	
	/**
	 * Sets the value of the attribute {@link #plannerType}.
	 * @param plannerTypeParam The value for the attribute {@link #plannerType}.
	 */
	public void setPlannerType(String plannerTypeParam) {
		this.plannerType = plannerTypeParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #idPlannerType}.
	 * @return the value of the attribute {@link #idPlannerType}.
	 */

	public Long getIdPlannerType() {
		return idPlannerType;
	}

	
	/**
	 * Sets the value of the attribute {@link #idPlannerType}.
	 * @param idPlannerTypeParam The value for the attribute {@link #idPlannerType}.
	 */
	public void setIdPlannerType(Long idPlannerTypeParam) {
		this.idPlannerType = idPlannerTypeParam;
	}


	
	/**
	 * Gets the value of the attribute {@link #idPlanner}.
	 * @return the value of the attribute {@link #idPlanner}.
	 */
	public Long getIdPlanner() {
		return idPlanner;
	}

	
	/**
	 * Sets the value of the attribute {@link #idPlanner}.
	 * @param idPlannerParam The value for the attribute {@link #idPlanner}.
	 */
	public void setIdPlanner(Long idPlannerParam) {
		this.idPlanner = idPlannerParam;
	}


	
	
	/**
	 * Gets the value of the attribute {@link #error}.
	 * @return the value of the attribute {@link #error}.
	 */
	public String getError() {
		return error;
	}

	
	/**
	 * Sets the value of the attribute {@link #error}.
	 * @param errorParam The value for the attribute {@link #error}.
	 */
	public void setError(String errorParam) {
		this.error = errorParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #initDayString}.
	 * @return the value of the attribute {@link #initDayString}.
	 */
	public String getInitDayString() {
		return initDayString;
	}

	
	/**
	 * Sets the value of the attribute {@link #initDayString}.
	 * @param initDayStringParam The value for the attribute {@link #initDayString}.
	 */
	public void setInitDayString(String initDayStringParam) {
		this.initDayString = initDayStringParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #msgOk}.
	 * @return the value of the attribute {@link #msgOk}.
	 */
	public String getMsgOk() {
		return msgOk;
	}

	
	/**
	 * Sets the value of the attribute {@link #msgOk}.
	 * @param msgOkParam The value for the attribute {@link #msgOk}.
	 */
	public void setMsgOk(String msgOkParam) {
		this.msgOk = msgOkParam;
	}


	
}
