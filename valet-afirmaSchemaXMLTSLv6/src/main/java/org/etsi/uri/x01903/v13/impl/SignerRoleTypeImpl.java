/*
 * XML Type:  SignerRoleType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignerRoleType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML SignerRoleType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class SignerRoleTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignerRoleType
{
    
    public SignerRoleTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CLAIMEDROLES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ClaimedRoles");
    private static final javax.xml.namespace.QName CERTIFIEDROLES$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CertifiedRoles");
    
    
    /**
     * Gets the "ClaimedRoles" element
     */
    public org.etsi.uri.x01903.v13.ClaimedRolesListType getClaimedRoles()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ClaimedRolesListType target = null;
            target = (org.etsi.uri.x01903.v13.ClaimedRolesListType)get_store().find_element_user(CLAIMEDROLES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ClaimedRoles" element
     */
    public boolean isSetClaimedRoles()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CLAIMEDROLES$0) != 0;
        }
    }
    
    /**
     * Sets the "ClaimedRoles" element
     */
    public void setClaimedRoles(org.etsi.uri.x01903.v13.ClaimedRolesListType claimedRoles)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ClaimedRolesListType target = null;
            target = (org.etsi.uri.x01903.v13.ClaimedRolesListType)get_store().find_element_user(CLAIMEDROLES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.ClaimedRolesListType)get_store().add_element_user(CLAIMEDROLES$0);
            }
            target.set(claimedRoles);
        }
    }
    
    /**
     * Appends and returns a new empty "ClaimedRoles" element
     */
    public org.etsi.uri.x01903.v13.ClaimedRolesListType addNewClaimedRoles()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ClaimedRolesListType target = null;
            target = (org.etsi.uri.x01903.v13.ClaimedRolesListType)get_store().add_element_user(CLAIMEDROLES$0);
            return target;
        }
    }
    
    /**
     * Unsets the "ClaimedRoles" element
     */
    public void unsetClaimedRoles()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CLAIMEDROLES$0, 0);
        }
    }
    
    /**
     * Gets the "CertifiedRoles" element
     */
    public org.etsi.uri.x01903.v13.CertifiedRolesListType getCertifiedRoles()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertifiedRolesListType target = null;
            target = (org.etsi.uri.x01903.v13.CertifiedRolesListType)get_store().find_element_user(CERTIFIEDROLES$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "CertifiedRoles" element
     */
    public boolean isSetCertifiedRoles()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CERTIFIEDROLES$2) != 0;
        }
    }
    
    /**
     * Sets the "CertifiedRoles" element
     */
    public void setCertifiedRoles(org.etsi.uri.x01903.v13.CertifiedRolesListType certifiedRoles)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertifiedRolesListType target = null;
            target = (org.etsi.uri.x01903.v13.CertifiedRolesListType)get_store().find_element_user(CERTIFIEDROLES$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CertifiedRolesListType)get_store().add_element_user(CERTIFIEDROLES$2);
            }
            target.set(certifiedRoles);
        }
    }
    
    /**
     * Appends and returns a new empty "CertifiedRoles" element
     */
    public org.etsi.uri.x01903.v13.CertifiedRolesListType addNewCertifiedRoles()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertifiedRolesListType target = null;
            target = (org.etsi.uri.x01903.v13.CertifiedRolesListType)get_store().add_element_user(CERTIFIEDROLES$2);
            return target;
        }
    }
    
    /**
     * Unsets the "CertifiedRoles" element
     */
    public void unsetCertifiedRoles()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CERTIFIEDROLES$2, 0);
        }
    }
}
