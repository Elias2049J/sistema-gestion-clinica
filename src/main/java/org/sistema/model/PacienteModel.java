package org.sistema.model;

import lombok.Getter;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.CrudInterface;
import org.sistema.interfaces.PersistenceInterface;
import org.sistema.persistencia.PersistenciaPaciente;

import java.util.List;

@Getter
public class PacienteModel implements CrudInterface<Paciente, Integer> {
    private PersistenceInterface<Paciente> persistenciaPaciente = new PersistenciaPaciente();


    @Override
    public boolean crear(Paciente objeto) {
        return false;
    }

    @Override
    public boolean update(Paciente objeto) {
        return false;
    }

    @Override
    public Paciente getById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<Paciente> findAll() {
        return List.of();
    }
}