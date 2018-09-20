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
 * <b>File:</b><p>es.gob.valet.quartz.planner.Planner.java.</p>
 * <b>Description:</b><p> Class object for manage planners.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.quartz.planner;

import java.io.Serializable;
import java.util.Date;

/** 
 * <p>Class object for manage planners.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 18/09/2018.
 */
public class Planner implements Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = -5613643857073247136L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idPlanner;

	/**
	 * Attribute that represents the hours associated to a period.
	 */
	private Integer hourPeriod;

	/**
	 * Attribute that represents the init date of the planner.
	 */
	private Date initDay;

	/**
	 * Attribute that represents the minutes associated to a period.
	 */
	private Integer minutePeriod;

	/**
	 * Attribute that represents the seconds associated to a period.
	 */
	private Integer secondPeriod;

	/**
	 * Attribute that represents the type of the planner.
	 */
	private PlannerType plannerType;

	/**
	 * Gets the value of the attribute {@link #idPlanner}.
	 * @return the value of the attribute {@link #idPlanner}.
	 */
	public Long getIdPlanner() {
		return idPlanner;
	}

	/**
	 * Sets the value of the attribute {@link #idPlanner}.
	 * @param pIdPlanner The value for the attribute {@link #idPlanner}.
	 */
	public void setIdPlanner(final Long pIdPlanner) {
		this.idPlanner = pIdPlanner;
	}

	/**
	 * Gets the value of the attribute {@link #hourPeriod}.
	 * @return the value of the attribute {@link #hourPeriod}.
	 */
	public Integer getHourPeriod() {
		return hourPeriod;
	}

	/**
	 * Sets the value of the attribute {@link #hourPeriod}.
	 * @param pHourPeriod The value for the attribute {@link #hourPeriod}.
	 */
	public void setHourPeriod(final Integer pHourPeriod) {
		this.hourPeriod = pHourPeriod;
	}

	/**
	 * Gets the value of the attribute {@link #initDay}.
	 * @return the value of the attribute {@link #initDay}.
	 */
	public Date getInitDay() {
		return initDay;
	}

	/**
	 * Sets the value of the attribute {@link #initDay}.
	 * @param pInitDay The value for the attribute {@link #initDay}.
	 */
	public void setInitDay(final Date pInitDay) {
		this.initDay = pInitDay;
	}

	/**
	 * Gets the value of the attribute {@link #minutePeriod}.
	 * @return the value of the attribute {@link #minutePeriod}.
	 */
	public Integer getMinutePeriod() {
		return minutePeriod;
	}

	/**
	 * Sets the value of the attribute {@link #minutePeriod}.
	 * @param pMinutePeriod The value for the attribute {@link #minutePeriod}.
	 */
	public void setMinutePeriod(final Integer pMinutePeriod) {
		this.minutePeriod = pMinutePeriod;
	}

	/**
	 * Gets the value of the attribute {@link #secondPeriod}.
	 * @return the value of the attribute {@link #secondPeriod}.
	 */
	public Integer getSecondPeriod() {
		return secondPeriod;
	}

	/**
	 * Sets the value of the attribute {@link #secondPeriod}.
	 * @param pSecondPeriod The value for the attribute {@link #secondPeriod}.
	 */
	public void setSecondPeriod(final Integer pSecondPeriod) {
		this.secondPeriod = pSecondPeriod;
	}

	/**
	 * Gets the value of the attribute {@link #plannerType}.
	 * @return the value of the attribute {@link #plannerType}.
	 */
	public PlannerType getPlannerType() {
		return plannerType;
	}

	/**
	 * Sets the value of the attribute {@link #plannerType}.
	 * @param pPlannerType The value for the attribute {@link #plannerType}.
	 */
	public void setPlannerType(final PlannerType pPlannerType) {
		this.plannerType = pPlannerType;
	}
}