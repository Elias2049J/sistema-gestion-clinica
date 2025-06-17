package org.sistema.model;

import lombok.Getter;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenceInterface;
import org.sistema.persistencia.PersistencePaciente;
import org.sistema.repository.DataRepository;
import org.sistema.use_case.PacienteUseCase;

import java.util.List;

@Getter
public class PacienteModel implements PacienteUseCase {
    private PersistenceInterface<Paciente> persistenciaPaciente = new PersistencePaciente();

    public PacienteModel() {
        persistenciaPaciente.loadListFromFile(DataRepository.getPacientes());
    }

    //consulta un paciente por su id
    @Override
    public Paciente getById(Integer id) {
        persistenciaPaciente.loadListFromFile(DataRepository.getPacientes());

        for (Paciente paciente: DataRepository.getPacientes()) {
            if(paciente.getIdPaciente().equals(id)) {
                return paciente;
            }
        }
        return null;
    }

    @Override
    //crea un nuevo paciente, recibe sus atributos y lo agrega a la lista
    public boolean create(String nombre, String apellido, Integer edad, String dni, String direccion, String telefono) {
        persistenciaPaciente.loadListFromFile(DataRepository.getPacientes());
        // validaciones internas
        if (nombre == null || nombre.trim().isEmpty()) return false;
        if (apellido == null || apellido.trim().isEmpty()) return false;
        if (edad == null || edad <= 0) return false;
        if (dni == null || !dni.matches("\\d{8}")) return false;
        if (direccion == null || direccion.trim().isEmpty()) return false;
        if (telefono == null || !telefono.matches("\\d{9}")) return false;
        Integer id;
        // si la lista de pacientes está vacía, el id del nuevo paciente es 1
        if (DataRepository.getPacientes().isEmpty()) {
            id = 1;
        } else {
            //si no esta vacia, obtiene el ultimo id de paciente y se suma 1
            id = DataRepository.getPacientes().getLast().getIdPaciente()+1;
        }
        String estado = "Activo"; // estado por defecto
        //el historial clinico y las citas son null por defecto
        Paciente nuevoPaciente = new Paciente(id, nombre, apellido, edad, dni, direccion, telefono, estado, null, null);
        DataRepository.agregarPaciente(nuevoPaciente);
        persistenciaPaciente.updateFileFromList(DataRepository.getPacientes());
        persistenciaPaciente.loadListFromFile(DataRepository.getPacientes());
        return true;
    }

    //metodo para actualizar los datos de un paciente
    @Override
    public boolean update(Integer id, String nombre, String apellido, String dni, String direccion, String telefono, String estado) {
        for (Paciente paciente : DataRepository.getPacientes()) {
            // si encuentra el id le asigna los valores
            if (paciente.getIdPaciente().equals(id)) {
                paciente.setNombre(nombre);
                paciente.setApellido(apellido);
                paciente.setDni(dni);
                paciente.setDireccion(direccion);
                paciente.setTelefono(telefono);
                paciente.setEstado(estado);
                break;
            }
        }
        //actualiza el archivo
        persistenciaPaciente.updateFileFromList(DataRepository.getPacientes());
        persistenciaPaciente.loadListFromFile(DataRepository.getPacientes());
        return true;
    }

    @Override
    //actualiza el archivo de lista
    public boolean saveFromList(List<Paciente> lista) {
        persistenciaPaciente.updateFileFromList(lista);
        persistenciaPaciente.loadListFromFile(DataRepository.getPacientes());
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        persistenciaPaciente.loadListFromFile(DataRepository.getPacientes());
        for (int i = 0; i < DataRepository.getPacientes().size(); i++) {
            // se busca el paciente por el id ingresado
            if(DataRepository.getPacientes().get(i).getIdPaciente().equals(id)) {
                DataRepository.getPacientes().remove(i);
                // si se elimina se sale del ciclo
                break;
            }
        }
        persistenciaPaciente.updateFileFromList(DataRepository.getPacientes());
        return true;
    }
}