# Test-ANCP-CCE - Prueba Técnica


Repositorio: https://github.com/sugalitte24/Test-ANCP-CCE

### Autor
>Daniel Steven Montealegre Peña.      
> Correo: daniel_steven9624@hotmail.com
> Tel. +57 3185924883
> LinkedIn: https://www.linkedin.com/in/steven-pe%C3%B1a/

### Ejecución Proyecto:
> Para ejecutar el proyecto, simplemente debemos abrirlo como
> proyecto Spring, y el IDEA se encargará de las dependencias del pom.xml.
> Luego, daremos "Run" a la aplicación:
>![img.png](img_1.png)
> 
> ###Para tener encuenta:
> Para que el proyecto funcione correctamente, se necesita la creación 
> de una base de datos local en POSTGRESQL con el nombre de:
> ### ancp-cce
> y usuario y contraseña: 
> ### User: postgres
> ### password: admin
> En tal caso de que se tenga una base de datos con otras credenciales,
> se pueden ajustar en el archivo llamado: application.properties
>![img.png](img_2.png)
> La creación de las tablas, se hará automáticamente una vez se tenga la
> base de datos y se ejecute el proyecto.

### Registro de Usuario
> Para hacer el registro de un usuario, consumimos la url:
> POST``http://localhost:8080/v1/user``
>
> y enviamos el request por body-->raw-->Json
>
 ```json
{
  "name": "Prueba",
  "lastName": "Prueba Apellido",
  "email": "daniel1@gmail.com",
  "password": "123456"
}
```

>
> Como respuesta obtendremos un String con ``User created`` y un HttpStatus Created.
> También podemos ejecutar un crud completo, con las siguientes URL:
> GET``http://localhost:8080/v1/user`` --> Listar todos los Usuarios    
> GET ``http://localhost:8080/v1/user/{userUuid}`` --> Listar usuario por uuid
> PATCH ``http://localhost:8080/v1/user/{userUuid`` --> Actualizar usuario
> DELETE ``http://localhost:8080/v1/user/{userUuid`` --> Eliminar usuario

### Inicio Sesión en la Aplicacación
> Para iniciar sesión, consumimos la url:
> POST``http://localhost:8080/v1/user/login``
>
> y enviamos el request por body-->raw-->Json
 ```json
{
  "email": "daniel@gmail.com",
  "password": "123456"
}
```
>
> Como respuesta obtendremos la siguiente respuesta:
 ```json
{
    "token": "Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJtZXJjYWRvTGlicmVKV1QiLCJzdWIiOiJwcnVlYmEiLCJhdXRob3JpdGllcyI6IlJPTEVfVVNFUiIsImlhdCI6MTYzNTM4OTMzMSwiZXhwIjoxNjM1NDc1NzMxfQ.oDmYuqf9p7jWHRPAACXIIsmY4FMjBApP5eYtyv6yJqbW4aiQcLx4g6SdovojP3zOEPtenuv1_HhcQh_DT-FxFg",
    "startDate": "2022-07-08T02:48:51.145+00:00",
    "expirationDate": "2022-07-09T02:48:51.145+00:00",
    "typeToken": "Bearer"
}
```
>
> Donde veremos el token de autorización para hacer uso de los demás endpoint, la fecha en la que inicia, y la fecha de expiración.

### Autenticación

> Para tener acceso a los demás Endpoint, primero debemos autenticarnos, desde postman, vamos a la pestaña de "Auth" y allí en "Type"
> elegimos la de "_Bearer Token_" y procedemos a insertar el token que se nos dió cuando nos logueamos.
>
>![img.png](img.png)

### Creación, Listar, Actualizar, Eliminar un Producto(Usuario Autenticado)
> POST``http://localhost:8080/v1/products`` --> Crear
>
> y enviamos el request que sería el Uuid del usuario a la que pertenece la lista, y el nombre de la lista.
>
```json
{
  "name": "Teclado",
  "amount": "10",
  "price": 10000
}
```
>  Como respuesta obtendremos un String con ``Created`` y un HttpStatus Created.
> 
> GET``http://localhost:8080/v1/products`` --> Listar
>
> Y Obtendremos un listado de los productos en la base de datos:
```json
[
  {
    "uuid": "5d56f6af-d126-4ce8-a83f-cb3a07fbd109",
    "createdAt": "2022-08-07T17:51:53.377",
    "lastModifiedAt": "2022-08-07T17:51:53.377",
    "name": "Mouse",
    "amount": 0,
    "price": 30000.0
  },
  {
    "uuid": "704cbf49-29b6-4c04-80cb-f66cf5720776",
    "createdAt": "2022-08-07T17:49:50.247",
    "lastModifiedAt": "2022-08-07T17:49:50.247",
    "name": "Teclado",
    "amount": 6,
    "price": 10000.0
  }
]
```
> También lo podremos buscar por uuid con:
> GET``http://localhost:8080/v1/products/{producUuid}`` --> Listar por Uuid
```json
{
    "uuid": "5d56f6af-d126-4ce8-a83f-cb3a07fbd109",
    "createdAt": "2022-08-07T17:51:53.377",
    "lastModifiedAt": "2022-08-07T17:51:53.377",
    "name": "Mouse",
    "amount": 0,
    "price": 30000.0
}
```
>Para actualizar enviamos el UUID del producto, consumimos:
> PATCH``http://localhost:8080/v1/products/{producUuid}`` --> Actualizar
>
> Con request:
```json
{
  "name": "Teclado",
  "amount": "10",
  "price": 10000
}
```
>Y para Eliminar, enviamos el UUID del producto:
> DELETE``http://localhost:8080/v1/products/{producUuid}`` --> Eliminar    
> Dónde recibiremos un Status Http 200 si todo fue correcto.

