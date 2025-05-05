package es.gob.valet.importsls;

import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.exceptions.ImporTslsException;
import es.gob.valet.tsl.access.TSLManager;

@Service
public class AsyncImporTslService implements IAsyncImportService {
	
	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = Logger.getLogger(AsyncImporTslService.class);

	@Autowired
	@Qualifier("executorServiceImport")
    private ExecutorService executorServiceImport;  // Usamos ExecutorService en lugar de Executor
	
	@Autowired
    private IImporTslService iImporTslService;

    public void executeProcessImport() {
        executorServiceImport.submit(new DelegatingCallable<Void>(() -> {
        	LOGGER.info("Comenzamos con la importación");
        	iImporTslService.setRunning(true);
    		try {
                for (int i = 1; i <= 3; i++) {
                    if (!iImporTslService.isRunning()) {
                        return null;
                    }
                    switch (i) {
                        case NumberConstants.NUM1:
                            iImporTslService.setCurrentStep(NumberConstants.NUM1);
                            iImporTslService.disableTslRelatedTask();
                            break;
                        case NumberConstants.NUM2:
                            iImporTslService.imporTslsUniqueTransaction();
                            break;
                        case NumberConstants.NUM3:
                            iImporTslService.setCurrentStep(NumberConstants.NUM5);
                            iImporTslService.enableTslRelatedTask();
                            break;
                        default:
                            break;
                    }
                }
                LOGGER.info("El proceso de importación ha finalizado con éxito");
            } catch (ImporTslsException e) {
                iImporTslService.setMessageError("El proceso ha fallado en el paso "+ iImporTslService.getCurrentStep());
                iImporTslService.setError(true);
                TSLManager.getInstance().reloadTSLCache();
            } catch (Exception e) {
                LOGGER.error("Se ha producido un fallo desconocido en:", e);
                iImporTslService.setMessageError("El proceso ha fallado en el paso "+ iImporTslService.getCurrentStep());
                iImporTslService.setError(true);
                TSLManager.getInstance().reloadTSLCache();
            } finally {
                iImporTslService.setRunning(false);
            }
            return null;
        }));
    }
}
