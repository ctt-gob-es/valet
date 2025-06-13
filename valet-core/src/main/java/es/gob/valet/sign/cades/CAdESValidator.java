/* Copyright (C) 2011 [Gobierno de Espana]
 * This file is part of "Cliente @Firma".
 * "Cliente @Firma" is free software; you can redistribute it and/or modify it under the terms of:
 *   - the GNU General Public License as published by the Free Software Foundation;
 *     either version 2 of the License, or (at your option) any later version.
 *   - or The European Software License; either version 1.1 or (at your option) any later version.
 * You may contact the copyright holder at: soporte.afirma@seap.minhap.es
 */

package es.gob.valet.sign.cades;

import java.io.IOException;
import java.util.Enumeration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1Set;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.cms.SignerInfo;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

/** Agrupa distintos m&eacute;todos de verificaci&oacute;n estructural de datos CAdES. Es importante rese&ntilde;ar que las
 * validaciones son &uacute;nicamente a nivel de estructura, y no a nivel de validez de la propia firma electr&oacute;ca o
 * los firmantes. */
public final class CAdESValidator {

    private static final Logger LOGGER = LogManager.getLogger(CAdESValidator.class); //$NON-NLS-1$

    private CAdESValidator() {
        // No permitimos la instanciacion
    }

    private static Enumeration<?> getCAdESObjects(final byte[] data) throws IOException {
    	try (
			final ASN1InputStream is = new ASN1InputStream(data);
		) {
    		final ASN1Sequence dsq = (ASN1Sequence) is.readObject();
    		return dsq.getObjects();
    	}
    }

    /** Verifica si los datos proporcionados se corresponden con una estructura de tipo <i>SignedData</i>.
     * @param data Datos PKCS#7/CMS/CAdES.
     * @return <code>true</code> si los datos proporcionados se corresponden con una estructura de tipo <i>SignedData</i>,
     * <code>false</code> en caso contrario. */
    public static boolean isCAdESSignedData(final byte[] data) {
        try {
            // LEEMOS EL FICHERO QUE NOS INTRODUCEN
            final Enumeration<?> e = getCAdESObjects(data);

            // Elementos que contienen los elementos OID SignedData
            final ASN1ObjectIdentifier doi = (ASN1ObjectIdentifier) e.nextElement();
            if (!doi.equals(PKCSObjectIdentifiers.signedData)) {
            	LOGGER.debug(
    				"Los datos proporcionados no son de tipo SignedData de CAdES (no esta declarado el OID de SignedData)" //$NON-NLS-1$
				);
        		return false;
            }

            // Contenido de SignedData
            final ASN1TaggedObject doj = (ASN1TaggedObject) e.nextElement();
            // El método getObject ya no existe para ASN1TaggedObject.
            // Probamos con getBaseObject, ya que la clase que devuelve: ASN1Object,
            // es antecesor de ASN1Sequence.
            final ASN1Sequence datos = ASN1Sequence.getInstance(doj.getObject());
            final SignedData sd = SignedData.getInstance(datos);

            final ASN1Set signerInfosSd = sd.getSignerInfos();

            for (int i = 0; i < signerInfosSd.size(); i++) {
            	if (!verifySignerInfo(SignerInfo.getInstance(signerInfosSd.getObjectAt(i)))) {
            		LOGGER.debug(
            				"Los datos proporcionados no son de tipo SignedData de CAdES (al menos un SignerInfo no se ha declarado de tipo CAdES)" //$NON-NLS-1$
            				);
            		return false;
            	}
            }
        }
        catch (final Exception ex) {
        	LOGGER.debug("Los datos proporcionados no son de tipo SignedData de CAdES: " + ex); //$NON-NLS-1$
            return false;
        }

        return true;
    }

    /** Verifica que los <code>SignerInfos</code> tengan el par&aacute;metro
     * que identifica que los datos son de tipo CAdES.
     * @param si <code>SignerInfo</code> para la verificaci&oacute;n del par&aacute;metro
     *        adecuado.
     * @return si contiene el par&aacute;metro. */
    private static boolean verifySignerInfo(final SignerInfo si) {
        boolean isSignerValid = false;
        final ASN1Set attrib = si.getAuthenticatedAttributes();
        final Enumeration<?> e = attrib.getObjects();
        Attribute atribute;
        while (e.hasMoreElements()) {
        	final ASN1Sequence seq = (ASN1Sequence) e.nextElement();
            atribute = new Attribute(
        		(ASN1ObjectIdentifier)seq.getObjectAt(0),
        		(ASN1Set)seq.getObjectAt(1)
    		);

            // Si tiene la politica es CADES.
            if (atribute.getAttrType().equals(PKCSObjectIdentifiers.id_aa_signingCertificate) ||
                atribute.getAttrType().equals(PKCSObjectIdentifiers.id_aa_signingCertificateV2)) {
                	isSignerValid = true;
            }
        }
        return isSignerValid;
    }

}
