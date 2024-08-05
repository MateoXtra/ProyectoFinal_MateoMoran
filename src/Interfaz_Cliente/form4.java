package Interfaz_Cliente;

import javax.swing.*;

public class form4 {
    public JPanel factura;
    private JLabel label0;
    private JLabel labelnombre;
    private JLabel labelcorreo;
    private JLabel labelpelicula;
    private JLabel label1numero_asientos;
    private JLabel labeltotal;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;

    public form4(String nombreCliente, String correoCliente, String nombrePelicula, int numeroAsientos, int total) {
        label1.setText(nombreCliente);
        label2.setText(correoCliente);
        label3.setText(nombrePelicula);
        label4.setText(String.valueOf(numeroAsientos));
        label5.setText("$" + total);
    }

    public JPanel getFacturaPanel() {
        return factura;
    }
}
