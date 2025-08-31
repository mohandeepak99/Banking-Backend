package com.example.mohan.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.mohan.filter.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("auth", r -> r.path("/api/auth/**")
                .uri("lb://USER-AUTHENTICATION-SERVICE"))

            .route("customer", r -> r.path("/api/v1/customer/**")
                .filters(f -> {
                    var config = new JwtAuthenticationFilter.Config();
                    config.setRoles(Arrays.asList("CUSTOMER", "MANAGER", "ADMIN"));
                    return f.filter(jwtAuthenticationFilter.apply(config));
                })
                .uri("lb://CUSTOMER-SERVICE"))

            .route("account", r -> r.path("/api/v1/accounts/**")
                .filters(f -> {
                    var config = new JwtAuthenticationFilter.Config();
                    config.setRoles(Arrays.asList("CUSTOMER", "MANAGER", "ADMIN"));
                    return f.filter(jwtAuthenticationFilter.apply(config));
                })
                .uri("lb://ACCOUNT-SERVICE"))

            .route("payment", r -> r.path("/api/v1/payments/**")
                .filters(f -> {
                    var config = new JwtAuthenticationFilter.Config();
                    config.setRoles(Arrays.asList("CUSTOMER", "MANAGER", "ADMIN"));
                    return f.filter(jwtAuthenticationFilter.apply(config));
                })
                .uri("lb://PAYMENT-SERVICE"))

            .route("notification", r -> r.path("/api/v1/notifications/**")
                .filters(f -> {
                    var config = new JwtAuthenticationFilter.Config();
                    config.setRoles(Arrays.asList("MANAGER", "ADMIN"));
                    return f.filter(jwtAuthenticationFilter.apply(config));
                })
                .uri("lb://NOTIFICATION-SERVICE"))

            .route("audit", r -> r.path("/api/v1/audit/**")
                .filters(f -> {
                    var config = new JwtAuthenticationFilter.Config();
                    config.setRoles(Arrays.asList("ADMIN", "MANAGER"));
                    return f.filter(jwtAuthenticationFilter.apply(config));
                })
                .uri("lb://AUDIT-SERVICE"))

            .build();
    }
}