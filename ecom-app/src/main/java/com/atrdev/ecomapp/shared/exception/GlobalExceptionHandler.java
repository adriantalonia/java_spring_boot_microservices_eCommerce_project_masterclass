package com.atrdev.ecomapp.shared.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFound(
            ResourceNotFoundException ex, HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());

        problem.setTitle("Resource Not Found");
        //problem.setType(URI.create("https://api.example.com/errors/not-found"));
        problem.setInstance(URI.create(request.getRequestURI()));

        // Non-standard extension fields (RFC 9457 allows this)
        problem.setProperty("resourceName", ex.getResourceName());
        problem.setProperty("resourceId", ex.getResourceId());
        problem.setProperty("timestamp", Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problem);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleValidationErrors(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Request contains invalid fields");

        problem.setTitle("Validation Failed");
        problem.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> fieldErrors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                fieldErrors.put(error.getField(), error.getDefaultMessage())
        );

        problem.setProperty("fieldErrors", fieldErrors);
        problem.setProperty("timestamp", Instant.now());

        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ProblemDetail> handleConstraintViolation(
            ConstraintViolationException ex, HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, "Invalid request parameter");

        problem.setTitle("Constraint Violation");
        problem.setInstance(URI.create(request.getRequestURI()));

        Map<String, String> violations = new LinkedHashMap<>();
        ex.getConstraintViolations().forEach(v -> {
            String field = v.getPropertyPath().toString();
            field = field.contains(".") ? field.substring(field.lastIndexOf('.') + 1) : field;
            violations.put(field, v.getMessage());
        });

        problem.setProperty("violations", violations);
        problem.setProperty("timestamp", Instant.now());

        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ProblemDetail> handleInsufficientStock(
            InsufficientStockException ex, HttpServletRequest request) {

        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());

        problem.setTitle("Insufficient Stock");
        problem.setInstance(URI.create(request.getRequestURI()));

        problem.setProperty("productId", ex.getProductId());
        problem.setProperty("availableStock", ex.getAvailableStock());
        problem.setProperty("requestedQuantity", ex.getRequestedQuantity());
        problem.setProperty("timestamp", Instant.now());

        return ResponseEntity.badRequest().body(problem);
    }

}
