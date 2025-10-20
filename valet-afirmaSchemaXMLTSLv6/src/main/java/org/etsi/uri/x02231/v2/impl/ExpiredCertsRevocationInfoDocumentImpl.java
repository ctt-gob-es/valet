/*
 * An XML document type.
 * Localname: ExpiredCertsRevocationInfo
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ExpiredCertsRevocationInfoDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one ExpiredCertsRevocationInfo(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ExpiredCertsRevocationInfoDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ExpiredCertsRevocationInfoDocument
{
    
    public ExpiredCertsRevocationInfoDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXPIREDCERTSREVOCATIONINFO$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ExpiredCertsRevocationInfo");
    
    
    /**
     * Gets the "ExpiredCertsRevocationInfo" element
     */
    public java.util.Calendar getExpiredCertsRevocationInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPIREDCERTSREVOCATIONINFO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "ExpiredCertsRevocationInfo" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetExpiredCertsRevocationInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(EXPIREDCERTSREVOCATIONINFO$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ExpiredCertsRevocationInfo" element
     */
    public void setExpiredCertsRevocationInfo(java.util.Calendar expiredCertsRevocationInfo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPIREDCERTSREVOCATIONINFO$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EXPIREDCERTSREVOCATIONINFO$0);
            }
            target.setCalendarValue(expiredCertsRevocationInfo);
        }
    }
    
    /**
     * Sets (as xml) the "ExpiredCertsRevocationInfo" element
     */
    public void xsetExpiredCertsRevocationInfo(org.apache.xmlbeans.XmlDateTime expiredCertsRevocationInfo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(EXPIREDCERTSREVOCATIONINFO$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(EXPIREDCERTSREVOCATIONINFO$0);
            }
            target.set(expiredCertsRevocationInfo);
        }
    }
}
