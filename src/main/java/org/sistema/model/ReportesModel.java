package org.sistema.model;

import org.sistema.entidad.Paciente;
import org.sistema.interfaces.CrudInterface;
import org.sistema.interfaces.ReportesInterface;
import org.sistema.persistencia.PersistenciaRepPac;

import java.util.ArrayList;
import java.util.List;

public class ReportesModel implements ReportesInterface<Paciente> {
    private CrudInterface<Paciente, Integer> crudPacienteModel;
    private PersistenciaRepPac persistenciaRepPac;

    public ReportesModel(CrudInterface<Paciente, Integer> crudPacienteModel) {
        this.crudPacienteModel = crudPacienteModel;
        this.persistenciaRepPac = new PersistenciaRepPac();
    }

    @Override
    public List<String> getListaPacientesReport(List<Paciente> pacientes) {
        List<String> report = new ArrayList<>();
        report.add("|===============================LISTA COMPLETA DE PACIENTES===============================");
        report.add(String.format("| %-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO"));

        for (Paciente p : pacientes) {
            report.add(String.format("| %-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                    p.getIdPaciente(),
                    p.getNombre() != null ? p.getNombre() : "",
                    p.getApellido() != null ? p.getApellido() : "",
                    p.getEdad() != null ? p.getEdad() : 0,
                    p.getDni() != null ? p.getDni() : "",
                    p.getDireccion() != null ? p.getDireccion() : "",
                    p.getTelefono() != null ? p.getTelefono() : "",
                    p.getEstado() != null ? p.getEstado() : ""));
        }
        return report;
    }

    @Override
    public boolean exportarReporte(List<Paciente> lista) {
        return persistenciaRepPac.exportarLista(lista);
    }


    public List<String> getListaPacientesTerceraEdadReport() {
        List<Paciente> pacientesMayores = new ArrayList<>();
        for (Paciente p : crudPacienteModel.findAll()) {
            if (p.getEdad() != null && p.getEdad() >= 60) {
                pacientesMayores.add(p);
            }
        }

        List<String> report = new ArrayList<>();
        report.add("|===============================PACIENTES TERCERA EDAD===============================");
        report.add(String.format("| %-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO"));

        for (Paciente p : pacientesMayores) {
            report.add(String.format("| %-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                    p.getIdPaciente(),
                    p.getNombre() != null ? p.getNombre() : "",
                    p.getApellido() != null ? p.getApellido() : "",
                    p.getEdad() != null ? p.getEdad() : 0,
                    p.getDni() != null ? p.getDni() : "",
                    p.getDireccion() != null ? p.getDireccion() : "",
                    p.getTelefono() != null ? p.getTelefono() : "",
                    p.getEstado() != null ? p.getEstado() : ""));
        }
        return report;
    }

    public boolean exportarReporteTerceraEdad() {
        List<Paciente> pacientes = crudPacienteModel.findAll();
        return persistenciaRepPac.exportarReporteTerceraEdad(pacientes);
    }
}
