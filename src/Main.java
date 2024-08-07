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
    public static void main(String[] args) {

        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

        /*String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";*/

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (connection != null) {
                System.out.println("Conexión a la base de datos establecida con éxito.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        JFrame frame = new JFrame("CINE RESERVA");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new login().panel_login);
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.pack();




    }
}