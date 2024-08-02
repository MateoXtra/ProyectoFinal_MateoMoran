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
    private String clienteCorreo;
    private String idPelicula; // ID de la película proporcionado

    public form3(String clienteCorreo, String idPelicula) {
        this.clienteCorreo = clienteCorreo;
        this.idPelicula = idPelicula;

        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idAsiento = (Integer) spinner1.getValue(); // Obtener el número de asiento desde el JSpinner
                reservarAsiento(idAsiento);
            }
        });
    }

    private void reservarAsiento(int idAsiento) {
        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

        String queryReservas = "INSERT INTO reservas (id, cliente_id, pelicula_id, asiento_id) VALUES (?, ?, ?, ?)";

        // Generar un ID único para la reserva
        String idReserva = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            // Insertar la reserva en la base de datos
            try (PreparedStatement preparedStatement = connection.prepareStatement(queryReservas)) {
                preparedStatement.setString(1, idReserva);
                preparedStatement.setString(2, clienteCorreo); // El correo del cliente como ID
                preparedStatement.setString(3, idPelicula); // El ID de la película
                preparedStatement.setInt(4, idAsiento); // El ID del asiento
                int filasAfectadas = preparedStatement.executeUpdate();
                if (filasAfectadas > 0) {
                    JOptionPane.showMessageDialog(reserva_asientos, "Reserva de asiento realizada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(reserva_asientos, "No se pudo realizar la reserva.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al realizar la reserva: " + e.getMessage());
            JOptionPane.showMessageDialog(reserva_asientos, "Error al realizar la reserva.");
        }
    }

    public form3(String clienteCorreo, String idPelicula) {
        this.clienteCorreo = clienteCorreo;
        this.idPelicula = idPelicula;

        // Verifica que el panel no sea nulo
        if (reserva_asientos == null) {
            reserva_asientos = new JPanel(); // o cargar el diseño de algún archivo si es necesario
            // Configurar componentes adicionales si es necesario
        }

        continuarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idAsiento = (Integer) spinner1.getValue(); // Obtener el número de asiento desde el JSpinner
                reservarAsiento(idAsiento);
            }
        });
    }

}

