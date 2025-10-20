/*
 * An XML document type.
 * Localname: TSPInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.TSPInformationDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one TSPInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TSPInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.TSPInformationDocument
{
    
    public TSPInformationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSPINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPInformation");
    
    
    /**
     * Gets the "TSPInformation" element
     */
    public org.etsi.uri.x02231.v2.TSPInformationType getTSPInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSPInformationType)get_store().find_element_user(TSPINFORMATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPInformation" element
     */
    public void setTSPInformation(org.etsi.uri.x02231.v2.TSPInformationType tspInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSPInformationType)get_store().find_element_user(TSPINFORMATION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.TSPInformationType)get_store().add_element_user(TSPINFORMATION$0);
            }
            target.set(tspInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPInformation" element
     */
    public org.etsi.uri.x02231.v2.TSPInformationType addNewTSPInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSPInformationType)get_store().add_element_user(TSPINFORMATION$0);
            return target;
        }
    }
}
