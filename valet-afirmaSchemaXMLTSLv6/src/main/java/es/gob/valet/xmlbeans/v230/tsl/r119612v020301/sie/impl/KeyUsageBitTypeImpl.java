/*
 * XML Type:  KeyUsageBitType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.KeyUsageBitType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.impl;
/**
 * An XML KeyUsageBitType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is an atomic type that is a restriction of es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.KeyUsageBitType.
 */
public class KeyUsageBitTypeImpl extends org.apache.xmlbeans.impl.values.JavaBooleanHolderEx implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType
{
    
    public KeyUsageBitTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType, true);
    }
    
    protected KeyUsageBitTypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
    {
        super(sType, b);
    }
    
    private static final javax.xml.namespace.QName NAME$0 = 
        new javax.xml.namespace.QName("", "name");
    
    
    /**
     * Gets the "name" attribute
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name.Enum getName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$0);
            if (target == null)
            {
                return null;
            }
            return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "name" attribute
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name xgetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name)get_store().find_attribute_user(NAME$0);
            return target;
        }
    }
    
    /**
     * True if has "name" attribute
     */
    public boolean isSetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(NAME$0) != null;
        }
    }
    
    /**
     * Sets the "name" attribute
     */
    public void setName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name.Enum name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(NAME$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(NAME$0);
            }
            target.setEnumValue(name);
        }
    }
    
    /**
     * Sets (as xml) the "name" attribute
     */
    public void xsetName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name name)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name)get_store().find_attribute_user(NAME$0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name)get_store().add_attribute_user(NAME$0);
            }
            target.set(name);
        }
    }
    
    /**
     * Unsets the "name" attribute
     */
    public void unsetName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(NAME$0);
        }
    }
    /**
     * An XML name(@).
     *
     * This is an atomic type that is a restriction of es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.KeyUsageBitType$Name.
     */
    public static class NameImpl extends org.apache.xmlbeans.impl.values.JavaStringEnumerationHolderEx implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name
    {
        
        public NameImpl(org.apache.xmlbeans.SchemaType sType)
        {
            super(sType, false);
        }
        
        protected NameImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
        {
            super(sType, b);
        }
    }
}
