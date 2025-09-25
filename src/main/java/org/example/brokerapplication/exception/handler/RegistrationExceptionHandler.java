package org.example.brokerapplication.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.example.brokerapplication.exception.model.ErrorResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RegistrationExceptionHandler {

    @ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException ex, HttpServletRequest request) {
        String constraintName = ex.getConstraintName();

        if (constraintName.equalsIgnoreCase("uq_customer_accounts_email") ||
            constraintName.equalsIgnoreCase("uq_customer_accounts_login")) {
            ErrorResponse response = new ErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.CONFLICT.value(),
                    "Conflict",
                    "Этот логин или адрес электронной почты использовать нельзя",
                    request.getRequestURI()
            );

            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        } else {
            return null;
        }
    }
}
