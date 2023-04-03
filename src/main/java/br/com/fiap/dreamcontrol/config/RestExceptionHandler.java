package br.com.fiap.dreamcontrol.config;

import br.com.fiap.dreamcontrol.errors.RestConstraintViolationError;
import br.com.fiap.dreamcontrol.errors.RestValidationError;
import br.com.fiap.dreamcontrol.exceptions.RestUnauthorizedException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.UnexpectedTypeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestExceptionHandler {

    Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<RestValidationError>> handlemethodArgumentNotValidException(MethodArgumentNotValidException e){
        log.error("Erro de argumento inválido");
        List<RestValidationError> errors = new ArrayList<>();
        e.getFieldErrors().forEach(v -> errors.add(new RestValidationError(400, v.getField(), v.getDefaultMessage())));
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<RestConstraintViolationError>> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("Erro de argumento inválido");
        List<RestConstraintViolationError> errors = new ArrayList<>();
        e.getConstraintViolations().forEach(constraintViolation -> errors.add(new RestConstraintViolationError(400, constraintViolation.getInvalidValue(),constraintViolation.getMessage())));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(RestUnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorizedException(RestUnauthorizedException e) {
        log.error("Usuário ou senha inválido", e);
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException e) {
        log.error("Objeto não encontrado", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("Restrição de chave única violada", e);
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("Valor de campo inválido", e);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Erro de mensagem HTTP inválida", ex);
        return ResponseEntity.badRequest().body("Erro de mensagem HTTP inválida: " + ex.getMessage());
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity<Object> handleUnexpectedTypeException(UnexpectedTypeException ex) {
        log.error("Erro de tipo inesperado", ex);
        return ResponseEntity.badRequest().body("Erro de tipo inesperado: " + ex.getMessage());
    }
}