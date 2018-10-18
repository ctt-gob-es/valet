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
 * <b>File:</b><p>es.gob.valet.quartz.planner.IPlanificador.java.</p>
 * <b>Description:</b><p> Interface that defines the methods for the planners.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.quartz.planner;

import java.util.Date;


/** 
 * <p>Interface that defines the methods for the planners.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/09/2018.
 */
public interface IPlanificador {

	/**
	 * Constant attribute that represents the number to identify the daily planner type.
	 */
	int TIPO_PLAN_DIARIO = 0;

	/**
	 * Constant attribute that represents the number to identify the periodic planner type.
	 */
	int TIPO_PLAN_PERIOD = 1;

	/**
	 * Constant attribute that represents the number to identify the planner type by date.
	 */
	int TIPO_PLAN_PFECHA = 2;

	/**
	 * Constant attribute that represents the key value for the planner type 'Diario'.
	 */
	String PLANNER_TYPE00 = "PLANNER_TYPE00";

	/**
	 * Constant attribute that represents the key value for the planner type 'Periódico'.
	 */
	String PLANNER_TYPE01 = "PLANNER_TYPE01";

	/**
	 * Constant attribute that represents the key value for the planner type 'Por Fecha'.
	 */
	String PLANNER_TYPE02 = "PLANNER_TYPE02";

	/**
	 * Method that obtain the milliseconds necessary for the next execution. The method returns a negative number
	 * if the execution date has expired and the task isn't a periodic execution task.
	 * @return the milliseconds necessary for the next execution.
	 */
	long getNextExecutionInMilliseconds();

	/**
	 * Method that obtain the date for the next execution.
	 * @return Date of the next execution. Null if the date has expired and there
	 * isn't a period.
	 */
	Date getNextExecutionDate();

	/**
	 * Method that obtain the number of milliseconds that represents the time period.
	 * @return Number of millisecond of the period. A negative number if there isn't repetitions.
	 */
	long getPeriodInMilliSeconds();

	/**
	 * Method that indicates the number of the repetitions for the execution of the task.
	 * @return Number of repetitions for the task. Zero if is a loop.
	 */
	int getNumberOfRepetitions();

	/**
	 * Method that obtains the unique identifier for this planner.
	 * @return {@link String} with the unique identifier of this planner.
	 */
	String getIdentifier();

	/**
	 * Method that sets the unique identifier for this planner.
	 * @param identifier {@link Long} that represents the unique identifier.
	 */
	void setIdentifier(Long identifier);

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(Object)
	 */
	boolean equals(Object planificador);

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	int hashCode();

	/**
	 * Gets the POJO that represents the planner.
	 * @return POJO that represents the planner.
	 */
	Planner getPlanner();

}
