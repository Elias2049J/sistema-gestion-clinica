package org.sistema.model;

import org.sistema.entidad.EvaluacionMedica;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenciaInterface;
import org.sistema.persistencia.PersistenciaHistorialClinico;
import org.sistema.repository.DataRepository;
import org.sistema.use_case.EvaluacionMedicaUseCase;
import org.sistema.use_case.HistorialClinicoUseCase;
import org.sistema.use_case.PacienteUseCase;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HistorialClinicoModel implements HistorialClinicoUseCase {
    private PersistenciaInterface<HistorialClinico> persistenciaHistorial = new PersistenciaHistorialClinico();
    private PacienteUseCase pacienteModel = new PacienteModel();
    private EvaluacionMedicaUseCase evaluacionModel = new EvaluacionMedicaModel();

    public HistorialClinicoModel(){
        try {
            persistenciaHistorial.llenarListaDesdeArchivo(DataRepository.getHistorialesClinicos());
        } catch (FileNotFoundException e) {
            System.out.println("Error al instanciar la lista de historiales clinicos desde el archivo en constructor de HistorialClinicoModel: s"+e.getMessage());
        }
    }

    @Override
    public boolean crearHistorialClinico(Integer idPaciente, List<EvaluacionMedica> evaluacionesMedicas, String antecedentes, String alergias, String observaciones, LocalDate fechaCreacion, String estado) {
        Integer idHistorial;
        if(DataRepository.getHistorialesClinicos().isEmpty()) {
            idHistorial = 1;
        } else {
            idHistorial = DataRepository.getHistorialesClinicos().getLast().getIdHistorial()+1;
        }

        Paciente paciente = pacienteModel.consultarPorID(idPaciente);
        List<EvaluacionMedica> evaluaciones;
        if (evaluacionesMedicas != null) {
            evaluaciones = evaluacionesMedicas;
        } else {
            evaluaciones = evaluacionModel.obtenerEvaluacionesPorHistorial(idHistorial);
        }
        if (evaluaciones == null) {
            evaluaciones = new ArrayList<>();
        }

        HistorialClinico nuevoHistorial = new HistorialClinico(idHistorial, paciente, evaluaciones, antecedentes, alergias, observaciones, fechaCreacion, estado);
        DataRepository.agregarHistorialClinico(nuevoHistorial);
        persistenciaHistorial.actualizarArchivo(DataRepository.getHistorialesClinicos());
        return true;
    }

    @Override
    //TODO
    public boolean actualizarHistorialClinico(Integer idHistorial, Integer idPaciente, List<EvaluacionMedica> evaluacionesMedicas, String antecedentes, String alergias, String observaciones, LocalDate fechaCreacion, String estado) {
        return false;
    }

    //obtiene el historial clínico por id
    @Override
    public HistorialClinico consultarPorPaciente(Integer idPaciente) {
        try {
            persistenciaHistorial.llenarListaDesdeArchivo(DataRepository.getHistorialesClinicos());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        for (HistorialClinico historialClinico: DataRepository.getHistorialesClinicos()) {
            if (historialClinico.getPaciente().getIdPaciente().equals(idPaciente)) {
                return historialClinico;
            }
        }
        return null;
    }

    @Override
    //TODO
    public boolean eliminarHistorialClinico(Integer id) {
        return false;
    }

    @Override
    //TODO
    public boolean guardarCambiosDesdeTabla(List<HistorialClinico> lista) {
        return false;
    }
}
