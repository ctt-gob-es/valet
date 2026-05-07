/*
 * An XML document type.
 * Localname: ExtendedKeyUsage
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageDocument
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.additionaltypes.impl;
/**
 * A document containing one ExtendedKeyUsage(@http://uri.etsi.org/02231/v2/additionaltypes#) element.
 *
 * This is a complex type.
 */
public class ExtendedKeyUsageDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageDocument
{
    
    public ExtendedKeyUsageDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXTENDEDKEYUSAGE$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2/additionaltypes#", "ExtendedKeyUsage");
    
    
    /**
     * Gets the "ExtendedKeyUsage" element
     */
    public org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType getExtendedKeyUsage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType target = null;
            target = (org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType)get_store().find_element_user(EXTENDEDKEYUSAGE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ExtendedKeyUsage" element
     */
    public void setExtendedKeyUsage(org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType extendedKeyUsage)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType target = null;
            target = (org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType)get_store().find_element_user(EXTENDEDKEYUSAGE$0, 0);
            if (target == null)
            {
                target = (org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType)get_store().add_element_user(EXTENDEDKEYUSAGE$0);
            }
            target.set(extendedKeyUsage);
        }
    }
    
    /**
     * Appends and returns a new empty "ExtendedKeyUsage" element
     */
    public org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType addNewExtendedKeyUsage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType target = null;
            target = (org.etsi.uri.x02231.v2.additionaltypes.ExtendedKeyUsageType)get_store().add_element_user(EXTENDEDKEYUSAGE$0);
            return target;
        }
    }
}
