package org.sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;
import org.sistema.use_case.RepositoryUseCase;
import org.sistema.use_case.CrudUseCase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrudCitaModel implements CrudUseCase<Cita, Integer, String> {
    private RepositoryUseCase<Cita, Integer> citaRepository;
    private RepositoryUseCase<Paciente, Integer> pacienteRepository;

    @Override
    public boolean create(Cita objeto) {
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
        objeto.setPaciente(pacienteActual);
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
    public Cita getByAttribute(String s) {
        return null;
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
        List<Cita> citas = citaRepository.findAll();
        for (Cita cita : citas) {
            setDataPaciente(cita);
        }
        return citas;
    }

    private void setDataPaciente(Cita cita) {
        if (cita.getPaciente() != null && cita.getPaciente().getIdPaciente() != null) {
            Paciente pacienteCompleto = pacienteRepository.getById(cita.getPaciente().getIdPaciente());
            if (pacienteCompleto != null) {
                cita.setPaciente(pacienteCompleto);
            }
        }
    }
}
