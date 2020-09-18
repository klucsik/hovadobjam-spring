package klucsik.hovadobjam.exceptionhandling;


import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

//https://github.com/eugenp/tutorials/blob/master/spring-boot-rest/src/main/java/com/baeldung/web/error/RestResponseEntityExceptionHandler.java

//https://stackoverflow.com/questions/61846732/springboot-dto-validation

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<String> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        return getExceptionResponseEntity(HttpStatus.BAD_REQUEST, request, validationErrors);
    }

    @ExceptionHandler(value= NotFoundException.class)
    protected ResponseEntity<Object> handleNotFound(NotFoundException exception, WebRequest request){
        List<String>  errors = new ArrayList<>() ;
                 errors.add("Not found: " + exception.getMessage()) ;
        return  getExceptionResponseEntity(HttpStatus.NOT_FOUND, request, errors);
    }

    @ExceptionHandler(value= InvalidInputException.class)
    protected ResponseEntity<Object> handleInvalidInput(Exception exception, WebRequest request){
        List<String>  errors = new ArrayList<>() ;
        errors.add( "Invalid input: " + exception.getMessage());
        return  getExceptionResponseEntity(HttpStatus.BAD_REQUEST, request, errors);
    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException exception, WebRequest request) {
        List<String> validationErrors = exception.getConstraintViolations().stream().
                map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.toList());
        return getExceptionResponseEntity(HttpStatus.BAD_REQUEST, request, validationErrors);
    }

    private ResponseEntity<Object> getExceptionResponseEntity(final HttpStatus status, WebRequest request, List<String> errors) {
        final Map<String, Object> body = new LinkedHashMap<>();
        final String errorsMessage = CollectionUtils.isNotEmpty(errors) ? errors.stream().filter(StringUtils::isNotEmpty).collect(Collectors.joining(",")):status.getReasonPhrase();
        final String path = request.getDescription(false);
        body.put("TIMESTAMP", Instant.now());
        body.put("STATUS", status.value());
        body.put("ERRORS", errorsMessage);
        body.put("PATH", path);
        body.put("MESSAGE", status.getReasonPhrase());
        return new ResponseEntity<>(body, status);
    }
}
