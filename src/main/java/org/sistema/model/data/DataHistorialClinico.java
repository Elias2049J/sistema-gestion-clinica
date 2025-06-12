package org.sistema.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.sistema.model.entidad.HistorialClinico;
import org.sistema.model.entidad.Paciente;
import org.sistema.model.entidad.Diagnostico;
import org.sistema.model.entidad.Tratamiento;
import org.sistema.model.entidad.Consulta;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class DataHistorialClinico {
    List<HistorialClinico> historialesClinicos = new ArrayList<>();

    public DataHistorialClinico(){
        precargarDatos();
    }

    //para crear instancias de los objetos relacionados con historial clinico
    public boolean precargarDatos(){

        // pacientes
        Paciente paciente1 = new Paciente(1, "Miguel", "Centellas", "12345678", "La Molina mas na", "987654321", "activo");
        Paciente paciente2 = new Paciente(2, "Maria", "Calderon", "12345679", "Calle falsa 123", "912345678", "inactivo");
        Paciente paciente3 = new Paciente(3, "Axcel", "Sanchez", "23457891", "Calle falsa 234", "123456789", "activo");

        // diagnsticos
        Diagnostico diag1 = new Diagnostico(1, "Mucha elegancia");
        Diagnostico diag2 = new Diagnostico(2, "Demasiada belleza");
        Diagnostico diag3 = new Diagnostico(1, "Finura en exceso");

        List<Diagnostico> diagnosticosMiguel = new ArrayList<>();
        diagnosticosMiguel.add(diag1);
        List<Diagnostico> diagnosticosMaria = new ArrayList<>();
        diagnosticosMaria.add(diag2);
        List<Diagnostico> diagnosticosAxcel = new ArrayList<>();
        diagnosticosAxcel.add(diag3);

        // tratamientos
        Tratamiento trat1 = new Tratamiento(1, "Paracetamol");
        Tratamiento trat2 = new Tratamiento(2, "1Kg. de lasaña diaria");
        Tratamiento trat3 = new Tratamiento(3, "Insulina cada noche");

        List<Tratamiento> tratamientos1 = new ArrayList<>();
        tratamientos1.add(trat1);
        List<Tratamiento> tratamientos2 = new ArrayList<>();
        tratamientos2.add(trat2);
        List<Tratamiento> tratamientos3 = new ArrayList<>();
        tratamientos3.add(trat3);

        // consultas
        Consulta cons1 = new Consulta(1, "Fiebre de poder", "Se recomienda reposo");
        Consulta cons2 = new Consulta(2, "Da caries a los ojos al verla", "Continuar tratamiento");
        Consulta cons3 = new Consulta(3, "Irradiar elegancia", "Se recomienda bajar la facha");


        List<Consulta> consultas1 = new ArrayList<>();
        consultas1.add(cons1);
        List<Consulta> consultas2 = new ArrayList<>();
        consultas2.add(cons2);
        List<Consulta> consultas3 = new ArrayList<>();
        consultas3.add(cons3);

        HistorialClinico historial1 = new HistorialClinico(
                1,
                paciente1,
                java.time.LocalDate.now(),
                "Activo",
                diagnosticosMiguel,
                tratamientos1,
                consultas1,
                "sin antedecentes",
                "No tiene alergias",
                "Asmático"
        );
        HistorialClinico historial2 = new HistorialClinico(
                2,
                paciente2,
                java.time.LocalDate.now(),
                "Inactivo",
                diagnosticosMaria,
                tratamientos2,
                consultas2,
                "Familiares con diabetes",
                "Alergia a los AINES",
                "Le sabe a los memes"
        );

        HistorialClinico historial3 = new HistorialClinico(
                3,
                paciente3,
                java.time.LocalDate.now(),
                "Inactivo",
                diagnosticosAxcel,
                tratamientos3,
                consultas3,
                "Familiares con diabetes",
                "Alergia a los AINES",
                "Sin observaciones"
        );
        historialesClinicos.add(historial1);
        historialesClinicos.add(historial2);
        historialesClinicos.add(historial3);
        return true;
    }
}
