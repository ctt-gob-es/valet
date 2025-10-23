/*
 * XML Type:  KeyUsageType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.KeyUsageType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.impl;
/**
 * An XML KeyUsageType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public class KeyUsageTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageType
{
    
    public KeyUsageTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KEYUSAGEBIT$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "KeyUsageBit");
    
    
    /**
     * Gets array of all "KeyUsageBit" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType[] getKeyUsageBitArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(KEYUSAGEBIT$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "KeyUsageBit" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType getKeyUsageBitArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType)get_store().find_element_user(KEYUSAGEBIT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "KeyUsageBit" element
     */
    public int sizeOfKeyUsageBitArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(KEYUSAGEBIT$0);
        }
    }
    
    /**
     * Sets array of all "KeyUsageBit" element
     */
    public void setKeyUsageBitArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType[] keyUsageBitArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(keyUsageBitArray, KEYUSAGEBIT$0);
        }
    }
    
    /**
     * Sets ith "KeyUsageBit" element
     */
    public void setKeyUsageBitArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType keyUsageBit)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType)get_store().find_element_user(KEYUSAGEBIT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(keyUsageBit);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "KeyUsageBit" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType insertNewKeyUsageBit(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType)get_store().insert_element_user(KEYUSAGEBIT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "KeyUsageBit" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType addNewKeyUsageBit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType)get_store().add_element_user(KEYUSAGEBIT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "KeyUsageBit" element
     */
    public void removeKeyUsageBit(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(KEYUSAGEBIT$0, i);
        }
    }
}
