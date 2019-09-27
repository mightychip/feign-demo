package ca.purpleowl.examples.boot2.feign.service.omega.config;

import ca.purpleowl.examples.boot2.feign.service.omega.client.properties.ServiceAlphaConfigProperties;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This {@link Configuration} annotated class provides a {@link RequestInterceptor} for use on all requests against the
 * {@link ca.purpleowl.examples.boot2.feign.service.omega.client.ServiceAlphaFeignClient}.  Such a configuration class can also be used to add other configurations
 * to a particular client.  All you need to do is ensure that this class is referenced by the appropriate {@link org.springframework.cloud.openfeign.FeignClient}
 * annotated class.
 */
@Configuration
public class ServiceAlphaClientConfig {
    /**
     * Returns a {@link RequestInterceptor} which adds a "username" and "password" header to each request against Service
     * Alpha.
     *
     * @param configProperties - An autowired {@link ServiceAlphaConfigProperties} object containing the necessary username/password properties.
     * @return A properly configured {@link RequestInterceptor} implementation which adds our desired headers to each request.
     */
    @Bean
    public RequestInterceptor serviceAlphaRequestInterceptor(ServiceAlphaConfigProperties configProperties) {
        return requestTemplate -> {
            requestTemplate.header("username", configProperties.getUsername());
            requestTemplate.header("password", configProperties.getPassword());
        };
    }
}
