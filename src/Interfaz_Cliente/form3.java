package Interfaz_Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

public class form3 {
    public JPanel reserva_asientos;
    private JSpinner spinner1;
    private JButton continuarButton;
    private JLabel label; // Etiqueta para mostrar el total
    private String clienteCorreo;
    private String idPelicula; // ID de la película proporcionado
    private final int COSTO_ASIENTO = 3; // Costo por asiento

    public form3(String clienteCorreo, String idPelicula) {
        this.clienteCorreo = clienteCorreo;
        this.idPelicula = idPelicula;

        // Inicializa el JPanel si es nulo
        if (reserva_asientos == null) {
            reserva_asientos = new JPanel();
            spinner1 = new JSpinner();
            continuarButton = new JButton("Continuar");
            label = new JLabel("Total: $0");
            reserva_asientos.add(spinner1);
            reserva_asientos.add(continuarButton);
            reserva_asientos.add(label);
        }

        // Configura el ActionListener para el JSpinner
        spinner1.addChangeListener(e -> actualizarTotal());

        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idAsiento = (Integer) spinner1.getValue(); // Obtener el número de asiento desde el JSpinner
                reservarAsiento(idAsiento);
            }
        });
    }

    private void actualizarTotal() {
        int cantidadAsientos = (Integer) spinner1.getValue();
        int total = cantidadAsientos * COSTO_ASIENTO;
        label.setText("Total: $" + total);
    }

    private void reservarAsiento(int idAsiento) {
        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

        String queryReservas = "INSERT INTO reservas (id, cliente_id, pelicula_id, asiento_id, fecha_reserva) VALUES (?, ?, ?, ?, ?)";

        // Generar un ID único para la reserva
        String idReserva = UUID.randomUUID().toString();

        // Obtener el total de la reserva
        int cantidadAsientos = (Integer) spinner1.getValue();
        int totalReserva = cantidadAsientos * COSTO_ASIENTO;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryReservas)) {

            preparedStatement.setString(1, idReserva);
            preparedStatement.setString(2, clienteCorreo); // El correo del cliente como ID
            preparedStatement.setString(3, idPelicula); // El ID de la película
            preparedStatement.setInt(4, idAsiento); // El ID del asiento
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis())); // Fecha de reserva

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(reserva_asientos, "Reserva de asiento realizada exitosamente.\nTotal: $" + totalReserva);
            } else {
                JOptionPane.showMessageDialog(reserva_asientos, "No se pudo realizar la reserva.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reserva_asientos, "Error al realizar la reserva: " + e.getMessage());
        }
    }
}
