# eRetail Application

Welcome to the eRetail Application! This project demonstrates a microservices-based architecture using Java Spring Boot, Spring Data JPA, MySQL, MongoDB, WebClient, a load balancer, an API Gateway, and a Discovery Server. The application is composed of four main components: `product-service`, `order-service`, `inventory-service`, and `discovery-server`.

## Microservices

### Product Service
- **Database**: MongoDB
- **Functionality**: Manages product information.
- **API**: REST API built using Spring Boot and Spring Data MongoDB.
- **Discovery**: Registers with the `discovery-server`.

### Order Service
- **Functionality**: Manages orders and communicates with the `inventory-service` to get inventory details.
- **API**: REST API built using Spring Boot and Spring Data JPA.
- **Communication**: Uses WebClient to communicate with the `inventory-service`.
- **Discovery**: Registers with the `discovery-server`.

### Inventory Service
- **Database**: MySQL
- **Functionality**: Manages inventory details.
- **API**: REST API built using Spring Boot and Spring Data JPA.
- **Discovery**: Registers with the `discovery-server`.

### API Gateway
- **Functionality**: Provides a single entry point for clients, routing requests to the appropriate services.
- **Discovery**: Registers with the `discovery-server`.

### Discovery Server
- **Functionality**: Manages service registration and discovery, allowing services to find and communicate with each other.

## Features

- **Microservice Communication**: The services communicate with each other using WebClient.
- **Circuit Breaker**: Implemented using Resilience4j to handle failures gracefully and ensure the system's resilience.
- **Load Balancer**: Ensures even distribution of incoming requests among multiple instances of services to optimize resource use and improve response times.
- **API Gateway**: Provides a single entry point for clients, routing requests to the appropriate services.
- **Service Discovery**: Services register with the `discovery-server` to enable dynamic discovery and communication.

## Technologies Used

- **Java Spring Boot**: For creating the microservices and REST APIs.
- **Spring Data JPA**: For ORM with MySQL.
- **Spring Data MongoDB**: For interacting with MongoDB.
- **MongoDB**: For storing product information.
- **MySQL**: For storing inventory data.
- **WebClient**: For inter-service communication.
- **Resilience4j**: For implementing circuit breakers.
- **Load Balancer**: To distribute incoming requests evenly across multiple instances of services.
- **API Gateway**: To provide a single entry point for all client requests.
- **Discovery Server**: For service registration and discovery.

## Getting Started

### Prerequisites

- Java 8 or higher
- Maven
- MongoDB
- MySQL

### Installation

1. **Clone the repository**:
    ```sh
    git clone https://github.com/shivdatt23/eRetailApp.git
    cd e-retail-application
    ```

2. **Set up MongoDB and MySQL**:
    - Ensure MongoDB is running on the default port.
    - Set up MySQL and create the necessary database and tables.

3. **Configure databases**:
    - Update the `application.properties` files in the respective services with your MongoDB and MySQL configurations.

4. **Build and run the services**:
    - Navigate to each service directory and run:
      ```sh
      mvn clean install
      mvn spring-boot:run
      ```

### Running the Services

1. **Discovery Server**:
    ```sh
    cd discovery-server
    mvn spring-boot:run
    ```

2. **Product Service**:
    ```sh
    cd product-service
    mvn spring-boot:run
    ```

3. **Order Service**:
    ```sh
    cd order-service
    mvn spring-boot:run
    ```

4. **Inventory Service**:
    ```sh
    cd inventory-service
    mvn spring-boot:run
    ```

5. **API Gateway**:
    ```sh
    cd api-gateway
    mvn spring-boot:run
    ```

## Usage

- **Product Service**:
    - API endpoints to manage products.
    - Accessible at `http://localhost:8080/api/products` but each instance runs a random port
  
- **Order Service**:
    - API endpoints to manage orders.
    - Accessible at `http://localhost:8080/api/order` but each instance runs a random port
    - Communicates with `inventory-service` to verify inventory.

- **Inventory Service**:
    - API endpoints to manage inventory.
    - Accessible at `http://localhost:8080/api/inventory` but each instance runs a random port

- **Discovery Server**:
    - manages service registration and discovery
    - Runs on port 8761
    - Accessible at `http://localhost:8080/eureka/web`

- **API Gateway**:
    - Routes requests to the appropriate services.
    - Provides a unified entry point for all client requests.
    - Accessible at `http://localhost:8080`

## Load Balancer

- A load balancer is used to ensure even distribution of incoming requests among multiple instances of services.
- This improves resource utilization and system resilience, providing better performance and fault tolerance.

## Circuit Breaker

- The circuit breaker pattern is implemented using Resilience4j to handle service failures gracefully.
- Configuration and annotations can be found in the respective service codebases.

## Discovery Server

- The Discovery Server manages service registration and discovery, allowing services to find and communicate with each other dynamically.
- It uses Eureka Server for service discovery.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for review.


