# ChâTop - Location App (Backend)

Backend API for ChâTop, a real estate rental platform that connects tenants with property owners.

## Overview

This Spring Boot application provides the following features:
- User authentication (register/login) with JWT tokens
- CRUD operations for rental listings
- Messaging system between users
- Image upload and storage for rental listings
- Swagger API documentation

## Frontend Repository

The frontend Angular application can be found at:
[ChâTop Frontend](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring)

## Prerequisites

- Java 17
- Maven
- MySQL 8.0+
- IDE (IntelliJ IDEA recommended)

## Configuration

1. Clone the repository:
```bash
git clone https://github.com/NairodP/P3-Back.git
```

2. Create a .env file in the root directory using .env.example as template:

```bash
DB_URL=jdbc:mysql://localhost:3306/your_database?useSSL=false&serverTimezone=UTC
DB_USERNAME=your_username
DB_PASSWORD=your_password
JWT_SECRET_KEY=your_secret_key
```

3. Create MySQL database:

```sql
CREATE DATABASE rental_portal;
```

4. Run the initialization script (script.sql) located in the frontend repository's resources/sql/ folder

5. Update application.properties if needed (default port is 3001)

## Running the Application

1. Install dependencies:

```bash
mvn install
```

2. Run the application:

```bash
mvn spring-boot:run
```

The API will be available at: http://localhost:3001/api

## API Documentation

Swagger UI documentation available at: http://localhost:3001/api/swagger-ui.html

## Project Structure

- config/ - Security and configuration classes
- controller/ - REST API endpoints
- dto/ - Data Transfer Objects
- mapper/ - Object mappers
- model/ - Entity classes
- repository/ - Data access layer
- service/ - Business logic



