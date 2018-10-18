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
 * <b>File:</b><p>es.gob.valet.form.ConstantsForm.java.</p>
 * <b>Description:</b><p>Class that represents a constant in clients view.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>3 oct. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 3 oct. 2018.
 */
package es.gob.valet.form;




/** 
 * <p>Class that represents a constant in clients view.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 3 oct. 2018.
 */
public class ConstantsForm {

	/**
	 * Constant id.
	 */
	private Long idConstant;

	/**
	 * Constant value obtained from multilanguage.
	 */
	private String value;

	
	/**
	 * Gets the value of the attribute {@link #idConstant}.
	 * @return the value of the attribute {@link #idConstant}.
	 */
	
	public Long getIdConstant() {
		return idConstant;
	}

		
	/**
	 * Gets the value of the attribute {@link #value}.
	 * @return the value of the attribute {@link #value}.
	 */
	
	public String getValue() {
		return value;
	}




	/**
	 * Constructor method for the class ConstantsForm.java.
	 * @param idConstantParam Constant id from db;
	 * @param valueParam  Constant value
	 */
	public ConstantsForm(Long idConstantParam, String valueParam) {
		this.idConstant = idConstantParam;
		this.value = valueParam;
	}


	/**
	 * Constructor method for the class ConstantsForm.java. 
	 */
	public ConstantsForm() {
		super();
	}
	
}
