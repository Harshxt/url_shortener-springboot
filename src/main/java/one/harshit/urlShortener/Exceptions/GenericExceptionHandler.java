package one.harshit.urlShortener.Exceptions;

import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import one.harshit.urlShortener.Model.ErrorResponse;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<?> handleUrlNotFoundException(UrlNotFoundException exception) {
        ErrorResponse response = new ErrorResponse(LocalDate.now(), exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


}
