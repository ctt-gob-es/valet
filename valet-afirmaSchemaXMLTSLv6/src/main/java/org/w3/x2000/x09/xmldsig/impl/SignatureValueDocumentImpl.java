/*
 * An XML document type.
 * Localname: SignatureValue
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.SignatureValueDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one SignatureValue(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class SignatureValueDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.SignatureValueDocument
{
    
    public SignatureValueDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNATUREVALUE$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignatureValue");
    
    
    /**
     * Gets the "SignatureValue" element
     */
    public org.w3.x2000.x09.xmldsig.SignatureValueType getSignatureValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureValueType)get_store().find_element_user(SIGNATUREVALUE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignatureValue" element
     */
    public void setSignatureValue(org.w3.x2000.x09.xmldsig.SignatureValueType signatureValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureValueType)get_store().find_element_user(SIGNATUREVALUE$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.SignatureValueType)get_store().add_element_user(SIGNATUREVALUE$0);
            }
            target.set(signatureValue);
        }
    }
    
    /**
     * Appends and returns a new empty "SignatureValue" element
     */
    public org.w3.x2000.x09.xmldsig.SignatureValueType addNewSignatureValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureValueType)get_store().add_element_user(SIGNATUREVALUE$0);
            return target;
        }
    }
}
