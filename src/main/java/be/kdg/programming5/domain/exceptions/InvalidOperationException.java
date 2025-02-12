package be.kdg.programming5.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//TODO
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Hotel Operation")
public class InvalidOperationException extends RuntimeException {
    private InvalidOperationException(String message) {
        super(message);
    }
}
