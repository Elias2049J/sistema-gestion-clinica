package org.sistema.persistencia;

public class PersistenciaRepCitasCanceladas extends PersistenciaCita{
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/citas_canceladas.txt";
    }

    @Override
    protected String getCabecera() {
        return "|=======================================CITAS CANCELADAS======================================\n" +
                String.format("%-12s %-14s %-15s %-20s %-12s %-12s %-10s %-15s",
                "Id_Cita", "ID_Paciente", "Medico", "Especialidad", "Fecha", "Hora", "Costo", "Estado");
    }
}
