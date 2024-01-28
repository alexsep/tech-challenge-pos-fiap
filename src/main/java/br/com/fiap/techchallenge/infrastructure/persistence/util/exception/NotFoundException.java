package br.com.fiap.techchallenge.infrastructure.persistence.util.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
