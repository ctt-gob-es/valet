/*
 * Este fichero forma parte de la plataforma de @firma.
 * La plataforma de @firma es de libre distribución cuyo código fuente puede ser consultado
 * y descargado desde http://administracionelectronica.gob.es
 *
 * Copyright 2005-2019 Gobierno de España
 * Este fichero se distribuye bajo las licencias EUPL versión 1.1, según las
 * condiciones que figuran en el fichero 'LICENSE.txt' que se acompaña.  Si se   distribuyera este
 * fichero individualmente, deben incluirse aquí las condiciones expresadas allí.
 */

/**
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.entity.TslMappingPK.java.</p>
 * <b>Description:</b><p>Class that represents the Primary Key for the <i>TSL_MAPPING</i> database table.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI
 * certificates and electronic signature.</p>
 * <b>Date:</b><p>20/10/2020.</p>
 * @author Gobierno de España.
 * @version 1.0, 28/09/2022.
 */
package es.gob.valet.persistence.configuration.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import es.gob.valet.commons.utils.NumberConstants;

/**
 * <p>Class that represents the Primary Key for the <i>X_CERT_TYPE_POLICY</i> database table.</p>
 * <b>Project:</b><p>Horizontal platform of validation services of multiPKI
 * certificates and electronic signature.</p>
 * @version 1.0, 28/09/2022.
 */
@Embeddable
public class TslMappingPK implements Serializable {

	/**
	 * Attribute that represents the class serial version.
	 */
	private static final long serialVersionUID = 4630855093693876916L;

	/**
	 * Attribute that represents the id service.
	 */
	@Column(name = "ID_SERVICE", unique = true, nullable = false, precision = NumberConstants.NUM19)
	private Long idService;

	/**
	 * Attribute that represents the logical field.
	 */
	@Column(name = "ID_LOGICAL_FIELD", unique = true, nullable = false, precision = NumberConstants.NUM19)
	private Long idLogicalField;

	/**
	 * Gets the value of the attribute {@link #idService}.
	 * @return the value of the attribute {@link #idService}.
	 */
	public Long getIdService() {
		return idService;
	}

	/**
	 * Sets the value of the attribute {@link #idService}.
	 * @param idService The value for the attribute {@link #idService}.
	 */
	public void setIdService(Long idService) {
		this.idService = idService;
	}

	/**
	 * Gets the value of the attribute {@link #idLogicalField}.
	 * @return the value of the attribute {@link #idLogicalField}.
	 */
	public Long getIdLogicalField() {
		return idLogicalField;
	}

	/**
	 * Sets the value of the attribute {@link #idLogicalField}.
	 * @param idLogicalField The value for the attribute {@link #idLogicalField}.
	 */
	public void setIdLogicalField(Long idLogicalField) {
		this.idLogicalField = idLogicalField;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idLogicalField == null) ? 0 : idLogicalField.hashCode());
		result = prime * result + ((idService == null) ? 0 : idService.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TslMappingPK other = (TslMappingPK) obj;
		if (idLogicalField == null) {
			if (other.idLogicalField != null)
				return false;
		} else if (!idLogicalField.equals(other.idLogicalField))
			return false;
		if (idService == null) {
			if (other.idService != null)
				return false;
		} else if (!idService.equals(other.idService))
			return false;
		return true;
	}

}
