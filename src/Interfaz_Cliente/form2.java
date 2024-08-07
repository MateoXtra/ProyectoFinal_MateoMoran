package Interfaz_Cliente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * La clase <code>form2</code> representa la interfaz para que los clientes puedan agregar películas y reservar asientos.
 * Proporciona botones para agregar diferentes películas a la base de datos y abrir la interfaz de reserva de asientos.
 *
 * @author Mateo Morán
 */

public class form2 {
    private JButton DEADPOOLYWOLVERINEButton;
    private JButton INTENSAMENTE2Button;
    private JButton MIVILLANOFAVORITO4Button;
    private JButton TORNADOSButton;
    private JPanel cartelera;
    private JButton CARNADAButton;
    private JButton DENOCHECONELButton;
    private JButton BLACKPINKBORNPINKButton;
    private final String clienteCorreo;
    /**
     * Constructor de la clase <code>form2</code>.
     * Inicializa los componentes de la interfaz y configura los oyentes de eventos para los botones.
     *
     * @param clienteCorreo Correo electrónico del cliente para la reserva de asientos.
     */

    public form2(String clienteCorreo) {
        this.clienteCorreo = clienteCorreo;

        // Configurar ActionListener para cada botón para agregar una película específica
        DEADPOOLYWOLVERINEButton.addActionListener(e -> agregarPelicula("DEADPOOL Y WOLVERINE"));
        INTENSAMENTE2Button.addActionListener(e -> agregarPelicula("INTENSAMENTE 2"));
        MIVILLANOFAVORITO4Button.addActionListener(e -> agregarPelicula("MI VILLANO FAVORITO 4"));
        TORNADOSButton.addActionListener(e -> agregarPelicula("TORNADOS"));
        CARNADAButton.addActionListener(e -> agregarPelicula("CARNADA"));
        DENOCHECONELButton.addActionListener(e -> agregarPelicula("DE NOCHE CON EL DIABLO"));
        BLACKPINKBORNPINKButton.addActionListener(e -> agregarPelicula("BLACKPINK BORN PINK"));
    }
    /**
     * Agrega una película a la base de datos y muestra la interfaz para la reserva de asientos.
     *
     * @param nombrePelicula Nombre de la película a agregar.
     */

    private void agregarPelicula(String nombrePelicula) {
        // URL, usuario y contraseña para la conexión a la base de datos
        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

       /* URL y credenciales de conexión a la nube.
        String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";*/

        // Consulta SQL para insertar una nueva película
        String queryPeliculas = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        // Generar un ID único para la película
        String idPelicula = UUID.randomUUID().toString();
        String horario = "{\"lunes\": \"20:00\", \"martes\": \"22:00\", \"miércoles\": \"18:00\"}";

        // Intentar establecer la conexión con la base de datos e insertar la película
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryPeliculas)) {

            preparedStatement.setString(1, idPelicula);
            preparedStatement.setString(2, nombrePelicula);
            preparedStatement.setString(3, horario);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                // Mostrar mensaje de éxito y abrir la interfaz de reserva de asientos
                JOptionPane.showMessageDialog(null, "Película '" + nombrePelicula + "' agregada exitosamente.");

                JFrame frame1 = new JFrame("Reserva de asientos");
                form3 reservaForm = new form3(clienteCorreo, idPelicula);
                frame1.setContentPane(reservaForm.reserva_asientos);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);

                // Cerrar la ventana actual
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cartelera);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la película.");
            }

        } catch (SQLException e) {
            // Manejar errores de conexión o consulta
            JOptionPane.showMessageDialog(null, "Error al agregar la película: " + e.getMessage());
        }
    }
    /**
     * Devuelve el panel de cartelera.
     *
     * @return El panel de cartelera.
     */
    public JPanel getCartelera() {
        return cartelera;
    }
}
