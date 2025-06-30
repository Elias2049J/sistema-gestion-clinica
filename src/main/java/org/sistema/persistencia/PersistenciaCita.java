package org.sistema.persistencia;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class PersistenciaCita extends Persistencia<Cita> {
    @Override
    protected String getFilePath() {
        return "src/main/java/org/sistema/data/pacientes/citas.txt";
    }

    @Override
    protected Cita parsearLinea(String linea) {
        String[] campos = linea.trim().split("\\s{2,}");
        if (campos.length < 7) return null;

        try {
            Integer idCita = Integer.parseInt(campos[0].trim());
            Integer idPaciente = Integer.parseInt(campos[1].trim());
            String medico = campos[2].trim();
            String especialidad = campos[3].trim();
            LocalDate fecha = LocalDate.parse(campos[4].trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime hora = LocalTime.parse(campos[5].trim(), DateTimeFormatter.ofPattern("HH:mm"));
            double costo = Double.parseDouble(campos[6].trim());
            String estado = campos[7].trim();

            Cita cita = new Cita();
            cita.setIdCita(idCita);
            Paciente paciente = new Paciente();
            paciente.setIdPaciente(idPaciente);
            cita.setPaciente(paciente);
            cita.setMedico(medico);
            cita.setEspecialidad(especialidad);
            cita.setFecha(fecha);
            cita.setHora(hora);
            cita.setCosto(costo);
            cita.setEstado(estado);

            return cita;
        } catch (Exception e) {
            System.out.println("Error al parsear línea: " + linea);
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    protected String getCabecera() {
        return String.format("%-12s %-14s %-15s %-20s %-12s %-12s %-10s %-15s",
                "Id_Cita", "ID_Paciente", "Medico", "Especialidad", "Fecha", "Hora", "Costo", "Estado");
    }

    @Override
    protected String formatObjeto(Cita cita) {
        return String.format("%-12s %-14s %-15s %-20s %-12s %-12s %-10s %-15s",
                cita.getIdCita(),
                (cita.getPaciente() != null ? cita.getPaciente().getIdPaciente() : ""),
                cita.getMedico(),
                cita.getEspecialidad(),
                (cita.getFecha() != null ? cita.getFecha().toString() : ""),
                (cita.getHora() != null ? cita.getHora().toString() : ""),
                cita.getCosto(),
                cita.getEstado());
    }
}
