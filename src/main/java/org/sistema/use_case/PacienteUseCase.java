package org.sistema.use_case;

import org.sistema.model.entidad.HistorialCitas;
import org.sistema.model.entidad.HistorialClinico;

public interface PacienteUseCase {
    boolean registrarDatosPersonales();
    boolean registrarPaciente(Integer idPaciente);
    boolean actualizarDatosPaciente(Integer idPaciente);
    boolean eliminarPaciente(Integer idPaciente);
    HistorialClinico obtenerHistorialClinico(Integer idPaciente);
    HistorialCitas obtenerHistorialCitas(Integer idPaciente);
}
