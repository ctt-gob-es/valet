/*
 * XML Type:  CertIDListType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CertIDListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CertIDListType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CertIDListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CertIDListType
{
    
    public CertIDListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CERT$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "Cert");
    
    
    /**
     * Gets array of all "Cert" elements
     */
    public org.etsi.uri.x01903.v13.CertIDType[] getCertArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CERT$0, targetList);
            org.etsi.uri.x01903.v13.CertIDType[] result = new org.etsi.uri.x01903.v13.CertIDType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Cert" element
     */
    public org.etsi.uri.x01903.v13.CertIDType getCertArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDType)get_store().find_element_user(CERT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Cert" element
     */
    public int sizeOfCertArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CERT$0);
        }
    }
    
    /**
     * Sets array of all "Cert" element
     */
    public void setCertArray(org.etsi.uri.x01903.v13.CertIDType[] certArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(certArray, CERT$0);
        }
    }
    
    /**
     * Sets ith "Cert" element
     */
    public void setCertArray(int i, org.etsi.uri.x01903.v13.CertIDType cert)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDType)get_store().find_element_user(CERT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(cert);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Cert" element
     */
    public org.etsi.uri.x01903.v13.CertIDType insertNewCert(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDType)get_store().insert_element_user(CERT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Cert" element
     */
    public org.etsi.uri.x01903.v13.CertIDType addNewCert()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDType)get_store().add_element_user(CERT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Cert" element
     */
    public void removeCert(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CERT$0, i);
        }
    }
}
