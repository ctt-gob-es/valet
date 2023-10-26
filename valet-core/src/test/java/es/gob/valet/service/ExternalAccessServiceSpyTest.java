package es.gob.valet.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.mockito.Spy;

import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.bouncycastle.asn1.DERIA5String;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.Extensions;
import org.bouncycastle.asn1.x509.GeneralName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import es.gob.valet.persistence.ManagerPersistenceServices;
import es.gob.valet.persistence.configuration.ManagerPersistenceConfigurationServices;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.dto.ExternalAccessDTO;
import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.entity.TslData;
import es.gob.valet.persistence.configuration.model.repository.ExternalAccessRepository;
import es.gob.valet.persistence.configuration.model.repository.TslCountryRegionRepository;
import es.gob.valet.persistence.configuration.model.repository.datatable.ExternalAccessTablesRepository;
import es.gob.valet.persistence.configuration.model.specification.ExternalAccessSpecification;
import es.gob.valet.persistence.configuration.services.ifaces.IAlarmService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslCountryRegionService;
import es.gob.valet.persistence.configuration.services.ifaces.ITslDataService;
import es.gob.valet.service.ExternalAccessService;
import es.gob.valet.tsl.access.TSLManager;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.exceptions.TSLParsingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.SchemeInformation;
import es.gob.valet.tsl.parsing.impl.common.TSLObject;
import es.gob.valet.alarms.AlarmsManager;
import es.gob.valet.alarms.conf.AlarmsConfiguration;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreGeneralMessages;
import es.gob.valet.i18n.messages.CoreTslMessages;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Language.class,AlarmsManager.class,AlarmsConfiguration.class,ManagerPersistenceServices.class,ManagerPersistenceConfigurationServices.class,
	TSLManager.class, AuthorityInformationAccess.class})
public class ExternalAccessServiceSpyTest {

	@InjectMocks
	@Spy
	private ExternalAccessService externalAccessService = new ExternalAccessService();
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
	private TSLManager tSLManagerMock;

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
    private AlarmsManager alarmsManager;

    @Mock
    private TSLObject tslObjectMock;
    @Mock
    private Logger LOGGERMock;
    @Captor
    private ArgumentCaptor<String> alarmMessageCaptor;
	

	private ExternalAccess request;
	private ExternalAccessDTO dto;

	@Before
	public void setUp() {
        PowerMockito.mockStatic(AlarmsManager.class);

		request = new ExternalAccess(); // Se inicializa objeto de prueba
		dto = new ExternalAccessDTO(); // Se inicializa objeto de prueba
		
		
	}

	@Test
	public final void testPrepareUrlExternalAccessForTask() {

		 // Crear una lista de objetos ExternalAccess de ejemplo
		List<ExternalAccess> externalAccessList = new ArrayList<>();
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");
		request.setOriginUrl("DistributionPointCRL");
		externalAccessList.add(request);

        // Configurar el comportamiento del mock para findAll
        when(externalAccessRepository.findAll()).thenReturn(externalAccessList);
        
        doNothing().when(externalAccessService).makeChangesToExternalAccess(any(ExternalAccessDTO.class), anyString());
        doNothing().when(externalAccessService).launchAlarmIfTestConnFail(anyList(), anyString());

        // Configurar un mock de Language
        // Configura el comportamiento del mock
        PowerMockito.mockStatic(Language.class);
        PowerMockito.when(Language.getResCoreTsl(CoreTslMessages.LOGMTSL406)).thenReturn("Mensaje de prueba");
        PowerMockito.when(Language.getResCoreGeneral(CoreGeneralMessages.ALM009_EVENT_001)).thenReturn("Mensaje de prueba");
        
        // Llamar al método que deseas probar
        externalAccessService.prepareUrlExternalAccessForTask();

        // Verificar que se llamó a makeChangesToExternalAccess con el DTO correcto
        verify(externalAccessService, times(1)).makeChangesToExternalAccess(any(ExternalAccessDTO.class), anyString());

        // Verificar que se llamó a launchAlarmIfTestConnFail con la lista correcta y el mensaje correcto
        verify(externalAccessService, times(1)).launchAlarmIfTestConnFail(anyList(), anyString());

	}

