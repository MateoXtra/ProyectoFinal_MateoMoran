package Interfaz_Cliente;

import Login_Registro.login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class form4 {
    public JPanel factura;
    private JLabel labelfactura;
    private JLabel labelnombre; // Nombre del cliente
    private JLabel labelcorreo; // Correo del cliente
    private JLabel labelpelicula; // Nombre de la película
    private JLabel label1numero_asientos; // Número de asientos
    private JLabel labeltotal; // Total a pagar
    private JButton volverAlLoginButton;

    public form4(String nombreCliente, String correoCliente, String nombrePelicula, int numeroAsientos, int total) {
        labelfactura.setText("Factura");
        labelnombre.setText("Nombre: " + nombreCliente);
        labelcorreo.setText("Correo: " + correoCliente);
        labelpelicula.setText("Película: " + nombrePelicula);
        label1numero_asientos.setText("Número de asientos: " + numeroAsientos);
        labeltotal.setText("Total: $" + total);


        volverAlLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Login");
                frame1.setContentPane(new login().panel_login);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(factura);
                frame.dispose();
            }
        });
    }

    public JPanel getFacturaPanel() {
        return factura;
    }
}
