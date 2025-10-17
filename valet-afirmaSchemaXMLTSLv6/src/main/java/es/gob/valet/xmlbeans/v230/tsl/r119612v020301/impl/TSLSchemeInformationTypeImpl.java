/*
 * XML Type:  TSLSchemeInformationType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSLSchemeInformationType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.impl;
/**
 * An XML TSLSchemeInformationType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public class TSLSchemeInformationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType
{
    
    public TSLSchemeInformationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TSLVERSIONIDENTIFIER$0 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSLVersionIdentifier");
    private static final javax.xml.namespace.QName TSLSEQUENCENUMBER$2 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSLSequenceNumber");
    private static final javax.xml.namespace.QName TSLTYPE$4 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "TSLType");
    private static final javax.xml.namespace.QName SCHEMEOPERATORNAME$6 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeOperatorName");
    private static final javax.xml.namespace.QName SCHEMEOPERATORADDRESS$8 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeOperatorAddress");
    private static final javax.xml.namespace.QName SCHEMENAME$10 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeName");
    private static final javax.xml.namespace.QName SCHEMEINFORMATIONURI$12 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeInformationURI");
    private static final javax.xml.namespace.QName STATUSDETERMINATIONAPPROACH$14 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "StatusDeterminationApproach");
    private static final javax.xml.namespace.QName SCHEMETYPECOMMUNITYRULES$16 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeTypeCommunityRules");
    private static final javax.xml.namespace.QName SCHEMETERRITORY$18 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeTerritory");
    private static final javax.xml.namespace.QName POLICYORLEGALNOTICE$20 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "PolicyOrLegalNotice");
    private static final javax.xml.namespace.QName HISTORICALINFORMATIONPERIOD$22 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "HistoricalInformationPeriod");
    private static final javax.xml.namespace.QName POINTERSTOOTHERTSL$24 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "PointersToOtherTSL");
    private static final javax.xml.namespace.QName LISTISSUEDATETIME$26 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "ListIssueDateTime");
    private static final javax.xml.namespace.QName NEXTUPDATE$28 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "NextUpdate");
    private static final javax.xml.namespace.QName DISTRIBUTIONPOINTS$30 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "DistributionPoints");
    private static final javax.xml.namespace.QName SCHEMEEXTENSIONS$32 = 
        new javax.xml.namespace.QName("http://uri.etsi.org/02231/v2#", "SchemeExtensions");
    
    
    /**
     * Gets the "TSLVersionIdentifier" element
     */
    public java.math.BigInteger getTSLVersionIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLVERSIONIDENTIFIER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "TSLVersionIdentifier" element
     */
    public org.apache.xmlbeans.XmlInteger xgetTSLVersionIdentifier()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(TSLVERSIONIDENTIFIER$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "TSLVersionIdentifier" element
     */
    public void setTSLVersionIdentifier(java.math.BigInteger tslVersionIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLVERSIONIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TSLVERSIONIDENTIFIER$0);
            }
            target.setBigIntegerValue(tslVersionIdentifier);
        }
    }
    
    /**
     * Sets (as xml) the "TSLVersionIdentifier" element
     */
    public void xsetTSLVersionIdentifier(org.apache.xmlbeans.XmlInteger tslVersionIdentifier)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlInteger target = null;
            target = (org.apache.xmlbeans.XmlInteger)get_store().find_element_user(TSLVERSIONIDENTIFIER$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlInteger)get_store().add_element_user(TSLVERSIONIDENTIFIER$0);
            }
            target.set(tslVersionIdentifier);
        }
    }
    
    /**
     * Gets the "TSLSequenceNumber" element
     */
    public java.math.BigInteger getTSLSequenceNumber()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLSEQUENCENUMBER$2, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "TSLSequenceNumber" element
     */
    public org.apache.xmlbeans.XmlPositiveInteger xgetTSLSequenceNumber()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlPositiveInteger target = null;
            target = (org.apache.xmlbeans.XmlPositiveInteger)get_store().find_element_user(TSLSEQUENCENUMBER$2, 0);
            return target;
        }
    }
    
    /**
     * Sets the "TSLSequenceNumber" element
     */
    public void setTSLSequenceNumber(java.math.BigInteger tslSequenceNumber)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLSEQUENCENUMBER$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TSLSEQUENCENUMBER$2);
            }
            target.setBigIntegerValue(tslSequenceNumber);
        }
    }
    
    /**
     * Sets (as xml) the "TSLSequenceNumber" element
     */
    public void xsetTSLSequenceNumber(org.apache.xmlbeans.XmlPositiveInteger tslSequenceNumber)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlPositiveInteger target = null;
            target = (org.apache.xmlbeans.XmlPositiveInteger)get_store().find_element_user(TSLSEQUENCENUMBER$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlPositiveInteger)get_store().add_element_user(TSLSEQUENCENUMBER$2);
            }
            target.set(tslSequenceNumber);
        }
    }
    
    /**
     * Gets the "TSLType" element
     */
    public java.lang.String getTSLType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLTYPE$4, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "TSLType" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType xgetTSLType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(TSLTYPE$4, 0);
            return target;
        }
    }
    
    /**
     * Sets the "TSLType" element
     */
    public void setTSLType(java.lang.String tslType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(TSLTYPE$4, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(TSLTYPE$4);
            }
            target.setStringValue(tslType);
        }
    }
    
    /**
     * Sets (as xml) the "TSLType" element
     */
    public void xsetTSLType(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType tslType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(TSLTYPE$4, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().add_element_user(TSLTYPE$4);
            }
            target.set(tslType);
        }
    }
    
    /**
     * Gets the "SchemeOperatorName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getSchemeOperatorName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(SCHEMEOPERATORNAME$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeOperatorName" element
     */
    public void setSchemeOperatorName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType schemeOperatorName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(SCHEMEOPERATORNAME$6, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(SCHEMEOPERATORNAME$6);
            }
            target.set(schemeOperatorName);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeOperatorName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewSchemeOperatorName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(SCHEMEOPERATORNAME$6);
            return target;
        }
    }
    
    /**
     * Gets the "SchemeOperatorAddress" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType getSchemeOperatorAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType)get_store().find_element_user(SCHEMEOPERATORADDRESS$8, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeOperatorAddress" element
     */
    public void setSchemeOperatorAddress(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType schemeOperatorAddress)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType)get_store().find_element_user(SCHEMEOPERATORADDRESS$8, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType)get_store().add_element_user(SCHEMEOPERATORADDRESS$8);
            }
            target.set(schemeOperatorAddress);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeOperatorAddress" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType addNewSchemeOperatorAddress()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType)get_store().add_element_user(SCHEMEOPERATORADDRESS$8);
            return target;
        }
    }
    
    /**
     * Gets the "SchemeName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getSchemeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(SCHEMENAME$10, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeName" element
     */
    public void setSchemeName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType schemeName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().find_element_user(SCHEMENAME$10, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(SCHEMENAME$10);
            }
            target.set(schemeName);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeName" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewSchemeName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType)get_store().add_element_user(SCHEMENAME$10);
            return target;
        }
    }
    
    /**
     * Gets the "SchemeInformationURI" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getSchemeInformationURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMEINFORMATIONURI$12, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SchemeInformationURI" element
     */
    public void setSchemeInformationURI(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType schemeInformationURI)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMEINFORMATIONURI$12, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMEINFORMATIONURI$12);
            }
            target.set(schemeInformationURI);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeInformationURI" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewSchemeInformationURI()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMEINFORMATIONURI$12);
            return target;
        }
    }
    
    /**
     * Gets the "StatusDeterminationApproach" element
     */
    public java.lang.String getStatusDeterminationApproach()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETERMINATIONAPPROACH$14, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "StatusDeterminationApproach" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType xgetStatusDeterminationApproach()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(STATUSDETERMINATIONAPPROACH$14, 0);
            return target;
        }
    }
    
    /**
     * Sets the "StatusDeterminationApproach" element
     */
    public void setStatusDeterminationApproach(java.lang.String statusDeterminationApproach)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSDETERMINATIONAPPROACH$14, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSDETERMINATIONAPPROACH$14);
            }
            target.setStringValue(statusDeterminationApproach);
        }
    }
    
    /**
     * Sets (as xml) the "StatusDeterminationApproach" element
     */
    public void xsetStatusDeterminationApproach(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType statusDeterminationApproach)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().find_element_user(STATUSDETERMINATIONAPPROACH$14, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType)get_store().add_element_user(STATUSDETERMINATIONAPPROACH$14);
            }
            target.set(statusDeterminationApproach);
        }
    }
    
    /**
     * Gets the "SchemeTypeCommunityRules" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getSchemeTypeCommunityRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMETYPECOMMUNITYRULES$16, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SchemeTypeCommunityRules" element
     */
    public boolean isSetSchemeTypeCommunityRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SCHEMETYPECOMMUNITYRULES$16) != 0;
        }
    }
    
    /**
     * Sets the "SchemeTypeCommunityRules" element
     */
    public void setSchemeTypeCommunityRules(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType schemeTypeCommunityRules)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().find_element_user(SCHEMETYPECOMMUNITYRULES$16, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMETYPECOMMUNITYRULES$16);
            }
            target.set(schemeTypeCommunityRules);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeTypeCommunityRules" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewSchemeTypeCommunityRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType)get_store().add_element_user(SCHEMETYPECOMMUNITYRULES$16);
            return target;
        }
    }
    
    /**
     * Unsets the "SchemeTypeCommunityRules" element
     */
    public void unsetSchemeTypeCommunityRules()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SCHEMETYPECOMMUNITYRULES$16, 0);
        }
    }
    
    /**
     * Gets the "SchemeTerritory" element
     */
    public java.lang.String getSchemeTerritory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SCHEMETERRITORY$18, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "SchemeTerritory" element
     */
    public org.apache.xmlbeans.XmlString xgetSchemeTerritory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SCHEMETERRITORY$18, 0);
            return target;
        }
    }
    
    /**
     * True if has "SchemeTerritory" element
     */
    public boolean isSetSchemeTerritory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SCHEMETERRITORY$18) != 0;
        }
    }
    
    /**
     * Sets the "SchemeTerritory" element
     */
    public void setSchemeTerritory(java.lang.String schemeTerritory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(SCHEMETERRITORY$18, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(SCHEMETERRITORY$18);
            }
            target.setStringValue(schemeTerritory);
        }
    }
    
    /**
     * Sets (as xml) the "SchemeTerritory" element
     */
    public void xsetSchemeTerritory(org.apache.xmlbeans.XmlString schemeTerritory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(SCHEMETERRITORY$18, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(SCHEMETERRITORY$18);
            }
            target.set(schemeTerritory);
        }
    }
    
    /**
     * Unsets the "SchemeTerritory" element
     */
    public void unsetSchemeTerritory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SCHEMETERRITORY$18, 0);
        }
    }
    
    /**
     * Gets the "PolicyOrLegalNotice" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType getPolicyOrLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType)get_store().find_element_user(POLICYORLEGALNOTICE$20, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "PolicyOrLegalNotice" element
     */
    public boolean isSetPolicyOrLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYORLEGALNOTICE$20) != 0;
        }
    }
    
    /**
     * Sets the "PolicyOrLegalNotice" element
     */
    public void setPolicyOrLegalNotice(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType policyOrLegalNotice)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType)get_store().find_element_user(POLICYORLEGALNOTICE$20, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType)get_store().add_element_user(POLICYORLEGALNOTICE$20);
            }
            target.set(policyOrLegalNotice);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicyOrLegalNotice" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType addNewPolicyOrLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType)get_store().add_element_user(POLICYORLEGALNOTICE$20);
            return target;
        }
    }
    
    /**
     * Unsets the "PolicyOrLegalNotice" element
     */
    public void unsetPolicyOrLegalNotice()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYORLEGALNOTICE$20, 0);
        }
    }
    
    /**
     * Gets the "HistoricalInformationPeriod" element
     */
    public java.math.BigInteger getHistoricalInformationPeriod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HISTORICALINFORMATIONPERIOD$22, 0);
            if (target == null)
            {
                return null;
            }
            return target.getBigIntegerValue();
        }
    }
    
    /**
     * Gets (as xml) the "HistoricalInformationPeriod" element
     */
    public org.apache.xmlbeans.XmlNonNegativeInteger xgetHistoricalInformationPeriod()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlNonNegativeInteger target = null;
            target = (org.apache.xmlbeans.XmlNonNegativeInteger)get_store().find_element_user(HISTORICALINFORMATIONPERIOD$22, 0);
            return target;
        }
    }
    
    /**
     * Sets the "HistoricalInformationPeriod" element
     */
    public void setHistoricalInformationPeriod(java.math.BigInteger historicalInformationPeriod)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(HISTORICALINFORMATIONPERIOD$22, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(HISTORICALINFORMATIONPERIOD$22);
            }
            target.setBigIntegerValue(historicalInformationPeriod);
        }
    }
    
    /**
     * Sets (as xml) the "HistoricalInformationPeriod" element
     */
    public void xsetHistoricalInformationPeriod(org.apache.xmlbeans.XmlNonNegativeInteger historicalInformationPeriod)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlNonNegativeInteger target = null;
            target = (org.apache.xmlbeans.XmlNonNegativeInteger)get_store().find_element_user(HISTORICALINFORMATIONPERIOD$22, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlNonNegativeInteger)get_store().add_element_user(HISTORICALINFORMATIONPERIOD$22);
            }
            target.set(historicalInformationPeriod);
        }
    }
    
    /**
     * Gets the "PointersToOtherTSL" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType getPointersToOtherTSL()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType)get_store().find_element_user(POINTERSTOOTHERTSL$24, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "PointersToOtherTSL" element
     */
    public boolean isSetPointersToOtherTSL()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POINTERSTOOTHERTSL$24) != 0;
        }
    }
    
    /**
     * Sets the "PointersToOtherTSL" element
     */
    public void setPointersToOtherTSL(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType pointersToOtherTSL)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType)get_store().find_element_user(POINTERSTOOTHERTSL$24, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType)get_store().add_element_user(POINTERSTOOTHERTSL$24);
            }
            target.set(pointersToOtherTSL);
        }
    }
    
    /**
     * Appends and returns a new empty "PointersToOtherTSL" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType addNewPointersToOtherTSL()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType)get_store().add_element_user(POINTERSTOOTHERTSL$24);
            return target;
        }
    }
    
    /**
     * Unsets the "PointersToOtherTSL" element
     */
    public void unsetPointersToOtherTSL()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POINTERSTOOTHERTSL$24, 0);
        }
    }
    
    /**
     * Gets the "ListIssueDateTime" element
     */
    public java.util.Calendar getListIssueDateTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LISTISSUEDATETIME$26, 0);
            if (target == null)
            {
                return null;
            }
            return target.getCalendarValue();
        }
    }
    
    /**
     * Gets (as xml) the "ListIssueDateTime" element
     */
    public org.apache.xmlbeans.XmlDateTime xgetListIssueDateTime()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(LISTISSUEDATETIME$26, 0);
            return target;
        }
    }
    
    /**
     * Sets the "ListIssueDateTime" element
     */
    public void setListIssueDateTime(java.util.Calendar listIssueDateTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(LISTISSUEDATETIME$26, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(LISTISSUEDATETIME$26);
            }
            target.setCalendarValue(listIssueDateTime);
        }
    }
    
    /**
     * Sets (as xml) the "ListIssueDateTime" element
     */
    public void xsetListIssueDateTime(org.apache.xmlbeans.XmlDateTime listIssueDateTime)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlDateTime target = null;
            target = (org.apache.xmlbeans.XmlDateTime)get_store().find_element_user(LISTISSUEDATETIME$26, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlDateTime)get_store().add_element_user(LISTISSUEDATETIME$26);
            }
            target.set(listIssueDateTime);
        }
    }
    
    /**
     * Gets the "NextUpdate" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType getNextUpdate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType)get_store().find_element_user(NEXTUPDATE$28, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "NextUpdate" element
     */
    public void setNextUpdate(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType nextUpdate)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType)get_store().find_element_user(NEXTUPDATE$28, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType)get_store().add_element_user(NEXTUPDATE$28);
            }
            target.set(nextUpdate);
        }
    }
    
    /**
     * Appends and returns a new empty "NextUpdate" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType addNewNextUpdate()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType)get_store().add_element_user(NEXTUPDATE$28);
            return target;
        }
    }
    
    /**
     * Gets the "DistributionPoints" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType getDistributionPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType)get_store().find_element_user(DISTRIBUTIONPOINTS$30, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "DistributionPoints" element
     */
    public boolean isSetDistributionPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DISTRIBUTIONPOINTS$30) != 0;
        }
    }
    
    /**
     * Sets the "DistributionPoints" element
     */
    public void setDistributionPoints(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType distributionPoints)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType)get_store().find_element_user(DISTRIBUTIONPOINTS$30, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType)get_store().add_element_user(DISTRIBUTIONPOINTS$30);
            }
            target.set(distributionPoints);
        }
    }
    
    /**
     * Appends and returns a new empty "DistributionPoints" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType addNewDistributionPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType)get_store().add_element_user(DISTRIBUTIONPOINTS$30);
            return target;
        }
    }
    
    /**
     * Unsets the "DistributionPoints" element
     */
    public void unsetDistributionPoints()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DISTRIBUTIONPOINTS$30, 0);
        }
    }
    
    /**
     * Gets the "SchemeExtensions" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType getSchemeExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType)get_store().find_element_user(SCHEMEEXTENSIONS$32, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "SchemeExtensions" element
     */
    public boolean isSetSchemeExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SCHEMEEXTENSIONS$32) != 0;
        }
    }
    
    /**
     * Sets the "SchemeExtensions" element
     */
    public void setSchemeExtensions(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType schemeExtensions)
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType)get_store().find_element_user(SCHEMEEXTENSIONS$32, 0);
            if (target == null)
            {
                target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType)get_store().add_element_user(SCHEMEEXTENSIONS$32);
            }
            target.set(schemeExtensions);
        }
    }
    
    /**
     * Appends and returns a new empty "SchemeExtensions" element
     */
    public es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType addNewSchemeExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType target = null;
            target = (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType)get_store().add_element_user(SCHEMEEXTENSIONS$32);
            return target;
        }
    }
    
    /**
     * Unsets the "SchemeExtensions" element
     */
    public void unsetSchemeExtensions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SCHEMEEXTENSIONS$32, 0);
        }
    }
}
