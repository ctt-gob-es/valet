/*
 * An XML document type.
 * Localname: SignaturePolicyIdentifier
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignaturePolicyIdentifierDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SignaturePolicyIdentifier(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SignaturePolicyIdentifierDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignaturePolicyIdentifierDocument
{
    
    public SignaturePolicyIdentifierDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNATUREPOLICYIDENTIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignaturePolicyIdentifier");
    
    
    /**
     * Gets the "SignaturePolicyIdentifier" element
     */
    public org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType getSignaturePolicyIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType)get_store().find_element_user(SIGNATUREPOLICYIDENTIFIER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignaturePolicyIdentifier" element
     */
    public void setSignaturePolicyIdentifier(org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType signaturePolicyIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType)get_store().find_element_user(SIGNATUREPOLICYIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType)get_store().add_element_user(SIGNATUREPOLICYIDENTIFIER$0);
            }
            target.set(signaturePolicyIdentifier);
        }
    }
    
    /**
     * Appends and returns a new empty "SignaturePolicyIdentifier" element
     */
    public org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType addNewSignaturePolicyIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.SignaturePolicyIdentifierType)get_store().add_element_user(SIGNATUREPOLICYIDENTIFIER$0);
            return target;
        }
    }
}
