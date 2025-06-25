package org.sistema.repository;

import org.sistema.interfaces.PersistenceInterface;
import org.sistema.interfaces.RepositoryInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Repository<T, ID> implements RepositoryInterface<T, ID> {
    private List<T> elementos = new ArrayList<>();
    private final Function<T, ID> idExtractor;
    private final PersistenceInterface<T> persistencia;

    public Repository(PersistenceInterface<T> persistencia, Function<T, ID> idExtractor) {
        this.persistencia = persistencia;
        this.idExtractor = idExtractor;
        this.cargarDatos();
    }

    @Override
    public ID getId(T entity) {
        return idExtractor.apply(entity);
    }

    @Override
    public boolean save(T entity) {
        elementos.add(entity);
        return persistencia.exportarLista(this.elementos);
    }

    @Override
    public T getById(ID id) {
        for (T e : elementos) {
            if (getId(e).equals(id)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public boolean delete(ID id) {
        return elementos.removeIf(e -> getId(e).equals(id))
                && persistencia.exportarLista(this.elementos);
    }

    @Override
    public List<T> findAll() {
        return elementos;
    }

    @Override
    public boolean update(T entity) {
        ID id = getId(entity);
        if (id == null) return false;
        T e = getById(id);
        if (e == null) return false;
        if (!delete(id)) return false;
        return save(entity) && persistencia.exportarLista(this.elementos);
    }

    @Override
    public boolean cargarDatos() {
        return persistencia.importarLista(this.elementos);
    }
}
