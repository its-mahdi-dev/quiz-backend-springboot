package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class GatewayConfig {

//     @Bean
//     public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//         return builder.routes()
//                 .route("player-service", r -> r.path("/api/player/**")
//                         .uri("lb://player-service"))
//                 .route("designer-service", r -> r.path("/api/designer/**")
//                         .uri("lb://designer-service"))
//                 .route("auth-service", r -> r.path("/api/auth/**")
//                         .uri("lb://auth-service"))
//                 .build();
//     }
}
