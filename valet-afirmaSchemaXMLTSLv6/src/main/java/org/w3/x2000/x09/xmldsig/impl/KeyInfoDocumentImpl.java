/*
 * An XML document type.
 * Localname: KeyInfo
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.KeyInfoDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one KeyInfo(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class KeyInfoDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.KeyInfoDocument
{
    
    public KeyInfoDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KEYINFO$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "KeyInfo");
    
    
    /**
     * Gets the "KeyInfo" element
     */
    public org.w3.x2000.x09.xmldsig.KeyInfoType getKeyInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyInfoType)get_store().find_element_user(KEYINFO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "KeyInfo" element
     */
    public void setKeyInfo(org.w3.x2000.x09.xmldsig.KeyInfoType keyInfo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyInfoType)get_store().find_element_user(KEYINFO$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.KeyInfoType)get_store().add_element_user(KEYINFO$0);
            }
            target.set(keyInfo);
        }
    }
    
    /**
     * Appends and returns a new empty "KeyInfo" element
     */
    public org.w3.x2000.x09.xmldsig.KeyInfoType addNewKeyInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyInfoType)get_store().add_element_user(KEYINFO$0);
            return target;
        }
    }
}
