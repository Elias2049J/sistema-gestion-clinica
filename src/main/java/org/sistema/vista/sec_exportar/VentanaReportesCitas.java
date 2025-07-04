package org.sistema.vista.sec_exportar;

import lombok.Getter;
import org.sistema.entidad.Cita;
import org.sistema.model.ReporteCitaModel;
import org.sistema.model.ReporteCitasCanceladasModel;
import org.sistema.persistencia.PersistenciaRepCita;
import org.sistema.persistencia.PersistenciaRepCitasCanceladas;
import org.sistema.use_case.CrudUseCase;
import org.sistema.use_case.ReporteUseCase;
import org.sistema.model.ReporteCita3MesesModel;
import org.sistema.persistencia.PersistenciaRepCita3Meses;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaReportesCitas extends JFrame {
    private CrudUseCase<Cita, Integer, String> crudCitaModel;
    private ReporteUseCase<Cita> reporte3MesesModel;
    private ReporteUseCase<Cita> reporteCitaModel;
    private ReporteUseCase<Cita> reporteCitasCanceladasModel;
    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter(lienzoCentral);

    public VentanaReportesCitas(CrudUseCase<Cita, Integer, String> crudCitaModel) {
        super();
        this.crudCitaModel = crudCitaModel;
        this.reporte3MesesModel = new ReporteCita3MesesModel(crudCitaModel, new PersistenciaRepCita3Meses());
        this.reporteCitaModel = new ReporteCitaModel(crudCitaModel, new PersistenciaRepCita());
        this.reporteCitasCanceladasModel = new ReporteCitasCanceladasModel(crudCitaModel, new PersistenciaRepCitasCanceladas());
        this.setTitle("Reportes de Citas");
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
            cboReportes.addItem("Citas de los Últimos 3 Meses");
            cboReportes.addItem("Total de Citas");
            cboReportes.addItem("Citas Canceladas");

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
            //campo de buaqeuda
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
                List<String> contenidoReporte;
                if (reporteElegido == 1) {
                    contenidoReporte = reporte3MesesModel.generarReporte();
                } else if (reporteElegido == 2) {
                    contenidoReporte = reporteCitaModel.generarReporte();
                } else if (reporteElegido == 3) {
                    contenidoReporte = reporteCitasCanceladasModel.generarReporte();
                } else {
                    contenidoReporte = java.util.Collections.singletonList("Reporte no implementado");
                }
                for (String linea : contenidoReporte) {
                    txtSReportes.append(linea + "\n");
                }
            });
        }
    }

    class LienzoHeader extends JPanel {
        private JLabel lblTitulo = new JLabel("PANEL DE REPORTERÍAS POR CITAS", SwingConstants.CENTER);
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
        private JButton btnImprimir = new JButton("Imprimir Reporte");
        private LienzoCentral lienzoCentral;
        private JButton btnSalir = new JButton("Salir");
        private JButton btnAyuda = new JButton("Ayuda");

        public LienzoFooter(LienzoCentral lienzoCentral){
            super();
            this.lienzoCentral = lienzoCentral;
            this.setLayout(new FlowLayout(FlowLayout.RIGHT));
            this.setBackground(new Color(33, 122, 210));
            this.setForeground(Color.WHITE);
            this.add(btnImprimir);
            this.add(btnAyuda);
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
                    if (reporte3MesesModel.imprimirReporte()) {
                        JOptionPane.showMessageDialog(lienzoCentral, "Impresión exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(lienzoCentral, "Error al imprimir", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (reporteSeleccionado == 2) {
                    if (reporteCitaModel.imprimirReporte()) {
                        JOptionPane.showMessageDialog(lienzoCentral, "Impresión exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(lienzoCentral, "Error al imprimir", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else if (reporteSeleccionado == 3) {
                    if (reporteCitasCanceladasModel.imprimirReporte()) {
                        JOptionPane.showMessageDialog(lienzoCentral, "Impresión exitosa", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(lienzoCentral, "Error al imprimir", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(lienzoCentral, "Impresión no implementada para este reporte", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });

            btnAyuda.addActionListener(e -> {
                JOptionPane.showMessageDialog(lienzoCentral,
                    "Ventana de Reportes de Citas:\n\n" +
                    "1. Seleccione el tipo de reporte del menú desplegable:\n" +
                    "   - Citas de los Últimos 3 Meses: Muestra todas las citas programadas en los últimos 3 meses.\n" +
                    "   - Total de Citas: Muestra todas las citas registradas en el sistema.\n" +
                    "   - Citas Canceladas: Muestra todas las citas que han sido canceladas.\n\n" +
                    "2. Haga clic en 'Previsualizar' para ver el reporte en pantalla.\n\n" +
                    "3. Utilice 'Imprimir Reporte' para guardar el reporte en un archivo de texto.\n\n" +
                    "Nota: Debe previsualizar un reporte antes de imprimirlo.",
                    "Ayuda - Reportes de Citas", JOptionPane.INFORMATION_MESSAGE);
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
