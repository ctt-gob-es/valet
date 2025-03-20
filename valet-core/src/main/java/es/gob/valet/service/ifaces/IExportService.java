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
 * <b>File:</b><p>es.gob.valet.service.ifaces.IExternalAccessService.java.</p>
 * <b>Description:</b><p>Interface that provides communication with the operations of the persistence layer
 * in relation of the ExportService.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 2.0, 19/03/2025.
 */
package es.gob.valet.service.ifaces;

import java.io.File;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Properties;

import es.gob.valet.exceptions.CipherException;
import es.gob.valet.exceptions.CommonUtilsException;
import es.gob.valet.exceptions.ExportException;
import es.gob.valet.exceptions.ValetException;
import es.gob.valet.sign.cades.SignatureException;

/**
 * <p>Interface that provides communication with the operations of the persistence layer
 * in relation of the ExportService.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 2.0, 19/12/2023.
 */
public interface IExportService {

	/**
	 * Exports TSL data to JSON files and calculates SHA-256 hashes for each file.
	 * The hashes are added to the provided properties object.
	 *
	 * @param filesHashProperties the properties object to store file hashes
	 * @param tslDataFolder the folder where TSL JSON files will be saved
	 * @throws IOException if there is an error writing the files
	 * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
	 */
	void exportTslData(Properties filesHashProperties, File externalAccessFolder) throws IOException, NoSuchAlgorithmException;

	/**
	 * Exports TSL country-region mappings to JSON files and calculates SHA-256 hashes for each file.
	 * The hashes are added to the provided properties object.
	 *
	 * @param filesHashProperties the properties object to store file hashes
	 * @param tslCountryRegionMappingFolder the folder where TSL country-region mapping JSON files will be saved
	 * @throws IOException if there is an error writing the files
	 * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
	 */
	void exportMappingToTsls(Properties filesHashProperties, File tslCountryRegionMappingFolder) throws IOException, NoSuchAlgorithmException;

	/**
	 * Exports certificate mappings to JSON files and calculates SHA-256 hashes for each file.
	 * The hashes are added to the provided properties object.
	 *
	 * @param filesHashProperties the properties object to store file hashes
	 * @param tslMappingFolder the folder where TSL certificate mapping JSON files will be saved
	 * @throws IOException if there is an error writing the files
	 * @throws NoSuchAlgorithmException if the SHA-256 algorithm is not available
	 * @throws CommonUtilsException if there is a general utility error
	 */
	void exportMappingToCert(Properties filesHashProperties, File tslMappingFolder) throws CommonUtilsException, IOException, NoSuchAlgorithmException;

	/**
	 * Adds a `files_hash.properties` file with the hash of the created files to the META-INF folder.
	 * The hash is stored in the provided properties object.
	 *
	 * @param filesHashProperties the properties object containing the file hashes
	 * @param metaInfFolder the folder where the `files_hash.properties` file will be saved
	 * @param version the version of the exported TSL
	 * @param tslsValetFolder the folder used for temporary file deletion in case of an error
	 * @return the byte array containing the hash data
	 * @throws ExportException if there is an error generating the file hash or saving the file
	 */
	byte[] addMetaInf(Properties filesHashProperties, File metaInfFolder, String version, File tslsValetFolder) throws ExportException;

	/**
	 * Signs the provided hash using the private key from the signing certificate.
	 * 
	 * @param hashByteArray the byte array representing the hash to be signed
	 * @return the signed hash byte array
	 * @throws ExportException if the signing certificate is not found or any other exception occurs during signing
	 * @throws KeyStoreException if an error occurs while accessing the keystore
	 * @throws NoSuchAlgorithmException if the cryptographic algorithm is not found
	 * @throws CertificateException if there is an issue with the certificate
	 * @throws IOException if an I/O error occurs
	 * @throws UnrecoverableKeyException if the private key cannot be retrieved
	 * @throws SignatureException if an error occurs during the signing process
	 * @throws CipherException if an error occurs with the cipher string
	 */
	byte[] signHash(byte[ ] hashByteArray) throws ExportException, KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, SignatureException, CipherException;

	/**
	 * Creates a ZIP file containing the exported TSL data along with the signature file.
	 * 
	 * @param metaInfFolder the folder where the META-INF directory is located
	 * @param signedHashFile the signed hash data to be stored as a signature
	 * @param tslsValetFolder the folder containing the TSL data to be added to the ZIP file
	 * @return a Base64 encoded string representing the ZIP file
	 * @throws ExportException if an error occurs during the creation of the ZIP file or writing the signature
	 */
	String getZIPFileWithTsls(File metaInfFolder, byte[ ] signedHashFile, File tslsValetFolder) throws ExportException;
	
}
