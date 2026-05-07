/*
 * XML Type:  PGPDataType
 * Namespace: http://www.w3.org/2000/09/xmldsig#
 * Java type: org.w3.x2000.x09.xmldsig.PGPDataType
 *
 * Automatically generated - do not modify.
 */
package org.w3.x2000.x09.xmldsig;


/**
 * An XML PGPDataType(@http://www.w3.org/2000/09/xmldsig#).
 *
 * This is a complex type.
 */
public interface PGPDataType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(PGPDataType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("pgpdatatype3234type");
    
    /**
     * Gets the "PGPKeyID" element
     */
    byte[] getPGPKeyID();
    
    /**
     * Gets (as xml) the "PGPKeyID" element
     */
    org.apache.xmlbeans.XmlBase64Binary xgetPGPKeyID();
    
    /**
     * True if has "PGPKeyID" element
     */
    boolean isSetPGPKeyID();
    
    /**
     * Sets the "PGPKeyID" element
     */
    void setPGPKeyID(byte[] pgpKeyID);
    
    /**
     * Sets (as xml) the "PGPKeyID" element
     */
    void xsetPGPKeyID(org.apache.xmlbeans.XmlBase64Binary pgpKeyID);
    
    /**
     * Unsets the "PGPKeyID" element
     */
    void unsetPGPKeyID();
    
    /**
     * Gets the "PGPKeyPacket" element
     */
    byte[] getPGPKeyPacket();
    
    /**
     * Gets (as xml) the "PGPKeyPacket" element
     */
    org.apache.xmlbeans.XmlBase64Binary xgetPGPKeyPacket();
    
    /**
     * True if has "PGPKeyPacket" element
     */
    boolean isSetPGPKeyPacket();
    
    /**
     * Sets the "PGPKeyPacket" element
     */
    void setPGPKeyPacket(byte[] pgpKeyPacket);
    
    /**
     * Sets (as xml) the "PGPKeyPacket" element
     */
    void xsetPGPKeyPacket(org.apache.xmlbeans.XmlBase64Binary pgpKeyPacket);
    
    /**
     * Unsets the "PGPKeyPacket" element
     */
    void unsetPGPKeyPacket();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.w3.x2000.x09.xmldsig.PGPDataType newInstance() {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.w3.x2000.x09.xmldsig.PGPDataType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.w3.x2000.x09.xmldsig.PGPDataType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
