/*
 * XML Type:  TSPInformationType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSPInformationType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML TSPInformationType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class TSPInformationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType
{
    
    public TSPInformationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSPNAME$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPName");
    private static final javax.xml.namespace.QName TSPTRADENAME$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPTradeName");
    private static final javax.xml.namespace.QName TSPADDRESS$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPAddress");
    private static final javax.xml.namespace.QName TSPINFORMATIONURI$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPInformationURI");
    private static final javax.xml.namespace.QName TSPINFORMATIONEXTENSIONS$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSPInformationExtensions");
    
    
    /**
     * Gets the "TSPName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getTSPName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(TSPNAME$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPName" element
     */
    public void setTSPName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType tspName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(TSPNAME$0, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(TSPNAME$0);
            }
            target.set(tspName);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewTSPName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(TSPNAME$0);
            return target;
        }
    }
    
    /**
     * Gets the "TSPTradeName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getTSPTradeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(TSPTRADENAME$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "TSPTradeName" element
     */
    public boolean isSetTSPTradeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TSPTRADENAME$2) != 0;
        }
    }
    
    /**
     * Sets the "TSPTradeName" element
     */
    public void setTSPTradeName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType tspTradeName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(TSPTRADENAME$2, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(TSPTRADENAME$2);
            }
            target.set(tspTradeName);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPTradeName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewTSPTradeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(TSPTRADENAME$2);
            return target;
        }
    }
    
    /**
     * Unsets the "TSPTradeName" element
     */
    public void unsetTSPTradeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TSPTRADENAME$2, 0);
        }
    }
    
    /**
     * Gets the "TSPAddress" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType getTSPAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType)get_store().find_element_user(TSPADDRESS$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPAddress" element
     */
    public void setTSPAddress(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType tspAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType)get_store().find_element_user(TSPADDRESS$4, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType)get_store().add_element_user(TSPADDRESS$4);
            }
            target.set(tspAddress);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPAddress" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType addNewTSPAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType)get_store().add_element_user(TSPADDRESS$4);
            return target;
        }
    }
    
    /**
     * Gets the "TSPInformationURI" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getTSPInformationURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(TSPINFORMATIONURI$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "TSPInformationURI" element
     */
    public void setTSPInformationURI(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType tspInformationURI)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(TSPINFORMATIONURI$6, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(TSPINFORMATIONURI$6);
            }
            target.set(tspInformationURI);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPInformationURI" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewTSPInformationURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(TSPINFORMATIONURI$6);
            return target;
        }
    }
    
    /**
     * Gets the "TSPInformationExtensions" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType getTSPInformationExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType)get_store().find_element_user(TSPINFORMATIONEXTENSIONS$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "TSPInformationExtensions" element
     */
    public boolean isSetTSPInformationExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TSPINFORMATIONEXTENSIONS$8) != 0;
        }
    }
    
    /**
     * Sets the "TSPInformationExtensions" element
     */
    public void setTSPInformationExtensions(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType tspInformationExtensions)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType)get_store().find_element_user(TSPINFORMATIONEXTENSIONS$8, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType)get_store().add_element_user(TSPINFORMATIONEXTENSIONS$8);
            }
            target.set(tspInformationExtensions);
        }
    }
    
    /**
     * Appends and returns a new empty "TSPInformationExtensions" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType addNewTSPInformationExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType)get_store().add_element_user(TSPINFORMATIONEXTENSIONS$8);
            return target;
        }
    }
    
    /**
     * Unsets the "TSPInformationExtensions" element
     */
    public void unsetTSPInformationExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TSPINFORMATIONEXTENSIONS$8, 0);
        }
    }
}
