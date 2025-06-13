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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.DERUTCTime;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.cms.Attribute;
import org.bouncycastle.asn1.cms.CMSAttributes;
import org.bouncycastle.asn1.ess.ContentHints;
import org.bouncycastle.asn1.ess.ESSCertIDv2;
import org.bouncycastle.asn1.ess.SigningCertificateV2;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.GeneralNames;
import org.bouncycastle.asn1.x509.IssuerSerial;
import org.bouncycastle.asn1.x509.PolicyInformation;
import org.bouncycastle.asn1.x509.TBSCertificate;

/** Utilidades varias relacionadas con firmas electr&oacute;nicas CAdES.
 * Se declara como clase p&uacute;blica para permitir su uso en el m&oacute;dulo de multifirmas CAdES.
 * Las principales estructuras ASN.1 implementadas son:
 * <pre>
 * id-aa-signingCertificateV2 OBJECT IDENTIFIER ::= { iso(1)
 *      member-body(2) us(840) rsadsi(113549) pkcs(1) pkcs9(9)
 *      smime(16) id-aa(2) 47
 * }
 *
 * SigningCertificateV2 ::=  SEQUENCE {
 *      certs        SEQUENCE OF ESSCertIDv2,
 *      policies     SEQUENCE OF PolicyInformation OPTIONAL
 * }
 *
 * ESSCertIDv2 ::= SEQUENCE {
 *   hashAlgorithm AlgorithmIdentifier
 *   DEFAULT {
 *     algorithm id-sha256
 *   },
 *   certHash Hash,
 *   issuerSerial IssuerSerial OPTIONAL
 * }
 * Hash ::= OCTET STRING+
 *
 * IssuerSerial ::= SEQUENCE {
 *   issuer GeneralNames,
 *   serialNumber CertificateSerialNumber
 *   issuerUID UniqueIdentifier OPTIONAL
 * }
 *
 * PolicyInformation ::= SEQUENCE {
 *    policyIdentifier CertPolicyId,
 *    policyQualifiers SEQUENCE SIZE (1..MAX) OF PolicyQualifierInfo OPTIONAL
 * }
 *
 * CertPolicyId ::= OBJECT IDENTIFIER
 * PolicyQualifierInfo ::= SEQUENCE {
 *   policyQualifierId PolicyQualifierId,
 *   qualifier ANY DEFINED BY policyQualifierId
 * }
 *
 * SigningCertificateV2 ::= SEQUENCE {
 *   certs SEQUENCE OF ESSCertIDv2,
 *   policies SEQUENCE OF PolicyInformation OPTIONAL
 * }
 *
 * id-aa-signingCertificate OBJECT IDENTIFIER ::= { iso(1)
 *      member-body(2) us(840) rsadsi(113549) pkcs(1) pkcs9(9)
 *      smime(16) id-aa(2) 12
 * }
 *
 * SigningCertificate ::=  SEQUENCE {
 *      certs        SEQUENCE OF ESSCertID,
 *      policies     SEQUENCE OF PolicyInformation OPTIONAL
 * }
 *
 * IssuerSerial ::= SEQUENCE {
 *   issuer GeneralNames,
 *   serialNumber CertificateSerialNumber
 * }
 *
 * ESSCertID ::= SEQUENCE {
 *   certHash Hash,
 *   issuerSerial IssuerSerial OPTIONAL
 * }
 * Hash ::= OCTET STRING -- SHA1 hash of entire certificate
 *
 * PolicyInformation ::= SEQUENCE {
 *   policyIdentifier CertPolicyId,
 *   policyQualifiers SEQUENCE SIZE (1..MAX) OF PolicyQualifierInfo OPTIONAL
 * }
 *
 * CertPolicyId ::= OBJECT IDENTIFIER
 *
 * PolicyQualifierInfo ::= SEQUENCE {
 *   policyQualifierId PolicyQualifierId,
 *   qualifier ANY DEFINED BY policyQualifierId
 * }
 *
 * SigningCertificateV2 ::= SEQUENCE {
 *    certs SEQUENCE OF ESSCertIDv2,
 *    policies SEQUENCE OF PolicyInformation OPTIONAL
 * }
 *
 * id-aa-signingCertificate OBJECT IDENTIFIER ::= {
 *    iso(1) member-body(2) us(840) rsadsi(113549) pkcs(1) pkcs9(9) smime(16) id-aa(2) 12
 * }
 *
 * SigPolicyId ::= OBJECT IDENTIFIER (Politica de firma)
 *
 * OtherHashAlgAndValue ::= SEQUENCE {
 *     hashAlgorithm    AlgorithmIdentifier,
 *     hashValue        OCTET STRING
 * }
 *
 * AOSigPolicyQualifierInfo ::= SEQUENCE {
 *       SigPolicyQualifierId  SigPolicyQualifierId,
 *       SigQualifier          ANY DEFINED BY policyQualifierId
 * }
 *
 * SignaturePolicyId ::= SEQUENCE {
 *    sigPolicyId           SigPolicyId,
 *    sigPolicyHash         SigPolicyHash,
 *    sigPolicyQualifiers   SEQUENCE SIZE (1..MAX) OF AOSigPolicyQualifierInfo OPTIONAL
 * }
 *
 * </pre>
 *
 *  */
