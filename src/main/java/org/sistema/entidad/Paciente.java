package org.sistema.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    private Integer idPaciente;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String dni;
    private String direccion;
    private String telefono;
    private String estado;
    private List<Cita> historialCitas;
}
