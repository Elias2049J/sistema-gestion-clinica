package org.sistema.interfaces;

import java.util.List;

public interface IPersistenciaReportes<T> {
    boolean imprimir(List<T> lista);
}
