package org.sistema.entidad;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Medico extends Empleado{
    private List<Cita> citas;
    private String especialidad;
    public Medico(Integer idEmpleado, String nombre, String apellido, Integer edad, String cargo,  List<Cita> citas, String especialidad){
        super(idEmpleado, nombre, apellido, edad, cargo);
        this.citas = citas;
        this.especialidad = especialidad;
    }
}
