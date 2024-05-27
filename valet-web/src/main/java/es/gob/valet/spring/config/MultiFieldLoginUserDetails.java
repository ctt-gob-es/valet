package es.gob.valet.spring.config;

public class MultiFieldLoginUserDetails {
    
    private final String username;
    
    private final String password;
    
    private final String signatureBase64;
    
    public MultiFieldLoginUserDetails(String username, String password, String signatureBase64) {
        this.username = username;
        this.password = password;
    	this.signatureBase64 = signatureBase64;
    }

    public String getUsername() {
        return this.username;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public String getSignatureBase64() {
        return this.signatureBase64;
    }
}
