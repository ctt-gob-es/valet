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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.CertSubjectDNAttributeOtherCriteria.java.</p>
 * <b>Description:</b><p>Class that represents a implementation for a specific Other Criteria
 * Any Type: CertSubjectDNAttributeOtherCriteria element.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.tsl.parsing.impl.common;

import java.io.Serializable;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreTslMessages;
import es.gob.valet.tsl.exceptions.TSLCertificateValidationException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.utils.TSLElementsAndAttributes;

/**
 * <p>Class that represents a implementation for a specific Other Criteria
 * Any Type: CertSubjectDNAttributeOtherCriteria element.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class CertSubjectDNAttributeOtherCriteria extends OtherCriteria implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -8917682156395204095L;

	/**
	 * Attribute that represents the list of Cert Subject DN Attributes OID.
	 */
	private transient List<String> oidList = null;

	/**
	 * Constructor method for the class CertSubjectDNAttributeOtherCriteria.java.
	 */
	public CertSubjectDNAttributeOtherCriteria() {
		oidList = new ArrayList<String>();
	}

	/**
	 * Adds a new OID.
	 * @param oid OID string representation.
	 */
	public final void addNewOID(String oid) {
		if (oid != null) {
			oidList.add(oid);
		}
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.impl.common.OtherCriteria#getOtherCriteriaType()
	 */
	@Override
	protected final String getOtherCriteriaType() {
		return TSLElementsAndAttributes.ELEMENT_OTHER_CRITERIA_CERTSUBJECTDNATTRIBUTE_LOCALNAME;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.IAnyTypeOtherCriteria#isUnknownOtherCriteria()
	 */
	@Override
	public final boolean isUnknownOtherCriteria() {
		// Esta implementación hace referencia a un elemento conocido,
		// por lo que devolvemos false.
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.IAnyTypeOtherCriteria#checkCertificateWithThisCriteria(java.security.cert.X509Certificate)
	 */
	@Override
	public final boolean checkCertificateWithThisCriteria(X509Certificate x509cert) throws TSLCertificateValidationException {

		// Inicializamos el resultado por defecto a que el certificado cumple
		// las restricciones necesarias.
		boolean result = true;

		// Ahora comprobamos que el certificado contiene en su subject los
		// atributos especificados por los OID.
		JcaX509CertificateHolder jcaX509cert;
		try {
			jcaX509cert = new JcaX509CertificateHolder(x509cert);
		} catch (CertificateEncodingException e) {
			throw new TSLCertificateValidationException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL084), e);
		}

		X500Name x500name = jcaX509cert.getSubject();

		for (String oidString: oidList) {
			ASN1ObjectIdentifier oid = new ASN1ObjectIdentifier(oidString);
			RDN[ ] rdnArray = x500name.getRDNs(oid);
			if (rdnArray == null || rdnArray.length == 0) {
				return false;
			}
		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.impl.common.OtherCriteria#checkOtherCriteriaValueSpec119612Vers020101()
	 */
	@Override
	protected final void checkOtherCriteriaValueSpec119612Vers020101() throws TSLMalformedException {

		// Debe existir al menos un OID.
		if (oidList.isEmpty()) {
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL083));
		}

	}

}