public final class CAdESUtils {

	private CAdESUtils() {
        // No permitimos la instanciacion
    }

    /** Genera una estructura <i>SigningCertificateV2</i> seg&uacute;n RFC 5035.
     * Es importante rese&ntilde;ar que la estructura <code>ESSCertIDv2</code> tiene la siguiente estructura:
     * <pre>
     *  ESSCertIDv2 ::=  SEQUENCE {
     *   hashAlgorithm AlgorithmIdentifier DEFAULT {algorithm id-sha256},
     *   certHash Hash,
     *   issuerSerial IssuerSerial OPTIONAL
     *  }
     * </pre>
     * En <code>DER</code> un campo marcado como <code>DEFAULT</code> no debe codificarse, debe dejarse ausente:
     * <a href="http://www.oss.com/asn1/resources/asn1-faq.html#default">http://www.oss.com/asn1/resources/asn1-faq.html#default</a>.<br>
     * Dado que el <code>SignedData</code> debe codificarse en <code>DER</code>, si el algoritmo de huella es SHA-256, el campo de OID de
     * algoritmo debe estar ausente.<br>
     * Citando la especificaci&oacute;n "ETSI TS 102 778-3"  (PDF Advanced Electronic Signature Profiles;Part 3: PAdES Enhanced - PAdES-BES
     * and PAdES-EPES Profiles) en su apartado "4.2 b"):<br>
     * <i>
     *  b) A <b>DER-encoded</b> SignedData object as specified in CMS (RFC 3852 [4]) shall be included as the PDF signature in the entry with the
     *  key Content of the signature dictionary as described in ISO 32000-1 [1], clause 12.8.1.
     * </i>
     * @param cert Certificado del firmante.
     * @param digestAlgorithmName Nombre del algoritmo de huella digital a usar.
     * @param includePolicyOnSigningCertificate Si se establece a <code>false</code>, omite la inclusi&oacute;n de la
     *                                               pol&iacute;tica de certificaci&oacute;n en el <i>SigningCertificate</i>,
     *                                               si se establece a <code>true</code> se incluye siempre que el certificado
     *                                               la declare.
     * @param includeIssuerSerial Si se establece a {@code false}, se omite la inclusi&oacute;n del n&uacute;mero
     *                                               de serie del issuer en el atributo <i>SigningCertificate</i>;
     *                                               si se establece a {@code true}, se incluye.
     * @return Estructura <i>SigningCertificateV2</i> seg&uacute;n RFC 5035.
     * @throws CertificateEncodingException Si el certificado proporcionado no es v&aacute;lido.
     * @throws NoSuchAlgorithmException Si no se soporta el algoritmo de huella indicado. */
    private static Attribute getSigningCertificateV2(final X509Certificate cert,
    		                                         final String digestAlgorithmName,
                                                     final boolean includePolicyOnSigningCertificate,
                                                     final boolean includeIssuerSerial) throws CertificateEncodingException,
    		                                                                                                      NoSuchAlgorithmException {
    	// ALGORITMO DE HUELLA DIGITAL
    	final String hashOid = AOAlgorithmID.getOID(digestAlgorithmName);
    	// Si es SHA-256 ponemos el OID a null para que no incluya el campo (y tome su
    	// valor por defecto).
        final AlgorithmIdentifier digestAlgorithmOID =
    		AOAlgorithmID.OID_SHA256.equals(hashOid) ?
				null :
					SigUtils.makeAlgId(hashOid);

        // INICIO SINGING CERTIFICATE-V2

        // La clase TBSCertificateStructure está deprecada, usar TBSCertificate
        //final TBSCertificateStructure tbs;
        final TBSCertificate tbs;
        try {
        	tbs = TBSCertificate.getInstance(ASN1Primitive.fromByteArray(cert.getTBSCertificate()));
        }
        catch (final IOException e) {
        	throw new CertificateEncodingException("Error al decodificar el certificado", e); //$NON-NLS-1$
		}
        final GeneralNames gns = new GeneralNames(
    		new GeneralName(X500Name.getInstance(cert.getIssuerX500Principal().getEncoded()))
		);

        IssuerSerial issuerSerial = null;
        if (includeIssuerSerial) {
        	issuerSerial = new IssuerSerial(gns, tbs.getSerialNumber());
        }

        final byte[] certHash = MessageDigest.getInstance(digestAlgorithmName).digest(cert.getEncoded());
        final ESSCertIDv2[] essCertIDv2 = {
            new ESSCertIDv2(digestAlgorithmOID, certHash, issuerSerial)
        };

        final SigningCertificateV2 scv2;
        final PolicyInformation[] polInfo = includePolicyOnSigningCertificate ? getPolicyInformation(cert) : null;

        if (polInfo != null) {
            scv2 = new SigningCertificateV2(essCertIDv2, polInfo); // con politica
        }
        else {
            scv2 = new SigningCertificateV2(essCertIDv2); // Sin politica
        }

        return new Attribute(
			PKCSObjectIdentifiers.id_aa_signingCertificateV2,
			new DERSet(scv2)
		);

    }

