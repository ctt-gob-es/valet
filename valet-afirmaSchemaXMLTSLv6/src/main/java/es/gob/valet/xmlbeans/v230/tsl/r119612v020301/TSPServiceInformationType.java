/*
 * XML Type:  TSPServiceInformationType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSPServiceInformationType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301;


/**
 * An XML TSPServiceInformationType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public interface TSPServiceInformationType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TSPServiceInformationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("tspserviceinformationtype563ftype");
    
    /**
     * Gets the "ServiceTypeIdentifier" element
     */
    java.lang.String getServiceTypeIdentifier();
    
    /**
     * Gets (as xml) the "ServiceTypeIdentifier" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType xgetServiceTypeIdentifier();
    
    /**
     * Sets the "ServiceTypeIdentifier" element
     */
    void setServiceTypeIdentifier(java.lang.String serviceTypeIdentifier);
    
    /**
     * Sets (as xml) the "ServiceTypeIdentifier" element
     */
    void xsetServiceTypeIdentifier(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType serviceTypeIdentifier);
    
    /**
     * Gets the "ServiceName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getServiceName();
    
    /**
     * Sets the "ServiceName" element
     */
    void setServiceName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType serviceName);
    
    /**
     * Appends and returns a new empty "ServiceName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewServiceName();
    
    /**
     * Gets the "ServiceDigitalIdentity" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType getServiceDigitalIdentity();
    
    /**
     * Sets the "ServiceDigitalIdentity" element
     */
    void setServiceDigitalIdentity(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType serviceDigitalIdentity);
    
    /**
     * Appends and returns a new empty "ServiceDigitalIdentity" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.DigitalIdentityListType addNewServiceDigitalIdentity();
    
    /**
     * Gets the "ServiceStatus" element
     */
    java.lang.String getServiceStatus();
    
    /**
     * Gets (as xml) the "ServiceStatus" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType xgetServiceStatus();
    
    /**
     * Sets the "ServiceStatus" element
     */
    void setServiceStatus(java.lang.String serviceStatus);
    
    /**
     * Sets (as xml) the "ServiceStatus" element
     */
    void xsetServiceStatus(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyURIType serviceStatus);
    
    /**
     * Gets the "StatusStartingTime" element
     */
    java.util.Calendar getStatusStartingTime();
    
    /**
     * Gets (as xml) the "StatusStartingTime" element
     */
    org.apache.xmlbeans.XmlDateTime xgetStatusStartingTime();
    
    /**
     * Sets the "StatusStartingTime" element
     */
    void setStatusStartingTime(java.util.Calendar statusStartingTime);
    
    /**
     * Sets (as xml) the "StatusStartingTime" element
     */
    void xsetStatusStartingTime(org.apache.xmlbeans.XmlDateTime statusStartingTime);
    
    /**
     * Gets the "SchemeServiceDefinitionURI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getSchemeServiceDefinitionURI();
    
    /**
     * True if has "SchemeServiceDefinitionURI" element
     */
    boolean isSetSchemeServiceDefinitionURI();
    
    /**
     * Sets the "SchemeServiceDefinitionURI" element
     */
    void setSchemeServiceDefinitionURI(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType schemeServiceDefinitionURI);
    
    /**
     * Appends and returns a new empty "SchemeServiceDefinitionURI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewSchemeServiceDefinitionURI();
    
    /**
     * Unsets the "SchemeServiceDefinitionURI" element
     */
    void unsetSchemeServiceDefinitionURI();
    
    /**
     * Gets the "ServiceSupplyPoints" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType getServiceSupplyPoints();
    
    /**
     * True if has "ServiceSupplyPoints" element
     */
    boolean isSetServiceSupplyPoints();
    
    /**
     * Sets the "ServiceSupplyPoints" element
     */
    void setServiceSupplyPoints(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType serviceSupplyPoints);
    
    /**
     * Appends and returns a new empty "ServiceSupplyPoints" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ServiceSupplyPointsType addNewServiceSupplyPoints();
    
    /**
     * Unsets the "ServiceSupplyPoints" element
     */
    void unsetServiceSupplyPoints();
    
    /**
     * Gets the "TSPServiceDefinitionURI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getTSPServiceDefinitionURI();
    
    /**
     * True if has "TSPServiceDefinitionURI" element
     */
    boolean isSetTSPServiceDefinitionURI();
    
    /**
     * Sets the "TSPServiceDefinitionURI" element
     */
    void setTSPServiceDefinitionURI(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType tspServiceDefinitionURI);
    
    /**
     * Appends and returns a new empty "TSPServiceDefinitionURI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewTSPServiceDefinitionURI();
    
    /**
     * Unsets the "TSPServiceDefinitionURI" element
     */
    void unsetTSPServiceDefinitionURI();
    
    /**
     * Gets the "ServiceInformationExtensions" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType getServiceInformationExtensions();
    
    /**
     * True if has "ServiceInformationExtensions" element
     */
    boolean isSetServiceInformationExtensions();
    
    /**
     * Sets the "ServiceInformationExtensions" element
     */
    void setServiceInformationExtensions(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType serviceInformationExtensions);
    
    /**
     * Appends and returns a new empty "ServiceInformationExtensions" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType addNewServiceInformationExtensions();
    
    /**
     * Unsets the "ServiceInformationExtensions" element
     */
    void unsetServiceInformationExtensions();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType newInstance() {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPServiceInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
