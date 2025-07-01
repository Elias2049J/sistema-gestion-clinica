package org.sistema.vista.sec_paciente;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;
import org.sistema.use_case.CrudUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaHistorialCitas extends JFrame {
    private CrudUseCase<Paciente, Integer, String> crudPacienteModel;
    private CrudUseCase<Cita, Integer, String> crudCitaModel;
    private Paciente paciente;
    private LienzoHeader lienzoHeader;
    private LienzoCentral lienzoCentral;
    private LienzoFooter lienzoFooter;

    public VentanaHistorialCitas(CrudUseCase<Paciente, Integer, String> crudPacienteModel, CrudUseCase<Cita, Integer, String> crudCitaModel, Paciente p) {
        super();
        this.paciente = p;
        this.crudCitaModel = crudCitaModel;
        this.crudPacienteModel = crudPacienteModel;
        this.lienzoCentral = new LienzoCentral();
        this.lienzoHeader = new LienzoHeader();
        this.lienzoFooter = new LienzoFooter();
        this.setTitle("Histórico De Citas");
        this.setSize(960, 514);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoHeader extends JPanel {
        private JLabel lblTitulo = new JLabel("HISTÓRICO DE CITAS", SwingConstants.CENTER);

        public LienzoHeader() {
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 122, 210));
            this.lblTitulo.setForeground(Color.WHITE);
            this.lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
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

    class LienzoCentral extends JPanel {
        // panel superior de tabla
        private JPanel panelTabla = new JPanel();
        private JTable tablaDatos = new JTable();
        private JScrollPane scpTabla = new JScrollPane();
        private String[] columnas = {"Id_Cita", "Médico", "Especialidad", "Fecha", "Hora", "Costo", "Estado"};
        private Object[][] datos;
        private DefaultTableModel modeloTabla = new DefaultTableModel();
        private GridBagConstraints gbcSuperior = new GridBagConstraints();

        public LienzoCentral() {
            super();
            this.setLayout(new BorderLayout());

            // panel tabla
            this.panelTabla.setLayout(new GridBagLayout());
            this.gbcSuperior.insets = new Insets(10, 10, 10, 10);
            this.gbcSuperior.weightx = 1;
            this.gbcSuperior.weighty = 1;
            this.gbcSuperior.fill = GridBagConstraints.BOTH;

            this.datos = new Object[paciente.getHistorialCitas() != null ? paciente.getHistorialCitas().size() : 0][columnas.length];

            if (paciente.getHistorialCitas() != null) {
                for (int i = 0; i < paciente.getHistorialCitas().size(); i++) {
                    Cita c = paciente.getHistorialCitas().get(i);
                    datos[i][0] = c.getIdCita();
                    datos[i][1] = c.getMedico() != null ? c.getMedico() : "n/a";
                    datos[i][2] = c.getEspecialidad() != null ? c.getEspecialidad() : "n/a";
                    datos[i][3] = c.getFecha() != null ? c.getFecha() : "n/a";
                    datos[i][4] = c.getHora() != null ? c.getHora() : "n/a";
                    datos[i][5] = !String.valueOf(c.getCosto()).isBlank() ? c.getCosto() : "n/a";
                    datos[i][6] = c.getEstado() != null ? c.getEstado() : "n/a";
                }
            }

            this.modeloTabla.setDataVector(datos, columnas);
            this.tablaDatos.setModel(modeloTabla);
            this.scpTabla.setViewportView(tablaDatos);
            this.panelTabla.add(scpTabla, gbcSuperior);
            this.add(panelTabla, BorderLayout.CENTER);
        }

        private Paciente getPacienteDeFila(int fila) {
            try {
                Integer idPaciente = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                String nombre = modeloTabla.getValueAt(fila, 1).toString();
                String apellido = modeloTabla.getValueAt(fila, 2).toString();
                String edadStr = modeloTabla.getValueAt(fila, 3).toString();
                String dni = modeloTabla.getValueAt(fila, 4).toString();
                String direccion = modeloTabla.getValueAt(fila, 5).toString();
                String telefono = modeloTabla.getValueAt(fila, 6).toString();
                String estado = modeloTabla.getValueAt(fila, 7).toString();

                Paciente paciente = new Paciente();
                paciente.setIdPaciente(idPaciente);
                paciente.setNombre(nombre.equals("n/a") ? "" : nombre);
                paciente.setApellido(apellido.equals("n/a") ? "" : apellido);
                paciente.setEdad(edadStr.equals("n/a") ? null : Integer.parseInt(edadStr));
                paciente.setDni(dni.equals("n/a") ? "" : dni);
                paciente.setDireccion(direccion.equals("n/a") ? "" : direccion);
                paciente.setTelefono(telefono.equals("n/a") ? "" : telefono);
                paciente.setEstado(estado.equals("n/a") ? "ACTIVO" : estado);

                Paciente pacienteExistente = crudPacienteModel.getById(idPaciente);
                if (pacienteExistente != null) {
                    paciente.setHistorialCitas(pacienteExistente.getHistorialCitas());
                }

                return paciente;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al obtener datos de la fila: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        private boolean actualizarTabla() {
            modeloTabla.setRowCount(0);
            for (Paciente p : crudPacienteModel.findAll()) {
                Object[] fila = new Object[8];
                fila[0] = p.getIdPaciente();
                fila[1] = p.getNombre() != null ? p.getNombre() : "n/a";
                fila[2] = p.getApellido() != null ? p.getApellido() : "n/a";
                fila[3] = p.getEdad() != null ? p.getEdad() : "n/a";
                fila[4] = p.getDni() != null ? p.getDni() : "n/a";
                fila[5] = p.getDireccion() != null ? p.getDireccion() : "n/a";
                fila[6] = p.getTelefono() != null ? p.getTelefono() : "n/a";
                fila[7] = p.getEstado() != null ? p.getEstado() : "n/a";
                modeloTabla.addRow(fila);
            }
            return true;
        }
    }

    class LienzoFooter extends JPanel {
        private JButton btnSalir = new JButton("Salir");
        private JButton btnAyuda = new JButton("Ayuda");

        public LienzoFooter() {
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
                JOptionPane.showMessageDialog(lienzoCentral,
                        "Esta ventana permite visualizar el historial de citas de un paciente.\n",
                        "Ayuda", JOptionPane.INFORMATION_MESSAGE);
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
