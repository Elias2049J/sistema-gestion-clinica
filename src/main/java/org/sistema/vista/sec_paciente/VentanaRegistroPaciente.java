package org.sistema.vista.sec_paciente;

import org.sistema.entidad.Paciente;
import org.sistema.use_case.CrudUseCase;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VentanaRegistroPaciente extends JFrame {
    private CrudUseCase<Paciente, Integer, String> crudPacienteModel;
    private LienzoHeader lienzoHeader;
    private LienzoCentral lienzoCentral;
    private LienzoFooter lienzoFooter;

    public VentanaRegistroPaciente(CrudUseCase<Paciente, Integer, String> crudPacienteModel) {
        super();
        this.crudPacienteModel = crudPacienteModel;
        this.lienzoHeader = new LienzoHeader();
        this.lienzoCentral = new LienzoCentral();
        this.lienzoFooter = new LienzoFooter();
        this.setTitle("Registro de Pacientes");
        this.setSize(600, 500);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoCentral extends JPanel {
        private JLabel lblRegistro = new JLabel("INGRESAR DATOS", SwingConstants.CENTER);

        private JLabel lblNombre = new JLabel("Nombres:");
        private JTextField jtfNombre = new JTextField(20);
        private JLabel lblApellido = new JLabel("Apellidos:");
        private JTextField jtfApellido = new JTextField(20);
        private JLabel lblEdad = new JLabel("Edad:");
        private JTextField jtfEdad = new JTextField(20);
        private JLabel lblDni = new JLabel("DNI:");
        private JTextField jtfDni = new JTextField(20);
        private JLabel lblDireccion = new JLabel("Dirección:");
        private JTextField jtfDireccion = new JTextField(20);
        private JLabel lblTlf = new JLabel("Teléfono:");
        private JTextField jtfTlf = new JTextField(20);

        private JButton btnRegistrar = new JButton("Registrar");

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // subtitulo
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            lblRegistro.setFont(new Font("Arial", Font.BOLD, 18));
            this.add(lblRegistro, gbc);

            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridwidth = 1;

            // labels de los campos
            gbc.gridx = 0;
            gbc.gridy = 1;
            this.add(lblNombre, gbc);
            gbc.gridy++;
            this.add(lblApellido, gbc);
            gbc.gridy++;
            this.add(lblEdad, gbc);
            gbc.gridy++;
            this.add(lblDni, gbc);
            gbc.gridy++;
            this.add(lblDireccion, gbc);
            gbc.gridy++;
            this.add(lblTlf, gbc);

            // columna vacia para separar
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.gridheight = 7;
            this.add(Box.createHorizontalStrut(30), gbc);
            gbc.gridheight = 1;

            // campos de texto
            gbc.gridx = 2;
            gbc.gridy = 1;
            this.add(jtfNombre, gbc);
            gbc.gridy++;
            this.add(jtfApellido, gbc);
            gbc.gridy++;
            this.add(jtfEdad, gbc);
            gbc.gridy++;
            this.add(jtfDni, gbc);
            gbc.gridy++;
            this.add(jtfDireccion, gbc);
            gbc.gridy++;
            this.add(jtfTlf, gbc);

            // boton registrar
            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            btnRegistrar.setBackground(new Color(33, 122, 210));
            btnRegistrar.setForeground(Color.WHITE);
            this.add(btnRegistrar, gbc);

            btnRegistrar.addActionListener(e -> {
                String nombre = jtfNombre.getText().trim();
                String apellido = jtfApellido.getText().trim();
                String edadStr = jtfEdad.getText().trim();
                String dni = jtfDni.getText().trim();
                String direccion = jtfDireccion.getText().trim();
                String telefono = jtfTlf.getText().trim();

                // validaciones para todos los campos
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jtfNombre.requestFocus();
                    return;
                }
                if (apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El apellido no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
                    jtfApellido.requestFocus();
                    return;
                }

                int edad;
                try {
                    edad = Integer.parseInt(edadStr);
                    if (edad <= 0) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Edad inválida. Debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    jtfEdad.requestFocus();
                    return;
                }

                if (dni.isEmpty() || !dni.matches("\\d{8}")) {
                    JOptionPane.showMessageDialog(this, "DNI inválido. Debe ser numérico y de 8 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
                    jtfDni.requestFocus();
                    return;
                }

                if (direccion.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "La dirección no puede estar vacía", "Error", JOptionPane.ERROR_MESSAGE);
                    jtfDireccion.requestFocus();
                    return;
                }

                if (telefono.isEmpty() || !telefono.matches("\\d{9}")) {
                    JOptionPane.showMessageDialog(this, "Teléfono inválido. Debe ser numérico y de 9 dígitos", "Error", JOptionPane.ERROR_MESSAGE);
                    jtfTlf.requestFocus();
                    return;
                }

                Paciente p = new Paciente();
                p.setNombre(nombre);
                p.setApellido(apellido);
                p.setEdad(edad);
                p.setDni(dni);
                p.setDireccion(direccion);
                p.setTelefono(telefono);
                p.setHistorialCitas(new ArrayList<>());

                if (crudPacienteModel.create(p)) {
                    JOptionPane.showMessageDialog(this, "Paciente registrado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar el paciente. Verifique los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    class LienzoHeader extends JPanel{
        private JLabel lblTitulo = new JLabel("REGISTRAR NUEVO PACIENTE EN EL SISTEMA", SwingConstants.CENTER);

        public LienzoHeader() {
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 122, 210));
            this.lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
            this.lblTitulo.setForeground(Color.WHITE);
            this.add(lblTitulo, BorderLayout.CENTER);
        }

        @Override
        public Dimension getPreferredSize() {
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            int alto = (int) (contenedorPadre.getHeight() * 0.12);
            return new Dimension(ancho, alto);
        }
    }

    class LienzoFooter extends JPanel{
        private JButton btnSalir = new JButton("Salir");

        public LienzoFooter() {
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
        public Dimension getPreferredSize() {
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            int alto = (int) (contenedorPadre.getHeight() * 0.08);
            return new Dimension(ancho, alto);
        }
    }
}
