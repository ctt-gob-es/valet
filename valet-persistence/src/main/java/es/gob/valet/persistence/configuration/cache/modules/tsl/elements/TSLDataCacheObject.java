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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.cache.modules.tsl.elements.TSLDataCacheObject.java.</p>
 * <b>Description:</b><p>Class that represents a TSL Data Object representation in the clustered cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>24/10/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 29/10/2018.
 */
package es.gob.valet.persistence.configuration.cache.modules.tsl.elements;

import java.io.Serializable;
import java.util.Date;

import es.gob.valet.exceptions.IValetException;
import es.gob.valet.i18n.Language;
import es.gob.valet.i18n.messages.IPersistenceCacheMessages;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectCloneException;
import es.gob.valet.persistence.configuration.cache.common.exceptions.ConfigurationCacheObjectStreamException;
import es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject;
import es.gob.valet.persistence.configuration.cache.modules.tsl.exceptions.TSLCacheException;
import es.gob.valet.persistence.configuration.model.entity.TslData;

/**
 * <p>Class that represents a TSL Data Object representation in the clustered cache.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 29/10/2018.
 */
public class TSLDataCacheObject extends ConfigurationCacheObject {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 8420951666695584053L;

	/**
	 * Attribute that represents the TSL data ID.
	 */
	private long tslDataId = -1;

	/**
	 * Attribute that represents the TSL Specification and Version ID.
	 */
	private long tslImplId = -1;


	/**
	 * Attribute that represents the TSL location URI.
	 */
	private String tslLocationUri = null;

	/**
	 * Attribute that represents a flag that indicates if the legible document of the TSL has been added in the data base.
	 */
	private boolean isLegibleDocumentAdded = false;

	/**
	 * Attribute that represents the issue date of the TSL.
	 */
	private Date issueDate = null;

	/**
	 * Attribute that represents the next update date of the TSL.
	 */
	private Date nextUpdateDate = null;

	/**
	 * Attribute that represents the sequence number of the TSL.
	 */
	private int sequenceNumber = -1;

	/**
	 * Attribute that represents a TSL object representation.
	 */
	private Serializable tslObject = null;

	/**
	 * Attribute that represents if exists a new version of TSL.
	 */
	private String newTSLAvailable = null;

	/**
	 * Attribute that represents the last new TSL available are find.
	 */
	private Date lastNewTSLAvailableFind = null;

	/**
	 * Constructor method for the class TSLDataCacheObject.java.
	 */
	public TSLDataCacheObject() {
		super();
	}

	/**
	 * Constructor method for the class TSLDataCacheObject.java.
	 * @param td Object that represents a TSL Data assigned to a specific country/region.
	 * @param tslObjectSerializable TSL Object that represents the parsed XML.
	 * @throws TSLCacheException If some input parameter is <code>null</code>.
	 */
	public TSLDataCacheObject(TslData td, Serializable tslObjectSerializable) throws TSLCacheException {

		this();

		// Si el pojo recibido es nulo, se lanza una excepción ya que no se
		// puede inicializar el objeto.
		if (td == null || tslObjectSerializable == null) {
			throw new TSLCacheException(IValetException.COD_191, Language.getResPersistenceCache(IPersistenceCacheMessages.CONFIG_TSL_CACHE_LOG091));
		} else {

			setTslDataId(td.getIdTslData().longValue());
			setTslImplId(td.getTslImpl().getIdTSLImpl().longValue());
			setTslLocationUri(td.getUriTslLocation());
			setLegibleDocumentAdded(td.getLegibleDocument() != null);
			setIssueDate(td.getIssueDate());
			setNextUpdateDate(td.getExpirationDate());
			setSequenceNumber(td.getSequenceNumber().intValue());
			setNewTSLAvailable(td.getNewTSLAvailable());
			setTslObject(tslObjectSerializable);
			setLastNewTSLAvailableFind(td.getLastNewTSLAvailableFind());

		}

	}

	/**
	 * Gets the value of the attribute {@link #tslDataId}.
	 * @return the value of the attribute {@link #tslDataId}.
	 */
	public final long getTslDataId() {
		return tslDataId;
	}

	/**
	 * Sets the value of the attribute {@link #tslDataId}.
	 * @param tslDataIdParam The value for the attribute {@link #tslDataId}.
	 */
	public final void setTslDataId(long tslDataIdParam) {
		this.tslDataId = tslDataIdParam;
	}

	/**
	 * Gets the value of the attribute {@link #tslImplId}.
	 * @return the value of the attribute {@link #tslImplId}.
	 */
	public final long getTslImplId() {
		return tslImplId;
	}

	/**
	 * Sets the value of the attribute {@link #tslImplId}.
	 * @param tslImplIdParam The value for the attribute {@link #tslImplId}.
	 */
	public final void setTslImplId(long tslImplIdParam) {
		this.tslImplId = tslImplIdParam;
	}


	/**
	 * Gets the value of the attribute {@link #tslLocationUri}.
	 * @return the value of the attribute {@link #tslLocationUri}.
	 */
	public final String getTslLocationUri() {
		return tslLocationUri;
	}

	/**
	 * Sets the value of the attribute {@link #tslLocationUri}.
	 * @param tslLocationUriParam The value for the attribute {@link #tslLocationUri}.
	 */
	public final void setTslLocationUri(String tslLocationUriParam) {
		this.tslLocationUri = tslLocationUriParam;
	}

