# StatusWatchr — Backend

Spring Boot REST API for the StatusWatchr service health monitoring system.

## What it does
- Registers services and polls their health every 5 seconds (configurable per monitor, minimum 5s)
- Automatically opens incident records on DOWN detection and resolves them on recovery
- Exposes 6 REST API endpoints with paginated responses, bean validation, and structured error handling

## Tech Stack
- Java 21, Spring Boot, Spring Data JPA (Hibernate)
- PostgreSQL, Maven, Lombok

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/watchrs` | Register a service |
| GET | `/api/watchrs` | List all services |
| GET | `/api/watchrs/{id}` | Get service by ID |
| GET | `/api/incidents` | All incidents (paginated) |
| GET | `/api/incidents?open=true` | Open incidents only |
| GET | `/api/incidents/watchr/{id}` | Incidents for a service |

## Running Locally
```bash
# Add PostgreSQL credentials to application-local.properties
./mvnw spring-boot:run
```

## Part of
[StatusWatchr](https://github.com/Prajwaliscoding/statuswatchr) — full-stack service health monitoring system.