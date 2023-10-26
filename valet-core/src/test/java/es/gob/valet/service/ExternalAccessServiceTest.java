package es.gob.valet.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;

import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;

import es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO;

import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.repository.ExternalAccessRepository;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.ExternalAccessTablesRepository;
import es.gob.valet.persistence.configuration.model.specification.ExternalAccessSpecification;
import es.gob.valet.persistence.configuration.services.ifaces.IAlarmService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;
import es.gob.valet.service.ExternalAccessService;

import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;

import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.SchemeInformation;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;
import es.gob.valet.alarms.conf.AlarmsConfiguration;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreGeneralMessages;
import es.gob.valet.i18n.messages.CoreTslMessages;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Language.class, AlarmsConfiguration.class, ManagerPersistenceServices.class, SSLContext.class,
		ManagerPersistenceConfigurationServices.class, HttpsURLConnection.class })
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

	@Mock
	private SSLContext sslContext;

	/**
	 * Attribute that represents the service object for accessing the repository
	 * of country region.
	 */
	@Mock
	private TslCountryRegionRepository tslCountryRegionRepository;

	@Mock
	private ExternalAccessSpecification externalAccessSpecification;

	@Mock
	private IAlarmService alarmService;

	@Mock
	private TSLObject tslObjectMock;
	@Mock
	private Logger LOGGERMock;

	private DataTablesInput input;

	private ExternalAccess request;
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
		List<ExternalAccess> externalAccessList = new ArrayList<>();
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

		when(externalAccessRepository.findByIdUrl(idUrlData)).thenReturn(request);

		ExternalAccess respuesta = externalAccessService.getUrlDataById(idUrlData);

		// Verificar que el resultado sea el esperado
		assertEquals("http://example.com", respuesta.getUrl());
		verify(externalAccessRepository, times(1)).findByIdUrl(idUrlData);

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
		ExternalAccess externalAccess = externalAccessService.getExternalAccessAndTestConn(uriTslLocation, originUrl,
				externalAccessDTO);

		// Verificamos que se haya creado el objeto ExternalAccess correctamente
		assertNotNull(externalAccess);
		assertEquals(uriTslLocation, externalAccess.getUrl());
		assertEquals(originUrl, externalAccess.getOriginUrl());
		assertTrue(externalAccess.getStateConn());
		assertNotNull(externalAccess.getLastConn());
	}

	@Test
	public final void testGetExternalAccessAndTestConnLDAP() {

		String uriTslLocation = "ldap://trustcenter.matav.hu:389/cn=Matav%20Minositett%20Root%20CA,c=HU?certificateRevocationList?base?objectClass=certificationAuthority";
		String originUrl = "ldap://trustcenter.matav.hu:389/cn=Matav%20Minositett%20Root%20CA,c=HU?certificateRevocationList?base?objectClass=certificationAuthority";
		ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
		externalAccessDTO.setIdCountryRegion(1L);
		// creamos los objetos de respuesta
		request.setStateConn(Boolean.TRUE);
		request.setUrl(
				"ldap://trustcenter.matav.hu:389/cn=Matav%20Minositett%20Root%20CA,c=HU?certificateRevocationList?base?objectClass=certificationAuthority");
		request.setOriginUrl(originUrl);

		// Simulamos el comportamiento de findByUrl
		when(externalAccessRepository.findByUrl(uriTslLocation)).thenReturn(request);

		// Llamamos al método que deseamos probar
		ExternalAccess externalAccess = externalAccessService.getExternalAccessAndTestConn(uriTslLocation, originUrl,
				externalAccessDTO);

		// Verificamos que se haya creado el objeto ExternalAccess correctamente
		assertNotNull(externalAccess);
		assertEquals(uriTslLocation, externalAccess.getUrl());
		assertEquals(originUrl, externalAccess.getOriginUrl());
		assertTrue(externalAccess.getStateConn());
		assertNotNull(externalAccess.getLastConn());
	}

	@Test
	public final void testGetExternalAccessAndTestConnHttps() throws Exception {

		String uriTslLocation = "https://tl-norway.no/TSL/NO_TSL.XML";
		String originUrl = "https://tl-norway.no/TSL/NO_TSL.XML";
		ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
		externalAccessDTO.setIdCountryRegion(1L);
		// creamos los objetos de respuesta
		request.setStateConn(Boolean.TRUE);
		request.setUrl("https://tl-norway.no/TSL/NO_TSL.XML");
		request.setOriginUrl(originUrl);

		// Simulamos el comportamiento de findByUrl
		when(externalAccessRepository.findByUrl(uriTslLocation)).thenReturn(request);
		PowerMockito.mockStatic(Language.class);
		PowerMockito.mockStatic(SSLContext.class);
		PowerMockito.mockStatic(HttpsURLConnection.class);
		PowerMockito.when(Language.getResCoreGeneral(CoreGeneralMessages.ERROR_SERVICE_01))
				.thenReturn("Mensaje prueba");
		PowerMockito.when(Language.getResCoreTsl(CoreTslMessages.LOGMTSL420)).thenReturn("Mensaje prueba");
		PowerMockito.when(SSLContext.getInstance("SSL")).thenReturn(sslContext);
		PowerMockito.doNothing().when(HttpsURLConnection.class, "setDefaultSSLSocketFactory",
				any(SSLSocketFactory.class));

		// Llamamos al método que deseamos probar
		ExternalAccess externalAccess = externalAccessService.getExternalAccessAndTestConn(uriTslLocation, originUrl,
				externalAccessDTO);

		// Verificamos que se haya creado el objeto ExternalAccess correctamente
		assertNotNull(externalAccess);
		assertEquals(uriTslLocation, externalAccess.getUrl());
		assertEquals(originUrl, externalAccess.getOriginUrl());
		assertTrue(externalAccess.getStateConn());
		assertNotNull(externalAccess.getLastConn());
	}

	@Test
	public final void testGetExternalAccessAndTestConnHttpsError() {

		String uriTslLocation = "https://www.pkioverheid.nl/fileadmin/PKI/PKI_certifcaten/staatdernederlandenorganisatieca-g2.crt";
		String originUrl = "https://www.pkioverheid.nl/fileadmin/PKI/PKI_certifcaten/staatdernederlandenorganisatieca-g2.crt";
		ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
		externalAccessDTO.setIdCountryRegion(1L);
		// creamos los objetos de respuesta
		request.setStateConn(Boolean.TRUE);
		request.setUrl(
				"https://www.pkioverheid.nl/fileadmin/PKI/PKI_certifcaten/staatdernederlandenorganisatieca-g2.crt");
		request.setOriginUrl(originUrl);

		// Simulamos el comportamiento de findByUrl
		when(externalAccessRepository.findByUrl(uriTslLocation)).thenReturn(request);
		PowerMockito.mockStatic(Language.class);
		PowerMockito.when(Language.getResCoreGeneral(CoreGeneralMessages.ERROR_SERVICE_01))
				.thenReturn("Mensaje prueba");
		PowerMockito.when(Language.getResCoreTsl(CoreTslMessages.LOGMTSL420)).thenReturn("Mensaje prueba");

		// Llamamos al método que deseamos probar
		ExternalAccess externalAccess = externalAccessService.getExternalAccessAndTestConn(uriTslLocation, originUrl,
				externalAccessDTO);

		// Verificamos que se haya creado el objeto ExternalAccess correctamente
		assertNotNull(externalAccess);
		assertEquals(uriTslLocation, externalAccess.getUrl());
		assertEquals(originUrl, externalAccess.getOriginUrl());
		assertFalse(externalAccess.getStateConn());
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
		ExternalAccess externalAccess = externalAccessService.getExternalAccessAndTestConn(uriTslLocation, originUrl,
				externalAccessDTO);

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
		// doNothing().when(externalAccessRepository).saveAndFlush(any(ExternalAccess.class));
		// Simulamos el comportamiento de findByUrl
		when(externalAccessRepository.findByUrl(uriTslLocation)).thenReturn(request);

		// Llamamos al método que deseamos probar
		ExternalAccess externalAccess = externalAccessService.getExternalAccessTestConnAndSave(uriTslLocation,
				originUrl, externalAccessDTO);

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
	public final void testPrepareUrlExternalAccessInitPlatformError() {
		// Crear una lista de objetos de ejemplo
		List<TslCountryRegion> tcrList = new ArrayList<>();
		TslCountryRegion tslCountry = new TslCountryRegion();
		tcrList.add(tslCountry);

		// Simular llamadas a los métodos externos
		PowerMockito.mockStatic(Language.class);
		PowerMockito.when(Language.getResCoreTsl(CoreTslMessages.LOGMTSL414)).thenReturn("Mensaje de prueba");

		when(iTslCountryRegionService.getAllTslCountryRegion(false)).thenReturn(tcrList);

		// Ejecutar el método bajo prueba
		externalAccessService.prepareUrlExternalAccessInitPlatform();
		
		verify(iTslCountryRegionService).getAllTslCountryRegion(false);
		verifyNoMoreInteractions(iTslCountryRegionService);
	}

	@Test
	public final void testPrepareUrlExternalAccessToTSL()
			throws TSLCertificateValidationException, TSLArgumentException {
		// Montamos los objetos para las pruebas
		ITSLObject tslObject = new TSLObject("Country", "1.2");

		TslCountryRegion tslCountry = new TslCountryRegion();
		tslCountry.setIdTslCountryRegion(1L);
		SchemeInformation si = new SchemeInformation();
		si.setSchemeTerritory("Local");
		tslObject.setSchemeInformation(si);

		ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
		externalAccessDTO.setIdCountryRegion(1L);

		// Simular llamadas a los métodos externos
		PowerMockito.mockStatic(Language.class);
		PowerMockito.when(Language.getResCoreTsl(CoreTslMessages.LOGMTSL410)).thenReturn("Mensaje de prueba");

		when(tslCountryRegionRepository.findByCountryRegionCode(anyString())).thenReturn(tslCountry);

		// Ejecutar el método bajo prueba
		externalAccessService.prepareUrlExternalAccessToTSL(tslObject);

		verify(tslCountryRegionRepository)
				.findByCountryRegionCode(tslObject.getSchemeInformation().getSchemeTerritory());

	}

	@Test
	public final void testPrepareUrlExternalAccessToDelete()
			throws TSLArgumentException, TSLCertificateValidationException {
		// Montamos los objetos para las pruebas
		ITSLObject tslObject = new TSLObject("Country", "1.2");

		// Simular llamadas a los métodos externos
		PowerMockito.mockStatic(Language.class);
		PowerMockito.when(Language.getResCoreTsl(CoreTslMessages.LOGMTSL410)).thenReturn("Mensaje de prueba");

		// Ejecutar el método bajo prueba
		externalAccessService.prepareUrlExternalAccessToDelete(tslObject);
		verify(externalAccessRepository).findAll();

	}

	@Test
	public final void testSetMessageError() {

		externalAccessService.setMessageError("Hola error");
		String mensageError1 = externalAccessService.getMessageErrorValue();
		assertNotNull(mensageError1);

	}
}
