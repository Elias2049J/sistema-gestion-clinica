package org.sistema.model;

import lombok.Data;
import org.sistema.entidad.Paciente;
import org.sistema.entidad.Cita;
import org.sistema.interfaces.RepositoryInterface;
import org.sistema.persistencia.PersistenciaPaciente;
import org.sistema.persistencia.PersistenciaCita;
import org.sistema.repository.Repository;
import org.sistema.interfaces.CrudInterface;

import java.util.ArrayList;
import java.util.List;
@Data
public class CrudPacienteModel implements CrudInterface<Paciente, Integer> {
    private RepositoryInterface<Paciente, Integer> pacienteRepository;
    private RepositoryInterface<Cita, Integer> citaRepository;

    public CrudPacienteModel() {
        this.pacienteRepository = new Repository<>(new PersistenciaPaciente(), Paciente::getIdPaciente);
        this.citaRepository = new Repository<>(new PersistenciaCita(), Cita::getIdCita);
    }

    @Override
    public boolean crear(Paciente objeto) {
        Integer idPaciente;
        if (pacienteRepository.findAll().isEmpty()) {
            idPaciente = 1;
        } else idPaciente = pacienteRepository.findAll().getLast().getIdPaciente()+1;
        objeto.setIdPaciente(idPaciente);
        objeto.setEstado("ACTIVO");
        return pacienteRepository.save(objeto);
    }

    @Override
    public boolean update(Paciente objeto) {
        return pacienteRepository.update(objeto);
    }

    @Override
    public Paciente getById(Integer id) {
        return pacienteRepository.getById(id);
    }

    @Override
    public boolean delete(Integer id) {
        List<Integer> lista = new ArrayList<>();
        for (Cita c : citaRepository.findAll()) {
            if (c.getPaciente() != null && c.getPaciente().getIdPaciente() != null &&
                    c.getPaciente().getIdPaciente().equals(id)) {
                lista.add(c.getIdCita());
            }
        }
        for (Integer idCita : lista) {
            citaRepository.delete(idCita);
        }
        return pacienteRepository.delete(id);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
}