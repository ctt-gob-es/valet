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
 * <b>File:</b><p>es.gob.valet.commons.utils.threads.TimeLimitedThread.java.</p>
 * <b>Description:</b><p>Class that execute in a independent thread a operation with
 * a limitation time.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 26/12/2018.
 */
package es.gob.valet.commons.utils.threads;


/** 
 * <p>Class that execute in a independent thread a operation with
 * a limitation time.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 26/12/2018.
 */
public class TimeLimitedThread extends Thread {

	/**
	 * Attribute that represents the user class that contains the operation to realize.
	 */
	private ATimeLimitedOperation tlo = null;
	
	/**
	 * Constructor method for the class TimeLimitedThread.java. 
	 */
	private TimeLimitedThread() {
		super();
	}

	/**
	 * Constructor method for the class TimeLimitedThread.java.
	 * @param tloParam User class that contains the operation to realize.
	 */
	public TimeLimitedThread(ATimeLimitedOperation tloParam) {
		this();
		this.tlo = tloParam;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {

		try {
			tlo.doOperationThread();
			tlo.setFinishedOperation();
		} catch (InterruptedException e) {

		} catch (Exception e) {
			tlo.setException(e);
			tlo.setFinishedOperation();
		}

	}

}
