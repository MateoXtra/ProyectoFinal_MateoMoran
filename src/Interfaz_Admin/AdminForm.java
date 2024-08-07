package Interfaz_Admin;

import Login_Registro.login;
import java.util.UUID;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
public class AdminForm {
    private JButton agregarButton;
    private JButton agregarPeliculaButton;
    private JButton actualizarPeliculaButton;
    private JButton eliminarPeliculaButton;
    private JButton cargarPeliculasButton;
    private JButton agregarClienteButton;
    private JButton actualizarClienteButton;
    private JButton eliminarClienteButton;
    private JButton cargarDatosClienteButton;
    private JButton cargarEstadisticasButton;
    public JPanel AdministradorPanel;
    private JButton volverButton;

            String URL = "jdbc:mysql://localhost:3306/cine_reserva";
            String USER = "root";
            String PASSWORD = "123456";

        /*String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";*/

    public AdminForm() {
        // Gestión de Películas
        agregarPeliculaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula();
            }
        });

        actualizarPeliculaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPelicula();
            }
        });

        eliminarPeliculaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarPelicula();
            }
        });

        cargarPeliculasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarPeliculas();
            }
        });

        // Gestión de Clientes
        agregarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarCliente();
            }
        });

        actualizarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });

        eliminarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }
        });

        cargarDatosClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarClientes();
            }
        });

        cargarEstadisticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarEstadisticas();
            }
        });

        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame1 = new JFrame("Login");
                frame1.setContentPane(new login().panel_login);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdministradorPanel);
                frame.dispose();
            }
        });
    }

    private void agregarPelicula() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre de la película:");
        String horario = JOptionPane.showInputDialog("Ingrese el horario (en formato JSON):");

        String query = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, UUID.randomUUID().toString());
            stmt.setString(2, nombre);
            stmt.setString(3, horario);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Película agregada exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar la película.");
        }
    }

    private void actualizarPelicula() {
        String id = JOptionPane.showInputDialog("Ingrese el ID de la película a actualizar:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre de la película:");
        String horario = JOptionPane.showInputDialog("Ingrese el nuevo horario (en formato JSON):");

        String query = "UPDATE peliculas SET nombre_pelicula = ?, horario = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.setString(2, horario);
            stmt.setString(3, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Película actualizada exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar la película.");
        }
    }

    private void eliminarPelicula() {
        String id = JOptionPane.showInputDialog("Ingrese el ID de la película a eliminar:");

        String query = "DELETE FROM peliculas WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Película eliminada exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar la película.");
        }
    }

    private void cargarPeliculas() {
        String query = "SELECT id, nombre_pelicula, horario FROM peliculas";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder peliculas = new StringBuilder("Películas:\n");
            while (rs.next()) {
                peliculas.append("ID: ").append(rs.getString("id"))
                        .append(", Nombre: ").append(rs.getString("nombre_pelicula"))
                        .append(", Horario: ").append(rs.getString("horario")).append("\n");
            }
            JOptionPane.showMessageDialog(null, peliculas.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar las películas.");
        }
    }

    private void agregarCliente() {
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña del cliente:");

        if (correo == null || correo.isEmpty() || nombre == null || nombre.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados.");
            return;
        }

        if (!correo.endsWith("@gmail.com")) {
            JOptionPane.showMessageDialog(null, "Para registrar un cliente, el correo debe terminar en '@gmail.com'.");
            return;
        }
        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());

        String queryInsert = "INSERT INTO clientes (correo, nombre, contrasena) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmtInsert = conn.prepareStatement(queryInsert)) {

            stmtInsert.setString(1, correo);
            stmtInsert.setString(2, nombre);
            stmtInsert.setString(3, hashedPassword);
            stmtInsert.executeUpdate();

                JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente.");

        } catch (SQLException e) {
            System.out.println("Error en la base de datos: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al agregar el cliente: " + e.getMessage());
        }
    }


    private void actualizarCliente() {
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente a actualizar:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del cliente:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la nueva contraseña del cliente:");

        String queryClientes = "UPDATE clientes SET nombre = ?, contrasena = ? WHERE correo = ?";
        String queryUsuarios = "UPDATE usuarios SET nombre = ?, contrasena = ? WHERE correo = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmtClientes = conn.prepareStatement(queryClientes)) {
                stmtClientes.setString(1, nombre);
                stmtClientes.setString(2, contrasena);
                stmtClientes.setString(3, correo);
                stmtClientes.executeUpdate();
            }
            try (PreparedStatement stmtUsuarios = conn.prepareStatement(queryUsuarios)) {
                stmtUsuarios.setString(1, nombre);
                stmtUsuarios.setString(2, contrasena);
                stmtUsuarios.setString(3, correo);
                stmtUsuarios.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente.");
        }
    }

    private void eliminarCliente() {
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente a eliminar:");

        String queryReservas = "SELECT asiento_id FROM reservas WHERE cliente_id = ?";
        String queryEliminarReservas = "DELETE FROM reservas WHERE cliente_id = ?";
        String queryActualizarAsiento = "UPDATE asientos SET reservado = FALSE WHERE id = ?";
        String queryEliminarClientes = "DELETE FROM clientes WHERE correo = ?";
        String queryEliminarUsuarios = "DELETE FROM usuarios WHERE correo = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmtReservas = conn.prepareStatement(queryReservas)) {
                stmtReservas.setString(1, correo);
                ResultSet rs = stmtReservas.executeQuery();

                while (rs.next()) {
                    int asientoId = rs.getInt("asiento_id");
                    try (PreparedStatement stmtActualizarAsiento = conn.prepareStatement(queryActualizarAsiento)) {
                        stmtActualizarAsiento.setInt(1, asientoId);
                        stmtActualizarAsiento.executeUpdate();
                    }
                }
            }

            try (PreparedStatement stmtEliminarReservas = conn.prepareStatement(queryEliminarReservas)) {
                stmtEliminarReservas.setString(1, correo);
                stmtEliminarReservas.executeUpdate();
            }
            try (PreparedStatement stmtEliminarClientes = conn.prepareStatement(queryEliminarClientes)) {
                stmtEliminarClientes.setString(1, correo);
                stmtEliminarClientes.executeUpdate();
            }
            try (PreparedStatement stmtEliminarUsuarios = conn.prepareStatement(queryEliminarUsuarios)) {
                stmtEliminarUsuarios.setString(1, correo);
                stmtEliminarUsuarios.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente: " + e.getMessage());
        }
    }

    private void cargarClientes() {
        String query = "SELECT correo, nombre FROM clientes";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder clientes = new StringBuilder("Clientes:\n");
            while (rs.next()) {
                clientes.append("Correo: ").append(rs.getString("correo"))
                        .append(", Nombre: ").append(rs.getString("nombre")).append("\n");
            }
            JOptionPane.showMessageDialog(null, clientes.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar los clientes.");
        }
    }

    private void cargarEstadisticas() {
        String queryOcupacion = "SELECT p.nombre_pelicula, COUNT(r.id) as reservas FROM reservas r " +
                "JOIN peliculas p ON r.pelicula_id = p.id GROUP BY r.pelicula_id";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(queryOcupacion);
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder estadisticas = new StringBuilder("Estadísticas de ocupación:\n");
            while (rs.next()) {
                estadisticas.append("Película: ").append(rs.getString("nombre_pelicula"))
                        .append(", Reservas: ").append(rs.getInt("reservas")).append("\n");
            }
            JOptionPane.showMessageDialog(null, estadisticas.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al cargar las estadísticas.");
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel de Administración");
        frame.setContentPane(new AdminForm().AdministradorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
