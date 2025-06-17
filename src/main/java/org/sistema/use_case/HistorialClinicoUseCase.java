package org.sistema.use_case;

import org.sistema.entidad.EvaluacionMedica;
import org.sistema.entidad.HistorialClinico;

import java.util.List;

public interface HistorialClinicoUseCase {
    boolean create(Integer idPaciente, List<EvaluacionMedica> evaluacionesMedicas, String antecedentes, String alergias, String observaciones, java.time.LocalDate fechaCreacion, String estado);
    boolean update(Integer idHistorial, Integer idPaciente, List<EvaluacionMedica> evaluacionesMedicas, String antecedentes, String alergias, String observaciones, java.time.LocalDate fechaCreacion, String estado);
    HistorialClinico getByPatientID(Integer idPaciente);
    boolean delete(Integer idPaciente);
    boolean saveFromList(List<HistorialClinico> lista);
}
