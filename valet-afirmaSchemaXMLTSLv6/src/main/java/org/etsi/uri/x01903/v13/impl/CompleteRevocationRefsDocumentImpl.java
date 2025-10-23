/*
 * An XML document type.
 * Localname: CompleteRevocationRefs
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CompleteRevocationRefsDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one CompleteRevocationRefs(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class CompleteRevocationRefsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CompleteRevocationRefsDocument
{
    
    public CompleteRevocationRefsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMPLETEREVOCATIONREFS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CompleteRevocationRefs");
    
    
    /**
     * Gets the "CompleteRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType getCompleteRevocationRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().find_element_user(COMPLETEREVOCATIONREFS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CompleteRevocationRefs" element
     */
    public void setCompleteRevocationRefs(org.etsi.uri.x01903.v13.CompleteRevocationRefsType completeRevocationRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().find_element_user(COMPLETEREVOCATIONREFS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().add_element_user(COMPLETEREVOCATIONREFS$0);
            }
            target.set(completeRevocationRefs);
        }
    }
    
    /**
     * Appends and returns a new empty "CompleteRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType addNewCompleteRevocationRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().add_element_user(COMPLETEREVOCATIONREFS$0);
            return target;
        }
    }
}
