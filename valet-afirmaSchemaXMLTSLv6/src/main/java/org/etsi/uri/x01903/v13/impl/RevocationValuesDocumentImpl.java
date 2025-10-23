/*
 * An XML document type.
 * Localname: RevocationValues
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.RevocationValuesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one RevocationValues(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class RevocationValuesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.RevocationValuesDocument
{
    
    public RevocationValuesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REVOCATIONVALUES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "RevocationValues");
    
    
    /**
     * Gets the "RevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType getRevocationValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().find_element_user(REVOCATIONVALUES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RevocationValues" element
     */
    public void setRevocationValues(org.etsi.uri.x01903.v13.RevocationValuesType revocationValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().find_element_user(REVOCATIONVALUES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().add_element_user(REVOCATIONVALUES$0);
            }
            target.set(revocationValues);
        }
    }
    
    /**
     * Appends and returns a new empty "RevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType addNewRevocationValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().add_element_user(REVOCATIONVALUES$0);
            return target;
        }
    }
}
