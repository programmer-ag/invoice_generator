# Invogen Invoice Generator

*Full Stack Development* **Invoice Generation and Management System** built with **Spring Boot** and **React**. This application allows users to generate professional invoices, set personal profile for autofill, track payment status, manage a saved client directory, and download or view generated PDFs.

The project is optimized for production as a **Single Origin Deployment**, where the built React frontend is served directly through the Spring Boot.

---

## 🚀 Core Features

* **Dynamic Invoice Generation:** Create customized invoices, assign unique invoice numbers, and generate binary PDF data on the fly.
* **One-Click Payment Status Toggle:** Integrated boolean tracking (`PAID` / `MARK AS PAID`) featuring an optimistic UI update mechanism over a `PATCH` API endpoint.
* **Persistent Client Directory:** Manage recurring client names and addresses via a dedicated database directory, which auto-populates search/filter metrics.
* **Stateless Security & Data Lifecycle:** Protected by JWT (JSON Web Tokens) with automated system-level deletion routines clearing temporary invoice data after 10 days.

---

## 🛠️ Tech Stack

### Backend
* **Framework:** Spring Boot 4.0.5 / Java 21
* **Security:** Spring Security 7.0.6 (Stateless JWT Authentication & Architecture)
* **Persistence:** Spring Data JPA / Hibernate 7.2.7
* **Database:** PostgreSQL (Configured with automated database-level cascading operations: `ON DELETE CASCADE`)

### Frontend
* **Build Tool & Framework:** React 18 / Vite
* **HTTP Client:** Axios (Configured with centralized modular interceptors for automatic `Authorization: Bearer <token>` injection)
* **Styling:** Clean, highly scannable, component-scoped inline flex matrices layout
