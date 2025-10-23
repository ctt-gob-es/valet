/*
 * An XML document type.
 * Localname: PostalAddresses
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.PostalAddressesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one PostalAddresses(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class PostalAddressesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.PostalAddressesDocument
{
    
    public PostalAddressesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POSTALADDRESSES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "PostalAddresses");
    
    
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
}
