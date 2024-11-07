package br.com.jvcardoso.desafio.messgage;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ErrorResponse(int status, String mensagem, List<ErrorField> erros) {
    
    public static ErrorResponse errorResponse(String mensagem) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
        
    }

    public static ErrorResponse errorResponseStatus(int status, String mensagem) {
        return new ErrorResponse(status, mensagem, List.of());
        
    }

    public static ErrorResponse conflict(String mensagem) {
        return new ErrorResponse(HttpStatus.CONFLICT.value(), mensagem, List.of());
       
    }
}
