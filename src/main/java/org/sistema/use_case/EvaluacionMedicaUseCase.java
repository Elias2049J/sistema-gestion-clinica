package org.sistema.use_case;

import org.sistema.entidad.EvaluacionMedica;

import java.util.List;

public interface EvaluacionMedicaUseCase {
    boolean create(EvaluacionMedica evaluacion);
    boolean update(EvaluacionMedica evaluacion);
    EvaluacionMedica getById(Integer id);
    List<EvaluacionMedica> getByHistoryId(Integer idHistorialClinico);
    boolean delete(Integer id);
    boolean saveFromList(List<EvaluacionMedica> lista);
}
