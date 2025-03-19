/* Copyright (C) 2011 [Gobierno de Espana]
 * This file is part of "Cliente @Firma".
 * "Cliente @Firma" is free software; you can redistribute it and/or modify it under the terms of:
 *   - the GNU General Public License as published by the Free Software Foundation;
 *     either version 2 of the License, or (at your option) any later version.
 *   - or The European Software License; either version 1.1 or (at your option) any later version.
 * You may contact the copyright holder at: soporte.afirma@seap.minhap.es
 */

package es.gob.valet.sign.cades;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.CMSSignerDigestMismatchException;
import org.bouncycastle.cms.DefaultCMSSignatureAlgorithmNameGenerator;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.SignerInformationVerifier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentVerifierProvider;
import org.bouncycastle.operator.DefaultSignatureAlgorithmIdentifierFinder;
import org.bouncycastle.operator.DigestCalculatorProvider;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentVerifierProviderBuilder;
import org.bouncycastle.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import org.bouncycastle.util.Store;

/** Validador de firmas binarias.
 * @author Carlos Gamuci
 * @author Tom&aacute;s Garc&iacute;a-Mer&aacute;s. */
public final class SignatureValidator {

    /**
     * Valida una firma binaria (CMS/CAdES). Si se especifican los datos que se firmaron
     * se comprobar&aacute; que efectivamente fueron estos, mientras que si no se indican
     * se extraer&aacute;n de la propia firma. Si la firma no contiene los datos no se realizara
     * esta comprobaci&oacute;n.
     * Se validan los certificados en local revisando las fechas de validez de los certificados.
     * @param sign Firma binaria.
     * @param data Datos firmados o {@code null} si se desea comprobar contra los datos incrustados
     *             en la firma.
     * @param checkCertificates Indica si debe comprobarse o no el periodo de validez de los certificados.
     * @return Validez de la firma.
     * @throws SignatureException Cuando la firma no es correcta o no se ha podido validar.
     */
    public static X509Certificate[] validate(final byte[] sign,
    		                            final byte[] data) throws SignatureException {
    	if (sign == null) {
    		throw new IllegalArgumentException("La firma a validar no puede ser nula"); //$NON-NLS-1$
    	}

    	if (!CAdESValidator.isCAdESSignedData(sign)) {
    		throw new SignatureException("No es una firma CAdES"); //$NON-NLS-1$
    	}

    	X509Certificate[] certChain;
    	try {
    		certChain = verifySignatures(sign, data);
	    }
    	catch (final CMSSignerDigestMismatchException e) {
    		throw new SignatureException("No es una firma binaria", e); //$NON-NLS-1$
    	}
    	catch (final Exception e) {
    		throw new SignatureException("Error al validar la firma", e); //$NON-NLS-1$
        }

    	return certChain;
    }

    /** Verifica la valides de una firma. Si la firma es v&aacute;lida, no hace nada. Si no es
     * v&aacute;lida, lanza una excepci&oacute;n.
     * @param sign Firma que se desea validar.
     * @param data Datos para la comprobaci&oacute;n.
     * @throws CMSException Cuando la firma no tenga una estructura v&aacute;lida.
     * @throws IOException Cuando no se puede crear un certificado desde la firma para validarlo.
     * @throws OperatorCreationException Cuando no se puede crear el validado de contenido de firma. */
    private static X509Certificate[] verifySignatures(final byte[] sign,
    		                             final byte[] data) throws CMSException,
                                                                                 CertificateException,
                                                                                 IOException,
                                                                                 OperatorCreationException {
        final CMSSignedData s;
        if (data == null) {
        	s = new CMSSignedData(sign);
        }
        else {
        	s = new CMSSignedData(new CMSProcessableByteArray(data), sign);
        }
        final Store store = s.getCertificates();

        final CertificateFactory certFactory = CertificateFactory.getInstance("X.509"); //$NON-NLS-1$

        List<X509Certificate> certChain = null;

        for (final Object si : s.getSignerInfos().getSigners()) {

        	final SignerInformation signer = (SignerInformation) si;

			final Iterator<X509CertificateHolder> certIt = store.getMatches(new CertHolderBySignerIdSelector(signer.getSID())).iterator();

            final X509Certificate cert = (X509Certificate) certFactory.generateCertificate(
        		new ByteArrayInputStream(
    				certIt.next().getEncoded()
				)
    		);
            
            // Con la nueva versión de Bouncycastle, la llamada al método verify cambia.
            // Es necesario instanciar un objeto SignerInformationVerifier.
            JcaContentVerifierProviderBuilder jcaContentVerifierProviderBuilder = new JcaContentVerifierProviderBuilder();
            jcaContentVerifierProviderBuilder.setProvider(BouncyCastleProvider.PROVIDER_NAME);
         			
         	ContentVerifierProvider contentVerifierProvider = jcaContentVerifierProviderBuilder.build(cert);

         	JcaDigestCalculatorProviderBuilder digestCalculatorProviderBuilder = new JcaDigestCalculatorProviderBuilder();
         	digestCalculatorProviderBuilder.setProvider(BouncyCastleProvider.PROVIDER_NAME);
         	DigestCalculatorProvider digestCalculatorProvider = digestCalculatorProviderBuilder.build();

            if (!signer.verify(
            	// En la nueva versión de Bouncycastle, la signatura del constructor SignerInformationVerifier, es:
				// public SignerInformationVerifier(CMSSignatureAlgorithmNameGenerator sigNameGenerator, SignatureAlgorithmIdentifierFinder sigAlgorithmFinder, ContentVerifierProvider verifierProvider, DigestCalculatorProvider digestProvider)	
//            	new SignerInformationVerifier(
//            			new JcaContentVerifierProviderBuilder().setProvider(new BouncyCastleProvider()).build(cert.getPublicKey()),
//            			new BcDigestCalculatorProvider()
//            		)
            		new SignerInformationVerifier(
			            	new	DefaultCMSSignatureAlgorithmNameGenerator(),
			            	new DefaultSignatureAlgorithmIdentifierFinder(),
			            	contentVerifierProvider,
			            	digestCalculatorProvider)
            	)) {
            	throw new CMSException("Firma no valida"); //$NON-NLS-1$
            }

            // Si es el primer firmante que encontramos, recogemos su cadena de certificacion
            if (certChain == null) {
				certChain = new ArrayList<>();
				certChain.add(cert);
				while (certIt.hasNext()) {
					final X509Certificate caCert = (X509Certificate) certFactory.generateCertificate(
			        		new ByteArrayInputStream(certIt.next().getEncoded()));
					certChain.add(caCert);
				}
			}
        }

        return certChain != null ? certChain.toArray(new X509Certificate[0]) : null;
    }

}
