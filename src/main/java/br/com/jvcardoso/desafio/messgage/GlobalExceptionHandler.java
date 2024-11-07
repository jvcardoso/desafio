package br.com.jvcardoso.desafio.messgage;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.jvcardoso.desafio.exception.AlunoNotFoundException;
import br.com.jvcardoso.desafio.exception.CursoNotFoundException;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        //System.out.println(e);
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<ErrorField> listaErros = fieldErrors
                .stream()
                .map(fe -> new ErrorField(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse erroResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de Validação", listaErros);
        return new ResponseEntity<>(erroResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        ErrorResponse errorResponse = ErrorResponse
                .errorResponse( "Formato do JSON recebido inválido.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception e) {
        System.err.println("Erro não tratado: " + e.getMessage());
        ErrorResponse errorResponse = ErrorResponse
                .errorResponse("Ocorreu um erro interno ao processar a requisição solicitada.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
    @ExceptionHandler(AlunoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAlunoNotFoundException(AlunoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ErrorResponse.errorResponse( e.getMessage()));
    }

    @ExceptionHandler(CursoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCursoNotFoundException(CursoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ErrorResponse.errorResponse(e.getMessage()));
    }

   
}
