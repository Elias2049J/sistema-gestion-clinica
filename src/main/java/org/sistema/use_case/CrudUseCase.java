package org.sistema.use_case;

public interface CrudUseCase<T, ID> {
    T consultarDatos(ID id);
    T crearNuevo(ID id);
    T actualizarDatos(ID id);
    boolean eliminar(ID id);
}
