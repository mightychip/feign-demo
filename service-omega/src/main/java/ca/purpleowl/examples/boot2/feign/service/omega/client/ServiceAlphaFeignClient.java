package ca.purpleowl.examples.boot2.feign.service.omega.client;

import ca.purpleowl.examples.boot2.feign.service.alpha.rest.client.ServiceAlpha;
import ca.purpleowl.examples.boot2.feign.service.omega.config.ServiceAlphaClientConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "serviceAlpha",
             url = "${service.alpha.url}",
             configuration = ServiceAlphaClientConfig.class)
public interface ServiceAlphaFeignClient extends ServiceAlpha {
}
