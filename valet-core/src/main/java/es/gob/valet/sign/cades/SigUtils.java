/* Copyright (C) 2011 [Gobierno de Espana]
 * This file is part of "Cliente @Firma".
 * "Cliente @Firma" is free software; you can redistribute it and/or modify it under the terms of:
 *   - the GNU General Public License as published by the Free Software Foundation;
 *     either version 2 of the License, or (at your option) any later version.
 *   - or The European Software License; either version 1.1 or (at your option) any later version.
 * You may contact the copyright holder at: soporte.afirma@seap.minhap.es
 */

package es.gob.valet.sign.cades;

import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.BERSet;
import org.bouncycastle.asn1.DERNull;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.AttributeTable;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;

/** Clase que contiene una serie de m&eacute;todos utilizados por GenSignedData,
 * GenCadesSignedData, CoSigner y CounterSigner. */
public final class SigUtils {

    private SigUtils() {
        // No permitimos la instanciacion
    }

    /** M&eacute;todo que devuelve el Identificador del algoritmo.
     * @param oid OID del algoritmo a idenfiticar
     * @return El identificador del algoritmo formateado y listo para introducir
     *         en el cms. */
    public static AlgorithmIdentifier makeAlgId(final String oid) {
        return new AlgorithmIdentifier(new ASN1ObjectIdentifier(oid), DERNull.INSTANCE);
    }

    /** Genera un estructura de tipo SET de formato ASN1.
     * @param derObjects Una lista con los objetos a obtener el tipo SET
     * @return Un SET de ASN1 con los elementos de la lista introducida. */
    public static ASN1Set createBerSetFromList(final List<ASN1Encodable> derObjects) {
        final ASN1EncodableVector v = new ASN1EncodableVector();
        for (final ASN1Encodable d : derObjects) {
            v.add(d);
        }
        return new BERSet(v);
    }

    /** Genera un atributo de un SET en formato DER
     * @param attr
     *        Atributo a formatear.
     * @return SET en formato DER del atributo. */
    public static ASN1Set getAttributeSet(final AttributeTable attr) {
        if (attr == null) {
            return null;
        }
        return new DERSet(attr.toASN1EncodableVector());
    }

    /** Obtiene el nombre de un algoritmo de huella digital a partir de una de
	 * las variantes de este.
	 * @param pseudoName Nombre o variante del nombre del algoritmo de huella digital.
	 * @return Nombre del algoritmo de huella digital. */
	public static String getDigestAlgorithmName(final String pseudoName) {
		if (pseudoName == null) {
			throw new IllegalArgumentException(
				"El nombre del algoritmo de huella digital no puede ser nulo" //$NON-NLS-1$
			);
		}
		final String upperPseudoName = pseudoName.toUpperCase(Locale.US);
		if (upperPseudoName.equals("SHA")  //$NON-NLS-1$
				|| upperPseudoName.equals("http://www.w3.org/2000/09/xmldsig#sha1".toUpperCase(Locale.US)) //$NON-NLS-1$
				|| upperPseudoName.equals("1.3.14.3.2.26") //$NON-NLS-1$
				|| upperPseudoName.startsWith("SHA1") //$NON-NLS-1$
				|| upperPseudoName.startsWith("SHA-1")) //$NON-NLS-1$
		{
			return "SHA1"; //$NON-NLS-1$
		}

		if (upperPseudoName.equals("http://www.w3.org/2001/04/xmlenc#sha256".toUpperCase(Locale.US))  //$NON-NLS-1$
				|| upperPseudoName.equals("2.16.840.1.101.3.4.2.1") //$NON-NLS-1$
				|| upperPseudoName.startsWith("SHA256") //$NON-NLS-1$
				|| upperPseudoName.startsWith("SHA-256")) { //$NON-NLS-1$
			return "SHA-256"; //$NON-NLS-1$
		}

		if (upperPseudoName.startsWith("SHA384") //$NON-NLS-1$
				|| upperPseudoName.equals("2.16.840.1.101.3.4.2.2") //$NON-NLS-1$
				|| upperPseudoName.startsWith("SHA-384")) { //$NON-NLS-1$
			return "SHA-384"; //$NON-NLS-1$
		}

		if (upperPseudoName.equals("http://www.w3.org/2001/04/xmlenc#sha512".toUpperCase(Locale.US))  //$NON-NLS-1$
				|| upperPseudoName.equals("2.16.840.1.101.3.4.2.3") //$NON-NLS-1$
				|| upperPseudoName.startsWith("SHA512") //$NON-NLS-1$
				|| upperPseudoName.startsWith("SHA-512")) { //$NON-NLS-1$
			return "SHA-512"; //$NON-NLS-1$
		}

		if (upperPseudoName.equals("http://www.w3.org/2001/04/xmlenc#ripemd160".toUpperCase(Locale.US))  //$NON-NLS-1$
				|| upperPseudoName.startsWith("RIPEMD160") //$NON-NLS-1$
				|| upperPseudoName.startsWith("RIPEMD-160")) { //$NON-NLS-1$
			return "RIPEMD160"; //$NON-NLS-1$
		}

		if (pseudoName.contains("with")) { //$NON-NLS-1$
			return pseudoName.substring(
				0,
				pseudoName.indexOf("with") //$NON-NLS-1$
			);
		}

		Logger.getLogger("es.gob.afirma").warning( //$NON-NLS-1$
			"Algoritmo de huella desconocido, no se normalizara su nombre: " + pseudoName //$NON-NLS-1$
		);

		return pseudoName;
	}
}
