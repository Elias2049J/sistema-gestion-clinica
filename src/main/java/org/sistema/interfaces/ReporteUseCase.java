package org.sistema.interfaces;

import java.util.List;

public interface ReporteUseCase<T> {
    List<String> generarReporte();
    boolean imprimirReporte();
    List<T> getListaReporte();
}
