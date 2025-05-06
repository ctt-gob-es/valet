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
 * <b>File:</b><p>es.gob.valet.importsls.IAsyncImportService.java.</p>
 * <b>Description:</b><p>Interface with Service responsible for executing the Trusted Service List (TSL) import process asynchronously.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/05/2025.
 */
package es.gob.valet.importsls;

import java.util.concurrent.Callable;

import org.springframework.web.context.request.RequestContextHolder;

/**
 * <p>Interface with Service responsible for executing the Trusted Service List (TSL) import process asynchronously.</p>
 * <b>Project:</b><p>Wrapper for a {@link Callable} that preserves and restores the {@link RequestContextHolder} request attributes when executing code in a separate thread.</p>
 * @version 1.0, 06/05/2025.
 */
public interface IAsyncImportService {
	/**
	 * Executes the TSL import process asynchronously using the configured executor service.
	 * <p>
	 * The method submits a background task that performs the import in three sequential steps:
	 * <ol>
	 *   <li>Disable TSL-related scheduled tasks.</li>
	 *   <li>Import TSL data within a single transaction.</li>
	 *   <li>Re-enable TSL-related tasks.</li>
	 * </ol>
	 * <p>
	 * The method ensures that only one import process runs at a time. It handles specific and generic
	 * exceptions, logs errors, and reloads the TSL cache in case of failure.
	 */
	public void executeProcessImport();
}
