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
 * <b>File:</b><p>es.gob.valet.quartz.job.AbstractValetTaskQuartzJob.java.</p>
 * <b>Description:</b><p>Class that represents a scheduler task in valET. This class must be extends
 * for all the scheduler task classes in valET.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 18/10/2018.
 */
package es.gob.valet.quartz.job;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IQuartzGeneralMessages;
import es.gob.valet.quartz.scheduler.AbstractQuartzScheduler;

/**
 * <p>Class that represents a scheduler task in valET. This class must be extends
 * for all the scheduler task classes in valET.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 18/10/2018.
 */
public abstract class AbstractValetTaskQuartzJob implements Job {

	/**
	 * Attribute that represents the task name.
	 */
	private transient String taskName;

	/**
	 * Constant attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(AbstractValetTaskQuartzJob.class);

	/**
	 * {@inheritDoc}
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public final void execute(final JobExecutionContext jobExecContext) throws JobExecutionException {

		try {

			List<JobExecutionContext> jobs = jobExecContext.getScheduler().getCurrentlyExecutingJobs();
			for (JobExecutionContext job: jobs) {
				if (job.getTrigger().equals(jobExecContext.getTrigger()) && !job.getFireInstanceId().equals(jobExecContext.getFireInstanceId())) {
					String keyName = jobExecContext.getJobDetail().getKey().getName();
					LOGGER.warn(Language.getFormatResQuartzGeneral(IQuartzGeneralMessages.LOGMQ32, new Object[ ] { keyName }));
					return;
				}

			}

			// Se preparan los datos necesarios para la ejecución de la tarea.
			JobDataMap jobDataMap = jobExecContext.getJobDetail().getJobDataMap();
			// Almacenamos el nombre de la tarea quitándole la cadena de texto
			// "-job"
			taskName = jobExecContext.getJobDetail().getKey().getName();
			if (taskName.endsWith(AbstractQuartzScheduler.HYPHEN + AbstractQuartzScheduler.JOB)) {
				int index = taskName.lastIndexOf(AbstractQuartzScheduler.HYPHEN + AbstractQuartzScheduler.JOB);
				taskName = taskName.substring(0, index);
			}
			// Si hay datos, la tarea deberá recogerlos...
			if (jobDataMap.getWrappedMap() != null) {
				prepareParametersForTheTask(jobDataMap.getWrappedMap());
			}

			// Se ejecuta la tarea.
			doTask();

			// Se guardan los datos de la ejecución de la tarea.
			jobDataMap.clear();
			Map<String, Object> dataResult = getDataResult();
			if (dataResult != null) {
				jobDataMap.putAll(dataResult);
			}

		} catch (TaskValetException e) {

			LOGGER.error(Language.getFormatResQuartzGeneral(IQuartzGeneralMessages.LOGMQ00, new Object[ ] { jobExecContext.getJobDetail().getKey().getName() }));
			throw new JobExecutionException(e);

		} catch (SchedulerException e) {
			LOGGER.error(Language.getResQuartzGeneral(IQuartzGeneralMessages.LOGMQ33));
			throw new JobExecutionException(e);

		}

	}

	/**
	 * Method that get from the map parameter, the necessary data for the execution of the task.
	 * @param dataMap Map with the data for the task. Can be null.
	 * @throws TaskValetException In case of some error preparing the parameters for the task.
	 */
	protected abstract void prepareParametersForTheTask(Map<String, Object> dataMap) throws TaskValetException;

	/**
	 * Method that execute the task.
	 * @throws TaskValetException In case of some error executing the task.
	 */
	protected abstract void doTask() throws TaskValetException;

	/**
	 * Method that returns in a map all the necessary data for the next execution of the task.
	 * @return Map with the data for the next execution of the task.
	 * @throws TaskValetException In case of some error setting the data for the next execution of the task.
	 */
	protected abstract Map<String, Object> getDataResult() throws TaskValetException;

	/**
	 * Gets the value of the attribute {@link #taskName}.
	 * @return the value of the attribute {@link #taskName}.
	 */
	public final String getTaskName() {
		return taskName;
	}

}
