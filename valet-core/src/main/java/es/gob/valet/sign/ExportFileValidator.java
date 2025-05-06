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
 * <b>File:</b><p>es.gob.valet.sign.ExportFileValidator.java.</p>
 * <b>Description:</b><p> Class that validates the signature of a hash in CADES-BES format.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>30/12/2022.</p>
 * @author Gobierno de España.
 * @version 1.2, 06/05/2025.
 */
package es.gob.valet.sign;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Base64;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import es.gob.valet.sign.cades.SignatureException;
import es.gob.valet.sign.cades.SignatureValidator;

/** 
 * <p>Class that validates the signature of a hash in CADES-BES format.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 06/05/2025.
 */
public class ExportFileValidator {

	private static final String ZIP_ENTRY_METAINF_PREFIX = "META-INF/"; //$NON-NLS-1$
	private static final String ZIP_ENTRY_HASHES_FILE = ZIP_ENTRY_METAINF_PREFIX + "files_hash_v_"; //$NON-NLS-1$
	private static final String ZIP_ENTRY_METAINF_SUFIX = ".properties"; //$NON-NLS-1$
	private static final String ZIP_ENTRY_SIGNATURE_FILE = ZIP_ENTRY_METAINF_PREFIX + "signature.p7s"; //$NON-NLS-1$

	private static final String HASH_ENTRY_SUFFIX = "tsls_valet/"; //$NON-NLS-1$

	private static final String HASH_ALGORITHM = "SHA-256"; //$NON-NLS-1$

	private static MessageDigest md = null;

	public static String version = null; 
	
	/**
	 * Valida un fichero de exportaci&oacute;n firmado.
	 * @param exportFileData Contenido del fichero de exportaci&oacute;n.
	 * @return El certificado utilizado para firmar.
	 * @throws IOException Cuando ocurre un error durante la lectura de los datos.
	 * @throws SecurityException Cuando se detecta que el fichero de exportacion ha sido
	 * modificado o est&aacute; corrupto.
	 */
	public static X509Certificate validateExportFile(final byte[] exportFileData)
			throws IOException, SecurityException {

		// Obtenemos el fichero de hashes y el de firma
		final ExportationCryptoFiles cryptoInfo = extractSignatureFiles(exportFileData);

		// Validamos la firma
		X509Certificate certificate;
		try {
			certificate = verifySignature(cryptoInfo);
		} catch (final SignatureException e) {
			throw new SecurityException("La firma del archivo no es valida", e); //$NON-NLS-1$
		}

		// Comprobamos que el fichero de hash incluye los has
		verifyHashes(exportFileData, cryptoInfo.getFileHash());

		return certificate;
	}

	/**
	 * Obtiene los ficheros para la validaci&oacute;n del archivo de exportaci&oacute;n.
	 * @param exportFileData Archivo de exportaci&oacute;n.
	 * @return Ficheros para la validaci&oacute;n del archivo.
	 * @throws IOException Cuando ocurre un error durante la lectura de los datos.
	 * @throws SecurityException Cuando no se encuentran los ficheros necesarios para la
	 * validaci&oacute;n.
	 */
	private static ExportationCryptoFiles extractSignatureFiles(final byte[] exportFileData)
			throws IOException, SecurityException {

		byte[] fileHash = null;
		byte[] signature = null;

		try (ByteArrayInputStream bais = new ByteArrayInputStream(exportFileData);
				ZipInputStream zis = new ZipInputStream(bais)) {

			ZipEntry entry;
			while ((fileHash == null || signature == null) && (entry = zis.getNextEntry()) != null) {
				final String entryName = entry.getName();
				if (entryName.startsWith(ZIP_ENTRY_METAINF_PREFIX)) {
					if (entryName.startsWith(ZIP_ENTRY_HASHES_FILE) && entryName.endsWith(ZIP_ENTRY_METAINF_SUFIX)) {
						// Obtenemos la version
						version = entryName.replace(ZIP_ENTRY_HASHES_FILE, "").replace(ZIP_ENTRY_METAINF_SUFIX, "");
						fileHash = readInputStream(zis);
					}
					else if (entryName.equals(ZIP_ENTRY_SIGNATURE_FILE)) {
						signature = readInputStream(zis);
					}
				}
			}
		}

		if (fileHash == null) {
			throw new SecurityException("No se ha encontrado el fichero de hash"); //$NON-NLS-1$
		}
		if (signature == null) {
			throw new SecurityException("No se ha encontrado el fichero de firma"); //$NON-NLS-1$
		}

		return new ExportationCryptoFiles(fileHash, signature);
	}

	/**
	 * Lee el contenido de un flujo de entrada de datos.
	 * @param is Flujo de entrada de datos.
	 * @return Contenido del flujo.
	 * @throws IOException Cuando ocurre un error durante la lectura.
	 */
	private static byte[] readInputStream(final InputStream is) throws IOException {

		int n;
		final byte[] buffer = new byte[2048];
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		while ((n = is.read(buffer)) > 0) {
			baos.write(buffer, 0, n);
		}

		return baos.toByteArray();
	}


