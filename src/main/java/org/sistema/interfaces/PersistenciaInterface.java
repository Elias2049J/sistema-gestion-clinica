package org.sistema.interfaces;

import org.sistema.entidad.Paciente;

import java.io.FileNotFoundException;
import java.util.List;

public interface PersistenciaInterface<T> {
    boolean llenarListaDesdeArchivo(List<T> lista) throws FileNotFoundException;
    boolean actualizarArchivo(List<T> lista);
}
