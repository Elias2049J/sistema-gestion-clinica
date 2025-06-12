package org.sistema.model;

import org.sistema.use_case.CrudUseCase;

public class CrudModel<T, ID> implements CrudUseCase<T, ID> {

    @Override
    public T consultarDatos(ID id) {
        return null;
    }

    @Override
    public T crearNuevo(ID id) {
        return null;
    }

    @Override
    public T actualizarDatos(ID id) {
        return null;
    }

    @Override
    public boolean eliminar(ID id) {
        return false;
    }
}
