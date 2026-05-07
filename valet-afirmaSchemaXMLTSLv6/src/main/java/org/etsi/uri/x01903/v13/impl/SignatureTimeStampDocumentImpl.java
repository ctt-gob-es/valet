/*
 * An XML document type.
 * Localname: SignatureTimeStamp
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignatureTimeStampDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SignatureTimeStamp(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SignatureTimeStampDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignatureTimeStampDocument
{
    
    public SignatureTimeStampDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNATURETIMESTAMP$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignatureTimeStamp");
    
    
    /**
     * Gets the "SignatureTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getSignatureTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(SIGNATURETIMESTAMP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignatureTimeStamp" element
     */
    public void setSignatureTimeStamp(org.etsi.uri.x01903.v13.XAdESTimeStampType signatureTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(SIGNATURETIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(SIGNATURETIMESTAMP$0);
            }
            target.set(signatureTimeStamp);
        }
    }
    
    /**
     * Appends and returns a new empty "SignatureTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewSignatureTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(SIGNATURETIMESTAMP$0);
            return target;
        }
    }
}
