/*
 * XML Type:  TakenOverByType
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.additionalTypes.TakenOverByType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes;


/**
 * An XML TakenOverByType(@http://uri.etsi.org/02231/v2/additionaltypes#).
 *
 * This is a complex type.
 */
public interface TakenOverByType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TakenOverByType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("takenoverbytype9d46type");
    
    /**
     * Gets the "URI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType getURI();
    
    /**
     * Sets the "URI" element
     */
    void setURI(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType uri);
    
    /**
     * Appends and returns a new empty "URI" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType addNewURI();
    
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
     * Gets the "SchemeTerritory" element
     */
    java.lang.String getSchemeTerritory();
    
    /**
     * Gets (as xml) the "SchemeTerritory" element
     */
    org.apache.xmlbeans.XmlString xgetSchemeTerritory();
    
    /**
     * Sets the "SchemeTerritory" element
     */
    void setSchemeTerritory(java.lang.String schemeTerritory);
    
    /**
     * Sets (as xml) the "SchemeTerritory" element
     */
    void xsetSchemeTerritory(org.apache.xmlbeans.XmlString schemeTerritory);
    
    /**
     * Gets array of all "OtherQualifier" elements
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AnyType[] getOtherQualifierArray();
    
    /**
     * Gets ith "OtherQualifier" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AnyType getOtherQualifierArray(int i);
    
    /**
     * Returns number of "OtherQualifier" element
     */
    int sizeOfOtherQualifierArray();
    
    /**
     * Sets array of all "OtherQualifier" element
     */
    void setOtherQualifierArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AnyType[] otherQualifierArray);
    
    /**
     * Sets ith "OtherQualifier" element
     */
    void setOtherQualifierArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AnyType otherQualifier);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "OtherQualifier" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AnyType insertNewOtherQualifier(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "OtherQualifier" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.AnyType addNewOtherQualifier();
    
    /**
     * Removes the ith "OtherQualifier" element
     */
    void removeOtherQualifier(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType newInstance() {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
