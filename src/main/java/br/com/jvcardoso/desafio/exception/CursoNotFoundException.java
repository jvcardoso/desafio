package br.com.jvcardoso.desafio.exception;

public class CursoNotFoundException extends RuntimeException {
    public CursoNotFoundException(String message) {
        super(message);
    }
}
