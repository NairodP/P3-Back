# ChaTop Rental Application

## Overview

ChaTop is a rental management application built with Spring Boot that allows users to manage property listings. It features user authentication, property listing management, and secure API endpoints for interacting with the system.
You can see the frontend part of this project [here](https://github.com/OpenClassrooms-Student-Center/Developpez-le-back-end-en-utilisant-Java-et-Spring)

## Features

- User registration and authentication
- JWT-based security
- Property rental listing management
- API for user and rental operations

## Technologies

- Java 17
- Spring Boot
- Spring Security
- Maven
- JWT Authentication
- dotenv for environment variable management

## Prerequisites

- Java JDK 17+
- Maven
- MySQL database

## Setup and Installation

### 1. Fork / Clone the repository

Click on the fork button to create your own copy of this repository or clone it to your local machine using:

```bash
git clone https://github.com/NairodP/P3-Back.git
cd location_app
```

### 2. Configure environment variables

Create a `.env` file in the root directory following the .env.example file. Update the values with your database credentials.

### 3. Build the application

```bash
mvn clean install
```

### 4. Run the application

```bash
mvn spring-boot:run
```

## Install the database

The application uses a MySQL database. You can find the database schema in the `database` directory. (the file is in the frontend repository)
You can create your database with the following command:

```bash
CREATE DATABASE chatop_db;
```

Here "chatop_db" is the name of the database. You can use any name you want.

After creating the database, you have to USE it:

```bash
USE chatop_db;
```

Then you can import the schema:

copy / past the content of the file `ressources/sql/script.sql` in the frontend repo.

Tips: If you want, you can use MySql Workbench to simplify the management of your database.

## API Documentation

### SWAGGER

You can access the API documentation by visiting the following URL after running the application:

http://localhost:3001/api/swagger-ui/index.html#/

### POSTMAN

You can also import the Postman collection from the `postman` directory (the file is in the frontend repository) to test the API endpoints.

