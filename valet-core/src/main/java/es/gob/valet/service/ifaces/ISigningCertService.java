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
 * <b>File:</b><p>es.gob.valet.service.ifaces.ISigningCertService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer in relation of the Signing Certificate entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 12/03/2025.
 */
package es.gob.valet.service.ifaces;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import org.springframework.web.multipart.MultipartFile;

import es.gob.valet.exceptions.CipherException;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.persistence.configuration.model.dto.SigningCertificateDTO;

/**
 * <p>Interface that provides communication with the operations of the persistence layer in relation of the Signing Certificate entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 12/03/2025.
 */
public interface ISigningCertService {
	/**
	 * Retrieves the signing certificate details stored in the database and converts them into a DTO.
	 *
	 * @return a {@link SigningCertificateDTO} containing the certificate details, or an empty DTO if no certificate is found.
	 * @throws CipherException if an error occurs during password decryption.
	 * @throws KeyStoreException if an error occurs while handling the keystore.
	 * @throws NoSuchAlgorithmException if the encryption algorithm is not available.
	 * @throws CertificateException if an error occurs while processing the certificate.
	 * @throws IOException if an I/O error occurs.
	 * @throws CommonUtilsException if an error occurs in utility functions.
	 */
	SigningCertificateDTO obtainSigningCertificate() throws CipherException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, CommonUtilsException;

	/**
	 * Updates or creates the signing certificate by processing the provided keystore file.
	 *
	 * @param passwordKeystore the password used to access the keystore.
	 * @param keystoreFile the keystore file containing the certificate.
	 * @return a {@link SigningCertificateDTO} containing the updated certificate details.
	 * @throws CipherException if an error occurs during password encryption.
	 * @throws KeyStoreException if an error occurs while handling the keystore.
	 * @throws NoSuchAlgorithmException if the encryption algorithm is not available.
	 * @throws CertificateException if an error occurs while processing the certificate.
	 * @throws IOException if an I/O error occurs while reading the keystore file.
	 * @throws CommonUtilsException if an error occurs in utility functions.
	 */
	SigningCertificateDTO updateSigningCert(String passwordKeystore, MultipartFile keystoreFile) throws CipherException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, CommonUtilsException;

	/**
	 * Deletes the signing certificate with a predefined ID.
	 * This operation is performed within a transactional context.
	 */
	void deleteSigningCert();
}
