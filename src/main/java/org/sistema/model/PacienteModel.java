package org.sistema.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.sistema.data.DataHistorialCitas;
import org.sistema.data.DataHistorialClinico;
import org.sistema.entidad.HistorialCitas;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;
import org.sistema.use_case.PacienteUseCase;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PacienteModel extends CrudModel<Paciente, Integer> implements PacienteUseCase  {
    private DataHistorialClinico dataHistorialClinico = new DataHistorialClinico();
    private DataHistorialCitas dataHistorialCitas = new DataHistorialCitas();

    @Override
    public boolean registrarDatosPersonales() {
        return false;
    }

    //lógica para obtener el historial clínico por ID del paciente
    @Override
    public HistorialClinico obtenerHistorialClinico(Integer idPaciente) {
        for (HistorialClinico historialClinico: dataHistorialClinico.getHistorialesClinicos()) {
            if (historialClinico.getPaciente().getIdPaciente().equals(idPaciente)) {
                return historialClinico;
            }
        }
        return null;
    }

    @Override
    public HistorialCitas obtenerHistorialCitas(Integer idPaciente) {
        return null;
    }
}
