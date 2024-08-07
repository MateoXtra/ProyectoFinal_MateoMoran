package usuarios;
/**
 * La clase <code>usuario</code> representa un usuario en el sistema, incluyendo
 * su nombre, contraseña y tipo de usuario.
 *
 * <p>Esta clase proporciona métodos para obtener y establecer las propiedades
 * del usuario.</p>
 *
 * @author Mateo Morán
 */

public class usuario {
    /** Nombre del usuario */
    String nombre;
    /** Contraseña del usuario */
    String contrasena;
    /** Tipo de usuario (por ejemplo, cliente o administrador) */
    String tipo;

    /**
     * Constructor para crear un nuevo objeto <code>usuario</code> con los valores
     * especificados.
     *
     * @param nombre    Nombre del usuario.
     * @param password  Contraseña del usuario.
     * @param tipo      Tipo del usuario (cliente o administrador).
     */
    public usuario(String nombre, String password, String tipo) {
        this.nombre = nombre;
        this.contrasena = password;
        this.tipo = tipo;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nuevo nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param contrasena La nueva contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el tipo del usuario.
     *
     * @return El tipo del usuario.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del usuario.
     *
     * @param tipo El nuevo tipo del usuario.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
