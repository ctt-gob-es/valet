/* 
/*******************************************************************************
 * Copyright (C) 2018 MINHAFP, Gobierno de España
 * This program is licensed and may be used, modified and redistributed under the  terms
 * of the European Public License (EUPL), either version 1.1 or (at your option)
 * any later version as soon as they are approved by the European Commission.
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and
 * more details.
 * You should have received a copy of the EUPL1.1 license
 * along with this program; if not, you may find it at
 * http:joinup.ec.europa.eu/software/page/eupl/licence-eupl
 ******************************************************************************/

/** 
 * <b>File:</b><p>es.gob.valet.afirma.utils.UtilsKeystore.java.</p>
 * <b>Description:</b><p> Class that manages operations related with the management of keystores.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>21/12/2022.</p>
 * @author Gobierno de España.
 * @version 1.2, 19/03/2025.
 */
package es.gob.valet.commons.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/** 
 * <p>Class that manages operations related with the management of keystores.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 19/03/2025.
 */
public class UtilsKeystore {
	
	/**
	 * Attribute that represents the JCEKS keystore type.
	 */
	public static final String JCEKS = "JCEKS";
	
	/**
	 * Attribute that represents the PKCS#12 keystore type.
	 */
	public static final String PKCS12 = "PKCS12";
	
	/**
	 * Loads a PKCS12 KeyStore from a byte array.
	 *
	 * @param certificate the byte array containing the PKCS12 certificate.
	 * @param password    the password to unlock the KeyStore.
	 * @return the loaded KeyStore instance.
	 * @throws KeyStoreException        if the KeyStore cannot be initialized.
	 * @throws NoSuchAlgorithmException if the algorithm for KeyStore integrity check is not available.
	 * @throws CertificateException     if there is an issue with the certificate format.
	 * @throws IOException              if an I/O error occurs while reading the KeyStore.
	 */
	public static KeyStore loadKsPKCS12(byte[] certificate, String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		KeyStore keyStore = KeyStore.getInstance(PKCS12);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(certificate)) {
            keyStore.load(bais, password.toCharArray());
        }
		return keyStore;
	}
	
	/**
	 * Loads a PKCS12 KeyStore from an InputStream.
	 *
	 * @param certificateStream the InputStream containing the PKCS12 certificate.
	 * @param password          the password to unlock the KeyStore.
	 * @return the loaded KeyStore instance.
	 * @throws KeyStoreException        if the KeyStore cannot be initialized.
	 * @throws NoSuchAlgorithmException if the algorithm for KeyStore integrity check is not available.
	 * @throws CertificateException     if there is an issue with the certificate format.
	 * @throws IOException              if an I/O error occurs while reading the KeyStore.
	 */
	public static KeyStore loadKsPKCS12(InputStream certificateStream, String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
	    KeyStore keyStore = KeyStore.getInstance(PKCS12);
	    try (InputStream input = certificateStream) {
	        keyStore.load(input, password.toCharArray());
	    }
	    return keyStore;
	}

	
	/**
	 * Retrieves a list of all {@link X509Certificate} instances from the given {@link KeyStore}.
	 *
	 * <p>This method iterates through all the aliases in the provided {@link KeyStore},
	 * extracts the associated {@link X509Certificate}, and adds it to a list.
	 *
	 * @param keyStore the {@link KeyStore} instance from which to extract certificates.
	 * @return a list of all {@link X509Certificate} instances found in the KeyStore.
	 * @throws KeyStoreException if an error occurs while accessing the KeyStore.
	 */
	public static List<X509Certificate> listAllX509Certificate(KeyStore keyStore) throws KeyStoreException {
		List<X509Certificate> listAllX509Certificate = new ArrayList<X509Certificate>();
		Enumeration<String> aliases = keyStore.aliases();
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();

            X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
            if (cert != null) {
            	listAllX509Certificate.add(cert);
            }
        }
		return listAllX509Certificate;
	}

	/**
	 * Converts a KeyStore into a byte array.
	 *
	 * @param keyStore the KeyStore instance to be converted.
	 * @param password the password used to encrypt the KeyStore.
	 * @return a byte array representing the KeyStore.
	 * @throws KeyStoreException        if the KeyStore has not been initialized.
	 * @throws NoSuchAlgorithmException if the encryption algorithm is not available.
	 * @throws CertificateException     if there is an issue with the certificates in the KeyStore.
	 * @throws IOException              if an I/O error occurs while writing the KeyStore.
	 */
	public static byte[] getKeyStoreBytes(KeyStore keyStore, char[] password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	    keyStore.store(byteArrayOutputStream, password);
	    return byteArrayOutputStream.toByteArray();
	}
	
	/**
	 * Retrieves the first alias from the specified KeyStore.
	 * 
	 * @param keystore the KeyStore to retrieve the alias from
	 * @return the first alias in the KeyStore, or null if no aliases are present
	 * @throws KeyStoreException if there is an error accessing the KeyStore
	 */
	public static String getFirstAlias(KeyStore keystore) throws KeyStoreException {
        return keystore.aliases().hasMoreElements() ? keystore.aliases().nextElement() : null;
    }
}
