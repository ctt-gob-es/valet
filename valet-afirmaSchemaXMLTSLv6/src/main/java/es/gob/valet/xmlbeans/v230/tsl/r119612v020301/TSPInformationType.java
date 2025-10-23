/*
 * XML Type:  TSPInformationType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.TSPInformationType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301;


/**
 * An XML TSPInformationType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public interface TSPInformationType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TSPInformationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("tspinformationtype4e64type");
    
    /**
     * Gets the "TSPName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getTSPName();
    
    /**
     * Sets the "TSPName" element
     */
    void setTSPName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType tspName);
    
    /**
     * Appends and returns a new empty "TSPName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewTSPName();
    
    /**
     * Gets the "TSPTradeName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType getTSPTradeName();
    
    /**
     * True if has "TSPTradeName" element
     */
    boolean isSetTSPTradeName();
    
    /**
     * Sets the "TSPTradeName" element
     */
    void setTSPTradeName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType tspTradeName);
    
    /**
     * Appends and returns a new empty "TSPTradeName" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.InternationalNamesType addNewTSPTradeName();
    
    /**
     * Unsets the "TSPTradeName" element
     */
    void unsetTSPTradeName();
    
    /**
     * Gets the "TSPAddress" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType getTSPAddress();
    
    /**
     * Sets the "TSPAddress" element
     */
    void setTSPAddress(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType tspAddress);
    
    /**
     * Appends and returns a new empty "TSPAddress" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AddressType addNewTSPAddress();
    
    /**
     * Gets the "TSPInformationURI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType getTSPInformationURI();
    
    /**
     * Sets the "TSPInformationURI" element
     */
    void setTSPInformationURI(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType tspInformationURI);
    
    /**
     * Appends and returns a new empty "TSPInformationURI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIListType addNewTSPInformationURI();
    
    /**
     * Gets the "TSPInformationExtensions" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType getTSPInformationExtensions();
    
    /**
     * True if has "TSPInformationExtensions" element
     */
    boolean isSetTSPInformationExtensions();
    
    /**
     * Sets the "TSPInformationExtensions" element
     */
    void setTSPInformationExtensions(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType tspInformationExtensions);
    
    /**
     * Appends and returns a new empty "TSPInformationExtensions" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.ExtensionsListType addNewTSPInformationExtensions();
    
    /**
     * Unsets the "TSPInformationExtensions" element
     */
    void unsetTSPInformationExtensions();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType newInstance() {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.TSPInformationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
