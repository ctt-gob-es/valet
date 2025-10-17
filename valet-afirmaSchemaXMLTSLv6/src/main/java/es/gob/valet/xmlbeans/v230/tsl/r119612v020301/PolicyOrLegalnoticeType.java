/*
 * XML Type:  PolicyOrLegalnoticeType
 * Namespace: http://uri.etsi.org/02231/v2#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.PolicyOrLegalnoticeType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301;


/**
 * An XML PolicyOrLegalnoticeType(@http://uri.etsi.org/02231/v2#).
 *
 * This is a complex type.
 */
public interface PolicyOrLegalnoticeType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(PolicyOrLegalnoticeType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("policyorlegalnoticetypeacf3type");
    
    /**
     * Gets array of all "TSLPolicy" elements
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType[] getTSLPolicyArray();
    
    /**
     * Gets ith "TSLPolicy" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType getTSLPolicyArray(int i);
    
    /**
     * Returns number of "TSLPolicy" element
     */
    int sizeOfTSLPolicyArray();
    
    /**
     * Sets array of all "TSLPolicy" element
     */
    void setTSLPolicyArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType[] tslPolicyArray);
    
    /**
     * Sets ith "TSLPolicy" element
     */
    void setTSLPolicyArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType tslPolicy);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "TSLPolicy" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType insertNewTSLPolicy(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "TSLPolicy" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.NonEmptyMultiLangURIType addNewTSLPolicy();
    
    /**
     * Removes the ith "TSLPolicy" element
     */
    void removeTSLPolicy(int i);
    
    /**
     * Gets array of all "TSLLegalNotice" elements
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType[] getTSLLegalNoticeArray();
    
    /**
     * Gets ith "TSLLegalNotice" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType getTSLLegalNoticeArray(int i);
    
    /**
     * Returns number of "TSLLegalNotice" element
     */
    int sizeOfTSLLegalNoticeArray();
    
    /**
     * Sets array of all "TSLLegalNotice" element
     */
    void setTSLLegalNoticeArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType[] tslLegalNoticeArray);
    
    /**
     * Sets ith "TSLLegalNotice" element
     */
    void setTSLLegalNoticeArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType tslLegalNotice);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "TSLLegalNotice" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType insertNewTSLLegalNotice(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "TSLLegalNotice" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.MultiLangStringType addNewTSLLegalNotice();
    
    /**
     * Removes the ith "TSLLegalNotice" element
     */
    void removeTSLLegalNotice(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType newInstance() {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.PolicyOrLegalnoticeType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
