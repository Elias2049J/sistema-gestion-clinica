package org.sistema.model;

import org.sistema.entidad.Paciente;
import org.sistema.interfaces.ExportarInterface;
import org.sistema.interfaces.IPersistenciaReportes;
import org.sistema.persistencia.PersistenciaRepPac;

import java.util.List;

public class ExportarModel implements ExportarInterface<Paciente> {
    private IPersistenciaReportes<Paciente> persistenciaReportes;
    public ExportarModel(){
        persistenciaReportes = new PersistenciaRepPac();
    }

    @Override
    public boolean preview(List<Paciente> lista) {
        persistenciaReportes.imprimir(lista);
        return false;
    }

    @Override
    public boolean imprimir(List<Paciente> objeto) {
        return false;
    }
}
