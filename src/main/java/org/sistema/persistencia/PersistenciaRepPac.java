package org.sistema.persistencia;

public class PersistenciaRepPac extends PersistenciaPaciente {
    protected String titulo = "LISTA COMPLETA DE PACIENTES";
    protected String columnas =
            String.format("%-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                    "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO");

    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/listaPacientes.txt";
    }

    @Override
    protected String getCabecera() {
        return "|================================================"+titulo+"=======================================|\n"
                + columnas;
    }
}
