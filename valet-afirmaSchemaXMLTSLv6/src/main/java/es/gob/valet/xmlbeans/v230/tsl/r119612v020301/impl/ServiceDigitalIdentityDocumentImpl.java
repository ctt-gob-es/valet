/*
 * An XML document type.
 * Localname: ServiceDigitalIdentity
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ServiceDigitalIdentityDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one ServiceDigitalIdentity(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceDigitalIdentityDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceDigitalIdentityDocument
{
    
    public ServiceDigitalIdentityDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEDIGITALIDENTITY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceDigitalIdentity");
    
    
    /**
     * Gets the "ServiceDigitalIdentity" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType getServiceDigitalIdentity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceDigitalIdentity" element
     */
    public void setServiceDigitalIdentity(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType serviceDigitalIdentity)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITY$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITY$0);
            }
            target.set(serviceDigitalIdentity);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceDigitalIdentity" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType addNewServiceDigitalIdentity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITY$0);
            return target;
        }
    }
}
