package org.sistema.use_case;

import java.util.List;

public interface CrudUseCase<T, ID, A> {
    boolean create(T objeto);
    boolean update(T objeto);
    T getById(ID id);
    T getByAttribute(A a);
    boolean delete(ID id);
    List<T> findAll();
}
