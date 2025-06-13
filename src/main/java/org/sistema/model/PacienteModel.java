package org.sistema.model;

import lombok.Getter;
import org.sistema.entidad.HistorialCitas;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenciaInterface;
import org.sistema.persistencia.PersistenciaPaciente;
import org.sistema.use_case.PacienteUseCase;
import org.sistema.data.listas.DataGestionPacientes;

import java.io.FileNotFoundException;
import java.util.List;

@Getter
public class PacienteModel implements PacienteUseCase {
    private PersistenciaInterface<Paciente> persistenciaPaciente = new PersistenciaPaciente();

    public PacienteModel() throws FileNotFoundException {
        persistenciaPaciente.llenarListaDesdeArchivo(DataGestionPacientes.getPacientes());
    }

    //metodo para consultar un paciente por su id
    @Override
    public Paciente consultarPorID(Integer id) throws FileNotFoundException {
        persistenciaPaciente.llenarListaDesdeArchivo(DataGestionPacientes.getPacientes());
        for (Paciente paciente: DataGestionPacientes.getPacientes()) {
            if(paciente.getIdPaciente().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

    @Override
    //metodo para crear un nuevo paciente, recibe sus atributos y agrega el paciente a la lista de pacientes
    public boolean crearPaciente(String nombre, String apellido, Integer edad, String dni, String direccion, String telefono) throws FileNotFoundException {
        // validaciones internas
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (apellido == null || apellido.trim().isEmpty()) return false;
        if (edad == null || edad <= 0) return false;
        if (dni == null || !dni.matches("\\d{8}")) return false;
        if (direccion == null || direccion.trim().isEmpty()) return false;
        if (telefono == null || !telefono.matches("\\d{9}")) return false;
        Integer id;
        // si la lista d epacientes está vacía, el id del nuevo paciente es 1
        if (DataGestionPacientes.getPacientes().isEmpty()) {
            id = 1;
        } else {
            //si no esta vacia, se obtitne el ultimo id de paciente y se suma 1
            id = DataGestionPacientes.getPacientes().getLast().getIdPaciente()+1;
        }
        String estado = "Activo"; // estado por defecto
        Paciente nuevoPaciente = new Paciente(id, nombre, apellido, edad, dni, direccion, telefono, estado);
        DataGestionPacientes.agregarPaciente(nuevoPaciente);
        persistenciaPaciente.actualizarArchivo(DataGestionPacientes.getPacientes());
        persistenciaPaciente.llenarListaDesdeArchivo(DataGestionPacientes.getPacientes());
        return true;
    }

    //metodo para actualizar los datos de un paciente
    @Override
    public boolean actualizarPaciente(Integer id, String nombre, String apellido, String dni, String direccion, String telefono, String estado) throws FileNotFoundException {
        for (Paciente paciente : DataGestionPacientes.getPacientes()) {
            // si encuentra el id le asigna los valores
            if (paciente.getIdPaciente().equals(id)) {
                paciente.setNombre(nombre);
                paciente.setApellido(apellido);
                paciente.setDni(dni);
                paciente.setDireccion(direccion);
                paciente.setTelefono(telefono);
                paciente.setEstado(estado);
                //actualiza el archivo
                persistenciaPaciente.actualizarArchivo(DataGestionPacientes.getPacientes());
                return true;
            }
        }
        return false;
    }

    //lógica para obtener el historial clínico por ID del paciente
    @Override
    public HistorialClinico obtenerHistorialClinico(Integer idPaciente) {
        for (HistorialClinico historialClinico: DataGestionPacientes.getHistorialesClinicos()) {
            if (historialClinico.getPaciente().getIdPaciente().equals(idPaciente)) {
                return historialClinico;
            }
        }
        return null;
    }

    @Override
    public HistorialCitas obtenerHistorialCitas(Integer idPaciente) {
        for (HistorialCitas historialCitas : DataGestionPacientes.getHistorialesCitas()) {
            if (historialCitas.getPaciente().getIdPaciente().equals(idPaciente)) {
                return historialCitas;
            }
        }
        return null;
    }

    @Override
    public boolean guardarCambiosDesdeTabla(List<Paciente> lista) {
        persistenciaPaciente.actualizarArchivo(lista);
        try {
            persistenciaPaciente.llenarListaDesdeArchivo(DataGestionPacientes.getPacientes());
            return true;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean eliminar(Integer id) {
        return false;
    }
}