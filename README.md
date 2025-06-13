# Pokemon API - Backend Java Jr Challenge - Jemer

Este proyecto fue desarrollado como parte de una prueba técnica para un puesto de Java Backend Jr. El objetivo es consumir datos desde una API externa (PokeAPI), exponer un endpoint REST con Spring Boot y almacenar un historial de búsquedas.

## Descripción

La aplicación expone un único endpoint:

```
GET /pokemon/{name}
```

Este endpoint consume la API pública [PokeAPI](https://pokeapi.co/api/v2/pokemon/{name}) y devuelve:

- Nombre del Pokémon
- Imagen (sprite frontal)
- Tipos (ej. electric, fire, etc.)

Además, cada búsqueda se almacena automáticamente en una base de datos H2 en memoria, registrando el nombre, imagen, tipos y la fecha de la consulta.

## Tecnologías y dependencias principales

- Java 17
- Spring Boot 3.5
- Spring WebFlux (WebClient)
- Spring Data JPA
- H2 Database (en memoria)
- Lombok
- Swagger (OpenAPI) para documentación
## Cómo probar

**1. Ejecutar el comando en GitBash:**
```
mvn clean spring-boot:run
```

**2. Consumir el endpoint**
```
GET http://localhost:8080/pokemon/charizard
```

O ingresar a [Swagger UI](http://localhost:8080/swagger-ui) y probar directamente desde ahí.


##  Documentación Swagger

La documentación del endpoint está disponible automáticamente en:

**Swagger UI:**  
http://localhost:8080/swagger-ui


## Base de datos en memoria (H2)

La aplicación usa una base de datos H2 en memoria, donde se guardan las búsquedas realizadas.

**Consola web de H2:**  
http://localhost:8080/h2-console

**Credenciales:**

- **JDBC URL:** `jdbc:h2:mem:pokemon_db`
- **User:** `sa`
- **Password:** _(vacío)_

**Consulta con datos completos y orden de fecha descendiente:**
```
SELECT 
  ph.ID,
  ph.NAME,
  ph.IMAGE_URL,
  GROUP_CONCAT(pt.NAME) AS types,
  ph.SEARCH_DATE
FROM POKEMON_HISTORY ph
LEFT JOIN POKEMON_HISTORY_TYPES pht ON ph.ID = pht.POKEMON_HISTORY_ENTITY_ID
LEFT JOIN POKEMON_TYPE pt ON pht.TYPES_ID = pt.ID
GROUP BY ph.ID, ph.NAME, ph.IMAGE_URL, ph.SEARCH_DATE
ORDER BY ph.SEARCH_DATE DESC;
```
## Estructura del proyecto

```
├── config/                # Configuración de WebClient
├── controllers/           # Controladores REST
├── dtos/                  # Objetos de transferencia de datos
├── entities/              # Entidades JPA
├── repositories/          # Interfaces de persistencia
└── services/              # Lógica de negocio y consumo de API externa
```
