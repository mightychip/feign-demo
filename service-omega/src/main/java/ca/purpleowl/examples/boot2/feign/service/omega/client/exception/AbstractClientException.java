package ca.purpleowl.examples.boot2.feign.service.omega.client.exception;

public abstract class AbstractClientException extends Exception {
    private final int statusCode;

    public AbstractClientException(int statusCode, String statusMessage) {
        super(statusMessage);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
