/*
 * XML Type:  ServiceSupplyPointsType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ServiceSupplyPointsType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML ServiceSupplyPointsType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class ServiceSupplyPointsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType
{
    
    public ServiceSupplyPointsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SERVICESUPPLYPOINT$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ServiceSupplyPoint");
    
    
    /**
     * Gets array of all "ServiceSupplyPoint" elements
     */
    public java.lang.String[] getServiceSupplyPointArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICESUPPLYPOINT$0, targetList);
            java.lang.String[] result = new java.lang.String[targetList.size()];
            for (int i = 0, len = targetList.size() ; i < len ; i++)
                result[i] = ((org.apache.xmlbeans.SimpleValue)targetList.get(i)).getStringValue();
            return result;
        }
    }
    
    /**
     * Gets ith "ServiceSupplyPoint" element
     */
    public java.lang.String getServiceSupplyPointArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICESUPPLYPOINT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) array of all "ServiceSupplyPoint" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType[] xgetServiceSupplyPointArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SERVICESUPPLYPOINT$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets (as xml) ith "ServiceSupplyPoint" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType xgetServiceSupplyPointArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(SERVICESUPPLYPOINT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)target;
        }
    }
    
    /**
     * Returns number of "ServiceSupplyPoint" element
     */
    public int sizeOfServiceSupplyPointArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SERVICESUPPLYPOINT$0);
        }
    }
    
    /**
     * Sets array of all "ServiceSupplyPoint" element
     */
    public void setServiceSupplyPointArray(java.lang.String[] serviceSupplyPointArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceSupplyPointArray, SERVICESUPPLYPOINT$0);
        }
    }
    
    /**
     * Sets ith "ServiceSupplyPoint" element
     */
    public void setServiceSupplyPointArray(int i, java.lang.String serviceSupplyPoint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SERVICESUPPLYPOINT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.setStringValue(serviceSupplyPoint);
        }
    }
    
    /**
     * Sets (as xml) array of all "ServiceSupplyPoint" element
     */
    public void xsetServiceSupplyPointArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType[]serviceSupplyPointArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(serviceSupplyPointArray, SERVICESUPPLYPOINT$0);
        }
    }
    
    /**
     * Sets (as xml) ith "ServiceSupplyPoint" element
     */
    public void xsetServiceSupplyPointArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType serviceSupplyPoint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(SERVICESUPPLYPOINT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(serviceSupplyPoint);
        }
    }
    
    /**
     * Inserts the value as the ith "ServiceSupplyPoint" element
     */
    public void insertServiceSupplyPoint(int i, java.lang.String serviceSupplyPoint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = 
                (org.apache.xmlbeans.SimpleValue)get_store().insert_element_user(SERVICESUPPLYPOINT$0, i);
            target.setStringValue(serviceSupplyPoint);
        }
    }
    
    /**
     * Appends the value as the last "ServiceSupplyPoint" element
     */
    public void addServiceSupplyPoint(java.lang.String serviceSupplyPoint)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SERVICESUPPLYPOINT$0);
            target.setStringValue(serviceSupplyPoint);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ServiceSupplyPoint" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType insertNewServiceSupplyPoint(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().insert_element_user(SERVICESUPPLYPOINT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ServiceSupplyPoint" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType addNewServiceSupplyPoint()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().add_element_user(SERVICESUPPLYPOINT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ServiceSupplyPoint" element
     */
    public void removeServiceSupplyPoint(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SERVICESUPPLYPOINT$0, i);
        }
    }
}
