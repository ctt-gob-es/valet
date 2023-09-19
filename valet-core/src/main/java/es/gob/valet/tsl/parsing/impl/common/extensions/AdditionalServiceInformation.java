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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.extensions.AdditionalServiceInformation.java.</p>
 * <b>Description:</b><p>Class that represents a TSL extension that provide additional information on a service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 19/09/2023.
 */
package es.gob.valet.tsl.parsing.impl.common.extensions;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.gob.valet.commons.utils.UtilsStringChar;
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
 * <p>Class that represents a TSL extension that provide additional information on a service.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 19/09/2023.
 */
public class AdditionalServiceInformation extends Extension {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -1855679124778509148L;

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(AdditionalServiceInformation.class);

	/**
	 * Attribute that represents an URI identifying the additional information.
	 */
	private URI uri = null;

	/**
	 * Attribute that represents a string containing the serviceInformation value.
	 */
	private String informationValue = null;

	/**
	 * Constructor method for the class AdditionalServiceInformation.java.
	 * @param isCritical Flag to indicate if this extension is critical (<code>true</code>) or not (<code>false</code>).
	 * @param extensionType Extension type. Refer to its location inside the XML. It could be one of the following:
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SCHEME}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_TSP_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SERVICE_INFORMATION}</li>
	 * </ul>
	 */
	private AdditionalServiceInformation(boolean isCritical, int extensionType) {
		super(isCritical, extensionType, TSLBuilderConstants.IMPL_ADDITIONAL_SERVICE_INFORMATION);
	}

	/**
	 * Constructor method for the class AdditionalServiceInformation.java.
	 * @param uriServInf URI identifying the additional information.
	 * @param isCritical Flag to indicate if this extension is critical (<code>true</code>) or not (<code>false</code>).
	 * @param extensionType Extension type. Refer to its location inside the XML. It could be one of the following:
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SCHEME}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_TSP_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SERVICE_INFORMATION}</li>
	 * </ul>
	 */
	public AdditionalServiceInformation(URI uriServInf, boolean isCritical, int extensionType) {
		this(isCritical, extensionType);
		setUri(uriServInf);
	}

	/**
	 * Gets the value of the attribute {@link #uri}.
	 * @return the value of the attribute {@link #uri}.
	 */
	public final URI getUri() {
		return uri;
	}

	/**
	 * Sets the value of the attribute {@link #uri}.
	 * @param uriServInf The value for the attribute {@link #uri}.
	 */
	public final void setUri(URI uriServInf) {
		if (uriServInf != null) {
			String auxUriString = uriServInf.toString();
			if (auxUriString != null && auxUriString.endsWith(UtilsStringChar.SYMBOL_SLASH_STRING)) {
				try {
					this.uri = new URI(auxUriString.substring(0, auxUriString.length() - 1));
				} catch (URISyntaxException e) {
					this.uri = null;
				}
			} else {
				this.uri = uriServInf;
			}
		}
	}

	/**
	 * Gets the value of the attribute {@link #informationValue}.
	 * @return the value of the attribute {@link #informationValue}.
	 */
	public final String getInformationValue() {
		return informationValue;
	}

	/**
	 * Sets the value of the attribute {@link #informationValue}.
	 * @param informValue The value for the attribute {@link #informationValue}.
	 */
	public final void setInformationValue(String informValue) {
		this.informationValue = informValue;
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
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL031, new Object[ ] { extensionTypeToString(TSLBuilderConstants.TYPE_SERVICE_INFORMATION), extensionTypeToString(getExtensionType()) }));
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.afirma.mtsl.parsing.impl.common.extensions.Extension#checkExtensionValueSpec119612Vers020101(es.gob.afirma.mtsl.parsing.interfaces.ITSLObject, es.gob.afirma.mtsl.parsing.impl.common.ServiceHistoryInstance, boolean)
	 */
	@Override
	protected final void checkExtensionValueSpec119612Vers020101(ITSLObject tsl, ServiceHistoryInstance shi, boolean isCritical) throws TSLMalformedException {

		// El atributo uri es obligatorio.
		if (uri == null) {
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL024, new Object[ ] { TSLElementsAndAttributes.ELEMENT_EXTENSION_ADDITIONALSERVICEINFORMATION, TSLElementsAndAttributes.ELEMENT_EXTENSION_ADDITIONALSERVICEINFORMATION_URI }));
		}

		// Pasamos a cadena la URI para poder compararla.
		String uriString = uri.toString();

		// La URI debe ser una de las permitidas.
		boolean isValid = uriString.equals(TSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_ROOTCAQC) || uriString.equals(TSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_FORESIGNATURES) || uriString.equals(TSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_FORESEALS);
		isValid = isValid || uriString.equals(TSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_FORWEBSITEAUTHENTICATION);

		if (!isValid) {
			if (isCritical) {
				throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL033, new Object[ ] { uriString }));
			} else {
				LOGGER.warn(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL232, new Object[ ] { shi.getServiceNameInLanguage(Locale.UK.getLanguage()), uriString }));
			}
		}

		// Pasamos a cadena el tipo del servicio.
		String serviceType = shi.getServiceTypeIdentifier().toString();

		// Comprobamos las restricciones respecto a la URI y el tipo de servicio
		// asociado.
		if (TSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_ROOTCAQC.equals(uriString) && !TSLCommonURIs.TSL_SERVICETYPE_CA_QC.equals(serviceType)) {
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL089, new Object[ ] { TSLCommonURIs.TSL_SERVINFEXT_ADDSERVINFEXT_ROOTCAQC, TSLCommonURIs.TSL_SERVICETYPE_CA_QC }));
		}

	}

}
