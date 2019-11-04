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
 * <b>File:</b><p>es.gob.valet.statistics.persistence.pojo.CCodResults.java.</p>
 * <b>Description:</b><p>The persistence class for the C_CODRESULTS database table .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/10/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/10/2019.
 */
package es.gob.valet.statistics.persistence.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import es.gob.valet.commons.utils.NumberConstants;


/** 
 * <p>The persistence class for the C_CODRESULTS database table </p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/10/2019.
 */
@Entity
@Table(name = "C_CODRESULTS")
public class CCodResultsPOJO implements Serializable {

	/**
	 * Attribute that represents the serial version UID. 
	 */
	private static final long serialVersionUID = -3101984144722358630L;
	
	/**
	 * Attribute that represents the primary key.
	 */
	private Long codResultPk;
	
	/**
	 * Attribute that represents the description of the result.
	 */
	private String resultDescription;

	
	/**
	 * Gets the value of the attribute {@link #codResultPk}.
	 * @return the value of the attribute {@link #codResultPk}.
	 */
	@Id
	@Column(name = "CODRESULTPK", unique = true, nullable = false, precision = NumberConstants.NUM4)
	public Long getCodResultPk() {
		return codResultPk;
	}

	
	/**
	 * Sets the value of the attribute {@link #codResultPk}.
	 * @param codResultPkParam The value for the attribute {@link #codResultPk}.
	 */
	public void setCodResultPk(Long codResultPkParam) {
		this.codResultPk = codResultPkParam;
	}

		
	/**
	 * Gets the value of the attribute {@link #resultDescription}.
	 * @return the value of the attribute {@link #resultDescription}.
	 */
	@Column(name = "DESCRIPTION", nullable = false)
	public String getResultDescription() {
		return resultDescription;
	}


	
	/**
	 * Sets the value of the attribute {@link #resultDescription}.
	 * @param resultDescriptionParam The value for the attribute {@link #resultDescription}.
	 */
	public void setResultDescription(String resultDescriptionParam) {
		this.resultDescription = resultDescriptionParam;
	}

}
