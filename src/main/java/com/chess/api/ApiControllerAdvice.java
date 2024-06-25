package com.chess.api;

import com.chess.api.core.exception.UnauthorizedAccessException;
import com.chess.api.view.response.ErrorView;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {

    @Value("${api.version}")
    private String apiVersion;

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorView> handleUnauthorizedAccess(
            HttpServletRequest request,
            UnauthorizedAccessException e) {
        final ErrorView response = new ErrorView(
                this.apiVersion,
                "Unauthorized access",
                e.getErrorCode(),
                e.getMessage(),
                request.getServletPath(),
                LocalDateTime.now().toString()
        );
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}
