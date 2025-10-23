/*
 * XML Type:  TrustServiceProviderListType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.TrustServiceProviderListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML TrustServiceProviderListType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class TrustServiceProviderListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.TrustServiceProviderListType
{
    
    public TrustServiceProviderListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRUSTSERVICEPROVIDER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TrustServiceProvider");
    
    
    /**
     * Gets array of all "TrustServiceProvider" elements
     */
    public org.etsi.uri.x02231.v2.TSPType[] getTrustServiceProviderArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(TRUSTSERVICEPROVIDER$0, targetList);
            org.etsi.uri.x02231.v2.TSPType[] result = new org.etsi.uri.x02231.v2.TSPType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "TrustServiceProvider" element
     */
    public org.etsi.uri.x02231.v2.TSPType getTrustServiceProviderArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPType target = null;
            target = (org.etsi.uri.x02231.v2.TSPType)get_store().find_element_user(TRUSTSERVICEPROVIDER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "TrustServiceProvider" element
     */
    public int sizeOfTrustServiceProviderArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TRUSTSERVICEPROVIDER$0);
        }
    }
    
    /**
     * Sets array of all "TrustServiceProvider" element
     */
    public void setTrustServiceProviderArray(org.etsi.uri.x02231.v2.TSPType[] trustServiceProviderArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(trustServiceProviderArray, TRUSTSERVICEPROVIDER$0);
        }
    }
    
    /**
     * Sets ith "TrustServiceProvider" element
     */
    public void setTrustServiceProviderArray(int i, org.etsi.uri.x02231.v2.TSPType trustServiceProvider)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPType target = null;
            target = (org.etsi.uri.x02231.v2.TSPType)get_store().find_element_user(TRUSTSERVICEPROVIDER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(trustServiceProvider);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "TrustServiceProvider" element
     */
    public org.etsi.uri.x02231.v2.TSPType insertNewTrustServiceProvider(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPType target = null;
            target = (org.etsi.uri.x02231.v2.TSPType)get_store().insert_element_user(TRUSTSERVICEPROVIDER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "TrustServiceProvider" element
     */
    public org.etsi.uri.x02231.v2.TSPType addNewTrustServiceProvider()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPType target = null;
            target = (org.etsi.uri.x02231.v2.TSPType)get_store().add_element_user(TRUSTSERVICEPROVIDER$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "TrustServiceProvider" element
     */
    public void removeTrustServiceProvider(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TRUSTSERVICEPROVIDER$0, i);
        }
    }
}
