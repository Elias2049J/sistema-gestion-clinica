package org.sistema.persistencia;

import org.sistema.entidad.Paciente;

public class PersistenciaRepPac extends PersistenciaPaciente {

    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/listaPacientes.txt";
    }

    @Override
    protected Paciente parsearLinea(String linea) {
        return null;
    }

    @Override
    protected String getCabecera() {
        return "|================================================LISTA COMPLETA DE PACIENTES=======================================|\n" +
                String.format("%-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                        "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO");
    }
}
