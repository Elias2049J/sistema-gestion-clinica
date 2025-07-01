package org.sistema.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sistema.entidad.Paciente;
import org.sistema.use_case.CrudUseCase;
import org.sistema.use_case.PacienteUseCase;
@NoArgsConstructor
@AllArgsConstructor
public class PacienteModel implements PacienteUseCase {
    private CrudUseCase<Paciente, Integer, String> crudPacienteModel;

    @Override
    public boolean validarDni(String dni) {
        for (Paciente p : crudPacienteModel.findAll()) {
            if (p.getDni() != null && p.getDni().equals(dni)) {
                return false;
            }
        }
        return true;
    }
}
