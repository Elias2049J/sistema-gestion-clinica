package org.sistema.vista.gestionpaciente;

import org.sistema.entidad.HistorialClinico;
import org.sistema.entidad.Paciente;
import org.sistema.model.PacienteModel;
import org.sistema.use_case.PacienteUseCase;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

public class VentanaHistorialClinico extends JFrame {

    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter();

    public VentanaHistorialClinico() throws FileNotFoundException {
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
        private PacienteUseCase pacienteModel = new PacienteModel();

        private JPanel panelBusqueda = new JPanel(new GridBagLayout());

        private JLabel lblNombrePaciente = new JLabel("Ingrese el Nombre del paciente: ");
        private JTextField jtfNombrePaciente = new JTextField(20);
        private JButton btnBuscarPorNombre = new JButton("Buscar");

        private JScrollPane scpResultados = new JScrollPane();
        private JTextArea txtS = new JTextArea();

        public LienzoCentral() throws FileNotFoundException {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbcPadre = new GridBagConstraints();
            gbcPadre.insets = new Insets(10, 20, 10, 10);

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
            panelBusqueda.add(btnBuscarPorNombre, gbcBusqueda);

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
            txtS.setMargin(new Insets(20, 20, 20, 20));
            txtS.setFont(new Font("Consolas", Font.BOLD, 16));
            txtS.setEditable(false);
            scpResultados.setViewportView(txtS);
            this.add(scpResultados, gbcPadre);

            //boton para ejecutar la busqueda
            btnBuscarPorNombre.addActionListener(e -> {
                //se obtiene el valor ingresado y se parsea a integer
                Integer idPacienteIngresado = Integer.parseInt(jtfNombrePaciente.getText());
                //se usa el servicio para buscar
                HistorialClinico elementosHistorial = pacienteModel.obtenerHistorialClinico(idPacienteIngresado);
                if (elementosHistorial != null) {
                    imprimirSeccion(
                        elementosHistorial.getIdHistorial(),
                        elementosHistorial.getPaciente(),
                        elementosHistorial.getFechaCreacion(),
                        elementosHistorial.getDiagnosticos(),
                        elementosHistorial.getTratamientos(),
                        elementosHistorial.getConsultas(),
                        elementosHistorial.getAntecedentes(),
                        elementosHistorial.getAlergias(),
                        elementosHistorial.getObservaciones()
                    );
                }
            });
        }

        public boolean imprimirLinea(String texto) {
            txtS.append(texto + "\n");
            return false;
        }

        public boolean imprimirSeccion(Integer id, Paciente paciente, LocalDate fecha, List<?> diag, List<?> trat, List<?> cons, String ant, String alrg, String obs) {
            txtS.setText("");
            imprimirLinea(completarEspacios("ID Historial")+ ": " + id);
            imprimirLinea(completarEspacios("Paciente")+ ": " + paciente);
            imprimirLinea(completarEspacios("Fecha de Creacion")+ ": " + fecha);
            imprimirLinea(completarEspacios("Diagnosticos")+ ": " + diag) ;
            imprimirLinea(completarEspacios("Tratamientos")+ ": " + trat);
            imprimirLinea(completarEspacios("Consultas")+ ":"+ cons);
            imprimirLinea(completarEspacios("Antecedentes")+ ":"+ ant);
            imprimirLinea(completarEspacios("Alergias")+ ":"+ alrg);
            imprimirLinea(completarEspacios("Observaciones")+ ":"+ obs);
            txtS.append("\n");
            return false;
        }

        public String completarEspacios(String str) {
            StringBuilder sb = new StringBuilder(str);
            while (sb.length() < 20) {
                sb.append(" ");
            }
            return sb.toString();
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
