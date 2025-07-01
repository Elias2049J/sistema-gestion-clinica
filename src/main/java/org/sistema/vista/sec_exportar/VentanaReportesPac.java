package org.sistema.vista.sec_exportar;

import lombok.Getter;
import org.sistema.entidad.Paciente;
import org.sistema.use_case.CrudUseCase;
import org.sistema.use_case.ReporteUseCase;
import org.sistema.model.ReportePacienteModel;
import org.sistema.model.ReporteTerceraEdadModel;
import org.sistema.persistencia.PersistenciaRepPac;
import org.sistema.persistencia.PersistenciaRepTerceraEdad;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaReportesPac extends JFrame {
    private ReporteUseCase<Paciente> reporteTerceraEdadModel;
    private CrudUseCase<Paciente, Integer, String> crudPacienteModel;
    private ReporteUseCase<Paciente> reportePacienteModel;
    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter(lienzoCentral);

    public VentanaReportesPac(CrudUseCase<Paciente, Integer, String> crudPacienteModel) {
        super();
        this.crudPacienteModel = crudPacienteModel;
        this.reportePacienteModel = new ReportePacienteModel(crudPacienteModel, new PersistenciaRepPac());
        this.reporteTerceraEdadModel = new ReporteTerceraEdadModel(crudPacienteModel, new PersistenciaRepTerceraEdad());
        this.setTitle("Reportes de Pacientes");
        this.setSize(800, 600);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    @Getter
    class LienzoCentral extends JPanel {
        private JPanel panelBusqueda = new JPanel(new GridBagLayout());
        private JLabel lblBusqueda = new JLabel("Seleccione el reporte: ");
        private JComboBox<String> cboReportes = new JComboBox<>();
        private JButton btnPreview = new JButton("Previsualizar");

        private JPanel panelResultados = new JPanel(new BorderLayout());
        private JTextArea txtSReportes = new JTextArea();
        private JScrollPane scpResultados;

        private int reporteElegido = 0;

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            txtSReportes.setFont(new Font("Monospaced", Font.PLAIN, 18));
            txtSReportes.setMargin(new Insets(20, 20, 20, 20));
            txtSReportes.setEditable(false);
            scpResultados = new JScrollPane(txtSReportes);
            panelResultados.removeAll();
            panelResultados.add(scpResultados);

            GridBagConstraints gbcPadre = new GridBagConstraints();
            gbcPadre.insets = new Insets(10, 20, 10, 10);

            // tamaño fijo para evitar que colapsen los jtf
            Dimension sizeFijo = new Dimension(200, 25);
            cboReportes.setPreferredSize(sizeFijo);
            cboReportes.setMinimumSize(sizeFijo);
            cboReportes.setMaximumSize(sizeFijo);
            cboReportes.addItem("Seleccione un reporte");
            cboReportes.addItem("Pacientes de la tercera edad");
            cboReportes.addItem("Lista Completa de Pacientes");

            // elementos del panel de busqueda
            GridBagConstraints gbcBusqueda = new GridBagConstraints();
            gbcBusqueda.insets = new Insets(2, 2, 2, 2);
            gbcBusqueda.fill = GridBagConstraints.BOTH;
            gbcBusqueda.weightx = 1.0;

            int gridy = 0;
            // fila vacia para separar el subtitulo
            gbcBusqueda.gridx = 0;
            gbcBusqueda.gridy = gridy++;
            gbcBusqueda.gridwidth = 5;
            gbcBusqueda.gridheight = 1;
            gbcBusqueda.weighty = 0;
            gbcBusqueda.weightx = 0;
            gbcBusqueda.fill = GridBagConstraints.HORIZONTAL;
            panelBusqueda.add(Box.createVerticalStrut(10), gbcBusqueda);

            // lbl busqueda
            gbcBusqueda.gridx = 0;
            gbcBusqueda.gridy = gridy++;
            gbcBusqueda.gridwidth = 1;
            gbcBusqueda.gridheight = 1;
            gbcBusqueda.weighty = 1;
            gbcBusqueda.weightx = 0;
            gbcBusqueda.fill = GridBagConstraints.NONE;
            gbcBusqueda.anchor = GridBagConstraints.EAST;
            lblBusqueda.setFont(new Font("Arial", Font.BOLD, 16));
            panelBusqueda.add(lblBusqueda, gbcBusqueda);

            //campo de búsqueda
            gbcBusqueda.gridx = 1;
            gbcBusqueda.gridy = gridy - 1;
            gbcBusqueda.gridwidth = 1;
            gbcBusqueda.gridheight = 1;
            gbcBusqueda.weighty = 1;
            gbcBusqueda.weightx = 0;
            gbcBusqueda.fill = GridBagConstraints.NONE;
            gbcBusqueda.anchor = GridBagConstraints.WEST;
            panelBusqueda.add(cboReportes, gbcBusqueda);

            // boton buscar
            gbcBusqueda.gridx = 3;
            gbcBusqueda.gridy = gridy - 1;
            gbcBusqueda.gridwidth = 1;
            gbcBusqueda.gridheight = 1;
            gbcBusqueda.weighty = 1;
            gbcBusqueda.weightx = 0;
            gbcBusqueda.fill = GridBagConstraints.HORIZONTAL;
            gbcBusqueda.anchor = GridBagConstraints.CENTER;
            panelBusqueda.add(btnPreview, gbcBusqueda);

            // ubicacion del panel busqueda en el grigbaglayout padre
            gbcPadre.gridx = 0;
            gbcPadre.gridy = gridy++;
            gbcPadre.weightx = 1;
            gbcPadre.fill = GridBagConstraints.HORIZONTAL;
            gbcPadre.anchor = GridBagConstraints.NORTH;
            this.add(panelBusqueda, gbcPadre);

            // ubicacion del panel resultados en el grigbaglayout padre
            gbcPadre.gridx = 0;
            gbcPadre.gridy = gridy++;
            gbcPadre.weightx = 1;
            gbcPadre.weighty = 2;
            gbcPadre.fill = GridBagConstraints.BOTH;
            this.add(panelResultados, gbcPadre);

            btnPreview.addActionListener(e -> {
                txtSReportes.setText("");
                reporteElegido = cboReportes.getSelectedIndex();

                if (reporteElegido == 0) {
                    JOptionPane.showMessageDialog(this,
                            "Por favor seleccione un tipo de reporte",
                            "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                List<String> lineasReporte;

                if (reporteElegido == 1) {
                    lineasReporte = reporteTerceraEdadModel.generarReporte();
                } else {
                    lineasReporte = reportePacienteModel.generarReporte();
                }
                for (String linea : lineasReporte) {
                    txtSReportes.append(linea + "\n");
                }
            });
        }

    }

    class LienzoHeader extends JPanel {
        private JLabel lblTitulo = new JLabel("PANEL DE REPORTES DE PACIENTES", SwingConstants.CENTER);
        public LienzoHeader() {
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 122, 210));
            this.lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
            this.lblTitulo.setForeground(Color.WHITE);
            this.add(lblTitulo, BorderLayout.CENTER);
        }

        @Override
        public Dimension getPreferredSize() {
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.12);
            return new Dimension(ancho, alto);
        }
    }

    class LienzoFooter extends JPanel {
        private JButton btnImprimir = new JButton("Imprimir Reporte");
        private JButton btnSalir = new JButton("Salir");

        public LienzoFooter(LienzoCentral lienzoCentral) {
            super();
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.setBackground(new Color(33, 122, 210));
            this.setForeground(Color.WHITE);
            this.add(btnImprimir);
            this.add(btnSalir);
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            btnSalir.addActionListener(e -> dispose());

            btnImprimir.addActionListener(e -> {
                int reporteSeleccionado = lienzoCentral.getReporteElegido();

                if (reporteSeleccionado == 0) {
                    JOptionPane.showMessageDialog(lienzoCentral, "Visualice un reporte antes de imprimir", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (reporteSeleccionado == 1) {
                    if (reporteTerceraEdadModel.imprimirReporte()) {
                        JOptionPane.showMessageDialog(lienzoCentral, "Impresion exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(lienzoCentral, "Error al imprimir", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    return;
                }

                if (reporteSeleccionado == 2) {
                    if (reportePacienteModel.imprimirReporte()) {
                        JOptionPane.showMessageDialog(lienzoCentral, "Impresion exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(lienzoCentral, "Error al imprimir", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        @Override
        public Dimension getPreferredSize() {
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.08);
            return new Dimension(ancho, alto);
        }
    }
}
