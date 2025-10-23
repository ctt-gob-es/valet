/*
 * An XML document type.
 * Localname: Extension
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ExtensionDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one Extension(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class ExtensionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionDocument
{
    
    public ExtensionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXTENSION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "Extension");
    
    
    /**
     * Gets the "Extension" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType getExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType)get_store().find_element_user(EXTENSION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Extension" element
     */
    public void setExtension(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType extension)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType)get_store().find_element_user(EXTENSION$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType)get_store().add_element_user(EXTENSION$0);
            }
            target.set(extension);
        }
    }
    
    /**
     * Appends and returns a new empty "Extension" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType addNewExtension()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType)get_store().add_element_user(EXTENSION$0);
            return target;
        }
    }
}
