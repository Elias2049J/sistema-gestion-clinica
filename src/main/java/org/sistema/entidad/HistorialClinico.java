package org.sistema.entidad;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistorialClinico {
    private Integer idHistorial;
    private Paciente paciente;
    private List<EvaluacionMedica> evaluacionesMedicas;
    private String antecedentes;
    private String alergias;
    private String observaciones;
    private LocalDate fechaCreacion;
    private String estado;
}
