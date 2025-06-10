package org.sistema.vista.gestionpaciente;

import javax.swing.*;
import java.awt.*;

public class VentanaGestionPaciente extends JFrame {
    private JLabel lblTitulo = new JLabel("Abrir archivo");
    private LienzoArchivo lienzoArchivo = new LienzoArchivo();

    public VentanaGestionPaciente() throws HeadlessException {
        super();
        this.setTitle("Análisis de archivos");
        this.setSize(800, 520);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lblTitulo, BorderLayout.NORTH);
        this.add(lienzoArchivo, BorderLayout.CENTER);
    }

    class LienzoArchivo extends JPanel{
        private JLabel lblArchivo = new JLabel("Ruta del archivo:", SwingConstants.CENTER);
        private JTextField textFieldArchivo = new JTextField(20);
        private JLabel lblTipo = new JLabel("Tipo de archivo:", SwingConstants.CENTER);
        private JTextField textFieldTipo = new JTextField(20);
        private JLabel lblSize = new JLabel("Tamaño:", SwingConstants.CENTER);
        private JTextField txtFieldSize = new JTextField(20);
        private JButton btnAnalizar = new JButton("Analizar archivo");
        private JTextArea areaResultado = new JTextArea(5, 30);

        public LienzoArchivo(){
            super();
            this.setLayout(new GridLayout(5, 2, 10, 10));
            this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            this.add(lblArchivo);
            this.add(textFieldArchivo);
            this.add(lblTipo);
            this.add(textFieldTipo);
            this.add(lblSize);
            this.add(txtFieldSize);
            this.add(new JLabel());
            this.add(btnAnalizar);
            this.add(new JLabel("Resultado:"));
            this.add(new JScrollPane(areaResultado));
        }
    }
}
