/*
 * An XML document type.
 * Localname: RefsOnlyTimeStamp
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.RefsOnlyTimeStampDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one RefsOnlyTimeStamp(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class RefsOnlyTimeStampDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.RefsOnlyTimeStampDocument
{
    
    public RefsOnlyTimeStampDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REFSONLYTIMESTAMP$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "RefsOnlyTimeStamp");
    
    
    /**
     * Gets the "RefsOnlyTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getRefsOnlyTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(REFSONLYTIMESTAMP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RefsOnlyTimeStamp" element
     */
    public void setRefsOnlyTimeStamp(org.etsi.uri.x01903.v13.XAdESTimeStampType refsOnlyTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(REFSONLYTIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(REFSONLYTIMESTAMP$0);
            }
            target.set(refsOnlyTimeStamp);
        }
    }
    
    /**
     * Appends and returns a new empty "RefsOnlyTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewRefsOnlyTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(REFSONLYTIMESTAMP$0);
            return target;
        }
    }
}
