/*
 * XML Type:  ObjectIdentifierType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.ObjectIdentifierType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML ObjectIdentifierType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class ObjectIdentifierTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.ObjectIdentifierType
{
    
    public ObjectIdentifierTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName IDENTIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "Identifier");
    private static final javax.xml.namespace.QName DESCRIPTION$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "Description");
    private static final javax.xml.namespace.QName DOCUMENTATIONREFERENCES$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "DocumentationReferences");
    
    
    /**
     * Gets the "Identifier" element
     */
    public org.etsi.uri.x01903.v13.IdentifierType getIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.IdentifierType)get_store().find_element_user(IDENTIFIER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Identifier" element
     */
    public void setIdentifier(org.etsi.uri.x01903.v13.IdentifierType identifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.IdentifierType)get_store().find_element_user(IDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.IdentifierType)get_store().add_element_user(IDENTIFIER$0);
            }
            target.set(identifier);
        }
    }
    
    /**
     * Appends and returns a new empty "Identifier" element
     */
    public org.etsi.uri.x01903.v13.IdentifierType addNewIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IdentifierType target = null;
            target = (org.etsi.uri.x01903.v13.IdentifierType)get_store().add_element_user(IDENTIFIER$0);
            return target;
        }
    }
    
    /**
     * Gets the "Description" element
     */
    public java.lang.String getDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Description" element
     */
    public org.apache.xmlbeans.XmlString xgetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "Description" element
     */
    public boolean isSetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DESCRIPTION$2) != 0;
        }
    }
    
    /**
     * Sets the "Description" element
     */
    public void setDescription(java.lang.String description)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DESCRIPTION$2);
            }
            target.setStringValue(description);
        }
    }
    
    /**
     * Sets (as xml) the "Description" element
     */
    public void xsetDescription(org.apache.xmlbeans.XmlString description)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DESCRIPTION$2);
            }
            target.set(description);
        }
    }
    
    /**
     * Unsets the "Description" element
     */
    public void unsetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DESCRIPTION$2, 0);
        }
    }
    
    /**
     * Gets the "DocumentationReferences" element
     */
    public org.etsi.uri.x01903.v13.DocumentationReferencesType getDocumentationReferences()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DocumentationReferencesType target = null;
            target = (org.etsi.uri.x01903.v13.DocumentationReferencesType)get_store().find_element_user(DOCUMENTATIONREFERENCES$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "DocumentationReferences" element
     */
    public boolean isSetDocumentationReferences()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DOCUMENTATIONREFERENCES$4) != 0;
        }
    }
    
    /**
     * Sets the "DocumentationReferences" element
     */
    public void setDocumentationReferences(org.etsi.uri.x01903.v13.DocumentationReferencesType documentationReferences)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DocumentationReferencesType target = null;
            target = (org.etsi.uri.x01903.v13.DocumentationReferencesType)get_store().find_element_user(DOCUMENTATIONREFERENCES$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.DocumentationReferencesType)get_store().add_element_user(DOCUMENTATIONREFERENCES$4);
            }
            target.set(documentationReferences);
        }
    }
    
    /**
     * Appends and returns a new empty "DocumentationReferences" element
     */
    public org.etsi.uri.x01903.v13.DocumentationReferencesType addNewDocumentationReferences()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DocumentationReferencesType target = null;
            target = (org.etsi.uri.x01903.v13.DocumentationReferencesType)get_store().add_element_user(DOCUMENTATIONREFERENCES$4);
            return target;
        }
    }
    
    /**
     * Unsets the "DocumentationReferences" element
     */
    public void unsetDocumentationReferences()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DOCUMENTATIONREFERENCES$4, 0);
        }
    }
}
