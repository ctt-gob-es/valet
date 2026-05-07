/*
 * An XML document type.
 * Localname: SigningCertificate
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SigningCertificateDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SigningCertificate(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SigningCertificateDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SigningCertificateDocument
{
    
    public SigningCertificateDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNINGCERTIFICATE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigningCertificate");
    
    
    /**
     * Gets the "SigningCertificate" element
     */
    public org.etsi.uri.x01903.v13.CertIDListType getSigningCertificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDListType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDListType)get_store().find_element_user(SIGNINGCERTIFICATE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SigningCertificate" element
     */
    public void setSigningCertificate(org.etsi.uri.x01903.v13.CertIDListType signingCertificate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDListType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDListType)get_store().find_element_user(SIGNINGCERTIFICATE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CertIDListType)get_store().add_element_user(SIGNINGCERTIFICATE$0);
            }
            target.set(signingCertificate);
        }
    }
    
    /**
     * Appends and returns a new empty "SigningCertificate" element
     */
    public org.etsi.uri.x01903.v13.CertIDListType addNewSigningCertificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertIDListType target = null;
            target = (org.etsi.uri.x01903.v13.CertIDListType)get_store().add_element_user(SIGNINGCERTIFICATE$0);
            return target;
        }
    }
}
