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
 * <b>File:</b><p>es.gob.valet.cache.ifaces.ICacheValetConstants.java.</p>
 * <b>Description:</b><p>Interface that defines the used constants for the differents Cache Valet implementations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>25/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.cache.utils;

/**
 * <p>Interface that defines the used constants for the differents Cache Valet implementations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class CacheValetConstants {

	/**
	 * Constant attribute that represents the key assigned for the actual cache name value.
	 */
	public static final String ACTUAL_CACHE_NAME_TOKEN = "actualCacheName";

	/**
	 * Constant attribute that represents the name of the Java-ConcurrentMap implementation for the Valet Cache.
	 */
	public static final String VALET_CACHE_IMPL_JAVA_CONCURRENT_MAP = "JavaConcurrentMap";

}
