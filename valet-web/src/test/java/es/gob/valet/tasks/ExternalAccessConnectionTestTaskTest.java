package es.gob.valet.tasks;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;

import static org.mockito.Mockito.*;

import es.gob.valet.i18n.Language;
import es.gob.valet.quartz.job.TaskValetException;
import es.gob.valet.service.ifaces.IExternalAccessService;
import es.gob.valet.spring.config.ApplicationContextProvider;
import es.gob.valet.tasks.ExternalAccessConnectionTestTask;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ApplicationContextProvider.class, ApplicationContext.class})
public class ExternalAccessConnectionTestTaskTest {

	  @InjectMocks
	  private ExternalAccessConnectionTestTask task;
	  
	  @Mock
	  private static ApplicationContextProvider applicationContextProvider;

	    @Before
	    public void setUp() {
	        task = new ExternalAccessConnectionTestTask();
	    }

	    @Test
	    public void testPrepareParametersForTheTask() throws TaskValetException {
	        Map<String, Object> dataMap = mock(Map.class);
	        task.prepareParametersForTheTask(dataMap);
	        
	    }

	    @Test
	    public void testGetDataResult() throws TaskValetException {
	        Map<String, Object> dataResult = task.getDataResult();
	        
	        // Verificar que el resultado sea nulo
	        assertNull(dataResult);
	    }

	     @Ignore
	    public void testDoActionOfTheTask() throws Exception {
	    	
	    	 // Crear un mock de ApplicationContextProvider
	    	ApplicationContext applicationContext = mock(ApplicationContext.class);
	        
	        // Crear un mock de IExternalAccessService
	        IExternalAccessService externalAccessService = mock(IExternalAccessService.class);
	        
	        // Configuramos el comportamiento del mock
	        PowerMockito.mockStatic(ApplicationContextProvider.class);
	        PowerMockito.mockStatic(ApplicationContext.class);
	    	//Configuramos el mock de ApplicationContextProvider para devolver el mock de IExternalAccessService
	        when(ApplicationContextProvider.getApplicationContext())
	            .thenReturn(applicationContext);
	        task.doActionOfTheTask();
	    }
}
