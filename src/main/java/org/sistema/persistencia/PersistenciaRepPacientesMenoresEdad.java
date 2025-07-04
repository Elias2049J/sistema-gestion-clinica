package org.sistema.persistencia;

public class PersistenciaRepPacientesMenoresEdad extends PersistenciaRepPac {
    protected String titulo = "LISTA PACIENTES MENORES DE EDAD";

    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/pacientesMenoresEdad.txt";
    }

    @Override
    protected String getCabecera() {
        return "|================================================"+titulo+"=======================================|\n"
                + columnas;
    }
}
