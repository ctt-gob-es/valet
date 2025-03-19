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
 * <b>File:</b><p>es.gob.valet.sign.ExportFileSigner.java.</p>
 * <b>Description:</b><p> Class that handles the signature of a hash in CADES-BES format.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>30/12/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 30/12/2022.
 */
package es.gob.valet.sign;

import java.security.KeyStore.PrivateKeyEntry;

import es.gob.valet.sign.cades.CAdESSigner;
import es.gob.valet.sign.cades.SignatureException;

/** 
 * <p>Class that handles the signature of a hash in CADES-BES format .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 30/12/2022.
 */
public class ExportFileSigner {

	/**
	 * Genera la firma CAdES BES/B-Level explicita de unos datos.
	 * @param data Datos que firmar.
	 * @param pke Clave y certificado de firma.
	 * @return Firma generada.
	 * @throws SignatureException Cuando falla la generaci&oacute;n de la firma.
	 */
	public static byte[] sign(final byte[] data, final String signAlgorithm, final PrivateKeyEntry pke) throws SignatureException {

		final byte[] signature = CAdESSigner.sign(
				data,
				signAlgorithm,
				pke.getPrivateKey(),
				pke.getCertificateChain());

		return signature;
	}
}
