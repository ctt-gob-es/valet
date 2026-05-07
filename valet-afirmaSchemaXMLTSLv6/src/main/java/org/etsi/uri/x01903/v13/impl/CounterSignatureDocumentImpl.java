/*
 * An XML document type.
 * Localname: CounterSignature
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CounterSignatureDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one CounterSignature(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class CounterSignatureDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.CounterSignatureDocument
{
    
    public CounterSignatureDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COUNTERSIGNATURE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CounterSignature");
    
    
    /**
     * Gets the "CounterSignature" element
     */
    public org.etsi.uri.x01903.v13.CounterSignatureType getCounterSignature()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CounterSignatureType target = null;
            target = (org.etsi.uri.x01903.v13.CounterSignatureType)get_store().find_element_user(COUNTERSIGNATURE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CounterSignature" element
     */
    public void setCounterSignature(org.etsi.uri.x01903.v13.CounterSignatureType counterSignature)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CounterSignatureType target = null;
            target = (org.etsi.uri.x01903.v13.CounterSignatureType)get_store().find_element_user(COUNTERSIGNATURE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CounterSignatureType)get_store().add_element_user(COUNTERSIGNATURE$0);
            }
            target.set(counterSignature);
        }
    }
    
    /**
     * Appends and returns a new empty "CounterSignature" element
     */
    public org.etsi.uri.x01903.v13.CounterSignatureType addNewCounterSignature()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CounterSignatureType target = null;
            target = (org.etsi.uri.x01903.v13.CounterSignatureType)get_store().add_element_user(COUNTERSIGNATURE$0);
            return target;
        }
    }
}
