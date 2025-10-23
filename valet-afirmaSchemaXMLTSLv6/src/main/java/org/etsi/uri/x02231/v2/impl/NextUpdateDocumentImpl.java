/*
 * An XML document type.
 * Localname: NextUpdate
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: org.etsi.uri.x02231.v2.NextUpdateDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.impl;
/**
 * A document containing one NextUpdate(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class NextUpdateDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.NextUpdateDocument
{
    
    public NextUpdateDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NEXTUPDATE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "NextUpdate");
    
    
    /**
     * Gets the "NextUpdate" element
     */
    public org.etsi.uri.x02231.v2.NextUpdateType getNextUpdate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NextUpdateType target = null;
            target = (org.etsi.uri.x02231.v2.NextUpdateType)get_store().find_element_user(NEXTUPDATE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NextUpdate" element
     */
    public void setNextUpdate(org.etsi.uri.x02231.v2.NextUpdateType nextUpdate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NextUpdateType target = null;
            target = (org.etsi.uri.x02231.v2.NextUpdateType)get_store().find_element_user(NEXTUPDATE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.NextUpdateType)get_store().add_element_user(NEXTUPDATE$0);
            }
            target.set(nextUpdate);
        }
    }
    
    /**
     * Appends and returns a new empty "NextUpdate" element
     */
    public org.etsi.uri.x02231.v2.NextUpdateType addNewNextUpdate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.NextUpdateType target = null;
            target = (org.etsi.uri.x02231.v2.NextUpdateType)get_store().add_element_user(NEXTUPDATE$0);
            return target;
        }
    }
}
