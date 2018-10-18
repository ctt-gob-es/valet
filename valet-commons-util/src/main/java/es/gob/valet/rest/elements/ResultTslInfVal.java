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
 * <b>File:</b><p>es.gob.valet.rest.elements.ResultTslInfVal.java.</p>
 * <b>Description:</b><p>Class that represents structure of TSL information and validation.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>07/08/2018.</p>
 * @author Gobierno de España.
 * @version 1.1, 10/09/2018.
 */
package es.gob.valet.rest.elements;

import java.io.Serializable;

/**
 * <p>Class that represents structure of TSL information and validation.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * @version 1.1, 10/09/2018.
 */
public class ResultTslInfVal implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -2903418041646647617L;

	/**
	 * Attribute that represents the TSL information data.
	 */
	private TslInformation tslInformation;

	/**
	 * Attribute that represents the certificated detected in TSL.
	 */
	private CertDetectedInTSL certDetectedInTSL;

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

	/**
	 * Gets the value of the attribute {@link #certDetectedInTSL}.
	 * @return the value of the attribute {@link #certDetectedInTSL}.
	 */
	public CertDetectedInTSL getCertDetectedInTSL() {
		return certDetectedInTSL;
	}

	/**
	 * Sets the value of the attribute {@link #certDetectedInTSL}.
	 * @param certDetectInTSLP The value for the attribute {@link #certDetectedInTSL}.
	 */
	public void setCertDetectedInTSL(final CertDetectedInTSL certDetectInTSLP) {
		this.certDetectedInTSL = certDetectInTSLP;
	}

}
