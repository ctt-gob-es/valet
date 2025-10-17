/*
 * An XML document type.
 * Localname: ElectronicAddress
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ElectronicAddressDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one ElectronicAddress(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ElectronicAddressDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressDocument
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
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType getElectronicAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType)get_store().find_element_user(ELECTRONICADDRESS$0, 0);
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
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType)get_store().find_element_user(ELECTRONICADDRESS$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType)get_store().add_element_user(ELECTRONICADDRESS$0);
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
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ElectronicAddressType)get_store().add_element_user(ELECTRONICADDRESS$0);
            return target;
        }
    }
}
