package org.sistema.vista.sec_paciente;

import org.sistema.entidad.Paciente;
import org.sistema.interfaces.CrudInterface;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VentanaGestionPaciente extends JFrame {
    private CrudInterface<Paciente, Integer> crudPacienteModel;
    private LienzoHeader lienzoHeader;
    private LienzoCentral lienzoCentral;
    private LienzoFooter lienzoFooter;

    public VentanaGestionPaciente(CrudInterface<Paciente, Integer> crudPacienteModel) {
        super();
        this.crudPacienteModel = crudPacienteModel;
        this.lienzoCentral = new LienzoCentral();
        this.lienzoHeader = new LienzoHeader();
        this.lienzoFooter = new LienzoFooter();
        this.setTitle("Gestión de Pacientes");
        this.setSize(960, 514);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoHeader extends JPanel {
        private JLabel lblTitulo = new JLabel("GESTIÓN DE PACIENTES", SwingConstants.CENTER);

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
        private String[] columnas = {"ID", "Nombre", "Apellido", "Edad", "DNI", "Dirección", "Teléfono", "Estado"};
        private Object[][] datos;
        private DefaultTableModel modeloTabla = new DefaultTableModel();
        private GridBagConstraints gbcSuperior = new GridBagConstraints();

        // panel inferior de botones
        private JPanel panelBtns = new JPanel();
        private JButton btnGuardar = new JButton("Guardar Cambios en la fila");
        private JButton btnDescartar = new JButton("Descartar Cambios");
        private JButton btnEliminar = new JButton("Eliminar Paciente");
        private JButton btnEliminarTodo = new JButton("Eliminar Todos");
        private GridBagConstraints gbcInferior = new GridBagConstraints();

        public LienzoCentral() {
            super();
            this.setLayout(new BorderLayout());

            // panel tabla
            this.panelTabla.setLayout(new GridBagLayout());
            this.gbcSuperior.insets = new Insets(10, 10, 10, 10);
            this.gbcSuperior.weightx = 1;
            this.gbcSuperior.weighty = 1;
            this.gbcSuperior.fill = GridBagConstraints.BOTH;

            this.datos = new Object[crudPacienteModel.findAll().size()][columnas.length];
            for (int i = 0; i < crudPacienteModel.findAll().size(); i++) {
                Paciente p = crudPacienteModel.findAll().get(i);
                datos[i][0] = p.getIdPaciente();
                datos[i][1] = p.getNombre() != null ? p.getNombre() : "n/a";
                datos[i][2] = p.getApellido() != null ? p.getApellido() : "n/a";
                datos[i][3] = p.getEdad() != null ? p.getEdad() : "n/a";
                datos[i][4] = p.getDni() != null ? p.getDni() : "n/a";
                datos[i][5] = p.getDireccion() != null ? p.getDireccion() : "n/a";
                datos[i][6] = p.getTelefono() != null ? p.getTelefono() : "n/a";
                datos[i][7] = p.getEstado() != null ? p.getEstado() : "n/a";
            }

            this.modeloTabla.setDataVector(datos, columnas);
            this.tablaDatos.setModel(modeloTabla);
            this.scpTabla.setViewportView(tablaDatos);
            this.panelTabla.add(scpTabla, gbcSuperior);
            this.add(panelTabla, BorderLayout.CENTER);

            // panel btns
            this.panelBtns.setLayout(new GridBagLayout());
            this.gbcInferior.insets = new Insets(10, 10, 10, 10);
            this.btnGuardar.setBackground(new Color(33, 122, 210));
            this.btnGuardar.setForeground(Color.WHITE);
            this.btnDescartar.setBackground(new Color(33, 122, 210));
            this.btnDescartar.setForeground(Color.WHITE);
            this.btnEliminar.setBackground(new Color(33, 122, 210));
            this.btnEliminar.setForeground(Color.WHITE);
            this.btnEliminarTodo.setBackground(new Color(33, 122, 210));
            this.btnEliminarTodo.setForeground(Color.WHITE);

            this.panelBtns.add(btnGuardar, gbcInferior);
            this.panelBtns.add(btnDescartar, gbcInferior);
            this.panelBtns.add(btnEliminar, gbcInferior);
            this.panelBtns.add(btnEliminarTodo, gbcInferior);
            this.add(panelBtns, BorderLayout.SOUTH);

            btnGuardar.addActionListener(e -> {
                int fila = tablaDatos.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione una fila para guardar los cambios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Paciente paciente = getPacienteDeFila(fila);
                if (crudPacienteModel.update(paciente)) {
                    JOptionPane.showMessageDialog(this, "Actualizado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnDescartar.addActionListener(e -> {
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "Cambios descartados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            });

            btnEliminar.addActionListener(e -> {
                int fila = tablaDatos.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione una fila para eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int idPaciente = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar Paciente? También se eliminarán todas sus citas.",
                                                               "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirmacion != JOptionPane.YES_OPTION) return;
                if (crudPacienteModel.delete(idPaciente)) {
                    JOptionPane.showMessageDialog(this, "Paciente eliminado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo eliminar el paciente.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnEliminarTodo.addActionListener(e-> {
                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Eliminar todo?",
                        "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirmacion != JOptionPane.YES_OPTION) return;
                for (Paciente p : new ArrayList<>(crudPacienteModel.findAll())) {
                    crudPacienteModel.delete(p.getIdPaciente());
                }
                actualizarTabla();
            });
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
                    "Esta ventana permite gestionar los pacientes del sistema.\n" +
                    "- Para editar: seleccione una fila, modifique los datos y haga clic en 'Guardar'.\n" +
                    "- Para eliminar: seleccione una fila y haga clic en 'Eliminar'.\n" +
                    "- Para descartar cambios: haga clic en 'Descartar'.",
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
