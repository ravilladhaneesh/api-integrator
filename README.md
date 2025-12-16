# AI Integration Builder – Spring Boot Project

## Overview

This project is a **generic integration platform** built using **Spring Boot** that dynamically integrates with third-party SaaS applications (e.g., Calendly, Dropbox) without requiring code changes or redeployments.

The system is designed to:

* Fetch user data from multiple external systems (currently supports Calendly)
* Store API configurations and endpoints dynamically in a database
* Use a **single generic API executor** to call any external API
* Demonstrate how AI-generated integration configurations could be executed safely


---

## Key Features

* ✅ Dynamic, DB-driven API integrations
* ✅ One-to-many support for multiple endpoints per application
* ✅ Generic REST API executor
* ✅ Configurable authentication (Personal Access Token)
* ✅ Temporary storage of fetched users
* ✅ Calendly integration implemented end-to-end
* ✅ Spring Boot 3 + Java 17

---

## Tech Stack

* Java 17
* Spring Boot 3.x
* Spring Web
* Spring Data JPA
* H2 (In-Memory Database)
* Maven

---

## High-Level Architecture

```
┌───────────────┐
│ REST API      │  (/sync/{app})
└───────┬───────┘
        │
┌───────▼──────────┐
│ Integration      │
│ Orchestrator     │
└───────┬──────────┘
        │
┌───────▼──────────┐
│ Generic API      │
│ Executor         │
└───────┬──────────┘
        │
┌───────▼──────────┐
│ Third-Party APIs │ (Calendly, etc.)
└──────────────────┘
```

---

## Database Design

### api_config

Stores application-level configuration (1 row per app).

| Column    | Description                           |
| --------- | ------------------------------------- |
| app_name  | Application name (e.g., CALENDLY)     |
| base_url  | Base API URL                          |
| auth_type | Authentication type (BEARER_TOKEN)    |
| headers   | Authentication headers stored as JSON |

---

### api_request_config

Stores **multiple endpoints per application**.

| Column           | Description                      |
| ---------------- | -------------------------------- |
| app_name         | Application name                 |
| endpoint         | API endpoint path                |
| http_method      | HTTP method (GET, POST, etc.)    |
| response_mapping | JSONPath-based response mappings |

**Relationship:**

```
ApiConfig (1) → ApiRequestConfig (N)
```

---

### external_user

Temporary table storing fetched user data.

| Column      | Description                     |
| ----------- | ------------------------------- |
| external_id | User ID from third-party system |
| email       | User email address              |
| name        | User name                       |
| source_app  | Source SaaS application         |
| raw_payload | Full raw API response           |

---


## Running the Application

### Important: Authentication Token Setup

⚠️ **Before running the application**, you must replace the placeholder personal access token in `src/main/resources/data.sql`.

For security reasons, the actual token is **not committed** to the repository.

Update the following line in `data.sql`:

```sql
INSERT INTO api_config (id, app_name, base_url, auth_type, headers)
VALUES (1, 'CALENDLY', 'https://api.calendly.com', 'BEARER',
'{"Authorization":"Bearer YOUR_TOKEN"}');
```

🔁 Replace `YOUR_TOKEN` with a valid **Calendly Personal Access Token**:

```text
Bearer <YOUR_PERSONAL_ACCESS_TOKEN>
```

Without this change, API calls will fail with **401 Unauthorized** errors.

---


### Prerequisites

* Java 17
* Maven

---

### Steps

```bash
mvn spring-boot:run // or run using eclipse
```


Trigger Calendly sync:

```bash
curl -X POST http://localhost:8080/sync/CALENDLY
```

---

## H2 Console Access

URL:

```
http://localhost:8080/h2-console
```

Login details:

| Field    | Value              |
| -------- | ------------------ |
| JDBC URL | jdbc:h2:mem:testdb |
| Username | sa                 |
| Password | (empty)            |

---

## Example Use Case

Adding a new SaaS integration requires:

1. Inserting a new row into `api_config`
2. Adding one or more rows into `api_request_config`

**No code changes or redeployments required.** (except business logic, if the request/response follows a different schema).

---

## Future Enhancements

* OAuth2 token refresh support
* Pagination and rate-limit handling
* Retry and circuit breakers
* Production database (PostgreSQL)
* AI-based API doc ingestion

---

## Conclusion

This project demonstrates:

* Clean Spring Boot architecture
* Real-world integration patterns
* Strong understanding of scalability and extensibility
* Practical use of AI in backend systems

---
