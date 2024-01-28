package br.com.fiap.techchallenge.infrastructure.persistence.util.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