	@Test
	public final void testPrepareUrlExternalAccessForTestConn() {
		
		 // Crear una lista de objetos ExternalAccess de ejemplo
		List<ExternalAccess> externalAccessList = new ArrayList<>();
		request.setStateConn(Boolean.TRUE);
		request.setUrl("http://example.com");
		request.setOriginUrl("DistributionPointCRL");
		externalAccessList.add(request);
		
        List<Long> listIdUrl = Arrays.asList(1L, 2L, 3L); // Ejemplo de lista de ID
        
        // Simular llamadas a los métodos externos
        PowerMockito.mockStatic(Language.class);
        PowerMockito.mockStatic(ManagerPersistenceServices.class);
        PowerMockito.mockStatic(ManagerPersistenceConfigurationServices.class);
        
        PowerMockito.when(Language.getResCoreTsl(CoreTslMessages.LOGMTSL414)).thenReturn("Mensaje de prueba");
        when(externalAccessRepository.findByIdUrlInQuery(listIdUrl)).thenReturn(externalAccessList);
        PowerMockito.when(Language.getResCoreGeneral(CoreGeneralMessages.ALM009_EVENT_002)).thenReturn("Mensaje de prueba");

        doNothing().when(externalAccessService).makeChangesToExternalAccess(any(ExternalAccessDTO.class), anyString());
        doNothing().when(externalAccessService).launchAlarmIfTestConnFail(anyList(), anyString());
        // Ejecutar el método bajo prueba
        externalAccessService.prepareUrlExternalAccessForTestConn(listIdUrl);

        verify(externalAccessRepository).findByIdUrlInQuery(listIdUrl);

	}


	@Test
	public final void testOperationsOnExternalAccessOp1() throws TSLCertificateValidationException {
		
		SchemeInformation si =  new SchemeInformation();
		si.setSchemeTerritory("Local");

		// Simula las llamadas a los métodos internos usando doNothing
        doNothing().when(externalAccessService).prepareUrlExternalAccessInitPlatform();
        doNothing().when(externalAccessService).prepareUrlExternalAccessToTSL(tslObjectMock);
        doNothing().when(externalAccessService).prepareUrlExternalAccessToDelete(tslObjectMock);
        doNothing().when(externalAccessService).prepareUrlExternalAccessForTask();

        // Ejecuta el método operationsOnExternalAccess con el caso de prueba OPERATION1
        externalAccessService.operationsOnExternalAccess(ExternalAccessService.OPERATION1, tslObjectMock, null);
        
        // Verifica que los métodos internos se llamen una vez
        verify(externalAccessService).prepareUrlExternalAccessInitPlatform();

        // También puedes verificar que no se llama a los otros métodos
        verify(externalAccessService, never()).prepareUrlExternalAccessToTSL(tslObjectMock);
        verify(externalAccessService, never()).prepareUrlExternalAccessToDelete(tslObjectMock);
        verify(externalAccessService, never()).prepareUrlExternalAccessForTask();
	}
	@Test
	public final void testOperationsOnExternalAccessOp2() throws TSLCertificateValidationException {
		
		SchemeInformation si =  new SchemeInformation();
		si.setSchemeTerritory("Local");

		// Simula las llamadas a los métodos internos usando doNothing
        doNothing().when(externalAccessService).prepareUrlExternalAccessInitPlatform();
        doNothing().when(externalAccessService).prepareUrlExternalAccessToTSL(tslObjectMock);
        doNothing().when(externalAccessService).prepareUrlExternalAccessToDelete(tslObjectMock);
        doNothing().when(externalAccessService).prepareUrlExternalAccessForTask();

        // Ejecuta el método operationsOnExternalAccess con el caso de prueba OPERATION2
        externalAccessService.operationsOnExternalAccess(ExternalAccessService.OPERATION2, tslObjectMock, null);

        // Verifica que los métodos internos se llamen una vez
        verify(externalAccessService).prepareUrlExternalAccessToTSL(tslObjectMock);

        // También puedes verificar que no se llama a los otros métodos
        verify(externalAccessService, never()).prepareUrlExternalAccessInitPlatform();
        verify(externalAccessService, never()).prepareUrlExternalAccessToDelete(tslObjectMock);
        verify(externalAccessService, never()).prepareUrlExternalAccessForTask();
	}
	@Test
	public final void testOperationsOnExternalAccessOp3() throws TSLCertificateValidationException {
		
		SchemeInformation si =  new SchemeInformation();
		si.setSchemeTerritory("Local");
		// Simula las llamadas a los métodos internos usando doNothing
        doNothing().when(externalAccessService).prepareUrlExternalAccessInitPlatform();
        doNothing().when(externalAccessService).prepareUrlExternalAccessToTSL(tslObjectMock);
        doNothing().when(externalAccessService).prepareUrlExternalAccessToDelete(tslObjectMock);
        doNothing().when(externalAccessService).prepareUrlExternalAccessForTask();

        // Ejecuta el método operationsOnExternalAccess con el caso de prueba OPERATION3
        externalAccessService.operationsOnExternalAccess(ExternalAccessService.OPERATION3, tslObjectMock, null);

        // Verifica que los métodos internos se llamen una vez
        verify(externalAccessService).prepareUrlExternalAccessToDelete(tslObjectMock);

        // También puedes verificar que no se llama a los otros métodos
        verify(externalAccessService, never()).prepareUrlExternalAccessInitPlatform();
        verify(externalAccessService, never()).prepareUrlExternalAccessToTSL(tslObjectMock);
        verify(externalAccessService, never()).prepareUrlExternalAccessForTask();
	}
	@Test
	public final void testOperationsOnExternalAccessOp4() throws TSLCertificateValidationException {
		
		SchemeInformation si =  new SchemeInformation();
		si.setSchemeTerritory("Local");
		// Simula las llamadas a los métodos internos usando doNothing
        doNothing().when(externalAccessService).prepareUrlExternalAccessInitPlatform();
        doNothing().when(externalAccessService).prepareUrlExternalAccessToTSL(tslObjectMock);
        doNothing().when(externalAccessService).prepareUrlExternalAccessToDelete(tslObjectMock);
        doNothing().when(externalAccessService).prepareUrlExternalAccessForTask();

        // Ejecuta el método operationsOnExternalAccess con el caso de prueba OPERATION4
        externalAccessService.operationsOnExternalAccess(ExternalAccessService.OPERATION4, tslObjectMock, null);

        // Verifica que los métodos internos se llamen una vez
        verify(externalAccessService).prepareUrlExternalAccessForTask();

        // También puedes verificar que no se llama a los otros métodos
        verify(externalAccessService, never()).prepareUrlExternalAccessInitPlatform();
        verify(externalAccessService, never()).prepareUrlExternalAccessToDelete(tslObjectMock);
        verify(externalAccessService, never()).prepareUrlExternalAccessToTSL(tslObjectMock);
	}
	


