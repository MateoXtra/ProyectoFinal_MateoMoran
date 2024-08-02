package Interfaz_Cliente;

import java.util.UUID;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class form2 {
    private JButton DEADPOOLYWOLVERINEButton;
    private JButton INTENSAMENTE2Button;
    private JButton MIVILLANOFAVORITO4Button;
    private JButton TORNADOSButton;
    public JPanel cartelera;
    private JButton CARNADAButton;
    private JButton DENOCHECONELButton;
    private JButton BLACKPINKBORNPINKButton;
    private String clienteCorreo;

    public form2() {
        DEADPOOLYWOLVERINEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula1();
            }
        });
        INTENSAMENTE2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula2();

            }
        });
        MIVILLANOFAVORITO4Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula3();

            }
        });
        TORNADOSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula4();

            }
        });
        CARNADAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula5();

            }
        });
        DENOCHECONELButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula6();

            }
        });
        BLACKPINKBORNPINKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPelicula7();

            }
        });
    }

    private void agregarPelicula1() {
        String URL = "jdbc:mysql://localhost:3306/cine_reservas";
        String USER = "root";
        String PASSWORD = "123456";

        String queryPeliculas = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        // Información de la película
        String nombrePelicula = "DEADPOOL Y WOLVERINE"; // Nombre de la película
        String horario = "{\"lunes\": \"20:00\", \"martes\": \"22:00\", \"miércoles\": \"18:00\"}"; // Horario

        // Generar un ID único para la película
        String idPelicula = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryPeliculas)) {

            preparedStatement.setString(1, idPelicula);
            preparedStatement.setString(2, nombrePelicula);
            preparedStatement.setString(3, horario);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Película '" + nombrePelicula + "' agregada exitosamente.");
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cartelera);
                frame.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la película.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la película: " + e.getMessage());
        }
    }


    private void agregarPelicula2() {
        String URL = "jdbc:mysql://localhost:3306/cine_reservas";
        String USER = "root";
        String PASSWORD = "123456";

        String queryPeliculas = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        // Información de la película
        String nombrePelicula = "INTENSAMENTE 2"; // Nombre de la película
        String horario = "{\"lunes\": \"20:00\", \"martes\": \"22:00\", \"miércoles\": \"18:00\"}"; // Horario en formato JSON

        // Generar un ID único para la película
        String idPelicula = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryPeliculas)) {

            preparedStatement.setString(1, idPelicula);
            preparedStatement.setString(2, nombrePelicula);
            preparedStatement.setString(3, horario);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Película '" + nombrePelicula + "' agregada exitosamente.");
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cartelera);
                frame.dispose();


            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la película.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la película: " + e.getMessage());
        }
    }


    private void agregarPelicula3() {
        String URL = "jdbc:mysql://localhost:3306/cine_reservas";
        String USER = "root";
        String PASSWORD = "123456";

        String queryPeliculas = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        // Información de la película
        String nombrePelicula = "MI VILLANO FAVORITO 4"; // Nombre de la película
        String horario = "{\"lunes\": \"20:00\", \"martes\": \"22:00\", \"miércoles\": \"18:00\"}"; // Horario en formato JSON

        // Generar un ID único para la película
        String idPelicula = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryPeliculas)) {

            preparedStatement.setString(1, idPelicula);
            preparedStatement.setString(2, nombrePelicula);
            preparedStatement.setString(3, horario);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Película '" + nombrePelicula + "' agregada exitosamente.");
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cartelera);
                frame.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la película.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la película: " + e.getMessage());
        }
    }


    private void agregarPelicula4() {
        String URL = "jdbc:mysql://localhost:3306/cine_reservas";
        String USER = "root";
        String PASSWORD = "123456";

        String queryPeliculas = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        // Información de la película
        String nombrePelicula = "TORNADOS"; // Nombre de la película
        String horario = "{\"lunes\": \"20:00\", \"martes\": \"22:00\", \"miércoles\": \"18:00\"}"; // Horario en formato JSON

        // Generar un ID único para la película
        String idPelicula = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryPeliculas)) {

            preparedStatement.setString(1, idPelicula);
            preparedStatement.setString(2, nombrePelicula);
            preparedStatement.setString(3, horario);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Película '" + nombrePelicula + "' agregada exitosamente.");
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cartelera);
                frame.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la película.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la película: " + e.getMessage());
        }
    }


    private void agregarPelicula5() {
        String URL = "jdbc:mysql://localhost:3306/cine_reservas";
        String USER = "root";
        String PASSWORD = "123456";

        String queryPeliculas = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        // Información de la película
        String nombrePelicula = "CARNADA"; // Nombre de la película
        String horario = "{\"lunes\": \"20:00\", \"martes\": \"22:00\", \"miércoles\": \"18:00\"}"; // Horario en formato JSON

        // Generar un ID único para la película
        String idPelicula = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryPeliculas)) {

            preparedStatement.setString(1, idPelicula);
            preparedStatement.setString(2, nombrePelicula);
            preparedStatement.setString(3, horario);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Película '" + nombrePelicula + "' agregada exitosamente.");
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cartelera);
                frame.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la película.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la película: " + e.getMessage());
        }
    }


    private void agregarPelicula6() {
        String URL = "jdbc:mysql://localhost:3306/cine_reservas";
        String USER = "root";
        String PASSWORD = "123456";

        String queryPeliculas = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        // Información de la película
        String nombrePelicula = "DE NOCHE CON EL DIABLO"; // Nombre de la película
        String horario = "{\"lunes\": \"20:00\", \"martes\": \"22:00\", \"miércoles\": \"18:00\"}"; // Horario en formato JSON

        // Generar un ID único para la película
        String idPelicula = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryPeliculas)) {

            preparedStatement.setString(1, idPelicula);
            preparedStatement.setString(2, nombrePelicula);
            preparedStatement.setString(3, horario);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Película '" + nombrePelicula + "' agregada exitosamente.");
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cartelera);
                frame.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la película.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la película: " + e.getMessage());
        }
    }

    private void agregarPelicula7() {
        String URL = "jdbc:mysql://localhost:3306/cine_reservas";
        String USER = "root";
        String PASSWORD = "123456";

        String queryPeliculas = "INSERT INTO peliculas (id, nombre_pelicula, horario) VALUES (?, ?, ?)";

        // Información de la película
        String nombrePelicula = "BLACKPINK BORN PINK"; // Nombre de la película
        String horario = "{\"lunes\": \"20:00\", \"martes\": \"22:00\", \"miércoles\": \"18:00\"}"; // Horario en formato JSON

        // Generar un ID único para la película
        String idPelicula = UUID.randomUUID().toString();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(queryPeliculas)) {

            preparedStatement.setString(1, idPelicula);
            preparedStatement.setString(2, nombrePelicula);
            preparedStatement.setString(3, horario);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                JOptionPane.showMessageDialog(null, "Película '" + nombrePelicula + "' agregada exitosamente.");
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(cartelera);
                frame.dispose();

            } else {
                JOptionPane.showMessageDialog(null, "No se pudo agregar la película.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al agregar la película: " + e.getMessage());
        }
    }
}


