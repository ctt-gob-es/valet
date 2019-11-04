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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.pojo.DimNode.java.</p>
 * <b>Description:</b><p>The persistence class for the DIM_NODES database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>22/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 22/10/2019.
 */
package es.gob.valet.statistics.persistence.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.gob.valet.commons.utils.NumberConstants;


/** 
 * <p>The persistence class for the DIM_NODES database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 22/10/2019.
 */
@Entity
@Table(name = "DIM_NODES")
public class DimNodePOJO implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -6183059183974916899L;
	/**
	 * Attribute that represents the PK key.
	 */
	private Long nodePk;

	/**
	 * Attribute that represents the NODE ID field.
	 */
	private String filename;

	/**
	 * Attribute that represents the start date of the statistics process.
	 */
	private Date startDate;

	/**
	 * Attribute that represents the end date of the statistics process.
	 */
	private Date endDate;

	
	/**
	 * Gets the value of the attribute {@link #nodePk}.
	 * @return the value of the attribute {@link #nodePk}.
	 */
	@Id
	@Column(name = "NODEPK", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long getNodePk() {
		return nodePk;
	}

	
	/**
	 * Sets the value of the attribute {@link #nodePk}.
	 * @param nodePkParam The value for the attribute {@link #nodePk}.
	 */
	public void setNodePk(Long nodePkParam) {
		this.nodePk = nodePkParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #filename}.
	 * @return the value of the attribute {@link #filename}.
	 */
	@Column(name = "FILENAME", unique=true)
	public String getFilename() {
		return filename;
	}

	
	/**
	 * Sets the value of the attribute {@link #filename}.
	 * @param filenameParam The value for the attribute {@link #filename}.
	 */
	public void setFilename(String filenameParam) {
		this.filename = filenameParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #startDate}.
	 * @return the value of the attribute {@link #startDate}.
	 */
	@Column(name = "START_DATE")
	public Date getStartDate() {
		return startDate;
	}

	
	/**
	 * Sets the value of the attribute {@link #startDate}.
	 * @param startDateParam The value for the attribute {@link #startDate}.
	 */
	public void setStartDate(Date startDateParam) {
		this.startDate = startDateParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #endDate}.
	 * @return the value of the attribute {@link #endDate}.
	 */
	@Column(name = "END_DATE")
	public Date getEndDate() {
		return endDate;
	}

	
	/**
	 * Sets the value of the attribute {@link #endDate}.
	 * @param endDateParam The value for the attribute {@link #endDate}.
	 */
	public void setEndDate(Date endDateParam) {
		this.endDate = endDateParam;
	}

}