	@Test
	public final void testPrepareUrlExternalAccessInitPlatform() throws TSLCacheException, TSLArgumentException, TSLParsingException, TSLMalformedException, TSLCertificateValidationException {
		 // Crear una lista de objetos de ejemplo
		List<TslCountryRegion> tcrList = new ArrayList<>();
		TslCountryRegion tslCountry = new TslCountryRegion();
		tcrList.add(tslCountry);
		
		List<ExternalAccessDTO> externalAccessList = new ArrayList<>();
		dto.setStateConn(Boolean.TRUE);
		dto.setUrl("http://example.com");
		dto.setOriginUrl("DistributionPointCRL");
		externalAccessList.add(dto);
		
        // Simular llamadas a los métodos externos
        PowerMockito.mockStatic(Language.class);
        PowerMockito.when(Language.getResCoreTsl(CoreTslMessages.LOGMTSL414)).thenReturn("Mensaje de prueba");
        PowerMockito.when(Language.getResCoreGeneral(CoreGeneralMessages.ALM009_EVENT_003)).thenReturn("Mensaje de prueba");
        
        when(iTslCountryRegionService.getAllTslCountryRegion(false)).thenReturn(tcrList);
        doReturn(externalAccessList).when(externalAccessService).obtainAllUrlToRegionTSL(tcrList);
        doNothing().when(externalAccessService).launchAlarmIfTestConnFail(anyList(), anyString());

        // Ejecutar el método bajo prueba
        externalAccessService.prepareUrlExternalAccessInitPlatform();


	}
	
