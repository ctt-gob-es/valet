/*
 * XML Type:  OCSPRefsType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.OCSPRefsType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML OCSPRefsType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class OCSPRefsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.OCSPRefsType
{
    
    public OCSPRefsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OCSPREF$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "OCSPRef");
    
    
    /**
     * Gets array of all "OCSPRef" elements
     */
    public org.etsi.uri.x01903.v13.OCSPRefType[] getOCSPRefArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OCSPREF$0, targetList);
            org.etsi.uri.x01903.v13.OCSPRefType[] result = new org.etsi.uri.x01903.v13.OCSPRefType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "OCSPRef" element
     */
    public org.etsi.uri.x01903.v13.OCSPRefType getOCSPRefArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPRefType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPRefType)get_store().find_element_user(OCSPREF$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "OCSPRef" element
     */
    public int sizeOfOCSPRefArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OCSPREF$0);
        }
    }
    
    /**
     * Sets array of all "OCSPRef" element
     */
    public void setOCSPRefArray(org.etsi.uri.x01903.v13.OCSPRefType[] ocspRefArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(ocspRefArray, OCSPREF$0);
        }
    }
    
    /**
     * Sets ith "OCSPRef" element
     */
    public void setOCSPRefArray(int i, org.etsi.uri.x01903.v13.OCSPRefType ocspRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPRefType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPRefType)get_store().find_element_user(OCSPREF$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(ocspRef);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "OCSPRef" element
     */
    public org.etsi.uri.x01903.v13.OCSPRefType insertNewOCSPRef(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPRefType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPRefType)get_store().insert_element_user(OCSPREF$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "OCSPRef" element
     */
    public org.etsi.uri.x01903.v13.OCSPRefType addNewOCSPRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OCSPRefType target = null;
            target = (org.etsi.uri.x01903.v13.OCSPRefType)get_store().add_element_user(OCSPREF$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "OCSPRef" element
     */
    public void removeOCSPRef(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OCSPREF$0, i);
        }
    }
}
