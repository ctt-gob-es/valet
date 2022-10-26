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
 * <b>File:</b><p>es.gob.valet.tsl.access.TslInformation.java.</p>
 * <b>Description:</b><p> Method to store the information obtained from the TSLs necessary for the generation of the tree of the TSL Mapping Module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>23/09/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 23/09/2022.
 */
package es.gob.valet.tsl.access;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import es.gob.valet.persistence.configuration.model.dto.TslMappingDTO;

/** 
 * <p>Method to store the information obtained from the TSLs necessary for the generation of the tree of the TSL Mapping Module.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 23/09/2022.
 */
@Component
public final class TslInformationTree {

	static {
		mapTslMappingTree =new HashMap<String, List<TslMappingDTO>>();
	}

	/**
	 * Attribute that represents the map of TslMappingTreeDTO that appear in the TSLs by Country.
	 */
	static Map<String, List<TslMappingDTO>> mapTslMappingTree = new HashMap<String, List<TslMappingDTO>>();

	
	/**
	 * Gets the value of the attribute {@link #mapTslMappingTree}.
	 * @return the value of the attribute {@link #mapTslMappingTree}.
	 */
	public static Map<String, List<TslMappingDTO>> getMapTslMappingTree() {
		return mapTslMappingTree;
	}



}
