package es.gob.valet.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import org.apache.logging.log4j.core.Core;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.repository.ExternalAccessRepository;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.ExternalAccessTablesRepository;
import es.gob.valet.persistence.configuration.model.specification.ExternalAccessSpecification;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;
import es.gob.valet.service.ExternalAccessService;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreTslMessages;

//@RunWith(MockitoJUnitRunner.class)
@RunWith(PowerMockRunner.class)
@PrepareForTest(Language.class)
public class ExternalAccessServiceTest {

	@InjectMocks
	private ExternalAccessService externalAccessService;

	/**
	 * Attribute that represents the injected interface that provides CRUD
	 * operations for the persistence.
	 */
	@Mock
	private ExternalAccessTablesRepository dtRepository;
	@Mock
	private ExternalAccessRepository externalAccessRepository;

	/**
	 * Attribute that represents the service object for accessing the repository
	 * of region service.
	 */
	@Mock
	private ITslCountryRegionService iTslCountryRegionService;

	/**
	 * Attribute that represents the service object for accessing the repository
	 * of data service.
	 */
	@Mock
	private ITslDataService iTslDataService;

	/**
	 * Attribute that represents the service object for accessing the repository
	 * of country region.
	 */
	@Mock
	private TslCountryRegionRepository tslCountryRegionRepository;

	@Mock
	private ExternalAccessSpecification externalAccessSpecification;

	
	
	private DataTablesInput input;

	private ExternalAccess request;
	private ExternalAccessDTO dto;
	private Date fromDate;
	private Date toDate;

	@Before
	public void setUp() {
		// Crear una entrada DataTablesInput de ejemplo
		input = new DataTablesInput();
		request = new ExternalAccess(); // Se inicializa objeto de prueba
		fromDate = new Date(); // Fecha de inicio de prueba
		toDate = new Date(); // Fecha de fin de prueba
		
		
	}

	@Test
	public void testGetAll() {

		// Crear un DataTablesOutput de ejemplo
		DataTablesOutput<ExternalAccess> expectedOutput = new DataTablesOutput<>();
		// Configurar el comportamiento del mock para que devuelva el resultado
		// esperado
		when(dtRepository.findAll(input)).thenReturn(expectedOutput);

		// Llamar al método que deseas probar
		DataTablesOutput<ExternalAccess> actualOutput = externalAccessService.getAll(input);
		// Verificar que el resultado sea el esperado
		assertEquals(expectedOutput, actualOutput);
		// Verificar que el método dtRepository.findAll(input) fue llamado
		// exactamente una vez
		verify(dtRepository, times(1)).findAll(input);
	}

	@Test
	public final void testGetAllList() {
		// creamos los objetos de búsqueda
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");

		// Creamos los objetos de respuesta
		List<ExternalAccess> expectedList = new ArrayList<>();
		Specification<ExternalAccess> specificationExternalAccess = externalAccessSpecification
				.getExternalAccess(request, fromDate, toDate);
		// Configuramos el comportamiento del mock para que devuelva el
		// resultado esperado
		when(externalAccessRepository.findAll(any(Specification.class))).thenReturn(expectedList);

		// Llamar al método que deseas probar
		List<ExternalAccess> actualList = externalAccessService.getAllList(request, fromDate, toDate);

		// Verificar que el resultado sea el esperado
		assertEquals(expectedList, actualList);

	}

	@Test
	public final void testGetAllListDTOByFilter() {
		// creamos los objetos de búsqueda
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");

		// Creamos los objetos de respuesta
		List<ExternalAccessDTO> expectedList = new ArrayList<>();
		List<ExternalAccess> externalAccessList = new ArrayList<>();
		Specification<ExternalAccess> specificationExternalAccess = externalAccessSpecification
				.getExternalAccess(request, fromDate, toDate);
		externalAccessList.add(request);

		// Configuramos el comportamiento del mock para que devuelva el
		// resultado esperado
		when(externalAccessRepository.findAll(any(Specification.class))).thenReturn(externalAccessList);

		// Llamar al método que deseas probar
		List<ExternalAccessDTO> actualList = externalAccessService.getAllListDTOByFilter(request, fromDate, toDate);

		// Verificar que el resultado sea el esperado
		assertEquals("http://example.com", actualList.get(0).getUrl());

	}

	@Test
	public final void testGetAllListDTO() {
		// creamos los objetos de búsqueda
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");

		// Creamos los objetos de respuesta
		List<ExternalAccess> externalAccessList = new ArrayList<>();
		externalAccessList.add(request);

		// Configuramos el comportamiento del mock para que devuelva el
		// resultado esperado
		when(externalAccessRepository.findAll()).thenReturn(externalAccessList);

		// Llamar al método que deseas probar
		List<ExternalAccessDTO> actualList = externalAccessService.getAllListDTO();

		// Verificar que el resultado sea el esperado
		assertEquals("http://example.com", actualList.get(0).getUrl());

	}

