package iteach.eaap.assignments.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AssignmentGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentGatewayApplication.class, args);
    }

}
