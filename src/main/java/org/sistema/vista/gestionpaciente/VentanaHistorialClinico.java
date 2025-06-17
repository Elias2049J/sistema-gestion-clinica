package org.sistema.vista.gestionpaciente;

import org.sistema.entidad.HistorialClinico;
import org.sistema.model.HistorialClinicoModel;
import org.sistema.repository.DataRepository;
import org.sistema.use_case.HistorialClinicoUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaHistorialClinico extends JFrame {

    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter();

    public VentanaHistorialClinico() {
        super();
        this.setTitle("Ver historiales clínicos");
        this.setSize(800, 600);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    static class LienzoCentral extends JPanel {
        //instancio el servicio/modelo de lógica de negocio para pacientes
        private HistorialClinicoUseCase historyModel = new HistorialClinicoModel();

        private JPanel panelBusqueda = new JPanel(new GridBagLayout());

        private JLabel lblNombrePaciente = new JLabel("Ingrese el Nombre o ID del paciente: ");
        private JTextField jtfNombrePaciente = new JTextField(20);
        private JButton btnBuscar = new JButton("Buscar");

        private Object[][] datosTabla;
        private String[] columnasTabla = {"ID", "Paciente", "Cant. Ev. Medicas", "Antecedentes", "Alergias", "Observaciones", "Fecha de creación", "Estado"};
        private JScrollPane scpResultados;
        private JPanel panelResultados = new JPanel(new BorderLayout());
        private DefaultTableModel modeloTabla = new DefaultTableModel();
        private JTable tabla = new JTable();

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbcPadre = new GridBagConstraints();
            gbcPadre.insets = new Insets(10, 20, 10, 10);

            int n;
            if (DataRepository.getHistorialesClinicos() != null && !DataRepository.getHistorialesClinicos().isEmpty()) {
                n = DataRepository.getHistorialesClinicos().size();
                datosTabla = new Object[n][8];
                for (int i = 1; i < n; i++) {
                    HistorialClinico h = DataRepository.getHistorialesClinicos().get(i);
                    datosTabla[i][0] = h.getIdHistorial();
                    datosTabla[i][1] = h.getPaciente().getNombre();
                    datosTabla[i][2] = h.getEvaluacionesMedicas().size();
                    datosTabla[i][3] = h.getAntecedentes();
                    datosTabla[i][4] = h.getAlergias();
                    datosTabla[i][5] = h.getObservaciones();
                    datosTabla[i][6] = h.getFechaCreacion().toString();
                    datosTabla[i][7] = h.getEstado();
                }
            } else datosTabla = new Object[1][8];
            datosTabla[0][0] = "No hay datos disponibles aún";

            modeloTabla = new DefaultTableModel(datosTabla, columnasTabla);
            tabla = new JTable(modeloTabla);
            panelResultados.removeAll();
            scpResultados = new JScrollPane(tabla);
            panelResultados.add(scpResultados);

            // tamaño fijo para evitar que colapsen los jtf
            Dimension sizeFijo = new Dimension(200, 25);
            jtfNombrePaciente.setPreferredSize(sizeFijo);
            jtfNombrePaciente.setMinimumSize(sizeFijo);
            jtfNombrePaciente.setMaximumSize(sizeFijo);

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

            // busqueda por nombre
            gbcBusqueda.gridx = 0;
            gbcBusqueda.gridy = gridy++;
            gbcBusqueda.gridwidth = 1;
            gbcBusqueda.gridheight = 1;
            gbcBusqueda.weighty = 1;
            gbcBusqueda.weightx = 0;
            gbcBusqueda.fill = GridBagConstraints.NONE;
            gbcBusqueda.anchor = GridBagConstraints.EAST;
            lblNombrePaciente.setFont(new Font("Arial", Font.BOLD, 16));
            panelBusqueda.add(lblNombrePaciente, gbcBusqueda);

            gbcBusqueda.gridx = 1;
            gbcBusqueda.gridy = gridy - 1;
            gbcBusqueda.gridwidth = 1;
            gbcBusqueda.gridheight = 1;
            gbcBusqueda.weighty = 1;
            gbcBusqueda.weightx = 0;
            gbcBusqueda.fill = GridBagConstraints.NONE;
            gbcBusqueda.anchor = GridBagConstraints.WEST;
            panelBusqueda.add(jtfNombrePaciente, gbcBusqueda);

            // boton buscar
            gbcBusqueda.gridx = 3;
            gbcBusqueda.gridy = gridy - 1;
            gbcBusqueda.gridwidth = 1;
            gbcBusqueda.gridheight = 1;
            gbcBusqueda.weighty = 1;
            gbcBusqueda.weightx = 0;
            gbcBusqueda.fill = GridBagConstraints.HORIZONTAL;
            gbcBusqueda.anchor = GridBagConstraints.CENTER;
            panelBusqueda.add(btnBuscar, gbcBusqueda);

            // ubicacion del panel busqueda en el grigbaglayout padre
            gbcPadre.gridx = 0;
            gbcPadre.gridy = gridy++;
            gbcPadre.weightx = 1;
            gbcPadre.fill = GridBagConstraints.HORIZONTAL;
            gbcPadre.anchor = GridBagConstraints.NORTH;
            this.add(panelBusqueda, gbcPadre);

            // ubicacion del scroll en el grigbaglayout padre
            gbcPadre.gridx = 0;
            gbcPadre.gridy = gridy++;
            gbcPadre.weightx = 1;
            gbcPadre.weighty = 2;
            gbcPadre.fill = GridBagConstraints.BOTH;
            this.add(panelResultados, gbcPadre);

            //boton para ejecutar la busqueda
            btnBuscar.addActionListener(e -> {
                String txt = jtfNombrePaciente.getText().trim();
                int id;
                if (txt.matches("\\d+") && !txt.matches("\\s*") && !txt.matches("0+")) {
                    id = Integer.parseInt(txt);
                    historyModel.getByPatientID(id);
                }
            });
        }
    }

    class LienzoHeader extends JPanel{
        private JLabel lblTitulo = new JLabel("HISTORIAL CLÍNICO DEL PACIENTE", SwingConstants.CENTER);
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
        public LienzoFooter (){
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 122, 210));
            this.setForeground(Color.WHITE);
            this.add(btnSalir, BorderLayout.EAST);
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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
