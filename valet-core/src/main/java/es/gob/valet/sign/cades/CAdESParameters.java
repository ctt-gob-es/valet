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
 * <b>File:</b><p>es.gob.valet.sign.cades.CAdESParameters.java.</p>
 * <b>Description:</b><p> Detalles de configuraci&oacute; para la generacion de una firma CAdES.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>30/12/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 30/12/2022.
 */
package es.gob.valet.sign.cades;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/** 
 * <p>Detalles de configuraci&oacute; para la generacion de una firma CAdES.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 30/12/2022.
 */
public class CAdESParameters {

	private final boolean signingCertificateV2 = true;

	private final boolean includedOnlySigningCertificate = false;

	private final boolean contentNeeded = false;

	private final byte[] contentData = null;

	private final String contentTypeOid = "1.2.840.113549.1.7.1"; //$NON-NLS-1$

	private final String contentDescription = "binary"; //$NON-NLS-1$

	private final String mimetype = "application/octet-stream"; //$NON-NLS-1$

	private final boolean includedPolicyOnSigningCertificate = true;

	private final boolean includedIssuerSerial = true;

	private String digestAlgorithm;

	private byte[] dataDigest;

	private Date signingTime;

	/**
	 * Carga la configuraci&oacute;n de firma CAdES a partir del los par&aacute;metros establecidos.
	 * @param data Huella digital de los datos si se establece el
	 * "precalculatedMessageDigest". Si no, ser&aacute;n los propios datos o el "procesable array"
	 * de un PDF en caso de querer la firma para incluirla en una PAdES.
	 * @param algorithm Algoritmo de firma.
	 * @param config Par&aacute;metros extra de configuraci&oacute;n.
	 * @return Par&aacute;metros para la creaci&oacute;n de la firma.
	 * @throws SignatureException Cuando ocurre un error grave al procesasr los par&aacute;metros.
	 */
	public static CAdESParameters load(final byte[] data, final String signatureAlgorithm)
			throws SignatureException {

		final CAdESParameters dataConfig = new CAdESParameters();

		final String digestAlgorithm = SigUtils.getDigestAlgorithmName(signatureAlgorithm);
		dataConfig.setDigestAlgorithm(digestAlgorithm);

		// Huella de los datos
		final byte[] dataDigest;
		try {
			dataDigest = MessageDigest.getInstance(digestAlgorithm).digest(data);
		}
		catch (final NoSuchAlgorithmException e) {
			throw new SignatureException("Algoritmo no soportado: " + e, e); //$NON-NLS-1$
		}
		dataConfig.setDataDigest(dataDigest);

		// La marca de tiempo se incluira siempre salvo que desde el exterior se indique que no se haga
		dataConfig.setSigningTime(new Date());

		return dataConfig;
	}

	/**
	 * Indica si la firma se generar&aacute;con el atributo SigningCertificateV2. Este atributo
	 * debe establecerse cuando se generen firmas con algoritmos de firma SHA-2 o superiores.
	 * @return {@code true} si se debe generar una firma con el atributo SigningCertificateV2,
	 * {@code false} si debe usarse el atributo SigningCertificate.
	 */
	public boolean isSigningCertificateV2() {
		return this.signingCertificateV2;
	}

	/**
	 * Indica si debe incorporarse a la firma s&oacute;lo el certificado de firma y no toda la
	 * cadena de certificaci&oacute;n.
	 * @return {@code true} si se debe incorporarse s&oacute;lo el certificado de firma, {@code false}
	 * si se introduci&aacute; toda la cadena de certificaci&oacute;n.
	 */
	public boolean isIncludedOnlySigningCertificate() {
		return this.includedOnlySigningCertificate;
	}

	/**
	 * Establece el algorimo de huella digital a utilizar internamente para el calculo de la huella
	 * de los datos.
	 * @param digestAlgorithm Nombre del algoritmo de huella digital.
	 */
	private void setDigestAlgorithm(final String digestAlgorithm) {
		this.digestAlgorithm = digestAlgorithm;
	}

