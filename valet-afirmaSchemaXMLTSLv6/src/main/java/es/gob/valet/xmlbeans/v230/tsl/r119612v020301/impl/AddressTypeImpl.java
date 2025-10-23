/*
 * XML Type:  AddressType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.AddressType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML AddressType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class AddressTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType
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
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType getPostalAddresses()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType)get_store().find_element_user(POSTALADDRESSES$0, 0);
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
    public void setPostalAddresses(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType postalAddresses)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType)get_store().find_element_user(POSTALADDRESSES$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType)get_store().add_element_user(POSTALADDRESSES$0);
            }
            target.set(postalAddresses);
        }
    }
    
    /**
     * Appends and returns a new empty "PostalAddresses" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType addNewPostalAddresses()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressListType)get_store().add_element_user(POSTALADDRESSES$0);
            return target;
        }
    }
    
    /**
     * Gets the "ElectronicAddress" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType getElectronicAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType)get_store().find_element_user(ELECTRONICADDRESS$2, 0);
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
    public void setElectronicAddress(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType electronicAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType)get_store().find_element_user(ELECTRONICADDRESS$2, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType)get_store().add_element_user(ELECTRONICADDRESS$2);
            }
            target.set(electronicAddress);
        }
    }
    
    /**
     * Appends and returns a new empty "ElectronicAddress" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType addNewElectronicAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType)get_store().add_element_user(ELECTRONICADDRESS$2);
            return target;
        }
    }
}
