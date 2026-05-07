/*
 * An XML document type.
 * Localname: ArchiveTimeStamp
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.ArchiveTimeStampDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one ArchiveTimeStamp(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class ArchiveTimeStampDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.ArchiveTimeStampDocument
{
    
    public ArchiveTimeStampDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ARCHIVETIMESTAMP$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ArchiveTimeStamp");
    
    
    /**
     * Gets the "ArchiveTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getArchiveTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(ARCHIVETIMESTAMP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ArchiveTimeStamp" element
     */
    public void setArchiveTimeStamp(org.etsi.uri.x01903.v13.XAdESTimeStampType archiveTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(ARCHIVETIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(ARCHIVETIMESTAMP$0);
            }
            target.set(archiveTimeStamp);
        }
    }
    
    /**
     * Appends and returns a new empty "ArchiveTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewArchiveTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(ARCHIVETIMESTAMP$0);
            return target;
        }
    }
}
