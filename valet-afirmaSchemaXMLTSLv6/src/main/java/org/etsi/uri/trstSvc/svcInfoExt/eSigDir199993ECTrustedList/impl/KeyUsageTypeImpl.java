/*
 * XML Type:  KeyUsageType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.impl;
/**
 * An XML KeyUsageType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public class KeyUsageTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType
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
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType[] getKeyUsageBitArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(KEYUSAGEBIT$0, targetList);
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType[] result = new org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "KeyUsageBit" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType getKeyUsageBitArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType)get_store().find_element_user(KEYUSAGEBIT$0, i);
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
    public void setKeyUsageBitArray(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType[] keyUsageBitArray)
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
    public void setKeyUsageBitArray(int i, org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType keyUsageBit)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType)get_store().find_element_user(KEYUSAGEBIT$0, i);
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
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType insertNewKeyUsageBit(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType)get_store().insert_element_user(KEYUSAGEBIT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "KeyUsageBit" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType addNewKeyUsageBit()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageBitType)get_store().add_element_user(KEYUSAGEBIT$0);
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
