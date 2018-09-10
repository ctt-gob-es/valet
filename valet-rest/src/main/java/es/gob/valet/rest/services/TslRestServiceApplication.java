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
 * <b>File:</b><p>es.gob.valet.rest.TslRestServiceApplication.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>7 ago. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 7 ago. 2018.
 */
package es.gob.valet.rest.services;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/** 
 * <p>Class needed for restful ws server.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 7/8/2018.
 */
public class TslRestServiceApplication extends Application {

	/**
	 * Attribute that represents singletons objects. 
	 */
	private final Set<Object> singletons = new HashSet<Object>();

	/**
	 * Constructor method for the class TslRestServiceApplication.java. 
	 */
	public TslRestServiceApplication() {
		singletons.add(new TslRestService());
	}

	/**
	 * {@inheritDoc}
	 * @see javax.ws.rs.core.Application#getSingletons()
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@Override
	public Set<Object> getSingletons() {
		// CHECKSTYLE:ON
		return singletons;
	}

	/**
	 * {@inheritDoc}
	 * @see javax.ws.rs.core.Application#getClasses()
	 */
	// CHECKSTYLE:OFF -- Checkstyle rule "Design for Extension" is not applied
	// because Restful needs not final access methods.
	@Override
	public Set<Class<?>> getClasses() {
		// CHECKSTYLE:ON
		return null;
	}

}
