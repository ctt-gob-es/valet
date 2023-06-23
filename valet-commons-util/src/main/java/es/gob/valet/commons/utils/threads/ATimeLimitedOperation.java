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
 * <b>File:</b><p>es.gob.valet.commons.utils.threads.ATimeLimitedOperation.java.</p>
 * <b>Description:</b><p>Abstract class that defines a operation to execute in other
 * thread with a time limitation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 22/06/2023.
 */
package es.gob.valet.commons.utils.threads;

/**
 * <p>Abstract class that defines a operation to execute in other
 * thread with a time limitation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 22/06/2023.
 */
public abstract class ATimeLimitedOperation {

	/**
	 * Attribute that represents the thread which run the operation.
	 */
	private TimeLimitedThread tlt = null;

	/**
	 * Attribute that represents an exception captured while running the operation.
	 */
	private Exception excep = null;

	/**
	 * Attribute that represents if the operation has been finished.
	 */
	private boolean operationFinished = false;

	/**
	 * Attribute that represents if the execution thread must be interrupted.
	 */
	private boolean interruptThread = false;

	/**
	 * Attribute that represents the number of millisecond how the max time for running
	 * the operation.
	 */
	private int millisecondsTime = 0;

	/**
	 * Method called by the thread to set the operation how finished.
	 */
	protected final void setFinishedOperation() {
		this.operationFinished = true;
	}

	/**
	 * Method to test if the operation has been finished.
	 * @return <i>true</i> if the operation has been finished, otherwise <i>false</i>.
	 */
	public final boolean isOperationFinished() {
		return operationFinished;
	}

	/**
	 * Method that obtain the max time (in milliseconds) before stop the thread.
	 * @return Max time (milliseconds).
	 */
	public final int getMaxTimeForRunningThread() {
		return millisecondsTime;
	}

	/**
	 * Method to set the max time (in milliseconds) before stop the thread.
	 * If it is less or equal to zero, then there isn't wait time.
	 * @param maxTimeForRunningThread Number of milliseconds.
	 */
	public final void setMaxTimeForRunningThread(int maxTimeForRunningThread) {
		if (maxTimeForRunningThread > 0) {
			millisecondsTime = maxTimeForRunningThread;
		} else {
			millisecondsTime = 0;
		}
	}

	/**
	 * Method to obatin the exception captured while running the operation.
	 * @return The captured exception.
	 */
	public final Exception getException() {
		return excep;
	}

	/**
	 * Method used by the thread to set the exception captured.
	 * @param exc Exception.
	 */
	protected final void setException(Exception exc) {
		this.excep = exc;
	}

	/**
	 * Method that test if the operation finished with a exception.
	 * @return <i>true</i> if the operation finished with a exception, otherwise <i>false</i>.
	 */
	public final boolean isOperationFinishedWithException() {

		boolean result = false;

		if (operationFinished && excep != null) {
			result = true;
		}

		return result;

	}

	/**
	 * Method that start the operation in other thread, except if the
	 * time is less or equal to 0. In that case, the operation will be executed
	 * in this thread.
	 */
	public final void startOperation(){
		init();
		tlt = new TimeLimitedThread(this);
		tlt.start();
	}

	/**
	 * Method that initializes the attributes for run from clean the operation.
	 */
	private void init() {

		tlt = null;
		excep = null;
		operationFinished = false;
		interruptThread = false;

	}

	/**
	 * Method that stop the running thread.
	 */
	@SuppressWarnings("deprecation")
	public void stopTlt() {

		if (tlt != null && tlt.isAlive()) {

			interruptThread = true;
			tlt.interrupt();

			if (tlt != null && tlt.isAlive()) {
				tlt.stop();
			}

		}

	}

	/**
	 * Method that checks whether the thread should continue running.
	 * This method must be called how test in all loops inside the 'doOperationThread' method.
	 * @return <i>true</i> if the operation should continue running, otherwise <i>false</i>.
	 */
	protected final boolean continueRunning() {

		return !interruptThread;

	}

	/**
	 * Abstract method where must be defined the operation to realize.
	 * In the code, in all the loops, is necessary to call the method 'continueRunning'
	 * to test if it is necessary to stop the execution.
	 * @throws Exception Any exception that occurs during the execution
	 * of the operation.
	 */
	protected abstract void doOperationThread() throws Exception;

}
