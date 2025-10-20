/*
 * An XML document type.
 * Localname: ReferenceInfo
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.ReferenceInfoDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one ReferenceInfo(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class ReferenceInfoDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.ReferenceInfoDocument
{
    
    public ReferenceInfoDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REFERENCEINFO$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ReferenceInfo");
    
    
    /**
     * Gets the "ReferenceInfo" element
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType getReferenceInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().find_element_user(REFERENCEINFO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ReferenceInfo" element
     */
    public void setReferenceInfo(org.etsi.uri.x01903.v13.ReferenceInfoType referenceInfo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().find_element_user(REFERENCEINFO$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().add_element_user(REFERENCEINFO$0);
            }
            target.set(referenceInfo);
        }
    }
    
    /**
     * Appends and returns a new empty "ReferenceInfo" element
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType addNewReferenceInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().add_element_user(REFERENCEINFO$0);
            return target;
        }
    }
}
