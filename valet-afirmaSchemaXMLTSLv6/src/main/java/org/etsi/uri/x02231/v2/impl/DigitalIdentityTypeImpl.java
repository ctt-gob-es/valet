/*
 * XML Type:  DigitalIdentityType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.DigitalIdentityType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML DigitalIdentityType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class DigitalIdentityTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.DigitalIdentityType
{
    
    public DigitalIdentityTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName X509CERTIFICATE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "X509Certificate");
    private static final javax.xml.namespace.QName X509SUBJECTNAME$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "X509SubjectName");
    private static final javax.xml.namespace.QName KEYVALUE$4 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "KeyValue");
    private static final javax.xml.namespace.QName X509SKI$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "X509SKI");
    private static final javax.xml.namespace.QName OTHER$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "Other");
    
    
    /**
     * Gets the "X509Certificate" element
     */
    public byte[] getX509Certificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(X509CERTIFICATE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "X509Certificate" element
     */
    public org.apache.xmlbeans.XmlBase64Binary xgetX509Certificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(X509CERTIFICATE$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "X509Certificate" element
     */
    public boolean isSetX509Certificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(X509CERTIFICATE$0) != 0;
        }
    }
    
    /**
     * Sets the "X509Certificate" element
     */
    public void setX509Certificate(byte[] x509Certificate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(X509CERTIFICATE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(X509CERTIFICATE$0);
            }
            target.setByteArrayValue(x509Certificate);
        }
    }
    
    /**
     * Sets (as xml) the "X509Certificate" element
     */
    public void xsetX509Certificate(org.apache.xmlbeans.XmlBase64Binary x509Certificate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(X509CERTIFICATE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBase64Binary)get_store().add_element_user(X509CERTIFICATE$0);
            }
            target.set(x509Certificate);
        }
    }
    
    /**
     * Unsets the "X509Certificate" element
     */
    public void unsetX509Certificate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(X509CERTIFICATE$0, 0);
        }
    }
    
    /**
     * Gets the "X509SubjectName" element
     */
    public java.lang.String getX509SubjectName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(X509SUBJECTNAME$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "X509SubjectName" element
     */
    public org.apache.xmlbeans.XmlString xgetX509SubjectName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(X509SUBJECTNAME$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "X509SubjectName" element
     */
    public boolean isSetX509SubjectName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(X509SUBJECTNAME$2) != 0;
        }
    }
    
    /**
     * Sets the "X509SubjectName" element
     */
    public void setX509SubjectName(java.lang.String x509SubjectName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(X509SUBJECTNAME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(X509SUBJECTNAME$2);
            }
            target.setStringValue(x509SubjectName);
        }
    }
    
    /**
     * Sets (as xml) the "X509SubjectName" element
     */
    public void xsetX509SubjectName(org.apache.xmlbeans.XmlString x509SubjectName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(X509SUBJECTNAME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(X509SUBJECTNAME$2);
            }
            target.set(x509SubjectName);
        }
    }
    
    /**
     * Unsets the "X509SubjectName" element
     */
    public void unsetX509SubjectName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(X509SUBJECTNAME$2, 0);
        }
    }
    
    /**
     * Gets the "KeyValue" element
     */
    public org.w3.x2000.x09.xmldsig.KeyValueType getKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyValueType)get_store().find_element_user(KEYVALUE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "KeyValue" element
     */
    public boolean isSetKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(KEYVALUE$4) != 0;
        }
    }
    
    /**
     * Sets the "KeyValue" element
     */
    public void setKeyValue(org.w3.x2000.x09.xmldsig.KeyValueType keyValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyValueType)get_store().find_element_user(KEYVALUE$4, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.KeyValueType)get_store().add_element_user(KEYVALUE$4);
            }
            target.set(keyValue);
        }
    }
    
    /**
     * Appends and returns a new empty "KeyValue" element
     */
    public org.w3.x2000.x09.xmldsig.KeyValueType addNewKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyValueType)get_store().add_element_user(KEYVALUE$4);
            return target;
        }
    }
    
    /**
     * Unsets the "KeyValue" element
     */
    public void unsetKeyValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(KEYVALUE$4, 0);
        }
    }
    
    /**
     * Gets the "X509SKI" element
     */
    public byte[] getX509SKI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(X509SKI$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getByteArrayValue();
        }
    }
    
    /**
     * Gets (as xml) the "X509SKI" element
     */
    public org.apache.xmlbeans.XmlBase64Binary xgetX509SKI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(X509SKI$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "X509SKI" element
     */
    public boolean isSetX509SKI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(X509SKI$6) != 0;
        }
    }
    
    /**
     * Sets the "X509SKI" element
     */
    public void setX509SKI(byte[] x509SKI)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(X509SKI$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(X509SKI$6);
            }
            target.setByteArrayValue(x509SKI);
        }
    }
    
    /**
     * Sets (as xml) the "X509SKI" element
     */
    public void xsetX509SKI(org.apache.xmlbeans.XmlBase64Binary x509SKI)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBase64Binary target = null;
            target = (org.apache.xmlbeans.XmlBase64Binary)get_store().find_element_user(X509SKI$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBase64Binary)get_store().add_element_user(X509SKI$6);
            }
            target.set(x509SKI);
        }
    }
    
    /**
     * Unsets the "X509SKI" element
     */
    public void unsetX509SKI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(X509SKI$6, 0);
        }
    }
    
    /**
     * Gets the "Other" element
     */
    public org.etsi.uri.x02231.v2.AnyType getOther()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().find_element_user(OTHER$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Other" element
     */
    public boolean isSetOther()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHER$8) != 0;
        }
    }
    
    /**
     * Sets the "Other" element
     */
    public void setOther(org.etsi.uri.x02231.v2.AnyType other)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().find_element_user(OTHER$8, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.AnyType)get_store().add_element_user(OTHER$8);
            }
            target.set(other);
        }
    }
    
    /**
     * Appends and returns a new empty "Other" element
     */
    public org.etsi.uri.x02231.v2.AnyType addNewOther()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AnyType target = null;
            target = (org.etsi.uri.x02231.v2.AnyType)get_store().add_element_user(OTHER$8);
            return target;
        }
    }
    
    /**
     * Unsets the "Other" element
     */
    public void unsetOther()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHER$8, 0);
        }
    }
}
