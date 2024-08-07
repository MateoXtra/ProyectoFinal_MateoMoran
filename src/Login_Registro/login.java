package Login_Registro;

import Interfaz_Admin.AdminForm;
import Interfaz_Cliente.form2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class login {
    public JPanel panel_login;
    private JComboBox<String> comboBox1;
    private JButton button1;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton registrarseButton;

    public login() {
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"Cliente", "Administrador"}));

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarse();
            }
        });
    }

    private void login() {
        /*String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";*/

        String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";

        String correo = textField1.getText();
        String contrasena = new String(passwordField1.getPassword());
        String tipo = (String) comboBox1.getSelectedItem();

        String query = "SELECT * FROM usuarios WHERE correo = ? AND contrasena = ? AND tipo = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, contrasena);
            preparedStatement.setString(3, tipo.toLowerCase());

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como " + tipo);
                    JFrame frame = new JFrame();
                    if ("cliente".equalsIgnoreCase(tipo)) {
                        form2 formulario2 = new form2(correo);
                        frame.setContentPane(formulario2.getCartelera());
                    } else if ("administrador".equalsIgnoreCase(tipo)) {
                        frame.setContentPane(new AdminForm().AdministradorPanel);
                    }
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setVisible(true);
                    SwingUtilities.getWindowAncestor(button1).dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos");
        }
    }

    public void registrarse() {
        /*String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";*/

        String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";

        JTextField correoField = new JTextField();
        JTextField nombreField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        String[] options = {"Cliente", "Administrador"};
        JComboBox<String> tipoBox = new JComboBox<>(options);

        Object[] fields = {
                "Correo:", correoField,
                "Nombre:", nombreField,
                "Contraseña:", passwordField,
                "Tipo:", tipoBox
        };

        int result = JOptionPane.showConfirmDialog(null, fields, "Registro", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String correo = correoField.getText();
            String nombre = nombreField.getText();
            String contrasena = new String(passwordField.getPassword());
            String tipo = (String) tipoBox.getSelectedItem();

            if (tipo.equals("Administrador") && !correo.endsWith("@admincine.com")) {
                JOptionPane.showMessageDialog(null, "Para registrar un administrador, el correo debe terminar en '@admincine.com'.");
                return;
            }

            if (tipo.equals("Cliente") && !correo.endsWith("@gmail.com")) {
                JOptionPane.showMessageDialog(null, "Para registrar un cliente, el correo debe terminar en '@gmail.com'.");
                return;
            }

            String queryUsuarios = "INSERT INTO usuarios (correo, nombre, contrasena, tipo) VALUES (?, ?, ?, ?)";
            String querySpecific = tipo.equals("Cliente") ?
                    "INSERT INTO clientes (correo, nombre, contrasena) VALUES (?, ?, ?)" :
                    "INSERT INTO administrador (correo, nombre, contrasena) VALUES (?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(queryUsuarios)) {
                    preparedStatement.setString(1, correo);
                    preparedStatement.setString(2, nombre);
                    preparedStatement.setString(3, contrasena);
                    preparedStatement.setString(4, tipo.toLowerCase());
                    preparedStatement.executeUpdate();
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement(querySpecific)) {
                    preparedStatement.setString(1, correo);
                    preparedStatement.setString(2, nombre);
                    preparedStatement.setString(3, contrasena);
                    preparedStatement.executeUpdate();
                }

                JOptionPane.showMessageDialog(null, tipo + " registrado exitosamente.");
            } catch (SQLIntegrityConstraintViolationException e) {
                JOptionPane.showMessageDialog(null, "El correo ya está registrado. Intenta con otro.");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
                JOptionPane.showMessageDialog(null, "Error al registrar " + tipo + ": " + ex.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new login().panel_login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
