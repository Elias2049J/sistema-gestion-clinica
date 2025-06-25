package org.sistema.model;

import org.sistema.entidad.Cita;
import org.sistema.interfaces.CrudInterface;
import org.sistema.interfaces.PersistenceInterface;
import org.sistema.persistencia.PersistenciaCita;

import java.util.List;

public class CitaModel implements CrudInterface<Cita, Integer> {
    private PersistenceInterface<Cita> persistenciaCita = new PersistenciaCita();

    @Override
    public boolean crear(Cita objeto) {
        return false;
    }

    @Override
    public boolean update(Cita objeto) {
        return false;
    }

    @Override
    public Cita getById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public List<Cita> findAll() {
        return List.of();
    }
}
