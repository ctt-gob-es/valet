/*
 * An XML document type.
 * Localname: AttributeCertificateRefs
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.AttributeCertificateRefsDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one AttributeCertificateRefs(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class AttributeCertificateRefsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.AttributeCertificateRefsDocument
{
    
    public AttributeCertificateRefsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTECERTIFICATEREFS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeCertificateRefs");
    
    
    /**
     * Gets the "AttributeCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType getAttributeCertificateRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().find_element_user(ATTRIBUTECERTIFICATEREFS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AttributeCertificateRefs" element
     */
    public void setAttributeCertificateRefs(org.etsi.uri.x01903.v13.CompleteCertificateRefsType attributeCertificateRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().find_element_user(ATTRIBUTECERTIFICATEREFS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().add_element_user(ATTRIBUTECERTIFICATEREFS$0);
            }
            target.set(attributeCertificateRefs);
        }
    }
    
    /**
     * Appends and returns a new empty "AttributeCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType addNewAttributeCertificateRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().add_element_user(ATTRIBUTECERTIFICATEREFS$0);
            return target;
        }
    }
}
