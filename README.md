# Postres 🍰

Aplicación web desarrollada en Spring Boot para gestionar recetas de postres. Los usuarios pueden registrarse, iniciar sesión, y crear, ver, editar y eliminar postres. Incluye sistema de "Me gusta" y búsqueda.

## Funcionalidades

- Registro e inicio de sesión con validaciones
- Contraseñas encriptadas con BCrypt
- Sesión de usuario en todas las vistas protegidas
- CRUD completo de postres
- Cada postre está asociado a su autor
- Sistema de "Me gusta" (cada usuario una vez por postre)
- Búsqueda de postres por nombre (ignora mayúsculas)
- Postres ordenados alfabéticamente
- Editar y eliminar disponibles solo para el autor del postre
- Vista "Mis postres" con los postres del usuario en sesión

## Rutas principales

| Ruta | Método | Descripción |
|---|---|---|
| `/` | GET | Login y registro |
| `/registro` | POST | Procesa el registro |
| `/login` | POST | Procesa el inicio de sesión |
| `/logout` | GET | Cierra la sesión |
| `/postres` | GET | Lista todos los postres |
| `/postres/buscar` | GET | Búsqueda por nombre |
| `/postres/nuevo` | GET | Formulario para crear |
| `/postres/crear` | POST | Guarda el postre |
| `/postres/{id}` | GET | Detalle del postre |
| `/postres/editar/{id}` | GET | Formulario para editar |
| `/postres/actualizar/{id}` | PUT | Guarda los cambios |
| `/postres/borrar/{id}` | DELETE | Elimina el postre |
| `/postres/mis-postres` | GET | Postres del usuario en sesión |
| `/postres/megusta/{usuarioId}/{postreId}` | GET | Da "Me gusta" a un postre |

## Requisitos previos

- MySQL corriendo en localhost:3306
- Base de datos `postres_db` creada

## Cómo ejecutar

1. Abrir el proyecto en STS
2. Configurar credenciales de MySQL en `application.properties`
3. Clic derecho en el proyecto → Run As → Spring Boot App
4. Abrir el navegador en `localhost:8080/`

## Tecnologías

- Java 21
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- MySQL
- BCrypt
- JSP + JSTL
- Bootstrap 5