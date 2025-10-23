/*
 * An XML document type.
 * Localname: SignedDataObjectProperties
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignedDataObjectPropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SignedDataObjectProperties(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SignedDataObjectPropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignedDataObjectPropertiesDocument
{
    
    public SignedDataObjectPropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNEDDATAOBJECTPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignedDataObjectProperties");
    
    
    /**
     * Gets the "SignedDataObjectProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType getSignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType)get_store().find_element_user(SIGNEDDATAOBJECTPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignedDataObjectProperties" element
     */
    public void setSignedDataObjectProperties(org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType signedDataObjectProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType)get_store().find_element_user(SIGNEDDATAOBJECTPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType)get_store().add_element_user(SIGNEDDATAOBJECTPROPERTIES$0);
            }
            target.set(signedDataObjectProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "SignedDataObjectProperties" element
     */
    public org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType addNewSignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType)get_store().add_element_user(SIGNEDDATAOBJECTPROPERTIES$0);
            return target;
        }
    }
}
