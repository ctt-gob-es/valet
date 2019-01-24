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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.utils.IPlannerTypeIdConstants.java.</p>
 * <b>Description:</b><p>Interface that contains all the planner types IDs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>22/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 22/01/2019.
 */
package es.gob.valet.persistence.configuration.model.utils;

/**
 * <p>Interface that contains all the planner types IDs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 22/01/2019.
 */
public interface IPlannerTypeIdConstants {

	/**
	 * Constant attribute that represents the ID to identify the Planner Type - Daily.
	 */
	Long PLANNER_TYPE_0_DAILY = 0L;

	/**
	 * Constant attribute that represents the ID to identify the Planner Type - Period.
	 */
	Long PLANNER_TYPE_1_PERIOD = 1L;

	/**
	 * Constant attribute that represents the ID to identify the Planner Type - ByDate.
	 */
	Long PLANNER_TYPE_2_BYDATE = 2L;

}
