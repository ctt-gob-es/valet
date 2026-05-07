/*
 * An XML document type.
 * Localname: ObjectIdentifier
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.ObjectIdentifierDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one ObjectIdentifier(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class ObjectIdentifierDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.ObjectIdentifierDocument
{
    
    public ObjectIdentifierDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OBJECTIDENTIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ObjectIdentifier");
    
    
    /**
     * Gets the "ObjectIdentifier" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType getObjectIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(OBJECTIDENTIFIER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ObjectIdentifier" element
     */
    public void setObjectIdentifier(org.etsi.uri.x01903.v13.ObjectIdentifierType objectIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().find_element_user(OBJECTIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().add_element_user(OBJECTIDENTIFIER$0);
            }
            target.set(objectIdentifier);
        }
    }
    
    /**
     * Appends and returns a new empty "ObjectIdentifier" element
     */
    public org.etsi.uri.x01903.v13.ObjectIdentifierType addNewObjectIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ObjectIdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.ObjectIdentifierType)get_store().add_element_user(OBJECTIDENTIFIER$0);
            return target;
        }
    }
}
