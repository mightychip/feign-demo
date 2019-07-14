package ca.purpleowl.examples.boot2.feign.service.omega.client.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("service.alpha.auth")
@Getter
@Setter
public class ServiceAlphaConfigProperties {
    private String password;
    private String username;
}
