package Interfaz_Admin;
import java.util.UUID;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

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

    // Datos de conexión
    String URL = "jdbc:mysql://localhost:3306/cine_reservas";
    String USER = "root";
    String PASSWORD = "123456";

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

        // Estadísticas
        cargarEstadisticasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarEstadisticas();
            }
        });
    }

    private void agregarPelicula() {
        // Lógica para agregar película
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
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar la película.");
        }
    }

    private void actualizarPelicula() {
        // Lógica para actualizar película
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
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar la película.");
        }
    }

    private void eliminarPelicula() {
        // Lógica para eliminar película
        String id = JOptionPane.showInputDialog("Ingrese el ID de la película a eliminar:");

        String query = "DELETE FROM peliculas WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Película eliminada exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al eliminar la película.");
        }
    }

    private void cargarPeliculas() {
        // Lógica para cargar datos de películas
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
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar las películas.");
        }
    }

    private void agregarCliente() {
        // Lógica para agregar cliente
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña del cliente:");

        String query = "INSERT INTO clientes (correo, nombre, contrasena) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, correo);
            stmt.setString(2, nombre);
            stmt.setString(3, contrasena);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente agregado exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al agregar el cliente.");
        }
    }

    private void actualizarCliente() {
        // Lógica para actualizar cliente
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente a actualizar:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del cliente:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la nueva contraseña del cliente:");

        String query = "UPDATE clientes SET nombre = ?, contrasena = ? WHERE correo = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);
            stmt.setString(3, correo);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente.");

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente.");
        }
    }

    private void eliminarCliente() {
        // Lógica para eliminar usuarios
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente a eliminar:");

        String queryClientes = "DELETE FROM clientes WHERE correo = ?";
        String queryUsuarios = "DELETE FROM usuarios WHERE correo = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(queryUsuarios)) {

            stmt.setString(1, correo);

            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cliente eliminado exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al eliminar el cliente.");
        }
    }

    private void cargarClientes() {
        // Lógica para cargar datos de clientes
        String query = "SELECT correo, nombre FROM usuarios";
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
        // Lógica para cargar estadísticas de ocupación y ventas
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
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar las estadísticas.");
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel de Administracion ");
        frame.setContentPane(new AdminForm().AdministradorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
