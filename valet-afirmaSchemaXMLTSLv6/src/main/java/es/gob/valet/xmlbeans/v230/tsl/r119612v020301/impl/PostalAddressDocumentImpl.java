/*
 * An XML document type.
 * Localname: PostalAddress
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.PostalAddressDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one PostalAddress(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class PostalAddressDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressDocument
{
    
    public PostalAddressDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POSTALADDRESS$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "PostalAddress");
    
    
    /**
     * Gets the "PostalAddress" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType getPostalAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType)get_store().find_element_user(POSTALADDRESS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PostalAddress" element
     */
    public void setPostalAddress(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType postalAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType)get_store().find_element_user(POSTALADDRESS$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType)get_store().add_element_user(POSTALADDRESS$0);
            }
            target.set(postalAddress);
        }
    }
    
    /**
     * Appends and returns a new empty "PostalAddress" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType addNewPostalAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PostalAddressType)get_store().add_element_user(POSTALADDRESS$0);
            return target;
        }
    }
}
