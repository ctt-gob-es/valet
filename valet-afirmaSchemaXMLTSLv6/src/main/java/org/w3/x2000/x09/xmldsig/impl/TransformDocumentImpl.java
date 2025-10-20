/*
 * An XML document type.
 * Localname: Transform
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.TransformDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one Transform(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class TransformDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.TransformDocument
{
    
    public TransformDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TRANSFORM$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "Transform");
    
    
    /**
     * Gets the "Transform" element
     */
    public org.w3.x2000.x09.xmldsig.TransformType getTransform()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.TransformType target = null;
            target = (org.w3.x2000.x09.xmldsig.TransformType)get_store().find_element_user(TRANSFORM$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Transform" element
     */
    public void setTransform(org.w3.x2000.x09.xmldsig.TransformType transform)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.TransformType target = null;
            target = (org.w3.x2000.x09.xmldsig.TransformType)get_store().find_element_user(TRANSFORM$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.TransformType)get_store().add_element_user(TRANSFORM$0);
            }
            target.set(transform);
        }
    }
    
    /**
     * Appends and returns a new empty "Transform" element
     */
    public org.w3.x2000.x09.xmldsig.TransformType addNewTransform()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.TransformType target = null;
            target = (org.w3.x2000.x09.xmldsig.TransformType)get_store().add_element_user(TRANSFORM$0);
            return target;
        }
    }
}
