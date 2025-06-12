package org.sistema.use_case;

import org.sistema.entidad.HistorialCitas;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;

public interface PacienteUseCase extends CrudUseCase<Paciente, Integer>{
    boolean registrarDatosPersonales();
    HistorialClinico obtenerHistorialClinico(Integer idPaciente);
    HistorialCitas obtenerHistorialCitas(Integer idPaciente);
}
