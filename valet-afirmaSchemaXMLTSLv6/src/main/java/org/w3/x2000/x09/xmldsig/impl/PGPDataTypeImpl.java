/*
 * XML Type:  PGPDataType
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.PGPDataType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * An XML PGPDataType(@http://www.w3.org/2000/09/xmldsig#).
 *
 * This is a complex type.
 */
public class PGPDataTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.PGPDataType
{
    
    public PGPDataTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PGPKEYID$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyID");
    private static final javax.xml.namespace.QName PGPKEYPACKET$2 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "PGPKeyPacket");
    
    
    /**
     * Gets the "PGPKeyID" element
     */
    public byte[] getPGPKeyID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PGPKEYID$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "PGPKeyID" element
     */
    public org.apache.xmlbeans.XmlBase64Binary xgetPGPKeyID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(PGPKEYID$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "PGPKeyID" element
     */
    public boolean isSetPGPKeyID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PGPKEYID$0) != 0;
        }
    }
    
    /**
     * Sets the "PGPKeyID" element
     */
    public void setPGPKeyID(byte[] pgpKeyID)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PGPKEYID$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PGPKEYID$0);
            }
            target.setByteArrayValue(pgpKeyID);
        }
    }
    
    /**
     * Sets (as xml) the "PGPKeyID" element
     */
    public void xsetPGPKeyID(org.apache.xmlbeans.XmlBase64Binary pgpKeyID)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(PGPKEYID$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBase64Binary)get_store().add_element_user(PGPKEYID$0);
            }
            target.set(pgpKeyID);
        }
    }
    
    /**
     * Unsets the "PGPKeyID" element
     */
    public void unsetPGPKeyID()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PGPKEYID$0, 0);
        }
    }
    
    /**
     * Gets the "PGPKeyPacket" element
     */
    public byte[] getPGPKeyPacket()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PGPKEYPACKET$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "PGPKeyPacket" element
     */
    public org.apache.xmlbeans.XmlBase64Binary xgetPGPKeyPacket()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(PGPKEYPACKET$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "PGPKeyPacket" element
     */
    public boolean isSetPGPKeyPacket()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(PGPKEYPACKET$2) != 0;
        }
    }
    
    /**
     * Sets the "PGPKeyPacket" element
     */
    public void setPGPKeyPacket(byte[] pgpKeyPacket)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PGPKEYPACKET$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PGPKEYPACKET$2);
            }
            target.setByteArrayValue(pgpKeyPacket);
        }
    }
    
    /**
     * Sets (as xml) the "PGPKeyPacket" element
     */
    public void xsetPGPKeyPacket(org.apache.xmlbeans.XmlBase64Binary pgpKeyPacket)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(PGPKEYPACKET$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBase64Binary)get_store().add_element_user(PGPKEYPACKET$2);
            }
            target.set(pgpKeyPacket);
        }
    }
    
    /**
     * Unsets the "PGPKeyPacket" element
     */
    public void unsetPGPKeyPacket()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(PGPKEYPACKET$2, 0);
        }
    }
}
