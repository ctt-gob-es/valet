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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.ifaces.ITSLChecker.java.</p>
 * <b>Description:</b><p>Interface that represents a TSL data checker regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 28/03/2025.
 */
package es.gob.valet.tsl.parsing.ifaces;


import java.security.cert.X509Certificate;
import java.util.concurrent.atomic.AtomicReference;

import es.gob.valet.tsl.exceptions.TSLMalformedException;

/**
 * <p>Interface that represents a TSL data checker regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 28/03/2025.
 */
public interface ITSLChecker {


	/**
	 * Validates the current values of this TSL according to the specific 
	 * specification and version requirements.
	 *
	 * @param checkSignature {@code true} to verify the TSL signature, {@code false} otherwise.
	 * @param fullTSLxml Byte array representing the complete TSL XML used for signature verification.
	 * @param signTsl Atomic reference holding the X.509 certificate used for signing the TSL.
	 * @throws TSLMalformedException If required data is missing or contains invalid values.
	 */
	void checkTSLValues(boolean checkSignature, byte[ ] fullTSLxml, AtomicReference<X509Certificate> signTsl) throws TSLMalformedException;

}
