package uk.gov.di.gpg45engine.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@Slf4j
@ControllerAdvice
public class ControllerConfig {

    @ExceptionHandler(value = {DecodingException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception exception) {
        log.warn("HTTP 400 Bad Request", exception);

        var error = new ErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @Data
    @RequiredArgsConstructor
    static class ErrorResponse {
        final HttpStatus status;
        final String message;
        Instant timestamp = Instant.now();
    }
}
