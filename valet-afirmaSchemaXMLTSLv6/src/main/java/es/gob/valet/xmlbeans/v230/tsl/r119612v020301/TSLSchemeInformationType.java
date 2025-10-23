/*
 * XML Type:  TSLSchemeInformationType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSLSchemeInformationType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301;


/**
 * An XML TSLSchemeInformationType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public interface TSLSchemeInformationType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TSLSchemeInformationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("tslschemeinformationtype3d43type");
    
    /**
     * Gets the "TSLVersionIdentifier" element
     */
    java.math.BigInteger getTSLVersionIdentifier();
    
    /**
     * Gets (as xml) the "TSLVersionIdentifier" element
     */
    org.apache.xmlbeans.XmlInteger xgetTSLVersionIdentifier();
    
    /**
     * Sets the "TSLVersionIdentifier" element
     */
    void setTSLVersionIdentifier(java.math.BigInteger tslVersionIdentifier);
    
    /**
     * Sets (as xml) the "TSLVersionIdentifier" element
     */
    void xsetTSLVersionIdentifier(org.apache.xmlbeans.XmlInteger tslVersionIdentifier);
    
    /**
     * Gets the "TSLSequenceNumber" element
     */
    java.math.BigInteger getTSLSequenceNumber();
    
    /**
     * Gets (as xml) the "TSLSequenceNumber" element
     */
    org.apache.xmlbeans.XmlPositiveInteger xgetTSLSequenceNumber();
    
    /**
     * Sets the "TSLSequenceNumber" element
     */
    void setTSLSequenceNumber(java.math.BigInteger tslSequenceNumber);
    
    /**
     * Sets (as xml) the "TSLSequenceNumber" element
     */
    void xsetTSLSequenceNumber(org.apache.xmlbeans.XmlPositiveInteger tslSequenceNumber);
    
    /**
     * Gets the "TSLType" element
     */
    java.lang.String getTSLType();
    
    /**
     * Gets (as xml) the "TSLType" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType xgetTSLType();
    
    /**
     * Sets the "TSLType" element
     */
    void setTSLType(java.lang.String tslType);
    
    /**
     * Sets (as xml) the "TSLType" element
     */
    void xsetTSLType(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType tslType);
    
    /**
     * Gets the "SchemeOperatorName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getSchemeOperatorName();
    
    /**
     * Sets the "SchemeOperatorName" element
     */
    void setSchemeOperatorName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType schemeOperatorName);
    
    /**
     * Appends and returns a new empty "SchemeOperatorName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewSchemeOperatorName();
    
    /**
     * Gets the "SchemeOperatorAddress" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType getSchemeOperatorAddress();
    
    /**
     * Sets the "SchemeOperatorAddress" element
     */
    void setSchemeOperatorAddress(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType schemeOperatorAddress);
    
    /**
     * Appends and returns a new empty "SchemeOperatorAddress" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType addNewSchemeOperatorAddress();
    
    /**
     * Gets the "SchemeName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getSchemeName();
    
    /**
     * Sets the "SchemeName" element
     */
    void setSchemeName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType schemeName);
    
    /**
     * Appends and returns a new empty "SchemeName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewSchemeName();
    
    /**
     * Gets the "SchemeInformationURI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getSchemeInformationURI();
    
    /**
     * Sets the "SchemeInformationURI" element
     */
    void setSchemeInformationURI(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType schemeInformationURI);
    
    /**
     * Appends and returns a new empty "SchemeInformationURI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewSchemeInformationURI();
    
    /**
     * Gets the "StatusDeterminationApproach" element
     */
    java.lang.String getStatusDeterminationApproach();
    
    /**
     * Gets (as xml) the "StatusDeterminationApproach" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType xgetStatusDeterminationApproach();
    
    /**
     * Sets the "StatusDeterminationApproach" element
     */
    void setStatusDeterminationApproach(java.lang.String statusDeterminationApproach);
    
    /**
     * Sets (as xml) the "StatusDeterminationApproach" element
     */
    void xsetStatusDeterminationApproach(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType statusDeterminationApproach);
    
    /**
     * Gets the "SchemeTypeCommunityRules" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getSchemeTypeCommunityRules();
    
    /**
     * True if has "SchemeTypeCommunityRules" element
     */
    boolean isSetSchemeTypeCommunityRules();
    
    /**
     * Sets the "SchemeTypeCommunityRules" element
     */
    void setSchemeTypeCommunityRules(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType schemeTypeCommunityRules);
    
    /**
     * Appends and returns a new empty "SchemeTypeCommunityRules" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewSchemeTypeCommunityRules();
    
    /**
     * Unsets the "SchemeTypeCommunityRules" element
     */
    void unsetSchemeTypeCommunityRules();
    
    /**
     * Gets the "SchemeTerritory" element
     */
    java.lang.String getSchemeTerritory();
    
    /**
     * Gets (as xml) the "SchemeTerritory" element
     */
    org.apache.xmlbeans.XmlString xgetSchemeTerritory();
    
    /**
     * True if has "SchemeTerritory" element
     */
    boolean isSetSchemeTerritory();
    
    /**
     * Sets the "SchemeTerritory" element
     */
    void setSchemeTerritory(java.lang.String schemeTerritory);
    
    /**
     * Sets (as xml) the "SchemeTerritory" element
     */
    void xsetSchemeTerritory(org.apache.xmlbeans.XmlString schemeTerritory);
    
    /**
     * Unsets the "SchemeTerritory" element
     */
    void unsetSchemeTerritory();
    
    /**
     * Gets the "PolicyOrLegalNotice" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType getPolicyOrLegalNotice();
    
    /**
     * True if has "PolicyOrLegalNotice" element
     */
    boolean isSetPolicyOrLegalNotice();
    
    /**
     * Sets the "PolicyOrLegalNotice" element
     */
    void setPolicyOrLegalNotice(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType policyOrLegalNotice);
    
    /**
     * Appends and returns a new empty "PolicyOrLegalNotice" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType addNewPolicyOrLegalNotice();
    
    /**
     * Unsets the "PolicyOrLegalNotice" element
     */
    void unsetPolicyOrLegalNotice();
    
    /**
     * Gets the "HistoricalInformationPeriod" element
     */
    java.math.BigInteger getHistoricalInformationPeriod();
    
    /**
     * Gets (as xml) the "HistoricalInformationPeriod" element
     */
    org.apache.xmlbeans.XmlNonNegativeInteger xgetHistoricalInformationPeriod();
    
    /**
     * Sets the "HistoricalInformationPeriod" element
     */
    void setHistoricalInformationPeriod(java.math.BigInteger historicalInformationPeriod);
    
    /**
     * Sets (as xml) the "HistoricalInformationPeriod" element
     */
    void xsetHistoricalInformationPeriod(org.apache.xmlbeans.XmlNonNegativeInteger historicalInformationPeriod);
    
    /**
     * Gets the "PointersToOtherTSL" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType getPointersToOtherTSL();
    
    /**
     * True if has "PointersToOtherTSL" element
     */
    boolean isSetPointersToOtherTSL();
    
    /**
     * Sets the "PointersToOtherTSL" element
     */
    void setPointersToOtherTSL(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType pointersToOtherTSL);
    
    /**
     * Appends and returns a new empty "PointersToOtherTSL" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.OtherTSLPointersType addNewPointersToOtherTSL();
    
    /**
     * Unsets the "PointersToOtherTSL" element
     */
    void unsetPointersToOtherTSL();
    
    /**
     * Gets the "ListIssueDateTime" element
     */
    java.util.Calendar getListIssueDateTime();
    
    /**
     * Gets (as xml) the "ListIssueDateTime" element
     */
    org.apache.xmlbeans.XmlDateTime xgetListIssueDateTime();
    
    /**
     * Sets the "ListIssueDateTime" element
     */
    void setListIssueDateTime(java.util.Calendar listIssueDateTime);
    
    /**
     * Sets (as xml) the "ListIssueDateTime" element
     */
    void xsetListIssueDateTime(org.apache.xmlbeans.XmlDateTime listIssueDateTime);
    
    /**
     * Gets the "NextUpdate" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType getNextUpdate();
    
    /**
     * Sets the "NextUpdate" element
     */
    void setNextUpdate(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType nextUpdate);
    
    /**
     * Appends and returns a new empty "NextUpdate" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NextUpdateType addNewNextUpdate();
    
    /**
     * Gets the "DistributionPoints" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType getDistributionPoints();
    
    /**
     * True if has "DistributionPoints" element
     */
    boolean isSetDistributionPoints();
    
    /**
     * Sets the "DistributionPoints" element
     */
    void setDistributionPoints(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType distributionPoints);
    
    /**
     * Appends and returns a new empty "DistributionPoints" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIListType addNewDistributionPoints();
    
    /**
     * Unsets the "DistributionPoints" element
     */
    void unsetDistributionPoints();
    
    /**
     * Gets the "SchemeExtensions" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType getSchemeExtensions();
    
    /**
     * True if has "SchemeExtensions" element
     */
    boolean isSetSchemeExtensions();
    
    /**
     * Sets the "SchemeExtensions" element
     */
    void setSchemeExtensions(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType schemeExtensions);
    
    /**
     * Appends and returns a new empty "SchemeExtensions" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType addNewSchemeExtensions();
    
    /**
     * Unsets the "SchemeExtensions" element
     */
    void unsetSchemeExtensions();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType newInstance() {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSLSchemeInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
