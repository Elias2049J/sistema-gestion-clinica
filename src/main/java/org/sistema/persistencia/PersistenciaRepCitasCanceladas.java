package org.sistema.persistencia;

public class PersistenciaRepCitasCanceladas extends PersistenciaRepCita{
    protected String titulo = "CITAS CANCELADAS";
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/citas_canceladas.txt";
    }

    @Override
    protected String getCabecera() {
        return "================================"+titulo+"=================================\n"
                + cabeceraCol;
    }
}