    /** Genera un vector con los atributos que deben firmarse dentro de la firma.
     * @param cert Certificado del firmante.
     * @param config Configraci&oacute;n con el detalle de firma.
     * @param isCountersign <code>true</code> si desea generarse el <code>SignerInfo</code> de una
     *                      contrafirma, <code>false</code> en caso contrario.
     * @return Los datos necesarios para generar la firma referente a los datos del usuario.
     * @throws java.security.NoSuchAlgorithmException Cuando se introduce un algoritmo no v&aacute;lido.
     * @throws java.io.IOException Cuando se produce un error de entrada/salida.
     * @throws CertificateEncodingException Error de codificaci&oacute;n en el certificado.
     * @throws IllegalArgumentException Cuando no se ha proporcionado ni los datos ni la huella digital a firmar. */
    public static ASN1EncodableVector generateSignedAttributes(
    		final Certificate cert,
    		final CAdESParameters config,
    		final boolean isCountersign)
    				throws NoSuchAlgorithmException,
    						IOException,
    						CertificateEncodingException {

        // Listado de atributos que se van a firmar
        final ASN1EncodableVector contextSpecific = new ASN1EncodableVector();

        // Algunos atributos basicos quedan listados en: http://tools.ietf.org/html/rfc3852#section-11

        // ContentType (https://tools.ietf.org/html/rfc3852#section-11.1) es obligatorio excepto en
        // contrafirmas (donde no debe aparecer nunca). Debe tener siempre el valor "id-data"
        if (!isCountersign) {
	        contextSpecific.add(
	    		new Attribute(
					CMSAttributes.contentType,
					new DERSet(PKCSObjectIdentifiers.data)
				)
			);
        }

        // MessageDigest (https://tools.ietf.org/html/rfc3852#section-11.2)
        if (config.getDataDigest() != null || config.getContentData() != null) {
        	contextSpecific.add(
       			new Attribute(
     				CMSAttributes.messageDigest,
    				new DERSet(
    					new DEROctetString(
    						config.getDataDigest() != null ?
    							config.getDataDigest() :
    							MessageDigest.getInstance(config.getDigestAlgorithm()).digest(config.getContentData())
    					)
    				)
    			)
    		);
        }
        else {
        	throw new IllegalArgumentException("Ni los datos a firmar ni la huella de los mismos se han configurado o encontrado "); //$NON-NLS-1$
        }

        // Informacion del certificado de firma  (en la forma original o la V2 segun sea necesario)
        contextSpecific.add(
        		getSigningCertificateV2(
        				(X509Certificate) cert,
        				config.getDigestAlgorithm(),
        				config.isIncludedPolicyOnSigningCertificate(),
        				config.isIncludedIssuerSerial()
        				)
        		);

        // ContentHints, que se crea en base al ContentType.
        // Tampoco se incluira si se ha definio externamente que no se haga (el contentTypeOid sera nulo)
        if (config.getContentTypeOid() != null) {
        	final ContentHints contentHints;
        	if (config.getContentDescription() != null) {
        		contentHints = new ContentHints(
        				new ASN1ObjectIdentifier(config.getContentTypeOid()),
        				new DERUTF8String(config.getContentDescription())
        				);
        	}
        	else {
        		contentHints = new ContentHints(
        				new ASN1ObjectIdentifier(config.getContentTypeOid())
        				);
        	}
        	contextSpecific.add(
        			new Attribute(
        					PKCSObjectIdentifiers.id_aa_contentHint,
        					new DERSet(contentHints.toASN1Primitive())
        					)
        			);
        }

        // La fecha de firma (https://tools.ietf.org/html/rfc3852#section-11.3), no se anade a
        // las firmas PAdES, pero es obligatoria en CAdES. La agregaremos cuando no estemos
        // generando una firma PAdES o cuando se fuerce a
        if (config.getSigningTime() != null) {
        	contextSpecific.add(
    			new Attribute(
    					CMSAttributes.signingTime,
					new DERSet(
						new DERUTCTime(config.getSigningTime())
					)
				)
			);
        }

        return contextSpecific;
    }

