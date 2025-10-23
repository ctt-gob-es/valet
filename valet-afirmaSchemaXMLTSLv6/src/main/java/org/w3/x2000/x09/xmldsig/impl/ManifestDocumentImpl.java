/*
 * An XML document type.
 * Localname: Manifest
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.ManifestDocument
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * A document containing one Manifest(@http://www.w3.org/2000/09/xmldsig#) element.
 *
 * This is a complex type.
 */
public class ManifestDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.ManifestDocument
{
    
    public ManifestDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MANIFEST$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "Manifest");
    
    
    /**
     * Gets the "Manifest" element
     */
    public org.w3.x2000.x09.xmldsig.ManifestType getManifest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ManifestType target = null;
            target = (org.w3.x2000.x09.xmldsig.ManifestType)get_store().find_element_user(MANIFEST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Manifest" element
     */
    public void setManifest(org.w3.x2000.x09.xmldsig.ManifestType manifest)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ManifestType target = null;
            target = (org.w3.x2000.x09.xmldsig.ManifestType)get_store().find_element_user(MANIFEST$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.ManifestType)get_store().add_element_user(MANIFEST$0);
            }
            target.set(manifest);
        }
    }
    
    /**
     * Appends and returns a new empty "Manifest" element
     */
    public org.w3.x2000.x09.xmldsig.ManifestType addNewManifest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ManifestType target = null;
            target = (org.w3.x2000.x09.xmldsig.ManifestType)get_store().add_element_user(MANIFEST$0);
            return target;
        }
    }
}
