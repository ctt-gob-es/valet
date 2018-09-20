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
 * <b>File:</b><p>es.gob.valet.quartz.scheduler.AbstractValetNonClusteredQuartzScheduler.java.</p>
 * <b>Description:</b><p> Class that represents an abstract non clustered quartz scheduler for valET.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.quartz.scheduler;

import org.quartz.Scheduler;

import es.gob.valet.i18n.utils.UtilsTomcat;

/** 
 * <p>Class that represents an abstract non clustered quartz scheduler for valET.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL</p>
 * @version 1.0, 18/09/2018.
 */
public abstract class AbstractValetNonClusteredQuartzScheduler extends AbstractValetQuartzScheduler {

	/**
	 * Constant attribute that represents the path to the properties file with the
	 * properties to define the cluster in a quartz scheduler.
	 */
	private static final String NOCLUSTERQTZ_FILE = "nonClustered-quartz.properties";

	/**
	 * Attribute that represents the singleton non clustered scheduler.
	 */
	private static Scheduler nonClusterSch;

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.scheduler.AbstractQuartzScheduler#initTheSchedulerOnThisNode()
	 */
	@Override
	protected final boolean initTheSchedulerOnThisNode() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.scheduler.AbstractQuartzScheduler#getPathPropertiesFile()
	 */
	@Override
	protected final String getPathPropertiesFile() {
		return UtilsTomcat.createAbsolutePath(UtilsTomcat.getTomcatConfigDir(), AbstractValetNonClusteredQuartzScheduler.NOCLUSTERQTZ_FILE);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.scheduler.AbstractQuartzScheduler#getScheduler()
	 */
	@Override
	protected final Scheduler getScheduler() {
		return AbstractValetNonClusteredQuartzScheduler.nonClusterSch;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.quartz.scheduler.AbstractQuartzScheduler#setScheduler(org.quartz.Scheduler)
	 */
	@Override
	protected final void setScheduler(final Scheduler scheduler) {
		AbstractValetNonClusteredQuartzScheduler.nonClusterSch = scheduler;
	}

}
