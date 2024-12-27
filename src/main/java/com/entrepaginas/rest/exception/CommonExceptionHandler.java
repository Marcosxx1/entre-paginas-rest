package com.entrepaginas.rest.exception;

import com.entrepaginas.rest.exception.error.ErrorResponse;
import com.entrepaginas.rest.exception.error.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class CommonExceptionHandler {

    private final MessageSourceAccessor messageSourceAccessor;

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleApiException(CustomException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getTitle(), ex.getDetail());
        return new ResponseEntity<>(errorResponse, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        for (ObjectError error : validationErrorList) {
            String fieldName = ((FieldError) error).getField();
            String validationMessage = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMessage);
        }

        var validationErrorResponse = new ValidationErrorResponse(
                messageSourceAccessor.getMessage("Validation error"),
                messageSourceAccessor.getMessage("Validation error"),
                validationErrors);
        return new ResponseEntity<>(validationErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(
            AccessDeniedException ex, HttpServletRequest request) {

        logAccessDenied(request);

        ErrorResponse errorResponse = new ErrorResponse(
                messageSourceAccessor.getMessage("Forbidden"), messageSourceAccessor.getMessage("Forbidden"));
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(HttpServletRequest request) {

        logAccessDenied(request);

        ErrorResponse errorResponse = new ErrorResponse(
                messageSourceAccessor.getMessage("Not authenticated"),
                messageSourceAccessor.getMessage("Authentication is required to access resource"));
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Exception {} was thrown: ", ex.getClass(), ex);
        String message = messageSourceAccessor.getMessage("INTERNAL SERVER ERROR");
        ErrorResponse errorResponse = new ErrorResponse(message, message);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static void logAccessDenied(HttpServletRequest request) {
        var user = Objects.requireNonNullElse(request.getRemoteUser(), "unknown");

        log.error("Access denied. URI: {} {}, User: {}", request.getMethod(), request.getRequestURI(), user);
    }
}
