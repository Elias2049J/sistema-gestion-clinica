package org.sistema.model.entidad;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Historial {
    private Integer idHistorial;
    private Paciente paciente;
    private LocalDate fechaCreacion;
    private String estado;
}
