/*
 * An XML document type.
 * Localname: UnsignedSignatureProperties
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one UnsignedSignatureProperties(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class UnsignedSignaturePropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesDocument
{
    
    public UnsignedSignaturePropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName UNSIGNEDSIGNATUREPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedSignatureProperties");
    
    
    /**
     * Gets the "UnsignedSignatureProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType getUnsignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType)get_store().find_element_user(UNSIGNEDSIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "UnsignedSignatureProperties" element
     */
    public void setUnsignedSignatureProperties(org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType unsignedSignatureProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType)get_store().find_element_user(UNSIGNEDSIGNATUREPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType)get_store().add_element_user(UNSIGNEDSIGNATUREPROPERTIES$0);
            }
            target.set(unsignedSignatureProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "UnsignedSignatureProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType addNewUnsignedSignatureProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType)get_store().add_element_user(UNSIGNEDSIGNATUREPROPERTIES$0);
            return target;
        }
    }
}
