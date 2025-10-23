/*
 * XML Type:  SignatureMethodType
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.SignatureMethodType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * An XML SignatureMethodType(@http://www.w3.org/2000/09/xmldsig#).
 *
 * This is a complex type.
 */
public class SignatureMethodTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.SignatureMethodType
{
    
    public SignatureMethodTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName HMACOUTPUTLENGTH$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "HMACOutputLength");
    private static final javax.xml.namespace.QName ALGORITHM$2 = 
        new javax.xml.namespace.QName("", "Algorithm");
    
    
    /**
     * Gets the "HMACOutputLength" element
     */
    public java.math.BigInteger getHMACOutputLength()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HMACOUTPUTLENGTH$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "HMACOutputLength" element
     */
    public org.w3.x2000.x09.xmldsig.HMACOutputLengthType xgetHMACOutputLength()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.HMACOutputLengthType target = null;
            target = (org.w3.x2000.x09.xmldsig.HMACOutputLengthType)get_store().find_element_user(HMACOUTPUTLENGTH$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "HMACOutputLength" element
     */
    public boolean isSetHMACOutputLength()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(HMACOUTPUTLENGTH$0) != 0;
        }
    }
    
    /**
     * Sets the "HMACOutputLength" element
     */
    public void setHMACOutputLength(java.math.BigInteger hmacOutputLength)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HMACOUTPUTLENGTH$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(HMACOUTPUTLENGTH$0);
            }
            target.setBigIntegerValue(hmacOutputLength);
        }
    }
    
    /**
     * Sets (as xml) the "HMACOutputLength" element
     */
    public void xsetHMACOutputLength(org.w3.x2000.x09.xmldsig.HMACOutputLengthType hmacOutputLength)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.HMACOutputLengthType target = null;
            target = (org.w3.x2000.x09.xmldsig.HMACOutputLengthType)get_store().find_element_user(HMACOUTPUTLENGTH$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.HMACOutputLengthType)get_store().add_element_user(HMACOUTPUTLENGTH$0);
            }
            target.set(hmacOutputLength);
        }
    }
    
    /**
     * Unsets the "HMACOutputLength" element
     */
    public void unsetHMACOutputLength()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(HMACOUTPUTLENGTH$0, 0);
        }
    }
    
    /**
     * Gets the "Algorithm" attribute
     */
    public java.lang.String getAlgorithm()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ALGORITHM$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Algorithm" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetAlgorithm()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(ALGORITHM$2);
            return target;
        }
    }
    
    /**
     * Sets the "Algorithm" attribute
     */
    public void setAlgorithm(java.lang.String algorithm)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ALGORITHM$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ALGORITHM$2);
            }
            target.setStringValue(algorithm);
        }
    }
    
    /**
     * Sets (as xml) the "Algorithm" attribute
     */
    public void xsetAlgorithm(org.apache.xmlbeans.XmlAnyURI algorithm)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(ALGORITHM$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(ALGORITHM$2);
            }
            target.set(algorithm);
        }
    }
}
