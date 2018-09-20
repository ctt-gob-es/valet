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
 * <b>File:</b><p>es.gob.valet.quartz.scheduler.ProcessTasksScheduler.java.</p>
 * <b>Description:</b><p> Class that implements the scheduler for the processes tasks of the
 * management asynchronous processes module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.quartz.scheduler;

/** 
 * <p>Class that implements the scheduler for the processes tasks of the
 * management asynchronous processes module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 18/09/2018.
 */
public final class ProcessTasksScheduler extends AbstractValetNonClusteredQuartzScheduler {

	/**
	 * Attribute that represents the singleton of the class.
	 */
	private static ProcessTasksScheduler singleton;

	/**
	 * Constant attribute that represents the name of the group for the scheduler.
	 */
	private static final String SCHEDULER_GROUP = "TASKS_GROUP_MPM_MODULE";

	/**
	 * Method that returns the singleton of the class.
	 * @return Singleton of the class.
	 */
	public static ProcessTasksScheduler getInstance() {

		if (ProcessTasksScheduler.singleton == null) {
			ProcessTasksScheduler.singleton = new ProcessTasksScheduler();
		}
		return ProcessTasksScheduler.singleton;

	}

	/**
	 * Constructor method for the class ProcessTasksScheduler.java.
	 */
	private ProcessTasksScheduler() {
		startScheduler();
	}

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.scheduler.AbstractQuartzScheduler#getSchedulerGroup()
	 */
	@Override
	protected String getSchedulerGroup() {
		return ProcessTasksScheduler.SCHEDULER_GROUP;
	}

}
