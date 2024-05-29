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
 * <b>File:</b><p>es.gob.valet.dto.MappingDTO.java.</p>
 * <b>Description:</b><p>Class that represents each file in the mapping table associated with a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>05/04/2021.</p>
 * @author Gobierno de España.
 * @version 1.1, 16/09/2021.
 */
package es.gob.valet.dto;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;


/** 
 * <p>Class that represents each file in the mapping table associated with a TSL.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.1, 16/09/2021.
 */
public class PersonalInfoBean {


	private String nombre;
	private String apellidos;
	private String dni;

	public PersonalInfoBean(String nombre, String apellidos, String dni) {
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setDni(dni);
	}
	
	public PersonalInfoBean() {
		this.setNombre("nombre");
		this.setApellidos("apellidos");
		this.setDni("dni");
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
}