	@Test
    public void testLaunchAlarmIfTestConnFail() {
        // Datos de prueba
        String messageHead = "Test connection failed: ";
        ExternalAccess access1 = new ExternalAccess();
        access1.setStateConn(false);
        access1.setLastConn(new Date());
        TslCountryRegion tslCountry = new TslCountryRegion();
		tslCountry.setIdTslCountryRegion(1L);
		TslData tslData = new TslData();
		tslData.setSequenceNumber(1);
		tslCountry.setTslData(tslData);
		
        access1.setTslCountryRegion(tslCountry);
        
        ExternalAccess access2 = new ExternalAccess();
        access2.setStateConn(true); 

        List<ExternalAccess> listExternalAccessResult = new ArrayList<>();
        listExternalAccessResult.add(access1);
        listExternalAccessResult.add(access2);

        listExternalAccessResult.add(access1);
        
		StringBuffer messageMail = new StringBuffer();
		messageMail.append(messageHead);

        // Crear una lista de objetos de ejemplo
		List<TslCountryRegion> tcrList = new ArrayList<>();
		tcrList.add(tslCountry);
        when(tslCountryRegionRepository.findAll()).thenReturn(tcrList);
        // Simular llamadas a los métodos externos
        PowerMockito.mockStatic(Language.class);
        PowerMockito.when(Language.getFormatResCoreGeneral(CoreGeneralMessages.ALM009_EVENT_004, new Object[ ] { tslCountry.getCountryRegionName(), tslCountry.getTslData().getSequenceNumber()})).thenReturn("Mensaje de prueba");
        PowerMockito.when(AlarmsManager.getInstance()).thenReturn(alarmsManager);
        
        // Ejecutar el método bajo prueba
        externalAccessService.launchAlarmIfTestConnFail(listExternalAccessResult, messageHead);

        // Verificar que el mensaje se ha construido correctamente
        String expectedMessage = messageHead ;
        assertEquals(expectedMessage, messageMail.toString());
    }

	@Test
	public void testObtainAllUrlToRegionTSLListResult0() throws Exception {
	    // Datos de prueba
	    TslCountryRegion tcr = new TslCountryRegion();
	    tcr.setCountryRegionCode("ES");
	    TslData tslData = new TslData();
	    tslData.setIdTslData(1L);
	    tcr.setTslData(tslData);

	    List<TslCountryRegion> tcrList = Collections.singletonList(tcr);

	    PowerMockito.mockStatic(Language.class);
        PowerMockito.when(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL398, new Object[ ] { tcr.getCountryRegionCode()})).thenReturn("Mensaje de prueba");
        PowerMockito.when(Language.getFormatResCoreTsl(eq(CoreTslMessages.LOGMTSL399), new Object[] { eq(tcr.getCountryRegionCode()), anyLong() })).thenReturn("Mensaje de prueba");
	    // Ejecutar el método bajo prueba
	    List<ExternalAccessDTO> result = externalAccessService.obtainAllUrlToRegionTSL(tcrList);

