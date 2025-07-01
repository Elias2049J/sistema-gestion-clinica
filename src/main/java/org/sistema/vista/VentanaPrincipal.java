package org.sistema.vista;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;
import org.sistema.use_case.CitaUseCase;
import org.sistema.use_case.CrudUseCase;
import org.sistema.model.*;
import org.sistema.vista.sec_citas.VentanaGestionCitas;
import org.sistema.vista.sec_citas.VentanaRegCita;
import org.sistema.vista.sec_exportar.VentanaReportesPac;
import org.sistema.vista.sec_exportar.VentanaReportesCitas;
import org.sistema.vista.sec_paciente.VentanaGestionPaciente;
import org.sistema.vista.sec_paciente.VentanaHistorialCitas;
import org.sistema.vista.sec_paciente.VentanaRegistroPaciente;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class VentanaPrincipal extends JFrame implements Serializable {
    private CrudUseCase<Paciente, Integer, String> crudPacienteModel;
    private CrudUseCase<Cita, Integer, String> crudCitaModel;
    private CitaUseCase citaModel;

    private VentanaGestionCitas ventanaGestionCitas;
    private VentanaGestionPaciente ventanaGestionPaciente;
    private VentanaRegistroPaciente ventanaRegistroPaciente;
    private VentanaHistorialCitas ventanaHistorialCitas;
    private VentanaAyuda ventanaAyuda;
    private VentanaReportesPac ventanaReportesPac;
    private VentanaReportesCitas ventanaReportesCitas;
    private VentanaRegCita ventanaRegCita;

    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter();

    public VentanaPrincipal(CrudUseCase<Paciente, Integer, String> crudPacienteModel, CrudUseCase<Cita, Integer, String> crudCitaModel) throws HeadlessException {
        super();
        this.crudPacienteModel = crudPacienteModel;
        this.crudCitaModel = crudCitaModel;
        this.citaModel = new CitaModel();
        this.setTitle("Sistema Clínico");
        this.setSize(960, 514);
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
        private JLabel lblReportes = new JLabel("Reportería");

        private JButton btnVentanaRegPaciente = new JButton("Registrar Paciente");
        private JButton btnVentanaGestPaciente = new JButton("Gestionar Pacientes");

        private JButton btnVentanaProgCita = new JButton("Programar Cita");
        private JButton btnVentanaModCita = new JButton("Modificar o Cancelar Cita");

        private JButton btnVentanaReportesPac = new JButton("Reportes de Pacientes");
        private JButton btnVentanaReportesCitas = new JButton("Reportes de Citas");

        // imagenes para las secciones
        private ImageIcon iconoPacientes = new ImageIcon("src/main/resources/paciente.png");
        private ImageIcon iconoCitas = new ImageIcon("src/main/resources/cita.png");
        private ImageIcon iconoReportes = new ImageIcon("src/main/resources/reportes.png");

        private Image imagenPacientes = iconoPacientes.getImage().getScaledInstance(120, 70, Image.SCALE_SMOOTH);
        private Image imagenCitas = iconoCitas.getImage().getScaledInstance(120, 90, Image.SCALE_SMOOTH);
        private Image imagenReportes = iconoReportes.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

        private JLabel lblIconoPacientes = new JLabel(new ImageIcon(imagenPacientes));
        private JLabel lblIconoCitas = new JLabel(new ImageIcon(imagenCitas));
        private JLabel lbliconoReportes = new JLabel(new ImageIcon(imagenReportes));

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
            gbcP.gridy++;
            panelPacientes.add(btnVentanaRegPaciente, gbcP);
            gbcP.gridy++;
            panelPacientes.add(btnVentanaGestPaciente, gbcP);
            gbcP.gridy++;
            panelPacientes.add(Box.createVerticalStrut(25), gbcP);

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
            gbcC.gridy++;
            panelCitas.add(lblGestionCitas, gbcC);
            btnVentanaProgCita.setIcon(UIManager.getIcon("FileChooser.newFolderIcon"));
            btnVentanaModCita.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
            gbcC.gridy++;
            panelCitas.add(btnVentanaProgCita, gbcC);
            gbcC.gridy++;
            panelCitas.add(btnVentanaModCita, gbcC);
            gbcC.fill = GridBagConstraints.HORIZONTAL;
            gbcC.gridy++;
            panelCitas.add(Box.createVerticalStrut(25), gbcC);

            // seccion de reportes
            JPanel panelReportes = new JPanel(new GridBagLayout());
            panelReportes.setBackground(Color.WHITE);
            panelReportes.setBorder(BorderFactory.createEmptyBorder());
            GridBagConstraints gbcR = new GridBagConstraints();
            gbcR.insets = new Insets(10, 10, 10, 10);
            gbcR.gridx = 0;
            gbcR.fill = GridBagConstraints.HORIZONTAL;

            gbcR.gridy = 0;
            panelReportes.add(lbliconoReportes, gbcR);
            lblReportes.setFont(new Font("Arial", Font.BOLD, 20));
            lblReportes.setHorizontalAlignment(SwingConstants.CENTER);
            gbcR.gridy = 1;
            panelReportes.add(lblReportes, gbcR);
            btnVentanaReportesPac.setIcon(UIManager.getIcon("FileView.computerIcon"));
            btnVentanaReportesCitas.setIcon(UIManager.getIcon("FileView.floppyDriveIcon"));
            gbcR.gridy = 2;
            panelReportes.add(btnVentanaReportesPac, gbcR);
            gbcR.gridy = 3;
            panelReportes.add(btnVentanaReportesCitas, gbcR);
            gbcR.gridy = 4;
            gbcR.fill = GridBagConstraints.HORIZONTAL;
            panelReportes.add(Box.createVerticalStrut(15), gbcR);
            gbcR.gridy = 5;
            panelReportes.add(Box.createVerticalStrut(15), gbcR);

            // agregar los paneles al gbcPadre padre
            gbcPadre.gridx = 0;
            gbcPadre.weightx = 1;
            this.add(panelPacientes, gbcPadre);
            gbcPadre.gridx = 1;
            this.add(panelCitas, gbcPadre);
            gbcPadre.gridx = 2;
            this.add(panelReportes, gbcPadre);

            btnVentanaRegPaciente.addActionListener(e -> {
                ventanaRegistroPaciente = new VentanaRegistroPaciente(crudPacienteModel);
                ventanaRegistroPaciente.setVisible(true);
            });

            btnVentanaGestPaciente.addActionListener(e -> {
                ventanaGestionPaciente = new VentanaGestionPaciente(crudPacienteModel, crudCitaModel);
                ventanaGestionPaciente.setVisible(true);
            });

            btnVentanaReportesPac.addActionListener(e -> {
                ventanaReportesPac = new VentanaReportesPac(crudPacienteModel);
                ventanaReportesPac.setVisible(true);
            });

            btnVentanaReportesCitas.addActionListener(e -> {
                ventanaReportesCitas = new VentanaReportesCitas(crudCitaModel);
                ventanaReportesCitas.setVisible(true);
            });

            btnVentanaProgCita.addActionListener(e-> {
                ventanaRegCita = new VentanaRegCita(crudCitaModel, crudPacienteModel, citaModel);
                ventanaRegCita.setVisible(true);
            });

            btnVentanaModCita.addActionListener(e-> {
                ventanaGestionCitas = new VentanaGestionCitas(crudPacienteModel, crudCitaModel);
                ventanaGestionCitas.setVisible(true);
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

            btnAyuda.addActionListener(e -> {
                ventanaAyuda = new VentanaAyuda();
                ventanaAyuda.setVisible(true);
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