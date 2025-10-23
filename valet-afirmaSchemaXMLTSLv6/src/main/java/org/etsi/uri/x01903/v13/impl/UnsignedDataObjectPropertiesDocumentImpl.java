/*
 * An XML document type.
 * Localname: UnsignedDataObjectProperties
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one UnsignedDataObjectProperties(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class UnsignedDataObjectPropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesDocument
{
    
    public UnsignedDataObjectPropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName UNSIGNEDDATAOBJECTPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "UnsignedDataObjectProperties");
    
    
    /**
     * Gets the "UnsignedDataObjectProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType getUnsignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType)get_store().find_element_user(UNSIGNEDDATAOBJECTPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "UnsignedDataObjectProperties" element
     */
    public void setUnsignedDataObjectProperties(org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType unsignedDataObjectProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType)get_store().find_element_user(UNSIGNEDDATAOBJECTPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType)get_store().add_element_user(UNSIGNEDDATAOBJECTPROPERTIES$0);
            }
            target.set(unsignedDataObjectProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "UnsignedDataObjectProperties" element
     */
    public org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType addNewUnsignedDataObjectProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.UnsignedDataObjectPropertiesType)get_store().add_element_user(UNSIGNEDDATAOBJECTPROPERTIES$0);
            return target;
        }
    }
}
