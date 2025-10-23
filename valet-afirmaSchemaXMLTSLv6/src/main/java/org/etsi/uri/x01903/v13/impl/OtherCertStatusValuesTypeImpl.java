/*
 * XML Type:  OtherCertStatusValuesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.OtherCertStatusValuesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML OtherCertStatusValuesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class OtherCertStatusValuesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.OtherCertStatusValuesType
{
    
    public OtherCertStatusValuesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OTHERVALUE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "OtherValue");
    
    
    /**
     * Gets array of all "OtherValue" elements
     */
    public org.etsi.uri.x01903.v13.AnyType[] getOtherValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OTHERVALUE$0, targetList);
            org.etsi.uri.x01903.v13.AnyType[] result = new org.etsi.uri.x01903.v13.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "OtherValue" element
     */
    public org.etsi.uri.x01903.v13.AnyType getOtherValueArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(OTHERVALUE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "OtherValue" element
     */
    public int sizeOfOtherValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHERVALUE$0);
        }
    }
    
    /**
     * Sets array of all "OtherValue" element
     */
    public void setOtherValueArray(org.etsi.uri.x01903.v13.AnyType[] otherValueArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(otherValueArray, OTHERVALUE$0);
        }
    }
    
    /**
     * Sets ith "OtherValue" element
     */
    public void setOtherValueArray(int i, org.etsi.uri.x01903.v13.AnyType otherValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(OTHERVALUE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(otherValue);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "OtherValue" element
     */
    public org.etsi.uri.x01903.v13.AnyType insertNewOtherValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().insert_element_user(OTHERVALUE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "OtherValue" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewOtherValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(OTHERVALUE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "OtherValue" element
     */
    public void removeOtherValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHERVALUE$0, i);
        }
    }
}
