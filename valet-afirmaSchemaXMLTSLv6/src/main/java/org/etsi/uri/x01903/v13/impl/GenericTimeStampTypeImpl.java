/*
 * XML Type:  GenericTimeStampType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.GenericTimeStampType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML GenericTimeStampType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class GenericTimeStampTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.GenericTimeStampType
{
    
    public GenericTimeStampTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName INCLUDE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "Include");
    private static final javax.xml.namespace.QName REFERENCEINFO$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ReferenceInfo");
    private static final javax.xml.namespace.QName CANONICALIZATIONMETHOD$4 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "CanonicalizationMethod");
    private static final javax.xml.namespace.QName ENCAPSULATEDTIMESTAMP$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "EncapsulatedTimeStamp");
    private static final javax.xml.namespace.QName XMLTIMESTAMP$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "XMLTimeStamp");
    private static final javax.xml.namespace.QName ID$10 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets array of all "Include" elements
     */
    public org.etsi.uri.x01903.v13.IncludeType[] getIncludeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(INCLUDE$0, targetList);
            org.etsi.uri.x01903.v13.IncludeType[] result = new org.etsi.uri.x01903.v13.IncludeType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Include" element
     */
    public org.etsi.uri.x01903.v13.IncludeType getIncludeArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IncludeType target = null;
            target = (org.etsi.uri.x01903.v13.IncludeType)get_store().find_element_user(INCLUDE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Include" element
     */
    public int sizeOfIncludeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(INCLUDE$0);
        }
    }
    
    /**
     * Sets array of all "Include" element
     */
    public void setIncludeArray(org.etsi.uri.x01903.v13.IncludeType[] includeArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(includeArray, INCLUDE$0);
        }
    }
    
    /**
     * Sets ith "Include" element
     */
    public void setIncludeArray(int i, org.etsi.uri.x01903.v13.IncludeType include)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IncludeType target = null;
            target = (org.etsi.uri.x01903.v13.IncludeType)get_store().find_element_user(INCLUDE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(include);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Include" element
     */
    public org.etsi.uri.x01903.v13.IncludeType insertNewInclude(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IncludeType target = null;
            target = (org.etsi.uri.x01903.v13.IncludeType)get_store().insert_element_user(INCLUDE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Include" element
     */
    public org.etsi.uri.x01903.v13.IncludeType addNewInclude()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.IncludeType target = null;
            target = (org.etsi.uri.x01903.v13.IncludeType)get_store().add_element_user(INCLUDE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Include" element
     */
    public void removeInclude(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(INCLUDE$0, i);
        }
    }
    
    /**
     * Gets array of all "ReferenceInfo" elements
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType[] getReferenceInfoArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(REFERENCEINFO$2, targetList);
            org.etsi.uri.x01903.v13.ReferenceInfoType[] result = new org.etsi.uri.x01903.v13.ReferenceInfoType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ReferenceInfo" element
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType getReferenceInfoArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().find_element_user(REFERENCEINFO$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ReferenceInfo" element
     */
    public int sizeOfReferenceInfoArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(REFERENCEINFO$2);
        }
    }
    
    /**
     * Sets array of all "ReferenceInfo" element
     */
    public void setReferenceInfoArray(org.etsi.uri.x01903.v13.ReferenceInfoType[] referenceInfoArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(referenceInfoArray, REFERENCEINFO$2);
        }
    }
    
    /**
     * Sets ith "ReferenceInfo" element
     */
    public void setReferenceInfoArray(int i, org.etsi.uri.x01903.v13.ReferenceInfoType referenceInfo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().find_element_user(REFERENCEINFO$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(referenceInfo);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ReferenceInfo" element
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType insertNewReferenceInfo(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().insert_element_user(REFERENCEINFO$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ReferenceInfo" element
     */
    public org.etsi.uri.x01903.v13.ReferenceInfoType addNewReferenceInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.ReferenceInfoType target = null;
            target = (org.etsi.uri.x01903.v13.ReferenceInfoType)get_store().add_element_user(REFERENCEINFO$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "ReferenceInfo" element
     */
    public void removeReferenceInfo(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(REFERENCEINFO$2, i);
        }
    }
    
    /**
     * Gets the "CanonicalizationMethod" element
     */
    public org.w3.x2000.x09.xmldsig.CanonicalizationMethodType getCanonicalizationMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CanonicalizationMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.CanonicalizationMethodType)get_store().find_element_user(CANONICALIZATIONMETHOD$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "CanonicalizationMethod" element
     */
    public boolean isSetCanonicalizationMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CANONICALIZATIONMETHOD$4) != 0;
        }
    }
    
    /**
     * Sets the "CanonicalizationMethod" element
     */
    public void setCanonicalizationMethod(org.w3.x2000.x09.xmldsig.CanonicalizationMethodType canonicalizationMethod)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CanonicalizationMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.CanonicalizationMethodType)get_store().find_element_user(CANONICALIZATIONMETHOD$4, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.CanonicalizationMethodType)get_store().add_element_user(CANONICALIZATIONMETHOD$4);
            }
            target.set(canonicalizationMethod);
        }
    }
    
    /**
     * Appends and returns a new empty "CanonicalizationMethod" element
     */
    public org.w3.x2000.x09.xmldsig.CanonicalizationMethodType addNewCanonicalizationMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.CanonicalizationMethodType target = null;
            target = (org.w3.x2000.x09.xmldsig.CanonicalizationMethodType)get_store().add_element_user(CANONICALIZATIONMETHOD$4);
            return target;
        }
    }
    
    /**
     * Unsets the "CanonicalizationMethod" element
     */
    public void unsetCanonicalizationMethod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CANONICALIZATIONMETHOD$4, 0);
        }
    }
    
    /**
     * Gets array of all "EncapsulatedTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] getEncapsulatedTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ENCAPSULATEDTIMESTAMP$6, targetList);
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] result = new org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "EncapsulatedTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType getEncapsulatedTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDTIMESTAMP$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "EncapsulatedTimeStamp" element
     */
    public int sizeOfEncapsulatedTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ENCAPSULATEDTIMESTAMP$6);
        }
    }
    
    /**
     * Sets array of all "EncapsulatedTimeStamp" element
     */
    public void setEncapsulatedTimeStampArray(org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] encapsulatedTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(encapsulatedTimeStampArray, ENCAPSULATEDTIMESTAMP$6);
        }
    }
    
    /**
     * Sets ith "EncapsulatedTimeStamp" element
     */
    public void setEncapsulatedTimeStampArray(int i, org.etsi.uri.x01903.v13.EncapsulatedPKIDataType encapsulatedTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().find_element_user(ENCAPSULATEDTIMESTAMP$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(encapsulatedTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "EncapsulatedTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType insertNewEncapsulatedTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().insert_element_user(ENCAPSULATEDTIMESTAMP$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "EncapsulatedTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.EncapsulatedPKIDataType addNewEncapsulatedTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.EncapsulatedPKIDataType target = null;
            target = (org.etsi.uri.x01903.v13.EncapsulatedPKIDataType)get_store().add_element_user(ENCAPSULATEDTIMESTAMP$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "EncapsulatedTimeStamp" element
     */
    public void removeEncapsulatedTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ENCAPSULATEDTIMESTAMP$6, i);
        }
    }
    
    /**
     * Gets array of all "XMLTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.AnyType[] getXMLTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(XMLTIMESTAMP$8, targetList);
            org.etsi.uri.x01903.v13.AnyType[] result = new org.etsi.uri.x01903.v13.AnyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "XMLTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.AnyType getXMLTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(XMLTIMESTAMP$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "XMLTimeStamp" element
     */
    public int sizeOfXMLTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(XMLTIMESTAMP$8);
        }
    }
    
    /**
     * Sets array of all "XMLTimeStamp" element
     */
    public void setXMLTimeStampArray(org.etsi.uri.x01903.v13.AnyType[] xmlTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(xmlTimeStampArray, XMLTIMESTAMP$8);
        }
    }
    
    /**
     * Sets ith "XMLTimeStamp" element
     */
    public void setXMLTimeStampArray(int i, org.etsi.uri.x01903.v13.AnyType xmlTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().find_element_user(XMLTIMESTAMP$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(xmlTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "XMLTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.AnyType insertNewXMLTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().insert_element_user(XMLTIMESTAMP$8, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "XMLTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.AnyType addNewXMLTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.AnyType target = null;
            target = (org.etsi.uri.x01903.v13.AnyType)get_store().add_element_user(XMLTIMESTAMP$8);
            return target;
        }
    }
    
    /**
     * Removes the ith "XMLTimeStamp" element
     */
    public void removeXMLTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(XMLTIMESTAMP$8, i);
        }
    }
    
    /**
     * Gets the "Id" attribute
     */
    public java.lang.String getId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$10);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Id" attribute
     */
    public org.apache.xmlbeans.XmlID xgetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$10);
            return target;
        }
    }
    
    /**
     * True if has "Id" attribute
     */
    public boolean isSetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ID$10) != null;
        }
    }
    
    /**
     * Sets the "Id" attribute
     */
    public void setId(java.lang.String id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$10);
            }
            target.setStringValue(id);
        }
    }
    
    /**
     * Sets (as xml) the "Id" attribute
     */
    public void xsetId(org.apache.xmlbeans.XmlID id)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlID target = null;
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$10);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlID)get_store().add_attribute_user(ID$10);
            }
            target.set(id);
        }
    }
    
    /**
     * Unsets the "Id" attribute
     */
    public void unsetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ID$10);
        }
    }
}
