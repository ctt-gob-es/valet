/*
 * An XML document type.
 * Localname: DigestMethod
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.DigestMethodDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one DigestMethod(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class DigestMethodDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.DigestMethodDocument
{
    
    public DigestMethodDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DIGESTMETHOD$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "DigestMethod");
    
    
    /**
     * Gets the "DigestMethod" element
     */
    public org.w3.x2000.x09.xmldsig.DigestMethodType getDigestMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.DigestMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.DigestMethodType)get_store().find_element_user(DIGESTMETHOD$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "DigestMethod" element
     */
    public void setDigestMethod(org.w3.x2000.x09.xmldsig.DigestMethodType digestMethod)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.DigestMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.DigestMethodType)get_store().find_element_user(DIGESTMETHOD$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.DigestMethodType)get_store().add_element_user(DIGESTMETHOD$0);
            }
            target.set(digestMethod);
        }
    }
    
    /**
     * Appends and returns a new empty "DigestMethod" element
     */
    public org.w3.x2000.x09.xmldsig.DigestMethodType addNewDigestMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.DigestMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.DigestMethodType)get_store().add_element_user(DIGESTMETHOD$0);
            return target;
        }
    }
}
