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
 * <b>File:</b><p>es.gob.valet.tasks.ExternalAccessConnectionTestTask.java.</p>
 * <b>Description:</b><p>Class that contains the task of performing connection tests and storing the result in the database.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>26/07/2023.</p>
 * @author Gobierno de España.
 * @version 1.1, 31/07/2023.
 */
package es.gob.valet.tasks;

import java.util.Calendar;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.quartz.task.Task;
import es.gob.valet.service.ifaces.IExternalAccessService;
import es.gob.valet.spring.config.ApplicationContextProvider;

/**
 * <p>Class that contains the task of performing connection tests and storing the result in the database.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 31/07/2023.
 */
public class ExternalAccessConnectionTestTask extends Task {
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ExternalAccessConnectionTestTask.class);
	
	/**
	 * Attribute that will save the moment the task starts.
	 */
	private static long startOperationTime = 0L;
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#initialMessage()
	 */
	@Override
	protected final void initialMessage() {

		LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.TASK_EXT_ACC_CONN_TEST_INIT_MSG));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#endMessage()
	 */
	@Override
	protected final void endMessage() {
		LOGGER.info(Language.getFormatResWebGeneral(IWebGeneralMessages.TASK_EXT_ACC_CONN_TEST_END_MSG, new Object[ ] { Calendar.getInstance().getTimeInMillis() - startOperationTime }));
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
	
	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#doActionOfTheTask()
	 */
	@Override
	protected final void doActionOfTheTask() throws Exception {
		// Realizamos el test de conexión y actualiazamos el resultado.
		ApplicationContextProvider.getApplicationContext().getBean(IExternalAccessService.class).realizeTestAndUpdateResult();
	}

}
