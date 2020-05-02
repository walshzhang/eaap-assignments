package iteach.eaap.assignments.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class AssignmentGatewayConfiguration {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("assignment management service", predicate -> predicate
                        .path("/api/assignments", "/api/assignments/**")
                        .filters(filter -> filter.stripPrefix(1))
                        .uri("http://management:39999"))
                .route("assignment submission service", predicate -> predicate
                        .path("/api/submissions").and()
                        .method(HttpMethod.POST)
                        .filters(filter -> filter.stripPrefix(1))
                        .uri("http://submission:29999"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderHandlerRouting(SubmissionHandler handler) {
        return RouterFunctions.route(GET("/api/submissions"), handler::getSubmissions);
    }
}
