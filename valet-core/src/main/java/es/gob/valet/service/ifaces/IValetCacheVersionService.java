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
 * <b>File:</b><p>es.gob.valet.service.ifaces.IValetCacheVersionService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer in relation of the ValetCacheVersion entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/11/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 07/11/2025.
 */
package es.gob.valet.service.ifaces;

/**
 * <p>Interface that provides communication with the operations of the persistence layer in relation of the ValetCacheVersion entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 10/07/2025.
 */
public interface IValetCacheVersionService {
	/**
	 * Increments the current valet cache version by one and updates its last modification date.
	 * <p>
	 * The updated version is then persisted to the database.
	 */
	void updateCacheVersion();
}
