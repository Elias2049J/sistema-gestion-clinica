package org.sistema.persistencia;

import org.sistema.entidad.Paciente;
import org.sistema.interfaces.IPersistenciaReportes;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class PersistenciaRepPac implements IPersistenciaReportes<Paciente> {
    @Override
    public boolean imprimir(List<Paciente> lista) {
        try {
            File archivo = new File("src/main/java/org/sistema/data/reportes/listaPacientes.txt");
            PrintWriter wr = new PrintWriter(new FileWriter(archivo));
            wr.println("===============================LISTA COMPLETA DE PACIENTES===============================");
            wr.printf("%-12s %-14s %-14s %-5s %-10s %-30s %-12s %-14s%n",
                    "ID", "NOMBRE", "APELLIDO", "EDAD", "DNI", "DIRECCION", "TELEFONO", "ESTADO");
            for (Paciente p : lista) {
                wr.printf("%-12s %-14s %-14s %-5s %-10s %-30s %-12s %-14s%n",
                        p.getIdPaciente(),
                        p.getNombre(),
                        p.getApellido(),
                        p.getEdad(),
                        p.getDni(),
                        p.getDireccion(),
                        p.getTelefono(),
                        p.getEstado()
                );
            }
            wr.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
