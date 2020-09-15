package klucsik.hovadobjam.exceptionhandling;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    public ControllerExceptionHandler(){
        super();
    }

    @ExceptionHandler(value= NotFoundException.class)
    protected ResponseEntity<String> handleNotFound(Exception exception){
        final String bodyOfResponse= "Not found: " + exception.getMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.NOT_FOUND);
    }
}
