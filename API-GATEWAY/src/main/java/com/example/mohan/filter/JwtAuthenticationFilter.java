package com.example.mohan.filter;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.example.mohan.utils.JwtUtil;

import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (request.getURI().getPath().startsWith("/api/auth")) {
                return chain.filter(exchange);
            }

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return deny(exchange, "Missing Authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return deny(exchange, "Invalid Authorization header", HttpStatus.UNAUTHORIZED);
            }

            String token = authHeader.substring(7);
            if (!jwtUtil.isTokenValid(token)) {
                return deny(exchange, "Invalid or expired token", HttpStatus.UNAUTHORIZED);
            }

            String userRole = jwtUtil.extractRole(token);
            if (config.getRoles() != null && !config.getRoles().isEmpty()) {
                boolean authorized = config.getRoles().stream()
                    .anyMatch(r -> r.equalsIgnoreCase(userRole));
                if (!authorized) {
                    return deny(exchange, "Forbidden", HttpStatus.FORBIDDEN);
                }
            }

            ServerHttpRequest mutatedRequest = request.mutate()
                    .header("X-User-Role", userRole)
                    .header(HttpHeaders.AUTHORIZATION, authHeader)
                    .build();

            return chain.filter(exchange.mutate().request(mutatedRequest).build());
        };
    }

    private Mono<Void> deny(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();
    }

    public static class Config {
        private List<String> roles;

        public void setRoles(List<String> roles) {
            this.roles = roles;
        }

        public List<String> getRoles() {
            return roles;
        }
    }
}