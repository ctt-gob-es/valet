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
 * <b>File:</b><p>es.gob.valet.tasks.ReloadCacheTask.java.</p>
 * <b>Description:</b><p>Class that represents a task to reload the cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>04/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 19/09/2023.
 */
package es.gob.valet.tasks;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.RestTasksMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheException;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.quartz.task.Task;
import es.gob.valet.utils.UtilsCache;

/**
 * <p>Class that represents a task to reload the cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 19/09/2023.
 */
public class ReloadCacheTask extends Task {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(ReloadCacheTask.class);

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#initialMessage()
	 */
	@Override
	protected void initialMessage() {
		LOGGER.info(Language.getResRestTasks(RestTasksMessages.RELOAD_CACHE_000));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#doActionOfTheTask()
	 */
	@Override
	protected void doActionOfTheTask() throws Exception {

		try {
			UtilsCache.reloadConfigurationLocalCache(true);
		} catch (ConfigurationCacheException e) {
			// Se produjo un error durante la recarga de la caché.
			LOGGER.error(Language.getFormatResRestTasks(RestTasksMessages.RELOAD_CACHE_002), e);
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.task.Task#endMessage()
	 */
	@Override
	protected void endMessage() {
		LOGGER.info(Language.getResRestTasks(RestTasksMessages.RELOAD_CACHE_001));
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.job.AbstractValetTaskQuartzJob#prepareParametersForTheTask(java.util.Map)
	 */
	@Override
	protected void prepareParametersForTheTask(Map<String, Object> dataMap) throws TaskValetException {
		return;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.job.AbstractValetTaskQuartzJob#getDataResult()
	 */
	@Override
	protected Map<String, Object> getDataResult() throws TaskValetException {
		return null;
	}

}
