#  ForoHub API - Challenge Backend (Java & Spring Boot 3)

![Java 25](https://img.shields.io/badge/Java-25-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot 3](https://img.shields.io/badge/Spring_Boot-3.4-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)

Bienvenido a **ForoHub**, una API REST robusta diseñada para gestionar un foro de discusión dinámico. Este proyecto es el resultado del desafío final del programa **Oracle Next Education (ONE)** en conjunto con **Alura Latam**, donde se aplica la lógica de negocio para la creación, consulta y moderación de tópicos.

---

##  Características del Proyecto

Este backend actúa como el núcleo de un foro, permitiendo a los usuarios interactuar con la base de datos de forma segura y eficiente:

* **Gestión de Tópicos (CRUD):** Registro, listado, actualización y eliminación de dudas o sugerencias.
* **Seguridad Avanzada:** Implementación de **Spring Security** y **JSON Web Tokens (JWT)** para proteger los datos.
* **Autenticación Stateless:** Sistema de login que genera tokens Bearer para validar la identidad del usuario.
* **Validaciones Dinámicas:** Reglas de negocio que impiden tópicos duplicados y aseguran la integridad de los campos obligatorios.
* **Persistencia de Datos:** Integración con **MySQL** mediante **Spring Data JPA**.

---

##  Stack Tecnológico

* **Lenguaje:** Java 25 (Aprovechando las últimas optimizaciones del JDK).
* **Framework:** Spring Boot 3.x.
* **Seguridad:** Spring Security + Auth0 JWT.
* **Base de Datos:** MySQL (con soporte para migraciones vía Flyway/Hibernate).
* **Herramientas de Testeo:** Insomnia / Postman.
* **Gestor de Dependencias:** Maven.

---

##  Flujo de Autenticación

Para garantizar que solo usuarios registrados interactúen con el foro, la API sigue este flujo:

1.  **Login:** El usuario envía sus credenciales al endpoint `/login`.
2.  **Generación de Token:** Si los datos son correctos, la API devuelve un token JWT con una validez definida.
3.  **Autorización:** El token debe enviarse en el header de las peticiones protegidas (`POST`, `PUT`, `DELETE`) como:
    `Authorization: Bearer <tu_token_aqui>`

> **Nota:** El acceso no autorizado devolverá un error **403 Forbidden**, protegiendo la integridad del foro.

---

##  Principales Endpoints

| Método | Ruta | Descripción |
| :--- | :--- | :--- |
| `POST` | `/login` | Autenticación de usuario y obtención de Token. |
| `GET` | `/topicos` | Lista todos los tópicos del foro (Status 200). |
| `POST` | `/topicos` | Crea un nuevo tópico (Requiere Token, Status 201). |
| `DELETE` | `/topicos/{id}` | Elimina un tópico por ID (Requiere Token, Status 200). |
| `PUT` | `/topicos/{id}` | Actualiza datos de un tópico (Requiere Token, Status 200). |

---

##  Configuración del Entorno

1.  Clona el repositorio:
    ```bash
    git clone https://github.com/SAPM2003/ForoHub.git
    ```
2.  Configura tus credenciales de base de datos en `src/main/resources/application.properties`.
3.  Asegúrate de tener instalado el **JDK 25**.
4.  Ejecuta la aplicación:
    ```bash
    ./mvnw spring-boot:run
    ```

---

###  Créditos e Inspiración
Este proyecto fue desarrollado como parte del proceso de aprendizaje en **Alura Latam**. El diseño de la solución sigue el tablero de tareas de Trello proporcionado en el challenge, dividiendo grandes problemas en soluciones modulares y escalables.

---
**Desarrollado por Samir Andres Pastran Montes**
