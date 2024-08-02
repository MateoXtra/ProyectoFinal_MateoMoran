import Login_Registro.login;
import java.util.UUID;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        String URL = "jdbc:mysql://localhost:3306/cine_reservas";
        String USER = "root";
        String PASSWORD = "123456";

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