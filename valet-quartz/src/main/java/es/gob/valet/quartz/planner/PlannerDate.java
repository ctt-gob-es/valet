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
 * <b>File:</b><p>es.gob.valet.quartz.planner.PlannerDate.java.</p>
 * <b>Description:</b><p>Class that defines the information of a planner from a date.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.quartz.planner;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.model.entity.CPlannerType;
import es.gob.valet.persistence.configuration.model.entity.Planner;
import es.gob.valet.persistence.configuration.model.utils.PlannerTypeIdConstants;

/**
 * <p>Class that defines the information of a planner from a date.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class PlannerDate implements IPlanner, Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = 2997347004798755836L;

	/**
	 * Attribute that represents the date planner identifier.
	 */
	public static final String DATE_PLANNER = "datePlanner";

	/**
	 * Attribute that represents the object POJO for a planner.
	 */
	private Planner planner;

	/**
	 * Constructor method for the class PlannerDate.java.
	 */
	private PlannerDate() {
		super();
	}

	/**
	 * Constructor method for the class PlannerDate.java.
	 * @param plannerParam Object POJO that represents the planner. If it is null,
	 * initializes a date planner that must be executed yesterday.
	 */
	public PlannerDate(final Planner plannerParam) {
		this();
		planner = plannerParam;
		if (planner == null) {

			planner = new Planner();
			planner.setHourPeriod(0L);
			planner.setMinutePeriod(0L);
			planner.setSecondPeriod(0L);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, NumberConstants.NUM_NEG_1);
			planner.setInitDay(calendar.getTime());

			CPlannerType plannerType = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCPlannerTypeService().getCPlannerTypeById(PlannerTypeIdConstants.PLANNER_TYPE_2_BYDATE);
			planner.setPlannerType(plannerType);

		}
	}

	/**
	 * Constructor method for the class PlannerDate.java.
	 * @param startDate Initialization date.
	 */
	public PlannerDate(final Date startDate) {

		this();
		planner = new Planner();
		planner.setHourPeriod(0L);
		planner.setMinutePeriod(0L);
		planner.setSecondPeriod(0L);
		if (startDate == null) {

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, NumberConstants.NUM_NEG_1);
			planner.setInitDay(calendar.getTime());

		} else {

			planner.setInitDay(startDate);

		}

		CPlannerType plannerType = ManagerPersistenceServices.getInstance().getManagerPersistenceConfigurationServices().getCPlannerTypeService().getCPlannerTypeById(PlannerTypeIdConstants.PLANNER_TYPE_2_BYDATE);
		planner.setPlannerType(plannerType);

	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public final boolean equals(final Object planificador) {

		boolean result = false;

		try {
			if (planificador instanceof PlannerDate) {
				PlannerDate plannerByDate = (PlannerDate) planificador;

				if (plannerByDate.planner.getInitDay().equals(this.planner.getInitDay())) {

					result = true;

				}
			}
		} catch (ClassCastException e) {
			result = false;
		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public final int hashCode() {
		final int prime = NumberConstants.NUM31;
		int result = NumberConstants.NUM1;
		Date fecha = planner.getInitDay();
		result = prime * result + (fecha == null ? 0 : fecha.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanner#getNextExecutionInMilliseconds()
	 */
	@Override
	public final long getNextExecutionInMilliseconds() {

		long result = -1;
		Date startDate = planner.getInitDay();
		if (startDate != null) {
			result = startDate.getTime() - System.currentTimeMillis();
		}
		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanner#getNextExecutionDate()
	 */
	@Override
	public final Date getNextExecutionDate() {

		Date result = null;
		Date startDate = planner.getInitDay();
		if (startDate != null && !startDate.before(Calendar.getInstance().getTime())) {
			result = startDate;
		}
		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanner#getPeriodInMilliSeconds()
	 */
	@Override
	public final long getPeriodInMilliSeconds() {
		return -1;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanner#getIdentifier()
	 */
	@Override
	public final String getIdentifier() {
		Long identifier = planner.getIdPlanner();
		if (identifier != null) {
			return identifier.toString();
		} else {
			return DATE_PLANNER;
		}
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanner#setIdentifier(java.lang.Long)
	 */
	public final void setIdentifier(final Long identifier) {
		planner.setIdPlanner(identifier);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanner#getNumberOfRepetitions()
	 */
	@Override
	public final int getNumberOfRepetitions() {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanner#getPlanner()
	 */
	public final Planner getPlanner() {
		return planner;
	}

}
