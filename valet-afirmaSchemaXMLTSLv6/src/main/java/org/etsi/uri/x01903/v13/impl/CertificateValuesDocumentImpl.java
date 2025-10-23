/*
 * An XML document type.
 * Localname: CertificateValues
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CertificateValuesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one CertificateValues(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class CertificateValuesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CertificateValuesDocument
{
    
    public CertificateValuesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CERTIFICATEVALUES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CertificateValues");
    
    
    /**
     * Gets the "CertificateValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType getCertificateValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().find_element_user(CERTIFICATEVALUES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CertificateValues" element
     */
    public void setCertificateValues(org.etsi.uri.x01903.v13.CertificateValuesType certificateValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().find_element_user(CERTIFICATEVALUES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().add_element_user(CERTIFICATEVALUES$0);
            }
            target.set(certificateValues);
        }
    }
    
    /**
     * Appends and returns a new empty "CertificateValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType addNewCertificateValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().add_element_user(CERTIFICATEVALUES$0);
            return target;
        }
    }
}
