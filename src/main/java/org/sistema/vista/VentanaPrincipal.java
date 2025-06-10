package org.sistema.vista;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class VentanaPrincipal extends JFrame implements Serializable {
    private VentanaArchivo ventanaArchivo;
    private VentanaImprimirCita ventanaImprimirCita;
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
        private JMenuItem registroDatosPersonales = new JMenuItem("Registrar datos personales");
        private JMenuItem historialClinico = new JMenuItem("Historial clinico");
        private JMenuItem historialCitas = new JMenuItem("Historial Citas");

        private JMenu menuGestionCitas = new JMenu("Gestión de citas");
        private JMenuItem programacionCitas = new JMenuItem("Programar citas");
        private JMenuItem modifCancelacion = new JMenuItem("Modificar o cancelar citas");
        private JMenuItem imprimirCita = new JMenuItem("Imprimir Cita");

        private JMenu menuGestionMedicos = new JMenu("Gestionar medicos y personal");
        private JMenuItem menuMedicos = new JMenu("Gestionar medicos");
        private JMenuItem mnPersonal = new JMenuItem("Acerca de");

        public LienzoMenu(){
            super();
            this.setLayout(new BorderLayout());
            this.add(barraMenu);

            this.barraMenu.add(mnGestionPacientes);
            this.mnGestionPacientes.add(registroDatosPersonales);
            this.mnGestionPacientes.add(historialClinico);
            this.mnGestionPacientes.add(historialCitas);

            this.barraMenu.add(menuGestionCitas);
            this.menuGestionCitas.add(imprimirCita);
            this.menuGestionCitas.add(programacionCitas);
            this.menuGestionCitas.add(modifCancelacion);

            this.barraMenu.add(menuGestionMedicos);
            this.menuGestionMedicos.add(imprimirCita);
            this.menuGestionMedicos.add(menuMedicos);
            this.menuGestionMedicos.add(mnPersonal);

            imprimirCita.addActionListener(e -> {
                ventanaImprimirCita = new VentanaImprimirCita();
                ventanaImprimirCita.setVisible(true);
            });

            registroDatosPersonales.addActionListener(e -> {
                ventanaImprimirCita = new VentanaImprimirCita();
                ventanaImprimirCita.setVisible(true);
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
