package org.sistema.persistencia;

import org.sistema.repository.DataRepository;
import org.sistema.entidad.Cita;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenciaInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaPaciente implements PersistenciaInterface<Paciente> {
    //metodo para asignar pacientes a una lista de pacientes desde un archivo
    @Override
    public boolean llenarListaDesdeArchivo(List<Paciente> pacientes) {
        pacientes.clear();
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaPacientes.txt");
        if (!archivo.exists()) return false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            //salta la cabecera
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length != 8) {
                    return false;
                }
                Integer id = Integer.parseInt(campos[0]);
                String nombre = campos[1];
                String apellido = campos[2];
                Integer edad = Integer.parseInt(campos[3]);
                String dni = campos[4];
                String direccion = campos[5];
                String telefono = campos[6];
                String estado = campos[7];

                Paciente paciente = new Paciente(id, nombre, apellido, edad, dni, direccion, telefono, estado, null, null);
                pacientes.add(paciente);
            }
            br.close();

            // asignar atributos faltantes si existen
            for (Paciente p : pacientes) {
                // asignar historial clinico
                if (DataRepository.getHistorialesClinicos() == null || DataRepository.getHistorialesClinicos().isEmpty()) {
                    p.setHistorialClinico(null);
                } else {
                    HistorialClinico historial = null;
                    for (HistorialClinico h : DataRepository.getHistorialesClinicos()) {
                        if (h.getPaciente() != null && h.getPaciente().getIdPaciente().equals(p.getIdPaciente())) {
                            historial = h;
                            break;
                        }
                    }
                    p.setHistorialClinico(historial);
                }
                // asignar citas
                if (DataRepository.getCitas() == null || DataRepository.getCitas().isEmpty()) {
                    p.setCitas(new ArrayList<>());
                } else {
                    List<Cita> citasPaciente = new ArrayList<>();
                    for (Cita cita : DataRepository.getCitas()) {
                        if (cita.getPaciente() != null && cita.getPaciente().getIdPaciente().equals(p.getIdPaciente())) {
                            citasPaciente.add(cita);
                        }
                    }
                    // setea las citas
                    p.setCitas(citasPaciente);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    // metodo para sobrescribir el archivo con la lista
    @Override
    public boolean actualizarArchivo(List<Paciente> lista) {
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaPacientes.txt");
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
