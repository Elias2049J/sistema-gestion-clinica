package org.sistema.persistencia;

import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenciaInterface;

import java.io.*;
import java.util.List;

public class PersistenciaPaciente implements PersistenciaInterface<Paciente> {
    //metodo para asignar pacientes a una lista de pacientes desde un archivo
    @Override
    public boolean llenarListaDesdeArchivo(List<Paciente> pacientes) throws FileNotFoundException {
        pacientes.clear();
        File archivo = new File("src/main/java/org/sistema/data/archivos/listaPacientes.txt");
        if (archivo.exists()) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                //salta la cabecera
                String linea = br.readLine();
                while ((linea = br.readLine()) != null) {
                    String[] campos = linea.split(",");
                    if (campos.length == 8) {
                        Integer id = Integer.parseInt(campos[0]);
                        String nombre = campos[1];
                        String apellido = campos[2];
                        Integer edad = Integer.parseInt(campos[3]);
                        String dni = campos[4];
                        String direccion = campos[5];
                        String telefono = campos[6];
                        String estado = campos[7];
                        Paciente paciente = new Paciente(id, nombre, apellido, edad, dni, direccion, telefono, estado);
                        pacientes.add(paciente);
                    }
                }
                br.close();
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    // metodo para sobrescribir el archivo con la lista
    @Override
    public boolean actualizarArchivo(List<Paciente> lista) {
        File archivo = new File("src/main/java/org/sistema/data/archivos/listaPacientes.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, false))) {
            // Escribir cabecera
            writer.println("idPaciente,nombre,apellido,edad,dni,direccion,telefono,estado");
            // setear datos de cada paciente
            for (Paciente paciente : lista) {
                writer.println(
                    paciente.getIdPaciente() + "," +
                    paciente.getNombre() + "," +
                    paciente.getApellido() + "," +
                    paciente.getEdad() + "," +
                    paciente.getDni() + "," +
                    paciente.getDireccion() + "," +
                    paciente.getTelefono() + "," +
                    paciente.getEstado()
                );
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
