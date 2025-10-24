# KudosPoints - Rewards Service

This is the `rewards-service` microservice for the KudosPoints system. It is a stateless service that acts as the "rules engine" for calculating loyalty points.

## Role in the System

This service's sole responsibility is to apply complex, company-specific business logic. It does not have its own database. It functions as a "worker" that:

1.  **Listens** for transaction events from a RabbitMQ message queue.
2.  **Calculates** the correct number of points to award based on a ruleset (e.g., "5x points for company cards").
3.  **Issues a Command** by making an API call to the `points-service` to add the calculated points to the member's ledger.

### Core Technologies
* **Java 21 & Spring Boot:** Core application framework.
* **Spring AMQP:** For connecting to and listening to RabbitMQ.
* **RestTemplate:** For making REST API calls to the `points-service`.
* **Docker:** Fully containerized for production and development.

---

### Full System Architecture

This service is a component of a larger microservices application. For a complete system overview and instructions on how to run the entire stack, please see the main [kudospoints "hub" repository](https://github.com/bhavesh-saluru/kudospoints).