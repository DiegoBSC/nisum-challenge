# **Challenge Nisum**

1. Clonar proyecto desde el repositorio.

2. Descargar las dependencias del build.gradle.

3. El proyecto fue desarrollado con **flyway** para la creacion de tablas y carga de datos iniciales, dentro del archivo: **/resources/db/migration/V1.0__create_tables.sql** se encuentran el script para creacion de tablas y carga de datos iniciales.

4. El proyecto se despliega en el puerto 8080 deacuerdo a la configuración en el archivo application.yml.

5. El contextPath de la app esta configurado con el valor /api.

6. Las configuraciones de las expresiones regulares en la base de datos se cargan en el arranque inicial las mismas que pueden ser modificadas / creadas / listadas, con los siguientes endpoints: 

**Petición GET:  Obtiene las configuraciones existentes
http://localhost:8080/api/global/configuration**
  
#### Respuesta:
![GetConfigAll](https://user-images.githubusercontent.com/62367756/218292722-f602c620-b130-449e-b4d8-4d8c5eaf219f.png)


#### 
**Petición POST:  Crea una nueva configuración siempre y cuando el campo name no exista en la Bdd
http://localhost:8080/api/global/configuration/create**

#### RequestBody:

![CreateConfig](https://user-images.githubusercontent.com/62367756/218292773-d9005ded-7182-457c-8960-89b90adb3361.png)

#### Respuesta:

![CreateConfigREs](https://user-images.githubusercontent.com/62367756/218292782-5c6aa79a-aa48-438e-876f-0b47f9ecd826.png)

#### 
**Petición PUT:  Actualiza una  configuración en base a al id de la configuración 
http://localhost:8080/api/global/configuration/9d4836f7-3918-4249-8a5c-f12cfa5aa7b5**

#### RequestBody:

![UpdateConfig](https://user-images.githubusercontent.com/62367756/218292823-0fcf6a0f-f8df-48e8-bd39-9e391cd6ccce.png)

#### Respuesta:

![UpdateConfigResp](https://user-images.githubusercontent.com/62367756/218292827-4771e9c1-9135-41dd-a743-6fc6b886bdfd.png)


Para ingresar a la consola de h2: **http://localhost:8080/api/h2-console** Datos para configurar conexión a la base de datos: Driver Class: org.h2.Driver JDBC URL: jdbc:h2:mem:nisum username: admin  /  password: system2023

El proyecto tiene la swagger para la documentacion y test de end points, para ingresar apuntar la siguiente ruta: **http://localhost:8080/api/swagger-ui/index.html#/**

El diagrama de la solución se encuentra en la ruta: src\main\resources\diagrama\diagrama.png

Proyecto desarrollado por **Diego Acosta** &copy;
