/*
 * XML Type:  ServiceHistoryType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ServiceHistoryType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML ServiceHistoryType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class ServiceHistoryTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ServiceHistoryType
{
    
    public ServiceHistoryTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEHISTORYINSTANCE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceHistoryInstance");
    
    
    /**
     * Gets array of all "ServiceHistoryInstance" elements
     */
    public org.etsi.uri.x02231.v2.ServiceHistoryInstanceType[] getServiceHistoryInstanceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICEHISTORYINSTANCE$0, targetList);
            org.etsi.uri.x02231.v2.ServiceHistoryInstanceType[] result = new org.etsi.uri.x02231.v2.ServiceHistoryInstanceType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceHistoryInstance" element
     */
    public org.etsi.uri.x02231.v2.ServiceHistoryInstanceType getServiceHistoryInstanceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceHistoryInstanceType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceHistoryInstanceType)get_store().find_element_user(SERVICEHISTORYINSTANCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ServiceHistoryInstance" element
     */
    public int sizeOfServiceHistoryInstanceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICEHISTORYINSTANCE$0);
        }
    }
    
    /**
     * Sets array of all "ServiceHistoryInstance" element
     */
    public void setServiceHistoryInstanceArray(org.etsi.uri.x02231.v2.ServiceHistoryInstanceType[] serviceHistoryInstanceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceHistoryInstanceArray, SERVICEHISTORYINSTANCE$0);
        }
    }
    
    /**
     * Sets ith "ServiceHistoryInstance" element
     */
    public void setServiceHistoryInstanceArray(int i, org.etsi.uri.x02231.v2.ServiceHistoryInstanceType serviceHistoryInstance)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceHistoryInstanceType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceHistoryInstanceType)get_store().find_element_user(SERVICEHISTORYINSTANCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceHistoryInstance);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceHistoryInstance" element
     */
    public org.etsi.uri.x02231.v2.ServiceHistoryInstanceType insertNewServiceHistoryInstance(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceHistoryInstanceType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceHistoryInstanceType)get_store().insert_element_user(SERVICEHISTORYINSTANCE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceHistoryInstance" element
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
    
    /**
     * Removes the ith "ServiceHistoryInstance" element
     */
    public void removeServiceHistoryInstance(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICEHISTORYINSTANCE$0, i);
        }
    }
}
