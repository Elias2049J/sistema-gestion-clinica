package org.sistema.vista.sec_citas;

import org.sistema.entidad.Cita;
import org.sistema.entidad.Paciente;
import org.sistema.use_case.CrudUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class VentanaGestionCitas extends JFrame{
    private CrudUseCase<Paciente, Integer, String> crudPacienteModel;
    private CrudUseCase<Cita, Integer, String> crudCitaModel;
    private LienzoCentral lienzoCentral;
    private LienzoHeader lienzoHeader;
    private LienzoFooter lienzoFooter;

    public VentanaGestionCitas(CrudUseCase<Paciente, Integer, String> crudPacienteModel, CrudUseCase<Cita, Integer, String> crudCitaModel) throws HeadlessException {
        super();
        this.crudPacienteModel = crudPacienteModel;
        this.crudCitaModel = crudCitaModel;
        this.lienzoCentral = new LienzoCentral();
        this.lienzoHeader = new LienzoHeader();
        this.lienzoFooter = new LienzoFooter();
        this.setTitle("Sección Clientes");
        this.setSize(960, 514);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoHeader extends JPanel{
        private JLabel lblTitulo = new JLabel("GESTIÓN DE CITAS", SwingConstants.CENTER);

        public LienzoHeader ( ){
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(33, 122, 210));
            this.lblTitulo.setForeground(Color.WHITE);
            this.lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
            this.add(lblTitulo, BorderLayout.CENTER);
        }

        @Override
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            int alto = (int) (contenedorPadre.getHeight() * 0.12);
            return new Dimension(ancho, alto);
        }
    }

    class LienzoCentral extends JPanel {
        //panel superior de tabla
        private JPanel panelTabla = new JPanel();
        private JTable tablaDatos = new JTable();
        private JScrollPane scpTabla = new JScrollPane();
        private String[] columnas = {"Id_Cita", "Paciente", "Médico", "Especialidad", "Fecha", "Hora", "Costo", "Estado"};
        private Object[][] datos;
        private DefaultTableModel modeloTabla = new DefaultTableModel();
        private GridBagConstraints gbcSuperior = new GridBagConstraints();
        // panel inferior de botones
        private JPanel panelBtns = new JPanel();
        private JButton btnGuardar = new JButton("Guardar Cambios");
        private JButton btnDescartar = new JButton("Descatar Cambios");
        private   JButton btnCancelar = new JButton("Cancelar Cita");
        private GridBagConstraints gbcInferior = new GridBagConstraints();

        public LienzoCentral() {
            super();
            this.setLayout(new BorderLayout());
            //panel tabla
            this.panelTabla.setLayout(new GridBagLayout());
            this.gbcSuperior.insets = new Insets(10, 10, 10, 10);
            this.gbcSuperior.weightx = 1; this.gbcSuperior.weighty = 1;
            this.gbcSuperior.fill = GridBagConstraints.BOTH;

            this.datos = new Object[crudPacienteModel.findAll().size()][columnas.length];

            this.modeloTabla.setDataVector(datos, columnas);
            this.tablaDatos.setModel(modeloTabla);
            this.scpTabla.setViewportView(tablaDatos);
            this.panelTabla.add(scpTabla, gbcSuperior);
            this.add(panelTabla, BorderLayout.CENTER);
            //panel btns
            this.panelBtns.setLayout(new GridBagLayout());
            this.gbcInferior.insets = new Insets(10, 10, 10, 10);
            this.btnGuardar.setBackground(new Color(33, 122, 210));
            this.btnGuardar.setForeground(Color.WHITE);
            this.btnDescartar.setBackground(new Color(33, 122, 210));
            this.btnDescartar.setForeground(Color.WHITE);
            this.btnCancelar.setBackground(new Color(33, 122, 210));
            this.btnCancelar.setForeground(Color.WHITE);
            this.panelBtns.add(btnGuardar, gbcInferior);
            this.panelBtns.add(btnDescartar, gbcInferior);
            this.panelBtns.add(btnCancelar, gbcInferior);
            this.add(panelBtns, BorderLayout.SOUTH);
            actualizarTabla();


            btnGuardar.addActionListener(e -> {
                int fila = tablaDatos.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione una fila para guardar los cambios.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Cita cita = getCitaDeFila(fila);
                if (cita == null) return;

                if (crudCitaModel.update(cita)) {
                    JOptionPane.showMessageDialog(this, "Cita actualizada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar cita.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            btnDescartar.addActionListener(e -> {
                actualizarTabla();
                JOptionPane.showMessageDialog(this, "Cambios descartados.", "Información", JOptionPane.INFORMATION_MESSAGE);
            });

            btnCancelar.addActionListener(e -> {
                int fila = tablaDatos.getSelectedRow();
                if (fila == -1) {
                    JOptionPane.showMessageDialog(this, "Seleccione una cita para cancelar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de cancelar esta cita?", "Confirmar cancelación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirmacion != JOptionPane.YES_OPTION) return;

                Cita cita = getCitaDeFila(fila);
                if (cita == null) return;

                // Cambiar estado a "Cancelada"
                cita.setEstado("Cancelada");

                if (crudCitaModel.update(cita)) {
                    JOptionPane.showMessageDialog(this, "La cita fue cancelada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    actualizarTabla();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo cancelar la cita.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }

        private Cita getCitaDeFila(int fila) {
            try {
                Integer idCita = Integer.parseInt(modeloTabla.getValueAt(fila, 0).toString());
                String nombrePaciente = modeloTabla.getValueAt(fila, 1).toString();
                String medico = modeloTabla.getValueAt(fila, 2).toString();
                String especialidad = modeloTabla.getValueAt(fila, 3).toString();
                String fecha = modeloTabla.getValueAt(fila, 4).toString();
                String hora = modeloTabla.getValueAt(fila, 5).toString();
                double costo = Double.parseDouble(modeloTabla.getValueAt(fila, 6).toString());
                String estado = modeloTabla.getValueAt(fila, 7).toString();

                Paciente paciente = new Paciente();
                paciente.setNombre(nombrePaciente); // Solo nombre (si tienes ID, mejor)

                Cita cita = new Cita();
                cita.setIdCita(idCita);
                cita.setPaciente(paciente);
                cita.setMedico(medico);
                cita.setEspecialidad(especialidad);
                cita.setFecha(LocalDate.parse(fecha));
                cita.setHora(LocalTime.parse(hora));
                cita.setCosto(costo);
                cita.setEstado(estado);

                return cita;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al recuperar los datos de la fila.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }

        private boolean actualizarTabla() {
            modeloTabla.setRowCount(0);
            for (Cita c : crudCitaModel.findAll()) {
                Object[] fila = new Object[8];
                fila[0]=c.getIdCita()!= null ? c.getIdCita() : "n/a";
                fila[1]=c.getPaciente().getNombre()!= null ? c.getPaciente().getNombre() : "n/a";
                fila[2]=c.getMedico()!= null ? c.getMedico() : "n/a";
                fila[3]=c.getEspecialidad()!= null ? c.getEspecialidad() : "n/a";
                fila[4]=c.getFecha()!= null ? c.getFecha() : "n/a";
                fila[5]=c.getHora()!= null ? c.getHora() : "n/a";
                fila[6]= !String.valueOf(c.getCosto()).isBlank() ? c.getCosto() : "n/a";
                fila[7]=c.getEstado()!= null ? c.getEstado() : "n/a";
                modeloTabla.addRow(fila);
            }
            return true;
        }
    }

    class LienzoFooter extends JPanel{
        private JButton btnSalir = new JButton("Salir");
        private JButton btnAyuda = new JButton("Ayuda");
        public LienzoFooter (){
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
        }

        @Override
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            int alto = (int) (contenedorPadre.getHeight() * 0.08);
            return new Dimension(ancho, alto);
        }
    }
}
