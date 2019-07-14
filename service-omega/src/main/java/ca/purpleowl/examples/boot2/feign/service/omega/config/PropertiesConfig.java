package ca.purpleowl.examples.boot2.feign.service.omega.config;

import ca.purpleowl.examples.boot2.feign.service.omega.client.properties.ServiceAlphaConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({ServiceAlphaConfigProperties.class})
public class PropertiesConfig {
}
