/*
 * An XML document type.
 * Localname: PGPData
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.PGPDataDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one PGPData(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class PGPDataDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.PGPDataDocument
{
    
    public PGPDataDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName PGPDATA$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "PGPData");
    
    
    /**
     * Gets the "PGPData" element
     */
    public org.w3.x2000.x09.xmldsig.PGPDataType getPGPData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.PGPDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.PGPDataType)get_store().find_element_user(PGPDATA$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PGPData" element
     */
    public void setPGPData(org.w3.x2000.x09.xmldsig.PGPDataType pgpData)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.PGPDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.PGPDataType)get_store().find_element_user(PGPDATA$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.PGPDataType)get_store().add_element_user(PGPDATA$0);
            }
            target.set(pgpData);
        }
    }
    
    /**
     * Appends and returns a new empty "PGPData" element
     */
    public org.w3.x2000.x09.xmldsig.PGPDataType addNewPGPData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.PGPDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.PGPDataType)get_store().add_element_user(PGPDATA$0);
            return target;
        }
    }
}
