package app.planner.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiErrorResponse> handleStatusException(
            ResponseStatusException ex,
            HttpServletRequest request) {

        HttpStatus httpStatus = HttpStatus.resolve(ex.getStatusCode().value());
        String reasonPhrase = httpStatus != null
                ? httpStatus.getReasonPhrase()
                : ex.getStatusCode().toString();

        ApiErrorResponse error = new ApiErrorResponse(
                Instant.now(),
                ex.getStatusCode().value(),
                reasonPhrase,
                ex.getReason(),
                request.getRequestURI());

        return ResponseEntity
                .status(ex.getStatusCode())
                .body(error);
    }
}
