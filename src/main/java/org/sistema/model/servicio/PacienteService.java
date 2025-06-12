package org.sistema.model.servicio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sistema.model.data.DataHistorialCitas;
import org.sistema.model.data.DataHistorialClinico;
import org.sistema.model.entidad.HistorialCitas;
import org.sistema.model.entidad.HistorialClinico;
import org.sistema.model.entidad.Paciente;
import org.sistema.use_case.PacienteUseCase;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PacienteService implements PacienteUseCase {
    private DataHistorialClinico dataHistorialClinico = new DataHistorialClinico();
    private DataHistorialCitas dataHistorialCitas = new DataHistorialCitas();

    @Override
    public boolean registrarDatosPersonales() {
        return false;
    }

    @Override
    public Paciente consultarDatos(Integer integer) {
        return null;
    }

    @Override
    public Paciente crearNuevo(Integer idPaciente) {
        return null;
    }

    @Override
    public Paciente actualizarDatos(Integer idPaciente) {
        return null;
    }

    @Override
    public boolean eliminar(Integer idPaciente) {
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
