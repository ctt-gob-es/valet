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
 * <b>File:</b><p>es.gob.valet.spring.config.TATA.java.</p>
 * <b>Description:</b><p>Class that allows access to spring context beans.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>20/10/2022.</p>
 * @author Gobierno de España.
 * @version 1.0, 20/10/2022.
 */
package es.gob.valet.spring.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/** 
 * <p>Class that allows access to spring context beans.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 20/10/2022.
 */
@Component
public class ApplicationContextProvider implements ApplicationContextAware {
	
	/**
	 * Attribute that represents the Spring application context. 
	 */
	private static ApplicationContext context;

	/**
	 * Statically gets the value of the attribute {@link #context}.
	 * @return the value of the attribute {@link #context}.
	 */
    public static ApplicationContext getApplicationContext() {
        return context;
    }
        
    /**
     * {@inheritDoc}
     * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext ac)
            throws BeansException {
        context = ac;
    }

}
