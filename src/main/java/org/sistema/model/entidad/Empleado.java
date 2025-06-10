package org.sistema.model.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Empleado {
    private Integer idEmpleado;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String cargo;
}
