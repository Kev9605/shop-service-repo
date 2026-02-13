# PROG3360 Assignment 1 — Microservices CI/CD Pipeline

## Tech Stack
- Java 21
- Spring Boot
- H2 In-Memory DB
- Docker + Docker Compose
- GitHub Actions CI

## Services
- Product Service (port 8081)
- Order Service (port 8082) — calls Product Service to validate availability

## Run locally (without Docker)
### Product
cd product-service
mvn spring-boot:run

### Order
cd order-service
mvn spring-boot:run

## Run with Docker Compose
From repo root:
docker compose up --build

## Endpoints
### Product
GET  /api/products
GET  /api/products/{id}
POST /api/products
DELETE /api/products/{id}

### Order
GET  /api/orders
GET  /api/orders/{id}
POST /api/orders

## Demo (curl)
curl http://localhost:8081/api/products

curl -X POST http://localhost:8081/api/products \
-H "Content-Type: application/json" \
-d '{"name":"Mouse","price":25,"quantity":10}'

curl -X POST http://localhost:8082/api/orders \
-H "Content-Type: application/json" \
-d '{"productId":1,"quantity":2}'

curl http://localhost:8082/api/orders
