package org.sistema;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;
import org.sistema.model.CrudCitaModel;
import org.sistema.model.CrudPacienteModel;
import org.sistema.persistencia.PersistenciaCita;
import org.sistema.persistencia.PersistenciaPaciente;
import org.sistema.repository.Repository;
import org.sistema.vista.VentanaPrincipal;

import javax.swing.*;

public class MainTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //inyeccion de dependencias
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(
                new CrudPacienteModel(new Repository<>(new PersistenciaPaciente(), Paciente::getIdPaciente), new Repository<>(new PersistenciaCita(), Cita::getIdCita)),
                new CrudCitaModel(new Repository<>(new PersistenciaCita(), Cita::getIdCita), new Repository<>(new PersistenciaPaciente(), Paciente::getIdPaciente))
        );
        ventanaPrincipal.setVisible(true);
    }
}
