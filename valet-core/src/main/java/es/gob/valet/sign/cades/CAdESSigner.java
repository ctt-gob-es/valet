/* Copyright (C) 2011 [Gobierno de Espana]
 * This file is part of "Cliente @Firma".
 * "Cliente @Firma" is free software; you can redistribute it and/or modify it under the terms of:
 *   - the GNU General Public License as published by the Free Software Foundation;
 *     either version 2 of the License, or (at your option) any later version.
 *   - or The European Software License; either version 1.1 or (at your option) any later version.
 * You may contact the copyright holder at: soporte.afirma@seap.minhap.es
 */

package es.gob.valet.sign.cades;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.util.Date;

import es.gob.afirma.core.AOException;

/** Manejador de firmas binarias CADES.
 * Soporta CAdES-BES, CAdES-EPES, CAdES-T y CAdES B-Level. Implementa los m&eacute;todos declarados
 * en el interfaz <code>AOSigner</code>.
 * <p>Un posible ejemplo de uso ser&iacute;a el siguiente:</p>
 * <pre>
 *
 *   // Establecemos los parametros adicionales
 *   final Properties extraParams = new Properties();
 *   extraParams.setProperty(CAdESExtraParams.MODE, AOSignConstants.SIGN_MODE_IMPLICIT);
 *   extraParams.setProperty(CAdESExtraParams.POLICY_IDENTIFIER, "urn:oid:2.16.724.1.3.1.1.2.1.8");
 *   extraParams.setProperty(CAdESExtraParams.POLICY_IDENTIFIER_HASH, "V8lVVNGDCPen6VELRD1Ja8HARFk=");
 *   extraParams.setProperty(CAdESExtraParams.POLIY_IDENTIFIER_HAS_HALGORITHM, "urn:oid:1.3.14.3.2.26");
 *
 *   // Usamos un PKCS#12 / PFX para obtener el certificado y su clave privada
 *   final InputStream fis = new FileInputStream("cert.pfx");
 *   KeyStore ks = KeyStore.getInstance("PKCS12");
 *   ks.load(fis, "contrasena".toCharArray());
 *   final PrivateKeyEntry pke = (PrivateKeyEntry) ks.getEntry(CERT_ALIAS, new KeyStore.PasswordProtection("contrasena".toCharArray()));
 *   final X509Certificate cert = (X509Certificate) ks.getCertificate("alias");
 *
 *   // Realizamos la firma CAdES
 *   final AOSigner signer = new AOCAdESSigner();
 *   final byte[] firma = signer.sign("Texto a firmar".getBytes(), "SHA1withRSA", pke, extraParams);
 *
 * </pre>
 * @version 0.4 */
public final class CAdESSigner {

    /** Firma datos en formato CAdES.
     * @param data Datos que deseamos firmar.
     * @param algorithm Algoritmo a usar para la firma.
     * @param key Clave privada a usar para firmar.
     * @param certChain Cadena de certificaci&oacute;n.
     * @return Firma en formato CAdES
     * @throws SignatureException Cuando ocurre cualquier problema durante el proceso */
	public static byte[] sign(final byte[] data,
                       final String algorithm,
                       final PrivateKey key,
                       final Certificate[] certChain) throws SignatureException {

    	if (certChain == null || certChain.length < 1) {

    	    throw new IllegalArgumentException("La cadena de certificados debe contener al menos un elemento"); //$NON-NLS-1$
    	}

    	// Determinamos la configuracion de la firma a partir de los datos introducidos,
    	// el algoritmo y los parametros adicionales
        final CAdESParameters cadesConfig = CAdESParameters.load(data, algorithm);

        final byte[] cadesSignedData;
        try {
			cadesSignedData = generateSignedData(
					algorithm,
					key,
					certChain,
					cadesConfig);
        }
        catch (final Exception e) {
            throw new SignatureException("Error al generar la firma CAdES: " + e, e); //$NON-NLS-1$
        }

        return cadesSignedData;
    }

	/** Genera una firma digital usando una estructura PKCS#7 SignedData.
     * @param signatureAlgorithm Algoritmo de firma que se deber&acute; usar.
     * @param key Referencia a la cl@ve privada de firme.
     * @param certChain Cadena de certificaci&oacute;n del certificado de firma.
     * @param config Configurac&oacute;n de la firma a generar.
     * @return La firma generada codificada en ASN.1 binario.
     * @throws AOException Cuando ocurre alg&uacute;n error durante el proceso de codificaci&oacute;n ASN.1 */
    private static byte[] generateSignedData(
    		final String signatureAlgorithm,
            final PrivateKey key,
            final Certificate[] certChain,
            final CAdESParameters config) throws SignatureException {

    	if (config == null) {
            throw new IllegalArgumentException("No se ha introducido configuracion para la construccion de la firma"); //$NON-NLS-1$
        }

        final Date signDate = new Date();

        final Certificate[] aplicableCertificateChain = config.isIncludedOnlySigningCertificate() ?
        		new Certificate[] { certChain[0] } : certChain;

        // Obtenemos la estructura con los atributos que hay que firmar (Prefirma)
        final byte[] preSignature = CAdESTriPhaseSigner.preSign(
        		aplicableCertificateChain,
                signDate,
                config
        );

        // Firmamos la prefirma (PKCS#1)
        final byte[] signatureValue = pkcs1Sign(
    		preSignature,
    		signatureAlgorithm,
    		key
		);

        // Componemos la firma completa (Postfirma)
        return CAdESTriPhaseSigner.postSign(
            signatureAlgorithm,
            config.getContentData(),
            aplicableCertificateChain,
            signatureValue,
            preSignature
        );

    }

    private static byte[] pkcs1Sign(final byte[] data, final String algorithm, final PrivateKey pk)
    		throws SignatureException {
    	final Signature sig;
		try {
			sig = Signature.getInstance(algorithm);
		}
		catch (final NoSuchAlgorithmException e) {
			throw new SignatureException("No se soporta el algoritmo de firma: " + algorithm, e); //$NON-NLS-1$
		}

		try {
			sig.initSign(pk);
		}
		catch (final Exception e) {
			throw new SignatureException("Error al inicializar la firma con la clave privada para el algoritmo: " + algorithm, e); //$NON-NLS-1$
		}

		try {
			sig.update(data);
		}
		catch (final Exception e) {
			throw new SignatureException("Error al configurar los datos a firmar", e); //$NON-NLS-1$
		}

		byte[] signature;
		try {
			signature = sig.sign();
		}
		catch (final Exception e) {
			throw new SignatureException("Error durante el proceso de firma PKCS#1", e); //$NON-NLS-1$
		}

		return signature;
	}
}
