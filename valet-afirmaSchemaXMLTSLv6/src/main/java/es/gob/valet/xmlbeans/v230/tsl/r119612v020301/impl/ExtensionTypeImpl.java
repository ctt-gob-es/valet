/*
 * XML Type:  ExtensionType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.ExtensionType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML ExtensionType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class ExtensionTypeImpl extends es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl.AnyTypeImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionType
{
    
    public ExtensionTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CRITICAL$0 = 
        new javax.xml.namespace.QName("", "Critical");
    
    
    /**
     * Gets the "Critical" attribute
     */
    public boolean getCritical()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CRITICAL$0);
            if (target == null)
            {
                return false;
            }
            return target.getBooleanValue();
        }
    }
    
    /**
     * Gets (as xml) the "Critical" attribute
     */
    public org.apache.xmlbeans.XmlBoolean xgetCritical()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(CRITICAL$0);
            return target;
        }
    }
    
    /**
     * Sets the "Critical" attribute
     */
    public void setCritical(boolean critical)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(CRITICAL$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(CRITICAL$0);
            }
            target.setBooleanValue(critical);
        }
    }
    
    /**
     * Sets (as xml) the "Critical" attribute
     */
    public void xsetCritical(org.apache.xmlbeans.XmlBoolean critical)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(CRITICAL$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBoolean)get_store().add_attribute_user(CRITICAL$0);
            }
            target.set(critical);
        }
    }
}
