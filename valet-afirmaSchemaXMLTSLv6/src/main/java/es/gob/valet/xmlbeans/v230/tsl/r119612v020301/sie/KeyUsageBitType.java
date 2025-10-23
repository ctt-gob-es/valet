/*
 * XML Type:  KeyUsageBitType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.KeyUsageBitType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie;


/**
 * An XML KeyUsageBitType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is an atomic type that is a restriction of es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.KeyUsageBitType.
 */
public interface KeyUsageBitType extends org.apache.xmlbeans.XmlBoolean
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(KeyUsageBitType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("keyusagebittypeea5etype");
    
    /**
     * Gets the "name" attribute
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name.Enum getName();
    
    /**
     * Gets (as xml) the "name" attribute
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name xgetName();
    
    /**
     * True if has "name" attribute
     */
    boolean isSetName();
    
    /**
     * Sets the "name" attribute
     */
    void setName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name.Enum name);
    
    /**
     * Sets (as xml) the "name" attribute
     */
    void xsetName(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name name);
    
    /**
     * Unsets the "name" attribute
     */
    void unsetName();
    
    /**
     * An XML name(@).
     *
     * This is an atomic type that is a restriction of es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.KeyUsageBitType$Name.
     */
    public interface Name extends org.apache.xmlbeans.XmlString
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Name.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("name5749attrtype");
        
        org.apache.xmlbeans.StringEnumAbstractBase enumValue();
        void set(org.apache.xmlbeans.StringEnumAbstractBase e);
        
        static final Enum DIGITAL_SIGNATURE = Enum.forString("digitalSignature");
        static final Enum NON_REPUDIATION = Enum.forString("nonRepudiation");
        static final Enum KEY_ENCIPHERMENT = Enum.forString("keyEncipherment");
        static final Enum DATA_ENCIPHERMENT = Enum.forString("dataEncipherment");
        static final Enum KEY_AGREEMENT = Enum.forString("keyAgreement");
        static final Enum KEY_CERT_SIGN = Enum.forString("keyCertSign");
        static final Enum CRL_SIGN = Enum.forString("crlSign");
        static final Enum ENCIPHER_ONLY = Enum.forString("encipherOnly");
        static final Enum DECIPHER_ONLY = Enum.forString("decipherOnly");
        
        static final int INT_DIGITAL_SIGNATURE = Enum.INT_DIGITAL_SIGNATURE;
        static final int INT_NON_REPUDIATION = Enum.INT_NON_REPUDIATION;
        static final int INT_KEY_ENCIPHERMENT = Enum.INT_KEY_ENCIPHERMENT;
        static final int INT_DATA_ENCIPHERMENT = Enum.INT_DATA_ENCIPHERMENT;
        static final int INT_KEY_AGREEMENT = Enum.INT_KEY_AGREEMENT;
        static final int INT_KEY_CERT_SIGN = Enum.INT_KEY_CERT_SIGN;
        static final int INT_CRL_SIGN = Enum.INT_CRL_SIGN;
        static final int INT_ENCIPHER_ONLY = Enum.INT_ENCIPHER_ONLY;
        static final int INT_DECIPHER_ONLY = Enum.INT_DECIPHER_ONLY;
        
        /**
         * Enumeration value class for es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.KeyUsageBitType$Name.
         * These enum values can be used as follows:
         * <pre>
         * enum.toString(); // returns the string value of the enum
         * enum.intValue(); // returns an int value, useful for switches
         * // e.g., case Enum.INT_DIGITAL_SIGNATURE
         * Enum.forString(s); // returns the enum value for a string
         * Enum.forInt(i); // returns the enum value for an int
         * </pre>
         * Enumeration objects are immutable singleton objects that
         * can be compared using == object equality. They have no
         * public constructor. See the constants defined within this
         * class for all the valid values.
         */
        static final class Enum extends org.apache.xmlbeans.StringEnumAbstractBase
        {
            /**
             * Returns the enum value for a string, or null if none.
             */
            public static Enum forString(java.lang.String s)
                { return (Enum)table.forString(s); }
            /**
             * Returns the enum value corresponding to an int, or null if none.
             */
            public static Enum forInt(int i)
                { return (Enum)table.forInt(i); }
            
            private Enum(java.lang.String s, int i)
                { super(s, i); }
            
            static final int INT_DIGITAL_SIGNATURE = 1;
            static final int INT_NON_REPUDIATION = 2;
            static final int INT_KEY_ENCIPHERMENT = 3;
            static final int INT_DATA_ENCIPHERMENT = 4;
            static final int INT_KEY_AGREEMENT = 5;
            static final int INT_KEY_CERT_SIGN = 6;
            static final int INT_CRL_SIGN = 7;
            static final int INT_ENCIPHER_ONLY = 8;
            static final int INT_DECIPHER_ONLY = 9;
            
            public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
                new org.apache.xmlbeans.StringEnumAbstractBase.Table
            (
                new Enum[]
                {
                    new Enum("digitalSignature", INT_DIGITAL_SIGNATURE),
                    new Enum("nonRepudiation", INT_NON_REPUDIATION),
                    new Enum("keyEncipherment", INT_KEY_ENCIPHERMENT),
                    new Enum("dataEncipherment", INT_DATA_ENCIPHERMENT),
                    new Enum("keyAgreement", INT_KEY_AGREEMENT),
                    new Enum("keyCertSign", INT_KEY_CERT_SIGN),
                    new Enum("crlSign", INT_CRL_SIGN),
                    new Enum("encipherOnly", INT_ENCIPHER_ONLY),
                    new Enum("decipherOnly", INT_DECIPHER_ONLY),
                }
            );
            private static final long serialVersionUID = 1L;
            private java.lang.Object readResolve() { return forInt(intValue()); } 
        }
        
        /**
         * A factory class with static methods for creating instances
         * of this type.
         */
        
        public static final class Factory
        {
            public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name newValue(java.lang.Object obj) {
              return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name) type.newValue( obj ); }
            
            public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name newInstance() {
              return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType.Name) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType newInstance() {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageBitType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
