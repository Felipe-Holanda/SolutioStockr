package com.techsolutio.solutiostockr.exceptions;

import java.net.http.HttpHeaders;
import java.util.HashMap;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.validation.ObjectError;
import org.springframework.validation.FieldError;

@ControllerAdvice
public class GlobalExceptionHandler {


    //Responsável por lidar com os erros de validação de campos, criados nos DTO's
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request){
        final HashMap<String, HashMap<String, String>> returnObject = new HashMap<>();
        final HashMap<String, String> allErrorsObject = new HashMap<>();


        final List<ObjectError> allErrorsList = ex.getAllErrors();
        
        allErrorsList.forEach((error) -> {
            if(error instanceof FieldError){
                allErrorsObject.put(((FieldError) error).getField(), error.getDefaultMessage());
            }else{
                allErrorsObject.put(error.getObjectName(), error.getDefaultMessage());
            }
        });

        returnObject.put("message", allErrorsObject);

        return new ResponseEntity<>(returnObject, HttpStatus.BAD_REQUEST);
    }
    
    //Responsável por lidar com os erros de rota da aplicação
    @ExceptionHandler({AppException.class})
    public ResponseEntity<Object> handleAppException(final AppException ex){
        final HashMap<String, String> returnObject = new HashMap<>();
        returnObject.put("message", ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(returnObject);
    }

    //Responsável por lidar com os erros internos da aplicação
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex){
        final HashMap<String, String> returnObject = new HashMap<>();
        returnObject.put("message", "Internal server error");
        
        System.out.println(ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnObject);
    }

}
