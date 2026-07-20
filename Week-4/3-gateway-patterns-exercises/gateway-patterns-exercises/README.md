# Spring Cloud Gateway Patterns — Exercises

Three independent Spring Boot 3.2.5 / Java 17 gateway projects (Spring Cloud
2023.0.1), each with its own `pom.xml`. Adapted from a standard Spring Cloud
Gateway exercise set, with different naming and some genuinely different
implementation choices (not a straight copy) — see notes below.

## exercise1-gateway-routing/ (port 9080)
Basic route configuration (`/proxy/**` -> httpbin.org via RewritePath) plus a
`RequestTimingFilter` — a GlobalFilter that logs method, path, response
status, **and elapsed time** for every request via SLF4J (the original
reference only logged the request URI with `System.out.println`).

```bash
cd exercise1-gateway-routing && mvn spring-boot:run
curl localhost:9080/proxy/get
```

## exercise2-gateway-loadbalancing/ (port 9081)
Route `lb://catalog-service` load-balanced with a **custom weighted-random**
strategy (`WeightedRandomLoadBalancer`) instead of plain random: instances can
advertise a `weight` in their own metadata to receive proportionally more
traffic, and every pick is logged. See the exercise's own README for how to
set instance weights.

```bash
cd exercise2-gateway-loadbalancing && mvn spring-boot:run
```

## exercise3-gateway-circuitbreaker/ (port 9082)
Resilience4j circuit breaker (`backendServiceBreaker`) protecting
`/protected/**`, tuned with stricter thresholds than the framework defaults
(60% failure rate over a 20-call window, 15s open-state wait, 2s timeout —
vs. the defaults of 50%/100 calls/60s/1s), **plus a real fallback controller**
that returns structured JSON instead of a raw connection error once the
breaker trips.

```bash
cd exercise3-gateway-circuitbreaker && mvn spring-boot:run
curl -i localhost:9082/protected/anything   # times out -> falls back to /fallback/protected
curl localhost:9082/actuator/circuitbreakers
```

## Stack
Java 17, Spring Boot 3.2.5, Spring Cloud Gateway, Spring Cloud LoadBalancer, Resilience4j, Actuator.
