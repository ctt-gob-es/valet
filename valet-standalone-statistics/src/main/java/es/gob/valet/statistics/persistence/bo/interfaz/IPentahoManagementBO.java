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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.bo.interfaz.IPentahoManagementBO.java.</p>
 * <b>Description:</b><p>Interface that publics the operations associated to statistics of system.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 21/10/2019.
 */
package es.gob.valet.statistics.persistence.bo.interfaz;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import es.gob.valet.statistics.ValetStatisticsException;
import es.gob.valet.statistics.persistence.dto.TransactionDTO;
import es.gob.valet.statistics.persistence.dto.ValidationDTO;

/** 
 * <p>Interface that publics the operations associated to statistics of system.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 21/10/2019.
 */
public interface IPentahoManagementBO extends Serializable {

	/**
	 * Constants that represents the separator used in the event file for separating the field identifier and value. 
	 */
	String TOKEN_SEPARATOR = "=";
	/**
	 * Constants that represents the separator used in the event file for separating of audit fields. 
	 */
	String SEPARATOR = ";";

	/**
	 * Attribute that represents the token used to indicate the transaction identifier in the event file. 
	 */
	String TOKEN_ID_TRANSACION = "ID";

	/**
	 * Attribute that represents the token used to indicate the service identifier in the event file. 
	 */
	String TOKEN_ID_SERVICE = "SV";

	/**
	 * Attribute that represents the token used to indicate the trace identifier in the event file. 
	 */
	String TOKEN_OP = "OP";

	/**
	 * Attribute that represents the token used to indicate the open transaction trace. 
	 */
	String OPEN_TRACE_TOKEN = "open";

	/**
	 * Attribute that represents the token used to indicate the close transaction trace. 
	 */
	String CLOSE_TRACE_TOKEN = "close";

	/**
	 * Constant that identifies the 'application identifier' field.
	 */
	String TOKEN_APP_ID = "APPID";

	/**
	 * Constant that identifies the  'delegated application identifier' field.
	 */
	String TOKEN_DEL_APP_ID = "DELAPPID";

	/**
	 * Constant that code of 'Result' field.
	 */
	String TOKEN_RESULT = "RS_RES_CODE";

	/**
	 * Constant that represents the country issuing the TSL.
	 */
	String TOKEN_COUNTRY = "TSL_CR";

	/**
	 *  Attribute that represents the Trust Service Provider in which the certificate was detected.
	 */
	String TOKEN_TSP_NAME = "TSL_TSPNAME";
	/**
	 * Attribute that represents the Trust Service Provider Service in which the certificate was detected.
	 */
	String TOKEN_TSP_SERVICE = "TSL_TSPSERVICENAME";
	/**
	 * Attribute that represents the Trust Service Provider Service Historic in which the certificate was detected.
	 */
	String TOKEN_TSP_SERVICE_HIST = "TSL_TSPSERVICEHISTNAME";

	/**
	 * Method that stores an entity (POJO) into the database.
	 * 
	 * @param listTransactions Parameter that represents transactions.
	 * @param listValidations Parameter that represents validations.
	 * @param dateStart Parameter that represents the process execution date.
	 * @param logFilename Parameter that represents the name of the log file that is being executed.
	 * @throws ValetStatisticsException If the method fails.
	 */
	void saveTransactions(List<TransactionDTO> listTransactions, List<ValidationDTO> listValidations, Date dateStart, String logFilename) throws ValetStatisticsException;

}
