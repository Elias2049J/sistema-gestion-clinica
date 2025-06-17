package org.sistema.persistencia;

import org.sistema.repository.DataRepository;
import org.sistema.entidad.EvaluacionMedica;
import org.sistema.entidad.HistorialClinico;
import org.sistema.interfaces.PersistenceInterface;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class PersistenceEvaluacionMedica implements PersistenceInterface<EvaluacionMedica> {
    @Override
    public boolean loadListFromFile(List<EvaluacionMedica> lista) {
        lista.clear();
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaEvaluacionesMedicas.txt");
        if (!archivo.exists()) return false;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea = br.readLine(); // saltar cabecera
            while ((linea = br.readLine()) != null) {
                String[] campos = linea.split(",");
                if (campos.length != 6) return false;
                Integer idEvaluacion = Integer.parseInt(campos[0]);
                Integer idHistorial = Integer.parseInt(campos[1]);
                String diagnostico = campos[2];
                String tratamiento = campos[3];
                LocalDate fecha = LocalDate.parse(campos[4]);
                LocalTime hora = LocalTime.parse(campos[5]);

                // por defecto los atributos faltantes son null
                EvaluacionMedica evaluacion = new EvaluacionMedica(idEvaluacion, new HistorialClinico(idHistorial, null, null, null, null, null, null, null), diagnostico, tratamiento, fecha, hora);
                lista.add(evaluacion);
            }
            br.close();

            // este bucle asigna los atributos faltantes
            for (EvaluacionMedica ev: lista) {
                //se verifica que la lista instanciada de datagestion no sea null o vacia
                if (DataRepository.getHistorialesClinicos() == null || DataRepository.getHistorialesClinicos().isEmpty()) {
                    ev.setHistorialClinico(null);
                } else {
                    HistorialClinico historial;
                    for (HistorialClinico h: DataRepository.getHistorialesClinicos()) {
                        //si coinciden se asigna el historial, se verifica que el historial no sea null
                        if (h.getIdHistorial().equals(ev.getHistorialClinico() != null ? ev.getHistorialClinico().getIdHistorial(): null)) {
                            historial = h;
                            ev.setHistorialClinico(historial);
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
    public boolean updateFileFromList(List<EvaluacionMedica> lista) {
        File archivo = new File("src/main/java/org/sistema/data/archivo/listaEvaluacionesMedicas.txt");
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            writer.println("idEvaluacion,idHistorial,diagnostico,tratamiento,fecha,hora");
            for (EvaluacionMedica ev: lista) {
                writer.println(
                        ev.getIdEvaluacion()+","+
                        ev.getHistorialClinico().getIdHistorial()+","+
                        ev.getDiagnostico()+","+
                        ev.getTratamiento()+","+
                        ev.getFecha()+","+
                        ev.getHora()
                );
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
