package org.sistema.entidad;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Medico extends Empleado{
    public Medico(Integer idEmpleado, String nombre, String apellido, Integer edad, String cargo){
        super(idEmpleado, nombre, apellido, edad, cargo);
    }
}
