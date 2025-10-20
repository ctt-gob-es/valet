/*
 * An XML document type.
 * Localname: Any
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.AnyDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one Any(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class AnyDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.AnyDocument
{
    
    public AnyDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ANY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "Any");
    
    
    /**
     * Gets the "Any" element
     */
    public org.etsi.uri.x01903.v13.AnyType getAny()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(ANY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Any" element
     */
    public void setAny(org.etsi.uri.x01903.v13.AnyType any)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(ANY$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(ANY$0);
            }
            target.set(any);
        }
    }
    
    /**
     * Appends and returns a new empty "Any" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewAny()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(ANY$0);
            return target;
        }
    }
}