	@Test
	public final void testGetListDTObyId() {
		// Simulamos una lista de IDs de ejemplo
		List<Long> ids = Arrays.asList(1L, 2L, 3L);
		// creamos los objetos de búsqueda
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");

		// Creamos los objetos de respuesta
		List<ExternalAccess> externalAccessList = new ArrayList<>();
		externalAccessList.add(request);

		// Configuramos el comportamiento del mock para que devuelva el
		// resultado esperado
		when(externalAccessRepository.findByIdUrlInQuery(ids)).thenReturn(externalAccessList);

		// Llamar al método que deseas probar
		List<ExternalAccessDTO> actualList = externalAccessService.getListDTObyId(ids);

		// Verificar que el resultado sea el esperado
		assertEquals("http://example.com", actualList.get(0).getUrl());

	}

	@Test
	public final void testGetListDTObyId1000() {
		// Simulamos una lista de IDs de ejemplo
		List<Long> listaAleatoria = new ArrayList<>();
		// Crear un generador de números aleatorios
		Random random = new Random();

		// Generar 1000 datos aleatorios y agregarlos a la lista
		for (int i = 0; i < 1002; i++) {
			long numeroAleatorio = (long) random.nextInt(1000); // Puedes
																// ajustar el
																// rango según
																// tus
																// necesidades
			listaAleatoria.add(numeroAleatorio);
		}
		// creamos los objetos de búsqueda
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");

		// Creamos los objetos de respuesta
		List<ExternalAccess> externalAccessList = new ArrayList<>();
		externalAccessList.add(request);

		// Configuramos el comportamiento del mock para que devuelva el
		// resultado esperado
		when(externalAccessRepository.findByIdUrlInQuery(any(List.class))).thenReturn(externalAccessList);

		// Llamar al método que deseas probar
		List<ExternalAccessDTO> actualList = externalAccessService.getListDTObyId(listaAleatoria);

		// Verificar que el resultado sea el esperado
		assertEquals("http://example.com", actualList.get(0).getUrl());

	}

	@Test
	public final void testCreateListExternalAccessDTO() {

		// Creamos los objetos de respuesta
		List<ExternalAccess> externalAccessList = new ArrayList<>();
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");
		externalAccessList.add(request);

		List<ExternalAccessDTO> listExternalAccessDTO = externalAccessService
				.createListExternalAccessDTO(externalAccessList);

		// Verificar que el resultado sea el esperado
		assertEquals("http://example.com", listExternalAccessDTO.get(0).getUrl());
	}

	@Test
	public final void testGetUrlDataById() {
		// creamos los objetos de búsqueda
		Long idUrlData = 1L;
		// creamos los objetos de respuesta
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");

		when(externalAccessRepository.findByIdUrl(1L)).thenReturn(request);

		ExternalAccess respuesta = externalAccessService.getUrlDataById(1L);

		// Verificar que el resultado sea el esperado
		assertEquals("http://example.com", respuesta.getUrl());
		verify(externalAccessRepository, times(1)).findByIdUrl(1L);

	}

	@Test
	public final void testGetDataUrlByUrl() {
		// creamos los objetos de búsqueda
		String url = "http://example.com";
		// creamos los objetos de respuesta
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");

		when(externalAccessRepository.findByUrl(url)).thenReturn(request);

		ExternalAccess respuesta = externalAccessService.getDataUrlByUrl(url);

		// Verificar que el resultado sea el esperado
		assertEquals("http://example.com", respuesta.getUrl());
		verify(externalAccessRepository, times(1)).findByUrl(url);
	}

	@Test
	public final void testGetExternalAccessAndTestConn() {

		   String uriTslLocation = "http://example.com";
	        String originUrl = "http://origin.com";
	        ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
	        externalAccessDTO.setIdCountryRegion(1L);
	     // creamos los objetos de respuesta
			request.setStateConn(Boolean.TRUE);
			request.setUrl("http://example.com");
			request.setOriginUrl(originUrl);
			
	        // Simulamos el comportamiento de findByUrl
	        when(externalAccessRepository.findByUrl(uriTslLocation)).thenReturn(request);

	        // Llamamos al método que deseamos probar
	        ExternalAccess externalAccess = externalAccessService.getExternalAccessAndTestConn(uriTslLocation, originUrl, externalAccessDTO);

	        // Verificamos que se haya creado el objeto ExternalAccess correctamente
	        assertNotNull(externalAccess);
	        assertEquals(uriTslLocation, externalAccess.getUrl());
	        assertEquals(originUrl, externalAccess.getOriginUrl());
	        assertTrue(externalAccess.getStateConn());
	        assertNotNull(externalAccess.getLastConn());
	}

