/*
 * An XML document type.
 * Localname: X509Data
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.X509DataDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one X509Data(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class X509DataDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.X509DataDocument
{
    
    public X509DataDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName X509DATA$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "X509Data");
    
    
    /**
     * Gets the "X509Data" element
     */
    public org.w3.x2000.x09.xmldsig.X509DataType getX509Data()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509DataType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509DataType)get_store().find_element_user(X509DATA$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "X509Data" element
     */
    public void setX509Data(org.w3.x2000.x09.xmldsig.X509DataType x509Data)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509DataType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509DataType)get_store().find_element_user(X509DATA$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.X509DataType)get_store().add_element_user(X509DATA$0);
            }
            target.set(x509Data);
        }
    }
    
    /**
     * Appends and returns a new empty "X509Data" element
     */
    public org.w3.x2000.x09.xmldsig.X509DataType addNewX509Data()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.X509DataType target = null;
            target = (org.w3.x2000.x09.xmldsig.X509DataType)get_store().add_element_user(X509DATA$0);
            return target;
        }
    }
}
