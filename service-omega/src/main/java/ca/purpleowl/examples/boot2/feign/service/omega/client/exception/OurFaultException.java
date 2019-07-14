package ca.purpleowl.examples.boot2.feign.service.omega.client.exception;

public class OurFaultException extends AbstractClientException {
    public OurFaultException(int value, String reason) {
        super(value, reason);
    }
}
