/*
 * An XML document type.
 * Localname: AttributeRevocationValues
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.AttributeRevocationValuesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one AttributeRevocationValues(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class AttributeRevocationValuesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.AttributeRevocationValuesDocument
{
    
    public AttributeRevocationValuesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEREVOCATIONVALUES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeRevocationValues");
    
    
    /**
     * Gets the "AttributeRevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType getAttributeRevocationValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().find_element_user(ATTRIBUTEREVOCATIONVALUES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AttributeRevocationValues" element
     */
    public void setAttributeRevocationValues(org.etsi.uri.x01903.v13.RevocationValuesType attributeRevocationValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().find_element_user(ATTRIBUTEREVOCATIONVALUES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().add_element_user(ATTRIBUTEREVOCATIONVALUES$0);
            }
            target.set(attributeRevocationValues);
        }
    }
    
    /**
     * Appends and returns a new empty "AttributeRevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType addNewAttributeRevocationValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().add_element_user(ATTRIBUTEREVOCATIONVALUES$0);
            return target;
        }
    }
}
