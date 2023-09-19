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
 * <b>File:</b><p>es.gob.valet.tsl.parsing.impl.common.TSLObject.java.</p>
 * <b>Description:</b><p>Class that represents a TSL object with the principal functions
 * (access information) regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>06/11/2018.</p>
 * @author Gobierno de España.
 * @version 1.6, 19/09/2023.
 */
package es.gob.valet.tsl.parsing.impl.common;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;import org.apache.logging.log4j.LogManager;
import org.w3.x2000.x09.xmldsig.SignatureType;

import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.exceptions.ValetExceptionConstants;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.CoreTslMessages;
import es.gob.valet.tsl.exceptions.TSLArgumentException;
import es.gob.valet.tsl.exceptions.TSLEncodingException;
import es.gob.valet.tsl.exceptions.TSLMalformedException;
import es.gob.valet.tsl.exceptions.TSLParsingException;
import es.gob.valet.tsl.parsing.ifaces.ITSLBuilder;
import es.gob.valet.tsl.parsing.ifaces.ITSLChecker;
import es.gob.valet.tsl.parsing.ifaces.ITSLObject;
import es.gob.valet.tsl.parsing.impl.TSLBuilderFactory;
import es.gob.valet.tsl.parsing.impl.TSLCheckerFactory;
import es.gob.valet.utils.TSLElementsAndAttributes;

/**
 * <p>Class that represents a TSL object with the principal functions
 * (access information) regardless it implementation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.6, 19/09/2023.
 */
public class TSLObject implements ITSLObject {

	/**
	 * Attribute that represents the object that manages the log of the class.
	 */
	private static final Logger LOGGER = LogManager.getLogger(TSLObject.class);
	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -1157512627219419369L;

	/**
	 * Attribute that represents the specification of this TSL object.
	 */
	private String tslSpecification = null;

	/**
	 * Attribute that represents the specification version of this TSL object.
	 */
	private String tslSpecificationVersion = null;

	/**
	 * Attribute that represents the tag attribute for the TrustServiceStatus element.
	 */
	private URI tslTag = null;

	/**
	 * Attribute that represents an optional identifier for the TSL.
	 */
	private String tslID = null;

	/**
	 * Attribute that represents the TSL Scheme Information with all its information.
	 */
	private SchemeInformation schemeInformation = null;

	/**
	 * Attribute that represents a list with all the Trust Services Providers associated to this TSL.
	 */
	private transient List<TrustServiceProvider> trustServiceProviderList = null;

	/**
	 * Attribute that represents the signature of the TSL.
	 */
	private transient SignatureType signature = null;

	/**
	 * Attribute that represents the full TSL.
	 */
	private transient byte[ ] fullTSLxml = null;

	/**
	 * Constructor method for the class TSLObject.java.
	 */
	private TSLObject() {
		super();
		schemeInformation = new SchemeInformation();
		trustServiceProviderList = new ArrayList<TrustServiceProvider>();
	}

