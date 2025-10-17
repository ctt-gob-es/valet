/*
 * XML Type:  TSPServicesListType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSPServicesListType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML TSPServicesListType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class TSPServicesListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServicesListType
{
    
    public TSPServicesListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSPSERVICE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPService");
    
    
    /**
     * Gets array of all "TSPService" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType[] getTSPServiceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(TSPSERVICE$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "TSPService" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType getTSPServiceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType)get_store().find_element_user(TSPSERVICE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "TSPService" element
     */
    public int sizeOfTSPServiceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TSPSERVICE$0);
        }
    }
    
    /**
     * Sets array of all "TSPService" element
     */
    public void setTSPServiceArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType[] tspServiceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(tspServiceArray, TSPSERVICE$0);
        }
    }
    
    /**
     * Sets ith "TSPService" element
     */
    public void setTSPServiceArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType tspService)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType)get_store().find_element_user(TSPSERVICE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(tspService);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "TSPService" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType insertNewTSPService(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType)get_store().insert_element_user(TSPSERVICE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "TSPService" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType addNewTSPService()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceType)get_store().add_element_user(TSPSERVICE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "TSPService" element
     */
    public void removeTSPService(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TSPSERVICE$0, i);
        }
    }
}
