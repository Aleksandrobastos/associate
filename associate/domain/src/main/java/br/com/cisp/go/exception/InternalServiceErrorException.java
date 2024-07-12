package br.com.cisp.go.exception;

public class InternalServiceErrorException extends RuntimeException {

    public InternalServiceErrorException() {
    }

    public InternalServiceErrorException(String message) {
        super(message);
    }

    public InternalServiceErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public InternalServiceErrorException(Throwable cause) {
        super(cause);
    }
}
