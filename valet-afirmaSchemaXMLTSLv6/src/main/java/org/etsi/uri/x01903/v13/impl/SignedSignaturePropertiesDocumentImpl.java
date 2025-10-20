/*
 * An XML document type.
 * Localname: SignedSignatureProperties
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignedSignaturePropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SignedSignatureProperties(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SignedSignaturePropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignedSignaturePropertiesDocument
{
    
    public SignedSignaturePropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNEDSIGNATUREPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignedSignatureProperties");
    
    
    /**
     * Gets the "SignedSignatureProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedSignaturePropertiesType getSignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType)get_store().find_element_user(SIGNEDSIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignedSignatureProperties" element
     */
    public void setSignedSignatureProperties(org.etsi.uri.x01903.v13.SignedSignaturePropertiesType signedSignatureProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType)get_store().find_element_user(SIGNEDSIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType)get_store().add_element_user(SIGNEDSIGNATUREPROPERTIES$0);
            }
            target.set(signedSignatureProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "SignedSignatureProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedSignaturePropertiesType addNewSignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedSignaturePropertiesType)get_store().add_element_user(SIGNEDSIGNATUREPROPERTIES$0);
            return target;
        }
    }
}
