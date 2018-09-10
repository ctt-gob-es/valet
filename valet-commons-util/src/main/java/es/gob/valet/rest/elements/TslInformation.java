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
 * <b>File:</b><p>es.gob.valet.rest.elements.TslInformation.java.</p>
 * <b>Description:</b><p>Class that represents structure of a TSL information request.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/08/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 10/09/2018.
 */
package es.gob.valet.rest.elements;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Class that represents structure of a TSL information request.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * @version 1.1, 10/09/2018.
 */
public class TslInformation implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 2705319222271442661L;

	/**
	 * Attribute that represents the country region in TSL information.
	 */
	private String countryRegion;

	/**
	 * Attribute that represents the sequence number in TSL information.
	 */
	private Integer sequenceNumber;

	/**
	 * Attribute that represents the location in TSL information.
	 */
	private String tslLocation;

	/**
	 * Attribute that represents the issued date in TSL information.
	 */
	private Date issued;

	/**
	 * Attribute that represents the next update date in TSL information.
	 */
	private Date nextUpdate;

	/**
	 * Attribute that represents the xml data in TSL information.
	 */
	private byte[ ] tslXmlData;

	/**
	 * Gets the value of the attribute {@link #countryRegion}.
	 * @return the value of the attribute {@link #countryRegion}.
	 */
	public String getCountryRegion() {
		return countryRegion;
	}

	/**
	 * Sets the value of the attribute {@link #countryRegion}.
	 * @param countryRegionP The value for the attribute {@link #countryRegion}.
	 */
	public void setCountryRegion(final String countryRegionP) {
		this.countryRegion = countryRegionP;
	}

	/**
	 * Gets the value of the attribute {@link #sequenceNumber}.
	 * @return the value of the attribute {@link #sequenceNumber}.
	 */
	public Integer getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * Sets the value of the attribute {@link #sequenceNumber}.
	 * @param sequenceNumberP The value for the attribute {@link #sequenceNumber}.
	 */
	public void setSequenceNumber(final Integer sequenceNumberP) {
		this.sequenceNumber = sequenceNumberP;
	}

	/**
	 * Gets the value of the attribute {@link #tslLocation}.
	 * @return the value of the attribute {@link #tslLocation}.
	 */
	public String getTslLocation() {
		return tslLocation;
	}

	/**
	 * Sets the value of the attribute {@link #tslLocation}.
	 * @param tslLocationParam The value for the attribute {@link #tslLocation}.
	 */
	public void setTslLocation(final String tslLocationParam) {
		this.tslLocation = tslLocationParam;
	}

	/**
	 * Gets the value of the attribute {@link #issued}.
	 * @return the value of the attribute {@link #issued}.
	 */
	public Date getIssued() {
		return issued;
	}

	/**
	 * Sets the value of the attribute {@link #issued}.
	 * @param issuedParam The value for the attribute {@link #issued}.
	 */
	public void setIssued(final Date issuedParam) {
		this.issued = issuedParam;
	}

	/**
	 * Gets the value of the attribute {@link #nextUpdate}.
	 * @return the value of the attribute {@link #nextUpdate}.
	 */
	public Date getNextUpdate() {
		return nextUpdate;
	}

	/**
	 * Sets the value of the attribute {@link #nextUpdate}.
	 * @param nextUpdateParam The value for the attribute {@link #nextUpdate}.
	 */
	public void setNextUpdate(final Date nextUpdateParam) {
		this.nextUpdate = nextUpdateParam;
	}

	/**
	 * Gets the value of the attribute {@link #tslXmlData}.
	 * @return the value of the attribute {@link #tslXmlData}.
	 */
	public byte[ ] getTslXmlData() {
		return tslXmlData;
	}

	/**
	 * Sets the value of the attribute {@link #tslXmlData}.
	 * @param tslXmlDataParam The value for the attribute {@link #tslXmlData}.
	 */
	public void setTslXmlData(final byte[ ] tslXmlDataParam) {
		this.tslXmlData = tslXmlDataParam;
	}

}
