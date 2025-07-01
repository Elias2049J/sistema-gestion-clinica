package org.sistema.persistencia;

public class PersistenciaRepPacientesMenoresEdad extends PersistenciaPaciente {

    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/reportes/pacientesMenoresEdad.txt";
    }

    @Override
    protected String getCabecera() {
        return "|===================================LISTA PACIENTES MENORES DE EDAD====================================|\n" +
                String.format("%-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                        "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO");
    }
}
