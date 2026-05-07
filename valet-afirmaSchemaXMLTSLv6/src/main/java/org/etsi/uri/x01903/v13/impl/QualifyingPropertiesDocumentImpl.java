/*
 * An XML document type.
 * Localname: QualifyingProperties
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.QualifyingPropertiesDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one QualifyingProperties(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class QualifyingPropertiesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.QualifyingPropertiesDocument
{
    
    public QualifyingPropertiesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFYINGPROPERTIES$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "QualifyingProperties");
    
    
    /**
     * Gets the "QualifyingProperties" element
     */
    public org.etsi.uri.x01903.v13.QualifyingPropertiesType getQualifyingProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.QualifyingPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.QualifyingPropertiesType)get_store().find_element_user(QUALIFYINGPROPERTIES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "QualifyingProperties" element
     */
    public void setQualifyingProperties(org.etsi.uri.x01903.v13.QualifyingPropertiesType qualifyingProperties)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.QualifyingPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.QualifyingPropertiesType)get_store().find_element_user(QUALIFYINGPROPERTIES$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.QualifyingPropertiesType)get_store().add_element_user(QUALIFYINGPROPERTIES$0);
            }
            target.set(qualifyingProperties);
        }
    }
    
    /**
     * Appends and returns a new empty "QualifyingProperties" element
     */
    public org.etsi.uri.x01903.v13.QualifyingPropertiesType addNewQualifyingProperties()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.QualifyingPropertiesType target = null;
            target = (org.etsi.uri.x01903.v13.QualifyingPropertiesType)get_store().add_element_user(QUALIFYINGPROPERTIES$0);
            return target;
        }
    }
}
