package org.sistema.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    private Integer idCita;
    private Paciente paciente;
    private String medico;
    private String especialidad;
    private LocalDate fecha;
    private LocalTime hora;
    private double costo;
    private String estado;
}
