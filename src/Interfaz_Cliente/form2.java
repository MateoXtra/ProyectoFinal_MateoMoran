package Interfaz_Cliente;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

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

    public form2(String clienteCorreo) {
        this.clienteCorreo = clienteCorreo;

        DEADPOOLYWOLVERINEButton.addActionListener(e -> agregarPelicula("DEADPOOL Y WOLVERINE"));
        INTENSAMENTE2Button.addActionListener(e -> agregarPelicula("INTENSAMENTE 2"));
        MIVILLANOFAVORITO4Button.addActionListener(e -> agregarPelicula("MI VILLANO FAVORITO 4"));
        TORNADOSButton.addActionListener(e -> agregarPelicula("TORNADOS"));
        CARNADAButton.addActionListener(e -> agregarPelicula("CARNADA"));
        DENOCHECONELButton.addActionListener(e -> agregarPelicula("DE NOCHE CON EL DIABLO"));
        BLACKPINKBORNPINKButton.addActionListener(e -> agregarPelicula("BLACKPINK BORN PINK"));
    }

    private void agregarPelicula(String nombrePelicula) {
        /*String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";*/

        String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";

        String queryPeliculas = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        // Generar un ID único para la película
        String idPelicula = UUID.randomUUID().toString();
        String horario = "{\"lunes\": \"20:00\", \"martes\": \"22:00\", \"miércoles\": \"18:00\"}";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryPeliculas)) {

            preparedStatement.setString(1, idPelicula);
            preparedStatement.setString(2, nombrePelicula);
            preparedStatement.setString(3, horario);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Película '" + nombrePelicula + "' agregada exitosamente.");

                JFrame frame1 = new JFrame("Reserva de asientos");
                form3 reservaForm = new form3(clienteCorreo, idPelicula);
                frame1.setContentPane(reservaForm.reserva_asientos);
                frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame1.pack();
                frame1.setVisible(true);

                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cartelera);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la película.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la película: " + e.getMessage());
        }
    }

    public JPanel getCartelera() {
        return cartelera;
    }
}
