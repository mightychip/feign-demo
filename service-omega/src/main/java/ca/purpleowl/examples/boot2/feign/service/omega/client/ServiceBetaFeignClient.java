package ca.purpleowl.examples.boot2.feign.service.omega.client;

import ca.purpleowl.examples.boot2.feign.service.beta.rest.client.ServiceBeta;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "serviceBeta",
             url = "${service.beta.url}")
public interface ServiceBetaFeignClient extends ServiceBeta {
}
