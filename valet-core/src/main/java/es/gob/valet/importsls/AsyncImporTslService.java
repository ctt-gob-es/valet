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
 * <b>File:</b><p>es.gob.valet.exceptions.AsyncImporTslService.java.</p>
 * <b>Description:</b><p>Class with Service responsible for executing the Trusted Service List (TSL) import process asynchronously.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.1, 21/05/2025.
 */
package es.gob.valet.importsls;

import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.gob.valet.exceptions.ImporTslsException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IWebGeneralMessages;
import es.gob.valet.tsl.access.TSLManager;

/**
 * <p>Class with Service responsible for executing the Trusted Service List (TSL) import process asynchronously.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 21/05/2025.
 */
@Service
public class AsyncImporTslService implements IAsyncImportService {
	
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(AsyncImporTslService.class);

	/**
	 * Executor service for running the TSL import asynchronously.
	 * Injected using the {@code executorServiceImport} qualifier.
	 */
	@Autowired
	@Qualifier("executorServiceImport")
	private ExecutorService executorServiceImport;

	/**
	 * Service that handles the core TSL import logic.
	 */
	@Autowired
	private IImporTslService iImporTslService;

	/**
	 * 
	 * {@inheritDoc}
	 * @see es.gob.valet.importsls.IAsyncImportService#executeProcessImport()
	 */
    public void executeProcessImport() {
        executorServiceImport.submit(new DelegatingCallable<Void>(() -> {
        	iImporTslService.setRunning(true);
    		try {
    			LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP044));
    			iImporTslService.doImport();
                LOGGER.info(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP045));
            } catch (ImporTslsException e) {
                iImporTslService.setMessageError(Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP046, new Object[ ] { iImporTslService.getCurrentStep() }));
                iImporTslService.setError(true);
                TSLManager.getInstance().clearAllCache();
                TSLManager.getInstance().reloadTSLCache();
            } catch (Exception e) {
                LOGGER.error(Language.getResWebGeneral(IWebGeneralMessages.LOG_IMP047), e);
                iImporTslService.setMessageError(Language.getFormatResWebGeneral(IWebGeneralMessages.LOG_IMP046, new Object[ ] { iImporTslService.getCurrentStep() }));
                iImporTslService.setError(true);
                TSLManager.getInstance().clearAllCache();
                TSLManager.getInstance().reloadTSLCache();
            } finally {
                iImporTslService.setRunning(false);
            }
            return null;
        }));
    }
}
