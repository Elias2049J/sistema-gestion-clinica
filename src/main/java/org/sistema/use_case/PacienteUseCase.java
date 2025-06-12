package org.sistema.use_case;

import org.sistema.model.entidad.HistorialCitas;
import org.sistema.model.entidad.HistorialClinico;
import org.sistema.model.entidad.Paciente;

public interface PacienteUseCase extends CrudUseCase<Paciente, Integer>{
    boolean registrarDatosPersonales();
    HistorialClinico obtenerHistorialClinico(Integer idPaciente);
    HistorialCitas obtenerHistorialCitas(Integer idPaciente);
}
