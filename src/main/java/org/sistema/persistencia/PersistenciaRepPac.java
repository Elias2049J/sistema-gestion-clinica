package org.sistema.persistencia;

import org.sistema.entidad.Paciente;

import java.io.*;
import java.util.List;

public class PersistenciaRepPac extends Persistencia<Paciente> {

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
        return "|===============================LISTA COMPLETA DE PACIENTES===============================\n" +
                String.format("| %-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                        "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO");
    }

    @Override
    protected String formatObjeto(Paciente p) {
        return String.format("| %-5d %-10s %-10s %-7d %-10s %-20s %-10s %-6s",
                p.getIdPaciente(),
                p.getNombre() != null ? p.getNombre() : "",
                p.getApellido() != null ? p.getApellido() : "",
                p.getEdad() != null ? p.getEdad() : 0,
                p.getDni() != null ? p.getDni() : "",
                p.getDireccion() != null ? p.getDireccion() : "",
                p.getTelefono() != null ? p.getTelefono() : "",
                p.getEstado() != null ? p.getEstado() : ""
        );
    }

    public boolean exportarReporteTerceraEdad(List<Paciente> lista) {
        try {
            File archivo = new File("src/main/java/org/sistema/data/reportes/pacientesTerceraEdad.txt");
            PrintWriter wr = new PrintWriter(new FileWriter(archivo));

            wr.println("|===============================PACIENTES TERCERA EDAD===============================");
            wr.println(String.format("| %-10s %-15s %-15s %-6s %-12s %-30s %-12s %-10s",
                    "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO"));

            for (Paciente p : lista) {
                if (p.getEdad() != null && p.getEdad() >= 60) {
                    wr.println(formatObjeto(p));
                }
            }
            wr.close();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
