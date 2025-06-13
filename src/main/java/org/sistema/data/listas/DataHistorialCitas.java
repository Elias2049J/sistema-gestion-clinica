package org.sistema.data.listas;

import org.sistema.entidad.Cita;
import org.sistema.entidad.HistorialCitas;
import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataHistorialCitas {
    private DataHistorialClinico dataHistorialClinico = new DataHistorialClinico();
    private List<HistorialCitas> historialesCitas = new ArrayList<>();


    public DataHistorialCitas() {
        precargarDatos();
    }

    public boolean precargarDatos() {
        List<HistorialClinico> historiales = dataHistorialClinico.getHistorialesClinicos();

        Paciente paciente1 = historiales.getFirst().getPaciente();
        Paciente paciente2 = historiales.get(1).getPaciente();
        Paciente paciente3 = historiales.get(2).getPaciente();

        // citas
        Cita cita1 = new Cita(1, "Cardiología", "Dra corazon", paciente1, 150.0, LocalDateTime.now(), "Programada");
        Cita cita2 = new Cita(2, "Memelogía", "Dr House", paciente2, 120.0, LocalDateTime.now(), "Programada");
        Cita cita3 = new Cita(3, "Pediatría", "Dr strange", paciente3, 100.0, LocalDateTime.now(), "Cancelada");

        List<Cita> citasMiguel = new ArrayList<>();
        citasMiguel.add(cita1);
        List<Cita> citasMaria = new ArrayList<>();
        citasMaria.add(cita2);
        List<Cita> citasAxcel = new ArrayList<>();
        citasAxcel.add(cita3);

        //historial de citas de cada uno
        HistorialCitas historialCitas1 = new HistorialCitas(
                1,
                paciente1,
                LocalDate.now(),
                "Activo",
                citasMiguel
        );
        HistorialCitas historialCitas2 = new HistorialCitas(
                2,
                paciente2,
                LocalDate.now(),
                "Inactivo",
                citasMaria
        );
        HistorialCitas historialCitas3 = new HistorialCitas(
                3,
                paciente3,
                LocalDate.now(),
                "Activo",
                citasAxcel
        );
        //se agrega cada historial a la lista de historiales
        historialesCitas.add(historialCitas1);
        historialesCitas.add(historialCitas2);
        historialesCitas.add(historialCitas3);
        return true;
    }
}
