/*
 * An XML document type.
 * Localname: SignedInfo
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.SignedInfoDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one SignedInfo(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class SignedInfoDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.SignedInfoDocument
{
    
    public SignedInfoDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNEDINFO$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignedInfo");
    
    
    /**
     * Gets the "SignedInfo" element
     */
    public org.w3.x2000.x09.xmldsig.SignedInfoType getSignedInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignedInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignedInfoType)get_store().find_element_user(SIGNEDINFO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignedInfo" element
     */
    public void setSignedInfo(org.w3.x2000.x09.xmldsig.SignedInfoType signedInfo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignedInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignedInfoType)get_store().find_element_user(SIGNEDINFO$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.SignedInfoType)get_store().add_element_user(SIGNEDINFO$0);
            }
            target.set(signedInfo);
        }
    }
    
    /**
     * Appends and returns a new empty "SignedInfo" element
     */
    public org.w3.x2000.x09.xmldsig.SignedInfoType addNewSignedInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignedInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignedInfoType)get_store().add_element_user(SIGNEDINFO$0);
            return target;
        }
    }
}
