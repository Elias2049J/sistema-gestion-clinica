package org.sistema.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaDatosPersonales extends JFrame {

    public VentanaDatosPersonales() throws HeadlessException {
        super();
        this.setTitle("Análisis de archivos");
        this.setSize(800, 520);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
    }

}
