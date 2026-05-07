/*
 * An XML document type.
 * Localname: XAdESTimeStamp
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.XAdESTimeStampDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one XAdESTimeStamp(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class XAdESTimeStampDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.XAdESTimeStampDocument
{
    
    public XAdESTimeStampDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName XADESTIMESTAMP$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "XAdESTimeStamp");
    
    
    /**
     * Gets the "XAdESTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getXAdESTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(XADESTIMESTAMP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "XAdESTimeStamp" element
     */
    public void setXAdESTimeStamp(org.etsi.uri.x01903.v13.XAdESTimeStampType xAdESTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(XADESTIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(XADESTIMESTAMP$0);
            }
            target.set(xAdESTimeStamp);
        }
    }
    
    /**
     * Appends and returns a new empty "XAdESTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewXAdESTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(XADESTIMESTAMP$0);
            return target;
        }
    }
}
