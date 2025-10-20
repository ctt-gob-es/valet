/*
 * An XML document type.
 * Localname: Object
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.ObjectDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one Object(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class ObjectDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.ObjectDocument
{
    
    public ObjectDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OBJECT$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "Object");
    
    
    /**
     * Gets the "Object" element
     */
    public org.w3.x2000.x09.xmldsig.ObjectType getObject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ObjectType target = null;
            target = (org.w3.x2000.x09.xmldsig.ObjectType)get_store().find_element_user(OBJECT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Object" element
     */
    public void setObject(org.w3.x2000.x09.xmldsig.ObjectType object)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ObjectType target = null;
            target = (org.w3.x2000.x09.xmldsig.ObjectType)get_store().find_element_user(OBJECT$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.ObjectType)get_store().add_element_user(OBJECT$0);
            }
            target.set(object);
        }
    }
    
    /**
     * Appends and returns a new empty "Object" element
     */
    public org.w3.x2000.x09.xmldsig.ObjectType addNewObject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ObjectType target = null;
            target = (org.w3.x2000.x09.xmldsig.ObjectType)get_store().add_element_user(OBJECT$0);
            return target;
        }
    }
}
