package org.sistema.use_case;

import org.sistema.entidad.HistorialCitas;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;

import java.io.FileNotFoundException;
import java.util.List;

public interface PacienteUseCase{
    boolean crearPaciente(String nombre, String apellido, Integer edad, String dni, String direccion, String telefono) throws FileNotFoundException;
    boolean actualizarPaciente(Integer id, String nombre, String apellido, String dni, String direccion, String telefono, String estado) throws FileNotFoundException;
    HistorialClinico obtenerHistorialClinico(Integer idPaciente);
    HistorialCitas obtenerHistorialCitas(Integer idPaciente);
    boolean guardarCambiosDesdeTabla(List<Paciente> lista);
    Paciente consultarPorID(Integer id) throws FileNotFoundException;
    boolean eliminar(Integer id);
}
