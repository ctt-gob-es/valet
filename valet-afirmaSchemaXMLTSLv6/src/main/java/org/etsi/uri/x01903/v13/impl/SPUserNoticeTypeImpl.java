/*
 * XML Type:  SPUserNoticeType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SPUserNoticeType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML SPUserNoticeType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class SPUserNoticeTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SPUserNoticeType
{
    
    public SPUserNoticeTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NOTICEREF$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "NoticeRef");
    private static final javax.xml.namespace.QName EXPLICITTEXT$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ExplicitText");
    
    
    /**
     * Gets the "NoticeRef" element
     */
    public org.etsi.uri.x01903.v13.NoticeReferenceType getNoticeRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.NoticeReferenceType target = null;
            target = (org.etsi.uri.x01903.v13.NoticeReferenceType)get_store().find_element_user(NOTICEREF$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "NoticeRef" element
     */
    public boolean isSetNoticeRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NOTICEREF$0) != 0;
        }
    }
    
    /**
     * Sets the "NoticeRef" element
     */
    public void setNoticeRef(org.etsi.uri.x01903.v13.NoticeReferenceType noticeRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.NoticeReferenceType target = null;
            target = (org.etsi.uri.x01903.v13.NoticeReferenceType)get_store().find_element_user(NOTICEREF$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.NoticeReferenceType)get_store().add_element_user(NOTICEREF$0);
            }
            target.set(noticeRef);
        }
    }
    
    /**
     * Appends and returns a new empty "NoticeRef" element
     */
    public org.etsi.uri.x01903.v13.NoticeReferenceType addNewNoticeRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.NoticeReferenceType target = null;
            target = (org.etsi.uri.x01903.v13.NoticeReferenceType)get_store().add_element_user(NOTICEREF$0);
            return target;
        }
    }
    
    /**
     * Unsets the "NoticeRef" element
     */
    public void unsetNoticeRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NOTICEREF$0, 0);
        }
    }
    
    /**
     * Gets the "ExplicitText" element
     */
    public java.lang.String getExplicitText()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPLICITTEXT$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ExplicitText" element
     */
    public org.apache.xmlbeans.XmlString xgetExplicitText()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EXPLICITTEXT$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "ExplicitText" element
     */
    public boolean isSetExplicitText()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(EXPLICITTEXT$2) != 0;
        }
    }
    
    /**
     * Sets the "ExplicitText" element
     */
    public void setExplicitText(java.lang.String explicitText)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(EXPLICITTEXT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(EXPLICITTEXT$2);
            }
            target.setStringValue(explicitText);
        }
    }
    
    /**
     * Sets (as xml) the "ExplicitText" element
     */
    public void xsetExplicitText(org.apache.xmlbeans.XmlString explicitText)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(EXPLICITTEXT$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(EXPLICITTEXT$2);
            }
            target.set(explicitText);
        }
    }
    
    /**
     * Unsets the "ExplicitText" element
     */
    public void unsetExplicitText()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(EXPLICITTEXT$2, 0);
        }
    }
}
