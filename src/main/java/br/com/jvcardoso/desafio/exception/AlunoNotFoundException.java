package br.com.jvcardoso.desafio.exception;

public class AlunoNotFoundException extends RuntimeException {
    
    public AlunoNotFoundException(String message) {
        super(message);
    }
}
