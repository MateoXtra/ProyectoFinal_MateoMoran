EXPLICACION PROYECTO CINE RESERVA


LINK DEL MANUAL DE USUARIO: https://youtu.be/xF3_heJKrWk
-------------------------------------------------------------------------
1. BASE DE DATOS

![image](https://github.com/user-attachments/assets/6af31da4-d049-4382-b0e3-2e402aae912b)

Se crea base de datos llamada cine_reserva para almacenar todas la tablas e informacion.

TABLAS 

*(Se tiene a correo como primary key ya que al ser un servicio de cines, el correo sera lo importante para acceder al servicio.)

TABLA DE USUARIOS:

* Tabla de usuarios en donde se almacenaran datos tanto del cliente como del administrador, diferenciandolos segun su tipo (cliente o administrador)
* correo: es la clave primaria y se usa para identificar si el correo es de un cliente o un administrador.
* nombre: Almacena el nombre del usuario.
* contrasena: Guarda la contraseña del usuario
* tipo: Define el tipo de usuario, que puede ser 'cliente' o 'administrador'.


![image](https://github.com/user-attachments/assets/d6a7e71f-3950-4ca2-8260-c144e4eeb331)

TABLA DE CLIENTES:

* Tabla de clientes donde se guardara especificamente la informacion de los clientes, servira para manejar las reservas mas adelante.
* Se usa la FOREIGN KEY para establecer una relacion con la tabla de usuarios.
* correo: Es la clave primaria y referencia al correo del cliente en la tabla usuarios.
* nombre: Almacena el nombre del cliente.
* contrasena: Guarda la contraseña del cliente.

![image](https://github.com/user-attachments/assets/7082f060-b842-42d1-bf31-b748489eeacf)

TABLA DE ADMINISTRADORES:

* Tabla de administradores donde guardara especificamente la informacion de los administradores.
* Se usa la FOREIGN KEY para establecer una relacion con la tabla de usuarios.
* correo: Es la clave primaria y referencia al correo del administrador en la tabla usuarios.
* nombre: Almacena el nombre del administrador.
* contrasena: Guarda la contraseña del administrador.

![image](https://github.com/user-attachments/assets/d0e5e5ec-27fa-4802-a724-8401b01b404d)

TABLA DE PELICULAS:

* Tabla que identifica una pelicula por su id, se usa VARCHAR(36) para permitir el uso de UUIDs.
* nombre_pelicula almacenara el nombre de la pelicula
* horario JSON (tipo de dato para almacenar horarios de manera estructurada)

![image](https://github.com/user-attachments/assets/66998ed2-5ce0-4cfc-a0e1-a9cfcd479202)

TABLA DE ASIENTOS

* id: Clave primariA que identifica a un asiento.
* fila: Número de la fila del asiento.
* numero: Número del asiento en la fila.
* reservado: Indica si el asiento está reservado.
* UNIQUE (fila, numero): Evita que se dupliquen la combinacion de fila y numero para ser única.

![image](https://github.com/user-attachments/assets/4f7a2ce5-3db3-42a8-9a95-cd189210819f)

TABLA DE RESERVAS
* id: Clave primaria que identifica de manera única una reserva. (Se usa VARCHAR(36) para permitir el uso de UUIDs.)
* cliente_id: Referencia al correo del cliente que realizó la reserva.
* pelicula_id: Referencia al ID de la película reservada.
* asiento_id: Referencia al ID del asiento reservado.
* fecha_reserva: Registra la fecha y hora en que se realizó la reserva. (Se usa CURRENT_TIMESTAMP para establecer fecha y hora actual).
* FOREIGN KEY: Establece relaciones con las tablas clientes, peliculas y asientos.

 ![image](https://github.com/user-attachments/assets/27606634-522a-4d9e-8d49-ce4eb1714771)


INSTALACIONES EN JAVA.
CONECTOR DE JAVA:

Para usar la base de datos en java se necesita el conector. 
PASOS A SEGUIR:
1. Ir a https://dev.mysql.com/downloads/connector/j/
2. Seleccionar plataforma independiente y descargar el pimero ![image](https://github.com/user-attachments/assets/f1122f37-647e-4c7f-ad65-61c950b3cdf5)
3. Una vez descargado, ir al IntelliJ IDEA y en la esquina izqueirda buscar File>Project Structure >Libraries
4. Le damos al "+" y seleccionamos nuestra descarga del conector. ![image](https://github.com/user-attachments/assets/da8e8f9c-14c0-4689-ac0e-157bf51a91c1)
5. Por ultimo le damos apply y listo.


HASH DE CONTRASEÑAS JBCrypt » 0.4:

Para Hashear las contraseñas en la base de datos se necesita la dependencia de JBCrypt » 0.4.
PASOS A SEGUIR>
1. Ir a https://mvnrepository.com/artifact/org.mindrot/jbcrypt/0.4
2. Hacer click en "jar", en la parte de files.  ![image](https://github.com/user-attachments/assets/1c55b9c6-dc76-40c3-ba6d-4459b04217b8)
3. Se descargara el JBCrypt.jar.
4. ir al IntelliJ IDEA y en la esquina izqueirda buscar File>Project Structure > Modules.
5. Le damos al "+" y seleccionamos nuestra descarga. ![image](https://github.com/user-attachments/assets/c338b216-7c3f-448e-86dc-2c6a99936c8c)
6. Le damos en apply.



PANEL DE LOGIN Y REGISTRO:

![image](https://github.com/user-attachments/assets/f2ac2ebe-9d8c-4eb9-a1a7-6ba7c2fd6c3d)

Este panel permite iniciar sesion ademas de poder registrarse tanto como cliente o como administrador.

COMO CLIENTE:

Al ingresar las credenciales correctas de un cliente registrado, se pasara a la siguiente pantalla que muestra la cartelera de peliculas.
El cliente podra seleccionar la pelicula que le guste.

![image](https://github.com/user-attachments/assets/3054fd22-d82f-40af-a979-127ded3ab00f)

Al selecionar la pelicula se pasara a la pantlala de reserva de asientos, donde se puede elegir cuantos asientos se quiere ademas del total.

![image](https://github.com/user-attachments/assets/fe9a0ac5-9006-4782-8f72-ef5e0518c4f3)

Una vez hecho click en continuar, se mostrar la factura con los datos de la reserva del cliente y un boton para volver al login.

![image](https://github.com/user-attachments/assets/e1203511-9bbd-4839-888c-643555751b40)

En la base de datos se mostrara la reserva y los asientos reservados.

![image](https://github.com/user-attachments/assets/fc16cb55-063d-4f99-8c92-073cec8b1433)

![image](https://github.com/user-attachments/assets/3ef16935-b6c4-4c75-b3c2-d3dadc0b7702)



COMO ADMINISTRADOR:

Al ingresar las credenciales correctas de un administrador registrado, se pasara a la siguiente pantalla que muestra opciones de gestion, tanto de peliculas, clientes y una opcion para ver las estadisticas de ocupacion de las reservas realizadas.

![image](https://github.com/user-attachments/assets/90f76814-38ea-4534-9fc5-7ec4df090f2e)

*Gestion de peliculas:

Boton agregar pelicula: Este boton permite agregar el nombre de la pelicula, el horario en formato JSON ({"lunes": "20:00"}) y mostrara un mensaje de confirmacion.

![image](https://github.com/user-attachments/assets/07e7501f-39bc-4015-a3fe-250606cbb27e) ![image](https://github.com/user-attachments/assets/6dd5cc3c-0df5-4b65-bd2f-b6465efde122) ![image](https://github.com/user-attachments/assets/e9687103-d913-4d02-98ef-691513f0b44e)


Boton Actualizar pelicula: Este boton permite actualizar una pelicula por su ID, se puede actualziar el nombre y el horario. mostrara un mensaje de confirmacion.

![image](https://github.com/user-attachments/assets/44235830-06c8-448f-b954-ab05bd53e132) ![image](https://github.com/user-attachments/assets/701c0bb2-ea10-4090-b6a6-dd8bff5bffcc) ![image](https://github.com/user-attachments/assets/de6c4a4f-8949-4f3e-b3f7-a51e4d473fc6)

En la base de datos se podra verificar dicha actualizacion:  ![image](https://github.com/user-attachments/assets/fff1c51c-e73d-4828-82dd-bb5af560f4e6)

Boton Elimianr pelicula: Este boton permite eliminar una pelicula de la base de datos pur su ID. y mostrara un mensaje

![image](https://github.com/user-attachments/assets/78be0d77-0877-4bbd-a2cb-77ee1d7f9d20) ![image](https://github.com/user-attachments/assets/1d1acc47-244a-46df-a381-fa6ab0f98687)

Boton Cargar peliculas: Este boton permite cargar todas las peliculas disponibles.

![image](https://github.com/user-attachments/assets/a629abe4-20cb-4635-ad8c-bd43f1ab671c)


*Gestion de Clientes:

Boton agregar cliente: Permite agregar un cliente de la misma forma que el login al registrar uno. El correo debe terminar en "@gmail.com" ya que es el formato establecido para el registro de clientes.

![image](https://github.com/user-attachments/assets/e66cf4ea-6f26-4d4d-a51b-73a9e331cff3)

Nos mostrara un mensaje de confirmacion y el cliente se añade a la base de datos, con su contraseña hasheada.

![image](https://github.com/user-attachments/assets/28fce4a7-6ab5-4920-acac-9402541a033c)

Boton actualizar cliente: Nos permite actulizar el nombre y la contraseña de un cliente mediante su correo.

![image](https://github.com/user-attachments/assets/37fbbfda-5eb1-4a8f-a225-2096a2aaaca4) ![image](https://github.com/user-attachments/assets/be95075a-c8e8-44b8-8171-91c88f51dabc) ![image](https://github.com/user-attachments/assets/be8d2d44-4139-4c22-812b-c88bd767ba97)

Cuando se actualiza la contraseña se hashea en la base de datos.

![image](https://github.com/user-attachments/assets/9730ad18-9966-40e6-9b4d-f0844e01d30a)

Boton Eliminar cliente: Mediante el correo electronico se puede eliminar clientes, OJO: al eliminar un cliente con reserva, esta tambien se elimina y los asientos vuelven a estar disponibles.

![image](https://github.com/user-attachments/assets/50439905-556a-4390-b132-649aef011267)

Mostrara un mensaje de confirmacion y la reserva se eliminara.

![image](https://github.com/user-attachments/assets/dfa0e33f-586a-4de7-8bb4-b0df9cc419cd)

Boton Cargar datos cliente: Permite ver a los clientes disponibles en la base de datos.

![image](https://github.com/user-attachments/assets/1c084e94-22c2-493f-abf1-f3d5f09f61d7)

*Estadisticas de ocupacion:
Boton cargar estadisticas: Permite ver las estadisticas de las reservas disponibles, si no hay reservas no se mostrara nada.

![image](https://github.com/user-attachments/assets/795975ac-fd11-4057-a374-183d3228950f)

![image](https://github.com/user-attachments/assets/7bf24863-09ca-45b9-9105-05d6386fca65)







































