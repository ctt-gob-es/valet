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
 * <b>File:</b><p>es.gob.valet.quartz.planner.PlanificadorPeriodico.java.</p>
 * <b>Description:</b><p> Class that defines the information of a diary/periodic planner.</p>
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
import es.gob.valet.commons.utils.UtilsFecha;

/** 
 * <p>Class that defines the information of a diary/periodic planner.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 18/09/2018.
 */
public class PlanificadorPeriodico implements IPlanificador, Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = -4656113774614331791L;

	/**
	 * Attribute that represents the period planner identifier. 
	 */
	public static final String PERIOD_PLANNER = "periodPlanner";

	/**
	 * Attribute that represents the daily planner identifier. 
	 */
	public static final String DAILY_PLANNER = "dailyPlanner";

	/**
	 * Attribute that represents the object POJO for a planner.
	 */
	private transient Planner planner;

	/**
	 * Attribute that represents the default planner identifier. 
	 */
	private transient String plannerTypeId = PERIOD_PLANNER;

	/**
	 * Constructor method for the class PlanificadorPeriodico.java.
	 */
	private PlanificadorPeriodico() {
		super();
	}

	/**
	 * Constructor method for the class PlanificadorPeriodico.java.
	 * @param plannerPojo Object POJO that represents the planner. If it is null,
	 * initializes a diary planner that starts at 03:00 (24H).
	 */
	public PlanificadorPeriodico(final Planner plannerPojo) {
		this();
		planner = plannerPojo;
		if (planner == null) {

			planner = new Planner();
			planner.setHourPeriod(Integer.valueOf(NumberConstants.NUM24));
			planner.setMinutePeriod(Integer.valueOf(0));
			planner.setSecondPeriod(Integer.valueOf(0));
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, NumberConstants.NUM3);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			planner.setInitDay(calendar.getTime());

			PlannerType result = new PlannerType();
			result.setIdPlannerType(Long.valueOf(TIPO_PLAN_DIARIO));
			result.setTokenName(PLANNER_TYPE00);

			planner.setPlannerType(result);

		}
	}

	/**
	 * Constructor method for the class PlanificadorPeriodico.java.
	 * @param period Period in milliseconds.
	 * @param startDate Date of initialization.
	 * @param type the planner type {@link PlanificadorPeriodico#DAILY_PLANNER} or {@link PlanificadorPeriodico#PERIOD_PLANNER}
	 * @param isDaily Execute daily
	 */
	public PlanificadorPeriodico(final long period, final Date startDate, final String type, final Boolean isDaily) {

		this();
		createPeriodPlanner(period, startDate, isDaily);
		plannerTypeId = type;

	}

	/**
	 * Constructor method for the class PlanificadorPeriodico.java.
	 * @param period Period in milliseconds.
	 * @param startDate Date of initialization.
	 * @param isDaily Execute daily
	 */
	public PlanificadorPeriodico(final long period, final Date startDate, final Boolean isDaily) {

		this();
		createPeriodPlanner(period, startDate, isDaily);

	}

	/**
	 * Private method to construct the class PlanificadorPeriodico.java.
	 * @param period Period in milliseconds.
	 * @param startDate Date of initialization.
	 * @param isDaily Execute daily
	 */
	private void createPeriodPlanner(final long period, final Date startDate, final Boolean isDaily) {

		planner = new Planner();
		if (period >= 0) {

			long periodSeconds = period / NumberConstants.NUM1000;
			long hours = periodSeconds / NumberConstants.NUM3600;
			long seconds = periodSeconds % NumberConstants.NUM3600;
			long mins = seconds / NumberConstants.NUM60;
			seconds = seconds % NumberConstants.NUM60;
			planner.setHourPeriod(Integer.valueOf(Long.toString(hours)));
			planner.setMinutePeriod(Integer.valueOf(Long.toString(mins)));
			planner.setSecondPeriod(Integer.valueOf(Long.toString(seconds)));

		} else {
			planner.setHourPeriod(Integer.valueOf(NumberConstants.NUM24));
			planner.setMinutePeriod(Integer.valueOf(0));
			planner.setSecondPeriod(Integer.valueOf(0));
		}

		if (startDate == null) {

			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, NumberConstants.NUM3);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			planner.setInitDay(calendar.getTime());

		} else {

			planner.setInitDay(startDate);

		}

		if (isDaily == null || !isDaily) {
			PlannerType result = new PlannerType();
			result.setIdPlannerType(Long.valueOf(TIPO_PLAN_PERIOD));
			result.setTokenName(PLANNER_TYPE01);

			planner.setPlannerType(result);
		} else {
			PlannerType result = new PlannerType();
			result.setIdPlannerType(Long.valueOf(TIPO_PLAN_DIARIO));
			result.setTokenName(PLANNER_TYPE00);

			planner.setPlannerType(result);
		}

	}

	/**
	 * Gets the period in milliseconds.
	 * @return number of milliseconds that define the period.
	 */
	public final long getPeriodo() {
		long hourPeriod = planner.getHourPeriod().longValue();
		long minutePeriod = planner.getMinutePeriod().longValue();
		long secondPeriod = planner.getSecondPeriod().longValue();
		return UtilsFecha.getPeriod(hourPeriod, minutePeriod, secondPeriod);
	}

	/**
	 * Gets the start date of the planner.
	 * @return Start date of the planner.
	 */
	public final Date getInicio() {
		return planner.getInitDay();
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public final boolean equals(final Object planificador) {

		boolean result = false;

		try {
			if (planificador instanceof PlanificadorPeriodico) {
				PlanificadorPeriodico plannerDaily = (PlanificadorPeriodico) planificador;

				if (plannerDaily.getPeriodo() == this.getPeriodo() && plannerDaily.getInicio().equals(this.getInicio())) {
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
		Date inicio = getInicio();
		result = prime * result + (inicio == null ? 0 : inicio.hashCode());
		long periodo = getPeriodo();
		result = prime * result + (int) (periodo ^ periodo >>> NumberConstants.NUM32);
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getNextExecutionInMilliseconds()
	 */
	@Override
	public final long getNextExecutionInMilliseconds() {
		long result = 0;
		Date inicio = getInicio();
		long periodo = getPeriodo();
		Date actualDate = Calendar.getInstance().getTime();
		// Si no hay fecha de inicio, se considera el periodo:
		if (inicio == null) {
			result = periodo;
		}

		// Si la fecha de inicio es anterior a la fecha actual:
		else if (inicio.before(actualDate)) {
			long milliseconds = actualDate.getTime() - inicio.getTime();

			if (periodo > 0) {
				result = milliseconds % periodo;
				result = periodo - result;
			} else {
				result = -1;
			}
		} else // La fecha actual (actualDate) es menor que inicio.
		{
			result = inicio.getTime() - actualDate.getTime();
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getNextExecutionDate()
	 */
	@Override
	public final Date getNextExecutionDate() {

		Date result = null;
		Date actualDate = Calendar.getInstance().getTime();
		Date inicio = getInicio();
		long periodo = getPeriodo();
		// Si no hay fecha de inicio, se toma a partir de la actual, el
		// siguiente periodo.
		if (inicio == null) {

			result = new Date(actualDate.getTime() + periodo);

		} else {

			// Si la fecha de inicio es anterior a la actual, calculamos la
			// próxima según el periodo.
			if (inicio.before(actualDate)) {

				if (periodo > 0) {

					long msBetweenDates = actualDate.getTime() - inicio.getTime();
					long restOfPeriod = msBetweenDates % periodo;
					long toCompletePeriod = periodo - restOfPeriod;
					result = new Date(actualDate.getTime() + toCompletePeriod);

				} else {

					result = null;

				}

			} else {

				result = inicio;

			}

		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getPeriodInMilliSeconds()
	 */
	@Override
	public final long getPeriodInMilliSeconds() {
		return getPeriodo();
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
			return plannerTypeId;
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
		return 0;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.planner.IPlanificador#getPlanner()
	 */
	public final Planner getPlanner() {
		return planner;
	}

}
