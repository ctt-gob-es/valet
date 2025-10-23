/*
 * An XML document type.
 * Localname: QualifyingPropertiesReference
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one QualifyingPropertiesReference(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class QualifyingPropertiesReferenceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceDocument
{
    
    public QualifyingPropertiesReferenceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName QUALIFYINGPROPERTIESREFERENCE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "QualifyingPropertiesReference");
    
    
    /**
     * Gets the "QualifyingPropertiesReference" element
     */
    public org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType getQualifyingPropertiesReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType target = null;
            target = (org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType)get_store().find_element_user(QUALIFYINGPROPERTIESREFERENCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "QualifyingPropertiesReference" element
     */
    public void setQualifyingPropertiesReference(org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType qualifyingPropertiesReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType target = null;
            target = (org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType)get_store().find_element_user(QUALIFYINGPROPERTIESREFERENCE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType)get_store().add_element_user(QUALIFYINGPROPERTIESREFERENCE$0);
            }
            target.set(qualifyingPropertiesReference);
        }
    }
    
    /**
     * Appends and returns a new empty "QualifyingPropertiesReference" element
     */
    public org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType addNewQualifyingPropertiesReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType target = null;
            target = (org.etsi.uri.x01903.v13.QualifyingPropertiesReferenceType)get_store().add_element_user(QUALIFYINGPROPERTIESREFERENCE$0);
            return target;
        }
    }
}
