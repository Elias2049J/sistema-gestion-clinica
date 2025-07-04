package org.sistema.persistencia;

public class PersistenciaRepCita extends PersistenciaCita{
    protected String titulo = "LIST COMPLETA DE CITAS";
    protected String cabeceraCol = String.format("%-12s %-14s %-15s %-20s %-12s %-12s %-10s %-15s",
                    "Id_Cita", "ID_Paciente", "Medico", "Especialidad", "Fecha", "Hora", "Costo", "Estado");

    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/listaCitas.txt";
    }

    @Override
    protected String getCabecera() {
        return "================================"+titulo+"=================================\n"
                + cabeceraCol;
    }
}