	@Test
	public final void testGetExternalAccessAndTestConnNull() {

		   String uriTslLocation = "http://example.com";
	        String originUrl = "http://origin.com";
	        ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
	        externalAccessDTO.setIdCountryRegion(1L);
	     // creamos los objetos de respuesta
			request.setStateConn(Boolean.TRUE);
			request.setUrl("http://example.com");
			request.setOriginUrl(originUrl);
			
	        // Simulamos el comportamiento de findByUrl
	        when(externalAccessRepository.findByUrl(uriTslLocation)).thenReturn(null);

	        // Llamamos al método que deseamos probar
	        ExternalAccess externalAccess = externalAccessService.getExternalAccessAndTestConn(uriTslLocation, originUrl, externalAccessDTO);

	        // Verificamos que se haya creado el objeto ExternalAccess correctamente
	        assertNotNull(externalAccess);
	        assertEquals(uriTslLocation, externalAccess.getUrl());
	        assertEquals(originUrl, externalAccess.getOriginUrl());
	        assertTrue(externalAccess.getStateConn());
	        assertNotNull(externalAccess.getLastConn());
	}
	
	@Test
	public final void testGetExternalAccessTestConnAndSave() {
		String uriTslLocation = "http://example.com";
        String originUrl = "http://origin.com";
        ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
        externalAccessDTO.setIdCountryRegion(1L);
        // creamos los objetos de respuesta
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");
		request.setOriginUrl(originUrl);
		
		 // Simulamos el comportamiento de findByUrl
        when(externalAccessRepository.findByUrl(uriTslLocation)).thenReturn(request);
        // Hacer un mock del método void saveAndFlush para que no haga nada
        //doNothing().when(externalAccessRepository).saveAndFlush(any(ExternalAccess.class));
        // Simulamos el comportamiento de findByUrl
        when(externalAccessRepository.findByUrl(uriTslLocation)).thenReturn(request);
        
        // Llamamos al método que deseamos probar
        ExternalAccess externalAccess = externalAccessService.getExternalAccessTestConnAndSave(uriTslLocation, originUrl, externalAccessDTO);
        
        // Verificamos que se haya creado el objeto ExternalAccess correctamente
        assertNotNull(externalAccess);
        assertEquals(uriTslLocation, externalAccess.getUrl());
        assertEquals(originUrl, externalAccess.getOriginUrl());
        assertTrue(externalAccess.getStateConn());
        assertNotNull(externalAccess.getLastConn());
	
	}

	@Test
	public final void testGetExternalAccess() {
		String uriTslLocation = "http://example.com";
        String originUrl = "http://origin.com";

		// creamos los objetos de respuesta
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");
		request.setOriginUrl(originUrl);
				
		 // Simulamos el comportamiento de findByUrl
        when(externalAccessRepository.findByUrl(uriTslLocation)).thenReturn(request);
        
		ExternalAccess externalAccess = externalAccessService.getExternalAccess(uriTslLocation);
        // Verificamos que se haya creado el objeto ExternalAccess correctamente
        assertNotNull(externalAccess);
        assertEquals(uriTslLocation, externalAccess.getUrl());
        assertEquals(originUrl, externalAccess.getOriginUrl());
        assertTrue(externalAccess.getStateConn());
	
	}

	@Test
	public final void testPrepareUrlExternalAccessForTask() {

		 // Crear una lista de objetos ExternalAccess de ejemplo
		List<ExternalAccess> externalAccessList = new ArrayList<>();
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");
		externalAccessList.add(request);

        // Configurar el comportamiento del mock para findAll
        when(externalAccessRepository.findAll()).thenReturn(externalAccessList);
        // Configurar un mock de Language
     // Configura el comportamiento del mock de MyClass
        PowerMockito.mockStatic(Language.class);
        PowerMockito.when(Language.getResCoreTsl(CoreTslMessages.LOGMTSL406)).thenReturn("Mensaje de prueba");
        // Llamar al método que deseas probar
        externalAccessService.prepareUrlExternalAccessForTask();

        // Verificar que los métodos y lógica del método prepareUrlExternalAccessForTask
        // funcionaron correctamente según tus expectativas

        // Verificar que se llamó a makeChangesToExternalAccess con el DTO correcto
        verify(externalAccessService, times(1)).makeChangesToExternalAccess(any(ExternalAccessDTO.class), anyString());

        // Verificar que se llamó a launchAlarmIfTestConnFail con la lista correcta y el mensaje correcto
        verify(externalAccessService, times(1)).launchAlarmIfTestConnFail(anyList(), anyString());

        // Otras verificaciones según tus expectativas
	}

	@Test
	public final void testPrepareUrlExternalAccessForTestConn() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPrepareUrlExternalAccessInitPlatform() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPrepareUrlExternalAccessToTSL() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testPrepareUrlExternalAccessToDelete() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testMakeChangesToExternalAccess() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testExtractUrlToDistributionPoints() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testOperationsOnExternalAccess() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetMessageErrorValue() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetMessageError() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testSetMessageError() {
		fail("Not yet implemented"); // TODO
	}
}
