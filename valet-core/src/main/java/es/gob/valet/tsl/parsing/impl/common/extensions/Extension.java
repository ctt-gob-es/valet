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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.extensions.Extension.java.</p>
 * <b>Description:</b><p>Abstract class that represents a TSL Extension with could contains differents elements
 * regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.tsl.parsing.impl.common.extensions;

import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreTslMessages;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.parsing.ifaces.IAnyTypeExtension;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;
import es.gob.valet.utils.TSLBuilderConstants;
import es.gob.valet.utils.TSLSpecificationsVersions;

/**
 * <p>Abstract class that represents a TSL Extension with could contains differents elements
 * regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public abstract class Extension implements IAnyTypeExtension {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 3686158522847321635L;

	/**
	 * Attribute that represents if this extension is marked how critical (<code>true</code>) or not (<code>false</code>).
	 */
	private boolean critical = false;

	/**
	 * Attribute that represents the extension type, refers to its location inside the XML.
	 * It could be one of the following:<br>
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SCHEME}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_TSP_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SERVICE_INFORMATION}</li>
	 * </ul>
	 */
	private int type = -1;

	/**
	 * Attribute that represents the implementation of the extension.
	 * It must be one of the following:<br>
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#IMPL_ADDITIONAL_SERVICE_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#IMPL_EXPIRED_CERTS_REVOCATION_INFO}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#IMPL_QUALIFICATIONS}</li>
	 *  <li>Scheme Extension: {@link TSLBuilderConstants#IMPL_TAKENOVERBY}</li>
	 *  <li>Scheme Extension: {@link TSLBuilderConstants#IMPL_UNKNOWN_EXTENSION}</li>
	 * </ul>
	 */
	private int implementation = -1;

	/**
	 * Constructor method for the class Extension.java.
	 */
	private Extension() {
		super();
	}

	/**
	 * Constructor method for the class Extension.java.
	 * @param isCritical Flag to indicate if this extension is critical (<code>true</code>) or not (<code>false</code>).
	 * @param extensionType Extension type. Refer to its location inside the XML. It could be one of the following:<br>
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SCHEME}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_TSP_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SERVICE_INFORMATION}</li>
	 * </ul>
	 * @param implementationExtension Implementation Extension. It must be one of the following:<br>
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#IMPL_ADDITIONAL_SERVICE_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#IMPL_EXPIRED_CERTS_REVOCATION_INFO}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#IMPL_QUALIFICATIONS}</li>
	 *  <li>Scheme Extension: {@link TSLBuilderConstants#IMPL_TAKENOVERBY}</li>
	 *  <li>Scheme Extension: {@link TSLBuilderConstants#IMPL_UNKNOWN_EXTENSION}</li>
	 * </ul>
	 */
	protected Extension(boolean isCritical, int extensionType, int implementationExtension) {
		this();
		critical = isCritical;
		type = extensionType;
		implementation = implementationExtension;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.IAnyTypeExtension#isCritical()
	 */
	public final boolean isCritical() {
		return critical;
	}

	/**
	 * Gets the value of the attribute {@link #type}.
	 * @return the value of the attribute {@link #type}.
	 */
	public final int getExtensionType() {
		return type;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.afirma.mtsl.parsing.impl.common.extensions.IAnyTypeExtension#getImplementationExtension()
	 */
	@Override
	public final int getImplementationExtension() {
		return implementation;
	}

	/**
	 * Gets a string name representation of the specified extension type.
	 * @param extensionType Extension type. Refer to its location inside the XML. It could be one of the following:
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SCHEME}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_TSP_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SERVICE_INFORMATION}</li>
	 * </ul>
	 * @return string name representation of the specified extension type.
	 */
	public final String extensionTypeToString(int extensionType) {

		String result = null;
		switch (extensionType) {
			case TSLBuilderConstants.TYPE_SCHEME:
				result = Language.getResCoreTsl(CoreTslMessages.LOGMTSL027);
				break;

			case TSLBuilderConstants.TYPE_TSP_INFORMATION:
				result = Language.getResCoreTsl(CoreTslMessages.LOGMTSL028);
				break;

			case TSLBuilderConstants.TYPE_SERVICE_INFORMATION:
				result = Language.getResCoreTsl(CoreTslMessages.LOGMTSL029);
				break;

			default:
				result = Language.getResCoreTsl(CoreTslMessages.LOGMTSL030);
				break;
		}

		return result;

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.IAnyTypeExtension#checkExtensionValue(es.gob.valet.tsl.parsing.ifaces.ITSLObject, es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance)
	 */
	@Override
	public final void checkExtensionValue(ITSLObject tsl, ServiceHistoryInstance shi) throws TSLMalformedException {

		// En función de la especificación y versión de esta, se actúa de una
		// manera u otra.
		String tslSpecification = tsl.getSpecification();
		String tslSpecificationVersion = tsl.getSpecificationVersion();

		switch (tslSpecification) {
			case TSLSpecificationsVersions.SPECIFICATION_119612:

				switch (tslSpecificationVersion) {
					case TSLSpecificationsVersions.VERSION_020101:
						checkExtensionTypeSpec119612Vers020101();
						checkExtensionValueSpec119612Vers020101(tsl, shi, isCritical());
						break;

					default:
						break;
				}

				break;

			default:
				break;
		}

	}

	/**
	 * Checks if the extension type is the appropriate for this extension for the specification ETSI 119612
	 * and version 2.1.1.
	 * @throws TSLMalformedException Incase of the extension type is not the appropriate.
	 */
	protected abstract void checkExtensionTypeSpec119612Vers020101() throws TSLMalformedException;

	/**
	 * Checks if the extension has an appropiate value in the TSL for the specification ETSI 119612
	 * and version 2.1.1.
	 * @param tsl TSL Object representation that contains the service and the extension.
	 * @param shi Service Information (or service history information) in which is declared the extension.
	 * @param isCritical Indicates if the extension is critical (<code>true</code>) or not (<code>false</code>).
	 * @throws TSLMalformedException In case of the extension has not a correct value.
	 */
	protected abstract void checkExtensionValueSpec119612Vers020101(ITSLObject tsl, ServiceHistoryInstance shi, boolean isCritical) throws TSLMalformedException;

}
