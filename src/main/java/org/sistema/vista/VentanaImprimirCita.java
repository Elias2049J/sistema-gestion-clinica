package org.sistema.vista;

import org.sistema.model.Cita;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class VentanaImprimirCita extends JFrame {
    private LienzoRegistrar lienzoRegistrar = new LienzoRegistrar();

    public VentanaImprimirCita(){
        super();
        this.setTitle("Registro de cita");
        this.setSize(800, 600);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoRegistrar, BorderLayout.CENTER);
    }

    static class LienzoRegistrar extends JPanel {
        private JLabel lblNombreCliente = new JLabel("CLIENTE:");
        private JLabel lblEspecialidad = new JLabel("ESPECIALIDAD:");
        private JTextField txtNombreCliente = new JTextField("Karen Mendoza Navarro");
        private JTextField txtEspecialidad = new JTextField("Odontologia");
        private String[] columnas = {"IdCita", "Especialidad", "Doctor", "Paciente", "Costo: S/", "Fecha", "Estado"};

        Cita c1 = new Cita(1, "Odontologia", "Chapatin", "Karen Mendoza Navarro", 7.00, LocalDateTime.of(2023, 8, 15, 14, 30, 0), "Libre");
        Cita c2 = new Cita(2, "Cardiología", "Dr. Pérez", "Luis Pérez", 8.50, LocalDateTime.of(2023, 8, 16, 10, 0, 0), "Reservado");
        Cita c3 = new Cita(3, "Pediatría", "Dra. Torres", "Ana Torres", 6.00, LocalDateTime.of(2023, 8, 17, 9, 15, 0), "Reservado");
        Cita c4 = new Cita(4, "Dermatología", "Dr. Ruiz", "Carlos Ruiz", 9.00, LocalDateTime.of(2023, 8, 18, 11, 45, 0), "Libre");
        Cita c5 = new Cita(5, "Ginecología", "Dra. López", "María López", 7.50, LocalDateTime.of(2023, 8, 19, 13, 0, 0), "Libre");
        Cita c6 = new Cita(6, "Traumatología", "Dr. Gómez", "Pedro Gómez", 8.00, LocalDateTime.of(2023, 8, 20, 15, 30, 0), "Reservado");
        Cita c7 = new Cita(7, "Neurología", "Dra. Fernández", "Lucía Fernández", 7.20, LocalDateTime.of(2023, 8, 21, 16, 0, 0), "Libre");
        Cita c8 = new Cita(8, "Oftalmología", "Dr. Ramírez", "Jorge Ramírez", 8.80, LocalDateTime.of(2023, 8, 22, 17, 10, 0), "Pendiente");
        Cita c9 = new Cita(9, "Endocrinología", "Dra. Castro", "Sofía Castro", 6.50, LocalDateTime.of(2023, 8, 23, 12, 20, 0), "Reservado");
        Cita c10 = new Cita(10, "Urología", "Dr. Díaz", "Miguel Díaz", 9.30, LocalDateTime.of(2023, 8, 24, 14, 50, 0), "Libre");
        Cita[] citas = {c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
        private Object[][] datos = new Object[citas.length][columnas.length];
        private DefaultTableModel modeloTabla;
        private JTable tablaRegistro = new JTable();
        private JScrollPane scpRegistroCita = new JScrollPane(tablaRegistro);
        private JButton btnImprimirRegistro = new JButton("Imprimir Registro");

        public LienzoRegistrar() {
            super();
            for (int i = 0; i < citas.length; i++) {
                datos[i][0] = citas[i].getIdCita();
                datos[i][1] = citas[i].getEspecialidad();
                datos[i][2] = citas[i].getDoctor();
                datos[i][3] = citas[i].getPaciente();
                datos[i][4] = citas[i].getCosto();
                datos[i][5] = citas[i].getFecha();
                datos[i][6] = citas[i].getEstado();
            }
            this.modeloTabla = new DefaultTableModel(datos, columnas);
            this.tablaRegistro.setModel(modeloTabla);
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(4, 20, 2, 20);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 4;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            this.add(scpRegistroCita, gbc);
            gbc.gridy = 1;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            gbc.gridheight = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.weightx = 0;
            gbc.weighty = 0;
            this.add(lblNombreCliente, gbc);
            gbc.gridx = 1; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
            this.add(txtNombreCliente, gbc);
            gbc.gridy = 2;
            gbc.gridx = 0; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
            this.add(lblEspecialidad, gbc);
            gbc.gridx = 1; gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.HORIZONTAL; gbc.weightx = 1;
            this.add(txtEspecialidad, gbc);
            gbc.gridy = 3;
            gbc.gridx = 3; gbc.gridwidth = 1; gbc.fill = GridBagConstraints.NONE; gbc.weightx = 0; gbc.weighty = 0; gbc.anchor = GridBagConstraints.WEST;
            this.add(btnImprimirRegistro, gbc);
            btnImprimirRegistro.addActionListener(e -> {
                int filaSeleccionada = tablaRegistro.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(this, "Selecciona una fila para imprimir.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int opcion = JOptionPane.showConfirmDialog(
                        this,
                        "¿Imprimir Registro?",
                        "Confirmar impresión",
                        JOptionPane.YES_NO_OPTION
                );
                if (opcion == JOptionPane.YES_OPTION) {
                    String rutaArchivo = "C:\\Users\\henoc\\OneDrive\\Escritorio\\registro.txt";
                    try (FileWriter fileWriter = new FileWriter(rutaArchivo)) {
                        for (int col = 0; col < tablaRegistro.getColumnCount(); col++) {
                            Object valor = tablaRegistro.getValueAt(filaSeleccionada, col);
                            fileWriter.write(tablaRegistro.getColumnName(col) + ": " + valor + System.lineSeparator());
                        }
                        JOptionPane.showMessageDialog(this, "Registro impreso correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    } catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                } else if (opcion == JOptionPane.NO_OPTION) {
                    System.out.println("No se hizo nada");
                }
            });
        }


    }

}
