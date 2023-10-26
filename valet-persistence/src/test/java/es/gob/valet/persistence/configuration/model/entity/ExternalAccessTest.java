package es.gob.valet.persistence.configuration.model.entity;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import es.gob.valet.persistence.configuration.model.repository.ExternalAccessRepository;

public class ExternalAccessTest {

	@Mock
	ExternalAccessRepository dtRepository ;
	
	ExternalAccess externalAccess;
	
	Date date = new Date();
	TslCountryRegion tsl = new TslCountryRegion();
	@Before
	public void setUp() throws Exception {
		//insertamos datos de prueba
				externalAccess = new ExternalAccess();
				externalAccess.setIdUrl(1L);
				externalAccess.setLastConn(date);
				externalAccess.setOriginUrl("");
				externalAccess.setStateConn(Boolean.TRUE);
				externalAccess.setTslCountryRegion(tsl);
				externalAccess.setUrl("urlPrueba.com");
	}
		
	@Test
	public void testGets() {
		Long id = 1L;
		
		assertEquals(id,externalAccess.getIdUrl());
		assertEquals(date,externalAccess.getLastConn());
		assertEquals("urlPrueba.com",externalAccess.getUrl());
		assertEquals(Boolean.TRUE,externalAccess.getStateConn());
		assertEquals(tsl,externalAccess.getTslCountryRegion());
		assertEquals("",externalAccess.getOriginUrl());
	}


	@Test
	public void testEqualsObject() {
		assertTrue(externalAccess.equals(externalAccess));
	}
	@Test
	public void testEqualsObjectNull() {
		assertFalse(externalAccess.equals(null));
	}
	@Test
	public void testEqualsObjectCompare() {
		ExternalAccess externalAccess2 = new ExternalAccess();
		externalAccess2.setIdUrl(1L);
		externalAccess2.setLastConn(date);
		externalAccess2.setOriginUrl("");
		externalAccess2.setStateConn(Boolean.TRUE);
		externalAccess2.setTslCountryRegion(tsl);
		externalAccess2.setUrl("urlPrueba.com");
		assertTrue(externalAccess.equals(externalAccess2));
	}
	
	@Test
	public void testEqualsObjectCompareid() {
		ExternalAccess externalAccess2 = new ExternalAccess();
		externalAccess2.setIdUrl(2L);
		externalAccess2.setLastConn(date);
		externalAccess2.setOriginUrl("");
		externalAccess2.setStateConn(Boolean.TRUE);
		externalAccess2.setTslCountryRegion(tsl);
		externalAccess2.setUrl("urlPrueba.com");
		assertFalse(externalAccess.equals(externalAccess2));
	}
	@Test
	public void testEqualsObjectCompareidNull() {
		ExternalAccess externalAccess2 = new ExternalAccess();
		externalAccess2.setIdUrl(null);
		externalAccess2.setLastConn(date);
		externalAccess2.setOriginUrl("");
		externalAccess2.setStateConn(Boolean.TRUE);
		externalAccess2.setTslCountryRegion(tsl);
		externalAccess2.setUrl("urlPrueba.com");
		assertFalse(externalAccess2.equals(externalAccess));
	}

	@Test
	public void testEqualsObjectCompareState() {
		ExternalAccess externalAccess2 = new ExternalAccess();
		externalAccess2.setIdUrl(1L);
		externalAccess2.setLastConn(date);
		externalAccess2.setOriginUrl("");
		externalAccess2.setStateConn(Boolean.FALSE);
		externalAccess2.setTslCountryRegion(tsl);
		externalAccess2.setUrl("urlPrueba.com");
		assertFalse(externalAccess.equals(externalAccess2));
	}
	@Test
	public void testEqualsObjectCompareStateNull() {
		ExternalAccess externalAccess2 = new ExternalAccess();
		externalAccess2.setIdUrl(1L);
		externalAccess2.setLastConn(date);
		externalAccess2.setOriginUrl("");
		externalAccess2.setStateConn(null);
		externalAccess2.setTslCountryRegion(tsl);
		externalAccess2.setUrl("urlPrueba.com");
		assertFalse(externalAccess2.equals(externalAccess));
	}

	@Test
	public void testEqualsObjectCompareDate() {
		ExternalAccess externalAccess2 = new ExternalAccess();
		externalAccess2.setIdUrl(1L);
		externalAccess2.setLastConn(new Date(1992,5,2));
		externalAccess2.setOriginUrl("");
		externalAccess2.setStateConn(Boolean.TRUE);
		externalAccess2.setTslCountryRegion(tsl);
		externalAccess2.setUrl("urlPrueba.com");
		assertFalse(externalAccess.equals(externalAccess2));
	}
	
	@Test
	public void testhasCode() {
		int value= externalAccess.hashCode();
		assertNotNull(value);
	}
}
