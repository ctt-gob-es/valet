/*
 * XML Type:  PostalAddressType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.PostalAddressType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML PostalAddressType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class PostalAddressTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.PostalAddressType
{
    
    public PostalAddressTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STREETADDRESS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "StreetAddress");
    private static final javax.xml.namespace.QName LOCALITY$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "Locality");
    private static final javax.xml.namespace.QName STATEORPROVINCE$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "StateOrProvince");
    private static final javax.xml.namespace.QName POSTALCODE$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "PostalCode");
    private static final javax.xml.namespace.QName COUNTRYNAME$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "CountryName");
    private static final javax.xml.namespace.QName LANG$10 = 
        new javax.xml.namespace.QName("http://www.w3.org/XML/1998/namespace", "lang");
    
    
    /**
     * Gets the "StreetAddress" element
     */
    public java.lang.String getStreetAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STREETADDRESS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "StreetAddress" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyString xgetStreetAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(STREETADDRESS$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "StreetAddress" element
     */
    public void setStreetAddress(java.lang.String streetAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STREETADDRESS$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STREETADDRESS$0);
            }
            target.setStringValue(streetAddress);
        }
    }
    
    /**
     * Sets (as xml) the "StreetAddress" element
     */
    public void xsetStreetAddress(org.etsi.uri.x02231.v2.NonEmptyString streetAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(STREETADDRESS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().add_element_user(STREETADDRESS$0);
            }
            target.set(streetAddress);
        }
    }
    
    /**
     * Gets the "Locality" element
     */
    public java.lang.String getLocality()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LOCALITY$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Locality" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyString xgetLocality()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(LOCALITY$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Locality" element
     */
    public void setLocality(java.lang.String locality)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LOCALITY$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(LOCALITY$2);
            }
            target.setStringValue(locality);
        }
    }
    
    /**
     * Sets (as xml) the "Locality" element
     */
    public void xsetLocality(org.etsi.uri.x02231.v2.NonEmptyString locality)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(LOCALITY$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().add_element_user(LOCALITY$2);
            }
            target.set(locality);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATEORPROVINCE$4, 0);
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
    public org.etsi.uri.x02231.v2.NonEmptyString xgetStateOrProvince()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(STATEORPROVINCE$4, 0);
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
            return get_store().count_elements(STATEORPROVINCE$4) != 0;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATEORPROVINCE$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATEORPROVINCE$4);
            }
            target.setStringValue(stateOrProvince);
        }
    }
    
    /**
     * Sets (as xml) the "StateOrProvince" element
     */
    public void xsetStateOrProvince(org.etsi.uri.x02231.v2.NonEmptyString stateOrProvince)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(STATEORPROVINCE$4, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().add_element_user(STATEORPROVINCE$4);
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
            get_store().remove_element(STATEORPROVINCE$4, 0);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(POSTALCODE$6, 0);
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
    public org.etsi.uri.x02231.v2.NonEmptyString xgetPostalCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(POSTALCODE$6, 0);
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
            return get_store().count_elements(POSTALCODE$6) != 0;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(POSTALCODE$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(POSTALCODE$6);
            }
            target.setStringValue(postalCode);
        }
    }
    
    /**
     * Sets (as xml) the "PostalCode" element
     */
    public void xsetPostalCode(org.etsi.uri.x02231.v2.NonEmptyString postalCode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(POSTALCODE$6, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().add_element_user(POSTALCODE$6);
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
            get_store().remove_element(POSTALCODE$6, 0);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COUNTRYNAME$8, 0);
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
    public org.etsi.uri.x02231.v2.NonEmptyString xgetCountryName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(COUNTRYNAME$8, 0);
            return target;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(COUNTRYNAME$8, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(COUNTRYNAME$8);
            }
            target.setStringValue(countryName);
        }
    }
    
    /**
     * Sets (as xml) the "CountryName" element
     */
    public void xsetCountryName(org.etsi.uri.x02231.v2.NonEmptyString countryName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyString target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().find_element_user(COUNTRYNAME$8, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyString)get_store().add_element_user(COUNTRYNAME$8);
            }
            target.set(countryName);
        }
    }
    
    /**
     * Gets the "lang" attribute
     */
    public java.lang.String getLang()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LANG$10);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "lang" attribute
     */
    public org.apache.xmlbeans.XmlLanguage xgetLang()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlLanguage target = null;
            target = (org.apache.xmlbeans.XmlLanguage)get_store().find_attribute_user(LANG$10);
            return target;
        }
    }
    
    /**
     * Sets the "lang" attribute
     */
    public void setLang(java.lang.String lang)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LANG$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(LANG$10);
            }
            target.setStringValue(lang);
        }
    }
    
    /**
     * Sets (as xml) the "lang" attribute
     */
    public void xsetLang(org.apache.xmlbeans.XmlLanguage lang)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlLanguage target = null;
            target = (org.apache.xmlbeans.XmlLanguage)get_store().find_attribute_user(LANG$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlLanguage)get_store().add_attribute_user(LANG$10);
            }
            target.set(lang);
        }
    }
}
