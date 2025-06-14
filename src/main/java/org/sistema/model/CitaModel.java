package org.sistema.model;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Medico;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenciaInterface;
import org.sistema.persistencia.PersistenciaCita;
import org.sistema.repository.DataRepository;
import org.sistema.use_case.CitaUseCase;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CitaModel implements CitaUseCase {
    private PersistenciaInterface<Cita> persistenciaCita = new PersistenciaCita();

    @Override
    // crea una nueva
    public boolean crearCita(Integer idPaciente, Integer idMedico, String especialidad, String motivo, LocalDate fecha, LocalTime hora, double costo) {
        Integer idCita;
        if (DataRepository.getCitas().isEmpty()) {
            idCita = 1;
        } else {
            idCita = DataRepository.getCitas().getLast().getIdCita() + 1;
        }
        // buscar paciente por id
        Paciente paciente = null;
        for (Paciente p : DataRepository.getPacientes()) {
            if (p.getIdPaciente().equals(idPaciente)) {
                paciente = p;
                break;
            }
        }
        // buscar medico por id
        Medico medico = null;
        for (Medico m : DataRepository.getMedicos()) {
            if (m.getIdEmpleado().equals(idMedico)) {
                medico = m;
                break;
            }
        }
        //si no existen ni el paciente o el medico, retorna false
        if (paciente == null || medico == null) {
            return false;
        }
        especialidad = medico.getEspecialidad();
        String estado = "Programada";
        Cita nuevaCita = new Cita(idCita, paciente, medico, especialidad, motivo, fecha, hora, costo, estado);

        // agregar cita a la lista de citas del medico
        if (medico.getCitas().isEmpty()) {
            medico.setCitas(new ArrayList<>());
        }
        medico.getCitas().add(nuevaCita);

        // agregar cita a la lista de citas del paciente
        if (paciente.getCitas().isEmpty()) {
            paciente.setCitas(new ArrayList<>());
        }
        paciente.getCitas().add(nuevaCita);

        DataRepository.agregarCita(nuevaCita);
        persistenciaCita.actualizarArchivo(DataRepository.getCitas());
        try {
            persistenciaCita.llenarListaDesdeArchivo(DataRepository.getCitas());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        //si sale bien retorna true
        return true;
    }

    @Override
    public boolean actualizarCita(Cita cita) {
        return false;
    }

    @Override
    public Cita consultarPorID(Integer id) {
        return null;
    }

    @Override
    public List<Cita> obtenerCitasPorPaciente(Integer idPaciente) {
        for (Paciente paciente: DataRepository.getPacientes()) {
            if (paciente.getIdPaciente().equals(idPaciente)) {
                return paciente.getCitas();
            }
        }
        return null;
    }

    @Override
    public List<Cita> obtenerCitasPorMedico(Integer idMedico) {
        for (Medico m: DataRepository.getMedicos()) {
            if(m.getIdEmpleado().equals(idMedico)) {
                return m.getCitas();
            }
        }
        return null;
    }

    @Override
    public boolean eliminarCita(Integer id) {
        return false;
    }

    @Override
    public boolean guardarCambiosDesdeTabla(List<Cita> lista) {
        return false;
    }
}