	/**
	 * Verifica la firma del fichero de exportaci&oacute;n.
	 * @param cryptoFiles Conjunto de ficheros para la validaci&oacute;n.
	 * @return El certificado que hizo la firma.
	 * @throws SignatureException Cuando la firma no es v&aacute;lida.
	 * @throws IOException Cuando ocurre un error de lectura de los datos.
	 */
	private static X509Certificate verifySignature(final ExportationCryptoFiles cryptoFiles) throws IOException, SignatureException {
		return SignatureValidator.validate(cryptoFiles.getSignature(), cryptoFiles.getFileHash());
	}

	/**
	 * Verifica que las entradas del ZIP de exportacion sean correctas.
	 * @param exportFileData ZIP de exportaci&oacute;n.
	 * @param fileHash Fichero de hashes del ZIP.
	 * @throws IOException Cuando no se puede leer o decodificar el contenido de los ficheros.
	 * @throws SecurityException Cuando falla la validaci&aocute;n de las entradas del ZIP.
	 */
	private static void verifyHashes(final byte[] exportFileData, final byte[] fileHash)
			throws IOException, SecurityException {

		// Cargamos la informacion del fichero de hashes
		final Properties fileHashEntries = new Properties();
		fileHashEntries.load(new ByteArrayInputStream(fileHash));
		
		int validateEntriesCount = 0;

		// Leemos y comprobamos las entradas del fichero de exportacion
		try (ByteArrayInputStream bais = new ByteArrayInputStream(exportFileData);
				ZipInputStream zis = new ZipInputStream(bais)) {

			ZipEntry entry;
			while ((entry = zis.getNextEntry()) != null) {
				if (!entry.isDirectory() && !entry.getName().startsWith(ZIP_ENTRY_METAINF_PREFIX)) {
					checkEntry(entry, zis, fileHashEntries);
					validateEntriesCount++;
				}
			}
		}

		// Comprobamos que se hayan comprobado todas las entradas que se declararon en el fichero de hashes
		if (validateEntriesCount != fileHashEntries.size()) {
			throw new SecurityException(String.format(
					"Se han eliminado elementos del archivo exportado: %1s entradas en el fichero de hashes y %2s ficheros en el ZIP", //$NON-NLS-1$
					fileHashEntries.size(), validateEntriesCount));
		}
	}

	/**
	 * Comprueba que una entrada del ZIP de exportaci&oacute;n sea correcta:
	 * <ol>
	 * <li>Aparece en el listado del fichero de hashes.</li>
	 * <li>Su hash coincide con el indicado en el listado.</li>
	 * </ol>
	 * @param entry Entrada Entrada del ZIP que se debe validar.
	 * @param zis Flujo para la lectura de la entrada del ZIP.
	 * @param fileHashEntries Mapa con las entradas del ZIP y sus hashes.
	 * @throws IOException Cuando no se puede leer o decodificar la informaci&oacute;n del ZIP.
	 * @throws SecurityException Cuando falla la vomprobaci&oacute;n de la entrada.
	 */
	private static void checkEntry(final ZipEntry entry, final ZipInputStream zis, final Properties fileHashEntries)
			throws IOException, SecurityException {
		final String entryName = entry.getName();
		final String hashB64 = fileHashEntries.getProperty(HASH_ENTRY_SUFFIX + entryName);
		if (hashB64 == null) {
			throw new SecurityException("Se ha encontrado en el fichero de importacion una entrada no firmada: " + entryName); //$NON-NLS-1$
		}
		
		final byte[] entryContent = readInputStream(zis);
		final byte[] realHash = calculateHash(entryContent);
		final byte[] expectedHash = Base64.getDecoder().decode(hashB64);
		if (!Arrays.equals(realHash, expectedHash)) {
			throw new SecurityException("Se han manipulado entradas del fichero. Cambio detectado en: " + entryName); //$NON-NLS-1$
		}
	}

	/**
	 * Calcula el hash de los datos.
	 * @param data Datos sobre los que calcular el hash.
	 * @return Hash de los datos.
	 * @throws SecurityException Cuando no se reconoce el algoritmo de hash preconfigurado.
	 */
	private static byte[] calculateHash(final byte[] data) throws SecurityException {
		if (md == null) {
			try {
				md = MessageDigest.getInstance(HASH_ALGORITHM);
			}
			catch (final NoSuchAlgorithmException e) {
				throw new SecurityException("No se reconoce el algoritmo de hash configurado para la validacion", e); //$NON-NLS-1$
			}
		}
		return md.digest(data);
	}



	/**
	 * Conjunto de ficheros que permiten la validaci&oacute;n del fichero de importaci&oacute;n.
	 */
	private static class ExportationCryptoFiles {
		private final byte[] fileHash;
		private final byte[] signature;

		public ExportationCryptoFiles(final byte[] fileHash, final byte[] signature) {
			this.fileHash = fileHash;
			this.signature = signature;
		}

		public byte[] getFileHash() {
			return this.fileHash;
		}

		public byte[] getSignature() {
			return this.signature;
		}
	}
}
