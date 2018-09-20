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
 * <b>File:</b><p>es.gob.valet.quartz.planner.PlanificadorPorFecha.java.</p>
 * <b>Description:</b><p> Class that defines the information of a planner from a date.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.quartz.planner;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import es.gob.valet.commons.utils.NumberConstants;

/** 
 * <p>Class that defines the information of a planner from a date.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 18/09/2018.
 */
public class PlanificadorPorFecha implements IPlanificador, Serializable {

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
	private static Planner planner;

	/**
	 * Constructor method for the class PlanificadorPorFecha.java.
	 */
	private PlanificadorPorFecha() {
		super();
	}

	/**
	 * Constructor method for the class PlanificadorPorFecha.java.
	 * @param plannerPojo Object POJO that represents the planner. If it is null,
	 * initializes a date planner that must be executed yesterday.
	 */
	public PlanificadorPorFecha(final Planner plannerPojo) {
		this();
		planner = plannerPojo;
		if (planner == null) {

			planner = new Planner();
			planner.setHourPeriod(0);
			planner.setMinutePeriod(0);
			planner.setSecondPeriod(0);
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, NumberConstants.NUM_NEG_1);
			planner.setInitDay(calendar.getTime());

			PlannerType result = new PlannerType();
			result.setIdPlannerType(Long.valueOf(TIPO_PLAN_PFECHA));
			result.setTokenName(PLANNER_TYPE02);

			planner.setPlannerType(result);

		}
	}

	/**
	 * Constructor method for the class PlanificadorPorFecha.java.
	 * @param startDate Initialization date.
	 */
	public PlanificadorPorFecha(final Date startDate) {

		this();
		planner = new Planner();
		planner.setHourPeriod(0);
		planner.setMinutePeriod(0);
		planner.setSecondPeriod(0);
		if (startDate == null) {

			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_MONTH, NumberConstants.NUM_NEG_1);
			planner.setInitDay(calendar.getTime());

		} else {

			planner.setInitDay(startDate);

		}

		PlannerType result = new PlannerType();
		result.setIdPlannerType(Long.valueOf(TIPO_PLAN_PFECHA));
		result.setTokenName(PLANNER_TYPE02);

		planner.setPlannerType(result);

	}

	/**
	 * Gets the start date of the planner.
	 * @return the start date of the planner.
	 */
	public final Date getFecha() {
		return planner.getInitDay();
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public final boolean equals(final Object planificador) {

		boolean result = false;

		try {
			if (planificador instanceof PlanificadorPorFecha) {
				PlanificadorPorFecha plannerByDate = (PlanificadorPorFecha) planificador;

				if (plannerByDate.getFecha().equals(this.getFecha())) {

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
		Date fecha = getFecha();
		result = prime * result + (fecha == null ? 0 : fecha.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getNextExecutionInMilliseconds()
	 */
	@Override
	public final long getNextExecutionInMilliseconds() {

		long result = -1;
		Date fecha = getFecha();
		if (fecha != null) {
			result = fecha.getTime() - System.currentTimeMillis();
		}
		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getNextExecutionDate()
	 */
	@Override
	public final Date getNextExecutionDate() {

		Date result = Calendar.getInstance().getTime();
		Date fecha = getFecha();
		if (fecha == null || fecha.before(result)) {
			result = null;
		} else {
			result = fecha;
		}
		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getPeriodInMilliSeconds()
	 */
	@Override
	public final long getPeriodInMilliSeconds() {
		return -1;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getIdentifier()
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
	 * @see es.gob.valet.quartz.planner.IPlanificador#setIdentifier(java.lang.Long)
	 */
	public final void setIdentifier(final Long identifier) {
		planner.setIdPlanner(identifier);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getNumberOfRepetitions()
	 */
	@Override
	public final int getNumberOfRepetitions() {
		return 1;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getPlanner()
	 */
	public final Planner getPlanner() {
		return planner;
	}

}
