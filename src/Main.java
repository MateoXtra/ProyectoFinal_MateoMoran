import Login_Registro.login;
import java.util.UUID;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Es el punto de entrada de la aplicación que
 * establece una conexión con la base de datos y muestra la interfaz de
 * inicio de sesión del sistema de reservas de cine.
 *
 * En el método main, se realiza la conexión con la base de
 * datos y se inicializa la ventana de la aplicación.
 *
 * @author Mateo Morán
 */

public class Main {
    /**
     * Método principal que se ejecuta al iniciar la aplicación.
     *
     * <p>Este método establece una conexión con la base de datos y, si
     * la conexión es exitosa, muestra un mensaje de éxito. Luego, crea
     * y muestra la ventana principal de la aplicación con la interfaz
     * de inicio de sesión.</p>
     *
     */
    public static void main(String[] args) {

        // URL, usuario y contraseña para la conexión a la base de datos

        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

        /* URL y credenciales de conexión a la nube.

        String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";*/


        // Intentar establecer la conexión con la base de datos
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("Conexión a la base de datos establecida con éxito.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // Crear y configurar la ventana de login de cine reservas
        JFrame frame = new JFrame("CINE RESERVA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new login().panel_login);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.pack();




    }
}