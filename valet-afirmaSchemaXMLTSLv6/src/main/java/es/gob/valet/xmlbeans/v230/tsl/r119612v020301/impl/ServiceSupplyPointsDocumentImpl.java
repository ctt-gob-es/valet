/*
 * An XML document type.
 * Localname: ServiceSupplyPoints
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ServiceSupplyPointsDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one ServiceSupplyPoints(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceSupplyPointsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsDocument
{
    
    public ServiceSupplyPointsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICESUPPLYPOINTS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceSupplyPoints");
    
    
    /**
     * Gets the "ServiceSupplyPoints" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType getServiceSupplyPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType)get_store().find_element_user(SERVICESUPPLYPOINTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceSupplyPoints" element
     */
    public void setServiceSupplyPoints(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType serviceSupplyPoints)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType)get_store().find_element_user(SERVICESUPPLYPOINTS$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType)get_store().add_element_user(SERVICESUPPLYPOINTS$0);
            }
            target.set(serviceSupplyPoints);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceSupplyPoints" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType addNewServiceSupplyPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType)get_store().add_element_user(SERVICESUPPLYPOINTS$0);
            return target;
        }
    }
}
