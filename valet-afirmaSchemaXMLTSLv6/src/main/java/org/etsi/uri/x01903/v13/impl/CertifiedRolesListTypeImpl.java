/*
 * XML Type:  CertifiedRolesListType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CertifiedRolesListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CertifiedRolesListType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CertifiedRolesListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CertifiedRolesListType
{
    
    public CertifiedRolesListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CERTIFIEDROLE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CertifiedRole");
    
    
    /**
     * Gets array of all "CertifiedRole" elements
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] getCertifiedRoleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CERTIFIEDROLE$0, targetList);
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] result = new org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CertifiedRole" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType getCertifiedRoleArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(CERTIFIEDROLE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CertifiedRole" element
     */
    public int sizeOfCertifiedRoleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CERTIFIEDROLE$0);
        }
    }
    
    /**
     * Sets array of all "CertifiedRole" element
     */
    public void setCertifiedRoleArray(org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] certifiedRoleArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(certifiedRoleArray, CERTIFIEDROLE$0);
        }
    }
    
    /**
     * Sets ith "CertifiedRole" element
     */
    public void setCertifiedRoleArray(int i, org.etsi.uri.x01903.v13.EncapsulatedPKIDataType certifiedRole)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(CERTIFIEDROLE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(certifiedRole);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CertifiedRole" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType insertNewCertifiedRole(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().insert_element_user(CERTIFIEDROLE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CertifiedRole" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType addNewCertifiedRole()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().add_element_user(CERTIFIEDROLE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "CertifiedRole" element
     */
    public void removeCertifiedRole(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CERTIFIEDROLE$0, i);
        }
    }
}
