/*
 * XML Type:  CriteriaListType
 * Namespace: http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#
 * Java type: es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.CriteriaListType
 *
 * Automatically generated - do not modify.
 */
package es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie;


/**
 * An XML CriteriaListType(@http://uri.etsi.org/TrstSvc/SvcInfoExt/eSigDir-1999-93-EC-TrustedList/#).
 *
 * This is a complex type.
 */
public interface CriteriaListType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CriteriaListType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("criterialisttype3d02type");
    
    /**
     * Gets array of all "KeyUsage" elements
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageType[] getKeyUsageArray();
    
    /**
     * Gets ith "KeyUsage" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageType getKeyUsageArray(int i);
    
    /**
     * Returns number of "KeyUsage" element
     */
    int sizeOfKeyUsageArray();
    
    /**
     * Sets array of all "KeyUsage" element
     */
    void setKeyUsageArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageType[] keyUsageArray);
    
    /**
     * Sets ith "KeyUsage" element
     */
    void setKeyUsageArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageType keyUsage);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "KeyUsage" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageType insertNewKeyUsage(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "KeyUsage" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.KeyUsageType addNewKeyUsage();
    
    /**
     * Removes the ith "KeyUsage" element
     */
    void removeKeyUsage(int i);
    
    /**
     * Gets array of all "PolicySet" elements
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.PoliciesListType[] getPolicySetArray();
    
    /**
     * Gets ith "PolicySet" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.PoliciesListType getPolicySetArray(int i);
    
    /**
     * Returns number of "PolicySet" element
     */
    int sizeOfPolicySetArray();
    
    /**
     * Sets array of all "PolicySet" element
     */
    void setPolicySetArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.PoliciesListType[] policySetArray);
    
    /**
     * Sets ith "PolicySet" element
     */
    void setPolicySetArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.PoliciesListType policySet);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicySet" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.PoliciesListType insertNewPolicySet(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicySet" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.PoliciesListType addNewPolicySet();
    
    /**
     * Removes the ith "PolicySet" element
     */
    void removePolicySet(int i);
    
    /**
     * Gets array of all "CriteriaList" elements
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType[] getCriteriaListArray();
    
    /**
     * Gets ith "CriteriaList" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType getCriteriaListArray(int i);
    
    /**
     * Returns number of "CriteriaList" element
     */
    int sizeOfCriteriaListArray();
    
    /**
     * Sets array of all "CriteriaList" element
     */
    void setCriteriaListArray(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType[] criteriaListArray);
    
    /**
     * Sets ith "CriteriaList" element
     */
    void setCriteriaListArray(int i, es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType criteriaList);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CriteriaList" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType insertNewCriteriaList(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CriteriaList" element
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType addNewCriteriaList();
    
    /**
     * Removes the ith "CriteriaList" element
     */
    void removeCriteriaList(int i);
    
    /**
     * Gets the "Description" element
     */
    java.lang.String getDescription();
    
    /**
     * Gets (as xml) the "Description" element
     */
    org.apache.xmlbeans.XmlString xgetDescription();
    
    /**
     * True if has "Description" element
     */
    boolean isSetDescription();
    
    /**
     * Sets the "Description" element
     */
    void setDescription(java.lang.String description);
    
    /**
     * Sets (as xml) the "Description" element
     */
    void xsetDescription(org.apache.xmlbeans.XmlString description);
    
    /**
     * Unsets the "Description" element
     */
    void unsetDescription();
    
    /**
     * Gets the "otherCriteriaList" element
     */
    org.etsi.uri.x01903.v13.AnyType getOtherCriteriaList();
    
    /**
     * True if has "otherCriteriaList" element
     */
    boolean isSetOtherCriteriaList();
    
    /**
     * Sets the "otherCriteriaList" element
     */
    void setOtherCriteriaList(org.etsi.uri.x01903.v13.AnyType otherCriteriaList);
    
    /**
     * Appends and returns a new empty "otherCriteriaList" element
     */
    org.etsi.uri.x01903.v13.AnyType addNewOtherCriteriaList();
    
    /**
     * Unsets the "otherCriteriaList" element
     */
    void unsetOtherCriteriaList();
    
    /**
     * Gets the "assert" attribute
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert.Enum getAssert();
    
    /**
     * Gets (as xml) the "assert" attribute
     */
    es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert xgetAssert();
    
    /**
     * True if has "assert" attribute
     */
    boolean isSetAssert();
    
    /**
     * Sets the "assert" attribute
     */
    void setAssert(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert.Enum xassert);
    
    /**
     * Sets (as xml) the "assert" attribute
     */
    void xsetAssert(es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert xassert);
    
    /**
     * Unsets the "assert" attribute
     */
    void unsetAssert();
    
    /**
     * An XML assert(@).
     *
     * This is an atomic type that is a restriction of es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.CriteriaListType$Assert.
     */
    public interface Assert extends org.apache.xmlbeans.XmlString
    {
        public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
            org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(Assert.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s75A53B0181764E0C60828C7A837F9550").resolveHandle("asserta6dcattrtype");
        
        org.apache.xmlbeans.StringEnumAbstractBase enumValue();
        void set(org.apache.xmlbeans.StringEnumAbstractBase e);
        
        static final Enum ALL = Enum.forString("all");
        static final Enum AT_LEAST_ONE = Enum.forString("atLeastOne");
        static final Enum NONE = Enum.forString("none");
        
        static final int INT_ALL = Enum.INT_ALL;
        static final int INT_AT_LEAST_ONE = Enum.INT_AT_LEAST_ONE;
        static final int INT_NONE = Enum.INT_NONE;
        
        /**
         * Enumeration value class for es.gob.afirma.xmlbeans.v230.tsl.r119612v020101.sie.CriteriaListType$Assert.
         * These enum values can be used as follows:
         * <pre>
         * enum.toString(); // returns the string value of the enum
         * enum.intValue(); // returns an int value, useful for switches
         * // e.g., case Enum.INT_ALL
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
            
            static final int INT_ALL = 1;
            static final int INT_AT_LEAST_ONE = 2;
            static final int INT_NONE = 3;
            
            public static final org.apache.xmlbeans.StringEnumAbstractBase.Table table =
                new org.apache.xmlbeans.StringEnumAbstractBase.Table
            (
                new Enum[]
                {
                    new Enum("all", INT_ALL),
                    new Enum("atLeastOne", INT_AT_LEAST_ONE),
                    new Enum("none", INT_NONE),
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
            public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert newValue(java.lang.Object obj) {
              return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert) type.newValue( obj ); }
            
            public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert newInstance() {
              return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
            
            public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert newInstance(org.apache.xmlbeans.XmlOptions options) {
              return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType.Assert) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
            
            private Factory() { } // No instance of this class allowed
        }
    }
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType newInstance() {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (es.gob.valet.xmlbeans.v230.tsl.r119612v020301.sie.CriteriaListType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
