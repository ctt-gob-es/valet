/*
 * XML Type:  RSAKeyValueType
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.RSAKeyValueType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * An XML RSAKeyValueType(@http://www.w3.org/2000/09/xmldsig#).
 *
 * This is a complex type.
 */
public class RSAKeyValueTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.RSAKeyValueType
{
    
    public RSAKeyValueTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MODULUS$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "Modulus");
    private static final javax.xml.namespace.QName EXPONENT$2 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "Exponent");
    
    
    /**
     * Gets the "Modulus" element
     */
    public byte[] getModulus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MODULUS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "Modulus" element
     */
    public org.w3.x2000.x09.xmldsig.CryptoBinary xgetModulus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CryptoBinary target = null;
            target = (org.w3.x2000.x09.xmldsig.CryptoBinary)get_store().find_element_user(MODULUS$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Modulus" element
     */
    public void setModulus(byte[] modulus)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(MODULUS$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(MODULUS$0);
            }
            target.setByteArrayValue(modulus);
        }
    }
    
    /**
     * Sets (as xml) the "Modulus" element
     */
    public void xsetModulus(org.w3.x2000.x09.xmldsig.CryptoBinary modulus)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CryptoBinary target = null;
            target = (org.w3.x2000.x09.xmldsig.CryptoBinary)get_store().find_element_user(MODULUS$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.CryptoBinary)get_store().add_element_user(MODULUS$0);
            }
            target.set(modulus);
        }
    }
    
    /**
     * Gets the "Exponent" element
     */
    public byte[] getExponent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPONENT$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "Exponent" element
     */
    public org.w3.x2000.x09.xmldsig.CryptoBinary xgetExponent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CryptoBinary target = null;
            target = (org.w3.x2000.x09.xmldsig.CryptoBinary)get_store().find_element_user(EXPONENT$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Exponent" element
     */
    public void setExponent(byte[] exponent)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPONENT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EXPONENT$2);
            }
            target.setByteArrayValue(exponent);
        }
    }
    
    /**
     * Sets (as xml) the "Exponent" element
     */
    public void xsetExponent(org.w3.x2000.x09.xmldsig.CryptoBinary exponent)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CryptoBinary target = null;
            target = (org.w3.x2000.x09.xmldsig.CryptoBinary)get_store().find_element_user(EXPONENT$2, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.CryptoBinary)get_store().add_element_user(EXPONENT$2);
            }
            target.set(exponent);
        }
    }
}
