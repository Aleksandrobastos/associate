package br.com.cisp.go.exception;

public class BadRequest extends RuntimeException {
    public BadRequest(final String message) {
        super(message);
    }
}