	/**
	 * Recupera el algorimo de huella digital a utilizar internamente para el calculo de la huella
	 * de los datos. Es obligatorio indicar el algoritmo de huella.
	 * @return Nombre del algoritmo de huella digital o {@code null} si no se ha establecido.
	 */
	public String getDigestAlgorithm() {
		return this.digestAlgorithm;
	}

	/**
	 * Recupera la huella digital de los datos a firmar. Esta huella debe generarse con el algoritmo
	 * que se obtiene en la llamada a {@code #getDigestAlgorithm()}. Si no se establece, ser&aacute;
	 * necesario proporcionar e incluir los datos en la firma.
	 * @return Huella digital de los datos o {@code null} si no se ha establecido.
	 * @see #getDigestAlgorithm()
	 */
	public byte[] getDataDigest() {
		return this.dataDigest != null ? this.dataDigest.clone() : null;
	}

	/**
	 * Establece la huella digital de los datos a firmar. Esta huella debe
	 * generarse con el algoritmo que se obtiene en la llamada a
	 * {@code #getDigestAlgorithm()}. Si se establece a {@code null} la huella
	 * se generar&aacute; en el momento de la firma en base a los datos.
	 * @param dataDigest Huella digital de los datos o {@code null} si no se ha
	 * establecido.
	 * @see #getDigestAlgorithm()
	 */
	private void setDataDigest(final byte[] dataDigest) {
		this.dataDigest = dataDigest != null ? dataDigest.clone() : null;
	}

	/**
	 * Indica si deben incorporarse los datos firmados a la firma. Por defecto, se incluir&aacute;n.
	 * @return {@code true} si se deben incorporarse los datos, {@code false}
	 * en caso contrario.
	 */
	public boolean isContentNeeded() {
		return this.contentNeeded;
	}

	/**
	 * Recupera los datos que se firman e incluir&aacute;n en la firma.
	 * @return Datos que se firmar&aacute;n o {@code null} si los datos no se deben introducir en la firma.
	 */
	public byte[] getContentData() {
		return this.contentData;
	}

	/**
	 * Recupera la hora que se debe introducir en la firma.
	 * @return Hora de firma o {@code null} si no se debe introducir la hora.
	 */
	public Date getSigningTime() {
		return this.signingTime;
	}

	/**
	 * Establece la hora que se debe introducir en la firma.
	 * @param signingTime Hora de firma o {@code null} si no se estableci&oacute;.
	 */
	private void setSigningTime(final Date signingTime) {
		this.signingTime = signingTime;
	}

	/**
	 * Recupera el OID correspondiente al tipo de contenido.
	 * @return OID del tipo de contenido o {@code null} si no se estableci&oacute;.
	 */
	public String getContentTypeOid() {
		return this.contentTypeOid;
	}

	/**
	 * Recupera el texto descriptivo del tipo de contenido.
	 * @return Descripci&oacute;n del tipo de contenido o {@code null} si no se estableci&oacute;.
	 */
	public String getContentDescription() {
		return this.contentDescription;
	}

	/**
	 * Recupera el texto descriptivo del tipo de contenido.
	 * @return Descripci&oacute;n del tipo de contenido o {@code null} si no se estableci&oacute;.
	 */
	public String getMimeType() {
		return this.mimetype;
	}

	/**
	 * Indica si se debe incluir la politica de certificaci&oacute;n del certificado en la firma.
	 * Por defecto, s&iacute; se incluir&aacute;.
	 * @return {@code true} si debe incluirse, {@code false} en caso contrario.
	 */
	public boolean isIncludedPolicyOnSigningCertificate() {
		return this.includedPolicyOnSigningCertificate;
	}

	/**
	 * Indica si se debe incluir el n&uacute;mero de serie del certificado de firma y el Principal de su
	 * emisor en el SigningCertificate de la firma. Por defecto, se incluir&aacute;.
	 * @return {@code true} si debe incluirse, {@code false} en caso contrario.
	 */
	public boolean isIncludedIssuerSerial() {
		return this.includedIssuerSerial;
	}
}
