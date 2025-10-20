/*
 * An XML document type.
 * Localname: SPKIData
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.SPKIDataDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one SPKIData(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class SPKIDataDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.SPKIDataDocument
{
    
    public SPKIDataDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SPKIDATA$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SPKIData");
    
    
    /**
     * Gets the "SPKIData" element
     */
    public org.w3.x2000.x09.xmldsig.SPKIDataType getSPKIData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SPKIDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.SPKIDataType)get_store().find_element_user(SPKIDATA$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SPKIData" element
     */
    public void setSPKIData(org.w3.x2000.x09.xmldsig.SPKIDataType spkiData)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SPKIDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.SPKIDataType)get_store().find_element_user(SPKIDATA$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.SPKIDataType)get_store().add_element_user(SPKIDATA$0);
            }
            target.set(spkiData);
        }
    }
    
    /**
     * Appends and returns a new empty "SPKIData" element
     */
    public org.w3.x2000.x09.xmldsig.SPKIDataType addNewSPKIData()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SPKIDataType target = null;
            target = (org.w3.x2000.x09.xmldsig.SPKIDataType)get_store().add_element_user(SPKIDATA$0);
            return target;
        }
    }
}
