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
 * <b>Date:</b><p>19 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 19 jun. 2018.
 */
package es.gob.valet.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import es.gob.valet.rest.exception.CheckItFirst;
import es.gob.valet.rest.exception.ThenCheckIt;

/** 
 * <p>
 * Class that represents the backing form for adding/editing a user.
 * </p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 19 jun. 2018.
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
	@NotBlank(groups = CheckItFirst.class, message = "{form.valid.user.name.notempty}")
	@Size(min = 1, max = 15, groups = ThenCheckIt.class)
	private String nameEdit = "";

	/**
	 * Attribute that represents the value of the input surnames of the user in
	 * the form.
	 */
	@NotBlank(groups = CheckItFirst.class, message = "{form.valid.user.surnames.notempty}")
	@Size(min = 1, max = 30, groups = ThenCheckIt.class)
	private String surnamesEdit = "";

	/**
	 * Attribute that represents the value of the input username of the user in
	 * the form.
	 */
	@NotBlank(groups = CheckItFirst.class, message = "{form.valid.user.login.notempty}")
	@Size(min = 5, max = 30, groups = ThenCheckIt.class)
	private String loginEdit = "";

	/**
	 * Attribute that represents the value of the input email of the user in the
	 * form.
	 */
	@NotBlank(groups = CheckItFirst.class, message = "{form.valid.user.email.notempty}")
	@Size(min = 3, max = 254, groups = ThenCheckIt.class)
	private String emailEdit = "";

	/**
	 * Gets the value of the attribute {@link #idUserValet}.
	 * 
	 * @return the value of the attribute {@link #idUserValet}.
	 */
	public Long getIdUserValetEdit() {
		return idUserValetEdit;
	}

	/**
	 * Gets the value of the attribute {@link #idUserValet}.
	 * 
	 * @return the value of the attribute {@link #idUserValet}.
	 */
	public void setIdUserValetEdit(Long idUserValetEdit) {
		this.idUserValetEdit = idUserValetEdit;
	}

	/**
	 * @return the nameEdit
	 */
	public String getNameEdit() {
		return nameEdit;
	}

	/**
	 * @param nameEdit the nameEdit to set
	 */
	public void setNameEdit(String nameEdit) {
		this.nameEdit = nameEdit;
	}

	/**
	 * @return the surnamesEdit
	 */
	public String getSurnamesEdit() {
		return surnamesEdit;
	}

	/**
	 * @param surnamesEdit the surnamesEdit to set
	 */
	public void setSurnamesEdit(String surnamesEdit) {
		this.surnamesEdit = surnamesEdit;
	}

	/**
	 * @return the loginEdit
	 */
	public String getLoginEdit() {
		return loginEdit;
	}

	/**
	 * @param loginEdit the loginEdit to set
	 */
	public void setLoginEdit(String loginEdit) {
		this.loginEdit = loginEdit;
	}

	/**
	 * @return the emailEdit
	 */
	public String getEmailEdit() {
		return emailEdit;
	}

	/**
	 * @param emailEdit the emailEdit to set
	 */
	public void setEmailEdit(String emailEdit) {
		this.emailEdit = emailEdit;
	}

}
