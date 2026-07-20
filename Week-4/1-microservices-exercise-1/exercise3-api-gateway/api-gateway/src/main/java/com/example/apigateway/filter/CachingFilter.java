package com.example.apigateway.filter;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * Caches successful GET responses in-memory (Caffeine, 30s TTL) and adds an
 * X-Cache: HIT|MISS header so cache behaviour is visible to the caller.
 */
@Component
public class CachingFilter implements GlobalFilter, Ordered {

    private final Cache<String, String> responseCache;

    public CachingFilter(Cache<String, String> responseCache) {
        this.responseCache = responseCache;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (exchange.getRequest().getMethod() != HttpMethod.GET) {
            return chain.filter(exchange);
        }

        String cacheKey = exchange.getRequest().getURI().toString();
        String cached = responseCache.getIfPresent(cacheKey);

        if (cached != null) {
            exchange.getResponse().getHeaders().add("X-Cache", "HIT");
            DataBuffer buffer = exchange.getResponse().bufferFactory()
                    .wrap(cached.getBytes(StandardCharsets.UTF_8));
            return exchange.getResponse().writeWith(Mono.just(buffer));
        }

        exchange.getResponse().getHeaders().add("X-Cache", "MISS");
        ServerHttpResponse originalResponse = exchange.getResponse();
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(originalResponse) {
            @Override
            public Mono<Void> writeWith(org.reactivestreams.Publisher<? extends DataBuffer> body) {
                if (getStatusCode() != null && getStatusCode().is2xxSuccessful() && body instanceof Flux) {
                    Flux<? extends DataBuffer> flux = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(flux.map(dataBuffer -> {
                        byte[] content = new byte[dataBuffer.readableByteCount()];
                        dataBuffer.read(content);
                        DataBufferUtils.release(dataBuffer);
                        String responseBody = new String(content, StandardCharsets.UTF_8);
                        responseCache.put(cacheKey, responseBody);
                        return bufferFactory().wrap(content);
                    }));
                }
                return super.writeWith(body);
            }
        };

        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
