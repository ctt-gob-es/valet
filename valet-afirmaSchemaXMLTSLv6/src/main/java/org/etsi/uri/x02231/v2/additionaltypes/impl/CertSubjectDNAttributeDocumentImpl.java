/*
 * An XML document type.
 * Localname: CertSubjectDNAttribute
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.additionaltypes.impl;
/**
 * A document containing one CertSubjectDNAttribute(@http://uri.etsi.org/02231/v2/additionaltypes#) element.
 *
 * This is a complex type.
 */
public class CertSubjectDNAttributeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeDocument
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
    public org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType getCertSubjectDNAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType target = null;
            target = (org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType)get_store().find_element_user(CERTSUBJECTDNATTRIBUTE$0, 0);
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
    public void setCertSubjectDNAttribute(org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType certSubjectDNAttribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType target = null;
            target = (org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType)get_store().find_element_user(CERTSUBJECTDNATTRIBUTE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType)get_store().add_element_user(CERTSUBJECTDNATTRIBUTE$0);
            }
            target.set(certSubjectDNAttribute);
        }
    }
    
    /**
     * Appends and returns a new empty "CertSubjectDNAttribute" element
     */
    public org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType addNewCertSubjectDNAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType target = null;
            target = (org.etsi.uri.x02231.v2.additionaltypes.CertSubjectDNAttributeType)get_store().add_element_user(CERTSUBJECTDNATTRIBUTE$0);
            return target;
        }
    }
}
