package br.com.fiap.dreamcontrol.config;

import br.com.fiap.dreamcontrol.errors.RestConstraintViolationError;
import br.com.fiap.dreamcontrol.errors.RestValidationError;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RestValidationError>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        log.error("erro de argumento inválido");
        List<RestValidationError> errors = new ArrayList<>();
        e.getFieldErrors().forEach(v -> errors.add(new RestValidationError(400, v.getField(), v.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<RestConstraintViolationError>> ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error("erro de argumento inválido");
        List<RestConstraintViolationError> errors = new ArrayList<>();
        e.getConstraintViolations().forEach(constraintViolation -> errors.add(new RestConstraintViolationError(400, constraintViolation.getInvalidValue(),constraintViolation.getMessage())));
        return ResponseEntity.badRequest().body(errors);
    }
}
