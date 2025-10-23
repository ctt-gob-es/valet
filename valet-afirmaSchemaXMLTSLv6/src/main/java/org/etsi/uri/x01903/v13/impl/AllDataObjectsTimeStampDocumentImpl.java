/*
 * An XML document type.
 * Localname: AllDataObjectsTimeStamp
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.AllDataObjectsTimeStampDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one AllDataObjectsTimeStamp(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class AllDataObjectsTimeStampDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.AllDataObjectsTimeStampDocument
{
    
    public AllDataObjectsTimeStampDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ALLDATAOBJECTSTIMESTAMP$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AllDataObjectsTimeStamp");
    
    
    /**
     * Gets the "AllDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getAllDataObjectsTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(ALLDATAOBJECTSTIMESTAMP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AllDataObjectsTimeStamp" element
     */
    public void setAllDataObjectsTimeStamp(org.etsi.uri.x01903.v13.XAdESTimeStampType allDataObjectsTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(ALLDATAOBJECTSTIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(ALLDATAOBJECTSTIMESTAMP$0);
            }
            target.set(allDataObjectsTimeStamp);
        }
    }
    
    /**
     * Appends and returns a new empty "AllDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewAllDataObjectsTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(ALLDATAOBJECTSTIMESTAMP$0);
            return target;
        }
    }
}
