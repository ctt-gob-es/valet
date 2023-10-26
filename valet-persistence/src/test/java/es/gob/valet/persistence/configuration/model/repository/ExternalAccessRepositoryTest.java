package es.gob.valet.persistence.configuration.model.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;
import es.gob.valet.persistence.configuration.model.entity.TslCountryRegion;

@RunWith(PowerMockRunner.class)
public class ExternalAccessRepositoryTest {


	@Mock
	ExternalAccessRepository dtRepository ;
	
	ExternalAccess externalAccess;
	
	@Before
	public void setUp() throws Exception {
		//insertamos datos de prueba
				externalAccess = new ExternalAccess();
				externalAccess.setIdUrl(1L);
				externalAccess.setLastConn(new Date());
				externalAccess.setOriginUrl("");
				externalAccess.setStateConn(Boolean.TRUE);
				externalAccess.setTslCountryRegion(new TslCountryRegion());
				externalAccess.setUrl("urlPrueba.com");
				dtRepository.save(externalAccess);
	}

	@Test
	public void testFindAll() {
		// Crear una entrada de ejemplo
        List<ExternalAccess> list = new ArrayList<ExternalAccess>();
        List<ExternalAccess> listResult = new ArrayList<ExternalAccess>();
        list.add(externalAccess);
        Mockito.when(dtRepository.findAll()).thenReturn(list);

        listResult = dtRepository.findAll();
		
		assertEquals(1,listResult.size());
		assertEquals(list.get(0).getUrl(), listResult.get(0).getUrl());
	    assertEquals(list.get(0).getIdUrl(), listResult.get(0).getIdUrl());
	    assertEquals(list.get(0).getStateConn(), listResult.get(0).getStateConn());
	}

	@Test
	public void testFindByIdUrl() {
		ExternalAccess result = new ExternalAccess();
        Mockito.when(dtRepository.findByIdUrl(1L)).thenReturn(externalAccess);
        result = dtRepository.findByIdUrl(1L);
        
        assertEquals(externalAccess.getIdUrl(), result.getIdUrl());
   	}

	@Test
	public void testFindByUrl() {
		String url = "urlPrueba.com";
		ExternalAccess result = new ExternalAccess();
        Mockito.when(dtRepository.findByUrl(url)).thenReturn(externalAccess);
        result = dtRepository.findByUrl(url);
        
        assertEquals(externalAccess.getUrl(), result.getUrl());
	}

	@Test
	public void testFindByIdUrlInQuery() {
		 List<ExternalAccess> list = new ArrayList<ExternalAccess>();
	     List<ExternalAccess> listResult = new ArrayList<ExternalAccess>();
	     list.add(externalAccess);
	     List<Long> listIdUrl = new ArrayList<>();
	     listIdUrl.add(1L);
	     
	     Mockito.when(dtRepository.findByIdUrlInQuery(listIdUrl)).thenReturn(list);
		
	     listResult =  dtRepository.findByIdUrlInQuery(listIdUrl);

		 assertEquals(1,listResult.size());
	     assertEquals(list.get(0).getUrl(), listResult.get(0).getUrl());
	     assertEquals(list.get(0).getIdUrl(), listResult.get(0).getIdUrl());
	     assertEquals(list.get(0).getStateConn(), listResult.get(0).getStateConn());
		
	}

}
