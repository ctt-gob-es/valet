/*
 * An XML document type.
 * Localname: ServiceSupplyPoints
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ServiceSupplyPointsDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one ServiceSupplyPoints(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceSupplyPointsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ServiceSupplyPointsDocument
{
    
    public ServiceSupplyPointsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICESUPPLYPOINTS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceSupplyPoints");
    
    
    /**
     * Gets the "ServiceSupplyPoints" element
     */
    public org.etsi.uri.x02231.v2.ServiceSupplyPointsType getServiceSupplyPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceSupplyPointsType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceSupplyPointsType)get_store().find_element_user(SERVICESUPPLYPOINTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceSupplyPoints" element
     */
    public void setServiceSupplyPoints(org.etsi.uri.x02231.v2.ServiceSupplyPointsType serviceSupplyPoints)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceSupplyPointsType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceSupplyPointsType)get_store().find_element_user(SERVICESUPPLYPOINTS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ServiceSupplyPointsType)get_store().add_element_user(SERVICESUPPLYPOINTS$0);
            }
            target.set(serviceSupplyPoints);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceSupplyPoints" element
     */
    public org.etsi.uri.x02231.v2.ServiceSupplyPointsType addNewServiceSupplyPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceSupplyPointsType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceSupplyPointsType)get_store().add_element_user(SERVICESUPPLYPOINTS$0);
            return target;
        }
    }
}
