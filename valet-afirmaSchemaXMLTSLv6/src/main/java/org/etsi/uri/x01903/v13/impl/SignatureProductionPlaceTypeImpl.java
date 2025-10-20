/*
 * XML Type:  SignatureProductionPlaceType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignatureProductionPlaceType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML SignatureProductionPlaceType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class SignatureProductionPlaceTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignatureProductionPlaceType
{
    
    public SignatureProductionPlaceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CITY$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "City");
    private static final javax.xml.namespace.QName STATEORPROVINCE$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "StateOrProvince");
    private static final javax.xml.namespace.QName POSTALCODE$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "PostalCode");
    private static final javax.xml.namespace.QName COUNTRYNAME$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CountryName");
    
    
    /**
     * Gets the "City" element
     */
    public java.lang.String getCity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CITY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "City" element
     */
    public org.apache.xmlbeans.XmlString xgetCity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CITY$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "City" element
     */
    public boolean isSetCity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CITY$0) != 0;
        }
    }
    
    /**
     * Sets the "City" element
     */
    public void setCity(java.lang.String city)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(CITY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(CITY$0);
            }
            target.setStringValue(city);
        }
    }
    
    /**
     * Sets (as xml) the "City" element
     */
    public void xsetCity(org.apache.xmlbeans.XmlString city)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(CITY$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(CITY$0);
            }
            target.set(city);
        }
    }
    
    /**
     * Unsets the "City" element
     */
    public void unsetCity()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CITY$0, 0);
        }
    }
    
    /**
     * Gets the "StateOrProvince" element
     */
    public java.lang.String getStateOrProvince()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATEORPROVINCE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "StateOrProvince" element
     */
    public org.apache.xmlbeans.XmlString xgetStateOrProvince()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STATEORPROVINCE$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "StateOrProvince" element
     */
    public boolean isSetStateOrProvince()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(STATEORPROVINCE$2) != 0;
        }
    }
    
    /**
     * Sets the "StateOrProvince" element
     */
    public void setStateOrProvince(java.lang.String stateOrProvince)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATEORPROVINCE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATEORPROVINCE$2);
            }
            target.setStringValue(stateOrProvince);
        }
    }
    
    /**
     * Sets (as xml) the "StateOrProvince" element
     */
    public void xsetStateOrProvince(org.apache.xmlbeans.XmlString stateOrProvince)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STATEORPROVINCE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(STATEORPROVINCE$2);
            }
            target.set(stateOrProvince);
        }
    }
    
    /**
     * Unsets the "StateOrProvince" element
     */
    public void unsetStateOrProvince()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(STATEORPROVINCE$2, 0);
        }
    }
    
    /**
     * Gets the "PostalCode" element
     */
    public java.lang.String getPostalCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(POSTALCODE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "PostalCode" element
     */
    public org.apache.xmlbeans.XmlString xgetPostalCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(POSTALCODE$4, 0);
            return target;
        }
    }
    
    /**
     * True if has "PostalCode" element
     */
    public boolean isSetPostalCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POSTALCODE$4) != 0;
        }
    }
    
    /**
     * Sets the "PostalCode" element
     */
    public void setPostalCode(java.lang.String postalCode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(POSTALCODE$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(POSTALCODE$4);
            }
            target.setStringValue(postalCode);
        }
    }
    
    /**
     * Sets (as xml) the "PostalCode" element
     */
    public void xsetPostalCode(org.apache.xmlbeans.XmlString postalCode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(POSTALCODE$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(POSTALCODE$4);
            }
            target.set(postalCode);
        }
    }
    
    /**
     * Unsets the "PostalCode" element
     */
    public void unsetPostalCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POSTALCODE$4, 0);
        }
    }
    
    /**
     * Gets the "CountryName" element
     */
    public java.lang.String getCountryName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COUNTRYNAME$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "CountryName" element
     */
    public org.apache.xmlbeans.XmlString xgetCountryName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COUNTRYNAME$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "CountryName" element
     */
    public boolean isSetCountryName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COUNTRYNAME$6) != 0;
        }
    }
    
    /**
     * Sets the "CountryName" element
     */
    public void setCountryName(java.lang.String countryName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COUNTRYNAME$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COUNTRYNAME$6);
            }
            target.setStringValue(countryName);
        }
    }
    
    /**
     * Sets (as xml) the "CountryName" element
     */
    public void xsetCountryName(org.apache.xmlbeans.XmlString countryName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(COUNTRYNAME$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(COUNTRYNAME$6);
            }
            target.set(countryName);
        }
    }
    
    /**
     * Unsets the "CountryName" element
     */
    public void unsetCountryName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COUNTRYNAME$6, 0);
        }
    }
}