	/**
	 * Constructor method for the class TSLObject.java.
	 * @param tslSpecificationParam TSL ETSI specification for this TSL Object.
	 * @param tslSpecificationVersionParam TSL ETSI specification version for this TSL Object.
	 * @throws TSLArgumentException In case of some of the input parameters are empty or null strings.
	 */
	public TSLObject(String tslSpecificationParam, String tslSpecificationVersionParam) throws TSLArgumentException {
		this();
		if (UtilsStringChar.isNullOrEmptyTrim(tslSpecificationParam) || UtilsStringChar.isNullOrEmptyTrim(tslSpecificationVersionParam)) {
			throw new TSLArgumentException(ValetExceptionConstants.COD_187, Language.getResCoreTsl(CoreTslMessages.LOGMTSL021));
		}
		tslSpecification = tslSpecificationParam;
		tslSpecificationVersion = tslSpecificationVersionParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#getSpecification()
	 */
	@Override
	public final String getSpecification() {
		return tslSpecification;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#getSpecificationVersion()
	 */
	@Override
	public final String getSpecificationVersion() {
		return tslSpecificationVersion;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#getTSLTag()
	 */
	@Override
	public final URI getTSLTag() {
		return tslTag;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#setTSLTag(java.net.URI)
	 */
	@Override
	public final void setTSLTag(URI tag) throws TSLArgumentException {

		// Si la entrada es nula, lanzamos excepción.
		if (tag == null) {
			throw new TSLArgumentException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL013, new Object[ ] { TSLElementsAndAttributes.ATTRIBUTE_TSL_TAG }));
		} else {
			tslTag = tag;
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#getID()
	 */
	@Override
	public final String getID() {
		return tslID;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#setID(java.lang.String)
	 */
	@Override
	public final void setID(String id) {
		tslID = id;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#getSchemeInformation()
	 */
	@Override
	public final SchemeInformation getSchemeInformation() {
		return schemeInformation;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#setSchemeInformation(es.gob.valet.tsl.parsing.impl.common.SchemeInformation)
	 */
	@Override
	public final void setSchemeInformation(SchemeInformation si) throws TSLArgumentException {

		// Si la entrada es nula, lanzamos excepción.
		if (si == null) {
			throw new TSLArgumentException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL014, new Object[ ] { TSLElementsAndAttributes.ELEMENT_SCHEME_INFORMATION }));
		} else {
			schemeInformation = si;
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#getTrustServiceProviderList()
	 */
	@Override
	public final List<TrustServiceProvider> getTrustServiceProviderList() {

		if (trustServiceProviderList.isEmpty()) {
			return null;
		} else {
			return trustServiceProviderList;
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#addNewTrustServiceProvider(es.gob.valet.tsl.parsing.impl.common.TrustServiceProvider)
	 */
	@Override
	public final void addNewTrustServiceProvider(TrustServiceProvider tsp) throws TSLArgumentException {

		// Si la entrada es nula, lanzamos excepción.
		if (tsp == null) {
			throw new TSLArgumentException(ValetExceptionConstants.COD_187, Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL015, new Object[ ] { TSLElementsAndAttributes.ELEMENT_TRUST_SERVICE_PROVIDER }));
		} else {
			trustServiceProviderList.add(tsp);
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#isThereSomeTrustServiceProvider()
	 */
	@Override
	public final boolean isThereSomeTrustServiceProvider() {
		return !trustServiceProviderList.isEmpty();
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#getSignature()
	 */
	@Override
	public final SignatureType getSignature() {
		return signature;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#setSignature(org.w3.x2000.x09.xmldsig.SignatureType)
	 */
	@Override
	public final void setSignature(SignatureType dsSignature) {
		signature = dsSignature;
	}

	/**
	 * Gets the TSL Builder associated to this specification and version of TSL.
	 * @return TSL Builder associated to this specification and version of TSL.
	 */
	private ITSLBuilder getTSLBuilder() {
		return TSLBuilderFactory.createTSLBuilder(this);
	}

	/**
	 * Gets the TSL Data Checker associated to this specification and version of TSL.
	 * @return TSL Data Checker associated to this specification and version of TSL.
	 */
	private ITSLChecker getTSLChecker() {
		return TSLCheckerFactory.createTSLChecker(this);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#checkTSLValues()
	 */
	@Override
	public final void checkTSLValues() throws TSLMalformedException{
		getTSLChecker().checkTSLValues(false, fullTSLxml);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#buildTSLFromXMLcheckValues(java.io.InputStream)
	 */
	@Override
	public final void buildTSLFromXMLcheckValues(InputStream isParam) throws TSLArgumentException, TSLParsingException, TSLMalformedException{
		buildTSLFromXMLcheckValues(isParam, true, false);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#buildTSLFromXMLcheckValues(java.io.InputStream)
	 */
	@Override
	public final void buildTSLFromXMLcheckValuesCache(InputStream isParam) throws TSLArgumentException, TSLParsingException, TSLMalformedException{
		buildTSLFromXMLcheckValues(isParam, true, true);
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#buildTSLFromXMLcheckValues(java.io.InputStream, boolean)
	 */
	@Override
	public final void buildTSLFromXMLcheckValues(InputStream is, boolean checkSignature, boolean cache) throws TSLArgumentException, TSLParsingException, TSLMalformedException{

		// Almacenamos una "copia de seguridad" de los actuales datos,
		// para que en caso de error, los podamos restaurar.
		URI backupTslTag = tslTag;
		String backupTslID = tslID;
		SchemeInformation backupSchemeInformation = schemeInformation;
		List<TrustServiceProvider> backupTrustServiceProviderList = trustServiceProviderList;
		SignatureType backupSignature = signature;
		boolean restoreBackup = false;
		String msgError = new String();
		try {
			LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL343));
			// Construimos la TSL a partir del XML.
			fullTSLxml = getTSLBuilder().buildTSLFromXML(is);
			// Comprobamos que los valores establecidos son los correctos.
			if(schemeInformation != null){
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL347));
				getTSLChecker().checkTSLValues(checkSignature, fullTSLxml);
			}
			
			
			
			

		} catch (TSLParsingException | TSLMalformedException e) {
			restoreBackup = true;
			msgError = Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL266, new Object[] {e.getErrorDescription()});
			if (!cache) {
				throw e;
			}
		} finally {
			if(schemeInformation != null && !UtilsStringChar.isNullOrEmpty(schemeInformation.getSchemeTerritory())){
				LOGGER.info(Language.getFormatResCoreTsl(CoreTslMessages.LOGMTSL345, new Object[]{schemeInformation.getSchemeTerritory()}));
			}else{
				LOGGER.info(Language.getResCoreTsl(CoreTslMessages.LOGMTSL346));
			}
			// Si hubiera que restaurar los datos originales debido a un fallo
			// en el parseo
			// o en la comprobación de los valores...
			if (restoreBackup && !cache) {
				tslTag = backupTslTag;
				tslID = backupTslID;
				schemeInformation = backupSchemeInformation;
				trustServiceProviderList = backupTrustServiceProviderList;
				signature = backupSignature;
			}
			if (cache && !UtilsStringChar.isNullOrEmpty(msgError)) {
				LOGGER.warn(msgError);
			}
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.tsl.parsing.ifaces.ITSLObject#checkValuesBuildXMLfromTSL()
	 */
	@Override
	public final byte[ ] checkValuesBuildXMLfromTSL() throws TSLMalformedException, TSLEncodingException{

		byte[ ] result = null;

		// Comprobamos que los valores establecidos son los correctos.
		getTSLChecker().checkTSLValues(false, null);
		// Una vez comprobados, construimos el XML.
		result = getTSLBuilder().buildXMLfromTSL();

		return result;

	}

}
