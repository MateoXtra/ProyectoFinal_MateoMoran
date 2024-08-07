package usuarios;

/**
 * La clase <code>cliente</code> extiende la clase <code>usuario</code> y representa
 * a un cliente en el sistema. Esta clase incluye métodos para obtener y establecer
 * el nombre y la contraseña del cliente.
 *
 * <p>La clase <code>cliente</code> proporciona dos constructores y sobreescribe los métodos
 * heredados para manipular los atributos del cliente.</p>
 *
 * @author Mateo Morán
 */

public class cliente extends usuario{
    /** Nombre del cliente (específico de la clase <code>cliente</code>) */
    String nombre;
    /** Contraseña del cliente (específica de la clase <code>cliente</code>) */
    String contrasena;

    /**
     * Constructor para crear un nuevo objeto <code>cliente</code> con los valores
     * especificados. Utiliza el constructor de la clase base <code>usuario</code>.
     *
     * @param nombre    Nombre del cliente.
     * @param password  Contraseña del cliente.
     * @param tipo      Tipo del cliente.
     */
    public cliente(String nombre, String password, String tipo) {
        super(nombre, password, tipo);
    }

    /**
     * Obtiene el nombre del cliente.
     *
     * @return El nombre del cliente.
     */
    @Override
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     *
     * @param nombre El nuevo nombre del cliente.
     */
    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la contraseña del cliente.
     *
     * @return La contraseña del cliente.
     */
    @Override
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Establece la contraseña del cliente.
     *
     * @param contrasena La nueva contraseña del cliente.
     */
    @Override
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
