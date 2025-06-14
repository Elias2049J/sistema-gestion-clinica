package org.sistema.model;

import org.sistema.entidad.Medico;
import org.sistema.repository.DataRepository;
import org.sistema.use_case.MedicoUseCase;

import java.util.List;

public class MedicoModel implements MedicoUseCase {
    @Override
    public boolean crearMedico(String nombre, String apellido, Integer edad, String cargo, String especialidad) {
        Integer idEmpleado;
        if (DataRepository.getMedicos().isEmpty()) {
            idEmpleado = 1;
        } else {
            idEmpleado = DataRepository.getMedicos().getLast().getIdEmpleado()+1;
        }
        Medico nuevoMedico = new Medico(idEmpleado, nombre, apellido, edad, cargo, null, especialidad);
        DataRepository.agregarMedico(nuevoMedico);
        return false;
    }

    @Override
    public boolean actualizarMedico(Integer idEmpleado, String nombre, String apellido, Integer edad, String cargo, String especialidad) {
        return false;
    }

    @Override
    public Medico consultarPorID(Integer id) {
        return DataRepository.getMedicos().get(id);
    }

    @Override
    public List<Medico> obtenerTodosLosMedicos() {
        return DataRepository.getMedicos();
    }

    @Override
    public boolean eliminarMedico(Integer id) {
        return false;
    }

    @Override
    public boolean guardarCambiosDesdeTabla(List<Medico> lista) {
        return false;
    }
}
