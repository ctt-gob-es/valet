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
 * <b>File:</b><p>es.gob.valet.importsls.AsyncImport.java.</p>
 * <b>Description:</b><p>Configuration class for asynchronous import operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/05/2025.
 */
package es.gob.valet.importsls;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Configuration class for asynchronous import operations.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 06/05/2025.
 */
@Configuration
public class AsyncImport {
	/**
     * Creates a bean for the ExecutorService that handles asynchronous tasks for the import process.
     * <p>
     * This method creates and configures an ExecutorService using a fixed thread pool with a size of 10 threads.
     * It allows multiple tasks related to the import process to run concurrently, improving performance by 
     * parallelizing operations.
     * </p>
     *
     * @return an ExecutorService with a fixed thread pool of size 10.
     */
	@Bean(name = "executorServiceImport")
	public ExecutorService taskExecutor() {
		return Executors.newFixedThreadPool(10);
	}
}
