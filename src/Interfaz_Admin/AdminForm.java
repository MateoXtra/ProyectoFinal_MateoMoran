package Interfaz_Admin;
import Login_Registro.login;
import java.util.UUID;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;

/**
 * La clase <code>AdminForm</code> representa la interfaz de administración del sistema de reservas.
 * Permite al administrador agregar, actualizar, eliminar películas y clientes, cargar datos y ver estadísticas de ocupacion.
 *
 * @author Mateo Morán
 */
public class AdminForm {
    private JButton agregarPeliculaButton; // Botón para agregar una película
    private JButton actualizarPeliculaButton; // Botón para actualizar una película
    private JButton eliminarPeliculaButton; // Botón para eliminar una película
    private JButton cargarPeliculasButton; // Botón para cargar la lista de películas
    private JButton agregarClienteButton; // Botón para agregar un cliente
    private JButton actualizarClienteButton; // Botón para actualizar un cliente
    private JButton eliminarClienteButton; // Botón para eliminar un cliente
    private JButton cargarDatosClienteButton; // Botón para cargar la lista de clientes
    private JButton cargarEstadisticasButton; // Botón para cargar estadísticas de ocupaciónv
    public JPanel AdministradorPanel; // Panel principal del administrador
    private JButton volverButton; // Botón para volver al loginv

    // Configuración de conexión a la base de datos
    String URL = "jdbc:mysql://localhost:3306/cine_reserva";
    String USER = "root";
    String PASSWORD = "123456";


    // Configuración de conexión a la base de datos en la nube
    /*String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
     String USER = "sql10724198";
     String PASSWORD = "MA6tTZqL72";*/

    /**
     * Constructor de la clase <code>AdminForm</code>.
     * Inicializa los componentes de la interfaz y configura los ActionListeners para los botones.
     */
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
                // Regresar al panel de login
                JFrame frame1 = new JFrame("Login");
                frame1.setContentPane(new login().panel_login);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);

                // Cerrar la ventana del panel de administración
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(AdministradorPanel);
                frame.dispose();
            }
        });
    }

    /**
     * Agrega una nueva película a la base de datos.
     */
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

    /**
     * Actualiza los detalles de una película existente en la base de datos.
     */
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

    /**
     * Elimina una película de la base de datos.
     */
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

    /**
     * Carga y muestra la lista de películas desde la base de datos.
     */
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

    /**
     * Agrega un nuevo cliente a la base de datos.
     */
    private void agregarCliente() {
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la contraseña del cliente:");

        if (correo == null || correo.isEmpty() || nombre == null || nombre.isEmpty() || contrasena == null || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Todos los campos deben ser completados.");
            return;
        }

        // Validar formato del correo
        if (!correo.endsWith("@gmail.com")) {
            JOptionPane.showMessageDialog(null, "Para registrar un cliente, el correo debe terminar en '@gmail.com'.");
            return;
        }
        // Hashear contraseña
        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());

        // Consulta SQL para agregar clientes
        String queryInsert = "INSERT INTO clientes (correo, nombre, contrasena) VALUES (?, ?, ?)";

        // Intentar la conexion para agregar clientes
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

    /**
     * Actualiza la información de un cliente en la base de datos.
     */
    private void actualizarCliente() {
        String correo = JOptionPane.showInputDialog("Ingrese el correo del cliente a actualizar:");
        String nombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del cliente:");
        String contrasena = JOptionPane.showInputDialog("Ingrese la nueva contraseña del cliente:");

        String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());

        String queryClientes = "UPDATE clientes SET nombre = ?, contrasena = ? WHERE correo = ?";
        String queryUsuarios = "UPDATE usuarios SET nombre = ?, contrasena = ? WHERE correo = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            try (PreparedStatement stmtClientes = conn.prepareStatement(queryClientes)) {
                stmtClientes.setString(1, nombre);
                stmtClientes.setString(2, hashedPassword);
                stmtClientes.setString(3, correo);
                stmtClientes.executeUpdate();
            }
            try (PreparedStatement stmtUsuarios = conn.prepareStatement(queryUsuarios)) {
                stmtUsuarios.setString(1, nombre);
                stmtUsuarios.setString(2, hashedPassword);
                stmtUsuarios.setString(3, correo);
                stmtUsuarios.executeUpdate();
            }

            JOptionPane.showMessageDialog(null, "Cliente actualizado exitosamente.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Error al actualizar el cliente.");
        }
    }

    /**
     * Elimina un cliente de la base de datos.
     * También elimina las reservas asociadas y actualiza el estado de los asientos.
     */
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

            // Eliminar reservas y clientes
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

    /**
     * Carga y muestra la lista de clientes desde la base de datos.
     */
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

    /**
     * Carga y muestra las estadísticas de ocupación de las películas desde la base de datos.
     */
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

    /**
     * Método principal para ejecutar la aplicación y mostrar la interfaz de administración.
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Panel de Administración");
        frame.setContentPane(new AdminForm().AdministradorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
