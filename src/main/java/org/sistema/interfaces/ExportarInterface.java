package org.sistema.interfaces;

import java.util.List;

public interface ExportarInterface <T> {
    boolean preview(List<T> objeto);
    boolean imprimir(List<T> objeto);
}
