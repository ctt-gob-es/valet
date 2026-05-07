/*
 * An XML document type.
 * Localname: ElectronicAddress
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.ElectronicAddressDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one ElectronicAddress(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ElectronicAddressDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.ElectronicAddressDocument
{
    
    public ElectronicAddressDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ELECTRONICADDRESS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ElectronicAddress");
    
    
    /**
     * Gets the "ElectronicAddress" element
     */
    public org.etsi.uri.x02231.v2.ElectronicAddressType getElectronicAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.ElectronicAddressType target = null;
            target = (org.etsi.uri.x02231.v2.ElectronicAddressType)get_store().find_element_user(ELECTRONICADDRESS$0, 0);
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
            target = (org.etsi.uri.x02231.v2.ElectronicAddressType)get_store().find_element_user(ELECTRONICADDRESS$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.ElectronicAddressType)get_store().add_element_user(ELECTRONICADDRESS$0);
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
            target = (org.etsi.uri.x02231.v2.ElectronicAddressType)get_store().add_element_user(ELECTRONICADDRESS$0);
            return target;
        }
    }
}
