package ca.purpleowl.examples.boot2.feign.service.omega.client.exception;

public class TotallyUnexpectedException extends AbstractClientException {
    public TotallyUnexpectedException(int statusCode, String statusMessage) {
        super(statusCode, statusMessage);
    }
}
