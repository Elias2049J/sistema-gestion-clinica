package org.sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sistema.entidad.Paciente;
import org.sistema.entidad.Cita;
import org.sistema.use_case.RepositoryUseCase;
import org.sistema.use_case.CrudUseCase;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudPacienteModel implements CrudUseCase<Paciente, Integer, String> {
    private RepositoryUseCase<Paciente, Integer> pacienteRepository;
    private RepositoryUseCase<Cita, Integer> citaRepository;

    @Override
    public boolean create(Paciente objeto) {
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
    public Paciente getByAttribute(String n) {
        for (Paciente p : pacienteRepository.findAll()) {
            if (p.getNombre() != null && p.getNombre().equalsIgnoreCase(n)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        for (Cita cita : new ArrayList<>(citaRepository.findAll())) {
            if (cita.getPaciente() != null && cita.getPaciente().getIdPaciente().equals(id)) {
                citaRepository.delete(cita.getIdCita());
            }
        }
        return pacienteRepository.delete(id);
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }
}