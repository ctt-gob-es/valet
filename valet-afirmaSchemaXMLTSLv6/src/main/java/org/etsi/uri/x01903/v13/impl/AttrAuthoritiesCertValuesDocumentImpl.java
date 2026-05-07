/*
 * An XML document type.
 * Localname: AttrAuthoritiesCertValues
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.AttrAuthoritiesCertValuesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one AttrAuthoritiesCertValues(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class AttrAuthoritiesCertValuesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.AttrAuthoritiesCertValuesDocument
{
    
    public AttrAuthoritiesCertValuesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRAUTHORITIESCERTVALUES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AttrAuthoritiesCertValues");
    
    
    /**
     * Gets the "AttrAuthoritiesCertValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType getAttrAuthoritiesCertValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().find_element_user(ATTRAUTHORITIESCERTVALUES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AttrAuthoritiesCertValues" element
     */
    public void setAttrAuthoritiesCertValues(org.etsi.uri.x01903.v13.CertificateValuesType attrAuthoritiesCertValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().find_element_user(ATTRAUTHORITIESCERTVALUES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().add_element_user(ATTRAUTHORITIESCERTVALUES$0);
            }
            target.set(attrAuthoritiesCertValues);
        }
    }
    
    /**
     * Appends and returns a new empty "AttrAuthoritiesCertValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType addNewAttrAuthoritiesCertValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().add_element_user(ATTRAUTHORITIESCERTVALUES$0);
            return target;
        }
    }
}
