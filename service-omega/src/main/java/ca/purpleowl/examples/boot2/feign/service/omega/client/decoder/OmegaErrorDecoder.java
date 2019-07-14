package ca.purpleowl.examples.boot2.feign.service.omega.client.decoder;

import ca.purpleowl.examples.boot2.feign.service.omega.client.exception.OurFaultException;
import ca.purpleowl.examples.boot2.feign.service.omega.client.exception.TheirFaultException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class OmegaErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());

        if(status.is4xxClientError()) {
            return new OurFaultException(status.value(), response.reason());
        }

        if(status.is5xxServerError()) {
            return new TheirFaultException(status.value(), response.reason());
        }

        return new TheirFaultException(status.value(), response.reason());
    }
}
