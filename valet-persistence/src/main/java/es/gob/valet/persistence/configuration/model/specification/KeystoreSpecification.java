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
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import es.gob.valet.persistence.configuration.model.entity.Keystore;
import es.gob.valet.persistence.configuration.model.entity.SystemCertificate;

/**
 * <p>Class that manages the SQL request over the keystore entity.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * @version 1.0, 20/09/2018.
 */
public class KeystoreSpecification implements Specification<SystemCertificate> {

	/**
	 * Constant attribute that represents the serial version UID.
	 */
	private static final long serialVersionUID = 2394216280110018182L;

	/**
	 * Attribute that represents the keystore entity to manage.
	 */
	private Keystore keystoreEntity;

	/**
	 * Constructor method for the class KeystoreSpecification.java.
	 * @param keystoreEntityParam Keystore entity to manage.
	 */
	public KeystoreSpecification(Keystore keystoreEntityParam) {
		super();
		this.keystoreEntity = keystoreEntityParam;
	}

	/**
	 * {@inheritDoc}
	 * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery, javax.persistence.criteria.CriteriaBuilder)
	 */
	@Override
	public Predicate toPredicate(Root<SystemCertificate> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		List<Predicate> predicates = new ArrayList<Predicate>();
		predicates.add(builder.equal(root.get("keystore").get("idKeystore"), keystoreEntity.getIdKeystore()));
		return builder.and(predicates.toArray(new Predicate[predicates.size()]));
	}

}
