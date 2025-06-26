package org.sistema.vista.sec_citas;

import org.sistema.entidad.Cita;
import org.sistema.interfaces.CrudInterface;
import org.sistema.model.CrudCitaModel;

import javax.swing.*;
import java.awt.*;

public class VentanaRegCita extends JFrame{
    private CrudInterface<Cita, Integer> crudCitaModel;
    private VentanaGestionCitas ventanaGestionCitas;
    private LienzoHeader lienzoHeader;
    private LienzoCentral lienzoCentral;
    private LienzoFooter lienzoFooter;
    private Integer idCliente;

    public VentanaRegCita(Integer idCliente){
        super();
        this.idCliente = idCliente;
        this.crudCitaModel = new CrudCitaModel();
        this.lienzoHeader = new LienzoHeader();
        this.lienzoCentral = new LienzoCentral();
        this.lienzoFooter = new LienzoFooter();
        this.setTitle("Registro de Clientes");
        this.setSize(600, 500);
        this.setLocationRelativeTo(rootPane);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(lienzoHeader, BorderLayout.NORTH);
        this.add(lienzoCentral, BorderLayout.CENTER);
        this.add(lienzoFooter, BorderLayout.SOUTH);
    }

    class LienzoCentral extends JPanel {
        private JLabel lblRegistro = new JLabel("ABRIR CUENTA", SwingConstants.CENTER);

        private JComboBox<String> cboTipoCuenta = new JComboBox<>();

        private JLabel lblSaldo = new JLabel("Depósito:");
        private JTextField jtfSaldo = new JTextField(20);

        private JLabel lblTipoMoneda = new JLabel("Tipo de Moneda");
        private JComboBox<String> cboTipoMoneda = new JComboBox<>();

        private JLabel lblTasa = new JLabel("Tasa de Interés");
        private JTextField jtfTasa = new JTextField(20);
        private JLabel lblLimiteGiros = new JLabel("Límite sobre giros");
        private JTextField jtfLimiteGiros = new JTextField(20);

        private JButton btnRegistrar = new JButton("Registrar");

        public LienzoCentral() {
            super();
            this.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(8, 8, 8, 8);
            gbc.fill = GridBagConstraints.HORIZONTAL;
            jtfTasa.setEditable(true);
            jtfLimiteGiros.setEditable(false);
            jtfSaldo.setEditable(true);

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            lblRegistro.setFont(new Font("Arial", Font.BOLD, 18));
            this.add(lblRegistro, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridwidth = 5;
            gbc.anchor = GridBagConstraints.CENTER;
            cboTipoCuenta.addItem("Ahorro");
            cboTipoCuenta.addItem("Corriente");
            this.add(cboTipoCuenta, gbc);

            gbc.anchor = GridBagConstraints.WEST;
            gbc.gridwidth = 1;

            gbc.gridx = 0;
            gbc.gridy = 2;
            this.add(lblTipoMoneda, gbc);
            gbc.gridy++;
            this.add(lblSaldo, gbc);
            gbc.gridy++;
            this.add(lblTasa, gbc);
            gbc.gridy++;
            this.add(lblLimiteGiros, gbc);

            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.gridheight = 7;
            this.add(Box.createHorizontalStrut(30), gbc);
            gbc.gridheight = 1;

            gbc.gridx = 2;
            gbc.gridy = 2;
            cboTipoMoneda.addItem("Soles");
            cboTipoMoneda.addItem("Dolares");
            this.add(cboTipoMoneda, gbc);
            gbc.gridy++;
            this.add(jtfSaldo, gbc);
            gbc.gridy++;
            this.add(jtfTasa, gbc);
            gbc.gridy++;
            this.add(jtfLimiteGiros, gbc);

            gbc.gridx = 0;
            gbc.gridy++;
            gbc.gridwidth = 3;
            gbc.anchor = GridBagConstraints.CENTER;
            this.add(btnRegistrar, gbc);

            cboTipoCuenta.addItemListener(e -> {
                if (cboTipoCuenta.getSelectedItem() == "Ahorro") {
                    jtfSaldo.setEditable(true);
                    jtfTasa.setEditable(true);
                    jtfLimiteGiros.setEditable(false);
                    jtfLimiteGiros.setText("");
                } else {
                    jtfSaldo.setEditable(true);
                    jtfTasa.setEditable(false);
                    jtfLimiteGiros.setEditable(true);
                    jtfTasa.setText("");
                }
            });

            btnRegistrar.addActionListener(e -> {
                Double saldo = jtfSaldo.getText().isEmpty() ? null : Double.parseDouble(jtfSaldo.getText());
                String tipoMoneda = String.valueOf(cboTipoMoneda.getSelectedItem());
                String tipoCuenta = String.valueOf(cboTipoCuenta.getSelectedItem());
                Double tasaInteres = null;
                Double limiteSobreGiros = null;

                if (tipoCuenta.equals("Ahorro")) {
                    tasaInteres = jtfTasa.getText().isEmpty() ? null : Double.parseDouble(jtfTasa.getText());
                } else {
                    limiteSobreGiros = jtfLimiteGiros.getText().isEmpty() ? null : Double.parseDouble(jtfLimiteGiros.getText());
                }
                Cita c = new Cita();

                if (crudCitaModel.crear(c)) {
                    JOptionPane.showMessageDialog(this, "Se ha registrado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Hubo un Error al registrar, compruebe los campos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
    }

    class LienzoHeader extends JPanel{
        LienzoHeader (){
            super();
            this.setLayout(new BorderLayout());
            this.setBackground(new Color(37, 55, 40));
        }

        @Override
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.12);
            return new Dimension(ancho, alto);
        }
    }

    class LienzoFooter extends JPanel{
        private JButton btnSalir = new JButton("Cancelar");
        public LienzoFooter (){
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
        public Dimension getPreferredSize(){
            Container contenedorPadre = getParent();
            int ancho = contenedorPadre.getWidth();
            //se castea a int
            int alto = (int) (contenedorPadre.getHeight() * 0.08);
            return new Dimension(ancho, alto);
        }
    }
}
