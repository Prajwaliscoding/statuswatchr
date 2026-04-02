# StatusWatchr

A full-stack service health monitoring system — register any URL, poll it automatically, and get real-time UP/DOWN status with automatic incident tracking.

> Conceptually similar to tools like UptimeRobot, Pingdom, or Datadog — built from scratch for learning.

## What it does

- Register services (APIs, websites, or any HTTP endpoint) with a custom check interval
- Background scheduler polls every service automatically (every 5 seconds, configurable per monitor, minimum 5s)
- Detects failures and automatically opens an incident record on first DOWN detection
- Auto-resolves incidents when the service recovers
- Paginated REST API endpoints to query open incidents, historical checks, and per-service status
- React + TypeScript frontend fetches and displays monitored services via Axios

## Tech Stack

**Backend**
- Java 21, Spring Boot, Spring Data JPA (Hibernate)
- PostgreSQL, Maven, Lombok

**Frontend**
- React, TypeScript, Vite, Axios

## Project Structure

```
statuswatchr/
├── statuswatchr-backend/
│   └── src/main/java/com/statuswatchr/statuswatchr/
│       ├── config/           # CORS configuration
│       ├── controller/       # WatchrController, IncidentController
│       ├── dto/              # WatchrCreateRequest, WatchrResponse, IncidentResponse
│       ├── exception/        # GlobalExceptionHandler, ApiError, NotFoundException
│       ├── model/            # Watchr, Incident, Status (enum)
│       ├── repository/       # WatchrRepository, IncidentRepository
│       └── service/          # WatchrService, IncidentService, HealthCheckService, WatchrScheduler
└── statuswatchr-frontend/
    └── src/
        ├── api/watchrApi.ts  # Axios API client
        ├── types/watchr.ts   # TypeScript types matching backend response
        └── App.tsx           # Main React component
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/watchrs` | Register a new service to monitor |
| GET | `/api/watchrs` | List all monitored services |
| GET | `/api/watchrs/{id}` | Get a specific service by ID |
| GET | `/api/incidents` | List all incidents (paginated) |
| GET | `/api/incidents?open=true` | List only open (unresolved) incidents |
| GET | `/api/incidents/watchr/{id}` | Get incidents for a specific service |

## Running Locally

### Backend
```bash
cd statuswatchr-backend
# Add your PostgreSQL credentials to application-local.properties
./mvnw spring-boot:run
```

### Frontend
```bash
cd statuswatchr-frontend
npm install
npm run dev
```

Frontend runs at `http://localhost:5173`, backend at `http://localhost:8080`.

## Author

Prajwal Khatiwada — CS Undergraduate, The University of Texas at Arlington