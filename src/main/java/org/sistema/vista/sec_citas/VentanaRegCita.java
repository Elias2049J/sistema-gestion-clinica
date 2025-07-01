package org.sistema.vista.sec_citas;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;
import org.sistema.use_case.CitaUseCase;
import org.sistema.use_case.CrudUseCase;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VentanaRegCita extends JFrame {
    private CrudUseCase<Cita, Integer, String> crudCitaModel;
    private CrudUseCase<Paciente, Integer, String> crudPacienteModel;
    private CitaUseCase citaModel;
    private LienzoHeader lienzoHeader;
    private LienzoCentral lienzoCentral;
    private LienzoFooter lienzoFooter;

    public VentanaRegCita(CrudUseCase<Cita, Integer, String> crudCitaModel, CrudUseCase<Paciente, Integer, String> crudPacienteModel, CitaUseCase citaModel) {
        super();
        this.crudCitaModel = crudCitaModel;
        this.crudPacienteModel = crudPacienteModel;
        this.citaModel = citaModel;
        this.lienzoHeader = new LienzoHeader();
        this.lienzoCentral = new LienzoCentral();
        this.lienzoFooter = new LienzoFooter();
        this.setTitle("Programación de Citas");
        this.setSize(600, 500);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoCentral extends JPanel {
        private JLabel lblRegistro = new JLabel("Programar Cita", SwingConstants.CENTER);

        private JLabel lblPaciente = new JLabel("DNI del Paciente:");
        private JLabel lblNombrePaciente = new JLabel("Nombre del Paciente:");
        private JLabel lblMedico = new JLabel("Médico:");
        private JLabel lblEspecialidad = new JLabel("Especialidad:");
        private JLabel lblFecha = new JLabel("Fecha (dd/MM/yyyy):");
        private JLabel lblHora = new JLabel("Hora (HH:mm):");
        private JLabel lblCosto = new JLabel("Costo:");

        private JTextField jtfDniPaciente = new JTextField(20);
        private JButton btnBuscarPaciente = new JButton("Buscar");
        private JTextField jtfNombrePaciente = new JTextField(20);
        private JTextField jtfMedico = new JTextField(20);
        private JComboBox<String> cboEspecialidad = new JComboBox<>();
        private JTextField jtfFecha = new JTextField(20);
        private JTextField jtfHora = new JTextField(20);
        private JTextField jtfCosto = new JTextField(20);

        private JButton btnRegistrar = new JButton("Registrar Cita");
        private Paciente pacienteElegido = null;

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            jtfNombrePaciente.setEditable(false);

            cboEspecialidad.addItem("Seleccione especialidad");
            cboEspecialidad.addItem("Medicina General");
            cboEspecialidad.addItem("Pediatría");
            cboEspecialidad.addItem("Ginecología");
            cboEspecialidad.addItem("Cardiología");
            cboEspecialidad.addItem("Dermatología");
            cboEspecialidad.addItem("Traumatología");

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            lblRegistro.setFont(new Font("Arial", Font.BOLD, 18));
            this.add(lblRegistro, gbc);

            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridwidth = 1;
            gbc.gridx = 0;
            gbc.gridy++;
            this.add(lblPaciente, gbc);
            gbc.gridy++;
            this.add(lblNombrePaciente, gbc);
            gbc.gridy++;
            this.add(lblMedico, gbc);
            gbc.gridy++;
            this.add(lblEspecialidad, gbc);
            gbc.gridy++;
            this.add(lblFecha, gbc);
            gbc.gridy++;
            this.add(lblHora, gbc);
            gbc.gridy++;
            this.add(lblCosto, gbc);

            gbc.gridx = 1;
            gbc.gridy = 1;
            this.add(jtfDniPaciente, gbc);
            gbc.gridy++;
            this.add(jtfNombrePaciente, gbc);
            gbc.gridy++;
            this.add(jtfMedico, gbc);
            gbc.gridy++;
            this.add(cboEspecialidad, gbc);
            gbc.gridy++;
            this.add(jtfFecha, gbc);
            gbc.gridy++;
            this.add(jtfHora, gbc);
            gbc.gridy++;
            this.add(jtfCosto, gbc);

            gbc.gridx = 2;
            gbc.gridy = 1;
            this.add(btnBuscarPaciente, gbc);

            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.gridwidth = 3;
            gbc.gridheight = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            btnRegistrar.setBackground(new Color(33, 122, 210));
            btnRegistrar.setForeground(Color.WHITE);
            this.add(btnRegistrar, gbc);

            btnBuscarPaciente.addActionListener(e -> {
                String dni = jtfDniPaciente.getText().trim();
                if (dni.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese el DNI del paciente", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                pacienteElegido = null;
                for (Paciente p : crudPacienteModel.findAll()) {
                    if (p.getDni() != null && p.getDni().equals(dni)) {
                        pacienteElegido = p;
                        break;
                    }
                }

                if (pacienteElegido != null) {
                    jtfNombrePaciente.setText(pacienteElegido.getNombre() + " " + pacienteElegido.getApellido());
                } else {
                    jtfNombrePaciente.setText("");
                    JOptionPane.showMessageDialog(this, "No se encontró ningún paciente con ese DNI", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            });

            btnRegistrar.addActionListener(e -> {
                if (pacienteElegido == null) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un paciente", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String medico = jtfMedico.getText().trim();
                String especialidad = cboEspecialidad.getSelectedItem().toString();
                String fechaIngresada = jtfFecha.getText().trim();
                String horaIngresada = jtfHora.getText().trim();
                String costoIngresado = jtfCosto.getText().trim();
                LocalDate fecha;
                LocalTime hora;
                double costo;


                if (medico.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Ingrese el nombre del médico", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (especialidad.equals("Seleccione especialidad")) {
                    JOptionPane.showMessageDialog(this, "Seleccione una especialidad", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (fechaIngresada.isEmpty() || horaIngresada.isEmpty() || costoIngresado.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    fecha = LocalDate.parse(fechaIngresada, dateFormatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto. Use dd/MM/yyyy", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    hora = LocalTime.parse(horaIngresada, timeFormatter);
                } catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(this, "Formato de hora incorrecto. Use HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    costo = Double.parseDouble(costoIngresado);
                    if (costo <= 0) throw new NumberFormatException();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "El costo debe ser un número positivo", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Cita cita = new Cita();
                cita.setPaciente(pacienteElegido);
                cita.setMedico(medico);
                cita.setEspecialidad(especialidad);
                cita.setFecha(fecha);
                cita.setHora(hora);
                cita.setCosto(costo);
                cita.setEstado("PROGRAMADA");

                if (crudCitaModel.create(cita)) {
                    JOptionPane.showMessageDialog(this, "Cita registrada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    resetCampos();

                    int confirmacion = JOptionPane.showConfirmDialog(this, "¿Imprimir Ticket?", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (confirmacion == JOptionPane.YES_OPTION) {
                        if (citaModel.imprimirCita(cita)) {
                            JOptionPane.showMessageDialog(this, "Ticket impreso con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Hubo un error al imprimir", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Error al registrar la cita", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        private void resetCampos() {
            jtfDniPaciente.setText("");
            jtfNombrePaciente.setText("");
            jtfMedico.setText("");
            cboEspecialidad.setSelectedIndex(0);
            jtfFecha.setText("");
            jtfHora.setText("");
            jtfCosto.setText("");
            pacienteElegido = null;
        }
    }

    class LienzoHeader extends JPanel {
        private JLabel lblTitulo = new JLabel("PROGRAMACIÓN DE CITAS", SwingConstants.CENTER);

        LienzoHeader() {
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 122, 210));
            this.lblTitulo.setFont(new Font("Arial", Font.BOLD, 20));
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

    class LienzoFooter extends JPanel {
        private JButton btnSalir = new JButton("Cancelar");

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
