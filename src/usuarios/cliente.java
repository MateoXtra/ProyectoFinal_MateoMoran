package usuarios;

import java.util.ArrayList;

public class cliente extends usuario{
    String nombre;
    String contrasena;

    public cliente(String nombre, String contrasena) {
        super(nombre, contrasena);
        this.nombre = nombre;
        this.contrasena = contrasena;
    }
}
