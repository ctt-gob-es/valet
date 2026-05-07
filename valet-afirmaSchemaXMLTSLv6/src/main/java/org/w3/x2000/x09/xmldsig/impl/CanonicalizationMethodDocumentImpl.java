/*
 * An XML document type.
 * Localname: CanonicalizationMethod
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.CanonicalizationMethodDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one CanonicalizationMethod(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class CanonicalizationMethodDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.CanonicalizationMethodDocument
{
    
    public CanonicalizationMethodDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CANONICALIZATIONMETHOD$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
    
    
    /**
     * Gets the "CanonicalizationMethod" element
     */
    public org.w3.x2000.x09.xmldsig.CanonicalizationMethodType getCanonicalizationMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CanonicalizationMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.CanonicalizationMethodType)get_store().find_element_user(CANONICALIZATIONMETHOD$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CanonicalizationMethod" element
     */
    public void setCanonicalizationMethod(org.w3.x2000.x09.xmldsig.CanonicalizationMethodType canonicalizationMethod)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CanonicalizationMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.CanonicalizationMethodType)get_store().find_element_user(CANONICALIZATIONMETHOD$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.CanonicalizationMethodType)get_store().add_element_user(CANONICALIZATIONMETHOD$0);
            }
            target.set(canonicalizationMethod);
        }
    }
    
    /**
     * Appends and returns a new empty "CanonicalizationMethod" element
     */
    public org.w3.x2000.x09.xmldsig.CanonicalizationMethodType addNewCanonicalizationMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CanonicalizationMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.CanonicalizationMethodType)get_store().add_element_user(CANONICALIZATIONMETHOD$0);
            return target;
        }
    }
}
