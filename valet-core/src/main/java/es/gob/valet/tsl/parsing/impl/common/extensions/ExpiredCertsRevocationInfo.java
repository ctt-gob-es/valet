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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.extensions.ExpiredCertsRevocationInfo.java.</p>
 * <b>Description:</b><p>Class that represents a TSL extension that indicates the time from which
 * on the service issues CRL and/or OCSP responses that keep revocation notices for revoked certificates
 * also after they have expired.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.tsl.parsing.impl.common.extensions;

import java.util.Date;

import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreTslMessages;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;
import es.gob.valet.utils.TSLBuilderConstants;
import es.gob.valet.utils.TSLCommonURIs;
import es.gob.valet.utils.TSLElementsAndAttributes;

/**
 * <p>Class that represents a TSL extension that indicates the time from which
 * on the service issues CRL and/or OCSP responses that keep revocation notices for revoked certificates
 * also after they have expired.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class ExpiredCertsRevocationInfo extends Extension {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 2448698992852320188L;

	/**
	 * Attribute that represents the expired date.
	 */
	private Date expiredDate = null;

	/**
	 * Constructor method for the class ExpiredCertsRevocationInfo.java.
	 * @param isCritical Flag to indicate if this extension is critical (<code>true</code>) or not (<code>false</code>).
	 * @param extensionType Extension type. Refer to its location inside the XML. It could be one of the following:
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SCHEME}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_TSP_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SERVICE_INFORMATION}</li>
	 * </ul>
	 */
	private ExpiredCertsRevocationInfo(boolean isCritical, int extensionType) {
		super(isCritical, extensionType, TSLBuilderConstants.IMPL_EXPIRED_CERTS_REVOCATION_INFO);
	}

	/**
	 * Constructor method for the class ExpiredCertsRevocationInfo.java.
	 * @param expired Expired date to set.
	 * @param isCritical Flag to indicate if this extension is critical (<code>true</code>) or not (<code>false</code>).
	 * @param extensionType Extension type. Refer to its location inside the XML. It could be one of the following:
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SCHEME}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_TSP_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SERVICE_INFORMATION}</li>
	 * </ul>
	 */
	public ExpiredCertsRevocationInfo(Date expired, boolean isCritical, int extensionType) {
		this(isCritical, extensionType);
		expiredDate = expired;
	}

	/**
	 * Gets the value of the attribute {@link #expiredDate}.
	 * @return the value of the attribute {@link #expiredDate}.
	 */
	public final Date getExpiredDate() {
		return expiredDate;
	}

	/**
	 * Sets the value of the attribute {@link #expiredDate}.
	 * @param expiredDateParam The value for the attribute {@link #expiredDate}.
	 */
	public final void setExpiredDate(Date expiredDateParam) {
		this.expiredDate = expiredDateParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.impl.common.extensions.Extension#checkExtensionTypeSpec119612Vers020101()
	 */
	@Override
	protected final void checkExtensionTypeSpec119612Vers020101() throws TSLMalformedException {

		// Esta extensión tan solo puede ser del tipo
		// 'ServiceInformationExtension'.
		if (getExtensionType() != TSLBuilderConstants.TYPE_SERVICE_INFORMATION) {
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL032, new Object[ ] { extensionTypeToString(TSLBuilderConstants.TYPE_SERVICE_INFORMATION), extensionTypeToString(getExtensionType()) }));
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.impl.common.extensions.Extension#checkExtensionValueSpec119612Vers020101(es.gob.valet.tsl.parsing.ifaces.ITSLObject, es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance, boolean)
	 */
	@Override
	protected final void checkExtensionValueSpec119612Vers020101(ITSLObject tsl, ServiceHistoryInstance shi, boolean isCritical) throws TSLMalformedException {

		// Según la especificación, la extensión NO puede ser crítica.
		if (isCritical) {
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL022, new Object[ ] { TSLElementsAndAttributes.ELEMENT_EXTENSION_EXPIREDCERTSREVOCATIONINFO }));
		}

		// El tipo del servicio asociado debe ser CA(PKC), CA(QC),
		// NationalRootCA-QC, OCPS, OCSP(QC), CRL o CRL(QC).
		String serviceType = shi.getServiceTypeIdentifier().toString();

		boolean isServiceTypeOK = serviceType.equals(TSLCommonURIs.TSL_SERVICETYPE_CA_PKC) || serviceType.equals(TSLCommonURIs.TSL_SERVICETYPE_CA_QC) || serviceType.equals(TSLCommonURIs.TSL_SERVICETYPE_NATIONALROOTCA);
		isServiceTypeOK = isServiceTypeOK || serviceType.equals(TSLCommonURIs.TSL_SERVICETYPE_CERTSTATUS_OCSP) || serviceType.equals(TSLCommonURIs.TSL_SERVICETYPE_CERTSTATUS_OCSP_QC);
		isServiceTypeOK = isServiceTypeOK || serviceType.equals(TSLCommonURIs.TSL_SERVICETYPE_CERTSTATUS_CRL) || serviceType.equals(TSLCommonURIs.TSL_SERVICETYPE_CERTSTATUS_CRL_QC);
		if (!isServiceTypeOK) {
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL023, new Object[ ] { TSLElementsAndAttributes.ELEMENT_EXTENSION_EXPIREDCERTSREVOCATIONINFO, serviceType }));
		}

	}

}
