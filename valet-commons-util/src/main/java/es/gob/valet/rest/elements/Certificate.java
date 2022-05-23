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
 * <b>File:</b><p>es.gob.valet.rest.elements.Certificate.java.</p>
 * <b>Description:</b><p>Class that represents the structure of a certificate contained in the certification chain of the certificate detected in the TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/03/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 02/03/2022.
 */
package es.gob.valet.rest.elements;

import java.io.Serializable;

import es.gob.valet.rest.elements.json.ByteArrayB64;

/** 
 * <p>Class that represents the structure of a certificate contained in the certification chain of the certificate detected in the TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 02/03/2022.
 */
public class Certificate  implements Serializable {

	/**
	 *  Constant attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -4752546025681915677L;
	
	/**
	 * Attribute that indicates if the certificate is root.
	 */
	private Boolean isRoot;
	
	/**
	 * Attribute that represents an array of bytes that represents the certificate in Base64
	 */
	private ByteArrayB64 certificateValue;

	/**
	 * Attribute that represents the TSL revocation status in certificated.
	 */
	private TslRevocationStatus tslRevocStatus;

	
	/**
	 * Gets the value of the attribute {@link #isRoot}.
	 * @return the value of the attribute {@link #isRoot}.
	 */
	public Boolean getIsRoot() {
		return isRoot;
	}

	
	/**
	 * Sets the value of the attribute {@link #isRoot}.
	 * @param isRoot The value for the attribute {@link #isRoot}.
	 */
	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	
	/**
	 * Gets the value of the attribute {@link #certificateValue}.
	 * @return the value of the attribute {@link #certificateValue}.
	 */
	public ByteArrayB64 getCertificateValue() {
		return certificateValue;
	}

	
	/**
	 * Sets the value of the attribute {@link #certificateValue}.
	 * @param certificateValue The value for the attribute {@link #certificateValue}.
	 */
	public void setCertificateValue(ByteArrayB64 certificateValue) {
		this.certificateValue = certificateValue;
	}

	
	/**
	 * Gets the value of the attribute {@link #tslRevocStatus}.
	 * @return the value of the attribute {@link #tslRevocStatus}.
	 */
	public TslRevocationStatus getTslRevocStatus() {
		return tslRevocStatus;
	}

	
	/**
	 * Sets the value of the attribute {@link #tslRevocStatus}.
	 * @param tslRevocStatus The value for the attribute {@link #tslRevocStatus}.
	 */
	public void setTslRevocStatus(TslRevocationStatus tslRevocStatus) {
		this.tslRevocStatus = tslRevocStatus;
	}
}
