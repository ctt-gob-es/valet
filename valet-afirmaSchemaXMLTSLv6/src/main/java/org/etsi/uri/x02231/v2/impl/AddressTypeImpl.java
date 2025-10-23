/*
 * XML Type:  AddressType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.AddressType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * An XML AddressType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class AddressTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.AddressType
{
    
    public AddressTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POSTALADDRESSES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "PostalAddresses");
    private static final javax.xml.namespace.QName ELECTRONICADDRESS$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ElectronicAddress");
    
    
    /**
     * Gets the "PostalAddresses" element
     */
    public org.etsi.uri.x02231.v2.PostalAddressListType getPostalAddresses()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PostalAddressListType target = null;
            target = (org.etsi.uri.x02231.v2.PostalAddressListType)get_store().find_element_user(POSTALADDRESSES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PostalAddresses" element
     */
    public void setPostalAddresses(org.etsi.uri.x02231.v2.PostalAddressListType postalAddresses)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PostalAddressListType target = null;
            target = (org.etsi.uri.x02231.v2.PostalAddressListType)get_store().find_element_user(POSTALADDRESSES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.PostalAddressListType)get_store().add_element_user(POSTALADDRESSES$0);
            }
            target.set(postalAddresses);
        }
    }
    
    /**
     * Appends and returns a new empty "PostalAddresses" element
     */
    public org.etsi.uri.x02231.v2.PostalAddressListType addNewPostalAddresses()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.PostalAddressListType target = null;
            target = (org.etsi.uri.x02231.v2.PostalAddressListType)get_store().add_element_user(POSTALADDRESSES$0);
            return target;
        }
    }
    
    /**
     * Gets the "ElectronicAddress" element
     */
    public org.etsi.uri.x02231.v2.ElectronicAddressType getElectronicAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ElectronicAddressType target = null;
            target = (org.etsi.uri.x02231.v2.ElectronicAddressType)get_store().find_element_user(ELECTRONICADDRESS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ElectronicAddress" element
     */
    public void setElectronicAddress(org.etsi.uri.x02231.v2.ElectronicAddressType electronicAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ElectronicAddressType target = null;
            target = (org.etsi.uri.x02231.v2.ElectronicAddressType)get_store().find_element_user(ELECTRONICADDRESS$2, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ElectronicAddressType)get_store().add_element_user(ELECTRONICADDRESS$2);
            }
            target.set(electronicAddress);
        }
    }
    
    /**
     * Appends and returns a new empty "ElectronicAddress" element
     */
    public org.etsi.uri.x02231.v2.ElectronicAddressType addNewElectronicAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ElectronicAddressType target = null;
            target = (org.etsi.uri.x02231.v2.ElectronicAddressType)get_store().add_element_user(ELECTRONICADDRESS$2);
            return target;
        }
    }
}
