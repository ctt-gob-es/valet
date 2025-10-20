/*
 * XML Type:  TSPType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.TSPType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML TSPType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class TSPTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.TSPType
{
    
    public TSPTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSPINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPInformation");
    private static final javax.xml.namespace.QName TSPSERVICES$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPServices");
    
    
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
    
    /**
     * Gets the "TSPServices" element
     */
    public org.etsi.uri.x02231.v2.TSPServicesListType getTSPServices()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSPServicesListType target = null;
            target = (org.etsi.uri.x02231.v2.TSPServicesListType)get_store().find_element_user(TSPSERVICES$2, 0);
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
            target = (org.etsi.uri.x02231.v2.TSPServicesListType)get_store().find_element_user(TSPSERVICES$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.TSPServicesListType)get_store().add_element_user(TSPSERVICES$2);
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
            target = (org.etsi.uri.x02231.v2.TSPServicesListType)get_store().add_element_user(TSPSERVICES$2);
            return target;
        }
    }
}
