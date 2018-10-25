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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.services.ifaces.IPlanner.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer related to Planner.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>03/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 25/10/2018.
 */
package es.gob.valet.persistence.configuration.services.ifaces;

import es.gob.valet.persistence.configuration.model.entity.Planner;

/**
 * <p>Interface that provides communication with the operations of the persistence layer related to Planner.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 25/10/2018.
 */
public interface IPlannerService {

	/**
	 *	Method to obtain the list of planners associated with the task.
	 * @param idTask Parameter that represents ID of task.
	 * @return List of planners.
	 */
	Iterable<Planner> getListPlannersByTask(Long idTask);

	/**
	 * Method that obtains a planner by its identifier.
	 * @param idPlanner The planner identifier.
	 * @return {@link Planner} an object that represents the planner.
	 */
	Planner getPlannerById(Long idPlanner);

	/**
	 * Method that saves planner.
	 * @param planner Planner to update.
	 * @return {@link Planner} an object that represents the Planner.
	 */
	Planner savePlanner(Planner planner);

}
