package org.sistema.model;

import org.sistema.entidad.EvaluacionMedica;
import org.sistema.use_case.EvaluacionMedicaUseCase;

import java.util.List;

public class EvaluacionMedicaModel implements EvaluacionMedicaUseCase {
    @Override
    public boolean create(EvaluacionMedica evaluacion) {
        return false;
    }

    @Override
    public boolean update(EvaluacionMedica evaluacion) {
        return false;
    }

    @Override
    public EvaluacionMedica getById(Integer id) {
        return null;
    }

    @Override
    public List<EvaluacionMedica> getByHistoryId(Integer idHistorialClinico) {
        return List.of();
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean saveFromList(List<EvaluacionMedica> lista) {
        return false;
    }
}
