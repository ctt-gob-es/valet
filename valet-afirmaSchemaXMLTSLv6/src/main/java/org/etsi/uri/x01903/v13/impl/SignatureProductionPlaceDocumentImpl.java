/*
 * An XML document type.
 * Localname: SignatureProductionPlace
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignatureProductionPlaceDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SignatureProductionPlace(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SignatureProductionPlaceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignatureProductionPlaceDocument
{
    
    public SignatureProductionPlaceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNATUREPRODUCTIONPLACE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignatureProductionPlace");
    
    
    /**
     * Gets the "SignatureProductionPlace" element
     */
    public org.etsi.uri.x01903.v13.SignatureProductionPlaceType getSignatureProductionPlace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignatureProductionPlaceType target = null;
            target = (org.etsi.uri.x01903.v13.SignatureProductionPlaceType)get_store().find_element_user(SIGNATUREPRODUCTIONPLACE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignatureProductionPlace" element
     */
    public void setSignatureProductionPlace(org.etsi.uri.x01903.v13.SignatureProductionPlaceType signatureProductionPlace)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignatureProductionPlaceType target = null;
            target = (org.etsi.uri.x01903.v13.SignatureProductionPlaceType)get_store().find_element_user(SIGNATUREPRODUCTIONPLACE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignatureProductionPlaceType)get_store().add_element_user(SIGNATUREPRODUCTIONPLACE$0);
            }
            target.set(signatureProductionPlace);
        }
    }
    
    /**
     * Appends and returns a new empty "SignatureProductionPlace" element
     */
    public org.etsi.uri.x01903.v13.SignatureProductionPlaceType addNewSignatureProductionPlace()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignatureProductionPlaceType target = null;
            target = (org.etsi.uri.x01903.v13.SignatureProductionPlaceType)get_store().add_element_user(SIGNATUREPRODUCTIONPLACE$0);
            return target;
        }
    }
}
