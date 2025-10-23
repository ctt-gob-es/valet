/*
 * An XML document type.
 * Localname: PublicKeyLocation
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.additionalTypes.PublicKeyLocationDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.impl;
/**
 * A document containing one PublicKeyLocation(@http://uri.etsi.org/02231/v2/additionaltypes#) element.
 *
 * This is a complex type.
 */
public class PublicKeyLocationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.PublicKeyLocationDocument
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
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType xgetPublicKeyLocation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(PUBLICKEYLOCATION$0, 0);
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
    public void xsetPublicKeyLocation(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType publicKeyLocation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(PUBLICKEYLOCATION$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().add_element_user(PUBLICKEYLOCATION$0);
            }
            target.set(publicKeyLocation);
        }
    }
}
