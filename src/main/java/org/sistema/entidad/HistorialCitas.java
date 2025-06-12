package org.sistema.entidad;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class HistorialCitas extends Historial{
    private List<Cita> citas;

    public HistorialCitas(
            Integer idHistorial,
            Paciente paciente,
            LocalDate fechaCreacion,
            String estado,
            List<Cita> citas
    ) {
        super(idHistorial, paciente, fechaCreacion, estado);
        this.citas = citas;
    }
}
