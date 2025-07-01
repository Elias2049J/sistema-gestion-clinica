package org.sistema.use_case;

import java.util.List;

public interface ReporteUseCase<T> {
    List<String> generarReporte();
    boolean imprimirReporte();
    List<T> getListaReporte();
}
