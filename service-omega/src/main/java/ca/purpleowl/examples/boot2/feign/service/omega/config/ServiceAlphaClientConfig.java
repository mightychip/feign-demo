package ca.purpleowl.examples.boot2.feign.service.omega.config;

import ca.purpleowl.examples.boot2.feign.service.omega.client.properties.ServiceAlphaConfigProperties;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceAlphaClientConfig {
    @Bean
    public RequestInterceptor serviceAlphaRequestInterceptor(ServiceAlphaConfigProperties configProperties) {
        return requestTemplate -> {
            requestTemplate.header("username", configProperties.getUsername());
            requestTemplate.header("password", configProperties.getPassword());
        };
    }
}
