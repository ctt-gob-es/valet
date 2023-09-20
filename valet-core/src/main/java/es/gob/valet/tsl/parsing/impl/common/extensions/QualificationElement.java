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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.extensions.QualificationElement.java.</p>
 * <b>Description:</b><p>Class that represents a field bundles a list of assertions that specifies the attributes
 * a certificate must have (e.g. certain key-usage-bits set) and a list of qualifiers that
 * specify some certificate properties (e.g. it is a qualified certificate).</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.tsl.parsing.impl.common.extensions;

import java.io.Serializable;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreTslMessages;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;
import es.gob.valet.utils.TSLCommonURIs;
import es.gob.valet.utils.TSLElementsAndAttributes;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;

/**
 * <p>Class that represents a field bundles a list of assertions that specifies the attributes
 * a certificate must have (e.g. certain key-usage-bits set) and a list of qualifiers that
 * specify some certificate properties (e.g. it is a qualified certificate).</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class QualificationElement implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 5808451382023230437L;

	/**
	 * Attribute that represents a list of URI with the qualifiers.
	 */
	private transient List<URI> qualifiersList = null;

	/**
	 * Attribute that represents the Criteria List element.
	 */
	private CriteriaList criteriaList = null;

	/**
	 * Constructor method for the class QualificationElement.java.
	 */
	public QualificationElement() {
		qualifiersList = new ArrayList<URI>();
	}

	/**
	 * Gets the value of the attribute {@link #qualifiersList}.
	 * @return the value of the attribute {@link #qualifiersList}.
	 */
	public final List<URI> getQualifiersList() {
		return qualifiersList;
	}

	/**
	 * Adds new Qualifier URI.
	 * @param qualifierUri Qualifier URI to add.
	 */
	public final void addNewQualifier(URI qualifierUri) {
		if (qualifierUri != null) {
			qualifiersList.add(qualifierUri);
		}
	}

	/**
	 * Checks if there is at least one qualifier uri.
	 * @return <code>true</code> if there is at least one qualifier uri, otherwise <code>false</code>.
	 */
	public final boolean isThereSomeQualifierUri() {
		return !qualifiersList.isEmpty();
	}

	/**
	 * Gets the value of the attribute {@link #criteriaList}.
	 * @return the value of the attribute {@link #criteriaList}.
	 */
	public final CriteriaList getCriteriaList() {
		return criteriaList;
	}

	/**
	 * Adds a new criteria list with the assert type specified.
	 * @param assertType Assert type to assing to the Criteria List.
	 * @return Criteria List created.
	 */
	public final CriteriaList addNewCriteriaList(String assertType) {
		criteriaList = new CriteriaList(assertType);
		return criteriaList;
	}

	/**
	 * Checks if the qualification element has an appropiate value in the TSL for the specification ETSI 119612
	 * and version 2.1.1.
	 * @param tsl TSL Object representation that contains the service and the extension.
	 * @param shi Service Information (or service history information) in which is declared the extension.
	 * @param isCritical Indicates if the extension is critical (<code>true</code>) or not (<code>false</code>).
	 * @throws TSLMalformedException In case of the qualification element has not a correct value.
	 */
	protected final void checkExtensionValueSpec119612Vers020101(ITSLObject tsl, ServiceHistoryInstance shi, boolean isCritical) throws TSLMalformedException {

		// Comprobamos primero los Qualifiers.
		if (qualifiersList.isEmpty()) {
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL026, new Object[ ] { TSLElementsAndAttributes.ELEMENT_EXTENSION_QUALIFICATION_QUALIFIER }));
		} else {
			for (URI qualifierUri: qualifiersList) {
				String qualifierUriString = qualifierUri.toString();
				boolean isValid = qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCWITHSSCD) || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCNOSSCD);
				isValid = isValid || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCSTATUSASINCERT) || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCWITHQSCD);
				isValid = isValid || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCNOQSCD) || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCQSCDSTATUSASINCERT);
				isValid = isValid || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCQSCDMANAGEDONBEHALF) || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCFORLEGALPERSON);
				isValid = isValid || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCFORESIG) || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCFORESEAL);
				isValid = isValid || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCFORWSA) || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_NOTQUALIFIED);
				isValid = isValid || qualifierUriString.equals(TSLCommonURIs.TSL_SERVINFEXT_QUALEXT_QUALIFIER_QCSTATEMENT);
				if (!isValid) {
					throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL036, new Object[ ] { qualifierUriString }));
				}
			}
		}

		// Comprobamos el CriteriaList.
		if (criteriaList == null) {
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL024, new Object[ ] { TSLElementsAndAttributes.ELEMENT_EXTENSION_QUALIFICATIONS_QUALIFICATION, TSLElementsAndAttributes.ELEMENT_EXTENSION_QUALIFICATION_CRITERIALIST }));
		} else {
			criteriaList.checkExtensionValueSpec119612Vers020101(tsl, shi, isCritical);
		}

	}

}
