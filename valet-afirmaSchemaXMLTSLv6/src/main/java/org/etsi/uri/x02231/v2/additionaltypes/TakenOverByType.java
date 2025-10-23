/*
 * XML Type:  TakenOverByType
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x02231.v2.additionaltypes;


/**
 * An XML TakenOverByType(@http://uri.etsi.org/02231/v2/additionaltypes#).
 *
 * This is a complex type.
 */
public interface TakenOverByType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TakenOverByType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("takenoverbytype9d46type");
    
    /**
     * Gets the "URI" element
     */
    org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType getURI();
    
    /**
     * Sets the "URI" element
     */
    void setURI(org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType uri);
    
    /**
     * Appends and returns a new empty "URI" element
     */
    org.etsi.uri.x02231.v2.NonEmptyMultiLangURIType addNewURI();
    
    /**
     * Gets the "TSPName" element
     */
    org.etsi.uri.x02231.v2.InternationalNamesType getTSPName();
    
    /**
     * Sets the "TSPName" element
     */
    void setTSPName(org.etsi.uri.x02231.v2.InternationalNamesType tspName);
    
    /**
     * Appends and returns a new empty "TSPName" element
     */
    org.etsi.uri.x02231.v2.InternationalNamesType addNewTSPName();
    
    /**
     * Gets the "SchemeOperatorName" element
     */
    org.etsi.uri.x02231.v2.InternationalNamesType getSchemeOperatorName();
    
    /**
     * Sets the "SchemeOperatorName" element
     */
    void setSchemeOperatorName(org.etsi.uri.x02231.v2.InternationalNamesType schemeOperatorName);
    
    /**
     * Appends and returns a new empty "SchemeOperatorName" element
     */
    org.etsi.uri.x02231.v2.InternationalNamesType addNewSchemeOperatorName();
    
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
    org.etsi.uri.x02231.v2.AnyType[] getOtherQualifierArray();
    
    /**
     * Gets ith "OtherQualifier" element
     */
    org.etsi.uri.x02231.v2.AnyType getOtherQualifierArray(int i);
    
    /**
     * Returns number of "OtherQualifier" element
     */
    int sizeOfOtherQualifierArray();
    
    /**
     * Sets array of all "OtherQualifier" element
     */
    void setOtherQualifierArray(org.etsi.uri.x02231.v2.AnyType[] otherQualifierArray);
    
    /**
     * Sets ith "OtherQualifier" element
     */
    void setOtherQualifierArray(int i, org.etsi.uri.x02231.v2.AnyType otherQualifier);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "OtherQualifier" element
     */
    org.etsi.uri.x02231.v2.AnyType insertNewOtherQualifier(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "OtherQualifier" element
     */
    org.etsi.uri.x02231.v2.AnyType addNewOtherQualifier();
    
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
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType newInstance() {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x02231.v2.additionaltypes.TakenOverByType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
