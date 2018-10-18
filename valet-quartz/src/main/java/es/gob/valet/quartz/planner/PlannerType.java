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
 * <b>File:</b><p>es.gob.valet.quartz.planner.PlannerType.java.</p>
 * <b>Description:</b><p> Class object for manage planners types.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/09/2018.
 */
package es.gob.valet.quartz.planner;

import java.io.Serializable;

/** 
 * <p>Class object for manage planners types.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/09/2018.
 */
public class PlannerType implements Serializable {

	/**
	 * Class serial version.
	 */
	private static final long serialVersionUID = -4124066859380977850L;

	/**
	 * Attribute that represents the object ID.
	 */
	private Long idPlannerType;

	/**
	 * Attribute that represents the name of the token.
	 */
	private String tokenName;

	/**
	 * Gets the value of the attribute {@link #idPlannerType}.
	 * @return the value of the attribute {@link #idPlannerType}.
	 */
	public Long getIdPlannerType() {
		return idPlannerType;
	}

	/**
	 * Sets the value of the attribute {@link #idPlannerType}.
	 * @param pIdPlannerType The value for the attribute {@link #idPlannerType}.
	 */
	public void setIdPlannerType(final Long pIdPlannerType) {
		this.idPlannerType = pIdPlannerType;
	}

	/**
	 * Gets the value of the attribute {@link #tokenName}.
	 * @return the value of the attribute {@link #tokenName}.
	 */
	public String getTokenName() {
		return tokenName;
	}

	/**
	 * Sets the value of the attribute {@link #tokenName}.
	 * @param pTokenName The value for the attribute {@link #tokenName}.
	 */
	public void setTokenName(final String pTokenName) {
		this.tokenName = pTokenName;
	}

}