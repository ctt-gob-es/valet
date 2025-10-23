/*
 * An XML document type.
 * Localname: CertSubjectDNAttribute
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.additionalTypes.CertSubjectDNAttributeDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.impl;
/**
 * A document containing one CertSubjectDNAttribute(@http://uri.etsi.org/02231/v2/additionaltypes#) element.
 *
 * This is a complex type.
 */
public class CertSubjectDNAttributeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeDocument
{
    
    public CertSubjectDNAttributeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CERTSUBJECTDNATTRIBUTE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "CertSubjectDNAttribute");
    
    
    /**
     * Gets the "CertSubjectDNAttribute" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType getCertSubjectDNAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType)get_store().find_element_user(CERTSUBJECTDNATTRIBUTE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CertSubjectDNAttribute" element
     */
    public void setCertSubjectDNAttribute(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType certSubjectDNAttribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType)get_store().find_element_user(CERTSUBJECTDNATTRIBUTE$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType)get_store().add_element_user(CERTSUBJECTDNATTRIBUTE$0);
            }
            target.set(certSubjectDNAttribute);
        }
    }
    
    /**
     * Appends and returns a new empty "CertSubjectDNAttribute" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType addNewCertSubjectDNAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.CertSubjectDNAttributeType)get_store().add_element_user(CERTSUBJECTDNATTRIBUTE$0);
            return target;
        }
    }
}
