/*
 * An XML document type.
 * Localname: ServiceInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ServiceInformationDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one ServiceInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ServiceInformationDocument
{
    
    public ServiceInformationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceInformation");
    
    
    /**
     * Gets the "ServiceInformation" element
     */
    public org.etsi.uri.x02231.v2.TSPServiceInformationType getServiceInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPServiceInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSPServiceInformationType)get_store().find_element_user(SERVICEINFORMATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceInformation" element
     */
    public void setServiceInformation(org.etsi.uri.x02231.v2.TSPServiceInformationType serviceInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPServiceInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSPServiceInformationType)get_store().find_element_user(SERVICEINFORMATION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.TSPServiceInformationType)get_store().add_element_user(SERVICEINFORMATION$0);
            }
            target.set(serviceInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceInformation" element
     */
    public org.etsi.uri.x02231.v2.TSPServiceInformationType addNewServiceInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPServiceInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSPServiceInformationType)get_store().add_element_user(SERVICEINFORMATION$0);
            return target;
        }
    }
}
