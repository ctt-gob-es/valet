/*
 * An XML document type.
 * Localname: SignatureMethod
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.SignatureMethodDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one SignatureMethod(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class SignatureMethodDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.SignatureMethodDocument
{
    
    public SignatureMethodDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNATUREMETHOD$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignatureMethod");
    
    
    /**
     * Gets the "SignatureMethod" element
     */
    public org.w3.x2000.x09.xmldsig.SignatureMethodType getSignatureMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureMethodType)get_store().find_element_user(SIGNATUREMETHOD$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignatureMethod" element
     */
    public void setSignatureMethod(org.w3.x2000.x09.xmldsig.SignatureMethodType signatureMethod)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureMethodType)get_store().find_element_user(SIGNATUREMETHOD$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.SignatureMethodType)get_store().add_element_user(SIGNATUREMETHOD$0);
            }
            target.set(signatureMethod);
        }
    }
    
    /**
     * Appends and returns a new empty "SignatureMethod" element
     */
    public org.w3.x2000.x09.xmldsig.SignatureMethodType addNewSignatureMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureMethodType)get_store().add_element_user(SIGNATUREMETHOD$0);
            return target;
        }
    }
}
