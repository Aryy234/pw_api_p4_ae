# Normas y Estructura para el Desarrollo de APIs

## Índice
1. [¿Qué es una API?](#que-es-una-api)
2. [Tipos de APIs](#tipos-de-apis)
3. [Principios RESTful](#principios-restful)
4. [MicroProfile y Quarkus](#microprofile-y-quarkus)
5. [Microservicios y DDD](#microservicios-y-ddd)
6. [Modelo de madurez de Richardson](#modelo-de-madurez-de-richardson)
7. [Códigos de Estado HTTP](#codigos-de-estado-http)
8. [Autentificación y Autorización](#autentificacion-y-autorizacion)
9. [OAuth2.0 y JWT](#oauth20-y-jwt)
10. [Generación de llaves](#generacion-de-llaves)

---

## 1. ¿Qué es una API?
Una API es un contrato (definiciones, especificaciones, entradas, salidas, protocolos) que se define para que dos o más componentes tecnológicos interactúen o se integren entre sí. Una API encapsula toda la lógica que implementa para cumplir los contratos.

---

## 2. Tipos de APIs
- **RESTful:** Trabaja con tipos JSON.
- **SOAP:** Se basa en XML, donde sus esquemas son rígidos.
- **gRPC:** Comunicación basada en HTTP/2, para backend interno.
- **Asíncronas:** Mensajería o colas, se basa en eventos, genera desacoplamiento entre los componentes.
- **GraphQL:** El cliente define lo que necesita.

---

## 3. Principios RESTful
REST significa *Representational State Transfer*.

### Principios clave:
1. **Recursos:** Objetos con los que trabajamos.
2. **Cliente-Servidor:** Separación de responsabilidades.
3. **Sin estado:** Cada petición es independiente, no se guarda el estado entre peticiones.
4. **Uso correcto de HTTP:**
   - GET: Consultar un recurso
   - PUT: Actualizar un recurso
   - POST: Crear un recurso
   - PATCH: Actualizar parcialmente un recurso
   - DELETE: Eliminar un recurso
5. **Representaciones:** Se trabaja con representaciones de los objetos, no con los objetos reales.
6. **Códigos de estado:** Indican el resultado del consumo de la API (ejemplo: 200 OK, 404 Not Found).
7. **Cacheable:** La API puede manejar información volátil (cache) para mejorar la respuesta y evitar consultas repetidas.
8. **Por capas:**
   - Acceso a datos
   - Lógica de negocio
   - Controlador

---

## 4. MicroProfile y Quarkus
**MicroProfile** es un conjunto de especificaciones para la construcción de APIs web basadas en Java y orientadas a microservicios.

La implementación utilizada para MicroProfile es **Quarkus**.

---

## 5. Microservicios y DDD
**Domain Driven Design (DDD):** El software debe dividirse por dominios funcionales.

### Estructuración por capas:
- **Domain:** Mapeo de entidades
- **Infrastructure:** Acceso a datos
- **Application:** Lógica de negocio
- **Interfaces:** Controlador o acceso a los recursos

Cada capa debe estar representada como paquetes.

---

## 6. Modelo de madurez de Richardson
El modelo de madurez de Richardson es una guía para evaluar y mejorar la calidad de una API REST, clasificándola en niveles según el uso correcto de los recursos, verbos HTTP y otras buenas prácticas. Cada nivel representa un avance en la estructura y funcionalidad de la API.

### Niveles del modelo:
**Nivel 0:**
- La API utiliza HTTP/HTTPS solo como transporte, pero no aprovecha los recursos ni los verbos HTTP. Ejemplo: todas las operaciones se hacen con POST y una sola URL.

**Nivel 1:**
- La API define recursos claros y URLs autodescriptivas para cada entidad. Ejemplo: `/pacientes`, `/doctores`, `/citas`.

**Nivel 2:**
- Se usan correctamente los verbos HTTP (GET, POST, PUT, DELETE, PATCH), los códigos de estado y el mediaType adecuado. Ejemplo: `GET /pacientes` para consultar, `POST /pacientes` para crear, `PUT /pacientes/1` para actualizar.

**Nivel 3 (HATEOAS):**
- La API incluye hipervínculos en las respuestas para navegar entre recursos relacionados, permitiendo descubrir nuevas acciones dinámicamente. No se exponen directamente las entidades, sino objetos Representation.

#### Ejemplo práctico:
Supongamos una API de hospital:

- **Nivel 1:**
   - `GET /pacientes` retorna la lista de pacientes.
- **Nivel 2:**
   - `POST /pacientes` crea un nuevo paciente.
   - `PUT /pacientes/5` actualiza el paciente con ID 5.
   - `DELETE /pacientes/5` elimina el paciente con ID 5.
- **Nivel 3:**
   - La respuesta de `GET /pacientes/5` incluye enlaces como:
      ```json
      {
         "id": 5,
         "nombre": "Juan",
         "links": [
            { "rel": "citas", "href": "/pacientes/5/citas" },
            { "rel": "doctor", "href": "/doctores/2" }
         ]
      }
      ```
   - Así el cliente puede navegar a las citas del paciente o al doctor asignado.

Este modelo ayuda a construir APIs más robustas, intuitivas y fáciles de consumir, mejorando la experiencia de integración y el mantenimiento.

---

## 7. Códigos de Estado HTTP
### Grupos de estados:
- **G1:** Respuestas Informativas (100-1XX)
- **G2:** Respuestas Satisfactorias (200-299)
- **G3:** Mensajes de Redirección (300-399)
- **G4:** Mensajes de error en Cliente (400-4XX)
- **G5:** Errores de servidor (500-5XX)

### Códigos importantes:
#### G1:
- 100: Respuesta informativa, continúa procesando solicitud
- 102: Solicitud recibida, aún procesando

#### G2:
- 200: Request exitoso
- 201: Recurso creado exitosamente
- 204: Procesado correctamente, sin respuesta

#### G4:
- 400: Error de sintaxis en la petición
- 401: Usuario o contraseña incorrecta
- 403: No autorizado para consumir la API
- 404: Petición a recurso inexistente
- 405: Método no permitido
- 408: Tiempo excedido en la petición
- 415: MediaType no soportado

#### G5:
- 500: Error interno de la API
- 503: Servidor no disponible

---

## 8. Autentificación y Autorización
**Autentificación:** Probar quién soy quien digo ser. Mecanismos:
1. Algo que yo sé (usuario y contraseña)
2. Algo que yo tengo (TP o tokens)
3. Algo que yo soy (huella digital, biométrico)

**Autorización:** A qué recursos estoy autorizado a acceder.

---

## 9. OAuth2.0 y JWT
**OAuth2.0:** Protocolo para gestionar la autorización basado en una autenticación previa.
**Server de Autorización:** Provee la autorización para nuestra API.

Se puede tener una llave privada.

Usaremos un token JSON (JWT).

---

## 10. Generación de llaves
Para generar llaves RSA:

```bash
openssl genpkey -algorithm RSA -out privateKey.pem -pkeyopt rsa_keygen_bits:2048
openssl rsa -pubout -in privateKey.pem -out publicKey.pem
```