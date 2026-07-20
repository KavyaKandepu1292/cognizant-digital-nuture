package com.example.orderservice.client;

import com.example.orderservice.model.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/** Reactive alternative to UserClient — same call, WebClient style instead of Feign. */
@Service
public class UserWebClientService {

    private final WebClient webClient;

    public UserWebClientService(WebClient.Builder webClientBuilder,
                                 @Value("${user-service.url}") String userServiceUrl) {
        this.webClient = webClientBuilder.baseUrl(userServiceUrl).build();
    }

    public UserDto getUserById(Long id) {
        return webClient.get()
                .uri("/api/users/{id}", id)
                .retrieve()
                .bodyToMono(UserDto.class)
                .block();
    }
}
