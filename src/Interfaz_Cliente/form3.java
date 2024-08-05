package Interfaz_Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            spinner1 = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Establecer un rango razonable para los asientos
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
                int cantidadAsientos = (Integer) spinner1.getValue();
                reservarAsientos(cantidadAsientos);
            }
        });
    }

    private void actualizarTotal() {
        int cantidadAsientos = (Integer) spinner1.getValue();
        int total = cantidadAsientos * COSTO_ASIENTO;
        label.setText("Total: $" + total);
    }

    private void reservarAsientos(int cantidadAsientos) {
        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

        String queryReservas = "INSERT INTO reservas (id, cliente_id, pelicula_id, asiento_id, fecha_reserva) VALUES (?, ?, ?, ?, ?)";
        String idReserva = UUID.randomUUID().toString();

        // Recorre la cantidad de asientos seleccionados y realiza la reserva
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            for (int i = 0; i < cantidadAsientos; i++) {
                // Generar ID único para cada reserva de asiento
                String idReservaAsiento = UUID.randomUUID().toString();

                // Seleccionar un asiento disponible
                int idAsiento = obtenerAsientoDisponible(connection);
                if (idAsiento == -1) {
                    JOptionPane.showMessageDialog(reserva_asientos, "No hay asientos disponibles.");
                    return;
                }

                // Insertar en la tabla reservas
                try (PreparedStatement preparedStatement = connection.prepareStatement(queryReservas)) {
                    preparedStatement.setString(1, idReservaAsiento);
                    preparedStatement.setString(2, clienteCorreo);
                    preparedStatement.setString(3, idPelicula);
                    preparedStatement.setInt(4, idAsiento);
                    preparedStatement.setTimestamp(5, new java.sql.Timestamp(System.currentTimeMillis()));
                    preparedStatement.executeUpdate();

                    // Actualizar el estado del asiento
                    actualizarEstadoAsiento(connection, idAsiento);
                    int totalReserva = cantidadAsientos * COSTO_ASIENTO;

                    JOptionPane.showMessageDialog(reserva_asientos, "Reserva de asientos realizada exitosamente.\nTotal: $" + totalReserva);

                    // Mostrar formulario de factura
                    JFrame frame = new JFrame("Factura");
                    form4 facturaForm = new form4("NombreCliente", clienteCorreo, "NombrePelicula", cantidadAsientos, totalReserva);
                    frame.setContentPane(facturaForm.getFacturaPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                }
            }
            JOptionPane.showMessageDialog(reserva_asientos, "Reserva de asientos realizada exitosamente.\nTotal: $" + (cantidadAsientos * COSTO_ASIENTO));

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reserva_asientos, "Error al realizar la reserva: " + e.getMessage());
        }
    }

    private int obtenerAsientoDisponible(Connection connection) {
        String query = "SELECT id FROM asientos WHERE reservado = FALSE LIMIT 1";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reserva_asientos, "Error al obtener asiento disponible: " + e.getMessage());
        }
        return -1; // No hay asientos disponibles
    }

    private void actualizarEstadoAsiento(Connection connection, int idAsiento) {
        String query = "UPDATE asientos SET reservado = TRUE WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idAsiento);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reserva_asientos, "Error al actualizar el estado del asiento: " + e.getMessage());
        }
    }
}
