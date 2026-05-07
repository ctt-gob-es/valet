/*
 * XML Type:  CRLIdentifierType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CRLIdentifierType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML CRLIdentifierType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class CRLIdentifierTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CRLIdentifierType
{
    
    public CRLIdentifierTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ISSUER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "Issuer");
    private static final javax.xml.namespace.QName ISSUETIME$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "IssueTime");
    private static final javax.xml.namespace.QName NUMBER$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "Number");
    private static final javax.xml.namespace.QName URI$6 = 
        new javax.xml.namespace.QName("", "URI");
    
    
    /**
     * Gets the "Issuer" element
     */
    public java.lang.String getIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ISSUER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Issuer" element
     */
    public org.apache.xmlbeans.XmlString xgetIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ISSUER$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Issuer" element
     */
    public void setIssuer(java.lang.String issuer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ISSUER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ISSUER$0);
            }
            target.setStringValue(issuer);
        }
    }
    
    /**
     * Sets (as xml) the "Issuer" element
     */
    public void xsetIssuer(org.apache.xmlbeans.XmlString issuer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ISSUER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ISSUER$0);
            }
            target.set(issuer);
        }
    }
    
    /**
     * Gets the "IssueTime" element
     */
    public java.util.Calendar getIssueTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ISSUETIME$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "IssueTime" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetIssueTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(ISSUETIME$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "IssueTime" element
     */
    public void setIssueTime(java.util.Calendar issueTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ISSUETIME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ISSUETIME$2);
            }
            target.setCalendarValue(issueTime);
        }
    }
    
    /**
     * Sets (as xml) the "IssueTime" element
     */
    public void xsetIssueTime(org.apache.xmlbeans.XmlDateTime issueTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(ISSUETIME$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(ISSUETIME$2);
            }
            target.set(issueTime);
        }
    }
    
    /**
     * Gets the "Number" element
     */
    public java.math.BigInteger getNumber()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUMBER$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "Number" element
     */
    public org.apache.xmlbeans.XmlInteger xgetNumber()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(NUMBER$4, 0);
            return target;
        }
    }
    
    /**
     * True if has "Number" element
     */
    public boolean isSetNumber()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NUMBER$4) != 0;
        }
    }
    
    /**
     * Sets the "Number" element
     */
    public void setNumber(java.math.BigInteger number)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(NUMBER$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(NUMBER$4);
            }
            target.setBigIntegerValue(number);
        }
    }
    
    /**
     * Sets (as xml) the "Number" element
     */
    public void xsetNumber(org.apache.xmlbeans.XmlInteger number)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(NUMBER$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(NUMBER$4);
            }
            target.set(number);
        }
    }
    
    /**
     * Unsets the "Number" element
     */
    public void unsetNumber()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NUMBER$4, 0);
        }
    }
    
    /**
     * Gets the "URI" attribute
     */
    public java.lang.String getURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(URI$6);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "URI" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(URI$6);
            return target;
        }
    }
    
    /**
     * True if has "URI" attribute
     */
    public boolean isSetURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(URI$6) != null;
        }
    }
    
    /**
     * Sets the "URI" attribute
     */
    public void setURI(java.lang.String uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(URI$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(URI$6);
            }
            target.setStringValue(uri);
        }
    }
    
    /**
     * Sets (as xml) the "URI" attribute
     */
    public void xsetURI(org.apache.xmlbeans.XmlAnyURI uri)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(URI$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(URI$6);
            }
            target.set(uri);
        }
    }
    
    /**
     * Unsets the "URI" attribute
     */
    public void unsetURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(URI$6);
        }
    }
}
