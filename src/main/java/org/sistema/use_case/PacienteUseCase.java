package org.sistema.use_case;

import org.sistema.entidad.Paciente;

import java.util.List;

public interface PacienteUseCase{
    boolean create(String nombre, String apellido, Integer edad, String dni, String direccion, String telefono);
    boolean update(Integer id, String nombre, String apellido, String dni, String direccion, String telefono, String estado);
    boolean saveFromList(List<Paciente> lista);
    Paciente getById(Integer id);
    boolean delete(Integer id);
}
