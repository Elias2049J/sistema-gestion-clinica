package org.sistema.persistencia;

public class PersistenciaRepPac3raEdad extends PersistenciaRepPac {
    protected String titulo = "PACIENTES DE LA TERCERA EDAD";

    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/pacientesTerceraEdad.txt";
    }

    @Override
    protected String getCabecera() {
        return "|================================================"+titulo+"=======================================|\n"
                + columnas;
    }
}
