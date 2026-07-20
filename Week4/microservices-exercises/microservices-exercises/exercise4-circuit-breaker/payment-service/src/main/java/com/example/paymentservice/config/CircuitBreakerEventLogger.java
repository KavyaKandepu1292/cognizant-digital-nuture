package com.example.paymentservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Subscribes to Resilience4j's event publisher and logs every state transition,
 * error, and "call not permitted" event for the paymentService circuit breaker.
 * Live state is also inspectable via /actuator/circuitbreakers and
 * /actuator/circuitbreakerevents.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CircuitBreakerEventLogger {

    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @PostConstruct
    public void registerListeners() {
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("paymentService");

        circuitBreaker.getEventPublisher()
                .onStateTransition(event ->
                        log.warn("Circuit breaker '{}' state transition: {} -> {}",
                                event.getCircuitBreakerName(),
                                event.getStateTransition().getFromState(),
                                event.getStateTransition().getToState()))
                .onError(event ->
                        log.error("Circuit breaker '{}' recorded error: {}",
                                event.getCircuitBreakerName(), event.getThrowable().toString()))
                .onCallNotPermitted(event ->
                        log.warn("Circuit breaker '{}' rejected a call — circuit is OPEN",
                                event.getCircuitBreakerName()))
                .onSlowCallRateExceeded(event ->
                        log.warn("Circuit breaker '{}' slow-call rate exceeded: {}%",
                                event.getCircuitBreakerName(), event.getSlowCallRate()))
                .onFailureRateExceeded(event ->
                        log.warn("Circuit breaker '{}' failure rate exceeded: {}%",
                                event.getCircuitBreakerName(), event.getFailureRate()));
    }
}
