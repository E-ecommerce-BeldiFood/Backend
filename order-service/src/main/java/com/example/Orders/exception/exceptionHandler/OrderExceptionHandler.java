package com.example.Orders.exception.exceptionHandler;

import com.example.Orders.exception.InvalidOrderException;
import com.example.Orders.exception.OrderNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Date;

@RestControllerAdvice
public class OrderExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(OrderNotFoundException ex){
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }

    @ExceptionHandler(InvalidOrderException.class)
    public ResponseEntity<?> handleUserNotFound(InvalidOrderException ex){
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }

}
