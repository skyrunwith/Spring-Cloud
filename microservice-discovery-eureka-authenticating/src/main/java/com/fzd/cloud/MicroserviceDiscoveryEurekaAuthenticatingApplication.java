package com.fzd.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicroserviceDiscoveryEurekaAuthenticatingApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceDiscoveryEurekaAuthenticatingApplication.class, args);
    }

}
