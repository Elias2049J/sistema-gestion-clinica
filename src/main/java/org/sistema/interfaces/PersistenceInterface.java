package org.sistema.interfaces;

import java.util.List;

public interface PersistenceInterface<T> {
    boolean importarLista(List<T> lista);
    boolean exportarLista(List<T> lista);
}
