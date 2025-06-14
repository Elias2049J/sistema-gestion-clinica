package org.sistema.use_case;

import org.sistema.entidad.Cita;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface CitaUseCase {
    boolean crearCita(Integer idPaciente, Integer idMedico, String especialidad, String motivo, LocalDate fecha, LocalTime hora, double costo);
    boolean actualizarCita(Cita cita);
    Cita consultarPorID(Integer id);
    List<Cita> obtenerCitasPorPaciente(Integer idPaciente);
    List<Cita> obtenerCitasPorMedico(Integer idMedico);
    boolean eliminarCita(Integer id);
    boolean guardarCambiosDesdeTabla(List<Cita> lista);
}
