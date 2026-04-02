# StatusWatchr — Frontend

React + TypeScript + Vite frontend for the StatusWatchr service health monitoring system.

Fetches and displays monitored services from the Spring Boot backend API.

## Tech Stack
- React, TypeScript, Vite
- Axios (HTTP client)

## Structure
```
src/
├── api/
│   └── watchrApi.ts   # Axios API client for backend
├── types/
│   └── watchr.ts      # TypeScript types matching backend response
└── App.tsx            # Main component
```

## Running Locally
```bash
npm install
npm run dev
```

Runs at `http://localhost:5173` — requires the backend running at `http://localhost:8080`.

## Part of

[StatusWatchr](https://github.com/Prajwaliscoding/statuswatchr) — full-stack service health monitoring system.