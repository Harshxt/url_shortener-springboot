package one.harshit.urlShortener.Model;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private LocalDate timestamp;
    private String message;
    private HttpStatus statusCode;
}
