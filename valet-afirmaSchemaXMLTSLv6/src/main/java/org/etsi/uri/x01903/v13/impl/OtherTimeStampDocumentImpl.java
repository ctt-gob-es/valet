/*
 * An XML document type.
 * Localname: OtherTimeStamp
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.OtherTimeStampDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one OtherTimeStamp(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class OtherTimeStampDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.OtherTimeStampDocument
{
    
    public OtherTimeStampDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OTHERTIMESTAMP$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "OtherTimeStamp");
    
    
    /**
     * Gets the "OtherTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.OtherTimeStampType getOtherTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OtherTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.OtherTimeStampType)get_store().find_element_user(OTHERTIMESTAMP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "OtherTimeStamp" element
     */
    public void setOtherTimeStamp(org.etsi.uri.x01903.v13.OtherTimeStampType otherTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OtherTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.OtherTimeStampType)get_store().find_element_user(OTHERTIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.OtherTimeStampType)get_store().add_element_user(OTHERTIMESTAMP$0);
            }
            target.set(otherTimeStamp);
        }
    }
    
    /**
     * Appends and returns a new empty "OtherTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.OtherTimeStampType addNewOtherTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.OtherTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.OtherTimeStampType)get_store().add_element_user(OTHERTIMESTAMP$0);
            return target;
        }
    }
}
