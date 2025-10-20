/*
 * An XML document type.
 * Localname: ServiceDigitalIdentities
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ServiceDigitalIdentitiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one ServiceDigitalIdentities(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceDigitalIdentitiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ServiceDigitalIdentitiesDocument
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
    public org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType getServiceDigitalIdentities()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITIES$0, 0);
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
    public void setServiceDigitalIdentities(org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType serviceDigitalIdentities)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITIES$0);
            }
            target.set(serviceDigitalIdentities);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceDigitalIdentities" element
     */
    public org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType addNewServiceDigitalIdentities()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITIES$0);
            return target;
        }
    }
}
