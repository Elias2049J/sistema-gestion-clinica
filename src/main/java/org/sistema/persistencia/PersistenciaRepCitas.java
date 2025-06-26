package org.sistema.persistencia;

import org.sistema.entidad.Cita;
import org.sistema.interfaces.IPersistenciaReportes;

import java.util.List;

public class PersistenciaRepCitas implements IPersistenciaReportes<Cita> {
    @Override
    public boolean imprimir(List lista) {
        return false;
    }
}
