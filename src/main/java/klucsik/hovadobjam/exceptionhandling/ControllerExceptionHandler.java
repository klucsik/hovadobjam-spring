package klucsik.hovadobjam.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    public ControllerExceptionHandler(){
        super();
    }

    @ExceptionHandler(value= MyResourceNotFoundException.class)
    protected ResponseEntity<String> handleNotFound(MyResourceNotFoundException exception){
        final String bodyOfResponse= exception.getMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.NOT_FOUND);
    }
}
