/*
 * An XML document type.
 * Localname: SignedProperties
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignedPropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SignedProperties(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SignedPropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignedPropertiesDocument
{
    
    public SignedPropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNEDPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignedProperties");
    
    
    /**
     * Gets the "SignedProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedPropertiesType getSignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedPropertiesType)get_store().find_element_user(SIGNEDPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignedProperties" element
     */
    public void setSignedProperties(org.etsi.uri.x01903.v13.SignedPropertiesType signedProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedPropertiesType)get_store().find_element_user(SIGNEDPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignedPropertiesType)get_store().add_element_user(SIGNEDPROPERTIES$0);
            }
            target.set(signedProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "SignedProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedPropertiesType addNewSignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedPropertiesType)get_store().add_element_user(SIGNEDPROPERTIES$0);
            return target;
        }
    }
}
