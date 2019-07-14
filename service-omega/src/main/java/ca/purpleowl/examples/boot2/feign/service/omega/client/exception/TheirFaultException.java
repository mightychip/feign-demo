package ca.purpleowl.examples.boot2.feign.service.omega.client.exception;

public class TheirFaultException extends AbstractClientException {
    public TheirFaultException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
}
