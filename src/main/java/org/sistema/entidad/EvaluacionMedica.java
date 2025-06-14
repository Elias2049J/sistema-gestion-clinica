package org.sistema.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluacionMedica {
    private Integer idEvaluacion;
    private HistorialClinico historialClinico;
    private String diagnostico;
    private String tratamiento;
    private LocalDate fecha;
    private LocalTime hora;
}
