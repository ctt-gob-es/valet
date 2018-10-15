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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.Task.java.</p>
 * <b>Description:</b><p>Class that represents the representation of the <i>TASK</i> database table as a Plain Old Java Object.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>2 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 2 oct. 2018.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import es.gob.valet.commons.utils.NumberConstants;


/** 
 * <p>Class that represents the representation of the <i>TASK</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 2 oct. 2018.
 */
@Entity
@Table(name = "TASK")
public class Task implements Serializable {

	/**
	 * Attribute that represents  the serial version UID. 
	 */
	private static final long serialVersionUID = 6787410726809984204L;
	/**
	 * Attribute that represents the object ID.
	 */
	private Long idTask;

	/**
	 * Attribute that represents the name of the class which implements the task.
	 */
	private String implementationClass;

	/**
	 * Attribute that represents the name of the task.
	 */
	private String name;

	/**
	 * Attribute that represents the list of associated planners.
	 */
	private List<Planner> planners;
	
	/**
	 * Attribute that indicates if the task is enabled (true) or not (false).
	 */
	private Boolean isEnabled;

	
	/**
	 * Gets the value of the attribute {@link #idTask}.
	 * @return the value of the attribute {@link #idTask}.
	 */
	@Id
	@Column(name = "ID_TASK", unique = true, nullable = false, precision = NumberConstants.NUM19)
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
	 * Gets the value of the attribute {@link #implementationClass}.
	 * @return the value of the attribute {@link #implementationClass}.
	 */
	@Column(name = "IMPLEMENTATION_CLASS", nullable = false, length = NumberConstants.NUM150)
	public String getImplementationClass() {
		return implementationClass;
	}

	
	/**
	 * Sets the value of the attribute {@link #implementationClass}.
	 * @param implementationClassParam The value for the attribute {@link #implementationClass}.
	 */
	public void setImplementationClass(String implementationClassParam) {
		this.implementationClass = implementationClassParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */
	@Column(name = "NAME", nullable = false, length = NumberConstants.NUM50, unique = true)
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
	 * Gets the value of the attribute {@link #listPlanners}.
	 * @return the value of the attribute {@link #listPlanners}.
	 */
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "X_TASK_PLANNER", joinColumns = { @JoinColumn(name = "ID_TASK", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "ID_PLANNER", nullable = false) })
	public List<Planner> getPlanners() {
		return planners;
	}

	
	/**
	 * Sets the value of the attribute {@link #planners}.
	 * @param plannersParam The value for the attribute {@link #planners}.
	 */
	public void setPlanners(List<Planner> plannersParam) {
		this.planners = plannersParam;
	}


	
	/**
	 * Gets the value of the attribute {@link #isEnabled}.
	 * @return the value of the attribute {@link #isEnabled}.
	 */
	@Column(name = "IS_ENABLED", nullable = false, precision = 1)
	@Type(type = "yes_no")
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


	

}
