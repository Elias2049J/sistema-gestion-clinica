package org.sistema;

import org.sistema.vista.VentanaPrincipal;

import javax.swing.*;

public class MainTest {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatLightLaf");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        ventanaPrincipal.setVisible(true);
    }
}
