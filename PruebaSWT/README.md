# PruebaSWT
Tecnologías Utilizadar
 - MySQL
 - SWT
 - Maven
 - Spring
 - Hibernate
 - Jpa
 
 Entorno utilizado 
 - Windows 10 Pro 64 bits
 - Eclipse 2018-12 (4.10.0)
 - Java jre1.8.0_201
 
 
Configuración
----------
### spring/spring-context.xml
Aquí se encuentra la configuración del contexto de Spring.

### databse/database.properties
En este property está configurada la conexion a base de datos.

| jdbc.driverClassName |com.mysql.cj.jdbc.Driver |
|-- | -- |
| jdbc.url | jdbc:mysql://localhost:3306/juegosolimpicos |
| jdbc.username | root |
| jdbc.password | root |
| jpa.showSql | true |
| jpa.database | MYSQL |

### databse/queries.properties

En entre property esta almacenada las querty que tiene que devolver:
 * id_pais
 * nombre_pais
 * id_ciudad
 * nombre_ciudad
 * valor. Se devolverá el valor de la ciudad, en caso de que ésta sea NULL
   se devolverá el valor del país correspondiente.
 * descripción_tipo_jjoo: Si de invierno o de verano. 
   En caso de que el país no haya sido sede este campo tendrá NULL.
 * número_veces_sede: El número de veces que la ciudad ha sido sede.

Modificaciones para utilizar Web Services
--------
Al inyectar las depedencias de los DAOs, se podría utilizar la anotación @Qualifier para que utlizando la interface con los métodos CRUD
poder tener tanto un Bean preparado para base de datos y otro, con la misma implementación pero con los metodos del WebServices.


Estructura las clases
--------

Los principales paquetes de la aplicación son 

| Paquete    | Descripción|
| ---------- | ---------- |
| dao        | Contiene la gestión de la base de datos |
| dto        | Contiene la clase encargada de recoger la query nativa, mencionada anteriormente, y mostrarla en la pantalla SWT. |
| entities   | Contiene las entidades de base de datos   |
| services   | Contiene la lógica de negocio, en este caso la obtención de la native query y el mappeo al DTO.   |
| view       | Contiene las clases encargadas de crear la interdace gráfica.   |



~~~ 
PruebaSWT:.
src
├───main
│   ├───java
│   │   ├───controller
│   │   └───es
│   │       └───kevinrm
│   │           └───ejercicioswt
│   │               │   Main.java
│   │               │
│   │               ├───dao
│   │               │   ├───especific
│   │               │   │   │   CiudadDao.java
│   │               │   │   │   SedeJJOODao.java
│   │               │   │   │   TipoJJOODao.java
│   │               │   │   │
│   │               │   │   └───impl
│   │               │   │           CiudadDaoImpl.java
│   │               │   │           SedeJJOODaoImpl.java
│   │               │   │           TipoJJOODaoImpl.java
│   │               │   │
│   │               │   ├───generic
│   │               │   │   │   GenericDao.java
│   │               │   │   │
│   │               │   │   └───impl
│   │               │   │           GenericDaoImpl.java
│   │               │   │
│   │               │   └───nativequery
│   │               │       │   NativeQueryDao.java
│   │               │       │
│   │               │       └───Impl
│   │               │               NativeQueryDaoImpl.java
│   │               │
│   │               ├───dto
│   │               │       JuegosOlimpicosDTO.java
│   │               │
│   │               ├───entities
│   │               │       Ciudad.java
│   │               │       Pais.java
│   │               │       SedeJJOO.java
│   │               │       TipoJJOO.java
│   │               │
│   │               ├───services
│   │               │       JJOOService.java
│   │               │
│   │               │
│   │               └───view
│   │                   │   Window.java
│   │                   │
│   │                   └───components
│   │                           ComponentTable.java
│   │
│   └───resources
│       ├───database
│       │       database.properties
│       │       queries.properties
│       │
│       ├───META-INF
│       └───spring
│               spring-context.xml
│
└───test
├───java
│   │   juegosOlimpicosTest.java
│   │   SedeJJooTest.java
│   │
│   └───es
│       └───ejercicioSWT
│           └───prueba
│               └───Dao
│                       SedeJJOODaoImplPTest.java
│
└───resources
└───META-INF
~~~    

Posibles Errores
--------

He tenido un error con la codificación de base de datos por lo que me ha sido necesario modificarla ejecutando:
~~~
SET @@global.time_zone = '+00:00';
~~~
