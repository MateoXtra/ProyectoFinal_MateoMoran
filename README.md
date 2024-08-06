EXPLICACION PROYECTO CINE RESERVA
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









