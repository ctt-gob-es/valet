/*
 * XML Type:  OtherTSLPointerType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.OtherTSLPointerType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML OtherTSLPointerType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class OtherTSLPointerTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.OtherTSLPointerType
{
    
    public OtherTSLPointerTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICEDIGITALIDENTITIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceDigitalIdentities");
    private static final javax.xml.namespace.QName TSLLOCATION$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSLLocation");
    private static final javax.xml.namespace.QName ADDITIONALINFORMATION$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "AdditionalInformation");
    
    
    /**
     * Gets the "ServiceDigitalIdentities" element
     */
    public org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType getServiceDigitalIdentities()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ServiceDigitalIdentities" element
     */
    public boolean isSetServiceDigitalIdentities()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICEDIGITALIDENTITIES$0) != 0;
        }
    }
    
    /**
     * Sets the "ServiceDigitalIdentities" element
     */
    public void setServiceDigitalIdentities(org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType serviceDigitalIdentities)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType)get_store().find_element_user(SERVICEDIGITALIDENTITIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITIES$0);
            }
            target.set(serviceDigitalIdentities);
        }
    }
    
    /**
     * Appends and returns a new empty "ServiceDigitalIdentities" element
     */
    public org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType addNewServiceDigitalIdentities()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType target = null;
            target = (org.etsi.uri.x02231.v2.ServiceDigitalIdentityListType)get_store().add_element_user(SERVICEDIGITALIDENTITIES$0);
            return target;
        }
    }
    
    /**
     * Unsets the "ServiceDigitalIdentities" element
     */
    public void unsetServiceDigitalIdentities()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICEDIGITALIDENTITIES$0, 0);
        }
    }
    
    /**
     * Gets the "TSLLocation" element
     */
    public java.lang.String getTSLLocation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLLOCATION$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TSLLocation" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType xgetTSLLocation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(TSLLOCATION$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "TSLLocation" element
     */
    public void setTSLLocation(java.lang.String tslLocation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLLOCATION$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TSLLOCATION$2);
            }
            target.setStringValue(tslLocation);
        }
    }
    
    /**
     * Sets (as xml) the "TSLLocation" element
     */
    public void xsetTSLLocation(org.etsi.uri.x02231.v2.NonEmptyURIType tslLocation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(TSLLOCATION$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().add_element_user(TSLLOCATION$2);
            }
            target.set(tslLocation);
        }
    }
    
    /**
     * Gets the "AdditionalInformation" element
     */
    public org.etsi.uri.x02231.v2.AdditionalInformationType getAdditionalInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AdditionalInformationType target = null;
            target = (org.etsi.uri.x02231.v2.AdditionalInformationType)get_store().find_element_user(ADDITIONALINFORMATION$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "AdditionalInformation" element
     */
    public boolean isSetAdditionalInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ADDITIONALINFORMATION$4) != 0;
        }
    }
    
    /**
     * Sets the "AdditionalInformation" element
     */
    public void setAdditionalInformation(org.etsi.uri.x02231.v2.AdditionalInformationType additionalInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AdditionalInformationType target = null;
            target = (org.etsi.uri.x02231.v2.AdditionalInformationType)get_store().find_element_user(ADDITIONALINFORMATION$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.AdditionalInformationType)get_store().add_element_user(ADDITIONALINFORMATION$4);
            }
            target.set(additionalInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "AdditionalInformation" element
     */
    public org.etsi.uri.x02231.v2.AdditionalInformationType addNewAdditionalInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.AdditionalInformationType target = null;
            target = (org.etsi.uri.x02231.v2.AdditionalInformationType)get_store().add_element_user(ADDITIONALINFORMATION$4);
            return target;
        }
    }
    
    /**
     * Unsets the "AdditionalInformation" element
     */
    public void unsetAdditionalInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ADDITIONALINFORMATION$4, 0);
        }
    }
}
