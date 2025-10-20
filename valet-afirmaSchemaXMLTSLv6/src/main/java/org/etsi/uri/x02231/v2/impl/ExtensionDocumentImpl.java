/*
 * An XML document type.
 * Localname: Extension
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ExtensionDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one Extension(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ExtensionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ExtensionDocument
{
    
    public ExtensionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXTENSION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "Extension");
    
    
    /**
     * Gets the "Extension" element
     */
    public org.etsi.uri.x02231.v2.ExtensionType getExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionType)get_store().find_element_user(EXTENSION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Extension" element
     */
    public void setExtension(org.etsi.uri.x02231.v2.ExtensionType extension)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionType)get_store().find_element_user(EXTENSION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ExtensionType)get_store().add_element_user(EXTENSION$0);
            }
            target.set(extension);
        }
    }
    
    /**
     * Appends and returns a new empty "Extension" element
     */
    public org.etsi.uri.x02231.v2.ExtensionType addNewExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionType)get_store().add_element_user(EXTENSION$0);
            return target;
        }
    }
}
