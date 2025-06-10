package org.sistema.model.entidad;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@NoArgsConstructor
public class HistorialClinico extends Historial{
    private List<Diagnostico> diagnosticos;
    private List<Tratamiento> tratamientos;
    private List<Consulta> consultas;
    private String antecedentes;
    private String alergias;
    private String observaciones;

    public HistorialClinico(
            Integer idHistorial,
            Paciente paciente,
            LocalDate fechaCreacion,
            String estado,
            List<Diagnostico> diagnosticos,
            List<Tratamiento> tratamientos,
            List<Consulta> consultas,
            String antecedentes,
            String alergias,
            String observaciones
    ) {
        super(idHistorial, paciente, fechaCreacion, estado);
        this.diagnosticos = diagnosticos;
        this.tratamientos = tratamientos;
        this.consultas = consultas;
        this.antecedentes = antecedentes;
        this.alergias = alergias;
        this.observaciones = observaciones;
    }
}

