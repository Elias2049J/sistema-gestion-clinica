package org.sistema.persistencia;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenceInterface;

import java.util.List;

public class PersistenciaRepCitas implements PersistenceInterface<Cita> {

    @Override
    public boolean importarLista(List<Cita> lista) {
        return false;
    }
    @Override
    public boolean exportarLista(List<Cita> lista) {
        return false;
    }
    }
