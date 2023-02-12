# **Challenge Nisum**

1. Clonar proyecto desde el repositorio.

2. Descargar las dependencias del build.gradle.

3. El proyecto fue desarrollado con **flyway** para la creacion de tablas y carga de datos iniciales, dentro del archivo: **/resources/db/migration/V1.0__create_tables.sql ** se encuentran el script para creacion de tablas y carga de datos iniciales.

4. El proyecto se despliega en el puerto 8080 deacuerdo a la configuración en el archivo application.yml.

5. El contextPath de la app esta configurado con el valor /api.

6. Las configuraciones de las expresiones regulares en la base de datos se cargan en el arranque inicial las mismas que pueden ser modificadas / creadas / listadas, con los siguientes endpoints: 

#### **  Petición GET:  Obtiene las configuraciones existentes**
#### ** http://localhost:8080/api/global/configuration**
  
#### Respuesta:
[{
        "id": "d0d796fc-1b3b-47b7-aa1a-6f3a6ceabe77",
        "name": "PASSWORD_REGULAR_EXPRESSION",
        "description": "Patron que valida el password",
        "pattern": "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$"
    },
    {
        "id": "164fc319-d79a-4546-a671-b4ce639c620a",
        "name": "EMAIL_REGULAR_EXPRESSION",
        "description": "Patron que valida el email",
        "pattern": "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$"
    }]

#### 
**  Petición POST:  Crea una nueva configuración siempre y cuando el campo name no exista en la Bdd
  http://localhost:8080/api/global/configuration/create**

#### RequestBody:
{
    "name": "EMAIL_REGULAR_EXPRESSION",
    "description": "Patron que valida el email",
    "pattern": "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$"
}

#### Respuesta:

{
	"id": "9d4836f7-3918-4249-8a5c-f12cfa5aa7b5",
    "name": "EMAIL_REGULAR_EXPRESSION",
    "description": "Patron que valida el email",
    "pattern": "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$"
}

#### 
**  Petición PUT:  Actualiza una  configuración en base a al id de la configuración 
  http://localhost:8080/api/global/configuration/9d4836f7-3918-4249-8a5c-f12cfa5aa7b5**

#### RequestBody:
{
    "name": "EMAIL_REGULAR_EXPRESSION",
    "description": "Patron que valida el email modificado",
    "pattern": "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$"
}

#### Respuesta:

{
	"id": "9d4836f7-3918-4249-8a5c-f12cfa5aa7b5",
    "name": "EMAIL_REGULAR_EXPRESSION",
    "description": "Patron que valida el email modificado",
    "pattern": "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$"
}


Para ingresar a la consola de h2: http://localhost:8080/api/h2-console Datos para configurar conexión a la base de datos: Driver Class: org.h2.Driver JDBC URL: jdbc:h2:mem:nisum username: admin  /  password: system2023

El proyecto tiene la swagger para la documentacion y test de end points, para ingresar apuntar la siguiente ruta: http://localhost:8080/api/swagger-ui/index.html#/

El diagrama de la solución se encuentra en la ruta: src\main\resources\diagrama\diagrama.png

Proyecto desarrollado por **Diego Acosta** &copy;
