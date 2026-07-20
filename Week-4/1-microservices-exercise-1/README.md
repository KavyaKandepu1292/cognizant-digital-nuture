# Microservices with Spring Boot 3.0 — Exercises

Four independent Spring Boot 3.2.5 / Java 17 microservices exercises, each
project built with its own `pom.xml` (Maven).

## Exercise 1 — User & Order Management (`exercise1-user-order/`)
- **user-service** (8081): CRUD REST API for users, H2 in-memory DB.
- **order-service** (8082): CRUD REST API for orders. Calls user-service via
  either OpenFeign (`UserClient`, default) or WebClient (`UserWebClientService`).

```bash
cd user-service  && mvn spring-boot:run   # 8081
cd order-service && mvn spring-boot:run   # 8082
```

## Exercise 2 — Inventory + Service Discovery (`exercise2-inventory-eureka-config/`)
- **eureka-server** (8761): Netflix Eureka discovery server.
- **config-server** (8888): Spring Cloud Config Server (native profile, serves `config-repo/`).
- **product-service** (8083): registers with Eureka, pulls config from config-server.
- **inventory-service** (8084): calls product-service via Feign, resolved by
  service name through Eureka — no hardcoded host/port.

Start in order:
```bash
cd eureka-server      && mvn spring-boot:run   # 8761
cd config-server      && mvn spring-boot:run   # 8888
cd product-service    && mvn spring-boot:run   # 8083
cd inventory-service  && mvn spring-boot:run   # 8084
```

## Exercise 3 — API Gateway (`exercise3-api-gateway/`)
- **customer-service** (8091) / **billing-service** (8092): trivial downstream services.
- **api-gateway** (8090): Spring Cloud Gateway with path rewriting, Redis-backed
  rate limiting (5 req/s, burst 10), and a Caffeine response cache (30s TTL, `X-Cache` header).

```bash
docker run -d -p 6379:6379 redis
cd customer-service && mvn spring-boot:run   # 8091
cd billing-service  && mvn spring-boot:run   # 8092
cd api-gateway       && mvn spring-boot:run  # 8090
```

## Exercise 4 — Resilient Payment Service (`exercise4-circuit-breaker/`)
- **payment-service** (8095): Resilience4j `@TimeLimiter` (3s) + `@CircuitBreaker`
  (50% failure/slow-call threshold over 10 calls), graceful fallback, and an
  event logger for state transitions. Actuator exposes `/actuator/circuitbreakers`,
  `/actuator/circuitbreakerevents`, and Prometheus metrics.

```bash
cd payment-service && mvn spring-boot:run   # 8095
```

## Stack
Java 17, Spring Boot 3.2.5, Spring Cloud 2023.0.1, H2, Lombok, Resilience4j, Redis, Caffeine.
