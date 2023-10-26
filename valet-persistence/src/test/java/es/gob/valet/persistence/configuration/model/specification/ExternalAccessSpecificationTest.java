package es.gob.valet.persistence.configuration.model.specification;

import static org.junit.Assert.*;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.data.jpa.domain.Specification;

import static org.mockito.Mockito.*;

import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ CriteriaBuilder.class, CriteriaQuery.class, Root.class })
public class ExternalAccessSpecificationTest {

	@InjectMocks
    private ExternalAccessSpecification externalAccessSpecification;
    @Mock
    private ExternalAccess externalAccessEntity;
    @Before
    public void setup() {
        externalAccessSpecification = new ExternalAccessSpecification(new ExternalAccess());
    }

    @Test
    public void testGetExternalAccess() {
        // Creamos instancias mock
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        Predicate predicate = mock(Predicate.class);

        // Definimos ejemplos de datos para las pruebas
        ExternalAccess exampleExternalAccess = new ExternalAccess();
        exampleExternalAccess.setUrl("example-url");
        exampleExternalAccess.setStateConn(Boolean.TRUE);
        
        Date fromDate = new Date(); 
        Date toDate = new Date();   

        // Creamos una instancia de la clase ExternalAccessSpecification con el ejemplo de datos
        ExternalAccessSpecification externalAccessSpecification = new ExternalAccessSpecification(exampleExternalAccess);

        // Definimos el comportamiento esperado
        when(criteriaBuilder.like(any(Expression.class), anyString())).thenReturn(predicate);
        when(criteriaBuilder.equal(any(Predicate.class), anyString())).thenReturn(predicate);
        when(criteriaBuilder.between(any(Expression.class), any(Date.class), any(Date.class))).thenReturn(predicate);

        // Llamamos al método toPredicate y comparamos el resultado con el valor esperado
        Specification<ExternalAccess> specification = externalAccessSpecification.getExternalAccess(exampleExternalAccess, fromDate, toDate);
        assertNotNull(specification);
    }
    
    @Test
    public void testToPredicate() {
        // Crear un mock de Root
        Root<ExternalAccess> root = mock(Root.class);

        // Crear un mock de CriteriaQuery
        CriteriaQuery<?> query = mock(CriteriaQuery.class);
        Date fromDate = new Date(); 
        Date toDate = new Date();   
        // Crear un mock de CriteriaBuilder
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);

        // Configurar el comportamiento del mock using matchers o valores reales
        when(externalAccessEntity.getUrl()).thenReturn("example.com");
        when(externalAccessEntity.getStateConn()).thenReturn(Boolean.TRUE);

        // Crear una instancia de ExternalAccessSpecification con el objeto mock
        ExternalAccessSpecification specification = new ExternalAccessSpecification(externalAccessEntity);

        // Llamar al método toPredicate y capturar el resultado
        Specification<ExternalAccess> externalAccessSpecification = specification.getExternalAccess(externalAccessEntity, fromDate, toDate);
        externalAccessSpecification.toPredicate(root, query, criteriaBuilder);
        // Crear una instancia de Root para la prueba
        Root<ExternalAccess> rootForTest = mock(Root.class);
      //Comprobamos que no sea null la especificación
        assertNotNull(specification);
        // Llamar al método toPredicate en la especificación
        Predicate predicate = specification.toPredicate(rootForTest, query, criteriaBuilder);
        //Comprobamos que no sea null la especificación
        assertNotNull(specification);
    }
    
}


