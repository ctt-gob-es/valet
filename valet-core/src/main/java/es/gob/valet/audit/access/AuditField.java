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
 * <b>File:</b><p>es.gob.valet.audit.AuditField.java.</p>
 * <b>Description:</b><p>Class that represents an audit field (id and value).</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>18/02/2019.</p>
 * @author Gobierno de España.
 * @version 1.0, 18/02/2019.
 */
package es.gob.valet.audit.access;

import java.io.Serializable;

/**
 * <p>Class that represents an audit field (id and value).</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 18/02/2019.
 */
public class AuditField implements Serializable {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = -812635779074666389L;

	/**
	 * Attribute that represents the field name.
	 */
	private String fieldName = null;

	/**
	 * Attribute that represents the field value.
	 */
	private String fieldValue = null;

	/**
	 * Constructor method for the class AuditField.java.
	 */
	private AuditField() {
		super();
	}

	/**
	 * Constructor method for the class AuditField.java.
	 * @param name Name for the field.
	 * @param value Value for the field.
	 */
	public AuditField(String name, String value) {
		this();
		setFieldName(name);
		setFieldValue(value);
	}

	/**
	 * Gets the value of the attribute {@link #fieldName}.
	 * @return the value of the attribute {@link #fieldName}.
	 */
	public final String getFieldName() {
		return fieldName;
	}

	/**
	 * Sets the value of the attribute {@link #fieldName}.
	 * @param name The value for the attribute {@link #fieldName}.
	 */
	public final void setFieldName(String name) {
		this.fieldName = name;
	}

	/**
	 * Gets the value of the attribute {@link #fieldValue}.
	 * @return the value of the attribute {@link #fieldValue}.
	 */
	public final String getFieldValue() {
		return fieldValue;
	}

	/**
	 * Sets the value of the attribute {@link #fieldValue}.
	 * @param value The value for the attribute {@link #fieldValue}.
	 */
	public final void setFieldValue(String value) {
		this.fieldValue = value;
	}

}
