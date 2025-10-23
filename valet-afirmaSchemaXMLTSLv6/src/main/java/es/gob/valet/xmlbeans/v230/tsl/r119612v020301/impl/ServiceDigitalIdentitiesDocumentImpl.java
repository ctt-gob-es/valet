/*
 * An XML document type.
 * Localname: ServiceDigitalIdentities
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ServiceDigitalIdentitiesDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one ServiceDigitalIdentities(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceDigitalIdentitiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentitiesDocument
{
    
    public ServiceDigitalIdentitiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEDIGITALIDENTITIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceDigitalIdentities");
    
    
    /**
     * Gets the "ServiceDigitalIdentities" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType getServiceDigitalIdentities()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceDigitalIdentities" element
     */
    public void setServiceDigitalIdentities(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType serviceDigitalIdentities)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITIES$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITIES$0);
            }
            target.set(serviceDigitalIdentities);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceDigitalIdentities" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType addNewServiceDigitalIdentities()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITIES$0);
            return target;
        }
    }
}
