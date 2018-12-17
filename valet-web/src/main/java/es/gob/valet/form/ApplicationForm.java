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
 * <b>File:</b><p>es.gob.valet.form.ApplicationForm.java.</p>
 * <b>Description:</b><p>Class that represents the backing form fot adding/edditing an application.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>12/12/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 12/12/2018.
 */
package es.gob.valet.form;



import es.gob.valet.commons.utils.UtilsStringChar;

/**
 * <p>Class that represents the backing form fot adding/edditing an application.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 12/12/2018.
 */
public class ApplicationForm {

	/**
	 * Attribute that represents the value of the primary key as a hidden input in the form.
	 */
	private Long idApplication = null;


	/**
	 * Attribute that represents the value of the input identifier of the application in the form.
	 */
	private String identifier = UtilsStringChar.EMPTY_STRING;

	/**
	 * Attribute that represents the value of the input name of the application in the form.
	 */
	private String name = UtilsStringChar.EMPTY_STRING;


	/**
	 * Attribute that represents the name of the responsible of the application in the form.
	 */
	private String responsibleName;

	/**
	 * Attribute that represents the phone number of the responsible of the application in the form.
	 */
	private String responsiblePhone;

	/**
	 * Attribute that represents the surnames of the responsible of the application in the form.
	 */
	private String responsibleSurnames;

	/**
	 * Attribute that represents the email of the responsible of the application in the form.
	 */
	private String responsibleMail;

	/**
	 * Attribute that represents index of the row of the selected application.
	 */
	private String rowIndexApplication;

	/**
	 * Gets the value of the attribute {@link #idApplication}.
	 * @return the value of the attribute {@link #idApplication}.
	 */
	public Long getIdApplication() {
		return idApplication;
	}


	/**
	 * Sets the value of the attribute {@link #idApplication}.
	 * @param idApplication The value for the attribute {@link #idApplication}.
	 */
	public void setIdApplication(Long idApplication) {
		this.idApplication = idApplication;
	}


	/**
	 * Gets the value of the attribute {@link #identifier}.
	 * @return the value of the attribute {@link #identifier}.
	 */
	public String getIdentifier() {
		return identifier;
	}


	/**
	 * Sets the value of the attribute {@link #identifier}.
	 * @param identifier The value for the attribute {@link #identifier}.
	 */
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}


	/**
	 * Gets the value of the attribute {@link #name}.
	 * @return the value of the attribute {@link #name}.
	 */
	public String getName() {
		return name;
	}


	/**
	 * Sets the value of the attribute {@link #name}.
	 * @param name The value for the attribute {@link #name}.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * Gets the value of the attribute {@link #responsibleName}.
	 * @return the value of the attribute {@link #responsibleName}.
	 */
	public String getResponsibleName() {
		return responsibleName;
	}


	/**
	 * Sets the value of the attribute {@link #responsibleName}.
	 * @param responsibleName The value for the attribute {@link #responsibleName}.
	 */
	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}


	/**
	 * Gets the value of the attribute {@link #responsiblePhone}.
	 * @return the value of the attribute {@link #responsiblePhone}.
	 */
	public String getResponsiblePhone() {
		return responsiblePhone;
	}


	/**
	 * Sets the value of the attribute {@link #responsiblePhone}.
	 * @param responsiblePhone The value for the attribute {@link #responsiblePhone}.
	 */
	public void setResponsiblePhone(String responsiblePhone) {
		this.responsiblePhone = responsiblePhone;
	}


	/**
	 * Gets the value of the attribute {@link #responsibleSurnames}.
	 * @return the value of the attribute {@link #responsibleSurnames}.
	 */
	public String getResponsibleSurnames() {
		return responsibleSurnames;
	}


	/**
	 * Sets the value of the attribute {@link #responsibleSurnames}.
	 * @param responsibleSurnames The value for the attribute {@link #responsibleSurnames}.
	 */
	public void setResponsibleSurnames(String responsibleSurnames) {
		this.responsibleSurnames = responsibleSurnames;
	}


	/**
	 * Gets the value of the attribute {@link #responsibleMail}.
	 * @return the value of the attribute {@link #responsibleMail}.
	 */
	public String getResponsibleMail() {
		return responsibleMail;
	}


	/**
	 * Sets the value of the attribute {@link #responsibleMail}.
	 * @param responsibleMail The value for the attribute {@link #responsibleMail}.
	 */
	public void setResponsibleMail(String responsibleMail) {
		this.responsibleMail = responsibleMail;
	}



	/**
	 * Gets the value of the attribute {@link #rowIndexApplication}.
	 * @return the value of the attribute {@link #rowIndexApplication}.
	 */
	public String getRowIndexApplication() {
		return rowIndexApplication;
	}



	/**
	 * Sets the value of the attribute {@link #rowIndexApplication}.
	 * @param rowIndexApplication The value for the attribute {@link #rowIndexApplication}.
	 */
	public void setRowIndexApplication(String rowIndexApplication) {
		this.rowIndexApplication = rowIndexApplication;
	}




}
