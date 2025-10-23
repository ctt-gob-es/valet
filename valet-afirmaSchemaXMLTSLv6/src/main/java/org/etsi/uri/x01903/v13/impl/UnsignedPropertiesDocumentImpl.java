/*
 * An XML document type.
 * Localname: UnsignedProperties
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.UnsignedPropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one UnsignedProperties(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class UnsignedPropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.UnsignedPropertiesDocument
{
    
    public UnsignedPropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName UNSIGNEDPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedProperties");
    
    
    /**
     * Gets the "UnsignedProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedPropertiesType getUnsignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedPropertiesType)get_store().find_element_user(UNSIGNEDPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "UnsignedProperties" element
     */
    public void setUnsignedProperties(org.etsi.uri.x01903.v13.UnsignedPropertiesType unsignedProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedPropertiesType)get_store().find_element_user(UNSIGNEDPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.UnsignedPropertiesType)get_store().add_element_user(UNSIGNEDPROPERTIES$0);
            }
            target.set(unsignedProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "UnsignedProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedPropertiesType addNewUnsignedProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedPropertiesType)get_store().add_element_user(UNSIGNEDPROPERTIES$0);
            return target;
        }
    }
}
