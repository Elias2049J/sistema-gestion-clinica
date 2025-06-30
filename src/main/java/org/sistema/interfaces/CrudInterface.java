package org.sistema.interfaces;

import java.util.List;

public interface CrudInterface<T, ID> {
    boolean create(T objeto);
    boolean update(T objeto);
    T getById(ID id);
    boolean delete(ID id);
    List<T> findAll();
}
