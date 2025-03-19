/* Copyright (C) 2011 [Gobierno de Espana]
 * This file is part of "Cliente @Firma".
 * "Cliente @Firma" is free software; you can redistribute it and/or modify it under the terms of:
 *   - the GNU General Public License as published by the Free Software Foundation;
 *     either version 2 of the License, or (at your option) any later version.
 *   - or The European Software License; either version 1.1 or (at your option) any later version.
 * You may contact the copyright holder at: soporte.afirma@seap.minhap.es
 */

package es.gob.valet.sign.cades;

import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cms.SignerId;
import org.bouncycastle.util.Selector;

final class CertHolderBySignerIdSelector implements Selector {

	private final SignerId signerId;
	CertHolderBySignerIdSelector(final SignerId sid) {
		if (sid == null) {
			throw new IllegalArgumentException("El ID del firmante no puede ser nulo"); //$NON-NLS-1$
		}
		this.signerId = sid;
	}

	/** {@inheritDoc} */
	@Override
	public boolean match(final Object o) {

		if (!(o instanceof X509CertificateHolder)) {
			return false;
		}

		return CertHolderBySignerIdSelector.this.signerId.getSerialNumber().equals(((X509CertificateHolder) o).getSerialNumber());
	}

	/** {@inheritDoc} */
	@Override
	public Object clone() {
		throw new UnsupportedOperationException();
	}

}
