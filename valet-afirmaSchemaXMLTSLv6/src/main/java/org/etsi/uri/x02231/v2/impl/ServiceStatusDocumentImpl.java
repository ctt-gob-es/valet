/*
 * An XML document type.
 * Localname: ServiceStatus
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ServiceStatusDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one ServiceStatus(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceStatusDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ServiceStatusDocument
{
    
    public ServiceStatusDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICESTATUS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceStatus");
    
    
    /**
     * Gets the "ServiceStatus" element
     */
    public java.lang.String getServiceStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICESTATUS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ServiceStatus" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType xgetServiceStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(SERVICESTATUS$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ServiceStatus" element
     */
    public void setServiceStatus(java.lang.String serviceStatus)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICESTATUS$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SERVICESTATUS$0);
            }
            target.setStringValue(serviceStatus);
        }
    }
    
    /**
     * Sets (as xml) the "ServiceStatus" element
     */
    public void xsetServiceStatus(org.etsi.uri.x02231.v2.NonEmptyURIType serviceStatus)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(SERVICESTATUS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().add_element_user(SERVICESTATUS$0);
            }
            target.set(serviceStatus);
        }
    }
}
