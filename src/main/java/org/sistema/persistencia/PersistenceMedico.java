package org.sistema.persistencia;

import org.sistema.repository.DataRepository;
import org.sistema.entidad.Cita;
import org.sistema.entidad.Medico;
import org.sistema.interfaces.PersistenceInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceMedico implements PersistenceInterface<Medico> {
    @Override
    public boolean loadListFromFile(List<Medico> lista) {
        lista.clear();
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaMedicos.txt");
        if (!archivo.exists()) return false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine(); // saltar cabecera
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length != 6) return false;
                Integer idEmpleado = Integer.parseInt(campos[0]);
                String nombre = campos[1];
                String apellido = campos[2];
                Integer edad = Integer.parseInt(campos[3]);
                String cargo = campos[4];
                String especialidad = campos[5];

                // inicialmente citas es null
                Medico medico = new Medico(idEmpleado, nombre, apellido, edad, cargo, null, especialidad);
                lista.add(medico);
            }
            br.close();

            // asignar atributos faltantes
            for (Medico m : lista) {
                // asignar citas
                if (DataRepository.getCitas() == null || DataRepository.getCitas().isEmpty()) {
                    m.setCitas(new ArrayList<>());
                } else {
                    List<Cita> citasMedico = new ArrayList<>();
                    for (Cita cita : DataRepository.getCitas()) {
                        if (cita.getMedico() != null && cita.getMedico().getIdEmpleado().equals(m.getIdEmpleado())) {
                            citasMedico.add(cita);
                        }
                    }
                    m.setCitas(citasMedico);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateFileFromList(List<Medico> lista) {
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaMedicos.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, false))) {
            writer.println("idEmpleado,nombre,apellido,edad,cargo,especialidad");
            for (Medico m: lista) {
                writer.println(
                    m.getIdEmpleado() + "," +
                    m.getNombre() + "," +
                    m.getApellido() + "," +
                    m.getEdad() + "," +
                    m.getCargo() + "," +
                    m.getEspecialidad()
                );
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
