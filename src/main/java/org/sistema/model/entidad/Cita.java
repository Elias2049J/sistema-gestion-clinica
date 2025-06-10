package org.sistema.model.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cita {
    private Integer idCita;
    private String especialidad;
    private String doctor;
    private Paciente paciente;
    private double costo;
    private LocalDateTime fecha;
    private String estado;
}
