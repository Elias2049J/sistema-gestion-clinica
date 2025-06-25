package org.sistema.vista;

import javax.swing.*;
import java.awt.*;

public class VentanaAyuda extends JFrame {
    private LienzoHeader lienzoHeader = new LienzoHeader();
    private LienzoCentral lienzoCentral = new LienzoCentral();
    private LienzoFooter lienzoFooter = new LienzoFooter();

    public VentanaAyuda() {
        super();
        this.setTitle("Ayuda");
        this.setSize(600, 500);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoCentral extends JPanel {
        private JLabel lblTitulo = new JLabel("INFORMACIÓN DEL SISTEMA BANCARIO", SwingConstants.CENTER);

        private JTextArea infoSistema = new JTextArea(
                """
                Bienvenido al Sistema Bancario. El objetivo de este es facilitar la operación de pequeños emprendimientos.
                Versión: 0.01
                
                Funcionalidades:
                - Registro de nuevos clientes.
                - Consulta de cuentas existentes.
                
                Creado por:
                - Henoc Jorge
                - María Navarro
                - Axcel Sanchez
                - Miguel Centellas
                """
        );


        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(2, 2, 2, 2);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Título
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            this.add(lblTitulo, gbc);
            infoSistema.setEditable(false);
            infoSistema.setLineWrap(true);
            infoSistema.setWrapStyleWord(true);
            infoSistema.setFont(new Font("Arial", Font.PLAIN, 13));
            infoSistema.setBorder(BorderFactory.createTitledBorder("Acerca del sistema"));

            JScrollPane scroll = new JScrollPane(infoSistema);

            // Agregar área de texto
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 5;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.anchor = GridBagConstraints.CENTER;
            this.add(scroll, gbc);
        }
    }

    class LienzoHeader extends JPanel {
        LienzoHeader() {
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(37, 55, 40));
        }

        @Override
        public Dimension getPreferredSize() {
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.12);
            return new Dimension(ancho, alto);
        }
    }

    class LienzoFooter extends JPanel {
        private JButton btnSalir = new JButton("Salir");

        public LienzoFooter() {
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(37, 55, 40));
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
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.08);
            return new Dimension(ancho, alto);
        }
    }
}