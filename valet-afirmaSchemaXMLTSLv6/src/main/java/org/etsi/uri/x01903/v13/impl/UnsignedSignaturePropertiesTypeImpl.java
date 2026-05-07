/*
 * XML Type:  UnsignedSignaturePropertiesType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13.impl;
/**
 * An XML UnsignedSignaturePropertiesType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public class UnsignedSignaturePropertiesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x01903.v13.UnsignedSignaturePropertiesType
{
    
    public UnsignedSignaturePropertiesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COUNTERSIGNATURE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CounterSignature");
    private static final javax.xml.namespace.QName SIGNATURETIMESTAMP$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SignatureTimeStamp");
    private static final javax.xml.namespace.QName COMPLETECERTIFICATEREFS$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CompleteCertificateRefs");
    private static final javax.xml.namespace.QName COMPLETEREVOCATIONREFS$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CompleteRevocationRefs");
    private static final javax.xml.namespace.QName ATTRIBUTECERTIFICATEREFS$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeCertificateRefs");
    private static final javax.xml.namespace.QName ATTRIBUTEREVOCATIONREFS$10 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeRevocationRefs");
    private static final javax.xml.namespace.QName SIGANDREFSTIMESTAMP$12 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "SigAndRefsTimeStamp");
    private static final javax.xml.namespace.QName REFSONLYTIMESTAMP$14 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "RefsOnlyTimeStamp");
    private static final javax.xml.namespace.QName CERTIFICATEVALUES$16 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "CertificateValues");
    private static final javax.xml.namespace.QName REVOCATIONVALUES$18 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "RevocationValues");
    private static final javax.xml.namespace.QName ATTRAUTHORITIESCERTVALUES$20 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AttrAuthoritiesCertValues");
    private static final javax.xml.namespace.QName ATTRIBUTEREVOCATIONVALUES$22 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "AttributeRevocationValues");
    private static final javax.xml.namespace.QName ARCHIVETIMESTAMP$24 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/01903/v1.3.2#", "ArchiveTimeStamp");
    private static final javax.xml.namespace.QName ID$26 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets array of all "CounterSignature" elements
     */
    public org.etsi.uri.x01903.v13.CounterSignatureType[] getCounterSignatureArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(COUNTERSIGNATURE$0, targetList);
            org.etsi.uri.x01903.v13.CounterSignatureType[] result = new org.etsi.uri.x01903.v13.CounterSignatureType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CounterSignature" element
     */
    public org.etsi.uri.x01903.v13.CounterSignatureType getCounterSignatureArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CounterSignatureType target = null;
            target = (org.etsi.uri.x01903.v13.CounterSignatureType)get_store().find_element_user(COUNTERSIGNATURE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CounterSignature" element
     */
    public int sizeOfCounterSignatureArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COUNTERSIGNATURE$0);
        }
    }
    
    /**
     * Sets array of all "CounterSignature" element
     */
    public void setCounterSignatureArray(org.etsi.uri.x01903.v13.CounterSignatureType[] counterSignatureArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(counterSignatureArray, COUNTERSIGNATURE$0);
        }
    }
    
    /**
     * Sets ith "CounterSignature" element
     */
    public void setCounterSignatureArray(int i, org.etsi.uri.x01903.v13.CounterSignatureType counterSignature)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CounterSignatureType target = null;
            target = (org.etsi.uri.x01903.v13.CounterSignatureType)get_store().find_element_user(COUNTERSIGNATURE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(counterSignature);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CounterSignature" element
     */
    public org.etsi.uri.x01903.v13.CounterSignatureType insertNewCounterSignature(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CounterSignatureType target = null;
            target = (org.etsi.uri.x01903.v13.CounterSignatureType)get_store().insert_element_user(COUNTERSIGNATURE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CounterSignature" element
     */
    public org.etsi.uri.x01903.v13.CounterSignatureType addNewCounterSignature()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CounterSignatureType target = null;
            target = (org.etsi.uri.x01903.v13.CounterSignatureType)get_store().add_element_user(COUNTERSIGNATURE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "CounterSignature" element
     */
    public void removeCounterSignature(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COUNTERSIGNATURE$0, i);
        }
    }
    
    /**
     * Gets array of all "SignatureTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType[] getSignatureTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SIGNATURETIMESTAMP$2, targetList);
            org.etsi.uri.x01903.v13.XAdESTimeStampType[] result = new org.etsi.uri.x01903.v13.XAdESTimeStampType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "SignatureTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getSignatureTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(SIGNATURETIMESTAMP$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "SignatureTimeStamp" element
     */
    public int sizeOfSignatureTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGNATURETIMESTAMP$2);
        }
    }
    
    /**
     * Sets array of all "SignatureTimeStamp" element
     */
    public void setSignatureTimeStampArray(org.etsi.uri.x01903.v13.XAdESTimeStampType[] signatureTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(signatureTimeStampArray, SIGNATURETIMESTAMP$2);
        }
    }
    
    /**
     * Sets ith "SignatureTimeStamp" element
     */
    public void setSignatureTimeStampArray(int i, org.etsi.uri.x01903.v13.XAdESTimeStampType signatureTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(SIGNATURETIMESTAMP$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(signatureTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "SignatureTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType insertNewSignatureTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().insert_element_user(SIGNATURETIMESTAMP$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "SignatureTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewSignatureTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(SIGNATURETIMESTAMP$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "SignatureTimeStamp" element
     */
    public void removeSignatureTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGNATURETIMESTAMP$2, i);
        }
    }
    
    /**
     * Gets array of all "CompleteCertificateRefs" elements
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType[] getCompleteCertificateRefsArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(COMPLETECERTIFICATEREFS$4, targetList);
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType[] result = new org.etsi.uri.x01903.v13.CompleteCertificateRefsType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CompleteCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType getCompleteCertificateRefsArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().find_element_user(COMPLETECERTIFICATEREFS$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CompleteCertificateRefs" element
     */
    public int sizeOfCompleteCertificateRefsArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMPLETECERTIFICATEREFS$4);
        }
    }
    
    /**
     * Sets array of all "CompleteCertificateRefs" element
     */
    public void setCompleteCertificateRefsArray(org.etsi.uri.x01903.v13.CompleteCertificateRefsType[] completeCertificateRefsArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(completeCertificateRefsArray, COMPLETECERTIFICATEREFS$4);
        }
    }
    
    /**
     * Sets ith "CompleteCertificateRefs" element
     */
    public void setCompleteCertificateRefsArray(int i, org.etsi.uri.x01903.v13.CompleteCertificateRefsType completeCertificateRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().find_element_user(COMPLETECERTIFICATEREFS$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(completeCertificateRefs);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CompleteCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType insertNewCompleteCertificateRefs(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().insert_element_user(COMPLETECERTIFICATEREFS$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CompleteCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType addNewCompleteCertificateRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().add_element_user(COMPLETECERTIFICATEREFS$4);
            return target;
        }
    }
    
    /**
     * Removes the ith "CompleteCertificateRefs" element
     */
    public void removeCompleteCertificateRefs(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMPLETECERTIFICATEREFS$4, i);
        }
    }
    
    /**
     * Gets array of all "CompleteRevocationRefs" elements
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType[] getCompleteRevocationRefsArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(COMPLETEREVOCATIONREFS$6, targetList);
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType[] result = new org.etsi.uri.x01903.v13.CompleteRevocationRefsType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CompleteRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType getCompleteRevocationRefsArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().find_element_user(COMPLETEREVOCATIONREFS$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CompleteRevocationRefs" element
     */
    public int sizeOfCompleteRevocationRefsArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMPLETEREVOCATIONREFS$6);
        }
    }
    
    /**
     * Sets array of all "CompleteRevocationRefs" element
     */
    public void setCompleteRevocationRefsArray(org.etsi.uri.x01903.v13.CompleteRevocationRefsType[] completeRevocationRefsArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(completeRevocationRefsArray, COMPLETEREVOCATIONREFS$6);
        }
    }
    
    /**
     * Sets ith "CompleteRevocationRefs" element
     */
    public void setCompleteRevocationRefsArray(int i, org.etsi.uri.x01903.v13.CompleteRevocationRefsType completeRevocationRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().find_element_user(COMPLETEREVOCATIONREFS$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(completeRevocationRefs);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CompleteRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType insertNewCompleteRevocationRefs(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().insert_element_user(COMPLETEREVOCATIONREFS$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CompleteRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType addNewCompleteRevocationRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().add_element_user(COMPLETEREVOCATIONREFS$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "CompleteRevocationRefs" element
     */
    public void removeCompleteRevocationRefs(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMPLETEREVOCATIONREFS$6, i);
        }
    }
    
    /**
     * Gets array of all "AttributeCertificateRefs" elements
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType[] getAttributeCertificateRefsArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTECERTIFICATEREFS$8, targetList);
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType[] result = new org.etsi.uri.x01903.v13.CompleteCertificateRefsType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "AttributeCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType getAttributeCertificateRefsArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().find_element_user(ATTRIBUTECERTIFICATEREFS$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "AttributeCertificateRefs" element
     */
    public int sizeOfAttributeCertificateRefsArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTECERTIFICATEREFS$8);
        }
    }
    
    /**
     * Sets array of all "AttributeCertificateRefs" element
     */
    public void setAttributeCertificateRefsArray(org.etsi.uri.x01903.v13.CompleteCertificateRefsType[] attributeCertificateRefsArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(attributeCertificateRefsArray, ATTRIBUTECERTIFICATEREFS$8);
        }
    }
    
    /**
     * Sets ith "AttributeCertificateRefs" element
     */
    public void setAttributeCertificateRefsArray(int i, org.etsi.uri.x01903.v13.CompleteCertificateRefsType attributeCertificateRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().find_element_user(ATTRIBUTECERTIFICATEREFS$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(attributeCertificateRefs);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AttributeCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType insertNewAttributeCertificateRefs(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().insert_element_user(ATTRIBUTECERTIFICATEREFS$8, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AttributeCertificateRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteCertificateRefsType addNewAttributeCertificateRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteCertificateRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteCertificateRefsType)get_store().add_element_user(ATTRIBUTECERTIFICATEREFS$8);
            return target;
        }
    }
    
    /**
     * Removes the ith "AttributeCertificateRefs" element
     */
    public void removeAttributeCertificateRefs(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTECERTIFICATEREFS$8, i);
        }
    }
    
    /**
     * Gets array of all "AttributeRevocationRefs" elements
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType[] getAttributeRevocationRefsArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTEREVOCATIONREFS$10, targetList);
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType[] result = new org.etsi.uri.x01903.v13.CompleteRevocationRefsType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "AttributeRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType getAttributeRevocationRefsArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().find_element_user(ATTRIBUTEREVOCATIONREFS$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "AttributeRevocationRefs" element
     */
    public int sizeOfAttributeRevocationRefsArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTEREVOCATIONREFS$10);
        }
    }
    
    /**
     * Sets array of all "AttributeRevocationRefs" element
     */
    public void setAttributeRevocationRefsArray(org.etsi.uri.x01903.v13.CompleteRevocationRefsType[] attributeRevocationRefsArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(attributeRevocationRefsArray, ATTRIBUTEREVOCATIONREFS$10);
        }
    }
    
    /**
     * Sets ith "AttributeRevocationRefs" element
     */
    public void setAttributeRevocationRefsArray(int i, org.etsi.uri.x01903.v13.CompleteRevocationRefsType attributeRevocationRefs)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().find_element_user(ATTRIBUTEREVOCATIONREFS$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(attributeRevocationRefs);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AttributeRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType insertNewAttributeRevocationRefs(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().insert_element_user(ATTRIBUTEREVOCATIONREFS$10, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AttributeRevocationRefs" element
     */
    public org.etsi.uri.x01903.v13.CompleteRevocationRefsType addNewAttributeRevocationRefs()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CompleteRevocationRefsType target = null;
            target = (org.etsi.uri.x01903.v13.CompleteRevocationRefsType)get_store().add_element_user(ATTRIBUTEREVOCATIONREFS$10);
            return target;
        }
    }
    
    /**
     * Removes the ith "AttributeRevocationRefs" element
     */
    public void removeAttributeRevocationRefs(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTEREVOCATIONREFS$10, i);
        }
    }
    
    /**
     * Gets array of all "SigAndRefsTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType[] getSigAndRefsTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SIGANDREFSTIMESTAMP$12, targetList);
            org.etsi.uri.x01903.v13.XAdESTimeStampType[] result = new org.etsi.uri.x01903.v13.XAdESTimeStampType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "SigAndRefsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getSigAndRefsTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(SIGANDREFSTIMESTAMP$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "SigAndRefsTimeStamp" element
     */
    public int sizeOfSigAndRefsTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SIGANDREFSTIMESTAMP$12);
        }
    }
    
    /**
     * Sets array of all "SigAndRefsTimeStamp" element
     */
    public void setSigAndRefsTimeStampArray(org.etsi.uri.x01903.v13.XAdESTimeStampType[] sigAndRefsTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(sigAndRefsTimeStampArray, SIGANDREFSTIMESTAMP$12);
        }
    }
    
    /**
     * Sets ith "SigAndRefsTimeStamp" element
     */
    public void setSigAndRefsTimeStampArray(int i, org.etsi.uri.x01903.v13.XAdESTimeStampType sigAndRefsTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(SIGANDREFSTIMESTAMP$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(sigAndRefsTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "SigAndRefsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType insertNewSigAndRefsTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().insert_element_user(SIGANDREFSTIMESTAMP$12, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "SigAndRefsTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewSigAndRefsTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(SIGANDREFSTIMESTAMP$12);
            return target;
        }
    }
    
    /**
     * Removes the ith "SigAndRefsTimeStamp" element
     */
    public void removeSigAndRefsTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SIGANDREFSTIMESTAMP$12, i);
        }
    }
    
    /**
     * Gets array of all "RefsOnlyTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType[] getRefsOnlyTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(REFSONLYTIMESTAMP$14, targetList);
            org.etsi.uri.x01903.v13.XAdESTimeStampType[] result = new org.etsi.uri.x01903.v13.XAdESTimeStampType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "RefsOnlyTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getRefsOnlyTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(REFSONLYTIMESTAMP$14, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "RefsOnlyTimeStamp" element
     */
    public int sizeOfRefsOnlyTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(REFSONLYTIMESTAMP$14);
        }
    }
    
    /**
     * Sets array of all "RefsOnlyTimeStamp" element
     */
    public void setRefsOnlyTimeStampArray(org.etsi.uri.x01903.v13.XAdESTimeStampType[] refsOnlyTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(refsOnlyTimeStampArray, REFSONLYTIMESTAMP$14);
        }
    }
    
    /**
     * Sets ith "RefsOnlyTimeStamp" element
     */
    public void setRefsOnlyTimeStampArray(int i, org.etsi.uri.x01903.v13.XAdESTimeStampType refsOnlyTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(REFSONLYTIMESTAMP$14, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(refsOnlyTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "RefsOnlyTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType insertNewRefsOnlyTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().insert_element_user(REFSONLYTIMESTAMP$14, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "RefsOnlyTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewRefsOnlyTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(REFSONLYTIMESTAMP$14);
            return target;
        }
    }
    
    /**
     * Removes the ith "RefsOnlyTimeStamp" element
     */
    public void removeRefsOnlyTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(REFSONLYTIMESTAMP$14, i);
        }
    }
    
    /**
     * Gets array of all "CertificateValues" elements
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType[] getCertificateValuesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(CERTIFICATEVALUES$16, targetList);
            org.etsi.uri.x01903.v13.CertificateValuesType[] result = new org.etsi.uri.x01903.v13.CertificateValuesType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CertificateValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType getCertificateValuesArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().find_element_user(CERTIFICATEVALUES$16, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CertificateValues" element
     */
    public int sizeOfCertificateValuesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CERTIFICATEVALUES$16);
        }
    }
    
    /**
     * Sets array of all "CertificateValues" element
     */
    public void setCertificateValuesArray(org.etsi.uri.x01903.v13.CertificateValuesType[] certificateValuesArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(certificateValuesArray, CERTIFICATEVALUES$16);
        }
    }
    
    /**
     * Sets ith "CertificateValues" element
     */
    public void setCertificateValuesArray(int i, org.etsi.uri.x01903.v13.CertificateValuesType certificateValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().find_element_user(CERTIFICATEVALUES$16, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(certificateValues);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CertificateValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType insertNewCertificateValues(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().insert_element_user(CERTIFICATEVALUES$16, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CertificateValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType addNewCertificateValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().add_element_user(CERTIFICATEVALUES$16);
            return target;
        }
    }
    
    /**
     * Removes the ith "CertificateValues" element
     */
    public void removeCertificateValues(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CERTIFICATEVALUES$16, i);
        }
    }
    
    /**
     * Gets array of all "RevocationValues" elements
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType[] getRevocationValuesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(REVOCATIONVALUES$18, targetList);
            org.etsi.uri.x01903.v13.RevocationValuesType[] result = new org.etsi.uri.x01903.v13.RevocationValuesType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "RevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType getRevocationValuesArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().find_element_user(REVOCATIONVALUES$18, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "RevocationValues" element
     */
    public int sizeOfRevocationValuesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(REVOCATIONVALUES$18);
        }
    }
    
    /**
     * Sets array of all "RevocationValues" element
     */
    public void setRevocationValuesArray(org.etsi.uri.x01903.v13.RevocationValuesType[] revocationValuesArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(revocationValuesArray, REVOCATIONVALUES$18);
        }
    }
    
    /**
     * Sets ith "RevocationValues" element
     */
    public void setRevocationValuesArray(int i, org.etsi.uri.x01903.v13.RevocationValuesType revocationValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().find_element_user(REVOCATIONVALUES$18, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(revocationValues);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "RevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType insertNewRevocationValues(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().insert_element_user(REVOCATIONVALUES$18, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "RevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType addNewRevocationValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().add_element_user(REVOCATIONVALUES$18);
            return target;
        }
    }
    
    /**
     * Removes the ith "RevocationValues" element
     */
    public void removeRevocationValues(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(REVOCATIONVALUES$18, i);
        }
    }
    
    /**
     * Gets array of all "AttrAuthoritiesCertValues" elements
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType[] getAttrAuthoritiesCertValuesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRAUTHORITIESCERTVALUES$20, targetList);
            org.etsi.uri.x01903.v13.CertificateValuesType[] result = new org.etsi.uri.x01903.v13.CertificateValuesType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "AttrAuthoritiesCertValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType getAttrAuthoritiesCertValuesArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().find_element_user(ATTRAUTHORITIESCERTVALUES$20, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "AttrAuthoritiesCertValues" element
     */
    public int sizeOfAttrAuthoritiesCertValuesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRAUTHORITIESCERTVALUES$20);
        }
    }
    
    /**
     * Sets array of all "AttrAuthoritiesCertValues" element
     */
    public void setAttrAuthoritiesCertValuesArray(org.etsi.uri.x01903.v13.CertificateValuesType[] attrAuthoritiesCertValuesArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(attrAuthoritiesCertValuesArray, ATTRAUTHORITIESCERTVALUES$20);
        }
    }
    
    /**
     * Sets ith "AttrAuthoritiesCertValues" element
     */
    public void setAttrAuthoritiesCertValuesArray(int i, org.etsi.uri.x01903.v13.CertificateValuesType attrAuthoritiesCertValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().find_element_user(ATTRAUTHORITIESCERTVALUES$20, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(attrAuthoritiesCertValues);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AttrAuthoritiesCertValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType insertNewAttrAuthoritiesCertValues(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().insert_element_user(ATTRAUTHORITIESCERTVALUES$20, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AttrAuthoritiesCertValues" element
     */
    public org.etsi.uri.x01903.v13.CertificateValuesType addNewAttrAuthoritiesCertValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.CertificateValuesType target = null;
            target = (org.etsi.uri.x01903.v13.CertificateValuesType)get_store().add_element_user(ATTRAUTHORITIESCERTVALUES$20);
            return target;
        }
    }
    
    /**
     * Removes the ith "AttrAuthoritiesCertValues" element
     */
    public void removeAttrAuthoritiesCertValues(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRAUTHORITIESCERTVALUES$20, i);
        }
    }
    
    /**
     * Gets array of all "AttributeRevocationValues" elements
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType[] getAttributeRevocationValuesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTEREVOCATIONVALUES$22, targetList);
            org.etsi.uri.x01903.v13.RevocationValuesType[] result = new org.etsi.uri.x01903.v13.RevocationValuesType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "AttributeRevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType getAttributeRevocationValuesArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().find_element_user(ATTRIBUTEREVOCATIONVALUES$22, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "AttributeRevocationValues" element
     */
    public int sizeOfAttributeRevocationValuesArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTEREVOCATIONVALUES$22);
        }
    }
    
    /**
     * Sets array of all "AttributeRevocationValues" element
     */
    public void setAttributeRevocationValuesArray(org.etsi.uri.x01903.v13.RevocationValuesType[] attributeRevocationValuesArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(attributeRevocationValuesArray, ATTRIBUTEREVOCATIONVALUES$22);
        }
    }
    
    /**
     * Sets ith "AttributeRevocationValues" element
     */
    public void setAttributeRevocationValuesArray(int i, org.etsi.uri.x01903.v13.RevocationValuesType attributeRevocationValues)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().find_element_user(ATTRIBUTEREVOCATIONVALUES$22, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(attributeRevocationValues);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AttributeRevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType insertNewAttributeRevocationValues(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().insert_element_user(ATTRIBUTEREVOCATIONVALUES$22, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AttributeRevocationValues" element
     */
    public org.etsi.uri.x01903.v13.RevocationValuesType addNewAttributeRevocationValues()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.RevocationValuesType target = null;
            target = (org.etsi.uri.x01903.v13.RevocationValuesType)get_store().add_element_user(ATTRIBUTEREVOCATIONVALUES$22);
            return target;
        }
    }
    
    /**
     * Removes the ith "AttributeRevocationValues" element
     */
    public void removeAttributeRevocationValues(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTEREVOCATIONVALUES$22, i);
        }
    }
    
    /**
     * Gets array of all "ArchiveTimeStamp" elements
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType[] getArchiveTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ARCHIVETIMESTAMP$24, targetList);
            org.etsi.uri.x01903.v13.XAdESTimeStampType[] result = new org.etsi.uri.x01903.v13.XAdESTimeStampType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ArchiveTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType getArchiveTimeStampArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(ARCHIVETIMESTAMP$24, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ArchiveTimeStamp" element
     */
    public int sizeOfArchiveTimeStampArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ARCHIVETIMESTAMP$24);
        }
    }
    
    /**
     * Sets array of all "ArchiveTimeStamp" element
     */
    public void setArchiveTimeStampArray(org.etsi.uri.x01903.v13.XAdESTimeStampType[] archiveTimeStampArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(archiveTimeStampArray, ARCHIVETIMESTAMP$24);
        }
    }
    
    /**
     * Sets ith "ArchiveTimeStamp" element
     */
    public void setArchiveTimeStampArray(int i, org.etsi.uri.x01903.v13.XAdESTimeStampType archiveTimeStamp)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().find_element_user(ARCHIVETIMESTAMP$24, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(archiveTimeStamp);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ArchiveTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType insertNewArchiveTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().insert_element_user(ARCHIVETIMESTAMP$24, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ArchiveTimeStamp" element
     */
    public org.etsi.uri.x01903.v13.XAdESTimeStampType addNewArchiveTimeStamp()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x01903.v13.XAdESTimeStampType target = null;
            target = (org.etsi.uri.x01903.v13.XAdESTimeStampType)get_store().add_element_user(ARCHIVETIMESTAMP$24);
            return target;
        }
    }
    
    /**
     * Removes the ith "ArchiveTimeStamp" element
     */
    public void removeArchiveTimeStamp(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ARCHIVETIMESTAMP$24, i);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$26);
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
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$26);
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
            return get_store().find_attribute_user(ID$26) != null;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ID$26);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ID$26);
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
            target = (org.apache.xmlbeans.XmlID)get_store().find_attribute_user(ID$26);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlID)get_store().add_attribute_user(ID$26);
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
            get_store().remove_attribute(ID$26);
        }
    }
}
