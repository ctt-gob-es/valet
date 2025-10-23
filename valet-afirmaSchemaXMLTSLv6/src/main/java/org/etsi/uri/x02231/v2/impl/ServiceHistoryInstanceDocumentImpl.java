/*
 * An XML document type.
 * Localname: ServiceHistoryInstance
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ServiceHistoryInstanceDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one ServiceHistoryInstance(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceHistoryInstanceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ServiceHistoryInstanceDocument
{
    
    public ServiceHistoryInstanceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEHISTORYINSTANCE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceHistoryInstance");
    
    
    /**
     * Gets the "ServiceHistoryInstance" element
     */
    public org.etsi.uri.x02231.v2.ServiceHistoryInstanceType getServiceHistoryInstance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceHistoryInstanceType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceHistoryInstanceType)get_store().find_element_user(SERVICEHISTORYINSTANCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceHistoryInstance" element
     */
    public void setServiceHistoryInstance(org.etsi.uri.x02231.v2.ServiceHistoryInstanceType serviceHistoryInstance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceHistoryInstanceType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceHistoryInstanceType)get_store().find_element_user(SERVICEHISTORYINSTANCE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ServiceHistoryInstanceType)get_store().add_element_user(SERVICEHISTORYINSTANCE$0);
            }
            target.set(serviceHistoryInstance);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceHistoryInstance" element
     */
    public org.etsi.uri.x02231.v2.ServiceHistoryInstanceType addNewServiceHistoryInstance()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceHistoryInstanceType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceHistoryInstanceType)get_store().add_element_user(SERVICEHISTORYINSTANCE$0);
            return target;
        }
    }
}
