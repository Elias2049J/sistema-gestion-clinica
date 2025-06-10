package org.sistema.vista;

import org.sistema.vista.gestionpaciente.VentanaGestionPaciente;
import org.sistema.vista.gestionpaciente.VentanaHistorialCitas;
import org.sistema.vista.gestionpaciente.VentanaHistorialClinico;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class VentanaPrincipal extends JFrame implements Serializable {
    private VentanaGestionPaciente ventanaGestionPaciente;
    private VentanaHistorialClinico ventanaHistorialClinico;
    private VentanaHistorialCitas ventanaHistorialCitas;

    private  LienzoMenu lienzoMenu = new LienzoMenu();
    private LienzoCentral lienzoCentral = new LienzoCentral();

    public VentanaPrincipal() throws HeadlessException {
        super();
        this.setTitle("Sistema");
        this.setSize(1300, 760);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoMenu, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
    }

    class LienzoMenu extends JPanel {
        private JMenuBar barraMenu = new JMenuBar();

        private JMenu mnGestionPacientes = new JMenu("Gestionar pacientes");
        private JMenuItem registrarPaciente = new JMenuItem("Registrar nuevo paciente");
        private JMenuItem gestionarPaciente = new JMenuItem("Gestionar pacientes");
        private JMenuItem historialClinico = new JMenuItem("Historiales clinicos");
        private JMenuItem historialCitas = new JMenuItem("Historiales de Citas");

        private JMenu menuGestionCitas = new JMenu("Gestión de citas");
        private JMenuItem programacionCitas = new JMenuItem("Programar citas");
        private JMenuItem modifCancelacion = new JMenuItem("Modificar o cancelar citas");
        private JMenuItem imprimirCita = new JMenuItem("Imprimir Cita");

        private JMenu menuGestionMedicos = new JMenu("Gestionar medicos y personal");
        private JMenuItem gestionarMedicos = new JMenu("Gestionar medicos");
        private JMenuItem gestionarPersonal = new JMenuItem("Gestionar Personal");

        public LienzoMenu(){
            super();
            this.setLayout(new BorderLayout());
            this.add(barraMenu);

            this.barraMenu.add(mnGestionPacientes);
            this.mnGestionPacientes.add(registrarPaciente);
            this.mnGestionPacientes.add(gestionarPaciente);
            this.mnGestionPacientes.add(historialClinico);
            this.mnGestionPacientes.add(historialCitas);

            this.barraMenu.add(menuGestionCitas);
            this.menuGestionCitas.add(imprimirCita);
            this.menuGestionCitas.add(programacionCitas);
            this.menuGestionCitas.add(modifCancelacion);

            this.barraMenu.add(menuGestionMedicos);
            this.menuGestionMedicos.add(imprimirCita);
            this.menuGestionMedicos.add(gestionarMedicos);
            this.menuGestionMedicos.add(gestionarPersonal);

            // para abrir la ventana de gestionar pacientes
            gestionarPaciente.addActionListener(e -> {
                ventanaGestionPaciente = new VentanaGestionPaciente();
                ventanaGestionPaciente.setVisible(true);
            });

            // para abrir la ventana de historiales clínicos
            historialClinico.addActionListener(e -> {
                ventanaHistorialClinico = new VentanaHistorialClinico();
                ventanaHistorialClinico.setVisible(true);
            });

            // para abrir la ventana de historiales de citas
            historialCitas.addActionListener(e -> {
                ventanaHistorialCitas = new VentanaHistorialCitas();
                ventanaHistorialCitas.setVisible(true);
            });
        }
    }

    class LienzoCentral extends JPanel {
        public LienzoCentral() {
            super();
            this.setLayout(new BorderLayout(10, 10));
            JLabel lblBienvenida = new JLabel("Bienvenido al Sistema de Gestión de Archivos", SwingConstants.CENTER);
            lblBienvenida.setFont(new Font("Arial", Font.BOLD, 24));
            JTextArea areaInfo = new JTextArea("Seleccione una opción del menú para comenzar.");
            areaInfo.setEditable(false);
            areaInfo.setFont(new Font("Arial", Font.PLAIN, 16));
            this.add(lblBienvenida, BorderLayout.NORTH);
            this.add(new JScrollPane(areaInfo), BorderLayout.CENTER);
        }
    }
}
