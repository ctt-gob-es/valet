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
 * <b>File:</b><p>es.gob.valet.persistence.configuration.model.specification.KeystoreSpecification.java.</p>
 * <b>Description:</b><p>Class that manages the SQL request over the keystore table.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>20/09/2018.</p>
 * @author Gobierno de España.
 * @version 1.0, 20/09/2018.
 */
package es.gob.valet.persistence.configuration.model.specification;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import es.gob.valet.persistence.configuration.model.entity.ExternalAccess;

/**
 * <p>Class that manages the SQL request over the externalAccess entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 08/08/2023.
 */
public class ExternalAccessSpecification implements Specification<ExternalAccess> {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 2394216280110018182L;

	/**
	 * Attribute that represents the keystore entity to manage.
	 */
	private ExternalAccess externalAccessEntity;

	/**
	 * Constructor method for the class KeystoreSpecification.java.
	 * @param keystoreEntityParam Keystore entity to manage.
	 */
	public ExternalAccessSpecification(ExternalAccess externalAccessEntityParam) {
		super();
		this.externalAccessEntity = externalAccessEntityParam;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
	 */
	@Override
	public Predicate toPredicate(Root<ExternalAccess> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		//ponemos el filtro de URL
        if (externalAccessEntity.getUrl() != null && !externalAccessEntity.getUrl().isEmpty()) {
            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("url")),
                    "%" + externalAccessEntity.getUrl().toLowerCase() + "%"));
        }
        
        //ponemos el filtro de estado
        if (externalAccessEntity.getStateConn() != null) {
            predicates.add(criteriaBuilder.equal(criteriaBuilder.lower(root.get("stateConn")),
            		externalAccessEntity.getStateConn()));
        }
        
        //ponemos la fecha
      //  Predicate date =  criteriaBuilder.between(root.get("lastConn"), fromDate, toDate);
      //  predicates.add(date);


        query.orderBy(criteriaBuilder.desc(root.get("url")));

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

	}
	
	   public Specification<ExternalAccess> getExternalAccess(ExternalAccess request, Date fromDate,Date toDate) {
	        return (root, query, criteriaBuilder) -> {

	            List<Predicate> predicates = new ArrayList<>();

	            //ponemos el filtro de URL
	            if (request.getUrl() != null && !request.getUrl().isEmpty()) {
	                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("url")),
	                        "%" + request.getUrl().toLowerCase() + "%"));
	            }
	            
	            //ponemos el filtro de estado
	            if (request.getStateConn() != null) {
	                predicates.add(criteriaBuilder.equal(root.get("stateConn"),
	                         request.getStateConn()));
	            }
	            
	            //ponemos la fecha
	           if(fromDate != null && toDate != null ) {
	        	   Predicate date =  criteriaBuilder.between(root.get("lastConn"), fromDate, toDate);
	               predicates.add(date);
	           }else if(fromDate != null && toDate == null ) {
	        	   Predicate date =  criteriaBuilder.greaterThan(root.get("lastConn"), fromDate);
	               predicates.add(date); 
	           }else if(fromDate == null && toDate != null ) {
	        	   Predicate date =  criteriaBuilder.lessThan(root.get("lastConn"), toDate);
	               predicates.add(date);
	           }
	           
	            query.orderBy(criteriaBuilder.desc(root.get("url")));

	            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

	        };
	    }


}
