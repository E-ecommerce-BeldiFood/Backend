package ma.beldifood.User.exception.GlobalExceptionHandler;

import ma.beldifood.User.exception.EmailAlreadyExistsException;
import ma.beldifood.User.exception.ErrorBody;
import ma.beldifood.User.exception.EmptyEntityException;
import ma.beldifood.User.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleUserNotFound(EntityNotFoundException ex){
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_FOUND, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }

    @ExceptionHandler(EmptyEntityException.class)
    public ResponseEntity<?> emptyEntryExceptionHandler(EmptyEntityException ex){
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<?> handleAlreadyExistsException(EmailAlreadyExistsException ex) {
        ErrorBody errorBody = new ErrorBody(new Date(), HttpStatus.NOT_ACCEPTABLE, Collections.singletonList(ex.getMessage()));
        return new ResponseEntity<>(errorBody, errorBody.getStatus());
    }



    }

