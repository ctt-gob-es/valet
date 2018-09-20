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
 * <b>File:</b><p>es.gob.valet.tasks.FindNewTSLRevisionsTask.java.</p>
 * <b>Description:</b><p> Class that checks the new versions of TSLs.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.tasks;

import java.util.Calendar;
import java.util.Map;

import org.apache.log4j.Logger;

import es.gob.valet.i18n.ILogMessages;
import es.gob.valet.i18n.Language;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.quartz.task.Task;

/** 
 * <p>Class that checks the new versions of TSLs.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 18/09/2018.
 */
public class FindNewTSLRevisionsTask extends Task {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(FindNewTSLRevisionsTask.class);

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#initialMessage()
	 */
	@Override
	protected final void initialMessage() {
		LOGGER.info(Language.getFormatResWebValet(ILogMessages.TASK_FIND_NEW_TSL_REV_INIT_MSG, new Object[ ] { }));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#doActionOfTheTask()
	 */
	@Override
	protected final void doActionOfTheTask() throws Exception {

		// TODO: Añadir funcionalidad para la tarea.
		LOGGER.info("#######################################");
		LOGGER.info("Ejecución de la tarea -> " + Calendar.getInstance().getTime());
		LOGGER.info("#######################################");

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#endMessage()
	 */
	@Override
	protected final void endMessage() {
		LOGGER.info(Language.getFormatResWebValet(ILogMessages.TASK_FIND_NEW_TSL_REV_END_MSG, new Object[ ] { }));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.job.AbstractValetTaskQuartzJob#prepareParametersForTheTask(java.util.Map)
	 */
	@Override
	protected final void prepareParametersForTheTask(Map<String, Object> dataMap) throws TaskValetException {
		return;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.job.AbstractValetTaskQuartzJob#getDataResult()
	 */
	@Override
	protected final Map<String, Object> getDataResult() throws TaskValetException {
		return null;
	}

}
