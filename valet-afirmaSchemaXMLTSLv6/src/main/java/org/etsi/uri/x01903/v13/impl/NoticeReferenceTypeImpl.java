/*
 * XML Type:  NoticeReferenceType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.NoticeReferenceType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML NoticeReferenceType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class NoticeReferenceTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.NoticeReferenceType
{
    
    public NoticeReferenceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ORGANIZATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "Organization");
    private static final javax.xml.namespace.QName NOTICENUMBERS$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "NoticeNumbers");
    
    
    /**
     * Gets the "Organization" element
     */
    public java.lang.String getOrganization()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ORGANIZATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Organization" element
     */
    public org.apache.xmlbeans.XmlString xgetOrganization()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ORGANIZATION$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Organization" element
     */
    public void setOrganization(java.lang.String organization)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(ORGANIZATION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(ORGANIZATION$0);
            }
            target.setStringValue(organization);
        }
    }
    
    /**
     * Sets (as xml) the "Organization" element
     */
    public void xsetOrganization(org.apache.xmlbeans.XmlString organization)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(ORGANIZATION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(ORGANIZATION$0);
            }
            target.set(organization);
        }
    }
    
    /**
     * Gets the "NoticeNumbers" element
     */
    public org.etsi.uri.x01903.v13.IntegerListType getNoticeNumbers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IntegerListType target = null;
            target = (org.etsi.uri.x01903.v13.IntegerListType)get_store().find_element_user(NOTICENUMBERS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NoticeNumbers" element
     */
    public void setNoticeNumbers(org.etsi.uri.x01903.v13.IntegerListType noticeNumbers)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IntegerListType target = null;
            target = (org.etsi.uri.x01903.v13.IntegerListType)get_store().find_element_user(NOTICENUMBERS$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.IntegerListType)get_store().add_element_user(NOTICENUMBERS$2);
            }
            target.set(noticeNumbers);
        }
    }
    
    /**
     * Appends and returns a new empty "NoticeNumbers" element
     */
    public org.etsi.uri.x01903.v13.IntegerListType addNewNoticeNumbers()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IntegerListType target = null;
            target = (org.etsi.uri.x01903.v13.IntegerListType)get_store().add_element_user(NOTICENUMBERS$2);
            return target;
        }
    }
}
