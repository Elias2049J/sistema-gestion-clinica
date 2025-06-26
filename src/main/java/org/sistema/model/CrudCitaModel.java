package org.sistema.model;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.RepositoryInterface;
import org.sistema.persistencia.PersistenciaCita;
import org.sistema.persistencia.PersistenciaPaciente;
import org.sistema.repository.Repository;
import org.sistema.interfaces.CrudInterface;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class CrudCitaModel implements CrudInterface<Cita, Integer> {
    private RepositoryInterface<Cita, Integer> citaRepository;
    private RepositoryInterface<Paciente, Integer> pacienteRepository;

    public CrudCitaModel() {
        this.citaRepository = new Repository<>(new PersistenciaCita(), Cita::getIdCita);
        this.pacienteRepository = new Repository<>(new PersistenciaPaciente(), Paciente::getIdPaciente);
    }

    @Override
    public boolean crear(Cita objeto) {
        Integer idCita;
        Paciente p = objeto.getPaciente();

        if (p == null || p.getIdPaciente() == null) {
            return false;
        }

        Paciente pacienteActual = pacienteRepository.getById(p.getIdPaciente());
        if (pacienteActual == null) {
            return false;
        }

        List<Cita> citas;
        if (pacienteActual.getHistorialCitas() == null || pacienteActual.getHistorialCitas().isEmpty()) {
            citas = new ArrayList<>();
            citas.add(objeto);
            pacienteActual.setHistorialCitas(citas);
        } else {
            pacienteActual.getHistorialCitas().add(objeto);
        }

        if (citaRepository.findAll().isEmpty()) {
            idCita = 1;
        } else {
            idCita = citaRepository.findAll().getLast().getIdCita() + 1;
        }

        objeto.setIdCita(idCita);
        objeto.setFecha(objeto.getFecha() != null ? objeto.getFecha() : LocalDate.now());
        objeto.setHora(objeto.getHora() != null ? objeto.getHora() : LocalTime.now());
        objeto.setEstado("PROGRAMADA");

        pacienteRepository.update(pacienteActual);
        return citaRepository.save(objeto);
    }

    @Override
    public boolean update(Cita objeto) {
        return citaRepository.update(objeto);
    }

    @Override
    public Cita getById(Integer id) {
        return citaRepository.getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        Cita cita = citaRepository.getById(id);
        if (cita == null) {
            return false;
        }

        Paciente paciente = cita.getPaciente();
        if (paciente != null && paciente.getHistorialCitas() != null) {
            Paciente pacienteActual = pacienteRepository.getById(paciente.getIdPaciente());
            if (pacienteActual != null) {
                pacienteActual.getHistorialCitas().removeIf(c -> c.getIdCita().equals(id));
                pacienteRepository.update(pacienteActual);
            }
        }

        return citaRepository.delete(id);
    }

    @Override
    public List<Cita> findAll() {
        return citaRepository.findAll();
    }
}
