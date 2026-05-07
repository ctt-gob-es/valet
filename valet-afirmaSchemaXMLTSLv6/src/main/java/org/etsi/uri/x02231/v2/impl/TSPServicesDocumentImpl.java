/*
 * An XML document type.
 * Localname: TSPServices
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.TSPServicesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one TSPServices(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TSPServicesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.TSPServicesDocument
{
    
    public TSPServicesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSPSERVICES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPServices");
    
    
    /**
     * Gets the "TSPServices" element
     */
    public org.etsi.uri.x02231.v2.TSPServicesListType getTSPServices()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPServicesListType target = null;
            target = (org.etsi.uri.x02231.v2.TSPServicesListType)get_store().find_element_user(TSPSERVICES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPServices" element
     */
    public void setTSPServices(org.etsi.uri.x02231.v2.TSPServicesListType tspServices)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPServicesListType target = null;
            target = (org.etsi.uri.x02231.v2.TSPServicesListType)get_store().find_element_user(TSPSERVICES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.TSPServicesListType)get_store().add_element_user(TSPSERVICES$0);
            }
            target.set(tspServices);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPServices" element
     */
    public org.etsi.uri.x02231.v2.TSPServicesListType addNewTSPServices()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPServicesListType target = null;
            target = (org.etsi.uri.x02231.v2.TSPServicesListType)get_store().add_element_user(TSPSERVICES$0);
            return target;
        }
    }
}
