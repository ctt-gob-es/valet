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
 * <b>File:</b><p>es.gob.valet.dto.TslMappingTreeDTO.java.</p>
 * <b>Description:</b><p>Class that represents the information needed to generate the tree of the TSL Mappings module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 28/09/2022.
 */
package es.gob.valet.persistence.configuration.model.dto;

/** 
 * <p>Class that represents the information needed to generate the tree of the TSL Mappings module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 28/09/2022.
 */
public class TslMappingDTO {

	/**
	 * Attribute that represents the country/region code for the TSL.
	 */
	private String codeCountry;
	/**
	 * Attribute that represents the version of the TSL.
	 */
	private String version;

	/**
	 * Attribute that represents the TSP name in certificated detected in TSL.
	 */
	private String tspName;
	/**
	 * Attribute that represents the TSP service name.
	 */
	private String tspServiceName;

	/**
	 * Attribute that represents the digital identity represented by the X509 certificate expressed in Base64 encoded format.
	 */
	private String digitalIdentity;

	/**
	 * Attribute that represents the expiration date of the certificate indicated in digitalIdentity.
	 */
	private String expirationDate;

	/**
	 * Constructor method for the class TslMappingTreeDTO.java. 
	 */
	public TslMappingDTO() {
		super();
	}

	/**
	 * Constructor method for the class TslMappingDTO.java.
	 * @param codeCountry
	 * @param version 
	 * @param tspName
	 */
	public TslMappingDTO(String codeCountry, String version, String tspName) {
		super();
		this.codeCountry = codeCountry;
		this.version = version;
		this.tspName = tspName;
	}

	/**
	 * Gets the value of the attribute {@link #codeCountry}.
	 * @return the value of the attribute {@link #codeCountry}.
	 */
	public String getCodeCountry() {
		return codeCountry;
	}

	/**
	 * Sets the value of the attribute {@link #codeCountry}.
	 * @param codeCountry The value for the attribute {@link #codeCountry}.
	 */
	public void setCodeCountry(String codeCountry) {
		this.codeCountry = codeCountry;
	}

	/**
	 * Gets the value of the attribute {@link #version}.
	 * @return the value of the attribute {@link #version}.
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Sets the value of the attribute {@link #version}.
	 * @param version The value for the attribute {@link #version}.
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Gets the value of the attribute {@link #tspName}.
	 * @return the value of the attribute {@link #tspName}.
	 */
	public String getTspName() {
		return tspName;
	}

	/**
	 * Sets the value of the attribute {@link #tspName}.
	 * @param tspName The value for the attribute {@link #tspName}.
	 */
	public void setTspName(String tspName) {
		this.tspName = tspName;
	}

	/**
	 * Gets the value of the attribute {@link #tspServiceName}.
	 * @return the value of the attribute {@link #tspServiceName}.
	 */
	public String getTspServiceName() {
		return tspServiceName;
	}

	/**
	 * Sets the value of the attribute {@link #tspServiceName}.
	 * @param tspServiceName The value for the attribute {@link #tspServiceName}.
	 */
	public void setTspServiceName(String tspServiceName) {
		this.tspServiceName = tspServiceName;
	}

	/**
	 * Gets the value of the attribute {@link #digitalIdentity}.
	 * @return the value of the attribute {@link #digitalIdentity}.
	 */
	public String getDigitalIdentity() {
		return digitalIdentity;
	}

	/**
	 * Sets the value of the attribute {@link #digitalIdentity}.
	 * @param digitalIdentity The value for the attribute {@link #digitalIdentity}.
	 */
	public void setDigitalIdentity(String digitalIdentity) {
		this.digitalIdentity = digitalIdentity;
	}

	/**
	 * Gets the value of the attribute {@link #expirationDate}.
	 * @return the value of the attribute {@link #expirationDate}.
	 */
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets the value of the attribute {@link #expirationDate}.
	 * @param expirationDate The value for the attribute {@link #expirationDate}.
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codeCountry == null) ? 0 : codeCountry.hashCode());
		result = prime * result + ((digitalIdentity == null) ? 0 : digitalIdentity.hashCode());
		result = prime * result + ((expirationDate == null) ? 0 : expirationDate.hashCode());
		result = prime * result + ((tspName == null) ? 0 : tspName.hashCode());
		result = prime * result + ((tspServiceName == null) ? 0 : tspServiceName.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
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
		TslMappingDTO other = (TslMappingDTO) obj;
		if (codeCountry == null) {
			if (other.codeCountry != null)
				return false;
		} else if (!codeCountry.equals(other.codeCountry))
			return false;
		if (digitalIdentity == null) {
			if (other.digitalIdentity != null)
				return false;
		} else if (!digitalIdentity.equals(other.digitalIdentity))
			return false;
		if (expirationDate == null) {
			if (other.expirationDate != null)
				return false;
		} else if (!expirationDate.equals(other.expirationDate))
			return false;
		if (tspName == null) {
			if (other.tspName != null)
				return false;
		} else if (!tspName.equals(other.tspName))
			return false;
		if (tspServiceName == null) {
			if (other.tspServiceName != null)
				return false;
		} else if (!tspServiceName.equals(other.tspServiceName))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	
}
