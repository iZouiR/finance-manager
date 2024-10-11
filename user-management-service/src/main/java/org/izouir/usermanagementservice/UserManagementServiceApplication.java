package org.izouir.usermanagementservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Management Service API", version = "1.0"))
public class UserManagementServiceApplication {
    public static void main(final String[] args) {
        SpringApplication.run(UserManagementServiceApplication.class, args);
    }
}
