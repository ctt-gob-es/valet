/*
 * XML Type:  TSPServiceInformationType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.TSPServiceInformationType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML TSPServiceInformationType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class TSPServiceInformationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.TSPServiceInformationType
{
    
    public TSPServiceInformationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICETYPEIDENTIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceTypeIdentifier");
    private static final javax.xml.namespace.QName SERVICENAME$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceName");
    private static final javax.xml.namespace.QName SERVICEDIGITALIDENTITY$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceDigitalIdentity");
    private static final javax.xml.namespace.QName SERVICESTATUS$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceStatus");
    private static final javax.xml.namespace.QName STATUSSTARTINGTIME$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "StatusStartingTime");
    private static final javax.xml.namespace.QName SCHEMESERVICEDEFINITIONURI$10 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeServiceDefinitionURI");
    private static final javax.xml.namespace.QName SERVICESUPPLYPOINTS$12 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceSupplyPoints");
    private static final javax.xml.namespace.QName TSPSERVICEDEFINITIONURI$14 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPServiceDefinitionURI");
    private static final javax.xml.namespace.QName SERVICEINFORMATIONEXTENSIONS$16 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceInformationExtensions");
    
    
    /**
     * Gets the "ServiceTypeIdentifier" element
     */
    public java.lang.String getServiceTypeIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICETYPEIDENTIFIER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ServiceTypeIdentifier" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType xgetServiceTypeIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(SERVICETYPEIDENTIFIER$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ServiceTypeIdentifier" element
     */
    public void setServiceTypeIdentifier(java.lang.String serviceTypeIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICETYPEIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SERVICETYPEIDENTIFIER$0);
            }
            target.setStringValue(serviceTypeIdentifier);
        }
    }
    
    /**
     * Sets (as xml) the "ServiceTypeIdentifier" element
     */
    public void xsetServiceTypeIdentifier(org.etsi.uri.x02231.v2.NonEmptyURIType serviceTypeIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(SERVICETYPEIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().add_element_user(SERVICETYPEIDENTIFIER$0);
            }
            target.set(serviceTypeIdentifier);
        }
    }
    
    /**
     * Gets the "ServiceName" element
     */
    public org.etsi.uri.x02231.v2.InternationalNamesType getServiceName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(SERVICENAME$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceName" element
     */
    public void setServiceName(org.etsi.uri.x02231.v2.InternationalNamesType serviceName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().find_element_user(SERVICENAME$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(SERVICENAME$2);
            }
            target.set(serviceName);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceName" element
     */
    public org.etsi.uri.x02231.v2.InternationalNamesType addNewServiceName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.InternationalNamesType target = null;
            target = (org.etsi.uri.x02231.v2.InternationalNamesType)get_store().add_element_user(SERVICENAME$2);
            return target;
        }
    }
    
    /**
     * Gets the "ServiceDigitalIdentity" element
     */
    public org.etsi.uri.x02231.v2.DigitalIdentityListType getServiceDigitalIdentity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.DigitalIdentityListType target = null;
            target = (org.etsi.uri.x02231.v2.DigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITY$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ServiceDigitalIdentity" element
     */
    public void setServiceDigitalIdentity(org.etsi.uri.x02231.v2.DigitalIdentityListType serviceDigitalIdentity)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.DigitalIdentityListType target = null;
            target = (org.etsi.uri.x02231.v2.DigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITY$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.DigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITY$4);
            }
            target.set(serviceDigitalIdentity);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceDigitalIdentity" element
     */
    public org.etsi.uri.x02231.v2.DigitalIdentityListType addNewServiceDigitalIdentity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.DigitalIdentityListType target = null;
            target = (org.etsi.uri.x02231.v2.DigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITY$4);
            return target;
        }
    }
    
    /**
     * Gets the "ServiceStatus" element
     */
    public java.lang.String getServiceStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICESTATUS$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ServiceStatus" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType xgetServiceStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(SERVICESTATUS$6, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ServiceStatus" element
     */
    public void setServiceStatus(java.lang.String serviceStatus)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICESTATUS$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SERVICESTATUS$6);
            }
            target.setStringValue(serviceStatus);
        }
    }
    
    /**
     * Sets (as xml) the "ServiceStatus" element
     */
    public void xsetServiceStatus(org.etsi.uri.x02231.v2.NonEmptyURIType serviceStatus)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(SERVICESTATUS$6, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().add_element_user(SERVICESTATUS$6);
            }
            target.set(serviceStatus);
        }
    }
    
    /**
     * Gets the "StatusStartingTime" element
     */
    public java.util.Calendar getStatusStartingTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSSTARTINGTIME$8, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "StatusStartingTime" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetStatusStartingTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(STATUSSTARTINGTIME$8, 0);
            return target;
        }
    }
    
    /**
     * Sets the "StatusStartingTime" element
     */
    public void setStatusStartingTime(java.util.Calendar statusStartingTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSSTARTINGTIME$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSSTARTINGTIME$8);
            }
            target.setCalendarValue(statusStartingTime);
        }
    }
    
    /**
     * Sets (as xml) the "StatusStartingTime" element
     */
    public void xsetStatusStartingTime(org.apache.xmlbeans.XmlDateTime statusStartingTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(STATUSSTARTINGTIME$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(STATUSSTARTINGTIME$8);
            }
            target.set(statusStartingTime);
        }
    }
    
    /**
     * Gets the "SchemeServiceDefinitionURI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType getSchemeServiceDefinitionURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMESERVICEDEFINITIONURI$10, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SchemeServiceDefinitionURI" element
     */
    public boolean isSetSchemeServiceDefinitionURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SCHEMESERVICEDEFINITIONURI$10) != 0;
        }
    }
    
    /**
     * Sets the "SchemeServiceDefinitionURI" element
     */
    public void setSchemeServiceDefinitionURI(org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType schemeServiceDefinitionURI)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMESERVICEDEFINITIONURI$10, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMESERVICEDEFINITIONURI$10);
            }
            target.set(schemeServiceDefinitionURI);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeServiceDefinitionURI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType addNewSchemeServiceDefinitionURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMESERVICEDEFINITIONURI$10);
            return target;
        }
    }
    
    /**
     * Unsets the "SchemeServiceDefinitionURI" element
     */
    public void unsetSchemeServiceDefinitionURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SCHEMESERVICEDEFINITIONURI$10, 0);
        }
    }
    
    /**
     * Gets the "ServiceSupplyPoints" element
     */
    public org.etsi.uri.x02231.v2.ServiceSupplyPointsType getServiceSupplyPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceSupplyPointsType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceSupplyPointsType)get_store().find_element_user(SERVICESUPPLYPOINTS$12, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ServiceSupplyPoints" element
     */
    public boolean isSetServiceSupplyPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICESUPPLYPOINTS$12) != 0;
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
            target = (org.etsi.uri.x02231.v2.ServiceSupplyPointsType)get_store().find_element_user(SERVICESUPPLYPOINTS$12, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ServiceSupplyPointsType)get_store().add_element_user(SERVICESUPPLYPOINTS$12);
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
            target = (org.etsi.uri.x02231.v2.ServiceSupplyPointsType)get_store().add_element_user(SERVICESUPPLYPOINTS$12);
            return target;
        }
    }
    
    /**
     * Unsets the "ServiceSupplyPoints" element
     */
    public void unsetServiceSupplyPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICESUPPLYPOINTS$12, 0);
        }
    }
    
    /**
     * Gets the "TSPServiceDefinitionURI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType getTSPServiceDefinitionURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().find_element_user(TSPSERVICEDEFINITIONURI$14, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "TSPServiceDefinitionURI" element
     */
    public boolean isSetTSPServiceDefinitionURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TSPSERVICEDEFINITIONURI$14) != 0;
        }
    }
    
    /**
     * Sets the "TSPServiceDefinitionURI" element
     */
    public void setTSPServiceDefinitionURI(org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType tspServiceDefinitionURI)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().find_element_user(TSPSERVICEDEFINITIONURI$14, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().add_element_user(TSPSERVICEDEFINITIONURI$14);
            }
            target.set(tspServiceDefinitionURI);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPServiceDefinitionURI" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType addNewTSPServiceDefinitionURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyMultiLangURIListType)get_store().add_element_user(TSPSERVICEDEFINITIONURI$14);
            return target;
        }
    }
    
    /**
     * Unsets the "TSPServiceDefinitionURI" element
     */
    public void unsetTSPServiceDefinitionURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TSPSERVICEDEFINITIONURI$14, 0);
        }
    }
    
    /**
     * Gets the "ServiceInformationExtensions" element
     */
    public org.etsi.uri.x02231.v2.ExtensionsListType getServiceInformationExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionsListType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionsListType)get_store().find_element_user(SERVICEINFORMATIONEXTENSIONS$16, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ServiceInformationExtensions" element
     */
    public boolean isSetServiceInformationExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICEINFORMATIONEXTENSIONS$16) != 0;
        }
    }
    
    /**
     * Sets the "ServiceInformationExtensions" element
     */
    public void setServiceInformationExtensions(org.etsi.uri.x02231.v2.ExtensionsListType serviceInformationExtensions)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionsListType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionsListType)get_store().find_element_user(SERVICEINFORMATIONEXTENSIONS$16, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ExtensionsListType)get_store().add_element_user(SERVICEINFORMATIONEXTENSIONS$16);
            }
            target.set(serviceInformationExtensions);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceInformationExtensions" element
     */
    public org.etsi.uri.x02231.v2.ExtensionsListType addNewServiceInformationExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ExtensionsListType target = null;
            target = (org.etsi.uri.x02231.v2.ExtensionsListType)get_store().add_element_user(SERVICEINFORMATIONEXTENSIONS$16);
            return target;
        }
    }
    
    /**
     * Unsets the "ServiceInformationExtensions" element
     */
    public void unsetServiceInformationExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICEINFORMATIONEXTENSIONS$16, 0);
        }
    }
}
