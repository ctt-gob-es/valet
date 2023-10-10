package es.gob.valet.rest.controller;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.gob.valet.commons.utils.UtilsDate;
import es.gob.valet.form.ExternalAccessForm;
import es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.service.ExternalAccessService;
import es.gob.valet.service.ifaces.IExternalAccessService;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class ExternalAccessRestControllerTest {


	@Autowired
	private MockMvc mockmvc;
	
	@Mock
	private ExternalAccessService externalAccessService;

    @Mock
    private IExternalAccessService iExternalAccessService;

    @Autowired
    private ObjectMapper objectMapper;
	
    @InjectMocks
    private ExternalAccessRestController externalAccessRestController;

    
    @Before
    public void setUp(){
    	MockitoAnnotations.initMocks(this);
        mockmvc = MockMvcBuilders.standaloneSetup(externalAccessRestController).build();
        objectMapper = new ObjectMapper();
    }
    
	 @Test
	  public void testLoadExternalAccessBySearch() throws Exception {
			ExternalAccess externalAccess = new ExternalAccess();
   
			ExternalAccessForm externalAccessForm = new ExternalAccessForm();
	        externalAccessForm.setDateFrom("01/01/1992 00:00:00");
	        externalAccessForm.setDateTo("30/12/9999 00:00:00");
	        externalAccessForm.setStateConn(Boolean.TRUE);
	        externalAccessForm.setUrl("http://example.com");
	        
	        externalAccess.setStateConn(externalAccessForm.getStateConn());
			externalAccess.setUrl(externalAccessForm.getUrl());
			
	        MvcResult result = mockmvc.perform(MockMvcRequestBuilders.post("/externalAccessDatatable")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(externalAccessForm))
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.data").isArray())
	                .andReturn();
			Mockito.verify(iExternalAccessService).getAllListDTOByFilter(externalAccess, 
					UtilsDate.transformDate(externalAccessForm.getDateFrom(), 
							UtilsDate.FORMAT_DATE_TIME_STANDARD) , 
					UtilsDate.transformDate(externalAccessForm.getDateTo(), 
							UtilsDate.FORMAT_DATE_TIME_STANDARD));
			assertEquals(200, result.getResponse().getStatus());

	    }
	 
	 @Test
	  public void testLoadExternalAccessBySearchErrorDates() throws Exception {
			ExternalAccess externalAccess = new ExternalAccess();
  
			ExternalAccessForm externalAccessForm = new ExternalAccessForm();
	        externalAccessForm.setDateTo("01/01/1992 00:00:00");
	        externalAccessForm.setDateFrom("30/12/9999 00:00:00");
	        externalAccessForm.setStateConn(Boolean.TRUE);
	        externalAccessForm.setUrl("http://example.com");
	        
	        externalAccess.setStateConn(externalAccessForm.getStateConn());
			externalAccess.setUrl(externalAccessForm.getUrl());
			
	        MvcResult result = mockmvc.perform(MockMvcRequestBuilders.post("/externalAccessDatatable")
	                .contentType(MediaType.APPLICATION_JSON)
	                .content(objectMapper.writeValueAsString(externalAccessForm))
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk())
	                .andReturn();
			assertEquals(200, result.getResponse().getStatus());
			String respuesta = objectMapper.readTree(result.getResponse().getContentAsString()).get("error").asText();
		    assertEquals("La fecha 'desde' debe ser anterior a la fecha 'hasta'.", respuesta);


	    }

	@Test
	public void testLoadExternalAccessInicial() throws Exception {
		DataTablesInput input = new DataTablesInput();

        // Realiza la solicitud GET a /externalAccessDatatableInicial
        MvcResult result = mockmvc.perform(MockMvcRequestBuilders.get("/externalAccessDatatableInicial")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
		assertEquals(200, result.getResponse().getStatus());

	}

	@Test
	public void testTryConnsError() throws Exception {
		// Simulamos una lista de IDs de ejemplo
	    List<Long> ids = Arrays.asList(1L, 2L, 3L);

	    // Realiza la solicitud POST a /tryConns
	    MvcResult result = mockmvc.perform(MockMvcRequestBuilders.post("/tryConns")
	            .contentType(MediaType.APPLICATION_JSON)
	            .param("ids[]", ids.stream().map(String::valueOf).toArray(String[]::new)) 
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn();

		assertEquals(200, result.getResponse().getStatus());
	    assertEquals("[]", result.getResponse().getContentAsString());
	}
	
	@Test
	public void testTryConns() throws Exception {
		// Simulamos una lista de IDs de ejemplo
	    List<Long> ids = Arrays.asList(1L);

	 // Simula el comportamiento del servicio para devolver un objeto ExternalAccess
        ExternalAccess expectedExternalAccess = new ExternalAccess(); // Crea un objeto ExternalAccess de ejemplo
        expectedExternalAccess.setIdUrl(1L);
        expectedExternalAccess.setStateConn(Boolean.TRUE);
        expectedExternalAccess.setUrl("http://example.com");

        Mockito.when(iExternalAccessService.getUrlDataById(1L)).thenReturn(expectedExternalAccess);
        Mockito.when(iExternalAccessService.getExternalAccessTestConnAndSave(expectedExternalAccess.getUrl(), expectedExternalAccess.getOriginUrl(), null)).thenReturn(expectedExternalAccess);

	    // Realiza la solicitud POST a /tryConns
	    MvcResult result = mockmvc.perform(MockMvcRequestBuilders.post("/tryConns")
	            .contentType(MediaType.APPLICATION_JSON)
	            .param("ids[]", ids.stream().map(String::valueOf).toArray(String[]::new)) 
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn();

		assertEquals(200, result.getResponse().getStatus());
		// Verifica que el contenido de la respuesta no sea nulo ni esté vacío
	    String content = result.getResponse().getContentAsString();
	    assertNotNull(content);
	    assertFalse(content.isEmpty());

	    // Define la representación JSON esperada como una cadena
	    String expectedJson = "[{\"listUrlDistributionPointCRLResult\":[],\"listUrlDistributionPointDPResult\":[],\"listUrlDistributionPointOCSPResult\":[],\"listUrlIssuerResult\":[],\"listExternalAccessResult\":[],\"idCountryRegion\":null,\"idUrl\":1,\"url\":\"http://example.com\",\"originUrl\":null,\"stateConn\":true,\"lastConn\":null,\"messageError\":\"\"}]";

	    // Compara la representación JSON esperada con la respuesta real
	    assertEquals(expectedJson, content);
	}
	
	@Test
	public void testTryConnsStateFalse() throws Exception {
		// Simulamos una lista de IDs de ejemplo
	    List<Long> ids = Arrays.asList(1L);

	 // Simula el comportamiento del servicio para devolver un objeto ExternalAccess
        ExternalAccess expectedExternalAccess = new ExternalAccess(); // Crea un objeto ExternalAccess de ejemplo
        expectedExternalAccess.setIdUrl(1L);
        expectedExternalAccess.setStateConn(Boolean.FALSE);
        expectedExternalAccess.setUrl("http://example.com");

        Mockito.when(iExternalAccessService.getUrlDataById(1L)).thenReturn(expectedExternalAccess);
        Mockito.when(iExternalAccessService.getExternalAccessTestConnAndSave(expectedExternalAccess.getUrl(), expectedExternalAccess.getOriginUrl(), null)).thenReturn(expectedExternalAccess);

	    // Realiza la solicitud POST a /tryConns
	    MvcResult result = mockmvc.perform(MockMvcRequestBuilders.post("/tryConns")
	            .contentType(MediaType.APPLICATION_JSON)
	            .param("ids[]", ids.stream().map(String::valueOf).toArray(String[]::new)) 
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn();

		assertEquals(200, result.getResponse().getStatus());
		// Verifica que el contenido de la respuesta no sea nulo ni esté vacío
	    String content = result.getResponse().getContentAsString();
	    assertNotNull(content);
	    assertFalse(content.isEmpty());

	    // Define la representación JSON esperada como una cadena
	    String expectedJson = "[{\"listUrlDistributionPointCRLResult\":[],\"listUrlDistributionPointDPResult\":[],\"listUrlDistributionPointOCSPResult\":[],\"listUrlIssuerResult\":[],\"listExternalAccessResult\":[],"
	    		+ "\"idCountryRegion\":null,\"idUrl\":1,\"url\":\"http://example.com\",\"originUrl\":null,\"stateConn\":false,\"lastConn\":null,\"messageError\":\"Error Desconocido\"}]";

	    // Compara la representación JSON esperada con la respuesta real
	    assertEquals(expectedJson, content);
	}
	
	@Test
	public void testTryConnsStateNull() throws Exception {
		// Simulamos una lista de IDs de ejemplo
	    List<Long> ids = Arrays.asList(1L);

	 // Simula el comportamiento del servicio para devolver un objeto ExternalAccess
        ExternalAccess expectedExternalAccess = new ExternalAccess(); // Crea un objeto ExternalAccess de ejemplo
        expectedExternalAccess.setIdUrl(1L);
        expectedExternalAccess.setUrl("http://example.com");

        Mockito.when(iExternalAccessService.getUrlDataById(1L)).thenReturn(expectedExternalAccess);
        Mockito.when(iExternalAccessService.getExternalAccessTestConnAndSave(expectedExternalAccess.getUrl(), expectedExternalAccess.getOriginUrl(), null)).thenReturn(expectedExternalAccess);

	    // Realiza la solicitud POST a /tryConns
	    MvcResult result = mockmvc.perform(MockMvcRequestBuilders.post("/tryConns")
	            .contentType(MediaType.APPLICATION_JSON)
	            .param("ids[]", ids.stream().map(String::valueOf).toArray(String[]::new)) 
	            .accept(MediaType.APPLICATION_JSON))
	            .andExpect(MockMvcResultMatchers.status().isOk())
	            .andReturn();

		assertEquals(200, result.getResponse().getStatus());
		// Verifica que el contenido de la respuesta no sea nulo ni esté vacío
	    String content = result.getResponse().getContentAsString();
	    assertNotNull(content);
	    assertFalse(content.isEmpty());

	    // Define la representación JSON esperada como una cadena
	    String expectedJson = "[{\"listUrlDistributionPointCRLResult\":[],\"listUrlDistributionPointDPResult\":[],\"listUrlDistributionPointOCSPResult\":[],\"listUrlIssuerResult\":[],\"listExternalAccessResult\":[],"
	    		+ "\"idCountryRegion\":null,\"idUrl\":1,\"url\":\"http://example.com\",\"originUrl\":null,\"stateConn\":false,\"lastConn\":null,\"messageError\":\"Error Desconocido\"}]";

	    // Compara la representación JSON esperada con la respuesta real
	    assertEquals(expectedJson, content);
	}

}
