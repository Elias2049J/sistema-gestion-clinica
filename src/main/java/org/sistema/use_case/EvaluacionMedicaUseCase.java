package org.sistema.use_case;

import org.sistema.entidad.EvaluacionMedica;

import java.util.List;

public interface EvaluacionMedicaUseCase {
    boolean crearEvaluacionMedica(EvaluacionMedica evaluacion);
    boolean actualizarEvaluacionMedica(EvaluacionMedica evaluacion);
    EvaluacionMedica consultarPorID(Integer id);
    List<EvaluacionMedica> obtenerEvaluacionesPorHistorial(Integer idHistorialClinico);
    boolean eliminarEvaluacionMedica(Integer id);
    boolean guardarCambiosDesdeTabla(List<EvaluacionMedica> lista);
}
