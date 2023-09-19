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
 * @version 1.1, 19/09/2023.
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
 * @version 1.1, 19/09/2023.
 */
public interface IPentahoManagementBO extends Serializable {

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
