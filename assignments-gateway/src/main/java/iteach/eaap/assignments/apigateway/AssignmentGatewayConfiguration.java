package iteach.eaap.assignments.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssignmentGatewayConfiguration {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("assignment management service", predicate -> predicate
                        .path("/api/assignments", "/api/assignments/**")
                        .filters(filter -> filter.stripPrefix(1))
                        .uri("http://localhost:39999"))
                .route("assignment submission service", predicate -> predicate
                        .path("/api/submissions", "/api/submissions/**")
                        .filters(filter -> filter.stripPrefix(1))
                        .uri("http://localhost:29999"))
                .build();
    }
}
