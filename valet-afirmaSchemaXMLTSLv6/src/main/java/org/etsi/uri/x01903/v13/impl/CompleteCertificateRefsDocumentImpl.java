/*
 * An XML document type.
 * Localname: CompleteCertificateRefs
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CompleteCertificateRefsDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one CompleteCertificateRefs(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class CompleteCertificateRefsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CompleteCertificateRefsDocument
{
    
    public CompleteCertificateRefsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMPLETECERTIFICATEREFS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CompleteCertificateRefs");
    
    
    /**
     * Gets the "CompleteCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType getCompleteCertificateRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().find_element_user(COMPLETECERTIFICATEREFS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CompleteCertificateRefs" element
     */
    public void setCompleteCertificateRefs(org.etsi.uri.x01903.v13.CompleteCertificateRefsType completeCertificateRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().find_element_user(COMPLETECERTIFICATEREFS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().add_element_user(COMPLETECERTIFICATEREFS$0);
            }
            target.set(completeCertificateRefs);
        }
    }
    
    /**
     * Appends and returns a new empty "CompleteCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType addNewCompleteCertificateRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().add_element_user(COMPLETECERTIFICATEREFS$0);
            return target;
        }
    }
}
