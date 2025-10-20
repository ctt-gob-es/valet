/*
 * An XML document type.
 * Localname: SigAndRefsTimeStamp
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SigAndRefsTimeStampDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one SigAndRefsTimeStamp(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class SigAndRefsTimeStampDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SigAndRefsTimeStampDocument
{
    
    public SigAndRefsTimeStampDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGANDREFSTIMESTAMP$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigAndRefsTimeStamp");
    
    
    /**
     * Gets the "SigAndRefsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getSigAndRefsTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(SIGANDREFSTIMESTAMP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SigAndRefsTimeStamp" element
     */
    public void setSigAndRefsTimeStamp(org.etsi.uri.x01903.v13.XAdESTimeStampType sigAndRefsTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(SIGANDREFSTIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(SIGANDREFSTIMESTAMP$0);
            }
            target.set(sigAndRefsTimeStamp);
        }
    }
    
    /**
     * Appends and returns a new empty "SigAndRefsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewSigAndRefsTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(SIGANDREFSTIMESTAMP$0);
            return target;
        }
    }
}
