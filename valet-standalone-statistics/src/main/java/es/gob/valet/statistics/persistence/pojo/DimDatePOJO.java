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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.pojo.DimDatePOJO.java.</p>
 * <b>Description:</b><p> The persistence class for the DIM_DATES database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>17/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 17/10/2019.
 */
package es.gob.valet.statistics.persistence.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import es.gob.valet.commons.utils.NumberConstants;


/** 
 * <p>The persistence class for the DIM_DATES database table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 17/10/2019.
 */
@Entity
@Table(name = "DIM_DATES")
public class DimDatePOJO implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = 2215926138664830305L;
	/**
	 * Attribute that represents the PK.
	 */
	private Long idDate;

	/**
	 * Attribute that represents the year.
	 */
	private Integer year;

	/**
	 * Attribute that represents the month.
	 */
	private Integer month;

	/**
	 * Attribute that represents the day.
	 */
	private Integer day;

	/**
	 * Gets the value of the attribute {@link #id}.
	 * @return the value of the attribute {@link #id}.
	 */
	@Id
	@Column(name = "DATEPK", unique = true, nullable = false, precision = NumberConstants.NUM19)
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long getIdDate() {
		return idDate;
	}

	/**
	 * Sets the value of the attribute {@link #idDate}.
	 * @param idDateParam The value for the attribute {@link #idDate}.
	 */
	public void setIdDate(Long idDateParam) {
		this.idDate = idDateParam;
	}

	/**
	 * Gets the value of the attribute {@link #year}.
	 * @return the value of the attribute {@link #year}.
	 */
	@Column(name = "NYEAR")
	public Integer getYear() {
		return year;
	}

	/**
	 * Sets the value of the attribute {@link #year}.
	 * @param yearParam The value for the attribute {@link #year}.
	 */
	public void setYear(Integer yearParam) {
		this.year = yearParam;
	}

	/**
	 * Gets the value of the attribute {@link #month}.
	 * @return the value of the attribute {@link #month}.
	 */
	@Column(name = "NMONTH")
	public Integer getMonth() {
		return month;
	}

	/**
	 * Sets the value of the attribute {@link #month}.
	 * @param monthParam The value for the attribute {@link #month}.
	 */
	public void setMonth(Integer monthParam) {
		this.month = monthParam;
	}

	/**
	 * Gets the value of the attribute {@link #day}.
	 * @return the value of the attribute {@link #day}.
	 */
	@Column(name = "NDAY")
	public Integer getDay() {
		return day;
	}

	/**
	 * Sets the value of the attribute {@link #day}.
	 * @param dayParam The value for the attribute {@link #day}.
	 */
	public void setDay(Integer dayParam) {
		this.day = dayParam;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		result = prime * result + ((month == null) ? 0 : month.hashCode());
		result = prime * result + ((year == null) ? 0 : year.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DimDatePOJO)) {
			return false;
		}
		DimDatePOJO other = (DimDatePOJO) obj;
		if (day == null) {
			if (other.day != null) {
				return false;
			}
		} else if (!day.equals(other.day)) {
			return false;
		}
		if (month == null) {
			if (other.month != null) {
				return false;
			}
		} else if (!month.equals(other.month)) {
			return false;
		}
		if (year == null) {
			if (other.year != null) {
				return false;
			}
		} else if (!year.equals(other.year)) {
			return false;
		}
		return true;
	}
	
}
