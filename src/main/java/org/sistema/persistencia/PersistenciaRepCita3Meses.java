package org.sistema.persistencia;

public class PersistenciaRepCita3Meses extends PersistenciaRepCita{
    protected String titulo = "CITAS DE LOS ULTIMOS 3 MESES";
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/citas_menores_3_meses.txt";
    }

    @Override
    protected String getCabecera() {
        return "================================"+titulo+"================================="
                + cabeceraCol;
    }
}
