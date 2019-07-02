package ca.purpleowl.examples.boot2.feign.service.omega;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients //This MUST be on the root application class to work for some reason...
public class ServiceOmegaApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceOmegaApplication.class, args);
    }
}
