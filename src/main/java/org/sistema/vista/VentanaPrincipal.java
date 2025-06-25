package org.sistema.vista;

import org.sistema.vista.sec_paciente.VentanaGestionPaciente;
import org.sistema.vista.sec_paciente.VentanaHistorialCitas;
import org.sistema.vista.sec_paciente.VentanaRegistroPaciente;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class VentanaPrincipal extends JFrame implements Serializable {
    private VentanaGestionPaciente ventanaGestionPaciente;
    private VentanaRegistroPaciente ventanaRegistroPaciente;
    private VentanaHistorialClinico ventanaHistorialClinico;
    private VentanaHistorialCitas ventanaHistorialCitas;

    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter();

    public VentanaPrincipal() throws HeadlessException {
        super();
        this.setTitle("Sistema Clínico");
        this.setSize(800, 600);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoCentral extends JPanel {
        private JLabel lblGestionPacientes = new JLabel("Gestión de Pacientes");
        private JLabel lblGestionCitas = new JLabel("Gestion de citas");
        private JLabel lblGestionMedicos = new JLabel("Gestion de Personal");

        private JButton btnVentanaRegPaciente = new JButton("Registrar Paciente");
        private JButton btnVentanaGestPaciente = new JButton("Gestionar Pacientes");
        private JButton btnVentanaHistCli = new JButton("Buscar Historial Clínico");
        private JButton btnVentanaHistCitas = new JButton("Buscar Historial de Citas");

        private JButton btnVentanaProgCita = new JButton("Programar Cita");
        private JButton btnVentanaModCita = new JButton("Modificar o Cancelar Cita");
        private JButton btnVentanaImpCita = new JButton("Imprimir Cita");

        private JButton btnVentanaGestMedicos = new JButton("Gestionar Medicos");
        private JButton btnVentanaGestPersonal = new JButton("Gestionar Personal");

        // imagenes para las secciones
        private ImageIcon iconoPacientes = new ImageIcon("src/main/resources/paciente.png");
        private ImageIcon iconoCitas = new ImageIcon("src/main/resources/cita.png");
        private ImageIcon iconoMedicos = new ImageIcon("src/main/resources/medico.png");

        private Image imagenPacientes = iconoPacientes.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH);
        private Image imagenCitas = iconoCitas.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
        private Image imagenMedicos = iconoMedicos.getImage().getScaledInstance(120, 80, Image.SCALE_SMOOTH);

        private JLabel lblIconoPacientes = new JLabel(new ImageIcon(imagenPacientes));
        private JLabel lblIconoCitas = new JLabel(new ImageIcon(imagenCitas));
        private JLabel lblIconoMedicos = new JLabel(new ImageIcon(imagenMedicos));

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            setBackground(new Color(240, 245, 255));
            GridBagConstraints gbcPadre = new GridBagConstraints();
            gbcPadre.gridy = 1;
            gbcPadre.fill = GridBagConstraints.BOTH;
            gbcPadre.weighty = 1;

            // seccion de pacientes
            JPanel panelPacientes = new JPanel(new GridBagLayout());
            panelPacientes.setBackground(Color.WHITE);
            panelPacientes.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(200,200,200)));
            GridBagConstraints gbcP = new GridBagConstraints();
            gbcP.insets = new Insets(10, 10, 10, 10);
            gbcP.gridx = 0;
            gbcP.fill = GridBagConstraints.HORIZONTAL;

            gbcP.gridy = 0;
            panelPacientes.add(lblIconoPacientes, gbcP);
            lblGestionPacientes.setFont(new Font("Arial", Font.BOLD, 20));
            gbcP.gridy = 1;
            panelPacientes.add(lblGestionPacientes, gbcP);
            btnVentanaRegPaciente.setIcon(UIManager.getIcon("FileView.fileIcon"));
            btnVentanaGestPaciente.setIcon(UIManager.getIcon("FileView.directoryIcon"));
            btnVentanaHistCli.setIcon(UIManager.getIcon("FileChooser.detailsViewIcon"));
            gbcP.gridy = 2;
            panelPacientes.add(btnVentanaRegPaciente, gbcP);
            gbcP.gridy = 3;
            panelPacientes.add(btnVentanaGestPaciente, gbcP);
            gbcP.gridy = 4;
            panelPacientes.add(btnVentanaHistCli, gbcP);
            gbcP.gridy = 5;
            panelPacientes.add(btnVentanaHistCitas, gbcP);

            // seccion de citas
            JPanel panelCitas = new JPanel(new GridBagLayout());
            panelCitas.setBackground(Color.WHITE);
            panelCitas.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(200,200,200)));
            GridBagConstraints gbcC = new GridBagConstraints();
            gbcC.insets = new Insets(10, 10, 10, 10);
            gbcC.gridx = 0;
            gbcC.fill = GridBagConstraints.HORIZONTAL;

            gbcC.gridy = 0;
            panelCitas.add(lblIconoCitas, gbcC);
            lblGestionCitas.setFont(new Font("Arial", Font.BOLD, 20));
            lblGestionCitas.setHorizontalAlignment(SwingConstants.CENTER);
            gbcC.gridy = 1;
            panelCitas.add(lblGestionCitas, gbcC);
            btnVentanaProgCita.setIcon(UIManager.getIcon("FileChooser.newFolderIcon"));
            btnVentanaModCita.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
            btnVentanaHistCitas.setIcon(UIManager.getIcon("FileChooser.listViewIcon"));
            btnVentanaImpCita.setIcon(UIManager.getIcon("FileView.hardDriveIcon"));
            gbcC.gridy = 2;
            panelCitas.add(btnVentanaProgCita, gbcC);
            gbcC.gridy = 3;
            panelCitas.add(btnVentanaModCita, gbcC);
            gbcC.gridy = 4;
            panelCitas.add(btnVentanaImpCita, gbcC);

            // seccion de medicos y personal
            JPanel panelMedicos = new JPanel(new GridBagLayout());
            panelMedicos.setBackground(Color.WHITE);
            panelMedicos.setBorder(BorderFactory.createEmptyBorder());
            GridBagConstraints gbcM = new GridBagConstraints();
            gbcM.insets = new Insets(10, 10, 10, 10);
            gbcM.gridx = 0;
            gbcM.fill = GridBagConstraints.HORIZONTAL;

            gbcM.gridy = 0;
            panelMedicos.add(lblIconoMedicos, gbcM);
            lblGestionMedicos.setFont(new Font("Arial", Font.BOLD, 20));
            gbcM.gridy = 1;
            panelMedicos.add(lblGestionMedicos, gbcM);
            btnVentanaGestMedicos.setIcon(UIManager.getIcon("FileView.computerIcon"));
            btnVentanaGestPersonal.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
            gbcM.gridy = 2;
            panelMedicos.add(btnVentanaGestMedicos, gbcM);
            gbcM.gridy = 3;
            panelMedicos.add(btnVentanaGestPersonal, gbcM);

            // agregar los paneles al gbcPadre padre
            gbcPadre.gridx = 0;
            gbcPadre.weightx = 1;
            this.add(panelPacientes, gbcPadre);
            gbcPadre.gridx = 1;
            this.add(panelCitas, gbcPadre);
            gbcPadre.gridx = 2;
            this.add(panelMedicos, gbcPadre);

            btnVentanaHistCli.addActionListener(e -> {
                ventanaHistorialClinico = new VentanaHistorialClinico();
                ventanaHistorialClinico.setVisible(true);
            });

            btnVentanaRegPaciente.addActionListener(e -> {
                try {
                    ventanaRegistroPaciente = new VentanaRegistroPaciente();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                ventanaRegistroPaciente.setVisible(true);
            });

            btnVentanaGestPaciente.addActionListener(e -> {
                try {
                    ventanaGestionPaciente = new VentanaGestionPaciente();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
                ventanaGestionPaciente.setVisible(true);
            });
        }
    }

    class LienzoHeader extends JPanel{
        private JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN", SwingConstants.CENTER);
        public LienzoHeader (){
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 122, 210));
            this.lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
            this.lblTitulo.setForeground(Color.WHITE);
            this.add(lblTitulo, BorderLayout.CENTER);
        }

        //para hacer que el lienzo ocupe el 12% de alto del contenedor padre
        @Override
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.12);
            return new Dimension(ancho, alto);
        }
    }

    class LienzoFooter extends JPanel{
        private JButton btnSalir = new JButton("Salir");
        private JButton btnAyuda = new JButton("Ayuda");
        public LienzoFooter (){
            super();
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.setBackground(new Color(33, 122, 210));
            this.setForeground(Color.WHITE);
            this.add(btnAyuda);
            this.add(btnSalir);
            this.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));

            btnSalir.addActionListener(e -> {
                dispose();
            });
        }

        @Override
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.08);
            return new Dimension(ancho, alto);
        }
    }
}