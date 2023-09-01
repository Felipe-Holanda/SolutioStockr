package com.techsolutio.solutiostockr.exceptions;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler({AppException.class})
    public ResponseEntity<Object> handleAppException(final AppException ex){
        final HashMap<String, String> returnObject = new HashMap<>();
        returnObject.put("message", ex.getMessage());

        return ResponseEntity.status(ex.getStatus()).body(returnObject);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleInternal(final RuntimeException ex){
        final HashMap<String, String> returnObject = new HashMap<>();
        returnObject.put("message", "Internal server error");
        
        System.out.println(ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(returnObject);
    }

}
