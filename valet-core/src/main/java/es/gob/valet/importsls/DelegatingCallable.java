package es.gob.valet.importsls;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.concurrent.Callable;

public class DelegatingCallable<V> implements Callable<V> {

    private final Callable<V> delegate;
    private final ServletRequestAttributes requestAttributes;

    public DelegatingCallable(Callable<V> delegate) {
        this.delegate = delegate;
        // Si RequestContextHolder no tiene atributos, significa que no estamos en un hilo manejado por Spring
        ServletRequestAttributes currentRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        this.requestAttributes = currentRequestAttributes != null ? currentRequestAttributes : null;
    }

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
