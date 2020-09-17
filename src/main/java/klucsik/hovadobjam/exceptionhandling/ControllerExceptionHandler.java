package klucsik.hovadobjam.exceptionhandling;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.HashMap;
import java.util.Map;

//https://github.com/eugenp/tutorials/blob/master/spring-boot-rest/src/main/java/com/baeldung/web/error/RestResponseEntityExceptionHandler.java

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

    @ExceptionHandler(value= InvalidInputException.class)
    protected ResponseEntity<String> handleInvalidInput(Exception exception){
        final String bodyOfResponse= "Invalid input: " + exception.getMessage();
        return new ResponseEntity<>(bodyOfResponse, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
