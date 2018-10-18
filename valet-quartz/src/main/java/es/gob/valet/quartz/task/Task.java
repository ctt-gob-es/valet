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
 * <b>File:</b><p>es.gob.valet.quartz.task.Task.java.</p>
 * <b>Description:</b><p> Class taht represents a scheduler task and the action that realizes it.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.quartz.task;

import es.gob.valet.exceptions.IValetException;
import es.gob.valet.quartz.job.AbstractValetTaskQuartzJob;
import es.gob.valet.quartz.job.TaskValetException;

/** 
 * <p>Class that represents a scheduler task and the action that realizes it.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/09/2018.
 */
public abstract class Task extends AbstractValetTaskQuartzJob {

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.job.AbstractValetTaskQuartzJob#doTask()
	 */
	@Override
	protected final void doTask() throws TaskValetException {

		initialMessage();

		try {
			doActionOfTheTask();
		} catch (Exception e) {
			throw new TaskValetException(IValetException.COD_185, e.getMessage(), e);
		}

		endMessage();

	}

	/**
	 * Abstract method that must show in the log the initial message for the task.
	 */	
	protected abstract void initialMessage();

	/**
	 * Method that must realize the action of the task.
	 * @throws Exception In case of some error while is running the task.
	 */
	protected abstract void doActionOfTheTask() throws Exception;

	/**
	 * Abstract method that must show in the log the end message for the task.
	 */
	protected abstract void endMessage();

}
