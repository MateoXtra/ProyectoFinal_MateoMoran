package Interfaz_Cliente;
import java.util.ArrayList;
import java.util.List;
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
    private JLabel label;
    private String clienteCorreo;
    private String idPelicula;
    private final int COSTO_ASIENTO = 3;

    public form3(String clienteCorreo, String idPelicula) {
        this.clienteCorreo = clienteCorreo;
        this.idPelicula = idPelicula;


        spinner1.addChangeListener(e -> actualizarTotal());

        continuarButton.addActionListener(e -> {
            int cantidadAsientos = (Integer) spinner1.getValue();
            for (int i = 0; i < cantidadAsientos; i++) {
                int idAsiento = obtenerAsientoDisponible();
                if (idAsiento != -1) {
                    reservarAsientos(idAsiento);
                } else {
                    JOptionPane.showMessageDialog(reserva_asientos, "No hay suficientes asientos disponibles.");
                    break;
                }
            }
        });
    }

    private void actualizarTotal() {
        int cantidadAsientos = (Integer) spinner1.getValue();
        int total = cantidadAsientos * COSTO_ASIENTO;
        label.setText("Total: $" + total);
    }

    private int obtenerAsientoDisponible() {
        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

        /*String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";*/

        String query = "SELECT id FROM asientos WHERE reservado = FALSE LIMIT 1";
        int idAsiento = -1;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                idAsiento = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reserva_asientos, "Error al obtener asiento disponible: " + e.getMessage());
        }

        return idAsiento;
    }
    private void reservarAsientos(int cantidadAsientos) {
        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

        /*String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";*/

        String queryCheckAsientos = "SELECT id FROM asientos WHERE reservado = FALSE LIMIT ?";
        String queryReservas = "INSERT INTO reservas (id, cliente_id, pelicula_id, asiento_id) VALUES (?, ?, ?, ?)";
        String queryUpdateAsiento = "UPDATE asientos SET reservado = TRUE WHERE id = ?";
        String queryCliente = "SELECT nombre FROM clientes WHERE correo = ?";
        String queryPelicula = "SELECT nombre_pelicula FROM peliculas WHERE id = ?";

        // Generar IDs únicos para las reservas
        List<String> idsReservas = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement psCheck = connection.prepareStatement(queryCheckAsientos)) {
                psCheck.setInt(1, cantidadAsientos);
                ResultSet rs = psCheck.executeQuery();

                int asientosReservados = 0;
                while (rs.next() && asientosReservados < cantidadAsientos) {
                    int idAsiento = rs.getInt("id");
                    String idReserva = UUID.randomUUID().toString();
                    idsReservas.add(idReserva);

                    // Insertar la reserva en la base de datos
                    try (PreparedStatement psReserva = connection.prepareStatement(queryReservas)) {
                        psReserva.setString(1, idReserva);
                        psReserva.setString(2, clienteCorreo);
                        psReserva.setString(3, idPelicula);
                        psReserva.setInt(4, idAsiento);
                        psReserva.executeUpdate();
                    }

                    // Actualizar el estado del asiento
                    try (PreparedStatement psUpdate = connection.prepareStatement(queryUpdateAsiento)) {
                        psUpdate.setInt(1, idAsiento);
                        psUpdate.executeUpdate();
                    }

                    asientosReservados++;
                }

                if (asientosReservados == cantidadAsientos) {
                    // Obtener datos para la factura
                    String nombreCliente = null;
                    String nombrePelicula = null;

                    try (PreparedStatement psCliente = connection.prepareStatement(queryCliente)) {
                        psCliente.setString(1, clienteCorreo);
                        ResultSet rsCliente = psCliente.executeQuery();
                        if (rsCliente.next()) {
                            nombreCliente = rsCliente.getString("nombre");
                        }
                    }

                    try (PreparedStatement psPelicula = connection.prepareStatement(queryPelicula)) {
                        psPelicula.setString(1, idPelicula);
                        ResultSet rsPelicula = psPelicula.executeQuery();
                        if (rsPelicula.next()) {
                            nombrePelicula = rsPelicula.getString("nombre_pelicula");
                        }
                    }

                    int total = cantidadAsientos * COSTO_ASIENTO;

                    JFrame frameFactura = new JFrame("Factura");
                    form4 facturaForm = new form4(nombreCliente, clienteCorreo, nombrePelicula, cantidadAsientos, total);
                    frameFactura.setContentPane(facturaForm.getFacturaPanel());
                    frameFactura.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frameFactura.pack();
                    frameFactura.setVisible(true);

                    JFrame frameReserva = (JFrame) SwingUtilities.getWindowAncestor(reserva_asientos);
                    frameReserva.dispose();

                    JOptionPane.showMessageDialog(reserva_asientos, "Reserva de asientos realizada exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(reserva_asientos, "No se pudieron reservar todos los asientos.");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reserva_asientos, "Error al realizar la reserva: " + e.getMessage());
        }
    }




    private String obtenerNombrePelicula(String idPelicula) {
        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

        /*String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";*/

        String query = "SELECT nombre_pelicula FROM peliculas WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, idPelicula);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("nombre_pelicula");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(reserva_asientos, "Error al obtener nombre de la película: " + e.getMessage());
        }

        return "";
    }

}
