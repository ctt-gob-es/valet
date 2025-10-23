/*
 * An XML document type.
 * Localname: ServiceHistory
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ServiceHistoryDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one ServiceHistory(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceHistoryDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ServiceHistoryDocument
{
    
    public ServiceHistoryDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEHISTORY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceHistory");
    
    
    /**
     * Gets the "ServiceHistory" element
     */
    public org.etsi.uri.x02231.v2.ServiceHistoryType getServiceHistory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceHistoryType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceHistoryType)get_store().find_element_user(SERVICEHISTORY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceHistory" element
     */
    public void setServiceHistory(org.etsi.uri.x02231.v2.ServiceHistoryType serviceHistory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceHistoryType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceHistoryType)get_store().find_element_user(SERVICEHISTORY$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ServiceHistoryType)get_store().add_element_user(SERVICEHISTORY$0);
            }
            target.set(serviceHistory);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceHistory" element
     */
    public org.etsi.uri.x02231.v2.ServiceHistoryType addNewServiceHistory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceHistoryType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceHistoryType)get_store().add_element_user(SERVICEHISTORY$0);
            return target;
        }
    }
}