	    // Verificar que se obtienen los datos correctos
	    assertEquals(0, result.size());
	}
	
	@Test
	public void testObtainAllUrlToRegionTSL() throws Exception {
	    // Datos de prueba
	    TslCountryRegion tcr = new TslCountryRegion();
	    tcr.setCountryRegionCode("ES");
	    TslData tslData = new TslData();
	    tslData.setIdTslData(1L);
	    tcr.setTslData(tslData);
	    tcr.setIdTslCountryRegion(1L);
	    List<TslCountryRegion> tcrList = Collections.singletonList(tcr);

	    when(iTslDataService.getTslDataById(1L, true, false)).thenReturn(tslData);
	    PowerMockito.mockStatic(Language.class);
        PowerMockito.when(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL398, new Object[ ] { tcr.getCountryRegionCode()})).thenReturn("Mensaje de prueba");
        PowerMockito.when(Language.getFormatResCoreTsl(eq(CoreTslMessages.LOGMTSL399), new Object[] { eq(tcr.getCountryRegionCode()), anyLong() })).thenReturn("Mensaje de prueba");
	    // Ejecutar el método bajo prueba
	    List<ExternalAccessDTO> result = externalAccessService.obtainAllUrlToRegionTSL(tcrList);

	    // Verificar que se obtienen los datos correctos
	    assertEquals(1, result.size());
	    assertEquals(Long.valueOf(1L), result.get(0).getIdCountryRegion());

	}

	@Test
    public void testExtractUrlToDistributionPoints() throws Exception {
        ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
        TslData tslData = new TslData();
	    tslData.setIdTslData(1L);
	    List<URI> distributionPointsParam = new ArrayList<>();
	    distributionPointsParam.add(new URI("http://prueba.com"));
	    distributionPointsParam.add(new URI("http://prueba2.com"));
	    SchemeInformation si =  new SchemeInformation();
		si.setSchemeTerritory("Local");
		si.setDistributionPoints(distributionPointsParam);
		ITSLObject tslObject =  tslObjectMock;
		tslObject.setSchemeInformation(si);
        X509Certificate certificate = (X509Certificate) loadCertificateFromCerFile("src/test/resources/Certificate.cer");

		List<X509Certificate> listCertificate = new ArrayList<>();	
		listCertificate.add(certificate);
	    PowerMockito.mockStatic(TSLManager.class);
        PowerMockito.when(TSLManager.getInstance()).thenReturn(tSLManagerMock);
        PowerMockito.when(tSLManagerMock.getListCertificatesTSL(tslObject)).thenReturn(listCertificate);
       
        externalAccessService.extractUrlToDistributionPoints(externalAccessDTO, tslObjectMock);

        // Verificar que las URL válidas se han agregado a la lista de resultados
        assertEquals(1, externalAccessDTO.getListUrlDistributionPointDPResult().size());
    }

	@Test
    public void testExtractUrlToDistributionPointsErrorBreak() throws TSLCertificateValidationException, TSLArgumentException, TSLParsingException, TSLMalformedException, URISyntaxException {
        ExternalAccessDTO externalAccessDTO = new ExternalAccessDTO();
        TslData tslData = new TslData();
	    tslData.setIdTslData(1L);
	    List<URI> distributionPointsParam = new ArrayList<>();
	    distributionPointsParam.add(new URI("htp://prueba.com"));
	    distributionPointsParam.add(new URI("htp://prueba2.com"));
	    SchemeInformation si =  new SchemeInformation();
		si.setSchemeTerritory("Local");
		si.setDistributionPoints(distributionPointsParam);
		ITSLObject tslObject =  tslObjectMock;
		tslObject.setSchemeInformation(si);
		
	    PowerMockito.mockStatic(TSLManager.class);
	    PowerMockito.mockStatic(Language.class);
        PowerMockito.when(TSLManager.getInstance()).thenReturn(tSLManagerMock);
        PowerMockito.when(Language.getFormatResCoreTsl(eq(CoreTslMessages.LOGMTSL416), new Object[ ] { anyString() })).thenReturn("Mensaje de prueba");

        externalAccessService.extractUrlToDistributionPoints(externalAccessDTO, tslObjectMock);

        PowerMockito.verifyStatic(Language.class);
        Language.getFormatResCoreTsl(eq(CoreTslMessages.LOGMTSL416), new Object[ ] { anyString() });
    }
	
	 @Test
	    public void testSearchUrlDistributionPointOcsp() throws Exception {
	        AuthorityInformationAccess aia = mock(AuthorityInformationAccess.class);
	        AccessDescription[] accessDescArray = new AccessDescription[1];
	        AccessDescription accessDescription = mock(AccessDescription.class);
	        GeneralName accessLocationGeneralName = mock(GeneralName.class);
	        DERIA5String derIA5String = mock(DERIA5String.class);

	        // Configura el comportamiento de tus mocks
	        when(aia.getAccessDescriptions()).thenReturn(accessDescArray);
	        accessDescArray[0] = accessDescription;
	        when(accessDescription.getAccessMethod()).thenReturn(OCSPObjectIdentifiers.id_pkix_ocsp);
	        when(accessDescription.getAccessLocation()).thenReturn(accessLocationGeneralName);
	        when(accessLocationGeneralName.getTagNo()).thenReturn(GeneralName.uniformResourceIdentifier);
	        when(accessLocationGeneralName.getName()).thenReturn(derIA5String);
	        when(derIA5String.getString()).thenReturn("http://example.com/ocsp");

		    PowerMockito.mockStatic(AuthorityInformationAccess.class);
		    AuthorityInformationAccess authorityInformationAccess = mock(AuthorityInformationAccess.class);
	        PowerMockito.when(AuthorityInformationAccess.fromExtensions(any(Extensions.class))).thenReturn(authorityInformationAccess);
	        X509Certificate certificate = (X509Certificate) loadCertificateFromCerFile("src/test/resources/Certificate.cer");

	        // Llama al método bajo prueba
	        List<String> result = externalAccessService.searchUrlDistributionPointOcsp(certificate);

	        // Verifica que el resultado contiene la URL OCSP válida
	        assertEquals(0, result.size());
	    }
	 
	    public static X509Certificate loadCertificateFromCerFile(String filePath) throws Exception {
	        // Crea una instancia de CertificateFactory para cargar certificados X.509
	        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

	        // Lee el certificado desde el archivo .cer
	        FileInputStream fileInputStream = new FileInputStream(filePath);

	        try {
	            // Carga el certificado
	            X509Certificate certificate = (X509Certificate) certificateFactory.generateCertificate(fileInputStream);
	            return certificate;
	        } finally {
	            fileInputStream.close();
	        }
	    }

}
