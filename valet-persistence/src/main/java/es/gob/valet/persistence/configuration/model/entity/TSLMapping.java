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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TSLMapping.java.</p>
 * <b>Description:</b><p>Class that maps the <i>TSL_MAPPING</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 28/09/2022.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

/**
 * <p>Class that maps the <i>TSL_SERVICE</i> database table as a Plain Old Java Object.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 *  @version 1.0, 28/09/2022.
 */
@Entity
@Table(name = "TSL_MAPPING")
public class TSLMapping implements Serializable {

	/**
	 * Attribute that represents the class serial version.
	 */
	private static final long serialVersionUID = -5150100957528946006L;

	/**
	 * Attribute that represents constant to id service.
	 */
	private static final String ID_SERVICE = "ID_SERVICE";

	/**
	 * Attribute that represents constant to id logical field.
	 */
	private static final String ID_LOGICAL_FIELD = "ID_LOGICAL_FIELD";

	/**
	 * Attribute that represents the pk composite.
	 */
	@EmbeddedId
	private TslMappingPK idTslMapping;

	/**
	 * Attribute that represents the entity to tsl service.
	 */
	@MapsId("idService")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = ID_SERVICE, insertable = false, updatable = false)
	private TSLService tslService;

	/**
	 * Attribute that represents the entity to logical field.
	 */
	@MapsId("idLogicalField")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = ID_LOGICAL_FIELD, insertable = false, updatable = false)
	private LogicalField logicalField;

	/**
	 * Gets the value of the attribute {@link #idTslMapping}.
	 * @return the value of the attribute {@link #idTslMapping}.
	 */
	public TslMappingPK getIdTslMapping() {
		return idTslMapping;
	}

	/**
	 * Sets the value of the attribute {@link #idTslMapping}.
	 * @param idTslMapping The value for the attribute {@link #idTslMapping}.
	 */
	public void setIdTslMapping(TslMappingPK idTslMapping) {
		this.idTslMapping = idTslMapping;
	}

	/**
	 * Gets the value of the attribute {@link #tslService}.
	 * @return the value of the attribute {@link #tslService}.
	 */
	public TSLService getTslService() {
		return tslService;
	}

	/**
	 * Sets the value of the attribute {@link #tslService}.
	 * @param tslService The value for the attribute {@link #tslService}.
	 */
	public void setTslService(TSLService tslService) {
		this.tslService = tslService;
	}

	/**
	 * Gets the value of the attribute {@link #logicalField}.
	 * @return the value of the attribute {@link #logicalField}.
	 */
	public LogicalField getLogicalField() {
		return logicalField;
	}

	/**
	 * Sets the value of the attribute {@link #logicalField}.
	 * @param logicalField The value for the attribute {@link #logicalField}.
	 */
	public void setLogicalField(LogicalField logicalField) {
		this.logicalField = logicalField;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTslMapping == null) ? 0 : idTslMapping.hashCode());
		result = prime * result + ((logicalField == null) ? 0 : logicalField.hashCode());
		result = prime * result + ((tslService == null) ? 0 : tslService.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TSLMapping other = (TSLMapping) obj;
		if (idTslMapping == null) {
			if (other.idTslMapping != null)
				return false;
		} else if (!idTslMapping.equals(other.idTslMapping))
			return false;
		if (logicalField == null) {
			if (other.logicalField != null)
				return false;
		} else if (!logicalField.equals(other.logicalField))
			return false;
		if (tslService == null) {
			if (other.tslService != null)
				return false;
		} else if (!tslService.equals(other.tslService))
			return false;
		return true;
	}

}
