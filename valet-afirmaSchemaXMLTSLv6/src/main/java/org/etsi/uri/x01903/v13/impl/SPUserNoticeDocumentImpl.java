/*
 * An XML document type.
 * Localname: SPUserNotice
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SPUserNoticeDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SPUserNotice(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SPUserNoticeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SPUserNoticeDocument
{
    
    public SPUserNoticeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SPUSERNOTICE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SPUserNotice");
    
    
    /**
     * Gets the "SPUserNotice" element
     */
    public org.etsi.uri.x01903.v13.SPUserNoticeType getSPUserNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SPUserNoticeType target = null;
            target = (org.etsi.uri.x01903.v13.SPUserNoticeType)get_store().find_element_user(SPUSERNOTICE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SPUserNotice" element
     */
    public void setSPUserNotice(org.etsi.uri.x01903.v13.SPUserNoticeType spUserNotice)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SPUserNoticeType target = null;
            target = (org.etsi.uri.x01903.v13.SPUserNoticeType)get_store().find_element_user(SPUSERNOTICE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.SPUserNoticeType)get_store().add_element_user(SPUSERNOTICE$0);
            }
            target.set(spUserNotice);
        }
    }
    
    /**
     * Appends and returns a new empty "SPUserNotice" element
     */
    public org.etsi.uri.x01903.v13.SPUserNoticeType addNewSPUserNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.SPUserNoticeType target = null;
            target = (org.etsi.uri.x01903.v13.SPUserNoticeType)get_store().add_element_user(SPUSERNOTICE$0);
            return target;
        }
    }
}
