/*
 * An XML document type.
 * Localname: DataObjectFormat
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.DataObjectFormatDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one DataObjectFormat(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class DataObjectFormatDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.DataObjectFormatDocument
{
    
    public DataObjectFormatDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DATAOBJECTFORMAT$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "DataObjectFormat");
    
    
    /**
     * Gets the "DataObjectFormat" element
     */
    public org.etsi.uri.x01903.v13.DataObjectFormatType getDataObjectFormat()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DataObjectFormatType target = null;
            target = (org.etsi.uri.x01903.v13.DataObjectFormatType)get_store().find_element_user(DATAOBJECTFORMAT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "DataObjectFormat" element
     */
    public void setDataObjectFormat(org.etsi.uri.x01903.v13.DataObjectFormatType dataObjectFormat)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DataObjectFormatType target = null;
            target = (org.etsi.uri.x01903.v13.DataObjectFormatType)get_store().find_element_user(DATAOBJECTFORMAT$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.DataObjectFormatType)get_store().add_element_user(DATAOBJECTFORMAT$0);
            }
            target.set(dataObjectFormat);
        }
    }
    
    /**
     * Appends and returns a new empty "DataObjectFormat" element
     */
    public org.etsi.uri.x01903.v13.DataObjectFormatType addNewDataObjectFormat()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DataObjectFormatType target = null;
            target = (org.etsi.uri.x01903.v13.DataObjectFormatType)get_store().add_element_user(DATAOBJECTFORMAT$0);
            return target;
        }
    }
}
