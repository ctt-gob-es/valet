/*
 * An XML document type.
 * Localname: RetrievalMethod
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.RetrievalMethodDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one RetrievalMethod(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class RetrievalMethodDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.RetrievalMethodDocument
{
    
    public RetrievalMethodDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RETRIEVALMETHOD$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "RetrievalMethod");
    
    
    /**
     * Gets the "RetrievalMethod" element
     */
    public org.w3.x2000.x09.xmldsig.RetrievalMethodType getRetrievalMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RetrievalMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.RetrievalMethodType)get_store().find_element_user(RETRIEVALMETHOD$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RetrievalMethod" element
     */
    public void setRetrievalMethod(org.w3.x2000.x09.xmldsig.RetrievalMethodType retrievalMethod)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RetrievalMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.RetrievalMethodType)get_store().find_element_user(RETRIEVALMETHOD$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.RetrievalMethodType)get_store().add_element_user(RETRIEVALMETHOD$0);
            }
            target.set(retrievalMethod);
        }
    }
    
    /**
     * Appends and returns a new empty "RetrievalMethod" element
     */
    public org.w3.x2000.x09.xmldsig.RetrievalMethodType addNewRetrievalMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.RetrievalMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.RetrievalMethodType)get_store().add_element_user(RETRIEVALMETHOD$0);
            return target;
        }
    }
}
