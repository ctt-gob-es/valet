/*
 * XML Type:  SignatureType
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.SignatureType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig.impl;
/**
 * An XML SignatureType(@http://www.w3.org/2000/09/xmldsig#).
 *
 * This is a complex type.
 */
public class SignatureTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.w3.x2000.x09.xmldsig.SignatureType
{
    
    public SignatureTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SIGNEDINFO$0 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignedInfo");
    private static final javax.xml.namespace.QName SIGNATUREVALUE$2 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "SignatureValue");
    private static final javax.xml.namespace.QName KEYINFO$4 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "KeyInfo");
    private static final javax.xml.namespace.QName OBJECT$6 = 
        new javax.xml.namespace.QName("http://www.w3.org/2000/09/xmldsig#", "Object");
    private static final javax.xml.namespace.QName ID$8 = 
        new javax.xml.namespace.QName("", "Id");
    
    
    /**
     * Gets the "SignedInfo" element
     */
    public org.w3.x2000.x09.xmldsig.SignedInfoType getSignedInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignedInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignedInfoType)get_store().find_element_user(SIGNEDINFO$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignedInfo" element
     */
    public void setSignedInfo(org.w3.x2000.x09.xmldsig.SignedInfoType signedInfo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignedInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignedInfoType)get_store().find_element_user(SIGNEDINFO$0, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.SignedInfoType)get_store().add_element_user(SIGNEDINFO$0);
            }
            target.set(signedInfo);
        }
    }
    
    /**
     * Appends and returns a new empty "SignedInfo" element
     */
    public org.w3.x2000.x09.xmldsig.SignedInfoType addNewSignedInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignedInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignedInfoType)get_store().add_element_user(SIGNEDINFO$0);
            return target;
        }
    }
    
    /**
     * Gets the "SignatureValue" element
     */
    public org.w3.x2000.x09.xmldsig.SignatureValueType getSignatureValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureValueType)get_store().find_element_user(SIGNATUREVALUE$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SignatureValue" element
     */
    public void setSignatureValue(org.w3.x2000.x09.xmldsig.SignatureValueType signatureValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureValueType)get_store().find_element_user(SIGNATUREVALUE$2, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.SignatureValueType)get_store().add_element_user(SIGNATUREVALUE$2);
            }
            target.set(signatureValue);
        }
    }
    
    /**
     * Appends and returns a new empty "SignatureValue" element
     */
    public org.w3.x2000.x09.xmldsig.SignatureValueType addNewSignatureValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.SignatureValueType target = null;
            target = (org.w3.x2000.x09.xmldsig.SignatureValueType)get_store().add_element_user(SIGNATUREVALUE$2);
            return target;
        }
    }
    
    /**
     * Gets the "KeyInfo" element
     */
    public org.w3.x2000.x09.xmldsig.KeyInfoType getKeyInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyInfoType)get_store().find_element_user(KEYINFO$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "KeyInfo" element
     */
    public boolean isSetKeyInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(KEYINFO$4) != 0;
        }
    }
    
    /**
     * Sets the "KeyInfo" element
     */
    public void setKeyInfo(org.w3.x2000.x09.xmldsig.KeyInfoType keyInfo)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyInfoType)get_store().find_element_user(KEYINFO$4, 0);
            if (target == null)
            {
                target = (org.w3.x2000.x09.xmldsig.KeyInfoType)get_store().add_element_user(KEYINFO$4);
            }
            target.set(keyInfo);
        }
    }
    
    /**
     * Appends and returns a new empty "KeyInfo" element
     */
    public org.w3.x2000.x09.xmldsig.KeyInfoType addNewKeyInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.KeyInfoType target = null;
            target = (org.w3.x2000.x09.xmldsig.KeyInfoType)get_store().add_element_user(KEYINFO$4);
            return target;
        }
    }
    
    /**
     * Unsets the "KeyInfo" element
     */
    public void unsetKeyInfo()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(KEYINFO$4, 0);
        }
    }
    
    /**
     * Gets array of all "Object" elements
     */
    public org.w3.x2000.x09.xmldsig.ObjectType[] getObjectArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OBJECT$6, targetList);
            org.w3.x2000.x09.xmldsig.ObjectType[] result = new org.w3.x2000.x09.xmldsig.ObjectType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Object" element
     */
    public org.w3.x2000.x09.xmldsig.ObjectType getObjectArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ObjectType target = null;
            target = (org.w3.x2000.x09.xmldsig.ObjectType)get_store().find_element_user(OBJECT$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Object" element
     */
    public int sizeOfObjectArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OBJECT$6);
        }
    }
    
    /**
     * Sets array of all "Object" element
     */
    public void setObjectArray(org.w3.x2000.x09.xmldsig.ObjectType[] objectArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(objectArray, OBJECT$6);
        }
    }
    
    /**
     * Sets ith "Object" element
     */
    public void setObjectArray(int i, org.w3.x2000.x09.xmldsig.ObjectType object)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ObjectType target = null;
            target = (org.w3.x2000.x09.xmldsig.ObjectType)get_store().find_element_user(OBJECT$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(object);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Object" element
     */
    public org.w3.x2000.x09.xmldsig.ObjectType insertNewObject(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ObjectType target = null;
            target = (org.w3.x2000.x09.xmldsig.ObjectType)get_store().insert_element_user(OBJECT$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Object" element
     */
    public org.w3.x2000.x09.xmldsig.ObjectType addNewObject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.w3.x2000.x09.xmldsig.ObjectType target = null;
            target = (org.w3.x2000.x09.xmldsig.ObjectType)get_store().add_element_user(OBJECT$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "Object" element
     */
    public void removeObject(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OBJECT$6, i);
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
