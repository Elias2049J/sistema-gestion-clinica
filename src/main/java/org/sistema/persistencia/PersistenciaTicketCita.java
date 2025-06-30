package org.sistema.persistencia;

import org.sistema.entidad.Cita;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PersistenciaTicketCita {
    public boolean imprimirTicket(Cita c){
        Integer contador = getNumTicket();
        File archivo = new File("src/main/java/org/sistema/data/tickets/ticket_"+contador+".txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            pw.println("--------------------");
            pw.println("Ticket de Cita Nro. "+ contador);
            pw.println("Dni del Paciente: " + c.getPaciente().getDni());
            pw.println("Nombres: " + c.getPaciente().getNombre());
            pw.println("Apellidos: " + c.getPaciente().getApellido());
            pw.println("Médico: " + c.getMedico());
            pw.println("Especialidad: " + c.getEspecialidad());
            pw.println("Fecha: " + c.getFecha());
            pw.println("Hora: " + c.getHora());
            pw.println("Costo: " + c.getCosto());
            pw.println("--------------------");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    private int getNumTicket() {
        File carpeta = new File("src/main/java/org/sistema/data/tickets/");
        int max = 0;
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.matches("ticket_\\d+\\.txt"));
            if (archivos != null) {
                for (File a : archivos) {
                    String nombre = a.getName();
                    String numero = nombre.replaceAll("ticket_(\\d+)\\.txt", "$1");
                    try {
                        int num = Integer.parseInt(numero);
                        if (num > max) max = num;
                    } catch (NumberFormatException ignored) {}
                }
            }
        }
        return max + 1;
    }
}
