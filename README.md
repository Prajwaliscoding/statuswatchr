# StatusWatchr

StatusWatchr is a backend service monitoring system built using Spring Boot.  
It tracks registered services, monitors their health, detects failures, records incidents, and exposes monitoring data via REST APIs.

This project is designed as a real-world backend system, focusing on clean architecture, scalability, and production-ready practices.


## 🚀 Project Goals

- Apply Spring Boot, JPA, and PostgreSQL in a real backend system
- Design REST APIs used by monitoring platforms
- Practice clean backend architecture (Controller → Service → Repository)
- Build a backend project with real-world relevance


## 🧠 What StatusWatchr Does (High Level)

- Register services (APIs, applications, or microservices)
- Periodically check service health (UP / DOWN)
- Detect failures and outages
- Store monitoring history and incidents
- Expose monitoring data via REST APIs

> Conceptually similar to tools like UptimeRobot, Pingdom, or Datadog — but built from scratch for learning.


## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot**
- **Spring Web (REST APIs)**
- **Spring Data JPA (Hibernate)**
- **PostgreSQL**
- **Lombok**
- **Maven**


## 📁 Project Structure

```text
statuswatchr
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.statuswatchr.statuswatchr
│   │   │       ├── controller     # REST controllers
│   │   │       ├── service        # Business logic
│   │   │       ├── repository     # Database access
│   │   │       ├── model          # JPA entities
│   │   │       └── ServiceMonitorApplication.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── application-local.properties  # ignored by git
├── pom.xml
└── README.md
```


## ⚙️ Configuration

The project uses Spring Profiles for environment-based configuration.

- `application.properties` → common configuration
- `application-local.properties` → local database credentials (git ignored)

```properties
spring.profiles.active=local
```


## 🗄️ Database

- PostgreSQL

- ORM handled by Hibernate via Spring Data JPA
  Schema is managed automatically using:

```properties
spring.jpa.hibernate.ddl-auto=update
```


## 📌 Planned Features

- Service registration

- Health check tracking

- Failure and incident logging

- Monitoring history APIs

- Search and filtering

- Alerting logic (future)

- Authentication & authorization (future)

- Frontend dashboard (optional)


## 🎯 Why This Project Matters

This project demonstrates:

Backend API design

Database modeling

Spring Boot best practices

Real-world monitoring system logic


## 📌 Status
🚧 In active development


## 👤 Author

Prajwal Khatiwada

Computer Science Undergraduate

Backend-focused Java Developer


---


