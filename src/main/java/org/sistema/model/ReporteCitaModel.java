package org.sistema.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sistema.entidad.Cita;
import org.sistema.use_case.CrudUseCase;
import org.sistema.use_case.PersistenceUseCase;
import org.sistema.use_case.ReporteUseCase;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class ReporteCitaModel implements ReporteUseCase<Cita> {
    private CrudUseCase<Cita, Integer, String> model;
    private PersistenceUseCase<Cita> persistencia;

    @Override
    public List<String> generarReporte() {
        List<String> report = new ArrayList<>();
        report.add("|===============================LISTA COMPLETA DE CITAS===============================");
        report.add(String.format("| %-12s %-14s %-15s %-20s %-12s %-12s %-10s %-15s",
                "Id_Cita", "ID_Paciente", "Medico", "Especialidad", "Fecha", "Hora", "Costo", "Estado"));

        for (Cita cita : getListaReporte()) {
            report.add(String.format("| %-12s %-14s %-15s %-20s %-12s %-12s %-10s %-15s",
                    cita.getIdCita(),
                    (cita.getPaciente() != null ? cita.getPaciente().getIdPaciente() : ""),
                    cita.getMedico(),
                    cita.getEspecialidad(),
                    (cita.getFecha() != null ? cita.getFecha().toString() : ""),
                    (cita.getHora() != null ? cita.getHora().toString() : ""),
                    cita.getCosto(),
                    cita.getEstado())
            );
        }
        return report;
    }

    @Override
    public boolean imprimirReporte() {
        return persistencia.exportarLista(getListaReporte());
    }

    @Override
    public List<Cita> getListaReporte() {
        return model.findAll();
    }
}
