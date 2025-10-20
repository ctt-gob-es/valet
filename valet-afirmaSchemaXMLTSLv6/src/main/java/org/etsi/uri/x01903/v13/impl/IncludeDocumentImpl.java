/*
 * An XML document type.
 * Localname: Include
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.IncludeDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one Include(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class IncludeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.IncludeDocument
{
    
    public IncludeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INCLUDE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "Include");
    
    
    /**
     * Gets the "Include" element
     */
    public org.etsi.uri.x01903.v13.IncludeType getInclude()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IncludeType target = null;
            target = (org.etsi.uri.x01903.v13.IncludeType)get_store().find_element_user(INCLUDE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Include" element
     */
    public void setInclude(org.etsi.uri.x01903.v13.IncludeType include)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IncludeType target = null;
            target = (org.etsi.uri.x01903.v13.IncludeType)get_store().find_element_user(INCLUDE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.IncludeType)get_store().add_element_user(INCLUDE$0);
            }
            target.set(include);
        }
    }
    
    /**
     * Appends and returns a new empty "Include" element
     */
    public org.etsi.uri.x01903.v13.IncludeType addNewInclude()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IncludeType target = null;
            target = (org.etsi.uri.x01903.v13.IncludeType)get_store().add_element_user(INCLUDE$0);
            return target;
        }
    }
}
