/*
 * XML Type:  TrustStatusListType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.TrustStatusListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML TrustStatusListType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class TrustStatusListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.TrustStatusListType
{
    
    public TrustStatusListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SCHEMEINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeInformation");
    private static final javax.xml.namespace.QName TRUSTSERVICEPROVIDERLIST$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TrustServiceProviderList");
    private static final javax.xml.namespace.QName SIGNATURE$4 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "Signature");
    private static final javax.xml.namespace.QName TSLTAG$6 = 
        new javax.xml.namespace.QName("", "TSLTag");
    private static final javax.xml.namespace.QName ID$8 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets the "SchemeInformation" element
     */
    public org.etsi.uri.x02231.v2.TSLSchemeInformationType getSchemeInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSLSchemeInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSLSchemeInformationType)get_store().find_element_user(SCHEMEINFORMATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeInformation" element
     */
    public void setSchemeInformation(org.etsi.uri.x02231.v2.TSLSchemeInformationType schemeInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSLSchemeInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSLSchemeInformationType)get_store().find_element_user(SCHEMEINFORMATION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.TSLSchemeInformationType)get_store().add_element_user(SCHEMEINFORMATION$0);
            }
            target.set(schemeInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeInformation" element
     */
    public org.etsi.uri.x02231.v2.TSLSchemeInformationType addNewSchemeInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TSLSchemeInformationType target = null;
            target = (org.etsi.uri.x02231.v2.TSLSchemeInformationType)get_store().add_element_user(SCHEMEINFORMATION$0);
            return target;
        }
    }
    
    /**
     * Gets the "TrustServiceProviderList" element
     */
    public org.etsi.uri.x02231.v2.TrustServiceProviderListType getTrustServiceProviderList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TrustServiceProviderListType target = null;
            target = (org.etsi.uri.x02231.v2.TrustServiceProviderListType)get_store().find_element_user(TRUSTSERVICEPROVIDERLIST$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "TrustServiceProviderList" element
     */
    public boolean isSetTrustServiceProviderList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TRUSTSERVICEPROVIDERLIST$2) != 0;
        }
    }
    
    /**
     * Sets the "TrustServiceProviderList" element
     */
    public void setTrustServiceProviderList(org.etsi.uri.x02231.v2.TrustServiceProviderListType trustServiceProviderList)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TrustServiceProviderListType target = null;
            target = (org.etsi.uri.x02231.v2.TrustServiceProviderListType)get_store().find_element_user(TRUSTSERVICEPROVIDERLIST$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.TrustServiceProviderListType)get_store().add_element_user(TRUSTSERVICEPROVIDERLIST$2);
            }
            target.set(trustServiceProviderList);
        }
    }
    
    /**
     * Appends and returns a new empty "TrustServiceProviderList" element
     */
    public org.etsi.uri.x02231.v2.TrustServiceProviderListType addNewTrustServiceProviderList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.TrustServiceProviderListType target = null;
            target = (org.etsi.uri.x02231.v2.TrustServiceProviderListType)get_store().add_element_user(TRUSTSERVICEPROVIDERLIST$2);
            return target;
        }
    }
    
    /**
     * Unsets the "TrustServiceProviderList" element
     */
    public void unsetTrustServiceProviderList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TRUSTSERVICEPROVIDERLIST$2, 0);
        }
    }
    
    /**
     * Gets the "Signature" element
     */
    public org.w3.x2000.x09.xmldsig.SignatureType getSignature()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureType)get_store().find_element_user(SIGNATURE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Signature" element
     */
    public boolean isSetSignature()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNATURE$4) != 0;
        }
    }
    
    /**
     * Sets the "Signature" element
     */
    public void setSignature(org.w3.x2000.x09.xmldsig.SignatureType signature)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureType)get_store().find_element_user(SIGNATURE$4, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.SignatureType)get_store().add_element_user(SIGNATURE$4);
            }
            target.set(signature);
        }
    }
    
    /**
     * Appends and returns a new empty "Signature" element
     */
    public org.w3.x2000.x09.xmldsig.SignatureType addNewSignature()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureType)get_store().add_element_user(SIGNATURE$4);
            return target;
        }
    }
    
    /**
     * Unsets the "Signature" element
     */
    public void unsetSignature()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNATURE$4, 0);
        }
    }
    
    /**
     * Gets the "TSLTag" attribute
     */
    public java.lang.String getTSLTag()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TSLTAG$6);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TSLTag" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetTSLTag()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(TSLTAG$6);
            return target;
        }
    }
    
    /**
     * Sets the "TSLTag" attribute
     */
    public void setTSLTag(java.lang.String tslTag)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(TSLTAG$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(TSLTAG$6);
            }
            target.setStringValue(tslTag);
        }
    }
    
    /**
     * Sets (as xml) the "TSLTag" attribute
     */
    public void xsetTSLTag(org.apache.xmlbeans.XmlAnyURI tslTag)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(TSLTAG$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(TSLTAG$6);
            }
            target.set(tslTag);
        }
    }
    
    /**
     * Gets the "Id" attribute
     */
    public java.lang.String getId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$8);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Id" attribute
     */
    public org.apache.xmlbeans.XmlID xgetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$8);
            return target;
        }
    }
    
    /**
     * True if has "Id" attribute
     */
    public boolean isSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ID$8) != null;
        }
    }
    
    /**
     * Sets the "Id" attribute
     */
    public void setId(java.lang.String id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$8);
            }
            target.setStringValue(id);
        }
    }
    
    /**
     * Sets (as xml) the "Id" attribute
     */
    public void xsetId(org.apache.xmlbeans.XmlID id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlID)get_store().add_attribute_user(ID$8);
            }
            target.set(id);
        }
    }
    
    /**
     * Unsets the "Id" attribute
     */
    public void unsetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ID$8);
        }
    }
}
