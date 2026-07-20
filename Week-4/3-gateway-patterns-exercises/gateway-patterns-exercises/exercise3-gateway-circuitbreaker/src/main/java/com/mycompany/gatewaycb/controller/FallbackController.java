package com.mycompany.gatewaycb.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

/**
 * Local fallback endpoint the gateway forwards to (via fallbackUri in
 * application.yml) once backendServiceBreaker trips OPEN — returns a
 * structured JSON response instead of letting the caller see a raw
 * connection failure.
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback/protected")
    public ResponseEntity<Map<String, Object>> protectedFallback() {
        Map<String, Object> body = Map.of(
                "status", "DEGRADED",
                "message", "backend-service is currently unavailable; please retry shortly",
                "timestamp", Instant.now().toString()
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(body);
    }
}
