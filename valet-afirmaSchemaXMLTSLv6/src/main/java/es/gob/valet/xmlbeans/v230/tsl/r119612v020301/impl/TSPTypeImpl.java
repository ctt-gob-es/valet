/*
 * XML Type:  TSPType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSPType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML TSPType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class TSPTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPType
{
    
    public TSPTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSPINFORMATION$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPInformation");
    private static final javax.xml.namespace.QName TSPSERVICES$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPServices");
    
    
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
    
    /**
     * Gets the "TSPServices" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType getTSPServices()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType)get_store().find_element_user(TSPSERVICES$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPServices" element
     */
    public void setTSPServices(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType tspServices)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType)get_store().find_element_user(TSPSERVICES$2, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType)get_store().add_element_user(TSPSERVICES$2);
            }
            target.set(tspServices);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPServices" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType addNewTSPServices()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType)get_store().add_element_user(TSPSERVICES$2);
            return target;
        }
    }
}
