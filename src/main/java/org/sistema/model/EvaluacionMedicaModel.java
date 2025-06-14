package org.sistema.model;

import org.sistema.entidad.EvaluacionMedica;
import org.sistema.use_case.EvaluacionMedicaUseCase;

import java.util.List;

public class EvaluacionMedicaModel implements EvaluacionMedicaUseCase {
    @Override
    public boolean crearEvaluacionMedica(EvaluacionMedica evaluacion) {
        return false;
    }

    @Override
    public boolean actualizarEvaluacionMedica(EvaluacionMedica evaluacion) {
        return false;
    }

    @Override
    public EvaluacionMedica consultarPorID(Integer id) {
        return null;
    }

    @Override
    public List<EvaluacionMedica> obtenerEvaluacionesPorHistorial(Integer idHistorialClinico) {

        return List.of();
    }

    @Override
    public boolean eliminarEvaluacionMedica(Integer id) {
        return false;
    }

    @Override
    public boolean guardarCambiosDesdeTabla(List<EvaluacionMedica> lista) {
        return false;
    }
}
