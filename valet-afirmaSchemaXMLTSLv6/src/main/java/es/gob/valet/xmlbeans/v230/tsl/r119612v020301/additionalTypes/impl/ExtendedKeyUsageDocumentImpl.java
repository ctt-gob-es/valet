/*
 * An XML document type.
 * Localname: ExtendedKeyUsage
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.additionalTypes.ExtendedKeyUsageDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.impl;
/**
 * A document containing one ExtendedKeyUsage(@http://uri.etsi.org/02231/v2/additionaltypes#) element.
 *
 * This is a complex type.
 */
public class ExtendedKeyUsageDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageDocument
{
    
    public ExtendedKeyUsageDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXTENDEDKEYUSAGE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "ExtendedKeyUsage");
    
    
    /**
     * Gets the "ExtendedKeyUsage" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType getExtendedKeyUsage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType)get_store().find_element_user(EXTENDEDKEYUSAGE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ExtendedKeyUsage" element
     */
    public void setExtendedKeyUsage(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType extendedKeyUsage)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType)get_store().find_element_user(EXTENDEDKEYUSAGE$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType)get_store().add_element_user(EXTENDEDKEYUSAGE$0);
            }
            target.set(extendedKeyUsage);
        }
    }
    
    /**
     * Appends and returns a new empty "ExtendedKeyUsage" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType addNewExtendedKeyUsage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType)get_store().add_element_user(EXTENDEDKEYUSAGE$0);
            return target;
        }
    }
}
