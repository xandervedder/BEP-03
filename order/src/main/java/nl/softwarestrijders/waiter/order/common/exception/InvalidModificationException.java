package nl.softwarestrijders.waiter.order.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "This modification cannot be done")
public class InvalidModificationException extends RuntimeException {
    public InvalidModificationException(String errorMessage) {
        super(errorMessage);
    }
}
