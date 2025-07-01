package org.sistema.use_case;

import java.util.List;

public interface PersistenceUseCase<T> {
    boolean importarLista(List<T> lista);
    boolean exportarLista(List<T> lista);
}
