/*
 * XML Type:  DigestAlgAndValueType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.DigestAlgAndValueType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML DigestAlgAndValueType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class DigestAlgAndValueTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.DigestAlgAndValueType
{
    
    public DigestAlgAndValueTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DIGESTMETHOD$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "DigestMethod");
    private static final javax.xml.namespace.QName DIGESTVALUE$2 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "DigestValue");
    
    
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
    
    /**
     * Gets the "DigestValue" element
     */
    public byte[] getDigestValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DIGESTVALUE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "DigestValue" element
     */
    public org.w3.x2000.x09.xmldsig.DigestValueType xgetDigestValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.DigestValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.DigestValueType)get_store().find_element_user(DIGESTVALUE$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "DigestValue" element
     */
    public void setDigestValue(byte[] digestValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DIGESTVALUE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DIGESTVALUE$2);
            }
            target.setByteArrayValue(digestValue);
        }
    }
    
    /**
     * Sets (as xml) the "DigestValue" element
     */
    public void xsetDigestValue(org.w3.x2000.x09.xmldsig.DigestValueType digestValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.DigestValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.DigestValueType)get_store().find_element_user(DIGESTVALUE$2, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.DigestValueType)get_store().add_element_user(DIGESTVALUE$2);
            }
            target.set(digestValue);
        }
    }
}
