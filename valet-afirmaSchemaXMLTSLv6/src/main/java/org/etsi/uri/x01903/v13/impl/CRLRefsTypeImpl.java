/*
 * XML Type:  CRLRefsType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CRLRefsType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CRLRefsType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CRLRefsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CRLRefsType
{
    
    public CRLRefsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CRLREF$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CRLRef");
    
    
    /**
     * Gets array of all "CRLRef" elements
     */
    public org.etsi.uri.x01903.v13.CRLRefType[] getCRLRefArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CRLREF$0, targetList);
            org.etsi.uri.x01903.v13.CRLRefType[] result = new org.etsi.uri.x01903.v13.CRLRefType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CRLRef" element
     */
    public org.etsi.uri.x01903.v13.CRLRefType getCRLRefArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLRefType target = null;
            target = (org.etsi.uri.x01903.v13.CRLRefType)get_store().find_element_user(CRLREF$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CRLRef" element
     */
    public int sizeOfCRLRefArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CRLREF$0);
        }
    }
    
    /**
     * Sets array of all "CRLRef" element
     */
    public void setCRLRefArray(org.etsi.uri.x01903.v13.CRLRefType[] crlRefArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(crlRefArray, CRLREF$0);
        }
    }
    
    /**
     * Sets ith "CRLRef" element
     */
    public void setCRLRefArray(int i, org.etsi.uri.x01903.v13.CRLRefType crlRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLRefType target = null;
            target = (org.etsi.uri.x01903.v13.CRLRefType)get_store().find_element_user(CRLREF$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(crlRef);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CRLRef" element
     */
    public org.etsi.uri.x01903.v13.CRLRefType insertNewCRLRef(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLRefType target = null;
            target = (org.etsi.uri.x01903.v13.CRLRefType)get_store().insert_element_user(CRLREF$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CRLRef" element
     */
    public org.etsi.uri.x01903.v13.CRLRefType addNewCRLRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CRLRefType target = null;
            target = (org.etsi.uri.x01903.v13.CRLRefType)get_store().add_element_user(CRLREF$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "CRLRef" element
     */
    public void removeCRLRef(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CRLREF$0, i);
        }
    }
}
