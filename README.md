# Hospital API - Microservices Architecture

Welcome to the Hospital API project! This Spring Boot application serves as the backend for managing hospital information using a microservices architecture.

## Overview

The Hospital API is designed to provide functionalities for managing hospitals, doctors, patients, appointments, and other related information. It follows a microservices architecture to ensure scalability, flexibility, and maintainability.

## Features

- **Hospital Management**: CRUD operations for hospitals including adding, updating, and deleting hospital information.
- **Doctor Management**: Manage doctors associated with hospitals, including their specialties and contact information.
- **Patient Management**: Track patient information and appointments.
- **Appointment Scheduling**: Schedule appointments between patients and doctors.
- **Microservices Architecture**: Modular design with individual services for scalability and flexibility.

## Technologies Used

- **Spring Boot**: Framework for building Java-based applications.
- **Spring Data JPA**: Simplifies data access and persistence.
- **Spring Web**: For building RESTful web services.
- **Spring Cloud Netflix Eureka**: Service discovery and registration.
- **Spring Cloud Gateway**: API gateway for routing requests to appropriate services.
- **Docker**: Containerization for easy deployment.
- **MySQL**: Database for storing hospital and related information.

## Getting Started

To get started with the Hospital API, follow these steps:

1. **Clone the Repository**: `git clone https://github.com/your-username/hospital-api.git`
2. **Navigate to Project Directory**: `cd hospital-api`
3. **Build the Application**: `./mvnw clean package`
4. **Run Docker Compose**: `docker-compose up`
5. **Access the API**: The API will be available at `http://localhost:8080`

## API Documentation

Detailed API documentation is available at `http://localhost:8080/swagger-ui.html`. Use this documentation to explore available endpoints and their functionalities.

## Contributing

Contributions are welcome! If you'd like to contribute to the project, feel free to fork the repository, make your changes, and submit a pull request. Make sure to follow the contribution guidelines.
