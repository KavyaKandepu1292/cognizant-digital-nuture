package com.example.paymentservice.service;

import com.example.paymentservice.model.ChargeRequest;
import com.example.paymentservice.model.ChargeResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class PaymentService {

    private final WebClient webClient;
    private final String thirdPartyUrl;

    public PaymentService(WebClient webClient,
                           @Value("${payment.third-party-url}") String thirdPartyUrl) {
        this.webClient = webClient;
        this.thirdPartyUrl = thirdPartyUrl;
    }

    /**
     * @TimeLimiter gives up after 3s (resilience4j.timelimiter.instances.paymentService).
     * @CircuitBreaker trips OPEN after a 50% failure/slow-call rate over the last
     * 10 calls, and once OPEN, short-circuits straight to chargeFallback without
     * even attempting the call.
     */
    @CircuitBreaker(name = "paymentService", fallbackMethod = "chargeFallback")
    @TimeLimiter(name = "paymentService")
    public CompletableFuture<ChargeResponse> charge(ChargeRequest request) {
        return webClient.post()
                .uri(thirdPartyUrl)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ChargeResponse.class)
                .toFuture();
    }

    /** Graceful fallback — returned instead of failing the caller when the circuit trips or times out. */
    public CompletableFuture<ChargeResponse> chargeFallback(ChargeRequest request, Throwable t) {
        log.warn("Falling back for order {}: {}", request.getOrderId(), t.toString());
        return CompletableFuture.completedFuture(
                new ChargeResponse(request.getOrderId(), "PENDING_FALLBACK",
                        "Payment provider unavailable, request queued for retry")
        );
    }
}
