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
 * <b>File:</b><p>es.gob.valet.rest.elements.TslInformationVersionsResponse.java.</p>
 * <b>Description:</b><p> Class that represents the structure that relates each TSL to the version registered in valET..</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>28/05/2021.</p>
 * @author Gobierno de España.
 * @version 1.0, 28/05/2021.
 */
package es.gob.valet.rest.elements;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/** 
 * <p>Class that represents the structure that relates each TSL to the version registered in valET. .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 28/05/2021.
 */
public class TslInformationVersionsResponse implements Serializable {

	/**
	 * Attribute that represents the serial version. 
	 */
	private static final long serialVersionUID = -7533070548023550183L;
	
	/**
	 * Attribute that represents the status.
	 */
	private Integer status;

	/**
	 * Attribute that represents the description.
	 */
	private String description;
	
	/**
	 * Attribute that represents a map that relates each TSL with the version registered in valET.
	 */
	private Map<String, Integer> tslVersionsMap = new HashMap<String, Integer>();


	/**
	 * Gets the value of the attribute {@link #tslVersionsMap}.
	 * @return the value of the attribute {@link #tslVersionsMap}.
	 */
	public Map<String, Integer> getTslVersionsMap() {
		return tslVersionsMap;
	}


	/**
	 * Sets the value of the attribute {@link #tslVersionsMap}.
	 * @param tslVersionsMap The value for the attribute {@link #tslVersionsMap}.
	 */
	public void setTslVersionsMap(Map<String, Integer> tslVersionsMap) {
		this.tslVersionsMap = tslVersionsMap;
	}


	
	/**
	 * Gets the value of the attribute {@link #status}.
	 * @return the value of the attribute {@link #status}.
	 */
	public Integer getStatus() {
		return status;
	}


	
	/**
	 * Sets the value of the attribute {@link #status}.
	 * @param status The value for the attribute {@link #status}.
	 */
	public void setStatus(Integer status) {
		this.status = status;
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
	 * @param description The value for the attribute {@link #description}.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
