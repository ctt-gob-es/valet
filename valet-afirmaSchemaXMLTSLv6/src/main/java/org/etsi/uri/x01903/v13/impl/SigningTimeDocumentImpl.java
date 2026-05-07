/*
 * An XML document type.
 * Localname: SigningTime
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SigningTimeDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SigningTime(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SigningTimeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SigningTimeDocument
{
    
    public SigningTimeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNINGTIME$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigningTime");
    
    
    /**
     * Gets the "SigningTime" element
     */
    public java.util.Calendar getSigningTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SIGNINGTIME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "SigningTime" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetSigningTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(SIGNINGTIME$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "SigningTime" element
     */
    public void setSigningTime(java.util.Calendar signingTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SIGNINGTIME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SIGNINGTIME$0);
            }
            target.setCalendarValue(signingTime);
        }
    }
    
    /**
     * Sets (as xml) the "SigningTime" element
     */
    public void xsetSigningTime(org.apache.xmlbeans.XmlDateTime signingTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(SIGNINGTIME$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(SIGNINGTIME$0);
            }
            target.set(signingTime);
        }
    }
}
