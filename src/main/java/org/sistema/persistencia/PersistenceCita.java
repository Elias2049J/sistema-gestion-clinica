package org.sistema.persistencia;

import org.sistema.repository.DataRepository;
import org.sistema.entidad.Cita;
import org.sistema.entidad.Medico;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenceInterface;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PersistenceCita implements PersistenceInterface<Cita> {

    @Override
    public boolean loadListFromFile(List<Cita> lista) {
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaCitas.txt");
        if(!archivo.exists()) return false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine();
            while((linea = br.readLine())!= null) {
                String[] campos = linea.split(",");
                if (campos.length != 9) return false;

                Integer id = Integer.parseInt(campos[0]);
                Integer idP = Integer.parseInt(campos[1]);
                Integer idM = Integer.parseInt(campos[2]);
                String esp = campos[3];
                String motivo = campos[4];
                LocalDate fecha = LocalDate.parse(campos[5]);
                LocalTime hora = LocalTime.parse(campos[6]);
                double costo = Double.parseDouble(campos[7]);
                String estado = campos[8];

                // atributos null por defecto
                Cita cita = new Cita(
                        id, new Paciente(idP, null, null, null, null,null,null,null,null,null),
                        new Medico(idM, null, null, null, null, null, null),
                        esp, motivo, fecha, hora, costo, estado);
                lista.add(cita);
            }

            for (Cita c:lista){
                if (DataRepository.getPacientes() == null || DataRepository.getPacientes().isEmpty()) {
                    c.setPaciente(null);
                } else {
                    Paciente paciente;
                    for (Paciente p: DataRepository.getPacientes()) {
                        //si coinciden se asigna el historial, se verifica que el historial no sea null
                        if (p.getIdPaciente().equals(c.getPaciente() != null ? c.getPaciente().getIdPaciente(): null)) {
                            paciente = p;
                            c.setPaciente(paciente);
                            break;
                        }
                    }
                }

                if (DataRepository.getMedicos() == null || DataRepository.getMedicos().isEmpty()) {
                    c.setMedico(null);
                } else {
                    Medico medico;
                    for (Medico m: DataRepository.getMedicos()) {
                        //si coinciden se asigna el historial, se verifica que el historial no sea null
                        if (m.getIdEmpleado().equals(c.getMedico() != null ? c.getMedico().getIdEmpleado(): null)) {
                            medico = m;
                            c.setMedico(medico);
                            break;
                        }
                    }
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateFileFromList(List<Cita> lista) {
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaPacientes.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, false))) {
            // Escribir cabecera
            writer.println("idPaciente,nombre,apellido,edad,dni,direccion,telefono,estado");
            // setear datos de cada paciente
            for (Cita c : lista) {
                writer.println(
                        c.getIdCita() + "," +
                        c.getPaciente().getIdPaciente() + "," +
                        c.getMedico().getIdEmpleado() + "," +
                        c.getEspecialidad() + "," +
                        c.getMotivo() + "," +
                        c.getFecha() + "," +
                        c.getCosto() + "," +
                        c.getEstado()
                );
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
