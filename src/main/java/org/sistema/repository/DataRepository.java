package org.sistema.repository;

import lombok.*;
import org.sistema.entidad.*;

import java.util.ArrayList;
import java.util.List;
// centraliza las listas de las entidades en memoria,
// actúa como capa de abstracción entre los modelos y la persistencia

public class DataRepository {
    @Getter
    @Setter
    // metodos estaticos para que las mismas listas sean compartidas por todas las instancias
    private static List<Paciente> pacientes = new ArrayList<>();

    @Getter
    @Setter
    private static List<HistorialClinico> historialesClinicos = new ArrayList<>();

    @Getter
    @Setter
    private static List<Cita> citas = new ArrayList<>();

    @Getter
    @Setter
    private static List<EvaluacionMedica> evaluacionMedicas = new ArrayList<>();

    @Getter
    @Setter
    private static List<Medico> medicos = new ArrayList<>();

    public static void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public static void agregarHistorialClinico(HistorialClinico historialClinico) {
        historialesClinicos.add(historialClinico);
    }

    public static void agregarCita(Cita cita) {
        citas.add(cita);
    }

    public static void agregarEvaluacionMedica(EvaluacionMedica evaluacionMedica) {
        evaluacionMedicas.add(evaluacionMedica);
    }

    public static void agregarMedico(Medico medico) {
        medicos.add(medico);
    }
}
