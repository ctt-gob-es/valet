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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.Planner.java.</p>
 * <b>Description:</b><p>Class that represents the representation of the <i>PLANNER</i> database table as a Plain Old Java Object.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>2 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 2 oct. 2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import es.gob.valet.commons.utils.NumberConstants;


/** 
 * <p>Class that represents the representation of the <i>PLANNER</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 2 oct. 2018.
 */
@Entity
@Table(name = "PLANNER")
public class Planner implements Serializable {

	/**
	 * Attribute that represents the class serial version.
	 */
	private static final long serialVersionUID = -2393187684085816603L;
	/**
	 * Attribute that represents the object ID.
	 */
	private Long idPlanner;

	/**
	 * Attribute that represents the hours associated to a period.
	 */
	private Long hourPeriod;

	/**
	 * Attribute that represents the init date of the planner.
	 */
	private Date initDay;

	/**
	 * Attribute that represents the minutes associated to a period.
	 */
	private Long minutePeriod;

	/**
	 * Attribute that represents the seconds associated to a period.
	 */
	private Long secondPeriod;

	/**
	 * Attribute that represents the type of the planner.
	 */
	private CPlannerType plannerType;

	/**
	 * Attribute that represents the list of associated tasks.
	 */
	private List<Task> listTasks;

	
	/**
	 * Gets the value of the attribute {@link #idPlanner}.
	 * @return the value of the attribute {@link #idPlanner}.
	 */
	@Id
	@Column(name = "ID_PLANNER", unique = true, nullable = false, precision = NumberConstants.NUM19)
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
	 * Gets the value of the attribute {@link #hourPeriod}.
	 * @return the value of the attribute {@link #hourPeriod}.
	 */
	@Column(name = "HOUR_PERIOD")
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
	 * Gets the value of the attribute {@link #initDay}.
	 * @return the value of the attribute {@link #initDay}.
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "INIT_DAY")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	public Date getInitDay() {
		return initDay;
	}

	
	/**
	 * Sets the value of the attribute {@link #initDay}.
	 * @param initDayParam The value for the attribute {@link #initDay}.
	 */
	public void setInitDay(Date initDayParam) {
		this.initDay = initDayParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #minutePeriod}.
	 * @return the value of the attribute {@link #minutePeriod}.
	 */
	@Column(name = "MINUTE_PERIOD")
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
	@Column(name = "SECOND_PERIOD")
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
	 * Gets the value of the attribute {@link #plannerType}.
	 * @return the value of the attribute {@link #plannerType}.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_PLANNER_TYPE", nullable = false)
	public CPlannerType getPlannerType() {
		return plannerType;
	}

	
	/**
	 * Sets the value of the attribute {@link #plannerType}.
	 * @param plannerTypeParam The value for the attribute {@link #plannerType}.
	 */
	public void setPlannerType(CPlannerType plannerTypeParam) {
		this.plannerType = plannerTypeParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #listTasks}.
	 * @return the value of the attribute {@link #listTasks}.
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "X_TASK_PLANNER", joinColumns = { @JoinColumn(name = "ID_PLANNER", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_TASK", nullable = false) })
	public List<Task> getListTasks() {
		return listTasks;
	}

	
	/**
	 * Sets the value of the attribute {@link #listTasks}.
	 * @param listTasksParam The value for the attribute {@link #listTasks}.
	 */
	public void setListTasks(List<Task> listTasksParam) {
		this.listTasks = listTasksParam;
	}


}