	/** Obtiene un <i>PolicyInformation</i> a partir de los datos de la pol&iacute;tica de un certificado.
     * Sirve para los datos de SigningCertificate y SigningCertificateV2. Tiene que llevar algunos
     * datos de la pol&iacute;tica.
     *
     * <pre>
     * PolicyInformation ::= SEQUENCE {
     *       policyIdentifier   CertPolicyId,
     *       policyQualifiers   SEQUENCE SIZE (1..MAX) OF PolicyQualifierInfo OPTIONAL
     * }
     *
     * CertPolicyId ::= OBJECT IDENTIFIER
     *
     * PolicyQualifierInfo ::= SEQUENCE {
     *      policyQualifierId  PolicyQualifierId,
     *      qualifier          ANY DEFINED BY policyQualifierId
     * }
     *
     * -- policyQualifierIds for Internet policy qualifiers
     *
     * id-qt          OBJECT IDENTIFIER ::=  { id-pkix 2 }
     * id-qt-cps      OBJECT IDENTIFIER ::=  { id-qt 1 }
     * id-qt-unotice  OBJECT IDENTIFIER ::=  { id-qt 2 }
     *
     * PolicyQualifierId ::= OBJECT IDENTIFIER ( id-qt-cps | id-qt-unotice )
     *
     * Qualifier ::= CHOICE {
     *      cPSuri           CPSuri,
     *      userNotice       UserNotice
     * }
     *
     * CPSuri ::= IA5String
     *
     * UserNotice ::= SEQUENCE {
     *      noticeRef        NoticeReference OPTIONAL,
     *      explicitText     DisplayText OPTIONAL
     * }
     *
     * NoticeReference ::= SEQUENCE {
     *      organization     DisplayText,
     *      noticeNumbers    SEQUENCE OF INTEGER
     * }
     *
     * DisplayText ::= CHOICE {
     *      ia5String        IA5String      (SIZE (1..200)),
     *      visibleString    VisibleString  (SIZE (1..200)),
     *      bmpString        BMPString      (SIZE (1..200)),
     *      utf8String       UTF8String     (SIZE (1..200))
     * }
     *
     * PolicyQualifierInfo ::= SEQUENCE {
     *          policyQualifierId  PolicyQualifierId,
     *          qualifier          ANY DEFINED BY policyQualifierId
     * }
     *
     * PolicyInformation ::= SEQUENCE {
     *     policyIdentifier   CertPolicyId,
     *     policyQualifiers   SEQUENCE SIZE (1..MAX) OF PolicyQualifierInfo OPTIONAL
     * }
     *
     * </pre>
     * @param cert Certificado del cual queremos describir su pol&iacute;tica.
     * @return Estructura con la pol&iacute;tica preparada para insertarla en la firma o
     *         <code>null</code> si el certificado no tiene declarada una pol&iacute;tica. */
    private static PolicyInformation[] getPolicyInformation(final X509Certificate cert) {

        if (cert == null) {
            throw new IllegalArgumentException("El certificado no puede ser nulo"); //$NON-NLS-1$
        }

        final byte[] certificatePoliciesBytes = cert.getExtensionValue("2.5.29.32"); //$NON-NLS-1$
		if (certificatePoliciesBytes == null || certificatePoliciesBytes.length < 1) {
			return null;
		}

		ASN1Object policies;
		try {
			policies = ASN1Primitive.fromByteArray(certificatePoliciesBytes);
		} catch (final IOException e) {
			throw new IllegalArgumentException("El formato de la politica del certificado no es correcto", e); //$NON-NLS-1$
		}

		final ASN1Sequence seq = ASN1Sequence.getInstance(
				ASN1OctetString.getInstance(policies).getOctets());

		final PolicyInformation[] policyInformation = new PolicyInformation[seq.size()];

        for (int i = 0; i != seq.size(); i++)
        {
            policyInformation[i] = PolicyInformation.getInstance(seq.getObjectAt(i));
        }

		return policyInformation;
    }
}
