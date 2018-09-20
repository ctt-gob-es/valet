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
 * <b>File:</b><p>es.gob.valet.quartz.scheduler.ValetSchedulerException.java.</p>
 * <b>Description:</b><p> Class that manages exceptions produced by any valET scheduler.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.quartz.scheduler;

import es.gob.valet.exceptions.ValetException;

/** 
 * <p>Class that manages exceptions produced by any valET scheduler.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 18/09/2018.
 */
public class ValetSchedulerException extends ValetException {

	/**
	 * Attribute that represents the serial version UID for the class. 
	 */
	private static final long serialVersionUID = 2057323255044975566L;

	/**
	 * 
	 * Constructor method for the class ValetSchedulerException.java.
	 */
	public ValetSchedulerException() {
		super();
	}

	/**
	 * Constructor method for the class ValetSchedulerException.java.
	 * @param errorCode Error code for the exception. It can be obtained from {@link IExcepcion}.
	 * @param description Description of the exception.
	 */
	public ValetSchedulerException(final String errorCode, final String description) {
		super(errorCode, description);
	}

	/**
	 * Constructor method for the class ValetSchedulerException.java.
	 * @param errorCode Error code for the exception. It can be obtained from {@link IExcepcion}.
	 * @param description Description of the exception.
	 * @param excep Exception object that causes the error.
	 */
	public ValetSchedulerException(final String errorCode, final String description, final Exception excep) {
		super(errorCode, description, excep);
	}

}
