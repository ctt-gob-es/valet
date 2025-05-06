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
 * <b>File:</b><p>es.gob.valet.importsls.DelegatingCallable.java.</p>
 * <b>Description:</b><p>Wrapper for a {@link Callable} that preserves and restores the {@link RequestContextHolder} request attributes when executing code in a separate thread.</p>
 * <b>Project:</b><p>Platform for detection and validation of certificates recognized in European TSL.</p>
 * <b>Date:</b><p>19/03/2025.</p>
 * @author Gobierno de España.
 * @version 1.0, 06/05/2025.
 */
package es.gob.valet.importsls;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.concurrent.Callable;

/**
 * <p>Class with Service responsible for executing the Trusted Service List (TSL) import process asynchronously.</p>
 * <b>Project:</b><p>Wrapper for a {@link Callable} that preserves and restores the {@link RequestContextHolder} request attributes when executing code in a separate thread.</p>
 * @version 1.0, 06/05/2025.
 */
public class DelegatingCallable<V> implements Callable<V> {

	/**
	 * The original {@link Callable} task to be executed.
	 */
	private final Callable<V> delegate;

	/**
	 * The request attributes captured from the current thread at the time of instantiation.
	 * Used to propagate the request context to another thread.
	 */
	private final ServletRequestAttributes requestAttributes;

	/**
	 * Constructs a {@code DelegatingCallable} that wraps the provided {@link Callable}
	 * and captures the current request attributes (if available) for later use.
	 *
	 * @param delegate the original {@code Callable} to execute
	 */
	public DelegatingCallable(Callable<V> delegate) {
	    this.delegate = delegate;
	    // Capture the request context from the current thread, if any
	    ServletRequestAttributes currentRequestAttributes =
	        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	    this.requestAttributes = currentRequestAttributes != null ? currentRequestAttributes : null;
	}

    /**
     * 
     * {@inheritDoc}
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public V call() throws Exception {
        if (requestAttributes != null) {
            // Restablecer el contexto de solicitud en el hilo actual
            RequestContextHolder.setRequestAttributes(requestAttributes);
        }

        try {
            return delegate.call();
        } finally {
            // Limpiar el contexto de solicitud solo si se restableció previamente
            if (requestAttributes != null) {
                RequestContextHolder.resetRequestAttributes();
            }
        }
    }
}
