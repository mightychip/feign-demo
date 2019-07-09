package ca.purpleowl.examples.boot2.feign.service.omega.config;

import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Configuration
public class Resilience4JConfig {
    @Bean
    public BulkheadRegistry bulkheadRegistry() {
        return BulkheadRegistry.ofDefaults();
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerRegistry registry = CircuitBreakerRegistry.ofDefaults();

        CircuitBreakerConfig defaultConfig = CircuitBreakerConfig
                        .from(CircuitBreakerConfig
                        .ofDefaults())
                        .ringBufferSizeInClosedState(4)
                        .ringBufferSizeInHalfOpenState(2)
                        .failureRateThreshold(25)
                        .waitDurationInOpenState(Duration.of(60, ChronoUnit.SECONDS))
                        .build();

        registry.addConfiguration("defaultConfig", defaultConfig);

        return registry;
    }

    @Bean
    public RetryRegistry retryRegistry() {
        return RetryRegistry.ofDefaults();
    }
}
