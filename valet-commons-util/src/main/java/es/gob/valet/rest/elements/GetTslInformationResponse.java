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
 * <b>File:</b><p>es.gob.valet.rest.elements.GetTslInformationResponse.java.</p>
 * <b>Description:</b><p> .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/08/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 10/09/2018.
 */
package es.gob.valet.rest.elements;

import java.io.Serializable;

/**
 * <p>Class that represents structure of TSL information.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * @version 1.1, 10/09/2018.
 */
public class GetTslInformationResponse implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -9182794714173356287L;

	/**
	 * Attribute that represents the status.
	 */
	private Integer status;

	/**
	 * Attribute that represents the description.
	 */
	private String description;

	/**
	 * Attribute that represents the result.
	 */
	private TslInformation tslInformation;

	/**
	 * Gets the value of the attribute {@link #status}.
	 * @return the value of the attribute {@link #status}.
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * Sets the value of the attribute {@link #status}.
	 * @param statusParam The value for the attribute {@link #status}.
	 */
	public void setStatus(final Integer statusParam) {
		this.status = statusParam;
	}

	/**
	 * Gets the value of the attribute {@link #description}.
	 * @return the value of the attribute {@link #description}.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the value of the attribute {@link #description}.
	 * @param descriptionParam The value for the attribute {@link #description}.
	 */
	public void setDescription(final String descriptionParam) {
		this.description = descriptionParam;
	}

	/**
	 * Gets the value of the attribute {@link #tslInformation}.
	 * @return the value of the attribute {@link #tslInformation}.
	 */
	public TslInformation getTslInformation() {
		return tslInformation;
	}

	/**
	 * Sets the value of the attribute {@link #tslInformation}.
	 * @param tslInformationP The value for the attribute {@link #tslInformation}.
	 */
	public void setTslInformation(final TslInformation tslInformationP) {
		this.tslInformation = tslInformationP;
	}

}
