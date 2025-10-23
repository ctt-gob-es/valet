/*
 * An XML document type.
 * Localname: SignatureProperties
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.SignaturePropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one SignatureProperties(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class SignaturePropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.SignaturePropertiesDocument
{
    
    public SignaturePropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNATUREPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignatureProperties");
    
    
    /**
     * Gets the "SignatureProperties" element
     */
    public org.w3.x2000.x09.xmldsig.SignaturePropertiesType getSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignaturePropertiesType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignaturePropertiesType)get_store().find_element_user(SIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignatureProperties" element
     */
    public void setSignatureProperties(org.w3.x2000.x09.xmldsig.SignaturePropertiesType signatureProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignaturePropertiesType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignaturePropertiesType)get_store().find_element_user(SIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.SignaturePropertiesType)get_store().add_element_user(SIGNATUREPROPERTIES$0);
            }
            target.set(signatureProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "SignatureProperties" element
     */
    public org.w3.x2000.x09.xmldsig.SignaturePropertiesType addNewSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignaturePropertiesType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignaturePropertiesType)get_store().add_element_user(SIGNATUREPROPERTIES$0);
            return target;
        }
    }
}
