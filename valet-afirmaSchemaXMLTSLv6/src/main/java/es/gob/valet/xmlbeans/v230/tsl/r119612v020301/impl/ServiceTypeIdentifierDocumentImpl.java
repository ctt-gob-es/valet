/*
 * An XML document type.
 * Localname: ServiceTypeIdentifier
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ServiceTypeIdentifierDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one ServiceTypeIdentifier(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ServiceTypeIdentifierDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceTypeIdentifierDocument
{
    
    public ServiceTypeIdentifierDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICETYPEIDENTIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceTypeIdentifier");
    
    
    /**
     * Gets the "ServiceTypeIdentifier" element
     */
    public java.lang.String getServiceTypeIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICETYPEIDENTIFIER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ServiceTypeIdentifier" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType xgetServiceTypeIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(SERVICETYPEIDENTIFIER$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ServiceTypeIdentifier" element
     */
    public void setServiceTypeIdentifier(java.lang.String serviceTypeIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICETYPEIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SERVICETYPEIDENTIFIER$0);
            }
            target.setStringValue(serviceTypeIdentifier);
        }
    }
    
    /**
     * Sets (as xml) the "ServiceTypeIdentifier" element
     */
    public void xsetServiceTypeIdentifier(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType serviceTypeIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(SERVICETYPEIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().add_element_user(SERVICETYPEIDENTIFIER$0);
            }
            target.set(serviceTypeIdentifier);
        }
    }
}
