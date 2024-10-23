package org.izouir.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;

@EnableDiscoveryClient
@SpringBootApplication
@EnableWebFluxSecurity
public class GatewayServiceApplication {
    public static void main(final String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
