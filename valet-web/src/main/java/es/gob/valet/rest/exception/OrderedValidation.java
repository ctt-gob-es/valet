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
 * <b>File:</b><p>es.gob.valet.rest.controller.OrderedValidation.java.</p>
 * <b>Description:</b><p> Interface to indicate the order the validations.</p>
  * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>20 jun. 2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 20 jun. 2018.
 */
package es.gob.valet.rest.exception;

import javax.validation.GroupSequence;

/** 
 * <p>Interface to indicate the order of the validations</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 13 sept. 2018.
 */
@GroupSequence({CheckItFirst.class, ThenCheckIt.class})
public interface OrderedValidation {

}
