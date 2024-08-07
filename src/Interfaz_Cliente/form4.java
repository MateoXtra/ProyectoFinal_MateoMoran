package Interfaz_Cliente;

import Login_Registro.login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La clase <code>form4</code> representa la interfaz de la factura para la reserva de asientos.
 * Muestra los detalles de la reserva y permite al usuario volver al login.
 *
 * @author Mateo Morán
 */
public class form4 {
    public JPanel factura;
    private JLabel labelfactura;
    private JLabel labelnombre; // Nombre del cliente
    private JLabel labelcorreo; // Correo del cliente
    private JLabel labelpelicula; // Nombre de la película
    private JLabel label1numero_asientos; // Número de asientos
    private JLabel labeltotal; // Total a pagar
    private JButton volverAlLoginButton; // Botón para regresar al login

    /**
     * Constructor de la clase <code>form4</code>.
     * Inicializa los componentes de la interfaz con la información de la factura.
     *
     * @param nombreCliente Nombre del cliente.
     * @param correoCliente Correo electrónico del cliente.
     * @param nombrePelicula Nombre de la película reservada.
     * @param numeroAsientos Número de asientos reservados.
     * @param total Total a pagar por la reserva.
     */

    public form4(String nombreCliente, String correoCliente, String nombrePelicula, int numeroAsientos, int total) {
        // Configurar el texto de los labels con la información de la factura
        labelfactura.setText("Factura");
        labelnombre.setText("Nombre: " + nombreCliente);
        labelcorreo.setText("Correo: " + correoCliente);
        labelpelicula.setText("Película: " + nombrePelicula);
        label1numero_asientos.setText("Número de asientos: " + numeroAsientos);
        labeltotal.setText("Total: $" + total);


        // Configurar ActionListener para el botón de volver al login
        volverAlLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Crear una nueva ventana de login
                JFrame frame1 = new JFrame("Login");
                frame1.setContentPane(new login().panel_login);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);
                // Cerrar la ventana de la factura
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(factura);
                frame.dispose();
            }
        });
    }

    /**
     * Obtiene el panel de la factura para ser mostrado en la ventana.
     *
     * @return JPanel que contiene la interfaz de la factura.
     */
    public JPanel getFacturaPanel() {
        return factura;
    }
}
