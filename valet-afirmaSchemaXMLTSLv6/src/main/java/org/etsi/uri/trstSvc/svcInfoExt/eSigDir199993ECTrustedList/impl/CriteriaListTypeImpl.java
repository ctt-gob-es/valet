/*
 * XML Type:  CriteriaListType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.impl;
/**
 * An XML CriteriaListType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public class CriteriaListTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType
{
    
    public CriteriaListTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName KEYUSAGE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "KeyUsage");
    private static final javax.xml.namespace.QName POLICYSET$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "PolicySet");
    private static final javax.xml.namespace.QName CRITERIALIST$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "CriteriaList");
    private static final javax.xml.namespace.QName DESCRIPTION$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "Description");
    private static final javax.xml.namespace.QName OTHERCRITERIALIST$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#", "otherCriteriaList");
    private static final javax.xml.namespace.QName ASSERT$10 = 
        new javax.xml.namespace.QName("", "assert");
    
    
    /**
     * Gets array of all "KeyUsage" elements
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType[] getKeyUsageArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(KEYUSAGE$0, targetList);
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType[] result = new org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "KeyUsage" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType getKeyUsageArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType)get_store().find_element_user(KEYUSAGE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "KeyUsage" element
     */
    public int sizeOfKeyUsageArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(KEYUSAGE$0);
        }
    }
    
    /**
     * Sets array of all "KeyUsage" element
     */
    public void setKeyUsageArray(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType[] keyUsageArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(keyUsageArray, KEYUSAGE$0);
        }
    }
    
    /**
     * Sets ith "KeyUsage" element
     */
    public void setKeyUsageArray(int i, org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType keyUsage)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType)get_store().find_element_user(KEYUSAGE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(keyUsage);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "KeyUsage" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType insertNewKeyUsage(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType)get_store().insert_element_user(KEYUSAGE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "KeyUsage" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType addNewKeyUsage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.KeyUsageType)get_store().add_element_user(KEYUSAGE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "KeyUsage" element
     */
    public void removeKeyUsage(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(KEYUSAGE$0, i);
        }
    }
    
    /**
     * Gets array of all "PolicySet" elements
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType[] getPolicySetArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(POLICYSET$2, targetList);
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType[] result = new org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "PolicySet" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType getPolicySetArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType)get_store().find_element_user(POLICYSET$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "PolicySet" element
     */
    public int sizeOfPolicySetArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYSET$2);
        }
    }
    
    /**
     * Sets array of all "PolicySet" element
     */
    public void setPolicySetArray(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType[] policySetArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(policySetArray, POLICYSET$2);
        }
    }
    
    /**
     * Sets ith "PolicySet" element
     */
    public void setPolicySetArray(int i, org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType policySet)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType)get_store().find_element_user(POLICYSET$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(policySet);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicySet" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType insertNewPolicySet(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType)get_store().insert_element_user(POLICYSET$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicySet" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType addNewPolicySet()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.PoliciesListType)get_store().add_element_user(POLICYSET$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "PolicySet" element
     */
    public void removePolicySet(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYSET$2, i);
        }
    }
    
    /**
     * Gets array of all "CriteriaList" elements
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType[] getCriteriaListArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CRITERIALIST$4, targetList);
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType[] result = new org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CriteriaList" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType getCriteriaListArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType)get_store().find_element_user(CRITERIALIST$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CriteriaList" element
     */
    public int sizeOfCriteriaListArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CRITERIALIST$4);
        }
    }
    
    /**
     * Sets array of all "CriteriaList" element
     */
    public void setCriteriaListArray(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType[] criteriaListArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(criteriaListArray, CRITERIALIST$4);
        }
    }
    
    /**
     * Sets ith "CriteriaList" element
     */
    public void setCriteriaListArray(int i, org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType criteriaList)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType)get_store().find_element_user(CRITERIALIST$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(criteriaList);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CriteriaList" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType insertNewCriteriaList(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType)get_store().insert_element_user(CRITERIALIST$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CriteriaList" element
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType addNewCriteriaList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType)get_store().add_element_user(CRITERIALIST$4);
            return target;
        }
    }
    
    /**
     * Removes the ith "CriteriaList" element
     */
    public void removeCriteriaList(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CRITERIALIST$4, i);
        }
    }
    
    /**
     * Gets the "Description" element
     */
    public java.lang.String getDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$6, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Description" element
     */
    public org.apache.xmlbeans.XmlString xgetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$6, 0);
            return target;
        }
    }
    
    /**
     * True if has "Description" element
     */
    public boolean isSetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DESCRIPTION$6) != 0;
        }
    }
    
    /**
     * Sets the "Description" element
     */
    public void setDescription(java.lang.String description)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DESCRIPTION$6);
            }
            target.setStringValue(description);
        }
    }
    
    /**
     * Sets (as xml) the "Description" element
     */
    public void xsetDescription(org.apache.xmlbeans.XmlString description)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$6, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DESCRIPTION$6);
            }
            target.set(description);
        }
    }
    
    /**
     * Unsets the "Description" element
     */
    public void unsetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DESCRIPTION$6, 0);
        }
    }
    
    /**
     * Gets the "otherCriteriaList" element
     */
    public org.etsi.uri.x01903.v13.AnyType getOtherCriteriaList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(OTHERCRITERIALIST$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "otherCriteriaList" element
     */
    public boolean isSetOtherCriteriaList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OTHERCRITERIALIST$8) != 0;
        }
    }
    
    /**
     * Sets the "otherCriteriaList" element
     */
    public void setOtherCriteriaList(org.etsi.uri.x01903.v13.AnyType otherCriteriaList)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(OTHERCRITERIALIST$8, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(OTHERCRITERIALIST$8);
            }
            target.set(otherCriteriaList);
        }
    }
    
    /**
     * Appends and returns a new empty "otherCriteriaList" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewOtherCriteriaList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(OTHERCRITERIALIST$8);
            return target;
        }
    }
    
    /**
     * Unsets the "otherCriteriaList" element
     */
    public void unsetOtherCriteriaList()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OTHERCRITERIALIST$8, 0);
        }
    }
    
    /**
     * Gets the "assert" attribute
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert.Enum getAssert()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ASSERT$10);
            if (target == null)
            {
                return null;
            }
            return (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "assert" attribute
     */
    public org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert xgetAssert()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert)get_store().find_attribute_user(ASSERT$10);
            return target;
        }
    }
    
    /**
     * True if has "assert" attribute
     */
    public boolean isSetAssert()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ASSERT$10) != null;
        }
    }
    
    /**
     * Sets the "assert" attribute
     */
    public void setAssert(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert.Enum xassert)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ASSERT$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ASSERT$10);
            }
            target.setEnumValue(xassert);
        }
    }
    
    /**
     * Sets (as xml) the "assert" attribute
     */
    public void xsetAssert(org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert xassert)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert target = null;
            target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert)get_store().find_attribute_user(ASSERT$10);
            if (target == null)
            {
                target = (org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert)get_store().add_attribute_user(ASSERT$10);
            }
            target.set(xassert);
        }
    }
    
    /**
     * Unsets the "assert" attribute
     */
    public void unsetAssert()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ASSERT$10);
        }
    }
    /**
     * An XML assert(@).
     *
     * This is an atomic type that is a restriction of org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType$Assert.
     */
    public static class AssertImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements org.etsi.uri.trstSvc.svcInfoExt.eSigDir199993ECTrustedList.CriteriaListType.Assert
    {
        
        public AssertImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected AssertImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
}
