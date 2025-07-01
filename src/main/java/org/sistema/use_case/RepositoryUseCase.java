package org.sistema.use_case;

import java.util.List;

public interface RepositoryUseCase<T, ID> {
    boolean save(T entity);
    boolean update(T entity);
    T getById(ID id);
    ID getId(T entity);
    boolean delete(ID id);
    List<T> findAll();
    boolean cargarDatos();
}