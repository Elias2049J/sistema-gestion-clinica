package org.sistema.vista.gestionpaciente;

import org.sistema.model.entidad.HistorialClinico;
import org.sistema.model.servicio.PacienteService;
import org.sistema.use_case.PacienteUseCase;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaHistorialClinico extends JFrame {

    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter();

    public VentanaHistorialClinico(){
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
        private PacienteUseCase pacienteService = new PacienteService();

        private JLabel lblIDPaciente = new JLabel("Ingrese el ID del paciente: ");
        private JTextField jtfIdPaciente = new JTextField(10);

        private JButton btnBuscar = new JButton("Buscar");

        //labels para cada campo
        private JLabel lblIdHistorial = new JLabel("Id Historial:");
        private JLabel lblPaciente = new JLabel("Datos paciente:");
        private JLabel lblFecha = new JLabel("Fecha:");
        private JLabel lblEstado = new JLabel("Estado:");
        private JLabel lblDiagnosticos = new JLabel("Diagnosticos:");
        private JLabel lblTratamientos = new JLabel("Tratamientos:");
        private JLabel lblConsultas = new JLabel("Consultas:");
        private JLabel lblAntecedentes = new JLabel("Antecedentes:");
        private JLabel lblAlergias = new JLabel("Alergias:");
        private JLabel lblObservaciones = new JLabel("Observaciones:");

        //campos de texto para mostrar los datos de un historial
        private JTextField jtfIdHistorial = new JTextField(30);
        private JTextField jtfPaciente = new JTextField(30);
        private JTextField jtfFecha = new JTextField(30);
        private JTextField jtfEstado = new JTextField(30);
        private JTextField jtfDiagnosticos = new JTextField(30);
        private JTextField jtfTratamientos = new JTextField(30);
        private JTextField jtfConsultas = new JTextField(30);
        private JTextField jtfAntecedentes = new JTextField(30);
        private JTextField jtfAlergias = new JTextField(30);
        private JTextField jtfObservaciones = new JTextField(30);

        //tabla
        private String[] columnasTabla = {"Label", "Valor"};
        private Object[][] datosHistorial = {};
        private JTable tablaHistorial = new JTable(datosHistorial, columnasTabla);
        private JScrollPane scpTabla = new JScrollPane(tablaHistorial);

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            //margenes externos para los elementos
            gbc.insets = new Insets(2, 2, 2, 2);

            // lbl, campo y boton para la busqueda
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblIDPaciente, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfIdPaciente, gbc);
            gbc.gridx = 2;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(btnBuscar, gbc);

            // elementos para mostrar el historial
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblIdHistorial, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfIdHistorial, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblPaciente, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfPaciente, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblFecha, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfFecha, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblEstado, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfEstado, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblDiagnosticos, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfDiagnosticos, gbc);

            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblTratamientos, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfTratamientos, gbc);

            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblConsultas, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfConsultas, gbc);

            gbc.gridx = 0;
            gbc.gridy = 8;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblAntecedentes, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfAntecedentes, gbc);

            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblAlergias, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfAlergias, gbc);

            gbc.gridx = 0;
            gbc.gridy = 10;
            gbc.fill = GridBagConstraints.NONE;
            gbc.weightx = 0;
            this.add(lblObservaciones, gbc);
            gbc.gridx = 1;
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.weightx = 1.0;
            this.add(jtfObservaciones, gbc);

            //tabla
            gbc.gridx = 0;
            gbc.gridy = 11;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            this.add(scpTabla, gbc);

            //boton para ejecutar la busqueda
            btnBuscar.addActionListener(e -> {
                //se obtiene el valor ingresado y se parsea a integer
                Integer idPacienteIngresado = Integer.parseInt(jtfIdPaciente.getText());
                //se usa el servicio para buscar
                HistorialClinico elementosHistorial = pacienteService.obtenerHistorialClinico(idPacienteIngresado);
                //se parsea de integer a string
                jtfIdHistorial.setText(String.valueOf(elementosHistorial.getIdHistorial()));
                jtfPaciente.setText(String.valueOf(elementosHistorial.getPaciente()));
                jtfFecha.setText(String.valueOf(elementosHistorial.getFechaCreacion()));
                jtfEstado.setText(elementosHistorial.getEstado());
                jtfDiagnosticos.setText(elementosHistorial.getDiagnosticos().toString());
                jtfTratamientos.setText(elementosHistorial.getTratamientos().toString());
                jtfConsultas.setText(elementosHistorial.getConsultas().toString());
                jtfAntecedentes.setText(elementosHistorial.getAntecedentes());
                jtfAlergias.setText(elementosHistorial.getAlergias());
                jtfObservaciones.setText(elementosHistorial.getObservaciones());

                // Mostrar los valores en la tabla: primera columna label, segunda columna valor
                String[][] datosTabla = {
                        {lblIdHistorial.getText(), String.valueOf(elementosHistorial.getIdHistorial())},
                        {lblPaciente.getText(), String.valueOf(elementosHistorial.getPaciente())},
                        {lblFecha.getText(), String.valueOf(elementosHistorial.getFechaCreacion())},
                        {lblEstado.getText(), elementosHistorial.getEstado()},
                        {lblDiagnosticos.getText(), String.valueOf(elementosHistorial.getDiagnosticos())},
                        {lblTratamientos.getText(), String.valueOf(elementosHistorial.getTratamientos())},
                        {lblConsultas.getText(), String.valueOf(elementosHistorial.getConsultas())},
                        {lblAntecedentes.getText(), elementosHistorial.getAntecedentes()},
                        {lblAlergias.getText(), elementosHistorial.getAlergias()},
                        {lblObservaciones.getText(), elementosHistorial.getObservaciones()}
                };
                tablaHistorial.setModel(new DefaultTableModel(datosTabla, columnasTabla));
            });
        }
    }

    class LienzoHeader extends JPanel{
        public LienzoHeader (){
            super();
        }
    }

    class LienzoFooter extends JPanel{
        public LienzoFooter (){
            super();

        }
    }
}
