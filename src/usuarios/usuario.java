package usuarios;

public class usuario {
    String nombre;
    String contrasena;

    public usuario(String nombre, String password) {
        this.nombre = nombre;
        this.contrasena = password;
    }
    public String getNombre() {
        return nombre;
    }
}
