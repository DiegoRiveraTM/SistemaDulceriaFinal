Sistema Dulcería
Descripción

Proyecto académico desarrollado en Java (JavaFX) con conexión a MySQL.
El sistema simula la gestión de una dulcería con enfoque en estructuras de datos, implementando una cola de prioridad para manejar categorías de dulces y un árbol binario para ordenar y mostrar productos.

Incluye además una interfaz gráfica sencilla para el manejo de productos y usuarios, así como control de inventario.

Características principales

Desarrollo en Java utilizando JavaFX para la interfaz gráfica.

Base de datos MySQL para el almacenamiento de información.

Implementación de cola de prioridad para gestionar dulces por categoría.

Implementación de árbol binario para ordenar productos.

Funcionalidades principales:

Registro e inicio de sesión de usuarios.

Agregar nuevos productos con imagen, descripción, precio y stock.

Visualización de productos en tarjetas gráficas.

Eliminación de productos en orden de prioridad.

Cambio de vista entre cola de prioridad y árbol binario.

Tecnologías utilizadas

Lenguaje: Java 17+

Interfaz gráfica: JavaFX

Base de datos: MySQL

Gestión de dependencias: Maven

Estructura del proyecto

src/main/java/com.example.sistemadulceria

controller/ Controladores de la interfaz (JavaFX).

dao/ Acceso a datos (MySQL).

estructuras/ Implementación de estructuras de datos (cola y árbol binario).

model/ Modelos de entidades (Producto, Usuario, etc.).

util/ Conexión a la base de datos.

src/main/resources/

fxml/ Vistas en FXML.

imagenes_dulces/ Imágenes de productos.

images/ Recursos gráficos de la interfaz.

Requisitos previos

Java 17 o superior.

Maven instalado y configurado.

MySQL instalado y corriendo en el sistema.

Base de datos creada con las tablas correspondientes (producto, tipo_dulce, usuario, etc.).

Ejecución

Clonar el repositorio:

git clone https://github.com/DiegoRiveraTM/SistemaDulceriaFinal.git
cd SistemaDulceriaFinal


Configurar la conexión a la base de datos en el archivo config.properties (no incluido en el repositorio por seguridad).

Compilar y ejecutar con Maven:

mvn clean install
mvn javafx:run

Nota importante

El archivo config.properties con las credenciales de la base de datos no está incluido en este repositorio. Cada usuario debe crear su propio archivo y configurarlo localmente.
