package org.sistema.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.CrudInterface;
import org.sistema.interfaces.PersistenceInterface;
import org.sistema.interfaces.ReporteUseCase;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
public class ReporteTerceraEdadModel implements ReporteUseCase<Paciente> {
    private CrudInterface<Paciente, Integer> crudPacienteModel;
    private PersistenceInterface<Paciente> persistenciaRepTerceraEdad;

    @Override
    public List<String> generarReporte() {


        List<String> report = new ArrayList<>();
        report.add("|===============================PACIENTES DE TERCERA EDAD===============================");
        report.add(String.format("| %-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO"));

        for (Paciente p : getListaReporte()) {
            report.add(String.format("| %-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                    p.getIdPaciente(),
                    p.getNombre() != null ? p.getNombre() : "",
                    p.getApellido() != null ? p.getApellido() : "",
                    p.getEdad() != null ? p.getEdad() : 0,
                    p.getDni() != null ? p.getDni() : "",
                    p.getDireccion() != null ? p.getDireccion() : "",
                    p.getTelefono() != null ? p.getTelefono() : "",
                    p.getEstado() != null ? p.getEstado() : "")
            );
        }
        return report;
    }

    @Override
    public boolean imprimirReporte() {
        return persistenciaRepTerceraEdad.exportarLista(getListaReporte());
    }

    @Override
    public List<Paciente> getListaReporte() {
        List<Paciente> terceraEdad = new ArrayList<>();

        for (Paciente p: crudPacienteModel.findAll()) {
            if (p.getEdad() >= 60) {
                terceraEdad.add(p);
            }
        }
        return terceraEdad;
    }
}
