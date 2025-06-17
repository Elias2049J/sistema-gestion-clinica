package org.sistema.model;

import org.sistema.entidad.EvaluacionMedica;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenceInterface;
import org.sistema.persistencia.PersistenceHistorialClinico;
import org.sistema.repository.DataRepository;
import org.sistema.use_case.EvaluacionMedicaUseCase;
import org.sistema.use_case.HistorialClinicoUseCase;
import org.sistema.use_case.PacienteUseCase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistorialClinicoModel implements HistorialClinicoUseCase {
    private PersistenceInterface<HistorialClinico> persistenciaHistorial = new PersistenceHistorialClinico();
    private PacienteUseCase pacienteModel = new PacienteModel();
    private EvaluacionMedicaUseCase evaluacionModel = new EvaluacionMedicaModel();

    public HistorialClinicoModel(){
        persistenciaHistorial.loadListFromFile(DataRepository.getHistorialesClinicos());
    }

    @Override
    public boolean create(Integer idPaciente, List<EvaluacionMedica> evaluacionesMedicas, String antecedentes, String alergias, String observaciones, LocalDate fechaCreacion, String estado) {
        Integer idHistorial;
        if(DataRepository.getHistorialesClinicos().isEmpty()) {
            idHistorial = 1;
        } else {
            idHistorial = DataRepository.getHistorialesClinicos().getLast().getIdHistorial()+1;
        }

        Paciente paciente = pacienteModel.getById(idPaciente);
        List<EvaluacionMedica> evaluaciones;
        if (evaluacionesMedicas != null) {
            evaluaciones = evaluacionesMedicas;
        } else {
            evaluaciones = evaluacionModel.getByHistoryId(idHistorial);
        }
        if (evaluaciones == null) {
            evaluaciones = new ArrayList<>();
        }

        HistorialClinico nuevoHistorial = new HistorialClinico(idHistorial, paciente, evaluaciones, antecedentes, alergias, observaciones, fechaCreacion, estado);
        DataRepository.agregarHistorialClinico(nuevoHistorial);
        persistenciaHistorial.updateFileFromList(DataRepository.getHistorialesClinicos());
        return true;
    }

    @Override
    //TODO
    public boolean update(Integer idHistorial, Integer idPaciente, List<EvaluacionMedica> evaluacionesMedicas, String antecedentes, String alergias, String observaciones, LocalDate fechaCreacion, String estado) {
        return false;
    }

    //obtiene el historial clínico por id
    @Override
    public HistorialClinico getByPatientID(Integer idPaciente) {
        persistenciaHistorial.loadListFromFile(DataRepository.getHistorialesClinicos());
        for (HistorialClinico historialClinico: DataRepository.getHistorialesClinicos()) {
            if (historialClinico.getPaciente().getIdPaciente().equals(idPaciente)) {
                return historialClinico;
            }
        }
        return null;
    }

    @Override
    //TODO
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    //TODO
    public boolean saveFromList(List<HistorialClinico> lista) {
        return false;
    }
}
