package org.sistema.vista.sec_exportar;

import lombok.Getter;
import org.sistema.interfaces.ExportarInterface;
import org.sistema.model.ExportarModel;

import javax.swing.*;
import java.awt.*;

public class VentanaReportesCitas extends JFrame {
    private ExportarInterface ExpModel;
    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter(lienzoCentral);

    public VentanaReportesCitas() {
        super();
        this.ExpModel = new ExportarModel();
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
        private JComboBox cboReportes = new JComboBox();
        private JButton btnPreview = new JButton("Previsualizar");

        private JPanel panelResultados = new JPanel(new BorderLayout());
        private JTextArea txtSReportes = new JTextArea();
        private JScrollPane scpResultados;

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            scpResultados = new JScrollPane(txtSReportes);
            panelResultados.removeAll();
            panelResultados.add(scpResultados);

            GridBagConstraints gbcPadre = new GridBagConstraints();
            gbcPadre.insets = new Insets(10, 20, 10, 10);
            panelResultados.add(scpResultados);

            // tamaño fijo para evitar que colapsen los jtf
            Dimension sizeFijo = new Dimension(200, 25);
            cboReportes.setPreferredSize(sizeFijo);
            cboReportes.setMinimumSize(sizeFijo);
            cboReportes.setMaximumSize(sizeFijo);
            cboReportes.addItem(" ");
            cboReportes.addItem("Citas de los Últimos 3 Meses");
            cboReportes.addItem("Citas por Especialidad");
            cboReportes.addItem("Historial de Citas por Paciente");

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
        public LienzoFooter(LienzoCentral lienzoCentral){
            super();
            this.lienzoCentral = lienzoCentral;
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 122, 210));
            this.setForeground(Color.WHITE);
            this.add(btnSalir, BorderLayout.EAST);
            this.add(btnImprimir, BorderLayout.WEST);
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            btnSalir.addActionListener(e -> dispose());

            btnImprimir.addActionListener(e ->dispose());
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
