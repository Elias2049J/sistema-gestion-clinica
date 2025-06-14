package org.sistema.use_case;

import org.sistema.entidad.Medico;

import java.util.List;

public interface MedicoUseCase {
    boolean crearMedico(String nombre, String apellido, Integer edad, String cargo, String especialidad);
    boolean actualizarMedico(Integer idEmpleado, String nombre, String apellido, Integer edad, String cargo, String especialidad);
    Medico consultarPorID(Integer id);
    List<Medico> obtenerTodosLosMedicos();
    boolean eliminarMedico(Integer id);
    boolean guardarCambiosDesdeTabla(List<Medico> lista);
}
