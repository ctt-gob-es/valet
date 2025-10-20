/*
 * An XML document type.
 * Localname: EncapsulatedPKIData
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.EncapsulatedPKIDataDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * A document containing one EncapsulatedPKIData(@http://uri.etsi.org/01903/v1.3.2#) element.
 *
 * This is a complex type.
 */
public class EncapsulatedPKIDataDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.EncapsulatedPKIDataDocument
{
    
    public EncapsulatedPKIDataDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENCAPSULATEDPKIDATA$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedPKIData");
    
    
    /**
     * Gets the "EncapsulatedPKIData" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType getEncapsulatedPKIData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDPKIDATA$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "EncapsulatedPKIData" element
     */
    public void setEncapsulatedPKIData(org.etsi.uri.x01903.v13.EncapsulatedPKIDataType encapsulatedPKIData)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDPKIDATA$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().add_element_user(ENCAPSULATEDPKIDATA$0);
            }
            target.set(encapsulatedPKIData);
        }
    }
    
    /**
     * Appends and returns a new empty "EncapsulatedPKIData" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType addNewEncapsulatedPKIData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().add_element_user(ENCAPSULATEDPKIDATA$0);
            return target;
        }
    }
}
