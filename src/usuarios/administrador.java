package usuarios;
/**
 * La clase <code>administrador</code> extiende de la clase <code>usuario</code> y representa
 * a un administrador en el sistema. Esta clase incluye métodos para obtener y establecer
 * el nombre y la contraseña del administrador.
 *
 * <p>La clase <code>administrador</code> proporciona dos constructores y sobreescribe los métodos
 * heredados para manipular los atributos del administrador.</p>
 *
 * @author Mateo Morán
 */

public class administrador extends usuario{
    /** Nombre del administrador (específico de la clase <code>administrador</code>) */
    String nombre;
    /** Contraseña del administrador (específica de la clase <code>administrador</code>) */
    String contrasena;

    /**
     * Constructor para crear un nuevo objeto <code>administrador</code> con los valores
     * especificados. Utiliza el constructor de la clase base <code>usuario</code>.
     *
     * @param nombre    Nombre del administrador.
     * @param password  Contraseña del administrador.
     * @param tipo      Tipo del administrador.
     */
    public administrador(String nombre, String password, String tipo) {
        super(nombre, password, tipo);
    }

    /**
     * Obtiene el nombre del administrador.
     *
     * @return El nombre del administrador.
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del administrador.
     *
     * @param nombre El nuevo nombre del administrador.
     */
    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la contraseña del administrador.
     *
     * @return La contraseña del administrador.
     */
    @Override
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del administrador.
     *
     * @param contrasena La nueva contraseña del administrador.
     */
    @Override
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
