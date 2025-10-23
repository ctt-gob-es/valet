/*
 * XML Type:  ExtendedKeyUsageType
 * Namespace: http://uri.etsi.org/02231/v2/additionaltypes#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.additionalTypes.ExtendedKeyUsageType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes;


/**
 * An XML ExtendedKeyUsageType(@http://uri.etsi.org/02231/v2/additionaltypes#).
 *
 * This is a complex type.
 */
public interface ExtendedKeyUsageType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ExtendedKeyUsageType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("extendedkeyusagetype6e21type");
    
    /**
     * Gets array of all "KeyPurposeId" elements
     */
    org.etsi.uri.x01903.v13.ObjectIdentifierType[] getKeyPurposeIdArray();
    
    /**
     * Gets ith "KeyPurposeId" element
     */
    org.etsi.uri.x01903.v13.ObjectIdentifierType getKeyPurposeIdArray(int i);
    
    /**
     * Returns number of "KeyPurposeId" element
     */
    int sizeOfKeyPurposeIdArray();
    
    /**
     * Sets array of all "KeyPurposeId" element
     */
    void setKeyPurposeIdArray(org.etsi.uri.x01903.v13.ObjectIdentifierType[] keyPurposeIdArray);
    
    /**
     * Sets ith "KeyPurposeId" element
     */
    void setKeyPurposeIdArray(int i, org.etsi.uri.x01903.v13.ObjectIdentifierType keyPurposeId);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "KeyPurposeId" element
     */
    org.etsi.uri.x01903.v13.ObjectIdentifierType insertNewKeyPurposeId(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "KeyPurposeId" element
     */
    org.etsi.uri.x01903.v13.ObjectIdentifierType addNewKeyPurposeId();
    
    /**
     * Removes the ith "KeyPurposeId" element
     */
    void removeKeyPurposeId(int i);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType newInstance() {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.additionalTypes.ExtendedKeyUsageType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
