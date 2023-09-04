package com.techsolutio.solutiostockr.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppException extends RuntimeException {
    
    private HttpStatus status;
    
    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public static ResponseEntity<Object> pop(String message, HttpStatus status){
        return ResponseEntity.status(status).body(new AppException(message, status));
    }

}
