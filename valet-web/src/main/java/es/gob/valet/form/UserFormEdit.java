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
 * <b>File:</b><p>es.gob.valet.form.UserFormEdit.java.</p>
 * <b>Description:</b><p> .</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/06/2018.</p>
 * @author Gobierno de España.
 * @version 1.2, 03/04/2023.
 */
package es.gob.valet.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.gob.valet.commons.utils.NumberConstants;
import es.gob.valet.commons.utils.UtilsStringChar;
import es.gob.valet.rest.exception.CheckItFirst;
import es.gob.valet.rest.exception.ThenCheckIt;

/**
 * <p>
 * Class that represents the backing form for adding/editing a user.
 * </p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.2, 03/04/2023.
 */
public class UserFormEdit {
	/**
	 * Attribute that represents the value of the primary key as a hidden input
	 * in the form.
	 */
	private Long idUserValetEdit = null;

	/**
	 * Attribute that represents the value of the input name of the user in the
	 * form.
	 */
	@NotNull(groups = CheckItFirst.class, message = "{form.valid.user.name.notempty}")
	@Size(min = 1, max = NumberConstants.NUM15, groups = ThenCheckIt.class)
	private String nameEdit = UtilsStringChar.EMPTY_STRING;

	/**
	 * Attribute that represents the value of the input surnames of the user in
	 * the form.
	 */
	@NotNull(groups = CheckItFirst.class, message = "{form.valid.user.surnames.notempty}")
	@Size(min = 1, max = NumberConstants.NUM30, groups = ThenCheckIt.class)
	private String surnamesEdit = UtilsStringChar.EMPTY_STRING;

	/**
	 * Attribute that represents the value of the input username of the user in
	 * the form.
	 */
	@NotNull(groups = CheckItFirst.class, message = "{form.valid.user.login.notempty}")
	@Size(min = NumberConstants.NUM5, max = NumberConstants.NUM30, groups = ThenCheckIt.class)
	private String loginEdit = UtilsStringChar.EMPTY_STRING;

	/**
	 * Attribute that represents the value of the input email of the user in the
	 * form.
	 */
	@NotNull(groups = CheckItFirst.class, message = "{form.valid.user.email.notempty}")
	@Size(min = NumberConstants.NUM3, max = NumberConstants.NUM255, groups = ThenCheckIt.class)
	private String emailEdit = UtilsStringChar.EMPTY_STRING;

	
	/**
	 * Gets the value of the attribute {@link #idUserValetEdit}.
	 * @return the value of the attribute {@link #idUserValetEdit}.
	 */
	public Long getIdUserValetEdit() {
		return idUserValetEdit;
	}

	
	/**
	 * Sets the value of the attribute {@link #idUserValetEdit}.
	 * @param idUserValetEditParam The value for the attribute {@link #idUserValetEdit}.
	 */
	public void setIdUserValetEdit(Long idUserValetEditParam) {
		this.idUserValetEdit = idUserValetEditParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #nameEdit}.
	 * @return the value of the attribute {@link #nameEdit}.
	 */
	public String getNameEdit() {
		return nameEdit;
	}

	
	/**
	 * Sets the value of the attribute {@link #nameEdit}.
	 * @param nameEditParam The value for the attribute {@link #nameEdit}.
	 */
	public void setNameEdit(String nameEditParam) {
		this.nameEdit = nameEditParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #surnamesEdit}.
	 * @return the value of the attribute {@link #surnamesEdit}.
	 */
	public String getSurnamesEdit() {
		return surnamesEdit;
	}

	
	/**
	 * Sets the value of the attribute {@link #surnamesEdit}.
	 * @param surnamesEditParam The value for the attribute {@link #surnamesEdit}.
	 */
	public void setSurnamesEdit(String surnamesEditParam) {
		this.surnamesEdit = surnamesEditParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #loginEdit}.
	 * @return the value of the attribute {@link #loginEdit}.
	 */
	public String getLoginEdit() {
		return loginEdit;
	}

	
	/**
	 * Sets the value of the attribute {@link #loginEdit}.
	 * @param loginEditParam The value for the attribute {@link #loginEdit}.
	 */
	public void setLoginEdit(String loginEditParam) {
		this.loginEdit = loginEditParam;
	}

	
	/**
	 * Gets the value of the attribute {@link #emailEdit}.
	 * @return the value of the attribute {@link #emailEdit}.
	 */
	public String getEmailEdit() {
		return emailEdit;
	}

	
	/**
	 * Sets the value of the attribute {@link #emailEdit}.
	 * @param emailEditParam The value for the attribute {@link #emailEdit}.
	 */
	public void setEmailEdit(String emailEditParam) {
		this.emailEdit = emailEditParam;
	}



}
