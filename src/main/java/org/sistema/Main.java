package org.sistema;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;
import org.sistema.model.CrudCitaModel;
import org.sistema.model.CrudPacienteModel;
import org.sistema.persistencia.PersistenciaCita;
import org.sistema.persistencia.PersistenciaPaciente;
import org.sistema.repository.Repository;
import org.sistema.use_case.CrudUseCase;
import org.sistema.vista.VentanaPrincipal;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Repository<Paciente, Integer> repoPacientes = new Repository<>(new PersistenciaPaciente(), Paciente::getIdPaciente);
        Repository<Cita, Integer> repoCitas = new Repository<>(new PersistenciaCita(), Cita::getIdCita);
        CrudUseCase<Paciente, Integer, String> crudPacienteModel = new CrudPacienteModel(repoPacientes, repoCitas);
        CrudUseCase<Cita, Integer, String> crudCitaModel = new CrudCitaModel(repoCitas, repoPacientes);
        crudPacienteModel.findAll();
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(crudPacienteModel, crudCitaModel);
        ventanaPrincipal.setVisible(true);
    }
}