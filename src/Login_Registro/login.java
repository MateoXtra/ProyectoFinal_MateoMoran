package Login_Registro;
import org.mindrot.jbcrypt.BCrypt;
import Interfaz_Admin.AdminForm;
import Interfaz_Cliente.form2;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * La clase <code>login</code> maneja el proceso de inicio de sesión y registro de usuarios.
 * Proporciona una interfaz de usuario para el inicio de sesión y el registro, y se encarga de
 * autenticar a los usuarios y registrar nuevos usuarios en la base de datos.
 *
 * <p>Esta clase utiliza la biblioteca <code>BCrypt</code> para el hash de contraseñas y JDBC para
 * interactuar con la base de datos.</p>
 *
 * @author Mateo Morán
 */
public class login {
    public JPanel panel_login;
    private JComboBox<String> comboBox1;
    private JButton button1;
    private JPasswordField passwordField1;
    private JTextField textField1;
    private JButton registrarseButton;
    /**
     * Constructor de la clase <code>login</code>. Inicializa el panel de inicio de sesión y
     * asigna las acciones a los botones de iniciar sesión y registrarse.
     */

    public login() {
        // Configurar el JComboBox para seleccionar el tipo de usuario
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{"Cliente", "Administrador"}));

        // Configurar el ActionListener para el botón de inicio de sesión
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        // Configurar el ActionListener para el botón de registro
        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarse();
            }
        });
    }
    /**
     * Maneja el proceso de inicio de sesión. Valida las credenciales del usuario y muestra
     * la interfaz correspondiente para clientes o administradores.
     *
     * <p>Se conecta a la base de datos, verifica las credenciales utilizando BCrypt para comparar
     * las contraseñas hasheadas, y muestra la interfaz adecuada según el tipo de usuario.</p>
     */

    private void login() {
        // URL, usuario y contraseña para la conexión a la base de datos
        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

        /* URL y credenciales de conexión a la nube.
        String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";*/

        // Recuperar las credenciales del formulario de inicio de sesión
        String correo = textField1.getText();
        String contrasena = new String(passwordField1.getPassword());
        String tipo = (String) comboBox1.getSelectedItem();

        // Consulta SQL para verificar las credenciales del usuario
        String query = "SELECT contrasena FROM usuarios WHERE correo = ? AND tipo = ?";

        // Intentar establecer la conexión con la base de datos
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, correo);
            preparedStatement.setString(2, tipo.toLowerCase());

            // Ejecutar la consulta y verificar las credenciales
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("contrasena");
                    // Verificar la contraseña con BCrypt
                    if (BCrypt.checkpw(contrasena, hashedPassword)) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso como " + tipo);
                        JFrame frame = new JFrame();
                        // Mostrar la interfaz correspondiente según el tipo de usuario

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
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas");
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, "Error al conectar a la base de datos");
        }
    }

    /**
     * Maneja el proceso de registro de nuevos usuarios. Muestra un formulario para ingresar
     * los datos del usuario y los valida antes de insertar la información en la base de datos.
     *
     * <p>Utiliza BCrypt para hashear la contraseña antes de almacenarla y valida que el correo
     * electrónico sea válido según el tipo de usuario.</p>
     */
    public void registrarse() {
        // URL, usuario y contraseña para la conexión a la base de datos
        String URL = "jdbc:mysql://localhost:3306/cine_reserva";
        String USER = "root";
        String PASSWORD = "123456";

       /* URL y credenciales de conexión a la nube.
        String URL = "jdbc:mysql://sql10.freemysqlhosting.net:3306/sql10724198";
        String USER = "sql10724198";
        String PASSWORD = "MA6tTZqL72";*/

        // Crear campos de entrada para el registro
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

            // Validar el formato del correo según el tipo de usuario
            if (tipo.equals("Administrador") && !correo.endsWith("@admincine.com")) {
                JOptionPane.showMessageDialog(null, "Para registrar un administrador, el correo debe terminar en '@admincine.com'.");
                return;
            }

            if (tipo.equals("Cliente") && !correo.endsWith("@gmail.com")) {
                JOptionPane.showMessageDialog(null, "Para registrar un cliente, el correo debe terminar en '@gmail.com'.");
                return;
            }

            // Hash de la contraseña usando BCrypt
            String hashedPassword = BCrypt.hashpw(contrasena, BCrypt.gensalt());

            // Consultas SQL para insertar los datos del usuario
            String queryUsuarios = "INSERT INTO usuarios (correo, nombre, contrasena, tipo) VALUES (?, ?, ?, ?)";
            String querySpecific = tipo.equals("Cliente") ?
                    "INSERT INTO clientes (correo, nombre, contrasena) VALUES (?, ?, ?)" :
                    "INSERT INTO administrador (correo, nombre, contrasena) VALUES (?, ?, ?)";

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                try (PreparedStatement preparedStatement = connection.prepareStatement(queryUsuarios)) {
                    preparedStatement.setString(1, correo);
                    preparedStatement.setString(2, nombre);
                    preparedStatement.setString(3, hashedPassword);
                    preparedStatement.setString(4, tipo.toLowerCase());
                    preparedStatement.executeUpdate();
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement(querySpecific)) {
                    preparedStatement.setString(1, correo);
                    preparedStatement.setString(2, nombre);
                    preparedStatement.setString(3, hashedPassword);
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

    /**
     * Método principal para iniciar la aplicación de inicio de sesión. Crea y muestra la ventana
     * de inicio de sesión.
     *
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new login().panel_login);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
