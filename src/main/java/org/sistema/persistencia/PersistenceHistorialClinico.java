package org.sistema.persistencia;

import org.sistema.repository.DataRepository;
import org.sistema.entidad.EvaluacionMedica;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;
import org.sistema.interfaces.PersistenceInterface;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenceHistorialClinico implements PersistenceInterface<HistorialClinico> {
    @Override
    public boolean loadListFromFile(List<HistorialClinico> lista) {
        lista.clear();
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaHistorialClinico.txt");
        if (!archivo.exists()) {
            return false;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine(); // saltar cabecera
            Integer idPaciente = 0;
            while((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length != 7) {
                    return false;
                }
                Integer idH = Integer.parseInt(campos[0]);
                Integer idP = Integer.parseInt(campos[1]);
                String ant = campos[2];
                String alr = campos[3];
                String obs = campos[4];
                LocalDate fecha = LocalDate.parse(campos[5]);
                String est = campos[6];
                // null por defecto para atributos faltantes
                HistorialClinico nH = new HistorialClinico(idH, new Paciente(idP, null, null, null, null, null, null, null, null, null), null, ant, alr, obs, fecha, est);
                lista.add(nH);
            }
            br.close();
            //bucle para asignar atributos faltantes
            for (HistorialClinico nH : lista) {
                Paciente paciente = null;
                Integer idP = nH.getPaciente() != null ? nH.getPaciente().getIdPaciente() : null;
                if (idP == null || DataRepository.getPacientes() == null || DataRepository.getPacientes().isEmpty()) {
                    nH.setPaciente(null);
                } else {
                    for (Paciente p : DataRepository.getPacientes()) {
                        if (p.getIdPaciente().equals(idP)) {
                            paciente = p;
                            break;
                        }
                    }
                    nH.setPaciente(paciente);
                }
                if (DataRepository.getEvaluacionMedicas() == null || DataRepository.getEvaluacionMedicas().isEmpty()) {
                    nH.setEvaluacionesMedicas(new ArrayList<>());
                } else {
                    List<EvaluacionMedica> evaluaciones = new ArrayList<>();
                    for (EvaluacionMedica ev : DataRepository.getEvaluacionMedicas()) {
                        if (ev.getHistorialClinico() != null && ev.getHistorialClinico().getIdHistorial().equals(nH.getIdHistorial())) {
                            evaluaciones.add(ev);
                        }
                    }
                    nH.setEvaluacionesMedicas(evaluaciones);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean updateFileFromList(List<HistorialClinico> lista) {
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaHistorialClinico.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo, false))) {
            writer.println("idHistorial,idPaciente,antecedentes,alergias,observaciones,fechaCreacion,estado");
            for (HistorialClinico h: lista) {
                writer.println(
                        h.getIdHistorial()+","+
                        h.getPaciente().getIdPaciente()+","+
                        h.getAntecedentes()+","+
                        h.getAlergias()+","+
                        h.getObservaciones()+","+
                        h.getFechaCreacion()+","+
                        h.getEstado()
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
