/*
 * XML Type:  CommitmentTypeIndicationType
 * Namespace: http://uri.etsi.org/01903/v1.3.2#
 * Java type: org.etsi.uri.x01903.v13.CommitmentTypeIndicationType
 *
 * Automatically generated - do not modify.
 */
package org.etsi.uri.x01903.v13;


/**
 * An XML CommitmentTypeIndicationType(@http://uri.etsi.org/01903/v1.3.2#).
 *
 * This is a complex type.
 */
public interface CommitmentTypeIndicationType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(CommitmentTypeIndicationType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sC5CF2A71F720CEDDD610D691A94D54CD").resolveHandle("commitmenttypeindicationtypef179type");
    
    /**
     * Gets the "CommitmentTypeId" element
     */
    org.etsi.uri.x01903.v13.ObjectIdentifierType getCommitmentTypeId();
    
    /**
     * Sets the "CommitmentTypeId" element
     */
    void setCommitmentTypeId(org.etsi.uri.x01903.v13.ObjectIdentifierType commitmentTypeId);
    
    /**
     * Appends and returns a new empty "CommitmentTypeId" element
     */
    org.etsi.uri.x01903.v13.ObjectIdentifierType addNewCommitmentTypeId();
    
    /**
     * Gets array of all "ObjectReference" elements
     */
    java.lang.String[] getObjectReferenceArray();
    
    /**
     * Gets ith "ObjectReference" element
     */
    java.lang.String getObjectReferenceArray(int i);
    
    /**
     * Gets (as xml) array of all "ObjectReference" elements
     */
    org.apache.xmlbeans.XmlAnyURI[] xgetObjectReferenceArray();
    
    /**
     * Gets (as xml) ith "ObjectReference" element
     */
    org.apache.xmlbeans.XmlAnyURI xgetObjectReferenceArray(int i);
    
    /**
     * Returns number of "ObjectReference" element
     */
    int sizeOfObjectReferenceArray();
    
    /**
     * Sets array of all "ObjectReference" element
     */
    void setObjectReferenceArray(java.lang.String[] objectReferenceArray);
    
    /**
     * Sets ith "ObjectReference" element
     */
    void setObjectReferenceArray(int i, java.lang.String objectReference);
    
    /**
     * Sets (as xml) array of all "ObjectReference" element
     */
    void xsetObjectReferenceArray(org.apache.xmlbeans.XmlAnyURI[] objectReferenceArray);
    
    /**
     * Sets (as xml) ith "ObjectReference" element
     */
    void xsetObjectReferenceArray(int i, org.apache.xmlbeans.XmlAnyURI objectReference);
    
    /**
     * Inserts the value as the ith "ObjectReference" element
     */
    void insertObjectReference(int i, java.lang.String objectReference);
    
    /**
     * Appends the value as the last "ObjectReference" element
     */
    void addObjectReference(java.lang.String objectReference);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ObjectReference" element
     */
    org.apache.xmlbeans.XmlAnyURI insertNewObjectReference(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ObjectReference" element
     */
    org.apache.xmlbeans.XmlAnyURI addNewObjectReference();
    
    /**
     * Removes the ith "ObjectReference" element
     */
    void removeObjectReference(int i);
    
    /**
     * Gets the "AllSignedDataObjects" element
     */
    org.apache.xmlbeans.XmlObject getAllSignedDataObjects();
    
    /**
     * True if has "AllSignedDataObjects" element
     */
    boolean isSetAllSignedDataObjects();
    
    /**
     * Sets the "AllSignedDataObjects" element
     */
    void setAllSignedDataObjects(org.apache.xmlbeans.XmlObject allSignedDataObjects);
    
    /**
     * Appends and returns a new empty "AllSignedDataObjects" element
     */
    org.apache.xmlbeans.XmlObject addNewAllSignedDataObjects();
    
    /**
     * Unsets the "AllSignedDataObjects" element
     */
    void unsetAllSignedDataObjects();
    
    /**
     * Gets the "CommitmentTypeQualifiers" element
     */
    org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType getCommitmentTypeQualifiers();
    
    /**
     * True if has "CommitmentTypeQualifiers" element
     */
    boolean isSetCommitmentTypeQualifiers();
    
    /**
     * Sets the "CommitmentTypeQualifiers" element
     */
    void setCommitmentTypeQualifiers(org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType commitmentTypeQualifiers);
    
    /**
     * Appends and returns a new empty "CommitmentTypeQualifiers" element
     */
    org.etsi.uri.x01903.v13.CommitmentTypeQualifiersListType addNewCommitmentTypeQualifiers();
    
    /**
     * Unsets the "CommitmentTypeQualifiers" element
     */
    void unsetCommitmentTypeQualifiers();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType newInstance() {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.etsi.uri.x01903.v13.CommitmentTypeIndicationType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (org.etsi.uri.x01903.v13.CommitmentTypeIndicationType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link org.apache.xmlbeans.xml.stream.XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
