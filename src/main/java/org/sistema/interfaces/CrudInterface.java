package org.sistema.interfaces;

public interface CrudInterface<T, ID> {
    boolean crear(T objeto);
    boolean update(T objeto);
    T getById(ID id);
    boolean delete(ID id);
    java.util.List<T> findAll();
}
