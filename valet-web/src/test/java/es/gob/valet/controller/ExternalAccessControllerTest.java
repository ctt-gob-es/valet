package es.gob.valet.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.gob.valet.form.ExternalAccessForm;
import es.gob.valet.rest.controller.ExternalAccessRestController;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(MockitoJUnitRunner.class)
public class ExternalAccessControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@InjectMocks
	private ExternalAccessController externalAccessController;

	
	  @Before
	    public void setUp(){
	    	MockitoAnnotations.initMocks(this);
	    	mockMvc = MockMvcBuilders.standaloneSetup(externalAccessController).build();
	    }

	@Test
	public final void testExternalAccessAdmin() throws Exception {
		// Mock del objeto Model
        Model model = mock(Model.class);
        
        // Ejecutar la prueba de controlador pasando el modelo al controlador real
        String viewName = externalAccessController.externalAccessAdmin(model);

        // Verificar el nombre de la vista devuelta
        assertEquals("fragments/externalAccessAdmin.html", viewName);

        // Verificar que se haya agregado el atributo "externalAccessform" al modelo
        verify(model, times(1)).addAttribute(eq("externalAccessform"), any(ExternalAccessForm.class));
    }

	@Test
	public final void testTryConnModel() {
		// Mock del objeto Model
        Model model = mock(Model.class);
        
        // Ejecutar la prueba de controlador pasando el modelo al controlador real
        String viewName = externalAccessController.tryConnModel(model);

        // Verificar el nombre de la vista devuelta
        assertEquals("modal/externalAccess/externalAccessTryConnModel", viewName);
	}
}
