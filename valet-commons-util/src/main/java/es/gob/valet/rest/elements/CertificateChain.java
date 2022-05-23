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
 * <b>File:</b><p>es.gob.valet.rest.elements.CertificateChain.java.</p>
 * <b>Description:</b><p>Class that represents the certificate chain of the certificate being validated.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>02/03/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 02/03/2022.
 */
package es.gob.valet.rest.elements;

import java.io.Serializable;
import java.util.List;


/** 
 * <p>Class that represents the certificate chain of the certificate being validated.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 02/03/2022.
 */
public class CertificateChain implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -5340721366963200015L;

	/**
	 * Attribute that represents the list of certificates that make up the certification chain.
	 */
	List<Certificate> certificates;

	
	/**
	 * Gets the value of the attribute {@link #certificates}.
	 * @return the value of the attribute {@link #certificates}.
	 */
	public List<Certificate> getCertificates() {
		return certificates;
	}

	
	/**
	 * Sets the value of the attribute {@link #certificates}.
	 * @param certificates The value for the attribute {@link #certificates}.
	 */
	public void setCertificates(List<Certificate> certificates) {
		this.certificates = certificates;
	}
}
