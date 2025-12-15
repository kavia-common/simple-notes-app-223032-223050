# Notes Backend (Spring Boot)

Exposes CRUD REST API for notes.

- Base URL: /api/notes
- Endpoints:
  - GET /api/notes
  - GET /api/notes/{id}
  - POST /api/notes
  - PUT /api/notes/{id}
  - DELETE /api/notes/{id}

Payloads:
- POST/PUT request body:
```json
{ "title": "My Title", "content": "My Content" }
```

Responses are in JSON with fields: id, title, content, createdAt, updatedAt.

Dev Info:
- Port: 3001
- Swagger UI: /docs (redirects to /swagger-ui.html)
- OpenAPI JSON: /api-docs
- H2 Console: /h2-console
- CORS: permissive for local development.
