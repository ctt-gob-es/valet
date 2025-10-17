/*
 * XML Type:  InternationalNamesType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.InternationalNamesType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML InternationalNamesType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class InternationalNamesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType
{
    
    public InternationalNamesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName NAME$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "Name");
    
    
    /**
     * Gets array of all "Name" elements
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType[] getNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(NAME$0, targetList);
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType[] result = new es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Name" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType getNameArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType)get_store().find_element_user(NAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Name" element
     */
    public int sizeOfNameArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(NAME$0);
        }
    }
    
    /**
     * Sets array of all "Name" element
     */
    public void setNameArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType[] nameArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(nameArray, NAME$0);
        }
    }
    
    /**
     * Sets ith "Name" element
     */
    public void setNameArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType)get_store().find_element_user(NAME$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(name);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Name" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType insertNewName(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType)get_store().insert_element_user(NAME$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Name" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType addNewName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangNormStringType)get_store().add_element_user(NAME$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Name" element
     */
    public void removeName(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(NAME$0, i);
        }
    }
}
