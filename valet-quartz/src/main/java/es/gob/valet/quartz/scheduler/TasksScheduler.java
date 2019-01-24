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
 * <b>File:</b><p>es.gob.valet.quartz.scheduler.TasksScheduler.java.</p>
 * <b>Description:</b><p> Class that implements the scheduler for the processes tasks of the
 * management asynchronous processes module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/01/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 24/01/2019.
 */
package es.gob.valet.quartz.scheduler;

/**
 * <p>Class that implements the scheduler for the processes tasks of the
 * management asynchronous processes module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 24/01/2019.
 */
public final class TasksScheduler extends AbstractValetNonClusteredQuartzScheduler {

	/**
	 * Attribute that represents the singleton of the class.
	 */
	private static TasksScheduler singleton;

	/**
	 * Constant attribute that represents the name of the group for the scheduler.
	 */
	private static final String SCHEDULER_GROUP = "TASKS_GROUP_PLANIFICATION_MODULE";

	/**
	 * Method that returns the singleton of the class.
	 * @return Singleton of the class.
	 */
	public static TasksScheduler getInstance() {

		if (TasksScheduler.singleton == null) {
			TasksScheduler.singleton = new TasksScheduler();
		}
		return TasksScheduler.singleton;

	}

	/**
	 * Constructor method for the class TasksScheduler.java.
	 */
	private TasksScheduler() {
		startScheduler();
	}

	/**
	 *
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.scheduler.AbstractQuartzScheduler#getSchedulerGroup()
	 */
	@Override
	protected String getSchedulerGroup() {
		return TasksScheduler.SCHEDULER_GROUP;
	}

}
