package com.techsolutio.solutiostockr.exceptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
    
    // Responsável por lidar com os erros de rota da aplicação
    @ExceptionHandler({AppException.class})
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ResponseEntity<Object> handleAppException(AppException ex) {
        HashMap<String, String> returnObject = new HashMap<>();
        returnObject.put("message", ex.getMessage());
        
        return ResponseEntity.status(ex.getStatus()).body(returnObject);
    }
    
    //Lida com as validações dos DTO's
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

                List<String> errors = extractFieldErrors(ex);

                Map<String, Object> response = new HashMap<>();
                response.put("message", "Validation failed");
                response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

    // Responsável por lidar com os erros internos da aplicação
    @Order(Ordered.LOWEST_PRECEDENCE)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal(Exception ex) {
        HashMap<String, String> returnObject = new HashMap<>();
        returnObject.put("message", "Internal server error");

        System.out.println(ex.getLocalizedMessage() + "\n" +ex.getCause() + "\n" + ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnObject);
    }


    //Método para extrair os erros.
    private List<String> extractFieldErrors(MethodArgumentNotValidException ex) {
    List<String> errors = new ArrayList<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> {
        errors.add(error.getDefaultMessage());
    });
    return errors;
}
}
