/*
 * An XML document type.
 * Localname: IndividualDataObjectsTimeStamp
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.IndividualDataObjectsTimeStampDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one IndividualDataObjectsTimeStamp(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class IndividualDataObjectsTimeStampDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.IndividualDataObjectsTimeStampDocument
{
    
    public IndividualDataObjectsTimeStampDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INDIVIDUALDATAOBJECTSTIMESTAMP$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "IndividualDataObjectsTimeStamp");
    
    
    /**
     * Gets the "IndividualDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getIndividualDataObjectsTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(INDIVIDUALDATAOBJECTSTIMESTAMP$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "IndividualDataObjectsTimeStamp" element
     */
    public void setIndividualDataObjectsTimeStamp(org.etsi.uri.x01903.v13.XAdESTimeStampType individualDataObjectsTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(INDIVIDUALDATAOBJECTSTIMESTAMP$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(INDIVIDUALDATAOBJECTSTIMESTAMP$0);
            }
            target.set(individualDataObjectsTimeStamp);
        }
    }
    
    /**
     * Appends and returns a new empty "IndividualDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewIndividualDataObjectsTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(INDIVIDUALDATAOBJECTSTIMESTAMP$0);
            return target;
        }
    }
}
