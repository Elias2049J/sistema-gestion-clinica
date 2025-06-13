package org.sistema.vista.gestionpaciente;

import lombok.Getter;
import org.sistema.data.listas.DataGestionPacientes;
import org.sistema.entidad.Paciente;
import org.sistema.model.PacienteModel;
import org.sistema.use_case.PacienteUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class VentanaGestionPaciente extends JFrame {
    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter(lienzoCentral);

    public VentanaGestionPaciente() throws FileNotFoundException {
        super();
        this.setTitle("Gestión de Pacientes");
        this.setSize(800, 600);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    static class LienzoCentral extends JPanel {
        //instancio el servicio para gestionar pacientes
        @Getter
        private PacienteUseCase pacienteModel = new PacienteModel();

        private JPanel panelBusqueda = new JPanel(new GridBagLayout());

        private JLabel lblBusqueda = new JLabel("Ingrese el Nombre del paciente: ");
        private JTextField jtfNombrePaciente = new JTextField(20);
        private JButton btnBuscar = new JButton("Buscar");

        private JPanel panelResultados = new JPanel(new BorderLayout());
        //columnas de la tabla
        private String[] columnasTabla = {"ID", "Nombre", "Apellido", "Edad", "DNI", "Dirección", "Teléfono", "Estado"};
        private Object[][] datosTabla;
        @Getter
        private DefaultTableModel modeloTabla;
        private JTable tablaResultados;
        private JScrollPane scpResultados;

        public LienzoCentral() throws FileNotFoundException {
            super();
            this.setLayout(new GridBagLayout());

            // llenar los datos de la tabla con datos de pacientes recorriendo la lista
            int n = DataGestionPacientes.getPacientes().size();
            datosTabla = new Object[n][8];
            for (int i = 0; i < n; i++) {
                Paciente p = DataGestionPacientes.getPacientes().get(i);
                datosTabla[i][0] = p.getIdPaciente();
                datosTabla[i][1] = p.getNombre();
                datosTabla[i][2] = p.getApellido();
                datosTabla[i][3] = p.getEdad();
                datosTabla[i][4] = p.getDni();
                datosTabla[i][5] = p.getDireccion();
                datosTabla[i][6] = p.getTelefono();
                datosTabla[i][7] = p.getEstado();
            }
            modeloTabla = new DefaultTableModel(datosTabla, columnasTabla);
            tablaResultados = new JTable(modeloTabla);
            scpResultados = new JScrollPane(tablaResultados);
            panelResultados.removeAll();
            panelResultados.add(scpResultados);

            GridBagConstraints gbcPadre = new GridBagConstraints();
            gbcPadre.insets = new Insets(10, 20, 10, 10);
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

            // ubicacion del panel resultados en el grigbaglayout padre
            gbcPadre.gridx = 0;
            gbcPadre.gridy = gridy++;
            gbcPadre.weightx = 1;
            gbcPadre.weighty = 2;
            gbcPadre.fill = GridBagConstraints.BOTH;
            this.add(panelResultados, gbcPadre);

            btnBuscar.addActionListener(e -> {
            });
        }
    }

    class LienzoHeader extends JPanel{
        private JLabel lblTitulo = new JLabel("PANEL DE ADMINISTRACION DE PACIENTES", SwingConstants.CENTER);
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

        private JButton btnGuardar = new JButton("Guardar cambios");
        private LienzoCentral lienzoCentral;
        private JButton btnSalir = new JButton("Salir");
        public LienzoFooter(LienzoCentral lienzoCentral){
            super();
            this.lienzoCentral = lienzoCentral;
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 122, 210));
            this.setForeground(Color.WHITE);
            this.add(btnSalir, BorderLayout.EAST);
            this.add(btnGuardar, BorderLayout.WEST);
            this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            btnSalir.addActionListener(e -> dispose());

            btnGuardar.addActionListener(e -> {
                DefaultTableModel modeloTabla = lienzoCentral.getModeloTabla();
                List<Paciente> listaModificada = new ArrayList<>();
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    try {
                        Integer id = Integer.parseInt(modeloTabla.getValueAt(i, 0).toString().trim());
                        String nombre = modeloTabla.getValueAt(i, 1).toString().trim();
                        String apellido = modeloTabla.getValueAt(i, 2).toString().trim();
                        String edadStr = modeloTabla.getValueAt(i, 3).toString().trim();
                        String dni = modeloTabla.getValueAt(i, 4).toString().trim();
                        String direccion = modeloTabla.getValueAt(i, 5).toString().trim();
                        String telefono = modeloTabla.getValueAt(i, 6).toString().trim();
                        String estado = modeloTabla.getValueAt(i, 7).toString().trim();

                        // validaciones
                        if (nombre.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío (fila " + (i+1) + ")", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (apellido.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "El apellido no puede estar vacío (fila " + (i+1) + ")", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        int edad;
                        try {
                            edad = Integer.parseInt(edadStr);
                            if (edad <= 0) throw new NumberFormatException();
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(this, "Edad inválida en la fila " + (i+1) + ". Debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (dni.isEmpty() || !dni.matches("\\d{8}")) {
                            JOptionPane.showMessageDialog(this, "DNI inválido en la fila " + (i+1) + ". Debe ser numérico y de 8 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (direccion.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "La dirección no puede estar vacía (fila " + (i+1) + ")", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        if (telefono.isEmpty() || !telefono.matches("\\d{9}")) {
                            JOptionPane.showMessageDialog(this, "Teléfono inválido en la fila " + (i+1) + ". Debe ser numérico y de 9 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        //se crea un paciente por cada iteracion
                        Paciente paciente = new Paciente(id, nombre, apellido, edad, dni, direccion, telefono, estado);
                        // cada paciente se agrega a la lista
                        listaModificada.add(paciente);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Error en los datos de la tabla (fila " + (i+1) + "): " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                //para guardar todas las modificaciones de la tabla
                if(lienzoCentral.getPacienteModel().guardarCambiosDesdeTabla(listaModificada)) {
                    JOptionPane.showMessageDialog(lienzoCentral, "Se guardaron los datos correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(lienzoCentral, "Hubo un error al guardar los datos modificados", "Error", JOptionPane.ERROR_MESSAGE);
                }
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