### Crear, Listar, Actualizar, Eliminar un Carrito de compras con productos (Usuario Autenticado)
> Para crear un carrito de compras, consumiremos la sigueinte url, y le pasaremos un
> listo de los productos a agregar en él.
>
> POST ``http://localhost:8080/v1/shopping-car``
>
 ```json
{
  "products": [
    {
      "productUuid": "5d56f6af-d126-4ce8-a83f-cb3a07fbd109",
      "quantity": 2
    },
    {
      "productUuid": "704cbf49-29b6-4c04-80cb-f66cf5720776",
      "quantity": 1
    }
  ]
}
```
>
>   Como respuesta obtendremos un String con ``Carro de compras creado.`` y un HttpStatus Created.
> 
> Así mismo, poseemos de un crud, donde podemos listar
> 
> GET ``http://localhost:8080/v1/shopping-car`` --> Listar todos los carros de compra.
>
> Y Obtenemos:
 ```json
[
  {
    "uuid": "b5ffcfbd-4a44-4ef2-9e13-87093b11e0ed",
    "createdAt": "2022-08-07T18:52:02.102",
    "lastModifiedAt": "2022-08-07T18:52:02.102",
    "dateOrder": "2022-08-07T18:52:02.102",
    "products": [
      {
        "uuid": "5d56f6af-d126-4ce8-a83f-cb3a07fbd109",
        "createdAt": "2022-08-07T17:51:53.377",
        "lastModifiedAt": "2022-08-07T17:51:53.377",
        "name": "Mouse",
        "amount": 2,
        "price": 30000.0
      }
    ],
    "total": 10000.0
  },
  {
    "uuid": "015cf0b1-ce98-457c-a51f-b463aae39ee1",
    "createdAt": "2022-08-07T18:58:10.149",
    "lastModifiedAt": "2022-08-07T18:58:10.149",
    "dateOrder": "2022-08-07T18:58:10.149",
    "products": [
      {
        "uuid": "5d56f6af-d126-4ce8-a83f-cb3a07fbd109",
        "createdAt": "2022-08-07T17:51:53.377",
        "lastModifiedAt": "2022-08-07T17:51:53.377",
        "name": "Mouse",
        "amount": 3,
        "price": 30000.0
      }
    ],
    "total": 90000.0
  }
]
```
> Listar por carrito :
> 
> GET ``http://localhost:8080/v1/shopping-car/{shoppinCarUuid}`` --> Listar carro por uuid.
>
 ```json
 {
  "uuid": "b2c13198-04ba-45c6-8f4a-b3dfa5dbf49e",
  "createdAt": "2022-08-07T19:05:17.191",
  "lastModifiedAt": "2022-08-07T19:05:17.191",
  "dateOrder": "2022-08-07T19:05:17.191",
  "products": [
    {
      "uuid": "5d56f6af-d126-4ce8-a83f-cb3a07fbd109",
      "createdAt": "2022-08-07T17:51:53.377",
      "lastModifiedAt": "2022-08-07T17:51:53.377",
      "name": "Mouse",
      "amount": 2,
      "price": 30000.0
    }
  ],
  "total": 70000.0
}
 ```
> actualizar un producto:
> 
> PATCH ``http://localhost:8080/v1/shopping-car/update-product/{shoppingCarUuid}`` --> Actualizar producto en carro por uuid.
>
> Con request:
 ```json
{
  "products": [
    {
      "productUuid": "704cbf49-29b6-4c04-80cb-f66cf5720776",
      "quantity": 1
    }
  ]
}
```

> Y eliminar un producto del carro de compras.
> 
> PATCH ``http://localhost:8080/v1/shopping-car/{shoppingUuid}/product/{productUuid}`` --> Eliminar producto en carro por uuid.
>
> Dónde recibiremos un HttpStatus 200.
> 

###Generar el .jar
> Para generar el war, debemos compilar el proyecto y ejecutar el plugin de maven:
>  
>mvn clean install -DskipTests
>