	/**
	 * Gets the value of the attribute {@link #isLegibleDocumentAdded}.
	 * @return the value of the attribute {@link #isLegibleDocumentAdded}.
	 */
	public final boolean isLegibleDocumentAdded() {
		return isLegibleDocumentAdded;
	}

	/**
	 * Sets the value of the attribute {@link #isLegibleDocumentAdded}.
	 * @param isLegibleDocumentAddedParam The value for the attribute {@link #isLegibleDocumentAdded}.
	 */
	public final void setLegibleDocumentAdded(boolean isLegibleDocumentAddedParam) {
		this.isLegibleDocumentAdded = isLegibleDocumentAddedParam;
	}

	/**
	 * Gets the value of the attribute {@link #issueDate}.
	 * @return the value of the attribute {@link #issueDate}.
	 */
	public final Date getIssueDate() {
		return issueDate;
	}

	/**
	 * Sets the value of the attribute {@link #issueDate}.
	 * @param issueDateParam The value for the attribute {@link #issueDate}.
	 */
	public final void setIssueDate(Date issueDateParam) {
		this.issueDate = issueDateParam;
	}

	/**
	 * Gets the value of the attribute {@link #nextUpdateDate}.
	 * @return the value of the attribute {@link #nextUpdateDate}.
	 */
	public final Date getNextUpdateDate() {
		return nextUpdateDate;
	}

	/**
	 * Sets the value of the attribute {@link #nextUpdateDate}.
	 * @param nextUpdateDateParam The value for the attribute {@link #nextUpdateDate}.
	 */
	public final void setNextUpdateDate(Date nextUpdateDateParam) {
		this.nextUpdateDate = nextUpdateDateParam;
	}

	/**
	 * Gets the value of the attribute {@link #sequenceNumber}.
	 * @return the value of the attribute {@link #sequenceNumber}.
	 */
	public final int getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Sets the value of the attribute {@link #sequenceNumber}.
	 * @param sequenceNumberParam The value for the attribute {@link #sequenceNumber}.
	 */
	public final void setSequenceNumber(int sequenceNumberParam) {
		this.sequenceNumber = sequenceNumberParam;
	}

	/**
	 * Gets the value of the attribute {@link #tslObject}.
	 * @return the value of the attribute {@link #tslObject}.
	 */
	public final Serializable getTslObject() {
		return tslObject;
	}

	/**
	 * Sets the value of the attribute {@link #tslObject}.
	 * @param tslObjectParam The value for the attribute {@link #tslObject}.
	 */
	public final void setTslObject(Serializable tslObjectParam) {
		this.tslObject = tslObjectParam;
	}

	/**
	 * Gets the value of the attribute {@link #newTSLAvailable}.
	 * @return the value of the attribute {@link #newTSLAvailable}.
	 */
	public final String getNewTSLAvailable() {
		return newTSLAvailable;
	}

	/**
	 * Sets the value of the attribute {@link #newTSLAvailable}.
	 * @param newTSLAvailableParam The value for the attribute {@link #newTSLAvailable}.
	 */
	public final void setNewTSLAvailable(String newTSLAvailableParam) {
		this.newTSLAvailable = newTSLAvailableParam;
	}

	/**
	 * Gets the value of the attribute {@link #lastNewTSLAvailableFind}.
	 * @return the value of the attribute {@link #lastNewTSLAvailableFind}.
	 */
	public final Date getLastNewTSLAvailableFind() {
		return lastNewTSLAvailableFind;
	}

	/**
	 * Sets the value of the attribute {@link #lastNewTSLAvailableFind}.
	 * @param lastNewTSLAvailableFindParam The value for the attribute {@link #lastNewTSLAvailableFind}.
	 */
	public final void setLastNewTSLAvailableFind(Date lastNewTSLAvailableFindParam) {
		this.lastNewTSLAvailableFind = lastNewTSLAvailableFindParam;
	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject#writeReplace()
	 */
	@Override
	protected Object writeReplace() throws ConfigurationCacheObjectStreamException {

		try {
			return this.clone();
		} catch (CloneNotSupportedException e) {
			throw new ConfigurationCacheObjectStreamException(e.getMessage());
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject#readResolve()
	 */
	@Override
	protected Object readResolve() throws ConfigurationCacheObjectStreamException {

		try {
			return this.clone();
		} catch (CloneNotSupportedException e) {
			throw new ConfigurationCacheObjectStreamException(e.getMessage());
		}

	}

	/**
	 * {@inheritDoc}
	 * @see es.gob.valet.persistence.configuration.cache.common.impl.ConfigurationCacheObject#clone()
	 */
	@Override
	public ConfigurationCacheObject clone() throws ConfigurationCacheObjectCloneException {

		TSLDataCacheObject tdco = new TSLDataCacheObject();

		tdco.setTslDataId(getTslDataId());
		tdco.setTslImplId(getTslImplId());
		tdco.setTslLocationUri(getTslLocationUri());
		tdco.setLegibleDocumentAdded(isLegibleDocumentAdded());
		tdco.setIssueDate((Date) getIssueDate().clone());
		if (getNextUpdateDate() != null) {
			tdco.setNextUpdateDate((Date) getNextUpdateDate().clone());
		}
		tdco.setSequenceNumber(getSequenceNumber());
		tdco.setTslObject(getTslObject());
		tdco.setNewTSLAvailable(getNewTSLAvailable());
		tdco.setLastNewTSLAvailableFind(getLastNewTSLAvailableFind());

		return tdco;

	}

}
