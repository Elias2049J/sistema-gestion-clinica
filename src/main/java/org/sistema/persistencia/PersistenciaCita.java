package org.sistema.persistencia;

import org.sistema.entidad.Cita;

public class PersistenciaCita extends Persistencia<Cita> {
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/pacientes/citas.txt";
    }

    @Override
    protected Cita parsearLinea(String linea) {
        return null;
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-30s %-12s %-15s %-15s %-10s %-30s %-15s",
                "Id_Cita", "ID_Paciente", "Medico", "Especialidad", "Fecha", "Hora", "Costo", "Estado");
    }

    @Override
    protected String formatObjeto(Cita cita) {
        return String.format("%-12s %-30s %-12s %-15s %-15s %-10s %-30s %-15s",
                cita.getIdCita(),
                (cita.getPaciente() != null ? cita.getPaciente().getIdPaciente() : ""),
                cita.getMedico(),
                cita.getEspecialidad(),
                (cita.getFecha() != null ? cita.getFecha().toString() : ""),
                (cita.getHora() != null ? cita.getHora().toString() : ""),
                cita.getCosto(),
                cita.getEstado());
    }
}
