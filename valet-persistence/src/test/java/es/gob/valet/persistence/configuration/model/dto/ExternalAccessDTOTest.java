package es.gob.valet.persistence.configuration.model.dto;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;
import es.gob.valet.persistence.configuration.model.repository.ExternalAccessRepository;

@RunWith(PowerMockRunner.class)
public class ExternalAccessDTOTest {


	@Mock
	ExternalAccessRepository dtRepository ;
	
	ExternalAccessDTO externalAccessDTO;
	
	Date date = new Date();
	TslCountryRegion tsl = new TslCountryRegion();
	@Before
	public void setUp() throws Exception {
		//insertamos datos de prueba
				externalAccessDTO = new ExternalAccessDTO();
				externalAccessDTO.setIdUrl(1L);
				externalAccessDTO.setLastConn(date);
				externalAccessDTO.setOriginUrl("");
				externalAccessDTO.setStateConn(Boolean.TRUE);
				externalAccessDTO.setIdCountryRegion(1L);
				externalAccessDTO.setUrl("urlPrueba.com");
				externalAccessDTO.setMessageError("prueba");
	}
		
	@Test
	public void testGets() {
		Long id = 1L;
		
		assertEquals(id,externalAccessDTO.getIdUrl());
		assertEquals(date,externalAccessDTO.getLastConn());
		assertEquals("urlPrueba.com",externalAccessDTO.getUrl());
		assertEquals(Boolean.TRUE,externalAccessDTO.getStateConn());
		assertEquals(id,externalAccessDTO.getIdCountryRegion());
		assertEquals("",externalAccessDTO.getOriginUrl());
		assertEquals("prueba", externalAccessDTO.getMessageError());

	}
	
	@Test
	public void testList(){
		List<String> list = new ArrayList<String>();
		List<ExternalAccess> listExternalAccessResult = new ArrayList<ExternalAccess>();
		list.add("prueba1");
		list.add("prueba2");
		ExternalAccess externalAccess = new ExternalAccess();
		externalAccess.setIdUrl(1L);
		externalAccess.setLastConn(date);
		externalAccess.setOriginUrl("");
		externalAccess.setStateConn(Boolean.TRUE);
		externalAccess.setUrl("urlPrueba.com");
		
		listExternalAccessResult.add(externalAccess);
	
		externalAccessDTO.setListExternalAccessResult(listExternalAccessResult);
		externalAccessDTO.setListUrlDistributionPointCRLResult(list);
		externalAccessDTO.setListUrlDistributionPointDPResult(list);
		externalAccessDTO.setListUrlDistributionPointOCSPResult(list);
		externalAccessDTO.setListUrlIssuerResult(list);
		
		
		assertNotNull(externalAccessDTO.getListExternalAccessResult());
		assertNotNull(externalAccessDTO.getListUrlDistributionPointCRLResult());
		assertNotNull(externalAccessDTO.getListUrlDistributionPointDPResult());
		assertNotNull(externalAccessDTO.getListUrlDistributionPointOCSPResult());
		assertNotNull(externalAccessDTO.getListUrlIssuerResult());
	}


}
