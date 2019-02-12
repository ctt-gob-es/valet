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
 * <b>File:</b><p>es.gob.valet.audit.IEventsCollectorConstants.java.</p>
 * <b>Description:</b><p>Interface that defines all the public constants needed to work with
 * audit transactions.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 12/02/2019.
 */
package es.gob.valet.audit;

/**
 * <p>Interface that defines all the public constants needed to work with
 * audit transactions.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 12/02/2019.
 */
public interface IEventsCollectorConstants {

	/**
	 * Constant attribute that represents the id for the rest service: Get TSL Information.
	 */
	int SERVICE_GET_TSL_INFORMATION_ID = 1;

	/**
	 * Constant attribute that represents the id for the rest service: Detect Certificate in TSL and Validate.
	 */
	int SERVICE_DETECT_CERT_IN_TSL_INFO_AND_VALIDATION_ID = 2;

}
