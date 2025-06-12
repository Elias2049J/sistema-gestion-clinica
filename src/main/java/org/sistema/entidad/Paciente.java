package org.sistema.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {
    private Integer idPaciente;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private String telefono;
    private String estado;
}
