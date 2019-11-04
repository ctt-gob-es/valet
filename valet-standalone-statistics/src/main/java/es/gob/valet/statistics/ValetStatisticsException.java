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
 * <b>File:</b><p>es.gob.valet.statistics.ValetStandaloneStatisticsException.java.</p>
 * <b>Description:</b><p>Class that implements Exceptions for this module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 21/10/2019.
 */
package es.gob.valet.statistics;


/** 
 * <p>Class that implements Exceptions for this module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 21/10/2019.
 */
public class ValetStatisticsException extends Exception {

	/**
	 * Attribute that represents the serial version UID . 
	 */
	private static final long serialVersionUID = 2555995187900490870L;

	/**
	 * Constructor method for the class ValetStatisticsException.java. 
	 */
	public ValetStatisticsException() {
		super();
	}

	/**
	 * Constructor method for the class ValetStatisticsException.java.
	 * @param message Error message.
	 * @param cause Error cause.
	 */
	public ValetStatisticsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor method for the class ValetStatisticsException.java.
	 * @param message 
	 */
	public ValetStatisticsException(String message) {
		super(message);
	}
	
	

}
