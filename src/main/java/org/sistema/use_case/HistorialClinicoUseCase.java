package org.sistema.use_case;

import org.sistema.entidad.EvaluacionMedica;
import org.sistema.entidad.HistorialClinico;

import java.util.List;

public interface HistorialClinicoUseCase {
    boolean crearHistorialClinico(Integer idPaciente, List<EvaluacionMedica> evaluacionesMedicas, String antecedentes, String alergias, String observaciones, java.time.LocalDate fechaCreacion, String estado);
    boolean actualizarHistorialClinico(Integer idHistorial, Integer idPaciente, List<EvaluacionMedica> evaluacionesMedicas, String antecedentes, String alergias, String observaciones, java.time.LocalDate fechaCreacion, String estado);
    HistorialClinico consultarPorPaciente(Integer idPaciente);
    boolean eliminarHistorialClinico(Integer idPaciente);
    boolean guardarCambiosDesdeTabla(List<HistorialClinico> lista);
}
