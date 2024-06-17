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
 * <b>File:</b><p>es.gob.afirma.spring.config.MultiFieldLoginUserDetails.java.</p>
 * <b>Description:</b><p> .</p>
  * <b>Project:</b><p>Horizontal platform of validation services of multiPKI certificates and electronic signature.</p>
 * <b>Date:</b><p>03/04/2020.</p>
 * @author Gobierno de España.
 * @version 1.1, 17/06/2024.
 */
package es.gob.valet.spring.config;

public class MultiFieldLoginUserDetails {
    
    private final String username;
    
    private final String signatureBase64;
    
    public MultiFieldLoginUserDetails(String username, String signatureBase64) {
        this.username = username;
    	this.signatureBase64 = signatureBase64;
    }

    public String getUsername() {
        return this.username;
    }
    
    public String getSignatureBase64() {
        return this.signatureBase64;
    }
}
