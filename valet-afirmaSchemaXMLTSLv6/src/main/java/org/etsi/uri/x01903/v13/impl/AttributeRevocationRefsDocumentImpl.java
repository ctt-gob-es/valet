/*
 * An XML document type.
 * Localname: AttributeRevocationRefs
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.AttributeRevocationRefsDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one AttributeRevocationRefs(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class AttributeRevocationRefsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.AttributeRevocationRefsDocument
{
    
    public AttributeRevocationRefsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEREVOCATIONREFS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeRevocationRefs");
    
    
    /**
     * Gets the "AttributeRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType getAttributeRevocationRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().find_element_user(ATTRIBUTEREVOCATIONREFS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AttributeRevocationRefs" element
     */
    public void setAttributeRevocationRefs(org.etsi.uri.x01903.v13.CompleteRevocationRefsType attributeRevocationRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().find_element_user(ATTRIBUTEREVOCATIONREFS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().add_element_user(ATTRIBUTEREVOCATIONREFS$0);
            }
            target.set(attributeRevocationRefs);
        }
    }
    
    /**
     * Appends and returns a new empty "AttributeRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType addNewAttributeRevocationRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().add_element_user(ATTRIBUTEREVOCATIONREFS$0);
            return target;
        }
    }
}
