/*
 * An XML document type.
 * Localname: PublicKeyLocation
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: org.etsi.uri.x02231.v2.additionaltypes.PublicKeyLocationDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.additionaltypes.impl;
/**
 * A document containing one PublicKeyLocation(@http://uri.etsi.org/02231/v2/additionaltypes#) element.
 *
 * This is a complex type.
 */
public class PublicKeyLocationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.additionaltypes.PublicKeyLocationDocument
{
    
    public PublicKeyLocationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PUBLICKEYLOCATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "PublicKeyLocation");
    
    
    /**
     * Gets the "PublicKeyLocation" element
     */
    public java.lang.String getPublicKeyLocation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PUBLICKEYLOCATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "PublicKeyLocation" element
     */
    public org.etsi.uri.x02231.v2.NonEmptyURIType xgetPublicKeyLocation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(PUBLICKEYLOCATION$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "PublicKeyLocation" element
     */
    public void setPublicKeyLocation(java.lang.String publicKeyLocation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(PUBLICKEYLOCATION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(PUBLICKEYLOCATION$0);
            }
            target.setStringValue(publicKeyLocation);
        }
    }
    
    /**
     * Sets (as xml) the "PublicKeyLocation" element
     */
    public void xsetPublicKeyLocation(org.etsi.uri.x02231.v2.NonEmptyURIType publicKeyLocation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NonEmptyURIType target = null;
            target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().find_element_user(PUBLICKEYLOCATION$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NonEmptyURIType)get_store().add_element_user(PUBLICKEYLOCATION$0);
            }
            target.set(publicKeyLocation);
        }
    }
}
