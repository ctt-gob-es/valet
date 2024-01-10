package es.gob.valet.service;

import org.apache.commons.codec.binary.Base64;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class ParseCERTtoBD64 {

    public static void main(String[] args) throws Exception {
        // Ruta del archivo del certificado
        String certFilePath = "C:\\Users\\Jairo.Figueroa\\Downloads\\Validar_Certificado_NL\\eutsa01_NL.cer";

     // Llamada a la función para obtener el certificado codificado en Base64
        String base64Cert = getBase64EncodedCert(certFilePath);

        // Imprime el resultado codificado en Base64
        System.out.println(base64Cert);
    }

    private static String getBase64EncodedCert(String certFilePath) throws IOException, CertificateException {
        // Lee el certificado desde el archivo
        FileInputStream fis = new FileInputStream(certFilePath);
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        X509Certificate cert = (X509Certificate) cf.generateCertificate(fis);
        fis.close();

        // Convierte el certificado a su representación en bytes y luego a Base64
        byte[] certBytes = cert.getEncoded();
        return Base64.encodeBase64String(certBytes);
    }
}