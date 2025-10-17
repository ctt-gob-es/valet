/*
 * An XML document type.
 * Localname: TSPInformation
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSPInformationDocument
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * A document containing one TSPInformation(@http://uri.etsi.org/02231/v2#) element.
 *
 * This is a complex type.
 */
public class TSPInformationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationDocument
{
    
    public TSPInformationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSPINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPInformation");
    
    
    /**
     * Gets the "TSPInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType getTSPInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType)get_store().find_element_user(TSPINFORMATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPInformation" element
     */
    public void setTSPInformation(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType tspInformation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType)get_store().find_element_user(TSPINFORMATION$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType)get_store().add_element_user(TSPINFORMATION$0);
            }
            target.set(tspInformation);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPInformation" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType addNewTSPInformation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType)get_store().add_element_user(TSPINFORMATION$0);
            return target;
        }
    }
}
