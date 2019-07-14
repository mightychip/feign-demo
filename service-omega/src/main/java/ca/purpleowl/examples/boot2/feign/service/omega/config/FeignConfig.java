package ca.purpleowl.examples.boot2.feign.service.omega.config;

import ca.purpleowl.examples.boot2.feign.service.omega.client.decoder.OmegaErrorDecoder;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public OmegaErrorDecoder errorDecoder() {
        return new OmegaErrorDecoder();
    }
}
