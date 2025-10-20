/*
 * An XML document type.
 * Localname: SignerRole
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignerRoleDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SignerRole(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SignerRoleDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignerRoleDocument
{
    
    public SignerRoleDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNERROLE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignerRole");
    
    
    /**
     * Gets the "SignerRole" element
     */
    public org.etsi.uri.x01903.v13.SignerRoleType getSignerRole()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignerRoleType target = null;
            target = (org.etsi.uri.x01903.v13.SignerRoleType)get_store().find_element_user(SIGNERROLE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignerRole" element
     */
    public void setSignerRole(org.etsi.uri.x01903.v13.SignerRoleType signerRole)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignerRoleType target = null;
            target = (org.etsi.uri.x01903.v13.SignerRoleType)get_store().find_element_user(SIGNERROLE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignerRoleType)get_store().add_element_user(SIGNERROLE$0);
            }
            target.set(signerRole);
        }
    }
    
    /**
     * Appends and returns a new empty "SignerRole" element
     */
    public org.etsi.uri.x01903.v13.SignerRoleType addNewSignerRole()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignerRoleType target = null;
            target = (org.etsi.uri.x01903.v13.SignerRoleType)get_store().add_element_user(SIGNERROLE$0);
            return target;
        }
    }
}
