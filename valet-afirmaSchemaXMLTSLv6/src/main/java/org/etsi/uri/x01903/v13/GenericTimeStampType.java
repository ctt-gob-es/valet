/*
 * XML Type:  GenericTimeStampType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.GenericTimeStampType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13;


/**
 * An XML GenericTimeStampType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public interface GenericTimeStampType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(GenericTimeStampType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("generictimestamptypecdadtype");
    
    /**
     * Gets array of all "Include" elements
     */
    org.etsi.uri.x01903.v13.IncludeType[] getIncludeArray();
    
    /**
     * Gets ith "Include" element
     */
    org.etsi.uri.x01903.v13.IncludeType getIncludeArray(int i);
    
    /**
     * Returns number of "Include" element
     */
    int sizeOfIncludeArray();
    
    /**
     * Sets array of all "Include" element
     */
    void setIncludeArray(org.etsi.uri.x01903.v13.IncludeType[] includeArray);
    
    /**
     * Sets ith "Include" element
     */
    void setIncludeArray(int i, org.etsi.uri.x01903.v13.IncludeType include);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Include" element
     */
    org.etsi.uri.x01903.v13.IncludeType insertNewInclude(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Include" element
     */
    org.etsi.uri.x01903.v13.IncludeType addNewInclude();
    
    /**
     * Removes the ith "Include" element
     */
    void removeInclude(int i);
    
    /**
     * Gets array of all "ReferenceInfo" elements
     */
    org.etsi.uri.x01903.v13.ReferenceInfoType[] getReferenceInfoArray();
    
    /**
     * Gets ith "ReferenceInfo" element
     */
    org.etsi.uri.x01903.v13.ReferenceInfoType getReferenceInfoArray(int i);
    
    /**
     * Returns number of "ReferenceInfo" element
     */
    int sizeOfReferenceInfoArray();
    
    /**
     * Sets array of all "ReferenceInfo" element
     */
    void setReferenceInfoArray(org.etsi.uri.x01903.v13.ReferenceInfoType[] referenceInfoArray);
    
    /**
     * Sets ith "ReferenceInfo" element
     */
    void setReferenceInfoArray(int i, org.etsi.uri.x01903.v13.ReferenceInfoType referenceInfo);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ReferenceInfo" element
     */
    org.etsi.uri.x01903.v13.ReferenceInfoType insertNewReferenceInfo(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ReferenceInfo" element
     */
    org.etsi.uri.x01903.v13.ReferenceInfoType addNewReferenceInfo();
    
    /**
     * Removes the ith "ReferenceInfo" element
     */
    void removeReferenceInfo(int i);
    
    /**
     * Gets the "CanonicalizationMethod" element
     */
    org.w3.x2000.x09.xmldsig.CanonicalizationMethodType getCanonicalizationMethod();
    
    /**
     * True if has "CanonicalizationMethod" element
     */
    boolean isSetCanonicalizationMethod();
    
    /**
     * Sets the "CanonicalizationMethod" element
     */
    void setCanonicalizationMethod(org.w3.x2000.x09.xmldsig.CanonicalizationMethodType canonicalizationMethod);
    
    /**
     * Appends and returns a new empty "CanonicalizationMethod" element
     */
    org.w3.x2000.x09.xmldsig.CanonicalizationMethodType addNewCanonicalizationMethod();
    
    /**
     * Unsets the "CanonicalizationMethod" element
     */
    void unsetCanonicalizationMethod();
    
    /**
     * Gets array of all "EncapsulatedTimeStamp" elements
     */
    org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] getEncapsulatedTimeStampArray();
    
    /**
     * Gets ith "EncapsulatedTimeStamp" element
     */
    org.etsi.uri.x01903.v13.EncapsulatedPKIDataType getEncapsulatedTimeStampArray(int i);
    
    /**
     * Returns number of "EncapsulatedTimeStamp" element
     */
    int sizeOfEncapsulatedTimeStampArray();
    
    /**
     * Sets array of all "EncapsulatedTimeStamp" element
     */
    void setEncapsulatedTimeStampArray(org.etsi.uri.x01903.v13.EncapsulatedPKIDataType[] encapsulatedTimeStampArray);
    
    /**
     * Sets ith "EncapsulatedTimeStamp" element
     */
    void setEncapsulatedTimeStampArray(int i, org.etsi.uri.x01903.v13.EncapsulatedPKIDataType encapsulatedTimeStamp);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "EncapsulatedTimeStamp" element
     */
    org.etsi.uri.x01903.v13.EncapsulatedPKIDataType insertNewEncapsulatedTimeStamp(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "EncapsulatedTimeStamp" element
     */
    org.etsi.uri.x01903.v13.EncapsulatedPKIDataType addNewEncapsulatedTimeStamp();
    
    /**
     * Removes the ith "EncapsulatedTimeStamp" element
     */
    void removeEncapsulatedTimeStamp(int i);
    
    /**
     * Gets array of all "XMLTimeStamp" elements
     */
    org.etsi.uri.x01903.v13.AnyType[] getXMLTimeStampArray();
    
    /**
     * Gets ith "XMLTimeStamp" element
     */
    org.etsi.uri.x01903.v13.AnyType getXMLTimeStampArray(int i);
    
    /**
     * Returns number of "XMLTimeStamp" element
     */
    int sizeOfXMLTimeStampArray();
    
    /**
     * Sets array of all "XMLTimeStamp" element
     */
    void setXMLTimeStampArray(org.etsi.uri.x01903.v13.AnyType[] xmlTimeStampArray);
    
    /**
     * Sets ith "XMLTimeStamp" element
     */
    void setXMLTimeStampArray(int i, org.etsi.uri.x01903.v13.AnyType xmlTimeStamp);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "XMLTimeStamp" element
     */
    org.etsi.uri.x01903.v13.AnyType insertNewXMLTimeStamp(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "XMLTimeStamp" element
     */
    org.etsi.uri.x01903.v13.AnyType addNewXMLTimeStamp();
    
    /**
     * Removes the ith "XMLTimeStamp" element
     */
    void removeXMLTimeStamp(int i);
    
    /**
     * Gets the "Id" attribute
     */
    java.lang.String getId();
    
    /**
     * Gets (as xml) the "Id" attribute
     */
    org.apache.xmlbeans.XmlID xgetId();
    
    /**
     * True if has "Id" attribute
     */
    boolean isSetId();
    
    /**
     * Sets the "Id" attribute
     */
    void setId(java.lang.String id);
    
    /**
     * Sets (as xml) the "Id" attribute
     */
    void xsetId(org.apache.xmlbeans.XmlID id);
    
    /**
     * Unsets the "Id" attribute
     */
    void unsetId();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        /** @deprecated No need to be able to create instances of abstract types */
        public static org.etsi.uri.x01903.v13.GenericTimeStampType newInstance() {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        /** @deprecated No need to be able to create instances of abstract types */
        public static org.etsi.uri.x01903.v13.GenericTimeStampType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x01903.v13.GenericTimeStampType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x01903.v13.GenericTimeStampType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
