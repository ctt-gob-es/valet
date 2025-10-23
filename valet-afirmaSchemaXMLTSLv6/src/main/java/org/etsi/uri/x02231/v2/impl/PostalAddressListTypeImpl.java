/*
 * XML Type:  PostalAddressListType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.PostalAddressListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML PostalAddressListType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class PostalAddressListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.PostalAddressListType
{
    
    public PostalAddressListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POSTALADDRESS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "PostalAddress");
    
    
    /**
     * Gets array of all "PostalAddress" elements
     */
    public org.etsi.uri.x02231.v2.PostalAddressType[] getPostalAddressArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(POSTALADDRESS$0, targetList);
            org.etsi.uri.x02231.v2.PostalAddressType[] result = new org.etsi.uri.x02231.v2.PostalAddressType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "PostalAddress" element
     */
    public org.etsi.uri.x02231.v2.PostalAddressType getPostalAddressArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PostalAddressType target = null;
            target = (org.etsi.uri.x02231.v2.PostalAddressType)get_store().find_element_user(POSTALADDRESS$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "PostalAddress" element
     */
    public int sizeOfPostalAddressArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POSTALADDRESS$0);
        }
    }
    
    /**
     * Sets array of all "PostalAddress" element
     */
    public void setPostalAddressArray(org.etsi.uri.x02231.v2.PostalAddressType[] postalAddressArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(postalAddressArray, POSTALADDRESS$0);
        }
    }
    
    /**
     * Sets ith "PostalAddress" element
     */
    public void setPostalAddressArray(int i, org.etsi.uri.x02231.v2.PostalAddressType postalAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PostalAddressType target = null;
            target = (org.etsi.uri.x02231.v2.PostalAddressType)get_store().find_element_user(POSTALADDRESS$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(postalAddress);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PostalAddress" element
     */
    public org.etsi.uri.x02231.v2.PostalAddressType insertNewPostalAddress(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PostalAddressType target = null;
            target = (org.etsi.uri.x02231.v2.PostalAddressType)get_store().insert_element_user(POSTALADDRESS$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PostalAddress" element
     */
    public org.etsi.uri.x02231.v2.PostalAddressType addNewPostalAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PostalAddressType target = null;
            target = (org.etsi.uri.x02231.v2.PostalAddressType)get_store().add_element_user(POSTALADDRESS$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "PostalAddress" element
     */
    public void removePostalAddress(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POSTALADDRESS$0, i);
        }
    }
}
