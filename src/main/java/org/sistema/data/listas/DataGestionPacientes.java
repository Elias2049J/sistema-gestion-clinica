package org.sistema.data.listas;

import lombok.*;
import org.sistema.entidad.Paciente;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.HistorialCitas;
import org.sistema.entidad.Consulta;
import org.sistema.entidad.Tratamiento;
import org.sistema.entidad.Diagnostico;

import java.util.ArrayList;
import java.util.List;
// Clase que contiene listas estáticas de todas las entidades del sistema
// que se relacionen con la gestion de pacientes
@AllArgsConstructor
public class DataGestionPacientes {
    @Getter
    @Setter
    //estaatico para que la misma lista sea compartida por todas las instancias
    private static List<Paciente> pacientes = new ArrayList<>();

    @Getter
    @Setter
    private static List<HistorialClinico> historialesClinicos = new ArrayList<>();

    @Getter
    @Setter
    private static List<HistorialCitas> historialesCitas = new ArrayList<>();

    @Getter
    @Setter
    private static List<Consulta> consultas = new ArrayList<>();

    @Getter
    @Setter
    private static List<Tratamiento> tratamientos = new ArrayList<>();

    @Getter
    @Setter
    private static List<Diagnostico> diagnosticos = new ArrayList<>();

    public static void agregarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }

    public static void agregarHistorialClinico(HistorialClinico historialClinico) {
        historialesClinicos.add(historialClinico);
    }

    public static void agregarHistorialCitas(HistorialCitas historialCitas) {
        historialesCitas.add(historialCitas);
    }

    public static void agregarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public static void agregarTratamiento(Tratamiento tratamiento) {
        tratamientos.add(tratamiento);
    }

    public static void agregarDiagnostico(Diagnostico diagnostico) {
        diagnosticos.add(diagnostico);
    }
}
