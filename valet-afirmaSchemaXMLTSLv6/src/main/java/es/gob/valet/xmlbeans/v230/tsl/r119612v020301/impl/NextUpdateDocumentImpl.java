/*
 * An XML document type.
 * Localname: NextUpdate
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.NextUpdateDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one NextUpdate(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class NextUpdateDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateDocument
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
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType getNextUpdate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType)get_store().find_element_user(NEXTUPDATE$0, 0);
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
    public void setNextUpdate(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType nextUpdate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType)get_store().find_element_user(NEXTUPDATE$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType)get_store().add_element_user(NEXTUPDATE$0);
            }
            target.set(nextUpdate);
        }
    }
    
    /**
     * Appends and returns a new empty "NextUpdate" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType addNewNextUpdate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType)get_store().add_element_user(NEXTUPDATE$0);
            return target;
        }
    }
}
