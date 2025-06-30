package org.sistema.persistencia;

public class PersistenciaRepCita3Meses extends PersistenciaCita{
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/citas_menores_3_meses.txt";
    }

    @Override
    protected String getCabecera() {
        return "|===============================CITAS MENORES A 3 MESES===============================\n" +
                String.format("%-12s %-30s %-12s %-15s %-15s %-10s %-30s %-15s",
                "Id_Cita", "ID_Paciente", "Medico", "Especialidad", "Fecha", "Hora", "Costo", "Estado");
    }
}
