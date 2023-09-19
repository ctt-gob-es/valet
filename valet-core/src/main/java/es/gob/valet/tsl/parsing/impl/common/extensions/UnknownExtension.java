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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.extensions.UnknownExtension.java.</p>
 * <b>Description:</b><p>Class that represents an Unknown TSL extension.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 19/09/2023.
 */
package es.gob.valet.tsl.parsing.impl.common.extensions;

import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreTslMessages;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance;
import es.gob.valet.utils.TSLBuilderConstants;

/**
 * <p>Class that represents an Unknown TSL extension.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 19/09/2023.
 */
public class UnknownExtension extends Extension {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -7431034904418695744L;

	/**
	 * Attribute that represents the extension name.
	 */
	private String extensionName = null;

	/**
	 * @param isCritical Flag to indicate if this extension is critical (<code>true</code>) or not (<code>false</code>).
	 * @param extensionType Extension type. Refer to its location inside the XML. It could be one of the following:
	 * <ul>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SCHEME}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_TSP_INFORMATION}</li>
	 * 	<li>Scheme Extension: {@link TSLBuilderConstants#TYPE_SERVICE_INFORMATION}</li>
	 * </ul>
	 * @param elementName Local name of the extension.
	 */
	public UnknownExtension(boolean isCritical, int extensionType, String elementName) {
		super(isCritical, extensionType, TSLBuilderConstants.IMPL_UNKNOWN_EXTENSION);
		extensionName = elementName;
	}

	/**
	 * Gets the value of the attribute {@link #extensionName}.
	 * @return the value of the attribute {@link #extensionName}.
	 */
	public final String getExtensionName() {
		return extensionName;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.impl.common.extensions.Extension#checkExtensionTypeSpec119612Vers020101()
	 */
	@Override
	protected final void checkExtensionTypeSpec119612Vers020101() throws TSLMalformedException {
		return;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.impl.common.extensions.Extension#checkExtensionValueSpec119612Vers020101(es.gob.valet.tsl.parsing.ifaces.ITSLObject, es.gob.valet.tsl.parsing.impl.common.ServiceHistoryInstance, boolean)
	 */
	@Override
	protected final void checkExtensionValueSpec119612Vers020101(ITSLObject tsl, ServiceHistoryInstance shi, boolean isCritical) throws TSLMalformedException {

		// Si la extensión es desconocida y crítica, debe producirse un error.
		if (isCritical) {
			throw new TSLMalformedException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL038, new Object[ ] { extensionName, extensionTypeToString(getExtensionType()) }));
		}

	}

}
