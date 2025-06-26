package org.sistema.persistencia;

import org.sistema.entidad.Cita;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PersistenciaTicketCita {
    public boolean imprimirTicket(Cita c){
        Integer contador = 0;
        File archivo = new File("src/main/java/org/sistema/data/tickets/ticket_"+contador+".txt");
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            pw.println("--------------------");
            pw.println("Ticket de Cita Nro."+ contador);
            pw.println("Dni del Paciente: " + c.getPaciente().getDni());
            pw.println("Nombres: " + c.getPaciente().getNombre());
            pw.println("Apellidos: " + c.getPaciente().getApellido());
            pw.println("Médico: " + c.getMedico());
            pw.println("Especialidad: " + c.getEspecialidad());
            pw.println("Fecha : " + c.getFecha());
            pw.println("Hora" + c.getHora());
            pw.println("Costo: " + c.getCosto());
            pw.println("--------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
