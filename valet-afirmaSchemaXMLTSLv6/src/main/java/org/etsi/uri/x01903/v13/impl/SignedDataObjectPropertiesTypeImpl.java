/*
 * XML Type:  SignedDataObjectPropertiesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML SignedDataObjectPropertiesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class SignedDataObjectPropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.SignedDataObjectPropertiesType
{
    
    public SignedDataObjectPropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DATAOBJECTFORMAT$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "DataObjectFormat");
    private static final javax.xml.namespace.QName COMMITMENTTYPEINDICATION$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CommitmentTypeIndication");
    private static final javax.xml.namespace.QName ALLDATAOBJECTSTIMESTAMP$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AllDataObjectsTimeStamp");
    private static final javax.xml.namespace.QName INDIVIDUALDATAOBJECTSTIMESTAMP$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "IndividualDataObjectsTimeStamp");
    private static final javax.xml.namespace.QName ID$8 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets array of all "DataObjectFormat" elements
     */
    public org.etsi.uri.x01903.v13.DataObjectFormatType[] getDataObjectFormatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(DATAOBJECTFORMAT$0, targetList);
            org.etsi.uri.x01903.v13.DataObjectFormatType[] result = new org.etsi.uri.x01903.v13.DataObjectFormatType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "DataObjectFormat" element
     */
    public org.etsi.uri.x01903.v13.DataObjectFormatType getDataObjectFormatArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DataObjectFormatType target = null;
            target = (org.etsi.uri.x01903.v13.DataObjectFormatType)get_store().find_element_user(DATAOBJECTFORMAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "DataObjectFormat" element
     */
    public int sizeOfDataObjectFormatArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DATAOBJECTFORMAT$0);
        }
    }
    
    /**
     * Sets array of all "DataObjectFormat" element
     */
    public void setDataObjectFormatArray(org.etsi.uri.x01903.v13.DataObjectFormatType[] dataObjectFormatArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(dataObjectFormatArray, DATAOBJECTFORMAT$0);
        }
    }
    
    /**
     * Sets ith "DataObjectFormat" element
     */
    public void setDataObjectFormatArray(int i, org.etsi.uri.x01903.v13.DataObjectFormatType dataObjectFormat)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DataObjectFormatType target = null;
            target = (org.etsi.uri.x01903.v13.DataObjectFormatType)get_store().find_element_user(DATAOBJECTFORMAT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(dataObjectFormat);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "DataObjectFormat" element
     */
    public org.etsi.uri.x01903.v13.DataObjectFormatType insertNewDataObjectFormat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DataObjectFormatType target = null;
            target = (org.etsi.uri.x01903.v13.DataObjectFormatType)get_store().insert_element_user(DATAOBJECTFORMAT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "DataObjectFormat" element
     */
    public org.etsi.uri.x01903.v13.DataObjectFormatType addNewDataObjectFormat()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.DataObjectFormatType target = null;
            target = (org.etsi.uri.x01903.v13.DataObjectFormatType)get_store().add_element_user(DATAOBJECTFORMAT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "DataObjectFormat" element
     */
    public void removeDataObjectFormat(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DATAOBJECTFORMAT$0, i);
        }
    }
    
    /**
     * Gets array of all "CommitmentTypeIndication" elements
     */
    public org.etsi.uri.x01903.v13.CommitmentTypeIndicationType[] getCommitmentTypeIndicationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(COMMITMENTTYPEINDICATION$2, targetList);
            org.etsi.uri.x01903.v13.CommitmentTypeIndicationType[] result = new org.etsi.uri.x01903.v13.CommitmentTypeIndicationType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CommitmentTypeIndication" element
     */
    public org.etsi.uri.x01903.v13.CommitmentTypeIndicationType getCommitmentTypeIndicationArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeIndicationType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType)get_store().find_element_user(COMMITMENTTYPEINDICATION$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CommitmentTypeIndication" element
     */
    public int sizeOfCommitmentTypeIndicationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMMITMENTTYPEINDICATION$2);
        }
    }
    
    /**
     * Sets array of all "CommitmentTypeIndication" element
     */
    public void setCommitmentTypeIndicationArray(org.etsi.uri.x01903.v13.CommitmentTypeIndicationType[] commitmentTypeIndicationArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(commitmentTypeIndicationArray, COMMITMENTTYPEINDICATION$2);
        }
    }
    
    /**
     * Sets ith "CommitmentTypeIndication" element
     */
    public void setCommitmentTypeIndicationArray(int i, org.etsi.uri.x01903.v13.CommitmentTypeIndicationType commitmentTypeIndication)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeIndicationType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType)get_store().find_element_user(COMMITMENTTYPEINDICATION$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(commitmentTypeIndication);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CommitmentTypeIndication" element
     */
    public org.etsi.uri.x01903.v13.CommitmentTypeIndicationType insertNewCommitmentTypeIndication(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeIndicationType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType)get_store().insert_element_user(COMMITMENTTYPEINDICATION$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CommitmentTypeIndication" element
     */
    public org.etsi.uri.x01903.v13.CommitmentTypeIndicationType addNewCommitmentTypeIndication()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CommitmentTypeIndicationType target = null;
            target = (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType)get_store().add_element_user(COMMITMENTTYPEINDICATION$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "CommitmentTypeIndication" element
     */
    public void removeCommitmentTypeIndication(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMMITMENTTYPEINDICATION$2, i);
        }
    }
    
    /**
     * Gets array of all "AllDataObjectsTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType[] getAllDataObjectsTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ALLDATAOBJECTSTIMESTAMP$4, targetList);
            org.etsi.uri.x01903.v13.XAdESTimeStampType[] result = new org.etsi.uri.x01903.v13.XAdESTimeStampType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "AllDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getAllDataObjectsTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(ALLDATAOBJECTSTIMESTAMP$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "AllDataObjectsTimeStamp" element
     */
    public int sizeOfAllDataObjectsTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ALLDATAOBJECTSTIMESTAMP$4);
        }
    }
    
    /**
     * Sets array of all "AllDataObjectsTimeStamp" element
     */
    public void setAllDataObjectsTimeStampArray(org.etsi.uri.x01903.v13.XAdESTimeStampType[] allDataObjectsTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(allDataObjectsTimeStampArray, ALLDATAOBJECTSTIMESTAMP$4);
        }
    }
    
    /**
     * Sets ith "AllDataObjectsTimeStamp" element
     */
    public void setAllDataObjectsTimeStampArray(int i, org.etsi.uri.x01903.v13.XAdESTimeStampType allDataObjectsTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(ALLDATAOBJECTSTIMESTAMP$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(allDataObjectsTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AllDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType insertNewAllDataObjectsTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().insert_element_user(ALLDATAOBJECTSTIMESTAMP$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AllDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewAllDataObjectsTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(ALLDATAOBJECTSTIMESTAMP$4);
            return target;
        }
    }
    
    /**
     * Removes the ith "AllDataObjectsTimeStamp" element
     */
    public void removeAllDataObjectsTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ALLDATAOBJECTSTIMESTAMP$4, i);
        }
    }
    
    /**
     * Gets array of all "IndividualDataObjectsTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType[] getIndividualDataObjectsTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(INDIVIDUALDATAOBJECTSTIMESTAMP$6, targetList);
            org.etsi.uri.x01903.v13.XAdESTimeStampType[] result = new org.etsi.uri.x01903.v13.XAdESTimeStampType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "IndividualDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getIndividualDataObjectsTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(INDIVIDUALDATAOBJECTSTIMESTAMP$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "IndividualDataObjectsTimeStamp" element
     */
    public int sizeOfIndividualDataObjectsTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(INDIVIDUALDATAOBJECTSTIMESTAMP$6);
        }
    }
    
    /**
     * Sets array of all "IndividualDataObjectsTimeStamp" element
     */
    public void setIndividualDataObjectsTimeStampArray(org.etsi.uri.x01903.v13.XAdESTimeStampType[] individualDataObjectsTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(individualDataObjectsTimeStampArray, INDIVIDUALDATAOBJECTSTIMESTAMP$6);
        }
    }
    
    /**
     * Sets ith "IndividualDataObjectsTimeStamp" element
     */
    public void setIndividualDataObjectsTimeStampArray(int i, org.etsi.uri.x01903.v13.XAdESTimeStampType individualDataObjectsTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(INDIVIDUALDATAOBJECTSTIMESTAMP$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(individualDataObjectsTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "IndividualDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType insertNewIndividualDataObjectsTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().insert_element_user(INDIVIDUALDATAOBJECTSTIMESTAMP$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "IndividualDataObjectsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewIndividualDataObjectsTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(INDIVIDUALDATAOBJECTSTIMESTAMP$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "IndividualDataObjectsTimeStamp" element
     */
    public void removeIndividualDataObjectsTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(INDIVIDUALDATAOBJECTSTIMESTAMP$6, i);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$8);
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
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$8);
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
            return get_store().find_attribute_user(ID$8) != null;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$8);
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
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlID)get_store().add_attribute_user(ID$8);
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
            get_store().remove_attribute(ID$8);
        }
    }
}
