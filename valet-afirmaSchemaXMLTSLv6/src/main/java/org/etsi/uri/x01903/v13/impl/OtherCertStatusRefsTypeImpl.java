/*
 * XML Type:  OtherCertStatusRefsType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.OtherCertStatusRefsType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML OtherCertStatusRefsType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class OtherCertStatusRefsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.OtherCertStatusRefsType
{
    
    public OtherCertStatusRefsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OTHERREF$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "OtherRef");
    
    
    /**
     * Gets array of all "OtherRef" elements
     */
    public org.etsi.uri.x01903.v13.AnyType[] getOtherRefArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OTHERREF$0, targetList);
            org.etsi.uri.x01903.v13.AnyType[] result = new org.etsi.uri.x01903.v13.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "OtherRef" element
     */
    public org.etsi.uri.x01903.v13.AnyType getOtherRefArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(OTHERREF$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "OtherRef" element
     */
    public int sizeOfOtherRefArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHERREF$0);
        }
    }
    
    /**
     * Sets array of all "OtherRef" element
     */
    public void setOtherRefArray(org.etsi.uri.x01903.v13.AnyType[] otherRefArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(otherRefArray, OTHERREF$0);
        }
    }
    
    /**
     * Sets ith "OtherRef" element
     */
    public void setOtherRefArray(int i, org.etsi.uri.x01903.v13.AnyType otherRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(OTHERREF$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(otherRef);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "OtherRef" element
     */
    public org.etsi.uri.x01903.v13.AnyType insertNewOtherRef(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().insert_element_user(OTHERREF$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "OtherRef" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewOtherRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(OTHERREF$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "OtherRef" element
     */
    public void removeOtherRef(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHERREF$0, i);
        }
    }
}
